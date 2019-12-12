package base.interfaces.member;

import base.application.member.MemberChanger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberControllerImpl implements MemberController {

  private final MemberChanger memberChanger;
  
  @Override
  @PatchMapping("/members/{memberId}")
  public ResponseEntity updatePassword(@PathVariable Long memberId, @RequestBody String newPassword) {
    memberChanger.changePassword(memberId, newPassword);

    return ResponseEntity.ok().build();
  }
}
