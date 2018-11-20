package com.koscom.kbrealestate.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;
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
	@SerializedName("RESP_DATA")
	protected List<KbMarketPriceOfferingsVO> resp_data;  //매물반복 리스트
	@SerializedName("RESP_DATA2")
	protected List<KbMarketPricePriceVO> resp_data2;  //시세반복 리스트
	@SerializedName("RESP_DATA3")
	protected List<KbMarketPriceComplexVO> resp_data3;  //단지정보반복 리스트
	@SerializedName("RESP_DATA4")
	protected List<KbMarketPricePyeongVO> resp_data4;  //평명도반복 리스트
	@SerializedName("RESULT_CD")
	protected String result_cd;
	@SerializedName("RESULT_MG")
	protected String result_mg;
	@SerializedName("TOTAL_COUNT")
	protected String total_count;
	@SerializedName("TOTAL_COUNT2")
	protected String total_count2;
	@SerializedName("TOTAL_COUNT3")
	protected String total_count3;
	@SerializedName("TOTAL_COUNT4")
	protected String total_count4;
	
	public List<KbMarketPriceOfferingsVO> getResp_data() {
		return resp_data;
	}
	public void setResp_data(List<KbMarketPriceOfferingsVO> resp_data) {
		this.resp_data = resp_data;
	}
	public List<KbMarketPricePriceVO> getResp_data2() {
		return resp_data2;
	}
	public void setResp_data2(List<KbMarketPricePriceVO> resp_data2) {
		this.resp_data2 = resp_data2;
	}
	public List<KbMarketPriceComplexVO> getResp_data3() {
		return resp_data3;
	}
	public void setResp_data3(List<KbMarketPriceComplexVO> resp_data3) {
		this.resp_data3 = resp_data3;
	}
	public List<KbMarketPricePyeongVO> getResp_data4() {
		return resp_data4;
	}
	public void setResp_data4(List<KbMarketPricePyeongVO> resp_data4) {
		this.resp_data4 = resp_data4;
	}
	public String getResult_cd() {
		return result_cd;
	}
	public void setResult_cd(String result_cd) {
		this.result_cd = result_cd;
	}
	public String getResult_mg() {
		return result_mg;
	}
	public void setResult_mg(String result_mg) {
		this.result_mg = result_mg;
	}
	public String getTotal_count() {
		return total_count;
	}
	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}
	public String getTotal_count2() {
		return total_count2;
	}
	public void setTotal_count2(String total_count2) {
		this.total_count2 = total_count2;
	}
	public String getTotal_count3() {
		return total_count3;
	}
	public void setTotal_count3(String total_count3) {
		this.total_count3 = total_count3;
	}
	public String getTotal_count4() {
		return total_count4;
	}
	public void setTotal_count4(String total_count4) {
		this.total_count4 = total_count4;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}