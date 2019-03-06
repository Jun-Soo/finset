package com.koscom.kisline.service.impl;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonSyntaxException;
import com.koscom.domain.KisCompanyOutlineInfo;
import com.koscom.domain.KisSrchByNameInfo;
import com.koscom.kisline.model.KisCompanyOutlineVO;
import com.koscom.kisline.service.KislineManager;
import com.koscom.kisline.service.KislineWebManager;
import com.koscom.util.ReturnClass;

@Service("kislineWebManager")
public class KislineWebManagerImpl implements KislineWebManager {
	@Autowired
	private KislineManager KislineManager;
	@Override
	public KisSrchByNameInfo getKisSrchByName(String nm, String bizno, String crpno, String prn_rst_cnt, String pge_st_no) throws JsonSyntaxException, JSONException, IOException {
		return KislineManager.getKisSrchByName(nm, bizno, crpno, prn_rst_cnt, pge_st_no);
	}
	@Override
	public KisCompanyOutlineInfo kisCompanyOutline(String kiscode, String bizno) throws JsonSyntaxException, JSONException, IOException {
		return KislineManager.kisCompanyOutline(kiscode, bizno);
	}
	@Override
	public ReturnClass createKisCompanyOutline(KisCompanyOutlineVO kisCompanyOutlineVO) {
		return KislineManager.createKisCompanyOutline(kisCompanyOutlineVO);
	}
	@Override
	public ReturnClass deleteKisCompanyOutline(String no_bunch) {
		return KislineManager.deleteKisCompanyOutline(no_bunch);
	}
	@Override
	public KisCompanyOutlineVO getKisCompanyOutline(String no_bunch) {
		return KislineManager.getKisCompanyOutline(no_bunch);
	}
}