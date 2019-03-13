package com.koscom.fincorp.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.koscom.domain.FincorpInfo;
import com.koscom.fincorp.model.FincorpForm;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.model.FincorpfcNminfoForm;
import com.koscom.fincorp.model.FincorpfcNminfoVO;
import com.koscom.util.ReturnClass;
import com.koscom.worker.model.WorkerForm;
import com.koscom.worker.model.WorkerVO;


public interface FincorpMapper {

	/**
	 * 금융사 목록 조회(검색)
	 * @param fincorpForm
	 * @return
	 */
//	List<FincorpVO> listFincorp(FincorpForm fincorpForm);

	/**
	 * 금융사 상세정보 조회
	 * @param FincorpInfo
	 * @return
	 */
	FincorpVO getFincorpInfo(FincorpInfo fincorpInfo);

	/**
	 * 금융사목록 카운트 조회
	 * @param fincorpForm
	 * @return
	 */
	int listFincorpCount(FincorpForm fincorpForm);

	/**
	 * 금융사 이미지파일 카운트 조회
	 * @param fincorpVO
	 * @return
	 */
	int getFileCount(String path_file1);

	/**
	 * 금융기관업권별 목록 조회
	 * @param fincorpVO
	 * @return
	 */
	List<FincorpVO> listFincorpInfo(FincorpForm fincorpForm);

	/**
	 *
	 * @param fincorpVO
	 * @return
	 */
	List<FincorpVO> listCodeFincorp(FincorpVO fincorpVO);
	/**
	 *
	 * @param fincorpVO
	 * @return
	 */
	HashMap getImgBi(FincorpVO vo);

	/**
	 * 금융사 추가
	 * @param FincorpVO
	 */
	int createFincorp(FincorpVO fincorpVO);

	/**
	 *  금융사 정보 등록/수정
	 *
	 * @param FincorpVO
	 * @return
	 */
	int procFincorpInfo(FincorpVO fincorpVO);


	/**
	 * 금융사 정보 삭제
	 * @param friendVO
	 * @return
	 */
	int delFincorpInfo(FincorpVO fincorpVO);
	String getNmFc(String cd_fc);

	//자동완성
	public List<WorkerVO> listWorkerInfoJson(WorkerForm form);

	List<String> getFcInfo(FincorpInfo fincorpInfo);

	/**
	 * 금융기관 전체 조회 전문에서 사용
	 * @param fincorpVO
	 * @return
	 */
	List<FincorpVO> listFincorpEdocInfo(FincorpForm fincorpForm);

	/**
	 * 금융기관명 자동완성
	 * @param
	 * @return
	 */
	public List<FincorpVO> listNmfcInfoJson(WorkerForm form);

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
	 * 금융사 이미지 등록
	 * @param vo
	 */
	int regFcImg(FincorpVO vo);

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
	int createFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm);

	/**
	 * 금융사명 KCB 사전연계 정보 수정
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
	int updFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm);

	/**
	 * 금융사명 KCB 사전연계 정보 삭제
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
	int delFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm);

	/**
	 *
	 * @param vo
	 * @return FincorpfcNminfoVO
	 */
	List<HashMap> tempQuery(FincorpVO vo);

	/**
	 * 신규 금융사코드 및 금융사명 체크
	 * @param vo
	 * @return FincorpfcNminfoVO
	 */
	int chkCdfc(FincorpVO fincorpVO);

	/**
	 * 금융사 스크래핑여부 변경(COOCON API관리 등록/삭제)
	 *
	 * @param fincorpVO
	 * @return
	 */
	int updateYnScrap(FincorpVO fincorpVO);

	/**
	 * COOCON 금융사 코드(스크래핑 대상)
	 *
	 * @param String
	 * @return List<String>
	 */
	List<String> getCooconFcCd(String type_fc);	
}
