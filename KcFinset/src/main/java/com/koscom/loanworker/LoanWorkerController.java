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
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.service.GoodsManager;
import com.koscom.goodsbank.model.GoodsbankForm;
import com.koscom.goodsbank.model.GoodsbankVO;
import com.koscom.goodsbank.service.GoodsbankManager;
import com.koscom.kisline.model.KisCompanyOutlineVO;
import com.koscom.kisline.service.KislineManager;
import com.koscom.loan.service.LoanManager;
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
	
	@Autowired
	LoanManager loanManager;
	
	@Autowired
	KislineManager kislineManager;
	
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
	
	/** VUE
	 * 부가정보 update
	 * @param request
	 * @param model
	 * @param txFcTransmitVO
	 * @return
	 */
	@RequestMapping("/modifyLoanAdditional.json")
	public String modifyLoanAdditional(HttpServletRequest request, Model model, TxFcTransmitVO txFcTransmitVO) {
		logger.debug("==============txFcTransmitVO==============  "+ txFcTransmitVO.toString());
		ReturnClass returnClass = loanManager.modifyLoanAdditional(txFcTransmitVO);
		model.addAttribute("message", returnClass.getMessage());
		model.addAttribute("result", returnClass.getCd_result());
		return JSON_VIEW;
	}
	
	/** VUE
	 * 직장/소득 정보 update
	 * @param request
	 * @param model
	 * @param txFcTransmitVO
	 * @return
	 */
	@RequestMapping("/updateTxFc.json")
	public String updateTxFc(HttpServletRequest request, HttpSession session, Model model, TxFcTransmitVO txFcTransmitVO) {
        String no_person = (String) session.getAttribute("no_person");
        txFcTransmitVO.setId_frt(no_person);
        txFcTransmitVO.setId_lst(no_person);
        logger.debug("==============txFcTransmitVO==============  "+ txFcTransmitVO.toString());
		KisCompanyOutlineVO kisCompanyOutlineVO = new KisCompanyOutlineVO();
		kisCompanyOutlineVO = kislineManager.getKisCompanyOutline(txFcTransmitVO.getNo_bunch());
		if(kisCompanyOutlineVO != null ){
			txFcTransmitVO.setNm_comp(kisCompanyOutlineVO.getKorentrnm());	//회사명
			if(StringUtil.isNotEmpty(kisCompanyOutlineVO.getBizno()) && StringUtil.isNotEmpty(kisCompanyOutlineVO.getCrpno())){
				txFcTransmitVO.setJb_tp_comppriv("2");	//기업구분(사업자번호 o, 법인번호 o 이면 1)
			} else if(StringUtil.isNotEmpty(kisCompanyOutlineVO.getBizno()) && StringUtil.isEmpty(kisCompanyOutlineVO.getCrpno())){
				txFcTransmitVO.setJb_tp_comppriv("1");	//기업구분(사업자번호 o, 법인번호 x 이면 2)
			}
			txFcTransmitVO.setJb_tp_listing(kisCompanyOutlineVO.getLtgmktdivcd());	//기타일 경우 9 상장여부
			txFcTransmitVO.setJb_tp_eprmdy(kisCompanyOutlineVO.getEprmdydivcd());	// 기업주체구분
			txFcTransmitVO.setJb_tp_compsize(kisCompanyOutlineVO.getScl());			//기업규모
			txFcTransmitVO.setJb_tp_etlipc(kisCompanyOutlineVO.getEtl_ipc_yn()); // YN으로 넘어오는데  1, 0 으로 들어가게 되어있음 외부감사여부
			if(StringUtil.isEmpty(kisCompanyOutlineVO.getChulja())) {
				txFcTransmitVO.setJb_tp_compchulja("0");
			} else{
				txFcTransmitVO.setJb_tp_compchulja(kisCompanyOutlineVO.getChulja());	//상호출자제한집단 여부
			}
		} else {	//일반기업이 아닐 경우 or 일반기업이지만 직장검색에서 안나온경우
			txFcTransmitVO.setJb_tp_comppriv("0");
			txFcTransmitVO.setJb_tp_listing("9");	//기타일 경우 9 상장여부
			txFcTransmitVO.setJb_tp_eprmdy("0");	// 기업주체구분
			txFcTransmitVO.setJb_tp_compsize("0");			//기업규모
			txFcTransmitVO.setJb_tp_etlipc("N"); // YN으로 넘어오는데  1, 0 으로 들어가게 되어있음 외부감사여부
			txFcTransmitVO.setJb_tp_compchulja("0");
		}
		logger.debug("txFcTransmitVO.getJb_dt_join()txFcTransmitVO.getJb_dt_join()txFcTransmitVO.getJb_dt_join()txFcTransmitVO.getJb_dt_join()txFcTransmitVO.getJb_dt_join()");
		logger.debug(txFcTransmitVO.getJb_dt_join());
		txFcTransmitVO.setCd_duty_comp(txFcTransmitVO.getCd_worker_position());
		txFcTransmitVO.setCd_job_class_l("1");
		txFcTransmitVO.setAmt_year_income(txFcTransmitVO.getAmt_year_income()*10000);	//연소득
		txFcTransmitVO.setJb_tp_mediinsure("1");
		txFcTransmitVO.setCd_loan_term("02");


		logger.debug("=======================================================11111111111111111111111111111111111111111111");
		logger.debug(txFcTransmitVO.toString());
		logger.debug("=======================================================");

		ReturnClass returnClass = loanManager.modifyLoanInfo(txFcTransmitVO);
		model.addAttribute("result", returnClass.getCd_result());
//		ReturnClass returnClass = loanManager.modifyLoanAdditional(txFcTransmitVO);
//		model.addAttribute("message", returnClass.getMessage());
//		model.addAttribute("result", returnClass.getCd_result());
		return JSON_VIEW;
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
