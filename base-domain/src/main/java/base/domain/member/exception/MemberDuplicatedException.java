package base.domain.member.exception;

public class MemberDuplicatedException extends RuntimeException {

    public MemberDuplicatedException(String msg) {
        super(msg);
    }
}
