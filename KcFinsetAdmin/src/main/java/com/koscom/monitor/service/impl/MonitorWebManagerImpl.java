package com.koscom.monitor.service.impl;

import com.koscom.domain.PersonLoginHist;
import com.koscom.domain.PersonalViewHist;
import com.koscom.monitor.service.MonitorManager;
import com.koscom.monitor.service.MonitorWebManager;
import com.koscom.person.model.*;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;
import com.koscom.util.SpringApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

@Service("monitorWebManager")
public class MonitorWebManagerImpl implements MonitorWebManager {

	@Autowired
	private MonitorManager monitorManager;

	@Override
	public void autoAD01(String type,String pId) {
		monitorManager.autoAD01(type,pId) ;
	}
}
