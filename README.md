# Member-Service

### 🕹 실행 방법

1. Gradle을 활용한 빌드 및 패키징

   ```
   ./gradlew clean build
   ```

2. Dockerizing

   ```
   docker build -t kangjm2/member-service:1.0 .
   ```

3. Container 실행

   ```
   docker run -d -p 8080:8080 --name member-service \
   -e "token.secret=my_secret_token" \
   kangjm2/memberservice:1.0
   ```

* DockerHub 이용시

  ```
  docker pull kangjm2/memberservice:1.0
  ```

