package base.application.member.exception;

import base.domain.support.exception.InvalidValueException;

public class MemberDuplicatedException extends InvalidValueException {

    private static final String messgae = "은 이미 사용중입니다.";

    public MemberDuplicatedException(String email) {
        super(email + messgae);
    }
}
