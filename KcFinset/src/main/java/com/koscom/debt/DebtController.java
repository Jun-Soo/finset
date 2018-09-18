package com.koscom.debt;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.koscom.debt.model.DebtCalendarVO;
import com.koscom.debt.model.DebtDetail12RepVO;
import com.koscom.debt.model.DebtForm;
import com.koscom.debt.model.DebtSummaryVO;
import com.koscom.debt.model.DebtVO;
import com.koscom.debt.service.DebtManager;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.scrap.model.AppFcLinkInfo;
import com.koscom.util.Constant;
import com.koscom.util.FinsetException;
import com.koscom.util.ResUtil;

@Controller
@RequestMapping("/m/debt")
@PropertySource("classpath:prop/webservice.properties")
public class DebtController {

	private static final Logger logger = LoggerFactory.getLogger(DebtController.class);
	
	@Autowired
	private DebtManager debtManager;
	
	@Autowired
	private PersonManager personManager;
	
	/**
	 * 부채관리 진입 (풋터에서 호출)
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameDebt.crz")
	public String frameDebt(HttpServletRequest request, Model model, HttpSession session) throws FinsetException {

		String no_person = (String) session.getAttribute("no_person");
        logger.debug("no_person : " + no_person);
		String path = ResUtil.getPath(request);
		
		if(no_person != null && !no_person.equals("")){
			/**
			 * IF 부채가 있으면
			 *   IF 등록할 부채가 있으면
			 *      부채등록 페이지
			 *   ELSE
			 *      부채관리 메인페이지
			 * ELSE
			 *   부채없음 페이지
			 */
			String debtExistYn = debtManager.getDebtExistYn(no_person);
			logger.debug(debtExistYn);
			//부채가 있다면
			if(debtExistYn != null && debtExistYn.equals("Y")){
				/**
				 * 등록할 부채가 존재하는지 체크
				 */
				int  newDebtCount = debtManager.newDebtCount(no_person);
				//등록할 부채가 존재한다면 ->등록 화면으로
				if(newDebtCount > 0){
					//부체 등록 페이지로 보내기 - 이제 사용하지 않게 됨
//					return "redirect:"+path+"/m/debt/frameInDebtInfo.crz";
					return "redirect:"+path+"/m/debt/frameDebtInfoMain.crz";

				}else{
					//부채관리 메인 페이지로 보내기
					return "redirect:"+path+"/m/debt/frameDebtInfoMain.crz";
				}
			}else{
				//부채가 없음. blank 페이지로 보내기
				return "redirect:"+path+"/m/debt/frameNoDebtInfo.crz";
			}
		}
		return "redirect:"+path+"/m/debt/frameDebtInfoMain.crz";
	}
	/**
	 * 부채관리 메인
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameDebtInfoMain.crz")
	public String frameDebtInfoMain(HttpServletRequest request, Model model, HttpSession session) throws FinsetException {
		String no_person = (String) session.getAttribute("no_person");
        logger.debug("no_person : " + no_person);
		if(no_person != null && !no_person.equals("")){
			//부채 등록 후에 마지막 credit_info.seq 를 등록
			//-> 신규 부채 유무 판별 시 사용함.
			String debt_reg_update_yn = request.getParameter("debt_reg_update_yn");
			if(debt_reg_update_yn != null && debt_reg_update_yn.equals("Y")){
				debtManager.modifySeqNewDeptReg(no_person);
			}
		}
		
		//test
        logger.debug("no_person : " + no_person);
		if(no_person != null && !no_person.equals("")){
			//부채메인 요약
			DebtSummaryVO debtSummaryVO = debtManager.getDebtSummary(no_person);
			model.addAttribute("debtSummaryData", debtSummaryVO);
			//부채메인 리스트
			DebtForm debtForm = new DebtForm();
			debtForm.setNo_person(no_person);
			
			//display_yn 이 Y 인 것만 가져오도록
			debtForm.setDisplay_yn("Y");
			List<DebtVO> debtList = debtManager.listDebtPg(debtForm);

			model.addAttribute("debtListData", debtList);
		}
		//end test
		
		return "/debt/frameDebtInfoMain";
	}
	
	/**
	 * 부채 메인 요약
	 * @param model
	 * @param session
	 * @return
	 * @throws FinsetException
	 */
	@RequestMapping("/getDebtSummary.json")
	public String getDebtSummary(Model model, HttpSession session) throws FinsetException {
		String no_person = (String) session.getAttribute("no_person");
		DebtSummaryVO debtSummaryVO = debtManager.getDebtSummary(no_person);
		model.addAttribute("debtSummaryData", debtSummaryVO);
		return "jsonView";
	}
	
	/**
	 * 부채 메인 리스트
	 * @param model
	 * @param session
	 * @return
	 * @throws FinsetException
	 */
	@RequestMapping("/listDebtPg.json")
	public String listDebtPg(Model model, HttpSession session) throws FinsetException {
		String no_person = (String) session.getAttribute("no_person");
		//부채메인 리스트
		DebtForm debtForm = new DebtForm();
		debtForm.setNo_person(no_person);
		//display_yn 이 Y 인 것만 가져오도록
		debtForm.setDisplay_yn("Y");
		List<DebtVO> debtList = debtManager.listDebtPg(debtForm);
		model.addAttribute("debtListData", debtList);
		return "jsonView";
	}
	
	/**
	 * 부채관리 캘린더
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameDebtCalendar.crz")
	public String frameDebtCalendar(HttpServletRequest request, Model model, HttpSession session) throws FinsetException {
		return "/debt/frameDebtCalendar";
	}
	
	/**
	 * 부채 내역 조회-에러 처리는 페이지에서 한다
	 * @param req_yyyymm 날짜 형태가 String이고, yyyymm 형식으로 붙어서 온다
	 * @return
	 */
	@RequestMapping(value = "/getDebtListWithMonth.json")
	public String getDebtListWithMonth(Model model, HttpSession session, String req_yyyymm) throws FinsetException {
		if(req_yyyymm == null||req_yyyymm.equals("")){
			model.addAttribute("code","99");
			return "jsonView";
		}
		else{
			String no_person = (String)session.getAttribute("no_person");
			DebtForm debtForm = new DebtForm();
			debtForm.setNo_person(no_person);
			debtForm.setReq_yyyymm(req_yyyymm);
			
			List<DebtCalendarVO> listDebtCalendar = debtManager.listDebtCalendar(debtForm);
			
			model.addAttribute("code","00");
			model.addAttribute("listDebtCalendar", listDebtCalendar);
			
			return "jsonView";
		}
	}

	/**
	 * 부채관리 상세정보
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameInDebtDetail.crz")
	public String frameInDebtDetail(HttpServletRequest request, Model model, HttpSession session) throws FinsetException {

		String no_person = (String) session.getAttribute("no_person");
        logger.debug("no_person : " + no_person);
        
		String no_manage_info = request.getParameter("no_manage_info");

		if(no_person != null && !no_person.equals("") &&
				no_manage_info !=null && !no_manage_info.equals("") ){

			//상단 요약정보
			DebtForm debtForm = new DebtForm();
			debtForm.setNo_person(no_person);
			debtForm.setNo_manage_info(no_manage_info);
			DebtVO debtVO = debtManager.getDebtInfo(debtForm);
			model.addAttribute("debtData", debtVO);
			List<DebtDetail12RepVO> debtDetailList = debtManager.listDebtLast12BizDay(debtForm);	// 최근 12개월 상환내역
			model.addAttribute("list12BizDayData", debtDetailList);
		}
		return "/debt/frameInDebtDetail";
	}
	
	@RequestMapping("/getDebtInfo.json")
	public String getDebtInfo(HttpSession session, Model model, String no_manage_info) throws FinsetException{
		String no_person = (String) session.getAttribute("no_person"); 
		
		DebtForm debtForm = new DebtForm();
		debtForm.setNo_person(no_person);
		debtForm.setNo_manage_info(no_manage_info);
		model.addAttribute("debtVO", debtManager.getDebtInfo(debtForm));
		
		return "jsonView";
	}
	
	@RequestMapping("/getDebtInfoForUpdate.json")
	public String getDebtInfoForUpdate(HttpSession session, HttpServletRequest request, Model model, String no_manage_info) throws FinsetException{
		String no_person = (String) session.getAttribute("no_person"); 
		
		DebtForm debtForm = new DebtForm();
		debtForm.setNo_person(no_person);
		debtForm.setNo_manage_info(no_manage_info);
		model.addAttribute("debtVO", debtManager.getDebtInfoForUpdate(debtForm));
		return "jsonView";
	}
	
	/**
	 * 부채 상세정보 수정
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameInDebtUpdate.crz")
	public String frameInDebtUpdate(HttpSession session,HttpServletRequest request, Model model) throws FinsetException {
		String no_person = (String) session.getAttribute("no_person");
		String interest = request.getParameter("interest");
		String no_manage_info = request.getParameter("no_manage_info");
		
		DebtForm debtForm = new DebtForm();
		debtForm.setNo_person(no_person);
		debtForm.setNo_manage_info(no_manage_info);
		
		DebtVO debtVO = debtManager.getDebtInfoForUpdate(debtForm);
		//여기서 debtVO가 null이라면 에러가 발생할텐데, 절대로 null이 되면 안되기 때문에 예외처리는 없음
		debtVO.setInterest(interest);
		
		debtVO.setNo_person(no_person);
		debtVO.setNo_manage_info(no_manage_info);
		
		model.addAttribute("debtVO",debtVO);
		
		return "/debt/frameInDebtUpdate";
	}
	
	/**
	 * 부채 수정
	 * @param request
	 * @param debtVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateDebtInfo.json")
	public String updateDebtInfo(HttpSession session,DebtVO debtVO, Model model) throws FinsetException {
		String no_person = (String) session.getAttribute("no_person");
		if(debtVO == null){
			model.addAttribute("code","99");
		} else {
			debtVO.setNo_person(no_person);
			debtManager.updateDebtInfo(debtVO);
			model.addAttribute("code","00");
		}
		return "jsonView";
	}
	
	/**
	 * 부채 삭제, 삭제 취소 처리
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteDebt.json")
	public String deleteDebt(HttpSession session, HttpServletRequest request, Model model) throws FinsetException {
		String no_person = (String) session.getAttribute("no_person");
        String no_manage_info = (String) request.getParameter("no_manage_info");
        logger.debug("in debt del no_person:"+no_person+"///////no_manage_info:"+no_manage_info);
        if(no_person!=null && no_manage_info!=null){
        	if((!no_person.equals("")) && (!no_manage_info.equals(""))){
        		//no_person이 null 이 아니고 공백이 아닐 때
        		DebtForm debtForm = new DebtForm();
        		debtForm.setNo_person(no_person);
        		debtForm.setNo_manage_info(no_manage_info);
        		debtForm.setDisplay_yn("N");
        		debtManager.updateDebtDisplay(debtForm);
        		model.addAttribute("code","00");
        	} else {
        		//no_person 이나 no_manage_info가 공백일 때
        		model.addAttribute("code", "99");
        	}
        } else {
        	//no_person이나 no_manage_info 가 null 일 떄
        	model.addAttribute("code", "99");
        }
		return "jsonView";
	}
	
	/**
	 * 대출정보 없을 시 페이지
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameNoDebtInfo.crz")
	public String frameNoDebtInfo(HttpServletRequest request, Model model, HttpSession session) throws FinsetException {
		String no_person = (String) session.getAttribute("no_person");
        logger.debug("no_person : " + no_person);
		return "/debt/frameNoDebtInfo";
	}
	
	/**
	 * 부채 삭제 취소 처리 
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 * @throws FinsetException
	 */
	@RequestMapping("/frameDebtCancelDelete.crz")
	public String frameDebtCancelDelete(HttpServletRequest request, Model model, HttpSession session) throws FinsetException {
		String no_person = (String) session.getAttribute("no_person");
		logger.debug("no_person : "+ no_person);
		DebtForm debtForm = new DebtForm();
		debtForm.setNo_person(no_person);
		List<DebtVO> debtList = debtManager.listDebtPg(debtForm);
		if(debtList.size()==0){
			return "/debt/frameNoDebtInfo";
		}
		model.addAttribute("debtList",debtList);
		return "/debt/frameDebtCancelDelete";
	}
	
	@RequestMapping("/updateDebtDisplayList.json")
	public String updateDebtDisplayList(HttpSession session, DebtForm debtForm, Model model) throws FinsetException {
		String no_person = (String) session.getAttribute("no_person");
		logger.debug("no_person : "+ no_person);
		debtForm.setNo_person(no_person);
		try{
			debtManager.updateDebtDisplayList(debtForm);
			model.addAttribute("code","00");
		} catch (Exception e){
			model.addAttribute("code","99");
		}
		return "jsonView";
	}
	
	@RequestMapping("/frameDebtSecurityCode.crz")
	public String frameDebtSecurityCode() throws FinsetException {
		return "/debt/frameDebtSecurityCode";
	}
	
	@RequestMapping("/debtChkCode.json")
	public String debtChkCode(HttpSession session, Model model, HttpServletRequest request, PersonVO personVO) {

		String no_person = (String) session.getAttribute("no_person");
		personVO.setNo_person(no_person);
		int pwdCheck = personManager.checkPersonPass(personVO);
		
		if(pwdCheck > 0) {	//암호화 비밀번호 체크
			model.addAttribute("result", Constant.SUCCESS);
		} else {
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("result", Constant.FAILED);
		}
		return "jsonView";
	}
}
