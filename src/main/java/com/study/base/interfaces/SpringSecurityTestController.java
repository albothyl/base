package com.study.base.interfaces;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.study.base.configration.SpringSecurityConfig.ROLE_PREFIX;

@Deprecated
@RestController
public class SpringSecurityTestController {

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
		return "base/role/ADMIN";
	}
}
