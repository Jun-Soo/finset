package com.koscom.consume.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.consume.dao.ConsumeMapper;
import com.koscom.consume.model.ConsumeForm;
import com.koscom.consume.model.ConsumeVO;
import com.koscom.consume.model.PersonConsumeClassVO;
import com.koscom.consume.model.PersonSetInfoVO;
import com.koscom.consume.model.PersonTransDetailVO;
import com.koscom.consume.service.ConsumeManager;

@Service("consumeManager")
public class ConsumeManagerImpl implements ConsumeManager {

	private static final Logger logger = LoggerFactory.getLogger(ConsumeManagerImpl.class);

	@Autowired
	private ConsumeMapper consumeMapper;
	
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
}
