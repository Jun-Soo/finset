package com.koscom.scrap.service;

import java.util.List;
import java.util.Map;

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
import com.koscom.util.ReturnClass;

public interface ScrapManager {
	/**
	 * 스크래핑 조회내역 저장
	 * @param ScrRsltScrapVO
	 */
	long insertScrRsltScrap(ScrRsltScrapVO scrRsltScrapVO);
	
	/**
	 * 스크래핑 조회내역 수정
	 * @param ScrRsltScrapVO
	 */
	int updateScrRsltScrap(ScrRsltScrapVO scrRsltScrapVO);
	
	/**
	 * 자동스크래핑 관련  정보 조회
	 * @param String
	 * @return String
	 */
	String getAutoScrapInfo(String cd_agency, String no_person);
	
	//ReturnClass insertNhisHealth(NHISInfo nhisInfo);
	//ReturnClass insertNTSInfo(NTSInfo nTSInfo);

	/**
	 * 은행스크래핑 조회 내역 insert
	 * @param ScrReqBankVO
	 * @return 
	 */
	ReturnClass createScrReqBank(List<ScrReqBankVO> list);
	
	/**
	 * 은행스크래핑 조회 내역  history 정보 insert
	 * @param FcLinkInfoVO
	 * @return 
	 */
	ReturnClass insertScrReqBankHist(ScrReqBankVO scrReqBankVO);
	
	/**
	 * 카드스크래핑 조회 내역 insert
	 * @param ScrReqBankVO
	 * @return 
	 */
	ReturnClass createScrReqCard(ScrReqCardVO scrReqCardVO);
	
	/**
	 * 카드스크래핑 조회 내역  history 정보 insert
	 * @param FcLinkInfoVO
	 * @return 
	 */
	ReturnClass insertScrReqCardHist(ScrReqCardVO scrReqCardVO);
	
	/**
	 * 건강보험 스크래핑 조회 내역 조회
	 * @param ScrReqHealthVO
	 */
	ScrReqHealthVO getScrReqHealth(ScrReqHealthVO scrReqHealthVO);
	
	/**
	 * 건강보험 스크래핑 조회 내역 insert
	 * @param ScrReqHealthVO
	 * @return 
	 */
	ReturnClass createScrReqHealth(ScrReqHealthVO scrReqHealthVO);
	
	/**
	 * 국민연금 스크래핑 조회 내역 조회
	 * @param ScrReqPensionVO
	 */
	ScrReqPensionVO getScrReqPension(ScrReqPensionVO scrReqPensionVO);
	
	/**
	 * 국민연금 스크래핑 조회 내역 insert
	 * @param ScrReqPensionVO
	 * @return 
	 */
	ReturnClass createScrReqPension(ScrReqPensionVO scrReqPensionVO);
	
	/**
	 * 금융사 스크랩핑 연동 정보 insert
	 * @param FcLinkInfoVO
	 * @return
	 */
	ReturnClass createFcLinkInfo(List<FcLinkInfoVO> list);
	
	/**
	 * 금융사 스크랩핑 연동 상태 update
	 * @param FcLinkInfoVO
	 * @return
	 */
	ReturnClass updateFcLinkInfo(FcLinkInfoVO fcLinkInfoVO);
	
	/**
	 * 금융사 스크랩핑 연동 건수
	 * @param String
	 * @return int
	 */
	int getLinkedFcCount(String no_person);
	
	/**
	 * 금융사 스크랩핑 연동 정보 select
	 * @param LinkedFcInfoVO
	 * @return LinkedFcInfo
	 */
	List<LinkedFcInfoVO> getLinkedFcInfo(LinkedFcInfoVO linkedFcInfo);
	
	/**
	 * 금융사 스크랩핑 연동 정보 select(전체 정보)
	 * @param LinkedFcInfoVO
	 * @return LinkedFcInfo
	 */
	List<LinkedFcInfoVO> getLinkFcInfo(LinkedFcInfoVO linkFcInfo);
	
	/**
	 * 금융사 스크랩핑 연동 history 정보 insert
	 * @param FcLinkInfoVO
	 * @return
	 */
	ReturnClass createFcLinkInfoHist(FcLinkInfoVO fcLinkInfoVO);
	
	/**
	 * 국세청민원증명통합조회 내역 insert
	 * @param ScrReqCertificationVO
	 * @return 
	 */
	ReturnClass createScrReqCertification(ScrReqCertificationVO scrReqCertification);
	
	/**
	 * 국세청민원증명통합조회 내역 select
	 * @param ScrReqCertificationVO
	 * @return ScrReqCertificationVO
	 */
	ScrReqCertificationVO getScrReqCertification(ScrReqCertificationVO scrReqCertification);
	
	/**
	 * 현금영수증  사용내역 insert
	 * @param ScrReqCertificationVO
	 * @return 
	 */
	ReturnClass createScrRespCashReceipt(List<ScrRespCashReceiptVO> scrRespCashReceipt);
	
	/**
	 * 현금영수증  사용내역 마지막 조회 일자/시간 조회
	 * @param String
	 * @return String
	 */
	String getMaxDateScrRespCashReceipt(String no_pserson);
	
	/**
	 * 스크랩핑 데이터 insert
	 * @param scrap
	 * @return
	 */
//	ReturnClass createScrap(Scrap scrap);
	RespCertFinancestatVO getRespCertFinancestat(String no_person);
	List<RespCertFinancestatdtlVO> getRespCertFinancestatdtl(String no_person);
	RespCertVatVO getRespCertVat(String no_person);
	List<RespCertVatdtlVO> getRespCertVatdtl(String no_person);
	RespIncomeVO getRespIncome(String no_person);
	List<RespIncomeDtlVO> getRespIncomedtl(String no_person);
	RespHealthPaymentVO getRespHealthPayment(String no_person);
	List<RespHealthPaymentdtlVO> getRespHealthPaymentdtl(String no_person);
	RespHealthQlfctnVO getRespHealthQlfctn(String no_person);
	List<RespHealthQlfctndtlVO> getRespHealthQlfctndtl(String no_person);
	RespHealthQlfctngainlossVO getRespHealthQlfctngainloss(String no_person);
	
	/**
	 * 자동차 등록원부 스크래핑 요청 insert
	 * 
	 * @param reqCarInfo
	 * @return
	 */
	ReturnClass insertReqCarInfo(ReqCarInfo reqCarInfo);
	
	/**
	 * 자동차 등록원부 스크래핑 응답 insert
	 * 
	 * @param respCarInfo
	 * @return
	 */
	ReturnClass insertRespCarInfo(RespCarInfo respCarInfo);
	
	/**
	 * 부동산 등기부등본 스크래핑 요청 insert
	 * 
	 * @param reqRealEstateInfo
	 * @return
	 */
	ReturnClass insertReqRealEstateInfo(ReqRealEstateInfo reqRealEstateInfo);
	
	/**
	 * 부동산 등기부등본 스크래핑 응답 insert
	 * 
	 * @param respRealEstateInfo
	 * @return
	 */
	ReturnClass insertRespRealEstateInfo(RespRealEstateInfo respRealEstateInfo);

	//ReturnClass createBankApiLoanAnInfo(List<BankApiLoanAnInfo> list);
	/**
	 * 계좌 내역 insert
	 * @param ScrBankApiAnInfoVO
	 * @return
	 */
	ReturnClass createScrBankApiAnInfo(List<ScrBankApiAnInfoVO> list);
	
	/**
	 * 계좌 내역 insert
	 * @param BankApiAnInfo
	 * @return
	 */
	ReturnClass createBankApiAnInfo(List<BankApiAnInfo> list);
	
	/**
	 * 입출금 계좌 상세 내역 마지막 조회 일자/시간 조회
	 * @param ScrBankApiAnInfoVO
	 * @return String
	 */
	String getMaxDateSrcTransactionDetail(ScrBankApiAnInfoVO scrBankApiAnInfo);
	
	/**
	 * 입출금 계좌 상세 내역 마지막 조회 일자/시간 조회
	 * @param no_person
	 * @return String
	 */
	String getMaxDateBankApiAnAllHistInfo(String no_person);

	/**
	 * 입출금 계좌 상세 내역 insert
	 * @param AnAllListHistoryVO
	 * @return 
	 */
	ReturnClass createScrTransactionDetail(List<AnAllListHistoryVO> list);

	/**
	 * 입출금 계좌 상새 내역 insert(입출금 내역)
	 * @param AnAllListHistoryVO
	 * @return
	 */
	ReturnClass createBankApiAnAllHistInfo(List<AnAllListHistoryVO> list);
	
	/**
	 * 예적금 계좌 상세 내역 마지막 조회 일자/시간 조회
	 * @param no_person
	 * @return String
	 */
	String getMaxDateScrSvngSvninDetail(ScrBankApiAnInfoVO scrBankApiAnInfo);
	
	/**
	 * 예적금 계좌 상세 내역 마지막 조회 일자/시간 조회
	 * @param no_person
	 * @return String
	 */
	String getMaxDateBankApiDepositAnHistInfo(String no_person);
	
	/**
	 * 예적금 계좌 상새 내역 insert(입출금 내역)
	 * @param DepositAnListHistoryVO
	 * @return
	 */
	ReturnClass createScrSvngSvninDetail(List<DepositAnListHistoryVO> list);
	
	/**
	 * 예적금 계좌 상새 내역 insert(입출금 내역)
	 * @param DepositAnListHistoryVO
	 * @return
	 */
	ReturnClass createBankApiDepositAnHistInfo(List<DepositAnListHistoryVO> list);
	
	/**
	 * 카드 내역 insert
	 * @param ScrCardInfoVO
	 * @return
	 */
	ReturnClass createScrCardInfo(List<ScrCardInfoVO> list);
	
	/**
	 * 카드승인 내역 insert
	 * @param ScrCardApprovalInfoVO
	 * @return
	 */
	ReturnClass createScrCardApprovalInfo(List<ScrCardApprovalInfoVO> list);
	
	/**
	 * 카드승인 내역 마지막 조회 일자/시간 조회
	 * @param String
	 * @return String
	 */
	String getMaxDateScrCardApprovalInfo(Map<String, Object> parmMap);
	
	/**
	 * 건강보험 납부 내역 insert
	 * @param ScrRespHealthPaymentdtlVO
	 * @return 
	 */
	ReturnClass createScrRespHealthPayment(List<ScrRespHealthPaymentVO> list);
	
	/**
	 * 건강보험 납부 상세 내역 insert
	 * @param ScrRespHealthPaymentdtlVO
	 * @return 
	 */
	ReturnClass createScrRespHealthPaymentdtl(List<ScrRespHealthPaymentdtlVO> list);
	
	/**
	 * 건강보험 납부 상세 내역 조회
	 * @param LinkedFcInfoVO
	 * @return LinkedFcInfo
	 */
	List<ScrRespHealthPaymentdtlVO> getScrRespHealthPaymentdtl(ScrRespHealthPaymentdtlVO scrRespHealthPaymentdtlVO);
	
	/**
	 * 소득증명 상세 내역 insert
	 * @param ScrRespIncomeDtlVO
	 * @return 
	 */
	ReturnClass createScrRespIncomeDtl(List<ScrRespIncomeDtlVO> list);
	
	/**
	 * 소득증명 상세 내역 조회
	 * @param ScrRespIncomeDtlVO
	 * @return 
	 */
	List<ScrRespIncomeDtlVO> getScrRespIncomeDtl(ScrRespIncomeDtlVO scrRespIncomeDtlVO);
	
	/**
	 * 국민연금 납부 내역 insert
	 * @param ScrRespPensionPaymentVO
	 * @return 
	 */
	ReturnClass createScrRespPensionPayment(ScrRespPensionPaymentVO scrRespPensionPaymentVO);
	
	/**
	 * 국민연금 납부 내역 조회
	 * @param ScrRespHealthPaymentVO
	 * @return 
	 */
	ScrRespPensionPaymentVO getScrRespPensionPayment(ScrRespPensionPaymentVO scrRespPensionPaymentVO);
	
	/**
	 * 국민연금 납부 상세 내역 insert
	 * @param ScrRespPensionPaymentdtlVO
	 * @return 
	 */
	ReturnClass createScrRespPensionPaymentdtl(List<ScrRespPensionPaymentdtlVO> scrRespPensionPaymentdtlVO);
	
	/**
	 * 국민연금 납부 상세 내역 조회
	 * @param ScrRespHealthPaymentdtlVO
	 * @return 
	 */
	List<ScrRespPensionPaymentdtlVO> getScrRespPensionPaymentdtl(ScrRespPensionPaymentdtlVO scrRespPensionPaymentdtlVO);
	//ReturnClass delBankApiLoanAnInfo(String no_person);
}