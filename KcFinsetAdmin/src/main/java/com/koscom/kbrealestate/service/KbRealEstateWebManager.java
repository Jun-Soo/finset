package com.koscom.kbrealestate.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.koscom.domain.KbMarketPriceInfo;
import com.koscom.kbrealestate.model.KbDongAptVO;
import com.koscom.kbrealestate.model.KbRealEstateForm;
import com.koscom.kbrealestate.model.KbSiGunGuVO;
import com.koscom.util.ReturnClass;

public interface KbRealEstateWebManager {
	public KbMarketPriceInfo scrapKbMarketPriceApi(KbRealEstateForm kbRealEstateForm) throws UnsupportedEncodingException;
	public void createKbSiGunGuApi() throws UnsupportedEncodingException;
	public void createKbDongAptApi(String building_type) throws UnsupportedEncodingException;
	public ReturnClass delKbSiGunGuInfo();
	public ReturnClass delKbDongAptInfo(String building_type);
	public ReturnClass createKbSiGunGuInfo(List<KbSiGunGuVO> list);
	public ReturnClass createKbDongAptInfo(String building_type, List<KbDongAptVO> list);
	public List<KbSiGunGuVO> listKbSiGunGuInfo();
	public ReturnClass listSrchApartmentInfoJson(KbRealEstateForm kbRealEstateForm);
	public List<String> listAddrRegion1(KbRealEstateForm kbRealEstateForm) throws Exception;
	public List<String> listAddrRegion2(KbRealEstateForm kbRealEstateForm) throws Exception;
	public List<String> listAddrRegion3(KbRealEstateForm kbRealEstateForm) throws Exception;
}