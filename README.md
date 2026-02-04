일정 관리 API 명세서 
## 1. 일정 생성
**POST** `/plans`

#### Request Body
```json
{
   "title": "이야기1",
   "content": "재밌고 재밌는 이야기1",
   "user": "김두한1",
   "password": "12345678"
}
```

#### Response (201 Created)
```json
{
   "id": 1,
   "title": "이야기1",
   "content": "재밌고 재밌는 이야기1",
   "user": "김두한1",
   "createdAt": "2026-02-04T17:03:59.785175",
   "modifiedAt": "2026-02-04T17:03:59.785175"
}
```

## 2.댓글 생성 기능 
**POST** `/plans/{planID}/comments`

Request Body
```json
{
   "user":"갓 김두한",
   "content":"나여 김두한",
   "password":"12345678"
}
```
Response (201 Created)
```json
{
   "id": 1,
   "user": "갓 김두한",
   "content": "나여 김두한",
   "createdAt": "2026-02-04T17:07:30.827299",
   "modifiedAt": "2026-02-04T17:07:30.827299"
}
```
## 4. 일정 단건 조회
GET/ plans/{planId}

Request
GET/ plans/1

Response (200 OK)
```json
{
  "comments": [
{
   "planId": 1,
   "user": "갓 김두한",
   "content": "나여 김두한",
   "password": "12345678",
   "createdAt": "2026-02-04T17:07:30.827299",
   "id": 1,
   "modifiedAt": "2026-02-04T17:07:30.827299"
}
],
   "content": "재밌고 재밌는 이야기1",
   "createdAt": "2026-02-04T17:03:59.785175",
   "id": 1,
   "modifiedAt": "2026-02-04T17:03:59.785175",
   "title": "이야기1",
   "user": "김두한1"
}
```

## 5. 일정 전체 조회
GET /plans
   
Response (200 OK)
```json
[
{
   "comments": null,
   "content": "재밌고 재밌는 이야기5",
   "createdAt": "2026-02-04T17:04:37.575689",
   "id": 6,
   "modifiedAt": "2026-02-04T17:04:37.575689",
   "title": "이야기5",
   "user": "김두한1"
},
{
   "comments": null,
   "content": "재밌고 재밌는 이야기4",
   "createdAt": "2026-02-04T17:04:34.602871",
   "id": 5,
   "modifiedAt": "2026-02-04T17:04:34.602871",
   "title": "이야기5",
   "user": "김두한1"
},
{
   "comments": null,
   "content": "재밌고 재밌는 이야기4",
   "createdAt": "2026-02-04T17:04:32.04016",
   "id": 4,
   "modifiedAt": "2026-02-04T17:04:32.04016",
   "title": "이야기4",
   "user": "김두한1"
},
{
   "comments": null,
   "content": "재밌고 재밌는 이야기3",
   "createdAt": "2026-02-04T17:04:27.600029",
   "id": 3,
   "modifiedAt": "2026-02-04T17:04:27.600029",
   "title": "이야기3",
   "user": "김두한1"
},
{
   "comments": null,
   "content": "재밌고 재밌는 이야기2",
   "createdAt": "2026-02-04T17:04:22.36984",
   "id": 2,
   "modifiedAt": "2026-02-04T17:04:22.36984",
   "title": "이야기2",
   "user": "김두한1"
},
{
   "comments": null,
   "content": "재밌고 재밌는 이야기1",
   "createdAt": "2026-02-04T17:03:59.785175",
   "id": 1,
   "modifiedAt": "2026-02-04T17:03:59.785175",
   "title": "이야기1",
   "user": "김두한1"
}
]
```
## 6. 일정 수정
PUT /plans/{planId}

Request Body
```json
{
"title":"이야기야",
"user":"김두한211",
"password":"12345678"
}
```
Response (200 OK)
```json
{
"id": 1,
"title": "이야기야",
"content": "재밌고 재밌는 이야기1",
"user": "김두한211",
"createdAt": "2026-02-04T17:03:59.785175",
"modifiedAt": "2026-02-04T17:03:59.785175"
}
```
## 7. 일정 삭제
DELETE /plans/{planId}

Request Body
```json
{
   "password":"12345678"
}
```
Response (204 No Content)

## ERD
<img width="454" height="1136" alt="Image" src="https://github.com/user-attachments/assets/74d1a644-088e-41da-abe6-2eba3c94c02c" />

## 일정 관리 구현 상황

## 2026.02.02 20:32

 구현 기능 
1. 필수 Lv 1

구현 해야 하는 기능
나머지 필수 기능

## 2026.02.03 12:21

구현 기능
1. 필수 Lv 2 조회 기능

구현 해야 하는 기능
나머지 필수 기능

## 2026.02.03 14:47
구현 기능
1. 필수 Lv 3 일정 기능
2. 필수 Lv 4 삭제 기능
 
구현 해야 하는 기능
도전 기능

## 2026.02.03 19:16
구현 기능
1. 도전 Lv 5 댓글 생성
2. 도전 Lv 6 댓글을 포함한 일정 단건 조회 

# 특이사항
도전 Lv6 일정 단건 조회 직접적인 도움을 받음. 기능 구현을 위해서 JPA 메서드 학습 필요.

구현 해야 하는 기능
도전 기능 Lv 7

## 2026.02.04 11:59
구현 기능
1. 도전 기능 Lv 7

특이사항
도전 Lv7 기능 구현에 있어서 조원에 도움을 받음. 예외처리부분을 배우고 적용시켰다. 예외처리부분 학습 필요.

구현 해야 하는 기능
1. API 명세서  
2. ERD
