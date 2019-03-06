package com.koscom.scrap.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.koscom.scrap.service.ScrapManager;
import com.koscom.scrap.service.ScrapWebManager;
import com.koscom.util.ReturnClass;

@Service("scrapWebManager")
public class ScrapWebManagerImpl implements ScrapWebManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ScrapWebManagerImpl.class);
	
	@Autowired
	private ScrapManager scrapManager;
//	@Override
//	public ReturnClass delBankApiLoanAnInfo(String no_person){
//		return scrapManager.delBankApiLoanAnInfo(no_person);
//	}
//	@Override
//	public ReturnClass createBankApiLoanAnInfo(List<BankApiLoanAnInfo> list) {
//		logger.info("############################################  createBankScrap");
//		logger.debug("############################### + createBankScrap");
//		return scrapManager.createBankApiLoanAnInfo(list);
//	}
	@Override
	public long insertScrRsltScrap(ScrRsltScrapVO scrRsltScrapVO) {
		logger.info("############################################  insertScrRsltScrap");
		return scrapManager.insertScrRsltScrap(scrRsltScrapVO);
	}
	@Override
	public int updateScrRsltScrap(ScrRsltScrapVO scrRsltScrapVO) {
		logger.info("############################################  updateScrRsltScrap");
		return scrapManager.updateScrRsltScrap(scrRsltScrapVO);
	}
	@Override
	public String getAutoScrapInfo(String cd_agency, String no_person)	{
		return scrapManager.getAutoScrapInfo(cd_agency, no_person);
	}
	@Override
	public ReturnClass createScrReqBank(List<ScrReqBankVO> list) {
		logger.info("############################################  createScrReqBank");
		return scrapManager.createScrReqBank(list);
	}
	@Override
	public ReturnClass insertScrReqBankHist(ScrReqBankVO scrReqBankVO) {
		logger.info("############################################  insertScrReqBankHist");
		return scrapManager.insertScrReqBankHist(scrReqBankVO);
	}
	@Override
	public ReturnClass createScrReqCard(ScrReqCardVO scrReqCardVO) {
		logger.info("############################################  createScrReqCard");
		return scrapManager.createScrReqCard(scrReqCardVO);
	}
	@Override
	public ReturnClass insertScrReqCardHist(ScrReqCardVO scrReqCardVO) {
		logger.info("############################################  insertScrReqCardHist");
		return scrapManager.insertScrReqCardHist(scrReqCardVO);
	}
	@Override
	public ScrReqHealthVO getScrReqHealth(ScrReqHealthVO scrReqHealthVO) {
		logger.info("############################################  getScrReqHealth");
		return scrapManager.getScrReqHealth(scrReqHealthVO);
	}
	@Override
	public ReturnClass createScrReqHealth(ScrReqHealthVO scrReqHealthVO) {
		logger.info("############################################  createScrReqHealth");
		return scrapManager.createScrReqHealth(scrReqHealthVO);
	}
	@Override
	public ScrReqPensionVO getScrReqPension(ScrReqPensionVO scrReqPensionVO) {
		logger.info("############################################  getScrReqPension");
		return scrapManager.getScrReqPension(scrReqPensionVO);
	}
	@Override
	public ReturnClass createScrReqPension(ScrReqPensionVO scrReqPensionVO) {
		logger.info("############################################  createScrReqPension");
		return scrapManager.createScrReqPension(scrReqPensionVO);
	}
	@Override
	public String getMaxDateSrcTransactionDetail(ScrBankApiAnInfoVO scrBankApiAnInfo)	{
		return scrapManager.getMaxDateSrcTransactionDetail(scrBankApiAnInfo);
	}
	@Override
	public String getMaxDateBankApiAnAllHistInfo(String no_person)	{
		return scrapManager.getMaxDateBankApiAnAllHistInfo(no_person);
	}
	@Override
	public ReturnClass createScrBankApiAnInfo(List<ScrBankApiAnInfoVO> list) {
		logger.info("############################################  createScrBankApiAnInfo");
		return scrapManager.createScrBankApiAnInfo(list);
	}
	@Override
	public ReturnClass createBankApiAnInfo(List<BankApiAnInfo> list) {
		logger.info("############################################  createBankScrap");
		return scrapManager.createBankApiAnInfo(list);
	}
	@Override
	public ReturnClass createScrTransactionDetail(List<AnAllListHistoryVO> list) {
		logger.info("############################################  createScrTransactionDetail");
		return scrapManager.createScrTransactionDetail(list);
	}
	@Override
	public ReturnClass createBankApiAnAllHistInfo(List<AnAllListHistoryVO> list) {
		logger.info("############################################  createBankApiAnAllHistInfo");
		return scrapManager.createBankApiAnAllHistInfo(list);
	}
	@Override
	public String getMaxDateScrSvngSvninDetail(ScrBankApiAnInfoVO scrBankApiAnInfo)	{
		return scrapManager.getMaxDateScrSvngSvninDetail(scrBankApiAnInfo);
	}
	@Override
	public String getMaxDateBankApiDepositAnHistInfo(String no_person)	{
		return scrapManager.getMaxDateBankApiDepositAnHistInfo(no_person);
	}
	@Override
	public ReturnClass createScrSvngSvninDetail(List<DepositAnListHistoryVO> list) {
		logger.info("############################################  createScrSvngSvninDetail");
		return scrapManager.createScrSvngSvninDetail(list);
	}
	@Override
	public ReturnClass createBankApiDepositAnHistInfo(List<DepositAnListHistoryVO> list) {
		logger.info("############################################  createBankApiDepositAnHistInfo");
		return scrapManager.createBankApiDepositAnHistInfo(list);
	}
	
	@Override
	public ReturnClass createScrCardInfo(List<ScrCardInfoVO> list) {
		logger.info("############################################  createScrCardInfo");
		return scrapManager.createScrCardInfo(list);
	}
	@Override
	public ReturnClass createScrCardApprovalInfo(List<ScrCardApprovalInfoVO> list) {
		logger.info("############################################  createScrCardApprovalInfo");
		return scrapManager.createScrCardApprovalInfo(list);
	}
	@Override
	public String getMaxDateScrCardApprovalInfo(Map<String, Object> parmMap)	{
		return scrapManager.getMaxDateScrCardApprovalInfo(parmMap);
	}
	
//	@Override
//	public ReturnClass insertNhisHealth(NHISInfo nhisInfo) {
//		return scrapManager.insertNhisHealth(nhisInfo);
//	}
//	@Override
//	public ReturnClass insertNTSInfo(NTSInfo nTSInfo) {
//		return scrapManager.insertNTSInfo(nTSInfo);
//	}
	@Override
	public ReturnClass createFcLinkInfo(List<FcLinkInfoVO> list) {
		logger.info("############################################  createScrap");
		return scrapManager.createFcLinkInfo(list);
	}
	@Override
	public ReturnClass updateFcLinkInfo(FcLinkInfoVO fcLinkInfoVO) {
		logger.info("############################################  updateFcLinkInfo");
		return scrapManager.updateFcLinkInfo(fcLinkInfoVO);
	}
	@Override
	public int getLinkedFcCount(String no_person) {
		return scrapManager.getLinkedFcCount(no_person);
	}
	@Override
	public List<LinkedFcInfoVO> getLinkedFcInfo(LinkedFcInfoVO linkedFcInfo) {
		return scrapManager.getLinkedFcInfo(linkedFcInfo);
	}
	@Override
	public List<LinkedFcInfoVO> getLinkFcInfo(LinkedFcInfoVO linkFcInfo) {
		return scrapManager.getLinkFcInfo(linkFcInfo);
	}
	@Override
	public ReturnClass createFcLinkInfoHist(FcLinkInfoVO fcLinkInfoVO) {
		logger.info("############################################  createFcLinkInfoHist");
		return scrapManager.createFcLinkInfoHist(fcLinkInfoVO);
	}
	@Override
	public ReturnClass createScrReqCertification(ScrReqCertificationVO scrReqCertification) {
		logger.info("############################################  createFcLinkInfoHist");
		return scrapManager.createScrReqCertification(scrReqCertification);
	}
	@Override
	public ScrReqCertificationVO getScrReqCertification(ScrReqCertificationVO scrReqCertification) {
		return scrapManager.getScrReqCertification(scrReqCertification);
	}
	@Override
	public ReturnClass createScrRespCashReceipt(List<ScrRespCashReceiptVO>  list) {
		logger.info("############################################  createScrRespCashReceipt");
		return scrapManager.createScrRespCashReceipt(list);
	}
	@Override
	public String getMaxDateScrRespCashReceipt(String no_person) {
		logger.info("############################################  getMaxDateScrRespCashReceipt");
		return scrapManager.getMaxDateScrRespCashReceipt(no_person);
	}
	@Override
	public ReturnClass createScrRespHealthPayment(List<ScrRespHealthPaymentVO>  list) {
		logger.info("############################################  createScrRespCashReceipt");
		return scrapManager.createScrRespHealthPayment(list);
	}
	@Override
	public ReturnClass createScrRespHealthPaymentdtl(List<ScrRespHealthPaymentdtlVO>  list) {
		logger.info("############################################  createScrRespCashReceipt");
		return scrapManager.createScrRespHealthPaymentdtl(list);
	}
	@Override
	public  List<ScrRespHealthPaymentdtlVO> getScrRespHealthPaymentdtl(ScrRespHealthPaymentdtlVO  scrRespHealthPaymentdtlVO) {
		logger.info("############################################  getScrRespHealthPaymentdtl");
		return scrapManager.getScrRespHealthPaymentdtl(scrRespHealthPaymentdtlVO);
	}
	@Override
	public ReturnClass createScrRespPensionPayment(ScrRespPensionPaymentVO  scrRespPensionPaymentVO) {
		logger.info("############################################  createScrRespPensionPayment");
		return scrapManager.createScrRespPensionPayment(scrRespPensionPaymentVO);
	}
	@Override
	public  ScrRespPensionPaymentVO getScrRespPensionPayment(ScrRespPensionPaymentVO  scrRespPensionPaymentVO) {
		logger.info("############################################  getScrRespPensionPayment");
		return scrapManager.getScrRespPensionPayment(scrRespPensionPaymentVO);
	}
	@Override
	public ReturnClass createScrRespPensionPaymentdtl(List<ScrRespPensionPaymentdtlVO>  list) {
		logger.info("############################################  createScrRespPensionPaymentdtl");
		return scrapManager.createScrRespPensionPaymentdtl(list);
	}
	@Override
	public  List<ScrRespPensionPaymentdtlVO> getScrRespPensionPaymentdtl(ScrRespPensionPaymentdtlVO  scrRespPensionPaymentdtlVO) {
		logger.info("############################################  getScrRespPensionPaymentdtl");
		return scrapManager.getScrRespPensionPaymentdtl(scrRespPensionPaymentdtlVO);
	}
	@Override
	public ReturnClass createScrRespIncomeDtl(List<ScrRespIncomeDtlVO>  list) {
		logger.info("############################################  createScrRespIncomeDtl");
		return scrapManager.createScrRespIncomeDtl(list);
	}
	@Override
	public List<ScrRespIncomeDtlVO> getScrRespIncomeDtl(ScrRespIncomeDtlVO  scrRespIncomeDtlVO) {
		logger.info("############################################  getScrRespIncomeDtl");
		return scrapManager.getScrRespIncomeDtl(scrRespIncomeDtlVO);
	}
//	@Override
//	public ReturnClass createScrap(Scrap scrap) {
//		logger.info("############################################  createScrap");
//		return scrapManager.createScrap(scrap);
//	}

	@Override
	public ReturnClass insertReqCarInfo(ReqCarInfo reqCarInfo) {
		return scrapManager.insertReqCarInfo(reqCarInfo);
	}

	@Override
	public ReturnClass insertRespCarInfo(RespCarInfo respCarInfo) {
		return scrapManager.insertRespCarInfo(respCarInfo);
	}

	@Override
	public ReturnClass insertReqRealEstateInfo(ReqRealEstateInfo reqRealEstateInfo) {
		return scrapManager.insertReqRealEstateInfo(reqRealEstateInfo);
	}

	@Override
	public ReturnClass insertRespRealEstateInfo(RespRealEstateInfo respRealEstateInfo) {
		return scrapManager.insertRespRealEstateInfo(respRealEstateInfo);
	}

	@Override
	public RespCertFinancestatVO getRespCertFinancestat(String no_person) {
		// TODO Auto-generated method stub
		return scrapManager.getRespCertFinancestat(no_person);
	}

	@Override
	public List<RespCertFinancestatdtlVO> getRespCertFinancestatdtl(String no_person) {
		// TODO Auto-generated method stub
		return scrapManager.getRespCertFinancestatdtl(no_person);
	}

	@Override
	public RespCertVatVO getRespCertVat(String no_person) {
		// TODO Auto-generated method stub
		return scrapManager.getRespCertVat(no_person);
	}

	@Override
	public List<RespCertVatdtlVO> getRespCertVatdtl(String no_person) {
		// TODO Auto-generated method stub
		return scrapManager.getRespCertVatdtl(no_person);
	}

	@Override
	public RespIncomeVO getRespIncome(String no_person) {
		// TODO Auto-generated method stub
		return scrapManager.getRespIncome(no_person);
	}

	@Override
	public List<RespIncomeDtlVO> getRespIncomedtl(String no_person) {
		// TODO Auto-generated method stub
		return scrapManager.getRespIncomedtl(no_person);
	}

	@Override
	public RespHealthPaymentVO getRespHealthPayment(String no_person) {
		// TODO Auto-generated method stub
		return scrapManager.getRespHealthPayment(no_person);
	}

	@Override
	public List<RespHealthPaymentdtlVO> getRespHealthPaymentdtl(String no_person) {
		// TODO Auto-generated method stub
		return scrapManager.getRespHealthPaymentdtl(no_person);
	}

	@Override
	public RespHealthQlfctnVO getRespHealthQlfctn(String no_person) {
		// TODO Auto-generated method stub
		return scrapManager.getRespHealthQlfctn(no_person);
	}

	@Override
	public List<RespHealthQlfctndtlVO> getRespHealthQlfctndtl(String no_person) {
		// TODO Auto-generated method stub
		return scrapManager.getRespHealthQlfctndtl(no_person);
	}

	@Override
	public RespHealthQlfctngainlossVO getRespHealthQlfctngainloss(String no_person) {
		// TODO Auto-generated method stub
		return scrapManager.getRespHealthQlfctngainloss(no_person);
	}
}