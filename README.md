## Start with Docker Compose

- `git clone https://github.com/abhirockzz/redis-cdi-example.git`
- `mvn clean install` - creates `redis-cdi.war` in `target` dir
- `docker-compose up --build` - starts Redis and TomEE containers (you can switch to any other Java EE runtime)

## Test

- `docker-machine ip` - get the IP address of your Docker host. Let's call it `APP_HOST`
- `curl -X POST http://<APP_HOST>/redis-cdi/kv/hello -d world` - this uses the [injected `Jedis` from the pool](https://github.com/abhirockzz/redis-cdi-example/blob/master/src/main/java/com/wordpress/abhirockzz/redis/cdi/PooledJedisProducer.java) to insert a key-value pair in Redis
- `curl -X GET http://<APP_HOST>:8080/redis-cdi/kv/hello` - this uses the [simple `Jedis` connection](https://github.com/abhirockzz/redis-cdi-example/blob/master/src/main/java/com/wordpress/abhirockzz/redis/cdi/JedisProducer.java) to fetch the value from Redis. You should get `world` in response (`HTTP 200`)