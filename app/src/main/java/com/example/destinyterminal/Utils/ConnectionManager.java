package com.example.destinyterminal.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import android.util.Base64;
import android.util.JsonWriter;


import com.alibaba.fastjson2.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.jivesoftware.smack.AbstractXMPPConnection;


import java.util.Arrays;

import redis.clients.jedis.Jedis;

import redis.clients.jedis.exceptions.JedisBusyException;
import redis.clients.jedis.exceptions.JedisDataException;

public class ConnectionManager {


    public void set(AbstractXMPPConnection connection) {

        Jedis jedis = new Jedis();
        try {
            JedisConnection jedisConnection = new JedisConnection();
            Thread thread = new Thread(jedisConnection);
            thread.start();
            thread.join();
            jedis = jedisConnection.get();
            String Logging = JSON.toJSONString(connection);
            byte[] bytes = Logging.getBytes();
            String base64bytes = Base64.encodeToString(bytes,Base64.DEFAULT);
            jedis.set("Logging",base64bytes);
        }catch (JsonIOException |JedisBusyException | InterruptedException e){
            e.printStackTrace();
        } finally{
            jedis.close();
        }

    }

    public AbstractXMPPConnection get() {
        Jedis jedis = new Jedis();
        try {

            JedisConnection jedisConnection = new JedisConnection();
            Thread thread = new Thread(jedisConnection);
            thread.start();
            thread.join();
            jedis = jedisConnection.get();
            String Logging = jedis.get("Logging");
            if(Logging == null){
                return null;
            }
            byte[] bytes = Base64.decode(Logging,Base64.DEFAULT);
            return JSON.parseObject(Arrays.toString(bytes),AbstractXMPPConnection.class);
        } catch (JsonIOException | JedisDataException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    void remove(){

    }
}
