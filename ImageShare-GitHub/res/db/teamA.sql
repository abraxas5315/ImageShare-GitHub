/* DB�쐬 */
DROP TABLE IF EXISTS t_article;
DROP TABLE IF EXISTS t_follow;
DROP TABLE IF EXISTS m_account;
DROP DATABASE teama;
CREATE DATABASE teama CHARACTER SET sjis COLLATE sjis_japanese_ci;

/* AUTOCOMMIT���� */
SET AUTOCOMMIT=0;

/* DB�I�� */
USE teamA;

CREATE TABLE m_account(
		account_id                    		VARCHAR(16)		 NOT NULL		 PRIMARY KEY,
		pass                          		CHARACTER(16)		 NOT NULL,
		name                          		VARCHAR(32)		 NOT NULL,
		my_image                      		VARCHAR(32)		 NOT NULL,
		profile                       		VARCHAR(32)		 NULL
)ENGINE = INNODB;

CREATE TABLE t_follow(
		follow_id                     		VARCHAR(16)		 NOT NULL,
		account_id                    		VARCHAR(16)		 NOT NULL,
  PRIMARY KEY (follow_id, account_id), 
  FOREIGN KEY (account_id) REFERENCES m_account (account_id)
)ENGINE = INNODB;

CREATE TABLE t_article(
		article_id                    		INT(255)		 NOT NULL		 PRIMARY KEY AUTO_INCREMENT,
		account_id                    		VARCHAR(16)		 NOT NULL,
		image_url                     		VARCHAR(255)		 NOT NULL,
		text                          		VARCHAR(255)		 NOT NULL,
		date                          		TIMESTAMP		 NOT NULL,
  FOREIGN KEY (account_id) REFERENCES m_account (account_id)
)ENGINE = INNODB;

CREATE INDEX IDX_t_article_1 ON t_article (account_id);

/* ������}�X�^INSERT */
INSERT INTO m_account VALUES('shiomi','shiomi','����','sample/a.jpg','������Ѓq���[�}���V�X�e��');
INSERT INTO m_account VALUES('kawashima','kawashima','�쓇','sample/b.jpg','������ЃP�A���b�c�E�A���h�E�p�[�g�i�[�Y');
INSERT INTO m_account VALUES('funo','funo','�z��','sample/c.jpg','������ЃP�A���b�c�E�A���h�E�p�[�g�i�[�Y');
INSERT INTO m_account VALUES('tukazawa','tukazawa','���V','sample/d.jpg','������Ѓ��x���t�@�C�u');

/* �t�H���[�e�[�u��INSERT */
INSERT INTO t_follow VALUES('shiomi','kawashima');
INSERT INTO t_follow VALUES('shiomi','funo');
INSERT INTO t_follow VALUES('shiomi','tukazawa');
INSERT INTO t_follow VALUES('tukazawa','funo');
INSERT INTO t_follow VALUES('tukazawa','kawashima');
INSERT INTO t_follow VALUES('funo','shiomi');

/* ���e�e�[�u��INSERT */
INSERT INTO t_article VALUES(1,'shiomi','sample/g.jpg','�e�X�g','2016-5-26 15:00:00');

/* �R�~�b�g */
commit;
