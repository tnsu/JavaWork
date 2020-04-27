--제약조건
--테이블 생성시 동시에 설정하기

--t_dept2/dcode 참조 예정
SELECT  * FROM T_DEPT2 ;

--#9001
-- 제약조건명을 명시하지 않는 방법
--CASCADE CONSTRAINT 제약조건도 같이 지우겠다는 뜻
DROP TABLE t_emp3 CASCADE CONSTRAINT;
CREATE TABLE t_emp3(
	no NUMBER(4) PRIMARY KEY ,
	name VARCHAR2(10) NOT NULL,
	jumin VARCHAR2(13) NOT NULL UNIQUE ,
	area NUMBER(1) CHECK(area < 5),
	deptno VARCHAR2(6) REFERENCES t_dept2(dcode)
);

--별도의 항복으로 제약조건 정의 가능
DROP TABLE t_emp4 CASCADE CONSTRAINT;
CREATE TABLE t_emp4(
	no NUMBER(4),
	name VARCHAR2(10) NOT NULL,
	jumin VARCHAR2(13) NOT NULL ,
	area NUMBER(1),
	deptno VARCHAR2(6), 
	PRIMARY KEY(no),
	unique(jumin),
	CHECK(area < 5),
	FOREIGN KEY(deptno) REFERENCES t_dept2(dcode)
);

-- #9002
-- 제약조건명을 명시하여 정의
DROP TABLE t_emp3 CASCADE CONSTRAINT;
CREATE TABLE t_emp3 (
	no NUMBER(4) CONSTRAINT emp3_no_pk PRIMARY KEY,
	name VARCHAR2(10) CONSTRAINT emp3_name_nn NOT NULL,
	jumin VARCHAR2(13) 
		CONSTRAINT emp3_jumin_nn NOT NULL 
		CONSTRAINT emp3_jumin_uk UNIQUE,
	area NUMBER(1) CONSTRAINT emp3_area_ck CHECK(area < 5),
	deptno VARCHAR2(6) CONSTRAINT emp3_deptno_fk REFERENCES t_dept2(dcode)
);

DROP TABLE t_emp3 CASCADE CONSTRAINT;
CREATE TABLE t_emp3 (
	no NUMBER(4),
	name VARCHAR2(10) CONSTRAINT emp3_name_nn NOT NULL,
	jumin VARCHAR2(13) CONSTRAINT emp3_jumin_nn NOT NULL,
	area NUMBER(1),
	deptno VARCHAR2(6),
	CONSTRAINT emp3_no_pk PRIMARY KEY(no),
	CONSTRAINT emp3_jumin_uk UNIQUE(jumin),
	CONSTRAINT emp3_area_ck CHECK(area < 5),
	CONSTRAINT emp3_deptno_fk FOREIGN KEY(deptno) REFERENCES t_dept2(dcode)
);

-- #9003 제약조건 조회하기
SELECT owner, constraint_name, constraint_type, status
FROM USER_CONSTRAINTS 
WHERE table_name = 'T_EMP4';  -- 테이블명 대문자로!

SELECT owner, constraint_name, constraint_type, status
FROM USER_CONSTRAINTS 
WHERE table_name = 'T_EMP3'; 

--#9005: 제약조건에 위배되는 DML 
INSERT INTO t_emp3 VALUES(1, '오라클', '1234561234567', 4,1000);
--두번 실행하면 오류!ORA-00001: unique constraint (SCOTT0316.EMP3_NO_PK) violated

INSERT INTO t_emp3 VALUES(2, '오라클', '1234561234567', 4,1000);
--jumin UNIQUE 오류 ORA-00001:unique constraint (SCOTT0316.EMP3_JUMIN_UK) violated

INSERT INTO t_emp3 VALUES(2, '오라클', '2222222222222222', 4,1000);
-- VARCHAR2(13) 초과 오류! ORA-12899: value too large for column "SCOTT0316"."T_EMP3"."JUMIN" (actual: 16, maximum: 13)

INSERT INTO t_emp3 VALUES(2, '오라클', '2222222222222', 10,1000);
--CHECK ORA-01438: value larger than specified precision allowed for this column

INSERT INTO t_emp3 VALUES(2, '오라클', '2222222222222', 3,2000);
--FK 오류 :  ORA-02291: integrity constraint (SCOTT0316.EMP3_DEPTNO_FK) violated - parent key not found

INSERT  INTO t_emp3 (NO, jumin, area, deptno) VALUES (2,'3333333333333', 4, 1001);
-- NN오류 : ORA-01400: cannot insert NULL into ("SCOTT0316"."T_EMP3"."NAME")

--INSERT뿐 아니라 UPDATE/DELETE 에서도 오류 발생 가능
UPDATE t_emp3 SET area = 10 WHERE NO =1;  -- CHECK 값  오류

SELECT * FROM t_emp3;
DELETE FROM t_dept2 WHERE dcode = 1000;  -- 참조하고 있는 부모는 삭제 불가
-- ORA-02292: integrity constraint (SCOTT0316.EMP3_DEPTNO_FK) violated - child record found

--#9005 ALTER 명령 사용하여 테이블 에 제약조건 추가가능
-- t_emp4 테이블의 name 컬럼에 UNIQUE 제약조건 추가
-- ALTER ~ ADD
-- ALTER ~ MODIFY
-- ALTER ~ DROP

ALTER TABLE t_emp4 ADD CONSTRAINT emp4_name_uk UNIQUE (name);

--#9006)  t_emp4 테이블의 area 컬럼에 NOT NULL 제약조건 추가해보세요
-- 이미 컬럼의 기본값은 null을 허용하는것으로 디폴트 되어있음 그렇기 때문에 add modify  로 해줘야함
 ALTER  TABLE t_emp4 ADD CONSTRAINT emp4_area_nn NOT NULL ;
 ALTER  TABLE t_emp4 MODIFY(area CONSTRAINT emp4_area_nn NOT NULL);

--#9007)   외래키 추가
-- t_emp4 테이블의 no 컬럼이 t_emp2 테이블의 
-- empno 컬럼의 값을 참조하도록 참조키 제약조건을 설정하세요
ALTER TABLE t_emp4 
ADD CONSTRAINT emp4_name_fk FOREIGN KEY(name) REFERENCES t_emp2(name);
-- 그냥 실행하면 에러다   ORA-02270: no matching unique or primary key for this column-list
-- 참조된느 부모테이블의 컬럼은 Primary Key 이거나 Unique 해야 한다!!!

-- 부모테이블의 name 을 UNIQUE 로 바꾼뒤 위의 쿼리를 다시 실행
ALTER TABLE t_emp2 ADD CONSTRAINT emp2_name_uk UNIQUE(name);
ALTER TABLE t_emp4 
ADD CONSTRAINT emp4_name_fk FOREIGN KEY(name) REFERENCES t_emp2(name);

--#9008)
DROP TABLE t_emp3 CASCADE CONSTRAINT;
CREATE TABLE t_emp3 (
	no NUMBER(4) CONSTRAINT emp3_no_pk PRIMARY KEY,
	name VARCHAR2(10) CONSTRAINT emp3_name_nn NOT NULL,
	jumin VARCHAR2(13) 
		CONSTRAINT emp3_jumin_nn NOT NULL 
		CONSTRAINT emp3_jumin_uk UNIQUE,
	area NUMBER(1) CONSTRAINT emp3_area_ck CHECK(area < 5),
	deptno VARCHAR2(6) CONSTRAINT emp3_deptno_fk REFERENCES t_dept2(dcode)
	ON DELETE CASCADE -- 부모 삭제되면 자식도 삭제
	--ON DELETE SET NULL --부모 삭제되면 NULL 값으로
);

--#9009 
-- t_emp4 테이블의 name 필드의 제약조건 에
-- 부모테이블이 삭제되면 NULL 이 되도록 설정하기 (ALTER 사용)

ALTER TABLE t_emp4 DROP CONSTRAINT emp4_name_fk; --일단 기존 제약조건 삭제

ALTER  TABLE  t_emp4
ADD CONSTRAINT emp4_name_fk FOREIGN KEY(name)
	REFERENCES t_emp2(name)
	ON DELETE SET NULL;  -- 부모 삭제되면 자식은 null 로 변환

--#9010
SELECT * FROM T_NOVALIDATE ;
SELECT  * FROM T_VALIDATE ;

SELECT OWNER , CONSTRAINT_NAME , CONSTRAINT_TYPE , status 
FROM USER_CONSTRAINTS
WHERE table_name='T_VALIDATE';
SELECT OWNER , CONSTRAINT_NAME , CONSTRAINT_TYPE , status 
FROM USER_CONSTRAINTS
WHERE table_name='T_NOVALIDATE';

INSERT  INTO T_validate VALUES(1,'ddd'); -- 처음에는 에러 PK임

-- p 기능을 활성화 하지 않음
ALTER TABLE T_NOVALIDATE  DISABLE NOVALIDATE CONSTRAINT SYS_C007029;

-- 중복되는 값이 들어가 있으면 활성화 되지 않음
ALTER TABLE T_NOVALIDATE  ENABLE NOVALIDATE CONSTRAINT SYS_C007029;

DELETE FROM t_novalidate WHERE name = 'DDD';
