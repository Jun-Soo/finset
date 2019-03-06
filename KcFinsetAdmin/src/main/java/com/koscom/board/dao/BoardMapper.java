package com.koscom.board.dao;

import java.util.List;
import java.util.Map;

import com.koscom.board.model.BoardForm;
import com.koscom.board.model.BoardInfoVO;
import com.koscom.domain.BoardComment;
import com.koscom.domain.BoardInfo;
import com.koscom.domain.BoardManage;

public interface BoardMapper {

	/**
	 * 게시판 관리 리스트 조회
	 * @param boardForm
	 * @return
	 */
	List<BoardManage> listBoardManage(BoardForm boardForm);

	/**
	 * 게시판 생성, 수정
	 * @param agencyVO
	 * @return
	 */
	int procBoardManage(BoardManage boardManage);

	/**
	 * 관리시 게시판 정보 조회
	 * @param boardManage
	 * @return
	 */
	BoardManage getBoardManage(BoardManage boardManage);

	/**
	 * 게시판 조회(게시판별 리스트)
	 * @param boardForm
	 * @return
	 */
	List<BoardInfoVO> listBoardInfo(BoardForm boardForm);

	/**
	 * 게시판(게시판별 리스트) 건수 조회
	 * @param boardForm
	 * @return
	 */
	int listBoardInfoCount(BoardForm boardForm);

	/**
	 * 게시글 등록
	 * @param boardInfo
	 * @return
	 */
	int createBoardInfo(BoardInfoVO boardInfoVO);

	/**
	 * 게시글 수정
	 * @param boardInfo
	 * @return
	 */
	int modifyBoardInfo(BoardInfoVO boardInfoVO);

	/**
	 * 게시글 정보 조회
	 * @param boardInfo
	 * @return
	 */
	BoardInfoVO getBoardInfo(BoardInfo boardInfo);

	/**
	 * 게시글 삭제
	 * @param boardInfo
	 * @return
	 */
	int delBoardInfo(BoardInfo boardInfo);

	/**
	 * 댓글 등록
	 * @param boardComment
	 * @return
	 */
	int createBoardComment(BoardComment boardComment);

	/**
	 * 글별 댓글 리스트 조회
	 * @param seq_board
	 * @return
	 */
	List<BoardComment> listBoardComment(String seq_board);

	/**
	 * 댓글 정보 조회
	 * @param seq
	 * @return
	 */
	BoardComment getBoardComment(String seq);

	/**
	 * 댓글 수정
	 * @param boardComment
	 * @return
	 */
	int modifyBoardComment(BoardComment boardComment);

	/**
	 * 댓글 삭제
	 * @param seq
	 * @return
	 */
	int delBoardComment(String seq);

	/**
	 * 게시글 조회수 업데이트
	 * @param seq
	 * @return
	 */
	int updateBoardInfoHit(String seq);

	/**
	 * 팝업 게시글 조회
	 * @param boardForm
	 * @return
	 */
	List<BoardInfo> listPopupBoard(BoardForm boardForm);

	/**
	 * 간단 게시글 조회
	 * @param boardForm
	 * @return
	 */
	List<BoardInfoVO> listBoardInfoSummary(BoardForm boardForm);

	/**
	 * 게시판 명 조회
	 * @param boardForm
	 * @return
	 */
	String getBoardNm(String nm_board);

	/**
	 * 자주묻는질문 검색
	 * @param
	 * @return
	 */
	public List<BoardInfoVO> SearchBoard(BoardForm boardForm);

	/**
	 * 자주묻는질문 검색 건수 조회
	 * @param boardForm
	 * @return
	 */
	int SearchBoardCount(BoardForm boardForm);
	/**
	 * 저장 파일명 조회
	 * @param boardInfo
	 * @return
	 */
	String getOrgFileName(BoardInfo boardInfo);

	/** srchou */
	/**
	 * BLOB 이미지 저장유무
	 * @param String
	 * @return int
	 */
	int dupliBoardFileInfo(BoardInfoVO boardInfoVO);


	/**
	 * BLOB 이미지 저장
	 * @param String
	 * @return int
	 */
	int createBoardFileInfo(BoardInfoVO boardInfoVO);

	/**
	 * BLOB 이미지 수정
	 * @param String
	 * @return int
	 */
	int modifyBoardFileInfo(BoardInfoVO boardInfoVO);

	/**
	 * BLOB 이미지 List
	 * @param String
	 * @return Map
	 */
	List<BoardInfoVO> getBoardFileList(BoardInfo boardInfo);

	/**
	 * BLOB 이미지 단건
	 * @param BoardInfoVO
	 * @return BoardInfoVO
	 */
	BoardInfoVO getBoardFileInfo(BoardInfoVO boardInfoVO);

	/**
	 * BLOB 이미지 불러오기
	 * @param String
	 * @return ResponseEntity<byte[]>
	 */
	Map<String, Object> getBoardImg(BoardInfoVO boardInfoVO);

	/**
	 * 게시판 등록시 seq값 셋팅
	 * @param String
	 * @return String
	 */
	String getBoardSeq();
}
