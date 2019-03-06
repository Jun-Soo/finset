package com.koscom.goodsbank.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.GoodsbankInfo;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goodsbank.model.CooconChangeInfoFormVO;
import com.koscom.goodsbank.model.CooconChangeInfoVO;
import com.koscom.goodsbank.model.CooconGoodsBankInfoForm;
import com.koscom.goodsbank.model.CooconGoodsBankInfoVO;
import com.koscom.goodsbank.model.GoodsStatsFormVO;
import com.koscom.goodsbank.model.GoodsStatsVO;
import com.koscom.goodsbank.model.GoodsbankForm;
import com.koscom.goodsbank.model.GoodsbankVO;
import com.koscom.goodsbank.service.GoodsbankManager;
import com.koscom.goodsbank.service.GoodsbankWebManager;
import com.koscom.util.ReturnClass;

@Service("goodsbankWebManager")
public class GoodsbankWebManagerImpl implements GoodsbankWebManager{
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsbankManagerImpl.class);
	
	@Autowired
	GoodsbankManager goodsbankManager;
	
	@Override
	public ReturnClass addLinkCount(GoodsbankVO goodsbankVO) {
		return goodsbankManager.addLinkCount(goodsbankVO);
	}
	@Override
	public List<GoodsbankVO> listGoodsbankInfo(GoodsbankForm goodsbankForm) {
		logger.info("===================goodsbankForm.toString()=LCA==WebManager==================");
		logger.info(goodsbankForm.toString());
		return goodsbankManager.listGoodsbankInfo(goodsbankForm);
	}
	@Override
	public int listGoodsbankCount(GoodsbankForm goodsbankForm) {
		return goodsbankManager.listGoodsbankCount(goodsbankForm) ;
	}
	@Override
	public List<GoodsbankForm> ChangeSelectBoxLMS(GoodsbankForm goodsbankForm) throws Exception {
		return goodsbankManager.ChangeSelectBoxLMS(goodsbankForm);
	}
	@Override
	public List<GoodsbankInfo> listGoodsNoAllianceCredit(GoodsbankForm goodsbankForm) {
		return goodsbankManager.listGoodsNoAllianceCredit(goodsbankForm);
	}
	@Override
	public int listGoodsNoAllianceCreditCount(GoodsbankForm goodsbankForm) {
		return goodsbankManager.listGoodsNoAllianceCreditCount(goodsbankForm);
	}
	@Override
	public GoodsbankVO getGoodsBankFavorite(GoodsbankVO goodsbankVO) {
		return goodsbankManager.getGoodsBankFavorite(goodsbankVO);
	}
	@Override
	public List<GoodsVO> listGoodsNoAllianceBiz(GoodsbankForm goodsbankForm) {
		return goodsbankManager.listGoodsNoAllianceBiz(goodsbankForm);
	}
	@Override
	public int listGoodsNoAllianceBizCount(GoodsbankForm goodsbankForm) {
		return goodsbankManager.listGoodsNoAllianceBizCount(goodsbankForm);
	}
	@Override
	public List<GoodsVO> listGoodsNoAllianceHouse(GoodsbankForm goodsbankForm) {
		return goodsbankManager.listGoodsNoAllianceHouse(goodsbankForm);
	}
	@Override
	public int listGoodsNoAllianceHouseCount(GoodsbankForm goodsbankForm) {
		return goodsbankManager.listGoodsNoAllianceHouseCount(goodsbankForm);
	}
	@Override
	public GoodsbankVO getGoodsbankInfo(String cd_fc, String cd_goods) {
		return goodsbankManager.getGoodsbankInfo(cd_fc, cd_goods);
	}
	
	/** srchou */
	@Override
	public String createGoodsbank(CooconGoodsBankInfoForm cooconGoodsBankInfoForm) {
		return goodsbankManager.createGoodsbank(cooconGoodsBankInfoForm);
	}
	@Override
	public String modifyGoodsbankDetails(CooconGoodsBankInfoForm cooconGoodsBankInfoForm) {
		return goodsbankManager.modifyGoodsbankDetails(cooconGoodsBankInfoForm);
	}
	@Override
	public String delGoodsbankDetails(CooconGoodsBankInfoForm cooconGoodsBankInfoForm) {
		return goodsbankManager.delGoodsbankDetails(cooconGoodsBankInfoForm);
	}
	@Override
	public List<CooconChangeInfoVO> listcooconChangeInfo(CooconChangeInfoFormVO cooconChangeInfoFormVO) {
		return goodsbankManager.listcooconChangeInfo(cooconChangeInfoFormVO);
	}
	@Override
	public int listcooconChangeInfoCount(CooconChangeInfoFormVO cooconChangeInfoFormVO) {
		return goodsbankManager.listcooconChangeInfoCount(cooconChangeInfoFormVO);
	}
	@Override
	public List<GoodsStatsVO> listGoodsStats(GoodsStatsFormVO goodsStatsFormVO){
		return goodsbankManager.listGoodsStats(goodsStatsFormVO);
	}
	@Override
	public int listGoodsStatsCount(GoodsStatsFormVO goodsStatsFormVO){
		return goodsbankManager.listGoodsStatsCount(goodsStatsFormVO);
	}
	@Override
	public CooconChangeInfoVO pastChangeData(CooconChangeInfoFormVO cooconChangeInfoFormVO){
		return goodsbankManager.pastChangeData(cooconChangeInfoFormVO);
	}
	@Override
	public CooconChangeInfoVO currentChangeData(CooconChangeInfoFormVO cooconChangeInfoFormVO){
		return goodsbankManager.currentChangeData(cooconChangeInfoFormVO);
	}
	@Override
	public CooconGoodsBankInfoVO setGoodsbankinfo(CooconGoodsBankInfoForm cooconGoodsBankInfoForm){
		return goodsbankManager.setGoodsbankinfo(cooconGoodsBankInfoForm);
	}
	@Override
	public List<CooconGoodsBankInfoVO> listGoodsbankInfoUse(CooconGoodsBankInfoForm cooconGoodsBankInfoForm){
		return goodsbankManager.listGoodsbankInfoUse(cooconGoodsBankInfoForm);
	}
	@Override
	public int listGoodsbankInfoUseCount(CooconGoodsBankInfoForm cooconGoodsBankInfoForm){
		return goodsbankManager.listGoodsbankInfoUseCount(cooconGoodsBankInfoForm);
	}
	@Override
	public CooconGoodsBankInfoVO setGoodsbankInfoUse(CooconGoodsBankInfoForm cooconGoodsBankInfoForm){
		return goodsbankManager.setGoodsbankInfoUse(cooconGoodsBankInfoForm);
	}

}