package base.interfaces.member;

import base.domain.member.MemberSaver;
import base.domain.member.entity.Member;
import base.interfaces.member.dto.SignUpMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberCreateController {

    private final MemberSaver memberSaver;

    @PostMapping(value = "/members")
    public ResponseEntity<Member> signUpMember(@RequestBody @Valid final SignUpMember signUpMember
            , Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Member member = signUpMember.signUpMemberToMember();
        Member savedMember = memberSaver.signUp(member);

        return ResponseEntity.ok(savedMember);
    }
}
