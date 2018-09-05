package com.koscom.scrap.dao;

import java.util.List;
import java.util.Map;

import com.koscom.kcb.model.KcbReqNonfiInfoVO;
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

public interface ScrapMapper { // com.koscom.scrap.dao.ScrapMapper
	
	/**
	 * 스크래핑 조회내역 저장
	 * @param ScrRsltScrapVO
	 */
	int insertScrRsltScrap(ScrRsltScrapVO scrRsltScrapVO);
	
	/**
	 * 스크래핑 조회내역 수정
	 * @param ScrRsltScrapVO
	 */
	int updateScrRsltScrap(ScrRsltScrapVO scrRsltScrapVO);
	
	/**
	 * 스크래핑 가능 금융사 조회
	 * @param FcLinkInfoVO
	 */
	List<FcLinkInfoVO> getFcLinkInfo(FcLinkInfoVO fcLinkInfoVO);
	
	/**
	 * 국세청민원증명통합조회 내역 insert
	 * @param ScrReqCertificationVO
	 * @return 
	 */
	int createScrReqCertification(ScrReqCertificationVO scrReqCertification);
	
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
	int createScrRespCashReceipt(ScrRespCashReceiptVO scrRespCashReceipt);
	
	/**
	 * 현금영수증  사용내역 마지막 조회 일자/시간 조회
	 * @param String
	 * @return String
	 */
	String getMaxDateScrRespCashReceipt(String no_pserson);
	
	/**
	 * 은행스크래핑 조회 내역 조회
	 * @param ScrReqBankVO
	 */
	List<ScrReqBankVO> getScrReqBank(ScrReqBankVO scrReqBankVO);

	/**
	 * 은행스크래핑 조회 내역 insert
	 * @param ScrReqBankVO
	 * @return 
	 */
	int createScrReqBank(ScrReqBankVO scrReqBankVO);
	
	/**
	 * 은행스크래핑 조회 내역  history 정보 insert
	 * @param FcLinkInfoVO
	 * @return 
	 */
	int insertScrReqBankHist(ScrReqBankVO scrReqBankVO);
	
	/**
	 * 카드스크래핑 조회 내역 조회
	 * @param ScrReqCardVO
	 */
	ScrReqCardVO getScrReqCard(ScrReqCardVO scrReqCardVO);
	
	/**
	 * 카드스크래핑 조회 내역 insert
	 * @param ScrReqCardVO
	 * @return 
	 */
	int createScrReqCard(ScrReqCardVO scrReqCardVO);
	
	/**
	 * 카드스크래핑 조회 내역  history 정보 insert
	 * @param FcLinkInfoVO
	 * @return 
	 */
	int insertScrReqCardHist(ScrReqCardVO scrReqCardVO);
	
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
	int createScrReqHealth(ScrReqHealthVO scrReqHealthVO);
	
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
	int createScrReqPension(ScrReqPensionVO scrReqPensionVO);
	
	/**
	 * 금융사 스크래핑 연동 정보 insert
	 * @param FcLinkInfoVO
	 * @return 
	 */
	int createFcLinkInfo(FcLinkInfoVO fcLinkInfoVO);

	/**
	 * 금융사 스크랩핑 연동 상태 update
	 * @param FcLinkInfoVO
	 * @return
	 */
	int updateFcLinkInfo(FcLinkInfoVO fcLinkInfoVO);
	
	/**
	 * 금융사 스크랩핑 연동 건수
	 * @param String
	 * @return int
	 */
	int getLinkedFcCount(String no_person);
	
	/**
	 * 금융사 스크랩핑 연동 정보 select(연동된 정보)
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
	 * 금융사 스크래핑 연동 history 정보 insert
	 * @param FcLinkInfoVO
	 * @return 
	 */
	int createFcLinkInfoHist(FcLinkInfoVO fcLinkInfoVO);
	
	/**
	 * 계좌 내역 insert
	 * @param BankApiAnInfo
	 * @return 
	 */
	int createScrBankApiAnInfo(ScrBankApiAnInfoVO scrBankApiAnInfo);

	/**
	 * 입출금 계좌 상세 내역 마지막 조회 일자/시간 조회
	 * @param ScrBankApiAnInfoVO
	 * @return String
	 */
	String getMaxDateSrcTransactionDetail(ScrBankApiAnInfoVO scrBankApiAnInfo);

	/**
	 * 입출금 계좌 상세 내역 insert
	 * @param AnAllListHistoryVO
	 * @return 
	 */
	int createScrTransactionDetail(AnAllListHistoryVO anAllListHistotyVO);
	
	/**
	 * 예적금 계좌 상세 내역 마지막 조회 일자/시간 조회
	 * @param no_person
	 * @return String
	 */
	String getMaxDateScrSvngSvninDetail(ScrBankApiAnInfoVO scrBankApiAnInfo);
	
	/**
	 * 예적금 계좌 상세 내역 insert
	 * @param DepositAnListHistoryVO
	 * @return 
	 */
	int createScrSvngSvninDetail(DepositAnListHistoryVO depositAnListHistotyVO);
	
	/**
	 * 카드 내역 insert
	 * @param BankApiAnInfo
	 * @return 
	 */
	int createScrCardInfo(ScrCardInfoVO scrCardInfoVO);
	
	/**
	 * 카드승인 내역 insert
	 * @param BankApiAnInfo
	 * @return 
	 */
	int createScrCardApprovalInfo(ScrCardApprovalInfoVO scrCardApprovalInfoVO);
	
	/**
	 * 카드승인 내역 마지막 조회 일자/시간 조회
	 * @param ScrBankApiAnInfoVO
	 * @return String
	 */
	String getMaxDateScrCardApprovalInfo(Map<String, Object> parmMap);
	
	/**
	 * 건강보험 납부 내역 insert
	 * @param ScrRespHealthPaymentdtlVO
	 * @return 
	 */
	int createScrRespHealthPayment(ScrRespHealthPaymentVO scrRespHealthPaymentVO);
	
	/**
	 * 건강보험 납부 상세 내역 insert
	 * @param ScrRespHealthPaymentdtlVO
	 * @return 
	 */
	int createScrRespHealthPaymentdtl(ScrRespHealthPaymentdtlVO scrRespHealthPaymentdtlVO);
	
	/**
	 * 건강보험 납부 상세 내역 조회
	 * @param ScrRespHealthPaymentdtlVO
	 * @return 
	 */
	List<ScrRespHealthPaymentdtlVO> getScrRespHealthPaymentdtl(ScrRespHealthPaymentdtlVO scrRespHealthPaymentdtlVO);
	
	/**
	 * 소득증명 상세 내역 insert
	 * @param ScrRespIncomeDtlVO
	 * @return 
	 */
	int createScrRespIncomeDtl(ScrRespIncomeDtlVO scrRespIncomeDtlVO);
	
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
	int createScrRespPensionPayment(ScrRespPensionPaymentVO scrRespPensionPaymentVO);
	
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
	int createScrRespPensionPaymentdtl(ScrRespPensionPaymentdtlVO scrRespPensionPaymentdtlVO);
	
	/**
	 * 국민연금 납부 상세 내역 조회
	 * @param ScrRespHealthPaymentdtlVO
	 * @return 
	 */
	List<ScrRespPensionPaymentdtlVO> getScrRespPensionPaymentdtl(ScrRespPensionPaymentdtlVO scrRespPensionPaymentdtlVO);
	
	/**
	 * KCB 비금융정보 요청내역 insert
	 * @param KcbReqNonfiInfoVO
	 * @return 
	 */
	int createKcbReqNonfiInfo(KcbReqNonfiInfoVO kcbReqNonfiInfoVO);
	
	/**
	 * KCB 비금융정보 요청내역
	 * @param FcLinkInfoVO
	 */
	List<KcbReqNonfiInfoVO> getKcbReqNonfiInfo(KcbReqNonfiInfoVO kcbReqNonfiInfoVO);
	
	/**
	 * KCB 비금융정보 요청내역 상태 update
	 * @param String
	 * @return
	 */
	int updateKcbReqNonfiInfo(KcbReqNonfiInfoVO kcbReqNonfiInfoVO);
}