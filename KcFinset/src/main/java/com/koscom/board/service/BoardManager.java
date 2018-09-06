package com.koscom.board.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.koscom.board.model.BoardForm;
import com.koscom.board.model.BoardInfoVO;
import com.koscom.domain.BoardInfo;
//import com.koscom.domain.BoardComment;
//import com.koscom.domain.BoardInfo;
//import com.koscom.domain.BoardManage;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;

public interface BoardManager {
	/**
	 * 게시판 조회(게시판별 리스트)
	 * @param boardManage
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
	 * 게시글 정보 조회
	 * @param boardInfo
	 * @return
	 */
	BoardInfoVO getBoardInfo(BoardInfo boardInfo);

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
	 * BLOB 이미지 단건
	 * @param BoardInfo
	 * @return BoardInfoVO
	 */
	BoardInfoVO getBoardFileInfo(BoardInfoVO boardInfoVO);

	/**
	 * BLOB 이미지 불러오기
	 * @param String
	 * @return ResponseEntity<byte[]>
	 */
	Map<String, Object> getBoardImg(BoardInfoVO boardInfoVO);
}
