package com.koscom.scrap.dao;

import java.util.List;

import com.koscom.scrap.model.FcLinkInfoVO;
import com.koscom.scrap.model.ScrReqBankVO;
import com.koscom.scrap.model.ScrReqCardVO;
import com.koscom.scrap.model.ScrReqCertificationVO;
import com.koscom.scrap.model.ScrRsltScrapVO;

/**
 * ScrapMapper Dao Interface
 * @author 홍성준
 *
 */
public interface ScrapMapper { // com.koscom.scrap.dao.ScrapMapper

	/**
	 * 스크래핑 가능 금융사 조회
	 * @param FcLinkInfoVO
	 * @return List<FcLinkInfoVO>
	 */
	List<FcLinkInfoVO> getFcLinkInfo(FcLinkInfoVO fcLinkInfoVO);
	
	/**
	 * 스크래핑 조회내역 저장
	 * @param ScrRsltScrapVO
	 * @return int
	 */
	int insertScrRsltScrap(ScrRsltScrapVO scrRsltScrapVO);
	
	/**
	 * 은행스크래핑 조회 내역 조회
	 * @param ScrReqBankVO
	 * @return List<ScrReqBankVO>
	 */
	List<ScrReqBankVO> getScrReqBank(ScrReqBankVO scrReqBankVO);
	
	/**
	 * 카드스크래핑 조회 내역 조회
	 * @param ScrReqCardVO
	 * @return ScrReqCardVO
	 */
	ScrReqCardVO getScrReqCard(ScrReqCardVO scrReqCardVO);
	
	/**
	 * 국세청민원증명통합조회 내역 select
	 * @param ScrReqCertificationVO
	 * @return ScrReqCertificationVO
	 */
	ScrReqCertificationVO getScrReqCertification(ScrReqCertificationVO scrReqCertification);
}