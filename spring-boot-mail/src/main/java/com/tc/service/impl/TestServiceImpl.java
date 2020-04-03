package com.tc.service.impl;

import com.tc.common.ResponseCode;
import com.tc.common.ServerResponse;
import com.tc.config.RabbitmqConfig;
import com.tc.mail.Mail;
import com.tc.mapper.MsgLogMapper;
import com.tc.mq.MessageHelper;
import com.tc.pojo.MsgLog;
import com.tc.service.TestService;
import com.tc.util.RandomUtil;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: tianchao
 * @Date: 2020/3/18 20:32
 * @Description:
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MsgLogMapper msgLogMapper;

    @Override
    public ServerResponse testIdempotence() {
        return null;
    }

    @Override
    public ServerResponse accessLimit() {
        return null;
    }

    @Override
    public ServerResponse send(Mail mail) {
        String msgId = RandomUtil.UUID32();
        mail.setMsgId(msgId);
        MsgLog msgLog = new MsgLog(msgId, mail, RabbitmqConfig.MAIL_EXCHANGE_NAME, RabbitmqConfig.MAIL_ROUTING_KEY_NAME);
        msgLogMapper.insert(msgLog);// 消息入库*/
        CorrelationData correlationData = new CorrelationData(msgId);
        MessageHelper.objToMsg(mail);
        rabbitTemplate.convertAndSend(RabbitmqConfig.MAIL_EXCHANGE_NAME, RabbitmqConfig.MAIL_ROUTING_KEY_NAME,mail, correlationData);// 发送消息
        return ServerResponse.success(ResponseCode.MAIL_SEND_SUCCESS.getMsg());
    }
}
