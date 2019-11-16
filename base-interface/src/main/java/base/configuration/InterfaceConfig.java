package base.configuration;

import base.application.Application;
import base.interfaces.Interfaces;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { SpringSecurityConfig.class, SwaggerConfig.class })
@ComponentScan(basePackageClasses = { Interfaces.class, Application.class })
public class InterfaceConfig {
}
