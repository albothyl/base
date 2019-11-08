package com.study.base.domain.member;

import com.study.base.domain.member.entity.Member;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.study.base.configration.SpringSecurityConfig.ROLE_PREFIX;

@Service
public class MemberFinder implements UserDetailsService {

	private MemberRepository memberRepository;

	public MemberFinder(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Optional<Member> findMemberById(Long memberId) {
		return memberRepository.findById(memberId);
	}

	public Optional<Member> findMemberByEmail(String memberEmail) {
		return memberRepository.findMemberByMemberEmail(memberEmail);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final Member member = memberRepository.findMemberByMemberEmail(email).orElseThrow(
			() -> new IllegalArgumentException(email + " is not founded"));

		final List<SimpleGrantedAuthority> authorities = member.getRoles().stream()
			.map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
			.collect(Collectors.toList());

		return User.builder()
			.username(member.getMemberEmail())
			.password(member.getMemberPassword())
			.authorities(authorities)
			.build();
	}
}
