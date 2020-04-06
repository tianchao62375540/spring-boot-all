package com.tc.security.distributed.uaa.model;


import lombok.Data;

/**
 * @Auther: tianchao
 * @Date: 2020/4/4 20:21
 * @Description:
 */
@Data
public class PermissionDTO {
    private Long id;
    private String code;
    private String description;
    private String url;
}
