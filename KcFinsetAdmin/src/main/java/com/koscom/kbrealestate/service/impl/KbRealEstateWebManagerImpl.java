package com.koscom.kbrealestate.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.KbMarketPriceInfo;
import com.koscom.kbrealestate.model.KbDongAptVO;
import com.koscom.kbrealestate.model.KbRealEstateForm;
import com.koscom.kbrealestate.model.KbSiGunGuVO;
import com.koscom.kbrealestate.service.KbRealEstateManager;
import com.koscom.kbrealestate.service.KbRealEstateWebManager;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;

@Service("kbRealEstateWebManager")
public class KbRealEstateWebManagerImpl implements KbRealEstateWebManager {
	@Autowired
	private KbRealEstateManager kbRealEstateManager;

	@Override
	public KbMarketPriceInfo scrapKbMarketPriceApi(KbRealEstateForm kbRealEstateForm) throws UnsupportedEncodingException {
		return kbRealEstateManager.scrapKbMarketPriceApi(kbRealEstateForm);
	}
	@Override
	public void createKbSiGunGuApi() throws UnsupportedEncodingException {
		kbRealEstateManager.createKbSiGunGuApi();
	}
	@Override
	public void createKbDongAptApi(String building_type) throws UnsupportedEncodingException {
		kbRealEstateManager.createKbDongAptApi(building_type);
	}
	@Override
	public ReturnClass delKbSiGunGuInfo() {
		return kbRealEstateManager.delKbSiGunGuInfo();
	}
	@Override
	public ReturnClass delKbDongAptInfo(String building_type) {
		return kbRealEstateManager.delKbDongAptInfo(building_type);
	}
	@Override
	public ReturnClass createKbSiGunGuInfo(List<KbSiGunGuVO> list) {
		return kbRealEstateManager.createKbSiGunGuInfo(list);
	}
	@Override
	public ReturnClass createKbDongAptInfo(String building_type, List<KbDongAptVO> list) {
		return kbRealEstateManager.createKbDongAptInfo(building_type, list);
	}
	@Override
	public List<KbSiGunGuVO> listKbSiGunGuInfo() {
		return kbRealEstateManager.listKbSiGunGuInfo();
	}
	@Override
	public ReturnClass listSrchApartmentInfoJson(KbRealEstateForm kbRealEstateForm) {
		return kbRealEstateManager.listSrchApartmentInfoJson(kbRealEstateForm);
	}
	@Override
	public List<String> listAddrRegion1(KbRealEstateForm kbRealEstateForm) throws Exception {
		return kbRealEstateManager.listAddrRegion1(kbRealEstateForm);
	}
	@Override
	public List<String> listAddrRegion2(KbRealEstateForm kbRealEstateForm) throws Exception {
		return kbRealEstateManager.listAddrRegion2(kbRealEstateForm);
	}
	@Override
	public List<String> listAddrRegion3(KbRealEstateForm kbRealEstateForm) throws Exception {
		return kbRealEstateManager.listAddrRegion3(kbRealEstateForm);
	}
}