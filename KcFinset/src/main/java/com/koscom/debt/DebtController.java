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

}
