package com.example.shardingjdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name RedisService
 * @Date 2019/02/21 16:11
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public Long getUUID(){

        return redisTemplate.opsForValue().increment("uuid-key");
    }
}
