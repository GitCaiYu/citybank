package com.tansun.citybank.util;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tansun.citybank.config.JedisClusterConfig;

@Component
public class JedisClusterUtil {

	@Autowired
	private JedisClusterConfig jedisClusterConfig;

	/**
	 * 设置String数据类型
	 * 
	 * @param key   键名
	 * @param value 键值
	 * @return Status code reply
	 */
	public String set(String key, String value) {
		return jedisClusterConfig.getJedisCluster().set(key, value);
	}

	/**
	 * 获取String数据类型
	 * 
	 * @param key 键名
	 * @return the value of the specified key
	 */
	public String get(String key) {
		return jedisClusterConfig.getJedisCluster().get(key);
	}

	/**
	 * 设置hash数据类型
	 * 
	 * @param key   键名
	 * @param field 域
	 * @param value 键名
	 * @return If the field already exists, and the HSET just produced an update of
	 *         the value, 0 is returned, otherwise if a new field is created 1 is
	 *         returned.
	 */
	public Long hset(String key, String field, String value) {
		return jedisClusterConfig.getJedisCluster().hset(key, field, value);
	}

	/**
	 * 获取hash数据类型
	 * 
	 * @param key   键名
	 * @param field 域
	 * @return If key holds a hash, retrieve the value associated to the specified
	 *         field.
	 *         <p>
	 *         If the field is not found or the key does not exist, a special 'nil'
	 *         value is returned.
	 *         </p>
	 */
	public String hget(String key, String field) {
		return jedisClusterConfig.getJedisCluster().hget(key, field);
	}

	/**
	 * 返回哈希表 key 中的所有域
	 * 
	 * @param key 键名
	 * @return All the fields names contained into a hash.
	 */
	public Set<String> hkeys(String key) {
		return jedisClusterConfig.getJedisCluster().hkeys(key);
	}

	/**
	 * 返回哈希表 key 中所有域的值。
	 * 
	 * @param key 键名
	 * @return All the fields values contained into a hash.
	 */
	public List<String> hvals(String key) {
		return jedisClusterConfig.getJedisCluster().hvals(key);
	}

	/**
	 * 判断key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean isExists(String key) {
		return jedisClusterConfig.getJedisCluster().exists(key == null ? "" : key);
	}
}
