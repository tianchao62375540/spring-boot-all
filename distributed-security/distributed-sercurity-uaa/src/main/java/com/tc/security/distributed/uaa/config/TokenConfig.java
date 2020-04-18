package com.tc.security.distributed.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Auther: tianchao
 * @Date: 2020/4/5 20:51
 * @Description:
 */
@Configuration
public class TokenConfig {

    private String SIGNING_KEY = "uaa123";
    /**
     * 令牌存储策略
     * @return
     */
    /*@Bean
    public TokenStore tokenStore(){
        *//**
         * 使用内存存储令牌(普通令牌)
         *//*
        return new InMemoryTokenStore();
    }*/
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);//对称 资源服务器使用秘钥来验证
        return converter;
    }
}
