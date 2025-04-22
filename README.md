# DatabasePjt
### 🏥 의료 게시판 프로젝트

## 📊 데이터베이스 설정 (IntelliJ)

### 설치 및 연결 방법

1. **Database Navigator 플러그인 설치**

1. Settings → Plugins → Database Navigator 검색 및 설치



2. **데이터베이스 연결**

1. 상단 DB Navigator → Database Browser
2. '+' 버튼 클릭 후 Oracle 선택



3. **연결 설정**

| 설정 항목 | 값
|-----|-----
| **Connect type** | Custom
| **URL** | 오라클 실행환경에 맞게 설정
| **Authentication** | DB 생성자 정보 입력
| **Drivers** | External library 선택 후 폴더 안 ojdbc11.jar 선택





# ✅ 구현 기능

| 번호 | 기능 | 설명
|-----|-----
| **1** | **게시판 전체 글** | 게시판의 모든 글 목록 조회
| **2** | **게시글 특정검색** | 키워드를 통한 게시글 검색
| **3** | **게시글 작성** | 새로운 게시글 등록
| **4** | **게시글 삭제** | 비밀번호 검증 후 게시글 삭제
| **5** | **로그인 기능** | 의사와 사용자 구분 (2023-11-08 추가)


# 🔍 구현 필요 & 검토 사항

| 번호 | 기능 | 설명
|-----|-----
| **1** | **댓글 기능** | BOARD 테이블의 게시글이 가진 고유번호 num을 참조하는 댓글 테이블 필요
| **2** | **의사 프로필 기능** | 의사 정보 및 프로필 관리


# 📂 프로젝트 구조

### 📦 Package: control

| 클래스/인터페이스 | 역할
|-----|-----
| **BoardDAO** | 게시판 필요 기능 인터페이스
| **Join_LoginDAO** | 회원가입 필요 기능 인터페이스
| **BoardVO** | 게시판에 필요한 객체들
| **Join_LoginVO** | 유저정보 객체들


### 📦 Package: model

| 클래스 | 역할
|-----|-----
| **DBConnector** | DB 연동 (사용예제 숙지 필요)
| **BoardDAOImpl** | 게시판에 필요한 함수들 구현
| **Join_LoginDAOImpl** | 회원가입 & 로그인에 필요한 함수들 구현


### 📦 Package: view

| 클래스 | 역할
|-----|-----
| **BoardInsert** | 게시글 작성 화면
| **BoardList** | 게시판 메인 화면
| **BoardList2** | 게시판 검색 후 화면
| **BoardUpdate** | 게시글 클릭 후 화면
| **Main** | 메인 클래스
| **JoinScreen** | 회원가입 화면
| **LoginScreen** | 로그인 화면 (시작화면)
## 쿼리문
● 게시글
```
CREATE TABLE 게시글 (
    게시글번호 NUMBER DEFAULT SEQ_BOARD_NUM.NEXTVAL PRIMARY KEY,
    제목 VARCHAR2(100),
    내용 VARCHAR2(1000),
    작성자ID VARCHAR2(25) REFERENCES 회원(ID),
    비밀번호 VARCHAR2(20),
    게시글작성날짜 DATE
);
```
● 회원
```
CREATE TABLE 회원 (
    ID VARCHAR2(25) PRIMARY KEY,
    PW VARCHAR2(25),
    이름 NCHAR(10),
    전화번호 VARCHAR2(20),
    계정생성날짜 DATE,
    사용자의사구분 NUMBER
);


CREATE TABLE 의사 (
    ID VARCHAR2(25) PRIMARY KEY,
    PW VARCHAR2(25),
    이름 NCHAR(10),
    전화번호 VARCHAR2(20),
    계정생성날짜 DATE,
    사용자의사구분 NUMBER,
    진료과 VARCHAR2(30)
);
```

● 답변
```
CREATE TABLE 답변 (
    답변번호 NUMBER DEFAULT SEQ_ANSWER_NUM.NEXTVAL PRIMARY KEY,
    게시글번호 NUMBER REFERENCES 게시글(게시글번호),
    의사ID VARCHAR2(25) REFERENCES 의사(ID),
    제목 VARCHAR2(100),
    내용 VARCHAR2(1000),
    답변일자 DATE
);

```
