package com.koscom.monitor;

import com.koscom.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.monitor.service.MonitorManager;
import com.koscom.util.SkipLoginCheck;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/monitor")
public class MonitorController {
//	static final Logger logger = LoggerFactory.getLogger(MonitorController.class);
	@Autowired
	MonitorManager monitorManager;
	@SkipLoginCheck
	@RequestMapping("/autoAD01.json")
	public String autoAD01(HttpServletRequest request) {
		SessionUtil sessionUtil = new SessionUtil(request);
		String loginId = sessionUtil.getUserId();
		String type = request.getParameter("type");
		type = ("1".equals(type) || "2".equals(type))? type:"1";
        monitorManager.autoAD01(type,loginId);
		return "jsonView";
	}
}