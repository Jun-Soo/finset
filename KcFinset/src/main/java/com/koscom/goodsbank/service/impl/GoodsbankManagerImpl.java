package com.koscom.goodsbank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.GoodsbankInfo;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goodsbank.dao.GoodsbankMapper;
import com.koscom.goodsbank.model.GoodsbankForm;
import com.koscom.goodsbank.service.GoodsbankManager;

@Service("goodsbankManager")
public class GoodsbankManagerImpl implements GoodsbankManager {
	@Autowired
	private GoodsbankMapper goodsbankMapper;
	
	@Override
	public List<GoodsbankInfo> listGoodsNoAllianceCredit(GoodsbankForm goodsbankForm) {
		return goodsbankMapper.listGoodsNoAllianceCredit(goodsbankForm);
	}
	
	@Override
	public int listGoodsNoAllianceCreditCount(GoodsbankForm goodsbankForm) {
		return goodsbankMapper.listGoodsNoAllianceCreditCount(goodsbankForm);
	}
	
	@Override
	public List<GoodsVO> listGoodsNoAllianceBiz(GoodsbankForm goodsbankForm) {
		return goodsbankMapper.listGoodsNoAllianceBiz(goodsbankForm);
	}
	
	@Override
	public int listGoodsNoAllianceBizCount(GoodsbankForm goodsbankForm) {
		return goodsbankMapper.listGoodsNoAllianceBizCount(goodsbankForm);
	}
	
	@Override
	public List<GoodsVO> listGoodsNoAllianceHouse(GoodsbankForm goodsbankForm) {
		return goodsbankMapper.listGoodsNoAllianceHouse(goodsbankForm);
	}
	
	@Override
	public int listGoodsNoAllianceHouseCount(GoodsbankForm goodsbankForm) {
		return goodsbankMapper.listGoodsNoAllianceHouseCount(goodsbankForm);
	}
}
