/* DB作成 */
DROP TABLE IF EXISTS t_article;
DROP TABLE IF EXISTS t_follow;
DROP TABLE IF EXISTS m_account;
DROP DATABASE teama;
CREATE DATABASE teama CHARACTER SET sjis COLLATE sjis_japanese_ci;

/* AUTOCOMMIT無効 */
SET AUTOCOMMIT=0;

/* DB選択 */
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

/* 会員情報マスタINSERT */
INSERT INTO m_account VALUES('shiomi','shiomi','塩見','sample/a.jpg','株式会社ヒューマンシステム');
INSERT INTO m_account VALUES('kawashima','kawashima','川島','sample/b.jpg','株式会社ケアリッツ・アンド・パートナーズ');
INSERT INTO m_account VALUES('funo','funo','布野','sample/c.jpg','株式会社ケアリッツ・アンド・パートナーズ');
INSERT INTO m_account VALUES('tukazawa','tukazawa','塚澤','sample/d.jpg','株式会社レベルファイブ');

/* フォローテーブルINSERT */
INSERT INTO t_follow VALUES('shiomi','kawashima');
INSERT INTO t_follow VALUES('shiomi','funo');
INSERT INTO t_follow VALUES('shiomi','tukazawa');
INSERT INTO t_follow VALUES('tukazawa','funo');
INSERT INTO t_follow VALUES('tukazawa','kawashima');
INSERT INTO t_follow VALUES('funo','shiomi');

/* 投稿テーブルINSERT */
INSERT INTO t_article VALUES(1,'shiomi','sample/g.jpg','テスト','2016-5-26 15:00:00');

/* コミット */
commit;
