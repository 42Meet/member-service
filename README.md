# Member-Service

<p align="center"><img width="427" alt="스크린샷 2021-06-23 오후 2 04 44" src="https://user-images.githubusercontent.com/54574014/123038796-0572ef00-d42c-11eb-87d9-78b0bab30f91.png"></p>

<br>

## 🔍 왜 42meet인가?

42 Seoul 내 제한된 회의실 개수로 인한 멤버들 간 자리 경쟁 문제를 해소하기 위해 자동 회의실 예약 서비스를 만들고자 하였습니다. 

기존 제공되고 있는 42 API를 활용하여 42 Seoul 카뎃들이 쉽게 서비스를 이용할 수 있도록 노력하였습니다.

<BR>

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
   --network 42meet \
   kangjm2/member-service:1.0
   ```

* DockerHub 이용시

  ```
  docker pull kangjm2/member-service:1.0
  ```

<BR>


## 🔧 Tech Stack

<p align="center"><img width="800" height="600" alt="tech-stack" src="https://user-images.githubusercontent.com/54574014/122851316-b3f23380-d349-11eb-8a9d-8081e90916fc.png"></p>

<BR>

## 🪜 Architecture

<p align="center"><img width="800" alt="스크린샷 2021-06-23 오후 1 22 01" src="https://user-images.githubusercontent.com/54574014/123035429-46680500-d426-11eb-8616-1e64e8c330ba.png">

   </p>

<p align ="center"><img width="800" alt="스크린샷 2021-06-23 오후 1 53 30" src="https://user-images.githubusercontent.com/54574014/123038515-82ea2f80-d42b-11eb-9326-6e20204a3cf5.png">

</p>

<br>

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

<BR>

## ⚙️  DB Design

<p align="center"><img width="600" height="300" alt="스크린샷 2021-06-22 오후 3 04 29" src="https://user-images.githubusercontent.com/54574014/122872348-4d7e0d00-d36b-11eb-8701-fb8008f1c461.png">

</p>

<BR>

## 📚 API Specification

<img width="1420" alt="스크린샷 2021-06-22 오후 3 49 23" src="https://user-images.githubusercontent.com/54574014/122877345-7903f600-d371-11eb-82d0-4e39fdd8c604.png">

<BR>

## 👨‍👩‍👦‍👦 Author

⭐️ **42seoul** ⭐️

> 👳🏻‍♀️ [강재민](https://github.com/jaeminkang)<br />
> 👨🏻‍💻 [심의석](https://github.com/simsulison)

