package com.tc;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;

/**
 * @Auther: tianchao
 * @Date: 2020/3/24 21:55
 * @Description:
 */
public class RedisMonitor {
    static class MonitorTask implements Runnable{

        private Jedis jedis;

        public MonitorTask(Jedis jedis){
            this.jedis = jedis;
        }

        @Override
        public void run() {
            jedis.monitor(new JedisMonitor() {
                @Override
                public void onCommand(String command) {
                    System.out.println(command);
                }
            });
        }
    }

    public static void main(String[] args) {
        MonitorTask monitorTask = new MonitorTask(new Jedis("192.168.25.155", 6379));
        Thread thread = new Thread(monitorTask);
        thread.start();
    }
}
