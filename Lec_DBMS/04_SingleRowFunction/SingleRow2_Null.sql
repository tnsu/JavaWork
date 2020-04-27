SELECT * FROM  T_PROFESSOR ;
--NUll  값과의 연산 결과는 null이다.
SELECT  name, pay, bonus, pay+ bonus  FROM T_PROFESSOR;

-- 그룹함수에서는 동작, null은 연산에서 제외되어 동작.
SELECT sum(pay), sum(bonus) FROM T_PROFESSOR;

--nvl()함수
SELECT  name, pay, bonus, pay+ bonus 총지급액, pay+ NVL(BONUS ,0) 총지급액 FROM T_PROFESSOR;
--#4201
SELECT name, pay,NVL(BONUS ,0) BONUS, pay*12+NVL(BONUS ,0) 연봉 FROM t_professor WHERE DEPTNO =101;
--nvl2()함수
SELECT name, pay,NVL2(BONUS ,BONUS , 0) BONUS, pay*12+NVL2(BONUS ,BONUS , 0) 연봉 FROM t_professor WHERE DEPTNO =101;







