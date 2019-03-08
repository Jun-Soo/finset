package com.koscom.kbrealestate.dao;

import java.util.List;

import com.koscom.kbrealestate.model.KbDongAptVO;
import com.koscom.kbrealestate.model.KbRealEstateForm;
import com.koscom.kbrealestate.model.KbSiGunGuVO;

public interface KbRealEstateMapper {
	int delKbSiGunGuInfo();
	int createKbSiGunGuInfo(KbSiGunGuVO kbSiGunGuVO);
	int delKbDongAptInfo(String building_type);
	int createKbDongAptInfo(KbDongAptVO kbDongAptVO);
	List<KbSiGunGuVO> listKbSiGunGuInfo();
	/**
	 * listSrchFcRepeatJson
	 * 아파트명 검색.
	 * @param FcCodeForm
	 * @return List<FcCodeForm>
	**/
	List<KbDongAptVO> listSrchApartmentInfoJson(KbRealEstateForm form);
	
	List<String> listAddrRegion1(KbRealEstateForm form);
	List<String> listAddrRegion2(KbRealEstateForm form);
	List<String> listAddrRegion3(KbRealEstateForm form);
}