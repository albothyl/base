package base.application;

import base.application.member.MemberFinder;
import base.domain.member.entity.Member;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static base.configuration.SpringSecurityConfig.ROLE_PREFIX;

@Service
public class BaseUserDetailService implements UserDetailsService {

	private MemberFinder memberFinder;

	public BaseUserDetailService(MemberFinder memberFinder) {
		this.memberFinder = memberFinder;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final Member member = memberFinder.findMemberByEmail(email).orElseThrow(
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
