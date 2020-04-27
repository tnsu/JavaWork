-- SELECT 결과물중 맨 위의 5개만 출력해보고 싶으면 어케 해야 하나?
--   ex) 게시판.. 페이징

-- DBMS 마다 구현 방법 다름
--	MYSQL : LIMIT
-- 	MS SQL server : TOP
-- 	ORACLE : ROWNUM 

SELECT EMPNO , ENAME ,sal FROM t_emp;
--오라클 내부객체 
-- 자동적으로 오라클에서 븥여주는 행번호 객체 (ROWNUM)
SELECT ROWNUM, EMPNO , ENAME ,sal FROM t_emp;
-- 직원번호 역순, 그러나 ROWNUM은 1
SELECT ROWNUM, EMPNO , ENAME ,sal FROM t_emp ORDER BY EMPNO DESC;
-- 상위 5개만!
SELECT ROWNUM, EMPNO , ENAME ,sal FROM t_emp WHERE ROWNUM <= 5 ORDER BY EMPNO DESC;
--SELECT 에 ROWNUM 없어도 동작
SELECT EMPNO , ENAME ,sal FROM t_emp WHERE ROWNUM <= 5 ORDER BY EMPNO DESC;

-- ROWNUM >5 동작안함
-- ROWNUM범위가 1 이 포함안되면 동작안함
SELECT ROWNUM, EMPNO , ENAME ,sal FROM t_emp WHERE ROWNUM >5 ORDER BY EMPNO DESC;

-- 상위 5개 출력
-- row1 ~ row5 까지 출력 (1 page)
SELECT ROWNUM, empno, ename, sal FROM t_emp 
WHERE ROWNUM >= 1 AND ROWNUM < 1 + 5
ORDER BY empno DESC;

-- (2page)
SELECT ROWNUM, empno, ename, sal FROM t_emp 
WHERE ROWNUM >= 6 AND ROWNUM < 6 + 5
ORDER BY empno DESC;  -- 안나온다.. 후엥..

-- phonebook 뻥튀기
SELECT  * FROM PHONEBOOK ;
INSERT INTO  PHONEBOOK (SELECT  * FROM PHONEBOOK) ; -- 에러 Primary Key중복
INSERT INTO  PHONEBOOK (SELECT phonebook_seq.nextval,  PB_NAME ,PB_PHONENUM ,PB_MEMO , SYSDATE FROM PHONEBOOK) ;

-- ROWNUM rev.
SELECT T.* 
FROM (SELECT pb_uid,PB_NAME , PB_PHONENUM FROM PHONEBOOK ORDER BY PB_UID DESC) T; --인라인뷰

SELECT ROWNUM AS RNUM, T.* 
FROM (SELECT pb_uid,PB_NAME , PB_PHONENUM FROM PHONEBOOK ORDER BY PB_UID DESC) T; --인라인뷰

--RNUM 이제 고정된 값을 가지게 된다.
-- 한 페이지 당 5개 데이터
-- 2번째 페이지
SELECT * FROM 
(
SELECT ROWNUM AS RNUM, T.* 
FROM (SELECT pb_uid,PB_NAME , PB_PHONENUM FROM PHONEBOOK ORDER BY PB_UID DESC) T
)
WHERE RNUM >=6 AND RNUM < 6+5;

-- 한 페이지당 10개 데이터
--세번째 페이지
SELECT * FROM 
(
SELECT ROWNUM AS RNUM, T.* 
FROM (SELECT pb_uid,PB_NAME , PB_PHONENUM FROM PHONEBOOK ORDER BY PB_UID DESC) T
)
WHERE RNUM >=21 AND RNUM < 21+10;








