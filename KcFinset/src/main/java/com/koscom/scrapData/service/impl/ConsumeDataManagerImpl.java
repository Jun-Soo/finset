/*
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180726~0801
 * 소비 지출 - 데이터를 가공하고 ConsumeMapper.java를 호출하기 위한 클래스
 * 
 * 수정
 * VITCOM  SYSTEM MANAGEMENT  KHK  20180726~0801
 * 카드명 입력하는 로직 추가
 */
package com.koscom.scrapData.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.contents.model.ContentsVO;
import com.koscom.scrapData.dao.ConsumeDataMapper;
import com.koscom.scrapData.model.BusinessTypeInfoVO;
import com.koscom.scrapData.model.ConsumeDataForm;
import com.koscom.scrapData.model.ConsumeDataVO;
import com.koscom.scrapData.service.ConsumeDataManager;
import com.koscom.util.DateUtil;

@Service("consumeDataManager")
public class ConsumeDataManagerImpl implements ConsumeDataManager {

	private static final Logger logger = LoggerFactory.getLogger(ConsumeDataManagerImpl.class);

	@Autowired
	private ConsumeDataMapper consumeDataMapper;

	@Override
	public void saveConsumeData(ConsumeDataForm consumeForm) throws Exception {
		setDefaultTmFrom(consumeForm);
		setTmFrom(consumeForm);
		saveScrCardApprovalInfo(consumeForm);
		saveScrRespCashReceipt(consumeForm);
		saveScrTransactionDetail(consumeForm);
	}
	
	/**
	 * 3개원 전 날짜로 기본적으로 세팅
	 * @param consumeForm
	 */
	private void setDefaultTmFrom(ConsumeDataForm consumeForm) throws Exception{
		try{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			cal.add(Calendar.MONTH, -3);
			String tm_from = df.format(cal.getTime());
			consumeForm.setTm_from(tm_from);
		} catch(Exception e) {
			throw new Exception("소비, 지출 데이터 저장을 위해 기본 날짜를 설정하는 도중 에러가 발생했습니다");
		}
	}
	
	/**
	 * 이전에 조회했던 마지막 날짜로 세팅
	 * @param consumeForm
	 * @throws Exception
	 */
	private void setTmFrom(ConsumeDataForm consumeForm) throws Exception{
		try{
			String tm_from = consumeDataMapper.getTmFromConsumeInfo(consumeForm.getNo_person());
			if(tm_from == null) {
				return;
			} else if(tm_from.equals("")) {
				return;
			} else {
				consumeForm.setTm_from(tm_from);
			}
		} catch(Exception e) {
			throw new Exception("이전 조회했던 마지막 시간을 가져오는데 에러가 발생했습니다.");
		}
	}
	
	/**
	 * 소비지출 데이터를 확보하기 위해 카드 승인 내역 스크래핑 데이터 조회 및 저장
	 * @param consumeForm
	 * @return
	 */
	private int saveScrCardApprovalInfo(ConsumeDataForm consumeForm) throws Exception {
		logger.debug("saveScrCardApprovalInfo");
		
		List<Map<String, String>> scrCardList = new ArrayList<Map<String,String>>();
		List<ConsumeDataVO> consumeList = new ArrayList<ConsumeDataVO>();
		int insertCnt = 0;
		
		try{
		scrCardList = consumeDataMapper.listScrCardApprovalInfo(consumeForm);
			if(scrCardList!=null){
				if(scrCardList.size()>0) {
					for(Map<String, String> scrCardMap: scrCardList) {
						//raw데이터
						String no_person = scrCardMap.get("NO_PERSON");
						String cd_fc = scrCardMap.get("CD_FC");
						String dt_approval = scrCardMap.get("DT_APPROVAL");
						String tm_approval = scrCardMap.get("TM_APPROVAL");
						String no_approval = scrCardMap.get("NO_APPROVAL");
//						String type_card = scrCardMap.get("TYPE_CARD"); V와 L 밖에 없는데 이에대한 논의가 필요해보임
						String no_card = scrCardMap.get("NO_CARD");
						String nm_member = scrCardMap.get("NM_MEMBER");
						String type_sales = scrCardMap.get("TYPE_SALES"); //0: 기타, 1: 일시불, 2:할부, 3:현금서비스, 4: 포인트 일시불, 5: 포인트 할부
						String period_installment = scrCardMap.get("PERIOD_INSTALLMENT");
						String amt_approval = scrCardMap.get("AMT_APPROVAL");
						String ymd_cancel = scrCardMap.get("YMD_CANCEL");
//						String dt_payment = scrCardMap.get("DT_PAYMENT_DUE");
						String no_biz_member = scrCardMap.get("NO_BIZ_MEMBER");
						//String cd_biz_member = scrCardMap.get("CD_BIZ_MEMBER");
						String type_biz_member = scrCardMap.get("TYPE_BIZ_MEMBER");	//업종명
						//String cd_currency = scrCardMap.get("CD_CURRENCY");
						//String cd_in_out = scrCardMap.get("CD_IN_OUT");
						//String dt_frt = scrCardMap.get("DT_FRT");
						
						if(type_sales.equals("0")||type_sales.equals("4")||type_sales.equals("5")) {
							continue;
						}
						
						//실제 사용될 변수
						String type_in_out = "02";		//01:수입, 02:지출
						String means_consume = "01";	//디폴트는 카드
						String nm_card = null;
						String type_card = null;
						String dt_trd = dt_approval;
						String tm_trd = tm_approval;
						String no_biz = no_biz_member;
						String nm_biz = type_biz_member;
						ContentsVO contentsVO = getCodeByNmBusiness(cd_fc, type_biz_member, no_person);
						String cd_class="";
						String cd_type="";
						if(contentsVO==null){
							cd_class = "99";			//기타
							cd_type = "999";			//기타
							
							BusinessTypeInfoVO businessTypeInfoVO =
									new BusinessTypeInfoVO(
											cd_fc,
											nm_biz,
											"99999",
											"NEW",
											null,
											"NEW",
											null
									);
							consumeDataMapper.createNewBusinessTypeInfo(businessTypeInfoVO);
						} else {
							cd_class = contentsVO.getCd_class();
							cd_type = contentsVO.getCd_type();
						}
						String cd_consume_class = null;
						String contents = nm_member;
						String memo = null;
						String grade = null;
						String amt_in_out = amt_approval;
						String mon_installment = period_installment;
						String mon_remaining = "0";
						String yn_pay_installment = "N";
						if(type_sales.equals("2")) {
							yn_pay_installment = "Y";
						}
						String yn_delete = "N";
						String yn_cancel = "N";
						if(ymd_cancel!=null) {
							if(!ymd_cancel.equals("")){
								yn_cancel = "Y";
							}
						}
						String yn_auto = "N";
						String yn_budget_except = "N";
						String yn_person_regist = "N";
						String id_frt = no_person;
						Date dt_frt = null;
						String id_lst = no_person;
						Date dt_lst = null;

						ConsumeDataVO consumeVO =
								new ConsumeDataVO(
										no_person,
										0,
										type_in_out,
										means_consume,
										cd_fc,
										nm_card,
										no_card,
										type_card,
										dt_trd,
										tm_trd,
										no_biz,
										nm_biz,
										cd_class,
										cd_type,
										cd_consume_class,
										contents,
										memo,
										grade,
										amt_in_out,
										no_approval,
										mon_installment,
										mon_remaining,
										yn_pay_installment,
										yn_delete,
										yn_cancel,
										yn_auto,
										yn_budget_except,
										yn_person_regist,
										id_frt,
										dt_frt,
										id_lst,
										dt_lst
										);
						logger.debug(consumeVO.toString());
						if(type_sales.equals("2")) {
							List<ConsumeDataVO> installmentList = getInstallmentList(consumeVO);
							consumeList.addAll(installmentList);
						}
						consumeList.add(consumeVO);
					}
				}
			}
		} catch (Exception e) {
			logger.error("카드승인내역 스크래핑 데이터를 가져오는 도중 에러가 발생했습니다.");
			e.printStackTrace();
			throw new Exception("카드승인내역 스크래핑 데이터를 가져오는 도중 에러가 발생했습니다.");
		}
		try{
			for(ConsumeDataVO consumeVO:consumeList) {
				insertCnt+= createConsumeInfo(consumeVO);
			}
		} catch(Exception e) {
			logger.error("카드승인내역 스크래핑 데이터를 DB에 집어넣는 도중 에러가 발생했습니다.");
			e.printStackTrace();
			throw new Exception("카드승인내역 스크래핑 데이터를 DB에 집어넣는 도중 에러가 발생했습니다."); 
		}
		return insertCnt;
	}
	
	/**
	 * 소비지출 데이터를 확보하기 위해 현금영수증 스크래핑 데이터 조회 및 저장
	 * @param consumeForm
	 * @return
	 */
	private int saveScrRespCashReceipt(ConsumeDataForm consumeForm) throws Exception{
		logger.debug("saveScrRespCashReceipt");
		List<Map<String, String>> scrCashReceiptList = new ArrayList<Map<String,String>>();
		List<ConsumeDataVO> consumeList = new ArrayList<ConsumeDataVO>();
		int insertCnt = 0;
		String nts = getNtsCode();
		try{
		scrCashReceiptList = consumeDataMapper.listScrRespCashReceipt(consumeForm);
		if(scrCashReceiptList!=null) {
			if(scrCashReceiptList.size()>0) {
				for(Map<String, String> scrCashReceiptMap:scrCashReceiptList) {
					//raw데이터
					String no_person = scrCashReceiptMap.get("NO_PERSON");
					String ymd_deal = scrCashReceiptMap.get("YMD_DEAL");
					String time_deal = scrCashReceiptMap.get("TIME_DEAL");
					String nm_affiliate = scrCashReceiptMap.get("NM_AFFILIATE");
					String amt_use = scrCashReceiptMap.get("AMT_USE");
					String no_approval = scrCashReceiptMap.get("NO_APPROVAL");
					//String type_id_check = scrCashReceiptMap.get("TYPE_ID_CHECK"); //신분확인수단
					//String type_deal = scrCashReceiptMap.get("TYPE_DEAL"); //거래구분
					//String yn_deduction = scrCashReceiptMap.get("YN_DEDUCTION"); //공제여부
					//String type_issue = scrCashReceiptMap.get("TYPE_ISSUE"); //발행구분
					//String dt_frt = scrCashReceiptMap.get("DT_FRT");
					
					//실제 사용할 변수들
					String type_in_out = "02";
					String means_consume = "03";	//01:카드, 02:현금, 03:현금영수증, 04:입출금내역
					String cd_fc = nts;		//국세청 
					String nm_card = null;
					String no_card = null;
					String type_card = null;
					String dt_trd = ymd_deal;
					String tm_trd = time_deal;
					String no_biz = null;
					String nm_biz = "현금영수증";
					String cd_class = "99";		//기타
					String cd_type = "999";		//기타
					String cd_consume_class = null;
					String contents = nm_affiliate;
					String memo = null;
					String grade = null;
					String amt_in_out = amt_use;
					String mon_installment = "0";
					String mon_remaining = "0";
					String yn_pay_installment = null;
					String yn_delete = "N";
					String yn_cancel = "N";
					String yn_auto = "N";
					String yn_budget_except = "N";
					String yn_person_regist = "N";
					String id_frt = no_person;
					Date dt_frt = null;
					String id_lst = no_person;
					Date dt_lst = null;
					
					ConsumeDataVO consumeVO = new ConsumeDataVO(
							no_person,
							0,
							type_in_out,
							means_consume,
							cd_fc,
							nm_card,
							no_card,
							type_card,
							dt_trd,
							tm_trd,
							no_biz,
							nm_biz,
							cd_class,
							cd_type,
							cd_consume_class,
							contents,
							memo,
							grade,
							amt_in_out,
							no_approval,
							mon_installment,
							mon_remaining,
							yn_pay_installment,
							yn_delete,
							yn_cancel,
							yn_auto,
							yn_budget_except,
							yn_person_regist,
							id_frt,
							dt_frt,
							id_lst,
							dt_lst
							);
					consumeList.add(consumeVO);
				}
			}
		}
		} catch(Exception e) {
			logger.error("현금영수증 스크래핑 데이터를 가져오는 도중 에러가 발생했습니다.");
			e.printStackTrace();
			throw new Exception("현금영수증 스크래핑 데이터를 가져오는 도중 에러가 발생했습니다.");
		}
		try{
			for(ConsumeDataVO consumeVO:consumeList) {
				insertCnt+=createConsumeInfo(consumeVO);
			}
		} catch(Exception e) {
			logger.error("현금영수증 스크래핑 데이터를 DB에 집어넣는 도중 에러가 발생했습니다.");
			e.printStackTrace();
			throw new Exception("현금영수증 스크래핑 데이터를 DB에 집어넣는 도중 에러가 발생했습니다.");
		}
		return insertCnt;
	}
	
	/**
	 * 소비지출 데이터를 확보하기 위해 카드 승인 내역 스크래핑 데이터 조회 및 저장- 차후 해당 VO로 받아야 한다.
	 * @param consumeForm
	 * @return
	 */
	private int saveScrTransactionDetail(ConsumeDataForm consumeForm) throws Exception {
		logger.debug("saveScrTransactionDetail");
		
		List<Map<String, String>> transactionDetailList = new ArrayList<Map<String,String>>();
		List<ConsumeDataVO> consumeList = new ArrayList<ConsumeDataVO>();
		int insertCnt = 0;
		
		try{
			transactionDetailList = consumeDataMapper.listScrTransactionDetail(consumeForm);
			if(transactionDetailList!=null){
				if(transactionDetailList.size()>0) {
					for(Map<String, String> transactionMap: transactionDetailList) {
						//raw데이터
						String no_person = transactionMap.get("NO_PERSON");
						String an = transactionMap.get("AN");
						String dt_trd = transactionMap.get("DT_TRD");
						String tm_trd = transactionMap.get("TM_TRD");
//						String cd_crncy = transactionMap.get("CD_CRNCY");
						String amt_wdrl = transactionMap.get("AMT_WDRL");
						String amt_dep = transactionMap.get("AMT_DEP");
//						String balance = transactionMap.get("BALANCE");
						String doc1 = transactionMap.get("DOC1"); //0: 기타, 1: 일시불, 2:할부, 3:현금서비스, 4: 포인트 일시불, 5: 포인트 할부
						String doc2 = transactionMap.get("DOC2");
//						String dealway1 = transactionMap.get("DEALWAY1");
//						String dealway2 = transactionMap.get("DEALWAY2");
//						String dt_frt = transactionMap.get("DT_FRT");
						String cd_class = transactionMap.get("CD_CLASS");
						String cd_type = transactionMap.get("CD_TYPE");
						String cd_fc = transactionMap.get("CD_FC");
						String nm_an = transactionMap.get("NM_AN");
						
						//실제 사용될 변수
						String type_in_out = "";		//01:수입, 02:지출
						if(amt_wdrl == null || amt_wdrl.equals("") ||amt_wdrl.equals("0")){
							type_in_out = "01";
						} else {
							type_in_out = "02";
						}
						String means_consume = "04";	//디폴트는 카드
						String nm_card = nm_an;
						String no_card = an;
						String type_card = null;
						String no_biz = null;
						String nm_biz = null;
						String cd_consume_class = cd_class + cd_type;
						String contents = "";
						if(doc1 == null || doc1.equals("")) {
							contents = doc2;
						} else {
							contents = doc1;
						}
						String memo = null;
						String grade = null;
						String amt_in_out = "";
						if(type_in_out.equals("01")) {
							amt_in_out = amt_dep;
						} else {
							amt_in_out = amt_wdrl;
						}
						String no_approval = null;
						String mon_installment = "0";
						String mon_remaining = "0";
						String yn_pay_installment = "N";
						String yn_delete = "N";
						String yn_cancel = "N";
						String yn_auto = "Y";
						String yn_budget_except = "N";
						String yn_person_regist = "N";
						String id_frt = no_person;
						Date dt_frt = null;
						String id_lst = no_person;
						Date dt_lst = null;

						ConsumeDataVO consumeVO =
								new ConsumeDataVO(
										no_person,
										0,
										type_in_out,
										means_consume,
										cd_fc,
										nm_card,
										no_card,
										type_card,
										dt_trd,
										tm_trd,
										no_biz,
										nm_biz,
										cd_class,
										cd_type,
										cd_consume_class,
										contents,
										memo,
										grade,
										amt_in_out,
										no_approval,
										mon_installment,
										mon_remaining,
										yn_pay_installment,
										yn_delete,
										yn_cancel,
										yn_auto,
										yn_budget_except,
										yn_person_regist,
										id_frt,
										dt_frt,
										id_lst,
										dt_lst
										);
						logger.debug(consumeVO.toString());
						consumeList.add(consumeVO);
					}
				}
			}
		} catch (Exception e) {
			logger.error("계좌 입출금 내역을 가져오는 도중 에러가 발생했습니다.");
			e.printStackTrace();
			throw new Exception("계좌 입출금 내역을 가져오는 도중 에러가 발생했습니다.");
		}
		try{
			for(ConsumeDataVO consumeVO:consumeList) {
				insertCnt+= consumeDataMapper.createConsumeInfoTransaction(consumeVO);
			}
		} catch(Exception e) {
			logger.error("계좌 입출금 내역을 DB에 집어넣는 도중 에러가 발생했습니다.");
			e.printStackTrace();
			throw new Exception("계좌 입출금 내역을 DB에 집어넣는 도중 에러가 발생했습니다."); 
		}
		return insertCnt;
	}
	
	/**
	 * 소비지출 테이블에 추가
	 * @param consumeVO
	 * @return
	 */
	@Override
	public int createConsumeInfo(ConsumeDataVO consumeVO) {
		logger.debug("createConsumeInfo");
		return consumeDataMapper.createConsumeInfo(consumeVO);
	}
	
	/**
	 * 업종명으로 코드 조회
	 * @param cd_fc
	 * @param nm_business
	 * @return
	 */
	private ContentsVO getCodeByNmBusiness(String cd_fc, String nm_business, String no_person){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("cd_fc", cd_fc);
		paramMap.put("nm_business", nm_business);
		
		String cd_consume_class = consumeDataMapper.getCdConsumeClass(paramMap);
		if(cd_consume_class==null) {
			return null;
		} else if(cd_consume_class.equals("")) {
			return null;
		}
		
		paramMap = new HashMap<>();
		paramMap.put("cd_consume_class", cd_consume_class);
		paramMap.put("no_person", no_person);
		ContentsVO contentsVO = consumeDataMapper.getCodeByCdConsumeClass(paramMap);
		return contentsVO;
	}
	
	/**
	 * 국세청 코드 조회
	 * @return
	 */
	private String getNtsCode() {
		String nts = ""; 
		try{
			nts = consumeDataMapper.getNtsCode();
			if(nts!=null) {
				if(nts.equals("")) {
					nts = null;
				}
			}
		} catch (Exception e) {
			nts = null;
		}
		return nts;
	}
	
	/**
	 * 미래 할부 금액을 나누기 위한 로직 
	 * @param consumeDataVO
	 * @return
	 */
	private List<ConsumeDataVO> getInstallmentList(ConsumeDataVO consumeDataVO) {
		List<ConsumeDataVO> returnList = new ArrayList<ConsumeDataVO>();
		
		int amt_in_out = Integer.parseInt(consumeDataVO.getAmt_in_out());
		int mon_installment = Integer.parseInt(consumeDataVO.getMon_installment());
		
		int each_amt_in_out = amt_in_out/mon_installment;
		for(int i = 0; i<mon_installment; i++) {
			ConsumeDataVO tempVO = new ConsumeDataVO(
					consumeDataVO.getNo_person(),
					0,
					consumeDataVO.getType_in_out(),
					consumeDataVO.getMeans_consume(),
					consumeDataVO.getCd_fc(),
					consumeDataVO.getNm_card(),
					consumeDataVO.getNo_card(),
					consumeDataVO.getType_card(),
					DateUtil.addMonths(consumeDataVO.getDt_trd(), i),
					consumeDataVO.getTm_trd(),
					consumeDataVO.getNo_biz(),
					consumeDataVO.getNm_biz(),
					consumeDataVO.getCd_class(),
					consumeDataVO.getCd_type(),
					consumeDataVO.getCd_consume_class(),
					consumeDataVO.getContents(),
					consumeDataVO.getMemo(),
					consumeDataVO.getGrade(),
					each_amt_in_out+"",
					consumeDataVO.getNo_approval(),
					mon_installment+"",
					(i+1)+"",
					consumeDataVO.getYn_pay_installment(),
					consumeDataVO.getYn_delete(),
					consumeDataVO.getYn_cancel(),
					consumeDataVO.getYn_auto(),
					consumeDataVO.getYn_budget_except(),
					consumeDataVO.getYn_person_regist(),
					consumeDataVO.getId_frt(),
					consumeDataVO.getDt_frt(),
					consumeDataVO.getId_lst(),
					consumeDataVO.getDt_lst()
					);
			returnList.add(tempVO);
			logger.debug("hwi---->"+tempVO.toString());
		}
		return returnList;
	}
}
