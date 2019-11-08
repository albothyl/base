package com.study.base.configration;

import com.study.base.interfaces.Interfaces;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Interfaces.class)
public class InterfaceConfig {
}
