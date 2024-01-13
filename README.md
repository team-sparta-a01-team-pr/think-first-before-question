# 물어보기 전에 생각했나요? - 튜터 학생 간 질문 커뮤니티

## 🤓 프로젝트 설명

스파르타코딩클럽의 부트캠프에 참여하는 모든 학생들이 학습 도중 모르는 문제에 직면했을 때 튜터님들에게 질문을 하고, 튜터님들은 해당 질문에 대한 답변을 등록하는 ‘질문-답변 게시판’ 을 제작하는 프로젝트입니다.


## 🚀 개발 기간

`2024-01-08` ~ `2024-01-15`

## 🤼 개발 인원

| 백승한                                                       | 허훈                                                      | 이인재                                                        | 박지영                                                        |
|-----------------------------------------------------------|---------------------------------------------------------|------------------------------------------------------------|------------------------------------------------------------|
| ![](https://avatars.githubusercontent.com/u/84169773?v=4) | ![](https://avatars.githubusercontent.com/u/152062846?v=4) | ![](https://avatars.githubusercontent.com/u/152145394?v=4) | ![](https://avatars.githubusercontent.com/u/152155627?v=4) |
| [@back-seung](https://github.com/back-seung)              | [@hunzzzzz](https://github.com/hunzzzzz)                | [@distecter](https://github.com/distecter/distecter)       | [@jiyeong2023](https://github.com/jiyeong2023)             |
| `팀 리드`, `전체 기능 리팩터링`, `버그 픽스`, `팀 컨벤션 관리`                 | `질문 & 답변 CRUD`, `버그 픽스`                                 | `멤버 CRUD`                                                    | `멤버 CRUD`                                                    |

## 🛠️ 개발 도구 및 환경
<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white">
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/Spring Data Jpa-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/Swagger-6DB33F?style=for-the-badge&logo=swagger&logoColor=white">
<img src="https://img.shields.io/badge/postgresql-151F5D?style=for-the-badge&logo=postgresql&logoColor=white">
<img src="https://img.shields.io/badge/IntelliJ Ultimate Idea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">


## 🗺️ 프로젝트 패키지 구조

```markdown
├── README.md
├── .github
│   ├── ISSUE-TEMPLATE
│   └── PULL_REQUEST_TEMPLATE
├── .gitignore
├── .build.gradle.kts
├── .idea
├── .gradle
└── src.main.kotlin
    └── com.sparta.tfbq
        ├── domain
        │   ├── answer
        │   │   ├── controller
        │   │   ├── dto
        │   │   │   ├── request
        │   │   │   └── response
        │   │   ├── model
        │   │   ├── repository
        │   │   └── service
        │   ├── member
        │   │   ├── common
        │   │   │   ├── controller
        │   │   │   └── service
        │   │   ├── domain
        │   │   │   ├── student
        │   │   │   │   ├── controller
        │   │   │   │   ├── dto
        │   │   │   │   │ ├── request
        │   │   │   │   │ └── response
        │   │   │   │   └── service
        │   │   │   └── tutor
        │   │   │   ├── controller
        │   │   │   ├── dto
        │   │   │   │   ├── request
        │   │   │   │   └── response
        │   │   │   └── service
        │   │   ├── exception
        │   │   ├── model
        │   │   ├── repository
        │   │   └── Member.kt
        │   └── question
        │   ├── controller
        │   ├── dto
        │   │   ├── request
        │   │   └── response
        │   ├── model
        │   ├── repository
        │   └── service
        └── global
                ├── config
                ├── entity
                ├── exception
                ├── infra
                │   └── swagger
                └── util
```

## 🤝 팀 컨벤션
> 커밋, 이슈, PR 등록시 공통 컨벤션을 지정했습니다.
> - [이슈 템플릿](https://github.com/team-sparta-a01-team-pr/think-first-before-question/tree/dev/.github/ISSUE_TEMPLATE)
> - [PR 템플릿](https://github.com/team-sparta-a01-team-pr/think-first-before-question/blob/dev/.github/PULL_REQUEST_TEMPLATE.md)
###  Issue / PR / 커밋 등록 시 제목 공통 Prefix
```markdown
- ✨ feat: 새로운 기능을 추가한 경우
- 🐛 bugfix: 올바르지 않은 동작(버그)을 고친 경우
- 📝 add: feat 이외의 부수적인 코드, 라이브러리 등을 추가한 경우, 새로운 파일(Component나 Activity 등)을 생성한 경우도 포함
- ♻️ refactor: 내부 로직은 변경하지 않고 기존의 코드를 개선한 경우, 클래스명 수정&가독성을 위해 변수명을 변경한 경우도 포함
- 🔥 remove: 코드, 파일을 삭제한 경우, 필요 없는 주석 삭제도 포함
- 📝 docs: 문서를 추가, 수정한 경우
- ✅ test: 테스트 코드를 추가, 수정, 삭제한 경우
```

### 커밋 컨벤션
```markdown
Feat: 제목 작성 (1줄 이내 작성)
(공백)
공백 이하 본문 작성 (본문 10줄 이내 작성)
- 작업 내용 간단 요약 1
- 작업 내용 간단 요약 2
- 작업 내용 간단 요약 3
- 작업 내용 간단 요약 4
- 작업 내용 간단 요약 5
```