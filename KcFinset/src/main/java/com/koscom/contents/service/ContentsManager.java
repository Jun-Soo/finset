package com.koscom.contents.service;

import java.util.List;
import java.util.Map;

import com.koscom.contents.model.ContentsForm;
import com.koscom.contents.model.ContentsVO;
import com.koscom.util.ReturnClass;


public interface ContentsManager {

	/**
	 * 뉴스관리 리스트 조회
	 * @param contentsForm
	 * @return List<ContentsVO>
	 */
	public List<ContentsVO> listNewsManagePg(ContentsForm contentsForm);

	/**
	 * 뉴스관리 리스트 건수 조회
	 * @param contentsForm
	 * @return int
	 */
	public int listNewsManageCount(ContentsForm contentsForm);

	/**
	 * 뉴스관리 정보 조회
	 * @param contentsVO
	 * @return ContentsVO
	 */
	public ContentsVO getNewsManageInfo(ContentsVO contentsVO);

	/**
	 * 뉴스관리_기사등록 썸네일 이미지 불러오기
	 * @param contentsVO
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getApiNewsImgInfo(ContentsVO contentsVO);

	/**
	 * 뉴스관리 저장
	 * @param contentsVO
	 * @return ReturnClass
	 */
	public ReturnClass modifyNewsManage(ContentsVO contentsVO);

	/**
	 * 뉴스관리 삭제
	 * @param contentsVO
	 * @return ReturnClass
	 */
	public ReturnClass delNewsManage(ContentsVO contentsVO);

	/**
	 * 소비분류관리_지출항목관리 목록조회
	 * @param codeForm
	 * @return List<ContentsVO>
	 */
	public List<ContentsVO> listConsumeSpendMng(ContentsForm contentsForm);
	public List<ContentsVO> listConsumeSchCdClass(ContentsForm contentsForm);
	/**
	 * 소비분류관리_지출항목관리 정보조회
	 * @param contentsVO
	 * @return ContentsVO
	 */
	public ContentsVO getConsumeSpendMng(ContentsVO contentsVO);

	/**
	 * 소비분류관리_지출항목관리 등록/수정
	 * @param contentsVO
	 * @return ReturnClass
	 */
	public ReturnClass procConsumeSpendMng(ContentsVO contentsVO);

	/**
	 * 소비분류관리_지출항목관리 삭제
	 * @param contentsVO
	 * @return ReturnClass
	 */
	public ReturnClass delConsumeSpendMng(ContentsVO contentsVO);

	/**
	 * 소비분류관리_카드업종관리 카드사 select목록조회
	 * @param
	 * @return List<ContentsVO>
	 */
	public List<ContentsVO> listConsumeCardFcInfo();

	/**
	 * 소비분류관리_카드업종관리 목록조회
	 * @param codeForm
	 * @return List<ContentsVO>
	 */
	public List<ContentsVO> listConsumeCardMng(ContentsForm contentsForm);

	/**
	 * 소비분류관리_카드업종관리 정보조회
	 * @param contentsVO
	 * @return ContentsVO
	 */
	public ContentsVO getConsumeCardMng(ContentsVO contentsVO);

	/**
	 * 소비분류관리_카드업종관리 등록/수정
	 * @param contentsVO
	 * @return ReturnClass
	 */
	public ReturnClass procConsumeCardMng(ContentsVO contentsVO);

	/**
	 * 소비분류관리_카드업종관리 삭제
	 * @param contentsVO
	 * @return ReturnClass
	 */
	public ReturnClass delConsumeCardMng(ContentsVO contentsVO);

	
}
