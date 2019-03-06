package com.koscom.kbrealestate.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.google.gson.Gson;
import com.koscom.domain.KbDongAptInfo;
import com.koscom.domain.KbMarketPriceInfo;
import com.koscom.domain.KbSiGunGuInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.kbrealestate.dao.KbRealEstateMapper;
import com.koscom.kbrealestate.model.KbDongAptVO;
import com.koscom.kbrealestate.model.KbRealEstateForm;
import com.koscom.kbrealestate.model.KbSiGunGuVO;
import com.koscom.kbrealestate.service.KbRealEstateManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;
import com.koscom.util.URLConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Service("kbRealEstateManager")
public class KbRealEstateManagerImpl implements KbRealEstateManager {
	private static final Logger logger = LoggerFactory.getLogger(KbRealEstateManagerImpl.class);
	static final String apiUrl = "http://dev.coocon.co.kr/sol/new_gateway/gateway.jsp";
	@Autowired
	private KbRealEstateMapper kbRealEstateMapper;
	@Autowired
	private CodeManager codeManager;
	public KbMarketPriceInfo scrapKbMarketPriceApi(KbRealEstateForm kbRealEstateForm) throws UnsupportedEncodingException {
		String api_id = "9921";	//주소(시군구 읍면동 아파트,오피스텔-부동산조회) :9920,  시세조회: 9921
		String orgcd = "007";
		String building_type = StringUtil.nullToString(kbRealEstateForm.getBUILDING_TYPE()).equals("") ? "1" : StringUtil.nullToString(kbRealEstateForm.getBUILDING_TYPE());	//building_type 1 아파트, 2 오피스텔
		String region1_code = StringUtil.nullToString(kbRealEstateForm.getREGION1_CODE()).equals("") ? "010000" : StringUtil.nullToString(kbRealEstateForm.getREGION1_CODE());
		String region2_code = StringUtil.nullToString(kbRealEstateForm.getREGION2_CODE()).equals("") ? "010700" : StringUtil.nullToString(kbRealEstateForm.getREGION2_CODE());
		String region3_code = StringUtil.nullToString(kbRealEstateForm.getREGION3_CODE()).equals("") ? "010701" : StringUtil.nullToString(kbRealEstateForm.getREGION3_CODE());
		String apartment = StringUtil.nullToString(kbRealEstateForm.getAPARTMENT()).equals("") ? "KBM021519" : StringUtil.nullToString(kbRealEstateForm.getAPARTMENT());
		String price_type = StringUtil.nullToString(kbRealEstateForm.getPRICE_TYPE());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("API_KEY", codeManager.getCodeName("_CONF_SYSTEM", "KEY_COOCON"));	//		1	인증키	API_KEY	20	-	S	 발급받은 API 인증키 값
		jsonObject.put("API_ID", api_id);	//
		jsonObject.put("ORGCD", orgcd);			//기관코드	문자	3	필수	007 : KB부동산
		jsonObject.put("BUILDING_TYPE", building_type);		//부동산구분	문자	1	필수	1 : 아파트, 2 : 오피스텔
		jsonObject.put("REGION1_CODE", region1_code);	//대지역코드	문자	12	필수	　
		jsonObject.put("REGION2_CODE", region2_code);		//중지역코드	문자	12	필수	　
		jsonObject.put("REGION3_CODE", region3_code);		//소지역코드	문자	12	필수	　
		jsonObject.put("APARTMENT", apartment);			//물건식별자	문자	12	필수	　
		jsonObject.put("PRICE_TYPE", price_type);			//시세종류	문자	1	필수	KB 부동산인 경우 공백처리
		logger.info("=========================JSONDataVal===========================");
		logger.info(jsonObject.toString());
		logger.info("=========================JSONDataVal===========================");
		URLConnection url = new URLConnection();
		String postString = "REQ_DATA="+ URLEncoder.encode(URLEncoder.encode(jsonObject.toString(), "UTF-8"), "UTF-8");
		Gson gson = new Gson();
		return gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(url.sendReqPOST_KB(apiUrl, postString).getDes_message())).toString(), KbMarketPriceInfo.class);
	}
	public void createKbSiGunGuApi() throws UnsupportedEncodingException {
		delKbSiGunGuInfo();
		//building_type 이 1,2있는데 시군구는 동일하므로 아파트만 돌림!!
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("API_KEY", codeManager.getCodeName("_CONF_SYSTEM", "KEY_COOCON"));	//		1	인증키	API_KEY	20	-	S	 발급받은 API 인증키 값
		jsonObject.put("API_ID", "9920");	//
		jsonObject.put("ORGCD", "007");			//기관코드	문자	3	필수	007 : KB부동산
		jsonObject.put("BUILDING_TYPE", "1");		//부동산구분	문자	1	필수	1 : 아파트, 2 : 오피스텔
		jsonObject.put("SEARCH_TYPE", "1");	//1: 대지역,중지역조회		2: 소지역,물건식별자 조회
		jsonObject.put("REGION1_INFO", "");		//조회구분 2 일때 필수 값	　
		jsonObject.put("REGION2_INFO", "");		//조회구분 2 일때 필수 값
		logger.info("=========================JSONDataVal===========================");
		logger.info(jsonObject.toString());
		logger.info("=========================JSONDataVal===========================");
		URLConnection url = new URLConnection();
		String postString = "REQ_DATA="+URLEncoder.encode(URLEncoder.encode(jsonObject.toString(), "UTF-8"), "UTF-8");
		Gson gson = new Gson();
		KbSiGunGuInfo kbSiGunGuInfo = gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(url.sendReqPOST_KB(apiUrl, postString).getDes_message())).toString(), KbSiGunGuInfo.class);
		if(kbSiGunGuInfo != null  && kbSiGunGuInfo.getRESULT_CD().equals("00000000") && kbSiGunGuInfo.getRESP_DATA() != null){
			logger.debug(kbSiGunGuInfo.getRESULT_CD() + ":" + kbSiGunGuInfo.getRESULT_MG()  + ":" +  kbSiGunGuInfo.getTOTAL_COUNT());
			createKbSiGunGuInfo(kbSiGunGuInfo.getRESP_DATA());
		}
	}
	public void createKbDongAptApi(String building_type) throws  UnsupportedEncodingException {
		String api_id = "9920";	//주소(시군구 읍면동 아파트,오피스텔-부동산조회) :9920,  시세조회: 9921
		String orgcd = "007";
//			String building_type = StringUtil.nullToString(request.getParameter("building_type")).equals("") ? "1" : "2";	//building_type 1 아파트, 2 오피스텔
		delKbDongAptInfo(building_type);
		String search_type = "2";
		String region1_info = "";
		String region2_info = "";
		JSONObject jsonObject;
		List<KbSiGunGuVO> list = listKbSiGunGuInfo();
		URLConnection url = new URLConnection();
		for (KbSiGunGuVO kbSiGunGuVO : list) {
			region1_info = kbSiGunGuVO.getREGION1_CODE();
			region2_info = kbSiGunGuVO.getREGION2_CODE();
			jsonObject = new JSONObject();
			jsonObject.put("API_KEY", codeManager.getCodeName("_CONF_SYSTEM", "KEY_COOCON"));	//		1	인증키	API_KEY	20	-	S	 발급받은 API 인증키 값
			jsonObject.put("API_ID", api_id);	//
			jsonObject.put("ORGCD", orgcd);			//기관코드	문자	3	필수	007 : KB부동산
			jsonObject.put("BUILDING_TYPE", building_type);		//부동산구분	문자	1	필수	1 : 아파트, 2 : 오피스텔
			jsonObject.put("SEARCH_TYPE", search_type);	//1: 대지역,중지역조회		2: 소지역,물건식별자 조회
			jsonObject.put("REGION1_INFO", region1_info);		//조회구분 2 일때 필수 값	　
			jsonObject.put("REGION2_INFO", region2_info);		//조회구분 2 일때 필수 값
			logger.info("=========================JSONDataVal===========================");
			logger.info(jsonObject.toString());
			logger.info("=========================JSONDataVal===========================");
			String postString = "REQ_DATA="+URLEncoder.encode(URLEncoder.encode(jsonObject.toString(), "UTF-8"), "UTF-8");
			Gson gson = new Gson();
			KbDongAptInfo kbDongAptInfo = gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(url.sendReqPOST_KB(apiUrl, postString).getDes_message())).toString(), KbDongAptInfo.class);
			if(kbDongAptInfo != null  && kbDongAptInfo.getRESULT_CD().equals("00000000") && kbDongAptInfo.getRESP_DATA() != null){
				logger.debug(kbDongAptInfo.getRESULT_CD() + ":" + kbDongAptInfo.getRESULT_MG()  + ":" +  kbDongAptInfo.getTOTAL_COUNT());
				createKbDongAptInfo(building_type, kbDongAptInfo.getRESP_DATA());
			}
		}

	}
	@Override
	public ReturnClass createKbSiGunGuInfo(List<KbSiGunGuVO> list) {
		for (KbSiGunGuVO kbSiGunGuVO : list) {
			if (1 != kbRealEstateMapper.createKbSiGunGuInfo(kbSiGunGuVO)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delKbSiGunGuInfo() {
		if (1 != kbRealEstateMapper.delKbSiGunGuInfo()) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass createKbDongAptInfo(String building_type, List<KbDongAptVO> list) {
		for (KbDongAptVO kbDongAptVO : list) {
			kbDongAptVO.setBUILDING_TYPE(building_type);
			if (1 != kbRealEstateMapper.createKbDongAptInfo(kbDongAptVO)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delKbDongAptInfo(String building_type) {
		if (1 != kbRealEstateMapper.delKbDongAptInfo(building_type)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public List<KbSiGunGuVO> listKbSiGunGuInfo() {
		return kbRealEstateMapper.listKbSiGunGuInfo();
	}
	
	/**
	 * listSrchApartmentInfoJson
	 * 아파트명 검색
	 * @param kbRealEstateForm
	 * @return 
	 */
	@Override
	public ReturnClass listSrchApartmentInfoJson(KbRealEstateForm kbRealEstateForm) {
		JSONArray list = new JSONArray();
		JSONObject object = null;
		List<KbDongAptVO> res = kbRealEstateMapper.listSrchApartmentInfoJson(kbRealEstateForm);
		for (int i=0; i < res.size() ; i++ ) {
			object = new JSONObject();
			object.put("auto_com_txt", res.get(i).getAuto_com_txt());
			object.put("APARTMENT", res.get(i).getAPARTMENT());
			object.put("APARTMENT_NAME", res.get(i).getAPARTMENT_NAME());
			object.put("REGION3_CODE", res.get(i).getREGION3_CODE());
			list.add(object);
		}
		return new ReturnClass( Constant.SUCCESS, "정상적으로 처리되었습니다.", list );
	}
	@Override
	public List<String> listAddrRegion1(KbRealEstateForm kbRealEstateForm) throws Exception {
		return kbRealEstateMapper.listAddrRegion1(kbRealEstateForm);
	}
	@Override
	public List<String> listAddrRegion2(KbRealEstateForm kbRealEstateForm) throws Exception {
		return kbRealEstateMapper.listAddrRegion2(kbRealEstateForm);
	}
	@Override
	public List<String> listAddrRegion3(KbRealEstateForm kbRealEstateForm) throws Exception {
		return kbRealEstateMapper.listAddrRegion3(kbRealEstateForm);
	}
}