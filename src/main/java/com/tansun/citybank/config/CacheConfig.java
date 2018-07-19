package com.tansun.citybank.config;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tansun.citybank.util.RandomUtil;

@Configuration
public class CacheConfig {

	@Bean(name = { "keyGenerator" })
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {

			@Override
			public Object generate(Object target, Method method, Object... params) {
				return method.getClass() + ":" + method.getName() + ":" + RandomUtil.getRandomNumber(3);
			}
		};
	}

}
