package brunogcarneiro.embeddedredis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TestingEmbeddedRedis {

    private static final String KEY = "myKey";
    public static final String FIRST = "first";
    public static final String SECOND = "second";
    public static final String VALUE = "value";

    private static Jedis redisClient;

    public static void main(String... args){
        System.out.println("Start");

        System.out.println(myOperation());

        System.out.println("End");
    }

    public static Map<String,String> myOperation(){
        Jedis client = getRedisClient();

        client.del(KEY);

        Map<String, String> ret = new HashMap<>();

        ret.put(FIRST, client.get(KEY));

        client.set(KEY, VALUE);

        ret.put(SECOND, client.get(KEY));

        return ret;
    }

    private static Jedis getRedisClient(){
        if (Objects.isNull(redisClient)){
            redisClient = new Jedis();
        }

        return redisClient;
    }
}
