package com.tc.security.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @Auther: tianchao
 * @Date: 2020/3/31 22:33
 * @Description:
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.tc.security.springmvc",
includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class WebConfig implements WebMvcConfigurer {

    /**
     * 视图解析器
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/login");
    }



}
