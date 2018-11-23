package com.koscom.loan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.koscom.domain.CooconGoodsFavoriteInfo;
import com.koscom.domain.KisCompanyOutlineInfo;
import com.koscom.domain.KisSrchByNameInfo;
import com.koscom.finance.model.TxFcTransmitVO;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;
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
import com.koscom.util.AuthUtil;
import com.koscom.util.Constant;
import com.koscom.util.LogUtil;
import com.koscom.util.Pagination;
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
	
	@Autowired
	KislineManager kislineManager;
	
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
        /**
         * 접근제어 : start
         */
        boolean isAuth = AuthUtil.isHaveAuth(request,"/frameLoanWorkerStep9.crz",environment);
        if(isAuth == false) {return JSON_VIEW;}
        /**
         * 접근제어 : end
         */
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
