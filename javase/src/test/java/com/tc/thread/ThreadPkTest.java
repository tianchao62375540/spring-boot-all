package com.tc.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: tianchao
 * @Date: 2020/2/1 15:33
 * @Description:
 */
public class ThreadPkTest {

    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        List<Integer> l = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread("t"+i){
                @Override
                public void run() {
                    l.add(random.nextInt());
                    System.out.println("current:"+Thread.currentThread().getName());
                }
            };
            t.start();
            t.join();
        }
        System.out.println("时间:"+(System.currentTimeMillis()-start));
    }
}
