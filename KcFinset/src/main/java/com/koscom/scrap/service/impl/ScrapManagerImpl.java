package com.koscom.scrap.service.impl;

import java.util.List;
import java.util.Map;

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
import com.koscom.scrap.model.LinkedFcInfoVO;
import com.koscom.scrap.model.ScrBankApiAnInfoVO;
import com.koscom.scrap.model.ScrCardApprovalInfoVO;
import com.koscom.scrap.model.ScrCardInfoVO;
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
import com.koscom.scrap.model.sub.AnAllListHistoryVO;
import com.koscom.scrap.model.sub.DepositAnListHistoryVO;
import com.koscom.scrap.service.ScrapManager;
import com.koscom.util.Constant;
import com.koscom.util.DateUtil;
import com.koscom.util.LogUtil;
import com.koscom.util.ReturnClass;

@Service("scrapManager")
public class ScrapManagerImpl implements ScrapManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ScrapManagerImpl.class);
	
	@Autowired
	private ScrapMapper scrapMapper;
	
	@Autowired
	private CodeManager codeManager;
	
	@Autowired
	private CreditMapper creditMapper;
	
	/**
	 * 스크래핑 조회내역 저장
	 * @param ScrRsltScrapVO
	 * @return long
	 */
	@Override
	public long insertScrRsltScrap(ScrRsltScrapVO scrRsltScrapVO){
		
		scrapMapper.insertScrRsltScrap(scrRsltScrapVO);

		return  scrRsltScrapVO.getSeq_scraping_result();
	}
	
	/**
	 * 스크래핑 조회내역 수정
	 * @param ScrRsltScrapVO
	 * @return int
	 */
	@Override
	public int updateScrRsltScrap(ScrRsltScrapVO scrRsltScrapVO){
		return scrapMapper.updateScrRsltScrap(scrRsltScrapVO); 
	}
	
	/**
	 * 자동스크래핑 관련 정보 조회
	 * @param String
	 * @return String
	 */
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
	
	@Override
	public ReturnClass createScrReqBank(List<ScrReqBankVO> list) {
		LogUtil.debugLn(logger,"LCA createScrReqBank");

		for (ScrReqBankVO ScrReqBankVO : list) {
			logger.debug("ScrReqBankVO.getNo_person    :" + ScrReqBankVO.getNo_person()  );
			logger.debug("ScrReqBankVO.getCd_fc        :" + ScrReqBankVO.getCd_fc()      );
			logger.debug("ScrReqBankVO.getCd_account   :" + ScrReqBankVO.getCd_account() );
			logger.debug("ScrReqBankVO.getNo_account   :" + ScrReqBankVO.getNo_account() );
			logger.debug("ScrReqBankVO.getYmd_stt      :" + ScrReqBankVO.getYmd_stt()    );
			logger.debug("ScrReqBankVO.getYmd_end      :" + ScrReqBankVO.getYmd_end()    );
			
			scrapMapper.createScrReqBank(ScrReqBankVO);
       }
		logger.info(">>>> 은행 스크래핑 조회 내역 정보 insert");
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass insertScrReqBankHist(ScrReqBankVO scrReqBankVO) {
		LogUtil.debugLn(logger,"LCA insertScrReqBankHist");
		
		logger.debug("fcLinkInfoVO.getNo_person    :" + scrReqBankVO.getNo_person()    );
		logger.debug("fcLinkInfoVO.getCd_fc        :" + scrReqBankVO.getCd_fc()        );

		scrapMapper.insertScrReqBankHist(scrReqBankVO);
		
		logger.info(">>>> 은행 스크래핑 요청 내역 history 정보 insert");
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass createScrReqCard(ScrReqCardVO scrReqCardVO) {
		LogUtil.debugLn(logger,"LCA createScrReqCard");

		scrapMapper.createScrReqCard(scrReqCardVO);

		logger.info(">>>> 카드 스크래핑 조회 내역 정보 insert");
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass insertScrReqCardHist(ScrReqCardVO scrReqCardVO) {
		LogUtil.debugLn(logger,"LCA insertScrReqCardHist");
		
		scrapMapper.insertScrReqCardHist(scrReqCardVO);
		
		logger.info(">>>> 카드 스크래핑 요청 내역 history 정보 insert");
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ScrReqHealthVO getScrReqHealth(ScrReqHealthVO scrReqHealthVO) {
		LogUtil.debugLn(logger,"LCA getScrReqHealth");
		
		return  scrapMapper.getScrReqHealth(scrReqHealthVO);
	}
	
	@Override
	public ReturnClass createScrReqHealth(ScrReqHealthVO scrReqHealthVO) {
		LogUtil.debugLn(logger,"LCA createScrReqHealth");

		scrapMapper.createScrReqHealth(scrReqHealthVO);

		logger.info(">>>> 건강보험 스크래핑 조회 내역 정보 insert");
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ScrReqPensionVO getScrReqPension(ScrReqPensionVO scrReqPensionVO) {
		LogUtil.debugLn(logger,"LCA getScrReqPension");
		
		return  scrapMapper.getScrReqPension(scrReqPensionVO);
	}
	
	@Override
	public ReturnClass createScrReqPension(ScrReqPensionVO scrReqPensionVO) {
		LogUtil.debugLn(logger,"LCA createScrReqPension");

		scrapMapper.createScrReqPension(scrReqPensionVO);

		logger.info(">>>> 국민연금 스크래핑 조회 내역 정보 insert");
		return  new ReturnClass(Constant.SUCCESS);
	}

	
	@Override
	public ReturnClass createFcLinkInfo(List<FcLinkInfoVO> list) {
		LogUtil.debugLn(logger,"LCA createFcLinkInfo");

		for (FcLinkInfoVO fcLinkInfoVO : list) {
			logger.debug("fcLinkInfoVO.getNO_PERSON    :" + fcLinkInfoVO.getNO_PERSON()    );
			logger.debug("fcLinkInfoVO.getCN           :" + fcLinkInfoVO.getCN()           );
			logger.debug("fcLinkInfoVO.getCD_FC        :" + fcLinkInfoVO.getCD_FC()        );
			logger.debug("fcLinkInfoVO.getCD_LINK_STAT :" + fcLinkInfoVO.getCD_LINK_STAT() );
			logger.debug("fcLinkInfoVO.getYN_LINK      :" + fcLinkInfoVO.getYN_LINK()      );
			logger.debug("fcLinkInfoVO.getTYPE_LOGIN   :" + fcLinkInfoVO.getTYPE_LOGIN()   );
			
			scrapMapper.createFcLinkInfo(fcLinkInfoVO);
       }
		logger.info(">>>> 스크래핑 연동 정보 insert");
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass updateFcLinkInfo(FcLinkInfoVO fcLinkInfoVO) {
		LogUtil.debugLn(logger,"LCA updateFcLinkInfo");

		scrapMapper.updateFcLinkInfo(fcLinkInfoVO);

		logger.info(">>>> 스크래핑 연동 정보 update");
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public int getLinkedFcCount(String no_person){
		return scrapMapper.getLinkedFcCount(no_person); 
	}
	
	@Override
	public List<LinkedFcInfoVO> getLinkedFcInfo(LinkedFcInfoVO linkedFcInfo){
		return scrapMapper.getLinkedFcInfo(linkedFcInfo); 
	}
	
	@Override
	public List<LinkedFcInfoVO> getLinkFcInfo(LinkedFcInfoVO linkFcInfo){
		return scrapMapper.getLinkFcInfo(linkFcInfo); 
	}
	
	@Override
	public ReturnClass createFcLinkInfoHist(FcLinkInfoVO fcLinkInfoVO) {
		LogUtil.debugLn(logger,"LCA createFcLinkInfoHist");
		
		logger.debug("fcLinkInfoVO.getNO_PERSON    :" + fcLinkInfoVO.getNO_PERSON()    );
		logger.debug("fcLinkInfoVO.getCN           :" + fcLinkInfoVO.getCN()           );

		scrapMapper.createFcLinkInfoHist(fcLinkInfoVO);
		
		logger.info(">>>> 스크래핑 연동 정보 history insert");
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass createScrBankApiAnInfo(List<ScrBankApiAnInfoVO> list) {
		LogUtil.debugLn(logger,"LCA createScrBankApiAnInfo");

		for (ScrBankApiAnInfoVO scrBankApiAnInfo : list) {
			
			scrapMapper.createScrBankApiAnInfo(scrBankApiAnInfo);
				
			//대출 계좌의 경우 은행금리 수정
            //scrapMapper.updateBankInterestByLoanAnInfo(bankApiAnInfo);
			if (scrBankApiAnInfo.getType_an().equals(codeManager.getCodeName("cd_account_type","대출계좌")))	{
				logger.debug("대출 계좌 은행 금리 내역 저장 : " + scrBankApiAnInfo.getAn());
				//scrapMapper.updateBankInterestByLoanAnInfo(scrBankApiAnInfo);
			}

        }
		logger.info(">>>> 스크래핑 insert");
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass createScrReqCertification(ScrReqCertificationVO scrReqCertification)	{
		scrapMapper.createScrReqCertification(scrReqCertification);
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ScrReqCertificationVO getScrReqCertification(ScrReqCertificationVO scrReqCertification)	{
		return scrapMapper.getScrReqCertification(scrReqCertification);
	}
	
	@Override
	public ReturnClass createScrRespCashReceipt(List<ScrRespCashReceiptVO> list) {
		LogUtil.debugLn(logger,"LCA createScrRespCashReceipt");

		for (ScrRespCashReceiptVO scrRespCashReceipt : list) {
				scrapMapper.createScrRespCashReceipt(scrRespCashReceipt);
        }
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass createScrCardInfo(List<ScrCardInfoVO> list) {
		LogUtil.debugLn(logger,"LCA createScrCardInfo");

		for (ScrCardInfoVO scrCardInfo : list) {
			scrapMapper.createScrCardInfo(scrCardInfo);
        }
		logger.info(">>>> 스크래핑 insert");
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass createScrCardApprovalInfo(List<ScrCardApprovalInfoVO> list) {
		LogUtil.debugLn(logger,"LCA createScrCardInfo");

		for (ScrCardApprovalInfoVO scrCardApprovalInfo : list) {
			scrapMapper.createScrCardApprovalInfo(scrCardApprovalInfo);
        }
		logger.info(">>>> 스크래핑 insert");
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public String getMaxDateScrCardApprovalInfo(Map<String, Object> parmMap)	{
		return scrapMapper.getMaxDateScrCardApprovalInfo(parmMap);
	}
	
	@Override
	public String getMaxDateSrcTransactionDetail(ScrBankApiAnInfoVO scrBankApiAnInfo)	{
		return scrapMapper.getMaxDateSrcTransactionDetail(scrBankApiAnInfo);
	}
	
	@Override
	public ReturnClass createScrTransactionDetail(List<AnAllListHistoryVO> list) {
		LogUtil.debugLn(logger,"LCA createScrTransactionDetail");

		for (AnAllListHistoryVO anAllListHistoryVO : list) {
				scrapMapper.createScrTransactionDetail(anAllListHistoryVO);
        }
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public String getMaxDateScrSvngSvninDetail(ScrBankApiAnInfoVO scrBankApiAnInfo)	{
		return scrapMapper.getMaxDateScrSvngSvninDetail(scrBankApiAnInfo);
	}
	
	@Override
	public ReturnClass createScrSvngSvninDetail(List<DepositAnListHistoryVO> list) {
		LogUtil.debugLn(logger,"LCA createScrSvngSvninDetail");

		for (DepositAnListHistoryVO depositAnListHistoryVO : list) {
				scrapMapper.createScrSvngSvninDetail(depositAnListHistoryVO);
        }
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public String getMaxDateScrRespCashReceipt(String no_person)	{
		return scrapMapper.getMaxDateScrRespCashReceipt(no_person);
	}
	
	@Override
	public ReturnClass createScrRespHealthPayment(List<ScrRespHealthPaymentVO> list) {
		LogUtil.debugLn(logger,"LCA createScrRespHealthPayment");

		for (ScrRespHealthPaymentVO scrRespHealthPaymentVO : list) {
				scrapMapper.createScrRespHealthPayment(scrRespHealthPaymentVO);
        }
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public ReturnClass createScrRespHealthPaymentdtl(List<ScrRespHealthPaymentdtlVO> list) {
		LogUtil.debugLn(logger,"LCA createScrRespHealthPaymentdtl");

		for (ScrRespHealthPaymentdtlVO scrRespHealthPaymentdtlVO : list) {
				scrapMapper.createScrRespHealthPaymentdtl(scrRespHealthPaymentdtlVO);
        }
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public  List<ScrRespHealthPaymentdtlVO> getScrRespHealthPaymentdtl(ScrRespHealthPaymentdtlVO scrRespHealthPaymentdtlVO) {
		LogUtil.debugLn(logger,"LCA getScrRespHealthPaymentdtl");
		
		return  scrapMapper.getScrRespHealthPaymentdtl(scrRespHealthPaymentdtlVO);
	}
	
	@Override
	public ReturnClass createScrRespPensionPayment(ScrRespPensionPaymentVO scrRespPensionPaymentVO) {
		LogUtil.debugLn(logger,"LCA createScrRespPensionPayment");

		
		scrapMapper.createScrRespPensionPayment(scrRespPensionPaymentVO);
        
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public  ScrRespPensionPaymentVO getScrRespPensionPayment(ScrRespPensionPaymentVO scrRespPensionPaymentVO) {
		LogUtil.debugLn(logger,"LCA getScrRespPensionPayment");
		
		return  scrapMapper.getScrRespPensionPayment(scrRespPensionPaymentVO);
	}
	
	@Override
	public ReturnClass createScrRespPensionPaymentdtl(List<ScrRespPensionPaymentdtlVO> list) {
		LogUtil.debugLn(logger,"LCA createScrRespPensionPaymentdtl");

		for (ScrRespPensionPaymentdtlVO scrRespPensionPaymentdtlVO : list) {
				scrapMapper.createScrRespPensionPaymentdtl(scrRespPensionPaymentdtlVO);
        }
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public  List<ScrRespPensionPaymentdtlVO> getScrRespPensionPaymentdtl(ScrRespPensionPaymentdtlVO scrRespPensionPaymentdtlVO) {
		LogUtil.debugLn(logger,"LCA getScrRespPensionPaymentdtl");
		
		return  scrapMapper.getScrRespPensionPaymentdtl(scrRespPensionPaymentdtlVO);
	}
	
	@Override
	public ReturnClass createScrRespIncomeDtl(List<ScrRespIncomeDtlVO> list) {
		LogUtil.debugLn(logger,"LCA createScrRespIncomeDtl");

		for (ScrRespIncomeDtlVO scrRespIncomeDtlVO : list) {
				scrapMapper.createScrRespIncomeDtl(scrRespIncomeDtlVO);
        }
		return  new ReturnClass(Constant.SUCCESS);
	}
	
	@Override
	public  List<ScrRespIncomeDtlVO> getScrRespIncomeDtl(ScrRespIncomeDtlVO scrRespIncomeDtlVO) {
		LogUtil.debugLn(logger,"LCA getScrRespIncomeDtl");
		
		return  scrapMapper.getScrRespIncomeDtl(scrRespIncomeDtlVO);
	}
}