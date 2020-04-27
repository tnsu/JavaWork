
/* Drop Tables */

DROP TABLE test_member CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE test_member
(
	mb_no number,
	mb_name varchar2(40) NOT NULL,
	mb_birthdate date
);

-- 시퀀스
DROP SEQUENCE test_member_seq;
CREATE SEQUENCE test_member_seq;

-- SQL 스크립트 실행
-- 처음 실행 하면 DROP 이에러가남 skip눌러주면 실행됨 처음 실행은 DROP 실행안되지만 두번째는 실행이 다됨

SELECT * FROM test_member ORDER BY mb_no DESC;
