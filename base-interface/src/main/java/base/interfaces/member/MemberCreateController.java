package base.interfaces.member;

import base.application.member.MemberRegistrationProvider;
import base.domain.member.entity.Member;
import base.interfaces.member.converter.SignUpMemberToMemberConverter;
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

    private final MemberRegistrationProvider memberRegistrationProvider;
    private final SignUpMemberToMemberConverter signUpMemberToMemberConverter;

    @PostMapping(value = "/members")
    public ResponseEntity signUpMember(@RequestBody @Valid final SignUpMember signUpMember
            , Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Member member = signUpMemberToMemberConverter.convert(signUpMember);
        Member savedMember = memberRegistrationProvider.signUp(member);

        return ResponseEntity.ok(savedMember);
    }
}
