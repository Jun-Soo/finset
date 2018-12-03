package com.koscom.batch;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@SpringBootApplication
public class App extends SpringBootServletInitializer {
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);

    @PostConstruct
    public void onInit() {
        logger.info("onInit");
    }

    @PreDestroy
    public void onDestory() {
        logger.info("onDestory");
    }

    @Bean
	public static TaskExecutor taskExecutor() {
    	
		SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
		taskExecutor.setConcurrencyLimit(10);
		return taskExecutor;
	}
    
	public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}

}
