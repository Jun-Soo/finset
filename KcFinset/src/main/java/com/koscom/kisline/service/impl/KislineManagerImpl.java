package com.koscom.kisline.service.impl;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.koscom.domain.KisCompanyOutlineInfo;
import com.koscom.domain.KisSrchByNameInfo;
import com.koscom.kisline.dao.KislineMapper;
import com.koscom.kisline.model.KisCompanyOutlineVO;
import com.koscom.kisline.service.KislineManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

import okhttp3.OkHttpClient;
import okhttp3.Request;

@Service("kislineManager")
public class KislineManagerImpl implements KislineManager {
	private static final Logger logger = LoggerFactory.getLogger(KislineManagerImpl.class);
	static final String apiUrl = "https://api.kisline.com/nice/sb/api/companyOutlineIfo/srchByName";
	private static final String KIS_HEADER_CLIENTID = "554ea570-287f-46a8-a3b6-fc334e792be9";
	private static final String KIS_HEADER_CLIENTSECRET = "kU4jP7cN6uO8oQ5nY6iB0wU2pP8aN6kH3vE7dN6gT5jY4bE8fC";
	@Autowired
	private KislineMapper KislineMapper;
	public KisSrchByNameInfo getKisSrchByName(String nm, String bizno, String crpno, String prn_rst_cnt, String pge_st_no) throws JsonSyntaxException, JSONException, IOException {
		OkHttpClient client = new OkHttpClient();
//		client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();//타임세팅
		String KIS_SRCHBYNAME_URL = "https://api.kisline.com/nice/sb/api/companyOutlineIfo/srchByName?uid=koscomco";
		if(nm !=null && !nm.equals("")) KIS_SRCHBYNAME_URL = KIS_SRCHBYNAME_URL + "&nm=" + nm;
		if(bizno !=null && !bizno.equals("")) KIS_SRCHBYNAME_URL = KIS_SRCHBYNAME_URL + "&bizno=" + bizno;
		if(crpno !=null && !crpno.equals("")) KIS_SRCHBYNAME_URL = KIS_SRCHBYNAME_URL + "&crpno=" + crpno;
		if(prn_rst_cnt !=null && !prn_rst_cnt.equals("")) KIS_SRCHBYNAME_URL = KIS_SRCHBYNAME_URL + "&prn_rst_cnt=" + prn_rst_cnt;
		if(pge_st_no !=null && !pge_st_no.equals("")) KIS_SRCHBYNAME_URL = KIS_SRCHBYNAME_URL + "&pge_st_no=" + pge_st_no;
		Request request = new Request.Builder().url(KIS_SRCHBYNAME_URL).get().addHeader("x-ibm-client-id", KIS_HEADER_CLIENTID).addHeader("x-ibm-client-secret", KIS_HEADER_CLIENTSECRET).addHeader("accept", "application/json").build();
		return new Gson().fromJson(new JSONObject(client.newCall(request).execute().body().string()).toString(), KisSrchByNameInfo.class);
	}
	public KisCompanyOutlineInfo kisCompanyOutline(String kiscode, String bizno) throws JsonSyntaxException, JSONException, IOException {
		OkHttpClient client = new OkHttpClient();
//		client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();//타임세팅
		String KIS_COMPANYOUTLINE_URL = "https://api.kisline.com/nice/sb/api/companyOutlineIfo/companyOutline?uid=koscomco";
		if(kiscode !=null && !kiscode.equals("")) KIS_COMPANYOUTLINE_URL = KIS_COMPANYOUTLINE_URL + "&kiscode=" + kiscode;
		if(bizno !=null && !bizno.equals("")) KIS_COMPANYOUTLINE_URL = KIS_COMPANYOUTLINE_URL + "&bizno=" + bizno;
		Request request = new Request.Builder().url(KIS_COMPANYOUTLINE_URL).get().addHeader("x-ibm-client-id", KIS_HEADER_CLIENTID).addHeader("x-ibm-client-secret", KIS_HEADER_CLIENTSECRET).addHeader("accept", "application/json").build();
		logger.debug(client.newCall(request).execute().body().string());
		return new Gson().fromJson(new JSONObject(client.newCall(request).execute().body().string()).toString(), KisCompanyOutlineInfo.class);
	}
	@Override
	public ReturnClass createKisCompanyOutline(KisCompanyOutlineVO kisCompanyOutlineVO){
		KislineMapper.createKisCompanyOutline(kisCompanyOutlineVO);
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass deleteKisCompanyOutline(String no_bunch) {
		if (1 != KislineMapper.deleteKisCompanyOutline(no_bunch)) {
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.");
	}
	@Override
	public KisCompanyOutlineVO getKisCompanyOutline(String no_bunch) {
		return KislineMapper.getKisCompanyOutline(no_bunch);
	}
}