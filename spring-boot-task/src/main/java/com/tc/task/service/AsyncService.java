package com.tc.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Auther: tianchao
 * @Date: 2020/3/26 20:49
 * @Description:
 */
@Service
public class AsyncService {
    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中。。。");
    }
}
