package com.tc.security.springmvc.service;

import com.tc.security.springmvc.model.AuthenticationRequest;
import com.tc.security.springmvc.model.UserDTO;

/**
 * @Auther: tianchao
 * @Date: 2020/3/31 22:52
 * @Description:
 */
public interface AuthenticationService {
    /**
     * 用户认证
     * @param authenticationRequest 用户认证请求,账号和密码
     * @return 认证成功的用户信息
     */
    UserDTO authentication(AuthenticationRequest authenticationRequest);
}
