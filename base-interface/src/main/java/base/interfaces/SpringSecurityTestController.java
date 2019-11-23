package base.interfaces;

import base.domain.member.MemberFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static base.configuration.SpringSecurityConfig.ROLE_PREFIX;

@Deprecated
@RestController
public class SpringSecurityTestController {

	@Autowired
	private MemberFinder memberFinder;

	@GetMapping("/base")
	public String base() {
		return "Hello Login Base Project";
	}

	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}

	@Secured("MEMBER_READ")
	@GetMapping("/base/role")
	public String role() {
		return "base/role/MEMBER_READ";
	}

	@Secured(ROLE_PREFIX + "ADMIN")
	@GetMapping("/base/role/admin")
	public String admin() {
		return memberFinder.findMemberByEmail("admin@naver.com").get().toString();
	}
}
