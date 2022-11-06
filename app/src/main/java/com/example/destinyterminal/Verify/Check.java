package com.example.destinyterminal.Verify;

import redis.clients.jedis.Jedis;

public class Check implements Runnable{

    private final String email;// 收件人邮箱
    private final String code;// 激活码

    boolean sign ;

    public Check(String email, String code) {
        this.email = email;
        this.code = code;
    }

    @Override
    public void run() {
        try {
            //与远程Redis 数据库建立连接
            Jedis jedis = new Jedis("124.221.210.85", 6379);
            jedis.auth("073600jn!");
            jedis.set("test@123.com","000000"); //用于测试的邮箱和验证码，已经输入进了redis服务器，测试完记得删除
            sign = this.code.equals(jedis.get(this.email));
            if(sign)
                jedis.del(this.email);
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getSign(){
        return sign;
    }
}
