package com.koscom.fincorp.dao;

import java.util.HashMap;

import com.koscom.fincorp.model.FincorpVO;

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
}
