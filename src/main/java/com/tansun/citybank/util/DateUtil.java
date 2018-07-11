package com.tansun.citybank.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ���ڹ�����
 */
public class DateUtil {

	/**
	 * ��SimpleDateFormatΪ�̲߳���ȫ���� Ҫ������Ϊһ����� ����Ϊ��̬������Ҫ���߳���
	 */
	private static final ThreadLocal<SimpleDateFormat> simpleDateFormat = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			// TODO Auto-generated method stub
			return new SimpleDateFormat();
		}

	};

	/**
	 * ��ȡʱ���ַ���
	 * 
	 * @return yyyyMMddhhmmss
	 */
	public static String getDateString() {
		simpleDateFormat.get().applyPattern("yyyyMMddhhmmss");
		String str = simpleDateFormat.get().format(new Date());
		return str;
	}

	/**
	 * 
	 * ������ת���ɸ�ʽΪyyyy-MM-dd hh:mm:ss�������ַ���
	 * 
	 * @return yyyy-MM-dd hh:mm:ss
	 */
	public static String formatFullDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(date);
		return strDate;
	}

	/**
	 * ��ȡǰһ��
	 * 
	 * @return
	 */
	public static String getLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); // �õ�ǰһ��
		Date date = calendar.getTime();
		simpleDateFormat.get().applyPattern("yyyy-MM-dd");
		return simpleDateFormat.get().format(date);
	}
}
