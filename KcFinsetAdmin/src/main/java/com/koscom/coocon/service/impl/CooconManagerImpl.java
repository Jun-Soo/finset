package com.koscom.coocon.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.koscom.coocon.dao.CooconMapper;
import com.koscom.coocon.model.CooconAPIinfoFormVO;
import com.koscom.coocon.model.CooconAPIinfoVO;
import com.koscom.coocon.model.CooconChangeVO;
import com.koscom.coocon.model.CooconVO;
import com.koscom.coocon.service.CooconManager;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Transactional
@Service("cooconManager")
public class CooconManagerImpl implements CooconManager {

	private static final Logger logger = LoggerFactory.getLogger(CooconManagerImpl.class);

	@Autowired
	private CooconMapper cooconMapper;

	@Autowired
	private FincorpManager fincorpManager;

	@Override
	public String getCdcoocongoods(String nm_coocon_goods, String cd_org) {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("nm_coocon_goods", nm_coocon_goods);
		map.put("cd_org", cd_org);
		return cooconMapper.getCdcoocongoods(map);
	}

	@Override
	public int setCooconGoods(List<CooconVO> listCooconVO) throws UnsupportedEncodingException {

		String cdNonGoods = "";
//		int seq = 0;
		int chk_goods = 0;
		int chk_change = 0;
		int result = 0;
		int count = 0;
		int goodsbankCnt = 0;

		CooconVO cooconVO = null;

		for(int i=0; i<listCooconVO.size(); i++){

			cooconVO = listCooconVO.get(i);

			//0. PARAM URLDecoding
			cooconVO.decodeData();

			if(!StringUtils.isEmpty(cooconVO.getCd_coocon_goods())
				&& !StringUtils.isEmpty(cooconVO.getNm_coocon_goods())){
				//COOCON_GOODS_INFO의 기존데이터의 cd_non_goods값 조회
				cdNonGoods = cooconMapper.getCooconGoodsInfoCdNongoods(cooconVO);

				//1. API상품정보 insert
				cooconVO.setCd_non_goods(cdNonGoods);
				result = cooconMapper.setCooconGoods(cooconVO);


				if(1 != result)  {
					logger.info("상품 정보 입력에 실패하였습니다.");
				}

				//2. 현 상품별 카운트 체크
				chk_goods = cooconMapper.chkCooconGoods(cooconVO);
				//2. coocon_goods_change_info data 유무 확인
				chk_change = cooconMapper.chkCooconGoodsChangeInfo(cooconVO);

				if( (chk_goods == 1) && (chk_change == 0) ) { //변경정보 INSERT
					cooconMapper.insCooconChangeinfo(cooconVO);
				} else {
					//TODO
					/*
					 * int seq = SELECT seq FROM XXX WHERE CD_COOCON_GOODS = ${cd_coocon_goods} AND COOCON_GOODS_INFO CD_NON_GOODS IS NULL
					 * cooconVO.setSeq(seq);
					 */
	//				seq = cooconMapper.getSeq(cooconVO.getCd_coocon_goods().toString());
	//				cooconVO.setSeq(String.valueOf(seq));

					if( chk_goods > 2) { //쿠콘API MIN(SEQ) DELETE - 2건만 남도록
	//					if( !"0".equals(cooconVO.getSeq())){
							cooconMapper.delCooconGoods(cooconVO);
	//					}
					}


					//COOCON_GOODS_CHANGE_INFO 정보 셋팅
					//변경정보 UPDATE
					cooconMapper.updCooconChangeinfo(cooconVO);

					/* 변경건수 COUNT */
					count = cooconMapper.chkChangeValue(cooconVO);

					//cd_non_goods값으로 GOODSBANK_INFO 데이터 조회
					if(!StringUtils.isEmpty(cdNonGoods)){
						goodsbankCnt = cooconMapper.getCdnongoodsGoodsbankCnt(cdNonGoods);
					}

					if( count > 0 ){ //변경건수 > 0
						cooconVO.setStatus("2");
						if(goodsbankCnt > 0){
							cooconVO.setYn_reg("N");
						}
						cooconMapper.updStatusCooconChangeinfo(cooconVO);
					}else if (count == 0){ //변경건수 zero
						cooconVO.setStatus("4");
						if(goodsbankCnt > 0){
							cooconVO.setYn_reg("Y");
						}
						cooconMapper.updStatusCooconChangeinfo(cooconVO);
					}
				}
			}
		}

		//cooconMapper.delCoocongoodsinfo();
		cooconMapper.delCoocongoodchangeinfo(cooconVO);
		//표시여부 update
		cooconMapper.updYnUseGoodsbankInfo(cooconVO);
		return result;
	}

	@Override
	public List<CooconAPIinfoVO> getCooconAPIinfo() {
		// TODO Auto-generated method stub
		return cooconMapper.getCooconAPIinfo();
	}

	@Override
	public int getSeq(String cd_coocon_goods) {
		// TODO Auto-generated method stub
		int result = 0;
		result = cooconMapper.getSeq(cd_coocon_goods);
		/* API 호출 상품 data */
		return result;
	}

	@Override
	public List<CooconAPIinfoVO> listcooconApiInfo(CooconAPIinfoFormVO cooconAPIinfoFormVO) {
		return cooconMapper.listcooconApiInfo(cooconAPIinfoFormVO);
	}

	@Override
	public int listcooconApiInfoCount(CooconAPIinfoFormVO cooconAPIinfoFormVO) {
		return cooconMapper.listcooconApiInfoCount(cooconAPIinfoFormVO);
	}

	@Override
	public String createcooconAPIinfo(CooconAPIinfoFormVO cooconAPIinfoFormVO) {
		// TODO Auto-generated method stub
		int result = 0;
		String message = null;
		result = cooconMapper.duplcooconAPIinfo(cooconAPIinfoFormVO);

		FincorpVO fincorpVO = new FincorpVO();

		if( result > 0 ){
			message = "이 중복 키가 존재합니다.";
		}else{
			cooconMapper.createcooconAPIinfo(cooconAPIinfoFormVO);
			fincorpVO.setYn_scrap("Y");
			fincorpVO.setId_lst(cooconAPIinfoFormVO.getId_lst());
			fincorpVO.setCd_fc(cooconAPIinfoFormVO.getCd_fc());
			fincorpManager.updateYnScrap(fincorpVO);
			message = "등록에 성공하였습니다.";
		}
		return message;

	}

	@Override
	public String updcooconAPIinfo(CooconAPIinfoFormVO cooconAPIinfoFormVO) {
		// TODO Auto-generated method stub
		int result = 0;
		String message = null;
		result = cooconMapper.updcooconAPIinfo(cooconAPIinfoFormVO);

		if( result > 0 ){
			message = "수정에 성공하였습니다.";
		}else{
			message = "수정에 실패하였습니다.";
		}
		return message;
	}

	@Override
	public String delcooconAPIinfo(CooconAPIinfoFormVO cooconAPIinfoFormVO) {
		// TODO Auto-generated method stub
		int result = 0;
		String message = null;

		FincorpVO fincorpVO = new FincorpVO();

		result = cooconMapper.delcooconAPIinfo(cooconAPIinfoFormVO);

		int cooconAPICnt = cooconMapper.duplcooconAPIFcInfo(cooconAPIinfoFormVO.getCd_fc());
		if(cooconAPICnt < 1){
			fincorpVO.setYn_scrap("N");
			fincorpVO.setId_lst(cooconAPIinfoFormVO.getId_lst());
			fincorpVO.setCd_fc(cooconAPIinfoFormVO.getCd_fc());
			fincorpManager.updateYnScrap(fincorpVO);
		}

		if( result > 0 ){
			message = "삭제에 성공하였습니다.";
		}else{
			message = "삭제에 실패하였습니다.";
		}
		return message;
	}

}