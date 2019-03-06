package com.koscom.crontab;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.koscom.env.service.BusinessManager;
import com.koscom.worker.service.WorkerManager;

public class CronTest {

	private BusinessManager businessManager;
	
	private WorkerManager workerManager;
	
	public CronTest() {
		init();
	}
	
	private void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("conf/application-context.xml");
		businessManager = (BusinessManager) context.getBean("businessManager");
		workerManager = (WorkerManager) context.getBean("workerManager");
	}

	public static void main(String[] args) {
		CronTest test = new CronTest();
		
		test.service();
	}

	private void service() {

	}
}
