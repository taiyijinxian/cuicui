
package com.lofty.springboot.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

/**
 * 2017年12月15日 下午4:37:33
 * youzheng-api @version 2.0.1
 * @author cuiwei
 */
public interface IRedisService extends InitializingBean{
	
	public String getValue(String key);
	
	public String setValue(String key, String value);
	
	public Long incr(String key);
	
	public Long expire(String key, int seconds);
	
	public Map<String, String> hgetAll(String key);
	
	public String hmset(String key, Map<String, String> map);
	
	public void del(String key);
	
	public String setValue(String key, String value, int seconds);
	
	public List<String> lrange(String key);
	
	public Long lpush(String key, String... string);

}
