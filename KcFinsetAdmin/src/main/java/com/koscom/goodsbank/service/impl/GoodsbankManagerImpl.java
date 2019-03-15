package com.koscom.goodsbank.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.koscom.domain.GoodsbankInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goodsbank.dao.GoodsbankMapper;
import com.koscom.goodsbank.model.CooconChangeInfoFormVO;
import com.koscom.goodsbank.model.CooconChangeInfoVO;
import com.koscom.goodsbank.model.CooconGoodsBankInfoForm;
import com.koscom.goodsbank.model.CooconGoodsBankInfoVO;
import com.koscom.goodsbank.model.GoodsStatsFormVO;
import com.koscom.goodsbank.model.GoodsStatsVO;
import com.koscom.goodsbank.model.GoodsbankForm;
import com.koscom.goodsbank.model.GoodsbankVO;
import com.koscom.goodsbank.service.GoodsbankManager;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.worker.service.WorkerManager;

@Service("goodsbankManager")
public class GoodsbankManagerImpl implements GoodsbankManager {

	private static final Logger logger = LoggerFactory.getLogger(GoodsbankManagerImpl.class);

	@Autowired
	private GoodsbankMapper goodsbankMapper;

	@Autowired
	private CodeManager codeManager;

	@Autowired
	private WorkerManager workerManager;

	@Autowired
	PersonManager personManager;

	@Override
	public ReturnClass addLinkCount(GoodsbankVO goodsbankVO) {
		goodsbankMapper.addLinkCount(goodsbankVO);
		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");
	}
	
	@Override
	public List<GoodsbankVO> listGoodsbankInfo(GoodsbankForm goodsbankForm) {
		logger.info("===================goodsbankForm.toString()===================");
		logger.info(goodsbankForm.toString());
		return goodsbankMapper.listGoodsbankInfo(goodsbankForm);
	}
	@Override
	public int listGoodsbankCount(GoodsbankForm goodsbankForm){
		return goodsbankMapper.listGoodsbankCount(goodsbankForm);
	}
	@Override
	public GoodsbankVO getGoodsbankInfo(String cd_fc, String cd_goods) {
		GoodsbankInfo goodsbankInfo = new GoodsbankInfo();
		goodsbankInfo.setCd_fc(cd_fc);
		goodsbankInfo.setCd_goods(cd_goods);
		return goodsbankMapper.getGoodsbankInfo(goodsbankInfo);
	}
	
	@Override
	public List<GoodsbankForm> ChangeSelectBoxLMS(GoodsbankForm goodsbankForm) throws Exception {
		List<GoodsbankForm> res = goodsbankMapper.ChangeSelectBoxLMS(goodsbankForm);
		logger.info("중분류 소분류 size  : " + res.size());
		return res;
	}
	@Override
	public List<GoodsbankInfo> listGoodsNoAllianceCredit(GoodsbankForm goodsbankForm) {
		return goodsbankMapper.listGoodsNoAllianceCredit(goodsbankForm);
	}
	@Override
	public int listGoodsNoAllianceCreditCount(GoodsbankForm goodsbankForm) {
		return goodsbankMapper.listGoodsNoAllianceCreditCount(goodsbankForm);
	}
	@Override
	public GoodsbankVO getGoodsBankFavorite(GoodsbankVO goodsbankVO) {
		return goodsbankMapper.getGoodsBankFavorite(goodsbankVO);
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




	/** srchou */
	@Override
	public String createGoodsbank(CooconGoodsBankInfoForm cooconGoodsBankInfoForm) {
		String result = null;
		int dulcount = 0;

		//금융사코드7 + 제휴구분1(제휴:1, 비제휴:0) + 대분류2(신용:01,담보:02) + 중분류2 + SEQ3
		String  newCdNongoods = goodsbankMapper.getGoodsbankCdNonGoodsSeq(cooconGoodsBankInfoForm);
		logger.error("newCdNongoods========================="+newCdNongoods);

		cooconGoodsBankInfoForm.setCd_non_goods(newCdNongoods);
		logger.error("cooconGoodsBankInfoForm.getCd_non_goods()========================="+cooconGoodsBankInfoForm.getCd_non_goods());

		//GOODSBANK_INFO에서 cd_non_goods값이 같은게 있는지 찾는다
		dulcount = goodsbankMapper.getGoodsbankCnt(cooconGoodsBankInfoForm.getCd_non_goods());
		if(dulcount == 0) {
			try {
				goodsbankMapper.createGoodsbank(cooconGoodsBankInfoForm);

				result = "등록에 성공하였습니다.";
			} catch (Exception e) {
				logger.error(e.getMessage());
				result = "등록에 실패했습니다.";
			}
		}else{
			result = "이미 추가된 상품코드입니다.";
		}
		return result;
	}
	@Override
	public String modifyGoodsbankDetails(CooconGoodsBankInfoForm cooconGoodsBankInfoForm) {
		String result = null;
		int resultVal = 0;

		resultVal = goodsbankMapper.modifyGoodsbankDetails(cooconGoodsBankInfoForm);

		if(StringUtils.isEmpty(cooconGoodsBankInfoForm.getCd_coocon_goods())
			|| StringUtils.isEmpty(cooconGoodsBankInfoForm.getNm_coocon_goods())){

			CooconGoodsBankInfoVO cooconGoodsInfo = goodsbankMapper.getCooconGoodsInfoByCdNonGoods(cooconGoodsBankInfoForm.getCd_non_goods());

			if(cooconGoodsInfo != null) {
				cooconGoodsBankInfoForm.setCd_coocon_goods(cooconGoodsInfo.getCd_coocon_goods());
				cooconGoodsBankInfoForm.setNm_coocon_goods(cooconGoodsInfo.getNm_coocon_goods());

				//coocon_goods_change_info의 등록여부 update
				goodsbankMapper.updateYnuse(cooconGoodsBankInfoForm);
			}
		}

		if( resultVal > 0 ){
			result = "수정에 성공하였습니다.";
		}else{
			result = "수정에 실패하였습니다.";
		}
		return result;
	}
	@Override
	public String delGoodsbankDetails(CooconGoodsBankInfoForm cooconGoodsBankInfoForm) {
		String result = null;
		int resultVal = 0;
		CooconGoodsBankInfoVO cooconGoodsInfo = new CooconGoodsBankInfoVO();

		//삭제 - goodsbank_info의 판매여부 N으로 변경
		cooconGoodsBankInfoForm.setYn_use("N");
		resultVal = goodsbankMapper.delGoodsbankDetails(cooconGoodsBankInfoForm);

		if( resultVal > 0){
			if(StringUtils.isEmpty(cooconGoodsBankInfoForm.getCd_coocon_goods())
					|| StringUtils.isEmpty(cooconGoodsBankInfoForm.getNm_coocon_goods())){

				//coocon_goodsbank_info 테이블 조회
				cooconGoodsInfo = goodsbankMapper.getCooconGoodsInfoByCdNonGoods(cooconGoodsBankInfoForm.getCd_non_goods());

				if(cooconGoodsInfo != null) {
					cooconGoodsBankInfoForm.setCd_coocon_goods(cooconGoodsInfo.getCd_coocon_goods());
					cooconGoodsBankInfoForm.setNm_coocon_goods(cooconGoodsInfo.getNm_coocon_goods());

					//coocon_goods_change_info의 등록여부 update
					goodsbankMapper.updateYnuse(cooconGoodsBankInfoForm);
				}
			}
//			cooconGoodsBankInfoForm.setCd_non_goods("");
//			goodsbankMapper.updateCdnongoods(cooconGoodsBankInfoForm);
			result = "삭제에 성공하였습니다.";
		}else{
			result = "삭제에 실패하였습니다.";
		}

		return result;
	}
	@Override
	public List<CooconChangeInfoVO> listcooconChangeInfo(CooconChangeInfoFormVO cooconChangeInfoFormVO) {
		return goodsbankMapper.listcooconChangeInfo(cooconChangeInfoFormVO);
	}
	@Override
	public int listcooconChangeInfoCount(CooconChangeInfoFormVO cooconChangeInfoFormVO){
		return goodsbankMapper.listcooconChangeInfoCount(cooconChangeInfoFormVO);
	}
	@Override
	public List<GoodsStatsVO> listGoodsStats(GoodsStatsFormVO goodsStatsFormVO){
		return goodsbankMapper.listGoodsStats(goodsStatsFormVO);
	}
	@Override
	public int listGoodsStatsCount(GoodsStatsFormVO goodsStatsFormVO){
		return goodsbankMapper.listGoodsStatsCount(goodsStatsFormVO);
	}
	@Override
	public CooconChangeInfoVO pastChangeData(CooconChangeInfoFormVO cooconChangeInfoFormVO){
		return goodsbankMapper.pastChangeData(cooconChangeInfoFormVO);
	}
	@Override
	public CooconChangeInfoVO currentChangeData(CooconChangeInfoFormVO cooconChangeInfoFormVO){
		return goodsbankMapper.currentChangeData(cooconChangeInfoFormVO);
	}
	@Override
	public CooconGoodsBankInfoVO setGoodsbankinfo(CooconGoodsBankInfoForm cooconGoodsBankInfoForm){

		if(StringUtils.isEmpty(cooconGoodsBankInfoForm.getCd_non_goods())){
			//goodsbank_info에 cd_non_goods값이 있는지 체크
			cooconGoodsBankInfoForm.setCd_non_goods(goodsbankMapper.chkcdNongoods(cooconGoodsBankInfoForm));
		}

		if(StringUtils.isEmpty(cooconGoodsBankInfoForm.getCd_non_goods())){
			return goodsbankMapper.getCoocongoodsDetailInfo(cooconGoodsBankInfoForm);
		}else{
			return goodsbankMapper.getGoodsbankDetailInfo(cooconGoodsBankInfoForm);
		}
	}
	@Override
	public List<CooconGoodsBankInfoVO> listGoodsbankInfoUse(CooconGoodsBankInfoForm cooconGoodsBankInfoForm){
		return goodsbankMapper.listGoodsbankInfoUse(cooconGoodsBankInfoForm);
	}
	@Override
	public int listGoodsbankInfoUseCount(CooconGoodsBankInfoForm cooconGoodsBankInfoForm){
		return goodsbankMapper.listGoodsbankInfoUseCount(cooconGoodsBankInfoForm);
	}
	@Override
	public CooconGoodsBankInfoVO setGoodsbankInfoUse(CooconGoodsBankInfoForm cooconGoodsBankInfoForm){
		return goodsbankMapper.setGoodsbankInfoUse(cooconGoodsBankInfoForm);
	}
}