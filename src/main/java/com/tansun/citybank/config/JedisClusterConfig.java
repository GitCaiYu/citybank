package com.tansun.citybank.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
public class JedisClusterConfig {

	@Value("${spring.jedis.clusterNodes}")
	private String clusterNodes;

	private static JedisCluster jedisCluster = null;

	public synchronized JedisCluster getJedisCluster() {
		String[] serverArray = clusterNodes.split(",");
		Set<HostAndPort> nodes = new HashSet<>(0);
		for (String ipPort : serverArray) {
			String[] ipPortPair = ipPort.split(":");
			nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
		}
		if (jedisCluster == null) {
			jedisCluster = new JedisCluster(nodes, 3000, 3000, 1, new GenericObjectPoolConfig());
		}
		return jedisCluster;
	}
}
