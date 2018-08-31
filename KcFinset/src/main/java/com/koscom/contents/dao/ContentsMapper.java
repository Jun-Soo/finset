package com.koscom.contents.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.koscom.contents.model.ContentsForm;
import com.koscom.contents.model.ContentsVO;

@Repository
public interface ContentsMapper {

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
	 * @return contentsVO
	 */
	public ContentsVO getNewsManageInfo(ContentsVO contentsVO);

	/**
	 * 뉴스관리_기사등록 썸네일 이미지 불러오기
	 * @param contentsVO
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getApiNewsImgInfo(ContentsVO contentsVO);

	/**
	 * 뉴스 썸네일 이미지 저장
	 * @param contentsVO
	 * @return int
	 */
	public int createApiNewsFileInfo(ContentsVO contentsVO);

	/**
	 * 뉴스 썸네일 이미지 수정
	 * @param contentsVO
	 * @return int
	 */
	public int modifyApiNewsFileInfo(ContentsVO contentsVO);

	/**
	 * 뉴스관리 저장
	 * @param contentsVO
	 * @return int
	 */
	public int modifyNewsManage(ContentsVO contentsVO);

	/**
	 * 뉴스관리 썸네일이미지 삭제
	 * @param contentsVO
	 * @return int
	 */
	public int delApiNewsFileInfo(ContentsVO contentsVO);

	/**
	 * 뉴스관리 삭제
	 * @param contentsVO
	 * @return int
	 */
	public int delNewsManage(ContentsVO contentsVO);

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
	public int procConsumeSpendMng(ContentsVO contentsVO);

	/**
	 * 소비분류관리_지출항목관리 삭제
	 * @param contentsVO
	 * @return ReturnClass
	 */
	public int delConsumeSpendMng(ContentsVO contentsVO);
	public int updateInitConsumeCardCd(ContentsVO contentsVO);

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
	public int procConsumeCardMng(ContentsVO contentsVO);

	/**
	 * 소비분류관리_카드업종관리 삭제
	 * @param contentsVO
	 * @return ReturnClass
	 */
	public int delConsumeCardMng(ContentsVO contentsVO);

	

}
