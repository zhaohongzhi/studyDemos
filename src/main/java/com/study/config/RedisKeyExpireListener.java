package com.study.config;

import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author zhaohz
 * @Date 2020-08-21
 * @Discribtion 描述
 */
@Slf4j
//@Component
public class RedisKeyExpireListener extends KeyExpirationEventMessageListener {
    public RedisKeyExpireListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 全局监听，不区分数据库和规则
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("===RedisKeyExpireListener===onMessage==="+message.toString());
        System.out.println("===RedisKeyExpireListener===body"+new String(message.getBody())+"===channel=="+new String(message.getChannel()));
    }

}
