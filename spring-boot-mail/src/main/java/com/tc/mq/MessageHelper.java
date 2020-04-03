package com.tc.mq;

import com.tc.util.JsonUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;

public class MessageHelper {

    public static Message objToMsg(Object obj) {
        if (null == obj) {
            return null;
        }

        Message message = MessageBuilder.withBody(JsonUtil.objToStr(obj).getBytes()).build();
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);// 消息持久化
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);

        return message;
    }

    public static <T> T msgToObj(Message message, Class<T> clazz) {
        if (null == message || null == clazz) {
            return null;
        }

        String str = new String(message.getBody());
        T obj = JsonUtil.strToObj(str, clazz);

        return obj;
    }

    public static void main(String[] args) {
        Node n1 = new Node();
        n1.value = 1;
        Node n2 = new Node();
        n2.value = 2;
        n1.next = n2;
        n2.next = n1;
        while (n1.next!=null&&n1.value!=3){
            n1 = n1.getNext();
        }
    }
    static class Node{
        Node next;
        public int value;

        public Node getNext() {
            System.out.println(value);
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
