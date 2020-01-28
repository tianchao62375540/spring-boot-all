import com.sun.media.sound.SoftTuning;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.params.SetParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: tianchao
 * @Date: 2020/1/28 18:30
 * @Description:
 */
public class TestRedis {

    private Jedis jedis;

    @Before
    public void before(){
        jedis = new Jedis("120.78.129.112", 6379);
    }
    @After
    public void after(){
        jedis.close();
    }
    public static void main(String[] args) {
        Jedis jedis = new Jedis("120.78.129.112", 6379);
        System.out.println(jedis.ping());
        jedis.close();
    }
    @Test
    public void testScan(){

    }



    @Test
    public void testKey(){
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.exists("k1"));
        System.out.println(jedis.expire("k1", 60));
        System.out.println(jedis.ttl("k1"));
        System.out.println(jedis.type("k1"));
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.incr("count"));
        System.out.println(jedis.decr("count"));

        System.out.println(jedis.incrBy("count", 2));
        System.out.println(jedis.incrBy("count", 2));
        System.out.println(jedis.decrBy("count", 2));
        System.out.println(jedis.decrBy("count", 2));
    }

    @Test
    public void testString(){
        System.out.println(jedis.set("k1", "v1"));
        System.out.println(jedis.get("k1"));
        System.out.println(jedis.del("k1"));

        System.out.println(jedis.append("k1", "v1"));
        System.out.println(jedis.append("k1", "v1"));
        System.out.println(jedis.get("k1"));
        System.out.println(jedis.strlen("k1"));

        System.out.println(jedis.mset("k1","v1","k2","v2","k3","v3"));
        System.out.println(jedis.mget("k1", "k2", "k3"));
        //key已经存在就不能设值，key不存在才能设值
        Long setnx = jedis.setnx("k4", "kkk1");
        System.out.println(setnx);
        System.out.println(jedis.get("k4"));
        //设置超时时间
        System.out.println(jedis.setex("k9", 10, "v9"));
        System.out.println(jedis.ttl("k9"));
        //set lock:codehole true ex 5 nx OK ... do something critical .
        /*System.out.println(jedis.set("k10", "true", "NX", "EX", 10));
        System.out.println(jedis.get("k10"));
        System.out.println(jedis.ttl("k10"));*/
        System.out.println("=============================================");
        SetParams setParams = new SetParams();
        setParams.ex(10);
        setParams.nx();
        System.out.println(jedis.set("k10", "v10", setParams));
        System.out.println(jedis.get("k10"));
    }

    @Test
    public void testList(){
        jedis.del("lpush");
        jedis.del("rpush");
        System.out.println(jedis.lpush("lpush", "v1", "v2", "v3", "v4"));
        System.out.println(jedis.rpush("rpush", "v1", "v2", "v3", "v4"));
        System.out.println(jedis.lrange("lpush", 0, -1));
        System.out.println(jedis.lrange("rpush", 0, -1));

        System.out.println(jedis.rpop("lpush"));
        System.out.println(jedis.rpop("rpush"));

        System.out.println(jedis.lpop("lpush"));
        System.out.println(jedis.lpop("rpush"));

        System.out.println(jedis.lset("lpush", 0, "a"));
        System.out.println(jedis.lrange("lpush", 0, -1));
    }

    @Test
    public void testSet(){
        System.out.println(jedis.smembers("set3"));
        System.out.println(jedis.del("set1", "set2"));
        System.out.println(jedis.sadd("set1", "v1", "v2", "v3", "v4", "v5", "v6", "v7"));
        System.out.println(jedis.smembers("set1"));

        System.out.println(jedis.sadd("set2", "v1","v2","v3","v4","v11","v12"));
        //差集
        System.out.println("set1差集:"+jedis.sdiff("set1","set2"));
        //差集
        System.out.println("set2差集:"+jedis.sdiff("set2","set1"));
        //交际
        System.out.println(jedis.sinter("set1","set2"));
        //并集
        System.out.println(jedis.sunion("set1","set2"));
    }

    @Test
    public void testHash(){
        Map<String,String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        System.out.println(jedis.hset("hash", map));
        System.out.println(jedis.hkeys("hash"));
        System.out.println(jedis.hvals("hash"));
        System.out.println(jedis.hgetAll("hash"));
    }

    @Test
    public void testZset(){
        Map<String,Double> map = new HashMap<>();
        map.put("k1", 10d);
        map.put("k4", 40d);
        map.put("k3", 30d);
        map.put("k2", 20d);
        System.out.println(jedis.zadd("zSet", map));
        System.out.println(jedis.zrange("zSet", 0, -1));
        Set<Tuple> zSet = jedis.zrangeWithScores("zSet", 0, -1);
        for (Tuple tuple : zSet) {
            System.out.println(tuple.getElement());
        }

        System.out.println(jedis.zrangeWithScores("zSet", 0, -1));

    }
    @Test
    public void testMethodScan(){
        this.methodScan("0");
    }

    public void methodScan(String cursor){
        //初始游标值为0
        String key = "*";
        ScanParams scanParams = new ScanParams();
        scanParams.match(key);
        scanParams.count(3);
        ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
        //返回0说明便利完成
        cursor = scanResult.getCursor();
        System.out.println("游标返回为:"+cursor);
        List<String> result = scanResult.getResult();
        System.out.print("查询出key:");
        for (String s : result) {
            System.out.print( s + "\t");
        }
        System.out.println();
        System.out.println("================================");
        if (!"0".equals(cursor) ) {
            methodScan(cursor);
        }
    }
}
