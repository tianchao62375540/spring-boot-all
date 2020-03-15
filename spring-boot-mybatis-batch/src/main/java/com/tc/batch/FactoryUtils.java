package com.tc.batch;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Auther: tianchao
 * @Date: 2020/3/15 13:19
 * @Description:
 */
@Component
public class FactoryUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext = applicationContext;
    }

    public static <T> T getInstance(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
}
