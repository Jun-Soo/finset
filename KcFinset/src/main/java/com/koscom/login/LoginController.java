package com.koscom.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.login.service.SecureManager;
import com.koscom.person.model.PersonForm;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Constant;
import com.koscom.util.DateUtil;
import com.koscom.util.FinsetException;
import com.koscom.util.LogUtil;
import com.koscom.util.NumberUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;
import com.koscom.util.SkipLoginCheck;
import com.koscom.util.StringUtil;

@Controller
@RequestMapping("/m/login")
@PropertySource("classpath:prop/webservice.properties")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	PersonManager personManager;
	
//	@Autowired
//	ConditioncreditManager conditioncreditManager;
//	
//	@Autowired
//	ConditionbizManager conditionbizManager;
//	
//	@Autowired
//	ConditionhouseManager conditionhouseManager;
//	
//	@Autowired
//	private KcbManager kcbManager;
//	
//	@Autowired
//	private DebtManager debtManager;
	
	@Autowired
	private SecureManager secureManager;
	
//	@Autowired
//	private CreditManager creditManager;
	
	@Autowired
	CodeManager codeManager;
	
	@Resource
	Environment environment;
	
	/** APP
	 * app 버전정보 조회
	 * @param response
	 * @param request
	 * @param session
	 * @param PersonVO 
	 * @param model
	 * @return
	 */
	@RequestMapping("/appVersion.json")
	public String appVersion(
			HttpServletResponse response, 
			HttpServletRequest request, 
			HttpSession session,
			PersonVO personVO,
			Model model) throws Exception {
		
		CodeInfo codeInfo = new CodeInfo();
		
		if(!"1".equals(personVO.getYn_os())) {
			codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "IOS_VERSION");
		} else {
			codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "ANDROID_VERSION");
		}
		
		model.addAttribute("codeInfo", codeInfo);
		
		return "jsonView";
	}
	@RequestMapping("/appVersionCheck.json")
	public String appVersionCheck(
			HttpServletResponse response, 
			HttpServletRequest request, 
			HttpSession session,
			Model model) throws Exception {
		
		String userAgent = request.getParameter("user_agent");
		String appVersion = request.getParameter("app_version");
		
		CodeInfo codeInfo = new CodeInfo();
		
		if("iOS".equals(userAgent)) {
			codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "IOS_VERSION");
			if(appVersion.length() < 4) appVersion = appVersion + ".0";
		} else {
			codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "ANDROID_VERSION");
		}
		
		String[] new_version = codeInfo.getNm_code().split("\\.");
		String[] app_version = appVersion.split("\\.");
		
		try {
			if(app_version.length == new_version.length) {
				for(int i=0; i<new_version.length; i++) {
					if(Integer.valueOf(new_version[i]) > Integer.valueOf(app_version[i]) && "1.1.1".equals(codeInfo.getNm_code())) {
						model.addAttribute("result", "update");
						break;
					} else {
						model.addAttribute("result", "pass");
					}
				}
			}
		} catch (Exception e) {
			logger.error("new_version ::: " + new_version);
			logger.error("app_version ::: " + app_version);
			LogUtil.error(logger, e);
		}
		
		
		return "jsonView";
	}
	
	/** VUE
     * 본인인증 인증번호 요청
     * @param request
     * @param response
     * @param session
     * @param model
     * @param personVO
     * @return
     */
    @SkipLoginCheck
    @RequestMapping("/kcmRequestCertNo.json")
    public String kcmRequestCertNo(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model, PersonVO personVO) throws Exception {

        if (personVO != null) {
        	
            logger.info("본인인증 인증번호 요청 이름 : " + personVO.toString());
            logger.info("본인인증 인증번호 요청 이름 : " + personVO.getNm_person());

            if(personVO.getSex().equals("2")){
                personVO.setSex("0");
            }

            // 요청파라미터
            String name 	= personVO.getNm_person();                       // 성명
            String birthday = personVO.getBirthday();                        // 생년월일
            String sex 		= personVO.getSex();                             // 성별
            String nation 	= personVO.getNation();                          // 내외국인구분
            String telComCd = personVO.getTelComCd();                        // 통신사코드
            String mbphnNo 	= personVO.getHp();                              // 휴대폰번호
            String rqstCausCd = "00";                                        // 인증요청사유코드 (00:회원가입, 01:성인인증, 02:회원정보수정, 03:비밀번호찾기, 04:상품구매, 99:기타)
            String smsReSndYn = StringUtil.isEmpty(personVO.getSmsReSndYn()) ? "N" : personVO.getSmsReSndYn();

            // 거래고유번호. 동일문자열을 두번 사용할 수 없음.
            String svcTxSeqno;

            if(personVO != null && "Y".equals(personVO.getSmsReSndYn())) {
                svcTxSeqno = personVO.getSvcTxSeqno();
            } else {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                svcTxSeqno = df.format(cal.getTime());
            }
            
            logger.info("유효성검증 start");
            boolean isValid = true;
            // 유효성검증
            if (name != null && !name.matches("^[가-힝a-zA-Z ]*")) {
                logger.info("성명에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "성명에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            } // EUC-KR인 경우
            if (birthday != null && !birthday.matches("^[0-9x]*")) {
                logger.info("생년월일에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "생년월일에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;

            }
            if (sex != null && !sex.matches("^[01x]")) {
                logger.info("성별에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "성별에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            if (nation != null && !nation.matches("^[12x]")) {
                logger.info("내외국인구분에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "내외국인구분에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            if (telComCd!= null && !telComCd.matches("^[0-9x]*")) {
                logger.info("통신사코드에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "통신사코드에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            if (mbphnNo != null && !mbphnNo.matches("^[0-9]*")) {
                logger.info("휴대폰번호에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "휴대폰번호에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            if (rqstCausCd != null && !rqstCausCd.matches("^[0-9x]*")) {
                logger.info("인증요청사유코드에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "인증요청사유코드에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            if (smsReSndYn != null && !smsReSndYn.matches("^[YN]")) {
                logger.info("재전송여부에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "재전송여부에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            if (svcTxSeqno !=null && !svcTxSeqno.matches("^[0-9a-zA-Z]*")) {
                logger.info("거래고유번호에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("message", "거래고유번호에 유효하지 않은 문자열이 있습니다.");
                model.addAttribute("result", Constant.FAILED);
                isValid = false;
            }
            
            if(isValid == true) {
            	
            	logger.info("본인인증 start");
                // ########################################################################
                // # KCB로부터 부여받은 회원사코드(아이디) 설정 (12자리)
                // ########################################################################
                String memId = "P18760000000";                                      // 회원사코드(아이디)

                // ########################################################################
                // # 회원사 모듈설치서버 IP 및 회원사 도메인 설정
                // ########################################################################
                String serverIp = "x";                  // 모듈이 설치된 서버IP (서버IP검증을 무시하려면 'x'로 설정)
                String siteUrl = environment.getProperty("okName.siteUrl"); // 회원사 사이트 URL
                LogUtil.debugLn(logger,"본인인증 siteUrl="+siteUrl);
                String siteDomain = "finset";       // 회원사 도메인명, SMS인증번호문자에 표시됨 **

                // 예비
                String rsv1= "0";
                String rsv2= "0";

                String rqstMsrCd= "10";                             // 요청수단코드 (10:핸드폰)

                // ########################################################################
                // # 운영전환시 변경 필요
                // ########################################################################
                String endPointUrl  = environment.getProperty("okName.endPointUrl");
                LogUtil.debugLn(logger,"본인인증 endPointUrl="+endPointUrl);

                // ########################################################################
                // # 로그 경로 지정 및 권한 부여 (절대경로)
                // ########################################################################
                String logPath = "/okname/log";     // 로그경로. 로그파일을 만들지 않더라도 경로는 지정하도록 한다.

                // ########################################################################
                // # 옵션값에 'L'을 추가하는 경우에만 로그가 생성됨.
                // # 시스템(환경변수 LANG설정)이 UTF-8인 경우 'U'옵션 추가 ex)$option='JLU'
                // ########################################################################
                String options = "JLU";     // L:파일로그생성


                String[] cmd = new String[19];
                cmd[0]=svcTxSeqno;      // 거래일련번호
                cmd[1]=name;            // 성명
                cmd[2]=birthday;        // 생년월일
                cmd[3]=sex;             // 성별
                cmd[4]=nation;          // 내외국인구분
                cmd[5]=telComCd;        // 통신사코드
                cmd[6]=mbphnNo;         // 휴대폰번호
                cmd[7]=smsReSndYn;      // SMS재전송여부
                cmd[8]=rsv1;            // 예약
                cmd[9]=rsv2;            // 예약
                cmd[10]=rqstMsrCd;      // 요청수단코드
                cmd[11]=rqstCausCd;     // 요청사유코드
                cmd[12]=memId;          // 회원사코드
                cmd[13]=serverIp;       // 회원사 서버 IP
                cmd[14]=siteUrl;        // 회원사 사이트 URL
                cmd[15]=siteDomain;     // 회원사 사이트 도메인
                cmd[16]=endPointUrl;    // KCB 서비스 주소
                cmd[17]=logPath;        // 로그경로
                cmd[18]=options;        // 옵션

                /**************************************************************************
                okname 실행
                **************************************************************************/
                List result = new ArrayList();  // 인증결과
                int ret = -999;         // 프로세스 리턴값
                kcb.jni.Okname okname = null;
                
                String site = (environment != null) ? environment.getProperty("service.profile") : "";
                if(!"LOCAL".equals(site)) {
                    okname = new kcb.jni.Okname();
                    ret = okname.exec(cmd, result);
                } else {
                    ret = 0;
                    svcTxSeqno = "15031654";
                }
                
                logger.info(">>>> 인증 결과 코드  ret : " + ret);
                if (ret == 0) {//성공일 경우 변수를 결과에서 얻음
                    model.addAttribute("message", "인증 번호가 전송 되었습니다.");
                    model.addAttribute("result", Constant.SUCCESS);
                    model.addAttribute("svcTxSeqno", svcTxSeqno);
                } else {
                    model.addAttribute("message", "입력정보가 잘못되었습니다.");
                    model.addAttribute("result", Constant.FAILED);
                }
            }
        }

        return "jsonView";
    }
    /** VUE
     * 본인인증 확인
     * @param request
     * @param session
     * @param personVO
     * @param model
     * @return
     * @throws Exception
     */
    @SkipLoginCheck
    @RequestMapping("/kcmCertify.json")
    public String kcmCertify(
    		HttpServletRequest request,
    		HttpSession session,
    		PersonVO personVO,
    		Model model) throws Exception{


        logger.info("본인인증 인증번호 확인 요청: " + personVO.toString());

        // 요청파라미터
        String svcTxSeqno = personVO.getSvcTxSeqno();   // 거래고유번호
        String mbphnNo = personVO.getHp();              // 휴대폰번호
        String smsCertNo = personVO.getSmsCertNo();     // SMS인증번호
        boolean valid = true;
        // 파라미터에 대한 유효성 검증
        if (svcTxSeqno != null && !svcTxSeqno.matches("^[0-9a-zA-Z]+$")) {
            logger.info("거래고유번호에 유효하지 않은 문자열이 있습니다.");
            model.addAttribute("message", "거래고유번호에 유효하지 않은 문자열이 있습니다.");
            model.addAttribute("result", Constant.FAILED);
            valid = false;
        }
        if (mbphnNo != null && !mbphnNo.matches("^[0-9]+$")) {
            logger.info("휴대폰번호에 유효하지 않은 문자열이 있습니다.");
            model.addAttribute("message", "휴대폰번호에 유효하지 않은 문자열이 있습니다.");
            model.addAttribute("result", Constant.FAILED);
            valid = false;
        }
        if (smsCertNo != null && !smsCertNo.matches("^[0-9]+$")) {
            logger.info("SMS인증번호에 유효하지 않은 문자열이 있습니다.");
            model.addAttribute("message", "SMS인증번호에 유효하지 않은 문자열이 있습니다.");
            model.addAttribute("result", Constant.FAILED);
            valid = false;
        }
        if(valid == true) {
            // ########################################################################
            // # KCB로부터 부여받은 회원사코드(아이디) 설정 (12자리)
            // ########################################################################
            String memId = "P18760000000";                                      // 회원사코드(아이디)

            // ########################################################################
            // # 운영전환시 확인 필요
            // ########################################################################
            String endPointUrl  = environment.getProperty("okName.endPointUrl");

            // ########################################################################
            // # 회원사 모듈설치서버 IP 설정
            // ########################################################################
            String serverIp = "x";                  // 모듈이 설치된 서버IP (서버IP검증을 무시하려면 'x'로 설정)

            // ########################################################################
            // # 로그 경로 지정 및 권한 부여 (절대경로)
            // ########################################################################
            String logPath = "/okname/log";     // 로그경로. 로그파일을 만들지 않더라도 경로는 지정하도록 한다.

            // ########################################################################
            // # 옵션값에 'L'을 추가하는 경우에만 로그가 생성됨.
            // # 시스템(환경변수 LANG설정)이 UTF-8인 경우 'U'옵션 추가 ex)$option='MLU'
            // ########################################################################
            String options = "MLU";     // L:파일로그생성

            String[] cmd = new String[8];
            cmd[0]=svcTxSeqno;
            cmd[1]=mbphnNo;
            cmd[2]=smsCertNo;
            cmd[3]=memId;
            cmd[4]=serverIp;
            cmd[5]=endPointUrl;
            cmd[6]=logPath;
            cmd[7]=options;

            /**************************************************************************
            okname 실행
            **************************************************************************/
            List result = new ArrayList();  // 인증결과
            int ret = -999;         // 프로세스 리턴값
            kcb.jni.Okname okname = null;
            String site = (environment != null)?environment.getProperty("service.profile"):"";
            LogUtil.debugLn(logger,"site="+site);
            if(!"LOCAL".equals(site)) {
                okname = new kcb.jni.Okname();
                ret = okname.exec(cmd, result);
            } else {
                ret = 0;
                result.add("1");
                result.add("1");
                result.add("kcb_cp");
                result.add("1");
                result.add("kcb_di");
                result.add("kcb_ci");
            }
            logger.info(">>>> 본인인증 인증번호 확인  ret : " + ret);
            if (ret == 0) {//성공일 경우 변수를 결과에서 얻음
                model.addAttribute("result", Constant.SUCCESS);
                int x = 0;
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                for (Object object : result) {
                    logger.debug("" + x++ + ":" +  object.toString());
                }
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                logger.debug("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX ");
                model.addAttribute("kcb_di", result.get(4));
                model.addAttribute("kcb_ci", result.get(5));
                model.addAttribute("kcb_cp", result.get(2));

                session.setAttribute("cert_result_value", Constant.SUCCESS);
            } else {
                model.addAttribute("message", "인증번호가 잘못되었습니다.");
                model.addAttribute("result", Constant.FAILED);
            }
        }
        return "jsonView";
    }
	

	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 로그아웃 업데이트
	 * @param model
	 * @param request
	 * @param fcmVO
	 * @return
	 */
	@RequestMapping("/framePersonLogout.crz")
	public String framePersonLogout(
			HttpServletRequest request,
			HttpSession session, 
			PersonVO personVO,
			Model model) {
		
		String no_person = (String) session.getAttribute("no_person");
		
		if(!StringUtil.isEmpty(no_person)) {
			
			personVO = personManager.getPersonInfo(no_person);
			
			logger.info("no_person : "+no_person);
			personVO.setNo_person(no_person);
			personVO.setYn_logout("Y");
			ReturnClass returnClass = personManager.modifyPersonLogout((PersonVO)SessionUtil.setUser(personVO, session));
			
			logger.info("cd_result : {},  message : {}", returnClass.getCd_result(), returnClass.getMessage());
			model.addAttribute("result" , returnClass.getCd_result());
			model.addAttribute("message" , returnClass.getMessage());
			model.addAttribute("hp" , personVO.getHp());
			
			session.invalidate();
		} else {
			
			Cookie[] cookies = request.getCookies();
			String hp = SessionUtil.getCookieValue(cookies, "hp");
			model.addAttribute("hp" , hp);
			
			logger.info("cookies : ", hp);
			
		}
		
		return "jsonView";
	}
	
	/**
	 * 로그아웃 업데이트
	 * @param model
	 * @param request
	 * @param fcmVO
	 * @return
	 * TODO 사용여부 확인 필요
	 */
	@RequestMapping("/modifyYnUseAndLogout.json")
	public String modifyYnUseAndLogout(
			HttpServletRequest request,
			HttpSession session, 
			PersonVO personVO,
			Model model) {
		
		String no_person = (String) session.getAttribute("no_person");
		logger.info("no_person : "+no_person);
		personVO.setNo_person(no_person);
		ReturnClass returnClass = personManager.modifyYnUseAndLogout((PersonVO)SessionUtil.setUser(personVO, session));
		
		logger.info("cd_result : {},  message : {}", returnClass.getCd_result(), returnClass.getMessage());
		model.addAttribute("result" , returnClass.getCd_result());
		model.addAttribute("message" , returnClass.getMessage());
		
		return "jsonView";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 최초 비밀번호 입력 화면
	 * @param response
	 * @param request
	 * @param model
	 * @param session
	 * @param PersonForm
	 * @return
	 */
	@RequestMapping("/frameSecurityCodeConfirm.crz")
	public String frameSecurityCodeConfirm(
			HttpServletResponse response, 
			HttpServletRequest request, 
			HttpSession session, 
			PersonForm personForm,
			Model model) {
		
		String denied = request.getParameter("denied");
		
		if(!StringUtil.isEmpty(denied)){
			String msg = "";
			int cd_result = NumberUtil.stringToInt(denied);
			//로그인 에러 코드에 따라 메시지 출력
			switch (cd_result) {
			case 21: 
				msg = "등록되지 않은 사용자 입니다.";
				break;
			case 22:
				msg = "비밀번호를 잘못 입력하였습니다.";
				break;
			case 91:
				msg = "시스템에 접근이 <strong>허용되지 않은 IP 주소</strong>입니다.";
				break;
			case 92:
				msg = "다른 사용자가 로그인을 시도하였습니다.";
				break;
			case 100:
				msg = "아이디를 입력해주세요.";
				break;
			case 101:
				msg = "패스워드를 입력해주세요.";
				break;
			}
			model.addAttribute("msg", msg);
			return "/login/frameSecurityCodeConfirm";
		}
		
		String rtnUrl 	= "";
		String noPerson = (String)session.getAttribute("no_person");
		String hp = (String)session.getAttribute("hp");

		PersonVO personVO = new PersonVO();

		logger.debug("접속 IP			: " + request.getRemoteAddr());
		logger.debug("세션 NO_PERSON 	: " + noPerson);
		logger.debug("받은 핸드폰 번호 	: " + personForm.getHp());

		
		model.addAttribute("no_person", noPerson);
		
		//전화번호값이 넘어오면 session 초기화 처리
		if(!StringUtil.isEmpty(personForm.getHp())) {
			noPerson = "";
			session.setAttribute("expiredTime", "");
			session.setAttribute("rememberMe",  "");
			session.setAttribute("no_person", 	"");
			session.setAttribute("yn_reload",   "");
		}
		
		//session에 전화번호와 URL정보가 담겨있을 경우
		if(!StringUtil.isEmpty(hp)) {
			personForm.setHp(session.getAttribute("hp").toString());
		}
		
		if(StringUtil.isEmpty(noPerson)) {
			
			// 1.전화번호 조회
			personVO = personManager.getPersonInfoHp(personForm.getHp());
			
			if(personVO != null) {
				
				model.addAttribute("no_token", personVO.getPass_person());
				
				logger.debug("personVO2.toString() === " + personVO.toString());
				logger.debug("========= 세션 없는데 번호 있을경우 =======");
				
				setAutoLoginWithCookies(session, response, personVO.getNo_person());
				
				//사용여부 N:회원가입, Y:로그인화면
				if(personVO.getYn_use().equals("N")) {
					rtnUrl = "/login/frameCertStep1";
				} else {
					
					if(StringUtil.isEmpty(personVO.getPass_person())) {
						session.setAttribute("cert_result_value", Constant.SUCCESS);
						rtnUrl = "/base/frameSecurityCode";
					} else if(Integer.parseInt(StringUtil.NVL(personVO.getCnt_fail_pwd(), "0")) > 4) { //비밀번호 실패건수
						model.addAttribute("personHp", personVO.getHp());
						rtnUrl = "/person/frameFindPwdStep1";
					} else if("Y".equals(personVO.getYn_fingerprint()) && Integer.parseInt(StringUtil.NVL(personVO.getCnt_fail_finger(), "0")) < 5) {
						rtnUrl = "/login/frameFingerConfirm";
					}else {
						rtnUrl = "/login/frameSecurityCodeConfirm";
					}
				}
				
				//지문 활성화 일 경우 체크 Y일때만 지문 활성화 N or 빈값 일 경우 비활성화
				model.addAttribute("yn_fingerprint", 	personVO.getYn_fingerprint());
				model.addAttribute("cd_push", 			personVO.getCd_push());
				model.addAttribute("yn_push", 			personVO.getYn_push());
				model.addAttribute("cnt_fail_pwd", 		personVO.getCnt_fail_pwd());
				model.addAttribute("cnt_fail_finger", 	personVO.getCnt_fail_finger());
				
				logger.debug(request.getHeader("user-agent"));
				logger.debug(personVO.toString());
				logger.debug(request.getRemoteAddr());

				model.addAttribute("securityResult", "Y");

				//App Version Check
				CodeInfo codeInfo = new CodeInfo();
				if(!"1".equals(StringUtil.NVL(personVO.getYn_os(), "1"))) {
					codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "IOS_VERSION");
				} else {
					codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "ANDROID_VERSION");
				}
				model.addAttribute("app_version", 	codeInfo.getNm_code());	
				
			} else {
				rtnUrl = "/login/frameServiceIntro";
			}
			
		} else {
			
			personVO = personManager.getPersonInfo(noPerson);
			
			model.addAttribute("no_token", personVO.getPass_person());
			
			//지문 활성화 일 경우 체크 Y일때만 지문 활성화 N or 빈값 일 경우 비활성화
			model.addAttribute("yn_fingerprint", 	personVO.getYn_fingerprint());
			model.addAttribute("cd_push", 			personVO.getCd_push());
			model.addAttribute("yn_push", 			personVO.getYn_push());
			model.addAttribute("cnt_fail_pwd", 		personVO.getCnt_fail_pwd());
			model.addAttribute("cnt_fail_finger", 	personVO.getCnt_fail_finger());
			
			logger.debug(request.getHeader("user-agent"));
			logger.debug(request.getRemoteAddr());

			model.addAttribute("securityResult", "Y");
			
			//사용여부 N:회원가입, Y:로그인화면
			if(personVO.getYn_use().equals("N")) {
				rtnUrl = "/login/frameCertStep1";
			} else {
				
				if(StringUtil.isEmpty(personVO.getPass_person())) {
					session.setAttribute("cert_result_value", Constant.SUCCESS);
					rtnUrl = "/base/frameSecurityCode";
				} else if(Integer.parseInt(StringUtil.NVL(personVO.getCnt_fail_pwd(), "0")) > 4) { //비밀번호 실패건수
					model.addAttribute("personHp", personVO.getHp());
					rtnUrl = "/person/frameFindPwdStep1";
				} else if("Y".equals(personVO.getYn_fingerprint()) && Integer.parseInt(StringUtil.NVL(personVO.getCnt_fail_finger(), "0")) < 5) {
					rtnUrl = "/login/frameFingerConfirm";
				} else {
					rtnUrl = "/login/frameSecurityCodeConfirm";
				}
			}
		}

		return rtnUrl;
	}
	
	@RequestMapping("/getYnfingerInfo.json")
	public String getYnfingerInfo(
			HttpSession session, 
			Model model) throws Exception {
		
		String noPerson = "";
		noPerson = (String)session.getAttribute("no_person");
		
		if(StringUtil.isEmpty(noPerson)) {
			model.addAttribute("yn_session", "N");
			model.addAttribute("yn_fingerprint", "");
		} else {
			PersonVO person = personManager.getPersonInfo(noPerson);
			model.addAttribute("yn_session", "Y");
			model.addAttribute("yn_fingerprint", person.getYn_fingerprint());
		}
		
		return "jsonView";
	}
	
	/**
	 * 비밀번호 입력 화면
	 * @param request
	 * @param model
	 * @param session
	 * @param personForm
	 * @return
	 */
	@RequestMapping("/frameCodeConfirm.crz")
	public String frameCodeConfirm(
			HttpServletRequest request,
			HttpSession session,
			PersonForm personForm, 
			Model model) throws FinsetException {
		
		String noPerson = (String)session.getAttribute("no_person");
		
		PersonVO personVO = new PersonVO();
		personVO = personManager.getPersonInfo(noPerson);
		logger.debug("personVO.toString()"+personVO.toString());
		
		model.addAttribute("no_person", noPerson);
		model.addAttribute("no_token", personVO.getPass_person());
		model.addAttribute("yn_fingerprint", personVO.getYn_fingerprint());
		model.addAttribute("cd_push", personVO.getCd_push());
		model.addAttribute("yn_push", personVO.getYn_push());
		model.addAttribute("cnt_fail_pwd", personVO.getCnt_fail_pwd());
		model.addAttribute("cnt_fail_finger", personVO.getCnt_fail_finger());
		
		if(!StringUtil.isEmpty(personForm.getYn_reload())) {
			session.setAttribute("yn_reload", personForm.getYn_reload());
		}
		model.addAttribute("yn_reload", (String)session.getAttribute("yn_reload"));
		model.addAttribute("securityResult", "Y");
			
		logger.debug(request.getHeader("user-agent"));
		logger.debug(request.getRemoteAddr());
		
		return "/login/frameSecurityCodeConfirm";
	}
	
	/**
	 * 지문 인증화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameFingerConfirm.crz")
	public String frameFingerConfirm(
			HttpSession session,
			PersonForm personForm,
			Model model) throws FinsetException {
		
		String noPerson = (String) session.getAttribute("no_person");
		
		PersonVO personVO = new PersonVO();
		personVO = personManager.getPersonInfo(noPerson);
		
		model.addAttribute("cnt_fail_finger", personVO.getCnt_fail_finger());
		model.addAttribute("no_token", personVO.getPass_person());
		if(!StringUtil.isEmpty(personForm.getYn_reload())) {
			session.setAttribute("yn_reload", personForm.getYn_reload());
		}
		model.addAttribute("yn_reload", (String)session.getAttribute("yn_reload"));
		
		return "/login/frameFingerConfirm";
	}
	
	/**
	 * 로그인 후 Kcb 크롤링 호출용 화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameKcbCrawling.crz")
	public String frameKcbCrawling(
			HttpServletRequest request,
			Model model) {
		
		model.addAttribute("linkUrl", (String)request.getAttribute("linkUrl"));
		return "/login/frameKcbCrawling";
	}
	
	/**
	 * Kcb 크롤링 호출
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws FinsetException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/getKcbCrawling.json")
	public String getKcbCrawling(
			HttpServletRequest request, 
			HttpSession session, 
			Model model) throws Exception {
		
		String noPerson = (String) session.getAttribute("no_person");
		String profile  = environment.getProperty("service.profile");
		
		logger.debug(":::::::::::::::::::::::::::::::::::: KCB START ::::::::::::::::::::::::::::::::::::");
		PersonVO 			person 	= personManager.getPersonInfo(noPerson);
		//KcbCreditInfoVO 	info 	= new KcbCreditInfoVO();

        boolean isSuccess = false;

        if("LOCAL".equals(profile)) {
			
			logger.debug("continue");
			model.addAttribute("cd_result", Constant.SUCCESS);
			
		} else {
			
//			try {
//
//				//600420 크롤링 시작
//				logger.debug(":::::::::::::::::::::::::::::::::::: KCB CRAW START ::::::::::::::::::::::::::::::::::::");
//				info.setNoPerson(person.getNo_person());
//				info.setNmCust(person.getNm_person());
//				info.setNmIf("600420");
//				info.setCd_regist("09");	//01 신규, 09 URL
//				info.setBgn(person.getBgn());
//				info.setDi(person.getKcb_di());
//				info.setHp(person.getHp());
//				
//				ReturnClass returnClass = kcbManager.urlCrawling(info);
//				
//				logger.debug(":::::::::::::::::::::::::::::::::::: KCB CRAW END ::::::::::::::::::::::::::::::::::::");
//				
//				//lca parsing
//				returnClass = kcbManager.parseCrawling(info);
//				if(Constant.SUCCESS.equals(returnClass.getCd_result())) {
//					
//					//TODO call Package
//					/*** 부채TABLE DATA생성 proc call ***/
//					debtManager.debtPdocRun(person.getNo_person());
//	                isSuccess = true;
//				}
//				
//				model.addAttribute("cd_result", returnClass.getCd_result());
//                
//			} catch (FinsetException e) {
//                isSuccess = false;
//                LogUtil.error(logger, e);
//                throw e;
//			} catch (IOException e) {
//                isSuccess = false;
//                LogUtil.error(logger, e);
//                throw e;
//			} finally {
//                if(isSuccess == false) {
//                    //error 발생시 당일 전문 데이터 DELETE
//                    kcbManager.deleteKcbCb(person.getNo_person());
//                    model.addAttribute("cd_result", Constant.FAILED);
//                }
//			}
		}
			
		logger.debug(":::::::::::::::::::::::::::::::::::: KCB END ::::::::::::::::::::::::::::::::::::");
		
		return "jsonView";
	}
	
	/**
	 * 본인인증화면 (약관)
	 * @param model 
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameCertStep1.crz")
	public String frameCertStep1() {
		return "/login/frameCertStep1";
	}
	
	/**
	 * 본인인증화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameCertStep2.crz")
	public String frameCertStep2(
			PersonVO personVO,
			Model model) {
		
		model.addAttribute("yn_eventPush", personVO.getYn_eventPush()); //이벤트푸시 수신여부
		return "/login/frameCertStep2";
	}
	
	//쿠키, 세션 값 맺는 메소드
	private void setAutoLoginWithCookies(HttpSession session, HttpServletResponse response, String noPerson) {
		
		String currentDateTime = DateUtil.getCurrentDateTime(DateUtil.DATE_HMS_PATTERN);
		String expiredTime =  DateUtil.addHours(currentDateTime, 720);

		/*
		Cookie _rememberMe = new Cookie("rememberMe", "Y");
		_rememberMe.setMaxAge(30*24*60*60);
		_rememberMe.setPath("/");
		response.addCookie(_rememberMe);

		Cookie _noPerson = new Cookie("noPerson", noPerson);
		_noPerson.setMaxAge(30*24*60*60);
		_noPerson.setPath("/");
		response.addCookie(_noPerson);

		Cookie _expiredTime = new Cookie("expiredTime", expiredTime);
		_expiredTime.setMaxAge(30*24*60*60);
		_expiredTime.setPath("/");
		response.addCookie(_expiredTime);
		*/
		
		session.setAttribute("expiredTime", expiredTime);
		session.setAttribute("rememberMe", "Y");
		session.setAttribute("no_person", noPerson);
	}
	
	/**
	 * 인증서 비밀번호 복호화
	 * : App에서 호출하나, 보안문제로 Deprecated
	 * @param encPwd
	 * @return
	 */
	@Deprecated
	@RequestMapping("/getDecodedPassword.crz")
	@ResponseBody
	public ReturnClass getDecodedPassword(@RequestParam("encPwd") String encPwd) {
		
		ReturnClass returnClass = new ReturnClass();

		returnClass.setCd_result(Constant.TRANS_STATUS_92); //정적테스트 처리
		
		String decPwd = secureManager.getDecodedPassword(encPwd);
		
		if ( encPwd.equals( decPwd ) ) {
			returnClass.setCd_result( Constant.FAILED );
			returnClass.setMessage("복호화에 실패하였습니다.");
		} else {
			returnClass.setCd_result( Constant.SUCCESS );
			returnClass.setMessage(decPwd);
		}
		return returnClass;
	}
}
