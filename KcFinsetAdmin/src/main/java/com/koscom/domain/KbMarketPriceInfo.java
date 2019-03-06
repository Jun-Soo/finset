package com.koscom.domain;

import java.io.Serializable;
import java.util.List;

import com.koscom.kbrealestate.model.KbMarketPriceComplexVO;
import com.koscom.kbrealestate.model.KbMarketPriceOfferingsVO;
import com.koscom.kbrealestate.model.KbMarketPricePriceVO;
import com.koscom.kbrealestate.model.KbMarketPricePyeongVO;

public class KbMarketPriceInfo implements Serializable{
	private static final long serialVersionUID = -1527768391065964236L;
//	RESULT_CD  : 00000000 ,
//	RESULT_MG  : 정상 처리했습니다. ,
//	TOTAL_COUNT  : 00001 ,
//	TOTAL_COUNT3  : 00001
//	TOTAL_COUNT2  : 00001 ,
//	TOTAL_COUNT4  : 00006 ,
//	RESP_DATA  : [] ,
//	RESP_DATA2  : [] ,
//	RESP_DATA3  : [] ,
//	RESP_DATA4  : [] ,
	protected List<KbMarketPriceOfferingsVO> RESP_DATA;  //매물반복 리스트
	protected List<KbMarketPricePriceVO> RESP_DATA2;  //시세반복 리스트
	protected List<KbMarketPriceComplexVO> RESP_DATA3;  //단지정보반복 리스트
	protected List<KbMarketPricePyeongVO> RESP_DATA4;  //평명도반복 리스트
	protected String RESULT_CD = "";
	protected String RESULT_MG = "";
	protected String TOTAL_COUNT = "";
	protected String TOTAL_COUNT2 = "";
	protected String TOTAL_COUNT3 = "";
	protected String TOTAL_COUNT4 = "";
	public List<KbMarketPriceOfferingsVO> getRESP_DATA() {
		return RESP_DATA;
	}
	public void setRESP_DATA(List<KbMarketPriceOfferingsVO> rESP_DATA) {
		RESP_DATA = rESP_DATA;
	}
	public List<KbMarketPricePriceVO> getRESP_DATA2() {
		return RESP_DATA2;
	}
	public void setRESP_DATA2(List<KbMarketPricePriceVO> rESP_DATA2) {
		RESP_DATA2 = rESP_DATA2;
	}
	public List<KbMarketPriceComplexVO> getRESP_DATA3() {
		return RESP_DATA3;
	}
	public void setRESP_DATA3(List<KbMarketPriceComplexVO> rESP_DATA3) {
		RESP_DATA3 = rESP_DATA3;
	}
	public List<KbMarketPricePyeongVO> getRESP_DATA4() {
		return RESP_DATA4;
	}
	public void setRESP_DATA4(List<KbMarketPricePyeongVO> rESP_DATA4) {
		RESP_DATA4 = rESP_DATA4;
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
	public String getTOTAL_COUNT2() {
		return TOTAL_COUNT2;
	}
	public void setTOTAL_COUNT2(String tOTAL_COUNT2) {
		TOTAL_COUNT2 = tOTAL_COUNT2;
	}
	public String getTOTAL_COUNT3() {
		return TOTAL_COUNT3;
	}
	public void setTOTAL_COUNT3(String tOTAL_COUNT3) {
		TOTAL_COUNT3 = tOTAL_COUNT3;
	}
	public String getTOTAL_COUNT4() {
		return TOTAL_COUNT4;
	}
	public void setTOTAL_COUNT4(String tOTAL_COUNT4) {
		TOTAL_COUNT4 = tOTAL_COUNT4;
	}
}