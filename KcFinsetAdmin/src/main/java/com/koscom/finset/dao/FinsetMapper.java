package com.koscom.finset.dao;

import java.util.LinkedList;
import java.util.List;

import com.koscom.domain.GoodsInfo;
import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finset.model.FinsetDenyVO;
import com.koscom.finset.model.FinsetForm;
import com.koscom.finset.model.FinsetVO;
import com.koscom.prepare.model.PrepareForm;

public interface FinsetMapper {
	void insertTxFcReceive(FinsetVO finsetVO);
	void deleteTxFcReceive(FinsetVO finsetVO);
	double getMue(GoodsInfo goodsInfo);
	void insertFinsetDeny(FinsetDenyVO finsetDenyVO);
	List<FinsetVO> listDenyDetail(FinsetVO finsetVO);
	FinsetVO getFinsetGoodsInfo(FinsetVO finsetVO);
	LinkedList<FinsetVO> listFinsetGoodsInfo(FinsetForm finsetForm);
	int listFinsetGoodsCount(FinsetForm finsetForm);
	
	/**
	 * 상품조회결과 조회
	 * @param
	 * @return String
	 */
	List<TxFcReceiveVO> listSearchGoods(FinsetForm finsetForm);
	/**
	 * 조회중 건수
	 * @param
	 * @return String
	 */
	int countLoading(FinsetVO vo);

	/**
	 * 상품조회결과 조회 건수
	 * @param 
	 * @return String
	 */
	int listSearchGoodsCount(FinsetForm finsetForm);
}