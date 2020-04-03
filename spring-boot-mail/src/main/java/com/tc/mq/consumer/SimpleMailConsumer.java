package com.tc.mq.consumer;

import com.rabbitmq.client.Channel;
import com.tc.common.Constant;
import com.tc.config.RabbitmqConfig;
import com.tc.mail.Mail;
import com.tc.mail.MailUtil;
import com.tc.mq.MessageHelper;
import com.tc.pojo.MsgLog;
import com.tc.service.MsgLogService;
import com.tc.task.ResendMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class SimpleMailConsumer {

    @Autowired
    private MsgLogService msgLogService;

    @Autowired
    private MailUtil mailUtil;

    @RabbitListener(queues = RabbitmqConfig.MAIL_QUEUE_NAME,containerFactory = "simpleRabbitListenerContainerFactory")
    public void consume(Message message, Channel channel) throws IOException {
        Mail mail = MessageHelper.msgToObj(message, Mail.class);
        log.info("收到消息: {}", mail.toString());

        String msgId = mail.getMsgId();

        MsgLog msgLog = msgLogService.selectByMsgId(msgId);
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        if (null == msgLog || msgLog.getStatus().equals(Constant.MsgLogStatus.CONSUMED_SUCCESS)) {// 消费幂等性
            log.info("重复消费, msgId: {}", msgId);
            channel.basicNack(tag, false, false);
            return;
        }
        try {
            mailUtil.send(mail);
            msgLogService.updateStatus(msgId, Constant.MsgLogStatus.CONSUMED_SUCCESS);
            channel.basicAck(tag, false);// 消费确认
        }catch (Exception e){
            log.error("[消息处理异常,{}]"+e.getMessage());
            if (msgLog.getTryCount()>= ResendMsg.MAX_TRY_COUNT){
                msgLogService.updateStatus(msgId, Constant.MsgLogStatus.DELIVER_FAIL);
                log.info("出错三次,丢弃消息, msgId: {}", msgId);
                channel.basicNack(tag, false, false);
                return;
            }
            msgLogService.updateTryCount(msgId);// 投递次数+1
            channel.basicNack(tag, false, true);
        }
    }

}
