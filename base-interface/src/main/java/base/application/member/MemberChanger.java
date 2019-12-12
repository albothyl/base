package base.application.member;

public interface MemberChanger {

    void changePassword(Long memberId, String newPassword);
}
