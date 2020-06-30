
/* Drop Tables */

DROP TABLE user CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE user
(
	u_uid number NOT NULL,
	u_id varchar2(20) NOT NULL UNIQUE,
	u_pw varchar2(20) NOT NULL,
	u_name varchar2(20),
	u_mail  NOT NULL UNIQUE,
	PRIMARY KEY (u_uid)
);



