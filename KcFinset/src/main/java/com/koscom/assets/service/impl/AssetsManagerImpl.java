package com.koscom.assets.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.assets.dao.AssetsMapper;
import com.koscom.assets.model.AssetsForm;
import com.koscom.assets.model.AssetsInfoVO;
import com.koscom.assets.service.AssetsManager;
import com.koscom.counsel.model.CounselVO;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Service("assetsManager")
public class AssetsManagerImpl implements AssetsManager {

	private static final Logger logger = LoggerFactory.getLogger(AssetsManagerImpl.class);


	@Autowired
	private AssetsMapper assetsMapper;

	@Override
	public AssetsInfoVO getAssetsMainInfo(AssetsForm assetsForm){
		return assetsMapper.getAssetsMainInfo(assetsForm);
	}

	@Override
	public AssetsInfoVO getAssetsBankDepWdrlInfo(AssetsForm assetsForm){
		return assetsMapper.getAssetsBankDepWdrlInfo(assetsForm);
	}

	@Override
	public List<AssetsInfoVO> listAssetsSharePerson(AssetsForm assetsForm){
		return assetsMapper.listAssetsSharePerson(assetsForm);
	}

	@Override
	public List<AssetsInfoVO> listAssetsBankAccount(AssetsForm assetsForm){
		return assetsMapper.listAssetsBankAccount(assetsForm);
	}
	@Override
	public int listAssetsBankAccountCount(AssetsForm assetsForm){
		return assetsMapper.listAssetsBankAccountCount(assetsForm);
	}

	@Override
	public void updateAssetsSortInfo(AssetsInfoVO assetsInfoVO){

		List<AssetsInfoVO> sortList = assetsInfoVO.getSortList();
		String no_person = assetsInfoVO.getNo_person();
		for(AssetsInfoVO sortVO: sortList) {
			sortVO.setNo_person(no_person);
			assetsMapper.updateAssetsSortInfo(sortVO);
		}
	}

	@Override
	public AssetsInfoVO getAssetsInfo(AssetsForm assetsForm){
		return assetsMapper.getAssetsInfo(assetsForm);
	}

	@Override
	public List<String> listAssetsSearchKeyword(AssetsForm assetsForm){
		return assetsMapper.listAssetsSearchKeyword(assetsForm);
	}

	@Override
	public List<AssetsInfoVO> listAssetsBankActTrns(AssetsForm assetsForm){
		return assetsMapper.listAssetsBankActTrns(assetsForm);
	}
	@Override
	public int listAssetsBankActTrnsCount(AssetsForm assetsForm){
		return assetsMapper.listAssetsBankActTrnsCount(assetsForm);
	}

	@Override
	public List<AssetsInfoVO> listAssetsAccount(AssetsForm assetsForm){
		return assetsMapper.listAssetsAccount(assetsForm);
	}

	@Override
	public AssetsInfoVO getAssetsBankDepWdrlTotalAmt(AssetsForm assetsForm){
		return assetsMapper.getAssetsBankDepWdrlTotalAmt(assetsForm);
	}
	@Override
	public List<AssetsInfoVO> listAssetsBankDepWdrl(AssetsForm assetsForm){
		return assetsMapper.listAssetsBankDepWdrl(assetsForm);
	}
	@Override
	public int listAssetsBankDepWdrlCount(AssetsForm assetsForm){
		return assetsMapper.listAssetsBankDepWdrlCount(assetsForm);
	}

	@Override
	public AssetsInfoVO getAssetsBankDepWdrlDetail(AssetsForm assetsForm){
		return assetsMapper.getAssetsBankDepWdrlDetail(assetsForm);
	}

	@Override
	public AssetsInfoVO getAssetsDetailCsInfo(AssetsForm assetsForm){
		return assetsMapper.getAssetsDetailCsInfo(assetsForm);
	}

	@Override
	public int updateAssetsDetailCsInfo(AssetsInfoVO assetsInfoVO){
		return assetsMapper.updateAssetsDetailCsInfo(assetsInfoVO);
	}


	@Override
	public List<AssetsInfoVO> listAssetsStockMainAccount(AssetsForm assetsForm){
		return assetsMapper.listAssetsStockMainAccount(assetsForm);
	}
	@Override
	public int listAssetsStockMainAccountCount(AssetsForm assetsForm){
		return assetsMapper.listAssetsStockMainAccountCount(assetsForm);
	}

	@Override
	public AssetsInfoVO getAssetsStockInfo(AssetsForm assetsForm){
		return assetsMapper.getAssetsStockInfo(assetsForm);
	}

	@Override
	public AssetsInfoVO getAssetsStockSumInfo(AssetsForm assetsForm){
		return assetsMapper.getAssetsStockSumInfo(assetsForm);
	}

	@Override
	public List<AssetsInfoVO> listAssetsStockDetailCompany(AssetsForm assetsForm){
		return assetsMapper.listAssetsStockDetailCompany(assetsForm);
	}
	@Override
	public List<AssetsInfoVO> listAssetsStockDetailAccount(AssetsForm assetsForm){
		return assetsMapper.listAssetsStockDetailAccount(assetsForm);
	}
	@Override
	public int listAssetsStockDetailAccountCount(AssetsForm assetsForm){
		return assetsMapper.listAssetsStockDetailAccountCount(assetsForm);
	}

	@Override
	public AssetsInfoVO getAssetsStockShrInfo(AssetsForm assetsForm){
		return assetsMapper.getAssetsStockShrInfo(assetsForm);
	}

	@Override
	public AssetsInfoVO getAssetsStockFndInfo(AssetsForm assetsForm){
		return assetsMapper.getAssetsStockFndInfo(assetsForm);
	}


	@Override
	public ReturnClass createAssetsInfo(AssetsInfoVO assetsInfoVO) {
		if(1 != assetsMapper.createAssetsInfo(assetsInfoVO)){
			return new ReturnClass(Constant.FAILED, "등록 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "등록 성공하였습니다.");
	}

	@Override
	public List<AssetsInfoVO> listAssetsEtcMain(String no_person){
		return assetsMapper.listAssetsEtcMain(no_person);
	}

}
