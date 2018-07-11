package com.tansun.citybank.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HttpUtil {
	/**
	 * Post��ʽ �õ�JSONObject
	 *
	 * @param paramsHashMap post����
	 * @param url
	 * @param encoding      ����utf-8
	 * @return
	 */
	public JSONObject getJSONObjectByPost(Map<String, String> paramsHashMap, String url, String encoding) {
		// ����httpClient����
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject result = null;
		List<NameValuePair> nameValuePairArrayList = new ArrayList<NameValuePair>();
		// ���������Ĳ�����ӵ�List<NameValuePair>��
		if (paramsHashMap != null && !paramsHashMap.isEmpty()) {
			// ����map
			for (Map.Entry<String, String> entry : paramsHashMap.entrySet()) {
				nameValuePairArrayList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		UrlEncodedFormEntity entity = null;
		try {
			// ����List<NameValuePair>����Post�����ʵ������
			// UrlEncodedFormEntity ���������ݱ���ɺ��ʵ�����
			entity = new UrlEncodedFormEntity(nameValuePairArrayList, encoding);
			HttpPost httpPost = new HttpPost(url);
			// ΪHttpPost����ʵ������
			httpPost.setEntity(entity);
			// HttpClient ����Post����
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// CloseableHttpResponse
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					BufferedReader reader = null;
					try {
						reader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"), 10 * 1024);
						StringBuilder strBuilder = new StringBuilder();
						String line = null;
						while ((line = reader.readLine()) != null) {
							strBuilder.append(line);
						}
						// ��fastjson��JSON������json�ַ���תΪjson����
						result = JSON.parseObject(strBuilder.toString());
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (reader != null) {
							try {
								// �ر���
								reader.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public JSONObject getJSONObjectByGet(String url) {
		JSONObject resultJsonObject = null;
		// ����httpClient����
		CloseableHttpClient httpClient = HttpClients.createDefault();
		StringBuilder urlStringBuilder = new StringBuilder(url);
		StringBuilder entityStringBuilder = new StringBuilder();
		// ����URL����һ��HttpGet����
		HttpGet httpGet = new HttpGet(urlStringBuilder.toString());
		// HttpClient ����Post����
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �õ�httpResponse��״̬��Ӧ��
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// �õ�httpResponse��ʵ������
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"), 8 * 1024);
					String line = null;
					while ((line = reader.readLine()) != null) {
						entityStringBuilder.append(line);
					}
					// ��HttpEntity�еõ���json String����תΪjson
					String json = entityStringBuilder.toString();
					resultJsonObject = JSON.parseObject(json);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (reader != null) {
						try {
							// �ر���
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return resultJsonObject;
	}
}
