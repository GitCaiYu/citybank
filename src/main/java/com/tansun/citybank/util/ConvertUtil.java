package com.tansun.citybank.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 类型转换工具类
 *
 */
public class ConvertUtil {

	/**
	 * @描述: beanToMap
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			Class<? extends Object> type = bean.getClass();
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						returnMap.put(propertyName, result);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}

	/**
	 * 解决汉字乱码问题
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String decode(String str) {
		try {
			if (ConvertUtil.isEmpty(str)) {
				return "";
			} else {
				return new String(str.getBytes("ISO-8859-1"), "UTF-8");
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str != null && !str.equals("")) {
			return false;
		}
		return true;
	}
}
