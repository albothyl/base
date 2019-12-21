CREATE TABLE `base`.`board` (
  `seq`                   BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '순서',
  `title`                 VARCHAR(100)  NOT NULL                COMMENT '제목',
  `content`               VARCHAR(300)  NOT NULL                COMMENT '내용',
  `createDate`            DATETIME   NOT NULL DEFAULT   CURRENT_TIMESTAMP               COMMENT '등록일시',
  PRIMARY KEY (`seq`));