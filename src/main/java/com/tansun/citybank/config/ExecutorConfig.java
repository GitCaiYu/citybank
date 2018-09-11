package com.tansun.citybank.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig {

    @Bean
    public Executor AsyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //���ú����߳���
        executor.setCorePoolSize(5);
        //��������߳���
        executor.setMaxPoolSize(15);
        //���ö��д�С
        executor.setQueueCapacity(99999);
        //�����̳߳��е��̵߳�����ǰ׺
        executor.setThreadNamePrefix("async-service-");
        // rejection-policy����pool�Ѿ��ﵽmax size��ʱ����δ���������
        // CALLER_RUNS���������߳���ִ�����񣬶����е��������ڵ��߳���ִ��
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //ִ�г�ʼ��
        executor.initialize();
        return executor;
    }
}