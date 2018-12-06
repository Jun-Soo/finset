package com.koscom.kbrealestate.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.koscom.kbrealestate.model.KbDongAptVO;
import com.koscom.kbrealestate.model.KbMarketPriceInfo;
import com.koscom.kbrealestate.model.KbRealEstateForm;
import com.koscom.util.FinsetException;

public interface KbRealEstateManager {
	
	/**
	 * 시/도 조회
	 * @param kbRealEstateForm
	 * @return
	 * @throws Exception
	 */
	public List<String> listAddrRegion1(KbRealEstateForm kbRealEstateForm) throws Exception;
	
	/**
	 * 시/군/구 조회
	 * @param kbRealEstateForm
	 * @return
	 * @throws Exception
	 */
	public List<String> listAddrRegion2(KbRealEstateForm kbRealEstateForm) throws Exception;
	
	/**
	 * 읍/면/동 조회
	 * @param kbRealEstateForm
	 * @return
	 * @throws Exception
	 */
	public List<String> listAddrRegion3(KbRealEstateForm kbRealEstateForm) throws Exception;
	
	/**
	 * 아파트 조회
	 * @param kbRealEstateForm
	 * @return
	 */
	public List<KbDongAptVO> listSrchApartmentInfo(KbRealEstateForm kbRealEstateForm);
	
	/**
	 * 시세 조회
	 * @param kbRealEstateForm
	 * @return
	 */
	public KbMarketPriceInfo scrapKbMarketPriceList(KbRealEstateForm kbRealEstateForm) throws UnsupportedEncodingException,FinsetException;
}
