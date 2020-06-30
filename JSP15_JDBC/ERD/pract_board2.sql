
/* Drop Tables */

DROP TABLE pra_borad CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE pra_rabod
(
	pr_uid number NOT NULL,
	pr_subject varchar2(200) NOT NULL,
	pr_content clob,
	pr_name varchar2(40) NOT NULL,
	pr_viewcnt number CHECK(pr_viewcnt > 0),
	pr_regdate date DEFAULT SYSDATE,
	PRIMARY KEY (pr_uid)
);
INSERT INTO pra_rabod VALUES
(1, '나의 메모장', '메모를 작성해주세요 ,날짜는 한번 정해서 만들면 수정할수 없어요!', '김희철', 2, '2017-03-02');



