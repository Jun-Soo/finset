package com.koscom.loanhomemortgage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.conditionhouse.model.ConditionhouseForm;
import com.koscom.conditionhouse.service.ConditionhouseManager;
import com.koscom.domain.ConditionhouseInfo;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.service.GoodsManager;
import com.koscom.goodsbank.model.GoodsbankForm;
import com.koscom.goodsbank.model.GoodsbankVO;
import com.koscom.goodsbank.service.GoodsbankManager;
import com.koscom.util.AuthUtil;
import com.koscom.util.Constant;
import com.koscom.util.Pagination;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;


@Controller
@RequestMapping("/m/loanhomemortgage")
@PropertySource("classpath:prop/webservice.properties")
public class LoanHomeMortgageController implements Constant {
	private static final Logger logger = LoggerFactory.getLogger(LoanHomeMortgageController.class);
	
	@Autowired
	GoodsManager goodsManager;
	
	@Autowired
	GoodsbankManager goodsbankManager;

	@Autowired
	ConditionhouseManager conditionhouseManager;
	
	@Resource
	Environment environment;
	
	/** VUE
	 * 상품리스트 (제휴)
	 * @param goodsForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/listLoanAffiliates.json")
	public String listLoanAffiliatesJson(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
		logger.info("listLoanAffiliates.json == start");
		String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);
//		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_l())){
//			goodsForm.setCd_goods_class_l("02");
//		}
//		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_m())) {
//			goodsForm.setCd_goods_class_m("05,08");
//		}
		goodsForm.setCd_goods_array_m(goodsForm.getCd_goods_class_m().split(","));
		
		model.addAttribute("goodsList", goodsManager.listGoodsAllianceCredit(goodsForm));
		return "jsonView";
	}
	
	/** VUE
	 * 상품리스트 (비제휴)
	 * @param goodsbankForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/listLoanNoAffiliates.json")
	public String listLoanNoAffiliatesJson(Model model, HttpServletRequest request, GoodsbankForm goodsbankForm, HttpSession session) {
		logger.info("listLoanNoAffiliates.json == start");
		logger.info(goodsbankForm.toString());
		String no_person = (String) session.getAttribute("no_person");
		goodsbankForm.setNo_person(no_person);
//		if(StringUtil.isEmpty(goodsbankForm.getCd_goods_class_m())) {
//			goodsbankForm.setCd_goods_class_m("05,08");
//		}
		goodsbankForm.setCd_goods_array_m(goodsbankForm.getCd_goods_class_m().split(","));
		Pagination pagedList = (Pagination) goodsbankForm.setPagedList(goodsbankManager.listGoodsNoAllianceHouse(goodsbankForm), goodsbankManager.listGoodsNoAllianceHouseCount(goodsbankForm));
		model.addAttribute("pagedList", pagedList);
		return "jsonView";
	}
	
	/**
	 * 상품리스트 메인
	 * @param goodsForm
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameLoanHomeMortgageStep1.crz")
	public String frameLoanHomeMortgageStep1(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {

		logger.info(goodsForm.toString());
		String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);
		goodsForm.setPage(goodsForm.getPage());
		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_l())){
			goodsForm.setCd_goods_class_l("02");
		}
		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_m())) {
			goodsForm.setCd_goods_class_m("05,08");
		}
		goodsForm.setCd_goods_array_m(goodsForm.getCd_goods_class_m().split(","));
		
		model.addAttribute("goodsList", goodsManager.listGoodsAllianceCredit(goodsForm));
		return "jsonView";
	}
	
	/**
	 * 상품리스트 (제휴)
	 * @param goodsForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/listLoanAffiliates.crz")
	public String listLoanAffiliates(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanHomeMortgageStep1.crz", environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
		String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);
		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_l())){
			goodsForm.setCd_goods_class_l("02");
		}
		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_m())) {
			goodsForm.setCd_goods_class_m("05,08");
		}
		goodsForm.setCd_goods_array_m(goodsForm.getCd_goods_class_m().split(","));
		
		Pagination pagedList = (Pagination) goodsForm.setPagedList(goodsManager.listGoodsAllianceCredit(goodsForm), goodsManager.listGoodsAllianceCreditCount(goodsForm));
		model.addAttribute("pagedList", pagedList);
		return "/loanhomemortgage/sub/listLoanAffiliates";
	}
	
	/**
	 * 상품리스트 (비제휴)
	 * @param goodsbankForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/listLoanNoAffiliates.crz")
	public String listLoanNoAffiliates(Model model, HttpServletRequest request, GoodsbankForm goodsbankForm, HttpSession session) {
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanHomeMortgageStep1.crz", environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
		logger.info("listLoanNoAffiliates.crz == start");
		logger.info(goodsbankForm.toString());
		String no_person = (String) session.getAttribute("no_person");
		goodsbankForm.setNo_person(no_person);
		if(StringUtil.isEmpty(goodsbankForm.getCd_goods_class_m())) {
			goodsbankForm.setCd_goods_class_m("05,08");
		}
		goodsbankForm.setCd_goods_array_m(goodsbankForm.getCd_goods_class_m().split(","));
		Pagination pagedList = (Pagination) goodsbankForm.setPagedList(goodsbankManager.listGoodsNoAllianceHouse(goodsbankForm), goodsbankManager.listGoodsNoAllianceHouseCount(goodsbankForm));
		model.addAttribute("pagedList", pagedList);
		return "/loanhomemortgage/sub/listLoanNoAffiliates";
	}
	
	/**
	 * 상품 상세 조회 (제휴)
	 * @param goodsForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/frameLoanHomeMortgageStep3.crz")
	public String frameLoanHomeMortgageStep3(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanHomeMortgageStep1.crz", environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
		String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);
		GoodsVO goodsInfo = new GoodsVO();
		GoodsVO goodsVO = new GoodsVO();
		
		if(goodsForm.getCd_fc() != null && goodsForm.getCd_goods() != null){
			goodsVO.setCd_fc(goodsForm.getCd_fc());
			goodsVO.setCd_goods(goodsForm.getCd_goods());
			goodsVO.setNo_person(no_person);
			goodsInfo = goodsManager.getGoodsFavorite(goodsVO);
			logger.info("goodsInfo.toString() : "+goodsInfo.toString());
			model.addAttribute("goodsInfo", goodsInfo);
		}
		return "/loanhomemortgage/frameLoanHomeMortgageStep3";
	}
	
	/**
	 * 상품 상세 조회 (비제휴)
	 * @param goodsbankForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/frameLoanHomeMortgageStep3Bank.crz")
	public String frameLoanHomeMortgageStep3Bank(Model model, HttpServletRequest request, GoodsbankForm goodsbankForm, HttpSession session) {
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanHomeMortgageStep1.crz", environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
		String no_person = (String) session.getAttribute("no_person");
		goodsbankForm.setNo_person(no_person);
		GoodsbankVO goodsInfo = new GoodsbankVO();
		GoodsbankVO goodsVO = new GoodsbankVO();
		
		if(goodsbankForm.getCd_fc() != null && goodsbankForm.getCd_goods() != null){
			goodsVO.setCd_fc(goodsbankForm.getCd_fc());
			goodsVO.setCd_goods(goodsbankForm.getCd_goods());
			goodsVO.setNo_person(no_person);
			goodsInfo = goodsbankManager.getGoodsBankFavorite(goodsVO);
			logger.info("goodsInfo.toString() : "+goodsInfo.toString());
			model.addAttribute("goodsInfo", goodsInfo);
		}
		return "/loanhomemortgage/frameLoanHomeMortgageStep3Bank";
	}
	
	/**
	 * 부동산 담보대출 조건 검색
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/frameLoanHomeMortgageStep4.crz")
	public String frameLoanHomeMortgageStep4(HttpServletRequest request, Model model, HttpSession session){
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanHomeMortgageStep1.crz", environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
		String no_person = (String) session.getAttribute("no_person");
		ConditionhouseForm conditionhouseForm = new ConditionhouseForm(); 
		conditionhouseForm.setNo_person(no_person);
		model.addAttribute("no_person", no_person);
		model.addAttribute("conditionhouseForm", conditionhouseForm);
		model.addAttribute("conditionhouseInfo", conditionhouseManager.getConditionhouseInfo(conditionhouseForm.getNo_person()));
		return "/loanhomemortgage/frameLoanHomeMortgageStep4";
	}
	
	/**
	 * 부동산 담보대출 조건 검색 수정
	 * @param model
	 * @param request
	 * @param conditionhouseInfo
	 * @return
	 */
	@RequestMapping("/procConditionhouseInfo.json")
	public String procConditionhouseInfo(HttpServletRequest request, ConditionhouseInfo conditionhouseInfo, Model model){
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanHomeMortgageStep4.crz", environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
		ReturnClass returnClass = conditionhouseManager.procConditionhouseInfo(conditionhouseInfo);
		model.addAttribute("returnData", returnClass);
		return "jsonView";
	}
}
