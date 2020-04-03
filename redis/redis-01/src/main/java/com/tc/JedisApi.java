package com.tc;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: tianchao
 * @Date: 2020/3/24 20:38
 * @Description:
 */
public class JedisApi {
    private Jedis jedis;
    public String KEY="monkey";
    public String VALUES="2019";
    @Before
    public void setUp() {
        jedis = new Jedis("192.168.25.155", 6379);
    }
    @Test
    public void testConnection(){
        System.out.println(jedis.ping());
    }
    @Test
    public void testKey(){
        System.out.println("清空数据：" + jedis.flushDB());//清空当前数据库
        System.out.println("判断某个键是否存在：" + jedis.exists("username"));
        System.out.println("新增<'username','zzh'>的键值对：" + jedis.set("username", "zzh"));
        Set<String> keys = jedis.keys("*");
        System.out.println("系统中所有的键如下：" + keys);
        //System.out.println("删除键password:" + jedis.del("username"));
        System.out.println("查看键username所存储的值的类型：" + jedis.type("username"));
    }
    /***
     * 剩余时间 秒
     */
    @Test
    public void testTtl() {
        long ttl = jedis.ttl(KEY);
        System.out.println("倒计时时间 :" + ttl);
    }
    @Test
    public void testPttl() {
        long pttl = jedis.pttl(KEY);
        System.out.println("pttl is :" + pttl);
    }
    /***
     * Jedis操作redis的字符串
     */
    @Test
    public void testString(){
        jedis.flushDB();
        System.out.println(jedis.set("key3", "value3"));
        System.out.println("在key3后面加入值："+jedis.append("key3", "End"));
        System.out.println("key3的值："+jedis.get("key3"));
        System.out.println("增加多个键值对："+jedis.mset("key01","value01","key02","value02","key03","value03"));
        System.out.println("获取多个键值对："+jedis.mget("key01","key02","key03"));
        System.out.println("获取多个键值对："+jedis.mget("key01","key02","key03","key04"));
        System.out.println("删除多个键值对："+jedis.del(new String[]{"key01","key02"}));
        System.out.println("获取多个键值对："+jedis.mget("key01","key02","key03"));
        jedis.flushDB();
        System.out.println("===========新增键值对防止覆盖原先值==============");
        System.out.println(jedis.setnx("key1", "value1"));
        System.out.println(jedis.setnx("key2", "value2"));
        System.out.println(jedis.setnx("key2", "value2-new"));
        System.out.println(jedis.get("key1"));
        System.out.println(jedis.get("key2"));
        System.out.println("===========获取原值，更新为新值==========");//GETSET is an atomic set this value and return the old value command.
        //具有原子性
        System.out.println(jedis.getSet("key2", "key2GetSet"));
        System.out.println(jedis.get("key2"));
    }
    @Test
    public void testList(){
        jedis.flushAll();
        System.out.println("===========添加一个list===========");
        jedis.lpush("collections", "monkey", "zhangsan", "wangwu", "xiaoming", "xiaoer", "tangseng");
        List<String> collections = jedis.lrange("collections", 0, -1);
        System.out.println("collections的内容："+collections + "   ,list类型:"+collections.getClass());
        System.out.println("collections的长度："+jedis.llen("collections"));
        jedis.lpush("sortedList", "3","6","2","0","7","4");
        System.out.println("sortedList排序前："+jedis.lrange("sortedList", 0, -1));
        SortingParams sortingParams = new SortingParams();
        jedis.sort("sortedList",sortingParams);
        System.out.println(jedis.sort("sortedList"));
        System.out.println("sortedList排序后："+jedis.lrange("sortedList", 0, -1));
    }

    /***
     * 键值对方式
     */
    @Test
    public void testMset(){
        jedis.mset(new String[]{"name", "monkey", "age", "18", "qq", "111111"});
        System.out.printf("姓名：%s,年龄：%s，联系方式：%s",jedis.get("name") , jedis.get("age") ,jedis.get("qq"));
    }


    /***
     * 不能重复 set
     */
    @Test
    public void testSet() {
        jedis.flushDB();
        System.out.println("============向集合中添加元素============");
        System.out.println(jedis.sadd("eleSet", "e1", "e2", "e4", "e3", "e0", "e8", "e7", "e5"));
        System.out.println(jedis.sadd("eleSet", "e6"));
        System.out.println(jedis.sadd("eleSet", "e6"));//重复问题 1 or 0
        System.out.println("eleSet的所有元素为：" + jedis.smembers("eleSet"));
        System.out.println("=================================");
        System.out.println(jedis.sadd("eleSet1", "e1","e2","e4","e3","e0","e8","e7","e5"));
        System.out.println("============集合运算=================");
        System.out.println("eleSet和eleSet1的交集:"+jedis.sinter("eleSet","eleSet1"));
        System.out.println("eleSet和eleSet1的并集:"+jedis.sunion("eleSet","eleSet1"));
        System.out.println("eleSet和eleSet1的差集:"+jedis.sdiff("eleSet","eleSet1"));//eleSet1中有，eleSet2中没有
        System.out.println("eleSet和eleSet1的差集:"+jedis.sdiff("eleSet1","eleSet"));//eleSet2中有，eleSet1中没有
    }

    /***
     * 散列
     */
    @Test
    public void testhash(){
        jedis.flushDB();
        Map<String, String> map = new HashMap<String, String>();
        map.put("zhangsan", "a");
        map.put("canglaoshi", "b");
        map.put("monkey", "c");
        map.put("wangwu", "d");
        jedis.hmset("hash",map);
        jedis.hset("hash", "luban", "e");
        System.out.println(jedis.type("hash"));
        System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash") + " ,hash数据类型"+jedis.hgetAll("hash").getClass());//return Map<String,String>
        System.out.println("散列hash的所有键为：" + jedis.hkeys("hash"));//return Set<String>
        System.out.println("散列hash的所有值为：" + jedis.hvals("hash"));//return List<String>
    }
    @Test
    public void testHack(){
        Jedis myJedis = new Jedis("127.0.0.1", 6379);
        //myJedis.set("username", "tianchao");
        /**
         * *3
         * $3
         * SET
         * $8
         * username
         * $8
         * tianchao
         */
        System.out.println(myJedis.lpush("list", "a","b","c"));
        /**
         * *5
         * $5
         * LPUSH
         * $4
         * list
         * $1
         * a
         * $1
         * b
         * $1
         */
    }
    @Test
    public void testSocket() throws IOException {
        Socket socket = new Socket("127.0.0.1", 6379);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hehe".getBytes());
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        inputStream.read(bytes) ;
        System.out.println(new String(bytes));
    }

}
