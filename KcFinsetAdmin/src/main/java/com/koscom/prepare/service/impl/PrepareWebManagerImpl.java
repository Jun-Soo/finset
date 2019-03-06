package com.koscom.prepare.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import com.koscom.util.FinsetException;
import com.koscom.util.FinsetMessageException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.apply.service.ApplyManager;
import com.koscom.car.service.CarManager;
import com.koscom.person.service.PersonManager;
import com.koscom.prepare.model.PrepareForm;
import com.koscom.prepare.model.PrepareVO;
import com.koscom.prepare.service.PrepareManager;
import com.koscom.prepare.service.PrepareWebManager;
import com.koscom.util.ReturnClass;

@Service("prepareWebManager")
public class PrepareWebManagerImpl implements PrepareWebManager {

//	private static final Logger logger = LoggerFactory.getLogger(PrepareWebManagerImpl.class);
	
	@Autowired
	private PrepareManager prepareManager;
	
	@Autowired
	private PersonManager personManager;
	
	@Autowired
	private CarManager carManager;
	
	@Autowired
	private ApplyManager applyManager;
	
	@Override
	public PrepareVO getPrepare(String no_prepare) {
		return prepareManager.getPrepare(no_prepare);
	}

	@Override
	public ReturnClass createPrepareSummary(PrepareVO prepareVO) {
		return prepareManager.createPrepareSummary(prepareVO);
	}
	
	@Override
	public ReturnClass createPrepareAgency(PrepareVO prepareVO) {
		return prepareManager.createPrepareAgency(prepareVO);
	}

	@Override
	public int getCntPrepareTodayByHP(String hp) {
		return prepareManager.getCntPrepareTodayByHP(hp);
	}

	@Override
	public PrepareVO getPrepareExist(PrepareVO prepareVO) {
		return prepareManager.getPrepareExist(prepareVO);
	}

	@Override
	public List<PrepareVO> listPrepareInfoAgency(PrepareForm prepareForm) {
		return prepareManager.listPrepareInfoAgency(prepareForm);
	}

	@Override
	public int listPrepareCountAgency(PrepareForm prepareForm) {
		return prepareManager.listPrepareCountAgency(prepareForm);
	}

	@Override
	public List<PrepareVO> listPrepareByPerson(PrepareForm prepareForm) {
		return prepareManager.listPrepareByPerson(prepareForm);
	}
	
	@Override
	public ReturnClass modifyPrepareAgency(PrepareVO prepareVO) {
		return prepareManager.modifyPrepareAgency(prepareVO);
	}

	@Override
	public ReturnClass updateOverlapChk(PrepareVO prepareVO) {
		return prepareManager.updateOverlapChk(prepareVO);
	}

	@Override
	public ReturnClass updatePrepareChk(PrepareVO prepareVO) throws ParseException, FinsetException, FinsetMessageException, IOException{
		return prepareManager.updatePrepareChk(prepareVO);
	}

	@Override
	public List<PrepareVO> listPrepareInfo(PrepareForm prepareForm) {
		// TODO Auto-generated method stub
		return prepareManager.listPrepareInfo(prepareForm);
	}

	@Override
	public int listPrepareCount(PrepareForm prepareForm) {
		// TODO Auto-generated method stub
		return prepareManager.listPrepareCount(prepareForm);
	}

	@Override
	public ReturnClass modifyPrepare(PrepareVO prepareVO) throws ParseException, IOException{
		// TODO Auto-generated method stub
		return prepareManager.modifyPrepare(prepareVO);
	}

	@Override
	public ReturnClass modifyPrepareDoc(PrepareVO prepareVo) throws ParseException, IOException {
		// TODO Auto-generated method stub
		return prepareManager.modifyPrepareDoc(prepareVo);
	}

	@Override
	public ReturnClass modifyPrepareId(PrepareVO prepareVo) {
		// TODO Auto-generated method stub
		return prepareManager.modifyPrepareId(prepareVo);
	}

	@Override
	public ReturnClass modifyPrepareBatch(PrepareVO prepareVo) throws ParseException, IOException{
		// TODO Auto-generated method stub
		return prepareManager.modifyPrepareBatch(prepareVo);
	}

	@Override
	public String getNoPerson(String no_prepare) {
		// TODO Auto-generated method stub
		return prepareManager.getNoPerson(no_prepare);
	}

	@Override
	public List<HashMap<String, String>> getCntPrepareDoc(PrepareForm prepareForm) {
		// TODO Auto-generated method stub
		return prepareManager.getCntPrepareDoc(prepareForm);
	}

	@Override
	public List<HashMap<String, String>> getCntPrepareClass(PrepareForm prepareForm) {
		// TODO Auto-generated method stub
		return prepareManager.getCntPrepareClass(prepareForm);
	}

	@Override
	public ReturnClass updatePrepareCnt(String no_prepare) {
		// TODO Auto-generated method stub
		return prepareManager.updatePrepareCnt(no_prepare);
	}

	@Override
	public ReturnClass updateCounselMemo(PrepareVO prepareVO) {
		// TODO Auto-generated method stub
		return prepareManager.updateCounselMemo(prepareVO);
	}

	@Override
	public List<HashMap<String, Object>> listPrepareInfo_excel(PrepareForm prepareForm) {
		// TODO Auto-generated method stub
		return prepareManager.listPrepareInfo_excel(prepareForm);
	}

	@Override
	public ReturnClass updatePrepareLst(PrepareVO prepareVO) {
		// TODO Auto-generated method stub
		return prepareManager.updatePrepareLst(prepareVO);
	}

	@Override
	public String getNoPrepare(PrepareForm prepareForm) {
		// TODO Auto-generated method stub
		return prepareManager.getNoPrepare(prepareForm);
	}

	@Override
	public ReturnClass procPreparePath(PrepareVO prepareVO) {
		// TODO Auto-generated method stub
		return prepareManager.procPreparePath(prepareVO);
	}

}
