## To run

- Start Redis on `localhost` and port 6379, else use `REDIS_HOST` and `REDIS_PORT` environment variables to point to another Redis instance
- `git clone` the project and execute `mvn clean install`
- Deploy `redis-cdi.war` in `target` directory to any of the [Java EE 7 containers](http://www.oracle.com/technetwork/java/javaee/overview/compatibility-jsp-136984.html)

## To check

- execute a HTTP `POST` to `http://localhost:8080/redis-cdi-example/kv/test-key` - this uses the injected `Jedis` from the pool. The key-value pair is now in Redis
- execute a HTTP `GET` to `http://localhost:8080/redis-cdi-example/kv/test-key` - this uses the simple `Jedis` connection. You should see the value you stored in the previous step

