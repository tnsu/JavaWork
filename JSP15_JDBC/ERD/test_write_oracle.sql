
/* Drop Tables */

DROP TABLE TEST_WRITE CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE TEST_WRITE
(
	wr_uid number NOT NULL,
	wr_subject varchar2(200) NOT NULL,
	wr_content clob,
	wr_name varchar2(40) NOT NULL,
	wr_viewcnt number DEFAULT 0,
	wr_regdate date DEFAULT SYSDATE,
	PRIMARY KEY (wr_uid)
);

-- 시퀀스 작성
CREATE SEQUENCE TEST_WRITE_SEQ;
-- 시퀀스 삭제
DROP SEQUENCE TEST_WRITE_SEQ;


-- 기본데이터 작성
INSERT INTO TEST_WRITE VALUES
(TEST_WRITE_SEQ.nextval, '첫째글:방가요', '안녕하세요', '김희철', 0, '2017-03-02');
INSERT INTO TEST_WRITE VALUES
(TEST_WRITE_SEQ.nextval, '둘째글:헤헤헤','1111', '김수길', 0, '2017-03-02');
INSERT INTO TEST_WRITE VALUES
(TEST_WRITE_SEQ.nextval, '세째글:힘내세요', '7394', '최진덕' , 0, '2017-08-12');
INSERT INTO TEST_WRITE VALUES
(TEST_WRITE_SEQ.nextval, '네째글: ... ', '9090', '이혜원', 0, '2018-02-09');
INSERT INTO TEST_WRITE VALUES
(TEST_WRITE_SEQ.nextval, '다섯째글: 게시판', '7531', '박수찬', 0, sysdate);

-- 기존의 테이블 두배로 늘리기 쿼리 : 레코드들을 그대로 복사해서 insert 
SELECT count(*) FROM TEST_WRITE ;
INSERT INTO test_write(wr_uid, wr_subject, wr_content, wr_name)
SELECT test_write_seq.nextval, wr_subject, wr_content, wr_name FROM test_write;


SET LINESIZE 120;
SET PAGESIZE 100;
col wr_uid for 999;
col wr_subject for a20;
col wr_content for a15;
col wr_name for a10;
col wr_viewcnt for 99;
col wr_regdate for a10;

SELECT * FROM test_write;

DELETE FROM TEST_WRITE WHERE WR_UID >10;




