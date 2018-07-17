package com.tansun.citybank.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

@Configuration
public class JedisClusterConfig {

	@Value("${spring.jedis.clusterNodes}")
	private String clusterNodes;

	@Value("${spring.redis.password}")
	private String password;

	public JedisCluster getJedisCluster() {
		String[] serverArray = clusterNodes.split(",");
		Set<HostAndPort> nodes = new HashSet<>(0);
		for (String ipPort : serverArray) {
			String[] ipPortPair = ipPort.split(":");
			nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
		}
		return new JedisCluster(nodes, 3000, 3000, 1, password, new GenericObjectPoolConfig());
	}

	public void poolInit() {
		Jedis jedis = new Jedis();
		JedisPool jedisPool = new JedisPool();
		List<JedisShardInfo> shards = new ArrayList<>();
		String[] serverArray = clusterNodes.split(",");
		for (String ipPort : serverArray) {
			String[] ipPortPair = ipPort.split(":");
			new JedisShardInfo(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())).setPassword(password);;
		}
		ShardedJedisPool shardedJedisPool = new ShardedJedisPool(new GenericObjectPoolConfig(), shards);
//		shardedJedisPool.
	}

}
