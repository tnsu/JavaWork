-- dual 은 row(행) 1개 짜리 dummy TABLE;
SELECT 'abcde' FROM dual;
SELECT '안녕하세요' FROM  dual;
SELECT 100 FROM dual;
SELECT  100+10 FROM  dual;
-- * : 모든 컬럼
SELECT * FROM t_emp;
--원하는 컬럼만 조회
SELECT empno, ename FROM t_emp;

--원하는 컬럼만 조회 FORM [테이블이름] 을 먼저쓰고 SELECT [컬럼이름] 첫글자만 써도 바로 나옴
SELECT EMPNO ,ENAME 
FROM T_EMP ;

--
SELECT * FROM T_PROFESSOR ;
SELECT NAME FROM T_PROFESSOR ;

--표현식 사용
SELECT '안녕하세요' FROM T_PROFESSOR ;
SELECT name, '교수님 사랑해여' FROM T_PROFESSOR ;

-- 컬럼의 별명(alias)  사용한 풀력
SELECT STUDNO 학번, NAME 이름
FROM  T_STUDENT ;

--
SELECT studno "학생 학번" , name AS 이름
FROM T_STUDENT ;

--연습1
SELECT EMPNO 사원번호, ENAME 사원명, JOB 직업 FROM t_emp;
--연습2
SELECT DEPTNO "부서#", DNAME 부서명, LOC 위치 FROM t_dept;

-- DISTINCT 
SELECT deptno FROM t_emp;
SELECT DISTINCT deptno FROM t_emp; --중복되는 값제거

--  DISTINCT  연습 1
SELECT  DISTINCT DEPTNO1 FROM T_STUDENT ;
--  DISTINCT  연습 2
SELECT DISTINCT JOB  FROM T_EMP;

-- || :  필드, 문자열 연결 연산
SELECT name || '-' || position AS "교수이름-직책" FROM t_professor;

-- || : 연습
SELECT NAME || '의 키는' ||HEIGHT ||'cm, 몸무게는' || WEIGHT||'kg 입니다'  AS "학생의 키와 몸무게"
FROM T_STUDENT ;

