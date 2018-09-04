package com.koscom.customercenter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koscom.scrap.service.ScrapManager;
import com.koscom.util.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.koscom.credit.service.CreditManager;
import com.koscom.debt.model.DebtForm;
import com.koscom.debt.model.DebtVO;
import com.koscom.debt.service.DebtManager;
import com.koscom.domain.CreditInfo;
import com.koscom.domain.PersonShareInfo;
import com.koscom.domain.PersonShareMessageInfo;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.person.model.PersonShareInfoForm;
import com.koscom.person.model.PersonShareInfoVO;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;

@Controller
@RequestMapping("/m/customercenter")
public class CustomerCenterController{

	private static final Logger logger = LoggerFactory.getLogger(CustomerCenterController.class);

	@Autowired
	PersonManager personManager;

	@Autowired
	CreditManager creditManager;

	@Autowired
	DebtManager debtManager;

	/**
	 * 마이페이지 메인
	 * @param request
	 * @param session
	 * @param model
	 * @return String
	 */
	@RequestMapping("/frameCustomerCenterMain.crz")
	public String frameCustomerCenterMain(HttpServletRequest request, HttpSession session, Model model) throws FinsetException, IOException {
		String no_person = (String) session.getAttribute("no_person");
		PersonVO personVO = personManager.getPersonInfo(no_person);
		model.addAttribute("personVO", personVO);
		logger.info("personVO====MAIN======" + personVO.toString());
		return "/customercenter/frameCustomerCenterMain";
	}

	//공유관리
	/**
	 * 공유관리 summary list
	 * @param request
	 * @param session
	 * @param model
	 * @return String
	 */
	@RequestMapping("/frameShareInfoSummary.crz")
	public String frameShareInfoSummary(HttpServletRequest request, HttpSession session, Model model) throws FinsetException, IOException {
		String no_person = (String)session.getAttribute("no_person");
		PersonShareInfoForm personShareInfoForm = new PersonShareInfoForm();
		personShareInfoForm.setNo_person(no_person);
		personShareInfoForm.setType_list("offer"); //타인정보열람list
		List<PersonShareInfoVO> offerList = personManager.listPersonShareInfoSummary(personShareInfoForm);
		personShareInfoForm.setType_list("req"); //내정보공유list
		List<PersonShareInfoVO> reqList = personManager.listPersonShareInfoSummary(personShareInfoForm);

		model.addAttribute("offerList", offerList);
		model.addAttribute("reqList", reqList);
		model.addAttribute("currentDate",DateUtil.getCurrentDate());

		logger.info("공유관리 summary list");
		return "/customercenter/frameShareInfoSummary";
	}

	/**
	 * 공유관리 신규요청
	 * @param request
	 * @param session
	 * @param model
	 * @return String
	 */
	@RequestMapping("/frameShareInfoNewRequest.crz")
	public String frameShareInfoNewRequest(HttpServletRequest request, HttpSession session, Model model) throws FinsetException, IOException {

		logger.info("공유관리 신규요청");
		return "/customercenter/frameShareInfoNewRequest";
	}

	/**
	 * 공유관리 등록
	 * @param request
	 * @param session
	 * @param model
	 * @param personShareInfo
	 * @return String
	 */
	@RequestMapping("/createPersonShareInfo.json")
	public String createPersonShareInfo(HttpServletRequest request, HttpSession session, Model model, PersonShareInfo personShareInfo) throws FinsetException, IOException {
		String req_no_person = (String) session.getAttribute("no_person");

		PersonVO reqPersonVO = personManager.getPersonInfo(req_no_person);

		personShareInfo.setReq_no_person(req_no_person);
		personShareInfo.setReq_nm_person(reqPersonVO.getNm_person());
		personShareInfo.setReq_hp(reqPersonVO.getHp());
		personShareInfo.setId_frt(req_no_person);
		personShareInfo.setId_lst(req_no_person);

		//전화번호로 회원정보조회
		PersonVO offerPersonVO = personManager.getPersonInfoHp(personShareInfo.getOffer_hp());
		if(offerPersonVO != null) {
			personShareInfo.setOffer_no_person(offerPersonVO.getNo_person());
			model.addAttribute("typeMessage", "push");
		}else {
			model.addAttribute("typeMessage", "sms");
		}

		ReturnClass rtnClass = personManager.createPersonShareInfo(personShareInfo);

		if("00".equals(rtnClass.getCd_result())) {
			model.addAttribute("seq_share", (String)rtnClass.getReturnObj());

		}

		model.addAttribute("cdResult", rtnClass.getCd_result());
		model.addAttribute("message", rtnClass.getMessage());
		model.addAttribute("req_nm_person", reqPersonVO.getNm_person());

		return "jsonView";
	}

	/**
	 * 마이페이지 공유관리 메인
	 * @param request
	 * @param session
	 * @param model
	 * @param personShareInfoForm
	 * @return String
	 */
	@RequestMapping("/frameShareInfoMain.crz")
	public String frameShareInfoMain(HttpServletRequest request, HttpSession session, Model model, PersonShareInfoForm personShareInfoForm) throws FinsetException, IOException {
		String no_person = (String)session.getAttribute("no_person");
		personShareInfoForm.setNo_person(no_person);

		if(StringUtils.isEmpty(personShareInfoForm.getType_list())) {
			personShareInfoForm.setType_list("offer");
		}
		personShareInfoForm.setPage(1);

		Pagination pagedList = (Pagination) personShareInfoForm.setPagedList(personManager.listPersonShareInfoMain(personShareInfoForm), personManager.listPersonShareInfoMainCount(personShareInfoForm));
		model.addAttribute("pagedList", pagedList);

		model.addAttribute("currentDate",DateUtil.getCurrentDate());
		model.addAttribute("type_list", personShareInfoForm.getType_list());

		logger.info("공유관리 main list");
		return "/customercenter/frameShareInfoMain";
	}

	/**
	 * 마이페이지 공유관리 list조회
	 * @param request
	 * @param session
	 * @param model
	 * @param personShareInfoForm
	 * @return String
	 */
	@RequestMapping("/listShareInfoMain.crz")
	public String listShareInfoMain(HttpServletRequest request, HttpSession session, Model model, PersonShareInfoForm personShareInfoForm) throws FinsetException, IOException {
		String no_person = (String)session.getAttribute("no_person");
		personShareInfoForm.setNo_person(no_person);

		Pagination pagedList = (Pagination) personShareInfoForm.setPagedList(personManager.listPersonShareInfoMain(personShareInfoForm), personManager.listPersonShareInfoMainCount(personShareInfoForm));
		model.addAttribute("pagedList", pagedList);

		model.addAttribute("currentDate",DateUtil.getCurrentDate());
		model.addAttribute("type_list", personShareInfoForm.getType_list());

		return "/customercenter/sub/listShareInfoMain";
	}

	/**
	 * 공유관리 - 모두해지하기
	 * @param request
	 * @param session
	 * @param model
	 * @return String
	 */
	@RequestMapping("/updateShareInfoAllCancel.json")
	public String updateShareInfoAllCancel(HttpServletRequest request, HttpSession session, Model model) throws FinsetException, IOException {
		String no_person = (String)session.getAttribute("no_person");
		PersonShareInfoVO personShareInfoVO = new PersonShareInfoVO();
		personShareInfoVO.setOffer_no_person(no_person);
		List<PersonShareInfoVO> cancelList = personManager.listShareInfoAllCancel(personShareInfoVO);
		for(PersonShareInfoVO cancelItem : cancelList) {
			 cancelItem.setShare_status("04"); //해지
			 cancelItem.setId_frt(no_person);
			 cancelItem.setId_lst(no_person);
			 ReturnClass updateRtnClass = personManager.updatePersonShareInfoSet03(cancelItem);

			 //푸시발송
			 if("00".equals(updateRtnClass.getCd_result())) {

			     String title = "[공유관리]";
			     String body = "";
			     String url = "";
			     String fcm_token = "";

			     body = cancelItem.getOffer_nm_person()+"님이 공유를 해지하였습니다.";

			     PersonVO recPersonVO = personManager.getPersonInfo(cancelItem.getReq_no_person());

			     if (recPersonVO != null) {
			         fcm_token = recPersonVO.getFcm_token();
			         if (fcm_token != null && !fcm_token.equals("")) {
			        	 logger.debug("@@@@SendTo())"+fcm_token);

			             if(FcmUtil.sendFcm(  fcm_token
			                     , title
			                     , body
			                     , url
			                     , StringUtil.nullToString(recPersonVO.getYn_os(), "1")
			                     , StringUtil.nullToString(recPersonVO.getCd_push(), ""))){
			             }
			         }
			     }
			 }
		}

		return "jsonView";
	}

	/**
	 * 공유관리 - 공유설정(setting_mode 01-공유재요청. 02- 허용/거절, 03- 변경/해지)
	 * @param request
	 * @param session
	 * @param model
	 * @param personShareInfoVO
	 * @return String
	 */
	@RequestMapping("/frameShareInfoSetting.crz")
	public String frameShareInfoSetting(HttpServletRequest request, HttpSession session, Model model, PersonShareInfoVO personShareInfoVO) throws FinsetException, IOException {
		PersonShareInfo shareInfo = personManager.getPersonShareInfo(personShareInfoVO);

		model.addAttribute("shareInfo", shareInfo);
		model.addAttribute("setting_mode", personShareInfoVO.getSetting_mode());
		logger.info("공유관리 공유설정");

		return "/customercenter/frameShareInfoSetting";

	}

	/**
	 * 공유관리 - 공유설정(공유재요청)
	 * @param request
	 * @param session
	 * @param model
	 * @param personShareInfo
	 * @return String
	 */
	@RequestMapping("/updatePersonShareInfoSet01.json")
	public String updatePersonShareInfoSet01(HttpServletRequest request, HttpSession session, Model model, PersonShareInfo personShareInfo) throws FinsetException, IOException {
		String no_person = (String) session.getAttribute("no_person");

		//공유재요청 기간설정(하루)
		PersonShareMessageInfo personShareMessageInfo = new PersonShareMessageInfo();
		personShareMessageInfo.setSeq_share(personShareInfo.getSeq_share());
		personShareMessageInfo.setCd_message("01");

		if(1 == personManager.chkPersonShareInfoMessageTerm(personShareMessageInfo)) { //하루가 지나지 않은 경우
			model.addAttribute("chkMessageTerm","N");
		}else { //하루가 지난 경우

			personShareInfo.setId_frt(no_person);
			personShareInfo.setId_lst(no_person);

			//전화번호로 회원정보조회
			PersonVO offerPersonVO = personManager.getPersonInfoHp(personShareInfo.getOffer_hp());
			if(offerPersonVO != null) { //푸시
				personShareInfo.setOffer_no_person(offerPersonVO.getNo_person());
				model.addAttribute("typeMessage", "push");
			}else { //메세지
				model.addAttribute("typeMessage", "sms");
			}

			ReturnClass rtnClass = personManager.updatePersonShareInfoSet01(personShareInfo);
			model.addAttribute("cdResult", rtnClass.getCd_result());
			model.addAttribute("message", rtnClass.getMessage());

			model.addAttribute("chkMessageTerm","Y");
		}

		return "jsonView";
	}

	/**
	 * 공유관리 - 공유설정(허용/거절)
	 * @param request
	 * @param session
	 * @param model
	 * @param personShareInfo
	 * @return String
	 */
	@RequestMapping("/updatePersonShareInfoSet02.json")
	public String updatePersonShareInfoSet02(HttpServletRequest request, HttpSession session, Model model, PersonShareInfo personShareInfo) throws FinsetException, IOException {
		String no_person = (String) session.getAttribute("no_person");

		personShareInfo.setId_frt(no_person);
		personShareInfo.setId_lst(no_person);
		ReturnClass rtnClass = personManager.updatePersonShareInfoSet02(personShareInfo);

		model.addAttribute("cdResult", rtnClass.getCd_result());
		model.addAttribute("message", rtnClass.getMessage());

		return "jsonView";
	}

	/**
	 * 공유관리 - 공유설정(변경/해지)
	 * @param request
	 * @param session
	 * @param model
	 * @param personShareInfo
	 * @return String
	 */
	@RequestMapping("/updatePersonShareInfoSet03.json")
	public String updatePersonShareInfoSet03(HttpServletRequest request, HttpSession session, Model model, PersonShareInfo personShareInfo) throws FinsetException, IOException {
		String no_person = (String) session.getAttribute("no_person");

		personShareInfo.setId_frt(no_person);
		personShareInfo.setId_lst(no_person);
		ReturnClass rtnClass = personManager.updatePersonShareInfoSet03(personShareInfo);

		model.addAttribute("cdResult", rtnClass.getCd_result());
		model.addAttribute("message", rtnClass.getMessage());

		return "jsonView";
	}

	/**
	 * 공유관리 - sms 정보입력
	 * @param request
	 * @param session
	 * @param model
	 * @param personShareInfoVO
	 * @return String
	 */
	@RequestMapping("/createPersonShareInfoSms.json")
	public String sendPersonShareInfoSms(HttpServletRequest request, HttpSession session, Model model, PersonShareInfoVO personShareInfoVO) throws FinsetException, IOException {
		String no_person = (String) session.getAttribute("no_person");

		String setting_mode = personShareInfoVO.getSetting_mode();

        String body = "";

        PersonShareInfoVO personShareInfo = personManager.getPersonShareInfo(personShareInfoVO);

        PersonShareMessageInfo personShareMessageInfo = new PersonShareMessageInfo();

        personShareMessageInfo.setSeq_share(personShareInfo.getSeq_share());
        personShareMessageInfo.setType_message("01"); //sms

        personShareMessageInfo.setId_lst(no_person); //최종수정아이디

        //요청메세지
        body = personShareInfo.getReq_nm_person()+"님으로부터 공유요청이 왔습니다. \n";
        body += "아래의 링크를 선택하여 FINSET을 \n";
        body += "설치하시기 바랍니다. \n";
        body += "https://play.google.com/store/apps/details?id=com.app.kc.koscom";
        personShareMessageInfo.setReq_message(body);

        //sms 정보셋팅
        if("00".equals(setting_mode)) { //공유 요청
        	personShareMessageInfo.setCd_message("01"); //공유요청
        	personShareMessageInfo.setReq_status("01"); //요청

        	personShareMessageInfo.setId_frt(no_person); //최초입력아이디

        }else if("01".equals(setting_mode)) { //공유재요청
        	personShareMessageInfo.setCd_message("01"); //공유요청
        	personShareMessageInfo.setReq_status("02"); //재요청

        }

        ReturnClass rtnClass = personManager.mergePersonShareInfoMessage(personShareMessageInfo);
        model.addAttribute("cdResult",rtnClass.getCd_result());


        return "jsonView";
	}

	/**
	 * 공유관리 - push전송
	 * @param request
	 * @param session
	 * @param model
	 * @param personShareInfoVO
	 * @return String
	 */
	@RequestMapping("/sendPersonShareInfoPush.json")
	public String sendPersonShareInfoPush(HttpServletRequest request, HttpSession session, Model model, PersonShareInfoVO personShareInfoVO) throws FinsetException, IOException {
		String no_person = (String) session.getAttribute("no_person");

		String setting_mode = personShareInfoVO.getSetting_mode();
		String share_status = personShareInfoVO.getShare_status();
		String rec_no_person = "";
		int chkTerm = 0;

        String title = "[공유관리]";
        String body = "";
        String mode_nm = "";
        String url = "";
        String fcm_token = "";

        //push수신자 정보조회
        PersonShareInfoVO personShareInfo = personManager.getPersonShareInfo(personShareInfoVO);

        //push 정보셋팅
        PersonShareMessageInfo personShareMessageInfo = new PersonShareMessageInfo();

        personShareMessageInfo.setSeq_share(personShareInfo.getSeq_share());
        personShareMessageInfo.setType_message("02"); //push

        personShareMessageInfo.setId_lst(no_person); //최종수정아이디

        if("00".equals(setting_mode)) { //공유 요청
        	rec_no_person = personShareInfo.getOffer_no_person();
        	url = "/m/customercenter/frameShareInfoSetting.crz?setting_mode=02&seq_share="+personShareInfo.getSeq_share();
        	body = personShareInfo.getReq_nm_person()+"님으로부터 공유요청이 왔습니다";

        	personShareMessageInfo.setCd_message("01"); //공유요청
        	personShareMessageInfo.setReq_status("01"); //요청
        	personShareMessageInfo.setReq_message(body); //요청메세지

        	personShareMessageInfo.setId_frt(no_person); //최초입력아이디

        }else if("01".equals(setting_mode)) { //공유재요청
        	rec_no_person = personShareInfo.getOffer_no_person();
        	url = "/m/customercenter/frameShareInfoSetting.crz?setting_mode=02&seq_share="+personShareInfo.getSeq_share();
        	body = personShareInfo.getReq_nm_person()+"님으로부터 공유요청이 왔습니다";

        	personShareMessageInfo.setCd_message("01"); //공유요청
        	personShareMessageInfo.setReq_status("02"); //재요청
        	personShareMessageInfo.setReq_message(body); //요청메세지

        	chkTerm = personManager.chkPersonShareInfoMessageTerm(personShareMessageInfo);
        }else if("02".equals(setting_mode)) { //허용/거절
        	rec_no_person = personShareInfo.getReq_no_person();
        	if("02".equals(share_status)) {
        		mode_nm = "수락";
        	}else if("03".equals(share_status)) {
        		mode_nm = "거절";
        	}

        	body = personShareInfo.getOffer_nm_person()+"님이 공유를 "+mode_nm+"하였습니다.";

        	personShareMessageInfo.setReq_status("03"); //응답
        	personShareMessageInfo.setRes_message(body); //응답메세지

        }else if("03".equals(setting_mode)) { //변경/해지
        	rec_no_person = personShareInfo.getReq_no_person();
        	if("02".equals(share_status)) {
        		mode_nm = "변경";
        	}else if("04".equals(share_status)) {
        		mode_nm = "해지";
        	}

        	body = personShareInfo.getOffer_nm_person()+"님이 공유를 "+mode_nm+"하였습니다.";

        }else if("04".equals(setting_mode)) { //업데이트요청
        	rec_no_person = personShareInfo.getOffer_no_person();

        	body = personShareInfo.getReq_nm_person()+"님이 정보 업데이트 요청을 하였습니다. \n";
        	body += "핀셋에 로그인 하시면 자동으로 공유정보가 업데이트 됩니다.";

        	personShareMessageInfo.setCd_message("02"); //정보업데이트
        	personShareMessageInfo.setReq_status("01"); //요청
        	personShareMessageInfo.setReq_message(body); //요청메세지

        	chkTerm = personManager.chkPersonShareInfoMessageTerm(personShareMessageInfo);

        }

        if(!(chkTerm == 1)) { //push기간설정, 공유재요청 / 업데이트요청하기 일때는 기간이 하루가 지난 경우에만 push발송

        	PersonVO recPersonVO = personManager.getPersonInfo(rec_no_person);

        	if (recPersonVO != null) {
	            fcm_token = recPersonVO.getFcm_token();
	            if (fcm_token != null && !fcm_token.equals("")) {
	                logger.debug("@@@@SendTo())"+fcm_token);

	                if(FcmUtil.sendFcm(  fcm_token
	                        , title
	                        , body
	                        , url
	                        , StringUtil.nullToString(recPersonVO.getYn_os(), "1")
	                        , StringUtil.nullToString(recPersonVO.getCd_push(), ""))){
	                }

	                //메세지 table insert / update
	                if(!("03".equals(setting_mode))) { //변경 / 해지는 메세지table updateX
		                ReturnClass rtnClass = personManager.mergePersonShareInfoMessage(personShareMessageInfo);
		                model.addAttribute("cdResult",rtnClass.getCd_result());
	                }else{
	                	model.addAttribute("cdResult","00");
	                }
	            }
	        }
        } else {
        	model.addAttribute("cdResult", "01"); //공유재요청 / 업데이트요청하기에서 기간이 하루가 지나지 않은경우
        }

        return "jsonView";
	}


	/**
	 * 공유관리 상세
	 * @param request
	 * @param session
	 * @param model
	 * @param personShareInfoVO
	 * @return String
	 */
	@RequestMapping("/frameShareInfoDetail.crz")
	public String frameShareInfoDetail(HttpServletRequest request, HttpSession session, Model model, PersonShareInfoVO personShareInfoVO) throws FinsetException, IOException {
		String no_person = "";

		//공유정보
        PersonShareInfo shareInfo = personManager.getPersonShareInfo(personShareInfoVO);
        model.addAttribute("shareInfo",shareInfo);

        no_person = shareInfo.getOffer_no_person();

		//신용정보
		model.addAttribute("baseInfo", creditManager.getCreditMainBaseInfo(no_person)); //등급, 점수

        //최근 1년 연체List, 건수 조회
        CreditInfo creditInfoParam = new CreditInfo();
        creditInfoParam.setNoPerson(no_person);
        creditInfoParam.setCdChangeInfo("02");
        List<CreditInfo> overdueList = creditManager.getCreditDetailGradeChangeList(creditInfoParam);

        String overdueYearCnt = "0";
        if(overdueList.size() > 0 && overdueList != null) {
        	overdueYearCnt = overdueList.get(0).getYear_cnt();
        }
        model.addAttribute("overdueCnt",overdueYearCnt);
        model.addAttribute("overdueListData",overdueList);


        //소득정보
        personShareInfoVO.setOffer_no_person(no_person);
        PersonShareInfoVO etmInfo = personManager.getPersonShareEtmInfo(personShareInfoVO);
        model.addAttribute("etmInfo",etmInfo);

        //부채정보
        DebtForm debtForm = new DebtForm();
		debtForm.setNo_person(no_person);
		debtForm.setDisplay_yn("Y"); //display_yn 이 Y 인 것만 가져오도록
		List<DebtVO> debtList = debtManager.listDebtPg(debtForm);
		model.addAttribute("debtListData", debtList);

		logger.info("공유관리 상세");
		return "/customercenter/frameShareInfoDetail";
	}

}
