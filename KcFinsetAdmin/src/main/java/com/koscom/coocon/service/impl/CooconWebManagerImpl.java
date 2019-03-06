package com.koscom.coocon.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.coocon.model.CooconAPIinfoFormVO;
import com.koscom.coocon.model.CooconAPIinfoVO;
import com.koscom.coocon.model.CooconChangeVO;
import com.koscom.coocon.model.CooconVO;
import com.koscom.coocon.service.CooconManager;
import com.koscom.coocon.service.CooconWebManager;

@Service("cooconWebManager")
public class CooconWebManagerImpl implements CooconWebManager{

	@Autowired
	private CooconManager cooconManager;

	@Override
	public String getCdcoocongoods(String nm_coocon_goods, String cd_org) {
		// TODO Auto-generated method stub
		return cooconManager.getCdcoocongoods(nm_coocon_goods, cd_org);
	}

	@Override
	public int setCooconGoods(List<CooconVO> listCooconVO) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return cooconManager.setCooconGoods(listCooconVO);

	}

	@Override
	public List<CooconAPIinfoVO> getCooconAPIinfo() {
		// TODO Auto-generated method stub
		return cooconManager.getCooconAPIinfo();
	}

	@Override
	public int getSeq(String cd_coocon_goods) {
		// TODO Auto-generated method stub
		return cooconManager.getSeq(cd_coocon_goods);
	}

	@Override
	public List<CooconAPIinfoVO> listcooconApiInfo(CooconAPIinfoFormVO cooconAPIinfoFormVO) {
		return cooconManager.listcooconApiInfo(cooconAPIinfoFormVO);
	}

	@Override
	public int listcooconApiInfoCount(CooconAPIinfoFormVO cooconAPIinfoFormVO) {
		return cooconManager.listcooconApiInfoCount(cooconAPIinfoFormVO);
	}

	@Override
	public String createcooconAPIinfo(CooconAPIinfoFormVO cooconAPIinfoFormVO) {
		// TODO Auto-generated method stub
		return cooconManager.createcooconAPIinfo(cooconAPIinfoFormVO);
	}

	@Override
	public String updcooconAPIinfo(CooconAPIinfoFormVO cooconAPIinfoFormVO) {
		// TODO Auto-generated method stub
		return cooconManager.updcooconAPIinfo(cooconAPIinfoFormVO);
	}

	@Override
	public String delcooconAPIinfo(CooconAPIinfoFormVO cooconAPIinfoFormVO) {
		// TODO Auto-generated method stub
		return cooconManager.delcooconAPIinfo(cooconAPIinfoFormVO);
	}

}