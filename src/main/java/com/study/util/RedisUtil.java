package com.study.util;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaohz
 * @Date 2020-08-22
 * @Discribtion 描述
 */
@Component
public class RedisUtil {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 指定库操作
     *
     * @param key     键
     * @param value   值
     * @param time    时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @param dbIndex 要存的库位置
     * @return true成功 false 失败
     */
    public boolean setValueToDataBase(String key, Object value, long time,TimeUnit timeUnit, Integer dbIndex) {
        try {
            LettuceConnectionFactory redisConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
            if (dbIndex > 0) {
                redisConnectionFactory.setDatabase(dbIndex);
                redisTemplate.setConnectionFactory(redisConnectionFactory);
                redisConnectionFactory.resetConnection();
                redisConnectionFactory.afterPropertiesSet();
            }
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, timeUnit);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            if(dbIndex>0){
                redisConnectionFactory.setDatabase(0);
                redisTemplate.setConnectionFactory(redisConnectionFactory);
                redisConnectionFactory.resetConnection();
                redisConnectionFactory.afterPropertiesSet();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * desc:   获取指定库中的指定key的value
     * param:
     * @dbIndex   数据库
     * author: CDN
     * date: 2020/5/2
     */
    public Object getValueFromRedisDatabase(String key, Integer dbIndex) {
        LettuceConnectionFactory redisConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        redisConnectionFactory.setDatabase(dbIndex);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisConnectionFactory.resetConnection();
        redisConnectionFactory.afterPropertiesSet();
        Object o = redisTemplate.opsForValue().get(key);
        if(dbIndex>0){
            redisConnectionFactory.setDatabase(0);
            redisTemplate.setConnectionFactory(redisConnectionFactory);
            redisConnectionFactory.resetConnection();
            redisConnectionFactory.afterPropertiesSet();
        }
        return o;
    }


 /*   @Bean
    RedisMessageListenerContainer container(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisTemplate.getConnectionFactory());
        //监听指定key的失效事件
        container.addMessageListener(new RedisKeyExpireListener(container), new PatternTopic("__keyspace@10__:TASKKEY_* expire"));
        return container;
    }*/


}
