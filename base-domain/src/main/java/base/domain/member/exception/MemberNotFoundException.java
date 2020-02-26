package base.domain.member.exception;

import base.domain.support.exception.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {

    private static final String MESSAGE = " is not found";

    public MemberNotFoundException(final Long memberId) {
        super(memberId+MESSAGE);
    }
}
