package com.tc.service.impl;

import com.tc.mapper.MsgLogMapper;
import com.tc.pojo.MsgLog;
import com.tc.service.MsgLogService;
import com.tc.util.JodaTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/3/18 22:01
 * @Description:
 */
@Service
public class MsgLogServiceImpl implements MsgLogService {
    @Autowired
    private MsgLogMapper msgLogMapper;

    @Override
    public void updateStatus(String msgId, Integer status) {
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setStatus(status);
        msgLog.setUpdateTime(new Date());
        msgLogMapper.updateStatus(msgLog);
    }

    @Override
    public MsgLog selectByMsgId(String msgId) {
        return msgLogMapper.selectByPrimaryKey(msgId);
    }

    @Override
    public List<MsgLog> selectTimeoutMsg() {
        return msgLogMapper.selectTimeoutMsg();
    }

    @Override
    public void updateTryCount(String msgId, Date tryTime) {
        Date nextTryTime = JodaTimeUtil.plusMinutes(tryTime, 1);
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setNextTryTime(nextTryTime);
        msgLogMapper.updateTryCount(msgLog);
    }

    @Override
    public void updateTryCount(String msgId) {
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLogMapper.updateTryCount(msgLog);
    }
}
