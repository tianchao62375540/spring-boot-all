package com.tc.security.springmvc.init;

import com.tc.security.springmvc.config.ApplicationConfig;
import com.tc.security.springmvc.config.WebConfig;
import com.tc.security.springmvc.config.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Auther: tianchao
 * @Date: 2020/3/31 22:39
 * @Description:
 */
public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * Specify {@code @Configuration} and/or {@code @Component} classes for the
     * {@linkplain #createRootApplicationContext() root application context}.
     *
     * @return the configuration for the root application context, or {@code null}
     * if creation and registration of a root context is not desired
     *
     * spring容器
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class, WebSecurityConfig.class};
    }

    /**
     * Specify {@code @Configuration} and/or {@code @Component} classes for the
     * {@linkplain #createServletApplicationContext() Servlet application context}.
     *
     * @return the configuration for the Servlet application context, or
     * {@code null} if all configuration is specified through root config classes.
     *
     * ServletContent 相当于加载spring-mvc.xml
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * Specify the servlet mapping(s) for the {@code DispatcherServlet} &mdash;
     * for example {@code "/"}, {@code "/app"}, etc.
     *
     *
     * urlMapping
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
