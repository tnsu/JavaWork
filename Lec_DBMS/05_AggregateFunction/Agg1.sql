SELECT * FROM t_professor;
SELECT COUNT(*), COUNT(hpage) FROM t_professor;
SELECT COUNT(bonus), SUM(bonus) FROM t_professor;
SELECT MAX(hiredate), MIN(hiredate) FROM t_emp;
SELECT COUNT(bonus), SUM(bonus), AVG(bonus) FROM t_professor; 
-- 받는 사람의 평균만 산출함으로 잘못된계산법이 나올 수 있음 avg(10 / 780으로 계산됨 16명이아님
-- null 허용 컬럼의 그룹함수 적용시 nvl(), nvl2() 사용해야 함
SELECT AVG(bonus), AVG(NVL(BONUS,0)) FROM t_professor; 

-- t_professor 테이블에서 ‘학과별’로 교수들의 평균 보너스를 출력하세요
--불가능하다!  SELECT 절에 group 함수와 group 함수가 아닌 것과는 같이 출력 불가
-- SELECT DEPTNO, AVG(BONUS ) FROM T_PROFESSOR; -- 그룹함수와 그룹함수아닌것은 같이 나올 수 없다.
SELECT DEPTNO, ROUND(AVG(nvl(bonus, 0))) "보너스 평균"
FROM T_PROFESSOR GROUP BY DEPTNO;

--#5101
SELECT  DEPTNO , POSITION,ROUND(AVG(pay)) "평균급여"  
FROM T_PROFESSOR GROUP BY DEPTNO, POSITION ORDER BY DEPTNO ASC, POSITION ASC;

-- HAVING : 그룹함수 결과에 대한 조건절
SELECT deptno , AVG(pay) 평균급여 
FROM t_professor 
GROUP BY deptno
HAVING AVG(pay) > 300 ;

-- <SELECT 쿼리문 순서>
-- SELECT
-- FROM
-- WHERE
-- GROUP BY
-- HAVING
-- ORDER BY

--#5102
SELECT * FROM T_EMP;
SELECT MGR "매니저", COUNT(MGR) 직원수, sum(SAL) "급여총액" , 
TRUNC(AVG(SAL)) "급여평균", avg(NVL(COMM ,0)) "교통비 평균"
FROM T_EMP WHERE job <> 'PRESIDENT' GROUP BY MGR; -- <> 은 != 과 같다.

--#5103
SELECT * FROM t_professor;
SELECT DEPTNO , COUNT(DEPTNO) "총인원", ROUND( avg(SYSDATE - HIREDATE ),1) "근속평균",
AVG(PAY) "급여평균", AVG(NVL(BONUS,0)) "보너스평균"
FROM t_professor WHERE POSITION LIKE '%교수' GROUP BY DEPTNO ; --POSITION = '정교수' OR POSITION = '조교수' 

--#5104
SELECT * FROM T_STUDENT;
SELECT DEPTNO1 "학과",  MAX(WEIGHT)-MIN(WEIGHT) "최대최소몸무게차" FROM T_STUDENT GROUP BY DEPTNO1;

--#5105
SELECT DEPTNO1 "학과",  MAX(WEIGHT)-MIN(WEIGHT) "최대최소몸무게차" FROM T_STUDENT GROUP BY DEPTNO1 HAVING MAX(WEIGHT)-MIN(WEIGHT) >30 ;



