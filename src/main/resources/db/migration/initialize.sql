CREATE DATABASE base DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

CREATE USER 'study'@'%' IDENTIFIED BY 'hard';
CREATE USER 'study'@'localhost' IDENTIFIED BY 'hard';

USE mysql;

-- DB 별 권한 부여
INSERT INTO db (HOST,Db,USER,Select_priv,Insert_priv,Update_priv,Delete_priv,Create_priv,Drop_priv,Index_priv, Alter_priv) VALUES('%','base','study','Y','Y','Y','Y','Y','Y','Y','Y');
INSERT INTO db (HOST,Db,USER,Select_priv,Insert_priv,Update_priv,Delete_priv,Create_priv,Drop_priv,Index_priv, Alter_priv) VALUES('localhost','base','study','Y','Y','Y','Y','Y','Y','Y','Y');


-- 마무리.
FLUSH PRIVILEGES;