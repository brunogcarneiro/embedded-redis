package brunogcarneiro.embeddedredis;

import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.util.Objects;

public class RedisClient implements AutoCloseable{

    private static Jedis jedis;
    private static final Integer REDIS_PORT = 6379;
    private static RedisServer embeddedRedisServer;

    private static RedisClient client;

    public static RedisClient getInstance() throws IOException {
        startEmbeddedRedis();
        instantiateJedis();
        instantiateClient();

        return client;
    }

    private static void instantiateClient() {
        if (Objects.isNull(client)){
            client = new RedisClient();
        }
    }

    private static void instantiateJedis() {
        if (Objects.isNull(jedis)){
            jedis = new Jedis();
        }
    }

    private static void startEmbeddedRedis() throws IOException {
        if (Objects.isNull(embeddedRedisServer)){
            embeddedRedisServer = new RedisServer(REDIS_PORT);
            embeddedRedisServer.start();
        }
    }

    @Override
    public void close() throws Exception {
        closeJedis();
        closeEmbeddedRedisServer();
    }

    private void closeEmbeddedRedisServer() {
        if (!Objects.isNull(embeddedRedisServer)){
            embeddedRedisServer.stop();
        }
    }

    private void closeJedis() {
        if (!Objects.isNull(jedis)){
            jedis.close();
        }
    }

    public long del(String key) {
        return jedis.del(key);
    }

    public String get(String key) {
        return jedis.get(key);
    }

    public String set(String key, String value) {
        return jedis.set(key, value);
    }
}
