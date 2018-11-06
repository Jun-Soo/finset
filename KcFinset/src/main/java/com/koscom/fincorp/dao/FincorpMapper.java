package com.koscom.fincorp.dao;

import java.util.HashMap;
import java.util.List;

import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.model.FincorpfcNminfoForm;

public interface FincorpMapper {
	
	/**
	 * cd_fc를 통해 nm_fc를 조회
	 * @param cd_fc
	 * @return
	 */
	String getNmFc(String cd_fc);
	
	/**
	 * 금융사 이미지 PATH
	 * @param String
	 * @return String
	 */
	String getFcPathFile(String cd_fc);
	
	/**
	 * 금융사 이미지 PATH
	 * @param fincorpVO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	HashMap getImgBi(FincorpVO vo);
	
	/**
	 * 금융사 추가 (KcbManagerImpl)
	 * @param FincorpVO
	 */
	int createFincorp(FincorpVO fincorpVO);
	
	/**
	 * 금융사 변경 (KcbManagerImpl)
	 * @param FincorpVO
	 */
	int updateFincorp(FincorpVO fincorpVO);
	
	/**
	 * 금융사명 KCB 사전연계 정보 삽입 (KcbManagerImpl)
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
	int createFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm);
	
	/**
	 * COOCON 금융사 코드(스크래핑 대상)
	 * @param String
	 * @return List<String>
	 */
	List<String> listCooconFcCd(String type_fc);
	
	/**
	 * 금융사 COM_ALIAS(스크래핑 대상)
	 * @param String
	 * @return List<String>
	 */
	List<String> listComAlias(String type_fc);
	
	/**
	 * COOCON 금융사 코드
	 * @param String
	 * @return String
	 */
	String getCooconFcCdByCdFc(String cd_fc);
	
	/**
	 *  증권사 금융사 코드
	 * @param String
	 * @return String
	 */
	String getCdFcByCooconFcCd(String cd_fc_coocon);
	
	/**
	 *  증권사 금융사 코드
	 * @param String
	 * @return String
	 */
	String getCdFcByComAlias(String com_alias);
	
	/**
	 * 금융사 COM_ALIAS
	 * @param String
	 * @return String
	 */
	String getComAliasCdByCdFc(String cd_fc);

}
