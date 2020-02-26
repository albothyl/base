package base.domain.member.exception;

import base.domain.support.exception.InvalidValueException;

public class MemberPasswordEqualException extends InvalidValueException {

    private static final String MESSAGE = "new password and exist password are equal";

    public MemberPasswordEqualException() {
        super(MESSAGE);
    }
}
