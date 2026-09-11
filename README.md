# GuestBook 📖

Spring Boot 기반 방명록 웹 애플리케이션. 이름과 메시지를 남기면 최신순으로 목록에 표시됩니다.
AWS EC2 배포 실습을 위한 예제 프로젝트입니다.

## 기술 스택

- Java 17
- Spring Boot 4.1.1 (Spring MVC, Thymeleaf)
- Tailwind CSS (CDN)
- Maven

## 실행 방법 (로컬)

### 요구 사항
- Java 17 이상

### 빌드 & 실행

```bash
# Maven Wrapper 사용 (Maven 별도 설치 불필요)
./mvnw spring-boot:run
```

또는 jar로 빌드 후 실행:

```bash
./mvnw clean package
java -jar target/GuestBook-0.0.1-SNAPSHOT.jar
```

브라우저에서 http://localhost:8080/ 접속.

### 주요 경로
| 경로 | 설명 |
|------|------|
| `GET /` | 방명록 화면 (작성 폼 + 목록) |
| `POST /entries` | 글 등록 후 목록으로 리다이렉트 |
| `GET /health` | 상태 검사용, 항상 `200 OK` / `"OK"` 반환 |

## 설정

`src/main/resources/application.properties`

| 속성 | 기본값 | 설명 |
|------|--------|------|
| `server.port` | `8080` | 서버 포트 |
| `guestbook.max-entries` | `100` | 메모리에 유지할 최대 메시지 수 |

> ⚠️ 메시지는 **메모리(인스턴스 내부)** 에 저장됩니다. 서버를 재시작하거나 여러 대로 늘리면 데이터가 공유되지 않습니다.

## AWS EC2 배포

EC2에서 clone → 빌드 → 실행하는 전체 절차는 [DEPLOY.md](DEPLOY.md)를 참고하세요.
