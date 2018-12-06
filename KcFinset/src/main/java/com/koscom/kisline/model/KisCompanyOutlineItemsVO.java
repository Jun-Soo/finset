package com.koscom.kisline.model;

import java.io.Serializable;
import java.util.List;

public class KisCompanyOutlineItemsVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278481316473690401L;
	public int count = 0;
	public List<KisCompanyOutlineVO> item;  //대출상품 리스트
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<KisCompanyOutlineVO> getItem() {
		return item;
	}
	public void setItem(List<KisCompanyOutlineVO> item) {
		this.item = item;
	}
}