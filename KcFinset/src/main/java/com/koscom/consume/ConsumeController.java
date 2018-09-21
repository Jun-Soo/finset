package com.koscom.consume;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.consume.model.ConsumeForm;
import com.koscom.consume.model.ConsumeGoalInfoVO;
import com.koscom.consume.model.ConsumeVO;
import com.koscom.consume.model.PersonSetInfoVO;
import com.koscom.consume.service.ConsumeManager;
import com.koscom.util.DateUtil;
import com.koscom.util.FinsetException;

@Controller
@RequestMapping("/m/consume")
public class ConsumeController {
	
	private static final Logger logger = LoggerFactory.getLogger(ConsumeController.class);
	
    @Autowired
    ConsumeManager consumeManager;
    
    @RequestMapping("/frameConsumeList.crz")
    public String frameConsumeList(Model model, HttpSession session) throws FinsetException{
    	
    	logger.debug("frameConsumeList");
    	String no_person = (String) session.getAttribute("no_person");
    	String ym = DateUtil.getCurrentYMD().substring(0, 6);
    	
    	//DateUtil에서 yyyymmdd를 요구
    	String dt_from = DateUtil.getFirstDateOfMonth(ym+"01");
    	String dt_to = DateUtil.getLastDateOfMonth(ym+"01");
    	
    	ConsumeForm consumeForm = new ConsumeForm();
    	consumeForm.setNo_person(no_person);
    	consumeForm.setDt_from(dt_from);
    	consumeForm.setDt_to(dt_to);
    	consumeForm.setType_in_out("01");	//수입
    	model.addAttribute("income",consumeManager.getConsumeInfoAmt(consumeForm));
    	
    	consumeForm.setType_in_out("02");	//지출
    	model.addAttribute("consume",consumeManager.getConsumeInfoAmt(consumeForm));
    	
    	model.addAttribute("listConsumeInfo",consumeManager.listConsumeInfo(consumeForm));
    	
    	model.addAttribute("listPersonIncomeClassInfo",consumeManager.listPersonIncomeClassInfo(no_person));
    	
    	model.addAttribute("listPersonConsumeClassInfo",consumeManager.listPersonConsumeClassInfo(no_person));
    	
    	return "/consume/frameConsumeList";
    }
    
    @RequestMapping("/listConsumeInfo.json")
    public String listConsumeInfo(Model model,String ym, HttpSession session) throws FinsetException {
    	logger.debug("listConsumeInfo");
    	
    	String no_person = (String) session.getAttribute("no_person");
    	
    	//DateUtil에서 yyyymmdd를 요구
    	String dt_from = DateUtil.getFirstDateOfMonth(ym+"01");
    	String dt_to = DateUtil.getLastDateOfMonth(ym+"01");
    	
    	ConsumeForm consumeForm = new ConsumeForm();
    	consumeForm.setNo_person(no_person);
    	consumeForm.setDt_from(dt_from);
    	consumeForm.setDt_to(dt_to);
    	consumeForm.setType_in_out("01");	//수입
    	model.addAttribute("income",consumeManager.getConsumeInfoAmt(consumeForm));
    	
    	consumeForm.setType_in_out("02");	//지출
    	model.addAttribute("consume",consumeManager.getConsumeInfoAmt(consumeForm));
    	
    	model.addAttribute("listConsumeInfo",consumeManager.listConsumeInfo(consumeForm));
    	
    	return "jsonView";
    }
    
    @RequestMapping("/listPersonTransDetail.json")
    public String listTransDetail(Model model, String ym, HttpSession session) throws FinsetException {
    	logger.debug("listTransDetail");
    	String no_person = (String) session.getAttribute("no_person");
    	
    	//DateUtil에서 yyyymmdd를 요구
    	String dt_from = DateUtil.getFirstDateOfMonth(ym+"01");
    	String dt_to = DateUtil.getLastDateOfMonth(ym+"01");
    	
    	ConsumeForm consumeForm = new ConsumeForm();
    	consumeForm.setNo_person(no_person);
    	consumeForm.setDt_from(dt_from);
    	consumeForm.setDt_to(dt_to);
    	
    	model.addAttribute("listPersonTransDetail",consumeManager.listPersonTransDetail(consumeForm));
    	return "jsonView";
    }
    
    @RequestMapping("/frameConsumeSetting.crz")
    public String frameConsumeSetting(Model model, HttpSession session) throws FinsetException {
    	logger.debug("frameConsumeSetting");
    	String no_person = (String) session.getAttribute("no_person");
    	model.addAttribute("personSetInfoVO",consumeManager.getPersonSetInfo(no_person));
    	return "/consume/frameConsumeSetting";
    }
    
    @RequestMapping("/frameConsumeBudget.crz")
    public String frameConsumeBudget(Model model) {
    	logger.debug("frameConsumeBudget");
    	return "/consume/frameConsumeBudget";
    }
    
    @RequestMapping("/frameConsumeCategory.crz")
    public String frameConsumeCategory(Model model, HttpSession session) throws FinsetException {
    	logger.debug("frameConsumeCategory");
    	String no_person = (String) session.getAttribute("no_person");
    	model.addAttribute("list",consumeManager.listPersonConsumeClassInfo(no_person));
    	return "/consume/frameConsumeCategory";
    }
    
    @RequestMapping("/modifyPersonSetInfo.json")
    public String modifyPersonSetInfo(HttpSession session ,PersonSetInfoVO personSetInfoVO) throws FinsetException {
    	logger.debug("modifyPersonSetInfo");
    	String no_person = (String) session.getAttribute("no_person");
    	personSetInfoVO.setNo_person(no_person);
    	personSetInfoVO.setId_frt(no_person);
    	personSetInfoVO.setId_lst(no_person);
    	consumeManager.modifyPersonSetInfo(personSetInfoVO);
    	return "jsonView";
    }
    
    @RequestMapping("/modifyConsumeInfo.json")
    public String modifyConsumeInfo(Model model, HttpSession session, ConsumeVO consumeVO) throws FinsetException {
    	logger.debug("modifyConsumeInfo");
    	model.addAttribute("result","00");
    	return "jsonView";
    }
    
    @RequestMapping("/registerGoal")
    public String registerGoal(HttpSession session, Model model, ConsumeGoalInfoVO consumeGoalInfoVO) throws FinsetException {
    	logger.debug("registerGoal");
    	return "jsonView";
    }
}
