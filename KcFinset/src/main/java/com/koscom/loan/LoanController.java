package com.koscom.loan;

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

import com.koscom.domain.CooconGoodsFavoriteInfo;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;
import com.koscom.loan.service.LoanManager;
import com.koscom.login.service.SecureManager;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.util.AuthUtil;
import com.koscom.util.Constant;
import com.koscom.util.LogUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;

@Controller
@RequestMapping("/m/loan")
@PropertySource("classpath:prop/webservice.properties")
public class LoanController implements Constant {
	
	private static final Logger logger = LoggerFactory.getLogger(LoanController.class);
	
	@Autowired
	LoanManager loanManager;
	
	@Autowired
	PersonManager personManager;
	
	@Autowired
	SecureManager secureManager;
	
	@Resource
	Environment environment;
	
	/** VUE
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

	/** VUE
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
	
	/** VUE
	 * 개인신용대출  본인인증
	 * @param txFcTransmitVO
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/getLoanPersonCertInfo.json")
	public String getLoanPersonCertInfo(Model model, HttpServletRequest request, HttpSession session) {
		String no_person = (String) session.getAttribute("no_person");
		PersonVO personVO = personManager.getPersonInfo(no_person);
		String hp        = null;
		String nm_person = null;
		String telComCd  = null;
		String bgn       = null;
		String cd_tel     = null;
		hp        = personVO.getHp       ();
		nm_person = personVO.getNm_person();
		telComCd  = personVO.getCd_tel   ();
		bgn       = personVO.getBgn      ();
		cd_tel    = personVO.getCd_tel   ();
		LogUtil.debugLn(logger,"telComCd="+telComCd);
		String birthDay = (bgn != null && bgn.length()==9)? bgn.substring(2,8):"";
		LogUtil.debugLn(logger,"bgn="+bgn);
		String sex = (bgn != null && bgn.length()>0)?bgn.substring(bgn.length()-1):"";
		LogUtil.debugLn(logger,"bgn="+bgn);
		model.addAttribute("hp"              , hp             );
		model.addAttribute("nm_person"       , nm_person      );
		model.addAttribute("telComCd"        , telComCd       );
		model.addAttribute("cd_tel"          , cd_tel         );
		model.addAttribute("birthDay"        , birthDay       );
		model.addAttribute("sex"             , sex            );
		model.addAttribute("personVO", personVO);
		return "jsonView";
	}
	
	/**
	 * 신청인 정보 등록
	 * @param request
	 * @param model
	 * @param pTxFcTransmitVO
	 * @param session
	 * @return
	 */
	@RequestMapping("/insertTxFc.json")
	public String insertTxFc(HttpServletRequest request, Model model, TxFcTransmitVO pTxFcTransmitVO, HttpSession session) {

        String no_person = (String) session.getAttribute("no_person");
		TxFcTransmitVO txFcTransmitVO = pTxFcTransmitVO;
		String encPwd = request.getParameter("encPwd");
        LogUtil.debugLn(logger,"==============insertTxFc:no_person="+ no_person);
        //logger.debug("==============insertTxFc:txFcTransmitVO=  "+ txFcTransmitVO.toString());
        String ssn_person = null;
		String site = (environment != null)?environment.getProperty("service.profile"):"";
		if (!"LOCAL".equals(site) && StringUtil.isNotEmpty( encPwd ) ) {
			// 보안키패드 복호화
			String ssn1 = request.getParameter("ssn1");
			String decPwd = secureManager.getDecodedPassword(encPwd);
            ssn_person = String.format("%1$s%2$s", ssn1, decPwd);
            txFcTransmitVO.setSsn_person(ssn_person);
		}
		//logger.debug("==============insertTxFc:txFcTransmitVO=  "+ txFcTransmitVO.toString());
        //대출 금리/한도조회 상품 목록
        String[] cd_fc = null;
        String[] cd_goods = null;
        List<GoodsVO> listFcGoods = new ArrayList<GoodsVO>();
        String _cd_goods = pTxFcTransmitVO.getCd_goods();
        String _cd_fc    = pTxFcTransmitVO.getCd_fc();
        if(    _cd_goods != null && StringUtil.isNotEmpty(_cd_goods)
                && _cd_fc    != null && StringUtil.isNotEmpty(_cd_fc   )){
            cd_fc = _cd_fc.split(",");
            cd_goods = _cd_goods.split(",");
        }
        logger.debug("------------------_cd_fc---------------  : "  + _cd_fc);
        logger.debug("------------------_cd_goods---------------  : "  + _cd_goods);
        txFcTransmitVO.setId_frt(no_person);
        txFcTransmitVO.setId_lst(no_person);
        if(cd_fc != null && cd_fc.length > 0 && cd_goods != null && cd_goods.length > 0) {
            for(int i = 0; i < cd_fc.length; i++) {
                GoodsVO goodsVO = new GoodsVO();
                goodsVO.setCd_fc(cd_fc[i]);
                goodsVO.setCd_goods(cd_goods[i]);
                goodsVO.setId_frt(no_person);
                listFcGoods.add(goodsVO);
            }
            txFcTransmitVO.setListGoods(listFcGoods);
        }

		PersonVO personVO = personManager.getPersonInfo(no_person);
		String bgn = personVO.getBgn();
		bgn = bgn.substring(0, bgn.length()-1);
		txFcTransmitVO.setNo_person(no_person);
		txFcTransmitVO.setId_frt   (no_person);
		txFcTransmitVO.setHp1(personVO.getHp());
		txFcTransmitVO.setYmd_birth(bgn);
		ReturnClass returnClass = loanManager.insertLoanInfo(txFcTransmitVO);
		txFcTransmitVO = (TxFcTransmitVO) returnClass.getReturnObj();
		logger.debug("------------------seq---------------  : "  + txFcTransmitVO.getNo_bunch());
		model.addAttribute("ssn_person", ssn_person);
		model.addAttribute("no_bunch", txFcTransmitVO.getNo_bunch());
		model.addAttribute("message", returnClass.getMessage());
		model.addAttribute("result", returnClass.getCd_result());

		return JSON_VIEW;
	}
}
