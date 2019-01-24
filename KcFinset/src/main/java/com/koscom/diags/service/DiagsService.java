package com.koscom.diags.service;

import com.koscom.diags.model.DiagsReport;
import com.koscom.diags.model.DiagsResult;
import com.koscom.diags.model.DiagsStockReport;

public interface DiagsService {

	/**
	 * 진단관련 결과 확인
	 * 
	 * @param String
	 * @return DiagsResult
	 */
	public DiagsResult diagsResult(String noPerson);

	/**
	 * 진단 결과 레포트 
	 * 
	 * @param noPerson
	 * @return
	 */
	public DiagsReport diagsReport(String noPerson);
	/**
	 * 진단결과 종목별 레포트 
	 * @param noPerson
	 * @return
	 */
	public DiagsStockReport diagsStockReport(String noPerson);


}
