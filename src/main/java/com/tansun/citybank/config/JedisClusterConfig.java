package com.tansun.citybank.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
public class JedisClusterConfig {
	
	@Value("${common.jedis.config.nodes}")
	private String nodes;

	public JedisCluster getJedisCluster() {
		String[] serverArray = nodes.split(",");
		Set<HostAndPort> nodes = new HashSet<>(0);
		for (String ipPort : serverArray) {
			String[] ipPortPair = ipPort.split(":");
			nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
		}
		return new JedisCluster(nodes);
	}

}
