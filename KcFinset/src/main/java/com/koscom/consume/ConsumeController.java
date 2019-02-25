package com.koscom.consume;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.koscom.consume.model.ConsumeVO;
import com.koscom.consume.model.PaymentForm;
import com.koscom.consume.model.PersonConsumeClassVO;
import com.koscom.consume.model.PersonSetInfoForm;
import com.koscom.consume.model.PersonSetInfoVO;
import com.koscom.consume.service.ConsumeManager;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;

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
     * 공유된 사용자 조회
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
     * 소비지출 내역 리스트, 스크래핑 여부, 예산 데이터 조회 
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
    	model.addAttribute("consumeGoal", consumeManager.getConsumeGoalData(no_person, ym));
    	model.addAttribute("isScrap", consumeManager.chkScrapCard(no_person));
    	model.addAttribute("listConsumeInfo", consumeManager.listConsumeInfo(consumeForm));
    	
    	return "jsonView";
    }
    
    /**
     * VUE
     * 기존 등록된 입출금내역 시퀀스 리스트 조회
     * @param session
     * @param model
     * @param consumeForm
     * @return
     * @throws FinsetException
     */
    @RequestMapping("/listRegisteredSeqTran.json")
	public String listRegisteredSeqTran(HttpSession session, Model model, ConsumeForm consumeForm) throws FinsetException {
		logger.debug("listRegisteredSeqTran");
		String no_person = (String) session.getAttribute("no_person");
		consumeForm.setNo_person(no_person);
		model.addAttribute("listRegisteredSeqTran", consumeManager.listRegisteredSeqTran(consumeForm));
		return "jsonView";
	}
    
    /**
     * VUE
     * 계좌 입출금내역리스트 조회
     * @param model
     * @param ym
     * @param session
     * @param consumeForm
     * @return
     * @throws FinsetException
     */
    @RequestMapping("/listPersonTransDetail.json")
    public String listTransDetail(HttpSession session, Model model, ConsumeForm consumeForm) throws FinsetException {
    	logger.debug("listTransDetail");
    	String no_person = (String) session.getAttribute("no_person");
    	consumeForm.setNo_person(no_person);
    	model.addAttribute("listPersonTransDetail",consumeManager.listPersonTransDetail(consumeForm));
    	return "jsonView";
    }
    
    
    /**
     * VUE
     * 계좌 입출금내역 조회
     * @param model
     * @param ym
     * @param session
     * @param consumeForm
     * @return
     * @throws FinsetException
     */
    @RequestMapping("/getPersonTransDetail.json")
    public String getPersonTransDetail(HttpSession session, Model model, ConsumeForm consumeForm) throws FinsetException {
    	logger.debug("getPersonTransDetail");
    	String no_person = (String) session.getAttribute("no_person");
    	consumeForm.setNo_person(no_person);
    	model.addAttribute("transVO",consumeManager.getPersonTransDetail(consumeForm));
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
    
    /**
     * VUE
     * 사용자 세팅 변경
     * @param session
     * @param personSetInfoVO
     * @return
     * @throws FinsetException
     */
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

    @RequestMapping("/chkExistPayment.json")
    public String chkExistPayment(HttpSession session, Model model) {
    	logger.debug("chkExistPayment");
    	String no_person = (String) session.getAttribute("no_person");
    	model.addAttribute("charge_yyyymm",consumeManager.chkExistPayment(no_person));
    	return "jsonView";
    }
    
    /**
     * VUE
     * 청구내역 리스트 조회
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
    	paymentForm.setNo_person(no_person);
    	paymentForm.setNo_person_list(no_person_list);
    	paymentForm.setCharge_yyyymm(charge_yyyymm);
    	model.addAttribute("isScrap", consumeManager.chkScrapCard(no_person));
    	model.addAttribute("paymentList",consumeManager.listPayment(paymentForm));
    	return "jsonView";
    }
    
    /**
     * VUE
     * 청구 상세 내역 리스트 조회
     * @param session
     * @param model
     * @param paymentForm
     * @return
     */
    @RequestMapping("/listPaymentDetail.json")
    public String listPaymentDetail(HttpSession session, Model model, PaymentForm paymentForm) {
    	logger.debug("listPaymentDetail");
    	String no_person = (String) session.getAttribute("no_person");
    	String cdResult = SessionUtil.chkNoPerson(no_person, paymentForm.getNo_person(), "03");
    	model.addAttribute("cdResult", cdResult);
		if("00".equals(cdResult)){
			model.addAttribute("listPaymentDetail", consumeManager.listPaymentDetail(paymentForm));
		}
    	return "jsonView";
    }
    
    /**
     * VUE
     * 사용자 세팅 조회
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
     * 할부 적용 여부 변경
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
     * 기준일 변경
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
     * 예산 리스트 조회
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
     * 이전 달 소비 내역 리스트 조회
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
     * 3개월 평균 소비 내역 리스트 조회
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
     * 예산 등록
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
     * 소비지출 상세내역 조회
     * @param session
     * @param model
     * @param consumeForm
     * @return
     */
    @RequestMapping("/getConsumeInfo.json")
    public String getConsumeInfo(HttpSession session, Model model, ConsumeForm consumeForm) {
    	logger.debug("getConsumeInfo");
    	String no_person = consumeForm.getNo_person();
    	if(no_person == null) {
    		no_person = (String) session.getAttribute("no_person");
    		consumeForm.setNo_person(no_person);
    	} else if (no_person.equals("")) {
    		no_person = (String) session.getAttribute("no_person");
    		consumeForm.setNo_person(no_person);
    	}
    	String cdResult = SessionUtil.chkNoPerson(no_person, consumeForm.getNo_person(), "03");
    	model.addAttribute("cdResult", cdResult);
		if("00".equals(cdResult)){
			model.addAttribute("consumeVO", consumeManager.getConsumeInfo(consumeForm));
		}
    	return "jsonView";
    }
    
    /**
     * VUE
     * 입출금일련번호를 통한 소비지출 상세내역 조회
     * @param session
     * @param model
     * @param consumeForm
     * @return
     */
    @RequestMapping("/getConsumeInfoWithSeqTran.json")
    public String getConsumeInfoWithSeqTran(HttpSession session, Model model, ConsumeForm consumeForm) {
    	logger.debug("getConsumeInfoWithSeqTran");
    	String no_person = consumeForm.getNo_person();
    	if(no_person == null) {
    		no_person = (String) session.getAttribute("no_person");
    		consumeForm.setNo_person(no_person);
    	} else if (no_person.equals("")) {
    		no_person = (String) session.getAttribute("no_person");
    		consumeForm.setNo_person(no_person);
    	}
    	model.addAttribute("consumeVO", consumeManager.getConsumeInfoWithSeqTran(consumeForm));
    	return "jsonView";
    }
    
    /**
     * VUE
     * 현금 + 스크래핑 된 사용자의 카드, 계좌 내역 조회
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/listMeansConsume.json")
    public String listMeansConsume(HttpSession session, Model model) {
    	logger.debug("listMeansConsume");
    	String no_person = (String) session.getAttribute("no_person");
    	model.addAttribute("listMeansConsume", consumeManager.listMeansConsume(no_person));
    	return "jsonView";
    }
    
    /**
     * VUE
     * 분류, 항목 조회
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
    
    /**
     * VUE
     * 분류 정렬순서 변경
     * @param session
     * @param model
     * @param personConsumeClassVO
     * @return
     */
    @RequestMapping("/modifyPersonSortClass.json")
    public String modifyPersonSortClass(HttpSession session, Model model, PersonConsumeClassVO personConsumeClassVO) {
    	logger.debug("modifyPersonSortClass");
    	String no_person = (String) session.getAttribute("no_person");
    	personConsumeClassVO.setNo_person(no_person);
    	consumeManager.modifyPersonSortClass(personConsumeClassVO);
    	return "jsonView";
    }
    
    /**
     * VUE
     * 항목 정렬순서 변경
     * @param session
     * @param model
     * @param personConsumeClassVO
     * @return
     */
    @RequestMapping("/modifyPersonSortType.json")
    public String modifyPersonSortType(HttpSession session, Model model, PersonConsumeClassVO personConsumeClassVO) {
    	logger.debug("modifyPersonSortType");
    	String no_person = (String) session.getAttribute("no_person");
    	personConsumeClassVO.setNo_person(no_person);
    	consumeManager.modifyPersonSortType(personConsumeClassVO);
    	return "jsonView";
    }
    
    /**
     * VUE 
     * 분류 삭제
     * @param session
     * @param model
     * @param personConsumeClassVO
     * @return
     */
    @RequestMapping("/deletePersonConsumeClass.json")
    public String deletePersonConsumeClass(HttpSession session, Model model, PersonConsumeClassVO personConsumeClassVO) {
    	logger.debug("deletePersonConsumeClass");
    	String no_person = (String) session.getAttribute("no_person");
    	personConsumeClassVO.setNo_person(no_person);
    	consumeManager.deletePersonConsumeClass(personConsumeClassVO);
    	return "jsonView";
    }
    
    /**
     * VUE
     * 항목 삭제
     * @param session
     * @param model
     * @param personConsumeClassVO
     * @return
     */
    @RequestMapping("/deletePersonConsumeClassType.json")
    public String deletePersonConsumeClassType(HttpSession session, Model model, PersonConsumeClassVO personConsumeClassVO) {
    	logger.debug("deletePersonConsumeClassType");
    	String no_person = (String) session.getAttribute("no_person");
    	personConsumeClassVO.setNo_person(no_person);
    	consumeManager.deletePersonConsumeClassType(personConsumeClassVO);
    	return "jsonView";
    }
    
    /**
     * VUE
     * 소비분류 추가
     * @param session
     * @param model
     * @param personConsumeClassVO
     * @return
     */
    @RequestMapping("/createPersonConsumeClassClass.json")
    public String createPersonConsumeClassClass(HttpSession session, Model model, PersonConsumeClassVO personConsumeClassVO) {
    	logger.debug("createPersonConsumeClassClass");
    	String no_person = (String) session.getAttribute("no_person");
    	personConsumeClassVO.setNo_person(no_person);
    	consumeManager.createPersonConsumeClassClass(personConsumeClassVO);
    	return "jsonView";
    }
    
    /**
     * VUE 
     * 소비항목 추가
     * @param session
     * @param model
     * @param personConsumeClassVO
     * @return
     */
    @RequestMapping("/createPersonConsumeClassType.json")
    public String createPersonConsumeClassType(HttpSession session, Model model, PersonConsumeClassVO personConsumeClassVO) {
    	logger.debug("createPersonConsumeClassType");
    	String no_person = (String) session.getAttribute("no_person");
    	personConsumeClassVO.setNo_person(no_person);
    	consumeManager.createPersonConsumeClassType(personConsumeClassVO);
    	return "jsonView";
    }
    
	/**
	 * VUE
	 * 수입분류 조회
	 * @param session
	 * @param model
	 * @param no_person
	 * @return
	 */
    @RequestMapping("/listPersonIncomeClassInfo.json")
    public String listPersonIncomeClassInfo(HttpSession session, Model model, String no_person) {
    	logger.debug("listPersonIncomeClassInfo");
    	if(no_person == null) {
    		no_person = (String) session.getAttribute("no_person");
    	} else if (no_person.equals("")) {
    		no_person = (String) session.getAttribute("no_person");
    	}
    	
    	model.addAttribute("listPersonIncomeClassInfo", consumeManager.listPersonIncomeClassInfo(no_person));
    	return "jsonView";
    }
    
    /**
     * VUE
     * 분류 명 변경
     * @param session
     * @param model
     * @param personConsumeClassVO
     * @return
     */
    @RequestMapping("/modifyPersonConsumeClassNmClass.json")
    public String modifyPersonConsumeClassNmClass(HttpSession session, Model model, PersonConsumeClassVO personConsumeClassVO) {
    	logger.debug("modifyPersonConsumeClassNmClass");
    	String no_person = (String) session.getAttribute("no_person");
    	personConsumeClassVO.setNo_person(no_person);
    	consumeManager.modifyPersonConsumeClassNmClass(personConsumeClassVO);
    	return "jsonView";
    }
    
    /**
     * VUE
     * 항목 명 변경
     * @param session
     * @param model
     * @param personConsumeClassVO
     * @return
     */
    @RequestMapping("/modifyPersonConsumeClassNmType.json")
    public String modifyPersonConsumeClassNmType(HttpSession session, Model model, PersonConsumeClassVO personConsumeClassVO) {
    	logger.debug("modifyPersonConsumeClassNmType");
    	String no_person = (String) session.getAttribute("no_person");
    	personConsumeClassVO.setNo_person(no_person);
    	consumeManager.modifyPersonConsumeClassNmType(personConsumeClassVO);
    	return "jsonView";
    }
    
    /**
     * VUE
     * 수입 분류 추가
     * @param session
     * @param model
     * @param personConsumeClassVO
     * @return
     */
    @RequestMapping("/createPersonConsumeClassIncome.json")
    public String createPersonConsumeClassIncome(HttpSession session, Model model, PersonConsumeClassVO personConsumeClassVO) {
    	logger.debug("createPersonConsumeClassIncome");
    	String no_person = (String) session.getAttribute("no_person");
    	personConsumeClassVO.setNo_person(no_person);
    	consumeManager.createPersonConsumeClassIncome(personConsumeClassVO);
    	return "jsonView";
    }
    
    /**
     * VUE
     * 소비지출 데이터 변경
     * @param session
     * @param model
     * @param consumeVO
     * @return
     */
    @RequestMapping("/modifyConsumeInfo.json")
    public String modifyConsumeInfo(HttpSession session, Model model, ConsumeVO consumeVO, String yn_change_all) {
    	logger.debug("modifyConsumeInfo");
    	String no_person = (String) session.getAttribute("no_person");
    	consumeVO.setNo_person(no_person);
    	consumeManager.modifyConsumeInfo(consumeVO, yn_change_all);
    	return "jsonView";
    }
    
    /**
     * VUE
     * 소비지출 데이터 삭제
     * @param session
     * @param model
     * @param consumeForm
     * @return
     */
    @RequestMapping("/deleteConsumeInfo.json")
    public String deleteConsumeInfo(HttpSession session, Model model, ConsumeForm consumeForm) {
    	logger.debug("deleteConsumeInfo");
    	String no_person = (String) session.getAttribute("no_person");
    	consumeForm.setNo_person(no_person);
    	consumeManager.deleteConsumeInfo(consumeForm);
    	return "jsonView";
    }
    
    /**
     * VUE
     * 소비지출 데이터 추가
     * @param session
     * @param model
     * @param consumeVO
     * @return
     */
    @RequestMapping("/createConsumeInfo.json")
    public String createConsumeInfo(HttpSession session, Model model, ConsumeVO consumeVO) {
    	logger.debug("createConsumeInfo");
    	String no_person = (String) session.getAttribute("no_person");
    	consumeVO.setNo_person(no_person);
    	logger.debug(consumeVO.toString());
    	consumeManager.createConsumeInfo(consumeVO);
    	return "jsonView";
    }
    
    /**
     * VUE
     * 배너에 들어갈 데이터 조회
     * @param session
     * @param model
     * @param consumeVO
     * @return
     * @throws FinsetException 
     */
    @RequestMapping("/getBannerData.json")
    public String getBannerData(HttpSession session, Model model, ConsumeVO consumeVO) throws FinsetException {
    	logger.debug("getBannerData");
    	String no_person = (String) session.getAttribute("no_person");
    	consumeVO.setNo_person(no_person);
		model.addAttribute("bannerData", consumeManager.getBannerData(consumeVO));
    	return "jsonView";
    }
    
    /**
     * VUE
     * 이력 상세 데이터 조회
     * @param session
     * @param model
     * @param consumeForm
     * @return
     * @throws FinsetException
     */
    @RequestMapping("/listConsumeAnalyze.json")
    public String listConsumeAnalyze(HttpSession session, Model model, ConsumeForm consumeForm) throws FinsetException {
    	logger.debug("listConsumeAnalyze");
    	String no_person = (String) session.getAttribute("no_person");
    	consumeForm.setNo_person(no_person);
    	model.addAttribute("listConsumeAnalyzeMonth", consumeManager.listConsumeAnalyzeMonth(consumeForm));
    	model.addAttribute("listConsumeAnalyzeDay", consumeManager.listConsumeAnalyzeDay(consumeForm));
    	return "jsonView";
    }
    
    /**
     * VUE
     * 통계 : 날짜 값을 받아 차트 데이터 조회 
     * @param session
     * @param model
     * @param consumeForm
     * @return
     * @throws FinsetException
     * @throws ParseException 
     */
    @RequestMapping("/listConsumeforSettlement.json")
    public String listConsumeforSettlement(HttpSession session, Model model, ConsumeForm consumeForm) throws FinsetException, ParseException {
    	logger.debug("listConsumeforSettlement");
    	String no_person = (String) session.getAttribute("no_person");
    	consumeForm.setNo_person(no_person);
    	String type = consumeForm.getContents();
    	
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date fromDt = format.parse(consumeForm.getDt_from());
		Date toDt = format.parse(consumeForm.getDt_to());
		if(toDt.compareTo(fromDt)<0) {
			ReturnClass returnClass = new ReturnClass("01" , "시작 날짜가 종료 날짜보다 더 큽니다.");
			model.addAttribute("message", returnClass.getMessage());
			model.addAttribute("result", returnClass.getCd_result());
		}else {
			model.addAttribute("result", "00");
			if("yr".equals(type)) {
				model.addAttribute("listSettlementConsumeData", consumeManager.listSettlementConsumeDataYear(consumeForm));
			} else if ("mon".equals(type)) {
				model.addAttribute("listSettlementConsumeData", consumeManager.listSettlementConsumeDataWeek(consumeForm));
			} else if("week".equals(type)) {
				model.addAttribute("listSettlementConsumeData", consumeManager.listSettlementConsumeDataDay(consumeForm));
			}	
		}
    	
    	return "jsonView";
    }
    
    /**
     * VUE
     * 통계 : 차트 선택시 범위에 따라 조회되는 데이터 
     * @param session
     * @param model
     * @param consumeForm
     * @return
     * @throws FinsetException
     */
    @RequestMapping("/getRangeListforSettlement.json")
    public String getRangeListforSettlement(HttpSession session, Model model, ConsumeForm consumeForm) throws FinsetException {
    	logger.debug("getRangeListforSettlement");
    	consumeForm.setNo_person((String)session.getAttribute("no_person"));
    	if(!consumeManager.verifyShareInfo(consumeForm)) {
    		model.addAttribute("result","99");
    		return null;
    	}
    	model.addAttribute("rangeList", consumeManager.getRangeListforSettlement(consumeForm));
    	return "jsonView";
    }
    
    /**
     * VUE
     * 통계 : 상세 페이지 리스트
     * @param session
     * @param model
     * @param consumeForm
     * @return
     * @throws FinsetException
     */
    @RequestMapping("/getSettlementDetail.json")
    public String getSettlementDetail(HttpSession session, Model model, ConsumeForm consumeForm) throws FinsetException {
    	logger.debug("getSettlementDetail");
    	logger.debug(consumeForm.toString());
    	model.addAttribute("rangeList", consumeManager.getSettlementDetail(consumeForm));
    	return "jsonView";
    }
    
    @RequestMapping("/chkNoPersonList.json")
    public String chkNoPersonList(HttpSession session, Model model, @RequestParam(value="no_person_list[]") List<String> no_person_list){
    	logger.debug("chkNoPersonList");
    	String no_person = (String)session.getAttribute("no_person");
    	model.addAttribute("cdResult", SessionUtil.chkNoPersonList(no_person, no_person_list, "03"));
    	return "jsonView";
    }
}
