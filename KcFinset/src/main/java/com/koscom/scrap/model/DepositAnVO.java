package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

import com.koscom.scrap.model.sub.DepositAnListVO;

public class DepositAnVO implements Serializable{
	private static final long serialVersionUID = 6844369973440826972L;
	String ERROR_CODE = "";		//결과코드(00000000) 성공 나머지 실패,
	String ERROR_MESSAGE = "";	//거래 결과가 없습니다.(거래내역조회 시 거래내역 없음),	결과메시지                         	
	List<DepositAnListVO> LIST;	//계좌리스트   
	public String getERROR_CODE() {
		return ERROR_CODE;
	}
	public void setERROR_CODE(String eRROR_CODE) {
		ERROR_CODE = eRROR_CODE;
	}
	public String getERROR_MESSAGE() {
		return ERROR_MESSAGE;
	}
	public void setERROR_MESSAGE(String eRROR_MESSAGE) {
		ERROR_MESSAGE = eRROR_MESSAGE;
	}
	public List<DepositAnListVO> getLIST() {
		return LIST;
	}
	public void setLIST(List<DepositAnListVO> lIST) {
		LIST = lIST;
	}
}