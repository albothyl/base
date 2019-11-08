package com.study.base;

import com.study.base.configration.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(value = {
	SpringSecurityConfig.class,
	InterfaceConfig.class,
	ApplicationConfig.class,
	InfraConfig.class,
	DomainConfig.class })
@SpringBootApplication
public class BaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}
}
