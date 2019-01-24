package com.koscom.diags.model.analysis;

import java.io.Serializable;

public class StockSector implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3041241914973650849L;
	
	protected String isincode; //종목코드  
	//
	protected String sectorCd;	// sectorCd
	protected String sectorNm;	// sectorNm
	
	public String getIsincode() {
		return isincode;
	}
	public void setIsincode(String isincode) {
		this.isincode = isincode;
	}
	
	public String getSectorCd() {
		return sectorCd;
	}
	public void setSectorCd(String sectorCd) {
		this.sectorCd = sectorCd;
	}

	public String getSectorNm() {
		return sectorNm;
	}
	public void setSectorNm(String sectorNm) {
		this.sectorNm = sectorNm;
	}



}
