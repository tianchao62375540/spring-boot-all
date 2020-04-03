package com.tc.exception;

/**
 * @Auther: tianchao
 * @Date: 2020/3/20 18:25
 * @Description:
 */
public class SendMailFailException extends RuntimeException {
    public SendMailFailException(String message) {
        super(message);
    }
}
