# DatabasePjt

● 구현기능
  1. 게시판 전체 글
  2. 게시글 특정검색
  3. 게시글 작성
  4. 게시글 삭제(비밀번호 검토)
  5. 로그인기능(의사와 사용자 구분) + 2023-11-08 추가
     
● 구현필요 & 검토
  1. 댓글기능
     
     BOARD테이블의 게시글이 가진 고유번호 num을 참조하는 댓글테이블 필요
  2. 의사프로필기능


## 클래스 & 인터페이스 설명
### Package control
● BoardDAO : 게시판 필요 기능

● Join_LoginVO : 회원가입 필요 기능

● BoarVO : 게시판에 필요한 객체들

● Join_LoginVO : 유저정보 객체들
### Package model
● BoardDAOImpl : 게시판에 필요한 함수들

● Join_LoginDAOImpl : 회원가입 & 로그인에 필요한 함수들
### Package view
● BoardInsert : 게시글작성

● BoardList : 게시판 메인 화면

● BoardList2 : 게시판 검색 후 화면

● BoardUpdate : 게시글 클릭 후 화면

● Main : 메인클래스

● JoinScreen : 회원가입 화면

● LoginScreen : 로그인 화면(시작화면)

## 쿼리문
● 게시글쿼리문
```
CREATE TABLE "DatabasePjt"."BOARD" 
   (	"NUM" NUMBER, -- 게시글 고유 넘버
	"TITLE" VARCHAR2(50 BYTE), -- 게시글제목
	"CONTENT" VARCHAR2(2000 BYTE), --게시글내용
	"WRITER" VARCHAR2(20 BYTE), --게시글작성자
    "PW" VARCHAR2 (20 BYTE), -- 게시글비밀번호
	"REGDATE" DATE -- 게시글작성날짜
   );

NEED 게시글생성날짜 및 시간
```
● 유저정보 USERS
```
CREATE TABLE USERS (
    num NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, -- 사용자 고유넘버
    nickname varchar2(12) not null, -- 사용자 닉네임
    account VARCHAR2(12) NOT NULL, -- 사용자 계정
    password VARCHAR2(12) NOT NULL, -- 사용자 비밀번호
    ctype int default 0, -- 사용자, 의사 구분
    regdate TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL -- 계정생성날짜
);
```

● NEED 게시판 BOARDCOMMENT댓글
```
num(Table BOARD의 num을 참조)
nickname(Table USERS의 nickname을 참조)
content(댓글텍스트)
regdate(댓글작성날짜 및 시간)
```
