package com.tansun.citybank.util;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {

	/**
	 * ����UUID��Ҫ���������ݿ�����
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * ����������
	 * ��ʽΪ prefix + yyyyMMddhhmmss + random(3)
	 * 
	 * @return
	 */
	public static String getRandomId(String prefix,int num) {
		String timeMiddle = DateUtil.getDateString();
		String randomId = prefix + timeMiddle + getRandomNumber(num);
		return randomId;
	}

	/**
	 * ���������
	 * 
	 * @return
	 */
	public static String getRandomNumber(int num) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++) {
			sb.append((char) ('0' + random.nextInt(10)));
		}
		return sb.toString();
	}
}
