package com.koscom.kcb.service.impl;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Pattern;

import javax.annotation.Resource;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.koscom.credit.dao.CreditMapper;
import com.koscom.credit.model.CreditDtlVO;
import com.koscom.debt.dao.DebtMapper;
import com.koscom.debt.model.CrawlingLoanDtlVO;
import com.koscom.debt.model.CrawlingLoanVO;
import com.koscom.debt.service.DebtManager;
import com.koscom.domain.CreditInfo;
import com.koscom.fccode.dao.FcCodeMapper;
import com.koscom.fincorp.dao.FincorpMapper;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.model.FincorpfcNminfoForm;
import com.koscom.kcb.model.AbstractKcbInfo;
import com.koscom.kcb.model.CrawlingVO;
import com.koscom.kcb.model.KcbAddrInfo;
import com.koscom.kcb.model.KcbCardDtlList;
import com.koscom.kcb.model.KcbCardInfo;
import com.koscom.kcb.model.KcbContactInfo;
import com.koscom.kcb.model.KcbCreditInfoVO;
import com.koscom.kcb.model.KcbGuaranteeInfo;
import com.koscom.kcb.model.KcbJobInfo;
import com.koscom.kcb.model.KcbOverdueDefaultInfo;
import com.koscom.kcb.model.KcbOverdueInfo;
import com.koscom.kcb.model.KcbOverduePublicInfo;
import com.koscom.kcb.model.KcbOverdueSteadpayInfo;
import com.koscom.kcb.model.KcbReqNonfiInfoVO;
import com.koscom.kcb.model.Kcb_600420;
import com.koscom.kcb.service.KcbManager;
import com.koscom.person.dao.PersonMapper;
import com.koscom.person.model.PersonEtmIncomeInfo;
import com.koscom.person.model.PersonVO;
import com.koscom.scrap.dao.ScrapMapper;
import com.koscom.util.CodeUtil;
import com.koscom.util.Constant;
import com.koscom.util.DateUtil;
import com.koscom.util.FinsetException;
import com.koscom.util.JsoupUtil;
import com.koscom.util.LogUtil;
import com.koscom.util.NumberUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;

@Service("kcbManager")
public class KcbManagerImpl implements KcbManager {

	private static final Logger logger = LoggerFactory.getLogger(KcbManagerImpl.class);

	@Resource
	public Environment environment;
	
	@Autowired
	private DebtManager debtManager;
	
	@Autowired
	private DebtMapper debtMapper;
	
	@Autowired
	private CreditMapper creditMapper;
	
	@Autowired
	private FcCodeMapper fcCodeMapper;
	
	@Autowired
	private FincorpMapper fincorpMapper;
	
	@Autowired
	private ScrapMapper scrapMapper;
	
	@Autowired
	private PersonMapper personMapper;
	
	@Value("${service.profile}")
    private String profile;

	@Override
	public String getKcbCrawling(PersonVO person) throws FinsetException, IOException {
		
		//String profile  = environment.getProperty("service.profile");
		String cd_result = Constant.SUCCESS;
		
		logger.debug(":::::::::::::::::::::::::::::::::::: KCB START ::::::::::::::::::::::::::::::::::::");
		KcbCreditInfoVO 	info 	= new KcbCreditInfoVO();

        boolean isSuccess = false;

        if(!"REAL".equals(profile)) {
			logger.error("continue");
//			info.setYn_craw_test("Y");
//			info.setNoPerson(person.getNo_person());
//			info.setNmCust(person.getNm_person());
//			info.setNmIf("600420");
//			info.setCd_regist("09");	//01 신규, 09 URL
//			info.setBgn(person.getBgn());
//			info.setDi(person.getKcb_di());
//			info.setHp(person.getHp());
//			ReturnClass returnClass = urlCrawling(info);
//			info.setYn_craw_test("Y");
//			returnClass = parseCrawling(info);
//			debtManager.debtPdocRun(person.getNo_person());
//			cd_result = returnClass.getCd_result();
		} else {
			
			try {

				//600420 크롤링 시작
				logger.debug(":::::::::::::::::::::::::::::::::::: KCB CRAW START ::::::::::::::::::::::::::::::::::::");
				info.setNoPerson(person.getNo_person());
				info.setNmCust(person.getNm_person());
				info.setNmIf("600420");
				info.setCd_regist("09");	//01 신규, 09 URL
				info.setBgn(person.getBgn());
				info.setDi(person.getKcb_di());
				info.setHp(person.getHp());
				
				ReturnClass returnClass = urlCrawling(info);
				
				logger.debug(":::::::::::::::::::::::::::::::::::: KCB CRAW END ::::::::::::::::::::::::::::::::::::");
				
				//parsing
				returnClass = parseCrawling(info);
				if(Constant.SUCCESS.equals(returnClass.getCd_result())) {
					
					//TODO call Package
					/*** 부채TABLE DATA생성 proc call ***/
					debtManager.debtPdocRun(person.getNo_person());
	                isSuccess = true;
				}
				
				cd_result = returnClass.getCd_result();
                
			} catch (FinsetException e) {
                isSuccess = false;
                LogUtil.error(logger, e);
                throw e;
			} catch (IOException e) {
                isSuccess = false;
                LogUtil.error(logger, e);
                throw e;
			} finally {
                if(isSuccess == false) {
                    //error 발생시 당일 전문 데이터 DELETE
                    deleteKcbCb(person.getNo_person());
                    cd_result = Constant.FAILED;
                }
			}
		}
			
		logger.debug(":::::::::::::::::::::::::::::::::::: KCB END ::::::::::::::::::::::::::::::::::::");
			
		return cd_result;
		
	}
	
	/*
	 * KCB 전문 관련 START 
	 */
	public ReturnClass procKcbCb(KcbCreditInfoVO pInfoVO) throws UnsupportedEncodingException, IOException {

		KcbCreditInfoVO infoVO 	= pInfoVO;
		AbstractKcbInfo kcbInfo = null;
		
		try {

			HashMap<String, String> schMap = new HashMap<String, String>();
			schMap.put("sch_no_person", infoVO.getNoPerson());
			schMap.put("sch_gubun", "1");	// 1:O일 전, 2:O시간 전
			schMap.put("sch_time", "1");	// O값
			schMap.put("nm_if", infoVO.getNmIf());
			schMap.put("nm_if_sub", StringUtil.NVL(infoVO.getNmIfSub(), ""));

			HashMap<String, String> clobMap = creditMapper.getKcbInfoCLOB(schMap);
			
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
				String seq = creditMapper.getCreditInfoNextSeq();

				//4. 신용조회 전문 송수신
				infoVO.setKcb_seq(seq);
				kcbInfo.setInitData(infoVO); 			//초기 전문 기본정보 전문객체에 셋
				kcbInfo.transferNiceCb(infoVO); 		//전문 송수신처리

				//URI setting
				if(!StringUtils.isEmpty(infoVO.getKcbURI())) kcbInfo.setKcbURI(infoVO.getKcbURI());

				//5. 전문 정보 저장
				CreditInfo info = getCreditInfoObj(infoVO);
				if (info != null) {
					if(StringUtil.isEmpty(info.getIdFrt())) info.setIdFrt("SYSTEM");
					logger.debug(info.toString());
					creditMapper.saveCredit(info);
				}
				
				//6. 300전문 상세 저장
				/*
				if("300".equals(infoVO.getNmIf())) {
					
					String resDt = creditMapper.selectResDt(infoVO.getNoPerson());
					resDt = StringUtil.NVL(resDt, "19000101"); //최종 수신일 셋팅, NULL일 경우 19000101 셋팅
					logger.info("resDt ================================== **** " + resDt + "****");
				
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
	 * Method Desc : 해당 전문타입에 매핑되는 모델 객체 생성하여 리턴
	 * @param nm_if 전문타입
	 * @return AbstractKcbInfo
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
	/**
	 * 신용조회 필요한 기본정보 셋팅
	 * @param pInfo
	 * @return boolean
	 * @throws Exception 
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
	
	/*
	 * KCB 전문 관련 END
	 */
	
	@Override
	public ReturnClass deleteKcbCb(String noPerson) {
		creditMapper.deleteKcbCb(noPerson);
		return new ReturnClass(Constant.SUCCESS, "KCB 당일 데이터 삭제 완료", "");
	}
	
	
	/*
	 * KCB 크롤링 관련 START 
	 */
	@Override
	public ReturnClass urlCrawling(KcbCreditInfoVO info) throws UnsupportedEncodingException, FinsetException, IOException {
		
		Queue<KcbThreadManager> threadQueue = new LinkedList<KcbThreadManager>();
		ArrayList<Throwable> listErr = null;
		Throwable throwable = null;
		
		for(int i=0; i<info.crawPageCnt; i++) {
			
			// 멀티쓰레드 처리 시작
			logger.error(":::::::::::: 쓰레드 ::::::::::::   " + i);
			KcbThreadManager thread = new KcbThreadManager(info, i);
			threadQueue.add(thread);
			thread.start();
		}
		
		waitThread(threadQueue);
		
		/* 수행중 에러내역 */
        listErr = new ArrayList<Throwable>();
        for (KcbThreadManager subThread : threadQueue) {
            throwable = subThread.getError();
            if (throwable != null) {
                listErr.add(throwable);
                
                return new ReturnClass(Constant.FAILED, "Crawling FAILED", info);
            }
        }

        threadQueue.clear();
		return new ReturnClass(Constant.SUCCESS, "Crawling SUCCESS", info);
	}
	
	private void waitThread(Queue<KcbThreadManager> threadQueue) {
		int cntLive = 0;	// 살아있는 쓰레드 갯수
		int cnt_wait = 0;	// 대기한 seconds
        Thread.State state = null;
		do {
			cntLive = 0;
            try {
                // 1초 마다 모든 쓰레드 상태 체크
                Thread.sleep(300);
            } catch (InterruptedException e) {
                LogUtil.error(logger,e);
            }

			for (KcbThreadManager subThread : threadQueue) {
                state = subThread.getState();
				if(Thread.State.TERMINATED != state) {
					cntLive++;
                }
			}
			// 최대 5분 대기
			if(cnt_wait > 300) {
				System.err.println("Thread wait time out: 5min");
				break;
			}
			cnt_wait++;
			
		} while (cntLive > 0);
		//쓰레드 종료후 다음 
		//threadQueue.clear();
	}
	
	public class KcbThreadManager extends Thread {
		
		private KcbCreditInfoVO info;
		private int	idx;
		private Throwable      error;
		
		public KcbThreadManager(KcbCreditInfoVO info, int idx)
		{
			this.info = info;
			this.idx = idx;
		}
		
		@Override
		public void run() {
			
			KcbCreditInfoVO infoTemp = new KcbCreditInfoVO();
			
			String profile = "Y".equals(info.getYn_craw_test()) ? "LOCAL" : "REAL";
			logger.error("profile ==== " + profile);
			try {
				
				infoTemp = info.clone();

				if(idx == 0) {
					
					infoTemp.setNmIfSub("210");
					infoTemp.setReq_menu_code("210");
					infoTemp.setReq_view_code("s02173986405");
				} else {
					infoTemp.setNmIfSub("230");
					infoTemp.setReq_menu_code("230");
					infoTemp.setReq_view_code("s07174012166");
				}

				if("REAL".equals(profile)) {
					
					logger.error("reqMenuCode ==== " + infoTemp.getReq_menu_code());
					ReturnClass kcbCbData = procKcbCb(infoTemp);
					if(infoTemp.getNmIf() != null && infoTemp.getNmIf().equals("600420")) {
						
						Object retObject = kcbCbData.getReturnObj();
						if(retObject != null && retObject instanceof Kcb_600420) {

							Kcb_600420 kcb_600420 = (Kcb_600420)retObject;
							
							logger.error("[KCB ]kcbURI == \n" + kcb_600420.getKcbURI());
							logger.error("[KCB ]MenuCode == \n" + kcb_600420.getReqsMenuCode());

							// 크롤링 (등록사유코드 09 && 메뉴코드 210)
							logger.debug("[KCB ] Crawling Start");
							urlCrawling(info, kcb_600420);
						}
					}
				} else {
					
					Document 	doc 	= null;
					String filePath = "D:/JSOUP/"; 				//local
					
					if("210".equals(infoTemp.getReq_menu_code())) {
						doc = Jsoup.parse(new File(filePath+"craw_5.txt"), "UTF-8"); //local
						info.setDoc_page1(doc.html());
					} else if("230".equals(infoTemp.getReq_menu_code())) {
						doc = Jsoup.parse(new File(filePath+"craw_6.txt"), "UTF-8"); //local
						info.setDoc_page2(doc.html());
					}
				}
			} catch (IOException e) {
				LogUtil.error(logger,e);
			} catch (Exception e) {
				LogUtil.error(logger,e);
			}
		}
		
		public void urlCrawling(KcbCreditInfoVO info, Kcb_600420 kcb_600420) {
			
			Document 	doc 	= null;
			HashMap		rtnMap 	= new HashMap<String, Object>();
			
			String 		parseHtml = "";
			
			try {

				String kcbHost = "";
				String kcbURI  = kcb_600420.getKcbURI();
				String domain  = kcb_600420.getResDomain();
				
				logger.error("kcbURI ==== " + kcbURI);
				
				if(kcbURI.indexOf("api") > -1) {
					if(kcbURI.indexOf("443/") > -1) {
						kcbHost = environment.getProperty("kcbHost");
						kcbURI	= kcbURI.replace("api.allcredit.co.kr", kcbHost).replace("https", "http");
					} else {
						kcbHost = environment.getProperty("kcbApi");
						kcbURI	= kcbURI.replace("api.allcredit.co.kr", kcbHost).replace("https", "http");
					}
					domain	= domain.replace("api.allcredit.co.kr", kcbHost);
				} else {
					kcbHost = environment.getProperty("kcbMaff");
					kcbURI	= kcbURI.replace("maff.allcredit.co.kr", kcbHost);
					domain	= domain.replace("maff.allcredit.co.kr", kcbHost);
				}
				
				logger.error("kcbURI ==== " + kcbURI);
				
				rtnMap = JsoupUtil.getCrawling(kcbURI);
				parseHtml = ((Document)rtnMap.get("doc")).html();
				
				String 	href 	= Pattern.compile("\\s").matcher(parseHtml).replaceAll("");
				int 	start 	= href.indexOf("href=")+6;
				int 	end 	= href.lastIndexOf("\";");

				logger.error("href ==== " + href);
				
				href = href.substring(start, end);
				String URL		= "http://" + domain + href;
				
				logger.error("URL ==== " + URL);
				logger.error("JSESSIONID ==== " + rtnMap.get("jsessionId").toString());
				doc = JsoupUtil.getCrawlingCookie(URL, rtnMap.get("jsessionId").toString());   
					   
				//logger.error("JSOUP ==== " + doc.html().substring(0, 100));
				
				if("210".equals(kcb_600420.getReqsMenuCode())) {
					info.setDoc_page1(doc.html());
				} else if("230".equals(kcb_600420.getReqsMenuCode())) {
					info.setDoc_page2(doc.html());
				}
				
			} catch (IOException e) {
				LogUtil.error(logger,e);
			}
		}
		
		public Throwable getError() {
	        return error;
	    }
	}
	/*
	 * KCB 크롤링 관련 END 
	 */

	/*
	 * KCB PARSING START 
	 */
	/**
	 * Method Desc : KCB 크롤링 정보 파싱 및 저장
	 * 2018.01.05
	 * @param info
	 */
	@Override
	public ReturnClass parseCrawling(KcbCreditInfoVO info) throws FinsetException {

		if(!StringUtil.isEmpty(info.getDoc_page1())) this.parsePage1(info);
		if(!StringUtil.isEmpty(info.getDoc_page2())) this.parsePage2(info);

		return new ReturnClass(Constant.SUCCESS, "크롤링 성공", "");

	}

	public void parsePage1(KcbCreditInfoVO info) {

		CrawlingVO 	vo 	= new CrawlingVO();
		Document 	doc = Jsoup.parse(info.getDoc_page1());
		
		List<String> nmFcList = new ArrayList<String>();
		List<String> cdFcList = new ArrayList<String>();
		
		String     step = "START";
		
		vo.setNo_person(info.getNoPerson());

		try {
			
			Elements divLoan = doc.select("div[class=page-credit-info]");
			/******
			 * (0)대출건수, 약정금액
			 * (1)나의신용등급, 신용평점, 백분율
			 * (2)일반정보 변동, 연체정보 변동
			 * (3)신용정보조회내역건수 최근1년내, 1~3년내
			 * (4)카드개설 카드개설수, 이용카드수
			 * (5)카드이용 이용카드수, 총 이용금액
			 * (6)대출개설 개설대출수, 대출금액
			 * (7)대출이용 개설대출수, 대출금액
			 * (8)연체잔액, 대지급 잔액
			 * (9)채무불이행, 공공정보, 금융질서문란
			 * (10)연대보증수, 보증금액
			 ******/
			Elements divSumBlock = divLoan.select("div[class=sum-block]");
			/**
			 * (0) 계좌별 대출상세
			 * (1) 신용정보변동내역_일반정보
			 * (2) 신용정보변동내역_부채정보
			 * (3) 신용정보조회내역
			 * (4) 카드개설정보
			 * (5) 카드이용정보 (전월)
			 * (6) 대출개설정보 (당월)
			 * (7) 대출이용정보 (전월)
			 * (8) 연체정보
			 * (9) 기타연체
			 * (10) 연대보증상세
			 **/
			Elements divPrdBlock = divLoan.select("div[class=prd-block prd-type]");
	
			/*****
			 * 대출 정보 parsing
			 *****/
			step = "[PAGE1_0] 월별부채";
			Elements divSumLoan = divSumBlock.get(0).select("dd");	//(0)대출건수, 약정금액
			//대출건수, 약정금액
			vo.setCtn_loan(StringUtil.regExpFindNum(divSumLoan.get(0).text()));
			vo.setAmt_loan(StringUtil.addMAmt(StringUtil.regExpFindNum(divSumLoan.get(1).text())));				//약정금액(만원단위)
	
			//계좌별 대출 상세
			Elements divDtl = divPrdBlock.get(0).select("div[class=container-fluid]"); //(0) 계좌별 대출상세
			
			for(int i=0; i < divDtl.size(); i++) {
				
				CrawlingLoanVO loanVO = new CrawlingLoanVO();
				loanVO.setNm_biz(StringUtil.stringRtnNmFc(divDtl.get(i).select("span").text()));	//은행명
	
				Elements divLoanDD = divDtl.get(i).select("div[class=prdlist-info] dd");
				if(divLoanDD.size() < 1) continue; //대출정보 부족 제외
				//미등록 회원사 (미등록 회원사 관리 테이블 추가)
				loanVO.setCd_fc(checkFcInfo(cdFcList, nmFcList, loanVO.getNm_biz()));
				loanVO.setCd_type_deal(divLoanDD.get(0).text());	//대출형태
				loanVO.setYmd_loan(StringUtil.regExpFindNum(divLoanDD.get(1).text()));		// 개설일자
				loanVO.setYmd_loanend(StringUtil.regExpFindNum(divLoanDD.get(2).text()));	// 만기일자
				loanVO.setAmt_contract(StringUtil.addAmt(divLoanDD.get(3).text()));			// 약정금액(천원단위)
	
				String gubun = divLoanDD.get(4).text(); // 보증/담보/신용 구분
				if("보증".equals(gubun)) {
					loanVO.setYn_guarantor("Y");
					loanVO.setYn_loan("N");
					loanVO.setYn_credit("N");
				} else if("담보".equals(gubun)) {
					loanVO.setYn_guarantor("N");
					loanVO.setYn_loan("Y");
					loanVO.setYn_credit("N");
				} else {
					loanVO.setYn_guarantor("N");
					loanVO.setYn_loan("N");
					loanVO.setYn_credit("Y");
				}
	
				//정보관리번호 SELECT
				loanVO.setNo_person(vo.getNo_person());
				loanVO.setId_frt(vo.getNo_person());
				loanVO.setId_lst(vo.getNo_person());
	
				loanVO.setNo_manage_info(debtMapper.selectKcbNoManage(loanVO));
	
				if(StringUtil.isEmpty(loanVO.getNo_manage_info())) {
					debtMapper.insertKcbDebt(loanVO);
				}
	
				//계좌별 대출 상세내역
				Elements elsTr = divDtl.get(i).select("table tbody tr");
				for(int j=0; j < elsTr.size(); j++) {
	
					CrawlingLoanDtlVO loanDtlVO = new CrawlingLoanDtlVO();
					loanDtlVO.setNo_person(loanVO.getNo_person());
					loanDtlVO.setNo_manage_info(loanVO.getNo_manage_info());
	
					Elements elsTd = elsTr.get(j).select("td");
	
					loanDtlVO.setReq_yyyymm(StringUtil.regExpFindNum(elsTd.get(0).text()));			//이용년월(천원단위)
					loanDtlVO.setAmt_repay(elsTd.get(1).text().equals("0") ? "0" : StringUtil.addAmt(elsTd.get(1).text()));	//상환금액(천원단위)
					loanDtlVO.setAmt_remain(StringUtil.addAmt(elsTd.get(2).text())); 				//대출잔액(천원단위)
					loanDtlVO.setAmt_due(elsTd.get(3).text().equals("0") ? "0" : StringUtil.addAmt(elsTd.get(3).text()));	//연체금액(천원단위)
					loanDtlVO.setNm_account(elsTd.get(4).text());	//계좌상태명
	
					//DEPT_PERSIONAL_REPAY_LIST(계좌별 대출 월별 리스트) MERGE
					debtMapper.updateKcbDtlDebt(loanDtlVO);
				}
			}
	
			/*****
			 * 신용 정보 parsing
			 *****/
			step = "[PAGE1_1] 신용점수";
			divSumLoan = divSumBlock.get(1).select("dd"); // (1)나의신용등급, 신용평점, 백분율
			vo.setReq_yyyymm(DateUtil.getCurrentDate().substring(0,6));
			vo.setGrade_credit(StringUtil.regExpFindNum(divSumLoan.get(0).text()));		//신용등급
			vo.setRating_credit(StringUtil.regExpFindNum(divSumLoan.get(1).text()));	//신용평점
			vo.setPercentage(StringUtil.regExpFindNum(divSumLoan.get(2).text()));		//백분율
	
			// KCB_CREDIT_LIST(신용등급 정보) INSERT OR UPDATE
			creditMapper.saveKcbCreditList(vo);
			creditMapper.saveKcbCreditHistList(vo);
	
			Elements divSpan = divLoan.select("span[class=info]");
			vo.setCnt_credit_change(StringUtil.regExpFindNum(divSpan.get(0).text()));	//신용변동내역(최근1년)
			vo.setCnt_credit_ref_1y(StringUtil.regExpFindNum(divSpan.get(1).text()));	//신용정보조회내역(최근1년)
	
			vo.setCnt_card(StringUtil.regExpFindNum(divSpan.get(2).text()));			//카드현황
			vo.setCnt_loan(StringUtil.regExpFindNum(divSpan.get(3).text()));			//대출현황
			vo.setCnt_overdue(StringUtil.regExpFindNum(divSpan.get(4).text()));			//연체현황
			vo.setCnt_guarantee(StringUtil.regExpFindNum(divSpan.get(5).text()));		//연대보증현황
	
			divSumLoan = divSumBlock.get(2).select("dd"); // (2)일반정보 변동, 연체정보 변동
			vo.setCnt_normal_info(StringUtil.regExpFindNum(divSumLoan.get(0).text()));	//일반정보 변동
			vo.setCnt_overdue_info(StringUtil.regExpFindNum(divSumLoan.get(1).text()));	//연체정보 변동
	
			divSumLoan = divSumBlock.get(3).select("dd"); // (3)신용정보조회내역건수 최근1년내, 1~3년내
			vo.setCnt_credit_ref_1y(StringUtil.regExpFindNum(divSumLoan.get(0).text()));	//최근1년내
			vo.setCnt_credit_ref_3y(StringUtil.regExpFindNum(divSumLoan.get(1).text()));	//1~3년내
	
	
	
	
			/** 신용변동이력 **/
			step = "[PAGE1_2] 신용변동이력";
			String maxDt = StringUtil.NVL(creditMapper.selectMaxCreditChange(vo.getNo_person()), "0");
			divDtl = divPrdBlock.get(1).select("div[class=container-fluid]"); 		//(1) 신용정보변동내역_일반정보
			for(int i=0; i < divDtl.size(); i++) {
	
				CreditDtlVO creditDtlVO = new CreditDtlVO();
	
				if(divDtl.get(i).children().size() > 0) {
	
					creditDtlVO.setNo_person(info.getNoPerson());
					creditDtlVO.setCd_change_info("01"); //정보변동구분 01:일반정보, 02:연체정보
					String nmFc = "", item = "";
					for(Object obj : divDtl.get(i).select("span").get(0).childNodes()) {
						if(obj instanceof TextNode) {
							nmFc = ((TextNode)obj).text();
						} else {
							for(Object o : ((Node)obj).childNodes()) {
								if(o instanceof TextNode) {
									item = ((TextNode)o).text();
								}
							}
						}
					}
					
					nmFc = StringUtil.stringRtnNmFc(nmFc);
					creditDtlVO.setCd_fc(checkFcInfo(cdFcList, nmFcList, nmFc));
					creditDtlVO.setNm_fc(nmFc);				//은행명
					creditDtlVO.setChange_contents(item);	//내용
	
					Elements divPrdList = divDtl.get(i).select("div[class=prdlist-info] dd");
					creditDtlVO.setDt_info(StringUtil.regExpFindNum(divPrdList.get(0).text()));	//정보등록일
					creditDtlVO.setCollector(divPrdList.get(1).text());							//수집처
	
					if(Integer.parseInt(creditDtlVO.getDt_info()) > Integer.parseInt(maxDt)) {
						creditMapper.saveKcbCreditChangeInfo(creditDtlVO);
					}
				}
			}
			divDtl = divPrdBlock.get(2).select("div[class=container-fluid]");	//(2) 신용정보변동내역_부채정보
			for(int i=0; i < divDtl.size(); i++) {
	
				CreditDtlVO creditDtlVO = new CreditDtlVO();
	
				if(divDtl.get(i).children().size() > 0) {
	
					creditDtlVO.setNo_person(info.getNoPerson());
					creditDtlVO.setCd_change_info("02"); //정보변동구분 01:일반정보, 02:연체정보
					String nmFc = "", item = "";
					for(Object obj : divDtl.get(i).select("span").get(0).childNodes()) {
						if(obj instanceof TextNode) {
							nmFc = ((TextNode)obj).text();
						} else {
							for(Object o : ((Node)obj).childNodes()) {
								if(o instanceof TextNode) {
									item = ((TextNode)o).text();
								}
							}
						}
					}
	
					nmFc = StringUtil.stringRtnNmFc(nmFc);
					creditDtlVO.setCd_fc(checkFcInfo(cdFcList, nmFcList, nmFc));
					creditDtlVO.setNm_fc(nmFc);				//은행명
					creditDtlVO.setChange_contents(item);	//내용
	
	
					Elements divPrdList = divDtl.get(i).select("div[class=prdlist-info] dd");
					creditDtlVO.setDt_info(StringUtil.regExpFindNum(divPrdList.get(0).text()));	//정보등록일
					creditDtlVO.setCollector(divPrdList.get(1).text());							//수집처
	
					if(Integer.parseInt(creditDtlVO.getDt_info()) > Integer.parseInt(maxDt)) {
						creditMapper.saveKcbCreditChangeInfo(creditDtlVO);
					}
				}
			}
	
			/** 신용정보조회내역 **/
			step = "[PAGE1_3] 신용정보조회내역";
			maxDt = StringUtil.NVL(creditMapper.selectMaxCreditInquiry(vo.getNo_person()), "0");
			divDtl = divPrdBlock.get(3).select("div[class=container-fluid]"); //(3) 신용정보조회내역
			for(int i=0; i < divDtl.size(); i++) {
	
				CreditDtlVO creditDtlVO = new CreditDtlVO();
	
				if(divDtl.get(i).children().size() > 0) {
	
					creditDtlVO.setNo_person(info.getNoPerson());
					creditDtlVO.setNm_fc(StringUtil.stringRtnNmFc(divDtl.get(i).select("span").text()));	//금융사명
	
					Elements divPrdList = divDtl.get(i).select("div[class=prdlist-info] dd");
					creditDtlVO.setDt_info(StringUtil.regExpFindNum(divPrdList.get(0).text()));	//조회일자
					creditDtlVO.setChange_contents(divPrdList.get(1).text());					//조회목적
	
					if(Integer.parseInt(creditDtlVO.getDt_info()) > Integer.parseInt(maxDt)) {
						creditMapper.saveKcbCreditInquiryInfo(creditDtlVO);
					}
				}
			}
	
			/** 카드개설정보 및 이용정보 SET **/
			step = "[PAGE1_4] 카드개설정보";
			JSONArray cardOpenList = new JSONArray();
			divSumLoan = divSumBlock.get(4).select("dd"); // (4)카드개설 카드개설수, 이용카드수
			vo.setCnt_card_open(StringUtil.regExpFindNum(divSumLoan.get(0).text()));	//카드개설수
			vo.setCnt_card_use(StringUtil.regExpFindNum(divSumLoan.get(1).text()));		//이용카드수
	
			divSumLoan = divSumBlock.get(5).select("dd"); // (5)카드이용 이용카드수, 총 이용금액
			vo.setAmt_card_total(StringUtil.addMAmt(divSumLoan.get(1).text()));	//총 이용금액
	
			//해지일자 업데이트하기
			KcbCardInfo kcbCardInfoInstance = new KcbCardInfo();
			kcbCardInfoInstance.setNo_person(info.getNoPerson());
			kcbCardInfoInstance.setYmd_cancel(DateUtil.dateToString(new Date()));	//오늘을 세팅
			creditMapper.updateKcbCardInfoToday(kcbCardInfoInstance);
			
			divDtl = divPrdBlock.get(4).select("div[class=container-fluid]"); //(4) 카드개설정보
			
			//20180629 김휘경 추가 - 이후에 ca type_card를 판별해주어야 하기 때문에 저장해 둘 변수가 필요했음
			Map<String, List<String>> kcbCardInfoMap = new HashMap<String, List<String>>();
			List<String> kcbCardTypeList = new ArrayList<String>();
			
			for(int i=0; i < divDtl.size(); i++) {
	
				JSONObject kcbCardInfo = new JSONObject();
	
				if(divDtl.get(i).children().size() > 0) {
	
					//금융사 코드(cd_fc)를 공통으로 사용하는데 두 번 태우는건 비효율적이라 변수로 선언
					String cd_fc = "";
					
					kcbCardInfo.put("no_person", info.getNoPerson());
					kcbCardInfo.put("nm_fc", StringUtil.stringRtnNmFc(divDtl.get(i).select("span").text()));	//금융사명
					cd_fc = checkFcInfo(cdFcList, nmFcList, kcbCardInfo.get("nm_fc").toString());				//금융사 코드 저장
					kcbCardInfo.put("cd_fc", cd_fc);				
					
					String cdTypeDeal = "";
					if(divDtl.get(i).select("label").text().indexOf("신용") > -1) {
						cdTypeDeal = "01";
					} else {
						cdTypeDeal = "02";
					}
					kcbCardInfo.put("cd_type_deal", cdTypeDeal);	//체크,신용 구분
	
					Elements divPrdList = divDtl.get(i).select("div[class=prdlist-info] dd");
					kcbCardInfo.put("ymd_open",	 	(StringUtil.regExpFindNum(divPrdList.get(0).text())));	//개설일자
					kcbCardInfo.put("amt_limit", 	(StringUtil.addAmt(divPrdList.get(1).text())));			//총한도
					kcbCardInfo.put("amt_ca_limit", (StringUtil.addAmt(divPrdList.get(2).text())));			//단기카드대출 한도
	
					cardOpenList.add(kcbCardInfo);
					
					//상단 내용을 기준으로 KCB_CARD_INFO에도 집어넣기
					kcbCardInfoInstance = new KcbCardInfo();
					kcbCardInfoInstance.setNo_person(vo.getNo_person());
					kcbCardInfoInstance.setCd_fc(cd_fc);
					kcbCardInfoInstance.setType_card(cdTypeDeal);
					kcbCardInfoInstance.setYmd_open(StringUtil.regExpFindNum(divPrdList.get(0).text()));
					kcbCardInfoInstance.setAmt_limit(StringUtil.addAmt(divPrdList.get(1).text()));
					kcbCardInfoInstance.setAmt_ca_limit(StringUtil.addAmt(divPrdList.get(2).text()));
					
					creditMapper.saveKcbCardInfo(kcbCardInfoInstance);
					
					//상세내역에서 사용하기 위해 map에 집어넣는 과정
					//우선 cd_fc를 통해 list를 가져와본다
					List<String> list = kcbCardInfoMap.get(cd_fc);
					//리스트가 null이라면 아직 해당 cd_fc에는 아무것도 들어가지 않았으므로 map에 리스트를 집어넣어줘야한다
					if(list == null) {
						kcbCardTypeList = new ArrayList<String>();
						kcbCardTypeList.add(cdTypeDeal);
						kcbCardInfoMap.put(cd_fc, kcbCardTypeList);
					} else { //만약 무엇인가 들어가 있다면 그 리스트에 cdTypeDeal을 추가하고 저장해야함
						list.add(cdTypeDeal);
						kcbCardInfoMap.put(cd_fc, list);
					}
				}
			}
			vo.setList_card_open(cardOpenList.toString());
	
			step = "[PAGE1_5] 카드이용정보";
			divDtl = divPrdBlock.get(5).select("div[class=container-fluid]"); //(5) 카드이용정보 (전월)
			JSONArray cardUseList = new JSONArray();
			
			//20180629 김휘경 추가 - 개별적으로 판단하기에는 과정이 복잡해서 한번에 처리할 수 있게 변수에 담아두려함
			Map<String, List<KcbCardDtlList>> kcbCardDtlListMap = new HashMap<String, List<KcbCardDtlList>>();
			//객체 이름이 List로 끝나서 어쩔 수 없었습니다
			List<KcbCardDtlList> kcbCardDtlListList = new ArrayList<KcbCardDtlList>();
			for(int i=0; i < divDtl.size(); i++) {
	
				JSONObject kcbCardInfo = new JSONObject();
	
				if(divDtl.get(i).children().size() > 0) {
					//cd_fc는 하단에도 쓰기때문에 저장
					String cd_fc = "";
					
					kcbCardInfo.put("no_person", info.getNoPerson());
					kcbCardInfo.put("nm_fc", StringUtil.stringRtnNmFc(divDtl.get(i).select("span").text()));	//금융사명
					cd_fc = checkFcInfo(cdFcList, nmFcList, kcbCardInfo.get("nm_fc").toString());
					kcbCardInfo.put("cd_fc", cd_fc);
	
					Elements divPrdList = divDtl.get(i).select("div[class=prdlist-info] dd");
					kcbCardInfo.put("amt_total",			(StringUtil.addAmt(divPrdList.get(0).text())));			//총이용금액
					kcbCardInfo.put("amt_lump_sum", 		(StringUtil.addAmt(divPrdList.get(1).text())));			//신용일시불 이용금액
					kcbCardInfo.put("amt_installment", 		(StringUtil.addAmt(divPrdList.get(2).text())));			//신용할부 이용금액
					kcbCardInfo.put("amt_short_card_loan", 	(StringUtil.addAmt(divPrdList.get(3).text())));			//단기카드대출 이용금액
					kcbCardInfo.put("amt_check", 			(StringUtil.addAmt(divPrdList.get(4).text())));			//체크 이용금액
					kcbCardInfo.put("amt_delay", 			(StringUtil.addAmt(divPrdList.get(5).text())));			//연체금액
	
					//체크 이용금액 > 0 -> 체크카드
					if(Integer.parseInt(kcbCardInfo.get("amt_total").toString()) == 0) {
						kcbCardInfo.put("cd_type_deal", "");	//체크,신용 구분
					} else if(Integer.parseInt(kcbCardInfo.get("amt_total").toString()) > Integer.parseInt(kcbCardInfo.get("amt_check").toString())
							&& Integer.parseInt(kcbCardInfo.get("amt_check").toString()) > 0) {
						kcbCardInfo.put("cd_type_deal", "01");	//신용
					} else if(Integer.parseInt(kcbCardInfo.get("amt_check").toString()) > 0) {
						kcbCardInfo.put("cd_type_deal", "02");	//체크
					} else {
						kcbCardInfo.put("cd_type_deal", "01");	//신용
					}
					cardUseList.add(kcbCardInfo);
					
					//상단 내용을 기준으로 KCB_CARD_DTL_LIST에 저장
					//기준년월은 스크래핑 시행 일자 한 달 전
					String req_yyyymm = DateUtil.getFirstDateOfPrevMonth(DateUtil.getCurrentDate(), 1).substring(0,6);
					
					//데이터 유무에 따라 type_card가 나뉘므로 선 저장
					String amt_lump_sum = StringUtil.addAmt(divPrdList.get(1).text());
					String amt_installment = StringUtil.addAmt(divPrdList.get(2).text());
					String amt_short_card_loan = StringUtil.addAmt(divPrdList.get(3).text());
					String amt_check = StringUtil.addAmt(divPrdList.get(4).text());
					
					//체크카드로 사용한 내역이 있는지 확인
					boolean isCheck = (!amt_check.equals("0"));
					
					//신용카드로 사용한 내역이 있는지 확인
					boolean isCredit = ((!amt_lump_sum.equals("0"))||(!amt_installment.equals("0"))||(!amt_short_card_loan.equals("0")));
					
					//type_card는 미리 선언
					String type_card = "";
					
					//type_card 검증
					if(!isCheck&&!isCredit) {
						//들어온 데이터가 아예 없을 경우 
						type_card = "00";
					} else if(!isCheck&&isCredit) {
						//체크는 아니고 신용은 맞는 경우
						type_card = "01";
					} else if(isCheck&&!isCredit) {
						//체크이고 신용이 아닌 경우
						type_card = "02";
					} else if(isCheck&&isCredit) {
						//둘 다 맞는 경우
						type_card = "03";
					} else {
						logger.error("카드 구분이 불가능합니다. KCB데이터를 확인해주세요.");
					}
					
					KcbCardDtlList kcbCardDtlList = new KcbCardDtlList();
					kcbCardDtlList.setNo_person(info.getNoPerson());
					kcbCardDtlList.setCd_fc(cd_fc);
					kcbCardDtlList.setType_card(type_card);
					kcbCardDtlList.setReq_yyyymm(req_yyyymm);
					kcbCardDtlList.setAmt_total(StringUtil.addAmt(divPrdList.get(0).text()));
					kcbCardDtlList.setAmt_lump_sum(amt_lump_sum);
					kcbCardDtlList.setAmt_installment(amt_installment);
					kcbCardDtlList.setAmt_short_card_loan(amt_short_card_loan);
					kcbCardDtlList.setAmt_check(amt_check);
					kcbCardDtlList.setAmt_delay(StringUtil.addAmt(divPrdList.get(5).text()));
					
					//한번에 데이터를 변환 시키기위해 map에 집어넣는 과정
					//우선 cd_fc를 통해 list를 가져와본다
					List<KcbCardDtlList> list = kcbCardDtlListMap.get(cd_fc);
					//리스트가 null이라면 아직 해당 cd_fc에는 아무것도 들어가지 않았으므로 map에 리스트를 집어넣어줘야한다
					if(list == null) {
						kcbCardDtlListList = new ArrayList<KcbCardDtlList>();
						kcbCardDtlListList.add(kcbCardDtlList);
						kcbCardDtlListMap.put(cd_fc, kcbCardDtlListList);
					} else { //만약 무엇인가 들어가 있다면 그 리스트에 kcbCardList를 추가하고 저장해야함
						list.add(kcbCardDtlList);
						kcbCardDtlListMap.put(cd_fc, list);
					}
				}
			}
			vo.setList_card_use(cardUseList.toString());
			//데이터 확인과 DB저장을 한번에 처리하는 메소드 호출
			checkCardDtlListAndSave(kcbCardInfoMap, kcbCardDtlListMap);
			step = "[PAGE1_6] 대출개설정보";
			/** 대출개설정보 및 이용정보 SET **/
			JSONArray debtOpenList = new JSONArray();
			divSumLoan = divSumBlock.get(6).select("dd"); // (6)대출개설 개설대출수, 대출금액
			vo.setCnt_debt_open(StringUtil.regExpFindNum(divSumLoan.get(0).text()));	//카드개설수
			vo.setAmt_debt(StringUtil.addMAmt(divSumLoan.get(1).text()));				//대출금액
	
			divDtl = divPrdBlock.get(6).select("div[class=container-fluid]"); 	//(6) 대출개설정보 (당월)
	
			//해지일자 SET UPDATE
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("no_person", info.getNoPerson());
			debtMapper.updateKcbDebtCancel(paramMap);
	
			for(int i=0; i < divDtl.size(); i++) {
	
				JSONObject kcbDebtInfo = new JSONObject();
	
				if(divDtl.get(i).children().size() > 0) {
	
					kcbDebtInfo.put("no_person", info.getNoPerson());
					kcbDebtInfo.put("nm_fc", StringUtil.stringRtnNmFc(divDtl.get(i).select("span").text()));	//금융사명
					kcbDebtInfo.put("cd_fc", checkFcInfo(cdFcList, nmFcList, kcbDebtInfo.get("nm_fc").toString()));
					
					Elements divPrdList = divDtl.get(i).select("div[class=prdlist-info] dd");
					kcbDebtInfo.put("ymd_loan",	 	(StringUtil.regExpFindNum(divPrdList.get(0).text())));	//개설일
					kcbDebtInfo.put("ymd_loanend", 	(StringUtil.regExpFindNum(divPrdList.get(1).text())));	//만기일
					kcbDebtInfo.put("amt_contract", (StringUtil.addAmt(divPrdList.get(2).text())));			//약정금액
					kcbDebtInfo.put("amt_remain", 	(StringUtil.addAmt(divPrdList.get(3).text())));			//대출잔액
	
					debtOpenList.add(kcbDebtInfo);
	
					paramMap = (Map<String, Object>)JSONObject.toBean(kcbDebtInfo, java.util.HashMap.class);
					debtMapper.updateKcbDebt(paramMap);
	
					//해지일자 NULL UPDATE
					int rtnCd = debtMapper.updateKcbDebtCancel(paramMap);
					
					//신규대출 계좌 INSERT
					if(rtnCd < 1) {
						CrawlingLoanVO loanVO = new CrawlingLoanVO();
						loanVO.setNo_person(paramMap.get("no_person").toString());
						loanVO.setYmd_loan(paramMap.get("ymd_loan").toString());			// 개설일자
						loanVO.setYmd_loanend(paramMap.get("ymd_loanend").toString());		// 만기일자
						loanVO.setAmt_contract(paramMap.get("amt_contract").toString());	// 약정금액(천원단위)
						loanVO.setAmt_remain(paramMap.get("amt_remain").toString());		// 대출잔액
						loanVO.setCd_fc(paramMap.get("cd_fc").toString());					// 금융사코드
						loanVO.setId_frt("SYS");
						loanVO.setId_lst("SYS");
						loanVO.setNo_manage_info(debtMapper.selectKcbNoManage(loanVO));
						if(StringUtil.isEmpty(loanVO.getNo_manage_info())) {
							debtMapper.insertKcbDebt(loanVO);
						}
					}
				}
			}
			vo.setList_debt_open(debtOpenList.toString());
	
			step = "[PAGE1_7] 대출이용정보";
			divDtl = divPrdBlock.get(7).select("div[class=container-fluid]"); 	//(7) 대출이용정보 (전월)
			JSONArray debtUseList = new JSONArray();
	
			for(int i=0; i < divDtl.size(); i++) {
				
				JSONObject kcbDebtInfo = new JSONObject();
	
				if(divDtl.get(i).children().size() > 0) {
	
					kcbDebtInfo.put("no_person", info.getNoPerson());
					kcbDebtInfo.put("nm_fc", StringUtil.stringRtnNmFc(divDtl.get(i).select("span").text()));	//금융사명
					kcbDebtInfo.put("cd_fc", checkFcInfo(cdFcList, nmFcList, kcbDebtInfo.get("nm_fc").toString()));
	
					Elements divPrdList = divDtl.get(i).select("div[class=prdlist-info] dd");
					kcbDebtInfo.put("amt_contract",	(StringUtil.addAmt(divPrdList.get(0).text())));	//약정금액
					kcbDebtInfo.put("amt_remain", 	(StringUtil.addAmt(divPrdList.get(1).text())));	//대출잔액
					kcbDebtInfo.put("amt_repay", 	(StringUtil.addAmt(divPrdList.get(2).text())));	//상환금액
					kcbDebtInfo.put("cd_type_deal",	(divPrdList.get(3).text()));	//대출형태
					kcbDebtInfo.put("amt_delay", 	(StringUtil.addAmt(divPrdList.get(4).text())));	//연체금액
	
					debtUseList.add(kcbDebtInfo);
				}
			}
			vo.setList_debt_use(debtUseList.toString());
	
			/** 연체정보 SET **/
			step = "[PAGE1_8] 연체정보";
			JSONArray orverDueList = new JSONArray();
			divSumLoan = divSumBlock.get(8).select("dd"); // (8)연체잔액, 대지급 잔액
			vo.setBal_overdue(StringUtil.addMAmt(divSumLoan.get(0).text()));	//연체잔액
			vo.setBal_pay(StringUtil.addMAmt(divSumLoan.get(1).text()));		//대지급잔액
	
			divDtl = divPrdBlock.get(8).select("div[class=container-fluid]"); 	//(8) 연체정보
			
			//DT_DELETE 업데이트
			creditMapper.updateDtDeleteOverdueInfo(info.getNoPerson());
			creditMapper.updateDtDeleteOverdueSteadpayInfo(info.getNoPerson());
			
			for(int i=0; i < divDtl.size(); i++) {
				JSONObject kcbOverdueInfo = new JSONObject();
	
				if(divDtl.get(i).children().size() > 0) {
					kcbOverdueInfo.put("no_person", info.getNoPerson());

					//cdFc는 DB에 접속해야 해서 하단에서 사용하기 위해 이를 저장하는 변수 선언
					String cdFc = "";
					String nmFc = "";
					
					//java 버전이 String을 바로 switch문에서 사용할  수 없기 때문에 boolean 타입 선언
					boolean isOverdue = true;
					
					for(Object obj : divDtl.get(i).select("span").get(0).childNodes()) {
						if(obj instanceof TextNode) {
							nmFc = ((TextNode)obj).text();
						}
					}
					kcbOverdueInfo.put("nm_fc", StringUtil.stringRtnNmFc(nmFc));	//금융사명
					cdFc = checkFcInfo(cdFcList, nmFcList, StringUtil.stringRtnNmFc(nmFc));
					kcbOverdueInfo.put("cd_fc", cdFc);
					
					Elements divPrdList = divDtl.get(i).select("div[class=prdlist-info] dd");
					String cdType = "";
					if(divDtl.get(i).select("label").text().indexOf("연체") > -1) {
						cdType = "01"; //연체
						kcbOverdueInfo.put("amt_frt_delay",	(StringUtil.addAmt(divPrdList.get(1).text())));			//최초연체금액
						isOverdue = true;
					} else {
						cdType = "02"; //대지급
						kcbOverdueInfo.put("amt_frt_delay",	(StringUtil.regExpFindNum(divPrdList.get(1).text())));	//대지급 상환일자
						isOverdue = false;
					}
					kcbOverdueInfo.put("cd_type", cdType);
					kcbOverdueInfo.put("ymd_delay",	 	(StringUtil.regExpFindNum(divPrdList.get(0).text())));	//최초연체기산일자, 대지급 발생일자
					kcbOverdueInfo.put("amt_repay", 	(StringUtil.addAmt(divPrdList.get(2).text())));			//상환금액
					kcbOverdueInfo.put("amt_delay", 	(StringUtil.addAmt(divPrdList.get(3).text())));			//연체잔액,			대지급잔액
	
					orverDueList.add(kcbOverdueInfo);
					
					//20180629 김휘경 추가 - 상단의 내용들을 바탕으로 DB에 따로 넣기 위함
					//연체라면
					if(isOverdue){
						KcbOverdueInfo overdueInfo = new KcbOverdueInfo();
						overdueInfo.setNo_person(info.getNoPerson());										//회원관리번호
						overdueInfo.setCd_fc(cdFc);															//금융사코드
						overdueInfo.setYmd_frt_delay(StringUtil.regExpFindNum(divPrdList.get(0).text()));	//최초연체가산일자
						overdueInfo.setAmt_frt_delay(StringUtil.addAmt(divPrdList.get(1).text())); 			//최초연체금액
						overdueInfo.setAmt_remain(StringUtil.addAmt(divPrdList.get(3).text()));				//연체잔액
						overdueInfo.setAmt_repay(StringUtil.addAmt(divPrdList.get(2).text()));				//상환금액
						
						//ID_FRT는 실제 객체에 선언되어 있으나 다른 메소드들과 같이 no_person으로 집어넣기로 함
						creditMapper.saveKcbOverdueInfo(overdueInfo);
					} else { //대지급이라면
						KcbOverdueSteadpayInfo overdueSteadpayInfo = new KcbOverdueSteadpayInfo();
						overdueSteadpayInfo.setNo_person(info.getNoPerson());											//회원관리번호
						overdueSteadpayInfo.setCd_fc(cdFc);																//금융사코드
						overdueSteadpayInfo.setYmd_frt_stead_pay(StringUtil.regExpFindNum(divPrdList.get(0).text()));	//대지금발생일자
						overdueSteadpayInfo.setAmt_stead_pay(StringUtil.addAmt(divPrdList.get(3).text())); 				//대지급금액
						overdueSteadpayInfo.setYmd_stead_repay(StringUtil.regExpFindNum(divPrdList.get(1).text()));		//대지급상환일자
						overdueSteadpayInfo.setAmt_repay(StringUtil.addAmt(divPrdList.get(2).text()));					//상환금액
						
						creditMapper.saveKcbOverdueSteadpayInfo(overdueSteadpayInfo);
					}
				}
			}
			vo.setList_overdue_info(orverDueList.toString());
	
			/** 기타연체 SET **/
			step = "[PAGE1_9] 기타연체";
			JSONArray orverDueEtcList = new JSONArray();
			divSumLoan = divSumBlock.get(9).select("dd"); 	// (9)채무불이행, 공공정보, 금융질서문란
			vo.setCnt_default(StringUtil.regExpFindNum(divSumLoan.get(0).text()));		//채무불이행
			vo.setCnt_public(StringUtil.regExpFindNum(divSumLoan.get(1).text()));		//공공정보
			vo.setCnt_fin_disorder(StringUtil.regExpFindNum(divSumLoan.get(2).text()));	//금융질서문란
	
			divDtl = divPrdBlock.get(9).select("div[class=container-fluid]"); 	//(9) 기타연체
			
			//DT_DELETE 업데이트
			creditMapper.updateDtDeleteOverdueDefaultInfo(info.getNoPerson());
			creditMapper.updateDtDeleteOverduePublicInfo(info.getNoPerson());
			
			for(int i=0; i < divDtl.size(); i++) {
	
				JSONObject kcbOverdueEctInfo = new JSONObject();
	
				if(divDtl.get(i).children().size() > 0) {
	
					kcbOverdueEctInfo.put("no_person", info.getNoPerson());
	
					String nmFc = "";
					String cdFc = "";
					for(Object obj : divDtl.get(i).select("span").get(0).childNodes()) {
						if(obj instanceof TextNode) {
							nmFc = ((TextNode)obj).text();
						}
					}
					nmFc = StringUtil.stringRtnNmFc(nmFc);
					kcbOverdueEctInfo.put("nm_fc", nmFc);	//금융사명
					cdFc = checkFcInfo(cdFcList, nmFcList, StringUtil.stringRtnNmFc(nmFc));
					kcbOverdueEctInfo.put("cd_fc", cdFc);
					String cdType = "";
					
					//switch 문을 사용하기 위한 변수
					int integerCdType = 0;
					
					Elements divPrdList = divDtl.get(i).select("div[class=prdlist-info] dd");
					
					if(divDtl.get(i).select("label").text().indexOf("채무불이행") > -1) {
						cdType = "01"; //채무불이행
						kcbOverdueEctInfo.put("ymd_delay",	 	(StringUtil.regExpFindNum(divPrdList.get(0).text())));	//발생일자
						kcbOverdueEctInfo.put("amt_delay",		(StringUtil.addAmt(divPrdList.get(1).text())));			//연체금액
						kcbOverdueEctInfo.put("ymd_repay",	 	(StringUtil.regExpFindNum(divPrdList.get(2).text())));	//상환일자
						kcbOverdueEctInfo.put("amt_repay", 		(StringUtil.addAmt(divPrdList.get(3).text())));			//상환금액
						kcbOverdueEctInfo.put("amt_orderdue",	(StringUtil.addAmt(divPrdList.get(4).text())));			//연체잔액
						kcbOverdueEctInfo.put("cd_default",	 	(StringUtil.regExpFindNum(divPrdList.get(5).text())));	//등록사유

						integerCdType = 1;
						
					} else if(divDtl.get(i).select("label").text().indexOf("공공정보") > -1) {
						cdType = "02"; //공공정보
						kcbOverdueEctInfo.put("nm_agency",	 	(StringUtil.regExpFindNum(divPrdList.get(0).text())));	//관리점명
						kcbOverdueEctInfo.put("ymd_delay",	 	(StringUtil.regExpFindNum(divPrdList.get(1).text())));	//연체등록일자
						kcbOverdueEctInfo.put("amt_delay",		(StringUtil.addAmt(divPrdList.get(2).text())));			//등록금액
						kcbOverdueEctInfo.put("no_case",	 	(StringUtil.regExpFindNum(divPrdList.get(3).text())));	//사건번호
						kcbOverdueEctInfo.put("cd_default",	 	(StringUtil.regExpFindNum(divPrdList.get(4).text())));	//등록사유
						
						integerCdType = 2;
						
					} else {
						cdType = "03"; //금융질서문란
						kcbOverdueEctInfo.put("nm_agency",	 	(StringUtil.regExpFindNum(divPrdList.get(0).text())));	//관리점명
						kcbOverdueEctInfo.put("ymd_default", 	(StringUtil.regExpFindNum(divPrdList.get(0).text())));	//발생일자
						kcbOverdueEctInfo.put("amt_delay",		(StringUtil.addAmt(divPrdList.get(1).text())));			//연체금액
						kcbOverdueEctInfo.put("ymd_repay",	 	(StringUtil.regExpFindNum(divPrdList.get(2).text())));	//상환일자
						kcbOverdueEctInfo.put("amt_repay", 		(StringUtil.addAmt(divPrdList.get(3).text())));			//상환금액
						kcbOverdueEctInfo.put("amt_regist",		(StringUtil.addAmt(divPrdList.get(4).text())));			//등록금액
						kcbOverdueEctInfo.put("cd_default",	 	(StringUtil.regExpFindNum(divPrdList.get(5).text())));	//등록사유
	
						integerCdType = 3;
					}
					kcbOverdueEctInfo.put("cd_type", cdType);
	
					orverDueEtcList.add(kcbOverdueEctInfo);
					
					switch (integerCdType) {
					case 1: //채무불이행정보
						step = "[PAGE1_9_01] 채무불이행";
						KcbOverdueDefaultInfo kcbOverdueDefaultInfo = new KcbOverdueDefaultInfo();
						kcbOverdueDefaultInfo.setNo_person(info.getNoPerson());											//회원관리번호
						kcbOverdueDefaultInfo.setCd_fc(cdFc);															//금융사코드
						kcbOverdueDefaultInfo.setYmd_default(StringUtil.regExpFindNum(divPrdList.get(0).text()));		//발생일자
						kcbOverdueDefaultInfo.setAmt_regist(StringUtil.addAmt(divPrdList.get(1).text()));				//연체 금액 - (DB)등록 금액
						kcbOverdueDefaultInfo.setYmd_repay(StringUtil.regExpFindNum(divPrdList.get(2).text()));			//상환일자
						kcbOverdueDefaultInfo.setAmt_repay(StringUtil.addAmt(divPrdList.get(3).text()));				//상환금액
						kcbOverdueDefaultInfo.setAmt_delay(StringUtil.addAmt(divPrdList.get(4).text()));				//연체 잔액 - (DB)연체 금액 
						kcbOverdueDefaultInfo.setCd_default(StringUtil.regExpFindNum(divPrdList.get(5).text()));		//등록사유 - (DB)등록사유코드 
						
						creditMapper.saveKcbOverdueDefaultInfo(kcbOverdueDefaultInfo);
						
						break;
						
					case 2: //공공정보
						step = "[PAGE1_9_02] 공공정보";
						KcbOverduePublicInfo kcbOverduePublicInfo = new KcbOverduePublicInfo();
						kcbOverduePublicInfo.setNo_person(info.getNoPerson());											//회원관리번호
						kcbOverduePublicInfo.setCd_fc(cdFc);															//금융사코드
						kcbOverduePublicInfo.setNm_agency(StringUtil.regExpFindNum(divPrdList.get(0).text()));			//관리점명 - 현재는 들어오지 않음
						kcbOverduePublicInfo.setYmd_default(StringUtil.regExpFindNum(divPrdList.get(1).text()));		//연체등록일자
						kcbOverduePublicInfo.setAmt_regist(StringUtil.addAmt(divPrdList.get(2).text()));				//등록금액
						kcbOverduePublicInfo.setNo_case(StringUtil.regExpFindNum(divPrdList.get(3).text()));			//사건번호
						kcbOverduePublicInfo.setCd_default(StringUtil.regExpFindNum(divPrdList.get(4).text()));			//등록번호
						
						creditMapper.saveKcbOverduePublicInfo(kcbOverduePublicInfo);
						
						break;
						
					case 3: //금융질서문란정보 - 들어오는 정보가 하나도 없어 CREDIT_CRAWLING_LOG에 담아두기로 
						step = "[PAGE1_9_03] 금융질서문란";
						Map<String, Object> logMap = new HashMap<String, Object>();
						logMap.put("no_person", info.getNoPerson());
						logMap.put("step", step);
						logMap.put("log", "금융 질서 문란 정보가 추가되었습니다. 데이터를 확인해 주세요");
						logMap.put("log_html", info.getDoc_page2());
						
						creditMapper.insertCrawlingLog(logMap);
						break;
						
					default:
						logger.debug("KCB 기타 연체 정보에서 알 수 없는 정보가 들어왔습니다. 정보를 확인해주세요");
						break;
					}
					
				}
			}
			vo.setList_overdue_etc(orverDueEtcList.toString());
	
			/** 연대보증상세 SET **/
			step = "[PAGE1_10] 연대보증";
			JSONArray kcbGuaranteeList = new JSONArray();
			divSumLoan = divSumBlock.get(10).select("dd"); 	// (10)연대보증수, 보증금액
			vo.setCnt_dt_guarantee(StringUtil.regExpFindNum(divSumLoan.get(0).text()));	//연대보증수
			vo.setAmt_guarantee(StringUtil.addAmt(divSumLoan.get(1).text()));			//보증금액
	
			divDtl = divPrdBlock.get(10).select("div[class=container-fluid]"); 	//(10) 연대보증상세
			
			//DT_DELETE 업데이트
			creditMapper.updateDtDeleteGuaranteeInfo(info.getNoPerson());
			
			for(int i=0; i < divDtl.size(); i++) {
	
				JSONObject kcbGuaranteeInfo = new JSONObject();
	
				if(divDtl.get(i).children().size() > 0) {
					String cdFc = "";
					kcbGuaranteeInfo.put("no_person", info.getNoPerson());
					String nmFc = "";
					for(Object obj : divDtl.get(i).select("span").get(0).childNodes()) {
						if(obj instanceof TextNode) {
							nmFc = ((TextNode)obj).text();
						}
					}
					kcbGuaranteeInfo.put("nm_fc", StringUtil.stringRtnNmFc(nmFc));	//금융사명
					cdFc = checkFcInfo(cdFcList, nmFcList, StringUtil.stringRtnNmFc(nmFc));
					kcbGuaranteeInfo.put("cd_fc", cdFc);
	
					Elements divPrdList = divDtl.get(i).select("div[class=prdlist-info] dd");
					kcbGuaranteeInfo.put("dt_guar_agree",	(StringUtil.regExpFindNum(divPrdList.get(0).text())));	//보증약정일자
					//kcbGuaranteeInfo.put("cd_guarantee",	(StringUtil.regExpFindNum(divPrdList.get(1).text())));	//보증종류
					//kcbGuaranteeInfo.put("amt_guar_limit",	(StringUtil.addAmt(divPrdList.get(2).text())));		//보증한도금액
					kcbGuaranteeInfo.put("amt_guar_object",	(StringUtil.addAmt(divPrdList.get(1).text())));			//보증대상금액
	
					kcbGuaranteeList.add(kcbGuaranteeInfo);
					
					KcbGuaranteeInfo guaranteeInfo = new KcbGuaranteeInfo();
					guaranteeInfo.setNo_person(info.getNoPerson());
					guaranteeInfo.setCd_fc(cdFc);
//					guaranteeInfo.setCd_guarantee(cd_guarantee);
					guaranteeInfo.setDt_guar_agree(StringUtil.regExpFindNum(divPrdList.get(0).text()));
//					guaranteeInfo.setAmt_guar_limit(amt_guar_limit);
					guaranteeInfo.setAmt_guar_object(StringUtil.addAmt(divPrdList.get(1).text()));
					
					creditMapper.saveKcbGuaranteeInfo(guaranteeInfo);
				}
			}
			vo.setList_guarantee(kcbGuaranteeList.toString());
	
			//크롤링 데이터 확인
			if(creditMapper.selectKcbCreditInfo(vo.getNo_person()) > 0) {
				creditMapper.updateKcbCreditInfo(vo);
			} else {
				creditMapper.insertKcbCreditInfo(vo);
			}
			
		} catch (Exception e) {
			
			Map<String, Object> logMap = new HashMap<String, Object>();
			logMap.put("no_person", vo.getNo_person());
			logMap.put("step", step);
			logMap.put("log", e.getMessage());
			logMap.put("log_html", info.getDoc_page1());
			
			creditMapper.insertCrawlingLog(logMap);
		}
	}

	public void parsePage2(KcbCreditInfoVO info) {
		
			CrawlingVO 	vo 	= new CrawlingVO();
			Document 	doc = Jsoup.parse(info.getDoc_page2());
			vo.setNo_person(info.getNoPerson());
	
			String     step = "START PARSEPAGE2";
			
			Elements div = doc.select("div[class=page-credit-info]");
			Elements tableList = div.select("table");
			/**
			 * tableList.get(0) 주소
			 * tableList.get(1) 직장
			 * tableList.get(2) 소득
			 * tableList.get(3) 연락처
			 */
		try{	
			//1. 주소정보 저장
			step = "[PAGE2_0] 주소정보";
			Elements addrTrList = tableList.get(0).select("tr");
			for(int i=1; i < addrTrList.size(); i++) {
				Elements addrTdList = addrTrList.get(i).select("td");
				
				String no_person = info.getNoPerson();									//회원관리번호
				String cd_addr = addrTdList.get(0).text();								//주소구분 - 자택:01 / 실거주지:02 / 직장:03
				if(cd_addr.indexOf("자택")>-1){
					cd_addr = "01";
				} else if(cd_addr.indexOf("실거주지")>-1) {
					cd_addr = "02";
				} else if(cd_addr.indexOf("직장")>-1) {
					cd_addr = "03";
				}
				String dt_regist = StringUtil.regExpFindNum(addrTdList.get(1).text());	//등록일자
				
				//dt_regist가 없는 경우가 있기 때문에 확인해준다
				if(dt_regist==null) {
					continue;
				} else if (dt_regist.equals("")){
					continue;
				}
				
				String post_home = StringUtil.regExpFindNum(addrTdList.get(2).text());	//우편번호
				String addr1_home = addrTdList.get(3).text();							//주소상
				String addr2_home = addrTdList.get(4).text();							//주소하
				String tel_home = StringUtil.regExpFindNum(addrTdList.get(5).text());	//전화번호
				String type_addr = addrTdList.get(6).text();							//주소구분
				
				KcbAddrInfo kcbAddrInfo = new KcbAddrInfo();
				kcbAddrInfo.setNo_person(no_person);
				kcbAddrInfo.setCd_addr(cd_addr);
				kcbAddrInfo.setDt_regist(dt_regist);
				kcbAddrInfo.setPost_home(post_home);
				kcbAddrInfo.setAddr1_home(addr1_home);
				kcbAddrInfo.setAddr2_home(addr2_home);
				kcbAddrInfo.setTel_home(tel_home);
				kcbAddrInfo.setType_addr(type_addr);
				
				creditMapper.saveKcbAddrInfo(kcbAddrInfo);
			}
			
			//2. 직장정보 저장
			step = "[PAGE2_1] 직장정보";
			Elements jobTrList = tableList.get(1).select("tr");
			for(int i=1; i < jobTrList.size(); i++) {
				Elements jobTdList = jobTrList.get(i).select("td");
				String no_person = info.getNoPerson();										//회원관리번호
				String nm_comp = jobTdList.get(0).text();									//직장명
				String ym_start_comp = StringUtil.regExpFindNum(jobTdList.get(3).text());	//입사년월
				String nm_dept = jobTdList.get(1).text();									//부서명
				String tel_dept = StringUtil.regExpFindNum(jobTdList.get(2).text());		//내선번호

				//직장명이나 부서명이 없는 경우가 있는데, 이런 경우는 건너 뛰기로 한다
				if(nm_comp==null||nm_dept==null) {
					continue;
				} else if(nm_comp.equals("")||nm_dept.equals("")) {
					continue;
				}
				
				KcbJobInfo kcbJobInfo = new KcbJobInfo();
				kcbJobInfo.setNo_person(no_person);
				kcbJobInfo.setNm_comp(nm_comp);
				kcbJobInfo.setYm_start_comp(ym_start_comp);
				kcbJobInfo.setNm_dept(nm_dept);
				kcbJobInfo.setTel_dept(tel_dept);
				
				//db에러 방지를 위해 우선 주석처리
				creditMapper.saveKcbJobInfo(kcbJobInfo);
			}
			
			//3. 연소득정보 update
			step = "[PAGE2_2] 연소득정보";
			Elements trList = tableList.get(2).select("tr"); //소득List
			for(int i=1; i < trList.size(); i++) {
				Elements tdList = trList.get(i).select("td");
	
				String ymd_year_income = tdList.get(0).text(); 						//연소득 등록 일자
				String amt_year_income = StringUtil.addAmt(tdList.get(1).text()); 	//연소득
	
				if(!StringUtil.isEmpty(ymd_year_income) && !StringUtil.isEmpty(amt_year_income)){
	
					PersonEtmIncomeInfo personEtmIncomeInfo = new PersonEtmIncomeInfo();
	
					personEtmIncomeInfo.setCd_etm_basic("30");		//10:건강보험, 20:국민연금, 30:KCB, 40:국세청
					personEtmIncomeInfo.setNo_person(info.getNoPerson());
					personEtmIncomeInfo.setAmt_etm_income(amt_year_income);
					personEtmIncomeInfo.setYmd_etm_basic(ymd_year_income);
					personEtmIncomeInfo.setStd_year(StringUtil.NVL(ymd_year_income, "0000").substring(0, 4));
					creditMapper.insertPersonEtmIncomeInfo(personEtmIncomeInfo);
				}
			}
			
			//4. 연락처 정보 저장
			step = "[PAGE2_3] 연락처정보";
			Elements contactTrList = tableList.get(3).select("tr");
			for(int i=1; i < contactTrList.size(); i++) {
				Elements contactTdList = contactTrList.get(i).select("td");
				
				String no_person = info.getNoPerson();										//회원관리번호
				String cd_info = contactTdList.get(0).text();								//정보구분 - 휴대폰(연락처?):01 / 이메일:02
				if(cd_info.indexOf("휴대폰")>-1 || cd_info.indexOf("연락처")>-1){
					cd_info = "01";
				} else if(cd_info.indexOf("이메일")>-1) {
					cd_info = "02";
				}
				//데이터가 안들어오는 경우도 있기 때문에 예외처리
				if(contactTdList.get(1).text()==null||contactTdList.get(2).text()==null) {
					continue;
				} else if(contactTdList.get(1).text().equals("")||contactTdList.get(2).text().equals("")){
					continue;
				}
				
				String dt_regist = StringUtil.regExpFindNum(contactTdList.get(1).text());	//등록일자
				//hp와 email은 같은 td를 사용하기 때문에 구분해줘야 하고, 이를 하단에서 사용하기 때문에 변수를 미리 선언
				String hp = null;
				String email = null;
				
				if(cd_info.equals("01")) {
					hp = StringUtil.regExpFindNum(contactTdList.get(2).text());				//휴대폰번호
				} else if(cd_info.equals("02")) {
					email = contactTdList.get(2).text();									//이메일주소
				}
				
				KcbContactInfo kcbContactInfo = new KcbContactInfo();
				kcbContactInfo.setNo_person(no_person);
				kcbContactInfo.setCd_info(cd_info);
				kcbContactInfo.setDt_regist(dt_regist);
				kcbContactInfo.setHp(hp);
				kcbContactInfo.setEmail(email);
				
				creditMapper.saveKcbContactInfo(kcbContactInfo);
				
				//PERSON_INFO에 이메일이 없을 경우 세팅해 주어야 한다
				PersonVO personVO = personMapper.getPersonInfo(no_person);
				if(personVO.getEmail() == null || personVO.getEmail().equals("")) {
				} else {
					personVO.setEmail(email);
					personMapper.modifyPersonEmail(personVO);
				}
			}
			
			//TODO 5. 거래형태, 자금용도
			
			//TODO 6. 집단대출대납구분, 연체대환대출여부, 신용회복지원여부, 거치기간
		} catch(Exception e) {
			Map<String, Object> logMap = new HashMap<String, Object>();
			logMap.put("no_person", vo.getNo_person());
			logMap.put("step", step);
			logMap.put("log", e.getMessage());
			logMap.put("log_html", info.getDoc_page2());
			
			creditMapper.insertCrawlingLog(logMap);
		}
	}

	public String checkFcInfo(List<String> cdFcList, List<String> nmFcList, String nmFc) {
		int 	idx 	= 0;
		String 	cdFc 	= "";
		
		nmFc = nmFc.trim();
		idx = nmFcList.indexOf(nmFc);
		if(idx > -1) {
			cdFc = cdFcList.get(idx);
		} else {
			cdFc = fcCodeMapper.selectCdFc(nmFc);
			if(StringUtil.isEmpty(cdFc)) {
				
				FincorpVO Fincorp = new FincorpVO();
				Fincorp.setCd_fin("E");
				Fincorp.setNo_biz_comp("0000000000");
				Fincorp.setNm_fc(nmFc);
				Fincorp.setYn_use("Y");
				Fincorp.setYn_alliance("N");
				Fincorp.setYn_scrap("N");
				Fincorp.setId_frt("SYS");
				Fincorp.setId_lst("SYS");
				
				fincorpMapper.createFincorp(Fincorp);
				cdFc = Fincorp.getCdFc();
				
				FincorpfcNminfoForm fincorpfcNminfoForm = new FincorpfcNminfoForm();
				fincorpfcNminfoForm.setCd_fc(cdFc);
				fincorpfcNminfoForm.setNm_nm_fc(nmFc);
				fincorpfcNminfoForm.setNm_yn_use("Y");
				fincorpfcNminfoForm.setId_frt("SYS");
				fincorpfcNminfoForm.setId_lst("SYS");
				fincorpMapper.createFincorpfcNminfo(fincorpfcNminfoForm);
			}
			nmFcList.add(nmFc); 
			cdFcList.add(cdFc);
		}
		
		return cdFc;
	}

	//20180629 김휘경 추가 - 카드 내역 상세에서 card_type을 지정해주고 처리하기 위한 함수
	public void checkCardDtlListAndSave(Map<String, List<String>> kcbCardInfoMap, Map<String, List<KcbCardDtlList>> kcbCardDtlListMap) {
		//우선 각 카드사별로 생각해보자 - 기준은 상세내역 Map을 기준으로 생각
		Iterator<String> iter = kcbCardDtlListMap.keySet().iterator();
		
		//iter.next()를 통해 가져오는 것은 가능하지만 kcbCardDtlListMap에서 cd_fc 를 삭제하자 오류가 발생했기 때문에 동일한 paramMap으로 재정의 한 후 paramMap을 통해 컨트롤
		Map<String, List<KcbCardDtlList>> paramMap = new HashMap<String, List<KcbCardDtlList>>();
		paramMap.putAll(kcbCardDtlListMap);
		
		while(iter.hasNext()) {
			String cd_fc = iter.next();
			//cd_fc에 맞는 카드 상세내역 - 어쩔 수 없는 List 두번
			List<KcbCardDtlList> dtlListList = paramMap.get(cd_fc);
			
			//카드 내역에 있는 데이터와 검증하기 위해 list로 가져옴
			List<String> typeList = kcbCardInfoMap.get(cd_fc);

			//데이터가 2개가 최대로 들어가야 하므로 일단 생성하고 시작
			KcbCardDtlList firstDtl = new KcbCardDtlList();
			KcbCardDtlList secondDtl = new KcbCardDtlList();
			
			//여기서 데이터 검증을 해준다. - dtlListList가 null일 수는 없는게 map에서 key를 빼와서 한거기 때문에 얘는 null이 들어갈 수 없고 typeList가 null인거만 체크
			if(typeList==null) {
				//카드가 내역은 없다. -> 상세내역에서 해당 삭제 
				paramMap.remove(cd_fc);
				continue;
			} //나머지 숫자가 다른 경우들은 하단에서 체크
			
			if(typeList.size()>2) {
				Map<String, Object> logMap = new HashMap<String, Object>();
				logMap.put("no_person", dtlListList.get(0).getNo_person());
				logMap.put("step", "[PAGE1_4] 카드개설정보");
				logMap.put("log", "동일한 카드사 중 보유 카드 갯수가 3개입니다. 데이터를 확인해주세요");
				logMap.put("log_html", "");
				
				paramMap.remove(cd_fc);
				creditMapper.insertCrawlingLog(logMap);
				
				continue;
			}
			
			switch(dtlListList.size()) {
			case 0:
				//아무것도 안들어왔으면 cd_fc는 있는데 내역이 없다는 것이기 때문에 데이터가 덜 들어온 것
				logger.debug("데이터가 너무 적게 들어왔습니다. 카드 내역 상세를 확인해주세요");
				break;
			case 1:
				//카드 내역 상세를 가져오고
				KcbCardDtlList cardDtlList = dtlListList.get(0);
				//내역 상세가 type이 00이라면 
				if(cardDtlList.getType_card().equals("00")){
					//상세가 00이고 type이 두개라 판단 근거가 없다면 삭제하고 넘어가기로
					if(typeList.size()==2) {
						paramMap.remove(cd_fc);
						break;
					}
					//type_card를 맞춰 준 다음
					cardDtlList.setType_card(typeList.get(0));
					//kcbCardDtlListMap에 담겨있던 list를 초기화 시켜준 다음
					dtlListList.clear();
					//type_card가 들어간 list를 새로 만들어줌
					dtlListList.add(cardDtlList);
					//결과적으로 type_card가 들어간 kcbCardDtlList하나가 담긴 list가 되었고 이 리스트를 map에 cd_fc를 key로 담아주면 기존에 있던 list가 대체됨 
					paramMap.put(cd_fc, dtlListList);
				} else if (cardDtlList.getType_card().equals("03")) { //데이터가 두가지로 들어오기 때문에 쪼개서 넣어야 한다.
					
					long amt_total = 0;
					amt_total = 
							Long.parseLong(cardDtlList.getAmt_lump_sum()) +
							Long.parseLong(cardDtlList.getAmt_installment()) +
							Long.parseLong(cardDtlList.getAmt_short_card_loan());
					
					KcbCardDtlList dtlCredit = new KcbCardDtlList();
					dtlCredit.setNo_person(cardDtlList.getNo_person());
					dtlCredit.setCd_fc(cardDtlList.getCd_fc());
					dtlCredit.setType_card("01");
					dtlCredit.setReq_yyyymm(cardDtlList.getReq_yyyymm());
					dtlCredit.setAmt_total(amt_total+"");
					dtlCredit.setAmt_lump_sum(cardDtlList.getAmt_lump_sum());
					dtlCredit.setAmt_installment(cardDtlList.getAmt_installment());
					dtlCredit.setAmt_short_card_loan(cardDtlList.getAmt_short_card_loan());
					dtlCredit.setAmt_check("0");
					dtlCredit.setAmt_delay(cardDtlList.getAmt_delay());
					
					KcbCardDtlList dtlCheck = new KcbCardDtlList();
					dtlCheck.setNo_person(cardDtlList.getNo_person());
					dtlCheck.setCd_fc(cardDtlList.getCd_fc());
					dtlCheck.setType_card("02");
					dtlCheck.setReq_yyyymm(cardDtlList.getReq_yyyymm());
					dtlCheck.setAmt_total(cardDtlList.getAmt_check());		//체크 총 합은 체크 사용량
					dtlCheck.setAmt_lump_sum("0");
					dtlCheck.setAmt_installment("0");
					dtlCheck.setAmt_short_card_loan("0");
					dtlCheck.setAmt_check(cardDtlList.getAmt_check());
					dtlCheck.setAmt_delay("0");
					
					firstDtl = dtlCredit;
					secondDtl = dtlCheck;
					
					//형식에 맞게 map에 담기
					dtlListList.clear();
					dtlListList.add(firstDtl);
					dtlListList.add(secondDtl);
					paramMap.put(cd_fc, dtlListList);
				} 
				
				break;
			case 2:
				firstDtl = dtlListList.get(0);
				secondDtl = dtlListList.get(1);
				
				boolean isFirstZero = firstDtl.getType_card().equals("00");
				boolean isSecondZero = secondDtl.getType_card().equals("00");
				
				boolean isFirstThird = firstDtl.getType_card().equals("03");
				boolean isSecondThird = secondDtl.getType_card().equals("03");
				
				//type_card가 동시에 01, 02가 들어올 수도 있어서 이를 확인해주는 변수
				boolean isSameType = false;
				
				//둘 다 00이 들어온 경우 - 00은 불명, 01은 신용, 02는 체크
				if(isFirstZero&&isSecondZero) {
					//둘 다 아무 데이터가 없으므로 그냥 01, 02 넣어주자
					firstDtl.setType_card("01");
					secondDtl.setType_card("02");
				} else if(!isFirstZero&&isSecondZero) { //두 번째만 00인 경우
					//그 중에서도 첫 번째 type_card가 03이라면 첫 번째 만 가지고 쪼개서 나눠넣는다 
					if(isFirstThird) {
						
						long amt_total = 0;
						amt_total = 
								Long.parseLong(firstDtl.getAmt_lump_sum()) +
								Long.parseLong(firstDtl.getAmt_installment()) +
								Long.parseLong(firstDtl.getAmt_short_card_loan());
						
						KcbCardDtlList dtlCredit = new KcbCardDtlList();
						dtlCredit.setNo_person(firstDtl.getNo_person());
						dtlCredit.setCd_fc(firstDtl.getCd_fc());
						dtlCredit.setType_card("01");
						dtlCredit.setReq_yyyymm(firstDtl.getReq_yyyymm());
						dtlCredit.setAmt_total(amt_total+"");
						dtlCredit.setAmt_lump_sum(firstDtl.getAmt_lump_sum());
						dtlCredit.setAmt_installment(firstDtl.getAmt_installment());
						dtlCredit.setAmt_short_card_loan(firstDtl.getAmt_short_card_loan());
						dtlCredit.setAmt_check("0");
						dtlCredit.setAmt_delay(firstDtl.getAmt_delay());
						
						KcbCardDtlList dtlCheck = new KcbCardDtlList();
						dtlCheck.setNo_person(firstDtl.getNo_person());
						dtlCheck.setCd_fc(firstDtl.getCd_fc());
						dtlCheck.setType_card("02");
						dtlCheck.setReq_yyyymm(firstDtl.getReq_yyyymm());
						dtlCheck.setAmt_total(firstDtl.getAmt_check());		//체크 총 합은 체크 사용량
						dtlCheck.setAmt_lump_sum("0");
						dtlCheck.setAmt_installment("0");
						dtlCheck.setAmt_short_card_loan("0");
						dtlCheck.setAmt_check(firstDtl.getAmt_check());
						dtlCheck.setAmt_delay("0");
						
						firstDtl = dtlCredit;
						secondDtl = dtlCheck;
					} else {
						//첫번째 type_car가 01이면 두 번째는 02가 되고, 02면 01이 된다
						String secondType = firstDtl.getType_card().equals("01")?"02":"01";
						secondDtl.setType_card(secondType);
					}
				} else if(isFirstZero&&!isSecondZero) { //첫 번째만 00인 경우
					//상동
					if(isSecondThird) {
						long amt_total = 0;
						amt_total = 
								Long.parseLong(secondDtl.getAmt_lump_sum()) +
								Long.parseLong(secondDtl.getAmt_installment()) +
								Long.parseLong(secondDtl.getAmt_short_card_loan());
						
						KcbCardDtlList dtlCredit = new KcbCardDtlList();
						dtlCredit.setNo_person(secondDtl.getNo_person());
						dtlCredit.setCd_fc(secondDtl.getCd_fc());
						dtlCredit.setType_card("01");
						dtlCredit.setReq_yyyymm(secondDtl.getReq_yyyymm());
						dtlCredit.setAmt_total(amt_total+"");
						dtlCredit.setAmt_lump_sum(secondDtl.getAmt_lump_sum());
						dtlCredit.setAmt_installment(secondDtl.getAmt_installment());
						dtlCredit.setAmt_short_card_loan(secondDtl.getAmt_short_card_loan());
						dtlCredit.setAmt_check("0");
						dtlCredit.setAmt_delay(secondDtl.getAmt_delay());
						
						KcbCardDtlList dtlCheck = new KcbCardDtlList();
						dtlCheck.setNo_person(secondDtl.getNo_person());
						dtlCheck.setCd_fc(secondDtl.getCd_fc());
						dtlCheck.setType_card("02");
						dtlCheck.setReq_yyyymm(secondDtl.getReq_yyyymm());
						dtlCheck.setAmt_total(secondDtl.getAmt_check());
						dtlCheck.setAmt_lump_sum("0");
						dtlCheck.setAmt_installment("0");
						dtlCheck.setAmt_short_card_loan("0");
						dtlCheck.setAmt_check(secondDtl.getAmt_check());
						dtlCheck.setAmt_delay("0");
						
						firstDtl = dtlCredit;
						secondDtl = dtlCheck;
					} else {
						//상동
						String firstType = secondDtl.getType_card().equals("01")?"02":"01";
						firstDtl.setType_card(firstType);
					}
				} else {
					//둘 다 00이 아닐 때 03이 들어오는 경우가 있을 것.
					if(isFirstThird&&isSecondThird) {
						//둘 다 03 이면 로직이 이미 어긋난 것 - 확인해야 한다
						logger.error("카드 내역에 type_card가 명확하지 않습니다. 카드 갯수:2개 , 타입:혼합+혼합");
					} else if(!isFirstThird&&isSecondThird) {
						//첫 번째는 01 혹은 02 이고 두 번째는 03일 경우
						if(firstDtl.getType_card().equals("01")) {
							//첫 번째가 01(신용)일 경우
							long amt_total = 0;
							long amt_lump_sum = 0;
							long amt_installment = 0;
							long amt_short_card_loan = 0;
							long amt_delay = 0;
							
							amt_lump_sum =
									Long.parseLong(firstDtl.getAmt_lump_sum())+
									Long.parseLong(secondDtl.getAmt_lump_sum());
							amt_installment =
									Long.parseLong(firstDtl.getAmt_installment())+
									Long.parseLong(secondDtl.getAmt_installment());
							amt_short_card_loan =
									Long.parseLong(firstDtl.getAmt_short_card_loan())+
									Long.parseLong(secondDtl.getAmt_short_card_loan());
							amt_delay =
									Long.parseLong(firstDtl.getAmt_delay())+
									Long.parseLong(secondDtl.getAmt_delay());
							amt_total =
									amt_lump_sum +
									amt_installment +
									amt_short_card_loan;
							
							KcbCardDtlList dtlCredit = new KcbCardDtlList();
							dtlCredit.setNo_person(secondDtl.getNo_person());
							dtlCredit.setCd_fc(secondDtl.getCd_fc());
							dtlCredit.setType_card("01");
							dtlCredit.setReq_yyyymm(secondDtl.getReq_yyyymm());
							dtlCredit.setAmt_total(amt_total+"");
							dtlCredit.setAmt_lump_sum(amt_lump_sum+"");
							dtlCredit.setAmt_installment(amt_installment+"");
							dtlCredit.setAmt_short_card_loan(amt_short_card_loan+"");
							dtlCredit.setAmt_check("0");
							dtlCredit.setAmt_delay(amt_delay+"");
							
							KcbCardDtlList dtlCheck = new KcbCardDtlList();
							dtlCheck.setNo_person(secondDtl.getNo_person());
							dtlCheck.setCd_fc(secondDtl.getCd_fc());
							dtlCheck.setType_card("02");
							dtlCheck.setReq_yyyymm(secondDtl.getReq_yyyymm());
							dtlCheck.setAmt_total(secondDtl.getAmt_check());	//total도 03인 secondDtl에서 체크 사용만 긁어와야 한다.
							dtlCheck.setAmt_lump_sum("0");
							dtlCheck.setAmt_installment("0");
							dtlCheck.setAmt_short_card_loan("0");
							dtlCheck.setAmt_check(secondDtl.getAmt_check());
							dtlCheck.setAmt_delay("0");
							
							firstDtl = dtlCredit;
							secondDtl = dtlCheck;
						} else{
							//첫 번째가 02(체크)일 경우
							long amt_total = 0;
							long amt_check = 0;
							
							amt_total = 
									Long.parseLong(secondDtl.getAmt_lump_sum())+
									Long.parseLong(secondDtl.getAmt_installment())+
									Long.parseLong(secondDtl.getAmt_short_card_loan());
									
							KcbCardDtlList dtlCredit = new KcbCardDtlList();
							dtlCredit.setNo_person(secondDtl.getNo_person());
							dtlCredit.setCd_fc(secondDtl.getCd_fc());
							dtlCredit.setType_card("01");
							dtlCredit.setReq_yyyymm(secondDtl.getReq_yyyymm());
							dtlCredit.setAmt_total(amt_total+"");
							dtlCredit.setAmt_lump_sum(secondDtl.getAmt_lump_sum());
							dtlCredit.setAmt_installment(secondDtl.getAmt_installment());
							dtlCredit.setAmt_short_card_loan(secondDtl.getAmt_short_card_loan());
							dtlCredit.setAmt_check("0");
							dtlCredit.setAmt_delay(secondDtl.getAmt_delay());

							amt_check =
									Long.parseLong(firstDtl.getAmt_check())+
									Long.parseLong(secondDtl.getAmt_check());
							
							KcbCardDtlList dtlCheck = new KcbCardDtlList();
							dtlCheck.setNo_person(secondDtl.getNo_person());
							dtlCheck.setCd_fc(secondDtl.getCd_fc());
							dtlCheck.setType_card("02");
							dtlCheck.setReq_yyyymm(secondDtl.getReq_yyyymm());
							dtlCheck.setAmt_total(amt_check+"");	//체크 카드의 사용내역은 체크로 쓴 액수의 합
							dtlCheck.setAmt_lump_sum("0");
							dtlCheck.setAmt_installment("0");
							dtlCheck.setAmt_short_card_loan("0");
							dtlCheck.setAmt_check(amt_check+"");
							dtlCheck.setAmt_delay("0");
							
							firstDtl = dtlCredit;
							secondDtl = dtlCheck;
						}
					} else if(isFirstThird&&!isSecondThird){
						//첫 번째는 03 이고 두 번째는 01 혹은 02일 경우
						if(secondDtl.getType_card().equals("01")) {
							//두 번째가 01(신용)일 경우
							long amt_total = 0;
							long amt_lump_sum = 0;
							long amt_installment = 0;
							long amt_short_card_loan = 0;
							long amt_delay = 0;
							
							amt_lump_sum =
									Long.parseLong(firstDtl.getAmt_lump_sum())+
									Long.parseLong(secondDtl.getAmt_lump_sum());
							amt_installment =
									Long.parseLong(firstDtl.getAmt_installment())+
									Long.parseLong(secondDtl.getAmt_installment());
							amt_short_card_loan =
									Long.parseLong(firstDtl.getAmt_short_card_loan())+
									Long.parseLong(secondDtl.getAmt_short_card_loan());
							amt_delay =
									Long.parseLong(firstDtl.getAmt_delay())+
									Long.parseLong(secondDtl.getAmt_delay());
							amt_total =
									amt_lump_sum +
									amt_installment +
									amt_short_card_loan;
							
							KcbCardDtlList dtlCredit = new KcbCardDtlList();
							dtlCredit.setNo_person(firstDtl.getNo_person());
							dtlCredit.setCd_fc(firstDtl.getCd_fc());
							dtlCredit.setType_card("01");
							dtlCredit.setReq_yyyymm(firstDtl.getReq_yyyymm());
							dtlCredit.setAmt_total(amt_total+"");
							dtlCredit.setAmt_lump_sum(amt_lump_sum+"");
							dtlCredit.setAmt_installment(amt_installment+"");
							dtlCredit.setAmt_short_card_loan(amt_short_card_loan+"");
							dtlCredit.setAmt_check("0");
							dtlCredit.setAmt_delay(amt_delay+"");
							
							KcbCardDtlList dtlCheck = new KcbCardDtlList();
							dtlCheck.setNo_person(firstDtl.getNo_person());
							dtlCheck.setCd_fc(firstDtl.getCd_fc());
							dtlCheck.setType_card("02");
							dtlCheck.setReq_yyyymm(firstDtl.getReq_yyyymm());
							dtlCheck.setAmt_total(firstDtl.getAmt_check());		//위와 마찬가지로 체크 금액이 총 금액
							dtlCheck.setAmt_lump_sum("0");
							dtlCheck.setAmt_installment("0");
							dtlCheck.setAmt_short_card_loan("0");
							dtlCheck.setAmt_check(firstDtl.getAmt_check());
							dtlCheck.setAmt_delay("0");
							
							firstDtl = dtlCredit;
							secondDtl = dtlCheck;
							
						} else{
							//두 번째가 02 일 때
							long amt_total = 0;
							long amt_check = 0;
							
							amt_total =
									Long.parseLong(firstDtl.getAmt_lump_sum())+
									Long.parseLong(firstDtl.getAmt_installment())+
									Long.parseLong(firstDtl.getAmt_short_card_loan());
							
							KcbCardDtlList dtlCredit = new KcbCardDtlList();
							dtlCredit.setNo_person(firstDtl.getNo_person());
							dtlCredit.setCd_fc(firstDtl.getCd_fc());
							dtlCredit.setType_card("01");
							dtlCredit.setReq_yyyymm(firstDtl.getReq_yyyymm());
							dtlCredit.setAmt_total(amt_total+"");
							dtlCredit.setAmt_lump_sum(firstDtl.getAmt_lump_sum());
							dtlCredit.setAmt_installment(firstDtl.getAmt_installment());
							dtlCredit.setAmt_short_card_loan(firstDtl.getAmt_short_card_loan());
							dtlCredit.setAmt_check("0");
							dtlCredit.setAmt_delay(firstDtl.getAmt_delay());
							
							amt_check =
									Long.parseLong(firstDtl.getAmt_check())+
									Long.parseLong(secondDtl.getAmt_check());
							
							KcbCardDtlList dtlCheck = new KcbCardDtlList();
							dtlCheck.setNo_person(firstDtl.getNo_person());
							dtlCheck.setCd_fc(firstDtl.getCd_fc());
							dtlCheck.setType_card("02");
							dtlCheck.setReq_yyyymm(firstDtl.getReq_yyyymm());
							dtlCheck.setAmt_total(amt_check+"");		//위와 마찬가지로 체크카드의 총 사용액은 체크로 사용한 총액
							dtlCheck.setAmt_lump_sum("0");
							dtlCheck.setAmt_installment("0");
							dtlCheck.setAmt_short_card_loan("0");
							dtlCheck.setAmt_check(amt_check+"");
							dtlCheck.setAmt_delay("0");
							
							firstDtl = dtlCredit;
							secondDtl = dtlCheck;
						}
					} else if(!isFirstThird&&!isSecondThird) {
						//둘다 03이 아니라면 잘 해결 되겠지요
					}
				}
				if(!isSameType){
					//case 1과 같이 map에 담기
					dtlListList.clear();
					dtlListList.add(firstDtl);
					dtlListList.add(secondDtl);
					paramMap.put(cd_fc, dtlListList);
				} else {
					//같은 타입일 경우 하나만 담아주면 된다.
					dtlListList.clear();
					dtlListList.add(firstDtl);
					paramMap.put(cd_fc, dtlListList);					
				}
				break;
			case 3:
				//경우의 수를 줄이기 위해 sort실행
				Collections.sort(dtlListList, new CustomCardDtlComparator());
				KcbCardDtlList dtlOne = dtlListList.get(0);
				KcbCardDtlList dtlTwo = dtlListList.get(1);
				KcbCardDtlList dtlThree = dtlListList.get(2);
				
				//혹시몰라 초기화
				firstDtl = new KcbCardDtlList();
				secondDtl = new KcbCardDtlList();
				
				//경우의 수는 00,00,00 / 00,00,01 / 00,00,02 / 00,00,03 / 00,01,02 / 00,01,03 / 00,02,03 / 01,02,03 8가지
				if(dtlOne.getType_card().equals("00")&&dtlTwo.getType_card().equals("00")&&dtlThree.getType_card().equals("00")){
					//셋다 00이면 첫 번째거는 없애고 두개 그냥 집어넣기만 하면 됨
					firstDtl = dtlTwo;
					secondDtl = dtlThree;
					
				} else if(dtlOne.getType_card().equals("00")&&dtlTwo.getType_card().equals("00")&&dtlThree.getType_card().equals("01")){
					//하나만 01이면 첫 번째꺼 없애고 두 번째꺼 type_card만 02로 주면 됨
					dtlTwo.setType_card("02");
					firstDtl = dtlTwo;
					secondDtl = dtlThree;
					
				} else if(dtlOne.getType_card().equals("00")&&dtlTwo.getType_card().equals("00")&&dtlThree.getType_card().equals("02")){
					//하나만 02이면  없애고 두 번째꺼 type_card만 01로 주면 됨
					dtlTwo.setType_card("01");
					firstDtl = dtlTwo;
					secondDtl = dtlThree;
					
				} else if(dtlOne.getType_card().equals("00")&&dtlTwo.getType_card().equals("00")&&dtlThree.getType_card().equals("03")){
					long amt_total = 0;
					amt_total =
							Long.parseLong(dtlThree.getAmt_lump_sum()) +
							Long.parseLong(dtlThree.getAmt_installment()) +
							Long.parseLong(dtlThree.getAmt_short_card_loan());
					
					//하나만 03이면 쪼개서 집어넣어야함
					KcbCardDtlList dtlCredit = new KcbCardDtlList();
					dtlCredit.setNo_person(dtlThree.getNo_person());
					dtlCredit.setCd_fc(dtlThree.getCd_fc());
					dtlCredit.setType_card("01");
					dtlCredit.setReq_yyyymm(dtlThree.getReq_yyyymm());
					dtlCredit.setAmt_total(amt_total+"");
					dtlCredit.setAmt_lump_sum(dtlThree.getAmt_lump_sum());
					dtlCredit.setAmt_installment(dtlThree.getAmt_installment());
					dtlCredit.setAmt_short_card_loan(dtlThree.getAmt_short_card_loan());
					dtlCredit.setAmt_check("0");
					dtlCredit.setAmt_delay(secondDtl.getAmt_delay());
					
					KcbCardDtlList dtlCheck = new KcbCardDtlList();
					dtlCheck.setNo_person(dtlThree.getNo_person());
					dtlCheck.setCd_fc(dtlThree.getCd_fc());
					dtlCheck.setType_card("02");
					dtlCheck.setReq_yyyymm(dtlThree.getReq_yyyymm());
					dtlCheck.setAmt_total(dtlThree.getAmt_check());
					dtlCheck.setAmt_lump_sum("0");
					dtlCheck.setAmt_installment("0");
					dtlCheck.setAmt_short_card_loan("0");
					dtlCheck.setAmt_check(secondDtl.getAmt_check());
					dtlCheck.setAmt_delay("0");
					
					firstDtl = dtlCredit;
					secondDtl = dtlCheck;
				} else if(dtlOne.getType_card().equals("00")&&dtlTwo.getType_card().equals("01")&&dtlThree.getType_card().equals("02")){
					//2개는 차례대로 저장
					firstDtl = dtlTwo;
					secondDtl = dtlThree;
					
				} else if(dtlOne.getType_card().equals("00")&&dtlTwo.getType_card().equals("01")&&dtlThree.getType_card().equals("03")){
					long amt_total = 0;
					long amt_lump_sum = 0;
					long amt_installment = 0;
					long amt_short_card_loan = 0;
					long amt_delay = 0;
					
					amt_lump_sum =
							Long.parseLong(dtlTwo.getAmt_lump_sum())+
							Long.parseLong(dtlThree.getAmt_lump_sum());
					amt_installment =
							Long.parseLong(dtlTwo.getAmt_installment())+
							Long.parseLong(dtlThree.getAmt_installment());
					amt_short_card_loan =
							Long.parseLong(dtlTwo.getAmt_short_card_loan())+
							Long.parseLong(dtlThree.getAmt_short_card_loan());
					amt_delay =
							Long.parseLong(dtlTwo.getAmt_delay())+
							Long.parseLong(dtlThree.getAmt_delay());
					
					amt_total = 
							amt_lump_sum +
							amt_installment +
							amt_short_card_loan;
					
					KcbCardDtlList dtlCredit = new KcbCardDtlList();
					dtlCredit.setNo_person(dtlThree.getNo_person());
					dtlCredit.setCd_fc(dtlThree.getCd_fc());
					dtlCredit.setType_card("01");
					dtlCredit.setReq_yyyymm(dtlThree.getReq_yyyymm());
					dtlCredit.setAmt_total(amt_total+"");
					dtlCredit.setAmt_lump_sum(amt_lump_sum+"");
					dtlCredit.setAmt_installment(amt_installment+"");
					dtlCredit.setAmt_short_card_loan(amt_short_card_loan+"");
					dtlCredit.setAmt_check("0");
					dtlCredit.setAmt_delay(amt_delay+"");
					
					KcbCardDtlList dtlCheck = new KcbCardDtlList();
					dtlCheck.setNo_person(dtlThree.getNo_person());
					dtlCheck.setCd_fc(dtlThree.getCd_fc());
					dtlCheck.setType_card("02");
					dtlCheck.setReq_yyyymm(dtlThree.getReq_yyyymm());
					dtlCheck.setAmt_total(dtlThree.getAmt_check());
					dtlCheck.setAmt_lump_sum("0");
					dtlCheck.setAmt_installment("0");
					dtlCheck.setAmt_short_card_loan("0");
					dtlCheck.setAmt_check(dtlThree.getAmt_check());
					dtlCheck.setAmt_delay("0");
					
					firstDtl = dtlCredit;
					secondDtl = dtlCheck;
				} else if(dtlOne.getType_card().equals("00")&&dtlTwo.getType_card().equals("02")&&dtlThree.getType_card().equals("03")){
					
					long amt_total = 0;
					long amt_check = 0;
					
					amt_total =
							Long.parseLong(dtlThree.getAmt_lump_sum()) +
							Long.parseLong(dtlThree.getAmt_installment()) +
							Long.parseLong(dtlThree.getAmt_short_card_loan());
					
					KcbCardDtlList dtlCredit = new KcbCardDtlList();
					dtlCredit.setNo_person(dtlThree.getNo_person());
					dtlCredit.setCd_fc(dtlThree.getCd_fc());
					dtlCredit.setType_card("01");
					dtlCredit.setReq_yyyymm(dtlThree.getReq_yyyymm());
					dtlCredit.setAmt_total(amt_total+"");
					dtlCredit.setAmt_lump_sum(dtlThree.getAmt_lump_sum());
					dtlCredit.setAmt_installment(dtlThree.getAmt_installment());
					dtlCredit.setAmt_short_card_loan(dtlThree.getAmt_short_card_loan());
					dtlCredit.setAmt_check("0");
					dtlCredit.setAmt_delay(dtlThree.getAmt_delay());
					
					amt_check =
							Long.parseLong(dtlTwo.getAmt_total())+
							Long.parseLong(dtlThree.getAmt_total());
					
					KcbCardDtlList dtlCheck = new KcbCardDtlList();
					dtlCheck.setNo_person(dtlThree.getNo_person());
					dtlCheck.setCd_fc(dtlThree.getCd_fc());
					dtlCheck.setType_card("02");
					dtlCheck.setReq_yyyymm(dtlThree.getReq_yyyymm());
					dtlCheck.setAmt_total(amt_check+"");
					dtlCheck.setAmt_lump_sum("0");
					dtlCheck.setAmt_installment("0");
					dtlCheck.setAmt_short_card_loan("0");
					dtlCheck.setAmt_check(amt_check+"");
					dtlCheck.setAmt_delay("0");
					
					firstDtl = dtlCredit;
					secondDtl = dtlCheck;
				} else if(dtlOne.getType_card().equals("01")&&dtlTwo.getType_card().equals("02")&&dtlThree.getType_card().equals("03")){
					long amt_total = 0;
					long amt_lump_sum = 0;
					long amt_installment = 0;
					long amt_short_card_loan = 0;
					long amt_check = 0;
					long amt_delay = 0;
					
					amt_lump_sum =
							Long.parseLong(dtlOne.getAmt_lump_sum())+
							Long.parseLong(dtlThree.getAmt_lump_sum());
					amt_installment =
							Long.parseLong(dtlOne.getAmt_installment())+
							Long.parseLong(dtlThree.getAmt_installment());
					amt_short_card_loan =
							Long.parseLong(dtlOne.getAmt_short_card_loan())+
							Long.parseLong(dtlThree.getAmt_short_card_loan());
					amt_delay =
							Long.parseLong(dtlOne.getAmt_delay())+
							Long.parseLong(dtlThree.getAmt_delay());
					amt_total =
							amt_lump_sum +
							amt_installment +
							amt_short_card_loan;
					
					KcbCardDtlList dtlCredit = new KcbCardDtlList();
					dtlCredit.setNo_person(dtlThree.getNo_person());
					dtlCredit.setCd_fc(dtlThree.getCd_fc());
					dtlCredit.setType_card("01");
					dtlCredit.setReq_yyyymm(dtlThree.getReq_yyyymm());
					dtlCredit.setAmt_total(amt_total+"");
					dtlCredit.setAmt_lump_sum(amt_lump_sum+"");
					dtlCredit.setAmt_installment(amt_installment+"");
					dtlCredit.setAmt_short_card_loan(amt_short_card_loan+"");
					dtlCredit.setAmt_check("0");
					dtlCredit.setAmt_delay(amt_delay+"");
					
					amt_check =
							Long.parseLong(dtlTwo.getAmt_total())+
							Long.parseLong(dtlThree.getAmt_total());
					
					KcbCardDtlList dtlCheck = new KcbCardDtlList();
					dtlCheck.setNo_person(dtlThree.getNo_person());
					dtlCheck.setCd_fc(dtlThree.getCd_fc());
					dtlCheck.setType_card("02");
					dtlCheck.setReq_yyyymm(dtlThree.getReq_yyyymm());
					dtlCheck.setAmt_total(amt_check+"");
					dtlCheck.setAmt_lump_sum("0");
					dtlCheck.setAmt_installment("0");
					dtlCheck.setAmt_short_card_loan("0");
					dtlCheck.setAmt_check(amt_check+"");
					dtlCheck.setAmt_delay("0");
					
					firstDtl = dtlCredit;
					secondDtl = dtlCheck;
					
				} else {
					logger.error("KCB 카드 정보를 분류 할 수 없습니다. line:2267");
				}
				//case 1과 같이 map에 담기
				dtlListList.clear();
				dtlListList.add(firstDtl);
				dtlListList.add(secondDtl);
				paramMap.put(cd_fc, dtlListList);
				break;
			default:
				logger.debug("데이터가 너무 많이 들어왔습니다. 카드 내역 상세를 확인해주세요");
				break;
			}
		}
		//while문이 끝났다면 데이터 정제가 다 되어서 type_card가 00인 부분이 없어야 한다.
		saveKcbCarDtlList(paramMap);
	}
	
	//map을 통해 카드 내역 상세를 한번에 저장하는 메소드
	public void saveKcbCarDtlList(Map<String, List<KcbCardDtlList>> kcbCardDtlListMap){
		Iterator<String> iter = kcbCardDtlListMap.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			List<KcbCardDtlList> list = kcbCardDtlListMap.get(key);
			for(KcbCardDtlList kcbCardDtlList:list) {
				creditMapper.saveKcbCardDtlList(kcbCardDtlList);
			}
		}
	}
	
	/*
	 * KCB PARSING END 
	 */
	
	@Override
	public ReturnClass createKcbReqNonfiInfo(KcbReqNonfiInfoVO kcbReqNonfiInfoVO) {

		scrapMapper.createKcbReqNonfiInfo(kcbReqNonfiInfoVO);
		return new ReturnClass(Constant.SUCCESS, "KCB 비금융정보 요청내역 저장 완료", "");
	}
	
	@Override
	public List<KcbReqNonfiInfoVO> getKcbReqNonfiInfo(KcbReqNonfiInfoVO kcbReqNonfiInfoVO) {
		return scrapMapper.getKcbReqNonfiInfo(kcbReqNonfiInfoVO);
	}
	
	@Override
	public int updateKcbReqNonfiInfo(KcbReqNonfiInfoVO kcbReqNonfiInfoVO) {
		return scrapMapper.updateKcbReqNonfiInfo(kcbReqNonfiInfoVO);
	}
}

//type_card에 따라 sort할 수 있도록 Comparator 추가
class CustomCardDtlComparator implements Comparator<KcbCardDtlList>{
	@Override
	public int compare(KcbCardDtlList firstDtl, KcbCardDtlList secondDtl) {
		if(Integer.parseInt(firstDtl.getType_card())>Integer.parseInt(secondDtl.getType_card())) {
			return 1;
		} else if(Integer.parseInt(firstDtl.getType_card())<Integer.parseInt(secondDtl.getType_card())) {
			return -1;
		} else {
			return 0;
		}
	}
}
