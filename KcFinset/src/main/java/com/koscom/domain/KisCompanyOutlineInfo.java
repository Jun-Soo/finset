package com.koscom.domain;

import java.io.Serializable;
import java.util.List;

import com.koscom.fss.model.FssCreditLoanOptionVO;
import com.koscom.fss.model.FssCreditLoanProductVO;
import com.koscom.kisline.model.KisCompanyOutlineItemsVO;
import com.koscom.kisline.model.KisSrchByNameItemsVO;

public class KisCompanyOutlineInfo implements Serializable{
	//	" items " : {
//	" count " : "1" ,
//	" realtotalcount " : "1" ,
//	" searchTime " : "231" ,
//	" totalcount " : "1" ,
//	" item " : []
//}
	public KisCompanyOutlineItemsVO items;  //매물반복 리스트
	public KisCompanyOutlineItemsVO getItems() {
		return items;
	}
	public void setItems(KisCompanyOutlineItemsVO items) {
		this.items = items;
	}
}