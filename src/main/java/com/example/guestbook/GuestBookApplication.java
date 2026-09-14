package com.example.guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 방명록 애플리케이션 진입점.
 *
 * GitHub Actions 자동 배포(CI/CD) 파이프라인 동작 확인용 커밋.
 * (재트리거 3회차 - 시크릿 정리 후 신규 실행)
 */
@SpringBootApplication
public class GuestBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestBookApplication.class, args);
	}

}
