package com.lofty.springboot.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

@Configuration
@PropertySource("classpath:config/redis.properties")
public class RedisConfig {

	@Value("${redis.hostName}")
	private String hostName;
	
	@Value("${redis.port}")
	private Integer port;
	
	@Value("${redis.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.maxTotal}")
    private Integer maxTotal;

    @Value("${redis.maxWaitMillis}")
    private Integer maxWaitMillis;

    @Value("${redis.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${redis.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.testWhileIdle}")
    private boolean testWhileIdle;


    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes; 

    @Value("${spring.redis.cluster.max-redirects}")
    private Integer mmaxRedirectsac;

    @Bean("redisPool")
	public JedisPool getJedisPool() {
		JedisPool jedisPool = new JedisPool(hostName, port);
		return jedisPool;
	}
 
	@Bean("redisCluster")
	public JedisCluster getJedisCluster() {
		List<String> nodesList = Arrays.asList(clusterNodes.split(","));
		HashSet<HostAndPort> nodesSet = new HashSet<>();
		if (nodesList != null && nodesList.size() > 0) {
			for (String node : nodesList) {
				if (node != null) {
					String[] hostAndPort = node.split(":");
					HostAndPort hostAndPort2 = new HostAndPort(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
					nodesSet.add(hostAndPort2);
				}
			}
		}
		return new JedisCluster(nodesSet);
	}

}
