package com.koscom.scrap.service.impl;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.credit.dao.CreditMapper;
import com.koscom.env.service.CodeManager;
import com.koscom.scrap.dao.ScrapMapper;
import com.koscom.scrap.model.FcLinkInfoVO;
import com.koscom.scrap.model.ScrReqBankVO;
import com.koscom.scrap.model.ScrReqCardVO;
import com.koscom.scrap.model.ScrReqCertificationVO;
import com.koscom.scrap.model.ScrRsltScrapVO;
import com.koscom.scrap.service.ScrapManager;
import com.koscom.util.DateUtil;


@Service("scrapManager")
public class ScrapManagerImpl implements ScrapManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ScrapManagerImpl.class);
	
	@Autowired
	private ScrapMapper scrapMapper;
	
	@Autowired
	private CodeManager codeManager;
	
	@Autowired
	private CreditMapper creditMapper;
	
	@Override
	public String getAutoScrapInfo(String cd_agency, String no_person) {
		
		JSONObject jsonRoot = new JSONObject();
		ScrRsltScrapVO scrRsltScrapVO = new ScrRsltScrapVO();
		int result;
	
		//자동 스크래래핑 관련 조회
		FcLinkInfoVO fcLinkInfoVO = new FcLinkInfoVO();
		fcLinkInfoVO.setNO_PERSON(no_person);
		fcLinkInfoVO.setCD_AGENCY(cd_agency);
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
					bankCode = codeManager.getCodeName("cd_coocon_fc", fcLinkInfo.getCD_FC());
					typeLogin = codeManager.getCodeName("type_login", fcLinkInfo.getTYPE_LOGIN());
							
					jsonBankInfo.put("CODE_BANK", bankCode);
					jsonBankInfo.put("TYPE_LOGIN", typeLogin);
					jsonBankInfo.put("CN", fcLinkInfo.getCN());
					
					//자동 스크래래핑 관련 은행사의 계좌 조회내역 조회(조회 시작일)				
					ScrReqBankVO scrReqBankVO = new ScrReqBankVO();
					scrReqBankVO.setNo_person(no_person);
					scrReqBankVO.setCd_fc(fcLinkInfo.getCD_FC());
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
					cardCode = codeManager.getCodeName("cd_coocon_fc", fcLinkInfo.getCD_FC());
					typeLogin = codeManager.getCodeName("type_login", fcLinkInfo.getTYPE_LOGIN());
					jsonCardInfo.put("CODE_CARD", cardCode);
					jsonCardInfo.put("TYPE_LOGIN", typeLogin);
					jsonCardInfo.put("CN", fcLinkInfo.getCN());
					
					//자동 스크래래핑 관련 은행사의 계좌 조회내역 조회(조회 시작일)				
					ScrReqCardVO scrReqCardVO = new ScrReqCardVO();
					scrReqCardVO.setNo_person(no_person);
					scrReqCardVO.setCd_fc(fcLinkInfo.getCD_FC());
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
					jsonCardInfo.put("DT_START", startDate);
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
				jsonRoot.put("CN", fcLinkInfo.getCN());
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
			logger.info("json object :" + jsonRoot.toString());
			return jsonRoot.toString();
		}
		return "";
	}
}