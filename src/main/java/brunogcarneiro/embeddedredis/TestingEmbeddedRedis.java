package brunogcarneiro.embeddedredis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TestingEmbeddedRedis {

    private static final String KEY = "myKey";
    public static final String FIRST = "first";
    public static final String SECOND = "second";
    public static final String VALUE = "value";

    public static void main(String... args) throws IOException {
        System.out.println("Start");

        Optional<Map<String, String>> ret = myOperation();

        ret.ifPresent(System.out::println);

        System.out.println("End");
    }

    public static Optional<Map<String, String>> myOperation() {

        Map<String, String> myMap = null;

        try (RedisClient redisClient = RedisClient.getInstance()){

            myMap = generateMap(redisClient);

        } catch (Exception e) {
            System.out.println("Catched error: "+e.getMessage());
            e.printStackTrace();
        }

        return Optional.ofNullable(myMap);
    }

    public static Map<String,String> generateMap(RedisClient redisClient){

        redisClient.del(KEY);

        Map<String, String> ret = new HashMap<>();

        ret.put(FIRST, redisClient.get(KEY));

        redisClient.set(KEY, VALUE);

        ret.put(SECOND, redisClient.get(KEY));

        return ret;
    }

}
