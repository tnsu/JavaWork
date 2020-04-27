
/* Drop Tables */

DROP TABLE regster CASCADE CONSTRAINTS;
DROP TABLE class CASCADE CONSTRAINTS;
DROP TABLE professor CASCADE CONSTRAINTS;
DROP TABLE student CASCADE CONSTRAINTS;
DROP TABLE department CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE class
(
	c_num number NOT NULL,
	c_name varchar2(20) NOT NULL,
	c_sudnum number,
	c_classnum varchar2(5) NOT NULL,
	c_personal number NOT NULL,
	c_year date,
	p_no number NOT NULL,
	PRIMARY KEY (c_num)
);


CREATE TABLE department
(
	d_num number NOT NULL,
	d_phone varchar2(15),
	d_office varchar2(5),
	d_name varchar2(10) NOT NULL,
	PRIMARY KEY (d_num)
);


CREATE TABLE professor
(
	p_no number NOT NULL,
	p_jumin char(13),
	p_address varchar2(30),
	p_name varchar2(10) NOT NULL,
	p_position varchar2(10),
	p_phone varchar2(15),
	p_hiredate date,
	d_num number NOT NULL,
	PRIMARY KEY (p_no)
);


CREATE TABLE regster
(
	studno number NOT NULL,
	c_num number NOT NULL
);


CREATE TABLE student
(
	studno number NOT NULL,
	s_name varchar2(10) NOT NULL,
	s_grade number,
	s_phone varchar2(15),
	s_address varchar2(30),
	s_jumin char(13) NOT NULL,
	d_num number NOT NULL,
	PRIMARY KEY (studno)
);



/* Create Foreign Keys */

ALTER TABLE regster
	ADD FOREIGN KEY (c_num)
	REFERENCES class (c_num)
;


ALTER TABLE professor
	ADD FOREIGN KEY (d_num)
	REFERENCES department (d_num)
;


ALTER TABLE student
	ADD FOREIGN KEY (d_num)
	REFERENCES department (d_num)
;


ALTER TABLE class
	ADD FOREIGN KEY (p_no)
	REFERENCES professor (p_no)
;


ALTER TABLE regster
	ADD FOREIGN KEY (studno)
	REFERENCES student (studno)
;



