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
import com.koscom.util.DateUtil;

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
		ConsumeGoalInfoVO consumeGoalInfoVO = new ConsumeGoalInfoVO();
		consumeGoalInfoVO.setAmt_expense(consumeAmt);
		String amt_budget = consumeMapper.getConsumeGoal(consumeDetailGoalInfoVO);
		if(amt_budget == null || amt_budget.equals("") || amt_budget.equals("0")) {
			return null;
		}
		consumeGoalInfoVO.setAmt_budget(amt_budget);
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
	public List<Integer> listRegisteredSeqTran(ConsumeForm consumeForm) {
		logger.debug("listRegisteredSeqTran");
		return consumeMapper.listRegisteredSeqTran(consumeForm);
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
	public PersonTransDetailVO getPersonTransDetail(ConsumeForm consumeForm) {
		logger.debug("getPersonTransDetail");
		return consumeMapper.getPersonTransDetail(consumeForm);
	}
	
	@Override
	public boolean chkPersonConsumeClassInfoExist(String no_person) {
		logger.debug("chkPersonConsumeClassInfoExist");
		if(consumeMapper.chkPersonConsumeClassInfoExist(no_person) < 1) {
			return false;
		} else {
			return true;
		}
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
				vo.setAmt_budget(vo.getAmt_budget().replaceAll(",", ""));
				vo.setYn_person_regist("Y");
				consumeMapper.createDetailGoalClass(vo);
			}
		} else {
			for(ConsumeDetailGoalInfoVO vo: consumeDetailGoalList) {
				vo.setNo_person(consumeDetailGoalInfoVO.getNo_person());
				vo.setAmt_budget(vo.getAmt_budget().replaceAll(",", ""));
				vo.setYn_person_regist("Y");
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
	public ConsumeVO getConsumeInfoWithSeqTran(ConsumeForm consumeForm) {
		logger.debug("getConsumeInfoWithSeqTran");
		return consumeMapper.getConsumeInfoWithSeqTran(consumeForm);
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
		if("04".equals(consumeVO.getMeans_consume())) {
			if(consumeVO.getYn_auto().equals("Y")) {
				List<ConsumeVO> list = consumeMapper.listPrevTransactionDetail(consumeVO);
				for(ConsumeVO vo: list) {
					consumeMapper.createConsumeInfoOthers(vo);
				}
			} else {
				consumeMapper.createConsumeInfoAcc(consumeVO);	
			}
		} else {
			consumeMapper.createConsumeInfoOthers(consumeVO);
		}
	}

	@Override
	public int getBannerData(ConsumeVO consumeVO) {
		logger.debug("getBannerData");
		return consumeMapper.getBannerData(consumeVO);
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

	@Override
	public List<ConsumeVO> listConsumeAnalyzeMonth(ConsumeForm consumeForm) {
		logger.debug("listConsumeAnalyzeMonth");
		return consumeMapper.listConsumeAnalyzeMonth(consumeForm);
	}

	@Override
	public List<ConsumeVO> listConsumeAnalyzeDay(ConsumeForm consumeForm) {
		logger.debug("listConsumeAnalyzeDay");
		return consumeMapper.listConsumeAnalyzeDay(consumeForm);
	}
	
	@Override
	public List<ConsumeVO> listSettlementConsumeDataYear(ConsumeForm consumeForm) {
		logger.debug("listSettlementConsumeDataYear");
		if(consumeForm.getDt_from().length() > 6) {
			consumeForm.setDt_from(consumeForm.getDt_from().substring(0,6));
		}
		if(consumeForm.getDt_to().length() > 6) {
			consumeForm.setDt_to(consumeForm.getDt_to().substring(0,6));
		}
		return consumeMapper.listSettlementConsumeDataYear(consumeForm);
	}
	
	@Override
	public List<ConsumeVO> listSettlementConsumeDataWeek(ConsumeForm consumeForm) {
		logger.debug("listSettlementConsumeDataWeek");
		return consumeMapper.listSettlementConsumeDataWeek(consumeForm);
	}
	
	@Override
	public List<ConsumeVO> listSettlementConsumeDataDay(ConsumeForm consumeForm) {
		logger.debug("listSettlementConsumeDataDay");
		return consumeMapper.listSettlementConsumeDataDay(consumeForm);
	}
	
	@Override
	public List<ConsumeVO> getRangeListforSettlement(ConsumeForm consumeForm) {
		logger.debug("getRangeListforSettlement");
		return consumeMapper.getRangeListforSettlement(consumeForm);
	}
	
	@Override
	public List<ConsumeVO> getSettlementDetail(ConsumeForm consumeForm) {
		logger.debug("getSettlementDetail");
		return consumeMapper.getSettlementDetail(consumeForm);
	}

	@Override
	public void autoRegisterGoal(String no_person) {
		String req_yyyymm = ""; // 예산에서 max값으로 나온 년월 
		String cur_yyyymm = DateUtil.getCurrentDateTime("yyyyMM"); // 금일에 해당하는 년월
		String tmp_yyyymm = cur_yyyymm; // for구문을 돌릴 때 사용할 년월
		List<ConsumeDetailGoalInfoVO> listClass = new ArrayList<ConsumeDetailGoalInfoVO>();
		listClass = consumeMapper.chkConsumeGoalInfoClass(no_person);
		if(listClass != null) {
			if(listClass.size() != 0) {
				// 데이터가 존재
				req_yyyymm = listClass.get(0).getReq_yyyymm();
				if(!req_yyyymm.equals(cur_yyyymm)) {
					while(true) {
						if(Integer.parseInt(req_yyyymm)>=Integer.parseInt(tmp_yyyymm)) {
							break;
						}
						for(ConsumeDetailGoalInfoVO vo: listClass) {
							vo.setReq_yyyymm(tmp_yyyymm);
							vo.setYn_person_regist("N");
							consumeMapper.createDetailGoal(vo);
						}
						//addMonths 에서 사용하는 값이 yyyyMMdd 형식이라 임의로 01 추가
						tmp_yyyymm = DateUtil.addMonths(tmp_yyyymm+"01", -1).substring(0,6);
					}
				}
			}
		}
		tmp_yyyymm = cur_yyyymm;
		List<ConsumeDetailGoalInfoVO> listMeans = new ArrayList<ConsumeDetailGoalInfoVO>();
		listMeans = consumeMapper.chkConsumeGoalInfoMeans(no_person);
		if(listMeans != null) {
			if(listMeans.size() != 0) {
				// 데이터가 존재
				req_yyyymm = listMeans.get(0).getReq_yyyymm();
				if(!req_yyyymm.equals(cur_yyyymm)) {
					while(true) {
						if(Integer.parseInt(req_yyyymm)>=Integer.parseInt(tmp_yyyymm)) {
							break;
						}
						for(ConsumeDetailGoalInfoVO vo: listMeans) {
							vo.setReq_yyyymm(tmp_yyyymm);
							vo.setYn_person_regist("N");
							consumeMapper.createDetailGoal(vo);
						}
						//addMonths 에서 사용하는 값이 yyyyMMdd 형식이라 임의로 01 추가
						tmp_yyyymm = DateUtil.addMonths(tmp_yyyymm+"01", -1).substring(0,6);
					}
				}
			}
		}
	}
}
