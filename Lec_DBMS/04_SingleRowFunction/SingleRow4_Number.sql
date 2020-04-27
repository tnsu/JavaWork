
--ROUND() 함수 - 반올림
SELECT
	'ROUND',
	ROUND(12.34) "(12.34)",    -- 소수점 1자리에서 반올림 (디폴트)
	ROUND(12.536) "(12.53)",    
	ROUND(12.536, 2) "(12.536, 2)",   -- 소수점 3자리에서 반올림
	ROUND(16.345, -1) "(16.345, -1)"   -- 1의 자리에서 반올림
FROM
	dual;

--TRUNC() 함수 - 자르기 
SELECT
	'TRUNC',
	TRUNC(12.345) "(12.345)",         -- 소수점 자름 (디폴트)
	TRUNC(12.345, 2) "(12.345, 2)",   -- 소수점 3자리부터 자름
	TRUNC(12.345, -1) "(12.345, -1)"  -- 1의 자리부터 자름
FROM
	dual;
--ROUND() TRUNC() CEIL() FLOOR() 함수 비교  
SELECT
	'12.5'  "12.5",
	ROUND(12.5)	"ROUND",
	TRUNC(12.5) "TRUNC",
	CEIL(12.1) "CEIL",
	FLOOR(12.) "FLOOR"
FROM
	dual;
	
SELECT
	'-12.5'  "-12.5",
	ROUND(-12.5)	"ROUND",
	TRUNC(-12.5) "TRUNC",
	CEIL(-12.5) "CEIL",
	FLOOR(-12.5) "FLOOR"
FROM
	dual;

--MOD 함수 
SELECT
	MOD(12, 10) "MOD(12, 10)",
	MOD(12.6, 4.1) "MOD(12.6, 4.1)"
FROM
	dual

--POWER() 함수  :  제곱.
SELECT 
	POWER(3, 2) "POWER(3, 2)",
	POWER(-3, 3) "POWER(-3, 3)",
	POWER(10, -2) "POWER(10, -2)",
	POwer(2,1/2) "sqrt(2, 1.2)",
	POWER(27,1/3) 
FROM
	dual;
