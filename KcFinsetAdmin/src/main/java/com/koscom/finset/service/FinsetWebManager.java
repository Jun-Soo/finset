package com.koscom.finset.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.koscom.domain.FinsetInfo;
import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finset.model.FinsetDenyVO;
import com.koscom.finset.model.FinsetForm;
import com.koscom.finset.model.FinsetVO;
import com.koscom.goods.model.GoodsVO;

public interface FinsetWebManager {
//	double getAmtLoan1stCalForYear(double monthIncome, double mueScore);
//	double getAmtPmt(double applyRatio, int applyTerm, double amtLoan1stCalForYear);
//	double getRtoDti(int amtHCPFY, int amtEtcIFY, double amtPmt, double amtYearIncome);
//	double getRtoLoan(String cd_apply_comp, String cd_goods, int creditGrade);
//	double getAmtYearIncomByHealthInsure(double amtHealthInsure);
//	double getAmtLimitLoanMin(String cd_apply_comp, String cd_goods);
//	void insertFinsetGoods(FinsetVO finsetVO);
//	double getMue(MueVO mueVO);
//	FinsetVO getFinsetGoodsInfo(FinsetVO finsetVO);
	List<FinsetVO> listFinsetGoodsInfo(FinsetForm finsetForm);
	int listFinsetGoodsCount(FinsetForm finsetForm);
	
//	LinkedHashMap<GoodsVO, List<FinsetInfo>> listGoodsInfoForFinset(GoodsForm goodsForm);
	LinkedHashMap<FinsetVO, List<FinsetInfo>> listGoodsInfoForFinset(TxFcReceiveVO txFcReceiveVO);
	
	void insertFinsetDeny(FinsetDenyVO finsetDenyVO); //namik ADD 2017.07.25

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

