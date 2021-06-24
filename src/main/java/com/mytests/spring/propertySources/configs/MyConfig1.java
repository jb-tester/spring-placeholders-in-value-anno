package com.mytests.spring.propertySources.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("/myconfig1.properties")
@ComponentScan("com.mytests.spring.propertySources.components")
public class MyConfig1 {
}
