package base.configuration;

import base.domain.Domain;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import(value = { JpaRepositoryConfig.class })
@ComponentScan(basePackageClasses = Domain.class)
public class DomainConfig {
}
