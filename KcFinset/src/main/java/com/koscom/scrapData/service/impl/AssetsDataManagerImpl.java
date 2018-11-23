package com.koscom.scrapData.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.scrapData.dao.AssetsDataMapper;
import com.koscom.scrapData.model.AssetsDataForm;
import com.koscom.scrapData.model.AssetsInfoDataVO;
import com.koscom.scrapData.service.AssetsDataManager;

@Service("assetDataManager")
public class AssetsDataManagerImpl implements AssetsDataManager {

	private static final Logger logger = LoggerFactory.getLogger(AssetsDataManagerImpl.class);

	@Autowired
	private AssetsDataMapper assetsDataMapper;

	public void mergeAssetsInfoScrData(AssetsDataForm assetsDataForm) throws Exception {

		//금융사계좌정보T 스크래핑 데이터 조회
		List<AssetsInfoDataVO> scrBankApiAnInfoList = assetsDataMapper.listScrBankApiAnInfo(assetsDataForm);

		//자산관리T insert
		try{
			if(scrBankApiAnInfoList != null){
				for(AssetsInfoDataVO list : scrBankApiAnInfoList){

					AssetsInfoDataVO assetsInfoDataVO = new AssetsInfoDataVO();
					assetsInfoDataVO.setNo_person(assetsDataForm.getNo_person()); //회원번호

					//자산분류코드
					assetsInfoDataVO.setCd_assets_class("00");
					if("02".equals(list.getType_an())){ //예적금계좌

						if(list.getNm_an().indexOf("예금") >= 0){
							assetsInfoDataVO.setCd_detail_class("02");
						} else if(list.getNm_an().indexOf("적금") >= 0){
							assetsInfoDataVO.setCd_detail_class("03");
						} else if(list.getNm_an().indexOf("청약") >= 0){
							assetsInfoDataVO.setCd_detail_class("06");
						} else if(list.getNm_an().indexOf("퇴직") >= 0 || list.getNm_an().indexOf("IRP") >= 0){
							assetsInfoDataVO.setCd_detail_class("07");
						}

					}else{
						assetsInfoDataVO.setCd_detail_class(list.getType_an());
					}
					assetsInfoDataVO.setCd_fc(list.getCd_fc()); //금융사코드
					assetsInfoDataVO.setNo_account(list.getAn()); //계좌번호
					assetsInfoDataVO.setNm_account(list.getNm_an()); //계좌명
					assetsInfoDataVO.setDt_open(list.getDt_new()); //개설일자
					assetsInfoDataVO.setDt_expire(list.getDt_end()); //만기일자
					assetsInfoDataVO.setAmt_balance(list.getCurrent_balance()); //잔액(금융사계좌정보 - 현재잔액)
					assetsInfoDataVO.setInterest(list.getInterest_rate()); //금리
					assetsInfoDataVO.setYn_use(("N".equals(list.getYn_delete())?"Y":"N")); //사용여부
					assetsInfoDataVO.setYn_person_regist("N"); //사용자등록여부
					assetsInfoDataVO.setId_frt(assetsDataForm.getNo_person()); //최초입력아이디
					assetsInfoDataVO.setId_lst(assetsDataForm.getNo_person()); //최종수정아이디

					assetsDataMapper.mergeAssetsInfo(assetsInfoDataVO);
				}
			}
		} catch (Exception e) {
			logger.error("자산관리 스크래핑 데이터를 DB에 집어넣는 도중 에러가 발생했습니다.");
			e.printStackTrace();
			throw new Exception("자산관리 스크래핑 데이터를 DB에 집어넣는 도중 에러가 발생했습니다.");
		}

	}
}
