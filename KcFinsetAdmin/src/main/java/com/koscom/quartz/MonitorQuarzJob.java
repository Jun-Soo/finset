package com.koscom.quartz;

import com.koscom.util.LogUtil;
import com.koscom.util.SpringApplicationContext;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.koscom.monitor.service.MonitorManager;

public class MonitorQuarzJob extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(MonitorQuarzJob.class);

    @Autowired
    private MonitorManager monitorManager;
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		LogUtil.debugLn(logger,"executeInternal.executeInternal");
		if(arg0 == null){
			logger.debug("JobExecutionContext is null");
			monitorManager = (MonitorManager)SpringApplicationContext.getBean("monitorManager");
		}
        if(monitorManager  == null) {
            LogUtil.debugLn(logger,"monitorManager IS NULL : getBean ");
		    monitorManager = (MonitorManager)SpringApplicationContext.getBean("monitorManager");
        }

		if (monitorManager != null) {
            monitorManager = (MonitorManager)SpringApplicationContext.getBean("monitorManager");
			String type ="2";//한번 처리한 건은 pass
			monitorManager.autoAD01("2","SYSTEM");
		} else {
			LogUtil.debugLn(logger,"monitorManager IS NULL");
		}
	}

}