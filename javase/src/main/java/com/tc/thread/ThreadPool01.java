package com.tc.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Auther: tianchao
 * @Date: 2020/2/1 17:03
 * @Description:
 */
public class ThreadPool01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            System.out.println("execute method...");
        });
        /*Future<?> submit = executorService.submit(() -> System.out.println("submit(runnable) method..."));
        Object o = submit.get();
        System.out.println(o);*/
        executorService.shutdown();
    }
}
