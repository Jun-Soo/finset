package com.koscom.monitor.service;

import com.koscom.domain.PersonLoginHist;
import com.koscom.domain.PersonalViewHist;
import com.koscom.person.model.*;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

public interface MonitorWebManager {
	void autoAD01(String type,String pId);
}

