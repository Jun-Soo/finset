package com.koscom.credit.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koscom.credit.dao.CreditMapper;
import com.koscom.credit.model.NpacVO;
import com.koscom.credit.service.CreditManager;
import com.koscom.domain.CreditInfo;
import com.koscom.kcb.model.seg.Kcb_Segment041;
import com.koscom.person.model.PersonEtmIncomeInfo;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;

@Service("creditManager")
@Transactional
public class CreditManagerImpl implements CreditManager {

	private static final Logger logger = LoggerFactory.getLogger(CreditManagerImpl.class);

	@Autowired
	private CreditMapper creditMapper;

	/**
	 * Method Desc :
	 * 2017.12.28
	 * @param	String seq
	 * @return	NpacVO
	 */
	public NpacVO getNpacHistBySeq(String seq) {
		return creditMapper.getNpacHistBySeq(seq);
	}

	/**
	 * Method Desc :
	 * 2017.12.28
	 * @param	NpacVO npacVO
	 * @return	ReturnClass
	 */
	public ReturnClass createNpacHist(NpacVO npacVO) {
		creditMapper.createNpacHist(npacVO);
		String seq = npacVO.getSeq();
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.", (Object) seq);
	}

	/**
	 * Method Desc : 개인번호 & 전문명으로 조회이력 가져오기
	 * @param info
	 * @return
	 */
	public List<CreditInfo> getCreditInfoByNmIf(CreditInfo info) {
		return creditMapper.getCreditInfoByNmIf(info);
	}

	/**
	 * Method Desc :
	 * @param info
	 * @return
	 */
	public CreditInfo getCreditInfo(int seq) {
		return creditMapper.findBySeqOrderBySeqDesc(seq);
	}

	/**
	 * Method Desc : 신용조회 정보 저장
	 * 2017.12.28
	 * @param info
	 */
	public void saveCredit(CreditInfo info) {
		if (info != null) {
			if(StringUtil.isEmpty(info.getIdFrt())) info.setIdFrt("SYSTEM");
			logger.debug(info.toString());

			//CREDIT INFO SAVE
			creditMapper.saveCredit(info);
			//DETAIL SAVE
		}
	}


	/**
	 * Method Desc : 신용조회 정보 리스트
	 * 2017.12.28
	 * @param info
	 * @return
	 */
	public List<CreditInfo> listCreditInfo(CreditInfo info) {
//		if(!StringUtil.isEmpty(info.getNoInqKey()) && !StringUtil.isEmpty(info.getNmIf()))
//			return creditMapper.findByNoInqKeyAndNmIfAndCdCbCompOrderBySeqDesc(info.getNoInqKey() ,info.getNmIf(), info.getCdCbComp() , new Sort(new Order(Sort.Direction.DESC,"seq")));
//		else if(!StringUtil.isEmpty(info.getNoPerson()) && !StringUtil.isEmpty(info.getNmCust()) && !StringUtil.isEmpty(info.getNmIf()) && !StringUtil.isEmpty(info.getNmIfSub()))
//			return creditMapper.findByNoPersonAndNmCustAndNmIfAndNmIfSubAndCdCbCompOrderBySeqDesc(info.getNoPerson(), info.getNmCust(), info.getNmIf(),info.getNmIfSub(), info.getCdCbComp(),new Sort(new Order(Sort.Direction.DESC,"seq")));
//		else if(!StringUtil.isEmpty(info.getNoPerson()) && !StringUtil.isEmpty(info.getNmCust()) && !StringUtil.isEmpty(info.getNmIf()))
//			return creditMapper.findByNoPersonAndNmCustAndNmIfAndCdCbCompOrderBySeqDesc(info.getNoPerson(), info.getNmCust(), info.getNmIf(), info.getCdCbComp(),new Sort(new Order(Sort.Direction.DESC,"seq")));
//		else if(!StringUtil.isEmpty(info.getNoPerson()) && !StringUtil.isEmpty(info.getNmCust()))
//			return creditMapper.findByNoPersonAndNmCustAndCdCbCompOrderBySeqDesc(info.getNoPerson(), info.getNmCust(), info.getCdCbComp() ,new Sort(new Order(Sort.Direction.DESC,"seq")));
//		else if(!StringUtil.isEmpty(info.getNoPerson()) && !StringUtil.isEmpty(info.getNmIf()) && !StringUtil.isEmpty(info.getNmIfSub()))
//			return creditMapper.findByNoPersonAndNmIfAndNmIfSubAndCdCbCompOrderBySeqDesc(info.getNoPerson(), info.getNmIf(),info.getNmIfSub(),info.getCdCbComp() ,new Sort(new Order(Sort.Direction.DESC,"seq")));
//		else if(!StringUtil.isEmpty(info.getNoPerson()) && !StringUtil.isEmpty(info.getNmIf()))
//			return creditMapper.findByNoPersonAndNmIfAndCdCbCompOrderBySeqDesc(info.getNoPerson(), info.getNmIf(),info.getCdCbComp() ,new Sort(new Order(Sort.Direction.DESC,"seq")));
//
		return null;
	}


	/**
	 * Method Desc :
	 * 2017.12.28
	 * @param	String seq
	 * @return	NpacVO
	 */
	public HashMap<String, String> getKcbInfoCLOB(HashMap<String, String> searchMap) {

		return creditMapper.getKcbInfoCLOB(searchMap);
	}

	/**
	 * Method Desc : 전문SEQ 조회
	 * 2017.12.28
	 * @param
	 * @return	String SEQ
	 */
	public String getCreditInfoNextSeq() {

		return creditMapper.getCreditInfoNextSeq();
	}

	/**
	 * Method Desc : 소득정보 입력
	 * 2017.12.28
	 * @param info
	 */
	public void savePersonIncome(PersonEtmIncomeInfo personEtmIncomeInfo){
		creditMapper.insertPersonEtmIncomeInfo(personEtmIncomeInfo);
//		creditMapper.updatePersonIncome(personEtmIncomeInfo);
	}

//	/**
//	 * Method Desc : 신용정보 저장
//	 * 2017.12.28
//	 * @param 	List<CrawlingVO> listKcbCreditInfoListVO
//	 * @return	void
//	 */
//	public void saveKcbCreditInfo(List<CrawlingVO> listKcbCreditInfoListVO) {
//		for(CrawlingVO crawlingVO : listKcbCreditInfoListVO){
//			creditMapper.saveKcbCreditInfo(crawlingVO);
//		}
//	}
//
//	/**
//	 * Method Desc : KCB 회원별 대출정보 저장
//	 * 2017.12.28
//	 * @param	List<KcbLoanInfoVO> listKcbLoanInfoVO
//	 * @return	void
//	 */
//	public void saveKcbLoanInfo(List<KcbLoanInfoVO> listKcbLoanInfoVO) {
//
//
//	}

	/**
	 * Method Desc : KCB 카드개설 정보 및 거래상태정보 저장
	 * 2017.12.28
	 * @param	Kcb_Segment041 kcb_Segment041
	 * @return	void
	 */
	public void saveKcbCardInfo(Kcb_Segment041 kcb_Segment041) {


	}


	/**
	 * Method Desc : KCB 전문 수신일 조회
	 * 2017.12.26 jsp
	 * @param noPerson
	 */
	@Override
	public String selectResDt(String noPerson) {
		return creditMapper.selectResDt(noPerson);
	}

	/**
	 * Method Desc : Client 신용관리 메인
	 * 2018.01.03 jsp
	 * @param noPerson
	 */
	@Override
	public CreditInfo getCreditMainBaseInfo(String no_person) {
		return creditMapper.getCreditMainBaseInfo(no_person);
	}

	@Override
	public CreditInfo getCreditMainCntInfo(String no_person) {
		return creditMapper.getCreditMainCntInfo(no_person);
	}

	/**
	 * Method Desc : Client 신용관리_신용등급상세
	 * 2018.01.12 jsp
	 * @param noPerson, creditInfo
	 */
	@Override
	public List<CreditInfo> getCreditDetailGradeChartList(String no_person) {
		return creditMapper.getCreditDetailGradeChartList(no_person);
	}
	@Override
	public List<CreditInfo> getCreditDetailGradeInquiryList(String no_person) {
		return creditMapper.getCreditDetailGradeInquiryList(no_person);
	}
	@Override
	public List<CreditInfo> getCreditDetailGradeChangeList(CreditInfo creditInfo) {
		return creditMapper.getCreditDetailGradeChangeList(creditInfo);
	}

	/**
	 * Method Desc : Client 신용관리_카드현황,연체현황,연대보증현황
	 * 2018.01.10 jsp
	 * @param noPerson
	 */
	@Override
	public HashMap<String, String> getCreditDetailJsonInfo(String no_person) {
		return creditMapper.getCreditDetailJsonInfo(no_person);
	}

	/**
	 * Method Desc : Client 신용관리_대출현황
	 * 2018.01.12 jsp
	 * @param noPerson
	 */
	@Override
	public CreditInfo getCreditDetailDEBTCreditSum(String no_person) {
		return creditMapper.getCreditDetailDEBTCreditSum(no_person);
	}
	@Override
	public List<CreditInfo> getCreditDetailDEBTCreditList(String no_person) {
		return creditMapper.getCreditDetailDEBTCreditList(no_person);
	}
	@Override
	public CreditInfo getCreditDetailDEBTLoanSum(String no_person) {
		return creditMapper.getCreditDetailDEBTLoanSum(no_person);
	}
	@Override
	public List<CreditInfo> getCreditDetailDEBTLoanList(String no_person) {
		return creditMapper.getCreditDetailDEBTLoanList(no_person);
	}
}