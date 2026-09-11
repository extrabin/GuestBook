package com.example.guestbook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** 방명록 한 줄. 이름 + 메시지 + 작성 시각. */
public record GuestEntry(String name, String message, LocalDateTime createdAt) {

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /** 템플릿에서 쓰기 좋은 형식의 시각 문자열. */
    public String formattedTime() {
        return createdAt.format(FMT);
    }
}