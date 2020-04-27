-- 산술 연산자 +, - , *, /
SELECT ename, sal, sal*1.1 --급여 10% 인상분
FROM t_emp;

SELECT ENAME , SAL , COMM , sal+comm -- null값과의 산술연산은 무조건 null
FROM  T_EMP ;

-- WHERE 조건절
SELECT * FROM t_emp WHERE job = 'SALESMAN';
-- 연습1
SELECT ename, sal, deptno FROM t_emp WHERE deptno = 10;
-- 연습2
SELECT ENAME , SAL  FROM T_EMP WHERE sal > 2000;
-- 연습3
SELECT ename, empno, sal FROM t_emp WHERE ename = 'SCOTT';

-- 여러개의 값을 동시에 비교
SELECT name, grade FROM T_STUDENT WHERE GRADE = 2 OR GRADE =3;
SELECT name, grade FROM T_STUDENT WHERE GRADE IN (2,3);
SELECT name, grade FROM T_STUDENT WHERE GRADE > 1 AND GRADE < 4;
SELECT name, grade FROM T_STUDENT WHERE GRADE NOT IN (1,4);
SELECT name, grade FROM T_STUDENT WHERE GRADE BETWEEN 2 AND 3;
-- 연습1
SELECT NAME, PAY, POSITION FROM  T_PROFESSOR WHERE PAY >300 AND POSITION='정교수';
-- 연습2
SELECT NAME , POSITION FROM T_PROFESSOR WHERE BONUS IS null;

--like % 연습1
SELECT name FROM  T_PROFESSOR  WHERE NAME LIKE '김%';
--like % 연습2
SELECT ENAME FROM  T_EMP WHERE ENAME  LIKE '%NE%';
--like % 연습3
SELECT ENAME FROM T_EMP  WHERE ENAME  LIKE '_A%';

--ORDER BY 
SELECT ename FROM t_emp WHERE ename LIKE '%L%' ORDER BY ename DESC ;
SELECT ENAME, JOB , SAL FROM T_EMP ORDER BY JOB DESC , sal ASC ;

SELECT name, grade, height FROM t_student ORDER BY grade ASC, height DESC;


