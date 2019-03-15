package com.koscom.kcb.service.impl;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.koscom.credit.dao.CreditMapper;
import com.koscom.credit.model.CreditDtlVO;
import com.koscom.credit.service.CreditManager;
import com.koscom.domain.CreditInfo;
import com.koscom.fincorp.dao.FincorpMapper;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.model.FincorpfcNminfoForm;
import com.koscom.kcb.model.AbstractKcbInfo;
import com.koscom.kcb.model.KcbAddrInfo;
import com.koscom.kcb.model.KcbCardDtlList;
import com.koscom.kcb.model.KcbCardInfo;
import com.koscom.kcb.model.KcbCardListInfo;
import com.koscom.kcb.model.KcbContactInfo;
import com.koscom.kcb.model.KcbCreditInfoVO;
import com.koscom.kcb.model.KcbGuaranteeInfo;
import com.koscom.kcb.model.KcbJobInfo;
import com.koscom.kcb.model.KcbOverdueDefaultInfo;
import com.koscom.kcb.model.KcbOverdueInfo;
import com.koscom.kcb.model.KcbOverduePublicInfo;
import com.koscom.kcb.model.KcbOverdueSteadpayInfo;
import com.koscom.kcb.model.KcbReqNonfiInfoVO;
import com.koscom.kcb.model.Kcb_300;
import com.koscom.kcb.model.Kcb_600;
import com.koscom.kcb.model.seg.Kcb_Segment029;
import com.koscom.kcb.model.seg.Kcb_Segment030;
import com.koscom.kcb.model.seg.Kcb_Segment105;
import com.koscom.kcb.service.KcbManager;
import com.koscom.person.dao.PersonMapper;
import com.koscom.person.model.PersonEtmIncomeInfo;
import com.koscom.util.CodeUtil;
import com.koscom.util.Constant;
import com.koscom.util.DateUtil;
import com.koscom.util.FinsetException;
import com.koscom.util.NumberUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;

@Service("kcbManager")
public class KcbManagerImpl implements KcbManager {

	private static final Logger logger = LoggerFactory.getLogger(KcbManagerImpl.class);

	@Autowired
	private CreditManager creditManager;

	@Autowired
	private CreditMapper creditMapper;

	@Autowired
	private PersonMapper personMapper;
	
	@Override
	public List<CreditInfo> listKcbCredit(CreditInfo info) {
		if(StringUtil.isEmpty(info.getCdCbComp())) info.setCdCbComp("KCB");
		return creditManager.listCreditInfo(info);
	}

	@Override
	public AbstractKcbInfo viewKcbCreditInfo(KcbCreditInfoVO infoVO) throws UnsupportedEncodingException,FinsetException {

		//신용조회 정보 조회
		CreditInfo creditInfo = null;
        AbstractKcbInfo kcbInfo = null;
        String msg_response = null; //응답전문
        String resCode = null;

        creditInfo = creditManager.getCreditInfo(infoVO.getSeq());

        if(infoVO != null && creditInfo != null) {

            msg_response = creditInfo.getMsgResponse();
            if(StringUtil.isEmpty(msg_response)) return null;

            //조회하는 전문타입에 맞는 모델 객체 생성
            kcbInfo = getKcbObjByCbType(infoVO.getNmIf());
            if (kcbInfo != null) {
                kcbInfo.parseHeader(msg_response); // 공통부 파싱
                resCode = kcbInfo.getResCode();
                //응답코드가 정상이 아니면 null리턴
                if(!"0000".equals(resCode))
                    return null;

                //응답전문 전체 파싱
                kcbInfo.parseResponse(msg_response);
            }

            infoVO.setDtFrt(creditInfo.getDtFrt()); //조회일시
            infoVO.setIdFrt(creditInfo.getIdFrt()); //조회자ID
            infoVO.setNmCust(creditInfo.getNmCust()); //조회고객명
            infoVO.setCdCbResponse(creditInfo.getCdCbResponse()); //응답코드
        }
		return kcbInfo;
	}

	/**
	 * Method Desc : 해당 전문타입에 매핑되는 모델 객체 생성하여 리턴
	 * @param nm_if 전문타입
	 * @return
	 */
	private AbstractKcbInfo getKcbObjByCbType(String nm_if) throws FinsetException {
		if(StringUtil.isEmpty(nm_if)) return null;
		AbstractKcbInfo kcbInfo = null;

        try {
            /**
             * 예) nm_if이 '100'인 경우 클래스명 => Kcb_100
             */
            Class<?> c = Class.forName("com.koscom.kcb.model.Kcb_" + nm_if);
            kcbInfo = (AbstractKcbInfo) c.newInstance();
        } catch (ClassNotFoundException e) {
            throw new FinsetException(e);
        } catch (InstantiationException e) {
            throw new FinsetException(e);
        } catch (IllegalAccessException e) {
            throw new FinsetException(e);
        }

		return kcbInfo;
	}

	@Override
	public ReturnClass getKcbCbInfo(KcbCreditInfoVO infoVO) throws UnsupportedEncodingException, FinsetException,IOException {

		//CB정보 가져오기
		HashMap<String, String> schMap = new HashMap<String, String>();
		schMap.put("sch_no_person", infoVO.getNoPerson());
		schMap.put("sch_gubun", "1");	// 1:O일 전, 2:O시간 전
		schMap.put("sch_time", "1");	// O값
		schMap.put("nm_if", infoVO.getNmIf());

		HashMap<String, String> clobMap = creditManager.getKcbInfoCLOB(schMap);
        ReturnClass returnClass = null;

		//DB 조회&리턴
		if(clobMap != null) {

			String recvMsg = clobMap.get("msg_response");

			/** DEBUG
			 *  recvMsg 파일에 저장 : kcb socket을 통해서 받은 문자열(받을 때 euckr 인코딩 적용)
			 *                 		-> DB CLOB에 저장됨 -> myBatis 통해 string 으로 불러옴 -> 파일에 저장
			 **/
			logger.debug("KCB :: DB CLOB -> "+recvMsg);
			//StringUtil.saveSbToFile(recvMsg, DateUtil.getCurrentDate(DateUtil.DATE_HMS_PATTERN)+"_"+"kcb_res_db_data_ "+StringUtil.getRandomString(10)+".txt");

			//조회하는 전문타입에 맞는 모델 객체 생성
			AbstractKcbInfo kcbInfo = null;
            kcbInfo = getKcbObjByCbType(infoVO.getNmIf());
            if(kcbInfo == null) {
                returnClass = new ReturnClass(Constant.FAILED, "확인되지 않은 전문입니다.");
			} else {

				//초기 전문 기본정보 전문객체에 셋팅
				kcbInfo.setInitData(infoVO);

				//전문 파싱 HEADER
				kcbInfo.parseHeader(recvMsg);
				kcbInfo.parseResponse(recvMsg);
                returnClass = new ReturnClass(Constant.SUCCESS, "정상 조회", kcbInfo);
			}

		} else {

			logger.debug("KCB :: GET DATA FROM KCB");
            returnClass = this.procKcbCb(infoVO);	//KCB 접속 및 조회 실행

			if(returnClass != null){
			    if(Constant.SUCCESS.equals(returnClass.getCd_result())) {
                    returnClass = getKcbCbInfo(infoVO);	//재조회하고 세그먼트 자르고 담아서 리턴
                } else {
                    logger.debug("실패"+returnClass.getMessage());
                    returnClass = new ReturnClass(Constant.FAILED, returnClass.getMessage());
                }
			} else {
                logger.debug("실패:returnClass="+returnClass);
                returnClass = new ReturnClass(Constant.FAILED, "ReturnClass is null.");
			}
		}

		return returnClass;
	}

	/**
	 * Method Desc : KCB 전문 요청 및 Parsing
	 * @param pInfoVO
	 * @return
	 */
	public ReturnClass procKcbCb(KcbCreditInfoVO pInfoVO) throws UnsupportedEncodingException, FinsetException, IOException {

		KcbCreditInfoVO infoVO 	= pInfoVO;
		AbstractKcbInfo kcbInfo = null;
		
		try {

			HashMap<String, String> schMap = new HashMap<String, String>();
			schMap.put("sch_no_person", infoVO.getNoPerson());
			schMap.put("sch_gubun", "1");	// 1:O일 전, 2:O시간 전
			schMap.put("sch_time", "1");	// O값
			schMap.put("nm_if", infoVO.getNmIf());
			schMap.put("nm_if_sub", StringUtil.NVL(infoVO.getNmIfSub(), ""));

			HashMap<String, String> clobMap = creditManager.getKcbInfoCLOB(schMap);
			
			//신용리포트 조회 및 데이터 확인용 조회
			if("220".equals(infoVO.getNmIfSub()) || "Y".equals(infoVO.getYn_craw_test())) clobMap = null; 

			//해지는 무조건 전문전송
			if("600".equals(infoVO.getNmIf()) && "05".equals(infoVO.getCd_regist())) clobMap = null;
			if("600420".equals(infoVO.getNmIf()) && "03".equals(infoVO.getCd_regist())) clobMap = null;
			
			if (infoVO != null && clobMap == null) {

				if(StringUtil.isEmpty(infoVO.getNoPerson()) || StringUtil.isEmpty(infoVO.getNmIf())) {
	                return new ReturnClass(Constant.FAILED, "[조회키 요청 실패]개인식별번호 OR 전문구분코드 없습니다.");
	            }

				//2.조회하는 전문타입에 맞는 모델 객체 생성
				kcbInfo = getKcbObjByCbType(infoVO.getNmIf());
				if(kcbInfo == null) {
					return new ReturnClass(Constant.FAILED, "확인되지 않은 전문입니다.");
				}
				this.setDefaultCbInfo(infoVO); //신용조회 필요한 기본정보 셋팅(USERID, IP, PORT등)

				//3. 전문SEQ 조회
				String seq = creditManager.getCreditInfoNextSeq();

				//4. 신용조회 전문 송수신
				infoVO.setKcb_seq(seq);
				kcbInfo.setInitData(infoVO); 			//초기 전문 기본정보 전문객체에 셋
				kcbInfo.transferNiceCb(infoVO); 		//전문 송수신처리

				//URI setting
				if(!StringUtils.isEmpty(infoVO.getKcbURI())) kcbInfo.setKcbURI(infoVO.getKcbURI());

				String resDt = creditManager.selectResDt(infoVO.getNoPerson());
				resDt = StringUtil.NVL(resDt, "19000101"); //최종 수신일 셋팅, NULL일 경우 19000101 셋팅
				logger.info("resDt ================================== **** " + resDt + "****" + kcbInfo.getResCode());

				//5. 전문 정보 저장
				creditManager.saveCredit(getCreditInfoObj(infoVO));
				
				//6. 600615 전문 KCB ID 저장
				if("600".equals(infoVO.getNmIf()) && "0000".equals(kcbInfo.getResCode())) {
					Kcb_600 kcb600 = new Kcb_600();
					kcb600 = (Kcb_600)kcbInfo;
					infoVO.setKcb_id(kcb600.getRes등록번호());
					
					logger.info("================================== **** updatePersonKcbID **** == " + kcb600.getRes등록번호());
					personMapper.updatePersonKcbID(infoVO);
				}
				
				//6. 300전문 상세 저장
				/*
				if("300".equals(infoVO.getNmIf())) {

					Kcb_300 kcb300 = new Kcb_300();
					kcb300 = (Kcb_300)kcbInfo;
					kcb300.setResDt(resDt);
					this.saveDetailInfo(kcb300, infoVO);
				}
				*/

				//응답코드가 정상이 아닌경우 처리부분
				if(!"0000".equals(infoVO.getCdCbResponse())) {
					CodeUtil codeUtil = CodeUtil.getInstance();
					ReturnClass rc = null;
					if(codeUtil !=null) {
						rc = new ReturnClass(Constant.FAILED, codeUtil.getCodeName("cd_kcb_cb_response", infoVO.getCdCbResponse()));;
					}
					return rc;
				}

			} else {
				return new ReturnClass(Constant.FAILED, "[조회키 요청 실패]개인식별번호 OR 전문구분코드 없습니다.");
			}
		} catch (FinsetException e) {
			return new ReturnClass(Constant.FAILED, "전문 송수신 오류", e.getMessage());
		}

		return new ReturnClass(Constant.SUCCESS, "전문 송수신 성공", kcbInfo);
	}

	/**
	 * 신용조회정보 DB등록위해 VO객체를 도메인객체로 옮기는 작업(VO객체로 그냥 저장시 entity객체 알수없다고 에러남)
	 * @param info
	 * @return
	 */
	private CreditInfo getCreditInfoObj(KcbCreditInfoVO info) {
		CreditInfo creditInfo = new CreditInfo();
		creditInfo.setSeq(Integer.parseInt(info.getKcb_seq()));
		creditInfo.setNoPerson(info.getNoPerson());
		creditInfo.setCdAgreeCause(info.getCdAgreeCause()); //조회동의사유
		creditInfo.setCdCbCause(info.getCdCbCause()); //조회사유
		creditInfo.setCdCbComp(info.getCdCbComp()); //신용정보사
		creditInfo.setNmIf(info.getNmIf()); //전문타입
		creditInfo.setNmIfSub(info.getNmIfSub());
		creditInfo.setNmCust(info.getNmCust()); //성명
		creditInfo.setCdCbResponse(info.getCdCbResponse()); //응답코드
		creditInfo.setMsgRequest(info.getMsgRequest()); //요청전문
		creditInfo.setMsgResponse(info.getMsgResponse()); //응답전문
		creditInfo.setNoInqKey(info.getNoInqKey()); //조회키
//		creditInfo.setNoInqKey(StringUtil.encodeSha256(info.getNoInqKey())); //조회키
		creditInfo.setIdFrt(info.getIdFrt());
		creditInfo.setKcbURI(info.getKcbURI());
		return creditInfo;
	}

	/**
	 * 신용조회 필요한 기본정보 셋팅
	 * @param pInfo
	 */
	private boolean setDefaultCbInfo(KcbCreditInfoVO pInfo) {
		KcbCreditInfoVO info = pInfo;
		CodeUtil code = CodeUtil.getInstance();

		String ip      = null;
		String strPort = null;
		int    port    = 0   ;
		String no_user = null;
		boolean isSuccess = true;

            if (code != null) {
                ip      = code.getCodeName("_CONF_KCB", "KCB_SERVER_IP");
                strPort = code.getCodeName("_CONF_KCB", "KCB_SERVER_PORT");
                port    = (strPort != null)? NumberUtil.parseInt(strPort) : 0;
                no_user = code.getCodeName("_CONF_KCB", "KCB_NO_USER");
                if (info != null) {
					ip      = (ip      !=null)? ip : "";
					no_user = (no_user !=null)? ip : "";
                    info.setIp     (ip     ); //NICE 서버 IP
                    info.setPort   (port   ); //NICE 서버 PORT
                    info.setNo_user(no_user); //NICE에서 부여한 사용자ID
                } else {
                    isSuccess = false;
                }
            } else {
                isSuccess = false;
            }

        return isSuccess;
    }

//	/**
//	 * 신용조회를 위해 필요한 금융사정보(대출/개인) 데이터 셋팅
//	 * @param msgJson
//	 * @param form
//	 */
//	private KcbCreditForm setCbFormData(String msgJson , KcbCreditForm form) {
//
//		ObjectMapper om = new ObjectMapper();
//		if(!StringUtil.isEmpty(msgJson))
//		{
//			try {
//				form = om.readValue(msgJson, new TypeReference< KcbCreditForm >(){});
//
//			} catch (JsonParseException e) {
//				e.printStackTrace();
//			} catch (JsonMappingException e) {
//				//e.printStackTrace();
//				logger.error("["+form.getClass() + "] JSON Mapping Error" );
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		logger.info(form+"");
//		return form;
//
//	}


	
}

