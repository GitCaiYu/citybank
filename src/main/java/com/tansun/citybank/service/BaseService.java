package com.tansun.citybank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tansun.citybank.util.HttpUtil;
import com.tansun.citybank.util.RestTemplateUtil;

@Service
public class BaseService {

	@Autowired
	protected HttpUtil httpUtil;

	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
	protected RestTemplateUtil restTemplateUtil;

}
