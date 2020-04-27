--날짜 함수
SELECT SYSDATE FROM dual;
--기본 적인 날짜 연산
SELECT SYSDATE  "오늘", SYSDATE+1 "내일 24 시간 뒤",SYSDATE - 2 "그저께",
	SYSDATE + 1/24 "한시간뒤" --오라클은 소수점 단위로 일자 계산 가능.
FROM dual;

SELECT
	SYSDATE "오늘",
	 TO_DATE('2020-03-16 09:00:00', 'YYYY-mm-dd HH:MI:SS') "시작한날",
	SYSDATE - TO_DATE('2020-03-16 09:00:00', 'YYYY-mm-dd HH:MI:SS') "경과"
FROM 
	dual;

--MONTHS_BETWEEN : 두 날짜 사이 개월수 
SELECT 
	-- 규칙1: 두 날짜중 큰 날짜를 먼저 써야  양수로 나옴
	MONTHS_BETWEEN('2012-03-01', '2012-01-01') 양수값,  
	MONTHS_BETWEEN('2012-01-01', '2012-03-01') 음수값,
	-- 규칙2: 두 날짜가 같은 달에 속해 있으면 특정 규칙으로 계산된 값
	MONTHS_BETWEEN('2012-02-29', '2012-02-01') "2/29-2/01", 
	MONTHS_BETWEEN('2012-04-30', '2012-04-01') "4/30-4/01",
	MONTHS_BETWEEN('2012-05-31', '2012-05-01') "5/31-5/01"
FROM dual;

--#4501
SELECT name 이름,SYSDATE 오늘 , HIREDATE 입사일, MONTHS_BETWEEN(SYSDATE, HIREDATE) 개월수, 
ROUND( SYSDATE - HIREDATE ,1) 근속일 FROM T_PROFESSOR;
SELECT
	name "이름", 
	TO_CHAR(SYSDATE, 'YYYY-MM-DD') "오늘", 
	TO_CHAR(hiredate, 'YYYY-MM-DD') "입사일",
	TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(hiredate, 'YYYY') "근속연수",
	ROUND(MONTHS_BETWEEN(SYSDATE, hiredate), 1) "근속개월",
	ROUND(SYSDATE - hiredate, 1) "근속일"
FROM
	t_professor;

--ADD_MONTH 함수 - 달을 추가.
SELECT SYSDATE, ADD_MONTHS(SYSDATE, 3)
FROM dual;

--LAST_DAY(), NEXT_DAY() 
SELECT
	SYSDATE "오늘",
	LAST_DAY(SYSDATE) "이번달 마지막날",
	NEXT_DAY(SYSDATE, '월') "다음 월요일"   -- 현재 언어 세팅에 따라.
FROM 
	dual;

--날짜의 ROUND(), TRUNC() 함수 
SELECT
	SYSDATE "오늘",
	ROUND(SYSDATE),
	TRUNC(SYSDATE)
FROM
	dual;











