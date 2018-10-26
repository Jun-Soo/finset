package com.koscom.consume.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.consume.dao.ConsumeMapper;
import com.koscom.consume.model.ConsumeDetailGoalInfoVO;
import com.koscom.consume.model.ConsumeForm;
import com.koscom.consume.model.ConsumeGoalInfoVO;
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
	public int getConsumeInfoAmt(ConsumeForm consumeForm) {
		logger.debug("getConsumeInfoAmt");
		return consumeMapper.getConsumeInfoAmt(consumeForm);
	}
	
	@Override
	public List<ConsumeVO> listConsumeInfo(ConsumeForm consumeForm) {
		logger.debug("listConsumeInfo");
		return consumeMapper.listConsumeInfo(consumeForm);
	}

	@Override
	public List<PersonTransDetailVO> listPersonTransDetail(ConsumeForm consumeForm) {
		logger.debug("listPersonTransDetail");
		return consumeMapper.listPersonTransDetail(consumeForm);
	}

	@Override
	public int createDefaultConsumeClassInfo(String no_person) {
		logger.debug("createDefaultConsumeClassInfo");
		return consumeMapper.createDefaultConsumeClassInfo(no_person);
	}
	
	@Override
	public List<List<PersonConsumeClassVO>> listPersonConsumeClassInfo(String no_person) {
		logger.debug("listPersonConsumeClassInfo");
		
		List<List<PersonConsumeClassVO>> returnList = new ArrayList<List<PersonConsumeClassVO>>();
		List<PersonConsumeClassVO> list = consumeMapper.listPersonConsumeClassInfo(no_person);
		String curClass = "";
		List<PersonConsumeClassVO> subList = new ArrayList<PersonConsumeClassVO>();
		for(PersonConsumeClassVO vo: list) {
			if(!curClass.equals(vo.getCd_class())){
				if(!curClass.equals("")){
					returnList.add(subList);
					subList = new ArrayList<PersonConsumeClassVO>();
				}
				curClass = vo.getCd_class();
			}
			subList.add(vo);
		}
		returnList.add(subList);
		return returnList;
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
	public int createDetailGoal(ConsumeDetailGoalInfoVO consumeDetailGoalInfoVO) {
		logger.debug("createDetailGoal");
		return consumeMapper.createDetailGoal(consumeDetailGoalInfoVO);
	}
	
	@Override
	public List<ConsumeDetailGoalInfoVO> listDetailGoal(ConsumeDetailGoalInfoVO consumeDetailGoalInfoVO) {
		logger.debug("listDetailGoal");
		return consumeMapper.listDetailGoal(consumeDetailGoalInfoVO);
	}
	
	@Override
	public List<ConsumeVO> getCalendarConsumeData(ConsumeForm consumeForm) {
		logger.debug("getCalendarConsumeData");
		return consumeMapper.getCalendarConsumeData(consumeForm);
	}

	@Override
	public List<ConsumeVO> listCalendarConsumeData(ConsumeForm consumeForm) {
		logger.debug("listCalendarConsumeData");
		return consumeMapper.listCalendarConsumeData(consumeForm);
	}
	
	@Override
	public List<PaymentVO> listPayment(PaymentForm paymentForm) {
		logger.debug("listPayment");
		return consumeMapper.listPayment(paymentForm);
	}
}
