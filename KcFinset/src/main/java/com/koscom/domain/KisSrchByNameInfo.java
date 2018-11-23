package com.koscom.domain;

import java.io.Serializable;

import com.koscom.kisline.model.KisSrchByNameItemsVO;

public class KisSrchByNameInfo implements Serializable{
	private static final long serialVersionUID = -4120880245383504257L;
	//	" items " : {
//	" count " : "1" ,
//	" realtotalcount " : "1" ,
//	" searchTime " : "231" ,
//	" totalcount " : "1" ,
//	" item " : []
//}
	public KisSrchByNameItemsVO items;  //매물반복 리스트
	public KisSrchByNameItemsVO getItems() {
		return items;
	}
	public void setItems(KisSrchByNameItemsVO items) {
		this.items = items;
	}
}