# 🏦 Piggybank  
**개인 금융 데이터 자동 수집 및 소비 분석 플랫폼**

---

## 📘 프로젝트 개요
**Piggybank**는 사용자의 은행 계좌 거래내역을 자동으로 수집하고,  
카테고리별 소비 패턴을 분석하여 **한도 초과 여부**를 실시간으로 파악하는 개인 금융 관리 시스템입니다.  

> 💡 목표  
> - 금융 데이터를 자동으로 수집 (CODEF API)  
> - 거래내역을 카테고리별로 분석  
> - 한도 초과/소비 패턴을 실시간 알림  

---

## ⚙️ 기술 스택

| 구분 | 기술 |
|------|------|
| **언어** | Java 21 |
| **프레임워크** | Spring Boot 3.5.4 |
| **DB / Cache** | MySQL 8.1, Redis |
| **빌드 도구** | Gradle |
| **API 통신** | WebClient / RestTemplate |
| **Infra / 환경 구성** | Docker Compose, .env, application.yml |
| **외부 연동** | CODEF Open Banking API |
| **보안** | Spring Security, JWT |
| **기타** | FastAPI (Python, 분석 전용 모듈 연결) |

---

## 🧩 시스템 아키텍처 (요약)
```
[Client]
   ↓
[Spring Boot - Application Layer]
   ├─ Controller (REST API)
   ├─ Service / UseCase
   ├─ Domain (Account, Transaction, Profile, Category, Member)
   └─ Global (Email, External API, Config)
   ↓
[FastAPI - Python 분석 서버]
   ↓
[MySQL]  [Redis]
```

---

## 🧠 주요 기능

| 구분 | 설명 |
|------|------|
| **CODEF API 연동** | OAuth 2.0 인증 기반으로 외부 은행 거래내역을 수집 |
| **거래내역 수집 / 정제** | 중복 제거 및 기간별 거래 합계 계산 |
| **카테고리 분석** | FastAPI 분석 모듈을 통해 소비 카테고리별 통계 계산 |
| **소비 한도 비교** | Profile(consumption)과 Account(limit)을 비교하여 한도 초과 판단 |
| **이메일 알림** | 한도 초과, 비밀번호 변경 등 사용자 이벤트 발생 시 이메일 자동 발송 |
| **JWT 인증 / 사용자 인증** | Spring Security + JWT 기반 세션리스 인증 구조 |
| **환경 설정** | Redis 세션 캐싱, Docker 기반 컨테이너 구성 |

---

## 🏗️ 도메인 구조 (DDD 기반)

| 도메인 | 주요 책임 |
|---------|-----------|
| **Account** | 계좌 식별, 한도 설정, 거래 연동 |
| **Transaction** | 거래 내역 저장, 거래 이벤트 발행 |
| **Profile** | 소비 한도, 누적 소비 금액 관리 |
| **Category** | 소비 카테고리 분석 및 통계 |
| **Member** | 회원 정보 관리, 비밀번호 변경 |
| **Global** | 이메일 전송, 외부 API, 전역 설정 |

---

## 💼 팀 역할 분담

| 이름 | 역할 | 주요 기여 영역 |
|------|------|----------------|
| **오재근** | Backend | Account(계좌 저장 및 CodeF ConnectedId 저장), Transaction(거래내역 조회 및 저장), Auth(회원 관리), Profile(소비 한도 및 목표 금액 설정 및 비교), CODEF, JWT 인증 구조 설계 |
| **배수빈** | Backend / DevOps | Category(소비 분석), Transaction(카테고리 통계), Member(비밀번호 변경)<br>Redis/Docker 환경 설정, Python FastAPI 연결, Global 모듈(email, external) |
| **옥진서** | Backend | Member 관리, 로그인/회원가입 인증 기능 구현 |

---

## 🚀 실행 방법

### 1️⃣ 환경 변수 설정 (`.env`)
```env
CODEF_CLIENT_KEY=...
CODEF_SECRET_KEY=...
CODEF_ACCESS_TOKEN_URL=https://oauth.codef.io/oauth/token
```

### 2️⃣ Docker Compose 실행
```bash
docker-compose up -d --build
```


### 5️⃣ API 예시
```bash
POST /api/v1/transaction
{
  "userId": "123e4567-e89b-12d3-a456-426614174000",
  "accountId": "uuid",
  "startDate": "240901",
  "endDate": "240930"
}
```

---

## 🧾 트러블슈팅 & 개선 사항

| 문제 | 원인 및 해결 |
|------|---------------|
| **JWT 쿠키 저장 문제** | `SameSite=None; Secure` 옵션 추가로 HTTPS 환경에서 세션 유지 |
| **CODEF API 401 오류** | Access Token 자동 재발급 로직 추가 |
| **Circular Reference (빈 주입 오류)** | Domain Event Listener와 Service 의존 분리 |

---

## 🌱 향후 개선 방향

- FastAPI 분석 모듈의 자동 학습(ML) 기반 소비 패턴 예측  
- 처리 효율성 증가

---

## 🧑‍💻 개발자 정보

**팀 Piggybank**  
- 오재근 • 배수빈 • 옥진서  
- GitHub: [Stack-Builder2/piggybank](https://github.com/Stack-Builder2/piggybank)  

