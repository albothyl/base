package base.domain.member.exception;

import base.domain.support.exception.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(String msg) {
        super(msg);
    }
}
