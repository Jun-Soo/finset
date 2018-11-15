package com.koscom.loanworker;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.conditioncredit.model.ConditioncreditForm;
import com.koscom.conditioncredit.service.ConditioncreditManager;
import com.koscom.domain.ConditioncreditInfo;
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
@RequestMapping("/m/loanworker")
@PropertySource("classpath:prop/webservice.properties")
public class LoanWorkerController implements Constant{
	
	private static final Logger logger = LoggerFactory.getLogger(LoanWorkerController.class);
	
	@Resource
    public Environment environment;
	
	@Autowired
	GoodsManager goodsManager;
	
	@Autowired
	GoodsbankManager goodsbankManager;
	
	@Autowired
	ConditioncreditManager conditioncreditManager;
	
	
	/** VUE
	 * 상품리스트 (제휴)
	 * @param goodsForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/listLoanAffiliates.json")
	public String listLoanAffiliatesJson(Model model, HttpServletRequest request, HttpServletResponse response, GoodsForm goodsForm, HttpSession session) {
        logger.debug("listLoanAffiliates.json == start");
		logger.debug(goodsForm.toString());
		String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);
//		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_l())){
//			goodsForm.setCd_goods_class_l("01");
//		}
//		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_m())) {
//			goodsForm.setCd_goods_class_m("01,03,08,09");
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
		logger.debug("listLoanNoAffiliates.json == start");
		logger.debug(goodsbankForm.toString());
		String no_person = (String) session.getAttribute("no_person");
		
		goodsbankForm.setNo_person(no_person);
//		if(StringUtil.isEmpty(goodsbankForm.getCd_goods_class_m())) {
//			goodsbankForm.setCd_goods_class_m("01,03,08,09");
//		}
		goodsbankForm.setCd_goods_array_m(goodsbankForm.getCd_goods_class_m().split(","));
		
		int count = goodsbankManager.listGoodsNoAllianceCreditCount(goodsbankForm);
		Pagination pagedList = goodsbankForm.setPagedList(goodsbankManager.listGoodsNoAllianceCredit(goodsbankForm),count);
		model.addAttribute("pagedList", pagedList);
		model.addAttribute("count", count);
		return "jsonView";
	}
	
	/**
	 * 상품상세 (제휴) VUE
	 * @param goodsForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/getLoanAffiliatesDetail.json")
	public String getLoanAffiliatesDetail(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
     	String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);
		GoodsVO goodsInfo = new GoodsVO();
		GoodsVO goodsVO = new GoodsVO();

		if(goodsForm.getCd_fc() != null && goodsForm.getCd_goods() != null){
			goodsVO.setCd_fc(goodsForm.getCd_fc());
			goodsVO.setCd_goods(goodsForm.getCd_goods());
			goodsVO.setNo_person(no_person);
			goodsInfo = goodsManager.getGoodsFavorite(goodsVO);
			logger.debug("goodsInfo.toString() : "+goodsInfo.toString());
			model.addAttribute("goodsInfo", goodsInfo);
		}
		return "jsonView";
	}

	/** VUE
	 * 상품상세 (비제휴)
	 * @param goodsbankForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/getLoanNoAffiliatesDetail.json")
	public String getLoanNoAffiliatesDetail(Model model, HttpServletRequest request, GoodsbankForm goodsbankForm, HttpSession session) {
		String no_person = (String) session.getAttribute("no_person");
		goodsbankForm.setNo_person(no_person);
		GoodsbankVO goodsInfo = new GoodsbankVO();
		GoodsbankVO goodsVO = new GoodsbankVO();
		if(goodsbankForm.getCd_fc() != null && goodsbankForm.getCd_goods() != null){
			goodsVO.setCd_fc(goodsbankForm.getCd_fc());
			goodsVO.setCd_goods(goodsbankForm.getCd_goods());
			goodsVO.setNo_person(no_person);
			goodsInfo = goodsbankManager.getGoodsBankFavorite(goodsVO);
			goodsInfo.setNm_fc(goodsInfo.getNm_fc().trim());
			logger.debug("goodsInfo.toString() : "+goodsInfo.toString());
			model.addAttribute("goodsInfo", goodsInfo);
		}
		return "jsonView";
	}
	
	/**
	 * 대출상품조회(직장인, 사업자, 주택) 선택
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/frameLoanWorkerStep1.crz")
	public String frameLoanWorkerStep1(Model model,HttpSession session,  HttpServletRequest request, HttpServletResponse response) {
		return "/loanworker/frameLoanWorkerStep1";
	}
	
	/**
	 * 상품리스트 메인
	 * @param goodsForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/frameLoanWorkerStep2.crz")
	public String frameLoanWorkerStep2(Model model, HttpServletRequest request, HttpServletResponse response, GoodsForm goodsForm, HttpSession session) {
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanWorkerStep1.crz", environment);
        if(isAuth == false) {return Constant.NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
        logger.debug(goodsForm.toString());
		String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);
		goodsForm.setPage(goodsForm.getPage());
        if(StringUtil.isEmpty(goodsForm.getCd_goods_class_l())){
			goodsForm.setCd_goods_class_l("01");
		}
        if(StringUtil.isEmpty(goodsForm.getCd_goods_class_m())) {
			goodsForm.setCd_goods_class_m("01,03,08,09");
		}
		goodsForm.setCd_goods_array_m(goodsForm.getCd_goods_class_m().split(","));
		
//		Pagination pagedList = (Pagination) goodsForm.setPagedList(goodsManager.listGoodsAllianceCredit(goodsForm), goodsManager.listGoodsAllianceCreditCount(goodsForm));
//		model.addAttribute("pagedList", pagedList);
        return "/loanworker/frameLoanWorkerStep2";
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
	public String listLoanAffiliates(Model model, HttpServletRequest request, HttpServletResponse response, GoodsForm goodsForm, HttpSession session) {
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanWorkerStep2.crz",environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
        logger.debug("listLoanAffiliates.crz == start");
		logger.debug(goodsForm.toString());
		String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);
		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_l())){
			goodsForm.setCd_goods_class_l("01");
		}
		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_m())) {
			goodsForm.setCd_goods_class_m("01,03,08,09");
		}
		goodsForm.setCd_goods_array_m(goodsForm.getCd_goods_class_m().split(","));
		
		if(StringUtil.isEmpty(goodsForm.getCd_goods_class_m())){
			goodsForm.setCd_goods_class_m("01");
		}
        int count =  goodsManager.listGoodsAllianceCreditCount(goodsForm);
        Pagination pagedList = (Pagination) goodsForm.setPagedList(goodsManager.listGoodsAllianceCredit(goodsForm),count);

		model.addAttribute("pagedList", pagedList);
		model.addAttribute("count", count);
		return "/loanworker/sub/listLoanAffiliates";
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
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanWorkerStep2.crz",environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
		logger.debug("listLoanNoAffiliates.crz == start");
		logger.debug(goodsbankForm.toString());
		String no_person = (String) session.getAttribute("no_person");
		
		goodsbankForm.setNo_person(no_person);
		if(StringUtil.isEmpty(goodsbankForm.getCd_goods_class_m())) {
			goodsbankForm.setCd_goods_class_m("01,03,08,09");
		}
		goodsbankForm.setCd_goods_array_m(goodsbankForm.getCd_goods_class_m().split(","));
		
		int count = goodsbankManager.listGoodsNoAllianceCreditCount(goodsbankForm);
		Pagination pagedList = goodsbankForm.setPagedList(goodsbankManager.listGoodsNoAllianceCredit(goodsbankForm),count);
		model.addAttribute("pagedList", pagedList);
		model.addAttribute("count", count);
		return "/loanworker/sub/listLoanNoAffiliates";
	}
	
	
	
	/**
	 * 상품상세 (제휴)
	 * @param goodsForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/frameLoanWorkerStep3.crz")
	public String frameLoanWorkerStep3(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanWorkerStep2.crz",environment);
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
			logger.debug("goodsInfo.toString() : "+goodsInfo.toString());
			model.addAttribute("goodsInfo", goodsInfo);
		}
		return "/loanworker/frameLoanWorkerStep3";
	}

	/**
	 * 상품상세 (비제휴)
	 * @param goodsbankForm
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/frameLoanWorkerStep3Bank.crz")
	public String frameLoanWorkerStep3Bank(Model model, HttpServletRequest request, GoodsbankForm goodsbankForm, HttpSession session) {
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanWorkerStep2.crz",environment);
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
			goodsInfo.setNm_fc(goodsInfo.getNm_fc().trim());
			logger.debug("goodsInfo.toString() : "+goodsInfo.toString());
			model.addAttribute("goodsInfo", goodsInfo);
		}
		return "/loanworker/frameLoanWorkerStep3Bank";
	}
	
	/**
	 * 개인신용대출  조건검색
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/frameLoanWorkerStep4.crz")
	public String frameLoanWorkerStep4(HttpServletRequest request, Model model, GoodsbankForm goodsbankForm, HttpSession session){
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanWorkerStep2.crz",environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
		String no_person = (String) session.getAttribute("no_person");
		ConditioncreditForm conditioncreditForm = new ConditioncreditForm();
		conditioncreditForm.setNo_person(no_person);
		model.addAttribute("no_person", no_person);
		model.addAttribute("conditioncreditForm", conditioncreditForm);
		model.addAttribute("conditioncreditInfo", conditioncreditManager.getConditioncreditInfo(conditioncreditForm.getNo_person()));
		return "/loanworker/frameLoanWorkerStep4";
	}

	/**
	 * 개인신용대출  조건 수정 /등록
	 * @param conditioncreditInfo
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/procConditioncreditInfo.json")
	public String procConditioncreditInfo(HttpServletRequest request, ConditioncreditInfo conditioncreditInfo, Model model, HttpSession session){
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanWorkerStep2.crz",environment);
        if(isAuth == false) {return NOT_AUTH_PAGE;}
        /**
         * 접근제어 : end
         */
        ReturnClass returnClass = conditioncreditManager.procConditioncreditInfo(conditioncreditInfo);
		model.addAttribute("returnData", returnClass);
		return "jsonView";
	}
   
}
