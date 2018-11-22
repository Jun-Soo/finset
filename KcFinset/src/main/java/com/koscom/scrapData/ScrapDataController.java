package com.koscom.scrapData;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.scrapData.service.ScrapDataManager;
import com.koscom.util.LogUtil;

@Controller
@RequestMapping("/m/scrapData")
@PropertySource("classpath:prop/webservice.properties")
public class ScrapDataController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScrapDataController.class);
	
    @Resource
    Environment environment;
    
    @Autowired
    ScrapDataManager scrapDataManager;
    
    @RequestMapping("/saveScrapData.json")
    public String saveScrapData(
    		HttpSession session, 
    		Model model) {
    	logger.debug("saveConsumeInfo.json");
    	
    	String no_person = (String) session.getAttribute("no_person");
    	
    	try{
    		scrapDataManager.saveScrapData(no_person);
    		logger.info("cd_err,00");
    		logger.info("msg_err,데이터 저장에 성공했습니다");
    		model.addAttribute("cd_err","00");
    		model.addAttribute("msg_err","데이터 저장에 성공했습니다");
    	} catch(Exception e) {
    		LogUtil.error(logger, e);
    		model.addAttribute("cd_err","01");
    		model.addAttribute("msg_err",e.getMessage());
    	}
    	return "jsonView";
    }
}
