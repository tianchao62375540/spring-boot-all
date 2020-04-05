package com.tc.security.springboot.model;

import lombok.Data;

/**
 * @Auther: tianchao
 * @Date: 2020/4/4 18:25
 * @Description:
 */
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
}
