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
	 * ����String��������
	 * 
	 * @param key   ����
	 * @param value ��ֵ
	 * @return Status code reply
	 */
	public String set(String key, String value) {
		return jedisClusterConfig.getJedisCluster().set(key, value);
	}

	/**
	 * ��ȡString��������
	 * 
	 * @param key ����
	 * @return the value of the specified key
	 */
	public String get(String key) {
		return jedisClusterConfig.getJedisCluster().get(key);
	}

	/**
	 * ����hash��������
	 * 
	 * @param key   ����
	 * @param field ��
	 * @param value ����
	 * @return If the field already exists, and the HSET just produced an update of
	 *         the value, 0 is returned, otherwise if a new field is created 1 is
	 *         returned.
	 */
	public Long hset(String key, String field, String value) {
		return jedisClusterConfig.getJedisCluster().hset(key, field, value);
	}

	/**
	 * ��ȡhash��������
	 * 
	 * @param key   ����
	 * @param field ��
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
	 * ���ع�ϣ�� key �е�������
	 * 
	 * @param key ����
	 * @return All the fields names contained into a hash.
	 */
	public Set<String> hkeys(String key) {
		return jedisClusterConfig.getJedisCluster().hkeys(key);
	}

	/**
	 * ���ع�ϣ�� key ���������ֵ��
	 * 
	 * @param key ����
	 * @return All the fields values contained into a hash.
	 */
	public List<String> hvals(String key) {
		return jedisClusterConfig.getJedisCluster().hvals(key);
	}

	/**
	 * �ж�key�Ƿ����
	 * 
	 * @param key
	 * @return
	 */
	public boolean isExists(String key) {
		return jedisClusterConfig.getJedisCluster().exists(key == null ? "" : key);
	}
}
