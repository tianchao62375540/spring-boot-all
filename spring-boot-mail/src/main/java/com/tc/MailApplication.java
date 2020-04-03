package com.tc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 授权码 JULGLOBEBSZAZAZQ
 * @Auther: tianchao
 * @Date: 2020/3/17 19:47
 * @Description:
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.tc.mapper")
public class MailApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }
}
