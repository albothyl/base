package com.study.base.configration;

import com.study.base.infra.Infra;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Infra.class)
public class InfraConfig {
}
