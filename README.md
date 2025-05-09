# 🎧 Studyfy - 스터디 매칭 플랫폼

스터디 모집자와 참여자를 연결해주는 온라인 스터디 매칭 플랫폼입니다.  
**Studyfy(Study + Spotify)** 는 스터디를 연결해주는 서비스라는 의미를 담고 있습니다.

---

## ✅ 주제 및 팀원

- **팀명**: Studyfy  
- **주제**: 스터디 플랫폼 개발  
- **팀원**: 김선익, 강희진, 이승경, 신동준

---

## 🛠️ 기술 스택

| 분류 | 사용 기술 |
|------|-----------|
| Backend | Java 17.2, Spring Boot 3.4.5, JPA, MySQL 8.0 |
| Frontend | React, Vite, Yarn |
| CI/CD | GitHub Actions, Vercel, AWS EC2 |
| IDE | IntelliJ |

---

## 📱 주요 기능

| 화면 | 사용자 | 주요 기능 |
|------|--------|-----------|
| 개설 화면 | 개설자 | 스터디 개설 |
| 신청자 관리 | 개설자 | 신청자 승인/거절 |
| 검색 화면 | 참여자 | 스터디 검색 |
| 상세 화면 | 참여자 | 스터디 소개 및 운영자/참여자 정보 확인 |
| 로그인 | 공통 | JWT 로그인/회원가입 기능 |

### 🔍 기능 상세 설명

- **로그인 및 인증**
  - 이메일과 비밀번호를 통한 로그인 요청 시, 일치 여부 확인 후 JWT 토큰 생성 및 반환
  - 로그인/회원가입을 제외한 모든 요청은 JWT 토큰 기반 인증

- **스터디 개설**
  - 목표, 주제, 방식, 기간 등을 입력하여 스터디 생성
  - 작성자 본인만 수정/삭제 가능하며, 일치하지 않을 경우 예외 처리

- **스터디 신청 및 승인**
  - 참여자가 원하는 스터디에 신청 → 개설자가 승인 시 참여 가능

---

## 🗃️ ERD
![해커톤ERD](https://github.com/user-attachments/assets/7e1c3097-d476-4b76-aa64-f287b0b285c8)



📌 **ERD 링크**: [ERD Cloud 바로가기](https://www.erdcloud.com/d/vhzwRYkAWm8xpwmf4)

---

## 🔌 API 명세서

### 🔐 유저 관련

| Method | Endpoint | 설명 |
|--------|----------|------|
| POST | `/api/v1/member/sign` | 회원가입 |
| POST | `/api/v1/login` | 로그인 (JWT 인증) |

### 📘 스터디 관련

| Method | Endpoint | 설명 |
|--------|----------|------|
| GET | `/api/v1/studies/{id}` | 스터디 상세 조회 |
| GET | `/api/v1/studies` | 전체 스터디 조회 |
| POST | `/api/v1/studies` | 스터디 개설 |
| PUT | `/api/v1/studies/{id}` | 스터디 수정 |
| DELETE | `/api/v1/studies/{id}` | 스터디 삭제 |
| POST | `/api/v1/studies/{id}/apply` | 스터디 참여 신청 |
| GET | `/api/v1/studies/search` | 스터디 검색 |

### 🧾 신청자 관리

| Method | Endpoint | 설명 |
|--------|----------|------|
| POST | `/api/v1/applications/study/{studyId}/apply` | 스터디 참여 신청 |
| GET | `/api/v1/applications/study/{studyId}/pending` | 신청자 목록 조회 |
| POST | `/api/v1/applications/{applicationId}/approve` | 신청 승인 |
| POST | `/api/v1/applications/{applicationId}/reject` | 신청 거절 |

---

## 🐞 오류 및 해결 방안

### 🔧 CORS 관련 이슈

- **문제**: 프론트엔드 (React - Vercel 배포)에서 백엔드 (Spring Boot - EC2 서버)로 API 요청 시 다음과 같은 오류 발생
- **해결**: `HttpSecurity`에서 CORS 설정을 명시적으로 열어 모든 도메인 또는 특정 도메인 허용

```java
http.cors(cors -> cors.configurationSource(request -> {
    var config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("*")); // 또는 프론트 도메인만
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    config.setAllowedHeaders(List.of("*"));
    return config;
}));
