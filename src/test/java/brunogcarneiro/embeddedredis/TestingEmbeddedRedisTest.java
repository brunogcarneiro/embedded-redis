package brunogcarneiro.embeddedredis;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static brunogcarneiro.embeddedredis.TestingEmbeddedRedis.*;
import static org.junit.jupiter.api.Assertions.*;

class TestingEmbeddedRedisTest {

    private static final Integer REDIS_PORT = 6379;

    private static String NULL = null;

    @Test
    public void MyOperation_ReturnNullAndValue(){
        Optional<Map<String, String>> actual = TestingEmbeddedRedis.myOperation();

        assertAll(
            () -> assertTrue(actual.isPresent()),
            () -> assertEquals(NULL, actual.get().get(FIRST)),
            () -> assertEquals(VALUE, actual.get().get(SECOND))
        );
    }

}