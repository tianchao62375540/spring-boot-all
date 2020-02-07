package com.tc.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: tianchao
 * @Date: 2020/2/1 15:41
 * @Description:
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            final int j = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(j);
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("时间："+(System.currentTimeMillis()-start));
    }
}
