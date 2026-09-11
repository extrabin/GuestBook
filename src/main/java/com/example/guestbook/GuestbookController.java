package com.example.guestbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class GuestbookController {

    private final GuestbookRepository repository;

    public GuestbookController(GuestbookRepository repository) {
        this.repository = repository;
    }

    /** 방명록 화면: 작성 폼 + 목록. */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("entries", repository.findAll());
        model.addAttribute("hostname", currentHostname());
        return "index"; // templates/index.html
    }

    /** 글 등록 후 목록으로 리다이렉트. */
    @PostMapping("/entries")
    public String create(@RequestParam String name,
                         @RequestParam String message) {
        if (StringUtils.hasText(name) && StringUtils.hasText(message)) {
            repository.add(name.trim(), message.trim());
        }
        return "redirect:/";
    }

    /**
     * 로드 밸런서·Auto Scaling의 상태 검사(13·16장)용 경로.
     * 항상 200 OK와 "OK"를 반환한다.
     */
    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "OK";
    }

    /** 어느 인스턴스가 응답했는지 화면에 보여주기 위한 호스트네임. */
    private String currentHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "unknown-host";
        }
    }
}