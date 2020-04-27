-- INITCAP() 함수

-- #4101
SELECT INITCAP('pretty girl') FROM DUAL;

--#4102
SELECT id, INITCAP(id)  FROM T_STUDENT  WHERE DEPTNO1  = 201;

--LOWER 함수   / UPPER 함수  
--#4103
SELECT name, id, upper(id) 대문자 , LOWER(id) 소문자  FROM T_STUDENT WHERE DEPTNO1 = 201;
--LENGTH() /LENGTHB() 함수 
--#4104
SELECT name, id,  LENGTH(id) 글자수 FROM T_STUDENT WHERE LENGTH(id) >=9; -- 단일행 함수는 WHERE 조건절에서 사용가능

--#4105
SELECT name 이름, LENGTH(name) 길이 , LENGTHB(name) 소문자  FROM T_STUDENT WHERE DEPTNO1 = 201;
--CONCAT 함수 ( || 연산자와 동일 )
--#4106
SELECT CONCAT(NAME, POSITION ) 교수님명단 FROM T_PROFESSOR WHERE DEPTNO = 101;

--SUBSTR() 함수
SELECT SUBSTR('ABCDE', 2, 3) FROM dual;   -- 인덱스 1부터 시작
SELECT SUBSTR('ABCDE', 20, 3) FROM dual;   -- 에러 아님 null로 나옴

--#4107
SELECT name, SUBSTR(JUMIN,1,6) FROM T_STUDENT WHERE DEPTNO1 =101;

--#4108
SELECT name, SUBSTR(JUMIN,1,6) FROM T_STUDENT WHERE SUBSTR(JUMIN,4,1) = 8;
--WHERE substr(jumin, 3, 2) = '08'; -- WHERE jumin LIKE '__08%';
--#4109
SELECT name, JUMIN FROM T_STUDENT WHERE GRADE = 4 AND SUBSTR(JUMIN,7,1) = 2; 

--INSTR 함수
SELECT INSTR('A*B*C*', '*', 1, 1) FROM dual;  
SELECT INSTR('A*B*C*', '*', 1, 2) FROM dual;
SELECT INSTR('A*B*C*', '*', 3, 2) FROM dual; 
SELECT INSTR('A*B*C*', '*', -4, 1) FROM dual;  
--#4110
SELECT name, tel, INSTR(tel, ')' ,1,1) AS 위치 FROM  T_STUDENT WHERE DEPTNO1 =101;
SELECT name, tel, INSTR(tel, ')') AS 위치 FROM  T_STUDENT WHERE DEPTNO1 =101; --기본값이기 때문에 동작가능 

--#4111
SELECT name, tel, SUBSTR(tel, 1, INSTR(tel, ')', 1,1)-1) 지역변호  FROM T_STUDENT WHERE DEPTNO1=101;

--LTRIM() 함수 RTRIM() 함수
SELECT 
	LTRIM('슈퍼슈퍼슈가맨', '슈퍼') LTRIM,
	LTRIM('슈퍼슈퍼슈가맨', '슈') LTRIM,
	'   슈퍼슈가맨',
	LTRIM('   슈퍼슈가맨', ' ') LTRIM,
	LTRIM('   슈퍼슈가맨') LTRIM,   -- 디폴트로 '공백' 제거
	RTRIM('우측 공백 제거      ') RTRIM,
	TRIM('    슈퍼맨         ')  TRIM  -- 좌우 공백 제거
FROM dual;

--#4116
SELECT LTRIM(DNAME ,'영') AS LTRIM예제 FROM T_DEPT2;

--#4117
SELECT RTRIM(DNAME ,'부') AS RTRIM예제 FROM T_DEPT2;

--REPLACE() 함수 
SELECT REPLACE('슈퍼맨 슈퍼걸', '슈퍼', '파워') REPLACE예제 
FROM dual;
SELECT REPLACE('아버지가 방에 들어간다', ' ', '') REPLACE예제 
FROM dual;

--#4118
SELECT REPLACE(name, SUBSTR(name,1,1),'#' ) AS 학생 FROM T_STUDENT WHERE DEPTNO1 =102;

--#4119
SELECT REPLACE (name, SUBSTR(name,2,1), '#') AS 학생 FROM T_STUDENT WHERE DEPTNO1 =101;

--#4120 !!
SELECT name, REPLACE (JUMIN, SUBSTR(JUMIN,7,7),'*******' ) AS 주민번호 FROM t_student WHERE DEPTNO1 =101;

--#4121 !!
SELECT name, tel,  REPLACE (tel,SUBSTR(tel, INSTR(tel, ')') +1, 3), '###') 전화번호 FROM t_student WHERE DEPTNO1 =102;













