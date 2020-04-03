package com.tc.security.springmvc.model;

import lombok.Data;

/**
 * @Auther: tianchao
 * @Date: 2020/3/31 22:53
 * @Description:
 */
@Data
public class AuthenticationRequest {

    private String username;

    private String password;
}
