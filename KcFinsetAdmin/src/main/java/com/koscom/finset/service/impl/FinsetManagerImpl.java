package com.koscom.finset.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.koscom.goods.model.GoodsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.FinsetInfo;
import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finset.dao.FinsetMapper;
import com.koscom.finset.model.FinsetDenyVO;
import com.koscom.finset.model.FinsetForm;
import com.koscom.finset.model.FinsetVO;
import com.koscom.finset.service.FinsetManager;
import com.koscom.goods.dao.GoodsMapper;
import com.koscom.person.service.PersonManager;
import com.koscom.prepare.service.PrepareManager;

@Service("finsetManager")
public class FinsetManagerImpl implements FinsetManager {
	@Autowired
	private PrepareManager prepareManager;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private PersonManager personManager;
	@Autowired
	private FinsetMapper finsetMapper;
	private static final Logger logger = LoggerFactory.getLogger(FinsetManagerImpl.class);
	@Override
	public LinkedHashMap<FinsetVO, List<FinsetInfo>> listGoodsInfoForFinset(TxFcReceiveVO txFcReceiveVO) {
		String no_person = txFcReceiveVO.getNo_person();// goodsForm.getNo_person();
		LinkedHashMap<FinsetVO, List<FinsetInfo>> linkedMap =  new LinkedHashMap<FinsetVO, List<FinsetInfo>>();
		if(txFcReceiveVO.getListGoods().size() <= 0){	//강제리턴-상품없음
			logger.debug("상품정보 없음");
			return linkedMap;
		}



		List<FinsetInfo> listFinsetInfo = new ArrayList<FinsetInfo>();

		//***

		LinkedList<FinsetVO> getListGoods = txFcReceiveVO.getListGoods();
		String oldCdApplyComp = null;
		String oldCdGoods = null;

		for (int i = 0; i < getListGoods.size(); i++) {
			FinsetVO tempVO = getListGoods.get(i);
			logger.info("상품      : " + tempVO.getCd_goods());
			if(oldCdGoods != null && oldCdApplyComp != null) {
				if(oldCdGoods.equals(tempVO.getCd_goods()) && oldCdApplyComp.equals(tempVO.getCd_fc())) {
					listFinsetInfo.add(tempVO);
				} else {
					FinsetVO makedFinsetVO = new FinsetVO();
					makedFinsetVO.setCd_fc(oldCdApplyComp);
					makedFinsetVO.setCd_goods(oldCdGoods);
					logger.info("linkedHashMap put : " + tempVO.getCd_goods());
					linkedMap.put(makedFinsetVO, listFinsetInfo);
					listFinsetInfo = new ArrayList<FinsetInfo>();
					listFinsetInfo.add(tempVO);
				}
			} else {
				listFinsetInfo.add(tempVO);
			}
			oldCdGoods = tempVO.getCd_goods();
			oldCdApplyComp = tempVO.getCd_fc();

			if(i == (getListGoods.size() -1)) {
				FinsetVO makedFinsetVO = new FinsetVO();
				makedFinsetVO.setCd_fc(oldCdApplyComp);
				makedFinsetVO.setCd_goods(oldCdGoods);
				logger.info("final linkedHashMap put : " + tempVO.getCd_goods());
				linkedMap.put(makedFinsetVO, listFinsetInfo);
			}
		}

		return linkedMap;
	}
	public LinkedList<FinsetVO> listFinsetGoodsInfo(FinsetForm finsetForm){
		return finsetMapper.listFinsetGoodsInfo(finsetForm);
	}
	@Override
	public int listFinsetGoodsCount(FinsetForm finsetForm){
		return finsetMapper.listFinsetGoodsCount(finsetForm);
	}

	@Override
	public void insertFinsetDeny(FinsetDenyVO finsetDenyVO){//namik ADD 2017.07.25
		finsetMapper.insertFinsetDeny(finsetDenyVO);
	}

	@Override
	public void insertTxFcReceive(FinsetVO finsetVO){//namik ADD 2017.07.25
		finsetMapper.insertTxFcReceive(finsetVO);
	}
	@Override
	public void deleteTxFcReceive(FinsetVO finsetVO){//namik ADD 2017.07.25
		finsetMapper.deleteTxFcReceive(finsetVO);
	}

	public List<TxFcReceiveVO> listSearchGoods(FinsetForm finsetForm){
		return finsetMapper.listSearchGoods(finsetForm);
	}

	public int countLoading(FinsetVO vo){
		return finsetMapper.countLoading(vo);
	}
	@Override
	public int listSearchGoodsCount(FinsetForm finsetForm){
		return finsetMapper.listSearchGoodsCount(finsetForm);
	}
	
}
