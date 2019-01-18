package com.koscom.assets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.assets.model.AssetsForm;
import com.koscom.assets.model.AssetsInfoVO;
import com.koscom.assets.service.AssetsManager;
import com.koscom.main.service.MainManager;
import com.koscom.util.DateUtil;
import com.koscom.util.FinsetException;
import com.koscom.util.Pagination;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;


@Controller
@RequestMapping("/m/assets")
public class AssetsController {

	private static final Logger logger = LoggerFactory.getLogger(AssetsController.class);
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private MainManager mainManager;

	/**
	 * VUE
     * 자산관리 - 메인
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getAssetsMainInfo.json")
	public String getAssetsMainInfo(
	HttpServletRequest request,
	HttpSession session,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		AssetsForm assetsForm = new AssetsForm();
		assetsForm.setNo_person(no_person);
		assetsForm.setType_list("bank");
		AssetsInfoVO assetsBankInfo = assetsManager.getAssetsMainInfo(assetsForm);
		assetsForm.setType_list("stock");
		AssetsInfoVO assetsStockInfo = assetsManager.getAssetsMainInfo(assetsForm);
		assetsForm.setType_list("etc");
		AssetsInfoVO assetsEtcInfo = assetsManager.getAssetsMainInfo(assetsForm);

        model.addAttribute("assetsSumAmt", mainManager.getMainAssetsSumAmt(no_person)); //자산총금액
        model.addAttribute("debtSumAmt", mainManager.getMainDebtSumAmt(no_person)); //부채총금액
        model.addAttribute("assetsBankInfo", assetsBankInfo); //은행정보
        model.addAttribute("assetsStockInfo", assetsStockInfo); //증권정보
        model.addAttribute("assetsEtcInfo", assetsEtcInfo); //기타정보

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 은행 메인
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getAssetsBankMainInfo.json")
	public String getAssetsBankMainInfo(
	HttpServletRequest request,
	HttpSession session,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		AssetsForm assetsForm = new AssetsForm();
		assetsForm.setNo_person(no_person);
		assetsForm.setType_list("bank");
		AssetsInfoVO sumAmt = assetsManager.getAssetsMainInfo(assetsForm);
		AssetsInfoVO depWdrlInfo = assetsManager.getAssetsBankDepWdrlInfo(assetsForm);

        model.addAttribute("sumAmt", sumAmt); //은행총금액
        model.addAttribute("depWdrlInfo", depWdrlInfo); //최근 입출금 내역
        model.addAttribute("personShareList", assetsManager.listAssetsSharePerson(assetsForm)); //공유자list

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 은행 메인 계좌내역
     * @param request
     * @param session
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/listAssetsBankMainAccount.json")
	public String listAssetsBankMainAccount(
	HttpServletRequest request,
	HttpSession session,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		assetsForm.setNo_person(no_person);

		Pagination pagedList = assetsForm.setPagedList(assetsManager.listAssetsBankAccount(assetsForm),assetsManager.listAssetsBankAccountCount(assetsForm));
		model.addAttribute("pagedList", pagedList);

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 계좌 정렬순서변경
     * @param request
     * @param session
     * @param AssetsInfoVO
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/updateAssetsSortInfo.json")
	public String updateAssetsSortInfo(
	HttpServletRequest request,
	HttpSession session,
	AssetsInfoVO assetsInfoVO,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		assetsInfoVO.setNo_person(no_person);
    	assetsManager.updateAssetsSortInfo(assetsInfoVO);

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 은행 계좌상세
     * @param request
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getAssetsBankActDetail.json")
	public String getAssetsBankActDetail(
	HttpServletRequest request,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{

		model.addAttribute("assetsInfo", assetsManager.getAssetsInfo(assetsForm));

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 은행 계좌상세 검색키워드list(입출금)
     * @param request
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getAssetsBankScKeywordList.json")
	public String getAssetsBankScKeywordList(
	HttpServletRequest request,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{

		model.addAttribute("scKeywordList", assetsManager.listAssetsSearchKeyword(assetsForm));

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 은행 계좌상세 입출금내역
     * @param request
     * @param session
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/listAssetsBankActTrns.json")
	public String listAssetsBankActTrns(
	HttpServletRequest request,
	HttpSession session,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		assetsForm.setNo_person(no_person);

		Pagination pagedList = new Pagination();
		String cdDetailClass = assetsForm.getCd_detail_class();
		if("01".equals(cdDetailClass)){
			pagedList = assetsForm.setPagedList(assetsManager.listAssetsBankActTrns(assetsForm),assetsManager.listAssetsBankActTrnsCount(assetsForm));
		}else if("02".equals(cdDetailClass) || "03".equals(cdDetailClass)){
			pagedList = assetsForm.setPagedList(assetsManager.listAssetsBankActSvng(assetsForm),assetsManager.listAssetsBankActSvngCount(assetsForm));
		}
		model.addAttribute("pagedList", pagedList);

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 입출금내역 검색조건
     * @param request
     * @param session
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getAssetsBankDepWdrlSc.json")
	public String getAssetsBankDepWdrlSc(
	HttpServletRequest request,
	HttpSession session,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		assetsForm.setNo_person(no_person);

		model.addAttribute("currentDate",DateUtil.getCurrentDate(DateUtil.DATE_PATTERN_DASH));
		model.addAttribute("scAccountList", assetsManager.listAssetsAccount(assetsForm));

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 은행 입출금내역 (입금 / 출금 총액)
     * @param request
     * @param session
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getAssetsBankDepWdrlTotalAmt.json")
	public String getAssetsBankDepWdrlTotalAmt(
	HttpServletRequest request,
	HttpSession session,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		assetsForm.setNo_person(no_person);

		model.addAttribute("totalAmt", assetsManager.getAssetsBankDepWdrlTotalAmt(assetsForm));

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 은행 입출금내역(list)
     * @param request
     * @param session
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/listAssetsBankDepWdrl.json")
	public String listAssetsBankDepWdrl(
	HttpServletRequest request,
	HttpSession session,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		assetsForm.setNo_person(no_person);

		Pagination pagedList = assetsForm.setPagedList(assetsManager.listAssetsBankDepWdrl(assetsForm),assetsManager.listAssetsBankDepWdrlCount(assetsForm));
		model.addAttribute("pagedList", pagedList);

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 은행 입출금상세
     * @param request
     * @param session
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getAssetsBankDepWdrlDetail.json")
	public String getAssetsBankDepWdrlDetail(
	HttpServletRequest request,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{

		model.addAttribute("depWdrlInfo", assetsManager.getAssetsBankDepWdrlDetail(assetsForm));
		model.addAttribute("consumeInfo", assetsManager.getAssetsDetailCsInfo(assetsForm));

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 증권 메인
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getAssetsStockMainInfo.json")
	public String getAssetsStockMainInfo(
	HttpServletRequest request,
	HttpSession session,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		AssetsForm assetsForm = new AssetsForm();
		assetsForm.setNo_person(no_person);
		assetsForm.setType_list("stock");
		AssetsInfoVO sumAmt = assetsManager.getAssetsMainInfo(assetsForm);

        model.addAttribute("sumAmt", sumAmt); //증권총금액
        model.addAttribute("personShareList", assetsManager.listAssetsSharePerson(assetsForm)); //공유자list

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 증권 메인 계좌내역
     * @param request
     * @param session
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/listAssetsStockMainAccount.json")
	public String listAssetsStockMainAccount(
	HttpServletRequest request,
	HttpSession session,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		assetsForm.setNo_person(no_person);

		Pagination pagedList = assetsForm.setPagedList(assetsManager.listAssetsStockMainAccount(assetsForm),assetsManager.listAssetsStockMainAccountCount(assetsForm));
		model.addAttribute("pagedList", pagedList);

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 증권 계좌상세
     * @param request
     * @param session
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getAssetsStockInfo.json")
	public String getAssetsStockInfo(
	HttpServletRequest request,
	HttpSession session,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");
		String cdResult = SessionUtil.chkNoPerson(no_person, assetsForm.getNo_person(), "02");
		model.addAttribute("cdResult", cdResult);

		if("00".equals(cdResult)){
			model.addAttribute("stockInfo", assetsManager.getAssetsStockInfo(assetsForm));
		}

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 증권 전체잔고 상세
     * @param request
     * @param session
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getAssetsStockSumInfo.json")
	public String getAssetsStockSumInfo(
	HttpServletRequest request,
	HttpSession session,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		assetsForm.setNo_person(no_person);

		model.addAttribute("scCompanyList", assetsManager.listAssetsStockDetailCompany(assetsForm));
		model.addAttribute("stockSumInfo", assetsManager.getAssetsStockSumInfo(assetsForm));

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 증권 상세 계좌내역
     * @param request
     * @param session
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/listAssetsStockDetailAccount.json")
	public String listAssetsStockDetailAccount(
	HttpServletRequest request,
	HttpSession session,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		assetsForm.setNo_person(no_person);

		Pagination pagedList = assetsForm.setPagedList(assetsManager.listAssetsStockDetailAccount(assetsForm),assetsManager.listAssetsStockDetailAccountCount(assetsForm));
		model.addAttribute("pagedList", pagedList);

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 증권 주식상세
     * @param request
     * @param session
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getAssetsStockShrInfo.json")
	public String getAssetsStockShrInfo(
	HttpServletRequest request,
	HttpSession session,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");
		String cdResult = SessionUtil.chkNoPerson(no_person, assetsForm.getNo_person(), "02");
		model.addAttribute("cdResult", cdResult);

		if("00".equals(cdResult)){
			model.addAttribute("shrInfo", assetsManager.getAssetsStockShrInfo(assetsForm));
		}

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 증권 펀드상세
     * @param request
     * @param session
     * @param AssetsForm
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getAssetsStockFndInfo.json")
	public String getAssetsStockFndInfo(
	HttpServletRequest request,
	HttpSession session,
	AssetsForm assetsForm,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");
		String cdResult = SessionUtil.chkNoPerson(no_person, assetsForm.getNo_person(), "02");
		model.addAttribute("cdResult", cdResult);

		if("00".equals(cdResult)){
			model.addAttribute("fndInfo", assetsManager.getAssetsStockFndInfo(assetsForm));
		}

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 직접입력
     * @param request
     * @param session
     * @param AssetsInfoVO
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/createAssetsInfo.json")
	public String createAssetsInfo(
	HttpServletRequest request,
	HttpSession session,
	AssetsInfoVO assetsInfoVO,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		assetsInfoVO.setNo_person(no_person);

		ReturnClass returnClass = assetsManager.createAssetsInfo((AssetsInfoVO)SessionUtil.setUser(assetsInfoVO, session));
		model.addAttribute("message", returnClass.getMessage());
		model.addAttribute("result" , returnClass.getCd_result());

		return "jsonView";
	}

	/**
	 * VUE
     * 자산관리 - 기타메인
     * @param request
     * @param session
     * @param model
     * @return String
     * @throws FinsetException, IOException
	 */
	@RequestMapping("/getAssetsEtcMainInfo.json")
	public String getAssetsEtcMainInfo(
	HttpServletRequest request,
	HttpSession session,
	Model model) throws FinsetException, IOException{
		String no_person = (String)session.getAttribute("no_person");

		AssetsForm assetsForm = new AssetsForm();
		assetsForm.setNo_person(no_person);
		assetsForm.setType_list("etc");
		AssetsInfoVO sumAmt = assetsManager.getAssetsMainInfo(assetsForm);

		model.addAttribute("sumAmt",sumAmt);
		model.addAttribute("etcList", assetsManager.listAssetsEtcMain(no_person));

		return "jsonView";
	}

}
