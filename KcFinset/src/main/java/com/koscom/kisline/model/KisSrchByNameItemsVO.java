package com.koscom.kisline.model;

import java.io.Serializable;
import java.util.List;

public class KisSrchByNameItemsVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4393916950730229421L;
	public int count = 0;		//*	업체코드
	public int realtotalcount = 0;		//*	업체코드
	public String searchTime = "";		//*	업체코드
	public int totalcount = 0;		//*	업체코드
	public List<KisSrchByNameVO> item;  //대출상품 리스트
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getRealtotalcount() {
		return realtotalcount;
	}
	public void setRealtotalcount(int realtotalcount) {
		this.realtotalcount = realtotalcount;
	}
	public String getSearchTime() {
		return searchTime;
	}
	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public List<KisSrchByNameVO> getItem() {
		return item;
	}
	public void setItem(List<KisSrchByNameVO> item) {
		this.item = item;
	}
}