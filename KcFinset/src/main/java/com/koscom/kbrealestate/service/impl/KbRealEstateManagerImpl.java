package com.koscom.kbrealestate.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koscom.env.service.CodeManager;
import com.koscom.kbrealestate.dao.KbRealEstateMapper;
import com.koscom.kbrealestate.model.KbDongAptVO;
import com.koscom.kbrealestate.model.KbMarketPriceInfo;
import com.koscom.kbrealestate.model.KbRealEstateForm;
import com.koscom.kbrealestate.service.KbRealEstateManager;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;
import com.koscom.util.URLConnection;

@Service("kbrealestateManager")
@PropertySource("classpath:prop/webservice.properties")
public class KbRealEstateManagerImpl implements KbRealEstateManager{

	private static final Logger logger = LoggerFactory.getLogger(KbRealEstateManagerImpl.class);

	@Autowired
	KbRealEstateMapper kbRealEstateMapper;
	@Autowired
	CodeManager codeManager;
	@Resource
	Environment environment;
	
	@Override
	public List<String> listAddrRegion1(KbRealEstateForm kbRealEstateForm) throws Exception {
		logger.debug("listAddrRegion1");
		return kbRealEstateMapper.listAddrRegion1(kbRealEstateForm);
	}
	
	@Override
	public List<String> listAddrRegion2(KbRealEstateForm kbRealEstateForm) throws Exception {
		logger.debug("listAddrRegion2");
		return kbRealEstateMapper.listAddrRegion2(kbRealEstateForm);
	}
	
	@Override
	public List<String> listAddrRegion3(KbRealEstateForm kbRealEstateForm) throws Exception {
		logger.debug("listAddrRegion3");
		return kbRealEstateMapper.listAddrRegion3(kbRealEstateForm);
	}
	
	@Override
	public List<KbDongAptVO> listSrchApartmentInfo(KbRealEstateForm kbRealEstateForm) {
		return kbRealEstateMapper.listSrchApartmentInfo(kbRealEstateForm);
	}
	
	@Override
	public KbMarketPriceInfo scrapKbMarketPriceList(KbRealEstateForm kbRealEstateForm) throws UnsupportedEncodingException,FinsetException {
		String api_id = "9921";	//주소(시군구 읍면동 아파트,오피스텔-부동산조회) :9920,  시세조회: 9921
		String orgcd = "007";
		String building_type = StringUtil.nullToString(kbRealEstateForm.getBuilding_type()).equals("") ? "1" : StringUtil.nullToString(kbRealEstateForm.getBuilding_type());	//building_type 1 아파트, 2 오피스텔
		String region1_code = StringUtil.nullToString(kbRealEstateForm.getRegion1_code()).equals("") ? "010000" : StringUtil.nullToString(kbRealEstateForm.getRegion1_code());
		String region2_code = StringUtil.nullToString(kbRealEstateForm.getRegion2_code()).equals("") ? "010700" : StringUtil.nullToString(kbRealEstateForm.getRegion2_code());
		String region3_code = StringUtil.nullToString(kbRealEstateForm.getRegion3_code()).equals("") ? "010701" : StringUtil.nullToString(kbRealEstateForm.getRegion3_code());
		String apartment = StringUtil.nullToString(kbRealEstateForm.getApartment()).equals("") ? "KBM021519" : StringUtil.nullToString(kbRealEstateForm.getApartment());
		String price_type = StringUtil.nullToString(kbRealEstateForm.getPrice_type());
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("API_KEY", codeManager.getCodeName("_CONF_SYSTEM", "KEY_COOCON"));	//		1	인증키	API_KEY	20	-	S	 발급받은 API 인증키 값
		jsonObject.addProperty("API_ID", api_id);	//
		jsonObject.addProperty("ORGCD", orgcd);			//기관코드	문자	3	필수	007 : KB부동산
		jsonObject.addProperty("BUILDING_TYPE", building_type);		//부동산구분	문자	1	필수	1 : 아파트, 2 : 오피스텔
		jsonObject.addProperty("REGION1_CODE", region1_code);	//대지역코드	문자	12	필수	　
		jsonObject.addProperty("REGION2_CODE", region2_code);		//중지역코드	문자	12	필수	　
		jsonObject.addProperty("REGION3_CODE", region3_code);		//소지역코드	문자	12	필수	　
		jsonObject.addProperty("APARTMENT", apartment);			//물건식별자	문자	12	필수	　
		jsonObject.addProperty("PRICE_TYPE", price_type);			//시세종류	문자	1	필수	KB 부동산인 경우 공백처리
		logger.info("=========================JSONDataVal===========================");
		logger.info(jsonObject.toString());
		logger.info("=========================JSONDataVal===========================");
		URLConnection url = new URLConnection();
		String postString = "REQ_DATA="+ URLEncoder.encode(URLEncoder.encode(jsonObject.toString(), "UTF-8"), "UTF-8");
		Gson gson = new Gson();
		String apiUrl = environment.getProperty("coocon.napiUrl") + "gateway.jsp";
        logger.info("coocon.apiUrl:"+apiUrl);
        ReturnClass rc = null;
        String des_message = null;
		KbMarketPriceInfo kbMarketPriceInfo = null;
		rc = url.sendReqPOST_KB(apiUrl, postString);
		logger.info("sendReqPOST_KB:"+rc);
		if (rc != null) {
            des_message = rc.getDes_message();
            logger.info("rc.getDes_message:"+des_message);
            if(des_message != null && !des_message.contains("전송 실패")) {
            	kbMarketPriceInfo = gson.fromJson(des_message, KbMarketPriceInfo.class);
            } else {
                throw new FinsetException("쿠콘 전송실패");
            }
		}
		return kbMarketPriceInfo;
	}
	
}
