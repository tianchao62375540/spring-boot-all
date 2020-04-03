package com.tc.security.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Auther: tianchao
 * @Date: 2020/4/1 20:38
 * @Description:
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 定义用户信息服务
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        inMemoryUserDetailsManager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
        return inMemoryUserDetailsManager;
    }

    /**
     * 密码编码器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 安全拦截机制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()//允许表单登录
                .successForwardUrl("/login-success");//自定义登录成功的页面地址
        super.configure(http);
    }
}
