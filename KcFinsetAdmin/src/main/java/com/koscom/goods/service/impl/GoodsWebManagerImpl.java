package com.koscom.goods.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.FinsetInfo;
import com.koscom.domain.GoodsInfo;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.finset.model.FinsetDenyVO;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.model.MueVO;
import com.koscom.goods.model.RtoCommissionVO;
import com.koscom.goods.service.GoodsManager;
import com.koscom.goods.service.GoodsWebManager;
import com.koscom.util.ReturnClass;

@Service("goodsWebManager")
public class GoodsWebManagerImpl implements GoodsWebManager{
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsWebManagerImpl.class);
	
	@Autowired
	GoodsManager goodsManager;

	@Override
	public GoodsVO getGoodsInfo(GoodsInfo goodsInfo) {
		return goodsManager.getGoodsInfo(goodsInfo);
	}
	
	@Override
	public List<GoodsVO> listCodeGoods(){
		return goodsManager.listCodeGoods();
	}
	
	@Override
	public GoodsVO getGoodsInfo(String cd_goods) {
		return goodsManager.getGoodsInfo(cd_goods);
	}

	@Override
	public String getGoodsDetail(String cd_goods, String type) {
		return goodsManager.getGoodsDetail(cd_goods, type);
	}
	
	@Override
	public List<GoodsVO> listGoodsOpenBYType(String id_agency, String cd_goods_type) {
		return goodsManager.listGoodsOpenBYType(id_agency, cd_goods_type);
	}
	
	@Override
	public List<GoodsVO> listGoodsOpenBYComp(String id_agency, String cd_fc) {
		return goodsManager.listGoodsOpenBYComp(id_agency, cd_fc);
	}
	
	@Override
	public List<GoodsVO> listGoodsAgency(String id_agency){
		return goodsManager.listGoodsAgency(id_agency);
	}

	@Override
	public List<GoodsVO> listGoodsInfoBYType(String cd_goods_type) {
		return goodsManager.listGoodsInfoBYType(cd_goods_type);
	}

	@Override
	public GoodsVO getGoodsRtoInfo(String cd_fc, String cd_goods) {
		return goodsManager.getGoodsRtoInfo(cd_fc, cd_goods);
	}
	@Override
	public List<GoodsVO> listYnReApply(GoodsVO goodsVO) {
		return goodsManager.listYnReApply(goodsVO);
	}
	@Override
	public GoodsVO getYnReApply(GoodsVO goodsVO) {
		return goodsManager.getYnReApply(goodsVO);
	}

	@Override
	public List<GoodsVO> listGoodsInfo(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.listGoodsInfo(goodsForm);
	}

	@Override
	public List<GoodsVO> listGoods(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.listGoods(goodsForm);
	}

	@Override
	public ReturnClass clearCacheGoods() {
		// TODO Auto-generated method stub
		return goodsManager.clearCacheGoods();
	}

	@Override
	public int listGoodsCount(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.listGoodsCount(goodsForm) ;
	}

	@Override
	public ReturnClass modifyGoodsDetails(GoodsVO goodsVO) {
		// TODO Auto-generated method stub
		return goodsManager.modifyGoodsDetails(goodsVO);
	}

	@Override
	public ReturnClass modifyGoodsTransmit(GoodsVO goodsVO) {
		// TODO Auto-generated method stub
		return goodsManager.modifyGoodsTransmit(goodsVO);
	}

	@Override
	public List<GoodsVO> listGoodsInfoBYComp(String cd_fc) {
		// TODO Auto-generated method stub
		return goodsManager.listGoodsInfoBYComp(cd_fc);
	}

	@Override
	public ReturnClass createGoods(GoodsVO goodsVO) {
		// TODO Auto-generated method stub
		return goodsManager.createGoods(goodsVO);
	}

	@Override
	public int getGoodsCnt(String cd_goods) {
		// TODO Auto-generated method stub
		return goodsManager.getGoodsCnt(cd_goods);
	}

	@Override
	public List<GoodsVO> listAgencyGoods(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.listAgencyGoods(goodsForm);
	}

	@Override
	public ReturnClass procAgencyOpen(GoodsVO goodsVO) {
		// TODO Auto-generated method stub
		return goodsManager.procAgencyOpen(goodsVO);
	}

	@Override
	public ReturnClass procGoodsOpen(GoodsVO goodsVO) {
		// TODO Auto-generated method stub
		return goodsManager.procGoodsOpen(goodsVO);
	}

	@Override
	public void createMue(MueVO mueVO) {
		// TODO Auto-generated method stub
		goodsManager.createMue(mueVO);
	}

	@Override
	public void delMue(MueVO mueVO) {
		// TODO Auto-generated method stub
		goodsManager.delMue(mueVO);
	}

	@Override
	public ReturnClass delGoodsRto(GoodsVO goodsRtoVO) {
		// TODO Auto-generated method stub
		return goodsManager.delGoodsRto(goodsRtoVO);
	}

	@Override
	public ReturnClass createGoodsRto(GoodsVO vo) {
		// TODO Auto-generated method stub
		return goodsManager.createGoodsRto(vo);
	}

	@Override
	public GoodsVO getGoodsInfo(String cd_fc, String cd_goods) {
		// TODO Auto-generated method stub
		return goodsManager.getGoodsInfo(cd_fc, cd_goods);
	}

	@Override
	public List<MueVO> listGoodsMue(GoodsInfo goodsInfo) {
		// TODO Auto-generated method stub
		return goodsManager.listGoodsMue(goodsInfo);
	}

	@Override
	public List<GoodsForm> ChangeSelectBoxLMS(GoodsForm goodsForm) throws Exception {
		// TODO Auto-generated method stub
		return goodsManager.ChangeSelectBoxLMS(goodsForm);
	}

	@Override
	public List<RtoCommissionVO> listGoodsRtoCommission(GoodsInfo goodsInfo) {
		// TODO Auto-generated method stub
		return goodsManager.listGoodsRtoCommission(goodsInfo);
	}

	@Override
	public void createRtoCommission(RtoCommissionVO rtoCommissionVO) {
		// TODO Auto-generated method stub
		goodsManager.createRtoCommission(rtoCommissionVO);
	}

	@Override
	public void deleteRtoCommission(RtoCommissionVO rtoCommissionVO) {
		// TODO Auto-generated method stub
		goodsManager.deleteRtoCommission(rtoCommissionVO);
	}

	@Override
	public ReturnClass deleteGoods(GoodsVO goodsVO) {
		// TODO Auto-generated method stub
		return goodsManager.deleteGoods(goodsVO);
	}

	@Override
	public LinkedHashMap<GoodsVO, List<FinsetInfo>> listGoodsInfoForFinset(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.listGoodsInfoForFinset(goodsForm);
	}

	@Override
	public List<GoodsVO> listGoodsInfoForCfs(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.listGoodsInfoForCfs(goodsForm);
	}

	@Override
	public String checkAge(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkAge(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkAmtYearIncome(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkAmtYearIncome(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkGoodsUseYn(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkGoodsUseYn(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkAmtApply(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkAmtApply(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkAmtYearSale(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkAmtYearSale(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkCdEmployType(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkCdEmployType(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkCdHouseType(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkCdHouseType(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkCdLiveType(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkCdLiveType(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkCdLoanUse(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkCdLoanUse(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkCdTypeIncome(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkCdTypeIncome(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkDayDelay12month(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkDayDelay12month(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkDayDelay6month(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkDayDelay6month(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkDtiGrade(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkDtiGrade(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkGradeDti(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkGradeDti(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkGradeKcb(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkGradeKcb(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkGradeLtv(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkGradeLtv(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkGradeNice(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkGradeNice(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkNoJobYear(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkNoJobYear(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkNoMonthApply(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkNoMonthApply(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkYmdStartComp(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list,
			String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkYmdStartComp(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkYn4insu(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc,
			FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkYn4insu(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkYnBadCredit(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkYnBadCredit(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkYnDelayCurrent(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkYnDelayCurrent(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkYnProofIncome(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkYnProofIncome(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkCd_sex(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkCd_sex(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkCash_service(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkCash_service(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkCd_owncar_type(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkCd_owncar_type(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkCd_car_type(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkCd_car_type(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkAmt_carprice(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkAmt_carprice(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String checkNo_caryear(TxFcTransmitVO txFcTransmitVO, GoodsVO goodsVO, List<FinsetInfo> list, String filterDesc, FinsetDenyVO finsetDenyVO) {
		// TODO Auto-generated method stub
		return goodsManager.checkNo_caryear(txFcTransmitVO, goodsVO, list, filterDesc, finsetDenyVO);
	}

	@Override
	public String getChkAgeLoan(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkAgeLoan(goodsForm);
	}

	@Override
	public String getChkAmtApply(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkAmtApply(goodsForm);
	}

	@Override
	public String getChkNoMonthApply(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkNoMonthApply(goodsForm);
	}

	@Override
	public String getChkCdTypeIncome(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkCdTypeIncome(goodsForm);
	}

	@Override
	public String getChkCdEmployType(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkCdEmployType(goodsForm);
	}

	@Override
	public String getChkCdLoanUse(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkCdLoanUse(goodsForm);
	}

	@Override
	public String getChkAmtYearIncome(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkAmtYearIncome(goodsForm);
	}

	@Override
	public String getChkAmtYearSale(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkAmtYearSale(goodsForm);
	}

	@Override
	public String getChkYmdStartComp(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkYmdStartComp(goodsForm);
	}

	@Override
	public String getChkNoJobYear(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkNoJobYear(goodsForm);
	}

	@Override
	public String getChkCdLiveType(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkCdLiveType(goodsForm);
	}

	@Override
	public String getChkCdHouseType(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkCdHouseType(goodsForm);
	}

	@Override
	public String getChkYnProofIncome(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkYnProofIncome(goodsForm);
	}

	@Override
	public String getChkYn4Insu(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkYn4Insu(goodsForm);
	}

	@Override
	public String getChkYnBadCredit(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkYnBadCredit(goodsForm);
	}

	@Override
	public String getChkYnDelayCurrent(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkYnDelayCurrent(goodsForm);
	}

	@Override
	public String getChkDayDelay6Month(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkDayDelay6Month(goodsForm);
	}

	@Override
	public String getChkDayDelay12Month(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkDayDelay12Month(goodsForm);
	}

	@Override
	public String getChkGradeKcb(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkGradeKcb(goodsForm);
	}

	@Override
	public String getChkGradeNice(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkGradeNice(goodsForm);
	}

	@Override
	public String getChkDtiGrade(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getChkDtiGrade(goodsForm);
	}

	@Override
	public GoodsVO getGoodsFavorite(GoodsVO goodsVO) {
		return goodsManager.getGoodsFavorite(goodsVO);
	}

	@Override
	public List<GoodsVO> listGoodsFavoriteAlliance(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.listGoodsFavoriteAlliance(goodsForm);
	}

	@Override
	public int getGoodsFavoriteAllianceCount(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getGoodsFavoriteAllianceCount(goodsForm);
	}
	
	@Override
	public List<GoodsVO> listGoodsFavoriteNoAlliance(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.listGoodsFavoriteNoAlliance(goodsForm);
	}

	@Override
	public int getGoodsFavoriteNoAllianceCount(GoodsForm goodsForm) {
		// TODO Auto-generated method stub
		return goodsManager.getGoodsFavoriteNoAllianceCount(goodsForm);
	}

	@Override
	public GoodsVO getCooconGoodsFavorite(GoodsVO goodsVO) {
		// TODO Auto-generated method stub
		return goodsManager.getCooconGoodsFavorite(goodsVO);
	}

	@Override
	public List<GoodsVO> listGoodsAllianceCredit(GoodsForm goodsForm) {
		return goodsManager.listGoodsAllianceCredit(goodsForm);
	}

	@Override
	public int listGoodsAllianceCreditCount(GoodsForm goodsForm) {
		return goodsManager.listGoodsAllianceCreditCount(goodsForm);
	}

	@Override
	public List<GoodsVO> listGoodsAllianceBiz(GoodsForm goodsForm) {
		return goodsManager.listGoodsAllianceBiz(goodsForm);
	}

	@Override
	public int listGoodsAllianceBizCount(GoodsForm goodsForm) {
		return goodsManager.listGoodsAllianceBizCount(goodsForm);
	}

	@Override
	public List<GoodsVO> listGoodsAllianceHouse(GoodsForm goodsForm) {
		return goodsManager.listGoodsAllianceHouse(goodsForm);
	}

	@Override
	public int listGoodsAllianceHouseCount(GoodsForm goodsForm) {
		return goodsManager.listGoodsAllianceHouseCount(goodsForm);
	}
}