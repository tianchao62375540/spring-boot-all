package com.tc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Auther: tianchao
 * @Date: 2020/4/7 21:13
 * @Description:
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.tc.mapper"})
public class ProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }
}
