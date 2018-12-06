package com.koscom.scrap.model;

import java.io.Serializable;
import java.util.List;

import com.koscom.scrap.model.sub.AnAllListVO;

public class AnAllVO implements Serializable{
	private static final long serialVersionUID = -798581988112096476L;
	String ERROR_CODE = "";			//결과코드(00000000) 성공 나머지 실패,
	String ERROR_MESSAGE = "";			//결과메시지,
	String START_DATE = "";			//계좌거래내역 검색 시작일자20170601,
	String END_DATE = "";			//계좌거래내역 검색 검색마지막일자20170922,
	List<AnAllListVO> LIST;			//계좌리스트
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
	public String getSTART_DATE() {
		return START_DATE;
	}
	public void setSTART_DATE(String sTART_DATE) {
		START_DATE = sTART_DATE;
	}
	public String getEND_DATE() {
		return END_DATE;
	}
	public void setEND_DATE(String eND_DATE) {
		END_DATE = eND_DATE;
	}
	public List<AnAllListVO> getLIST() {
		return LIST;
	}
	public void setLIST(List<AnAllListVO> lIST) {
		LIST = lIST;
	}
}