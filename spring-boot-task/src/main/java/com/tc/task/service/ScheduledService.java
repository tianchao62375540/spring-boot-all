package com.tc.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.swing.*;

/**
 * @Auther: tianchao
 * @Date: 2020/3/26 22:26
 * @Description:
 */
@Service
public class ScheduledService {
    /**
     * triggers second(秒)minute(分), hour(小时), day of month(日), month(月)
     * and day of week(周几)
     */
    //@Scheduled(cron="0 * * * * MON-FRI")
    //@Scheduled(cron="0,1,2,3,4 * * * * MON-FRI")
    //@Scheduled(cron="0-4 * * * * MON-FRI")

    /**
     * 每天14点整合18点整,每个五分钟执行一次 【0 0/5 14,18 * * ?】
     * 每个月的周一至周六10:15执行一次 【0 15 10 ? * 1-6】
     * 每个月的最后一个周六凌晨2点执行一次 【0 0 2 ? * 6L】
     * 每个月的最后一个工作日凌晨两点执行一次 【0 0 2 LW * ?】
     * 每个月的第2个周一凌晨2点到4点期间，每个整点执行一次 【0 0 2-4 ? * 1#2】
     */
    @Scheduled(cron="0/4 * * * * MON-FRI")//每4秒启动一次
    public void hello(){
        System.out.println("hello..");
    }
}
