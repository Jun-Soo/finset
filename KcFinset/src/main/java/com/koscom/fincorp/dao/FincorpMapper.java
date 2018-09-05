package com.koscom.fincorp.dao;

import java.util.HashMap;

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
	 * 금융사명 KCB 사전연계 정보 삽입 (KcbManagerImpl)
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
	int createFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm);
}
