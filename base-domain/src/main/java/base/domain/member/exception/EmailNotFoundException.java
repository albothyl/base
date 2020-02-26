package base.domain.member.exception;

import base.domain.support.exception.EntityNotFoundException;

public class EmailNotFoundException extends EntityNotFoundException {

    private static final String MESSAGE = " 해당 이메일을 찾을 수 없습니다. 다시 확인해 주세요";

    public EmailNotFoundException(final String memberEmail){
        super(memberEmail+MESSAGE);
    }
}
