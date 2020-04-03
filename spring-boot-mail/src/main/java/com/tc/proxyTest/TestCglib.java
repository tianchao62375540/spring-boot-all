package com.tc.proxyTest;

import com.tc.mail.Mail;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import javax.validation.constraints.NotBlank;
import java.lang.reflect.Method;

/**
 * @Auther: tianchao
 * @Date: 2020/3/20 22:07
 * @Description:
 */
public class TestCglib implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("================qianzhi:" + method.toGenericString());
        Object o1 ;
        if (objects!=null){
            o1 = methodProxy.invokeSuper(o, new Object[]{"滚犊子"});
        }else{
            o1 = methodProxy.invokeSuper(o, objects);
        }

        System.out.println("================houzhi:" + method.toGenericString());
        return o1;
    }

    public static void main(String[] args) {
        TestCglib interceptor = new TestCglib();
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(interceptor);
        enhancer.setSuperclass(Mail.class);
        Mail o =(Mail) enhancer.create();
        o.setContent("hehe");
        @NotBlank(message = "正文不能为空") String content = o.getContent();
        System.out.println(content);

    }
}
