package com.koscom.fss;

import java.io.IOException;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.koscom.domain.FssCompanyInfo;
import com.koscom.domain.FssCreditLoanInfo;
import com.koscom.domain.FssMortgageLoanInfo;
import com.koscom.domain.FssRentHouseLoanInfo;
import com.koscom.fss.model.FssCompanyResultVO;
import com.koscom.fss.model.FssCreditLoanResultVO;
import com.koscom.fss.model.FssMortgageLoanResultVO;
import com.koscom.fss.model.FssRentHouseLoanResultVO;
import com.koscom.fss.service.FssManager;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;
import com.koscom.util.URLConnection;

@Controller
@RequestMapping("/fss")
public class FssController {

	private static final Logger logger = LoggerFactory.getLogger(FssController.class);
	@Autowired
	FssManager fssManager;
	/**
	 * 금융감독원 - 1.금융회사API 홈|오픈 API|상세 및 테스트|금융회사 API | 금융상품 통합 비교공시 시스템
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/createFssCompany.crz")
	public String createFssCompany(Model model) throws Exception{
		
		/** mapper 연결 **/
//		fssManager.delFssCompanyResultInfo();
//		fssManager.delFssCompanyProductInfo();
//		fssManager.delFssCompanyOptionInfo();
		/** mapper 연결 **/
		
        ReturnClass returnClass = null;
        int pageNo = 1;
        String topFinGrpNo = "";
        for (int i = 0; i < 5; i++) {
            if(i == 0){
                topFinGrpNo = "020000";
            }else if(i == 1){
                topFinGrpNo = "030200";
            }else if(i == 2){
                topFinGrpNo = "030300";
            }else if(i == 3){
                topFinGrpNo = "050000";
            }else if(i == 4){
                topFinGrpNo = "060000";
            }
            String apiUrl = "http://finlife.fss.or.kr/finlifeapi/companySearch.json";
            returnClass = getFssApi(apiUrl, topFinGrpNo, pageNo);
            Gson gson = new Gson();
            int maxPageNo = 0;
            maxPageNo = setFssCompanyDao(pageNo, gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(returnClass.getDes_message())).get("result").toString(), FssCompanyInfo.class));
            if(maxPageNo > 1){
                for (int j = 2; j <= maxPageNo; j++) {
                    returnClass = getFssApi(apiUrl, topFinGrpNo, j);
                    gson = new Gson();
                    maxPageNo = setFssCompanyDao(pageNo, gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(returnClass.getDes_message())).get("result").toString(), FssCompanyInfo.class));
                }
            }
            logger.debug("Recv Frame Data Success");
            logger.debug(" returnClassData : " + returnClass.getDes_message());
        }
		
		/*
		fssManager.createFssCompanyApi();
		*/
		model.addAttribute("message", "alert('완료입니다.')");
		
		return "/comm/message";
	}
	private ReturnClass getFssApi(String apiUrl, String topFinGrpNo, int pageNo)  throws IOException  {
		String param = "auth=53e1793b7afaf71884f6659bb596a877&topFinGrpNo="+topFinGrpNo+"&pageNo="+pageNo;
		URLConnection url = new URLConnection();
		return url.sendReqPOST(apiUrl, param);
	}
	private int setFssCompanyDao(int pageNo, FssCompanyInfo fssCompanyInfo) {
		if(fssCompanyInfo != null){
			if(fssCompanyInfo.getErr_cd().equals("000")){
				if(pageNo == 1){
					FssCompanyResultVO fssCompanyResultVO = new FssCompanyResultVO();
					fssCompanyResultVO.setErr_cd(StringUtil.nullToString(fssCompanyInfo.getErr_cd()));		//응답코드
					fssCompanyResultVO.setErr_msg(StringUtil.nullToString(fssCompanyInfo.getErr_msg()));		//응답메시지
					fssCompanyResultVO.setTotal_count(fssCompanyInfo.getTotal_count());		//총 상품건수
					fssCompanyResultVO.setMax_page_no(fssCompanyInfo.getMax_page_no());		//총 페이지 건수(총 페이지 건수 = 총 상품건수/1회 조회 개수*)
					fssCompanyResultVO.setNow_page_no(fssCompanyInfo.getNow_page_no());		//현재 조회 페이지 번호
					/** mapper 연결 **/
					fssManager.createFssCompanyResultInfo(fssCompanyResultVO);
					/** mapper 연결 **/
				}
				/** mapper 연결 **/
				fssManager.createFssCompanyProductInfo(fssCompanyInfo.getBaseList());
				fssManager.createFssCompanyOptionInfo(fssCompanyInfo.getOptionList());
				/** mapper 연결 **/
			}
		}
		int result = fssCompanyInfo.getMax_page_no();
		return result;
	}
	
	
	/**
	 * 금융감독원 - 6.전세자금대출 API 홈|오픈 API|상세 및 테스트|전세자금대출 API | 금융상품 통합 비교공시 시스템
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/createFssMortgageLoan.crz")
	public String createFssMortgageLoan(Model model) throws Exception{
		
		/** mapper 연결 **/
//		fssManager.delFssMortgageLoanResultInfo();
//		fssManager.delFssMortgageLoanProductInfo();
//		fssManager.delFssMortgageLoanOptionInfo();
		/** mapper 연결 **/
		
		ReturnClass returnClass = null;
        int pageNo = 1;
        String topFinGrpNo = "";
        for (int i = 0; i < 5; i++) {
            if(i == 0){
                topFinGrpNo = "020000";
            }else if(i == 1){
                topFinGrpNo = "030200";
            }else if(i == 2){
                topFinGrpNo = "030300";
            }else if(i == 3){
                topFinGrpNo = "050000";
            }else if(i == 4){
                topFinGrpNo = "060000";
            }
            String apiUrl = "http://finlife.fss.or.kr/finlifeapi/rentHouseLoanProductsSearch.json";
            returnClass = getFssApi(apiUrl, topFinGrpNo, pageNo);
            Gson gson = new Gson();
            int maxPageNo = 0;
            maxPageNo = setFssMortgageLoanDao(pageNo, gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(returnClass.getDes_message())).get("result").toString(), FssMortgageLoanInfo.class));
            if(maxPageNo > 1){
                for (int j = 2; j <= maxPageNo; j++) {
                    returnClass = getFssApi(apiUrl, topFinGrpNo, j);
                    gson = new Gson();
                    maxPageNo = setFssMortgageLoanDao(pageNo, gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(returnClass.getDes_message())).get("result").toString(), FssMortgageLoanInfo.class));
                }
            }
            logger.debug("Recv Frame Data Success");
            logger.debug(" returnClassData : " + returnClass.getDes_message());
        }
		
		/*
		fssManager.createFssMortgageLoanApi();
		*/
		model.addAttribute("message", "alert('완료입니다.')");
		return "/comm/message";
	}
	private int setFssMortgageLoanDao(int pageNo, FssMortgageLoanInfo fssMortgageLoanInfo) {
	    int max_page_no = -1;
		if(fssMortgageLoanInfo != null){
            max_page_no = fssMortgageLoanInfo.getMax_page_no();
            if(fssMortgageLoanInfo.getErr_cd().equals("000")){
				if(pageNo == 1){
					FssMortgageLoanResultVO fssMortgageLoanResultVO = new FssMortgageLoanResultVO();
					fssMortgageLoanResultVO.setErr_cd(StringUtil.nullToString(fssMortgageLoanInfo.getErr_cd()));		//응답코드
					fssMortgageLoanResultVO.setErr_msg(StringUtil.nullToString(fssMortgageLoanInfo.getErr_msg()));		//응답메시지
					fssMortgageLoanResultVO.setTotal_count(fssMortgageLoanInfo.getTotal_count());		//총 상품건수
					fssMortgageLoanResultVO.setMax_page_no(fssMortgageLoanInfo.getMax_page_no());		//총 페이지 건수(총 페이지 건수 = 총 상품건수/1회 조회 개수*)
					fssMortgageLoanResultVO.setNow_page_no(fssMortgageLoanInfo.getNow_page_no());		//현재 조회 페이지 번호
					/** mapper 연결 **/
					fssManager.createFssMortgageLoanResultInfo(fssMortgageLoanResultVO);
					/** mapper 연결 **/
				}
				/** mapper 연결 **/
				fssManager.createFssMortgageLoanProductInfo(fssMortgageLoanInfo.getBaseList());
				fssManager.createFssMortgageLoanOptionInfo(fssMortgageLoanInfo.getOptionList());
				/** mapper 연결 **/
			}
		}
		return max_page_no;
	}	
	
	/**
	 * 금융감독원 - 6.전세자금대출 API 홈|오픈 API|상세 및 테스트|전세자금대출 API | 금융상품 통합 비교공시 시스템
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/createFssRentHouseLoan.crz")
	public String createFssRentHouseLoan(Model model) throws Exception{
		
		/** mapper 연결 **/
//		fssManager.delFssRentHouseLoanResultInfo();
//		fssManager.delFssRentHouseLoanProductInfo();
//		fssManager.delFssRentHouseLoanOptionInfo();
		/** mapper 연결 **/
		
		ReturnClass returnClass = null;

        int pageNo = 1;
        String topFinGrpNo = "";
        for (int i = 0; i < 5; i++) {
            if(i == 0){
                topFinGrpNo = "020000";
            }else if(i == 1){
                topFinGrpNo = "030200";
            }else if(i == 2){
                topFinGrpNo = "030300";
            }else if(i == 3){
                topFinGrpNo = "050000";
            }else if(i == 4){
                topFinGrpNo = "060000";
            }
            String apiUrl = "http://finlife.fss.or.kr/finlifeapi/rentHouseLoanProductsSearch.json";
            returnClass = getFssApi(apiUrl, topFinGrpNo, pageNo);
            Gson gson = new Gson();
            int maxPageNo = 0;
            maxPageNo = setFssRentHouseLoanDao(pageNo, gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(returnClass.getDes_message())).get("result").toString(), FssRentHouseLoanInfo.class));
            if(maxPageNo > 1){
                for (int j = 2; j <= maxPageNo; j++) {
                    returnClass = getFssApi(apiUrl, topFinGrpNo, j);
                    gson = new Gson();
                    maxPageNo = setFssRentHouseLoanDao(pageNo, gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(returnClass.getDes_message())).get("result").toString(), FssRentHouseLoanInfo.class));
                }
            }
            logger.debug("Recv Frame Data Success");
            logger.debug(" returnClassData : " + returnClass.getDes_message());
        }
		
		/*
		fssManager.createFssRentHouseLoanApi();
		*/
		model.addAttribute("message", "alert('완료입니다.')");
		return "/comm/message";
	}
	private int setFssRentHouseLoanDao(int pageNo, FssRentHouseLoanInfo fssRentHouseLoanInfo) {
        int result = -1;
		if(fssRentHouseLoanInfo != null){
			if(fssRentHouseLoanInfo.getErr_cd().equals("000")){
				if(pageNo == 1){
					FssRentHouseLoanResultVO fssRentHouseLoanResultVO = new FssRentHouseLoanResultVO();
					fssRentHouseLoanResultVO.setErr_cd(StringUtil.nullToString(fssRentHouseLoanInfo.getErr_cd()));		//응답코드
					fssRentHouseLoanResultVO.setErr_msg(StringUtil.nullToString(fssRentHouseLoanInfo.getErr_msg()));		//응답메시지
					fssRentHouseLoanResultVO.setTotal_count(fssRentHouseLoanInfo.getTotal_count());		//총 상품건수
					fssRentHouseLoanResultVO.setMax_page_no(fssRentHouseLoanInfo.getMax_page_no());		//총 페이지 건수(총 페이지 건수 = 총 상품건수/1회 조회 개수*)
					fssRentHouseLoanResultVO.setNow_page_no(fssRentHouseLoanInfo.getNow_page_no());		//현재 조회 페이지 번호
					
					/** mapper 연결 **/
					fssManager.createFssRentHouseLoanResultInfo(fssRentHouseLoanResultVO);
					/** mapper 연결 **/
				}
				/** mapper 연결 **/
				fssManager.createFssRentHouseLoanProductInfo(fssRentHouseLoanInfo.getBaseList());
				fssManager.createFssRentHouseLoanOptionInfo(fssRentHouseLoanInfo.getOptionList());
				/** mapper 연결 **/
			}
            result = fssRentHouseLoanInfo.getMax_page_no();
		}
		return result;
	}	
	/**
	 * 금융감독원 - 7.개인신용대출 API 홈|오픈 API|상세 및 테스트|개인신용대출 API | 금융상품 통합 비교공시 시스템
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/createFssCreditLoan.crz")
	public String createFssCreditLoan(Model model) throws Exception{
		
		/** mapper 연결 **/
//		fssManager.delFssCreditLoanResultInfo();
//		fssManager.delFssCreditLoanProductInfo();
//		fssManager.delFssCreditLoanOptionInfo();
		/** mapper 연결 **/
		
		ReturnClass returnClass = null;

		int pageNo = 1;
		String topFinGrpNo = "";
		for (int i = 0; i < 5; i++) {
			if(i == 0){
				topFinGrpNo = "020000";
			}else if(i == 1){
				topFinGrpNo = "030200";
			}else if(i == 2){
				topFinGrpNo = "030300";
			}else if(i == 3){
				topFinGrpNo = "050000";
			}else if(i == 4){
				topFinGrpNo = "060000";
			}
			String apiUrl = "http://finlife.fss.or.kr/finlifeapi/creditLoanProductsSearch.json";
			returnClass = getFssApi(apiUrl, topFinGrpNo, pageNo);
			Gson gson = new Gson();
			int maxPageNo = 0;
			maxPageNo = setFssCreditLoanDao(pageNo, gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(returnClass.getDes_message())).get("result").toString(), FssCreditLoanInfo.class));
			if(maxPageNo > 1){
				for (int j = 2; j <= maxPageNo; j++) {
					returnClass = getFssApi(apiUrl, topFinGrpNo, j);
					gson = new Gson();
					maxPageNo = setFssCreditLoanDao(pageNo, gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(returnClass.getDes_message())).get("result").toString(), FssCreditLoanInfo.class));
				}
			}
			logger.debug("Recv Frame Data Success");
			logger.debug(" returnClassData : " + returnClass.getDes_message());
		}
		
		/*
		fssManager.createFssCreditLoanApi();
		*/
		model.addAttribute("message", "alert('완료입니다.')");
		return "/comm/message";
	}
	
	private int setFssCreditLoanDao(int pageNo, FssCreditLoanInfo fssCreditLoanInfo) {
	    int max_page_no = 0;
		if(fssCreditLoanInfo != null){
			if("000".equals(fssCreditLoanInfo.getErr_cd())){
				if(pageNo == 1){
					FssCreditLoanResultVO fssCreditLoanResultVO = new FssCreditLoanResultVO();
					fssCreditLoanResultVO.setErr_cd(StringUtil.nullToString(fssCreditLoanInfo.getErr_cd()));		//응답코드
					fssCreditLoanResultVO.setErr_msg(StringUtil.nullToString(fssCreditLoanInfo.getErr_msg()));		//응답메시지
					fssCreditLoanResultVO.setTotal_count(fssCreditLoanInfo.getTotal_count());		//총 상품건수
					fssCreditLoanResultVO.setMax_page_no(fssCreditLoanInfo.getMax_page_no());		//총 페이지 건수(총 페이지 건수 = 총 상품건수/1회 조회 개수*)
					fssCreditLoanResultVO.setNow_page_no(fssCreditLoanInfo.getNow_page_no());		//현재 조회 페이지 번호
					/** mapper 연결 **/
					fssManager.createFssCreditLoanResultInfo(fssCreditLoanResultVO);
					/** mapper 연결 **/
				}
				/** mapper 연결 **/
				fssManager.createFssCreditLoanProductInfo(fssCreditLoanInfo.getBaseList());
				fssManager.createFssCreditLoanOptionInfo(fssCreditLoanInfo.getOptionList());
				/** mapper 연결 **/
			}
            max_page_no = fssCreditLoanInfo.getMax_page_no();
		}
		return max_page_no;
	}
}
