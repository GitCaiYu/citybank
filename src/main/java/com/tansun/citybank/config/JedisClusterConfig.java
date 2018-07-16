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

	@Value("${spring.redis.clusterNodes}")
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

}
