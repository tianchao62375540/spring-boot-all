package com.tc.service;


import com.tc.pojo.MsgLog;

import java.util.Date;
import java.util.List;

public interface MsgLogService {

    void updateStatus(String msgId, Integer status);

    MsgLog selectByMsgId(String msgId);

    List<MsgLog> selectTimeoutMsg();

    void updateTryCount(String msgId, Date tryTime);

    void updateTryCount(String msgId);
}
