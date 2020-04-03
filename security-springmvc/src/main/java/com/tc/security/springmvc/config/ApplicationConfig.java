package com.tc.security.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @Auther: tianchao
 * @Date: 2020/3/31 22:29
 * @Description:
 */
@Configuration
@ComponentScan(basePackages = "com.tc.security.springmvc",
excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class ApplicationConfig {
}
