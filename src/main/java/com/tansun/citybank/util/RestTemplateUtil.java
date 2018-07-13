package com.tansun.citybank.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

@Component
public class RestTemplateUtil {

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * ����url,����õ�mapͨ��restTemplate������post����
	 * 
	 * @param url
	 * @param map
	 * @return
	 */
	public JSONObject restTemplatePost(String url, Map<String, Object> map) {
		JSONObject jsonObject = new JSONObject(map);
		HttpHeaders httpHeaders = new HttpHeaders();
		MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");
		httpHeaders.setContentType(mediaType);
		httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> httpEntity = new HttpEntity<String>(jsonObject.toString(), httpHeaders);
		return restTemplate.postForObject(url, httpEntity, JSONObject.class);
	}

	/**
	 * ����url,�������objectת����mapͨ��restTemplate������post����
	 * 
	 * @param url
	 * @param object
	 * @return
	 */
	public JSONObject restTemplatePost(String url, Class<?> object) {
		Map<String, Object> map = ConvertUtil.beanToMap(object);
		JSONObject jsonObject = new JSONObject(map);
		HttpHeaders httpHeaders = new HttpHeaders();
		MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");
		httpHeaders.setContentType(mediaType);
		httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> httpEntity = new HttpEntity<String>(jsonObject.toString(), httpHeaders);
		return restTemplate.postForObject(url, httpEntity, JSONObject.class);
	}

	/**
	 * �޲�����get����
	 * 
	 * @param url
	 * @return
	 */
	public JSONObject restTemplateGet(String url) {
		return restTemplate.getForObject(url, JSONObject.class);
	}

	/**
	 * map������get����
	 * @param url
	 * @param map
	 * @return
	 */
	public JSONObject restTemplateGet(String url, Map<String, Object> map) {
		return restTemplate.getForObject(url, JSONObject.class, map);
	}

}
