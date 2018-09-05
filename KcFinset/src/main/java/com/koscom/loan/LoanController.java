package com.koscom.loan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.domain.CooconGoodsFavoriteInfo;
import com.koscom.goods.model.GoodsForm;
import com.koscom.loan.service.LoanManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Controller
@RequestMapping("/m/loan")
@PropertySource("classpath:prop/webservice.properties")
public class LoanController implements Constant {
	
	private static final Logger logger = LoggerFactory.getLogger(LoanController.class);
	
	@Autowired
	LoanManager loanManager;
	
	/**
	 * 즐겨찾기 추가
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/insertLoanGoodsChoice.json")
	public String insertLoanGoodsChoice(HttpServletRequest request, Model model, HttpSession session , GoodsForm goodsForm) {
		 CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo = new CooconGoodsFavoriteInfo();

		String no_person = (String) session.getAttribute("no_person");
		cooconGoodsFavoriteInfo.setNo_person(no_person);
		cooconGoodsFavoriteInfo.setCd_fc(goodsForm.getCd_fc());
		cooconGoodsFavoriteInfo.setCd_non_goods(goodsForm.getCd_goods());
		cooconGoodsFavoriteInfo.setYn_alliance(goodsForm.getYn_alliance());
		cooconGoodsFavoriteInfo.setId_frt(no_person);
		logger.info("============insertLoanGoodsChoice확인=============");
		logger.info(cooconGoodsFavoriteInfo.toString());

		ReturnClass returnClass = loanManager.insertLoanGoodsChoice(cooconGoodsFavoriteInfo);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 즐겨찾기 삭제
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteLoanGoodsChoice.json")
	public String deleteLoanGoodsChoice(Model model, HttpSession session , GoodsForm goodsForm) {
		 CooconGoodsFavoriteInfo cooconGoodsFavoriteInfo = new CooconGoodsFavoriteInfo();

		String no_person = (String) session.getAttribute("no_person");
		cooconGoodsFavoriteInfo.setNo_person(no_person);
		cooconGoodsFavoriteInfo.setCd_fc(goodsForm.getCd_fc());
		cooconGoodsFavoriteInfo.setCd_non_goods(goodsForm.getCd_goods());

		logger.info("============deleteLoanGoodsChoice확인=============");
		logger.info(cooconGoodsFavoriteInfo.toString());

		ReturnClass returnClass = loanManager.deleteLoanGoodsChoice(cooconGoodsFavoriteInfo);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}
}
