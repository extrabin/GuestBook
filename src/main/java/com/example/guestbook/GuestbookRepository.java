package com.example.guestbook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 메모리에 방명록을 저장하는 저장소.
 * ⚠️ 이 데이터는 인스턴스 안에만 있습니다. 서버가 여러 대가 되면(13장)
 *    서버마다 목록이 달라지는 문제가 드러납니다. 그때 상태를 서버 밖(DB 등)으로 빼야 합니다.
 */
@Repository
public class GuestbookRepository {

    private final Deque<GuestEntry> entries = new ConcurrentLinkedDeque<>();
    private final int maxEntries;

    public GuestbookRepository(@Value("${guestbook.max-entries:100}") int maxEntries) {
        this.maxEntries = maxEntries;
    }

    /** 새 글을 맨 앞에 추가하고, 상한을 넘으면 오래된 것을 버린다. */
    public void add(String name, String message) {
        entries.addFirst(new GuestEntry(name, message, LocalDateTime.now()));
        while (entries.size() > maxEntries) {
            entries.pollLast();
        }
    }

    /** 최신순 목록(수정 불가). */
    public List<GuestEntry> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(entries));
    }
}