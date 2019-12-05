package base.application.member;

public interface MemberService {

    boolean changePassword(Long memberId, String newPassword);
}
