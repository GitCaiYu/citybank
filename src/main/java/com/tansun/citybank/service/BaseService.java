package com.tansun.citybank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.tansun.citybank.util.HttpUtil;

@Service
public class BaseService {

	@Autowired
	protected RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	protected HttpUtil httpUtil;

}
