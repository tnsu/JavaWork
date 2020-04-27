--JOIN

-- FROM 절에 테이블에도 별칭(alias) 을 줄수 있다.
--20 레코드
SELECT  s.STUDNO , s.NAME , s.DEPTNO1 FROM T_STUDENT s;
--12 레코드
SELECT d.DEPTNO , d.DNAME FROM T_DEPARTMENT d;
--240개 
-- 카티션곱 (Cartesian Product)
-- 두개의 테이블을 JOIN 하게 되면, 
-- 각 테이블의 레코드들의 모든 조합이 출력된다.
-- WHERE 나 ON 등으로 JOIN 조건이 주어지지 않으면 
-- 모든 카티션곱이 출력된다. 

SELECT s.STUDNO , s.NAME, s.DEPTNO1 ,d.DEPTNO , d.DNAME 
FROM T_STUDENT s , T_DEPARTMENT d; -- Oracle 방식
SELECT s.STUDNO , s.NAME, s.DEPTNO1 ,d.DEPTNO , d.DNAME 
FROM T_STUDENT s CROSS JOIN T_DEPARTMENT d; -- ANSI 방식

--#6101 Equi Join (등가 Join) Oracle Join방식
SELECT  s.NAME "학생이름", s.DEPTNO1 "학과번호", d.DNAME "학과이름" 
FROM T_STUDENT s, T_DEPARTMENT d WHERE s.DEPTNO1  = d.DEPTNO ;
--#6101 Equi Join (등가 Join) ANSI방식
SELECT  s.NAME "학생이름", s.DEPTNO1 "학과번호", d.DNAME "학과이름" 
FROM T_STUDENT s join T_DEPARTMENT d on s.DEPTNO1  = d.DEPTNO ;


--DEPTNO2 Oracle Join방식
SELECT  s.NAME "학생이름", s.DEPTNO2 "제2학과", d.DNAME "제2학과이름"  FROM T_STUDENT s , T_DEPARTMENT d
WHERE s.DEPTNO2  = d.DEPTNO ;

SELECT  s.NAME "학생이름", s.DEPTNO2 "학과번호", d.DNAME "학과이름" 
FROM T_STUDENT s join T_DEPARTMENT d on s.DEPTNO2  = d.DEPTNO ;

--#6102
SELECT  s.NAME "학생이름" , s.PROFNO "지도교수", p.NAME "지도교수이름" 
FROM T_STUDENT s, T_PROFESSOR p WHERE s.PROFNO = p.PROFNO ; -- Oracle Join방식
SELECT  s.NAME "학생이름" , s.PROFNO "지도교수", p.NAME "지도교수이름" 
FROM T_STUDENT s JOIN T_PROFESSOR p ON s.PROFNO = p.PROFNO ; --ANSI방식

--#6103
SELECT  s.NAME "학생이름" , d.DNAME "학과이름", p.NAME "교수이름" 
FROM T_STUDENT s,T_DEPARTMENT d, T_PROFESSOR p 
WHERE s.PROFNO = p.PROFNO AND s.DEPTNO1 =d.DEPTNO ;
--괴랄 맞음
SELECT  s.NAME "학생이름" , d.DNAME "학과이름", p.NAME "교수이름" 
FROM
T_STUDENT s 
	JOIN T_DEPARTMENT d on s.DEPTNO1 =d.DEPTNO
	JOIN  T_PROFESSOR p  on s.DEPTNO1 =d.DEPTNO ;

--#6104
SELECT * FROM T_EMP2 ;
SELECT * FROM T_POST ;
SELECT e.NAME "사원이름", p.POST "직급", e.PAY "현재연봉",p.S_PAY "하한금액",p.E_PAY "상한금액" 
FROM T_EMP2 e, T_POST p WHERE e.POST = p.POST ;

SELECT e.NAME "사원이름", p.POST "직급", e.PAY "현재연봉",p.S_PAY"하한금액",p.E_PAY "상한금액"
FROM T_EMP2 e JOIN T_POST p on e.POST = p.POST ;

--#6105
SELECT * FROM T_STUDENT ;
SELECT * FROM T_PROFESSOR ;
SELECT s.NAME "학생이름", p.NAME "지도교수"
FROM T_STUDENT s, T_PROFESSOR p WHERE s.DEPTNO1 =101 and s.PROFNO = p.PROFNO ;

SELECT s.NAME "학생이름", p.NAME "지도교수"
FROM T_STUDENT s JOIN T_PROFESSOR p ON  s.PROFNO = p.PROFNO 
WHERE s.DEPTNO1 =101  ; -- WHERE 를 생략하고 and 로 해도됨


