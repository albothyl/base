CREATE TABLE `base`.`books` (
  `bookId`              BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '책 번호',
  `bookName`            VARCHAR(200)  NOT NULL                COMMENT '책 비밀번호',
  `memberId`            BIGINT(20)    NOT NULL                COMMENT '책 주인 멤버 번호',

  `createdAt`             DATETIME(6)   NOT NULL                COMMENT '책 등록 일시',
  `modifiedAt`            DATETIME(6)   NOT NULL                COMMENT '책정보 수정 일시',
  PRIMARY KEY (`bookId`),
  INDEX(memberId),
  FOREIGN KEY(memberId) REFERENCES members(memberId));