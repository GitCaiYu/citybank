package com.tansun.citybank.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.tansun.citybank.util.HttpUtil;

@Service
public class BaseService {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private HttpUtil httpUtil;

}
