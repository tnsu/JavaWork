SELECT * FROM TEST_MEMBER;

-- INSERT INTO 테이블 이름 
-- UPDATE 테이블 이름 SET
-- DELETE FROM 테이블 이름

INSERT INTO TEST_MEMBER values(10, '남윤주',sysdate);

INSERT INTO TEST_MEMBER values(22, '이승환','1994-02-01');
INSERT INTO TEST_MEMBER values(17, '윤종섭','2019-08-03');
INSERT INTO TEST_MEMBER values('', '이예지',''); -- 비어있는 '' 를 INSERT하면  NULL값 처리
-- INSERT INTO TEST_MEMBER values(10, '',sysdate); --cannot insert NULL INTO 
INSERT INTO TEST_MEMBER values(null, '문상현','2017-01-01');
 INTO TEST_MEMBER values(null, '문상현','2017-01-01');

--DBeaver 에서는 기본적으로  auto-commit 수행


