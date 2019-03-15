package com.koscom.scrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/scrap")
public class ScrapController {

	private static final Logger logger = LoggerFactory.getLogger(ScrapController.class);
	

	@RequestMapping("/listScrapMain.crz")
	public String formScrap() {
		logger.debug("KB부동산시세");
		return "/scrap/listScrapMain";
	}
	
	@RequestMapping("/listScrapMain2.crz")
	public String formScrap2() {
		logger.debug("FSS");
		return "/scrap/listScrapMain2";
	}
}