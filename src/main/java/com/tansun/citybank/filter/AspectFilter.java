package com.tansun.citybank.filter;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tansun.citybank.util.ConvertUtil;

/**
 * 接口日志切面类
 * 
 * @author Administrator
 *
 */
@Aspect
@Component
public class AspectFilter {

	private Logger logger = Logger.getLogger(getClass());

	ThreadLocal<Long> startTime = new ThreadLocal<>();

	private Object result;

	private static final Pattern pattern = Pattern.compile("com.tansun.citybank.service.*");

	@Pointcut("within(org.springframework.web.client.RestTemplate || com.tansun.citybank.util.HttpUtil))")
	public void AspectMethod() {
	}

	@Around("AspectMethod()")
	public Object handle(ProceedingJoinPoint proceedingJoinPoint) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (servletRequestAttributes != null) {
			HttpServletRequest request = servletRequestAttributes.getRequest();
			Stream.of(new Exception().getStackTrace()).forEach(stackTrace -> {
				Matcher matcher = pattern.matcher(stackTrace.getClassName());
				if (matcher.matches()) {
					startTime.set(System.currentTimeMillis());
					String exception = null;
					try {
						result = proceedingJoinPoint.proceed();
					} catch (Throwable e) {
						exception = e.getMessage();
					}
					if (exception == null) {
						logger.info("--------------------------------------------------------");
						logger.info("URL : " + request.getRequestURL().toString());
						logger.info("HTTP_METHOD : " + request.getMethod());
						logger.info("IP : " + request.getRemoteAddr());
						logger.info("CLASS_METHOD : " + stackTrace.getClassName() + "." + stackTrace.getMethodName()
								+ ":" + stackTrace.getLineNumber());
						logger.info("ARGS : " + Arrays.toString(proceedingJoinPoint.getArgs()));
						logger.info("RESPONSE : " + ConvertUtil.decode(result.toString()));
						logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
						logger.info("--------------------------------------------------------");
					} else {
						// 根据对方系统返回的500级别的处理
					}

				}
			});
		}
		return result;
	}

}
