CREATE TABLE `base`.`members` (
  `memberId`              BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '회원 번호',
  `memberPassword`        VARCHAR(200)  NOT NULL                COMMENT '회원 비밀번호',
  `memberName`            VARCHAR(100)  NOT NULL                COMMENT '회원 이름',
  `memberEmail`           VARCHAR(150)  NOT NULL                COMMENT '회원 이메일',
  `memberAge`             INT           NOT NULL                COMMENT '회원 나이',
  `memberSex`             VARCHAR(10)   NOT NULL                COMMENT '회원 성별',
  `memberPhoneNumber`     VARCHAR(50)   NOT NULL                COMMENT '회원 연락가능 전화번호',

  `roles`                 VARCHAR(100)  NOT NULL                COMMENT '회원 등급',
  `accountNonExpired`     TINYINT(1)    NOT NULL DEFAULT '1'    COMMENT '계정 만료 여부',
  `accountNonLocked`      TINYINT(1)    NOT NULL DEFAULT '1'    COMMENT '계정 잠김 여부',
  `credentialsNonExpired` TINYINT(1)    NOT NULL DEFAULT '1'    COMMENT '인증 만료 여부',
  `enabled`               TINYINT(1)    NOT NULL DEFAULT '1'    COMMENT '회원 이용가능 여부',

  `createdAt`             DATETIME(6)   NOT NULL                COMMENT '회원 등록 일시',
  `modifiedAt`            DATETIME(6)   NOT NULL                COMMENT '회원정보 수정 일시',

  `street`                VARCHAR(50)   NOT NULL                COMMENT '회원 거주지 도로명',
  `city`                  VARCHAR(50)   NOT NULL                COMMENT '회원 거주지 도시명',
  `zipCode`               VARCHAR(50)   NOT NULL                COMMENT '회원 거주지 우편명',

  PRIMARY KEY (`memberId`),
  UNIQUE INDEX `memberEmail_UNIQUE` (`memberEmail` ASC),
  UNIQUE INDEX `memberPhoneNumber_UNIQUE` (`memberPhoneNumber` ASC));
