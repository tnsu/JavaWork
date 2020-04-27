--Non-Equi Join (비등가 Join)
SELECT * FROM T_CUSTOMER ;
SELECT * FROM T_GIFT ;
SELECT c.C_NAME"고객명",  c.C_POINT , g.G_NAME "상품명"
FROM T_CUSTOMER c, T_GIFT g WHERE c.C_POINT BETWEEN g.G_START AND g.G_END ;

SELECT c.C_NAME"고객명",  c.C_POINT , g.G_NAME "상품명"
FROM T_CUSTOMER c JOIN T_GIFT g ON c.C_POINT BETWEEN g.G_START AND g.G_END ;

 --#6202
SELECT g.G_NAME "상품명" , COUNT(g.G_NAME)
FROM T_CUSTOMER c, T_GIFT g WHERE c.C_POINT BETWEEN g.G_START AND g.G_END GROUP BY g.G_NAME  ; 

SELECT g.G_NAME "상품명" , COUNT(g.G_NAME)
FROM T_CUSTOMER c JOIN T_GIFT g ON c.C_POINT BETWEEN g.G_START AND g.G_END GROUP BY g.G_NAME  ; 

-- #6203
SELECT * FROM t_exam01 ;
SELECT * FROM t_credit  ;
SELECT  s.NAME "학생이름", e.TOTAL "점수", c.GRADE "학점"
FROM T_STUDENT s, T_EXAM01 e, T_CREDIT c WHERE s.STUDNO = e.STUDNO AND e.TOTAL BETWEEN c.MIN_POINT  AND c.MAX_POINT ORDER BY c.GRADE ASC;

SELECT  s.NAME "학생이름", e.TOTAL "점수", c.GRADE "학점"
FROM T_STUDENT s
	JOIN T_EXAM01 e  ON s.STUDNO = e.STUDNO 
	JOIN T_CREDIT c ON e.TOTAL BETWEEN c.MIN_POINT  AND c.MAX_POINT 
ORDER BY c.GRADE ASC;

--#6204
SELECT * FROM T_CUSTOMER;
SELECT * FROM T_GIFT;
SELECT c.C_NAME "고객명", c.C_POINT "POINT", g.G_NAME "상품명"
FROM T_CUSTOMER c , T_GIFT g WHERE c.C_POINT >= g.G_START  AND g.G_START  = 400001;
SELECT c.C_NAME "고객명", c.C_POINT "POINT", g.G_NAME "상품명"
FROM T_CUSTOMER c JOIN T_GIFT g ON c.C_POINT >= g.G_START  AND g.G_START  = 400001;

-- #6205
SELECT * FROM T_EMP2 ;
SELECT * FROM T_POST ;
SELECT e.NAME , (TO_CHAR(SYSDATE,'yyyy') - TO_CHAR(e.BIRTHDAY, 'yyyy')+1) "현재나이" , NVL(e.POST ,' ')  "현재직급", p.POST "예상직급"
FROM  T_EMP2 e ,T_POST p 
WHERE  (TO_CHAR(SYSDATE,'yyyy') - TO_CHAR(e.BIRTHDAY, 'yyyy')+1) BETWEEN p.S_AGE  AND p.E_AGE;

SELECT e.NAME , (TO_CHAR(SYSDATE,'yyyy') - TO_CHAR(e.BIRTHDAY, 'yyyy')+1) "현재나이" , NVL(e.POST,' ')  "현재직급", p.POST "예상직급"
FROM  T_EMP2 e JOIN T_POST p 
ON TO_CHAR(SYSDATE,'yyyy') - TO_CHAR(e.BIRTHDAY, 'yyyy')+1 BETWEEN p.S_AGE  AND p.E_AGE;


--** 기존의 INNER Join 방식으로 먼저 만들어 보고 OUTER Join 을 해보고 비교해보자
SELECT 
	s.name "학생이름", 
	p.name "교수이름"
FROM 
	t_student s JOIN t_professor p
	ON s.profno = p.profno;
--OUTER Join (아우터 조인)
-- #6206
SELECT 
	s.name "학생이름", 
	p.name "교수이름"
FROM 
	t_student s 
	LEFT OUTER JOIN t_professor p
	ON s.profno = p.profno;
-- #6207
SELECT 
	s.name "학생이름", 
	p.name "교수이름"
FROM 
	t_student s 
	 RIGHT OUTER JOIN t_professor p
	ON s.profno = p.profno;
--#6208
SELECT 
	s.name "학생이름", 
	p.name "교수이름"
FROM 
	t_student s 
	 FULL OUTER JOIN t_professor p
	ON s.profno = p.profno;

--SELF Join 
SELECT * FROM T_DEPT2 ;

-- #6209 
SELECT d.DNAME "부서명", d1.DNAME "상위부서명"
FROM T_DEPT2 d , T_DEPT2 d1
WHERE d.PDEPT  = d1.DCODE ORDER BY  d1.DCODE ASC;

SELECT d.DNAME "부서명", d1.DNAME "상위부서명"
FROM T_DEPT2 d JOIN T_DEPT2 d1
ON d.PDEPT  = d1.DCODE ORDER BY  d1.DCODE ASC;

-- #6210
SELECT * FROM T_PROFESSOR ;
SELECT p1.PROFNO "교수번호", p1.NAME "교수명", TO_CHAR(p1.HIREDATE,'yyyy-mm-dd') "입사일",COUNT(p2.HIREDATE) "빠른사람"
FROM T_PROFESSOR p1 LEFT OUTER JOIN T_PROFESSOR p2 
ON p2.HIREDATE  < p1.HIREDATE 
GROUP BY p1.PROFNO, p1.NAME,p1.HIREDATE
ORDER BY 4; -- 4번째 컬럼 p1.HIREDATE  ASC
