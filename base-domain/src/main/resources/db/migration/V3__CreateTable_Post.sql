CREATE TABLE `base`.`posts` (
 `postId`                BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '게시글 번호',
 `memberId`              BIGINT(20)    NOT NULL                COMMENT '작성자',
 `boardType`             VARCHAR(30)   NOT NULL                 COMMENT '게시판 종류',
 `title`                 VARCHAR(300)  NOT NULL                COMMENT '제목',
 `contents`              TINYTEXT      NOT NULL                COMMENT '내용',
 `readCount`             BIGINT(20)    NOT NULL  DEFAULT 0     COMMENT '조회수',
 `createdAt`             DATETIME(6)   NOT NULL                COMMENT '게시글 등록 일시',
 `modifiedAt`            DATETIME(6)   NOT NULL                COMMENT '게시글 수정 일시',

 PRIMARY KEY (`postId`);



 CREATE TABLE `base`.`comments` (
 `commentId`             BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '댓글 번호',
 `postId`                BIGINT(20)    NOT NULL                COMMENT '게시글 번호',
 `memberId`              BIGINT(20)    NOT NULL                COMMENT '작성자',
 `contents`              TINYTEXT      NOT NULL                COMMENT '댓글 내용',
 `createdAt`             DATETIME(6)   NOT NULL                COMMENT '댓글 등록 일시',
 `modifiedAt`            DATETIME(6)   NOT NULL                COMMENT '댓글 수정 일시',

 PRIMARY KEY (`commentId`);