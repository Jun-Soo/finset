package com.koscom.scrap.service.impl;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koscom.credit.dao.CreditMapper;
import com.koscom.env.service.CodeManager;
import com.koscom.person.model.PersonEtmIncomeInfo;
import com.koscom.scrap.dao.ScrapMapper;
import com.koscom.scrap.model.BankApiAnInfo;
import com.koscom.scrap.model.FcLinkInfoVO;
import com.koscom.scrap.model.LinkedFcInfoVO;
import com.koscom.scrap.model.NHISInfo;
import com.koscom.scrap.model.NTSInfo;
import com.koscom.scrap.model.ReqCarInfo;
import com.koscom.scrap.model.ReqRealEstateInfo;
import com.koscom.scrap.model.RespCarInfo;
import com.koscom.scrap.model.RespCertFinancestatVO;
import com.koscom.scrap.model.RespCertFinancestatdtlVO;
import com.koscom.scrap.model.RespCertVatVO;
import com.koscom.scrap.model.RespCertVatdtlVO;
import com.koscom.scrap.model.RespHealthPaymentVO;
import com.koscom.scrap.model.RespHealthPaymentdtlVO;
import com.koscom.scrap.model.RespHealthQlfctnVO;
import com.koscom.scrap.model.RespHealthQlfctndtlVO;
import com.koscom.scrap.model.RespHealthQlfctngainlossVO;
import com.koscom.scrap.model.RespIncomeDtlVO;
import com.koscom.scrap.model.RespIncomeVO;
import com.koscom.scrap.model.RespRealEstateInfo;
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
import com.koscom.scrap.model.Scrap;
import com.koscom.scrap.model.sub.AnAllListHistoryVO;
import com.koscom.scrap.model.sub.DepositAnListHistoryVO;
import com.koscom.scrap.model.sub.NHISPayment;
import com.koscom.scrap.model.sub.NHISPaymentResult;
import com.koscom.scrap.model.sub.NHISPaymentResultDetail;
import com.koscom.scrap.model.sub.NHISPaymentResultDetailSub;
import com.koscom.scrap.model.sub.NHISQlfcti;
import com.koscom.scrap.model.sub.NHISQlfctiResult;
import com.koscom.scrap.model.sub.NHISQlfctiResultDetail;
import com.koscom.scrap.model.sub.NHISQlfctngainlos;
import com.koscom.scrap.model.sub.NHISQlfctngainlosResult;
import com.koscom.scrap.model.sub.NHISQlfctngainlosResultDetail;
import com.koscom.scrap.model.sub.NTSBizCert;
import com.koscom.scrap.model.sub.NTSBizCertResult;
import com.koscom.scrap.model.sub.NTSBizStatus;
import com.koscom.scrap.model.sub.NTSBizStatusResult;
import com.koscom.scrap.model.sub.NTSFinance;
import com.koscom.scrap.model.sub.NTSFinanceResult;
import com.koscom.scrap.model.sub.NTSFinanceStatusDtl;
import com.koscom.scrap.model.sub.NTSIncome;
import com.koscom.scrap.model.sub.NTSIncomeResult;
import com.koscom.scrap.model.sub.NTSIncomeResultDetail;
import com.koscom.scrap.model.sub.NTSTaxPayment;
import com.koscom.scrap.model.sub.NTSTaxPaymentDtl;
import com.koscom.scrap.model.sub.NTSTaxPaymentResult;
import com.koscom.scrap.model.sub.NTSVat;
import com.koscom.scrap.model.sub.NTSVatResult;
import com.koscom.scrap.model.sub.NTSVatResultDtl;
import com.koscom.scrap.service.ScrapManager;
import com.koscom.util.CodeUtil;
import com.koscom.util.Constant;
import com.koscom.util.NumberUtil;
import com.koscom.util.DateUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;
import com.koscom.util.LogUtil;


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
	 */
	@Override
	public long insertScrRsltScrap(ScrRsltScrapVO scrRsltScrapVO){
		
		scrapMapper.insertScrRsltScrap(scrRsltScrapVO);

		return  scrRsltScrapVO.getSeq_scraping_result();
	}
	
	/**
	 * 스크래핑 조회내역 수정
	 * @param ScrRsltScrapVO
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
	public String getAutoScrapInfo(String cd_agency, String no_person){
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
				jsonRoot.put("CODE_SCRAP", "bank");
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
				jsonRoot.put("CODE_SCRAP", "card");
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
				jsonRoot.put("CODE_SCRAP", "nts");
			
			}
			logger.info("json object :" + jsonRoot.toString());
			return jsonRoot.toString();
		}
		return "";
	}
	
//	/**
//	 * 국민건강보험공단 스크래핑 내역 입력
//	 * @return
//	 * @throws Exception
//	 */
//	@Transactional(rollbackFor=Exception.class)
//	public ReturnClass insertNhisHealth(NHISInfo nhisInfo) {
//		logger.debug(">>>> ScrapManagerImpl.insertNhisHealth:"+nhisInfo);
//		scrapMapper.insertReqHealth(nhisInfo);
//		int SEQ_HEALTH_INSU = nhisInfo.getSEQ_HEALTH_INSU();
//
//		// 납부 확인서
//		logger.info(">>>> 스크래핑 국민건강보험공단 납부 확인서");
//		NHISPayment PAYMENT = nhisInfo.getPAYMENT();
//		if(PAYMENT != null) {
//			NHISPaymentResult nhisPaymentResult = PAYMENT.getResult();
//			if(nhisPaymentResult != null) {
//				List<NHISPaymentResultDetail> nhisResultList = nhisPaymentResult.getNhisResultList();
//				for (int i = 0; i < nhisResultList.size(); i++) {
//					NHISPaymentResultDetail detail = nhisResultList.get(i);
//					detail.setSEQ_HEALTH_INSU(nhisInfo.getSEQ_HEALTH_INSU());
//					detail.setORDER_PAY_CONFIRM(i+1);
//					scrapMapper.insertRespHealthPayment(detail);
//
//					// 납부 내역 확인서
//					logger.info(">>>> 스크래핑 국민건강보험공단 납부 내역 확인서");
//					List<NHISPaymentResultDetailSub> nhisPaymentDetailList = detail.getNhisPaymentDetailList();
//					for (int j = 0; j < nhisPaymentDetailList.size(); j++) {
//						NHISPaymentResultDetailSub detailSub = nhisPaymentDetailList.get(j);
//						if(StringUtil.isNotEmpty(detailSub.getNOTICE_AMT_HEALTH_INSU_AMT())) {
//							if(!detailSub.getNOTICE_AMT_HEALTH_INSU_AMT().equals("0")) {
//								detailSub.setSEQ_HEALTH_INSU(SEQ_HEALTH_INSU);
//								detailSub.setORDER_PAY_CONFIRM(i+1);
//								detailSub.setORDER_PAY_DETAIL(j+1);
//								scrapMapper.insertRespHealthPaymentDtl(detailSub);
//							}
//						}
//					}
//				}
//			}
//		}
//
//		// 자격 확인서
//		logger.info(">>>> 스크래핑 국민건강보험공단 자격 확인서");
//		NHISQlfctngainlos QLFCTNGAINLOS = nhisInfo.getQLFCTNGAINLOS();
//		if(QLFCTNGAINLOS != null) {
//			NHISQlfctngainlosResult Result = QLFCTNGAINLOS.getResult();
//			if(Result != null) {
//				Result.setSEQ_HEALTH_INSU(SEQ_HEALTH_INSU);
//				Result.setErrorCode(QLFCTNGAINLOS.getErrorCode());
//				Result.setErrorMessage(QLFCTNGAINLOS.getErrorMessage());
//				scrapMapper.insertRespQlfcti(Result);
//
//				List<NHISQlfctngainlosResultDetail> nhisQlfctngainlosResultDetailList = Result.getNhisQlfctngainlosResultDetailList();
//				if(nhisQlfctngainlosResultDetailList != null) {
//					for (int i = 0; i < nhisQlfctngainlosResultDetailList.size(); i++) {
//						NHISQlfctngainlosResultDetail nhisQlfctngainlosResultDetail = nhisQlfctngainlosResultDetailList.get(i);
//						nhisQlfctngainlosResultDetail.setSEQ_HEALTH_INSU(SEQ_HEALTH_INSU);
//						nhisQlfctngainlosResultDetail.setORDER_QUALIFICATION_CONFIRM(i + 1);
//						scrapMapper.insertRespQlfctndt(nhisQlfctngainlosResultDetail);
//					}
//				}
//			}
//		}
//
//		// 자격 득실 확인서
//		logger.info(">>>> 스크래핑 국민건강보험공단 자격 득실 확인서");
//		NHISQlfcti QLFCTI = nhisInfo.getQLFCTI();
//		if(QLFCTI != null) {
//			NHISQlfctiResult result = QLFCTI.getResult();
//			if(result != null) {
//				List<NHISQlfctiResultDetail> nhisQlfctiResultDetailList = result.getNisQlfctiResultDetailList();
//				if(nhisQlfctiResultDetailList != null) {
//					for (int i = 0; i < nhisQlfctiResultDetailList.size(); i++) {
//						NHISQlfctiResultDetail nhisQlfctiResultDetail = nhisQlfctiResultDetailList.get(i);
//						nhisQlfctiResultDetail.setSEQ_HEALTH_INSU(SEQ_HEALTH_INSU);
//						nhisQlfctiResultDetail.setErrorCode(QLFCTI.getErrorCode());
//						nhisQlfctiResultDetail.setErrorMessage(QLFCTI.getErrorMessage());
//						nhisQlfctiResultDetail.setSEQ_QUALIFICATION_GAINSNLOSSES(i + 1);
//						scrapMapper.insertRespQlfctngainlos(nhisQlfctiResultDetail);
//					}
//				}
//			}
//		}
//
//		return new ReturnClass(Constant.SUCCESS);
//	}
//	
//	/**
//	 * 국세청 스크래핑 내역 입력
//	 * @param nTSInfo
//	 * @return
//	 * @throws Exception
//	 */
//	@Transactional(rollbackFor=Exception.class)
//	public ReturnClass insertNTSInfo(NTSInfo nTSInfo) {
//		
//		scrapMapper.insertReqCertification(nTSInfo);
//		int SEQ_REQ = nTSInfo.getSEQ_REQ();
//
//		// 부가가치세과세표준증명
//		logger.info(">>>> 부가가치세과세표준증명");
//		NTSVat VAT = nTSInfo.getVAT();
//		if(VAT != null) {
//			NTSVatResult ntsVatResult = VAT.getResult();
//			ntsVatResult.setErrorCode(VAT.getErrorCode());
//			ntsVatResult.setErrorMessage(VAT.getErrorMessage());
//			ntsVatResult.setSEQ_REQ(SEQ_REQ);
//			scrapMapper.insertCertVat(ntsVatResult);
//			List<NTSVatResultDtl> ntsVatResultDtlList = ntsVatResult.getNtsVatResultDtlList();
//
//			if(ntsVatResultDtlList != null) {
//				for (int i = 0; i < ntsVatResultDtlList.size(); i++) {
//					NTSVatResultDtl temp = ntsVatResultDtlList.get(i);
//					if(temp != null) {
//						temp.setSEQ_REQ(SEQ_REQ);
//						temp.setORDER_VAT(i+1);
//						scrapMapper.insertCertVatDtl(temp);
//					}
//				}
//			}
//		}
//
//		// 사업자등록증명
//		logger.info(">>>> 사업자등록증명");
//		NTSBizCert BIZCERT = nTSInfo.getBIZCERT();
//		if(BIZCERT != null) {
//			NTSBizCertResult result = BIZCERT.getResult();
//			if(result != null) {
//				result.setSEQ_REQ(SEQ_REQ);
//				result.setErrorCode(BIZCERT.getErrorCode());
//				result.setErrorMessage(BIZCERT.getErrorMessage());
//				result.setPBLS_YMD(result.getPBLS_YMD().substring(0, 8));
//				scrapMapper.insertBizCert(result);
//			}
//		}
//
//		// 사업자 등록 상태
//		logger.info(">>>> 사업자 등록 상태");
//		NTSBizStatus nTSBizStatus = nTSInfo.getBIZSTATUS();
//		if(nTSBizStatus != null) {
//			NTSBizStatusResult ntsBizStatus = nTSBizStatus.getResult();
//			if(ntsBizStatus != null) {
//				ntsBizStatus.setSEQ_REQ(SEQ_REQ);
//				ntsBizStatus.setErrorCode(nTSBizStatus.getErrorCode());
//				ntsBizStatus.setErrorMessage(nTSBizStatus.getErrorMessage());
//				ntsBizStatus.setINQUIRY_TARGET_BIZ_NO(nTSBizStatus.getINQUIRY_TARGET_BIZ_NO());
//				scrapMapper.insertBizStatus(ntsBizStatus);
//			}
//		}
//
//		// 납부내역증명
//		logger.info(">>>> 납부내역증명");
//		NTSTaxPayment TAXPAYMENT = nTSInfo.getTAXPAYMENT();
//		if(TAXPAYMENT != null) {
//			NTSTaxPaymentResult taxPayment = TAXPAYMENT.getResult();
//			if(taxPayment != null) {
//				taxPayment.setSEQ_REQ(SEQ_REQ);
//				taxPayment.setErrorCode(TAXPAYMENT.getErrorCode());
//				taxPayment.setErrorMessage(TAXPAYMENT.getErrorMessage());
//				scrapMapper.insertTaxPayment(taxPayment);
//				List<NTSTaxPaymentDtl> taxPaymentList = taxPayment.getTaxPaymentList();
//				if(taxPaymentList != null) {
//					for (int i = 0; i < taxPaymentList.size(); i++) {
//						NTSTaxPaymentDtl taxPaymentDtl = taxPaymentList.get(i);
//						if(taxPaymentDtl != null) {
//							taxPaymentDtl.setSEQ_REQ(SEQ_REQ);
//							taxPaymentDtl.setORDER_TAXPMNT_CERT_DETAIL(i+1);
//							scrapMapper.insertTaxPaymentDtl(taxPaymentDtl);
//						}
//					}
//				}
//			}
//		}
//
//		// 소득금액증명
//		logger.info(">>>> 소득금액증명");
//		NTSIncome INCOME = nTSInfo.getINCOME();
//		if(INCOME != null) {
//			NTSIncomeResult Result = INCOME.getResult();
//			if(Result != null) {
//				Result.setSEQ_REQ(SEQ_REQ);
//				scrapMapper.insertRespIncome(Result);
//				List<NTSIncomeResultDetail> ntsIncomeResultDetailList = Result.getNtsIncomeResultDetailList();
//				if(ntsIncomeResultDetailList != null) {
//					for (int i = 0; i < ntsIncomeResultDetailList.size(); i++) {
//						NTSIncomeResultDetail detail = ntsIncomeResultDetailList.get(i);
//						detail.setSEQ_REQ(SEQ_REQ);
//						detail.setORDER_INCOME_CERT_DETAIL(i+1);
//						scrapMapper.insertRespIncomeDtl(detail);
//					}
//				}
//			}
//		}
//
//		// 재무재표
//		logger.info(">>>> 재무재표");
//		List<NTSFinance> FINANCEList = nTSInfo.getFINANCE();
//		if(FINANCEList != null) {
//			for (int i = 0; i < FINANCEList.size(); i++) {
//				NTSFinance nTSFinance = FINANCEList.get(i);
//				if(nTSFinance != null) {
//					NTSFinanceResult Result = nTSFinance.getResult();
//					if(Result != null) {
//						Result.setSEQ_REQ(SEQ_REQ);
//						Result.setErrorCode(nTSFinance.getErrorCode());
//						Result.setErrorMessage(nTSFinance.getErrorMessage());
//						scrapMapper.insertCertFinStat(Result);
//						List<NTSFinanceStatusDtl> finDtlList = Result.getFinDtlList();
//						for (int j = 0; j < finDtlList.size(); j++) {
//							NTSFinanceStatusDtl nTSFinanceStatusDtl = finDtlList.get(j);
//							nTSFinanceStatusDtl.setREVERSION_YEAR(Result.getREVERSION_YEAR());
//							nTSFinanceStatusDtl.setSEQ_REQ(SEQ_REQ);
//							nTSFinanceStatusDtl.setORDER_FINANCIALSTAT(j+1);
//							scrapMapper.insertCertFinStatDtl(nTSFinanceStatusDtl);
//						}
//					}
//				}
//			}
//		}
//
//		return new ReturnClass(Constant.SUCCESS);
//	}
	
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

//	@Override
//	public ReturnClass createScrap(Scrap scrap) {
//		ReturnClass returnClass = new ReturnClass(Constant.FAILED);
//		
//		logger.info(">>>> 스크래핑 insert");
//		logger.info(">>>> scrap : " + scrap.toString());
//		int saveResult = scrapMapper.createScrap(scrap);
//		logger.debug(">>>> 저장결과 : " + saveResult);
//		int SEQ_SCRAPING_RESULT = scrap.getSEQ_SCRAPING_RESULT();
//		logger.debug(">>>> SEQ_SCRAPING_RESULT : " + SEQ_SCRAPING_RESULT);
//		if(1 != saveResult) {
//			return new ReturnClass(Constant.FAILED);
//		} else {
//			NHISInfo NHIS_OUTPUT = scrap.getNHIS_OUTPUT();
//			logger.debug(">>>> NHIS_OUTPUT : " + NHIS_OUTPUT);
//			if(NHIS_OUTPUT != null) {
//				NHIS_OUTPUT.setSEQ_SCRAPING_RESULT(scrap.getSEQ_SCRAPING_RESULT());
//				logger.info(">>>> 스크래핑 국민건강보험공단 start");
//				returnClass = insertNhisHealth(NHIS_OUTPUT);
//				logger.info(">>>> 스크래핑 국민건강보험공단 insert 완료 결과 : " + returnClass.getCd_result());
//				if(StringUtil.isNotEmpty(returnClass.getCd_result())) {
//					if(returnClass.getCd_result().equals(Constant.FAILED)) {
//						return new ReturnClass(returnClass.getCd_result());
//					}
//				}
//				
//				RespHealthPaymentVO respHealthPayment = null;
//				respHealthPayment = getRespHealthPayment(scrap.getNO_PERSON());
//				
//				// 직장인가입자인 경우 건강보험료로 연봉 추정
//				if( respHealthPayment != null && respHealthPayment.getMember_division().equals("직장가입자") )	{
//					logger.info(">>>> 건강보험료로 연봉계산 start");	
//					// 연소득추정금액 계산
//	                int nAveCnt = 0;
//	                long nTolAmt = 0;	// 합계금액
//	                long nAveAmt = 0;	// 평균금액
//	                double insuRate = NumberUtil.stringToDouble(codeManager.getCodeName("cd_premium_rate", "NHIS_PREMIUM_RATE"), 0); // 건강보험보험료율
//	
//	                // 직장인인 경우		: 보험료율 / 100 / 2
//	                double dInsuRate = insuRate / 100 / 2;
//	                
//	                // 건강보험 납부 내역 조회
//	                List<RespHealthPaymentdtlVO> listRespHealthPaymentdtl = null;
//	                RespHealthPaymentdtlVO list = null;
//	                long     amt_year_income = 0   ;//연소득금액
//	                
//	                listRespHealthPaymentdtl = getRespHealthPaymentdtl(scrap.getNO_PERSON());
//	
//		                // 내역이 존재하는 경우
//	                if( listRespHealthPaymentdtl != null && listRespHealthPaymentdtl.size() > 0 ){
//	
//	                    nAveCnt = listRespHealthPaymentdtl.size();
//	                    // 내역 건수가 3건 이상 인 경우 최신 3건으로 설정
//	                    if( nAveCnt >= 3 ) {
//	                        nAveCnt = 3;
//	                    }
//	
//	                    for(int i = 0; i < nAveCnt; i++){
//	                        list = listRespHealthPaymentdtl.get(i);
//	                        nTolAmt += list.getNotice_amt_health_insu_amt();
//	                    }
//	                    nAveAmt = nTolAmt / nAveCnt;
//	                    amt_year_income = (long)(((double)(nAveAmt / dInsuRate)) * (double)12);
//	
//	                } else {
//	                	logger.error("연소득 계산을 위한 건강보험 납부내역 조회 실패");
//	                	return new ReturnClass(Constant.FAILED);
//	                }
//					
//	                // 연소득 계산 내역 DB Insert 
//					PersonEtmIncomeInfo personEtmIncomeInfo = new PersonEtmIncomeInfo();
//					personEtmIncomeInfo.setNo_person(scrap.getNO_PERSON());
//					personEtmIncomeInfo.setCd_etm_basic(scrap.getINQUIRY_PATH());
//					personEtmIncomeInfo.setAmt_etm_income(Long.toString(amt_year_income));
//					personEtmIncomeInfo.setYmd_etm_basic(DateUtil.getCurrentDateTime("yyyyMMdd"));
//					personEtmIncomeInfo.setStd_year(DateUtil.getThisYear());
//				
//					creditMapper.insertPersonEtmIncomeInfo(personEtmIncomeInfo);
//				}
//			}
//			else {
//				logger.info(">>>> 스크래핑 국민건강보험공단 상태 변경 ");
//				scrap.setHEALTH_INSU_RESULT("999");
//				scrapMapper.updateNhisScrapResult(scrap);
//			}
//					
//			NTSInfo NTS_OUTPUT = scrap.getNTS_OUTPUT();
//			logger.debug(">>>> NTS_OUTPUT : " + NTS_OUTPUT);
//			if(NTS_OUTPUT != null) {
//				NTS_OUTPUT.setSEQ_SCRAPING_RESULT(scrap.getSEQ_SCRAPING_RESULT());
//				logger.info(">>>> 스크래핑 국세청 start");
//				returnClass = insertNTSInfo(NTS_OUTPUT);
//				logger.info(">>>> 스크래핑 국세청 insert 완료결과: " + returnClass.getCd_result());
//			}
//			else {
//				logger.info(">>>> 스크래핑 국세청 상태 변경");
//				scrap.setNTNLTXSRVC_RESULT("999");
//				scrapMapper.updateNTSScrapResult(scrap);
//			}
//			
//			return returnClass;
//		}
//	}
//	@Override
//	public ReturnClass delBankApiLoanAnInfo(String no_person) {
//
//		LogUtil.debugLn(logger,"delBankApiLoanAnInfono_person:" + no_person);
//
//		if (1 != scrapMapper.delBankApiLoanAnInfo(no_person)) {
//			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
//		}
//		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
//	}
//	@Override
//	public ReturnClass createBankApiLoanAnInfo(List<BankApiLoanAnInfo> list) {
//		LogUtil.debugLn(logger,"LCA createBankScrap");
//
//		for (BankApiLoanAnInfo bankApiLoanAnInfo : list) {
//			logger.debug("loanAnListVO.getAN                :" + bankApiLoanAnInfo.getAn()           );
//			logger.debug("bankApiLoanAnInfo.getTYPE         :" + bankApiLoanAnInfo.getType()         );
//			logger.debug("bankApiLoanAnInfo.getLOAN_BALANCE :" + bankApiLoanAnInfo.getLoan_balance() );
//			logger.debug("bankApiLoanAnInfo.getLOAN_CEILING :" + bankApiLoanAnInfo.getLoan_ceiling() );
//			logger.debug("bankApiLoanAnInfo.getNEW_DATE     :" + bankApiLoanAnInfo.getNew_date()     );
//			logger.debug("bankApiLoanAnInfo.getLAST_DATE    :" + bankApiLoanAnInfo.getLast_date()    );
//			logger.debug("bankApiLoanAnInfo.getLENDING_RATE :" + bankApiLoanAnInfo.getLending_rate() );
//			logger.debug("bankApiLoanAnInfo.getFEW_DAYS     :" + bankApiLoanAnInfo.getFew_days()     );
//			logger.debug("bankApiLoanAnInfo.getINTEREST_DATE:" + bankApiLoanAnInfo.getInterest_date());
//			
//			//if(Long.parseLong(StringUtil.NVL(bankApiLoanAnInfo.getLending_rate(), "0")) > 0) {
//			if(Double.parseDouble(StringUtil.NVL(bankApiLoanAnInfo.getLending_rate(), "0")) > 0) {
//				scrapMapper.createBankApiLoanAnInfo(bankApiLoanAnInfo);
//				
//				//은행금리 수정
//	            scrapMapper.updateBankInterestByLoanAnInfo(bankApiLoanAnInfo);
//			}
//        }
//		logger.info(">>>> 스크래핑 insert");
//		return  new ReturnClass(Constant.SUCCESS);
//	}
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
	public ReturnClass createBankApiAnInfo(List<BankApiAnInfo> list) {
		LogUtil.debugLn(logger,"LCA createBankScrap");

		for (BankApiAnInfo bankApiAnInfo : list) {
			logger.debug("loanAnListVO.getAN            :" + bankApiAnInfo.getAn()           );
			logger.debug("bankApiAnInfo.getTYPE         :" + bankApiAnInfo.getType()         );
			logger.debug("bankApiAnInfo.getPRINCIPAL    :" + bankApiAnInfo.getPrincipal()    );
			logger.debug("bankApiAnInfo.getBALANCE      :" + bankApiAnInfo.getBalance()      );
			logger.debug("bankApiAnInfo.getLOAN_BALANCE :" + bankApiAnInfo.getLoan_balance() );
			logger.debug("bankApiAnInfo.getLOAN_CEILING :" + bankApiAnInfo.getLoan_ceiling() );
			logger.debug("bankApiAnInfo.getNEW_DATE     :" + bankApiAnInfo.getNew_date()     );
			logger.debug("bankApiAnInfo.getLAST_DATE    :" + bankApiAnInfo.getLast_date()    );
			logger.debug("bankApiAnInfo.getINTEREST_RATE:" + bankApiAnInfo.getInterest_rate());
			logger.debug("bankApiAnInfo.getPROFIT_RATE  :" + bankApiAnInfo.getProfit_rate()  );
			logger.debug("bankApiAnInfo.getFEW_DAYS     :" + bankApiAnInfo.getFew_days()     );
			logger.debug("bankApiAnInfo.getINTEREST_DATE:" + bankApiAnInfo.getInterest_date());
			
			scrapMapper.createBankApiAnInfo(bankApiAnInfo);
				
			//대출 계좌의 경우 은행금리 수정
            //scrapMapper.updateBankInterestByLoanAnInfo(bankApiAnInfo);
			if (bankApiAnInfo.getType().equals(codeManager.getCodeName("cd_account_type","대출계좌")))	{
				logger.debug("대출 계좌 은행 금리 내역 저장 : " + bankApiAnInfo.getAn());
				scrapMapper.updateBankInterestByLoanAnInfo(bankApiAnInfo);
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
	public String getMaxDateBankApiAnAllHistInfo(String no_person)	{
		return scrapMapper.getMaxDateBankApiAnAllHistInfo(no_person);
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
	public ReturnClass createBankApiAnAllHistInfo(List<AnAllListHistoryVO> list) {
		LogUtil.debugLn(logger,"LCA createBankApiAnAllHistInfo");

		for (AnAllListHistoryVO anAllListHistoryVO : list) {
				scrapMapper.createBankApiAnAllHistInfo(anAllListHistoryVO);
        }
		return  new ReturnClass(Constant.SUCCESS);
	}
	@Override
	public String getMaxDateScrSvngSvninDetail(ScrBankApiAnInfoVO scrBankApiAnInfo)	{
		return scrapMapper.getMaxDateScrSvngSvninDetail(scrBankApiAnInfo);
	}
	@Override
	public String getMaxDateBankApiDepositAnHistInfo(String no_person)	{
		return scrapMapper.getMaxDateBankApiDepositAnHistInfo(no_person);
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
	public ReturnClass createBankApiDepositAnHistInfo(List<DepositAnListHistoryVO> list) {
		LogUtil.debugLn(logger,"LCA createBankApiDepositAnHistInfo");

		for (DepositAnListHistoryVO depositAnListHistoryVO : list) {
				scrapMapper.createBankApiDepositAnHistInfo(depositAnListHistoryVO);
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
	
	@Override
	public RespCertFinancestatVO getRespCertFinancestat(String no_person) {
		return scrapMapper.getRespCertFinancestat(no_person);
	}
	@Override
	public List<RespCertFinancestatdtlVO> getRespCertFinancestatdtl(String no_person) {
		return scrapMapper.getRespCertFinancestatdtl(no_person);
	}
	@Override
	public RespCertVatVO getRespCertVat(String no_person) {
		return scrapMapper.getRespCertVat(no_person);
	}
	@Override
	public List<RespCertVatdtlVO> getRespCertVatdtl(String no_person) {
		return scrapMapper.getRespCertVatdtl(no_person);
	}
	@Override
	public RespIncomeVO getRespIncome(String no_person) {
		return scrapMapper.getRespIncome(no_person);
	}
	@Override
	public List<RespIncomeDtlVO> getRespIncomedtl(String no_person) {
		return scrapMapper.getRespIncomedtl(no_person);
	}
	@Override
	public RespHealthPaymentVO getRespHealthPayment(String no_person) {
		return scrapMapper.getRespHealthPayment(no_person);
	}
	@Override
	public List<RespHealthPaymentdtlVO> getRespHealthPaymentdtl(String no_person) {
		return scrapMapper.getRespHealthPaymentdtl(no_person);
	}
	@Override
	public RespHealthQlfctnVO getRespHealthQlfctn(String no_person) {
		return scrapMapper.getRespHealthQlfctn(no_person);
	}
	@Override
	public List<RespHealthQlfctndtlVO> getRespHealthQlfctndtl(String no_person) {
		return scrapMapper.getRespHealthQlfctndtl(no_person);
	}
	@Override
	public RespHealthQlfctngainlossVO getRespHealthQlfctngainloss(String no_person) {
		return scrapMapper.getRespHealthQlfctngainloss(no_person);
	}

	@Override
	public ReturnClass insertReqCarInfo(ReqCarInfo reqCarInfo) {
		if(1 != scrapMapper.insertReqCarInfo(reqCarInfo)) {
			logger.info("처리에 실패하였습니다. 다시 시도해주세요"+reqCarInfo.getSeq_car_register());
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다. 다시 시도해주세요", reqCarInfo);
		}
		logger.info("처리에 성공"+reqCarInfo.getSeq_car_register());
		return new ReturnClass(Constant.SUCCESS, "정상처리하였습니다.", reqCarInfo);
	}

	@Override
	public ReturnClass insertRespCarInfo(RespCarInfo respCarInfo) {
		if(1 != scrapMapper.insertRespCarInfo(respCarInfo)) {
			logger.info("처리에 실패하였습니다. 다시 시도해주세요");
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다. 다시 시도해주세요");
		}
		logger.info("처리에 성공");
		return new ReturnClass(Constant.SUCCESS, "정상처리하였습니다.");
	}

	@Override
	public ReturnClass insertReqRealEstateInfo(ReqRealEstateInfo reqRealEstateInfo) {
		if(1 != scrapMapper.insertReqRealEstateInfo(reqRealEstateInfo)) {
			logger.info("처리에 실패하였습니다. 다시 시도해주세요"+reqRealEstateInfo.getSeq_realstate_register());
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다. 다시 시도해주세요", reqRealEstateInfo);
		}
		logger.info("처리에 성공"+reqRealEstateInfo.getSeq_realstate_register());
		return new ReturnClass(Constant.SUCCESS, "정상처리하였습니다.", reqRealEstateInfo);
	}

	@Override
	public ReturnClass insertRespRealEstateInfo(RespRealEstateInfo respRealEstateInfo) {
		if(1 != scrapMapper.insertRespRealEstateInfo(respRealEstateInfo)) {
			logger.info("처리에 실패하였습니다. 다시 시도해주세요");
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다. 다시 시도해주세요");
		}
		logger.info("처리에 성공");
		return new ReturnClass(Constant.SUCCESS, "정상처리하였습니다.");
	}
}