package com.koscom.domain;

import java.io.Serializable;
import java.util.List;

import com.koscom.fss.model.FssCompanyOptionVO;
import com.koscom.fss.model.FssCompanyProductVO;
import com.koscom.fss.model.FssCreditLoanOptionVO;
import com.koscom.fss.model.FssCreditLoanProductVO;
import com.koscom.kbrealestate.model.KbSiGunGuVO;

public class KbSiGunGuInfo implements Serializable{
	private static final long serialVersionUID = 7828890049046758009L;
//	" RESP_DATA " : [] ,
//	" RESULT_CD " : "00000000" ,
//	" RESULT_MG " : "정상 처리했습니다." ,
//	" TOTAL_COUNT " : "00229"
	protected List<KbSiGunGuVO> RESP_DATA;  //대출상품 리스트
	protected String RESULT_CD = "";
	protected String RESULT_MG = "";
	protected String TOTAL_COUNT = "";
	public List<KbSiGunGuVO> getRESP_DATA() {
		return RESP_DATA;
	}
	public void setRESP_DATA(List<KbSiGunGuVO> rESP_DATA) {
		RESP_DATA = rESP_DATA;
	}
	public String getRESULT_CD() {
		return RESULT_CD;
	}
	public void setRESULT_CD(String rESULT_CD) {
		RESULT_CD = rESULT_CD;
	}
	public String getRESULT_MG() {
		return RESULT_MG;
	}
	public void setRESULT_MG(String rESULT_MG) {
		RESULT_MG = rESULT_MG;
	}
	public String getTOTAL_COUNT() {
		return TOTAL_COUNT;
	}
	public void setTOTAL_COUNT(String tOTAL_COUNT) {
		TOTAL_COUNT = tOTAL_COUNT;
	}
}