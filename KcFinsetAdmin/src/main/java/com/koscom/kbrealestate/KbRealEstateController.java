package com.koscom.kbrealestate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.koscom.domain.KbDongAptInfo;
import com.koscom.domain.KbMarketPriceInfo;
import com.koscom.domain.KbSiGunGuInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.kbrealestate.model.KbRealEstateForm;
import com.koscom.kbrealestate.model.KbSiGunGuVO;
import com.koscom.kbrealestate.service.KbRealEstateManager;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;
import com.koscom.util.URLConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("/kbrealestate")
@PropertySource("classpath:prop/webservice.properties")
public class KbRealEstateController {
	private static final Logger logger = LoggerFactory.getLogger(KbRealEstateController.class);
	
	@Resource
	private Environment environment;
	@Autowired
	private CodeManager codeManager;
	@Autowired
	KbRealEstateManager kbRealEstateManager;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/createKbSiGunGuInfo.crz")
	public String createKbSiGunGuInfo(HttpServletResponse response, Model model) throws Exception{
		createKbSiGunGuApi();
		model.addAttribute("message", "alert('완료입니다.')");
		return "/comm/message";
	}
	public void createKbSiGunGuApi() {
		try {
			kbRealEstateManager.delKbSiGunGuInfo();
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
			String apiUrl = environment.getProperty("coocon.apiUrl") + "gateway.jsp";
			
			KbSiGunGuInfo kbSiGunGuInfo = gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(url.sendReqPOST_KB(apiUrl, postString).getDes_message())).toString(), KbSiGunGuInfo.class);
			if(kbSiGunGuInfo != null  && kbSiGunGuInfo.getRESULT_CD().equals("00000000") && kbSiGunGuInfo.getRESP_DATA() != null){
				System.out.println(kbSiGunGuInfo.getRESULT_CD() + ":" + kbSiGunGuInfo.getRESULT_MG()  + ":" +  kbSiGunGuInfo.getTOTAL_COUNT());
				kbRealEstateManager.createKbSiGunGuInfo(kbSiGunGuInfo.getRESP_DATA());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/createKbDongAptInfo.crz")
	public String createKbDongAptInfo(HttpServletRequest request, Model model) throws Exception{
		createKbDongAptApi(StringUtil.nullToString(request.getParameter("building_type")));
		model.addAttribute("message", "alert('완료입니다.')");
		return "/comm/message";
	}
	public void createKbDongAptApi(String building_type) {
		try {
			String api_id = "9920";	//주소(시군구 읍면동 아파트,오피스텔-부동산조회) :9920,  시세조회: 9921
			String orgcd = "007";
//			String building_type = StringUtil.nullToString(request.getParameter("building_type")).equals("") ? "1" : "2";	//building_type 1 아파트, 2 오피스텔
			kbRealEstateManager.delKbDongAptInfo(building_type);
			String search_type = "2";
			String region1_info = "";
			String region2_info = "";
			JSONObject jsonObject;
			List<KbSiGunGuVO> list = kbRealEstateManager.listKbSiGunGuInfo();
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
				String apiUrl = environment.getProperty("coocon.apiUrl") + "gateway.jsp";
				
				KbDongAptInfo kbDongAptInfo = gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(url.sendReqPOST_KB(apiUrl, postString).getDes_message())).toString(), KbDongAptInfo.class);
				if(kbDongAptInfo != null  && kbDongAptInfo.getRESULT_CD().equals("00000000") && kbDongAptInfo.getRESP_DATA() != null){
					System.out.println(kbDongAptInfo.getRESULT_CD() + ":" + kbDongAptInfo.getRESULT_MG()  + ":" +  kbDongAptInfo.getTOTAL_COUNT());
					kbRealEstateManager.createKbDongAptInfo(building_type, kbDongAptInfo.getRESP_DATA());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/listKbmarketpriceMain.crz")
	public String listKbmarketpriceMain() {
		return "/kbmarketprice/listKbmarketpriceMain";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/scrapKbMarketPriceList.crz")
	public String scrapKbMarketPriceList(HttpServletRequest request, KbRealEstateForm kbRealEstateForm, Model model) throws Exception{
		scrapKbMarketPrice(request, model);
		return "/kbmarketprice/listKbmarketprice";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/scrapKbMarketPrice.crz")
	public String scrapKbMarketPrice(HttpServletRequest request, KbRealEstateForm kbRealEstateForm, Model model) throws Exception{
		logger.info("scrapKbMarketPrice.crz start");
		logger.info("kbRealEstateForm ========================="+kbRealEstateForm.toString());
		scrapKbMarketPrice(request, model);
		return "/kbmarketprice/listKbmarketpriceMain";
	}
	private void scrapKbMarketPrice(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		KbRealEstateForm kbRealEstateForm = new KbRealEstateForm();
		kbRealEstateForm.setBUILDING_TYPE(StringUtil.nullToString(request.getParameter("BUILDING_TYPE")));
		kbRealEstateForm.setREGION1_CODE(StringUtil.nullToString(request.getParameter("REGION1_CODE")));
		kbRealEstateForm.setREGION2_CODE(StringUtil.nullToString(request.getParameter("REGION2_CODE")));
		kbRealEstateForm.setREGION3_CODE(StringUtil.nullToString(request.getParameter("REGION3_CODE")));
		kbRealEstateForm.setAPARTMENT(StringUtil.nullToString(request.getParameter("APARTMENT")));
		kbRealEstateForm.setPRICE_TYPE(StringUtil.nullToString(request.getParameter("PRICE_TYPE")));
		KbMarketPriceInfo kbMarketPriceInfo = scrapKbMarketPriceApi(kbRealEstateForm);
		if(kbMarketPriceInfo != null  && kbMarketPriceInfo.getRESULT_CD().equals("00000000")){
			System.out.println(kbMarketPriceInfo.getRESULT_CD() + ":" + kbMarketPriceInfo.getRESULT_MG()  + ":" +  kbMarketPriceInfo.getTOTAL_COUNT()  + ":" +  kbMarketPriceInfo.getTOTAL_COUNT2()  + ":" +  kbMarketPriceInfo.getTOTAL_COUNT3()  + ":" +  kbMarketPriceInfo.getTOTAL_COUNT4());
			model.addAttribute("KbMarketPriceOfferingsList", kbMarketPriceInfo.getRESP_DATA());
			model.addAttribute("KbMarketPricePriceList", kbMarketPriceInfo.getRESP_DATA2());
			model.addAttribute("KbMarketPriceComplexList", kbMarketPriceInfo.getRESP_DATA3());
			model.addAttribute("KbMarketPricePyeongList", kbMarketPriceInfo.getRESP_DATA4());
		}
		model.addAttribute("message", "alert('완료입니다.')");
	}
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
		String apiUrl = environment.getProperty("coocon.apiUrl") + "gateway.jsp";
		
		return gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(url.sendReqPOST_KB(apiUrl, postString).getDes_message())).toString(), KbMarketPriceInfo.class);
	}
	@RequestMapping("/listSrchApartmentInfo.json")
	public String listSrchApartmentInfoJson(KbRealEstateForm form, Model model) throws Exception {
		logger.info("listSrchApartmentInfo.json start");
		ReturnClass rc = kbRealEstateManager.listSrchApartmentInfoJson(form);
		
		model.addAttribute("data", (JSONArray)rc.getReturnObj());
		logger.info("output=[" + (JSONArray)rc.getReturnObj() + "]" );
		return "jsonView";
	}
	@RequestMapping("/listAddrRegion1.json")
	public String listAddrRegion1(KbRealEstateForm form, Model model) throws Exception {
		List<String> res = kbRealEstateManager.listAddrRegion1(form);
		model.addAttribute("List", res);
		return "jsonView";
	}
	@RequestMapping("/listAddrRegion2.json")
	public String listAddrRegion2(KbRealEstateForm form, Model model) throws Exception {
		List<String> res = kbRealEstateManager.listAddrRegion2(form);
		model.addAttribute("List", res);
		return "jsonView";
	}
	@RequestMapping("/listAddrRegion3.json")
	public String listAddrRegion3(KbRealEstateForm form, Model model) throws Exception {
		List<String> res = kbRealEstateManager.listAddrRegion3(form);
		model.addAttribute("List", res);
		return "jsonView";
	}
}