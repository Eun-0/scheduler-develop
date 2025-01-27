# Ch 3. 일정 관리 앱 Develop

## 0. 개발 조건

- 모든 테이블은 고유 식별자(ID)를 가집니다.
- `3 Layer Architecture` 에 따라 각 Layer의 목적에 맞게 개발합니다.
- CRUD 필수 기능은 모두 데이터베이스 연결 및  `JPA`를 사용해서 개발합니다.
- `JDBC`와 `Spring Security`는 사용하지 않습니다.
- 인증/인가 절차는 `JWT`를 활용하여 개발합니다.
- JPA의 연관관계는 `양방향`으로 구현합니다.

---

## 1. API 명세서
### 일정
| 기능         | Method | URL                         | Request                   | Response   | Return                        |
|------------|:------:|-----------------------------|---------------------------|------------|-------------------------------|
| 일정 생성      |  POST  | /api/schedules              | RequestBody               | 생성된 일정 정보  | ScheduleResponse              |
| 선택한 일정 조회  |  GET   | /api/schedules/{scheduleId} | PathVariable              | 선택한 일정 정보  | ScheduleResponse              |
| 페이징된 일정 조회 |  GET   | /api/schedules              | RequestParam              | 페이징된 일정 정보 | Page <SchedulePagingResponse> |
| 선택한 일정 수정  |  PUT   | /api/schedule/{scheduleId}  | PathVariable, RequestBody | 수정된 일정 정보  | ScheduleResponse              |
| 선택한 일정 삭제  | DELETE | /api/schedules/{scheduleId} | PathVariable              | -          | -                             |

### 댓글
| 기능               | Method | URL                                              | Request                                 | Response  | Return                 |
|:-----------------|:------:|--------------------------------------------------|-----------------------------------------|-----------|------------------------|
| 댓글 생성            |  POST  | /api/schedules/{scheduleId}/comments             | PathVariable, RequestBody               | 생성된 댓글 정보 | CommentResponse        |
| 선택한 일정의 전체 댓글 조회 |  GET   | /api/schedules/{scheduleId}/comments             | PathVariable                            | 모든 댓글 정보  | List <CommentResponse> |
| 선택한 댓글 수정        |  PUT   | /api/schedules/{scheduleId}/comments/{commentId} | PathVariable, PathVariable, RequestBody | 수정된 댓글 정보 | CommentResponse        |
| 선택한 댓글 삭제        | DELETE | /api/schedules/{scheduleId}/comments/{commentId} | PathVariable, PathVariable              | -         | -                      |

### 유저
| 기능           | Method | URL                 | Request                   | Response  | Return       |
|--------------|:------:|---------------------|---------------------------|-----------|--------------|
| 유저 회원가입      |  POST  | /api/users/signup   | RequestBody               | 가입된 유저 정보 | UserResponse |
| 선택한 유저 정보 조회 |  GET   | /api/users/{userId} | PathVariable              | 선택한 유저 정보 | UserResponse |
| 선택한 유저 정보 수정 |  PUT   | /api/users/{userId} | PathVariable, RequestBody | 선택한 유저 정보 | UserResponse |
| 선택한 유저 삭제    | DELETE | /api/users/{userId} | PathVariable              | -         | -            |

---

## 2. ERD
![ERD1](/img/ERD2.png)