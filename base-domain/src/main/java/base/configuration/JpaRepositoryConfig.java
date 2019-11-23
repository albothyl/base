package base.configuration;

import base.domain.Domain;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackageClasses = { Domain.class, Jsr310JpaConverters.class })
@EnableJpaRepositories(basePackageClasses = { Domain.class })
public class JpaRepositoryConfig {
}
