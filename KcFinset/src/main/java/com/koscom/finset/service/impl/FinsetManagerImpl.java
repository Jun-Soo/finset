package com.koscom.finset.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finset.dao.FinsetMapper;
import com.koscom.finset.model.FinsetForm;
import com.koscom.finset.model.FinsetVO;
import com.koscom.finset.service.FinsetManager;

@Service("finsetManager")
public class FinsetManagerImpl implements FinsetManager {
	
	private static final Logger logger = LoggerFactory.getLogger(FinsetManagerImpl.class);

	@Autowired
	private FinsetMapper finsetMapper;
		
	@Override
	public List<TxFcReceiveVO> listSearchGoods(FinsetForm finsetForm){
		return finsetMapper.listSearchGoods(finsetForm);
	}
	
	@Override
	public int listSearchGoodsCount(FinsetForm finsetForm){
		return finsetMapper.listSearchGoodsCount(finsetForm);
	}
	
	@Override
	public int countLoading(FinsetVO vo){
		return finsetMapper.countLoading(vo);
	}
}
