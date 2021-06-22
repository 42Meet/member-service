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



## 🔧 Tech Stack

<img width="645" alt="tech-stack" src="https://user-images.githubusercontent.com/54574014/122851316-b3f23380-d349-11eb-8a9d-8081e90916fc.png">


## 📕 디렉토리 구조

```
📁member-service
└── 📁src
    └──  📁main
         ├── 📁java
         │    ├── 📁config
         │    ├── 📁controller
         │    ├── 📁domain
         │    ├── 📁dto  
         │    ├── 📁security
         │    └── 📁utils
         └── 📁resources
```

## 👨‍👩‍👦‍👦 Author

⭐️ **42seoul** ⭐️
> 👳🏻‍♀️ [강재민](https://github.com/jaeminkang)<br />
> 👨🏻‍💻 [심의석](https://github.com/simsulison)



