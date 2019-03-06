package com.koscom.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.worker.service.WorkerManager;

@Service("loginWebManager")
public class LoginWebManagerImpl {

	@Autowired
	WorkerManager workerManager;

	public String getPwdDB(String pwd) {
		return workerManager.getPwdDB(pwd);
	}
}
