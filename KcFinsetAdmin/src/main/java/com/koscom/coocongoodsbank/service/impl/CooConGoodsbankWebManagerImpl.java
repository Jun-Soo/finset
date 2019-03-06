package com.koscom.coocongoodsbank.service.impl;

import com.koscom.coocongoodsbank.service.CooConGoodsbankManager;
import com.koscom.coocongoodsbank.service.CooConGoodsbankWebManager;
import com.koscom.coocongoodsbank.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cooConGoodsbankWebManager")
public class CooConGoodsbankWebManagerImpl implements CooConGoodsbankWebManager {
	
	private static final Logger logger = LoggerFactory.getLogger(CooConGoodsbankManagerImpl.class);
	
	@Autowired
	CooConGoodsbankManager cooConGoodsbankManager;
	
	@Override
	public List<GoodsStatsVO> listGoodsStats(GoodsStatsFormVO goodsStatsFormVO) {
		return cooConGoodsbankManager.listGoodsStats(goodsStatsFormVO);
	}
	@Override
	public int listGoodsStatsCount(GoodsStatsFormVO goodsStatsFormVO) {
		return cooConGoodsbankManager.listGoodsStatsCount(goodsStatsFormVO);
	}
	@Override
	public String createGoodsbank(GoodsbankInfoForm goodsbankInfoForm) {
		// TODO Auto-generated method stub
		return cooConGoodsbankManager.createGoodsbank(goodsbankInfoForm);
	}
	@Override
	public String modifyGoodsbankDetails(GoodsbankInfoForm goodsbankInfoForm) {
		// TODO Auto-generated method stub
		return cooConGoodsbankManager.modifyGoodsbankDetails(goodsbankInfoForm);
	}
	@Override
	public String delGoodsbankDetails(GoodsbankInfoForm goodsbankInfoForm) {
		// TODO Auto-generated method stub
		return cooConGoodsbankManager.delGoodsbankDetails(goodsbankInfoForm);
	}
	@Override
	public List<CooconChangeInfoFormVO> listcooconChangeInfo(CooconChangeInfoFormVO cooconChangeInfoFormVO) {
		return cooConGoodsbankManager.listcooconChangeInfo(cooconChangeInfoFormVO);
	}
	@Override
	public int listcooconChangeInfoCount(CooconChangeInfoFormVO cooconChangeInfoFormVO) {
		return cooConGoodsbankManager.listcooconChangeInfoCount(cooconChangeInfoFormVO);
	}
	@Override
	public CooconChangeInfoVO pastChangeData(CooconChangeInfoFormVO cooconChangeInfoFormVO){
		return cooConGoodsbankManager.pastChangeData(cooconChangeInfoFormVO);
	}
	@Override
	public CooconChangeInfoVO currentChangeData(CooconChangeInfoFormVO cooconChangeInfoFormVO){
		return cooConGoodsbankManager.currentChangeData(cooconChangeInfoFormVO);
	}
	@Override
	public CooconGoodsBankInfoVO setGoodsbankinfo(CooconGoodsBankInfoForm cooconGoodsBankInfoForm){
		return cooConGoodsbankManager.setGoodsbankinfo(cooconGoodsBankInfoForm);
	}	

	
}