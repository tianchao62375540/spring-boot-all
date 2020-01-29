import redis.clients.jedis.Jedis;

/**
 * @Auther: tianchao
 * @Date: 2020/1/29 10:05
 * @Description:
 */
public class DataInit {
    private static Jedis jedis = new Jedis("120.78.129.112", 6379);

    public static void main(String[] args) {
        for (int i = 0; i < 5000000; i++) {
            jedis.set("k"+i, "v"+i);
        }
        System.out.println("finish.......................");
    }
}
