package base.domain.member.exception;

import base.domain.support.exception.InvalidValueException;

public class MemberPasswordEqualException extends InvalidValueException {

  public MemberPasswordEqualException(String msg) {
    super(msg);
  }
}
