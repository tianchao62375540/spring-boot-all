package com.tc.security.distributed.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @Auther: tianchao
 * @Date: 2020/4/5 20:23
 * @Description: 授权服务的配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    /**
     * 令牌存储策略
     */
    @Autowired
    private TokenStore tokenStore;
    /**
     * 客户端详情服务
     */
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 配置客户端详情服务
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*clients.inMemory()
                //客户端id
                .withClient("c1")
                //客户端秘钥
                .secret(new BCryptPasswordEncoder().encode("secret"))
                //客户端可以访问的资源id
                .resourceIds("res1")
                //该client允许的授权类型  授权码模式 密码模式 客户端模式 简要模式 刷新令牌
                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")
                //允许的授权范围（相当于客户端的权限）
                .scopes("all")
                //false跳转到授权页面，让用户授权，是ture的话就直接默认同意了
                .autoApprove(false)
                .redirectUris("http://www.baidu.com")
                ;*/
        //数据库
        clients.withClientDetails(clientDetailsService);
    }

    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource){
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    /**
     *令牌管理服务
     * @return
     */
    public AuthorizationServerTokenServices tokenService(){
        DefaultTokenServices service = new DefaultTokenServices();
        //客户端详情服务
        service.setClientDetailsService(clientDetailsService);
        //支持刷新令牌
        service.setSupportRefreshToken(true);
        //令牌存储
        service.setTokenStore(tokenStore);
        /**
         * jwt converter 设置进去 ，令牌增强
         */
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);
        //令牌默认有效期2小时
        service.setAccessTokenValiditySeconds(7200);
        //刷新令牌默认有效期2天
        service.setRefreshTokenValiditySeconds(259200);
        return service;
    }

    /**
     * 设置授权码模式的授权码如何存取，暂时采用内存方式
     * @return
     */
   /* @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }*/

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource){
        //设置授权码模式的授权码如何存取
        return new JdbcAuthorizationCodeServices(dataSource);
    }


    /**
     * 令牌访问端点
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //认证管理器 //针对密码模式
                .authenticationManager(authenticationManager)
                //授权码服务 //针对授权码模式
                .authorizationCodeServices(authorizationCodeServices)
                //令牌管理五福
                .tokenServices(tokenService())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     * 安全约束配置
     * 允许用户如何访问
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //令牌端点 /oauth/token_key是公开
                .tokenKeyAccess("permitAll()")
                //用于资源服务访问的令牌解析端点/oauth/check_token 设置公开
                .checkTokenAccess("permitAll()")
                //表单认证
                .allowFormAuthenticationForClients()
                ;
    }
}
