package com.example.destinyterminal.Utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisBusyException;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class JedisConnection implements Runnable {
    private static final String Jhost = "YOU_HOST";
    private static final int port = 6379;
    private static final String passwd = "YOU_PASSWORD";
    Jedis jedis;
    public void run() {
        try {
            jedis = new Jedis(Jhost, port);
            jedis.auth(passwd);
        } catch (JedisBusyException | JedisConnectionException e) {
            e.printStackTrace();
        }
    }
    public Jedis get(){
        return jedis;
    }
}
