package com.lofty.springboot.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lofty.springboot.service.IRedisService;

@RestController
@RequestMapping("/redis")
public class RedisController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	
	@Autowired
	private IRedisService redisService;

	@RequestMapping("/set")
	public String set(String key,String value) {
		redisService.setValue(key, value);
		logger.info("key=" + key + "\t value=" +value);
		return "success";
	}
	
	@RequestMapping("/get")
	public String get(String key) {
		System.out.println(key);
		String value = redisService.getValue(key);
		return value;
	}
}
