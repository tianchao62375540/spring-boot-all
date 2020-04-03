package com.tc.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Auther: tianchao
 * @Date: 2020/3/26 20:54
 * @Description:
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class TaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}
