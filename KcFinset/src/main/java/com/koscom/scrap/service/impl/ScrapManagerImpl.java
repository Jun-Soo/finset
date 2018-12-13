package com.koscom.scrap.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koscom.env.service.CodeManager;
import com.koscom.fccode.dao.FcCodeMapper;
import com.koscom.fincorp.dao.FincorpMapper;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.model.FincorpfcNminfoForm;
import com.koscom.kcb.model.KcbReqNonfiInfoVO;
import com.koscom.person.dao.PersonMapper;
import com.koscom.person.model.PersonActiveHistVO;
import com.koscom.person.model.PersonAgreeHistVO;
import com.koscom.person.model.PersonAgreedtHistVO;
import com.koscom.person.model.PersonCertificateInfoVO;
import com.koscom.person.model.PersonVO;
import com.koscom.scrap.dao.ScrapMapper;
import com.koscom.scrap.model.AnAllVO;
import com.koscom.scrap.model.AppBankInfo;
import com.koscom.scrap.model.AppCardInfo;
import com.koscom.scrap.model.AppFcLinkInfo;
import com.koscom.scrap.model.AppNHISInfo;
import com.koscom.scrap.model.AppNPSInfo;
import com.koscom.scrap.model.AppNTSInfo;
import com.koscom.scrap.model.DepositAnVO;
import com.koscom.scrap.model.FcLinkInfoVO;
import com.koscom.scrap.model.FundAnVO;
import com.koscom.scrap.model.LinkedFcInfoVO;
import com.koscom.scrap.model.LoanAnVO;
import com.koscom.scrap.model.NHISInfo;
import com.koscom.scrap.model.NPSInfo;
import com.koscom.scrap.model.NTSInfo;
import com.koscom.scrap.model.ScrBankApiAnInfoVO;
import com.koscom.scrap.model.ScrCardApprovalInfoVO;
import com.koscom.scrap.model.ScrCardChargeDetailVO;
import com.koscom.scrap.model.ScrCardChargeInfoVO;
import com.koscom.scrap.model.ScrCardInfoVO;
import com.koscom.scrap.model.ScrCardLimitInfoVO;
import com.koscom.scrap.model.ScrCardPointInfoVO;
import com.koscom.scrap.model.ScrReqBankVO;
import com.koscom.scrap.model.ScrReqCardVO;
import com.koscom.scrap.model.ScrReqCertificationVO;
import com.koscom.scrap.model.ScrReqHealthVO;
import com.koscom.scrap.model.ScrReqPensionVO;
import com.koscom.scrap.model.ScrRespCashReceiptVO;
import com.koscom.scrap.model.ScrRespHealthPaymentVO;
import com.koscom.scrap.model.ScrRespHealthPaymentdtlVO;
import com.koscom.scrap.model.ScrRespIncomeDtlVO;
import com.koscom.scrap.model.ScrRespPensionPaymentVO;
import com.koscom.scrap.model.ScrRespPensionPaymentdtlVO;
import com.koscom.scrap.model.ScrRsltScrapVO;
import com.koscom.scrap.model.StockEquitylistVO;
import com.koscom.scrap.model.StockEtclistVO;
import com.koscom.scrap.model.StockFundlistVO;
import com.koscom.scrap.model.StockInterestIsinVO;
import com.koscom.scrap.model.StockListVO;
import com.koscom.scrap.model.StockSummaryVO;
import com.koscom.scrap.model.StockTransactionVO;
import com.koscom.scrap.model.UserBankOutputVO;
import com.koscom.scrap.model.UserCardOutputVO;
import com.koscom.scrap.model.sub.AnAllListHistoryVO;
import com.koscom.scrap.model.sub.AnAllListVO;
import com.koscom.scrap.model.sub.DepositAnListHistoryVO;
import com.koscom.scrap.model.sub.DepositAnListVO;
import com.koscom.scrap.model.sub.FundAnListVO;
import com.koscom.scrap.model.sub.LoanAnListVO;
import com.koscom.scrap.service.ScrapManager;
import com.koscom.util.Constant;
import com.koscom.util.DateUtil;
import com.koscom.util.LogUtil;
import com.koscom.util.StringUtil;
import com.koscom.util.URLConnection;
import com.koscom.util.ReturnClass;

@Service("scrapManager")
@PropertySource("classpath:prop/webservice.properties")
public class ScrapManagerImpl implements ScrapManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ScrapManagerImpl.class);
	
	@Autowired
	private ScrapMapper scrapMapper;
	
	@Autowired
	private CodeManager codeManager;
	
	@Autowired
	private PersonMapper personMapper;
	
	@Autowired
	private FcCodeMapper fcCodeMapper;
	
	@Autowired
	private FincorpMapper fincorpMapper;
	
	@Resource
	Environment environment;
	
	@Override
	public void getDirectFinanceSearch()	{
		String directUrl = environment.getProperty("direct.apiUrl") + "/company/finance/search";
		String directKey = environment.getProperty("direct.apiKey");
		
		URLConnection url = new URLConnection();
		logger.info("getDirectFinanceSearch  : URL[" + directUrl +"], APIkey : "+directKey);
		
		ReturnClass returnClass = url.sendReqGET_Direct(directUrl, directKey,"");
		
		if(returnClass.getCd_result()  == Constant.SUCCESS)	{
			String data = returnClass.getDes_message();
			logger.info("data : "+ data);
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = (JsonObject) jsonParser.parse(data);
			
			JsonArray jsonArray = (JsonArray)jsonObject.get("financeList");
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonObject object = (JsonObject) jsonArray.get(i);
				String nmFc = object.get("comName").getAsString();
				logger.info("name  :  "+ nmFc);
				
				FincorpVO Fincorp = new FincorpVO();
				Fincorp.setCd_fin("F"); //F:증권
				Fincorp.setNo_biz_comp("0000000000");
				Fincorp.setNm_fc(nmFc);
				Fincorp.setYn_use("Y");
				Fincorp.setYn_alliance("N");
				Fincorp.setCd_fc_coocon(object.get("comId").getAsString());
				Fincorp.setCom_alias(object.get("comAlias").getAsString());
				Fincorp.setYn_scrap("Y");
				Fincorp.setId_frt("SYS");
				Fincorp.setId_lst("SYS");
				
				// 증권사 내역 DB 조회 후 추가 또는 변경
				String cdFc = fcCodeMapper.selectCdFc(nmFc);				
				if(StringUtil.isEmpty(cdFc)) {
					fincorpMapper.createFincorp(Fincorp);
					logger.info("금융투자회사["+nmFc+"] 기업정보  생성 하였습니다.");
					cdFc = Fincorp.getCdFc();
					FincorpfcNminfoForm fincorpfcNminfoForm = new FincorpfcNminfoForm();
					fincorpfcNminfoForm.setCd_fc(cdFc);
					fincorpfcNminfoForm.setNm_nm_fc(nmFc);
					fincorpfcNminfoForm.setNm_yn_use("Y");
					fincorpfcNminfoForm.setId_frt("SYS");
					fincorpfcNminfoForm.setId_lst("SYS");
					fincorpMapper.createFincorpfcNminfo(fincorpfcNminfoForm);
				}
				else	{
					fincorpMapper.updateFincorp(Fincorp);
					logger.info("금융투자회사["+nmFc+"] 기업정보 업데이트 하였습니다.");
				}
			}
		}
		else	{
			logger.error("금융투자회사 기업정보 조회를 실패하였습니다.");
		}
	}
	
	public String getFinanceTerms(String no_person, String cd_fc, String uuid, String dn, String email)	{
		JsonObject jsonSendRoot = new JsonObject();
		
		String site = environment.getProperty("service.profile");
		
		String directUrl = environment.getProperty("direct.apiUrl") + "/terms/financeterms/content";
		String directKey = environment.getProperty("direct.apiKey");
		
		URLConnection url = new URLConnection();
		logger.info("getDirectFinanceSearch  : URL[" + directUrl +"], APIkey : "+directKey +"FC "+cd_fc);
		
		PersonVO personVO = null;
		personVO = personMapper.getPersonInfo(no_person);
	
		String noPerson = personVO.getNo_person();
		
		JsonObject jsonPartnerInfo = new JsonObject();
		jsonPartnerInfo.addProperty("comId", environment.getProperty("direct.comId"));
		jsonPartnerInfo.addProperty("srvId", environment.getProperty("direct.srvId"));
		jsonSendRoot.add("partner", jsonPartnerInfo);
		
		JsonObject jsonCommonInfo = new JsonObject();
		JsonObject jsonDevInfo = new JsonObject();
		JsonObject jsonBodyInfo = new JsonObject();
		JsonObject jsonReqParamInfo = new JsonObject();
		
		jsonCommonInfo.addProperty("reqIdConsumer", "test");
		jsonCommonInfo.addProperty("ci", personVO.getKcb_ci());
		jsonCommonInfo.addProperty("certDn", dn);
		jsonSendRoot.add("commonHeader", jsonCommonInfo);
		
		// uuid가 있을 경우 - 핸드폰 접속 , 없을 경우  - PC 접속
		if(uuid != null && uuid.length() > 0)	{
			jsonDevInfo.addProperty("ipAddr", personVO.getHp());
			jsonDevInfo.addProperty("macAddr", uuid);
		}
		else	{
			jsonDevInfo.addProperty("ipAddr", "");
			jsonDevInfo.addProperty("macAddr", "");
		}
		jsonSendRoot.add("devInfo", jsonDevInfo);
					
		jsonReqParamInfo.addProperty("customerBirthDay", personVO.getBgn().substring(0, 8));
		jsonReqParamInfo.addProperty("customerEmail", email);
		jsonReqParamInfo.addProperty("customerPhone", personVO.getHp());
		jsonReqParamInfo.addProperty("customerUserNm", personVO.getNm_person());
		JsonArray list = new JsonArray();
		if(cd_fc != null && cd_fc.length() > 0)	{
			list.add(cd_fc);
		}
		else	{
			
			List<String> stockList = fincorpMapper.listCooconFcCd(codeManager.getCodeId("cd_fin","증권"));
			for (int i = 0; i < stockList.size(); i++) {
				list.add(stockList.get(i));
			}
		}
		jsonReqParamInfo.add("companyList",list);
		jsonBodyInfo.add("requestParameters", jsonReqParamInfo);
		jsonSendRoot.add("financeTermsContentRequestBody", jsonBodyInfo);
		
		logger.info("jsonRoot.toString() : " +jsonSendRoot.toString());
		
		HashMap<String, String> headerMap = new HashMap();
		headerMap.put("Content-Type", "application/json; charset=UTF-8");
		headerMap.put("apikey", directKey);
		
		ReturnClass returnClass = url.sendReqPOST_Direct(directUrl, headerMap, jsonSendRoot.toString());
		
		if(returnClass.getCd_result()  == Constant.SUCCESS)	{
			String data = returnClass.getDes_message();
			logger.info("data : "+ data);
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonRecvRoot = (JsonObject) jsonParser.parse(data);
			JsonObject jsonBodyObject = (JsonObject) jsonRecvRoot.get("financeTermsInfo");
		
			logger.info("jsonBodyObject : "+ jsonBodyObject.toString());
			
			return jsonBodyObject.toString();
		}
		else	{
			logger.error("금융투자회사 금융정보제공동의서 Content 조회를 실패하였습니다.");
		}
		
		return null;
	}
	
	public String sendFinanceTerms(String no_person, String uuid, String dn, String email, String financeTerms, String jwsInfo)	{
		String site = environment.getProperty("service.profile");
		
		String directUrl = environment.getProperty("direct.apiUrl") + "/terms/financeterms/transmit";
		String directKey = environment.getProperty("direct.apiKey");
		
		URLConnection url = new URLConnection();
		logger.info("getDirectFinanceSearch  : URL[" + directUrl +"], APIkey : "+directKey);
		
		JsonObject jsonSendRoot = new JsonObject();
		
		JsonParser jsonParser = new JsonParser();
		
		PersonVO personVO = null;
		personVO = personMapper.getPersonInfo(no_person);
	
		String noPerson = personVO.getNo_person();
		
		JsonObject jsonPartnerInfo = new JsonObject();
		jsonPartnerInfo.addProperty("comId", environment.getProperty("direct.comId"));
		jsonPartnerInfo.addProperty("srvId", environment.getProperty("direct.srvId"));
		jsonSendRoot.add("partner", jsonPartnerInfo);
		
		JsonObject jsonCommonInfo = new JsonObject();
		JsonObject jsonDevInfo = new JsonObject();
		JsonObject jsonBodyInfo = new JsonObject();
		
		jsonCommonInfo.addProperty("reqIdConsumer", "test");
		jsonCommonInfo.addProperty("ci", personVO.getKcb_ci());
		jsonCommonInfo.addProperty("certDn", dn);
		jsonSendRoot.add("commonHeader", jsonCommonInfo);
		
		// uuid가 있을 경우 - 핸드폰 접속 , 없을 경우  - PC 접속
		if(uuid != null && uuid.length() > 0)	{
			jsonDevInfo.addProperty("ipAddr", personVO.getHp());
			jsonDevInfo.addProperty("macAddr", uuid);
		}
		else	{
			jsonDevInfo.addProperty("ipAddr", "");
			jsonDevInfo.addProperty("macAddr", "");
		}
		jsonSendRoot.add("devInfo", jsonDevInfo);
		

		JsonObject jsonfinanceTerms = (JsonObject) jsonParser.parse(financeTerms);
		jsonBodyInfo.add("requestParameters", jsonfinanceTerms);
		
		JsonObject jsonjwsInfo = (JsonObject) jsonParser.parse(jwsInfo);
		jsonSendRoot.add("financeTermsTransmitRequestBody", jsonBodyInfo);
		jsonSendRoot.add("jws", jsonjwsInfo);
		
		logger.info("jsonRoot.toString() : " +jsonSendRoot.toString());
		
		HashMap<String, String> headerMap = new HashMap();
		headerMap.put("Content-Type", "application/json; charset=UTF-8");
		headerMap.put("apikey", directKey);
		
		ReturnClass returnClass = url.sendReqPOST_Direct(directUrl, headerMap, jsonSendRoot.toString());
		
		if(returnClass.getCd_result()  == Constant.SUCCESS)	{
			String data = returnClass.getDes_message();
			logger.info("data : "+ data);
			JsonObject jsonRecvRoot = (JsonObject) jsonParser.parse(data);
			JsonObject jsonTemp = (JsonObject) jsonRecvRoot.get("finanaceTermsTransmitResponseBody");
			JsonObject jsonBodyObject = (JsonObject) jsonTemp.get("requestParameters");
		
			logger.info("jsonBodyObject : "+ jsonBodyObject.toString());
			
			PersonAgreeHistVO personAgreeHistVO = new PersonAgreeHistVO();
			personAgreeHistVO.setNo_person(noPerson);
			personAgreeHistVO.setDt_agree(DateUtil.getCurrentYMD());
			personAgreeHistVO.setCd_agree("04"); // 01:서비스 이용동의(금투사포함), 02:휴대전화 본인인증 동의, 03:마케팅 정보 수신동의, 04:금투사 정보제공동의 전자서명
			personAgreeHistVO.setTerms_content(jsonBodyObject.get("text").getAsString());
			personAgreeHistVO.setTerms_version(jsonBodyObject.get("version").getAsString());
			personAgreeHistVO.setTerms_date(jsonBodyObject.get("termsDate").getAsString());
			personAgreeHistVO.setTerms_start_date(jsonBodyObject.get("termsStartDate").getAsString());
			personAgreeHistVO.setTerms_end_date(jsonBodyObject.get("termsEndDate").getAsString());
			personAgreeHistVO.setId_frt("SYS");
			personMapper.createPersonAgreeHist(personAgreeHistVO);
			//JsonArray jsonArray = (JsonArray)jsonObject.get("financeList");
			JsonArray jsonkArr = (JsonArray)jsonBodyObject.get("companyList");
			//JsonObject jsonkArr = (JsonObject)jsonBodyObject.get("companyList");
			//List<String> fcList = (List)jsonkArr.toString();
			
			for (int i = 0; i < jsonkArr.size(); i++) {
				PersonAgreedtHistVO personAgreedtHistVO = new PersonAgreedtHistVO();
				String cd_fc =  fincorpMapper.getCdFcByCooconFcCd(jsonkArr.get(i).getAsString());
				personAgreedtHistVO.setNo_person(noPerson);
				personAgreedtHistVO.setDt_agree(DateUtil.getCurrentYMD());
				personAgreedtHistVO.setCd_agree("04"); // 01:서비스 이용동의(금투사포함), 02:휴대전화 본인인증 동의, 03:마케팅 정보 수신동의, 04:금투사 정보제공동의 전자서명
				personAgreedtHistVO.setCd_fc(cd_fc);
				personAgreedtHistVO.setId_frt("SYS");
				personMapper.createPersonAgreedtHist(personAgreedtHistVO);
				
			}
		}
		return null;
	}
	
	public String getAccessToken()	{
		String tokenUrl = environment.getProperty("token.apiUrl");
		String key = environment.getProperty("direct.apiKey")+":"+environment.getProperty("direct.secret");
		
		URLConnection url = new URLConnection();
		logger.info("getAccessToken  : URL[" + tokenUrl +"], Key : "+key);
		
		
		 // Base64 인코딩
		byte[] targetBytes = key.getBytes(); 
		Encoder encoder = Base64.getEncoder();
		byte[] encodedBytes = encoder.encode(targetBytes); 
	
		String authorization = "Basic " + new String(encodedBytes);
		
		HashMap<String, String> headerMap = new HashMap();
		headerMap.put("Content-Type", "Application/x-www-form-urlencoded");
		headerMap.put("Authorization", authorization);
		logger.info("Authorization  : " + authorization);
		
		ReturnClass returnClass = url.sendReqPOST_Direct(tokenUrl, headerMap, "");
		
		if(returnClass.getCd_result()  == Constant.SUCCESS)	{
			String data = returnClass.getDes_message();
			logger.info("data : "+ data);
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonRecvRoot = (JsonObject) jsonParser.parse(data);
			
			String token = jsonRecvRoot.get("token_type").getAsString() + " " + jsonRecvRoot.get("access_token").getAsString();
			logger.info("token  : " + token);
						
			return token;
		}
		else	{
			logger.error("금융투자회사 Access Token 조회를 실패하였습니다.");
		}
		
		return null;
	}
	
	public ReturnClass checkFinance(String no_person, String cd_fc, String uuid, String dn, String token)	{
		ReturnClass returnClass = null;
		if(cd_fc != null && cd_fc.length() > 0)	{

			String comAlias = fincorpMapper.getComAliasCdByCdFc(fincorpMapper.getCdFcByCooconFcCd(cd_fc));
			returnClass = createFinanceAccount(no_person, uuid, dn, token, comAlias);
		}
		else	{
			List<String> stockList = fincorpMapper.listComAlias(codeManager.getCodeId("cd_fin", "증권"));
			
			for (int i= 0; i< stockList.size(); i++){
				createFinanceAccount(no_person, uuid, dn, token, stockList.get(i));
			}
		}
		return returnClass;
	}
	
	public ReturnClass createFinanceAccount(String no_person, String uuid, String dn, String token, String com_alias)	{
		String financeUrl = String.format(environment.getProperty("account.apiUrl"), com_alias)+"/vtaccount/create";
		
		PersonVO personVO = personMapper.getPersonInfo(no_person);
		String ci = personVO.getKcb_ci();
		String hp = personVO.getHp();
		
		//테스트용 임시데이터=======================================================
		uuid = "1866DA0D99D6";
		dn = "testdn123";
		token = "Bearer fbf794d3-9be6-4163-9df6-927d43736470";
		ci = "1234567890";
		hp = "192168010001";
		//테스트용 임시데이터=======================================================
		
		JsonObject jsonSendRoot = new JsonObject();
		JsonObject jsonPartnerInfo = new JsonObject();
		jsonPartnerInfo.addProperty("comId", environment.getProperty("direct.comId"));
		jsonPartnerInfo.addProperty("srvId", environment.getProperty("direct.srvId"));
		jsonSendRoot.add("partner", jsonPartnerInfo);
		
		JsonObject jsonCommonInfo = new JsonObject();
		JsonObject jsonDevInfo = new JsonObject();
		
		jsonCommonInfo.addProperty("reqIdConsumer", "test");
		jsonCommonInfo.addProperty("ci", ci);
		jsonCommonInfo.addProperty("certDn", dn);
		jsonSendRoot.add("commonHeader", jsonCommonInfo);
		
		// uuid가 있을 경우 - 핸드폰 접속 , 없을 경우  - PC 접속
		if(uuid != null && uuid.length() > 0)	{
			jsonDevInfo.addProperty("ipAddr", hp);
			jsonDevInfo.addProperty("macAddr", uuid);
		}
		else	{
			jsonDevInfo.addProperty("ipAddr", "");
			jsonDevInfo.addProperty("macAddr", "");
		}
		jsonSendRoot.add("devInfo", jsonDevInfo);
					
		logger.info("jsonRoot.toString() : " +jsonSendRoot.toString());
			
		URLConnection url = new URLConnection();
		logger.info("createFinanceAccount  : URL[" + financeUrl +"], token : "+token);
		
		HashMap<String, String> headerMap = new HashMap();
		headerMap.put("Format", "JSON <application/json; charset=utf-8>");
		headerMap.put("Content-Type", "Application/json");
		headerMap.put("Authorization", token);
				
		ReturnClass returnClass = url.sendReqPOST_Direct(financeUrl, headerMap, jsonSendRoot.toString());
		
		String data = returnClass.getDes_message();
		logger.info("data : "+ data);
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonRecvRoot = (JsonObject) jsonParser.parse(data);
		
		if(returnClass.getCd_result()  == Constant.SUCCESS)	{
			JsonArray jsonArry = (JsonArray)jsonRecvRoot.get("accountList");
			if(jsonArry != null && jsonArry.size() > 0)	{
				String cd_fc = fincorpMapper.getCdFcByComAlias(com_alias);
				
				//계좌 내역 추가 전 계좌 삭제상태로 변경
				StockListVO stockListVO = new StockListVO();
				stockListVO.setNo_person(no_person);
				stockListVO.setCd_fc(cd_fc);
				scrapMapper.updateStockList(stockListVO);
				//계좌 내역 추가
				for(int i=0; i<jsonArry.size(); i++)	{
					JsonObject object = (JsonObject)jsonArry.get(i);
					StockListVO stockList = new StockListVO();
					stockList.setNo_person(no_person);
					stockList.setCd_fc(cd_fc);
					stockList.setAccno(object.get("vtAccNo").getAsString());
					stockList.setAlias(object.get("vtAccAlias").getAsString());
					stockList.setReal_accno(object.get("realAccNo").getAsString());
					stockList.setAcc_type(object.get("accType").getAsString());
					stockList.setState(object.get("status").getAsString());
					stockList.setYn_delete("N");
					scrapMapper.createStockList(stockList);
				}
				//금융사 연동 정보 추가
				FcLinkInfoVO fcLinkInfoVO = new FcLinkInfoVO();
	        	String cd_agency = codeManager.getCodeId("cd_agency", "증권");
	        	String type_login = codeManager.getCodeId("type_login", "CERT");
	        	fcLinkInfoVO.setNo_person(no_person);
	        	fcLinkInfoVO.setCn(dn);
	        	fcLinkInfoVO.setCd_fc(cd_fc);
	        	fcLinkInfoVO.setCd_agency(cd_agency);
	        	fcLinkInfoVO.setType_login(type_login);
	        	fcLinkInfoVO.setYn_link("Y");
	        	fcLinkInfoVO.setCd_link_stat("00");
	        	fcLinkInfoVO.setId_frt(no_person);
	        	fcLinkInfoVO.setId_lst(no_person);
	        	scrapMapper.createFcLinkInfo(fcLinkInfoVO);
			}
		}
		else	{
			logger.error("금융투자회사 가상 계좌 개설이 실패하였습니다.");
		}
		return returnClass;
	}
	
	public String getFinanceBalance(HashMap<String, String> headerMap, String body, StockSummaryVO stockSummaryVO, String type)	{
		String com_alias = fincorpMapper.getComAliasCdByCdFc(stockSummaryVO.getCd_fc());
		String financeUrl = String.format(environment.getProperty("account.apiUrl"), com_alias)+"/account/balance/search";
		
		String no_person = stockSummaryVO.getNo_person();
		String cd_fc = stockSummaryVO.getCd_fc();
		String accno = stockSummaryVO.getAccno();
		
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonSendRoot = (JsonObject) jsonParser.parse(body);
		JsonObject jsonReqPram = new JsonObject();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("assetType", type);
		jsonObject.addProperty("count", 0);
		jsonObject.addProperty("page", "null");
		jsonReqPram.add("requestParameters", jsonObject);
		jsonSendRoot.add("balanceRequestBody", jsonReqPram);
		
		logger.info("getFinanceBalance() : " + jsonSendRoot.toString());
		
		URLConnection url = new URLConnection();
		ReturnClass returnClass = url.sendReqPOST_Direct(financeUrl, headerMap, jsonSendRoot.toString());
		String data = returnClass.getDes_message();
		logger.info("Receive Data : "+ data);
		
		JsonObject jsonRecvRoot = (JsonObject) jsonParser.parse(data);
		
		if(returnClass.getCd_result()  == Constant.SUCCESS)	{
			JsonObject jsonBalance = (JsonObject)jsonRecvRoot.get("balanceList");
			if(jsonBalance == null)	{
				return Constant.FAILED;
			}
			JsonObject jsonSummary;
			JsonArray jsonArry;
			switch(type)
			{
				case "CASH":
					jsonSummary = (JsonObject)jsonBalance.get("summary");
					if(jsonSummary == null)	{
						return Constant.FAILED;
					}
					if(jsonSummary.get("cashBalance") != null && !jsonSummary.get("cashBalance").isJsonNull())	{
						stockSummaryVO.setCashbalance(jsonSummary.get("cashBalance").getAsString());
					}
					if(jsonSummary.get("d1") != null && !jsonSummary.get("d1").isJsonNull())	{
						stockSummaryVO.setD1(jsonSummary.get("d1").getAsString());
					}
					if(jsonSummary.get("d2") != null && !jsonSummary.get("d2").isJsonNull())	{
						stockSummaryVO.setD2(jsonSummary.get("d2").getAsString());
					}
					if(jsonSummary.get("substitute") != null && !jsonSummary.get("substitute").isJsonNull())	{
						stockSummaryVO.setSubstitute(jsonSummary.get("substitute").getAsString());
					}
					if(jsonSummary.get("subsMargin") != null && !jsonSummary.get("subsMargin").isJsonNull())	{
						stockSummaryVO.setSubsmargin(jsonSummary.get("subsMargin").getAsString());
					}
					if(jsonSummary.get("loanCredit") != null && !jsonSummary.get("loanCredit").isJsonNull())	{
						stockSummaryVO.setLoancredit(jsonSummary.get("loanCredit").getAsString());
					}
					if(jsonSummary.get("valAtTrade") != null && !jsonSummary.get("valAtTrade").isJsonNull())	{
						stockSummaryVO.setValattrade(jsonSummary.get("valAtTrade").getAsString());
					}
					if(jsonSummary.get("valueAtCur") != null && !jsonSummary.get("valueAtCur").isJsonNull())	{
						stockSummaryVO.setValueatcur(jsonSummary.get("valueAtCur").getAsString());
					}
					if(jsonSummary.get("proLoss") != null && !jsonSummary.get("proLoss").isJsonNull())	{
						stockSummaryVO.setProloss(jsonSummary.get("proLoss").getAsString());
					}
					if(jsonSummary.get("totalAccVal") != null && !jsonSummary.get("totalAccVal").isJsonNull())	{
						stockSummaryVO.setTotalaccval(jsonSummary.get("totalAccVal").getAsString());
					}
					if(jsonSummary.get("cashAvWithdraw") != null && !jsonSummary.get("cashAvWithdraw").isJsonNull())	{
						stockSummaryVO.setCashavwithdraw(jsonSummary.get("cashAvWithdraw").getAsString());
					}
					scrapMapper.insertStockSummary(stockSummaryVO);
				break;
				case "EQTY":
					jsonArry = (JsonArray)jsonBalance.get("equityList");
					if(jsonArry == null)	{
						return Constant.FAILED;
					}
					for(int i=0; i<jsonArry.size(); i++)	{
						JsonObject object = (JsonObject)jsonArry.get(i);
						StockEquitylistVO stockEquitylistVO = new StockEquitylistVO();
						stockEquitylistVO.setNo_person(no_person);
						stockEquitylistVO.setCd_fc(cd_fc);
						stockEquitylistVO.setAccno(accno);
						if(object.get("assetType") != null && !object.get("assetType").isJsonNull())	{
							stockEquitylistVO.setAssettype(object.get("assetType").getAsString());
						}
						if(object.get("isinCode") != null && !object.get("isinCode").isJsonNull())	{
							stockEquitylistVO.setIsincode(object.get("isinCode").getAsString());
						}
						if(object.get("qty") != null && !object.get("qty").isJsonNull())	{
							stockEquitylistVO.setQty(object.get("qty").getAsString());
						}
						if(object.get("tradeType") != null && !object.get("tradeType").isJsonNull())	{
							stockEquitylistVO.setTradetype(object.get("tradeType").getAsString());
						}
						if(object.get("valAtTrade") != null && !object.get("valAtTrade").isJsonNull())	{
							stockEquitylistVO.setValattrade(object.get("valAtTrade").getAsString());
						}
						if(object.get("valAtCur") != null && !object.get("valAtCur").isJsonNull())	{
							stockEquitylistVO.setValatcur(object.get("valAtCur").getAsString());
						}
						if(object.get("proLoss") != null && !object.get("proLoss").isJsonNull())	{
							stockEquitylistVO.setProloss(object.get("proLoss").getAsString());
						}
						if(object.get("earningRate") != null && !object.get("earningRate").isJsonNull())	{
							stockEquitylistVO.setEarningrate(object.get("earningRate").getAsString());
						}
						scrapMapper.insertStockEquitylist(stockEquitylistVO);
					}
				break;
				case "FUND":
					jsonArry = (JsonArray)jsonBalance.get("fundList");
					if(jsonArry == null)	{
						return Constant.FAILED;
					}
					for(int i=0; i<jsonArry.size(); i++)	{
						JsonObject object = (JsonObject)jsonArry.get(i);
						StockFundlistVO stockFundlistVO = new StockFundlistVO();
						stockFundlistVO.setNo_person(no_person);
						stockFundlistVO.setCd_fc(cd_fc);
						stockFundlistVO.setAccno(accno);
						if(object.get("fundCode") != null && !object.get("fundCode").isJsonNull())	{
							stockFundlistVO.setFundcode(object.get("fundCode").getAsString());
						}
						if(object.get("fundName") != null && !object.get("fundName").isJsonNull())	{
							stockFundlistVO.setFundname(object.get("fundName").getAsString());
						}
						if(object.get("valAtTrade") != null && !object.get("valAtTrade").isJsonNull())	{
							stockFundlistVO.setValattrade(object.get("valAtTrade").getAsString());
						}
						if(object.get("valAtCur") != null && !object.get("valAtCur").isJsonNull())	{
							stockFundlistVO.setValatcur(object.get("valAtCur").getAsString());
						}
						if(object.get("proLoss") != null && !object.get("proLoss").isJsonNull())	{
							stockFundlistVO.setProloss(object.get("proLoss").getAsString());
						}
						if(object.get("firstDateBuy") != null && !object.get("firstDateBuy").isJsonNull())	{
							stockFundlistVO.setFirstdatebuy(object.get("firstDateBuy").getAsString());
						}
						if(object.get("lastDateBuy") != null && !object.get("lastDateBuy").isJsonNull())	{
							stockFundlistVO.setLastdatebuy(object.get("lastDateBuy").getAsString());
						}
						if(object.get("maturity") != null && !object.get("maturity").isJsonNull())	{
							stockFundlistVO.setMaturity(object.get("maturity").getAsString());
						}
						if(object.get("earningRate") != null && !object.get("earningRate").isJsonNull())	{
							stockFundlistVO.setEarningrate(object.get("earningRate").getAsString());
						}
						scrapMapper.insertStockFundlist(stockFundlistVO);
					}
				break;
				case "ETC":
					jsonArry = (JsonArray)jsonBalance.get("etcList");
					if(jsonArry == null)	{
						return Constant.FAILED;
					}
					for(int i=0; i<jsonArry.size(); i++)	{
						JsonObject object = (JsonObject)jsonArry.get(i);
						StockEtclistVO stockEtclistVO = new StockEtclistVO();
						stockEtclistVO.setNo_person(no_person);
						stockEtclistVO.setCd_fc(cd_fc);
						stockEtclistVO.setAccno(accno);
						if(object.get("assetType") != null && !object.get("assetType").isJsonNull())	{
							stockEtclistVO.setAssettype(object.get("assetType").getAsString());
						}
						if(object.get("assetName") != null && !object.get("assetName").isJsonNull())	{
							stockEtclistVO.setAssetname(object.get("assetName").getAsString());
						}
						if(object.get("isinCode") != null && !object.get("isinCode").isJsonNull())	{
							stockEtclistVO.setIsincode(object.get("isinCode").getAsString());
						}
						if(object.get("qty") != null && !object.get("qty").isJsonNull())	{
							stockEtclistVO.setQty(object.get("qty").getAsString());
						}
						if(object.get("tradeType") != null && !object.get("tradeType").isJsonNull())	{
							stockEtclistVO.setTradetype(object.get("tradeType").getAsString());
						}
						if(object.get("valAtTrade") != null && !object.get("valAtTrade").isJsonNull())	{
							stockEtclistVO.setValattrade(object.get("valAtTrade").getAsString());
						}
						if(object.get("valueAtCur") != null && !object.get("valueAtCur").isJsonNull())	{
							stockEtclistVO.setValatcur(object.get("valueAtCur").getAsString());
						}
						if(object.get("earningRate") != null && !object.get("earningRate").isJsonNull())	{
							stockEtclistVO.setEarningrate(object.get("earningRate").getAsString());
						}
						scrapMapper.insertStockEtclist(stockEtclistVO);
					}
				break;
			}
		}
		else	{
			logger.error("금융투자회사 계좌잔고 조회 를 실패하였습니다.");
			return Constant.FAILED;
		}
		return Constant.SUCCESS;
	}
	
	public String getFinanceInterest(HashMap<String, String> headerMap, String body, StockInterestIsinVO stockInterestIsinVO)	{
		String com_alias = fincorpMapper.getComAliasCdByCdFc(stockInterestIsinVO.getCd_fc());
		String financeUrl = String.format(environment.getProperty("account.apiUrl"), com_alias)+"/account/interest/search";
		
		String no_person = stockInterestIsinVO.getNo_person();
		String cd_fc = stockInterestIsinVO.getCd_fc();
		String accno = stockInterestIsinVO.getAccno();
		
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonSendRoot = (JsonObject) jsonParser.parse(body);
		JsonObject jsonReqPram = new JsonObject();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("assetType", "ALL"); //문서상 Type가 한개여서 ALL로 하드코딩
		jsonObject.addProperty("count", 0);
		jsonObject.addProperty("page", "null");
		jsonReqPram.add("requestParameters", jsonObject);
		jsonSendRoot.add("interestSymbolListRequestBody", jsonReqPram);
		
		logger.info("getFinanceInterest() : " + jsonSendRoot.toString());
		
		URLConnection url = new URLConnection();
		ReturnClass returnClass = url.sendReqPOST_Direct(financeUrl, headerMap, jsonSendRoot.toString());
		String data = returnClass.getDes_message();
		logger.info("Receive Data : "+ data);
		
		JsonObject jsonRecvRoot = (JsonObject) jsonParser.parse(data);
		
		if(returnClass.getCd_result()  == Constant.SUCCESS)	{
			JsonArray jsonGroupArray = (JsonArray)jsonRecvRoot.get("groupList");
			if(jsonGroupArray == null)	{
				return Constant.FAILED;
			}
			for(int i=0; i<jsonGroupArray.size(); i++)	{
				JsonObject object = (JsonObject)jsonGroupArray.get(i);
				StockInterestIsinVO stockInterestIsin = new StockInterestIsinVO();
				stockInterestIsin.setNo_person(no_person);
				stockInterestIsin.setCd_fc(cd_fc);
				stockInterestIsin.setAccno(accno);
				logger.info("Object  : "+ object.toString());
				if(object.get("groupName") != null && !object.get("groupName").isJsonNull())	{
					stockInterestIsin.setGroup_name(object.get("groupName").getAsString());
				}
				if(object.get("isinCode") != null && !object.get("isinCode").isJsonNull())	{
					JsonArray jsonIsinArray = (JsonArray)object.get("isinCode");
					stockInterestIsin.setList_isincode(jsonIsinArray.toString());
				}
				scrapMapper.insertStockInterestIsin(stockInterestIsin);
			}
		}
		else	{
			logger.error("금융투자회사 관심종목 조회 를 실패하였습니다.");
			return Constant.FAILED;
		}
		return Constant.SUCCESS;
	}
	
	public String getFinanceTransaction(HashMap<String, String> headerMap, String body, StockTransactionVO stockTransactionVO)	{
		String com_alias = fincorpMapper.getComAliasCdByCdFc(stockTransactionVO.getCd_fc());
		String financeUrl = String.format(environment.getProperty("account.apiUrl"), com_alias)+"/account/transaction/search";
		
		String no_person = stockTransactionVO.getNo_person();
		String cd_fc = stockTransactionVO.getCd_fc();
		String accno = stockTransactionVO.getAccno();
		
		// 기존 조회된 내역에서 
		String toDay = DateUtil.getCurrentYMD();
		String fromDate;
		String maxDate = scrapMapper.getMaxDateStockTransaction(stockTransactionVO);
		if(maxDate != null && maxDate.length()==8)	{
			fromDate = DateUtil.addDays(scrapMapper.getMaxDateStockTransaction(stockTransactionVO), 1);
		}
		else	{
			//fromDate = DateUtil.addMonths(toDay, -3);
			fromDate = "20150801";
		}
		String toDate = DateUtil.addDays(toDay, -1);

		// 조회된 내역이 이미 있으므로 종료
		if(Long.parseLong(fromDate) >= Long.parseLong(toDate))	{
			return Constant.SUCCESS;
		}
		
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonSendRoot = (JsonObject) jsonParser.parse(body);
		JsonObject jsonReqPram = new JsonObject();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("fromDate", fromDate);
		jsonObject.addProperty("toDate", toDate);
		jsonObject.addProperty("isinCode", "");
		jsonObject.addProperty("side", "ALL");
		jsonObject.addProperty("count", 0);
		jsonObject.addProperty("page", "null");
		jsonReqPram.add("requestParameters", jsonObject);
		jsonSendRoot.add("transactionHistoryRequestBody", jsonReqPram);
		
		logger.info("getFinanceTransaction() : " + jsonSendRoot.toString());
		
		URLConnection url = new URLConnection();
		ReturnClass returnClass = url.sendReqPOST_Direct(financeUrl, headerMap, jsonSendRoot.toString());
		String data = returnClass.getDes_message();
		logger.info("Receive Data : "+ data);
		
		JsonObject jsonRecvRoot = (JsonObject) jsonParser.parse(data);
		
		if(returnClass.getCd_result()  == Constant.SUCCESS)	{
			JsonArray jsonGroupArray = (JsonArray)jsonRecvRoot.get("transList");
			if(jsonGroupArray == null)	{
				return Constant.FAILED;
			}
			for(int i=0; i<jsonGroupArray.size(); i++)	{
				JsonObject object = (JsonObject)jsonGroupArray.get(i);
				StockTransactionVO stockTransaction = new StockTransactionVO();
				stockTransaction.setNo_person(no_person);
				stockTransaction.setCd_fc(cd_fc);
				stockTransaction.setAccno(accno);
				logger.info("Object  : "+ object.toString());
				if(object.get("isinCode") != null && !object.get("isinCode").isJsonNull())	{
					stockTransaction.setIsincode(object.get("isinCode").getAsString());
				}
				if(object.get("transDate") != null && !object.get("transDate").isJsonNull())	{
					stockTransaction.setTransdate(object.get("transDate").getAsString());
				}
				if(object.get("transType") != null && !object.get("transType").isJsonNull())	{
					stockTransaction.setTranstype(object.get("transType").getAsString());
				}
				if(object.get("changeAmt") != null && !object.get("changeAmt").isJsonNull())	{
					stockTransaction.setChangeamt(object.get("changeAmt").getAsString());
				}
				if(object.get("changeQty") != null && !object.get("changeQty").isJsonNull())	{
					stockTransaction.setChangeqty(object.get("changeQty").getAsString());
				}
				if(object.get("qty") != null && !object.get("qty").isJsonNull())	{
					stockTransaction.setQty(object.get("qty").getAsString());
				}
				scrapMapper.insertStockTransaction(stockTransaction);
			}
		}
		else	{
			logger.error("금융투자회사 거래내역  조회 를 실패하였습니다.");
			return Constant.FAILED;
		}
		return Constant.SUCCESS;
	}
	
	public String startScrapFinance(String no_person, String uuid)	{
		PersonVO personVO = personMapper.getPersonInfo(no_person);
		String ci = personVO.getKcb_ci();
		String hp = personVO.getHp();
		
		String cd_agency = codeManager.getCodeId("cd_agency","증권");
    	
    	FcLinkInfoVO fcLinkInfoVO = new FcLinkInfoVO();
		fcLinkInfoVO.setNo_person(no_person);
		fcLinkInfoVO.setCd_agency(cd_agency);
		List<FcLinkInfoVO> fcLinkInfoList = scrapMapper.getFcLinkInfo(fcLinkInfoVO);
		
		if(fcLinkInfoList == null || fcLinkInfoList.size() == 0)	{
			return Constant.SUCCESS;
		}
		
		String token = getAccessToken();
		if(token == null || token.length() <= 0)	{
			logger.error("generate token failed");
			return Constant.FAILED;
		}
		
		for (int i = 0; i < fcLinkInfoList.size(); i++) {
			FcLinkInfoVO fcLinkInfo = fcLinkInfoList.get(i);
			String cd_fc = fcLinkInfo.getCd_fc();
			String dn = fcLinkInfo.getCn();
			String com_alias = fincorpMapper.getComAliasCdByCdFc(cd_fc);
			
			logger.debug("cd_fc		:" + cd_fc);
			logger.debug("com_alias	:" + com_alias);
			logger.debug("dn		:" + dn);
			
			// 조회내역 저장 전 데이터 삭제
			StockSummaryVO stockSummaryVO = new StockSummaryVO();
			stockSummaryVO.setNo_person(no_person);
			stockSummaryVO.setCd_fc(cd_fc);
			scrapMapper.insertStockSummaryHist(stockSummaryVO);
			scrapMapper.deleteStockSummary(stockSummaryVO);
			StockEquitylistVO stockEquitylistVO = new StockEquitylistVO();
			stockEquitylistVO.setNo_person(no_person);
			stockEquitylistVO.setCd_fc(cd_fc);
			scrapMapper.insertStockEquitylistHist(stockEquitylistVO);
			scrapMapper.deleteStockEquitylist(stockEquitylistVO);
			StockFundlistVO stockFundlistVO = new StockFundlistVO();
			stockFundlistVO.setNo_person(no_person);
			stockFundlistVO.setCd_fc(cd_fc);
			scrapMapper.insertStockFundlistHist(stockFundlistVO);
			scrapMapper.deleteStockFundlist(stockFundlistVO);
			StockEtclistVO stockEtclistVO = new StockEtclistVO();
			stockEtclistVO.setNo_person(no_person);
			stockEtclistVO.setCd_fc(cd_fc);
			scrapMapper.insertStockEtclistHist(stockEtclistVO);
			scrapMapper.deleteStockEtclist(stockEtclistVO);
			
			StockInterestIsinVO stockInterestIsinVO = new StockInterestIsinVO();
			stockInterestIsinVO.setNo_person(no_person);
			stockInterestIsinVO.setCd_fc(cd_fc);
			scrapMapper.insertStockInterestIsinHist(stockInterestIsinVO);
			scrapMapper.deleteStockInterestIsin(stockInterestIsinVO);
			
			StockTransactionVO stockTransactionVO = new StockTransactionVO();
			stockTransactionVO.setNo_person(no_person);
			stockTransactionVO.setCd_fc(cd_fc);
			
			// 계좌 조회 전 가상계좌 발급으로 가상계좌 내역 갱신  (uuid 처리 여부)
			ReturnClass returnClass = createFinanceAccount(no_person, uuid, dn, token, com_alias);
			if(returnClass.getCd_result()  == Constant.FAILED)	{
				logger.error("가상계좌 생성 오류 :" + cd_fc);
				String data = returnClass.getDes_message();
				logger.info("data : "+ data);
				JsonParser jsonParser = new JsonParser();
				JsonObject jsonRecvRoot = (JsonObject) jsonParser.parse(data);
				
				//금융사 연동 정보 추가
	        	fcLinkInfo.setYn_link("Y");
	        	fcLinkInfo.setCd_fc(cd_fc);
	        	fcLinkInfo.setCd_link_stat("99");
	        	fcLinkInfo.setId_frt(no_person);
	        	fcLinkInfo.setId_lst(no_person);
	        	scrapMapper.createFcLinkInfo(fcLinkInfo);
			}
			else if(returnClass.getCd_result()  == Constant.SUCCESS)	{
				StockListVO stockListVO = new StockListVO();
				stockListVO.setNo_person(no_person);
				stockListVO.setCd_fc(cd_fc);
				
				//테스트용 임시데이터=======================================================
				uuid = "1866DA0D99D6";
				dn = "testdn123";
				token = "Bearer fbf794d3-9be6-4163-9df6-927d43736470";
				ci = "1234567890";
				hp = "192168010001";
				//테스트용 임시데이터=======================================================
				
				//계좌 조회 공통 Request heder 생성			
				HashMap<String, String> headerMap = new HashMap();
				headerMap.put("Format", "JSON <application/json; charset=utf-8>");
				headerMap.put("Content-Type", "Application/json");
				headerMap.put("Authorization", token);
				
				//계좌 조회 공통 Request Body 생성
				JsonObject jsonBody = new JsonObject();
				JsonObject jsonPartnerInfo = new JsonObject();
				jsonPartnerInfo.addProperty("comId", environment.getProperty("direct.comId"));
				jsonPartnerInfo.addProperty("srvId", environment.getProperty("direct.srvId"));
				jsonBody.add("partner", jsonPartnerInfo);
				
				JsonObject jsonCommonInfo = new JsonObject();
				JsonObject jsonDevInfo = new JsonObject();
				
				jsonCommonInfo.addProperty("reqIdConsumer", "test");
				jsonCommonInfo.addProperty("ci", ci);
				jsonCommonInfo.addProperty("certDn", dn);
				jsonBody.add("commonHeader", jsonCommonInfo);
				
				// uuid가 있을 경우 - 핸드폰 접속 , 없을 경우  - PC 접속
				if(uuid != null && uuid.length() > 0)	{
					jsonDevInfo.addProperty("ipAddr", hp);
					jsonDevInfo.addProperty("macAddr", uuid);
				}
				else	{
					jsonDevInfo.addProperty("ipAddr", "");
					jsonDevInfo.addProperty("macAddr", "");
				}
				jsonBody.add("devInfo", jsonDevInfo);
				
				List<StockListVO> stockList = scrapMapper.listStockList(stockListVO);
				for (StockListVO stockListInfo : stockList) {
					logger.debug("stockListInfo.getCd_fc	:" + stockListInfo.getCd_fc());
					logger.debug("stockListInfo.getAccno	:" + stockListInfo.getAccno());
					logger.debug("stockListInfo.getAcc_type	:" + stockListInfo.getAcc_type());
					
					JsonObject jsonAccount = new JsonObject();
					jsonAccount.addProperty("vtAccNo", stockListInfo.getAccno());
					jsonBody.add("accInfo", jsonAccount);
					
					stockSummaryVO.setAccno(stockListInfo.getAccno());
					getFinanceBalance(headerMap, jsonBody.toString(), stockSummaryVO, "CASH");
					getFinanceBalance(headerMap, jsonBody.toString(), stockSummaryVO, "EQTY");
					getFinanceBalance(headerMap, jsonBody.toString(), stockSummaryVO, "FUND");
					getFinanceBalance(headerMap, jsonBody.toString(), stockSummaryVO, "ETC");
					
					stockInterestIsinVO.setAccno(stockListInfo.getAccno());
					getFinanceInterest(headerMap, jsonBody.toString(), stockInterestIsinVO);
					
					stockTransactionVO.setAccno(stockListInfo.getAccno());
					getFinanceTransaction(headerMap, jsonBody.toString(), stockTransactionVO);
				}
			}
		}
		return Constant.SUCCESS;
	}
	
	public String createScrapFcList(String data)	{
		JSONObject jsonObject = new JSONObject();
		logger.debug("createScrapFcList.crz");
		logger.info("금융사 스크래핑 정보   : " + data);
		
		Gson gson = new Gson();
		AppFcLinkInfo appFcLinkInfo = new AppFcLinkInfo();
		FcLinkInfoVO fcLinkInfo = new FcLinkInfoVO();
		PersonCertificateInfoVO personCertificateInfoVO = new PersonCertificateInfoVO(); 
		
		String no_person = "";
		String cn = "";
		int	nResult;
		
		appFcLinkInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppFcLinkInfo.class);
        no_person 	= appFcLinkInfo.getNO_PERSON();
        cn 	= appFcLinkInfo.getCN();
        
        logger.debug("no_person  :" + no_person);
        logger.debug("cn         :" + cn);

        //고객 인증서 정보 테이블에 저장
        personCertificateInfoVO.setNo_person(no_person);
        personCertificateInfoVO.setCn(cn);
        personCertificateInfoVO.setCertificate_agency(appFcLinkInfo.getCERTIFICATE_AGENCY());
        personCertificateInfoVO.setDt_expire(appFcLinkInfo.getDT_EXPIRE());
        personCertificateInfoVO.setYn_use("Y");
        personCertificateInfoVO.setId_frt(no_person);
        personMapper.createPersonCertificateInfo(personCertificateInfoVO);
        
        //기존 금융사 연계 정보 히스토리 테이블에 저장
        fcLinkInfo.setNo_person(no_person);
        fcLinkInfo.setCn(cn);
        nResult = scrapMapper.createFcLinkInfoHist(fcLinkInfo);
        logger.debug("scrapMapper.createFcLinkInfoHist return  :" + nResult);
        
        //국세청 자동 스크래핑 정보 테이블에 반영 
        if(appFcLinkInfo.getNTS_LINK_INFO() != null){
        	FcLinkInfoVO fcLinkInfoVO = appFcLinkInfo.getNTS_LINK_INFO();
        	//String cd_fc = codeManager.getCodeId("cd_coocon_fc", fcLinkInfoVO.getCd_fc());
        	String cd_fc = fincorpMapper.getCdFcByCooconFcCd(fcLinkInfoVO.getCd_fc());
        	String cd_agency = codeManager.getCodeId("cd_agency", "국세청");
        	String type_login = codeManager.getCodeId("type_login", fcLinkInfoVO.getType_login());
        	fcLinkInfoVO.setNo_person(no_person);
        	fcLinkInfoVO.setCn(cn);
        	fcLinkInfoVO.setCd_fc(cd_fc);
        	fcLinkInfoVO.setCd_agency(cd_agency);
        	fcLinkInfoVO.setType_login(type_login);
        	if(fcLinkInfoVO.getYn_link().equals("Y"))	{
        		fcLinkInfoVO.setCd_link_stat("00");
        	}
        	else	{
        		fcLinkInfoVO.setCd_link_stat("99");
        	}
        	fcLinkInfoVO.setRsn_link_message(fcLinkInfoVO.getError_message());
        	fcLinkInfoVO.setId_frt(no_person);
        	fcLinkInfoVO.setId_lst(no_person);
        	
        	nResult = scrapMapper.createFcLinkInfo(fcLinkInfoVO);
    		logger.debug("scrapManager.createFcLinkInfo return  :" + nResult);
        }

        //은행사 자동 스크래핑 정보 테이블에 반영 
        if(appFcLinkInfo.getBANK_LINK_INFO() != null){
        	List<FcLinkInfoVO> FC_LINK_INFO = appFcLinkInfo.getBANK_LINK_INFO();
            for (FcLinkInfoVO fcLinkInfoVO : FC_LINK_INFO) {
            	//String cd_fc = codeManager.getCodeId("cd_coocon_fc", fcLinkInfoVO.getCd_fc());
            	String cd_fc = fincorpMapper.getCdFcByCooconFcCd(fcLinkInfoVO.getCd_fc());
            	String cd_agency = codeManager.getCodeId("cd_agency", "은행");
            	String type_login = codeManager.getCodeId("type_login", fcLinkInfoVO.getType_login());
            	fcLinkInfoVO.setNo_person(no_person);
            	fcLinkInfoVO.setCn(cn);
            	fcLinkInfoVO.setCd_fc(cd_fc);
            	fcLinkInfoVO.setCd_agency(cd_agency);
            	fcLinkInfoVO.setType_login(type_login);
            	if(fcLinkInfoVO.getYn_link().equals("Y"))	{
            		fcLinkInfoVO.setCd_link_stat("00");
            	}
            	else	{
            		fcLinkInfoVO.setCd_link_stat("99");
            	}
            	fcLinkInfoVO.setRsn_link_message(fcLinkInfoVO.getError_message());
            	fcLinkInfoVO.setId_frt(no_person);
            	fcLinkInfoVO.setId_lst(no_person);
            	
            	nResult = scrapMapper.createFcLinkInfo(fcLinkInfoVO);
        		logger.debug("scrapManager.createFcLinkInfo return  :" + nResult);
            }
        }
        
        //카드사 자동 스크래핑 정보 테이블에 반영 
        if(appFcLinkInfo.getCARD_LINK_INFO() != null){
        	List<FcLinkInfoVO> FC_LINK_INFO = appFcLinkInfo.getCARD_LINK_INFO();
            for (FcLinkInfoVO fcLinkInfoVO : FC_LINK_INFO) {
            	//String cd_fc = codeManager.getCodeId("cd_coocon_fc", fcLinkInfoVO.getCd_fc());
            	String cd_fc = fincorpMapper.getCdFcByCooconFcCd(fcLinkInfoVO.getCd_fc());
            	String cd_agency = codeManager.getCodeId("cd_agency", "카드");
            	String type_login = codeManager.getCodeId("type_login", fcLinkInfoVO.getType_login());
            	fcLinkInfoVO.setNo_person(no_person);
            	fcLinkInfoVO.setCn(cn);
            	fcLinkInfoVO.setCd_fc(cd_fc);
            	fcLinkInfoVO.setCd_agency(cd_agency);
            	fcLinkInfoVO.setType_login(type_login);
            	if(fcLinkInfoVO.getYn_link().equals("Y"))	{
            		fcLinkInfoVO.setCd_link_stat("00");
            	}
            	else	{
            		fcLinkInfoVO.setCd_link_stat("99");
            	}
            	fcLinkInfoVO.setRsn_link_message(fcLinkInfoVO.getError_message());
            	fcLinkInfoVO.setId_frt(no_person);
            	fcLinkInfoVO.setId_lst(no_person);
            	
            	nResult = scrapMapper.createFcLinkInfo(fcLinkInfoVO);
        		logger.debug("scrapManager.createFcLinkInfo return  :" + nResult);
            }
        }
        jsonObject.put("result", Constant.SUCCESS);
        return jsonObject.toString();
	}
	
	@Override
	public String updateScrapFcList(String data)	{
		JSONObject jsonObject = new JSONObject();
		logger.debug("updateScrapFc.crz");
		logger.info("updateScrapFc Data   : " + data);
		
		Gson gson = new Gson();
		FcLinkInfoVO fcLinkInfoVO	= new FcLinkInfoVO();
		
		int nResult;
		
		fcLinkInfoVO = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), FcLinkInfoVO.class);
       
		logger.debug("no_person  :" + fcLinkInfoVO.getNo_person());
		logger.debug("cd_fc      :" + fcLinkInfoVO.getCd_fc());
		logger.debug("cn         :" + fcLinkInfoVO.getCn());
		logger.debug("error_code :" + fcLinkInfoVO.getError_code());
		logger.debug("error_msg  :" + fcLinkInfoVO.getError_message());
   
		//String cd_fc = codeManager.getCodeId("cd_coocon_fc", fcLinkInfoVO.getCd_fc());
		String cd_fc = fincorpMapper.getCdFcByCooconFcCd(fcLinkInfoVO.getCd_fc());
		fcLinkInfoVO.setCd_fc(cd_fc);
   
		// 스크래핑 변동 내역 History Table에 Insert
		nResult = scrapMapper.createFcLinkInfoHist(fcLinkInfoVO);
		logger.debug("scrapManager.createFcLinkInfoHist return  :" + nResult);
   
		if(fcLinkInfoVO.getYn_link().equals("Y"))	{
			fcLinkInfoVO.setCd_link_stat("00");
		}
		else	{
			fcLinkInfoVO.setCd_link_stat("99");
		}
		fcLinkInfoVO.setRsn_link_message(fcLinkInfoVO.getError_message());
       
		String cd_agency = null;
		if(fcLinkInfoVO.getCd_agency().equals("bank"))	{
			cd_agency = codeManager.getCodeId("cd_agency", "은행");
		}
		else if(fcLinkInfoVO.getCd_agency().equals("card"))	{
			cd_agency = codeManager.getCodeId("cd_agency", "카드");
		}
		else if(fcLinkInfoVO.getCd_agency().equals("nts"))	{
			cd_agency = codeManager.getCodeId("cd_agency", "국세청");
		}
		fcLinkInfoVO.setCd_agency(cd_agency);

		String type_login = codeManager.getCodeId("type_login", fcLinkInfoVO.getType_login());
		fcLinkInfoVO.setType_login(type_login);
		fcLinkInfoVO.setId_frt(fcLinkInfoVO.getNo_person());
		fcLinkInfoVO.setId_lst(fcLinkInfoVO.getNo_person());
   
		// 스크래핑 변동 내역  Table에 Update
		nResult = scrapMapper.createFcLinkInfo(fcLinkInfoVO);
		logger.debug("scrapManager.createFcLinkInfo return  :" + nResult);
		
		jsonObject.put("result", Constant.SUCCESS);
		return jsonObject.toString();
	}
	
	@Override
	public ReturnClass unlinkScrapFc(String no_person, String cd_fc)	{
		logger.debug("no_person : " + no_person);
		logger.debug("cd_fc     : " + cd_fc);
		
		FcLinkInfoVO fcLinkInfoVO	= new FcLinkInfoVO();
		int	nResult;
		
		fcLinkInfoVO.setNo_person(no_person);
		fcLinkInfoVO.setCd_fc(cd_fc);
		fcLinkInfoVO.setYn_link("N");
		fcLinkInfoVO.setCd_link_stat("00");
		fcLinkInfoVO.setId_lst(no_person);
		nResult = scrapMapper.createFcLinkInfo(fcLinkInfoVO);
		logger.debug("scrapManager.createFcLinkInfo return  :" + nResult);
		
		return  new ReturnClass(Constant.SUCCESS); 
	}
	
	@Override
	public List<LinkedFcInfoVO> listFcLinkList(String no_person, String cn, String dn)	{
		logger.debug("no_person : "+ no_person);
		logger.debug("cn        : "+ cn);
		logger.debug("dn        : "+ dn);
		LinkedFcInfoVO linkedFcInfo = new LinkedFcInfoVO();
		
		linkedFcInfo.setNo_person(no_person);
		linkedFcInfo.setCn(cn);
		linkedFcInfo.setDn(dn);
		
		List<LinkedFcInfoVO> linkedFcInfoList = scrapMapper.getLinkedFcInfo(linkedFcInfo);
		for (LinkedFcInfoVO linkedFcInfoVO : linkedFcInfoList) {
             logger.debug("linkedFcInfoVO.getNo_person() :" + linkedFcInfoVO.getNo_person()  );
             logger.debug("linkedFcInfoVO.getCn()        :" + linkedFcInfoVO.getCn()         );
             logger.debug("linkedFcInfoVO.getNm_fc()     :" + linkedFcInfoVO.getNm_fc()      );
             logger.debug("linkedFcInfoVO.getNm_code()   :" + linkedFcInfoVO.getNm_code()    );
		}
		return linkedFcInfoList;
	}
	
	@Override
	public ReturnClass updateFcLinkInfoList(FcLinkInfoVO linkedFcInfoList)	{
		List<FcLinkInfoVO> list = linkedFcInfoList.getList();
		logger.debug("list.size() : "+ list.size());
		for (FcLinkInfoVO fcLinkInfoVO : list) {
			fcLinkInfoVO.setId_lst(fcLinkInfoVO.getNo_person());
			if(fcLinkInfoVO.getYn_link().equals("Y"))	{
				fcLinkInfoVO.setCd_link_stat("00");
			}
			else	{
				fcLinkInfoVO.setCd_link_stat("99");
			}
			logger.debug("fcLinkInfoVO.getNo_person() : "+ fcLinkInfoVO.getNo_person());
			logger.debug("fcLinkInfoVO.getCd_fc()     : "+ fcLinkInfoVO.getCd_fc());
			scrapMapper.createFcLinkInfo(fcLinkInfoVO);
		}
		return new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass createAutoBankScrap(String data)	{
		logger.debug("DATA ::: " + data);
		
		Gson gson = new Gson();
		AppBankInfo appBankInfo = new AppBankInfo();
		
		List<LoanAnListVO> loanAnVOLIST = null;
		List<AnAllListVO> anAllVOLIST = null;
		List<DepositAnListVO> depositAnVOLIST = null;
		List<FundAnListVO> fundAnVOLIST = null;

		String no_person = "";
		long seq_scrap = 0;
		String cd_fc = "";
		String max_date = "";
		String error_code = "";
		
		appBankInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppBankInfo.class);
        no_person 	= appBankInfo.getNO_PERSON();
        seq_scrap 	= appBankInfo.getSEQ_SCRAP();
        error_code	= appBankInfo.getERROR_CODE();
        
        
        logger.debug("no_person  :" + no_person);
        logger.debug("seq_scrap  :" + seq_scrap);

        if(appBankInfo.getUSER_BANK_OUTPUT() != null){
            
        	List<UserBankOutputVO> USER_BANK_OUTPUT = appBankInfo.getUSER_BANK_OUTPUT();
            for (UserBankOutputVO userBankOutputVO : USER_BANK_OUTPUT) {
                
            	if(userBankOutputVO != null){
            		//cd_fc = codeManager.getCodeId("cd_coocon_fc", userBankOutputVO.getBANK_CODE());
            		cd_fc = fincorpMapper.getCdFcByCooconFcCd(userBankOutputVO.getBANK_CODE());
            		
                    logger.debug("getBankCode    : " + cd_fc);
                    logger.debug("getERROR_CODE   : " + userBankOutputVO.getERROR_CODE());
                    logger.debug("getERROR_MESSAGE: " + userBankOutputVO.getERROR_MESSAGE());
                    
                    FcLinkInfoVO fcLinkInfoVO  = new FcLinkInfoVO();
                    fcLinkInfoVO.setNo_person(no_person);
                    fcLinkInfoVO.setCd_fc(cd_fc);
                    fcLinkInfoVO.setRsn_link_message(userBankOutputVO.getERROR_MESSAGE());
                    if(userBankOutputVO.getERROR_CODE().equals("00000000"))	{
                    	fcLinkInfoVO.setCd_link_stat("00");
                    }
                    else	{
                    	fcLinkInfoVO.setCd_link_stat("99");
                    }
                    scrapMapper.updateFcLinkInfo(fcLinkInfoVO);
                    
                    //계좌 내역 저장 전 전체 계좌 삭제상태로 변경
                    ScrBankApiAnInfoVO scrBankApiAnInfoVO = new ScrBankApiAnInfoVO();
                    scrBankApiAnInfoVO.setNo_person(no_person);
                    scrBankApiAnInfoVO.setCd_fc(cd_fc);
                    scrapMapper.updateScrBankApiAnInfo(scrBankApiAnInfoVO);
                    
                    List<AnAllListHistoryVO> anAllhistList = new ArrayList<AnAllListHistoryVO>();
                    List<DepositAnListHistoryVO> depositAnhistList = new ArrayList<DepositAnListHistoryVO>();
                    //입출금 계좌 내역
                    if(userBankOutputVO.getAN_ALL() != null){
                        AnAllVO anAllVO = userBankOutputVO.getAN_ALL();
                        logger.debug("anAllVO.getERROR_CODE    : "+ anAllVO.getERROR_CODE());
                        logger.debug("anAllVO.getERROR_MESSAGE : "+ anAllVO.getERROR_MESSAGE());
                        logger.debug("anAllVO.getSTART_DATE	   : "+ anAllVO.getSTART_DATE());
                        logger.debug("anAllVO.getEND_DATE      : "+ anAllVO.getEND_DATE());
                        if(anAllVO.getLIST() != null){
                            anAllVOLIST = anAllVO.getLIST();
                        	String accountType = codeManager.getCodeId("cd_account_type","입출금계좌");
                            for (AnAllListVO anAllListVO : anAllVOLIST) {
                                logger.debug("anAllVO.ERROR_CODE    : "+ anAllListVO.getERROR_CODE());
                                logger.debug("anAllVO.ERROR_MESSAGE : "+ anAllListVO.getERROR_MESSAGE());
                                logger.debug("anAllVO.AN            : "+ anAllListVO.getAN());
                                logger.debug("anAllVO.BALANCE       : "+ anAllListVO.getBALANCE());
                                logger.debug("anAllVO.CODE          : "+ anAllListVO.getCODE());
                                logger.debug("anAllVO.DATE          : "+ anAllListVO.getDATE());
                                logger.debug("anAllVO.NAME          : "+ anAllListVO.getNAME());
                                
                                ScrBankApiAnInfoVO scrBankApiAnInfo = new ScrBankApiAnInfoVO();
                                scrBankApiAnInfo.setCd_fc(cd_fc);;
                                scrBankApiAnInfo.setNo_person(no_person);
                                scrBankApiAnInfo.setAn(anAllListVO.getAN());
                                scrBankApiAnInfo.setType_an(accountType);
                                scrBankApiAnInfo.setCd_currency(anAllListVO.getCODE());
                                scrBankApiAnInfo.setCurrent_balance(anAllListVO.getBALANCE());
                                scrBankApiAnInfo.setDt_new(anAllListVO.getDATE());
                                scrBankApiAnInfo.setNm_an(anAllListVO.getNAME());
                                scrBankApiAnInfo.setYn_delete("N");
                                scrapMapper.createScrBankApiAnInfo(scrBankApiAnInfo);
                                
                                ScrReqBankVO scrReqBankVO = new ScrReqBankVO();
                                scrReqBankVO.setSeq_scraping_result(seq_scrap);
                                scrReqBankVO.setCd_fc(cd_fc);
                                scrReqBankVO.setCd_account(accountType);
                                scrReqBankVO.setNo_account(anAllListVO.getAN());
                                scrReqBankVO.setYmd_stt(anAllListVO.getDT_START());
                                scrReqBankVO.setYmd_end(anAllListVO.getDT_END());
                                scrReqBankVO.setError_cd(anAllListVO.getERROR_CODE());
                                scrReqBankVO.setError_msg(anAllListVO.getERROR_MESSAGE());
                                scrReqBankVO.setNo_person(no_person);
                                scrapMapper.createScrReqBank(scrReqBankVO);
                                                         
                                //해당 고객의 입출금 계좌 중에 Insert된 계좌 상세내역의 날짜/시간 
                                max_date = scrapMapper.getMaxDateSrcTransactionDetail(scrBankApiAnInfo);
                                
                                logger.debug(scrBankApiAnInfo.getAn() + " : max_date   :" + max_date);
                                
                                List<AnAllListHistoryVO> anAllListVOHISTORY = anAllListVO.getHISTORY();
                                for (AnAllListHistoryVO anAllListHistoryVO : anAllListVOHISTORY) {
                                	// 시간에 대한 데이터가 없을 경우 "000000"으로 설정
                                	String time = anAllListHistoryVO.getTIME();
                                	if(time == null || time.length() == 0)	{
                                		anAllListHistoryVO.setTIME("000000");
                                	}
                                	String date_time = anAllListHistoryVO.getDATE()+anAllListHistoryVO.getTIME();
                                    logger.debug("anAllVO.AN          : "+ anAllListHistoryVO.getAN());
                                    logger.debug("anAllVO.BALANCE     : "+ anAllListHistoryVO.getBALANCE());
                                    logger.debug("anAllVO.CODE        : "+ anAllListHistoryVO.getCODE());
                                    logger.debug("anAllVO.DATE        : "+ anAllListHistoryVO.getDATE());
                                    logger.debug("anAllVO.TIME        : "+ anAllListHistoryVO.getTIME());
                                    logger.debug("anAllVO.DEALWAY1    : "+ anAllListHistoryVO.getDEALWAY1());
                                    logger.debug("anAllVO.DEALWAY2    : "+ anAllListHistoryVO.getDEALWAY2());
                                    logger.debug("anAllVO.DOC1        : "+ anAllListHistoryVO.getDOC1());
                                    logger.debug("anAllVO.DOC2        : "+ anAllListHistoryVO.getDOC2());
                                    logger.debug("anAllVO.IN_PAYMENT  : "+ anAllListHistoryVO.getIN_PAYMENT());
                                    logger.debug("anAllVO.OUT_PAYMENT : "+ anAllListHistoryVO.getOUT_PAYMENT());
                                    anAllListHistoryVO.setNO_PERSON(no_person);
                                    anAllListHistoryVO.setID_FRT(no_person);
                                    
                                    // 계좌 상세 내역의 마지막 날짜/시간 이후의 데이터만 List에 추가
                                    if(max_date != null && max_date != "" && date_time != null && date_time != ""
                                    	&& Long.parseLong(max_date) >= Long.parseLong(date_time))	{
                                    	continue;
                                    }
                                    anAllhistList.add(anAllListHistoryVO);
                                }
                            }
                            //은행스크래핑 조회 내역 Insert(기존 내역 history 테이블에 저장)
                            ScrReqBankVO scrReqBankVO = new ScrReqBankVO();
                            scrReqBankVO.setNo_person(no_person);
                            scrReqBankVO.setCd_fc(cd_fc);
                            scrReqBankVO.setCd_account(codeManager.getCodeId("cd_account_type","입출금계좌"));
                            scrapMapper.insertScrReqBankHist(scrReqBankVO);
                         
                            //입출금 계좌 입출금 내역 Insert
                            logger.debug("anAllhistList.size() : "+ anAllhistList.size());
                            for (AnAllListHistoryVO anAllListHistory : anAllhistList) {
                            	scrapMapper.createScrTransactionDetail(anAllListHistory);
                            }
                        }
                    }
                    //예적금 계좌 내역
                    if(userBankOutputVO.getDEPOSIT_AN() != null){
                        DepositAnVO depositAnVO = userBankOutputVO.getDEPOSIT_AN();
                        logger.debug("depositAnVO.getERROR_CODE    :"+ depositAnVO.getERROR_CODE());
                        logger.debug("depositAnVO.getERROR_MESSAGE :"+ depositAnVO.getERROR_MESSAGE());
                        if(depositAnVO.getLIST() != null){
                        	// 해당 고객의 예적금 계좌 중에 Insert된 계좌 상세내역의 날짜/시간 
                        	String accountType = codeManager.getCodeId("cd_account_type","예적금계좌");
                            depositAnVOLIST = depositAnVO.getLIST();
                            for (DepositAnListVO depositAnListVO : depositAnVOLIST) {
                                logger.debug("depositAnListVO.getAN()       :" + depositAnListVO.getAN()       );
                                logger.debug("depositAnListVO.getTYPE()     :" + depositAnListVO.getTYPE()     );
                                logger.debug("depositAnListVO.getNEW_DATE() :" + depositAnListVO.getNEW_DATE() );
                                logger.debug("depositAnListVO.getLAST_DATE():" + depositAnListVO.getLAST_DATE());
                                logger.debug("depositAnListVO.getBALANCE()  :" + depositAnListVO.getBALANCE()  );
                                logger.debug("depositAnListVO.getINTEREST() :" + depositAnListVO.getINTEREST());
                                
                                ScrBankApiAnInfoVO scrBankApiAnInfo = new ScrBankApiAnInfoVO();
                            	scrBankApiAnInfo.setCd_fc(cd_fc);;
                            	scrBankApiAnInfo.setNo_person(no_person);
                            	scrBankApiAnInfo.setAn(depositAnListVO.getAN());
                            	scrBankApiAnInfo.setType_an(accountType);
                            	scrBankApiAnInfo.setCurrent_balance(depositAnListVO.getBALANCE());
                            	scrBankApiAnInfo.setDt_new(depositAnListVO.getNEW_DATE());
                            	scrBankApiAnInfo.setDt_end(depositAnListVO.getLAST_DATE());
                            	scrBankApiAnInfo.setNm_an(depositAnListVO.getTYPE());
                            	scrBankApiAnInfo.setInterest_rate(depositAnListVO.getINTEREST());
                            	scrBankApiAnInfo.setYn_delete("N");
                            	scrapMapper.createScrBankApiAnInfo(scrBankApiAnInfo);
                                
                                ScrReqBankVO scrReqBankVO = new ScrReqBankVO();
                                scrReqBankVO.setSeq_scraping_result(seq_scrap);
                                scrReqBankVO.setCd_fc(cd_fc);
                                scrReqBankVO.setCd_account(accountType);
                                scrReqBankVO.setNo_account(depositAnListVO.getAN());
                                scrReqBankVO.setYmd_stt(depositAnListVO.getDT_START());
                                scrReqBankVO.setYmd_end(depositAnListVO.getDT_END());
                                scrReqBankVO.setError_cd(depositAnListVO.getERROR_CODE());
                                scrReqBankVO.setError_msg(depositAnListVO.getERROR_MESSAGE());
                                scrReqBankVO.setNo_person(no_person);
                                scrapMapper.createScrReqBank(scrReqBankVO);

                                max_date = scrapMapper.getMaxDateScrSvngSvninDetail(scrBankApiAnInfo);
                                logger.debug(scrBankApiAnInfo.getAn() + "   : max_date   :" + max_date);
                                List<DepositAnListHistoryVO> depositAnListVOHISTORY = depositAnListVO.getHISTORY();
                                for (DepositAnListHistoryVO depositAnListHistoryVO : depositAnListVOHISTORY) {
                                	// 시간에 대한 데이터가 없을 경우 "000000"으로 설정
                                	String time = depositAnListHistoryVO.getTIME();
                                	if(time == null || time.length() == 0)	{
                                		depositAnListHistoryVO.setTIME("000000");
                                	}
                                	String date_time = depositAnListHistoryVO.getDATE()+depositAnListHistoryVO.getTIME();
                                    logger.debug("depositAnVO.AN          : "+ depositAnListHistoryVO.getAN());
                                    logger.debug("depositAnVO.DATE        : "+ depositAnListHistoryVO.getDATE());
                                    logger.debug("depositAnVO.TIME        : "+ depositAnListHistoryVO.getTIME());
                                    logger.debug("depositAnVO.BATCH       : "+ depositAnListHistoryVO.getBATCH());
                                    logger.debug("depositAnVO.MONTH       : "+ depositAnListHistoryVO.getMONTH());
                                    logger.debug("depositAnVO.OUT_PAYMENT : "+ depositAnListHistoryVO.getOUT_PAYMENT());
                                    logger.debug("depositAnVO.IN_PAYMENT  : "+ depositAnListHistoryVO.getIN_PAYMENT());
                                    logger.debug("depositAnVO.BALANCE     : "+ depositAnListHistoryVO.getBALANCE());
                                    logger.debug("depositAnVO.DESCRIPTION : "+ depositAnListHistoryVO.getDESCRIPTION());
                                    logger.debug("depositAnVO.DISTRIBUTOR : "+ depositAnListHistoryVO.getDISTRIBUTOR());
                                    
                                    depositAnListHistoryVO.setNO_PERSON(no_person);
                                    depositAnListHistoryVO.setID_FRT(no_person);
                                    
                                    // 계좌 상세 내역의 마지막 날짜/시간 이후의 데이터만 List에 추가
                                    if(max_date != null && max_date != "" && date_time != null && date_time != ""
                                    	&& Long.parseLong(max_date) >= Long.parseLong(date_time))	{
                                    	continue;
                                    }
                                    depositAnhistList.add(depositAnListHistoryVO);
                                }
                            }
                            //은행스크래핑 조회 내역 Insert(기존 내역 history 테이블에 저장)
                            ScrReqBankVO scrReqBankVO = new ScrReqBankVO();
                            scrReqBankVO.setNo_person(no_person);
                            scrReqBankVO.setCd_fc(cd_fc);
                            scrReqBankVO.setCd_account(codeManager.getCodeId("cd_account_type","예적금계좌"));
                            scrapMapper.insertScrReqBankHist(scrReqBankVO);
                            
                             //예적금 계좌 입출금 내역 Insert
                            logger.debug("depositAnhistList.size() : "+ depositAnhistList.size());
                            for (DepositAnListHistoryVO depositAnListHistory : depositAnhistList) {
                            	scrapMapper.createScrSvngSvninDetail(depositAnListHistory);
                            }
                        }
                    }
                    //펀드 계좌 내역
                    if(userBankOutputVO.getFUND_AN() != null){
                    	String accountType = codeManager.getCodeId("cd_account_type","펀드계좌");
                        FundAnVO fundAnVO = userBankOutputVO.getFUND_AN();
                        logger.debug("fundAnVO.getERROR_CODE()) :" +fundAnVO.getERROR_CODE());
                        logger.debug("fundAnVO.getERROR_MESSAGE(:"+ fundAnVO.getERROR_MESSAGE());
                        if(fundAnVO.getLIST() != null){
                            fundAnVOLIST = fundAnVO.getLIST();
                            for (FundAnListVO fundAnListVO : fundAnVOLIST) {
                                logger.debug("fundAnListVO.AN               :" + fundAnListVO.getAN()               );
                                logger.debug("fundAnListVO.TYPE             :" + fundAnListVO.getTYPE()             );
                                logger.debug("fundAnListVO.PRINCIPAL        :" + fundAnListVO.getPRINCIPAL()        );
                                logger.debug("fundAnListVO.EVALUATION_AMOUNT:" + fundAnListVO.getEVALUATION_AMOUNT());
                                logger.debug("fundAnListVO.NEW_DATE         :" + fundAnListVO.getNEW_DATE()         );
                                logger.debug("fundAnListVO.LAST_DATE        :" + fundAnListVO.getLAST_DATE()        );
                                logger.debug("fundAnListVO.YIELD            :" + fundAnListVO.getYIELD()            );
                                
                                ScrBankApiAnInfoVO scrBankApiAnInfo = new ScrBankApiAnInfoVO();
                            	scrBankApiAnInfo.setCd_fc(cd_fc);;
                            	scrBankApiAnInfo.setNo_person(no_person);
                            	scrBankApiAnInfo.setAn(fundAnListVO.getAN());
                            	scrBankApiAnInfo.setType_an(accountType);
                            	scrBankApiAnInfo.setPrincipal(fundAnListVO.getPRINCIPAL());
                            	scrBankApiAnInfo.setCurrent_balance(fundAnListVO.getEVALUATION_AMOUNT());
                            	scrBankApiAnInfo.setDt_new(fundAnListVO.getNEW_DATE());
                            	scrBankApiAnInfo.setDt_end(fundAnListVO.getLAST_DATE());
                            	scrBankApiAnInfo.setNm_an(fundAnListVO.getTYPE());
                            	scrBankApiAnInfo.setProfit_rate(fundAnListVO.getYIELD());
                            	scrBankApiAnInfo.setYn_delete("N");
                            	scrapMapper.createScrBankApiAnInfo(scrBankApiAnInfo);
                            }
                        }
                    }
                    //대출 계좌 내역
                    if(userBankOutputVO.getLOAN_AN() != null){
                    	String accountType = codeManager.getCodeId("cd_account_type","대출계좌");
                        LoanAnVO loanAnVO = userBankOutputVO.getLOAN_AN();
                        logger.debug("loanAnVO.getERROR_CODE    :" +loanAnVO.getERROR_CODE());
                        logger.debug("loanAnVO.getERROR_MESSAGE :"+ loanAnVO.getERROR_MESSAGE());
                        if(loanAnVO.getLIST() != null){
                            loanAnVOLIST = loanAnVO.getLIST();
                            for (LoanAnListVO loanAnListVO : loanAnVOLIST) {
                                logger.debug("loanAnListVO.getAN           :" + loanAnListVO.getAN()           );
                                logger.debug("loanAnListVO.getTYPE         :" + loanAnListVO.getTYPE()         );
                                logger.debug("loanAnListVO.getLOAN_BALANCE :" + loanAnListVO.getLOAN_BALANCE() );
                                logger.debug("loanAnListVO.getLOAN_CEILING :" + loanAnListVO.getLOAN_CEILING() );
                                logger.debug("loanAnListVO.getNEW_DATE     :" + loanAnListVO.getNEW_DATE()     );
                                logger.debug("loanAnListVO.getLAST_DATE    :" + loanAnListVO.getLAST_DATE()    );
                                logger.debug("loanAnListVO.getLENDING_RATE :" + loanAnListVO.getLENDING_RATE() );
                                logger.debug("loanAnListVO.getFEW_DAYS     :" + loanAnListVO.getFEW_DAYS()     );
                                logger.debug("loanAnListVO.getINTEREST_DATE:" + loanAnListVO.getINTEREST_DATE());
                                
                                ScrBankApiAnInfoVO scrBankApiAnInfo = new ScrBankApiAnInfoVO();
                            	scrBankApiAnInfo.setCd_fc(cd_fc);;
                            	scrBankApiAnInfo.setNo_person(no_person);
                            	scrBankApiAnInfo.setAn(loanAnListVO.getAN());
                            	scrBankApiAnInfo.setType_an(accountType);
                            	scrBankApiAnInfo.setNm_an(loanAnListVO.getTYPE());
                            	scrBankApiAnInfo.setLoan_balance(loanAnListVO.getLOAN_BALANCE());
                            	scrBankApiAnInfo.setLoan_ceiling(loanAnListVO.getLOAN_CEILING());
                            	scrBankApiAnInfo.setDt_new(loanAnListVO.getNEW_DATE());
                            	scrBankApiAnInfo.setDt_end(loanAnListVO.getLAST_DATE());
                            	scrBankApiAnInfo.setInterest_rate(loanAnListVO.getLENDING_RATE());
                            	scrBankApiAnInfo.setFew_days(loanAnListVO.getFEW_DAYS());
                            	scrBankApiAnInfo.setInterest_date(loanAnListVO.getINTEREST_DATE());
                            	scrBankApiAnInfo.setYn_delete("N");
                            	scrapMapper.createScrBankApiAnInfo(scrBankApiAnInfo);
                            }
                        }
                    }
                }
            }
        }
        ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
        scrRsltScrapVO.setNo_person(no_person);
        scrRsltScrapVO.setSeq_scraping_result(seq_scrap);
        scrRsltScrapVO.setRslt_scraping(error_code);
        scrapMapper.updateScrRsltScrap(scrRsltScrapVO);
        return new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass createAutoCardScrap(String data)	{
		logger.debug("DATA ::: " + data);
		
		Gson gson = new Gson();
		AppCardInfo appCardInfo = new AppCardInfo();

		String no_person = "";
		long seq_scrap = 0;
		String max_date = "";
		String error_code = "";
		
		appCardInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppCardInfo.class);
        no_person 	= appCardInfo.getNO_PERSON();
        seq_scrap 	= appCardInfo.getSEQ_SCRAP();
        error_code	= appCardInfo.getERROR_CODE();
        
        
        logger.debug("no_person  :" + no_person);
        logger.debug("seq_scrap  :" + seq_scrap);
       
        if(appCardInfo.getUSER_CARD_OUTPUT() != null){
        	List<UserCardOutputVO> USER_CARD_OUTPUT = appCardInfo.getUSER_CARD_OUTPUT();
            for (UserCardOutputVO userCardOutputVO : USER_CARD_OUTPUT) {
            	//String cd_fc = codeManager.getCodeId("cd_coocon_fc", userCardOutputVO.getCARD_CODE());
            	String cd_fc = fincorpMapper.getCdFcByCooconFcCd(userCardOutputVO.getCARD_CODE());
            	            	
            	FcLinkInfoVO fcLinkInfoVO  = new FcLinkInfoVO();
                fcLinkInfoVO.setNo_person(no_person);
                fcLinkInfoVO.setCd_fc(cd_fc);
                fcLinkInfoVO.setRsn_link_message(userCardOutputVO.getERROR_MESSAGE());
                if(userCardOutputVO.getERROR_CODE().equals("00000000"))	{
                	fcLinkInfoVO.setCd_link_stat("00");
                }
                else	{
                	fcLinkInfoVO.setCd_link_stat("99");
                }
                scrapMapper.updateFcLinkInfo(fcLinkInfoVO);
                
                //카드내역 저장 전 삭제상태로 변경
                ScrCardInfoVO scrCardInfoVO = new ScrCardInfoVO();
                scrCardInfoVO.setNo_person(no_person);
                scrCardInfoVO.setCd_fc(cd_fc);
                scrapMapper.updateScrCardInfo(scrCardInfoVO);
                
            	//카드내역 저장
            	List<ScrCardInfoVO> ScrCardInfoList = userCardOutputVO.getCARD_INFO();
            	if(ScrCardInfoList != null && ScrCardInfoList.size() > 0){
            		for (ScrCardInfoVO scrCardInfo : ScrCardInfoList) {		
            			logger.debug("scrCardInfo.getNo_card     : "+ scrCardInfo.getNo_card());
                        logger.debug("scrCardInfo.getNm_card     : "+ scrCardInfo.getNm_card());
                        logger.debug("scrCardInfo.getDt_payment  : "+ scrCardInfo.getDt_payment());
                        logger.debug("scrCardInfo.getAmt_payment : "+ scrCardInfo.getAmt_payment());
                        
                        scrCardInfo.setNo_person(no_person);
                        scrCardInfo.setCd_fc(cd_fc);
                        scrCardInfo.setYn_delete("N");
                        scrapMapper.createScrCardInfo(scrCardInfo);
            		}
            	}
            	//카드 내역 History 저장 및 갱신
            	ScrReqCardVO scrReqCardVO = new ScrReqCardVO();
            	scrReqCardVO.setNo_person(no_person);
            	scrReqCardVO.setCd_fc(cd_fc);
            	scrReqCardVO.setCd_type("01"); //01 보유카드현황, 02 승인내역, 03 청구내역, 04 한도조회, 05 포인트조회
            	scrReqCardVO.setSeq_scraping_result(seq_scrap);
            	scrReqCardVO.setError_cd(userCardOutputVO.getCARD_ERROR_CODE());
            	scrReqCardVO.setError_msg(userCardOutputVO.getCARD_ERROR_MESSAGE());
                scrapMapper.insertScrReqCardHist(scrReqCardVO);
                scrapMapper.createScrReqCard(scrReqCardVO);
                
                //카드 한도내역 저장 전 삭제상태로 변경
                ScrCardLimitInfoVO scrCardLimitInfoVO = new ScrCardLimitInfoVO();
                scrCardLimitInfoVO.setNo_person(no_person);
                scrCardLimitInfoVO.setCd_fc(cd_fc);
                scrapMapper.updateScrCardLimitInfo(scrCardLimitInfoVO);
                //카드 한도내역 저장
            	List<ScrCardLimitInfoVO> ScrCardLimitInfoList = userCardOutputVO.getCARD_LIMIT();
            	if(ScrCardLimitInfoList != null && ScrCardLimitInfoList.size() > 0){
            		for (ScrCardLimitInfoVO scrCardLimitInfo : ScrCardLimitInfoList) {		
            			logger.debug("scrCardLimitInfo.getType_card      : "+ scrCardLimitInfo.getType_card());
                        logger.debug("scrCardLimitInfo.getAmt_cash_limit : "+ scrCardLimitInfo.getAmt_cash_limit());
                        logger.debug("scrCardLimitInfo.getAmt_card_limit : "+ scrCardLimitInfo.getAmt_card_limit());
                        
                        scrCardLimitInfo.setNo_person(no_person);
                        scrCardLimitInfo.setCd_fc(cd_fc);
                        scrCardLimitInfo.setYn_delete("N");
//                        if(scrCardLimitInfo.getType_card() == null || scrCardLimitInfo.getType_card().length() == 0){
//                        	scrCardLimitInfo.setType_card("defalut");
//                        }
                        scrapMapper.createScrCardLimitInfo(scrCardLimitInfo);
            		}
            	}
            	//카드 내역 History 저장 및 갱신
            	scrReqCardVO.setNo_person(no_person);
            	scrReqCardVO.setCd_fc(cd_fc);
            	scrReqCardVO.setCd_type("04"); //01 보유카드현황, 02 승인내역, 03 청구내역, 04 한도조회, 05 포인트조회
            	scrReqCardVO.setSeq_scraping_result(seq_scrap);
            	scrReqCardVO.setError_cd(userCardOutputVO.getLIMIT_ERROR_CODE());
            	scrReqCardVO.setError_msg(userCardOutputVO.getLIMIT_ERROR_MESSAGE());
                scrapMapper.insertScrReqCardHist(scrReqCardVO);
                scrapMapper.createScrReqCard(scrReqCardVO);
            	
              //카드 한도내역 저장
            	List<ScrCardPointInfoVO> ScrCardPointInfoList = userCardOutputVO.getCARD_POINT();
            	if(ScrCardPointInfoList != null && ScrCardPointInfoList.size() > 0){
            		for (ScrCardPointInfoVO ScrCardPointInfo : ScrCardPointInfoList) {		
            			logger.debug("ScrCardPointInfo.getType_card      : "+ ScrCardPointInfo.getType_point());
                        logger.debug("ScrCardPointInfo.getAmt_cash_limit : "+ ScrCardPointInfo.getHold_point());
                        
                        ScrCardPointInfo.setNo_person(no_person);
                        ScrCardPointInfo.setCd_fc(cd_fc);
                        scrapMapper.createScrCardPointInfo(ScrCardPointInfo);
            		}
            	}
            	//카드 내역 History 저장 및 갱신
            	scrReqCardVO.setNo_person(no_person);
            	scrReqCardVO.setCd_fc(cd_fc);
            	scrReqCardVO.setCd_type("05"); //01 보유카드현황, 02 승인내역, 03 청구내역, 04 한도조회, 05 포인트조회
            	scrReqCardVO.setSeq_scraping_result(seq_scrap);
            	scrReqCardVO.setError_cd(userCardOutputVO.getPOINT_ERROR_CODE());
            	scrReqCardVO.setError_msg(userCardOutputVO.getPOINT_ERROR_MESSAGE());
                scrapMapper.insertScrReqCardHist(scrReqCardVO);
                scrapMapper.createScrReqCard(scrReqCardVO);
                
            	//카드 승인내역 마지막 조회 내역 조회
            	Map<String, Object> paramMap = new HashMap<String, Object>();
            	paramMap.put("no_person", no_person);
            	paramMap.put("cd_fc", cd_fc);
            	max_date = scrapMapper.getMaxDateScrCardApprovalInfo(paramMap);
            	//카드 승인내역 저장
            	List<ScrCardApprovalInfoVO> scrCardApprovalInfoList = userCardOutputVO.getCARD_APPROVAL();
            	if(scrCardApprovalInfoList != null){
            		List<ScrCardApprovalInfoVO> list = new ArrayList<ScrCardApprovalInfoVO>();
            		for (ScrCardApprovalInfoVO scrCardApprovalInfo : scrCardApprovalInfoList) {
                         logger.debug("scrCardApprovalInfo.getNo_card      : "+ scrCardApprovalInfo.getNo_card());
                         logger.debug("scrCardApprovalInfo.getDt_approval  : "+ scrCardApprovalInfo.getDt_approval());
                         logger.debug("scrCardApprovalInfo.getTm_approval  : "+ scrCardApprovalInfo.getTm_approval());
                         logger.debug("scrCardApprovalInfo.getNo_approval  : "+ scrCardApprovalInfo.getNo_approval());
                         logger.debug("scrCardApprovalInfo.getAmt_approval : "+ scrCardApprovalInfo.getAmt_approval());
                         scrCardApprovalInfo.setNo_person(no_person);
                         scrCardApprovalInfo.setCd_fc(cd_fc);;
                         // 시간에 대한 데이터가 없을 경우 "000000"으로 설정
                         String time = scrCardApprovalInfo.getTm_approval();
                         if(time == null || time.length() == 0)	{
                        	 scrCardApprovalInfo.setTm_approval("000000");
                         }
                         String date_time = scrCardApprovalInfo.getDt_approval()+scrCardApprovalInfo.getTm_approval();
                         
                         // 카드 승인내역 조회 마지막 날짜 이후 마지막 날짜/시간 이후의 데이터만 List에 추가
                         if(max_date != null && max_date != "" && date_time != null && date_time != ""
                         	&& Long.parseLong(max_date) >= Long.parseLong(date_time))	{
                         	continue;
                         }
                         list.add(scrCardApprovalInfo);
            		}
            		//카드승인 내역 Insert
                    logger.debug("list.size() : "+ list.size());
                    for (ScrCardApprovalInfoVO scrCardApprovalInfo : list) {
                    	scrapMapper.createScrCardApprovalInfo(scrCardApprovalInfo);
                    }
            	}

            	//카드조회 내역 History 저장 및 갱신  - 승인내역
            	scrReqCardVO.setCd_type("02"); //01 보유카드현황, 02 승인내역, 03 청구내역, 04 한도조회, 05 포인트조회
            	scrReqCardVO.setSeq_scraping_result(seq_scrap);
            	scrReqCardVO.setYmd_stt(userCardOutputVO.getDT_APPROVAL_START());
            	scrReqCardVO.setYmd_end(userCardOutputVO.getDT_APPROVAL_END());
            	scrReqCardVO.setError_cd(userCardOutputVO.getAPPROVAL_ERROR_CODE());
            	scrReqCardVO.setError_msg(userCardOutputVO.getAPPROVAL_ERROR_MESSAGE());
                scrapMapper.insertScrReqCardHist(scrReqCardVO);
                scrapMapper.createScrReqCard(scrReqCardVO);
                
              //카드 청구내역 마지막 조회 내역 조회
            	max_date = scrapMapper.getMaxDateScrCardChargeDetail(paramMap);
                //카드 청구내역 저장
            	List<ScrCardChargeInfoVO> scrCardChargeInfoList = userCardOutputVO.getCARD_CHARGE();
            	if(scrCardChargeInfoList != null){
            		for (ScrCardChargeInfoVO scrCardChargeInfoVO : scrCardChargeInfoList) {
                        logger.debug("scrCardChargeInfoVO.getCharge_yyyymm  : "+ scrCardChargeInfoVO.getCharge_yyyymm());
                        logger.debug("scrCardChargeInfoVO.getMonthly_charge : "+ scrCardChargeInfoVO.getMonthly_charge());
                        scrCardChargeInfoVO.setNo_person(no_person);
                        scrCardChargeInfoVO.setCd_fc(cd_fc);

                        //카드 청구내역 Insert
                		scrapMapper.createScrCardChargeInfo(scrCardChargeInfoVO);
                        
                        List<ScrCardChargeDetailVO> scrCardChargeDetailList = scrCardChargeInfoVO.getCARD_CHARGE_DTL();
                        if(scrCardChargeDetailList != null){
                        	List<ScrCardChargeDetailVO> list = new ArrayList<ScrCardChargeDetailVO>();
                        	for (ScrCardChargeDetailVO scrCardChargeDetail : scrCardChargeDetailList) {
                        		logger.debug("scrCardChargeDetail.getDt_use : "+ scrCardChargeDetail.getDt_use());
                                logger.debug("scrCardChargeDetail.getFees   : "+ scrCardChargeDetail.getFees());
                                scrCardChargeDetail.setNo_person(no_person);
                        		scrCardChargeDetail.setCd_fc(cd_fc);
                        		scrCardChargeDetail.setCharge_yyyymm(scrCardChargeInfoVO.getCharge_yyyymm());
                        		
		                        String date = scrCardChargeDetail.getDt_use();
		                        // 카드 청구내역 조회 마지막 날짜 이후 마지막 날짜/시간 이후의 데이터만 List에 추가
		                        if(max_date != null && max_date != "" && date != null && date != ""
		                        	&& Long.parseLong(max_date) >= Long.parseLong(date))	{
		                        	continue;
		                        }
		                        list.add(scrCardChargeDetail);
                        	}
                        	//카드 청구내역 상세 Insert
                            logger.debug("list.size() : "+ list.size());
                            logger.debug("list.size() : "+ list.size());
                            for (ScrCardChargeDetailVO scrCardChargeDetailVO : list) {
                            	scrapMapper.createScrCardChargeDetail(scrCardChargeDetailVO);
                            }
                        }
            		}
            	}
            	//카드조회 내역 History 저장 및 갱신  - 청구내역
            	scrReqCardVO.setCd_type("03"); //01 보유카드현황, 02 승인내역, 03 청구내역, 4 한도조회, 05 포인트조회
            	scrReqCardVO.setSeq_scraping_result(seq_scrap);
            	scrReqCardVO.setYmd_stt(userCardOutputVO.getDT_CHARGE_START());
            	scrReqCardVO.setYmd_end(userCardOutputVO.getDT_CHARGE_END());
            	scrReqCardVO.setError_cd(userCardOutputVO.getCHARGE_ERROR_CODE());
            	scrReqCardVO.setError_msg(userCardOutputVO.getCHARGE_ERROR_MESSAGE());
                scrapMapper.insertScrReqCardHist(scrReqCardVO);
                scrapMapper.createScrReqCard(scrReqCardVO);
            }
        }
        //스크래핑 내역에 결과 수정
        ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
        scrRsltScrapVO.setNo_person(no_person);
        scrRsltScrapVO.setSeq_scraping_result(seq_scrap);
        scrRsltScrapVO.setRslt_scraping(error_code);
        scrapMapper.updateScrRsltScrap(scrRsltScrapVO);
        
        return new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass createAutoNTSScrap(String data)	{
		logger.debug("DATA ::: " + data);
		
		Gson gson = new Gson();
		AppNTSInfo appNTSInfo = new AppNTSInfo();

		String no_person = "";
		long seq_scrap = 0;
		String max_date = "";
		String error_code = "";
		
		appNTSInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppNTSInfo.class);
        no_person 	= appNTSInfo.getNO_PERSON();
        seq_scrap 	= appNTSInfo.getSEQ_SCRAP();
        error_code	= appNTSInfo.getERROR_CODE();
        
        
        logger.debug("no_person  :" + no_person);
        logger.debug("seq_scrap  :" + seq_scrap);
       
        if(appNTSInfo.getUSER_NTS_OUTPUT() != null){
        	NTSInfo USER_NTS_OUTPUT = appNTSInfo.getUSER_NTS_OUTPUT();
            if(USER_NTS_OUTPUT != null){
                // 국세청 현금영수증 마지막 조회 내역 조회
            	max_date = scrapMapper.getMaxDateScrRespCashReceipt(no_person);
            	List<ScrRespCashReceiptVO> scrRespCashReceiptList = USER_NTS_OUTPUT.getCASH_USE_HISTORY();
            	if(scrRespCashReceiptList != null){
            		for (ScrRespCashReceiptVO scrRespCashReceipt : scrRespCashReceiptList) {
                         logger.debug("scrRespCashReceipt.getYmd_deal    : "+ scrRespCashReceipt.getYmd_deal());
                         logger.debug("scrRespCashReceipt.getTime_deal   : "+ scrRespCashReceipt.getTime_deal());
                         logger.debug("scrRespCashReceipt.getNm_affiliate: "+ scrRespCashReceipt.getNm_affiliate());
                         logger.debug("scrRespCashReceipt.getAmt_use     : "+ scrRespCashReceipt.getAmt_use());
                         logger.debug("scrRespCashReceipt.getNo_approval : "+ scrRespCashReceipt.getNo_approval());
                                              
                         String date_time = scrRespCashReceipt.getYmd_deal()+scrRespCashReceipt.getTime_deal();
                         // 국세청 현금영수증 조회 마지막 날짜 이후 마지막 날짜/시간 이후의 데이터만 List에 추가
                         if(max_date != null && max_date != "" && date_time != null && date_time != ""
                         	&& Long.parseLong(max_date) >= Long.parseLong(date_time))	{
                         	continue;
                         }
                         
                         scrRespCashReceipt.setNo_person(no_person);
                         String deducation = scrRespCashReceipt.getYn_deduction();
                         if(deducation.equals("공제"))	{
                        	 scrRespCashReceipt.setYn_deduction("Y");
                         }
                         else	{
                        	 scrRespCashReceipt.setYn_deduction("N");
                         }
                         //현금영수증 내역 Insert
                         scrapMapper.createScrRespCashReceipt(scrRespCashReceipt);
            		}
            	}
            	ScrReqCertificationVO scrReqCertificationVO = new ScrReqCertificationVO();
                scrReqCertificationVO.setNo_person(no_person);
                scrReqCertificationVO.setSeq_scraping_result(seq_scrap);
                scrReqCertificationVO.setCd_type("01"); //01 현금영수증 사용내역, 02 소득금액증명, 03 사업자등록증명, 04 사업자등록상태, 05 부가가치세 과세표준증명원, 06 표준재무제표증명, 07 납세사실증명
                scrReqCertificationVO.setRcpt_start_month(USER_NTS_OUTPUT.getRCPT_START_MONTH());
                scrReqCertificationVO.setRcpt_end_month(USER_NTS_OUTPUT.getRCPT_END_MONTH());
                scrReqCertificationVO.setError_cd(USER_NTS_OUTPUT.getERROR_CODE());
                scrReqCertificationVO.setError_msg(USER_NTS_OUTPUT.getERROR_MESSAGE());
                scrapMapper.createScrReqCertification(scrReqCertificationVO);
            }
        }
        //스크래핑 내역에 결과 수정
        ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
        scrRsltScrapVO.setNo_person(no_person);
        scrRsltScrapVO.setSeq_scraping_result(seq_scrap);
        scrRsltScrapVO.setRslt_scraping(error_code);
        scrapMapper.updateScrRsltScrap(scrRsltScrapVO);
		
		return new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass createNHISScrap(String data)	{
		logger.debug("DATA ::: " + data);
		
		Gson gson = new Gson();
		AppNHISInfo appNHISInfo = new AppNHISInfo();

		String no_person = "";
		long seq_scrap = 0;
		String error_code = "";
		
		appNHISInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppNHISInfo.class);
        no_person 	= appNHISInfo.getNO_PERSON();
        error_code	= appNHISInfo.getERROR_CODE();
        
        logger.debug("no_person  :" + no_person);
        
        //스크래핑 조회 내역 저장
        String cd_agency = codeManager.getCodeId("cd_agency", "건강보험공단");
        ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
        scrRsltScrapVO.setNo_person(no_person);
		scrRsltScrapVO.setCd_scrap(cd_agency);
		scrRsltScrapVO.setRslt_scraping(error_code);
	
		seq_scrap = scrapMapper.insertScrRsltScrap(scrRsltScrapVO);
		logger.info("insertScrRsltScrap result :" + seq_scrap);

        if(appNHISInfo.getUSER_NHIS_OUTPUT() != null){
        	NHISInfo USER_NHIS_OUTPUT = appNHISInfo.getUSER_NHIS_OUTPUT();
        	ScrReqHealthVO scrReqHealthVO = new ScrReqHealthVO();
        	//정상 조회 시에만 조회내역 insert
        	if(USER_NHIS_OUTPUT.getERROR_CODE().equals("00000000"))	{
	        	List<ScrRespHealthPaymentVO> scrRespHealthPaymentVO = USER_NHIS_OUTPUT.getPAYMENT();
	        	if(scrRespHealthPaymentVO != null){
	        		List<ScrRespHealthPaymentVO> list = new ArrayList<ScrRespHealthPaymentVO>();
	        		for (ScrRespHealthPaymentVO scrRespHealthPayment : scrRespHealthPaymentVO) {
	                     logger.debug("scrRespHealthPayment.getNm_comp   : "+ scrRespHealthPayment.getNm_comp());
	                     logger.debug("scrRespHealthPayment.getNm_member : "+ scrRespHealthPayment.getNm_member());
	                     logger.debug("scrRespHealthPayment.getNo_payer  : "+ scrRespHealthPayment.getNo_payer());
	                     logger.debug("scrRespHealthPayment.getYmd_gain  : "+ scrRespHealthPayment.getYmd_gain());
	                     logger.debug("scrRespHealthPayment.getYmd_loss  : "+ scrRespHealthPayment.getYmd_loss());
	                     scrRespHealthPayment.setNo_person(no_person);
	                     
	                     List<ScrRespHealthPaymentdtlVO> scrRespHealthPaymentdtl = scrRespHealthPayment.getPAYMENT_DTL();
	                     if(scrRespHealthPaymentdtl != null)	{
	                    	 for (ScrRespHealthPaymentdtlVO scrRespHealthPaymentdtlVO : scrRespHealthPaymentdtl) {
	                    		 scrRespHealthPaymentdtlVO.setNo_person(no_person);
	                    		 scrapMapper.createScrRespHealthPaymentdtl(scrRespHealthPaymentdtlVO);
	                    	 }
	                     }
	                     //건강보험 내역 Insert
	                     scrapMapper.createScrRespHealthPayment(scrRespHealthPayment);
	                     scrReqHealthVO.setNo_pbls(scrRespHealthPayment.getNo_pbls());
	        		}
	        	}
	        	KcbReqNonfiInfoVO kcbReqNonfiInfoVO = new KcbReqNonfiInfoVO();
            	kcbReqNonfiInfoVO.setNo_person(no_person);
            	kcbReqNonfiInfoVO.setCd_req("02");  //01: 소득금액증명정보, 02:  건강보험납부정보, 03: 국민연금납부정보
            	kcbReqNonfiInfoVO.setSeq_scraping_result(seq_scrap);
            	kcbReqNonfiInfoVO.setStatus("01"); //01: 대기, 02: 요청, 03: 전송성공, 04: 전송실패
            	kcbReqNonfiInfoVO.setDt_reg(DateUtil.getCurrentYMD());
            	kcbReqNonfiInfoVO.setId_frt(no_person);
            	scrapMapper.createKcbReqNonfiInfo(kcbReqNonfiInfoVO);
        	}
        	scrReqHealthVO.setNo_person(no_person);
            scrReqHealthVO.setCd_confirm(codeManager.getCodeId("cd_confirm", "납부확인서"));
            scrReqHealthVO.setSeq_scraping_result(seq_scrap);
            scrReqHealthVO.setInquiry_start_yearmonth(USER_NHIS_OUTPUT.getPAYMENT_START_YM());
            scrReqHealthVO.setInquiry_end_yearmonth(USER_NHIS_OUTPUT.getPAYMENT_END_YM());
            scrReqHealthVO.setError_cd(USER_NHIS_OUTPUT.getERROR_CODE());
            scrReqHealthVO.setError_msg(USER_NHIS_OUTPUT.getERROR_MESSAGE());
            scrReqHealthVO.setError_msg(USER_NHIS_OUTPUT.getERROR_MESSAGE());
            scrapMapper.createScrReqHealth(scrReqHealthVO);
        }
		return new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass createNTSScrap(String data)	{
		logger.debug("DATA ::: " + data);
		
		Gson gson = new Gson();
		AppNTSInfo appNTSInfo = new AppNTSInfo();

		String no_person = "";
		long seq_scrap = 0;
		String error_code = "";
		
		appNTSInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppNTSInfo.class);
        no_person 	= appNTSInfo.getNO_PERSON();
        error_code	= appNTSInfo.getERROR_CODE();
        
        logger.debug("no_person  :" + no_person);
        
        //스크래핑 조회 내역 저장
        String cd_agency = codeManager.getCodeId("cd_agency", "국세청");
        ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
        scrRsltScrapVO.setNo_person(no_person);
		scrRsltScrapVO.setCd_scrap(cd_agency);
		scrRsltScrapVO.setRslt_scraping(error_code);
	
		seq_scrap = scrapMapper.insertScrRsltScrap(scrRsltScrapVO);
		logger.info("insertScrRsltScrap result :" + seq_scrap);

		if(appNTSInfo.getUSER_NTS_OUTPUT() != null){
			NTSInfo USER_NTS_OUTPUT = appNTSInfo.getUSER_NTS_OUTPUT();
        	String taxation_year = "";
        	String cd_income = "";
        	
            //정상 조회 시에만  조회 내역 insert
            if(USER_NTS_OUTPUT.getERROR_CODE().equals("00000000"))	{
            	List<ScrRespIncomeDtlVO> scrRespIncomeDtlVO = USER_NTS_OUTPUT.getINCOME();
	        	if(scrRespIncomeDtlVO != null){
	        		for (ScrRespIncomeDtlVO scrRespIncomeDtl : scrRespIncomeDtlVO) {
	        			scrRespIncomeDtl.setNo_person(no_person);
	        			logger.debug("scrRespIncomeDtl.getReversion_year   : "+ scrRespIncomeDtl.getReversion_year());
	        			logger.debug("scrRespIncomeDtl.getBiz_licence      : "+ scrRespIncomeDtl.getBiz_licence());
	        			logger.debug("scrRespIncomeDtl.getIncome_division  : "+ scrRespIncomeDtl.getIncome_division());
	        			logger.debug("scrRespIncomeDtl.getCorp_nm          : "+ scrRespIncomeDtl.getCorp_nm());
	        			logger.debug("scrRespIncomeDtl.getAmt_income       : "+ scrRespIncomeDtl.getAmt_income());
	        			taxation_year = scrRespIncomeDtl.getReversion_year();
	                     
	        			// * KCB 코드
	        			// 01:근로소득(갑종) 연말정산, 02:근로소득(을종) 연말정산, 03:종합소득세,  04:일용직근로소득, 05:퇴직소득, 06:양도소득, 07:기타소득, 08:사업소득 연말정산, 99:기타
	                    if(scrRespIncomeDtl.getIncome_division().equals("근로소득 연말정산"))	{
	                    	cd_income = "01";
	                    }
	                    else if(scrRespIncomeDtl.getIncome_division().equals("사업소득 연말정산"))	{
	                    	cd_income = "08";
	                    }
	                    else if(scrRespIncomeDtl.getIncome_division().equals("일용근로소득(국세청 제출)"))	{
	                    	cd_income = "04";
	                    }
	                    else if(scrRespIncomeDtl.getIncome_division().equals("일용근로소득(고용노동부 제출)"))	{
	                    	cd_income = "04";
	                    }
	                    else if(scrRespIncomeDtl.getIncome_division().equals("종합소득세"))	{
	                    	cd_income = "03";
	                    }
	                    else	{
	                    	cd_income = "99";
	                    }
	                    scrRespIncomeDtl.setIncome_division(cd_income);
	                    scrRespIncomeDtl.setNo_person(no_person);
	                    scrRespIncomeDtl.setCert_division(USER_NTS_OUTPUT.getCERT_DIVISION());
	                    //건강보험 내역 Insert
	                    scrapMapper.createScrRespIncomeDtl(scrRespIncomeDtl);
	        		}
	        		
	        	}
        	  	KcbReqNonfiInfoVO kcbReqNonfiInfoVO = new KcbReqNonfiInfoVO();
            	kcbReqNonfiInfoVO.setNo_person(no_person);
            	kcbReqNonfiInfoVO.setCd_req("01");  //01: 소득금액증명정보, 02:  건강보험납부정보, 03: 국민연금납부정보
            	kcbReqNonfiInfoVO.setSeq_scraping_result(seq_scrap);
            	kcbReqNonfiInfoVO.setStatus("01"); //01: 대기, 02: 요청, 03: 전송성공, 04: 전송실패
            	kcbReqNonfiInfoVO.setDt_reg(DateUtil.getCurrentYMD());
            	kcbReqNonfiInfoVO.setId_frt(no_person);
            	scrapMapper.createKcbReqNonfiInfo(kcbReqNonfiInfoVO);
        	}
            //국세청 스크래핑 조회 내역 저장
            ScrReqCertificationVO scrReqCertificationVO = new ScrReqCertificationVO();
            scrReqCertificationVO.setNo_person(no_person);
            scrReqCertificationVO.setSeq_scraping_result(seq_scrap);
            scrReqCertificationVO.setCd_type("02"); //01 현금영수증 사용내역, 02 소득금액증명, 03 사업자등록증명, 04 사업자등록상태, 05 부가가치세 과세표준증명원, 06 표준재무제표증명, 07 납세사실증명
            scrReqCertificationVO.setTaxation_start_month(USER_NTS_OUTPUT.getTAXATION_START_MONTH());
            scrReqCertificationVO.setTaxation_end_month(USER_NTS_OUTPUT.getTAXATION_END_MONTH());
            scrReqCertificationVO.setCert_division(USER_NTS_OUTPUT.getCERT_DIVISION());
            scrReqCertificationVO.setTaxation_year(taxation_year);
            scrReqCertificationVO.setCd_income(cd_income);
            scrReqCertificationVO.setError_cd(USER_NTS_OUTPUT.getERROR_CODE());
            scrReqCertificationVO.setError_msg(USER_NTS_OUTPUT.getERROR_MESSAGE());
            scrapMapper.createScrReqCertification(scrReqCertificationVO);
        }
		return new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass createNPSScrap(String data)	{
		logger.debug("DATA ::: " + data);
		
		Gson gson = new Gson();
		AppNPSInfo appNPSInfo = new AppNPSInfo();

		String no_person = "";
		long seq_scrap = 0;
		String error_code = "";
		
		appNPSInfo = gson.fromJson(net.sf.json.JSONObject.fromObject(JSONSerializer.toJSON(data)).toString(), AppNPSInfo.class);
        no_person 	= appNPSInfo.getNO_PERSON();
        error_code	= appNPSInfo.getERROR_CODE();
        
        logger.debug("no_person  :" + no_person);
        
        //스크래핑 조회 내역 저장
        String cd_agency = codeManager.getCodeId("cd_agency", "국민연금공단");
        ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
        scrRsltScrapVO.setNo_person(no_person);
		scrRsltScrapVO.setCd_scrap(cd_agency);
		scrRsltScrapVO.setRslt_scraping(error_code);
	
		seq_scrap = scrapMapper.insertScrRsltScrap(scrRsltScrapVO);
		logger.info("insertScrRsltScrap result :" + seq_scrap);

        if(appNPSInfo.getUSER_NPS_OUTPUT() != null){
        	NPSInfo USER_NPS_OUTPUT = appNPSInfo.getUSER_NPS_OUTPUT();
        	
        	ScrReqPensionVO scrReqPensionVO = new ScrReqPensionVO();
            scrReqPensionVO.setNo_person(no_person);
            scrReqPensionVO.setCd_confirm("01"); //01 가입내역조회, 02 연금지급내역 증명서, 03 가입증명서, 04 연금산정용 가입내역확인서
            scrReqPensionVO.setSeq_scraping_result(seq_scrap);
            scrReqPensionVO.setInquiry_start_yearmonth(USER_NPS_OUTPUT.getPAYMENT_START_YM());
            scrReqPensionVO.setInquiry_end_yearmonth(USER_NPS_OUTPUT.getPAYMENT_END_YM());
            scrReqPensionVO.setError_cd(USER_NPS_OUTPUT.getERROR_CODE());
            scrReqPensionVO.setError_msg(USER_NPS_OUTPUT.getERROR_MESSAGE());
            scrapMapper.createScrReqPension(scrReqPensionVO);
			
			//정상 조회 일 경우에만 조회내역 Insert
			if(USER_NPS_OUTPUT.getERROR_CODE().equals("00000000"))	{
	        	ScrRespPensionPaymentVO scrRespPensionPaymentVO = USER_NPS_OUTPUT.getPAYMENT();
	        	if(scrRespPensionPaymentVO != null){
	        		logger.debug("scrRespPensionPaymentVO.getCnt_month_pay        : "+ scrRespPensionPaymentVO.getCnt_month_pay());
	        		logger.debug("scrRespPensionPaymentVO.getAmt_pay              : "+ scrRespPensionPaymentVO.getAmt_pay());
	        		logger.debug("scrRespPensionPaymentVO.getStart_receipt_yyyymm : "+ scrRespPensionPaymentVO.getStart_receipt_yyyymm());
	        		logger.debug("scrRespPensionPaymentVO.getAmt_est_pns_month    : "+ scrRespPensionPaymentVO.getAmt_est_pns_month());
	        		scrRespPensionPaymentVO.setNo_person(no_person);
	        		scrapMapper.createScrRespPensionPayment(scrRespPensionPaymentVO);
	        	}
	        		
	        	List<ScrRespPensionPaymentdtlVO> scrRespPensionPaymentdtl = USER_NPS_OUTPUT.getPAYMENT_DTL();
	            if(scrRespPensionPaymentdtl != null)	{
	               	for (ScrRespPensionPaymentdtlVO scrRespPensionPaymentdtlVO : scrRespPensionPaymentdtl) {
	               		scrRespPensionPaymentdtlVO.setNo_person(no_person);
	               		scrapMapper.createScrRespPensionPaymentdtl(scrRespPensionPaymentdtlVO);
	               	}
	            }
	            KcbReqNonfiInfoVO kcbReqNonfiInfoVO = new KcbReqNonfiInfoVO();
	            kcbReqNonfiInfoVO.setNo_person(no_person);
	            kcbReqNonfiInfoVO.setCd_req("03");  //01: 소득금액증명정보, 02:  건강보험납부정보, 03: 국민연금납부정보
	            kcbReqNonfiInfoVO.setSeq_scraping_result(seq_scrap);
	            kcbReqNonfiInfoVO.setStatus("01"); //01: 대기, 02: 요청, 03: 전송성공, 04: 전송실패
	            kcbReqNonfiInfoVO.setDt_reg(DateUtil.getCurrentYMD());
	            kcbReqNonfiInfoVO.setId_frt(no_person);
	            scrapMapper.createKcbReqNonfiInfo(kcbReqNonfiInfoVO);
			}
        }
        return new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public List<LinkedFcInfoVO> getLinkFcInfo(LinkedFcInfoVO linkFcInfo){
		return scrapMapper.getLinkFcInfo(linkFcInfo); 
	}
	
	@Override
	public int getLinkedFcCount(String no_person){
		return scrapMapper.getLinkedFcCount(no_person); 
	}
	
	/**
	 * 자동스크래핑 관련 정보 조회
	 * @param String
	 * @return String
	 */
	@Override
	public String getAutoScrapInfo(String cd_agency, String no_person){
		JSONObject jsonRoot = new JSONObject();
		ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
		int result;
		
		// IOS 스크래핑 내역을 합산해서 전달하기때문에 구분자 필요
		if(cd_agency.equals(codeManager.getCodeId("cd_agency", "은행")))	{
			jsonRoot.put("CODE_SCRAP", "bank");
		}
		else if(cd_agency.equals(codeManager.getCodeId("cd_agency", "카드")))	{
			jsonRoot.put("CODE_SCRAP", "card");
		}
		else if(cd_agency.equals(codeManager.getCodeId("cd_agency", "국세청")))	{
			jsonRoot.put("CODE_SCRAP", "nts");
		}
		//자동 스크래래핑 관련 조회
		FcLinkInfoVO fcLinkInfoVO = new FcLinkInfoVO();
		fcLinkInfoVO.setNo_person(no_person);
		fcLinkInfoVO.setCd_agency(cd_agency);
		List<FcLinkInfoVO> fcLinkInfoList = scrapMapper.getFcLinkInfo(fcLinkInfoVO);
			
		if(fcLinkInfoList != null && fcLinkInfoList.size() > 0)	{
			// 스크래핑 결과 테이블에 기본 값 저장(결과코드:99999)
			scrRsltScrapVO.setNo_person(no_person);
			scrRsltScrapVO.setCd_scrap(cd_agency);
			scrRsltScrapVO.setRslt_scraping("99999");
			scrRsltScrapVO.getSeq_scraping_result();
			result = scrapMapper.insertScrRsltScrap(scrRsltScrapVO);
			logger.info("insertScrRsltScrap result :" + result);
			logger.info("scrRsltScrapVO.getSeq_scraping_result() :" + scrRsltScrapVO.getSeq_scraping_result());
			
			String toDay = DateUtil.getCurrentDateTime("yyyyMMdd");
			jsonRoot.put("SEQ_SCRAP", scrRsltScrapVO.getSeq_scraping_result());
			
			if(cd_agency.equals(codeManager.getCodeId("cd_agency", "은행")))	{
				JSONArray jsonBankArr = new JSONArray();
				//계좌별 조회 내역이 없을 경우 기본 시작일로 셋팅(3개월)
				jsonRoot.put("DT_DEFAULT", DateUtil.addMonths(toDay, -3));
				for (int i = 0; i < fcLinkInfoList.size(); i++) {
					JSONObject jsonBankInfo = new JSONObject();
					String bankCode = null;
					String typeLogin = null;
					FcLinkInfoVO fcLinkInfo = fcLinkInfoList.get(i);
					//bankCode = codeManager.getCodeName("cd_coocon_fc", fcLinkInfo.getCd_fc());
					bankCode = fincorpMapper.getCooconFcCdByCdFc(fcLinkInfo.getCd_fc());
					
					typeLogin = codeManager.getCodeName("type_login", fcLinkInfo.getType_login());
							
					jsonBankInfo.put("CODE_BANK", bankCode);
					jsonBankInfo.put("TYPE_LOGIN", typeLogin);
					jsonBankInfo.put("CN", fcLinkInfo.getCn());
					
					//자동 스크래래핑 관련 은행사의 계좌 조회내역 조회(조회 시작일)				
					ScrReqBankVO scrReqBankVO = new ScrReqBankVO();
					scrReqBankVO.setNo_person(no_person);
					scrReqBankVO.setCd_fc(fcLinkInfo.getCd_fc());
					List<ScrReqBankVO> scrReqBankList = scrapMapper.getScrReqBank(scrReqBankVO);
					
					JSONArray jsonAnArr = new JSONArray();
					if(scrReqBankList != null)	{
						for (int j = 0; j < scrReqBankList.size(); j++) {
							JSONObject jsonAnInfo = new JSONObject();
							ScrReqBankVO scrReqBank = scrReqBankList.get(j);
							String errCode = scrReqBank.getError_cd();
							jsonAnInfo.put("NO_AN", scrReqBank.getNo_account());
							//정상처리 경우 조회시작일을 이전 조회완료일로 셋팅
							if(errCode.equals("00000000") ||		//정상인 경우
									errCode.equals("42110000"))	{	//거래내역이 없는 경우
								jsonAnInfo.put("DT_START", scrReqBank.getYmd_end());
								
							}
							//비정상처리 경우 조회시작일을 이전 조회시작일로 셋팅
							else	{
								jsonAnInfo.put("DT_START", scrReqBank.getYmd_stt());
							}
							jsonAnArr.add(jsonAnInfo);
						}
					}
					jsonBankInfo.put("LIST_AN", jsonAnArr);
					jsonBankArr.add(jsonBankInfo);
				}
				jsonRoot.put("LIST_BANK", jsonBankArr);

			}
			else if(cd_agency.equals(codeManager.getCodeId("cd_agency", "카드")))	{
				String startDate = "";
				JSONArray jsonCardArr = new JSONArray();
				for (int i = 0; i < fcLinkInfoList.size(); i++) {
					JSONObject jsonCardInfo = new JSONObject();
					String cardCode = null;
					String typeLogin = null;
					FcLinkInfoVO fcLinkInfo = fcLinkInfoList.get(i);
					//cardCode = codeManager.getCodeName("cd_coocon_fc", fcLinkInfo.getCd_fc());
					cardCode = fincorpMapper.getCooconFcCdByCdFc(fcLinkInfo.getCd_fc());
					typeLogin = codeManager.getCodeName("type_login", fcLinkInfo.getType_login());
					jsonCardInfo.put("CODE_CARD", cardCode);
					jsonCardInfo.put("TYPE_LOGIN", typeLogin);
					jsonCardInfo.put("CN", fcLinkInfo.getCn());
					
					//하나카드 3개월 단위 조회로 인한 예외처리
					if("hanacard".equals(cardCode))	{
						jsonCardInfo.put("LIMIT_DAYS", "80");
					}
					
					//자동 스크래래핑 관련 은행사의 계좌 조회내역 조회(조회 시작일)				
					ScrReqCardVO scrReqCardVO = new ScrReqCardVO();
					scrReqCardVO.setNo_person(no_person);
					scrReqCardVO.setCd_fc(fcLinkInfo.getCd_fc());
					scrReqCardVO.setCd_type("02"); //01 보유카드현황, 02 승인내역, 03 청구내역, 04 한도조회, 05 포인트조회
					ScrReqCardVO scrReqCard = scrapMapper.getScrReqCard(scrReqCardVO);
					
					if(scrReqCard != null)	{
						if(scrReqCard.getError_cd().equals("00000000") ||			//정상인 경우
								scrReqCard.getError_cd().equals("42110000")	)	{	//조회내역이 없는 경우
							startDate = scrReqCard.getYmd_end();
						}
						else	{
							startDate = scrReqCard.getYmd_stt();
						}
					}
					else	{
						startDate = DateUtil.addMonths(toDay, -3);
					}
					jsonCardInfo.put("DT_APPROVAL", startDate);
					
					scrReqCardVO.setCd_type("03"); //01 보유카드현황, 02 승인내역, 03 청구내역, 04 한도조회, 05 포인트조회
					scrReqCard = scrapMapper.getScrReqCard(scrReqCardVO);
					
					if(scrReqCard != null)	{
						if(scrReqCard.getError_cd().equals("00000000") ||			//정상인 경우
								scrReqCard.getError_cd().equals("42110000")	)	{	//조회내역이 없는 경우
							startDate = scrReqCard.getYmd_end();
						}
						else	{
							startDate = scrReqCard.getYmd_stt();
						}
					}
					else	{
						startDate = DateUtil.addMonths(toDay, -3).substring(0, 6);
					}
					jsonCardInfo.put("DT_CHARGE", startDate);
					jsonCardArr.add(jsonCardInfo);
				}
				jsonRoot.put("LIST_CARD", jsonCardArr);
			}
			else if(cd_agency.equals(codeManager.getCodeId("cd_agency", "국세청")))	{
				FcLinkInfoVO fcLinkInfo = fcLinkInfoList.get(0);
				String startMonth = "";
				//자동 스크래래핑 관련 은행사의 계좌 조회내역 조회(조회 시작일)				
				ScrReqCertificationVO scrReqCertificationVO = new ScrReqCertificationVO();
				scrReqCertificationVO.setNo_person(no_person);
				jsonRoot.put("CN", fcLinkInfo.getCn());
				ScrReqCertificationVO scrReqCertificationResult = scrapMapper.getScrReqCertification(scrReqCertificationVO);
				if(scrReqCertificationResult != null)	{
					if(scrReqCertificationResult.getError_cd().equals("00000000") ||			//정상인 경우
							scrReqCertificationResult.getError_cd().equals("42110000")	)	{	//조회내역이 없는 경우
						startMonth = scrReqCertificationResult.getRcpt_end_month();
					}
					else	{
						startMonth = scrReqCertificationResult.getRcpt_start_month();
					}
				}
				else	{
					startMonth = DateUtil.addMonths(toDay, -3).substring(0, 6);
				}
				jsonRoot.put("RCPT_START_MONTH", startMonth);
			}
		}
		logger.info("json object :" + jsonRoot.toString());
		return jsonRoot.toString();
	}
	
	@Override
	public ScrReqHealthVO getScrReqHealth(ScrReqHealthVO scrReqHealthVO) {
		LogUtil.debugLn(logger,"LCA getScrReqHealth");
		
		return  scrapMapper.getScrReqHealth(scrReqHealthVO);
	}
	
	@Override
	public  List<ScrRespHealthPaymentdtlVO> getScrRespHealthPaymentdtl(ScrRespHealthPaymentdtlVO scrRespHealthPaymentdtlVO) {
		LogUtil.debugLn(logger,"LCA getScrRespHealthPaymentdtl");
		
		return  scrapMapper.getScrRespHealthPaymentdtl(scrRespHealthPaymentdtlVO);
	}
	
	@Override
	public ScrReqPensionVO getScrReqPension(ScrReqPensionVO scrReqPensionVO) {
		LogUtil.debugLn(logger,"LCA getScrReqPension");
		
		return  scrapMapper.getScrReqPension(scrReqPensionVO);
	}
	
	@Override
	public  ScrRespPensionPaymentVO getScrRespPensionPayment(ScrRespPensionPaymentVO scrRespPensionPaymentVO) {
		LogUtil.debugLn(logger,"LCA getScrRespPensionPayment");
		
		return  scrapMapper.getScrRespPensionPayment(scrRespPensionPaymentVO);
	}
	
	@Override
	public  List<ScrRespPensionPaymentdtlVO> getScrRespPensionPaymentdtl(ScrRespPensionPaymentdtlVO scrRespPensionPaymentdtlVO) {
		LogUtil.debugLn(logger,"LCA getScrRespPensionPaymentdtl");
		
		return  scrapMapper.getScrRespPensionPaymentdtl(scrRespPensionPaymentdtlVO);
	}
	
	@Override
	public  List<ScrRespIncomeDtlVO> getScrRespIncomeDtl(ScrRespIncomeDtlVO scrRespIncomeDtlVO) {
		LogUtil.debugLn(logger,"LCA getScrRespIncomeDtl");
		
		return  scrapMapper.getScrRespIncomeDtl(scrRespIncomeDtlVO);
	}
}