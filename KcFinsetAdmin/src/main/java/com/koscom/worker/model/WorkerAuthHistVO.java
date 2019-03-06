package com.koscom.worker.model;

import com.koscom.domain.WorkerAuthHist;

public class WorkerAuthHistVO extends WorkerAuthHist{
	
	/**
	 * 01 : 결재
	 * 02 : 프로그램
	 */
	public static final String CD_AUTH_01 = "01";
	public static final String CD_AUTH_02 = "02";
	
	/**
	 * 00 : 등록
	 * 99 : 삭제
	 */
	public static final String CD_AUTH_PROC_00 = "00";
	public static final String CD_AUTH_PROC_99 = "99";
}
