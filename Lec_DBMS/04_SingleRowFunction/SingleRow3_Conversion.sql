--묵시적 자동 형변환 예
SELECT 1 + 1 FROM dual; -- JAVA랑은 반대임 문자가 숫자로 변환됨 
SELECT '1' + 1 FROM dual;
SELECT 1 + to_number('1') FROM dual;

--TO_CHAR 함수  (날짜 → 문자)
SELECT SYSDATE, TO_CHAR(SYSDATE, 'yyyy' ) 연도4자리,
	TO_CHAR(SYSDATE, 'RRRR') 연도Y2K버그이후,
	TO_CHAR(SYSDATE, 'YY')  연도2자리,
	TO_CHAR(SYSDATE, 'YEAR') 연도영문
FROM dual;

SELECT 
TO_CHAR(SYSDATE, 'DD') 일숫자2자리,
	TO_CHAR(SYSDATE, 'DDTH')  몇번째날,
	TO_CHAR(SYSDATE, 'DAY') 요일,
	TO_CHAR(SYSDATE, 'Dy') 요일앞자리,
FROM dual;

SELECT 
	TO_CHAR(SYSDATE, 'MM') 월2자리,
	TO_CHAR(SYSDATE, 'MON') 월3자리, --4월 
	TO_CHAR(SYSDATE, 'MONTH')  월전체,
	TO_CHAR(SYSDATE, 'MON', 'NLS_DATE_LANGUAGE=ENGLISH') 월영문3자리, --APR
	TO_CHAR(SYSDATE, 'MONTH', 'NLS_DATE_LANGUAGE=ENGLISH')  "월영문전체(대)", --APRIL    
	TO_CHAR(SYSDATE, 'month', 'NLS_DATE_LANGUAGE=ENGLISH')  "월영문전체(소)",
	TO_CHAR(SYSDATE, 'Month', 'NLS_DATE_LANGUAGE=ENGLISH')  "월영문전체(첫글자대)" --April    
FROM dual;

SELECT 
	TO_CHAR(SYSDATE, 'HH24') 시24hr,
	TO_CHAR(SYSDATE, 'HH')  시12hr,
	TO_CHAR(SYSDATE, 'MI') 분,
	TO_CHAR(SYSDATE, 'SS') 초,
	TO_CHAR(SYSDATE, 'HH:MI:SS')
FROM dual;

--#4301
SELECT SYSDATE, TO_CHAR(SYSDATE, 'yyyy-mm-dd HH24:MI:SS') 날짜 FROM dual;
SELECT SYSDATE, TO_CHAR(SYSDATE, 'yyyy"월"mm"월"dd HH24"시"MI"분"SS') 날짜 FROM dual;

--#4302
SELECT name, TO_CHAR(BIRTHDAY, 'yyyy-mm-dd') 생일 FROM T_STUDENT WHERE TO_CHAR(BIRTHDAY, 'mm') =3;

--TO_CHAR 함수  (숫자 → 문자)
SELECT
	1234,
	TO_CHAR(1234, '99999')  "9하나당 1자리",
	TO_CHAR(1234, '099999')  "빈자리 0으로",
	TO_CHAR(1234, '$9999')  "$붙임",
	TO_CHAR(1234.1234, '9999.99')  "소수점이하",
	TO_CHAR(12345, '99,999')  "천단위구분기호"
FROM dual;

--#4303
SELECT name, TO_CHAR(pay*12 + nvl(BONUS,0), '9,999') 연봉 FROM t_professor WHERE DEPTNO =101;

--TO_NUMBER() 함수 
SELECT TO_NUMBER('123.44') FROM dual;

--TO_DATE() 함수 
SELECT TO_DATE('2012-01-01', 'YYYY-MM-DD') TO_DATE
FROM dual; -- 윤년계산도 가능하다


--#4304

SELECT *FROM t_professor;
SELECT name, TO_CHAR(HIREDATE, 'yyyy-MM-dd') 입사일,
TO_CHAR(pay*12, '9,999') 연봉, TO_CHAR((pay*12)*0.1+ (pay*12),'9,999') 인상후 
FROM t_professor WHERE HIREDATE  < '2000-01-01';

SELECT name, TO_CHAR(HIREDATE, 'yyyy-MM-dd') 입사일,
TO_CHAR(pay*12, '9,999') 연봉, TO_CHAR((pay*12)*0.1+ (pay*12),'9,999') 인상후 
FROM t_professor WHERE TO_CHAR(HIREDATE, 'yyyy') < 2000; --'2000' 해도됨

