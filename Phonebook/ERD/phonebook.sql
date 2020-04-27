
/* Drop Tables */

DROP TABLE phonebook CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE phonebook
(
	pb_uid number NOT NULL,
	pb_name varchar2(40) NOT NULL,
	pb_phonenum varchar2(40),
	pb_memo clob,
	pb_regdate date DEFAULT SYSDATE,
	PRIMARY KEY (pb_uid)
);

-- 시퀀스
DROP SEQUENCE phonebook_seq;
CREATE SEQUENCE phonebook_seq;

SELECT * FROM  PHONEBOOK;
SELECT * FROM  PHONEBOOK ORDER BY pb_uid DESC;

SELECT COUNT(*) cnt FROM TEST_MEMBER ; -- 테이블의 모든 레코드 개수 (별명도 지어줄수 있음)
SELECT  MAX(mb_no) FROM TEST_MEMBER ; -- 최댓값
SELECT  MIN(mb_no) FROM TEST_MEMBER ; -- 최소값

SELECT COUNT(*) cnt FROM PHONEBOOK ;

INSERT INTO PHONEBOOK (pb_uid, pb_name, pb_phonenum, pb_memo) VALUES (phonebook_seq.nextval, 'ada', '010-111-2222', 'sdgkdlfj;slkdlgws');

DELETE  PHONEBOOK WHERE pb_name = 'ada';






