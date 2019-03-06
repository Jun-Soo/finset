package com.koscom.fss.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.koscom.domain.FssCompanyInfo;
import com.koscom.domain.FssCreditLoanInfo;
import com.koscom.domain.FssMortgageLoanInfo;
import com.koscom.domain.FssRentHouseLoanInfo;
import com.koscom.fss.dao.FssMapper;
import com.koscom.fss.model.FssCompanyOptionVO;
import com.koscom.fss.model.FssCompanyProductVO;
import com.koscom.fss.model.FssCompanyResultVO;
import com.koscom.fss.model.FssCreditLoanOptionVO;
import com.koscom.fss.model.FssCreditLoanProductVO;
import com.koscom.fss.model.FssCreditLoanResultVO;
import com.koscom.fss.model.FssMortgageLoanOptionVO;
import com.koscom.fss.model.FssMortgageLoanProductVO;
import com.koscom.fss.model.FssMortgageLoanResultVO;
import com.koscom.fss.model.FssRentHouseLoanOptionVO;
import com.koscom.fss.model.FssRentHouseLoanProductVO;
import com.koscom.fss.model.FssRentHouseLoanResultVO;
import com.koscom.fss.service.FssManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;
import com.koscom.util.URLConnection;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Service("fssManager")
public class FssManagerImpl implements FssManager {
	private static final Logger logger = LoggerFactory.getLogger(FssManagerImpl.class);
	@Autowired
	private FssMapper fssMapper;
	/*
	public void createFssCompanyApi() {
		*//** mapper 연결 **//*
		delFssCompanyResultInfo();
		delFssCompanyProductInfo();
		delFssCompanyOptionInfo();
		*//** mapper 연결 **//*
		
		//권역코드		020000(은행), 030200(여신전문), 030300(저축은행), 050000(보험), 060000(금융투자)
		//pageNo
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

	}
	private ReturnClass getFssApi(String apiUrl, String topFinGrpNo, int pageNo) {
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
					*//** mapper 연결 **//*
					createFssCompanyResultInfo(fssCompanyResultVO);
					*//** mapper 연결 **//*
				}
				*//** mapper 연결 **//*
				createFssCompanyProductInfo(fssCompanyInfo.getBaseList());
				createFssCompanyOptionInfo(fssCompanyInfo.getOptionList());
				*//** mapper 연결 **//*
			}
		}
		int result = fssCompanyInfo.getMax_page_no();
		return result;
	}
	public void createFssMortgageLoanApi() {
		
		*//** mapper 연결 **//*
		delFssMortgageLoanResultInfo();
		delFssMortgageLoanProductInfo();
		delFssMortgageLoanOptionInfo();
		*//** mapper 연결 **//*
		
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
					*//** mapper 연결 **//*
					createFssMortgageLoanResultInfo(fssMortgageLoanResultVO);
					*//** mapper 연결 **//*
				}
				*//** mapper 연결 **//*
				createFssMortgageLoanProductInfo(fssMortgageLoanInfo.getBaseList());
				createFssMortgageLoanOptionInfo(fssMortgageLoanInfo.getOptionList());
				*//** mapper 연결 **//*
			}
		}
		return max_page_no;
	}	
	public void createFssRentHouseLoanApi() {
		
		*//** mapper 연결 **//*
		delFssRentHouseLoanResultInfo();
		delFssRentHouseLoanProductInfo();
		delFssRentHouseLoanOptionInfo();
		*//** mapper 연결 **//*
		
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
					
					*//** mapper 연결 **//*
					createFssRentHouseLoanResultInfo(fssRentHouseLoanResultVO);
					*//** mapper 연결 **//*
				}
				*//** mapper 연결 **//*
				createFssRentHouseLoanProductInfo(fssRentHouseLoanInfo.getBaseList());
				createFssRentHouseLoanOptionInfo(fssRentHouseLoanInfo.getOptionList());
				*//** mapper 연결 **//*
			}
            result = fssRentHouseLoanInfo.getMax_page_no();
		}
		return result;
	}	
	public void createFssCreditLoanApi() {
		
		*//** mapper 연결 **//*
		delFssCreditLoanResultInfo();
		delFssCreditLoanProductInfo();
		delFssCreditLoanOptionInfo();
		*//** mapper 연결 **//*
		
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
					*//** mapper 연결 **//*
					createFssCreditLoanResultInfo(fssCreditLoanResultVO);
					*//** mapper 연결 **//*
				}
				*//** mapper 연결 **//*
				createFssCreditLoanProductInfo(fssCreditLoanInfo.getBaseList());
				createFssCreditLoanOptionInfo(fssCreditLoanInfo.getOptionList());
				*//** mapper 연결 **//*
			}
            max_page_no = fssCreditLoanInfo.getMax_page_no();
		}
		return max_page_no;
	}*/
	
	@Override
	public ReturnClass createFssCompanyResultInfo(FssCompanyResultVO fssCompanyResultVO) {
		if (1 != fssMapper.createFssCompanyResultInfo(fssCompanyResultVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass createFssCompanyProductInfo(List<FssCompanyProductVO> list) {
		for (FssCompanyProductVO fssCompanyProductVO : list) {
			if (1 != fssMapper.createFssCompanyProductInfo(fssCompanyProductVO)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass createFssCompanyOptionInfo(List<FssCompanyOptionVO> list) {
		for (FssCompanyOptionVO fssCompanyOptionVO : list) {
			if (1 != fssMapper.createFssCompanyOptionInfo(fssCompanyOptionVO)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	
	public ReturnClass createFssCreditLoanResultInfo(FssCreditLoanResultVO fssCreditLoanResultVO) {
		if (1 != fssMapper.createFssCreditLoanResultInfo(fssCreditLoanResultVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass createFssCreditLoanProductInfo(List<FssCreditLoanProductVO> list) {
		for (FssCreditLoanProductVO fssCreditLoanProductVO : list) {
			if (1 != fssMapper.createFssCreditLoanProductInfo(fssCreditLoanProductVO)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass createFssCreditLoanOptionInfo(List<FssCreditLoanOptionVO> list) {
		for (FssCreditLoanOptionVO fssCreditLoanOptionVO : list) {
			if (1 != fssMapper.createFssCreditLoanOptionInfo(fssCreditLoanOptionVO)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delFssCompanyProductInfo() {
		if (1 != fssMapper.delFssCompanyProductInfo()) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delFssCompanyResultInfo() {
		if (1 != fssMapper.delFssCompanyResultInfo()) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delFssCompanyOptionInfo() {
		if (1 != fssMapper.delFssCompanyOptionInfo()) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delFssCreditLoanProductInfo() {
		if (1 != fssMapper.delFssCreditLoanProductInfo()) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delFssCreditLoanResultInfo() {
		if (1 != fssMapper.delFssCreditLoanResultInfo()) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delFssCreditLoanOptionInfo() {
		if (1 != fssMapper.delFssCreditLoanOptionInfo()) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass createFssRentHouseLoanResultInfo(FssRentHouseLoanResultVO fssRentHouseLoanResultVO) {
		if (1 != fssMapper.createFssRentHouseLoanResultInfo(fssRentHouseLoanResultVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass createFssRentHouseLoanProductInfo(List<FssRentHouseLoanProductVO> list) {
		for (FssRentHouseLoanProductVO fssRentHouseLoanProductVO : list) {
			if (1 != fssMapper.createFssRentHouseLoanProductInfo(fssRentHouseLoanProductVO)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass createFssRentHouseLoanOptionInfo(List<FssRentHouseLoanOptionVO> list) {
		for (FssRentHouseLoanOptionVO fssRentHouseLoanOptionVO : list) {
			if (1 != fssMapper.createFssRentHouseLoanOptionInfo(fssRentHouseLoanOptionVO)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delFssRentHouseLoanProductInfo() {
		if (1 != fssMapper.delFssRentHouseLoanProductInfo()) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delFssRentHouseLoanResultInfo() {
		if (1 != fssMapper.delFssRentHouseLoanResultInfo()) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delFssRentHouseLoanOptionInfo() {
		if (1 != fssMapper.delFssRentHouseLoanOptionInfo()) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass createFssMortgageLoanResultInfo(FssMortgageLoanResultVO fssMortgageLoanResultVO) {
		if (1 != fssMapper.createFssMortgageLoanResultInfo(fssMortgageLoanResultVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass createFssMortgageLoanProductInfo(List<FssMortgageLoanProductVO> list) {
		for (FssMortgageLoanProductVO fssMortgageLoanProductVO : list) {
			if (1 != fssMapper.createFssMortgageLoanProductInfo(fssMortgageLoanProductVO)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass createFssMortgageLoanOptionInfo(List<FssMortgageLoanOptionVO> list) {
		for (FssMortgageLoanOptionVO fssMortgageLoanOptionVO : list) {
			if (1 != fssMapper.createFssMortgageLoanOptionInfo(fssMortgageLoanOptionVO)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delFssMortgageLoanProductInfo() {
		if (1 != fssMapper.delFssMortgageLoanProductInfo()) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delFssMortgageLoanResultInfo() {
		if (1 != fssMapper.delFssMortgageLoanResultInfo()) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delFssMortgageLoanOptionInfo() {
		if (1 != fssMapper.delFssMortgageLoanOptionInfo()) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
}