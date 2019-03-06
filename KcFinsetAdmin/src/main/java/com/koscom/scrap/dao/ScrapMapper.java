package com.koscom.scrap.dao;

import java.util.List;
import java.util.Map;

import com.koscom.kcb.model.KcbReqNonfiInfoVO;
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
import com.koscom.scrap.model.sub.NHISPaymentResultDetail;
import com.koscom.scrap.model.sub.NHISPaymentResultDetailSub;
import com.koscom.scrap.model.sub.NHISQlfctiResultDetail;
import com.koscom.scrap.model.sub.NHISQlfctngainlosResult;
import com.koscom.scrap.model.sub.NHISQlfctngainlosResultDetail;
import com.koscom.scrap.model.sub.NTSBizCertResult;
import com.koscom.scrap.model.sub.NTSBizStatusResult;
import com.koscom.scrap.model.sub.NTSFinanceResult;
import com.koscom.scrap.model.sub.NTSFinanceStatusDtl;
import com.koscom.scrap.model.sub.NTSIncomeResult;
import com.koscom.scrap.model.sub.NTSIncomeResultDetail;
import com.koscom.scrap.model.sub.NTSTaxPaymentDtl;
import com.koscom.scrap.model.sub.NTSTaxPaymentResult;
import com.koscom.scrap.model.sub.NTSVatResult;
import com.koscom.scrap.model.sub.NTSVatResultDtl;
import com.koscom.util.ReturnClass;

/**
 * ScrapMapper Dao Interface
 * @author 홍성준
 *
 */
public interface ScrapMapper { // com.koscom.scrap.dao.ScrapMapper

	//int delBankApiLoanAnInfo(String no_person);
	//int createBankApiLoanAnInfo(BankApiLoanAnInfo bankApiLoanAnInfo);
	
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
	 * 계좌 내역 insert
	 * @param BankApiAnInfo
	 * @return 
	 */
	int createBankApiAnInfo(BankApiAnInfo bankApiAnInfo);

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
	int createScrTransactionDetail(AnAllListHistoryVO anAllListHistotyVO);
	
	/**
	 * 입출금 계좌 상세 내역 insert
	 * @param AnAllListHistoryVO
	 * @return 
	 */
	int createBankApiAnAllHistInfo(AnAllListHistoryVO anAllListHistotyVO);
	
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
	 * 예적금 계좌 상세 내역 insert
	 * @param DepositAnListHistoryVO
	 * @return 
	 */
	int createScrSvngSvninDetail(DepositAnListHistoryVO depositAnListHistotyVO);
	
	/**
	 * 예적금 계좌 상세 내역 insert
	 * @param DepositAnListHistoryVO
	 * @return 
	 */
	int createBankApiDepositAnHistInfo(DepositAnListHistoryVO depositAnListHistotyVO);
	
	/**
	 * 은행금리수정
	 * @param bankApiLoanAnInfo
	 * @return
	 */
	int updateBankInterestByLoanAnInfo(BankApiAnInfo bankApiAnInfo);
	
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
	
	/**
	 * 스크랩핑 데이터 insert
	 * @param Scrap
	 * @return
	 */
	int createScrap(Scrap scrap);
	
	/**
	 * 건강보험 스크래핑 상태 update
	 * @param String
	 * @return
	 */
	int updateNhisScrapResult(Scrap scrap);
	
	/**
	 * 국세청 스크래핑 상태 update
	 * @param String
	 * @return
	 */
	int updateNTSScrapResult(Scrap scrap);
	
	/**
	 * 건강보험 조회 요청
	 * @param NHISInfo
	 */
	void insertReqHealth(NHISInfo nhisInfo);
	
	/**
	 * 납부확인서
	 * @param NHISPaymentResultDetail
	 */
	void insertRespHealthPayment(NHISPaymentResultDetail nhisPaymentResultDetail);
	
	/**
	 * 납부확인서 상세
	 * @param NHISPaymentResultDetailSub
	 */
	void insertRespHealthPaymentDtl(NHISPaymentResultDetailSub nhisPaymentResultDetailSub);
	
	/**
	 * 자격확인서
	 * @param nhisQlfctngainlosResult
	 */
	void insertRespQlfcti(NHISQlfctngainlosResult nhisQlfctngainlosResult);
	
	/**
	 * 자격확인서 상세
	 * @param NHISQlfctngainlosResultDetail
	 */
	void insertRespQlfctndt(NHISQlfctngainlosResultDetail nhisQlfctngainlosResultDetail);
	
	/**
	 * 자격 실득 확인서
	 * @param nhisQlfctiResultDetail
	 */
	void insertRespQlfctngainlos(NHISQlfctiResultDetail nhisQlfctiResultDetail);
	
	/**
	 * 국세청 민원조회
	 * @param nTSInfo
	 */
	void insertReqCertification(NTSInfo nTSInfo);
	
	/**
	 * 민원증명통합조회결과 부가가치세과세표준증명
	 * @param ntsVatResult
	 */
	public void insertCertVat(NTSVatResult ntsVatResult);
	
	/**
	 * 민원증명통합조회결과 부가가치세과세표준증명 상세
	 * @param ntsVatResultDtl
	 */
	public void insertCertVatDtl(NTSVatResultDtl ntsVatResultDtl);
	
	/**
	 * 민원증명통합조회결과 사업자등록증명
	 * @param ntsBizCertResult
	 */
	public void insertBizCert(NTSBizCertResult ntsBizCertResult);
	
	/**
	 * 국세청 사업자상태조회
	 * @param ntsBizStatus
	 */
	public void insertBizStatus(NTSBizStatusResult ntsBizStatusResult);
	
	/**
	 * 납부내역증명 insert
	 * @return
	 */
	public void insertTaxPayment(NTSTaxPaymentResult ntsTaxPayment);
	
	/**
	 * 납부내역증명 상세 등록
	 * @return
	 */
	public void insertTaxPaymentDtl(NTSTaxPaymentDtl ntsTaxPaymentDtl);
	
	/**
	 * 국세청 재무제표
	 * @param nTSFinanceResult
	 */
	public void insertCertFinStat(NTSFinanceResult nTSFinanceResult);
	
	/**
	 * 국세청 재무제표 상세
	 * @param nTSFinanceStatusDtl
	 * @throws Exception
	 */
	public void insertCertFinStatDtl(NTSFinanceStatusDtl nTSFinanceStatusDtl);
	
	/**
	 * 국세청 소득금액증명결과
	 * @param Output
	 */
	public void insertRespIncome(NTSIncomeResult Output); 
	
	/**
	 * 국세청 소득금액증명결과 상세
	 * @param ntsIncomeResultDetail
	 */
	public void insertRespIncomeDtl(NTSIncomeResultDetail ntsIncomeResultDetail);
	
	/**
	 * 민원증명통합조회결과_표준재무제표증명 조회
	 * @param no_person
	 * @return RespCertFinancestatVO
	 */
	public RespCertFinancestatVO getRespCertFinancestat(String no_person);
	/**
	 * 민원증명통합조회결과_표준재무제표증명 상세 조회
	 * @param no_person
	 * @return List<RespCertFinancestatdtlVO>
	 */
	public List<RespCertFinancestatdtlVO> getRespCertFinancestatdtl(String no_person);
	/**
	 * 민원증명통합조회결과_부가가치세과세표준증명 조회
	 * @param no_person
	 * @return RespCertVatVO
	 */
	public RespCertVatVO getRespCertVat(String no_person);
	/**
	 * 민원증명통합조회결과_부가가치세과세표준증명 상세 조회
	 * @param no_person
	 * @return List<RespCertVatdtlVO>
	 */
	public List<RespCertVatdtlVO> getRespCertVatdtl(String no_person);
	/**
	 * 소득금액증명원조회결과_스득증명 조회
	 * @param no_person
	 * @return RespIncomeVO
	 */
	public RespIncomeVO getRespIncome(String no_person);
	/**
	 * 소득금액증명원조회결과_스득증명 상세 조회
	 * @param no_person
	 * @return List<RespIncomeDtlVO>
	 */
	public List<RespIncomeDtlVO> getRespIncomedtl(String no_person);
	/**
	 * 납부확인서 조회
	 * @param no_person
	 * @return RespHealthPaymentVO
	 */
	public RespHealthPaymentVO getRespHealthPayment(String no_person);
	/**
	 * 납부확인서 상세 조회
	 * @param no_person
	 * @return List<RespHealthPaymentdtlVO>
	 */
	public List<RespHealthPaymentdtlVO> getRespHealthPaymentdtl(String no_person);
	/**
	 * 자격확인서 조회
	 * @param no_person
	 * @return RespHealthQlfctnVO
	 */
	public RespHealthQlfctnVO getRespHealthQlfctn(String no_person);
	/**
	 * 자격확인서 상세 조회
	 * @param no_person
	 * @return List<RespHealthQlfctndtlVO>
	 */
	public List<RespHealthQlfctndtlVO> getRespHealthQlfctndtl(String no_person);
	/**
	 * 자격득실확인서 조회
	 * @param no_person
	 * @return RespHealthQlfctngainlossVO
	 */
	public RespHealthQlfctngainlossVO getRespHealthQlfctngainloss(String no_person);
	
	/**
	 * 자동차 등록원부 스크래핑 요청 insert
	 * 
	 * @param reqCarInfo
	 * @return
	 */
	int insertReqCarInfo(ReqCarInfo reqCarInfo); 
	
	/**
	 * 자동차 등록원부 스크래핑 응답 insert
	 * 
	 * @param respCarInfo
	 * @return
	 */
	int insertRespCarInfo(RespCarInfo respCarInfo); 
	
	/**
	 * 부동산 등기부등본 스크래핑 요청 insert
	 * 
	 * @param reqRealEstateInfo
	 * @return
	 */
	int insertReqRealEstateInfo(ReqRealEstateInfo reqRealEstateInfo); 
	
	/**
	 * 부동산 등기부등본 스크래핑 응답 insert
	 * 
	 * @param RespRealEstateInfo
	 * @return
	 */
	int insertRespRealEstateInfo(RespRealEstateInfo RespRealEstateInfo);

}