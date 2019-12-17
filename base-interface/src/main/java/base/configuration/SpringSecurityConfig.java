package base.configuration;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String ROLE_PREFIX = "ROLE_";

	@Autowired
	private UserDetailsService baseUserDetailService;

	@Bean
	public SessionRegistry sessionRegistryImpl() {
		return new SessionRegistryImpl();
	}

	@Bean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults(Strings.EMPTY); // Remove the "ROLE_" prefix
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder
			.userDetailsService(baseUserDetailService)
			.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
			.and()
			.eraseCredentials(true);
	}

	@Override
	// TODO: 17/12/2019 "/boards" "/boards/{seq}" delete  
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeRequests()
				.antMatchers("/resources", "/signup", "/hello", "/public", "/boards", "/boards/{seq}")
					.permitAll()
				.anyRequest()
					.authenticated()
				.and()

			.formLogin()
				.permitAll()
				.and()

			.logout()
				.logoutUrl("/logout").logoutSuccessUrl("/login?loggedOut")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID")
				.permitAll()
				.and()

			.exceptionHandling()
				.accessDeniedPage("/accessDenied")
				.and()

			.sessionManagement()
				.sessionFixation().changeSessionId()
				.maximumSessions(1).maxSessionsPreventsLogin(true)
				.sessionRegistry(this.sessionRegistryImpl())
				.and().and()

			.csrf()
				.disable()

			.rememberMe();
	}
}
