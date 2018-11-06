package com.koscom.fincorp.service;

import java.util.List;

public interface FincorpManager {
	
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
	 * @param String
	 * @return String
	 */
	byte[] getImgBi(String path_file) throws Exception;
	
	/**
	 * COOCON 금융사 코드(스크래핑 대상)
	 * @param String
	 * @return List<String>
	 */
	List<String> listCooconFcCd(String type_fc);
}
