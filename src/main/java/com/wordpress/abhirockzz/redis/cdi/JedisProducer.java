package com.wordpress.abhirockzz.redis.cdi;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import redis.clients.jedis.Jedis;

@ApplicationScoped
public class JedisProducer {

    @PostConstruct
    public void init() {
        System.out.println("JedisProducer init()");
    }

    @PreDestroy
    public void byebye() {
        System.out.println("JedisProducer destroy()");
    }

    @Dependent
    @Produces
    public Jedis get(InjectionPoint ip) {
        System.out.println("injecting Jedis into " + ip.getMember().getDeclaringClass().getSimpleName());
        //String redisHost = System.getProperty("REDIS_HOST", "192.168.99.100");
        String redisHost = System.getenv().getOrDefault("REDIS_HOST", "192.168.99.100");
        String redisPort = System.getenv().getOrDefault("REDIS_PORT", "6379");

        Jedis jedis = new Jedis(redisHost, Integer.valueOf(redisPort), 10000);
        jedis.connect();
        System.out.println("Redis Connection obtained");
        return jedis;
    }

    //close the connection when the context of the 'dependent' component does not exist
    public void destroy(@Disposes Jedis jedis) {
        jedis.close();
        System.out.println("Closed Jedis connection");
    }
}
