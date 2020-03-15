package com.tc.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther: tianchao
 * @Date: 2020/3/15 00:38
 * @Description:
 */
@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String username;
    private Integer age;
    private String phone;
    private String desc;
}
