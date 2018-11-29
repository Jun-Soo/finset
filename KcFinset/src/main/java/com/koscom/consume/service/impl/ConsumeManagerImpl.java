package com.koscom.consume.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.consume.dao.ConsumeMapper;
import com.koscom.consume.model.ConsumeDetailGoalInfoVO;
import com.koscom.consume.model.ConsumeForm;
import com.koscom.consume.model.ConsumeGoalInfoVO;
import com.koscom.consume.model.PersonSetInfoForm;
import com.koscom.consume.model.ConsumeVO;
import com.koscom.consume.model.PaymentForm;
import com.koscom.consume.model.PaymentVO;
import com.koscom.consume.model.PersonConsumeClassVO;
import com.koscom.consume.model.PersonSetInfoVO;
import com.koscom.consume.model.PersonTransDetailVO;
import com.koscom.consume.service.ConsumeManager;
import com.koscom.domain.PersonInfo;

@Service("consumeManager")
public class ConsumeManagerImpl implements ConsumeManager {

	private static final Logger logger = LoggerFactory.getLogger(ConsumeManagerImpl.class);

	@Autowired
	private ConsumeMapper consumeMapper;

	@Override
	public List<PersonInfo> listConsumeSharePersonInfo(String no_person) {
		logger.debug("listConsumeShareInfo");
		return consumeMapper.listConsumeSharePersonInfo(no_person);
	}

	@Override
	public boolean chkScrapCard(String no_person) {
		logger.debug("chkScrapCard");
		List<String> list = consumeMapper.chkScrapCard(no_person);
		if(list == null) {
			return false;
		} else if(list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public ConsumeGoalInfoVO getConsumeGoalData(String no_person, String ym) {
		ConsumeForm consumeForm = new ConsumeForm();
		consumeForm.setNo_person(no_person);
		consumeForm.setYm_trd(ym);
		String consumeAmt = consumeMapper.getConsumeInfoAmt(consumeForm);
		
		ConsumeDetailGoalInfoVO consumeDetailGoalInfoVO = new ConsumeDetailGoalInfoVO();
		consumeDetailGoalInfoVO.setNo_person(no_person);
		consumeDetailGoalInfoVO.setReq_yyyymm(ym);
		List<ConsumeDetailGoalInfoVO> listGoal = consumeMapper.getConsumeGoal(consumeDetailGoalInfoVO);
		
		ConsumeGoalInfoVO consumeGoalInfoVO = new ConsumeGoalInfoVO();
		consumeGoalInfoVO.setAmt_expense(consumeAmt);
		
		if(listGoal == null) {
			return null;
		}
		int key = listGoal.size();
		switch (key) {
		case 0:
			return null;
		case 1:
			if(listGoal.get(0).getAmt_budget().equals("0")) {
				return null;
			} else {
				consumeGoalInfoVO.setAmt_budget(listGoal.get(0).getAmt_budget());
			}
			break;
		case 2:
			String classAmt_budget = listGoal.get(0).getAmt_budget();  
			String meansAmt_budget = listGoal.get(1).getAmt_budget();
			if(classAmt_budget.equals("0") && meansAmt_budget.equals("0")) {
				return null;
			} else if(!classAmt_budget.equals("0") && !meansAmt_budget.equals("0")) {
				consumeGoalInfoVO.setAmt_budget(classAmt_budget);
			} else {
				if(classAmt_budget.equals("0")) {
					consumeGoalInfoVO.setAmt_budget(meansAmt_budget);
				} else {
					consumeGoalInfoVO.setAmt_budget(classAmt_budget);
				}
			}
			break;
		default:
			return null;
		}
		return consumeGoalInfoVO;
	}
	
	@Override
	public Map<String, String> listConsumeInfoAmt(ConsumeForm consumeForm) {
		logger.debug("getConsumeInfoAmt");
		Map<String, String> summaryMap = new HashMap<String, String>();
		summaryMap.put("income", "0");
		summaryMap.put("consume", "0");
		List<ConsumeVO> rawList = consumeMapper.listConsumeInfoAmt(consumeForm);
		for(ConsumeVO vo: rawList) {
			if(vo.getType_in_out().equals("01")) {
				summaryMap.put("income", vo.getAmt_in_out());
			} else {
				summaryMap.put("consume", vo.getAmt_in_out());
			}
		}
		return summaryMap;
	}
	
	@Override
	public List<List<ConsumeVO>> listConsumeInfo(ConsumeForm consumeForm) {
		logger.debug("listConsumeInfo");
		List<ConsumeVO> rawList = consumeMapper.listConsumeInfo(consumeForm);
		if(rawList.size() == 0) {
			return null;
		} else {
	    	List<List<ConsumeVO>> consumeList = new ArrayList<List<ConsumeVO>>();
	    	List<ConsumeVO> tempList = new ArrayList<ConsumeVO>();
	    	String curDt="";
	    	for(ConsumeVO vo : rawList) {
	    		if(!curDt.equals(vo.getDt_trd())) {
	    			if(!curDt.equals("")) {
	    				consumeList.add(tempList);
	    				tempList = new ArrayList<ConsumeVO>();
	    			}
	    			curDt = vo.getDt_trd();
	    		}
	    		tempList.add(vo);
	    	}
	    	consumeList.add(tempList);
	    	return consumeList;
		}
	}

	@Override
	public List<List<PersonTransDetailVO>> listPersonTransDetail(ConsumeForm consumeForm) {
		logger.debug("listPersonTransDetail");
		List<List<PersonTransDetailVO>> returnList = new ArrayList<List<PersonTransDetailVO>>();
		List<PersonTransDetailVO> subList = new ArrayList<PersonTransDetailVO>();
		String dt_trd = "";
		
		List<PersonTransDetailVO> rawList = consumeMapper.listPersonTransDetail(consumeForm);
		for(PersonTransDetailVO vo: rawList) {
			if(vo.getDt_trd().equals(dt_trd)) {
				subList.add(vo);
			} else {
				if(!dt_trd.equals("")) {
					returnList.add(subList);
					subList = new ArrayList<PersonTransDetailVO>();
				}
				dt_trd = vo.getDt_trd();
				subList.add(vo);
			}
		}
		returnList.add(subList);
		
		return returnList;
	}

	@Override
	public int createDefaultConsumeClassInfo(String no_person) {
		logger.debug("createDefaultConsumeClassInfo");
		return consumeMapper.createDefaultConsumeClassInfo(no_person);
	}
	
	@Override
	public List<List<PersonConsumeClassVO>> listPersonConsumeClassInfo(String no_person) {
		
		logger.debug("listPersonConsumeClassInfo");
		
		List<List<PersonConsumeClassVO>> consumeClassList = new ArrayList<>();
		List<PersonConsumeClassVO> rawList = consumeMapper.listPersonConsumeClassInfo(no_person);
		List<PersonConsumeClassVO> tempList = new ArrayList<>();
		String curCd_class = "";
		for(PersonConsumeClassVO vo: rawList) {
			if(curCd_class.equals("")) {
				tempList.add(vo);
				curCd_class = vo.getCd_class();
			} else if(curCd_class.equals(vo.getCd_class())) {
				tempList.add(vo);
			} else if(!curCd_class.equals(vo.getCd_class())) {
				consumeClassList.add(tempList);
				tempList = new ArrayList<>();
				curCd_class = vo.getCd_class();
				tempList.add(vo);
			}
		}
		consumeClassList.add(tempList);
		
		return consumeClassList;
	}
	
	@Override
	public List<PersonConsumeClassVO> listPersonIncomeClassInfo(String no_person) {
		logger.debug("listPersonIncomeClassInfo");
		return consumeMapper.listPersonIncomeClassInfo(no_person);
	}
	
	@Override
	public PersonSetInfoVO getPersonSetInfo(String no_person) {
		logger.debug("getPersonSetInfo");
		return consumeMapper.getPersonSetInfo(no_person);
	}
	
	@Override
	public int modifyPersonSetInfo(PersonSetInfoVO personSetInfoVO) {
		logger.debug("modifyPersonSetInfo");
		return consumeMapper.modifyPersonSetInfo(personSetInfoVO);
	}
	
	@Override
	public int createGoal(ConsumeGoalInfoVO consumeGoalInfoVO) {
		logger.debug("createGoal");
		return consumeMapper.createGoal(consumeGoalInfoVO);
	}
	
	@Override
	public ConsumeGoalInfoVO getGoal(ConsumeGoalInfoVO consumeGoalInfoVO) {
		logger.debug("getGoal");
		return consumeMapper.getGoal(consumeGoalInfoVO);
	}
	
	@Override
	public List<ConsumeVO> listCalendarConsumeData(ConsumeForm consumeForm) {
		logger.debug("listCalendarConsumeData");
		return consumeMapper.listCalendarConsumeData(consumeForm);
	}

	@Override
	public List<ConsumeVO> listDetailCalendarConsumeData(ConsumeForm consumeForm) {
		logger.debug("listDetailCalendarConsumeData");
		return consumeMapper.listDetailCalendarConsumeData(consumeForm);
	}
	
	@Override
	public PaymentVO getPaymentSummary(PaymentForm paymentForm) {
		logger.debug("getPaymentSummary");
		return consumeMapper.getPaymentSummary(paymentForm);
	}
	
	@Override
	public List<PaymentVO> listPayment(PaymentForm paymentForm) {
		logger.debug("listPayment");
		return consumeMapper.listPayment(paymentForm);
	}

	@Override
	public void modifyYn_installment(PersonSetInfoForm personSetInfoForm) {
		logger.debug("modifyYn_installment");
		consumeMapper.modifyYn_installment(personSetInfoForm);
	}
	
	@Override
	public void modifyDt_basic(PersonSetInfoForm personSetInfoForm) {
		logger.debug("modifyDt_basic");
		consumeMapper.modifyDt_basic(personSetInfoForm);
	}

	@Override
	public List<ConsumeDetailGoalInfoVO> listDetailGoal(String no_person, String cd_set) {
		logger.debug("listDetailGoal");
		if(cd_set.equals("01")) {
			return consumeMapper.listDetailGoalClass(no_person);
		} else {
			return consumeMapper.listDetailGoalMeans(no_person);
		}
		
	}
	
	@Override
	public List<ConsumeDetailGoalInfoVO> listPrevMonthConsume(String no_person, String cd_set) {
		logger.debug("listPrevMonthConsume");
		if(cd_set.equals("01")) {
			return consumeMapper.listPrevMonthClass(no_person);
		} else {
			return consumeMapper.listPrevMonthMeans(no_person);
		}
		
	}
	
	@Override
	public void createDetailGoal(ConsumeDetailGoalInfoVO consumeDetailGoalInfoVO, String cd_set) {
		logger.debug("createDetailGoal");
		List<ConsumeDetailGoalInfoVO> consumeDetailGoalList = consumeDetailGoalInfoVO.getList();
		if(cd_set.equals("01")) {
			for(ConsumeDetailGoalInfoVO vo: consumeDetailGoalList) {
				vo.setNo_person(consumeDetailGoalInfoVO.getNo_person());
				consumeMapper.createDetailGoalClass(vo);
			}
		} else {
			for(ConsumeDetailGoalInfoVO vo: consumeDetailGoalList) {
				vo.setNo_person(consumeDetailGoalInfoVO.getNo_person());
				consumeMapper.createDetailGoalMeans(vo);
			}
		}
	}
	
	@Override
	public List<ConsumeDetailGoalInfoVO> listAverageConsume(String no_person, String cd_set) {
		logger.debug("listAverageConsume");
		if(cd_set.equals("01")) {
			return consumeMapper.listAverageClass(no_person);
		} else {
			return consumeMapper.listAverageMeans(no_person);
		}
		
	}
	
	@Override
	public ConsumeVO getConsumeInfo(ConsumeForm consumeForm) {
		logger.debug("getConsumeInfo");
		return consumeMapper.getConsumeInfo(consumeForm);
	}
	
	@Override
	public void modifyPersonSortClass(PersonConsumeClassVO personConsumeClassVO) {
		logger.debug("modifyPersonSortClass");
		List<PersonConsumeClassVO> list = personConsumeClassVO.getList();
		String no_person = personConsumeClassVO.getNo_person();
		for(PersonConsumeClassVO vo: list) {
			vo.setNo_person(no_person);
			consumeMapper.modifyPersonSortClass(vo);
		}
	}
	
	@Override
	public void modifyPersonSortType(PersonConsumeClassVO personConsumeClassVO) {
		logger.debug("modifyPersonSortType");
		List<PersonConsumeClassVO> list = personConsumeClassVO.getList();
		String no_person = personConsumeClassVO.getNo_person();
		for(PersonConsumeClassVO vo: list) {
			vo.setNo_person(no_person);
			consumeMapper.modifyPersonSortType(vo);
		}
	}
	
	@Override
	public void deletePersonConsumeClass(PersonConsumeClassVO personConsumeClassVO) {
		logger.debug("deletePersonConsumeClass");
		try{
			consumeMapper.deletePersonConsumeClass(personConsumeClassVO);
			consumeMapper.modifyPersonConsumeClassOtherClass(personConsumeClassVO);
			consumeMapper.modifyConsumeInfoClass(personConsumeClassVO);
			if(personConsumeClassVO.getType_in_out().equals("02")) {
				consumeMapper.modifyConsumeGoalInfoClass(personConsumeClassVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	@Override
	public void deletePersonConsumeClassType(PersonConsumeClassVO personConsumeClassVO) {
		logger.debug("deletePersonConsumeClassType");
		try {
			consumeMapper.deletePersonConsumeClassType(personConsumeClassVO);
			consumeMapper.modifyPersonConsumeClassOtherType(personConsumeClassVO);
			consumeMapper.modifyConsumeInfoType(personConsumeClassVO);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	@Override
	public void modifyPersonConsumeClassNmClass(PersonConsumeClassVO personConsumeClassVO) {
		logger.debug("modifyPersonConsumeClassNmClass");
		consumeMapper.modifyPersonConsumeClassNmClass(personConsumeClassVO);
	}
	
	@Override
	public void modifyPersonConsumeClassNmType(PersonConsumeClassVO personConsumeClassVO) {
		logger.debug("modifyPersonConsumeClassNmType");
		consumeMapper.modifyPersonConsumeClassNmType(personConsumeClassVO);
	}
	
	@Override
	public void createPersonConsumeClassClass(PersonConsumeClassVO personConsumeClassVO) {
		logger.debug("createPersonConsumeClassClass");
		consumeMapper.createPersonConsumeClassClass(personConsumeClassVO);
	}
	
	@Override
	public void createPersonConsumeClassType(PersonConsumeClassVO personConsumeClassVO) {
		logger.debug("createPersonConsumeClassType");
		consumeMapper.createPersonConsumeClassType(personConsumeClassVO);
	}
	
	@Override
	public void createPersonConsumeClassIncome(PersonConsumeClassVO personConsumeClassVO) {
		logger.debug("createPersonConsumeClassIncome");
		consumeMapper.createPersonConsumeClassIncome(personConsumeClassVO);
	}
	
	@Override
	public void modifyConsumeInfo(ConsumeVO consumeVO) {
		logger.debug("modifyConsumeInfo");
		consumeMapper.modifyConsumeInfo(consumeVO);
	}
	
	@Override
	public void deleteConsumeInfo(ConsumeForm consumeForm) {
		logger.debug("deleteConsumeInfo");
		consumeMapper.deleteConsumeInfo(consumeForm);
	}
	
	@Override
	public void createConsumeInfo(ConsumeVO consumeVO) {
		logger.debug("createConsumeInfo");
		consumeMapper.createConsumeInfo(consumeVO);
	}

	@Override
	public int getBannerDataConsume(ConsumeVO consumeVO) {
		logger.debug("getBannerDataConsume");
		return consumeMapper.getBannerDataConsume(consumeVO);
	}

	@Override
	public int getBannerDataIncome(ConsumeVO consumeVO) {
		logger.debug("getBannerDataIncome");
		List<ConsumeVO> list = consumeMapper.getBannerDataIncome(consumeVO);
		if(list == null || list.size() != 2) {
			return 0;
		} else {
			ConsumeVO firstVO = list.get(0);
			ConsumeVO secondVO = list.get(1);
			return Integer.parseInt(secondVO.getAmt_in_out()) - Integer.parseInt(firstVO.getAmt_in_out());
		}
	}
	
	@Override
	public List<ConsumeVO> listMeansConsume(String no_person) {
		logger.debug("listMeansConsume");
		return consumeMapper.listMeansConsume(no_person);
	}

	@Override
	public List<ConsumeVO> listCalendarConsumeDataYear(ConsumeForm consumeForm) {
		logger.debug("listCalendarConsumeDataYear");
		return consumeMapper.listCalendarConsumeDataYear(consumeForm);
	}
}
