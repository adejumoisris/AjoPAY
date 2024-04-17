package com.AjoPay.AjoPay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableAsync
public class AjoPayApplication {


	public static void main(String[] args) {
		System.out.println("where are you");
		SpringApplication.run(AjoPayApplication.class, args);
		System.out.println("I am at home working");
	}

	// method Executor

	@Bean
	public Executor asynExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Email");
		executor.initialize();
		return executor;
	}

}
