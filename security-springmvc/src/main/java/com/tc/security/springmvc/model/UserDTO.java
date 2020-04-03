package com.tc.security.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @Auther: tianchao
 * @Date: 2020/3/31 22:54
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    public static final String SESSION_USER_KEY = "_USER";
    private String id;
    private String username;
    private String password;
    private String fullName;
    private String mobile;
    /**
     * 用户权限
     */
    private Set<String> authorities;
}
