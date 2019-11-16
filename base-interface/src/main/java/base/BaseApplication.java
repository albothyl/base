package base;

import base.configuration.DomainConfig;
import base.configuration.InterfaceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Import(value = { InterfaceConfig.class, DomainConfig.class })
public class BaseApplication {

	@Autowired
	private Environment environment;

	@PostConstruct
	public void init() {
		System.out.println();
	}

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}
}
