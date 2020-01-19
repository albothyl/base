package base.domain.member.exception;

public class MemberDuplicatedException extends RuntimeException {

    private static final String messgae = "은 이미 사용중입니다.";

    public MemberDuplicatedException(String email) {
        super(email + messgae);
    }
}
