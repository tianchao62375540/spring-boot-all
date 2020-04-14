package com.tc.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: tianchao
 * @Date: 2020/4/12 20:59
 * @Description:
 */

public class ServiceException extends RuntimeException {
    private ExceptionEnum exceptionEnum;

    public ServiceException(ExceptionEnum exceptionEnum){
        this.exceptionEnum = exceptionEnum;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
