package com.tc.postprocessor;

import com.tc.config.RabbitmqConfig;
import com.tc.mapper.MsgLogMapper;
import com.tc.mq.MessageHelper;
import com.tc.pojo.CustomerMsg;
import com.tc.pojo.MsgLog;
import com.tc.util.RandomUtil;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Auther: tianchao
 * @Date: 2020/3/20 20:42
 * @Description:
 */
//@Component
public class RabbitmqTemplateBeanPostProcessor implements BeanPostProcessor, MethodInterceptor {
    @Autowired
    private MsgLogMapper msgLogMapper;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == RabbitTemplate.class ){
            return doCglibProxy(bean);
        }
        return bean;
    }

    private Object doCglibProxy(Object bean) {
        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(RabbitTemplate.class);
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        /*boolean isProxyMethod = method.toGenericString().equals(RabbitTemplate.class.getMethod("convertAndSend"
                , String.class,String.class,Object.class, CorrelationData.class).toGenericString());
        if (isProxyMethod){
            String exchange = (String)args[0];
            String routingKey = (String) args[1];
            Object object = args[2];
            CorrelationData correlationData = (CorrelationData)args[3];
            if (object instanceof CustomerMsg){
                ((CustomerMsg)object).setMsgId(correlationData.getId());
            }
            object = MessageHelper.objToMsg(object);
            args[2] = object;
            MsgLog msgLog = new MsgLog(correlationData.getId(), object, exchange, routingKey);
            msgLogMapper.insert(msgLog);
        }*/
        Object o = methodProxy.invokeSuper(obj, args);
        return o;
    }
}
