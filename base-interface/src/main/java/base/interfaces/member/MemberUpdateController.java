package base.interfaces.member;

import base.domain.member.MemberChanger;
import base.support.PasswordEncoderUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberUpdateController {

  private final MemberChanger memberChanger;

  @PatchMapping("/members/{memberId}")
  public ResponseEntity updatePassword(@PathVariable @NonNull Long memberId,
      @RequestBody @NonNull String newPassword) {

    String encodePassword = PasswordEncoderUtils.passwordEncode(newPassword);
    memberChanger.changePassword(memberId, encodePassword);

    return ResponseEntity.ok().build();
  }
}
