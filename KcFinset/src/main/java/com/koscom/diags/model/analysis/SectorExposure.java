package com.koscom.diags.model.analysis;

import java.io.Serializable;

public class SectorExposure implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4032958381202519309L;
	
	private String sectorNm;		// sector 명 
	private Long invest;

	public SectorExposure() {};
	public SectorExposure(String sectorNm, Long invest) {
		this.sectorNm = sectorNm;
		this.invest = invest;
	}

	public String getSectorNm() {
		return sectorNm;
	}
	public void setSectorNm(String sectorNm) {
		this.sectorNm = sectorNm;
	}

	public Long getInvest() {
		return invest;
	}
	public void setInvest(Long invest) {
		this.invest = invest;
	}

}
