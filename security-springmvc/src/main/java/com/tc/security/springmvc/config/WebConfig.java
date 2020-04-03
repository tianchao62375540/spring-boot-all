package com.tc.security.springmvc.config;

import com.tc.security.springmvc.interceptors.SimpleAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.MappedInterceptor;
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

    @Autowired
    private SimpleAuthenticationInterceptor simpleAuthenticationInterceptor;
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
        registry.addViewController("/").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(simpleAuthenticationInterceptor).addPathPatterns("/r/**");
    }
}
