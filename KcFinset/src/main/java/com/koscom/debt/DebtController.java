package com.koscom.debt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.debt.model.DebtCalendarVO;
import com.koscom.debt.model.DebtDetail12RepVO;
import com.koscom.debt.model.DebtForm;
import com.koscom.debt.model.DebtSummaryVO;
import com.koscom.debt.model.DebtVO;
import com.koscom.debt.service.DebtManager;
import com.koscom.util.ResUtil;

@Controller
@RequestMapping("/m/debt")
@PropertySource("classpath:prop/webservice.properties")
public class DebtController {

	private static final Logger logger = LoggerFactory.getLogger(DebtController.class);
	
	@Autowired
	private DebtManager debtManager;
	
	/**
	 * 부채관리 진입 (풋터에서 호출)
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameDebt.crz")
	public String frameDebt(HttpServletRequest request, Model model, HttpSession session) {

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
					//부체 등록 페이지로 보내기
					return "redirect:"+path+"/m/debt/frameInDebtInfo.crz";

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
	 * 등록화면 //등록할 부채가 존재함. 등록하시겠습니까?
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameInDebtInfo.crz")
	public String frameInDebtInfo(HttpServletRequest request, Model model, HttpSession session) {
		String no_person = (String) session.getAttribute("no_person");

        logger.debug("no_person : " + no_person);
        
		/**
		 * 등록할 부채수
		 */
		int  newDebtCount = debtManager.newDebtCount(no_person);

		/**
		 * 등록된 부채수
		 */
		int  debtYCount = debtManager.getDebtYCount(no_person);
		/**
		 * 신규인지 확인
		 */

		if(no_person != null && !no_person.equals("")){

			model.addAttribute("no_person", no_person);
			model.addAttribute("new_debt_count", newDebtCount);
			model.addAttribute("debt_y_count", debtYCount);

		}
		return "/debt/frameInDebtInfo";
	}
	
	/**
	 * 부채관리 메인
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameDebtInfoMain.crz")
	public String frameDebtInfoMain(HttpServletRequest request, Model model, HttpSession session) {
		String no_person = (String) session.getAttribute("no_person");
        logger.debug("no_person : " + no_person);
		if(no_person != null && !no_person.equals("")){
			//부채 등록 후에 마지막 credit_info.seq 를 등록
			//-> 신규 부채 유무 판별 시 사용함.
			String debt_reg_update_yn = request.getParameter("debt_reg_update_yn");
			if(debt_reg_update_yn != null && debt_reg_update_yn.equals("Y")){
				debtManager.modifySeqNewDeptReg(no_person);
			}
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
		return "/debt/frameDebtInfoMain";
	}
	
	/**
	 * 부채관리 캘린더
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameDebtCalendar.crz")
	public String frameDebtCalendar(HttpServletRequest request, Model model, HttpSession session) {
		return "/debt/frameDebtCalendar";
	}
	
	/**
	 * 부채 내역 조회-에러 처리는 페이지에서 한다
	 * @param req_yyyymm 날짜 형태가 String이고, yyyymm 형식으로 붙어서 온다
	 * @return
	 */
	@RequestMapping(value = "/getDebtListWithMonth.json")
	public String getDebtListWithMonth(Model model, HttpSession session, String req_yyyymm){
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
	public String frameInDebtDetail(HttpServletRequest request, Model model, HttpSession session) {

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
	
	/**
	 * 부채 상세정보 수정
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameInDebtUpdate.crz")
	public String frameInDebtUpdate(HttpSession session,HttpServletRequest request, Model model){
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
	public String updateDebtInfo(HttpServletRequest request,DebtVO debtVO, Model model){
		if(debtVO == null){
			model.addAttribute("code","99");
		} else {
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
	@RequestMapping("/frameInDebtDelete.json")
	public String frameInDebtDelete(HttpSession session, HttpServletRequest request, Model model){
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
}
