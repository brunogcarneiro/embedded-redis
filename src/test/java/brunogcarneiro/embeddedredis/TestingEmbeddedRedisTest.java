package brunogcarneiro.embeddedredis;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static brunogcarneiro.embeddedredis.TestingEmbeddedRedis.*;
import static org.junit.jupiter.api.Assertions.*;

class TestingEmbeddedRedisTest {

    private static String NULL = null;

    @Test
    public void MyOperation_ReturnNullAndValue(){
        Map<String, String> actual = TestingEmbeddedRedis.myOperation();

        assertAll(
            () -> assertEquals(NULL, actual.get(FIRST)),
            () -> assertEquals(VALUE, actual.get(SECOND))
        );
    }
}