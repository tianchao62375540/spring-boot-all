package com.tc.common;

/**
 * @Auther: tianchao
 * @Date: 2020/3/18 20:24
 * @Description:
 */
public class Constant {
    public interface Redis {
        String OK = "OK";
        Integer EXPIRE_TIME_MINUTE = 60;// 过期时间, 60s, 一分钟
        Integer EXPIRE_TIME_HOUR = 60 * 60;// 过期时间, 一小时
        Integer EXPIRE_TIME_DAY = 60 * 60 * 24;// 过期时间, 一天
        String TOKEN_PREFIX = "token:";
        String MSG_CONSUMER_PREFIX = "consumer:";
        String ACCESS_LIMIT_PREFIX = "accessLimit:";
    }

    public interface LogType {
        Integer LOGIN = 1;// 登录
        Integer LOGOUT = 2;// 登出
    }

    public interface MsgLogStatus {
        Integer ROUTINGKEY_FAIL = -1;//消息投递到exchange投递不到queue
        Integer DELIVERING = 0;// 消息投递中
        Integer DELIVER_SUCCESS = 1;// 投递成功
        Integer DELIVER_FAIL = 2;// 投递失败
        Integer CONSUMED_SUCCESS = 3;// 已消费
    }
}
