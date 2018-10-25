package com.lofty.springboot.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lofty.springboot.service.IRedisService;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPool;

@Service("redisService")
public class RedisServiceImpl implements IRedisService{

    private JedisCommands redis;
    
    @Autowired
    private JedisPool jedisPool;
    
    @Autowired
    private JedisCluster jedisCluster;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        if("test".equals("test")){
            this.redis = jedisPool.getResource();
        }else{
            this.redis = jedisCluster;
        }
    }

    public JedisCommands getRedis(){
        return this.redis;
    }
    
    public String getValue(String key){
        return this.redis.get(key);
    }
    
    public String setValue(String key, String value){
        return this.redis.set(key, value);
    }

    public Long incr(String key){
        return redis.incr(key);
    }
    
    public String setValue(String key, String value, int seconds){
        return this.redis.setex(key, seconds, value);
    }
    
    public Map<String, String> hgetAll(String key){
        return redis.hgetAll(key);
    }
    public String hmset(String key, Map<String, String> map){
        return redis.hmset(key, map);
    }
    
    public Long expire(String key, int seconds){
        return redis.expire(key, seconds);
    }
    
    public void del(String key) {
    		redis.del(key);
    }

	@Override
	public List<String> lrange(String key) {
		return redis.lrange(key, 0, -1);
	}

	@Override
	public Long lpush(String key, String... string) {
		return redis.lpush(key, string);
	}
}
