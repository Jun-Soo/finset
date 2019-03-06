package com.koscom.finance.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.finance.dao.FinanceMapper;
import com.koscom.finance.model.FcWorkPositionForm;
import com.koscom.finance.model.FcWorkPositionVO;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.finance.service.FcWorkPositionManager;
import com.koscom.kisline.model.KisCompanyOutlineVO;
import com.koscom.kisline.service.KislineManager;
import com.koscom.stdcode.model.StdCodeInfo;
import com.koscom.stdcode.service.StdCodeManager;

@Service("fcWorkPositionManager")
public class fcWorkPositionManagerImpl implements FcWorkPositionManager {

	private static final Logger logger = LoggerFactory.getLogger(fcWorkPositionManagerImpl.class);

	@Autowired
	FinanceMapper financeMapper;
	
	@Autowired
	StdCodeManager stdCodeManager;
	
	@Autowired
	KislineManager kislineManager;
	
	/**
	 * 페퍼저축은행의 직업최종코드 및 Company Seg 구하기
     * @param  fcWorkPositionForm
	 * @return FcWorkPositionVO
	 **/
	@Override
	public FcWorkPositionVO getFcWorkPosition(FcWorkPositionForm fcWorkPositionForm) {
		return financeMapper.getFcWorkPosition(fcWorkPositionForm);
	}

	/**
	 * 페퍼저축은행의 직업코드 구하기
     * @param  fcWorkPositionForm
	 * @return FcWorkPositionVO
	 **/
	@Override
	public FcWorkPositionVO getPepperWorkPosition(TxFcTransmitVO txFcTransmitVO) {
		// TODO Auto-generated method stub
		if(txFcTransmitVO == null) return null;
		logger.info("txFcTransmitVO.toString() = " + txFcTransmitVO.toString());
		logger.info("txFcTransmitVO.getCd_duty_comp() = "+txFcTransmitVO.getCd_duty_comp());
		FcWorkPositionVO fcWorkPositionVO = null;
		String sGubun = txFcTransmitVO.getCd_duty_comp().substring(0, 1);
		String cd_job = "";
		String cd_fc = txFcTransmitVO.getCd_fc();
		logger.info("sGubun = "+sGubun);
		//페퍼직업직위코드 구하기
		if( "1".equals(sGubun) ){ //신용 직장인
			cd_job = getPepperWorkerCd(txFcTransmitVO);
		}else if( "2".equals(sGubun) ){ //신용 사업자
			cd_job = getPepperSelfEmployedCd(txFcTransmitVO);
		}
		
		//페퍼 직업최종코드 및 Company Seg 구하기 위한 직업코드
		logger.info("pepper JOB CODE : " + cd_job);
		
		FcWorkPositionForm fcWorkPositionForm = new FcWorkPositionForm();
		fcWorkPositionForm.setCd_fc(cd_fc);
//		fcWorkPositionForm.setCd_job("1A11");
		fcWorkPositionForm.setCd_job(cd_job);
		
		fcWorkPositionVO = getFcWorkPosition(fcWorkPositionForm);
		
		logger.info("pepper Job Position Code : " + fcWorkPositionVO.getCd_work_position());
		logger.info("pepper Company Seg : " + fcWorkPositionVO.getCompany_seg());
		
		return fcWorkPositionVO;
	}
	
	/**
	 * 페퍼 직장인 직업직위코드 조회
	 * @param txFcTransmitVO
	 * @return String
	 */
	private String getPepperWorkerCd( TxFcTransmitVO txFcTransmitVO ){
		
		String sJobCd_l = "1"; //대분류
		String sJobCd_m = null;
		String sJobCd_s = null;
		String sJobCd_desc = null;
		
		String sGubun     = txFcTransmitVO.getCd_duty_comp().substring(1, 2); // 대분류
		String sMGubun    = txFcTransmitVO.getCd_duty_comp().substring(2, 3); // 중분류
		String sSGubun    = txFcTransmitVO.getCd_duty_comp().substring(3, 4); // 소분류
		String sDescGubun = txFcTransmitVO.getCd_duty_comp().substring(4, txFcTransmitVO.getCd_duty_comp().length()); // 직업직위코드 대상자 코드
		logger.info("sGubun = "+sGubun);
		logger.info("sMGubun = "+sMGubun);
		logger.info("sSGubun = "+sSGubun);
		logger.info("sDescGubun = "+sDescGubun);
		StdCodeInfo stdCodeInfo = new StdCodeInfo();
		stdCodeInfo.setCode_group("cd_employee_type");
		stdCodeInfo.setNm_code("계약직");
		logger.info("stdCodeInfo = "+stdCodeInfo.toString());
		StdCodeInfo result = stdCodeManager.getStdCodeInfo(stdCodeInfo);
		logger.info("a");
		String sContractGubun = null;
		if( result != null ){
			sContractGubun = result.getCode_value(); //계약직 구분코드
		}
		logger.info("b");
		
		stdCodeInfo.setCode_group("cd_employee_type");
		stdCodeInfo.setNm_code("파견직");
		result = stdCodeManager.getStdCodeInfo(stdCodeInfo);
		logger.info("c");
		
		String sDispatchGubun = null;
		if( result != null ){
			sDispatchGubun = result.getCode_value(); //파견직 구분코드
		}
		
		stdCodeInfo.setCode_group("cd_employee_type");
		stdCodeInfo.setNm_code("기타");
		result = stdCodeManager.getStdCodeInfo(stdCodeInfo);
		
		String sEtcGubun = null;
		if( result != null ){
			sEtcGubun = result.getCode_value(); //기타 구분코드
		}
		logger.info("sContractGubun : "+sContractGubun);
		logger.info("sDispatchGubun : "+sDispatchGubun);
		logger.info("sEtcGubun : "+sEtcGubun);
		String sAmnisuyn = null; //관리종목여부
		String sIdscd = null; // 산업코드
		KisCompanyOutlineVO kisCompanyOutlineVO = kislineManager.getKisCompanyOutline(txFcTransmitVO.getNo_bunch());
		if(kisCompanyOutlineVO != null ){
			sAmnisuyn = kisCompanyOutlineVO.getAmnisuyn();
			sIdscd    = kisCompanyOutlineVO.getIdscd();
		}
		
		
		if( "1".equals(sGubun) ){ //직장인 - 일반기업
			logger.info("txFcTransmitVO.getJb_tp_comppriv() : "+txFcTransmitVO.getJb_tp_comppriv());
			if( "2".equals(txFcTransmitVO.getJb_tp_comppriv()) ){ //일반기업
				sJobCd_m = "1";
				
				if( "1".equals(txFcTransmitVO.getJb_tp_compchulja()) ){ // (기업구분)출자제한집당단(그룹사)여부→1 (4대계열)
					sJobCd_s = "1";
				}else if( "1".equals(txFcTransmitVO.getJb_tp_listing()) ){//(기업구분)상장여부→1 (상장회사)
					sJobCd_s = "2";
				}else if( "Y".equals(sAmnisuyn) ){//(기업구분) 관리종목여부 → Y (관리기업)
					sJobCd_s = "3";
				}else if( "2".equals(txFcTransmitVO.getJb_tp_listing()) ){//(기업구분)상장여부→2 (코스닥기업)
					sJobCd_s = "4";
				}else if( "1".equals(txFcTransmitVO.getJb_tp_etlipc()) ){//(기업구분)외부감사여부→1 (등록/외감법인)
					sJobCd_s = "5";
				}else if( "2".equals(txFcTransmitVO.getJb_tp_compsize()) ){//기업규모→4 (기타법인)
					sJobCd_s = "6";
				}
				
				if( sContractGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //계약직
					sJobCd_desc = "8";
				}else if( sDispatchGubun.equals(txFcTransmitVO.getCd_employee_type()) || sEtcGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //파견직, 기타
					sJobCd_desc = "9";
				}else if( "7".equals(sDescGubun) || "8".equals(sDescGubun) || "9".equals(sDescGubun) ){
					sJobCd_desc = "7";
				}
				else{
					sJobCd_desc = sDescGubun;
				}
				
			}else if( "1".equals(txFcTransmitVO.getJb_tp_comppriv()) ){ //개인기업
				sJobCd_m = "2";
				String sIdscdGubun = sIdscd.substring(0, 2); //산업코드 앞 2자리
				String sDescIdscdGubun = sIdscd.substring(0, 3); //산업코드 앞 3자리
				if( "01".equals(sIdscdGubun) || "02".equals(sIdscdGubun) || "03".equals(sIdscdGubun) ){ // idscd→01*~03*
					sJobCd_s = "1";
					sJobCd_desc = "1";
					
				}else if( "10".equals(sIdscdGubun) || "11".equals(sIdscdGubun) || "12".equals(sIdscdGubun) || "13".equals(sIdscdGubun)
					   || "14".equals(sIdscdGubun) || "15".equals(sIdscdGubun) || "16".equals(sIdscdGubun) || "17".equals(sIdscdGubun)
					   || "18".equals(sIdscdGubun) || "19".equals(sIdscdGubun) || "20".equals(sIdscdGubun) || "21".equals(sIdscdGubun)
					   || "22".equals(sIdscdGubun) || "23".equals(sIdscdGubun) || "24".equals(sIdscdGubun) || "25".equals(sIdscdGubun)
					   || "26".equals(sIdscdGubun) || "27".equals(sIdscdGubun) || "28".equals(sIdscdGubun) || "29".equals(sIdscdGubun)
					   || "30".equals(sIdscdGubun) || "31".equals(sIdscdGubun) || "32".equals(sIdscdGubun) || "33".equals(sIdscdGubun)
					   || "34".equals(sIdscdGubun) ){ // idscd(산업코드) → 10~34
					sJobCd_s = "2";
					if( "10".equals(sIdscdGubun) || "11".equals(sIdscdGubun) ){
						sJobCd_desc = "1";
					}else if( "13".equals(sIdscdGubun) || "14".equals(sIdscdGubun) || "15".equals(sIdscdGubun) ){
						sJobCd_desc = "2";
					}else if( "16".equals(sIdscdGubun) || "17".equals(sIdscdGubun) || "18".equals(sIdscdGubun) ){
						sJobCd_desc = "3";
					}else if( "22".equals(sIdscdGubun) || "23".equals(sIdscdGubun) ){
						sJobCd_desc = "4";
					}else if( "26".equals(sIdscdGubun) ){
						sJobCd_desc = "7";
					}else if( "30".equals(sIdscdGubun) || "31".equals(sIdscdGubun) ){
						sJobCd_desc = "8";
					}else if( "32".equals(sIdscdGubun) ){
						sJobCd_desc = "9";
					}else{
						sJobCd_desc = "6";
					}
					
				}else if( "35".equals(sIdscdGubun) || "36".equals(sIdscdGubun) || "37".equals(sIdscdGubun) || "38".equals(sIdscdGubun)
					   || "39".equals(sIdscdGubun) ){ // idscd(산업코드) → 35~39
					sJobCd_s = "3";
					sJobCd_desc = "1";
					
				}else if( "41".equals(sIdscdGubun) || "42".equals(sIdscdGubun) ){ // idscd(산업코드) → 41~42
					sJobCd_s = "4";
					sJobCd_desc = "1";
					
				}else if( "45".equals(sIdscdGubun) || "46".equals(sIdscdGubun) || "47".equals(sIdscdGubun) ){ // idscd(산업코드) → 45~47
					sJobCd_s = "5";
					if( "45".equals(sIdscdGubun) ){
						sJobCd_desc = "1";
					}else if( "46".equals(sIdscdGubun) ){
						sJobCd_desc = "2";
					}else if( "471".equals(sDescIdscdGubun) ){
						sJobCd_desc = "3";
					}else if( "472".equals(sDescIdscdGubun) ){
						sJobCd_desc = "4";
					}else if( "474".equals(sDescIdscdGubun) ){
						sJobCd_desc = "6";
					}else{
						sJobCd_desc = "9";
					}
					
				}else if( "55".equals(sIdscdGubun) || "56".equals(sIdscdGubun) ){ // idscd(산업코드) → 55~56
					sJobCd_s = "6";
					if( "55".equals(sIdscdGubun) ){
						sJobCd_desc = "1";
					}else if( "561".equals(sDescIdscdGubun) ){
						sJobCd_desc = "2";
					}else if( "562".equals(sDescIdscdGubun) ){
						sJobCd_desc = "3";
					}else{
						sJobCd_desc = "4";
					}
					
				}else if( "49".equals(sIdscdGubun) || "50".equals(sIdscdGubun) || "51".equals(sIdscdGubun) || "52".equals(sIdscdGubun) ){ // idscd(산업코드) → 49~52
					sJobCd_s = "7";
					if( "49231".equals(sIdscd) ){
						sJobCd_desc = "2";
					}else if( "52".equals(sIdscdGubun) ){
						sJobCd_desc = "3";
					}else{
						sJobCd_desc = "1";
					}
					
				}else if( "64".equals(sIdscdGubun) || "65".equals(sIdscdGubun) || "66".equals(sIdscdGubun) ){ // idscd(산업코드) → 64~66
					sJobCd_s = "8";
					sJobCd_desc = "1";
				}
			}
			logger.info("sJobCd_s111111111111111111111111111 : "+sJobCd_s);
			logger.info("sJobCd_desc111111111111111111111111111 : "+sJobCd_desc);
		}else if( "2".equals(sGubun) ){ //직장인 - 공무원
			sJobCd_m = "3";
			if( "1".equals(sMGubun) || "8".equals(sMGubun) ){ //(직업직위)중앙정부/지자체공무원→1,교육→8
				sJobCd_s = "1";
				
				if( "1".equals(sDescGubun) || "2".equals(sDescGubun) || "3".equals(sDescGubun) ){
					sJobCd_desc = "1";
				}else if( "4".equals(sDescGubun) ){
					sJobCd_desc = "2";
				}else if( "5".equals(sDescGubun) ){
					sJobCd_desc = "3";
				}else if( "6".equals(sDescGubun) ){
					sJobCd_desc = "4";
				}else if( "7".equals(sDescGubun) ){
					sJobCd_desc = "5";
				}else if( "8".equals(sDescGubun) ){
					sJobCd_desc = "6";
				}else if( "9".equals(sDescGubun) ){
					sJobCd_desc = "7";
				}else if( "A".equals(sDescGubun) ){
					sJobCd_desc = "8";
				}else if( sContractGubun.equals(txFcTransmitVO.getCd_employee_type()) || sDispatchGubun.equals(txFcTransmitVO.getCd_employee_type()) || sEtcGubun.equals(txFcTransmitVO.getCd_employee_type()) ){
					sJobCd_desc = "9";
				}
			}else if( "9".equals(sMGubun) ){// (직업직위)별정직→9
				sJobCd_s = "2";
				
				if( "1".equals(sDescGubun) || "2".equals(sDescGubun) || "3".equals(sDescGubun) ){
					sJobCd_desc = "1";
				}else if( "4".equals(sDescGubun) ){
					sJobCd_desc = "2";
				}else if( "5".equals(sDescGubun) ){
					sJobCd_desc = "3";
				}else if( "6".equals(sDescGubun) ){
					sJobCd_desc = "4";
				}else if( "7".equals(sDescGubun) ){
					sJobCd_desc = "5";
				}else if( "8".equals(sDescGubun) ){
					sJobCd_desc = "6";
				}else if( "9".equals(sDescGubun) ){
					sJobCd_desc = "7";
				}else if( "A".equals(sDescGubun) ){
					sJobCd_desc = "8";
				}else if( sContractGubun.equals(txFcTransmitVO.getCd_employee_type()) || sDispatchGubun.equals(txFcTransmitVO.getCd_employee_type()) || sEtcGubun.equals(txFcTransmitVO.getCd_employee_type()) ){
					sJobCd_desc = "9";
				}
			}else if( "2".equals(sMGubun) ){// (직업직위)기능직→2
				sJobCd_s = "3";
				
				if( "1".equals(sDescGubun) || "2".equals(sDescGubun) ){
					sJobCd_desc = "1";
				}else if( "3".equals(sDescGubun) || "4".equals(sDescGubun) || "5".equals(sDescGubun) ){
					sJobCd_desc = "2";
				}else if( "6".equals(sDescGubun) ){
					sJobCd_desc = "3";
				}else if( "7".equals(sDescGubun) ){
					sJobCd_desc = "4";
				}else if( "8".equals(sDescGubun) ){
					sJobCd_desc = "5";
				}else if( "9".equals(sDescGubun) ){
					sJobCd_desc = "6";
				}else if( "A".equals(sDescGubun) ){
					sJobCd_desc = "7";
				}
			}else if( "4".equals(sMGubun) ){// (직업직위)경찰→4
				sJobCd_s = "4";
				
				if( "1".equals(sDescGubun) || "2".equals(sDescGubun) ){
					sJobCd_desc = "1";
				}else if( "3".equals(sDescGubun) ){
					sJobCd_desc = "2";
				}else if( "4".equals(sDescGubun) ){
					sJobCd_desc = "3";
				}else if( "5".equals(sDescGubun) || "6".equals(sDescGubun) ){
					sJobCd_desc = "4";
				}else if( "7".equals(sDescGubun) ){
					sJobCd_desc = "5";
				}else if( "8".equals(sDescGubun) ){
					sJobCd_desc = "6";
				}else if( "9".equals(sDescGubun) ){
					sJobCd_desc = "7";
				}
			}else if( "5".equals(sMGubun) ){// (직업직위)소방→5
				sJobCd_s = "4";
				
				if( "1".equals(sDescGubun) || "2".equals(sDescGubun) ){
					sJobCd_desc = "1";
				}else if( "3".equals(sDescGubun) ){
					sJobCd_desc = "2";
				}else if( "4".equals(sDescGubun) ){
					sJobCd_desc = "3";
				}else if( "5".equals(sDescGubun) || "6".equals(sDescGubun) ){
					sJobCd_desc = "4";
				}else if( "7".equals(sDescGubun) ){
					sJobCd_desc = "5";
				}else if( "8".equals(sDescGubun) ){
					sJobCd_desc = "6";
				}else if( "9".equals(sDescGubun) ){
					sJobCd_desc = "7";
				}
			}else if( "6".equals(sMGubun) ){// (직업직위)공무원+군인→26
				sJobCd_m = "4";
				
				if( "1".equals(sDescGubun) ){
					sJobCd_s = "1";
					sJobCd_desc = "1";
				}else if( "2".equals(sDescGubun) ){
					sJobCd_s = "2";
					sJobCd_desc = "1";
				}else if( "6".equals(sDescGubun) ){
					sJobCd_desc = "4";
					sJobCd_desc = "1";
				}else if( "3".equals(sDescGubun) ){
					sJobCd_desc = "5";
					sJobCd_desc = "1";
				}else if( "4".equals(sDescGubun) ){
					sJobCd_desc = "6";
					sJobCd_desc = "1";
				}else if( "5".equals(sDescGubun) || "9".equals(sDescGubun) ){
					sJobCd_desc = "7";
					sJobCd_desc = "1";
				}else if( "A".equals(sDescGubun) ){
					sJobCd_desc = "8";
					sJobCd_desc = "1";
				}
			}else if( "A".equals(sMGubun) ){// (직업직위)공무원+정무의원직→2A
				sJobCd_m = "5";
				sJobCd_s = sDescGubun;
				sJobCd_desc = "1";
			}else if( "3".equals(sMGubun) ){// (직업직위)공무원+법조/법무→23
				sJobCd_m = "1";
				sJobCd_s = "1";
				sJobCd_desc = "1";
			}
			
		}else if( "3".equals(sGubun) ){ //직장인 - 전문직
			sJobCd_m = "C";
			if( "7".equals(sMGubun) && "1".equals(sDescGubun) ){// (직업직위)중분류→7(법무/법조) + 대상자→1(변호사)
				sJobCd_s = "1";
				sJobCd_desc = "4";
			}else if( "3".equals(sMGubun) && "1".equals(sDescGubun) ){// (직업직위)중분류→3(세무/회계) + 대상자→1(공인회계사)
				sJobCd_s = "2";
				sJobCd_desc = "4";
			}else if( "4".equals(sMGubun) && "1".equals(sDescGubun) ){// (직업직위)중분류→4(기술) + 대상자→1(기술사)
				sJobCd_s = "3";
				sJobCd_desc = "3";
			}else if( "1".equals(sMGubun) && "3".equals(sDescGubun) ){// (직업직위)중분류→1(법조/법무) + 대상자→3(변리사)
				sJobCd_s = "4";
				sJobCd_desc = "3";
			}else if( "3".equals(sMGubun) && "2".equals(sDescGubun) ){// (직업직위)중분류→3(세무/회계) + 대상자→2(세무사)
				sJobCd_s = "5";
				sJobCd_desc = "3";
			}else if( "3".equals(sMGubun) && "3".equals(sDescGubun) ){// (직업직위)중분류→3(세무/회계) + 대상자→3(관세사)
				sJobCd_s = "6";
				sJobCd_desc = "3";
			}else if( "4".equals(sMGubun) && "2".equals(sDescGubun) ){// (직업직위)중분류→4(기술) + 대상자→2(건축사)
				sJobCd_s = "7";
				sJobCd_desc = "3";
			}else if( "5".equals(sMGubun) && "1".equals(sDescGubun) ){// (직업직위)중분류→5(부동산) + 대상자→1(감정평가사)
				sJobCd_s = "8";
				sJobCd_desc = "3";
			}else if( "1".equals(sMGubun) && "2".equals(sDescGubun) ){// (직업직위)중분류→1(법조/법무) + 대상자→2(법무사)
				sJobCd_s = "9";
				sJobCd_desc = "3";
			}
			
		}else if( "4".equals(sGubun) ){ //직장인 - 공공기관
			sJobCd_m = "7";
			if( "1".equals(sMGubun) || "3".equals(sMGubun) ){// (직업직위)정부투자/산하기관→1,3
				sJobCd_s = "1";
				
				if( sContractGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //계약직
					sJobCd_desc = "8";
				}else if( sDispatchGubun.equals(txFcTransmitVO.getCd_employee_type()) || sEtcGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //파견직, 기타
					sJobCd_desc = "9";
				}else if( "7".equals(sDescGubun) || "8".equals(sDescGubun) || "9".equals(sDescGubun) ){
					sJobCd_desc = "7";
				}
				else{
					sJobCd_desc = sDescGubun;
				}
			}else if( "4".equals(sMGubun) ){// (직업직위)정부투자/산하기관→1,3
				sJobCd_s = "5";
				
				if( sContractGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //계약직
					sJobCd_desc = "8";
				}else if( sDispatchGubun.equals(txFcTransmitVO.getCd_employee_type()) || sEtcGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //파견직, 기타
					sJobCd_desc = "9";
				}else if( "7".equals(sDescGubun) || "8".equals(sDescGubun) || "9".equals(sDescGubun) ){
					sJobCd_desc = "7";
				}
				else{
					sJobCd_desc = sDescGubun;
				}
			}
			
		}else if( "5".equals(sGubun) ){ //직장인 - 교육기관
			sJobCd_m = "8";
			if( "2".equals(sSGubun) ){ //특수
				if( "1".equals(sMGubun) ){// (직업직위)대학교→1
					sJobCd_s = "1";
					sJobCd_desc = sDescGubun;
				}else if( "2".equals(sMGubun) ){// (직업직위)전문대학→2
					sJobCd_s = "2";
					sJobCd_desc = sDescGubun;
				}else if( "3".equals(sMGubun) ){// (직업직위)초/중/고교→3
					sJobCd_s = "3";
					if( "2".equals(sSGubun) ){ //계약직
						sJobCd_desc = sDescGubun;
						if( "4".equals(sDescGubun) || "5".equals(sDescGubun)){
							sJobCd_desc = "7";
						}
					}else{
						sJobCd_desc = "5";
					}
				}else if( "4".equals(sMGubun) || "5".equals(sMGubun) ){// (직업직위)기타학교,유치원/유아원/보육원→4,5
					sJobCd_s = "4";
					
					if( "2".equals(sSGubun) ){ //계약직
						sJobCd_desc = sDescGubun;
						if( "4".equals(sDescGubun) || "5".equals(sDescGubun)){
							sJobCd_desc = "7";
						}
					}else{
						sJobCd_desc = "5";
					}
				}else if( "6".equals(sMGubun) ){// (직업직위)일반학원→6
					sJobCd_s = "5";
					
					if( "2".equals(sSGubun) ){ //계약직
						sJobCd_desc = sDescGubun;
						if( "4".equals(sDescGubun) || "5".equals(sDescGubun)){
							sJobCd_desc = "7";
						}
					}else{
						sJobCd_desc = "5";
					}
				}
			}else if( "1".equals(sSGubun) ){ //일반
				sJobCd_s = "6";
				if( sContractGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //계약직
					sJobCd_desc = "8";
				}else if( sDispatchGubun.equals(txFcTransmitVO.getCd_employee_type()) || sEtcGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //파견직, 기타
					sJobCd_desc = "9";
				}else{
					sJobCd_desc = sDescGubun;
				}
			}
			
			
		}else if( "6".equals(sGubun) ){ //직장인 - 의료기관
			sJobCd_m = "9";
			if( "2".equals(sSGubun) ){ //특수
				if( "1".equals(sMGubun) ){// (직업직위)중분류→1(종합/대학병원)
					sJobCd_s = "1";
					sJobCd_desc = sDescGubun;
				}else if( "2".equals(sMGubun) ){// (직업직위)중분류→2(일반병원)
					sJobCd_s = "3";
					sJobCd_desc = sDescGubun;
				}else if( "3".equals(sMGubun) ){// (직업직위)중분류→3(개인/요양병원)
					sJobCd_s = "3";
					sJobCd_desc = "2";
				}
			}else if( "1".equals(sSGubun) ){ //일반
				sJobCd_s = "8";
				if( sContractGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //계약직
					sJobCd_desc = "8";
				}else if( sDispatchGubun.equals(txFcTransmitVO.getCd_employee_type()) || sEtcGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //파견직, 기타
					sJobCd_desc = "9";
				}else{
					sJobCd_desc = sDescGubun;
				}
			}else if( "3".equals(sSGubun) ){ //(직업직위)소분류→3(간호사)
				sJobCd_s = "5";
				sJobCd_desc = "5";
			}else if( "5".equals(sSGubun) ){ //(직업직위)소분류→5(약사/수의사)
				sJobCd_s = "6";
				sJobCd_desc = "3";
			}else if( "4".equals(sSGubun) ){ //(직업직위)소분류→4(기사)
				sJobCd_s = "7";
				sJobCd_desc = "5";
			}
			
		}else if( "8".equals(sGubun) ){ //(직업직위)언론기관→8
			sJobCd_m = "A";
			
			//소분류 코드 입력
			if( "1".equals(sMGubun) ){// (직업직위)중분류→1(신문사)
				sJobCd_s = "1";
			}else if( "2".equals(sMGubun) ){// (직업직위)중분류→2(잡지사)
				sJobCd_s = "2";
			}else if( "3".equals(sMGubun) ){// (직업직위)중분류→3(잡지사)
				sJobCd_s = "3";
			}
			
			//상세코드 입력
			if( sContractGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //계약직
				sJobCd_desc = "8";
			}else if( sDispatchGubun.equals(txFcTransmitVO.getCd_employee_type()) || sEtcGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //파견직, 기타
				sJobCd_desc = "9";
			}else{
				sJobCd_desc = sDescGubun;
			}
		}else if( "7".equals(sGubun) ){ //(직업직위)금융기관→7
			sJobCd_m = "B";
			//소분류 코드 입력
			if( "1".equals(sMGubun) ){// (직업직위)중분류→1(은행 및 중앙회)
				sJobCd_s = "1";
			}else if( "4".equals(sMGubun) ){// (직업직위)중분류→4(보험사)
				sJobCd_s = "2";
			}else if( "2".equals(sMGubun) ){// (직업직위)중분류→2(증권,투신사)
				sJobCd_s = "3";
			}else if( "3".equals(sMGubun) ){// (직업직위)중분류→3(투자금융사)
				sJobCd_s = "4";
			}else if( "6".equals(sMGubun) ){// (직업직위)중분류→6(새마을금고 등..)
				sJobCd_s = "5";
			}else if( "7".equals(sMGubun) ){// (직업직위)중분류→7(기타금융기관)
				sJobCd_s = "6";
			}
			
			//상세코드 입력
			if( sContractGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //계약직
				sJobCd_desc = "8";
			}else if( sDispatchGubun.equals(txFcTransmitVO.getCd_employee_type()) || sEtcGubun.equals(txFcTransmitVO.getCd_employee_type()) ){ //파견직, 기타
				sJobCd_desc = "9";
			}else{
				sJobCd_desc = sDescGubun;
			}
		}
		logger.info("sJobCd_l : "+sJobCd_l);
		logger.info("sJobCd_m : "+sJobCd_m);
		logger.info("sJobCd_s : "+sJobCd_s);
		logger.info("sJobCd_desc : "+sJobCd_desc);
		String jobCd = sJobCd_l + sJobCd_m + sJobCd_s + sJobCd_desc;
		if(jobCd.contains("null")){
			jobCd = "1111";
		}
		
		return jobCd;
	}
	
	/**
	 * 페퍼 사업자 직업직위코드 조회
	 * @param txFcTransmitVO
	 * @return String
	 */
	private String getPepperSelfEmployedCd( TxFcTransmitVO txFcTransmitVO ){
		
		String sJobCd_l = "2"; //대분류
		String sJobCd_m = null;
		String sJobCd_s = null;
		String sJobCd_desc = null;
		
		String sGubun     = txFcTransmitVO.getCd_duty_comp().substring(1, 2); // 대분류
		String sMGubun    = txFcTransmitVO.getCd_duty_comp().substring(2, 3); // 중분류
		String sDescGubun = txFcTransmitVO.getCd_duty_comp().substring(4, txFcTransmitVO.getCd_duty_comp().length()); // 직업직위코드 대상자 코드
		
		String sIdscd = null; // 산업코드
		KisCompanyOutlineVO kisCompanyOutlineVO = kislineManager.getKisCompanyOutline(txFcTransmitVO.getNo_bunch());
		if(kisCompanyOutlineVO != null ){
			sIdscd    = kisCompanyOutlineVO.getIdscd();
		}
		//sJobCd_m = "2";
		String sIdscdGubun = null; //산업코드 앞 2자리
		String sDescIdscdGubun = null; //산업코드 앞 3자리
		if (sIdscd != null) {
			sIdscdGubun = sIdscd.substring(0, 2);
			sDescIdscdGubun = sIdscd.substring(0, 3);
		}

		if( "3".equals(sGubun) ){ //직장인 - 전문직
			sJobCd_m = "C";
			if( "7".equals(sMGubun) && "1".equals(sDescGubun) ){// (직업직위)중분류→7(법무/법조) + 대상자→1(변호사)
				sJobCd_s = "1";
				sJobCd_desc = "4";
			}else if( "3".equals(sMGubun) && "1".equals(sDescGubun) ){// (직업직위)중분류→3(세무/회계) + 대상자→1(공인회계사)
				sJobCd_s = "2";
				sJobCd_desc = "4";
			}else if( "4".equals(sMGubun) && "1".equals(sDescGubun) ){// (직업직위)중분류→4(기술) + 대상자→1(기술사)
				sJobCd_s = "3";
				sJobCd_desc = "3";
			}else if( "1".equals(sMGubun) && "3".equals(sDescGubun) ){// (직업직위)중분류→1(법조/법무) + 대상자→3(변리사)
				sJobCd_s = "4";
				sJobCd_desc = "3";
			}else if( "3".equals(sMGubun) && "2".equals(sDescGubun) ){// (직업직위)중분류→3(세무/회계) + 대상자→2(세무사)
				sJobCd_s = "5";
				sJobCd_desc = "3";
			}else if( "3".equals(sMGubun) && "3".equals(sDescGubun) ){// (직업직위)중분류→3(세무/회계) + 대상자→3(관세사)
				sJobCd_s = "6";
				sJobCd_desc = "3";
			}else if( "4".equals(sMGubun) && "2".equals(sDescGubun) ){// (직업직위)중분류→4(기술) + 대상자→2(건축사)
				sJobCd_s = "7";
				sJobCd_desc = "3";
			}else if( "5".equals(sMGubun) && "1".equals(sDescGubun) ){// (직업직위)중분류→5(부동산) + 대상자→1(감정평가사)
				sJobCd_s = "8";
				sJobCd_desc = "3";
			}else if( "1".equals(sMGubun) && "2".equals(sDescGubun) ){// (직업직위)중분류→1(법조/법무) + 대상자→2(법무사)
				sJobCd_s = "9";
				sJobCd_desc = "3";
			}
		}
		else{ 
		
		if( "01".equals(sIdscdGubun) || "02".equals(sIdscdGubun) || "03".equals(sIdscdGubun) ){ // idscd→01*~03*
			sJobCd_m = "1";
			sJobCd_s = "1";
			sJobCd_desc = "1";
			
		}else if( "10".equals(sIdscdGubun) || "11".equals(sIdscdGubun) || "12".equals(sIdscdGubun) || "13".equals(sIdscdGubun)
			   || "14".equals(sIdscdGubun) || "15".equals(sIdscdGubun) || "16".equals(sIdscdGubun) || "17".equals(sIdscdGubun)
			   || "18".equals(sIdscdGubun) || "19".equals(sIdscdGubun) || "20".equals(sIdscdGubun) || "21".equals(sIdscdGubun)
			   || "22".equals(sIdscdGubun) || "23".equals(sIdscdGubun) || "24".equals(sIdscdGubun) || "25".equals(sIdscdGubun)
			   || "26".equals(sIdscdGubun) || "27".equals(sIdscdGubun) || "28".equals(sIdscdGubun) || "29".equals(sIdscdGubun)
			   || "30".equals(sIdscdGubun) || "31".equals(sIdscdGubun) || "32".equals(sIdscdGubun) || "33".equals(sIdscdGubun)
			   || "34".equals(sIdscdGubun) ){ // idscd(산업코드) → 10~34
			sJobCd_m = "2";
			
			if( "10".equals(sIdscdGubun) || "11".equals(sIdscdGubun) ){
				sJobCd_s = "1";
				sJobCd_desc = "1";
			}else if( "13".equals(sIdscdGubun) || "14".equals(sIdscdGubun) || "15".equals(sIdscdGubun) ){
				sJobCd_s = "2";
				sJobCd_desc = "1";
			}else if( "16".equals(sIdscdGubun) || "17".equals(sIdscdGubun) || "18".equals(sIdscdGubun) ){
				sJobCd_s = "3";
				sJobCd_desc = "1";
			}else if( "22".equals(sIdscdGubun) || "23".equals(sIdscdGubun) ){
				sJobCd_s = "4";
				sJobCd_desc = "1";
			}else if( "26".equals(sIdscdGubun) ){
				sJobCd_s = "7";
				sJobCd_desc = "1";
			}else if( "30".equals(sIdscdGubun) || "31".equals(sIdscdGubun) ){
				sJobCd_s = "8";
				sJobCd_desc = "1";
			}else if( "32".equals(sIdscdGubun) ){
				sJobCd_s = "9";
				sJobCd_desc = "1";
			}/*else{
				sJobCd_desc = "6";
			}*/
			
		}/*else if( "35".equals(sIdscdGubun) || "36".equals(sIdscdGubun) || "37".equals(sIdscdGubun) || "38".equals(sIdscdGubun)
			   || "39".equals(sIdscdGubun) ){ // idscd(산업코드) → 35~39
			sJobCd_s = "3";
			sJobCd_desc = "1";
			
		}else if( "41".equals(sIdscdGubun) || "42".equals(sIdscdGubun) ){ // idscd(산업코드) → 41~42
			sJobCd_s = "4";
			sJobCd_desc = "1";
			
		}*/else if( "45".equals(sIdscdGubun) || "46".equals(sIdscdGubun) || "47".equals(sIdscdGubun) ){ // idscd(산업코드) → 45~47
			sJobCd_m = "5";
			if( "45".equals(sIdscdGubun) ){
				sJobCd_s = "1";
				sJobCd_desc = "1";
			}else if( "46".equals(sIdscdGubun) ){
				sJobCd_s = "2";
				sJobCd_desc = "1";
			}else if( "471".equals(sDescIdscdGubun) ){
				sJobCd_s = "3";
				sJobCd_desc = "1";
			}else if( "472".equals(sDescIdscdGubun) ){
				sJobCd_s = "4";
				sJobCd_desc = "1";
			}else if( "474".equals(sDescIdscdGubun) ){
				sJobCd_s = "6";
				sJobCd_desc = "1";
			}else if( "478".equals(sDescIdscdGubun) ){
				sJobCd_s = "9";
				sJobCd_desc = "1";
			}/*else{
				sJobCd_s = "9";
				sJobCd_desc = "1";
			}*/
			
		}else if( "55".equals(sIdscdGubun) || "56".equals(sIdscdGubun) ){ // idscd(산업코드) → 55~56
			sJobCd_m = "6";
			if( "55".equals(sIdscdGubun) ){
				sJobCd_s = "1";
				sJobCd_desc = "1";
			}else if( "561".equals(sDescIdscdGubun) ){
				sJobCd_s = "2";
				sJobCd_desc = "1";
			}else if( "562".equals(sDescIdscdGubun) ){
				sJobCd_s = "3";
				sJobCd_desc = "1";
			}/*else{
				sJobCd_s = "1";
				sJobCd_desc = "1";
			}*/
			
		}else if( "49".equals(sIdscdGubun) || "50".equals(sIdscdGubun) || "51".equals(sIdscdGubun) || "52".equals(sIdscdGubun) ){ // idscd(산업코드) → 49~52
			sJobCd_m = "7";
			if( "49231".equals(sIdscd) ){
				sJobCd_s = "2";
				sJobCd_desc = "1";
			}else if( "52".equals(sIdscdGubun) ){
				sJobCd_s = "3";
				sJobCd_desc = "1";
			}/*else{
				sJobCd_desc = "1";
			}*/
			
		}/*else if( "64".equals(sIdscdGubun) || "65".equals(sIdscdGubun) || "66".equals(sIdscdGubun) ){ // idscd(산업코드) → 64~66
			sJobCd_s = "8";
			sJobCd_desc = "1";
		}*/
		}
		
		String jobCd = sJobCd_l + sJobCd_m + sJobCd_s + sJobCd_desc;
		return jobCd;
	}
}
