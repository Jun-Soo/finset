package com.koscom.main;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.board.service.BoardManager;
import com.koscom.credit.service.CreditManager;
import com.koscom.main.service.MainManager;
import com.koscom.util.FinsetException;


@Controller
@RequestMapping("/m/main")
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private MainManager mainManager;

	@Autowired
	private BoardManager boardManager;

	@Autowired
	private CreditManager creditManager;

	/**
	 * VUE
     * FINSET 메인
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getMainInfo.json")
	public String getMainInfo(
	HttpServletRequest request,
	HttpSession session,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

        model.addAttribute("noPerson", no_person);
        model.addAttribute("creditInfo", creditManager.getCreditMainBaseInfo(no_person)); //신용정보
		model.addAttribute("consumeSumAmt", mainManager.getMainConsumeSumAmt(no_person)); //지출정보
		model.addAttribute("assetsSumAmt", mainManager.getMainAssetsSumAmt(no_person)); //자산정보
		model.addAttribute("debtSumAmt", mainManager.getMainDebtSumAmt(no_person)); //부채정보

		return "jsonView";
	}

	/**
	 * VUE
     * FINSET 메인 - Bottom 이벤트
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/listMainEvnetBoard.json")
	public String listMainEvnetBoard(
	HttpServletRequest request,
	HttpSession session,
	Model model) throws FinsetException, IOException{

		model.addAttribute("listEvent", boardManager.listMainEventBoard()); //이벤트list

		return "jsonView";
	}
}
