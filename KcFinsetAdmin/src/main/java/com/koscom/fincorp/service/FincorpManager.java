package com.koscom.fincorp.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.koscom.fincorp.model.FincorpForm;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.model.FincorpfcNminfoForm;
import com.koscom.fincorp.model.FincorpfcNminfoVO;
import com.koscom.util.ReturnClass;
import com.koscom.worker.model.WorkerForm;

public interface FincorpManager {

	/**
	 * 금융사 상세정보 조회
	 * @param id_fincorp
	 * @return
	 */
	FincorpVO getFincorpInfo(String id_fincorp);

	/**
	 * 금융사 상세정보 조회 - 업권 기준
	 * @param id_fincorp
	 * @return
	 */
	List<String> getFcInfo(String cd_fin);

	/**
	 * 금융사 목록 조회(검색)
	 * @param FincorpVO
	 * @return
	 */
	List<FincorpVO> listFincorpInfo(FincorpForm fincorpForm);

	/**
	 * 금융사 목록 카운트 조회
	 * @param FincorpForm
	 * @return
	 */
	int listFincorpCount(FincorpForm fincorpForm);

	/**
	 * 금융사명 리스트(검색)
	 * @param FincorpVO
	 * @return
	 */
	List<FincorpVO> listCodeFincorp(FincorpVO fincorpVO);

	/**
	 * 금융사 정보 등록 수정
	 *
	 * @param fincorpVO
	 * @return
	 */
	ReturnClass procFincorpInfo(FincorpVO fincorpVO) throws IOException;

	/**
	 * 금융사 추가
	 * @param FincorpVO
	 * @return
	 */
	ReturnClass createFincorp(FincorpVO fincorpVO);

	/**
	 * 금융사 정보 삭제
	 * @param FincorpVO
	 * @return
	 */
	ReturnClass delFincorpInfo(FincorpVO fincorpVO);

	String getNmFc(String cd_fc);

	/**
	 * 자동완성 리스트
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ReturnClass listWorkerInfoJson(WorkerForm form);

    /**
     * 전문에서 사용할 금융사 목록 조회
     * @param FincorpVO
     * @return
     */
    List<FincorpVO> listFincorpEdocInfo(FincorpForm fincorpForm);

	/**
	 * 금융기관명 자동완성 리스트
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ReturnClass listNmfcInfoJson(WorkerForm form);

	/**
	 * 금융사 중복체크
	 * @param fincorpVO
	 * @return
	 */
	int getFcCnt(FincorpVO fincorpVO);

	/**
	 * 금융사 이미지 PATH
	 * @param String
	 * @return String
	 */
	String getFcPathFile(String cd_fc);
	/**
	 * 금융사 이미지 PATH
	 * @param String
	 * @return String
	 */
	byte[] getImgBi(String path_file1) throws Exception;
	/**
	 * 금융사 이미지 PATH
	 * @param String
	 * @return String
	 */
	int regFcImg(String nm_fc, byte[] fileData,String fileName);

/* srchou 추가 */

	/**
	 * 금융사명 KCB 사전연계 list
	 * @param FincorpfcNminfoForm
	 * @return FincorpfcNminfoVO
	 */
	List<FincorpfcNminfoVO> listFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm);

	/**
	 * 금융사명 KCB 사전연계 정보 삽입
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
	String createFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm);

	/**
	 * 금융사명 KCB 사전연계 정보 수정
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
	String updFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm);

	/**
	 * 금융사명 KCB 사전연계 정보 삭제
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
	String delFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm);

	List<HashMap> tempQuery(FincorpVO vo);

	/**
	 * 신규 금융사코드 및 금융사명 체크
	 * @param vo
	 * @return FincorpfcNminfoVO
	 */
	ReturnClass chkCdfc(FincorpVO fincorpVO);

	/**
	 * 금융사 스크래핑여부 변경(COOCON API관리 등록/삭제)
	 *
	 * @param fincorpVO
	 * @return
	 */
	ReturnClass updateYnScrap(FincorpVO fincorpVO);
	
	/**
	 * COOCON 금융사 코드(스크래핑 대상)
	 *
	 * @param String
	 * @return List<String>
	 */
	List<String> getCooconFcCd(String type_fc);
	
	
	/**
	 * 파일 업로드
	 * @param FincorpVO
	 * @return void
	 */
	void uploadFile(FincorpVO fincorpVO);

}
