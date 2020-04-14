package com.tc.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Auther: tianchao
 * @Date: 2020/4/10 21:32
 * @Description:
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    SYSTEM_ERROR(500,"网络系统异常"),
    ;
    private int code;
    private String msg;

}
