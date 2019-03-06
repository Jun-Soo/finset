package com.koscom.finset.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import com.koscom.goods.model.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.FinsetInfo;
import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finset.model.FinsetDenyVO;
import com.koscom.finset.model.FinsetForm;
import com.koscom.finset.model.FinsetVO;
import com.koscom.finset.service.FinsetManager;
import com.koscom.finset.service.FinsetWebManager;

@Service("finsetWebManager")
public class FinsetWebManagerImpl implements FinsetWebManager {
	@Autowired
	private FinsetManager finsetManager;
	@Override
	public List<FinsetVO> listFinsetGoodsInfo(FinsetForm finsetForm){
		return finsetManager.listFinsetGoodsInfo(finsetForm);
	}
	@Override
	public int listFinsetGoodsCount(FinsetForm finsetForm){
		return finsetManager.listFinsetGoodsCount(finsetForm);
	}
	@Override
	public LinkedHashMap<FinsetVO, List<FinsetInfo>> listGoodsInfoForFinset(TxFcReceiveVO txFcReceiveVO) {
		return finsetManager.listGoodsInfoForFinset(txFcReceiveVO);
	}
	@Override
	public void insertFinsetDeny(FinsetDenyVO finsetDenyVO) {
		finsetManager.insertFinsetDeny(finsetDenyVO);
	}

	@Override
	public List<TxFcReceiveVO> listSearchGoods(FinsetForm finsetForm){
		return finsetManager.listSearchGoods(finsetForm);
	}

	@Override
	public int countLoading(FinsetVO vo){
		return finsetManager.countLoading(vo);
	}
	@Override
	public int listSearchGoodsCount(FinsetForm finsetForm){
		return finsetManager.listSearchGoodsCount(finsetForm);
	}
}