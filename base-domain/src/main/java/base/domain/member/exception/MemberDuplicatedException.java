package base.domain.member.exception;

import base.domain.support.exception.InvalidValueException;

public class MemberDuplicatedException extends InvalidValueException {

    private static final String MESSAGE = " 해당 이메일은 이미 사용중입니다.";

    public MemberDuplicatedException(String email) {
        super(email + MESSAGE);
    }
}
