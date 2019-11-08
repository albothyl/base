package com.study.base.configration;

import com.study.base.domain.Domain;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackageClasses = { Domain.class, Jsr310JpaConverters.class })
public class JpaRepositoryConfig {
}
