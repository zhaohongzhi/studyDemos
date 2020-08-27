package com.study.config;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author zhaohz
 * @Date 2020-08-22
 * @Discribtion 描述
 */
public class RedisExpiredListener implements MessageListener {

    /**
     * 监听指定数据库以及指定规则
     * @param message
     * @param bytes
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();// 建议使用: valueSerializer
        byte[] channel = message.getChannel();
        System.out.print("=====ExpiredListener=====onMessage >> " );
        System.out.println(String.format("channel: %s, body: %s, bytes: %s"
                ,new String(channel), new String(body), new String(bytes)));
    }


}
