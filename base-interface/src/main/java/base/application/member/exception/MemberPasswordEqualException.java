package base.application.member.exception;

public class MemberPasswordEqualException extends RuntimeException {

  public MemberPasswordEqualException(String msg) {
    super(msg);
  }
}
