package com.koscom.debt;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.debt.model.DebtForm;
import com.koscom.debt.service.DebtManager;

@Controller
@RequestMapping(value= "/debt")
public class DebtController {

	private static final Logger logger = LoggerFactory.getLogger(DebtController.class);

	@Autowired
	private DebtManager debtManager;
	

	/**
	 * 부채데이터조회 메인
	 * @param debtForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listDebtPersonMain.crz")
	public String listDebtPersonMain(DebtForm debtForm, Model model) {
		
		logger.debug("부채데이터조회 메인");

		return "/debt/listDebtPersonMain";
	}

	/**
	 * 회원의 KCB대출개설 및 계좌상태정보를 가져온다.
	 * @param debtForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listDebtPersonInfo.crz")
	public String listDebtPersonInfo(DebtForm debtForm, Model model) {
		
		logger.debug("★ no_person : " + debtForm.getNo_person());
		
		model.addAttribute("listDebtPersonInfo", debtManager.listDebtPersonInfo(debtForm));
		
		return "/debt/listDebtPersonInfo";
	}

	/**
	 * 회원의 대출 계좌의 상세 정보를 조회한다.
	 * @param debtForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listDebtPersonRepay.crz")
	public String listDebtPersonRepay(DebtForm debtForm, Model model) {
		
		
		model.addAttribute("listDebtPersonRepay", debtManager.listDebtPersonRepay(debtForm));
		
		return "/debt/listDebtPersonRepay";
	}
}
