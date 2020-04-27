-- 계층형 퀴리

SELECT * FROM t_dept2; --서로의 계층관계 주목

SELECT LPAD(dname, 10 , '*') 부서명 FROM T_DEPT2 ;


--LEVEL
SELECT dname, LEVEL
FROM t_dept2
CONNECT BY PRIOR dcode = pdept
START WITH dcode = 0001
;

/* 해설
 * LEVEL 은 오라클에서 계속 사용할 수 있는 것으로
 * 해당 데이터가 몇번째 단계 이냐를 의미하는 것.
 * 
 * CONNECT BY PRIOR  :  각 row 들이 어떻게 연결되어야 하는지 조건 지정
 * PRIOR를 어느쪽에 주느냐가 중요!
 */
--PRIOR 
SELECT dname, LEVEL
FROM t_dept2
CONNECT BY PRIOR dcode = PRIOR pdept
START WITH dcode = 0001
;

SELECT DCODE, dname, PDEPT, LEVEL
FROM t_dept2
CONNECT BY  dcode = PRIOR pdept 
-- START WITH dcode = 1005 -- S/W지원(1) -> 기술부(2) -> 사장실(3) -- 객체를 타고 올라감
START WITH dcode = 1011 -- S/W지원(1) -> 기술부(2) -> 사장실(3) -- 객체를 타고 올라감
;

SELECT LPAD(DNAME ,LEVEL *6, '*') 부서명 
FROM T_DEPT2 
CONNECT BY PRIOR dcode = pdept
START WITH dcode = 0001;

--------------
SELECT * FROM t_emp2;

SELECT LPAD( e.name || ' ' || d.dname || ' ' || NVL(e.POST , '사원'), LEVEL*22, '-') "이름과 직급"
FROM t_emp2 e, (SELECT dname, dcode, pdept FROM T_DEPT2) d
WHERE e.DEPTNO  = d.dcode
CONNECT BY PRIOR e.EMPNO = e.PEMPNO 
START WITH e.EMPNO = 20000101
;

SELECT  LEVEL -1 HR 
FROM DUAL 
CONNECT BY LEVEL <=24;

-- 입양 시각 구하기(2)
-- https://programmers.co.kr/learn/courses/30/lessons/59413?language=oracle
SELECT h.hr "HOUR",count(a.datetime) "COUNT" 
FROM 
 (SELECT LEVEL -1 HR FROM DUAL CONNECT BY LEVEL <=24) h LEFT OUTER JOIN ANIMAL_OUTS a
ON h.hr = to_number(to_char(a.DATETIME, 'hh24')) 
GROUP BY h.hr
ORDER BY 1;










