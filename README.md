# embedded-redis basic example

This is a basic example of using [embedded-redis](https://github.com/kstyrc/embedded-redis).

## What is embedded-redis?

> You can find complete description in the project repository.

embedded-redis tool that permit that a redis server is started and controlled programatically.

It is usefull when you need to use a Redis Server but you don't have one and don't want to configure a docker container to work with your project. The main usage for embedded-redis is in Integration Tests, where tests are running in a CI enviroment.

The drawnback using embedded-redis is that you need programatically to start and to close the server in your application. 

## How this tutorial approach the problem?

The problem is: how to start and close the server programatically?

We have created the [RedisClient](./src/main/java/brunogcarneiro/embeddedredis/RedisClient.java) class to encapsulate the use of Redis in our projetct. This class is responsiblle to start and to stop the server. It is an [Singleton](https://en.wikipedia.org/wiki/Singleton_pattern) that implements the [AutoCloseable](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/AutoCloseable.html) interface.

Then, when it is necessary to use the RedisClient, an instance should be obteined from the Singleton in a [try-with-resources](https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html) block. See the example bellow.

```javascript
//TestingEmbeddedRedis.java line:25
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
```
