package com.study.base.configration;

import com.study.base.domain.Domain;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { JpaRepositoryConfig.class })
@ComponentScan(basePackageClasses = Domain.class)
public class DomainConfig {
}

