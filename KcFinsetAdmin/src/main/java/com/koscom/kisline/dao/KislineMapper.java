package com.koscom.kisline.dao;

import com.koscom.kisline.model.KisCompanyOutlineVO;
import com.koscom.kisline.model.KisSrchByNameVO;

public interface KislineMapper {
	int delKisSrchByNameInfo();
	int createKisSrchByNameInfo(KisSrchByNameVO kbSiGunGuVO);
	int createKisCompanyOutline(KisCompanyOutlineVO kbDongAptVO);
	int deleteKisCompanyOutline(String no_bunch);
	
	/**
	 * OutLine 조회
	 * @param goodsInfo
	 * @return
	 */
	KisCompanyOutlineVO getKisCompanyOutline(String no_bunch);
}