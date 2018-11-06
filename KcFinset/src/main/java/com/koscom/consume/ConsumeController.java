package com.koscom.consume;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koscom.consume.model.ConsumeDetailGoalInfoVO;
import com.koscom.consume.model.ConsumeForm;
import com.koscom.consume.model.ConsumeGoalInfoVO;
import com.koscom.consume.model.ConsumeVO;
import com.koscom.consume.model.PaymentForm;
import com.koscom.consume.model.PersonSetInfoForm;
import com.koscom.consume.model.PersonSetInfoVO;
import com.koscom.consume.service.ConsumeManager;
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
//    	String ym = DateUtil.getCurrentYMD().substring(0, 6);
    	
    	//DateUtil에서 yyyymmdd를 요구
//    	String dt_from = DateUtil.getFirstDateOfMonth(ym+"01");
//    	String dt_to = DateUtil.getLastDateOfMonth(ym+"01");
    	
    	ConsumeForm consumeForm = new ConsumeForm();
    	consumeForm.setNo_person(no_person);
//    	consumeForm.setDt_from(dt_from);
//    	consumeForm.setDt_to(dt_to);
    	consumeForm.setType_in_out("01");	//수입
//    	model.addAttribute("income",consumeManager.getConsumeInfoAmt(consumeForm));
    	
    	consumeForm.setType_in_out("02");	//지출
//    	model.addAttribute("consume",consumeManager.getConsumeInfoAmt(consumeForm));
    	
    	model.addAttribute("listConsumeInfo",consumeManager.listConsumeInfo(consumeForm));
    	
    	model.addAttribute("listPersonIncomeClassInfo",consumeManager.listPersonIncomeClassInfo(no_person));
    	
    	model.addAttribute("listPersonConsumeClassInfo",consumeManager.listPersonConsumeClassInfo(no_person));
    	
    	return "/consume/frameConsumeList";
    }
    
    /**
     * VUE
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/listConsumeSharePersonInfo.json")
    public String listConsumeSharePersonInfo(Model model, HttpSession session) {
    	logger.debug("listConsumeSharePersonInfo");
    	String no_person = (String) session.getAttribute("no_person");
    	model.addAttribute("listConsumeSharePersonInfo",consumeManager.listConsumeSharePersonInfo(no_person));
    	return "jsonView";
    }
    
    /**
     * VUE
     * @param model
     * @param ym
     * @param session
     * @return
     * @throws FinsetException
     */
    @RequestMapping("/listConsumeInfo.json")
    public String listConsumeInfo(Model model, String ym, String type_in_out, @RequestParam(value="no_person_list[]") List<String> no_person_list,HttpSession session, HttpServletRequest request) throws FinsetException {
    	logger.debug("listConsumeInfo");
    	String no_person = (String) session.getAttribute("no_person");
    	
    	ConsumeForm consumeForm = new ConsumeForm();
    	consumeForm.setNo_person(no_person);
    	consumeForm.setNo_person_list(no_person_list);
    	consumeForm.setYm_trd(ym);
    	if(type_in_out.equals("00")) {
    		consumeForm.setType_in_out(null);
    	} else {
    		consumeForm.setType_in_out(type_in_out);
    	}
    	
    	Map<String, String> summaryMap = consumeManager.listConsumeInfoAmt(consumeForm);
    	Iterator<String> iter = summaryMap.keySet().iterator();
    	while(iter.hasNext()) {
    		String key = iter.next();
    		model.addAttribute(key, summaryMap.get(key));
    	}
    	model.addAttribute("isScrap", consumeManager.chkScrapCard(no_person));
    	model.addAttribute("listConsumeInfo", consumeManager.listConsumeInfo(consumeForm));
    	
    	return "jsonView";
    }
    
    @RequestMapping("/listPersonTransDetail.json")
    public String listTransDetail(Model model, String ym, HttpSession session) throws FinsetException {
    	logger.debug("listTransDetail");
    	String no_person = (String) session.getAttribute("no_person");
    	
    	//DateUtil에서 yyyymmdd를 요구
//    	String dt_from = DateUtil.getFirstDateOfMonth(ym+"01");
//    	String dt_to = DateUtil.getLastDateOfMonth(ym+"01");
    	
    	ConsumeForm consumeForm = new ConsumeForm();
    	consumeForm.setNo_person(no_person);
//    	consumeForm.setDt_from(dt_from);
//    	consumeForm.setDt_to(dt_to);
    	
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
    
    /**
     * VUE
     * @param session
     * @param model
     * @param no_person_list
     * @param charge_yyyymm
     * @return
     */
    @RequestMapping("/listPayment.json")
    public String getPayment(HttpSession session, Model model, @RequestParam(value="no_person_list[]") List<String> no_person_list, String charge_yyyymm) {
    	logger.debug("listPayment");
    	String no_person = (String) session.getAttribute("no_person");
    	PaymentForm paymentForm = new PaymentForm();
    	paymentForm.setNo_person_list(no_person_list);
    	paymentForm.setCharge_yyyymm(charge_yyyymm);
    	model.addAttribute("isScrap", consumeManager.chkScrapCard(no_person));
    	model.addAttribute("paymentSummary",consumeManager.getPaymentSummary(paymentForm));
    	model.addAttribute("paymentList",consumeManager.listPayment(paymentForm));
    	return "jsonView";
    }
    
    /**
     * VUE
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/getPersonSetInfo.json")
    public String getPersonSetInfo(HttpSession session, Model model) {
    	logger.debug("getPersonSetInfo");
    	String no_person = (String) session.getAttribute("no_person");
    	model.addAttribute("personInfo", consumeManager.getPersonSetInfo(no_person));
    	return "jsonView";
    }
    
    /**
     * VUE
     * @param session
     * @param model
     * @param personSetInfoForm
     * @return
     */
    @RequestMapping("/modifyYn_installment.json")
    public String modifyYn_installment(HttpSession session, Model model, PersonSetInfoForm personSetInfoForm) {
    	logger.debug("modifyYn_installment");
    	String no_person = (String) session.getAttribute("no_person");
    	personSetInfoForm.setNo_person(no_person);
    	consumeManager.modifyYn_installment(personSetInfoForm);
    	return "jsonView";
    }
    
    /**
     * VUE
     * @param session
     * @param model
     * @param personSetInfoForm
     * @return
     */
    @RequestMapping("/modifyDt_basic.json")
    public String modifyDt_basic(HttpSession session, Model model, PersonSetInfoForm personSetInfoForm) {
    	logger.debug("modifyDt_basic");
    	String no_person = (String) session.getAttribute("no_person");
    	personSetInfoForm.setNo_person(no_person);
    	consumeManager.modifyDt_basic(personSetInfoForm);
    	return "jsonView";
    }
    
    /**
     * VUE
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/listDetailGoal.json")
    public String listDetailGoal(HttpSession session, Model model, String cd_set) {
    	logger.debug("listDetailGoal");
    	String no_person = (String) session.getAttribute("no_person");
    	model.addAttribute("listDetailGoal", consumeManager.listDetailGoal(no_person, cd_set));
    	return "jsonView";
    }
    /**
     * VUE
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/listPrevMonthConsume.json")
    public String getPrevMonthConsume(HttpSession session, Model model, String cd_set) {
    	logger.debug("listPrevMonthConsume");
    	String no_person = (String) session.getAttribute("no_person");
    	model.addAttribute("listPrevMonthConsume", consumeManager.listPrevMonthConsume(no_person, cd_set));
    	return "jsonView";
    }
    
    /**
     * VUE
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/listAverageConsume.json")
    public String listAverageConsume(HttpSession session, Model model, String cd_set) {
    	logger.debug("listAverageConsume");
    	String no_person = (String) session.getAttribute("no_person");
    	model.addAttribute("listAverageConsume", consumeManager.listAverageConsume(no_person, cd_set));
    	return "jsonView";
    }
    
    /**
     * VUE
     * @param session
     * @param model
     * @param consumeDetailGoalInfoVO
     * @return
     */
    @RequestMapping("/createGoal.json")
    public String createGoal(HttpSession session, Model model, ConsumeDetailGoalInfoVO consumeDetailGoalInfoVO, String cd_set) {
    	logger.debug("createGoal");
    	String no_person = (String) session.getAttribute("no_person");
    	consumeDetailGoalInfoVO.setNo_person(no_person);
    	consumeManager.createDetailGoal(consumeDetailGoalInfoVO, cd_set);
    	return "jsonView";
    }
    
    /**
     * VUE 
     * @param session
     * @param model
     * @param consumeForm
     * @return
     */
    @RequestMapping("/getConsumeInfo.json")
    public String getConsumeInfo(HttpSession session, Model model, ConsumeForm consumeForm) {
    	logger.debug("getConsumeInfo");
    	model.addAttribute("consumeVO", consumeManager.getConsumeInfo(consumeForm));
    	return "jsonView";
    }
    
    /**
     * VUE
     * @param model
     * @param no_person
     * @return
     */
    @RequestMapping("/listPersonConsumeClassInfo.json")
    public String listPersonConsumeClassInfo(HttpSession session, Model model, String no_person) {
    	logger.debug("listPersonConsumeClassInfo");
    	if(no_person == null) {
    		no_person = (String) session.getAttribute("no_person");
    	} else if (no_person.equals("")) {
    		no_person = (String) session.getAttribute("no_person");
    	}
    	
    	model.addAttribute("listPersonConsumeClassInfo", consumeManager.listPersonConsumeClassInfo(no_person));
    	return "jsonView";
    }
}
