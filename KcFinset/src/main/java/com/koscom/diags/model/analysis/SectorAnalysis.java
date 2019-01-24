package com.koscom.diags.model.analysis;

import java.io.Serializable;
import java.util.List;

public class SectorAnalysis extends AnalysisBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4032958381202519309L;
	// 
	private List<SectorExposure> sectorExposures;
	public List<SectorExposure> getSectorExposures() {
		return sectorExposures;
	}
	public void setSectorExposures(List<SectorExposure> sectorExposures) {
		this.sectorExposures = sectorExposures;
	}

}
