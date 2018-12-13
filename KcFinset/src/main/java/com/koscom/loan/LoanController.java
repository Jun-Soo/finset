package com.koscom.loan;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.koscom.apply.model.ApplyVO;
import com.koscom.domain.CooconGoodsFavoriteInfo;
import com.koscom.domain.KisCompanyOutlineInfo;
import com.koscom.domain.KisSrchByNameInfo;
import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.finance.service.FinanceManager;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.finset.model.FinsetForm;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsFormNSrch;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.service.GoodsManager;
import com.koscom.kisline.model.KisCompanyOutlineItemsVO;
import com.koscom.kisline.model.KisCompanyOutlineVO;
import com.koscom.kisline.model.KisCompanySrchVO;
import com.koscom.kisline.model.KisSrchByNameVO;
import com.koscom.kisline.model.KislineForm;
import com.koscom.kisline.service.KislineManager;
import com.koscom.loan.service.LoanManager;
import com.koscom.login.service.SecureManager;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.pusheach.model.PushEachVO;
import com.koscom.pusheach.service.PushEachManager;
import com.koscom.util.*;

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
	
	@Autowired
	KislineManager kislineManager;
	
	@Autowired
	FinanceManager financeManager;
	
	@Autowired
	PushEachManager pushEachManager;
	
	@Autowired
	FincorpManager fincorpManager;
	
	@Autowired
	GoodsManager goodsManager;
		
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
	@RequestMapping("/listLoanAffiliatesAll.json")
	public String listLoanAffiliatesAll(Model model, HttpServletRequest request, HttpServletResponse response, GoodsForm goodsForm, HttpSession session) {
        logger.debug("listLoanAffiliatesAll.json == start");
		logger.debug(goodsForm.toString());
		String no_person = (String) session.getAttribute("no_person");
		goodsForm.setNo_person(no_person);
		
		model.addAttribute("goodsList", goodsManager.listGoodsAllianceAll(goodsForm));
		return "jsonView";
	}
	
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
	
	/** VUE
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
	
	
	
	/** VUE
	 * 직장검색 결과 페이지
	 * @param kislineForm
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/listJobResult.json")
	public String listJobResult(Model model, HttpServletRequest request, KislineForm kislineForm) {
		try {
	        Double.parseDouble(kislineForm.getTxt_detail());
	        if(kislineForm.getTxt_detail().length() >= 5){
	        	logger.debug("사업자번호로 검색");
	        	kislineForm.setBizno(kislineForm.getTxt_detail());
	        } else{
	        	logger.debug("직장명으로 검색");
	        	kislineForm.setNm(kislineForm.getTxt_detail());
	        }
	    } catch (NumberFormatException e) {
	    	logger.debug("직장명으로 검색");
	    	kislineForm.setNm(kislineForm.getTxt_detail());
	    }
		String nm = kislineForm.getNm();//"크리젠";
		String bizno = kislineForm.getBizno();//"";
		String crpno = kislineForm.getCrpno();//"";
		String prn_rst_cnt = "10";//"";
		int page = kislineForm.getPage();//"";
		String realPage = page+"";
		String pge_st_no = realPage;//"";
		KisSrchByNameInfo kisSrchByNameInfo = new KisSrchByNameInfo();
		List<KisSrchByNameVO> list = new ArrayList();
		try {
			kisSrchByNameInfo = getKisSrchByName(nm, bizno, crpno, prn_rst_cnt, pge_st_no);
			//샘플
			logger.debug("count" + kisSrchByNameInfo.getItems().count);
			logger.debug("realtotalcount" + kisSrchByNameInfo.getItems().realtotalcount);
			logger.debug("searchTime" + kisSrchByNameInfo.getItems().searchTime);
			logger.debug("totalcount" + kisSrchByNameInfo.getItems().totalcount);
			if(kisSrchByNameInfo.getItems().totalcount > 0){
				logger.debug("getItems" + kisSrchByNameInfo.getItems().getItem());
				list = kisSrchByNameInfo.getItems().getItem();
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Pagination pagedList = (Pagination) kislineForm.setPagedList(list, kisSrchByNameInfo.getItems().totalcount);
		logger.debug("pagedList");
		logger.debug("pagedList");
		logger.debug("pagedList");
		logger.debug("pagedList");
		logger.debug("pagedList");
		logger.debug("pagedList="+pagedList);
		model.addAttribute("pagedList", pagedList);
		return JSON_VIEW;
	}
	
	/** VUE
	 * 개인신용대출 대출가능 상품 리스트, 전문 송/수신
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/reqFinanceInfo.json")
	public String reqFinanceInfo(Model model, HttpServletRequest request, GoodsForm goodsForm, HttpSession session, FinsetForm finsetForm) throws FinsetException, IOException{
		String no_person      = (String) session.getAttribute("no_person");
        String title          = null;// 제목
        String body           = null;// 내용
        String push_divcd     = "01";//푸쉬구분코드 : 일반
        String fcm_token      =null;
        String url            = null;
        String loan_code      = null;

        PersonVO   personVO   = null;
        PushEachVO pushEachVO = null;
        GoodsVO    goodsVO    = null;
		goodsForm.setNo_person(no_person);
		goodsForm.setYn_use   ("Y"      );
		
		//대출 금리/한도조회 상품 목록
		String[] cd_fc    = null;
		String[] cd_goods = null;
        String _cd_fc     = null;
        String _cd_goods  = null;

        _cd_goods = goodsForm.getCd_goods ();
        _cd_fc    = goodsForm.getCd_fc    ();
        loan_code = goodsForm.getLoan_code();

        /**
         * 로그추가
         */
		logger.debug("reqFinanceInfo.crz:선택한 대출상품리스트 : " + _cd_goods);
		logger.debug("reqFinanceInfo.crz:선택한 대출업체리스트 : " + _cd_fc   );
		logger.debug("reqFinanceInfo.crz:선택한 loan_code      : " + loan_code);
		logger.debug("no_person : " + no_person   );
		String site = (environment != null)?environment.getProperty("service.profile"):"";
		LogUtil.debugLn(logger,"site="+site);
		boolean isSuccess = true;
		String message = "한도조회가 완료되었습니다.";
		String error_message = "한도조회중 오류가 발생하였습니다.";
		String exception_desc = null;
        TxFcReceiveVO txFcReceiveVO = null;
        TxFcTransmitVO txFcTransmitVO = null;
		if(    _cd_goods != null && StringUtil.isNotEmpty(_cd_goods)
			&& _cd_fc    != null && StringUtil.isNotEmpty(_cd_fc   )){
			cd_fc    = goodsForm.getCd_fc().split(",");
			cd_goods = goodsForm.getCd_goods().split(",");
		}
		logger.debug("reqFinanceInfo.crz: cd_goods : " + cd_goods);
		List<GoodsVO> listFcGoods = new ArrayList<GoodsVO>();
		if(cd_fc != null && cd_fc.length > 0 && cd_goods != null && cd_goods.length > 0) {
			for(int i = 0; i < cd_fc.length; i++) {
                goodsVO = new GoodsVO();
                goodsVO.setCd_fc   (cd_fc[i]   );
				goodsVO.setCd_goods(cd_goods[i]);
				goodsVO.setId_frt  (no_person  );
				listFcGoods.add(goodsVO);
			}
			logger.debug("대출상품리스트 건수 : " + listFcGoods.size());
            txFcTransmitVO = loanManager.getTxFcTransmitInfoForMsg(goodsForm.getNo_bunch());
            txFcTransmitVO.setListGoods(listFcGoods);
			txFcTransmitVO.setLoan_code(loan_code);
			txFcTransmitVO.setNo_person(no_person);
			txFcTransmitVO.setId_frt   (no_person);

			logger.debug("============> 1차한도조회 시작: "  + txFcTransmitVO + " > KCB DI : " +txFcTransmitVO.getKcb_di() );
			ReturnClass rc = null;
			ArrayList<Throwable> listErr = null;
			try {
                /**
                 * 조회전 조회중 데이터 등록
                 */
				financeManager.setFinsetForReady(txFcTransmitVO);
                /**
                 * 조회 실행
                 */
				rc = financeManager.reqFinanceInfo(txFcTransmitVO);
				if (rc != null) {
					if( Constant.FAILED.equals(rc.getCd_result())) {
						logger.debug("EDOC ERROR " + rc.getDes_message());
						model.addAttribute("result", Constant.FAILED);
						model.addAttribute("errorMsg", rc.getDes_message());
					} else if(Constant.SUCCESS.equals(rc.getCd_result())) {
						txFcReceiveVO = (TxFcReceiveVO)rc.getReturnObj();
						listErr = txFcReceiveVO.getListErr();
						logger.debug("============> 1차한도조회 결과 응답: "  + txFcReceiveVO);
						model.addAttribute("result", Constant.SUCCESS);
					}
				}
				if(listErr != null && listErr.size() > 0) {
				    isSuccess = false;
                }
//				throw new FinsetException("TEST 에러");
			} catch (IOException e) {
				isSuccess = false;
//				message = e.getMessage();
				exception_desc = e.getMessage();
				LogUtil.error(logger,e);
			} catch (FinsetException e) {
				isSuccess = false;
//				message = e.getMessage();
				exception_desc = e.getMessage();
				LogUtil.error(logger,e);
			} catch (FinsetMessageException e) {
				isSuccess = true;
//				message = e.getMessage();
				exception_desc = e.getMessage();
				LogUtil.error(logger,e);
			} catch (RuntimeException e) {
				isSuccess = false;
//				message = e.getMessage();
				exception_desc = e.getMessage();
				LogUtil.error(logger,e);
			} catch (Exception e) {
				isSuccess = false;
//				message = e.getMessage();
				exception_desc = e.getMessage();
				LogUtil.error(logger,e);
			} finally {
				if (isSuccess == true) {
					body = message;
				} else {
					body = error_message;
//					txFcReceiveVO = new TxFcReceiveVO();
//					txFcReceiveVO.setHd_cd_result(-1);
//					txFcReceiveVO.setHd_err_msg(exception_desc);
//					/**
//					 * 조회후 에러 데이터 등록
//					 */
//					financeManager.setFinsetForFail(txFcReceiveVO,txFcTransmitVO);
				}
			}
		} else {
			model.addAttribute("errorMsg", "대출상품이 없습니다.");
			logger.debug("대출상품리스트 건수 : 0");
			model.addAttribute("count", 0);
		}

		model.addAttribute("no_bunch" , goodsForm.getNo_bunch());
		model.addAttribute("isSuccess", isSuccess              );
		model.addAttribute("message"  , message                );
		/**
		 * push 전송로직
		 */
        personVO = personManager.getPersonInfo(no_person);
        title    = "[상품조회결과]";
        url = "/mypage/rstlInqGoods";
        if (personVO != null) {
            pushEachVO = new PushEachVO();
            fcm_token = personVO.getFcm_token();
            if (fcm_token != null && !fcm_token.equals("")) {
                pushEachVO.setSendTo    (fcm_token    );
                pushEachVO.setNo_person (no_person    );
                pushEachVO.setTitle     (title        );
                pushEachVO.setBody      (body         );
                pushEachVO.setPush_divcd(push_divcd   );//일반
                pushEachVO.setLink_addr (url          );
                logger.debug("@@@@SendTo())"+fcm_token);

                if(FcmUtil.sendFcm(  fcm_token
                        , title
                        , body
                        , url
                        , StringUtil.nullToString(personVO.getYn_os(), "1")
                        , StringUtil.nullToString(personVO.getCd_push(), "")
                        , environment.getProperty("push.fcm"))){
                }
                pushEachManager.createPushEachInfo(pushEachVO);
            }
        } else {
            throw new FinsetException("사용자가 존재하지 않습니다.no_person="+no_person);
        }
		return JSON_VIEW;
	}
	
	/** VUE
	 * 대출신청 처리
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/reqFcPersonInfo.json")
	public String frameLoanIncomeSuccess(HttpServletRequest request, HttpSession session ,Model model, GoodsFormNSrch goodsForm) throws IOException, ParseException, FinsetException{
        String no_prepare = null;
        String amt_limit  = null;
        String cd_goods   = null;
        String cd_fc      = null;
        String no_bunch   = null;
        String no_person  = null;

        TxFcReceiveVO txFcReceiveVO = null;
        FincorpVO     fincorpVO     = null;
        String        nm_goods      = null;//상품명
        GoodsVO       goodsVO       = null;
        String        path_file     = null;//로그파일 경로

        no_prepare = goodsForm.getNo_prepare();
        amt_limit  = goodsForm.getAmt_limit ();
        cd_goods   = goodsForm.getCd_goods  ();
        cd_fc      = goodsForm.getCd_fc     ();
        no_bunch   = goodsForm.getNo_bunch  ();
		ApplyVO applyVO = new ApplyVO();
		logger.debug("############################################################");
		logger.debug("goodsForm.toString():" + goodsForm.toString());
		logger.debug("############################################################");
		logger.debug("no_prepare" + no_prepare);
		logger.debug("amt_limit " + amt_limit );
		logger.debug("cd_goods  " + cd_goods  );
		logger.debug("cd_fc     " + cd_fc     );
		logger.debug("no_bunch  " + no_bunch  );
		logger.debug("############################################################");
		applyVO.setNo_prepare(no_prepare);
		applyVO.setAmt_apply (amt_limit );
		applyVO.setCd_goods  (cd_goods  );
		applyVO.setCd_fc     (cd_fc     );
		applyVO.setNo_bunch  (no_bunch  );

        logger.info("frameLoanIncomeSuccess : applyVO.toString - " + applyVO.toString());
		logger.info("금융사코드  값 : "+cd_fc   );
		logger.info("상품코드 값 : "   +cd_goods);
		//==================================================namik ADD START
		TxFcTransmitVO txFcTransmitVO = loanManager.getTxFcTransmitInfoForMsg(goodsForm.getNo_bunch());

		logger.info("------------------txFcTransmitVO---------------  : "  + txFcTransmitVO.toString());
        no_person = (String) session.getAttribute("no_person");

        txFcTransmitVO.setNo_person (no_person);
		txFcTransmitVO.setCd_goods  (cd_goods );
		txFcTransmitVO.setPartner_cd(cd_fc    );

		String site = (environment != null)?environment.getProperty("service.profile"):"";
		model.addAttribute("site", site);
        String message = "success";
		String isSuccess = "true";
        try {
            txFcReceiveVO = financeManager.reqFcPersonInfo(txFcTransmitVO,applyVO);
            
        } catch (FinsetMessageException e) {
            isSuccess = "false";
            message = e.getMessage();
            LogUtil.error(logger,e);
        }
		fincorpVO  = fincorpManager.getFincorpInfo(cd_fc);
		goodsVO    = goodsManager.getGoodsInfo(cd_fc, cd_goods);
        nm_goods   = goodsVO.getNm_goods();
        path_file  = fincorpVO.getPath_file();
        logger.debug("txFcReceiveVO="+txFcReceiveVO);
        logger.debug("path_file    ="+path_file    );
        logger.debug("nm_goods     ="+nm_goods     );
        logger.debug("fincorpVO    ="+fincorpVO    );
        logger.debug("goodsVO      ="+goodsVO      );
        logger.debug("message      ="+message      );
        logger.debug("isSuccess    ="+isSuccess    );
        // 결과화면을 위한 정보 세션에 저장
        session.setAttribute("txFcReceiveVO", txFcReceiveVO);
        session.setAttribute("path_file1"   , path_file    );
		session.setAttribute("nm_goods"     , nm_goods     );
		session.setAttribute("fincorpVO"    , fincorpVO    );
		session.setAttribute("goodsVO"      , goodsVO      );
		session.setAttribute("message"      , message      );
		session.setAttribute("isSuccess"    , isSuccess    );
		
        model.addAttribute("txFcReceiveVO", txFcReceiveVO);
        model.addAttribute("path_file"    , path_file    );
        model.addAttribute("nm_goods"     , nm_goods     );
        model.addAttribute("fincorpVO"    , fincorpVO    );
        model.addAttribute("goodsVO"      , goodsVO      );
        model.addAttribute("message"      , message      );
        model.addAttribute("isSuccess"    , isSuccess    );
		//==================================================namik ADD END
		return "jsonView";
	}
	
	/**
	 * 대출신청 완료 페이지
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/getLoanIncomeSuccess.json")
	public String getLoanIncomeSuccess(HttpServletRequest request, HttpSession session ,Model model, GoodsForm goodsForm) {
        Object objTxFcReceiveVO = null;
        Object objFincorpVO     = null;
        Object objNm_goods      = null;//상품명
        Object objGoodsVO       = null;
        Object objPath_file1    = null;//로그파일 경로
        Object objMessage       = null;//전문메세지
        Object objIsSuccess     = null;//전문메세지

        TxFcReceiveVO txFcReceiveVO = null;
        FincorpVO     fincorpVO     = null;
        String        nm_goods      = null;//상품명
        GoodsVO       goodsVO       = null;
        String        path_file1    = null;//로그파일 경로
        String        message       = null;//전문메세지
        String        isSuccess     = null;

        objTxFcReceiveVO = session.getAttribute("txFcReceiveVO");
        objFincorpVO     = session.getAttribute("fincorpVO"    );
        objNm_goods      = session.getAttribute("nm_goods"     );
        objGoodsVO       = session.getAttribute("goodsVO"      );
        objPath_file1    = session.getAttribute("path_file1"   );
        objMessage       = session.getAttribute("message"      );
        objIsSuccess     = session.getAttribute("isSuccess"    );
        LogUtil.debugLn(logger,"frameLoanIncomeSuccessAfter.crz1");
        txFcReceiveVO = (objTxFcReceiveVO != null && (objTxFcReceiveVO.getClass() == TxFcReceiveVO.class))? (TxFcReceiveVO)objTxFcReceiveVO : null;
        fincorpVO     = (objFincorpVO     != null && (objFincorpVO    .getClass() == FincorpVO    .class))? (FincorpVO    )objFincorpVO     : null;
        nm_goods      = (objNm_goods      != null && (objNm_goods     .getClass() == String       .class))? (String       )objNm_goods      : null;
        goodsVO       = (objGoodsVO       != null && (objGoodsVO      .getClass() == GoodsVO      .class))? (GoodsVO      )objGoodsVO       : null;
        path_file1    = (objPath_file1    != null && (objPath_file1   .getClass() == String       .class))? (String       )objPath_file1    : null;
        message       = (objMessage       != null && (objMessage      .getClass() == String       .class))? (String       )objMessage       : null;
        isSuccess     = (objIsSuccess     != null && (objIsSuccess    .getClass() == String       .class))? (String       )objIsSuccess     : null;
        logger.debug("frameLoanIncomeSuccessAfter.objTxFcReceiveVO="+objTxFcReceiveVO);
        logger.debug("frameLoanIncomeSuccessAfter.objFincorpVO    ="+objFincorpVO    );
        logger.debug("frameLoanIncomeSuccessAfter.objNm_goods     ="+objNm_goods     );
        logger.debug("frameLoanIncomeSuccessAfter.objGoodsVO      ="+objGoodsVO      );
        logger.debug("frameLoanIncomeSuccessAfter.objPath_file1   ="+objPath_file1   );
        logger.debug("frameLoanIncomeSuccessAfter.objMessage      ="+objMessage      );
        logger.debug("frameLoanIncomeSuccessAfter.objIsSuccess    ="+objIsSuccess    );
        logger.debug("frameLoanIncomeSuccessAfter.txFcReceiveVO="+txFcReceiveVO);
        logger.debug("frameLoanIncomeSuccessAfter.fincorpVO    ="+fincorpVO    );
        logger.debug("frameLoanIncomeSuccessAfter.nm_goods     ="+nm_goods     );
        logger.debug("frameLoanIncomeSuccessAfter.goodsVO      ="+goodsVO      );
        logger.debug("frameLoanIncomeSuccessAfter.path_file1   ="+path_file1   );
        logger.debug("frameLoanIncomeSuccessAfter.message      ="+message      );
        logger.debug("frameLoanIncomeSuccessAfter.isSuccess    ="+isSuccess    );
       
        LogUtil.debugLn(logger,"frameLoanIncomeSuccessAfter.crz2");

        logger.debug("txFcReceiveVO="+txFcReceiveVO);
        logger.debug("fincorpVO    ="+fincorpVO    );
        logger.debug("nm_goods     ="+nm_goods     );
        logger.debug("goodsVO      ="+goodsVO      );
        logger.debug("path_file1   ="+path_file1   );
        logger.debug("message      ="+message      );
        logger.debug("isSuccess    ="+isSuccess    );

		if(txFcReceiveVO != null) {model.addAttribute("txFcReceiveVO", txFcReceiveVO);}
		if(fincorpVO     != null) {model.addAttribute("fincorpVO"    , fincorpVO    );}
		if(nm_goods      != null) {model.addAttribute("nm_goods"     , nm_goods     );}
		if(goodsVO       != null) {model.addAttribute("goodsVO"      , goodsVO      );}
		if(path_file1    != null) {model.addAttribute("path_file1"   , path_file1   );}
		if(message       != null) {model.addAttribute("message"      , message      );}
		if(isSuccess     != null) {model.addAttribute("isSuccess"    , isSuccess    );}
		//==================================================namik ADD END
		return "jsonView";
	}
	
	public KisSrchByNameInfo getKisSrchByName(String nm, String bizno, String crpno, String prn_rst_cnt, String pge_st_no) throws JsonSyntaxException, JSONException, IOException {
		
		OkHttpClient client = new OkHttpClient();
		String KIS_HEADER_CLIENTID = environment.getProperty("kisline.clientId");
		String KIS_HEADER_CLIENTSECRET = environment.getProperty("kisline.clientSecret");

//		client.setConnectTimeout(15, TimeUnit.SECONDS); // connect timeout
//		client.setReadTimeout(15, TimeUnit.SECONDS);    // socket timeout

		client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(300000, TimeUnit.SECONDS).build();//타임세팅
		String KIS_SRCHBYNAME_URL = environment.getProperty("kisline.apiUrl") + "srchByName?uid=koscomco";
		
		if(nm !=null && !nm.equals("")) KIS_SRCHBYNAME_URL = KIS_SRCHBYNAME_URL + "&nm=" + nm;
		if(bizno !=null && !bizno.equals("")) KIS_SRCHBYNAME_URL = KIS_SRCHBYNAME_URL + "&bizno=" + bizno;
		if(crpno !=null && !crpno.equals("")) KIS_SRCHBYNAME_URL = KIS_SRCHBYNAME_URL + "&crpno=" + crpno;
		if(prn_rst_cnt !=null && !prn_rst_cnt.equals("")) KIS_SRCHBYNAME_URL = KIS_SRCHBYNAME_URL + "&prn_rst_cnt=" + prn_rst_cnt;
		if(pge_st_no !=null && !pge_st_no.equals("")) KIS_SRCHBYNAME_URL = KIS_SRCHBYNAME_URL + "&pge_st_no=" + pge_st_no;
	
		Request request = new Request.Builder().url(KIS_SRCHBYNAME_URL).get().addHeader("x-ibm-client-id", KIS_HEADER_CLIENTID).addHeader("x-ibm-client-secret", KIS_HEADER_CLIENTSECRET).addHeader("accept", "application/json").build();
		
		return new Gson().fromJson(new JSONObject(client.newCall(request).execute().body().string()).toString(), KisSrchByNameInfo.class);
	}
	
	/**
	 * kisline create
	 * @param request
	 * @param model
	 * @param kisCompanySrchVO
	 * @param session
	 * @return
	 */
	@RequestMapping("/procKisCompanyOutline.json")
	public String procKisCompanyOutline(HttpServletRequest request, Model model, KisCompanySrchVO kisCompanySrchVO, HttpSession session) {
		String site = (environment != null)?environment.getProperty("service.profile"):"";
		logger.debug("kislineForm.getKiscode() : "+kisCompanySrchVO.getKiscode());
		logger.debug("kislineForm.getBizno() : "+kisCompanySrchVO.getBizno());
		logger.debug("kislineForm.getNo_bunch() : "+kisCompanySrchVO.getNo_bunch());
		String no_person = (String) session.getAttribute("no_person");
		String kiscode = kisCompanySrchVO.getKiscode();
		String bizno = kisCompanySrchVO.getBizno();
		KisCompanyOutlineInfo kisCompanyOutlineInfo = new KisCompanyOutlineInfo();
		try {
			kisCompanyOutlineInfo = kisCompanyOutline(kiscode, bizno);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("아웃라인 삭제 후 저장");
		ReturnClass rc = kislineManager.deleteKisCompanyOutline(kisCompanySrchVO.getNo_bunch());
		logger.debug("아웃라인 삭제 완료");
		//샘플
		if (kisCompanyOutlineInfo != null && kisCompanyOutlineInfo.getItems() != null ) {
			logger.debug(Integer.toString(kisCompanyOutlineInfo.getItems().count));
		}
		if(kisCompanyOutlineInfo.getItems().count > 0){
			logger.debug("getItems" + kisCompanyOutlineInfo.getItems().getItem());
			List<KisCompanyOutlineVO> list = kisCompanyOutlineInfo.getItems().getItem();

			for (KisCompanyOutlineVO kisCompanyOutlineVO : list) {
				logger.debug(kisCompanyOutlineVO.getAmnisuyn());
				logger.debug(kisCompanyOutlineVO.getBizno());
				logger.debug(kisCompanyOutlineVO.getBnk_brnm());
				logger.debug(kisCompanyOutlineVO.getBnknm());
				logger.debug(kisCompanyOutlineVO.getBtpnm());
				logger.debug(kisCompanyOutlineVO.getBzdnm());
				logger.debug(kisCompanyOutlineVO.getBzdnm2());
				logger.debug(kisCompanyOutlineVO.getBzdnm3());
				logger.debug(kisCompanyOutlineVO.getChulja());
				logger.debug(kisCompanyOutlineVO.getCrpno());
				logger.debug(kisCompanyOutlineVO.getCrprgrnstscd());
				logger.debug(kisCompanyOutlineVO.getDtlcont());
				logger.debug(kisCompanyOutlineVO.getEml());
				logger.debug(kisCompanyOutlineVO.getEmpnum());
				logger.debug(kisCompanyOutlineVO.getEmpnum_bse_date());
				logger.debug(kisCompanyOutlineVO.getEng_bnknm());
				logger.debug(kisCompanyOutlineVO.getEng_btpnm());
				logger.debug(kisCompanyOutlineVO.getEng_grpnm());
				logger.debug(kisCompanyOutlineVO.getEng_idscdnm());
				logger.debug(kisCompanyOutlineVO.getEng_itemnm());
				logger.debug(kisCompanyOutlineVO.getEng_mainpdtpcl());
				logger.debug(kisCompanyOutlineVO.getEng_scl());
				logger.debug(kisCompanyOutlineVO.getEngaddr());
				logger.debug(kisCompanyOutlineVO.getEngaddr2());
				logger.debug(kisCompanyOutlineVO.getEngaddr3());
				logger.debug(kisCompanyOutlineVO.getEngentrnm());
				logger.debug(kisCompanyOutlineVO.getEngreprnm());
				logger.debug(kisCompanyOutlineVO.getEpr_cnu_yn());
				logger.debug(kisCompanyOutlineVO.getEprdatastsdivcd());
				logger.debug(kisCompanyOutlineVO.getEprdtldivcd());
				logger.debug(kisCompanyOutlineVO.getEprmdydivcd());
				logger.debug(kisCompanyOutlineVO.getEtbDate());
				logger.debug(kisCompanyOutlineVO.getEtl_ipc_yn());
				logger.debug(kisCompanyOutlineVO.getFaBseDate());
				logger.debug(kisCompanyOutlineVO.getFadivcd());
				logger.debug(kisCompanyOutlineVO.getFax());
				logger.debug(kisCompanyOutlineVO.getFax2());
				logger.debug(kisCompanyOutlineVO.getFax3());
				logger.debug(kisCompanyOutlineVO.getGicd());
				logger.debug(kisCompanyOutlineVO.getGrpnm());
				logger.debug(kisCompanyOutlineVO.getHomepurl());
				logger.debug(kisCompanyOutlineVO.getHupegbn());
				logger.debug(kisCompanyOutlineVO.getIdscd());
				logger.debug(kisCompanyOutlineVO.getIdscdid());
				logger.debug(kisCompanyOutlineVO.getKiscode());
				logger.debug(kisCompanyOutlineVO.getKisreporturl());
				logger.debug(kisCompanyOutlineVO.getKorIdscdnm());
				logger.debug(kisCompanyOutlineVO.getKor_itemnm());
				logger.debug(kisCompanyOutlineVO.getKoraddr());
				logger.debug(kisCompanyOutlineVO.getKoraddr2());
				logger.debug(kisCompanyOutlineVO.getKoraddr3());
				logger.debug(kisCompanyOutlineVO.getKorentrnm());
				logger.debug(kisCompanyOutlineVO.getKorreprcd());
				logger.debug(kisCompanyOutlineVO.getKorreprnm());
				logger.debug(kisCompanyOutlineVO.getLogo());
				logger.debug(kisCompanyOutlineVO.getLtg_date());
				logger.debug(kisCompanyOutlineVO.getLtgmktdivcd());
				logger.debug(kisCompanyOutlineVO.getMainpdtpcl());
				logger.debug(kisCompanyOutlineVO.getMainupche());
				logger.debug(kisCompanyOutlineVO.getNolt_engaddr());
				logger.debug(kisCompanyOutlineVO.getNolt_engaddr2());
				logger.debug(kisCompanyOutlineVO.getNolt_engaddr3());
				logger.debug(kisCompanyOutlineVO.getNolt_koraddr());
				logger.debug(kisCompanyOutlineVO.getNolt_koraddr2());
				logger.debug(kisCompanyOutlineVO.getNolt_koraddr3());
				logger.debug(kisCompanyOutlineVO.getNts_sbqcdivcd());
				logger.debug(kisCompanyOutlineVO.getObz_date());
				logger.debug(kisCompanyOutlineVO.getOpt_entrnm());
				logger.debug(kisCompanyOutlineVO.getRbtpnm());
				logger.debug(kisCompanyOutlineVO.getRbtpnm2());
				logger.debug(kisCompanyOutlineVO.getRbtpnm3());
				logger.debug(kisCompanyOutlineVO.getRepr_regno());
				logger.debug(kisCompanyOutlineVO.getSbn_date());
				logger.debug(kisCompanyOutlineVO.getSbqc_date());
				logger.debug(kisCompanyOutlineVO.getScl());
				logger.debug(kisCompanyOutlineVO.getSforeign());
				logger.debug(kisCompanyOutlineVO.getSido());
				logger.debug(kisCompanyOutlineVO.getSlandc());
				logger.debug(kisCompanyOutlineVO.getSmanda());
				logger.debug(kisCompanyOutlineVO.getStacmm());
				logger.debug(kisCompanyOutlineVO.getStkcd());
				logger.debug(kisCompanyOutlineVO.getSventure());
				logger.debug(kisCompanyOutlineVO.getTel());
				logger.debug(kisCompanyOutlineVO.getTel2());
				logger.debug(kisCompanyOutlineVO.getTel3());
				logger.debug(kisCompanyOutlineVO.getUpt_date());
				logger.debug(kisCompanyOutlineVO.getUpt_dtm());
				logger.debug(kisCompanyOutlineVO.getX());
				logger.debug(kisCompanyOutlineVO.getX2());
				logger.debug(kisCompanyOutlineVO.getX3());
				logger.debug(kisCompanyOutlineVO.getY());
				logger.debug(kisCompanyOutlineVO.getY2());
				logger.debug(kisCompanyOutlineVO.getY3());
				logger.debug(kisCompanyOutlineVO.getZarcd());
				logger.debug(kisCompanyOutlineVO.getZarcd2());
				logger.debug(kisCompanyOutlineVO.getZarcd3());
				logger.debug(kisCompanyOutlineVO.getZcd());
				logger.debug(kisCompanyOutlineVO.getZcd2());
				logger.debug(kisCompanyOutlineVO.getZcd3());
				logger.debug(kisCompanyOutlineVO.getZipareacdseq());
				logger.debug(kisCompanyOutlineVO.getZipareacdseq2());
				logger.debug(kisCompanyOutlineVO.getZipareacdseq3());
				kisCompanyOutlineVO.setNo_person(no_person);
				kisCompanyOutlineVO.setNo_bunch(kisCompanySrchVO.getNo_bunch());
				ReturnClass returnClass = kislineManager.createKisCompanyOutline(kisCompanyOutlineVO);
				model.addAttribute("result", returnClass.getCd_result());
			}
		}
		if("LOCAL".equals(site)) {
            model.addAttribute("result", "00");
        }
		return JSON_VIEW;
	}
	
	public KisCompanyOutlineInfo kisCompanyOutline(String kiscode, String bizno) throws JsonSyntaxException, JSONException, IOException {

		String site = (environment != null)?environment.getProperty("service.profile"):"";
        KisCompanyOutlineInfo kisCompanyOutlineInfo = null;
        String result = null;
		if(!"LOCAL".equals(site)) {
            OkHttpClient client = new OkHttpClient();
            String KIS_COMPANYOUTLINE_URL = environment.getProperty("kisline.apiUrl") + "companyOutline?uid=koscomco";
            String KIS_HEADER_CLIENTID = environment.getProperty("kisline.clientId");
            String KIS_HEADER_CLIENTSECRET = environment.getProperty("kisline.clientSecret");

            if(kiscode !=null && !kiscode.equals("")) KIS_COMPANYOUTLINE_URL = KIS_COMPANYOUTLINE_URL + "&kiscode=" + kiscode;
            if(bizno !=null && !bizno.equals("")) KIS_COMPANYOUTLINE_URL = KIS_COMPANYOUTLINE_URL + "&bizno=" + bizno;

            Request request = new Request.Builder().url(KIS_COMPANYOUTLINE_URL).get()
                    .addHeader("x-ibm-client-id", KIS_HEADER_CLIENTID)
                    .addHeader("x-ibm-client-secret", KIS_HEADER_CLIENTSECRET)
                    .addHeader("accept", "application/json").build();
            result = client.newCall(request).execute().body().string();
            LogUtil.debugLn(logger,"kisCompanyOutline:\n"+result);
            kisCompanyOutlineInfo = new Gson().fromJson(new JSONObject(client.newCall(request).execute().body().string()).toString(), KisCompanyOutlineInfo.class);
        } else {
            kisCompanyOutlineInfo = new KisCompanyOutlineInfo();
            KisCompanyOutlineItemsVO items = new KisCompanyOutlineItemsVO();
            kisCompanyOutlineInfo.setItems(items);
        }
		return kisCompanyOutlineInfo;
	}
}
