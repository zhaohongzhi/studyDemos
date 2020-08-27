package com.study.config;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author zhaohz
 * @Date 2020-08-24
 * @Discribtion 描述
 */
//@Component
public class RedisKeySpaceExpiredListener extends KeyspaceEventMessageListener {
    public RedisKeySpaceExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    protected void doHandleMessage(Message message) {
        System.out.println("====RedisKeySpaceExpiredListener===body=="+new String(message.getBody()));
        System.out.println("====RedisKeySpaceExpiredListener===body=="+new String(message.getChannel()));
    }

    @Override
    protected void doRegister(RedisMessageListenerContainer container) {
        container.addMessageListener(new RedisKeySpaceExpiredListener(container), new PatternTopic("__keyspace@10__:TASKKEY_* expired"));
        super.doRegister(container);
    }
}
