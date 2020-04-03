package com.tc.postprocessor;

import org.junit.Before;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.support.Correlation;
import org.springframework.stereotype.Component;


/**
 * @Auther: tianchao
 * @Date: 2020/3/22 09:25
 * @Description:
 */
@Component
public class LogBeforePublishPostProcessors implements MessagePostProcessor {
    /**
     * Change (or replace) the message.
     *
     * @param message the message.
     * @return the message.
     * @throws AmqpException an exception.
     */
    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        return null;
    }

    /**
     * Change (or replace) the message and/or change its correlation data.
     *
     * @param message     the message.
     * @param correlation the correlation data.
     * @return the message.
     * @since 1.6.7
     */
    @Override
    public Message postProcessMessage(Message message, Correlation correlation) {
        System.err.println(message.getMessageProperties()+"  "+new String(message.getBody()));
        System.out.println(correlation);
        return message;
    }
}
