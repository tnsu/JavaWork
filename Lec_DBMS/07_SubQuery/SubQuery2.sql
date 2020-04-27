
--다중칼럼 Sub Query

--#7201) t_student 테이블을 조회하여 각 학년별로 최대 키를 가진 학생들의 학년과 이름과 키를 출력하세요,  학년 오름차순으로 출력
SELECT  GRADE ,  MAX(HEIGHT) FROM T_STUDENT GROUP BY GRADE ;
SELECT GRADE "학년" , NAME "이름", HEIGHT "키" FROM T_STUDENT  
WHERE (GRADE ,HEIGHT) IN (SELECT GRADE, MAX(HEIGHT) FROM T_STUDENT GROUP BY GRADE)
-- ORDER BY grade ASC;
-- ORDER BY 1 ASC;
ORDER BY "학년" ASC;

--#7202) t_professor , t_department 테이블 :  각 학과별로 입사일이 가장 오래된 교수의 교수번호와 이름, 학과명을 출력하세요. 
-- 단 학과이름 순으로 오름차순 정렬하세요

SELECT DEPTNO, to_char(MIN(HIREDATE),'yyyy-mm-dd') FROM T_PROFESSOR GROUP BY DEPTNO ;
SELECT p.PROFNO "교수번호" , p.NAME "교수명", p.HIREDATE "입사일" , d.DNAME "학과명" 
FROM  t_department d, T_PROFESSOR p  
WHERE p.DEPTNO = d.DEPTNO 
AND (p.DEPTNO, p.HIREDATE) IN(SELECT DEPTNO, MIN(HIREDATE) FROM T_PROFESSOR GROUP BY DEPTNO) 
ORDER BY d.DNAME ASC;
SELECT * FROM  T_PROFESSOR;

--#7203)  t_emp2 테이블 : 직급별로 해당직급에서 최대 연봉을 받는 직원의 이름과 직급, 연봉을 출력하세요,
--  단, 연봉순으로 오름차순 정렬하세요
SELECT POST, max(pay) FROM T_EMP2 GROUP BY post;
SELECT name"사원", POST "직급" , pay"연봉" FROM T_EMP2 
WHERE (post, pay) IN (SELECT POST, max(pay) FROM T_EMP2 GROUP BY post) ORDER BY "연봉" ASC;

--#7204)  t_emp2, t_dept2 테이블 : 각 부서별 평균 연봉을 구하고 그 중에서 평균 연봉이
-- 가장 적은 부서의 평균연봉보다 적게 받는 직원들의 부서명, 직원명, 연봉을 출력 하세요
SELECT * FROM t_emp2;
SELECT * FROM T_DEPT2;
SELECT  avg(pay) FROM T_EMP2 GROUP BY DEPTNO ;

SELECT d.DNAME "부서명", e.NAME "직원명", pay"연봉" FROM t_emp2 e, T_DEPT2 d
WHERE e.DEPTNO = d.DCODE AND e.pay <ALL (SELECT avg(pay) FROM T_EMP2 GROUP BY DEPTNO);

--상호연관 Sub Query

--**#7205) t_emp2 테이블 :  직원들 중에서 자신의 직급의 평균연봉과 같거나 많이 받는 사람들의 이름과 직급, 현재 연봉을 출력하세요.
SELECT * FROM t_emp2;

SELECT avg(pay) FROM t_emp2 GROUP BY NVL(POST, ' ') ;

SELECT a.NAME "사원이름", a.post "직급", b.pay"급여" FROM t_emp2 a, t_emp2 b WHERE a.NAME = b.NAME AND b.pay >= (SELECT avg(pay) FROM t_emp2 GROUP BY NVL(POST, ' '));

SELECT a.name "사원이름", NVL(a.post, ' ') "직급", a.pay "급여"
FROM t_emp2 a
-- WHERE a.pay >= (  a.post 직급의 평균연봉 )
WHERE a.pay >= (SELECT avg(b.pay) FROM t_emp2 b WHERE NVL(a.post, ' ') = NVL(b.post, ' '))     
;

SELECT avg(b.pay) FROM t_emp2 b WHERE '과장' = b.post; 

--Scalar Sub Query (스칼라 서브쿼리)
--#7206) t_emp2, t_dept2 테이블: 조회하여 사원들의 이름과 부서 이름을 출력, JOIN으로도 출려가능
SELECT 
name "사원이름", (SELECT d.dname FROM t_dept2 d WHERE e.deptno = d.dcode ) "부서이름"
FROM t_emp2 e;




















