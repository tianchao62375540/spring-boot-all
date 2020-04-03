package com.tc.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Auther: tianchao
 * @Date: 2020/3/27 21:30
 * @Description:
 */
@MapperScan(basePackages={"com.tc.cache.mapper"})
@SpringBootApplication
@EnableCaching
public class CachingApplication {
    public static void main(String[] args) {
        SpringApplication.run(CachingApplication.class, args);
    }
}
