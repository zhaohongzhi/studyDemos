package com.study.config;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author zhaohz
 * @Date 2020-08-22
 * @Discribtion 描述
 */
@Component
public class RedisExpiredListener implements MessageListener {

    /**
     * 监听指定数据库以及指定规则
     * @param message
     * @param bytes
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("==RedisExpiredListener===channel==="+new String(message.getChannel()));
        System.out.println("===RedisExpiredListener==body==="+new String (message.getBody()));
    }
}
