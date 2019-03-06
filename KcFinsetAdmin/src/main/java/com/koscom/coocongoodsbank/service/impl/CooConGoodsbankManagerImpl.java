package com.koscom.coocongoodsbank.service.impl;

import com.koscom.coocongoodsbank.service.CooConGoodsbankManager;
import com.koscom.env.service.CodeManager;
import com.koscom.coocongoodsbank.dao.CooConGoodsbankMapper;
import com.koscom.coocongoodsbank.model.*;
import com.koscom.loan.service.LoanManager;
import com.koscom.person.service.PersonManager;
import com.koscom.worker.service.WorkerManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cooConGoodsbankManager")
public class CooConGoodsbankManagerImpl implements CooConGoodsbankManager {

	private static final Logger logger = LoggerFactory.getLogger(CooConGoodsbankManagerImpl.class);
	
	@Autowired
	private CooConGoodsbankMapper cooConGoodsbankMapper;
	
	@Autowired
	private CodeManager codeManager;
	
	@Autowired
	private WorkerManager workerManager;

	@Autowired
	PersonManager personManager;
	
	@Autowired
	LoanManager loanManager;
	
	@Override
	public List<GoodsStatsVO> listGoodsStats(GoodsStatsFormVO goodsStatsFormVO){
		return cooConGoodsbankMapper.listGoodsStats(goodsStatsFormVO);
	}
	@Override
	public int listGoodsStatsCount(GoodsStatsFormVO goodsStatsFormVO){
		return cooConGoodsbankMapper.listGoodsStatsCount(goodsStatsFormVO);
	}
	
	@Override
	public String createGoodsbank(GoodsbankInfoForm goodsbankInfoForm) {
		int cnt = 0;
		int dulpcnt = 0;
		String result = null;
		
		dulpcnt = cooConGoodsbankMapper.getGoodsbankCnt(goodsbankInfoForm.getCd_non_goods());
		
		if(dulpcnt == 0 ){
			cnt = cooConGoodsbankMapper.createGoodsbank(goodsbankInfoForm);
			if( cnt > 0){
				cooConGoodsbankMapper.updateYnuse(goodsbankInfoForm);
				cooConGoodsbankMapper.updateCdnongoods(goodsbankInfoForm);
				
				result = "등록에 성공하였습니다.";
			}else{
				result = "등록에 실패하였습니다.";
			}
		}else{
			result = "이미 등록된 상품코드입니다.";
		}
		return result;
	}
	
	@Override
	public String modifyGoodsbankDetails(GoodsbankInfoForm goodsbankInfoForm) {
		
		int cnt = 0;
		String result = null;
		
		cnt = cooConGoodsbankMapper.modifyGoodsbankDetails(goodsbankInfoForm);
		
		if( cnt > 0 ){
			cooConGoodsbankMapper.updateYnuse(goodsbankInfoForm);
			result = "수정에 성공하였습니다.";
		}else{
			result = "수정에 실패하였습니다.";
		}
		
		return result;
		
	}
	
	@Override
	public String delGoodsbankDetails(GoodsbankInfoForm goodsbankInfoForm) {
		int cnt = 0;
		String result = null;
		
		cnt = cooConGoodsbankMapper.delGoodsbankDetails(goodsbankInfoForm);
		if( cnt > 0){
			goodsbankInfoForm.setYn_use("N");
			cooConGoodsbankMapper.updateYnuse(goodsbankInfoForm);
			goodsbankInfoForm.setCd_non_goods("");
			cooConGoodsbankMapper.updateCdnongoods(goodsbankInfoForm);
			result = "삭제에 성공하였습니다.";
		}else{
			result = "삭제에 실패하였습니다.";
		}
		return result;
	}
		
	
	@Override
	public List<CooconChangeInfoFormVO> listcooconChangeInfo(CooconChangeInfoFormVO cooconChangeInfoFormVO) {
		return cooConGoodsbankMapper.listcooconChangeInfo(cooconChangeInfoFormVO);
	}
	@Override
	public int listcooconChangeInfoCount(CooconChangeInfoFormVO cooconChangeInfoFormVO){
		return cooConGoodsbankMapper.listcooconChangeInfoCount(cooconChangeInfoFormVO);
	}
	@Override
	public CooconChangeInfoVO pastChangeData(CooconChangeInfoFormVO cooconChangeInfoFormVO){
		return cooConGoodsbankMapper.pastChangeData(cooconChangeInfoFormVO);
	}
	
	@Override
	public CooconChangeInfoVO currentChangeData(CooconChangeInfoFormVO cooconChangeInfoFormVO){
		return cooConGoodsbankMapper.currentChangeData(cooconChangeInfoFormVO);
	}
	
	@Override
	public CooconGoodsBankInfoVO setGoodsbankinfo(CooconGoodsBankInfoForm cooconGoodsBankInfoForm){
		int result = 0;
		
		result = cooConGoodsbankMapper.chkcdNongoods(cooconGoodsBankInfoForm);
		
		if(result != 0){
			return cooConGoodsbankMapper.getGoodsbankinfo(cooconGoodsBankInfoForm);
		}else{
			return cooConGoodsbankMapper.getCoocongoodsinfo(cooconGoodsBankInfoForm);
		}
	}

}