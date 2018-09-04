package com.koscom.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.goods.dao.GoodsMapper;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.service.GoodsManager;

@Service("goodsManager")
public class GoodsManagerImpl implements GoodsManager {
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public List<GoodsVO> listGoodsAllianceCredit(GoodsForm goodsForm) {
		return goodsMapper.listGoodsAllianceCredit(goodsForm);
	}
	
	@Override
	public int listGoodsAllianceCreditCount(GoodsForm goodsForm) {
		return goodsMapper.listGoodsAllianceCreditCount(goodsForm);
	}
	
	@Override
	public List<GoodsVO> listGoodsAllianceBiz(GoodsForm goodsForm) {
		return goodsMapper.listGoodsAllianceBiz(goodsForm);
	}

	@Override
	public int listGoodsAllianceBizCount(GoodsForm goodsForm) {
		return goodsMapper.listGoodsAllianceBizCount(goodsForm);
	}
}
