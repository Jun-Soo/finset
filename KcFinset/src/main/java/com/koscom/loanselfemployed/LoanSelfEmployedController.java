package com.koscom.loanselfemployed;

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

import com.koscom.conditionbiz.model.ConditionbizForm;
import com.koscom.conditionbiz.service.ConditionbizManager;
import com.koscom.domain.ConditionbizInfo;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.service.GoodsManager;
import com.koscom.goodsbank.model.GoodsbankForm;
import com.koscom.goodsbank.model.GoodsbankVO;
import com.koscom.goodsbank.service.GoodsbankManager;
import com.koscom.util.AuthUtil;
import com.koscom.util.Constant;
import com.koscom.util.LogUtil;
import com.koscom.util.Pagination;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;

@Controller
@RequestMapping("/m/loanselfemployed")
@PropertySource("classpath:prop/webservice.properties")
public class LoanSelfEmployedController implements Constant{
	private static final Logger logger = LoggerFactory.getLogger(LoanSelfEmployedController.class);
	
	@Autowired
	GoodsManager goodsManager;
	
	@Autowired
	GoodsbankManager goodsbankManager;
	
	@Autowired
	ConditionbizManager conditionbizManager;
	
	@Resource
	Environment environment;
	
	/**
	 * 신용대출 (제휴상품)
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/frameLoanSelfEmployedStep1.crz")
	public String frameLoanSelfEmployedStep1(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanWorkerStep1.crz", environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
		logger.info(goodsForm.toString());
		String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);
		goodsForm.setPage(goodsForm.getPage());
		String curTab = goodsForm.getCurTab();
		String st = goodsForm.getSt();
        LogUtil.debugLn(logger,"curTab="+curTab);
        curTab = (curTab == null || curTab.equals(""))? "affiliates": curTab;
		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_l())){
			goodsForm.setCd_goods_class_l("01");
		}
		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_m())) goodsForm.setCd_goods_class_m("02,03,08,09");
		goodsForm.setCd_goods_array_m(goodsForm.getCd_goods_class_m().split(","));
		LogUtil.debugLn(logger,"curTab="+curTab);

		model.addAttribute("curTab", curTab);
		model.addAttribute("st", st);
		return "/loanselfemployed/frameLoanSelfEmployedStep1";
	}
	
	/**
	 * 상품리스트 메인
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listLoanAffiliates.crz")
	public String listLoanAffiliates(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanSelfEmployedStep1.crz", environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
		String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);
		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_l())){
			goodsForm.setCd_goods_class_l("01");
		}
		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_m())) goodsForm.setCd_goods_class_m("02,03,08,09");
		goodsForm.setCd_goods_array_m(goodsForm.getCd_goods_class_m().split(","));
		Pagination pagedList = (Pagination) goodsForm.setPagedList(goodsManager.listGoodsAllianceBiz(goodsForm), goodsManager.listGoodsAllianceBizCount(goodsForm));
		model.addAttribute("pagedList", pagedList);
		return "/loanselfemployed/sub/listLoanAffiliates";
	}
	
	/**
	 * 상품리스트 메인
	 * @param model
	 * @param goodsbankForm
	 * @param session
	 * @return
	 */
	@RequestMapping("/listLoanNoAffiliates.crz")
	public String listLoanNoAffiliates(Model model, HttpServletRequest request, GoodsbankForm goodsbankForm, HttpSession session) {
		logger.info("listLoanNoAffiliates.crz == start");
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanSelfEmployedStep1.crz", environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
		logger.info(goodsbankForm.toString());
		String no_person = (String) session.getAttribute("no_person");
		goodsbankForm.setNo_person(no_person);
		if(StringUtil.isEmpty(goodsbankForm.getCd_goods_class_m())) goodsbankForm.setCd_goods_class_m("02,03,08,09");
		goodsbankForm.setCd_goods_array_m(goodsbankForm.getCd_goods_class_m().split(","));
		Pagination pagedList = (Pagination) goodsbankForm.setPagedList(goodsbankManager.listGoodsNoAllianceBiz(goodsbankForm), goodsbankManager.listGoodsNoAllianceBizCount(goodsbankForm));
		model.addAttribute("pagedList", pagedList);
		return "/loanselfemployed/sub/listLoanNoAffiliates";
	}
	
	/**
	 * 상품상세 (제휴)
	 * @param goodsForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/frameLoanSelfEmployedStep2.crz")
	public String frameLoanSelfEmployedStep2(HttpServletRequest request,Model model, GoodsForm goodsForm, HttpSession session) {
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanSelfEmployedStep1.crz",environment);
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
		return "/loanselfemployed/frameLoanSelfEmployedStep2";
	}
	
	/**
	 * 상품상세 (비제휴)
	 * @param goodsbankForm
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/frameLoanSelfEmployedStep2Bank.crz")
	public String frameLoanWorkerStep3Bank(Model model, GoodsbankForm goodsbankForm, HttpSession session) {
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
		return "/loanselfemployed/frameLoanSelfEmployedStep2Bank";
	}
	
	/**
	 * 신용대출(개인사업자) 조건 검색
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/frameLoanSelfEmployedStep3.crz")
	public String frameLoanSelfEmployedStep3(HttpServletRequest request, Model model, GoodsbankForm goodsbankForm, HttpSession session){
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanSelfEmployedStep1.crz",environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
		String no_person = (String) session.getAttribute("no_person");
		ConditionbizForm conditionbizForm = new ConditionbizForm(); 
		conditionbizForm.setNo_person(no_person);

		model.addAttribute("no_person", no_person);
		model.addAttribute("conditioncreditForm", conditionbizForm);
		model.addAttribute("conditionbizInfo", conditionbizManager.getConditionbizInfo(conditionbizForm.getNo_person()));

		return "/loanselfemployed/frameLoanSelfEmployedStep3";
	}
	
	/**
	 * 신용대출(개인사업자) 조건 검색 수정
	 * @param model
	 * @param request
	 * @param conditionhouseInfo
	 * @return
	 */
	@RequestMapping("/procConditionbizInfo.json")
	public String procConditionbizInfo(HttpServletRequest request, ConditionbizInfo conditionbizInfo, Model model){
		ReturnClass returnClass = conditionbizManager.procConditionbizInfo(conditionbizInfo);
		model.addAttribute("returnData", returnClass);
		return "jsonView";
	}	
}
