package com.koscom.kisline.service;

import java.io.IOException;

import org.json.JSONException;

import com.google.gson.JsonSyntaxException;
import com.koscom.domain.KisCompanyOutlineInfo;
import com.koscom.domain.KisSrchByNameInfo;
import com.koscom.kisline.model.KisCompanyOutlineVO;
import com.koscom.util.ReturnClass;

public interface KislineWebManager {
	public KisSrchByNameInfo getKisSrchByName(String nm, String bizno, String crpno, String prn_rst_cnt, String pge_st_no) throws JsonSyntaxException, JSONException, IOException;
	public KisCompanyOutlineInfo kisCompanyOutline(String kiscode, String bizno) throws JsonSyntaxException, JSONException, IOException;
	public ReturnClass createKisCompanyOutline(KisCompanyOutlineVO kisCompanyOutlineVO);
	public ReturnClass deleteKisCompanyOutline(String no_bunch);
	
	/**
	 * OutLine 조회
	 * @param goodsInfo
	 * @return
	 */
	public KisCompanyOutlineVO getKisCompanyOutline(String no_bunch);
}