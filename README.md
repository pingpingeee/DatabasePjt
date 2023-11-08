# DatabasePjt
MVC패턴사용

● 구현기능
  1. 게시판 전체 글
  2. 게시글 특정검색
  3. 게시글 작성
  4. 게시글 삭제(비밀번호 검토)
     
● 구현필요 & 검토
  1. 댓글기능
     
     BOARD테이블의 게시글이 가진 고유번호 num을 참조하는 댓글테이블 필요
  3. 로그인기능(의사와 사용자 구분)
  4. 의사프로필기능


## 클래스 & 인터페이스 설명
### Package control
● BoardDAO : 데이터액세스에 필요한 인터페이스

● BoarVO : 데이터를 담은 객체 정의클래스
### Package model
● BoardDAOImpl : 데이터 처리 담당클래스 / 해당 클래스에서 DB설정
### Package view
● BoardInsert : 게시글작성클래스

● BoardList : 게시판 메인 화면클래스

● BoardList2 : 게시판 검색 후 화면클래스

● BoardUpdate : 게시글 클릭 후 화면클래스

● BoardMain : 메인클래스

## 쿼리문
● 게시글쿼리문
```
CREATE TABLE "DatabasePjt"."BOARD" 
   (	"NUM" NUMBER, 
	"TITLE" VARCHAR2(50 BYTE), 
	"CONTENT" VARCHAR2(2000 BYTE), 
	"WRITER" VARCHAR2(20 BYTE), 
    "PW" VARCHAR2 (20 BYTE),
	"REGDATE" DATE
   );

NEED 게시글생성날짜 및 시간
```
● NEED 유저정보 USERS(회원가입 추가시)
```
num
nickname(기존 게시판작성자 삭제 후 nickname추가)
account
password
ctype( 0 = 사용자, 1 = 의사)
regdate(생성날짜)
```

● NEED 게시판 BOARDCOMMENT댓글
```
num(Table BOARD의 num을 참조)
nickname(Table USERS의 nickname을 참조)
content(댓글텍스트)
regdate(댓글작성날짜 및 시간)
```
