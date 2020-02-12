package base.interfaces.member;

import base.application.member.MemberRegistrationProvider;
import base.domain.member.MemberModifier;
import base.domain.member.entity.Member;
import base.interfaces.member.dto.MemberSignUpRequest;
import base.interfaces.member.dto.MemberSingUpResponse;
import base.support.PasswordEncoderUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberModifier memberModifier;
    private final ModelMapper modelMapper;
    private final MemberRegistrationProvider memberRegistrationProvider;

    @PatchMapping("/members/{memberId}")
    public ResponseEntity updatePassword(@PathVariable @NonNull Long memberId,
                                         @RequestBody @NonNull String newPassword) {

        String encodePassword = PasswordEncoderUtils.passwordEncode(newPassword);
        memberModifier.changePassword(memberId, encodePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/members")
    public ResponseEntity signUpMember(@RequestBody @Valid final MemberSignUpRequest memberSignUpRequest
            , Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Member member = modelMapper.map(memberSignUpRequest, Member.class);

        Member savedMember = memberRegistrationProvider.signUp(member);

        return ResponseEntity.ok(modelMapper.map(savedMember, MemberSingUpResponse.class));
    }
}
