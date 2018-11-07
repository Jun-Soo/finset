package com.koscom.customercenter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.board.model.BoardForm;
import com.koscom.board.model.BoardInfoVO;
import com.koscom.board.service.BoardManager;
import com.koscom.cache.service.CacheManager;
import com.koscom.credit.service.CreditManager;
import com.koscom.debt.model.DebtForm;
import com.koscom.debt.model.DebtVO;
import com.koscom.debt.service.DebtManager;
import com.koscom.domain.CreditInfo;
import com.koscom.domain.PersonShareInfo;
import com.koscom.domain.PersonShareMessageInfo;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.finset.model.FinsetForm;
import com.koscom.finset.model.FinsetVO;
import com.koscom.finset.service.FinsetManager;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.service.GoodsManager;
import com.koscom.person.model.PersonShareInfoForm;
import com.koscom.person.model.PersonShareInfoVO;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.pusheach.model.PushEachForm;
import com.koscom.pusheach.service.PushEachManager;

@Controller
@RequestMapping("/m/customercenter")
public class CustomerCenterController implements Constant {

	private static final Logger logger = LoggerFactory.getLogger(CustomerCenterController.class);

	@Autowired
	PersonManager personManager;

	@Autowired
	PushEachManager pushEachManager;

	@Autowired
	CreditManager creditManager;

	@Autowired
	DebtManager debtManager;
	
	@Autowired
	FinsetManager finsetManager;
	
	@Autowired
	GoodsManager goodsManager;
	
	@Autowired
	ScrapManager scrapManager;
	
	@Autowired
	BoardManager boardManager;
	
	@Autowired
	CodeManager codeManager;
	
	@Autowired
	CacheManager cacheManager;
	
	@Resource
	Environment environment;

	/**
	 * 마이페이지 메인
	 * @param request
	 * @param session
	 * @param model
	 * @return String
	 * @throws FinsetException, IOException
	 */
	@RequestMapping("/frameCustomerCenterMain.crz")
	public String frameCustomerCenterMain(HttpServletRequest request, HttpSession session, Model model) throws FinsetException, IOException {
		String no_person = (String) session.getAttribute("no_person");
		PersonVO personVO = personManager.getPersonInfo(no_person);
		model.addAttribute("personVO", personVO);
		logger.info("personVO====MAIN======" + personVO.toString());
		return "jsonView";
	}

	/**
	 * 마이페이지 알림
	 * @param request
	 * @param session
	 * @param model
	 * @param pushEachForm
	 * @return String
	 * @throws FinsetException, IOException
	 */
	@RequestMapping("/frameCustomerNotification.crz")
	public String frameInDebtDetail(HttpServletRequest request, HttpSession session, Model model, PushEachForm pushEachForm) throws FinsetException, IOException {
		String no_person = (String) session.getAttribute("no_person");
		pushEachForm.setNo_person(no_person);
		pushEachForm.setPage(pushEachForm.getPage());
		pushEachForm.setPush_divcd("02");
		Pagination pagedList = (Pagination) pushEachForm.setPagedList(pushEachManager.listPushNotification(pushEachForm), pushEachManager.listPushNotificationCount(pushEachForm));
		model.addAttribute("pagedList", pagedList);
		logger.info("pagedList========="+pagedList.toString());
		model.addAttribute("pushEachForm", pushEachForm);

		return "/customercenter/frameCustomerNotification";
	}

	//공유관리
	/**
	 * 공유관리 summary list
	 * @param request
	 * @param session
	 * @param model
	 * @return String
	 * @throws FinsetException, IOException
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
	 * @throws FinsetException, IOException
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
	 * @throws FinsetException, IOException
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
	 * @throws FinsetException, IOException
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
	 * @throws FinsetException, IOException
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
	 * @throws FinsetException, IOException
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
	 * @throws FinsetException, IOException
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
	 * @throws FinsetException, IOException
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
	 * @throws FinsetException, IOException
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
	 * @throws FinsetException, IOException
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
	 * @throws FinsetException, IOException
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
	 * @throws FinsetException, IOException
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
	 * @throws FinsetException, IOException
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
	
	/**
	 * 마이페이지 조회결과
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerViewResults.crz")
	public String frameCustomerViewResults(HttpServletRequest request, Model model, HttpSession session, FinsetForm finsetForm) {

		//고객번호 조회
		String no_person = (String) session.getAttribute("no_person");
		finsetForm.setNo_person(no_person);
		finsetForm.setPage(finsetForm.getPage());
		finsetForm.setCd_goods_gubun("01");//Default 조회 설정
		finsetForm.setCd_goods_gubun2("02");
		
		String site = (environment != null)?environment.getProperty("service.profile"):"";
		model.addAttribute("site", site);

		Pagination pagedList = (Pagination) finsetForm.setPagedList(finsetManager.listSearchGoods(finsetForm), finsetManager.listSearchGoodsCount(finsetForm));
		model.addAttribute("pagedList", pagedList);

		return "/customercenter/frameCustomerViewResults";
	}
	
	/**
     * 마이페이지 조회결과 리스트 페이징
     * @param model
     * @param request
     * @param finsetForm
     * @param session
     * @return
     */
	@RequestMapping("/listCustomerViewResults.crz")
	public String listCustomerViewResults(Model model, HttpServletRequest request, FinsetForm finsetForm, HttpSession session) {
		String no_person = (String) session.getAttribute("no_person");
		finsetForm.setNo_person(no_person);
		
		finsetForm.setCd_goods_gubun("01");//Default 조회 설정
		finsetForm.setCd_goods_gubun2("02");//Default 조회 설정

		Pagination pagedList = (Pagination) finsetForm.setPagedList(finsetManager.listSearchGoods(finsetForm), finsetManager.listSearchGoodsCount(finsetForm));

		logger.info("list Result : " + pagedList.toString());
		model.addAttribute("pagedList", pagedList);

		return "/customercenter/sub/listCustomerViewResults";
	}
	
	/**
     * 마이페이지 조회결과 리스트 페이징
     * @param model
     * @param request
     * @param vo
     * @param session
     * @return
     */
	@RequestMapping("/getIsSearching.crz")
	public String getIsSearching(Model model, HttpServletRequest request, FinsetVO vo, HttpSession session) {
		String no_person = (String) session.getAttribute("no_person");
		vo.setNo_person(no_person);
		int count = finsetManager.countLoading(vo);
		model.addAttribute("count", count);
		return JSON_VIEW;
	}
	
	/**
	 * 마이페이지 조회결과 상세
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerViewResultsDetail.crz")
	public String frameCustomerViewResultsDetail(HttpServletRequest request, Model model, GoodsForm goodsForm) {

		GoodsVO goodsInfo = new GoodsVO();

		if(goodsForm.getCd_fc() != null && goodsForm.getCd_goods() != null){
			goodsInfo.setCd_fc(goodsForm.getCd_fc());
			goodsInfo.setCd_goods(goodsForm.getCd_goods());

			GoodsVO goodsVO = goodsManager.getGoodsInfo(goodsInfo);
			logger.info(goodsVO.toString());
			model.addAttribute("goodsVO", goodsVO);
		}

		return "/customercenter/frameCustomerViewResultsDetail";
	}

	/**
	 * 마이페이지 내정보
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerMyInfo.crz")
	public String frameCustomerMyInfo(HttpSession session, HttpServletRequest request, Model model) {
		String no_person = (String) session.getAttribute("no_person");
		PersonVO personVO = personManager.getPersonInfo(no_person);
		personVO.setBirthday(personVO.getYmd_birth());
		personVO.setSex(personVO.getC1_gender());
		model.addAttribute("resultPerson", personVO);
		
		String site = (environment != null)?environment.getProperty("service.profile"):"";
		model.addAttribute("site", site);
		
		int debtCount = debtManager.getDebtCount(no_person);
		model.addAttribute("debtCount", debtCount);
		
		int linkedFcCount = scrapManager.getLinkedFcCount(no_person);
		model.addAttribute("linkedFcCount", linkedFcCount);
		
		logger.info("resultPerson==========" + personVO.toString());
		return "/customercenter/frameCustomerMyInfo";
	}
	
	/**
	 * 마이페이지 공지사항/이벤트
	 * @param boardForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerNotice.crz")
	public String frameCustomerNotice(HttpServletRequest request, Model model, BoardForm boardForm) {
		//Default 설정 - 공지사항게시판
		if( StringUtil.isEmpty(boardForm.getId_board()) ) boardForm.setId_board("notice");
		boardForm.setPage(1);
		Pagination pagedList = (Pagination) boardForm.setPagedList(boardManager.listBoardInfo(boardForm), boardManager.listBoardInfoCount(boardForm));

		logger.info(pagedList.toString());
		model.addAttribute("pagedList", pagedList);
		model.addAttribute("boardForm", boardForm);
		if(pagedList.getSource() != null) {
			if(pagedList.getSource().size() > 0) {
				pagedList.getSource().get(0);
				BoardInfoVO boardInfoVO = (BoardInfoVO) pagedList.getSource().get(0);
				model.addAttribute("seq", boardInfoVO.getSeq());
			}
		}
		return "/customercenter/frameCustomerNotice";
	}
	
	/**
	 * 마이페이지 공지사항/이벤트 list
	 * @param boardForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listCustomerNotice.crz")
	public String listCustomerNotice(Model model, HttpServletRequest request, BoardForm boardForm) {		
		boardForm.setPage(1);
		Pagination pagedList = (Pagination) boardForm.setPagedList(boardManager.listBoardInfo(boardForm), boardManager.listBoardInfoCount(boardForm));
	
		logger.info(pagedList.toString());
		model.addAttribute("pagedList", pagedList);
		model.addAttribute("boardForm", boardForm);
		if(pagedList.getSource() != null) {
			if(pagedList.getSource().size() > 0) {
				BoardInfoVO boardInfoVO = (BoardInfoVO) pagedList.getSource().get(0);
				model.addAttribute("seq", boardInfoVO.getSeq());
			}
		}
				
		return "/customercenter/sub/listNotice";
	}
	
	/**
	 * 마이페이지 공지사항/이벤트 상세
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerNoticeDetail.crz")
	public String frameCustomerNoticeDetail(HttpServletRequest request, Model model, BoardForm boardForm) {
		logger.info(boardForm.toString());

			BoardInfoVO boardInfo = new BoardInfoVO();
			BoardInfoVO boardInfoVO = new BoardInfoVO();

			if(boardForm.getSeq() != null && boardForm.getId_board() != null){
				boardInfoVO.setSeq(boardForm.getSeq() );
				boardInfoVO.setId_board(boardForm.getId_board());

				//이미지 정보
				if("event".equals(boardForm.getId_board())) {
					boardInfoVO.setFile_type("02");
					BoardInfoVO boardImgInfo = boardManager.getBoardFileInfo(boardInfoVO);
					model.addAttribute("boardImgInfo", boardImgInfo);
				}
				
				boardInfo = boardManager.getBoardInfo(boardInfoVO);
				logger.info(boardInfo.toString());
				model.addAttribute("boardInfo", boardInfo);
			}

		return "/customercenter/frameCustomerNoticeDetail";
	}
	
	/**
	 * 마이페이지 알림설정
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerNotificationSetting.crz")
	public String frameCustomerNotificationSetting(HttpServletRequest request, Model model, HttpSession session) {

		String no_person = (String) session.getAttribute("no_person");

		PersonVO personVO = new PersonVO();
		personVO = personManager.getPersonInfo(no_person);
		model.addAttribute("personVO", personVO);

		model.addAttribute("listCdPush", codeManager.listCodeInfo("cd_push"));

		model.addAttribute("listPushSetting", personManager.getPushSettingInfo(no_person));
		
		return "jsonView";
	}
	
	/**
	 * 마이페이지 인증/보안
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerAuthenticationSecurity.crz")
	public String frameCustomerAuthenticationSecurity(HttpServletRequest request, Model model, HttpSession session) {

		String no_person = (String) session.getAttribute("no_person");

		PersonVO personVO = new PersonVO();
		personVO = personManager.getPersonInfo(no_person);
		model.addAttribute("personVO", personVO);

		return "/customercenter/frameCustomerAuthenticationSecurity";
	}
	
	/**
	 * 마이페이지 비밀번호 변경 본인인증
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameChnagePwdCert.crz")
	public String frameChnagePwdCert(Model model, HttpServletRequest request, HttpSession session) {
		String no_person = (String) session.getAttribute("no_person");
		PersonVO personVO = personManager.getPersonInfo(no_person);

		model.addAttribute("personHp", personVO.getHp());
		logger.info("personHP");
		logger.info("personHP");
		logger.info("personHP");
		logger.info("personHP");
		logger.info("personHP");
		logger.info(personVO.getHp());
		return "/customercenter/frameChnagePwdCert";
	}
	
	/**
	 * 마이페이지 관심상품
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerGoodsFavorite.crz")
	public String frameCustomerGoodsFavorite(HttpServletRequest request, Model model, HttpSession session, GoodsForm goodsForm) {

		String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);
		goodsForm.setPage(goodsForm.getPage());

		//Default 설정
		if( StringUtil.isEmpty(goodsForm.getCd_goods_alliance()) ) goodsForm.setCd_goods_alliance("01");
		if( StringUtil.isEmpty(goodsForm.getCd_goods_class()) ) goodsForm.setCd_goods_class("01");
		
		Pagination pagedList = null;
		if( "01".equals(goodsForm.getCd_goods_alliance()) ){//일반상품
			pagedList = (Pagination) goodsForm.setPagedList(goodsManager.listGoodsFavoriteNoAlliance(goodsForm), goodsManager.getGoodsFavoriteNoAllianceCount(goodsForm));
		}else if( "02".equals(goodsForm.getCd_goods_alliance()) ){//제휴상품
			pagedList = (Pagination) goodsForm.setPagedList(goodsManager.listGoodsFavoriteAlliance(goodsForm), goodsManager.getGoodsFavoriteAllianceCount(goodsForm));
		}

		if(pagedList != null){
			logger.info(pagedList.toString());
		}
		model.addAttribute("pagedList", pagedList);
		model.addAttribute("cd_goods_alliance",goodsForm.getCd_goods_alliance());
		model.addAttribute("cd_goods_class",goodsForm.getCd_goods_class());
		
		return "/customercenter/frameCustomerGoodsFavorite";
	}
	
	/**
	 * 마이페이지 관심상품 리스트 페이징
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listLoanAffiliates.crz")
	public String listLoanAffiliates(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {

		String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);

		Pagination pagedList = null;
		if( "01".equals(goodsForm.getCd_goods_alliance()) ){//일반상품
			pagedList = (Pagination) goodsForm.setPagedList(goodsManager.listGoodsFavoriteNoAlliance(goodsForm), goodsManager.getGoodsFavoriteNoAllianceCount(goodsForm));
		}else if( "02".equals(goodsForm.getCd_goods_alliance()) ){//제휴상품
			pagedList = (Pagination) goodsForm.setPagedList(goodsManager.listGoodsFavoriteAlliance(goodsForm), goodsManager.getGoodsFavoriteAllianceCount(goodsForm));
		}
		if(pagedList != null){
			logger.info(pagedList.toString());
		}
		model.addAttribute("pagedList", pagedList);
		model.addAttribute("cd_goods_alliance",goodsForm.getCd_goods_alliance());
		
		return "/customercenter/sub/listLoanAffiliates";
	}


	/**
	 * 마이페이지 관심상품 상세
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerGoodsFavoriteDetail.crz")
	public String frameCustomerGoodsFavoriteDetail(HttpServletRequest request, Model model, HttpSession session, GoodsForm goodsForm) {
		String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);
		GoodsVO goodsInfo = new GoodsVO();
		GoodsVO goodsVO = new GoodsVO();

		if(goodsForm.getCd_fc() != null && goodsForm.getCd_goods() != null){
			goodsVO.setCd_fc(goodsForm.getCd_fc());
			goodsVO.setCd_goods(goodsForm.getCd_goods());
			goodsVO.setNo_person(no_person);

			if( "N".equals(goodsForm.getYn_alliance()) ){ //일반상품
				goodsInfo = goodsManager.getCooconGoodsFavorite(goodsVO);
			}else if( "Y".equals(goodsForm.getYn_alliance()) ){ //제휴상품
				goodsInfo = goodsManager.getGoodsFavorite(goodsVO);
			}
			model.addAttribute("goodsInfo", goodsInfo);
			model.addAttribute("yn_alliance",goodsForm.getYn_alliance());
		}

		return "/customercenter/frameCustomerGoodsFavoriteDetail";
	}
	
	/**
	 * 마이페이지 자주묻는질문
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerFAQ.crz")
	public String frameCustomerFAQ(HttpServletRequest request, Model model) {
		return "/customercenter/frameCustomerFAQ";
	}
	
	/**
	 * 마이페이지 자주묻는질문 검색
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerFAQSearch.crz")
	public String frameCustomerFAQSearch(HttpServletRequest request, Model model, BoardForm boardForm) {
		logger.info(boardForm.toString());
		Pagination pagedList = (Pagination) boardForm.setPagedList(boardManager.SearchBoard(boardForm), boardManager.SearchBoardCount(boardForm));
		
		model.addAttribute("pagedList", pagedList);
		model.addAttribute("boardForm", boardForm);
		
		return "/customercenter/frameCustomerFAQSearch";
	}

	/**
	 * 마이페이지 자주묻는질문 검색 리스트
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/listFaqSearch.crz")
	public String listFaqSearch(HttpServletRequest request, Model model, BoardForm boardForm) {
		logger.info(boardForm.toString());
		
		Pagination pagedList = (Pagination) boardForm.setPagedList(boardManager.SearchBoard(boardForm), boardManager.SearchBoardCount(boardForm));
		
		model.addAttribute("pagedList", pagedList);
		
		return "/customercenter/sub/listFaqSearch";
	}

	/**
	 * 마이페이지 자주묻는질문
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerFAQDetail.crz")
	public String frameCustomerFAQCredit(HttpServletRequest request, Model model, BoardForm boardForm) {
		//공지사항 게시판
				logger.info(boardForm.toString());
				
				model.addAttribute("nm_board", boardManager.getBoardNm(boardForm.getId_board()));
				boardForm.setPage(1);
				
				
				Pagination pagedList = (Pagination) boardForm.setPagedList(boardManager.listBoardInfo(boardForm), boardManager.listBoardInfoCount(boardForm));

				logger.info(pagedList.toString());
				model.addAttribute("pagedList", pagedList);
				model.addAttribute("boardForm", boardForm);
				if(pagedList.getSource() != null) {
					if(pagedList.getSource().size() > 0) {
						pagedList.getSource().get(0);
						BoardInfoVO boardInfoVO = (BoardInfoVO) pagedList.getSource().get(0);
						model.addAttribute("seq", boardInfoVO.getSeq());
					}
				}
				
		return "/customercenter/frameCustomerFAQDetail";
	}
	
	/**
	 * 자주묻는질문 리스트
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/listFaq.crz")
	public String listFaq(Model model, HttpServletRequest request, PushEachForm pushEachForm, BoardForm boardForm) {
		Pagination pagedList = (Pagination) boardForm.setPagedList(boardManager.listBoardInfo(boardForm), boardManager.listBoardInfoCount(boardForm));
		logger.info(pagedList.toString());
		model.addAttribute("pagedList", pagedList);
		
		return "/customercenter/sub/listFaq";
	}

	/**
	 * 마이페이지 고객센터
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerServiceCenter.crz")
	public String frameCustomerServiceCenter(HttpServletRequest request, Model model, HttpSession session) {

		String no_person = (String) session.getAttribute("no_person");
		ReturnClass rc = cacheManager.clearCacheCode();
		PersonVO personVO = new PersonVO();
		personVO = personManager.getPersonInfo(no_person);

		personVO.setEmail(personVO.getEmail());

		model.addAttribute("personVO", personVO);
		logger.info("personVO========="+personVO.toString());

		CodeInfo codeInfo = new CodeInfo();
		if(!"1".equals(personVO.getYn_os())) {
			codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "IOS_VERSION");
		} else {
			codeInfo = codeManager.getCodeInfo("_CONF_SYSTEM", "ANDROID_VERSION");
		}
			
		logger.info(codeInfo.getNm_code());
		model.addAttribute("newest_version", codeInfo.getNm_code());

		return "/customercenter/frameCustomerServiceCenter";
	}
	
	/**
	 * 마이페이지 이용약관및정책
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerTerms.crz")
	public String frameCustomerTerms(HttpServletRequest request, Model model) {
		return "/customercenter/frameCustomerTerms";
	}
	
	/**
	 * 마이페이지 핀셋 이용약관
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerFinsetTerms.crz")
	public String frameCustomerFinsetTerms(HttpServletRequest request, Model model) {
		return "/customercenter/frameCustomerFinsetTerms";
	}

	/**
	 * 마이페이지 개인정보 처리방침
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerPrivacyPolicy.crz")
	public String frameCustomerPrivacyPolicy(HttpServletRequest request, Model model) {
		return "/customercenter/frameCustomerPrivacyPolicy";
	}
	
	/**
	 * 마이페이지 회원탈퇴
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameCustomerQuit.crz")
	public String frameCustomerQuit(HttpServletRequest request, Model model) {
		return "/customercenter/frameCustomerQuit";
	}
	
	/**
	 * 회원탈퇴(no_person 기준 데이터 삭제)
	 * @param session
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameCustomerQuitComp.crz")
	public String frameCustomerQuitComp(HttpSession session, HttpServletRequest request, Model model) throws UnsupportedEncodingException, FinsetException, IOException {
		String no_person = (String) session.getAttribute("no_person");

		ReturnClass rc = personManager.procPersonInfoDelQuit(no_person); 
		model.addAttribute("result", Constant.SUCCESS);
		model.addAttribute("message", rc.getMessage());

		return "/customercenter/frameCustomerQuitComp";
	}
}
