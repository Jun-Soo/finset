package com.koscom.loanstock;
import java.util.ArrayList;
import java.util.List;

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

import com.google.gson.Gson;
import com.koscom.common.fulltext.FulltextResultVO;
import com.koscom.conditionhouse.service.ConditionhouseManager;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.service.GoodsManager;
import com.koscom.goodsbank.service.GoodsbankManager;
import com.koscom.loan.service.LoanManager;
import com.koscom.loanstock.model.LoanStock006InVO;
import com.koscom.loanstock.model.LoanStock006OutVO;
import com.koscom.loanstock.model.LoanStock007InVO;
import com.koscom.loanstock.model.LoanStock007OutVO;
import com.koscom.loanstock.model.LoanStock012InVO;
import com.koscom.loanstock.model.LoanStock012OutVO;
import com.koscom.loanstock.model.LoanStock013InVO;
import com.koscom.loanstock.model.LoanStock013OutVO;
import com.koscom.loanstock.service.LoanStock006Service;
import com.koscom.loanstock.service.LoanStock007Service;
import com.koscom.loanstock.service.LoanStock012Service;
import com.koscom.loanstock.service.LoanStock013Service;
import com.koscom.login.service.SecureManager;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Constant;

@Controller
@RequestMapping("/m/loanstock")
@PropertySource("classpath:prop/webservice.properties")
public class LoanStockController implements Constant {
	
	private static final Logger logger = LoggerFactory.getLogger(LoanStockController.class);

	@Autowired
	GoodsManager goodsManager;
	
	@Autowired
	PersonManager personManager;
	
	@Autowired
	LoanManager loanManager;
	
	@Autowired
	private ConditionhouseManager conditionhouseManager;
	
	@Autowired
	GoodsbankManager goodsbankManager;
	
	@Autowired
	private SecureManager secureManager;

	@Resource
	Environment environment;
	
	@Autowired
    private LoanStock006Service loanStock006Service;

	@Autowired
    private LoanStock007Service loanStock007Service;

	@Autowired
    private LoanStock012Service loanStock012Service;

	@Autowired
    private LoanStock013Service loanStock013Service;

	
	/**
	 * 스탁론상품 조회정보를 조회하는 함수
	 * @param	
	 * @return	
	 * @exception 
	 */
	@RequestMapping(value = "/loanStock006List.json")
	public String LoanStock006List(Model model, LoanStock006InVO vo) {
		logger.info("*** LoanStock006List");
		
		Gson gson = new Gson();
		LoanStock006OutVO outVO = null;
		List<LoanStock006OutVO> outVOList = new ArrayList<LoanStock006OutVO>();
		String errorMessage = "";
		String outVOListJson = "";
		String inVOJson = gson.toJson(vo);
		logger.info("*****inVOJson: {} ",inVOJson);
		if (vo.getInVOList() != null ) {
			List<FulltextResultVO> resultVOList = loanStock006Service.Send(vo);
			for(FulltextResultVO resultVO : resultVOList){
				/*
				logger.info("*****");
				logger.info(resultVO.getErrorMessage());
				logger.info("resultVO.getFulltext():"+resultVO.getFulltext());
				logger.info("*****");
				
				if (resultVO.getErrorMessage().length()>=9 && "WAS ERROR".equals(resultVO.getErrorMessage().substring(0, 9))) {
					resultVO.setSuccess(true);
					resultVO.setErrorMessage(resultVO.getErrorMessage() + "\\n테스트를 위해 임시로 가짜 테이터를 표시합니다.");
					errorMessage = resultVO.getErrorMessage();
					logger.info("**  resultVO.setErrorMessage 설정 ***");
				}
				*/
				if (resultVO.isSuccess()) {
					outVO = loanStock006Service.CreateOutVO(resultVO.getFulltext());
					outVOList.add(outVO);
					
				} else {
					errorMessage = resultVO.getErrorMessage();
				}
			}
			
		}
		outVOListJson = gson.toJson(outVOList);
		model.addAttribute("inVO", inVOJson);
		model.addAttribute("outVOList", outVOListJson);
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("inFieldJson", loanStock006Service.getInFieldInfo());
		
		return "jsonView";
				//"_st/loanStock006/loanStock006List";
	}
	
	
	/**
	 * 상품 조회/조회결과 상세정보를 조회하는 함수
	 * @param	
	 * @return	
	 * @exception 
	 */
	@RequestMapping("/loanStock007View.json")
	public String LoanStock007View(Model model, LoanStock007InVO vo) {
		logger.info("*** LoanStock007View");
		LoanStock007OutVO outVO = null;
		String errorMessage = "";
		
		if(vo.getCrdtIttCd()!=null && !"".equals(vo.getCrdtIttCd())) {
			FulltextResultVO resultVO = loanStock007Service.Send(vo);
			/*
			logger.info("*****");
			logger.info(resultVO.getErrorMessage());
			logger.info("resultVO.getFulltext():"+resultVO.getFulltext());
			logger.info("*****");
			
			if (resultVO.getErrorMessage().length()>=9 && "WAS ERROR".equals(resultVO.getErrorMessage().substring(0, 9))) {
				resultVO.setSuccess(true);
				resultVO.setErrorMessage(resultVO.getErrorMessage() + "\\n테스트를 위해 임시로 가짜 테이터를 표시합니다.");
				errorMessage = resultVO.getErrorMessage();
				logger.info("**  resultVO.setErrorMessage 설정 ***");
			}
			*/
			if (resultVO.isSuccess()) {
				outVO = loanStock007Service.CreateOutVO(resultVO.getFulltext());
				
			} else {
				errorMessage = resultVO.getErrorMessage();
			}
		}
		model.addAttribute("inVO", vo);
		model.addAttribute("outVO", outVO);
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("inFieldJson", loanStock007Service.getInFieldInfo());
		
		return "jsonView";
				//"/_st/loanStock007/loanStock007View";
	}
	
	/**
	 * 스탁론상품 조회정보를 조회하는 함수
	 * @param	
	 * @return	
	 * @exception 
	 */
	@RequestMapping(value = "/loanStock012List.json")
	public String LoanStock012List(Model model, LoanStock012InVO vo) {
		logger.info("*** LoanStock012List");
		
		Gson gson = new Gson();
		LoanStock012OutVO outVO = null;
		List<LoanStock012OutVO> outVOList = new ArrayList<LoanStock012OutVO>();
		String errorMessage = "";
		String outVOListJson = "";
		String inVOJson = gson.toJson(vo);
		logger.info("*****inVOJson: {} ",inVOJson);
		if (vo.getInVOList() != null ) {
			List<FulltextResultVO> resultVOList = loanStock012Service.Send(vo);
			for(FulltextResultVO resultVO : resultVOList){
				/*
				logger.info("*****");
				logger.info(resultVO.getErrorMessage());
				logger.info("resultVO.getFulltext():"+resultVO.getFulltext());
				logger.info("*****");
				
				if (resultVO.getErrorMessage().length()>=9 && "WAS ERROR".equals(resultVO.getErrorMessage().substring(0, 9))) {
					resultVO.setSuccess(true);
					resultVO.setErrorMessage(resultVO.getErrorMessage() + "\\n테스트를 위해 임시로 가짜 테이터를 표시합니다.");
					errorMessage = resultVO.getErrorMessage();
					logger.info("**  resultVO.setErrorMessage 설정 ***");
				}
				*/
				if (resultVO.isSuccess()) {
					outVO = loanStock012Service.CreateOutVO(resultVO.getFulltext());
					outVOList.add(outVO);
					
				} else {
					errorMessage = resultVO.getErrorMessage();
				}
			}
			
		}
		outVOListJson = gson.toJson(outVOList);
		model.addAttribute("inVO", inVOJson);
		model.addAttribute("outVOList", outVOListJson);
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("inFieldJson", loanStock012Service.getInFieldInfo());
		
		return "jsonView";
//		return "_st/loanStock012/loanStock012List";
	}
	
	
	/**
	 * 상품 조회/조회결과 상세정보를 조회하는 함수
	 * @param	
	 * @return	
	 * @exception 
	 */
	@RequestMapping("/loanStock013View.json")
	public String LoanStock013View(Model model, LoanStock013InVO vo) {
		logger.info("*** LoanStock013View");
		LoanStock013OutVO outVO = null;
		String errorMessage = "";
		
		if(vo.getCrdtIttCd()!=null && !"".equals(vo.getCrdtIttCd())) {
			FulltextResultVO resultVO = loanStock013Service.Send(vo);
			/*
			logger.info("*****");
			logger.info(resultVO.getErrorMessage());
			logger.info("resultVO.getFulltext():"+resultVO.getFulltext());
			logger.info("*****");
			
			if (resultVO.getErrorMessage().length()>=9 && "WAS ERROR".equals(resultVO.getErrorMessage().substring(0, 9))) {
				resultVO.setSuccess(true);
				resultVO.setErrorMessage(resultVO.getErrorMessage() + "\\n테스트를 위해 임시로 가짜 테이터를 표시합니다.");
				errorMessage = resultVO.getErrorMessage();
				logger.info("**  resultVO.setErrorMessage 설정 ***");
			}
			*/
			if (resultVO.isSuccess()) {
				outVO = loanStock013Service.CreateOutVO(resultVO.getFulltext());
				
			} else {
				errorMessage = resultVO.getErrorMessage();
			}
		}
		model.addAttribute("inVO", vo);
		model.addAttribute("outVO", outVO);
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("inFieldJson", loanStock013Service.getInFieldInfo());
		
		return "jsonView";
//		return "/_st/loanStock013/loanStock013View";
	}
	
	/**
	 * 상품 조회/조회결과 상세정보를 조회하는 함수
	 * @param	
	 * @return	
	 * @exception 
	 */
	@RequestMapping("/loanStock008List.json")
	public String loanStock008List(Model model) {
		logger.info("*** LoanStock008List");
		
		/*if(vo.getCrdtIttCd()!=null) {
			FulltextResultVO resultVO = loanStock008Service.Send(vo);
			
			if (resultVO.isSuccess()) {
				model.addAttribute("outVO", loanStock008Service.CreateOutVO(resultVO.getFulltext()));
				model.addAttribute("errorMessage", "");
			} else {
				model.addAttribute("outVO", null);
				model.addAttribute("errorMessage", resultVO.getErrorMessage());
			}
			// SEND
		}*/
		
		return "jsonView";
//		return "/_st/loanStock008/loanStock008List";
	}
	
	/**
	 * 보유주식확인(인증서선택)
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/frameLoanStockStep1.crz")
	public String frameLoanStockStep1(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        
		logger.info("frameLoanStockStep1");
		return "/loanstock/frameLoanStockStep1";
	}
	
	/**
	 * 공인인증서비밀번호입력
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/frameLoanStockStep2.crz")
	public String frameLoanStockStep2(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        
		logger.info("frameLoanStockStep2");
		return "/loanstock/frameLoanStockStep2";
	}
	
	/**
	 * 약관동의(오픈API)
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/frameLoanStockStep3.crz")
	public String frameLoanStockStep3(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        
		logger.info("frameLoanStockStep3");
		return "/loanstock/frameLoanStockStep3";
	}
	
	/**
	 * 보유주식현황
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/frameLoanStockStep4.crz")
	public String frameLoanStockStep4(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        
		logger.info("frameLoanStockStep4");
		return "/loanstock/frameLoanStockStep4";
	}
	
	/**
	 * 상품선택
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/frameLoanStockStep5.crz")
	public String frameLoanStockStep5(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        
		logger.info("frameLoanStockStep5");
		return "/loanstock/frameLoanStockStep5";
	}
	
	/**
	 * 계좌번호입력
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/frameLoanStockStep6.crz")
	public String frameLoanStockStep6(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
		String cd_fc = "0013002";
		String cd_goods = "BNK003";
		GoodsVO gvo = goodsManager.getGoodsInfo(cd_fc, cd_goods);
		model.addAttribute("gvo", gvo);
		logger.info("frameLoanStockStep6");
		return "/loanstock/frameLoanStockStep6";
	}
	
	/**
	 * 공인인증_본인확인
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/frameLoanStockStep7.crz")
	public String frameLoanStockStep7(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        
		logger.info("frameLoanStockStep7");
		return "/loanstock/frameLoanStockStep7";
	}
	
	/**
	 * 공인인증_본인확인_비번입력
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/frameLoanStockStep8.crz")
	public String frameLoanStockStep8(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        
		logger.info("frameLoanStockStep8");
		return "/loanstock/frameLoanStockStep8";
	}
	
	/**
	 * 조회대기
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/frameLoanStockStep9.crz")
	public String frameLoanStockStep9(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        
		logger.info("frameLoanStockStep9");
		return "/loanstock/frameLoanStockStep9";
	}
	
	/**
	 * 조회결과
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/frameLoanStockStep10.crz")
	public String frameLoanStockStep10(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        
		logger.info("frameLoanStockStep10");
		return "/loanstock/frameLoanStockStep10";
	}
	
	/**
	 * 조회결과_상세
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 */
	@RequestMapping("/frameLoanStockStep11.crz")
	public String frameLoanStockStep11(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session) {
        
		logger.info("frameLoanStockStep11");
		return "/loanstock/frameLoanStockStep11";
	}
	


}