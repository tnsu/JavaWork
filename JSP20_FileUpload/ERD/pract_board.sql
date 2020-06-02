
/* Drop Tables */

DROP TABLE pract_borad CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE pract_borad
(
	pr_uid number NOT NULL,
	pr_subject varchar2(200) NOT NULL,
	pr_content clob,
	pr_name varchar2(40) NOT NULL,
	pr_viewcnt number DEFAULT 0,
	pr_regdate date DEFAULT SYSDATE,
	PRIMARY KEY (pr_uid)
);

CREATE SEQUENCE peact_borad_SEQ;


INSERT INTO pract_borad VALUES
(peact_borad_SEQ.nextval, '나의 메모장', '메모를 작성해주세요 ,날짜는 한번 정해서 만들면 수정할수 없어요!', '김희철', 0, '2017-03-02');

SELECT * FROM PRACT_BORAD ;



