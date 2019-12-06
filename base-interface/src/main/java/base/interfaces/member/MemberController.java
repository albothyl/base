package base.interfaces.member;

import org.springframework.http.ResponseEntity;

public interface MemberController {

  ResponseEntity<Boolean> updatePassword(Long memberId, String newPassword);
}
