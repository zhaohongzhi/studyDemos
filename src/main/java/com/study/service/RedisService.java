package com.study.service;

import com.study.model.JsonResult;

/**
 * @author zhaohz
 * @Date 2010-08-21
 * @Discribtion 描述
 */
public interface RedisService {

    JsonResult<String> setAndGetKey(String redisKey,String redisValue);
}
