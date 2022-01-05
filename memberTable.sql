SELECT * FROM member;

CREATE TABLE member(
	name VARCHAR2(10),
	userid VARCHAR2(10),
	pwd VARCHAR2(10),
	email VARCHAR2(20),
	phone VARCHAR2(15),
	admin number(1) DEFAULT 0, -- 0 : 일반사용자, 1 : 관리자
	PRIMARY KEY(userid)
);


INSERT INTO member VALUES('노지선', 'ROSE', '1234', 'noline@naver.com', '010-1234-1234', 0);
INSERT INTO member VALUES('송하영', 'SONG', '1234', 'song@naver.com', '010-5555-5555', 0);
INSERT INTO member VALUES('이채영', 'CHANG', '1234', 'lee@naver.com', '010-2222-3333', 0);

--ALTER TABLE member ALTER COLUMN email VARCHAR2(20) NULL;
ALTER TABLE member MODIFY email VARCHAR2(20);
ALTER TABLE member MODIFY email VARCHAR2(40);
ALTER TABLE member MODIFY phone VARCHAR2(15);

SELECT * FROM member;

UPDATE member SET userid='rose' WHERE name LIKE '%노%';
UPDATE member SET userid='song' WHERE name LIKE '%송%';
UPDATE member SET userid='chang' WHERE name LIKE '%채%';

INSERT INTO member VALUES('박지원', 'park', null, 'jiji@naver.com', '010-8888-9999', 0);
INSERT INTO member VALUES('백지헌', 'honey', '', 'backback@naver.com', '010-8888-4545', 0);

INSERT INTO member(pwd) VALUES('1234');

UPDATE member SET pwd='1234' WHERE name LIKE '%박%'
UPDATE member SET pwd='1234' WHERE name LIKE '%백%'

UPDATE member SET admin=1 WHERE userid='rose';

SELECT * FROM member;
