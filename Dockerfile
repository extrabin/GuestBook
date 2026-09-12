# ---------- 1단계: 빌드 ----------
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# 의존성 캐시: 먼저 mvnw + pom.xml 만 복사해 다운로드
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# 소스 복사 후 빌드
COPY src/ src/
RUN ./mvnw clean package -DskipTests -B

# ---------- 2단계: 실행 ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/GuestBook-*.jar app.jar

EXPOSE 8080
# 컨테이너 헬스체크 (16장 헬스체크와 연결)
HEALTHCHECK --interval=10s --timeout=3s --retries=5 \
  CMD ["sh", "-c", "wget -qO- http://localhost:8080/health || exit 1"]

ENTRYPOINT ["java", "-jar", "app.jar"]
