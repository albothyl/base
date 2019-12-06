package base.interfaces.member;

import base.application.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberControllerImpl implements MemberController {

  private final MemberService memberService;

  @Override
  @PatchMapping("/members/{memberId}")
  public ResponseEntity<Boolean> updatePassword(@PathVariable Long memberId, @RequestParam String newPassword) {
    boolean result = memberService.changePassword(memberId, newPassword);

    return ResponseEntity.ok(result);
  }
}
