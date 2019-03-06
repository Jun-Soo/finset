package com.koscom.board.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.board.model.BoardForm;
import com.koscom.board.model.BoardInfoVO;
import com.koscom.board.service.BoardManager;
import com.koscom.board.service.BoardWebManager;
import com.koscom.domain.BoardComment;
import com.koscom.domain.BoardInfo;
import com.koscom.domain.BoardManage;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;

@Service("boardWebManager")
public class BoardWebManagerImpl implements BoardWebManager{

	private static final Logger logger = LoggerFactory.getLogger(BoardWebManagerImpl.class);

	@Autowired
	private BoardManager boardManager;

	@Override
	public List<BoardManage> listBoardManage(BoardForm boardForm) {
		return boardManager.listBoardManage(boardForm);
	}

	@Override
	public List<BoardInfoVO> listBoardInfo(BoardForm boardForm) {
		return boardManager.listBoardInfo(boardForm);
	}

	@Override
	public int listBoardInfoCount(BoardForm boardForm) {
		return boardManager.listBoardInfoCount(boardForm);
	}

	@Override
	public BoardInfoVO getBoardInfo(BoardInfo boardInfo) {
		return boardManager.getBoardInfo(boardInfo);
	}

	@Override
	public ReturnClass updateBoardInfoHit(String seq) {
		return boardManager.updateBoardInfoHit(seq);
	}

	@Override
	public BoardManage getBoardManage(BoardManage boardManage) {
		return boardManager.getBoardManage(boardManage);
	}

	@Override
	public List<BoardInfo> listPopupBoard(BoardForm boardForm) {
		return boardManager.listPopupBoard(boardForm);
	}

	@Override
	public List<BoardInfoVO> listBoardInfoSummary(BoardForm boardForm) {
		return boardManager.listBoardInfoSummary(boardForm);
	}

	@Override
	public ReturnClass procBoardManage(BoardManage boardManage) {
		return boardManager.procBoardManage(boardManage);
	}

	@Override
	public ReturnClass createBoardInfo(BoardInfoVO boardInfoVO) throws FinsetException, IOException {
		return boardManager.createBoardInfo(boardInfoVO);
	}

	@Override
	public ReturnClass modifyBoardInfo(BoardInfoVO boardInfoVO) throws FinsetException, IOException {
		return boardManager.modifyBoardInfo(boardInfoVO);
	}

	@Override
	public ReturnClass delBoardInfo(BoardInfo boardInfo) {
		return boardManager.delBoardInfo(boardInfo);
	}

	@Override
	public ReturnClass createBoardComment(BoardComment boardComment) {
		return boardManager.createBoardComment(boardComment);
	}

	@Override
	public List<BoardComment> listBoardComment(String seq_board) {
		return boardManager.listBoardComment(seq_board);
	}

	@Override
	public BoardComment getBoardComment(String seq) {
		return boardManager.getBoardComment(seq);
	}

	@Override
	public ReturnClass modifyBoardComment(BoardComment boardComment) {
		return boardManager.modifyBoardComment(boardComment);
	}

	@Override
	public ReturnClass delBoardComment(String seq) {
		return boardManager.delBoardComment(seq);
	}

	@Override
	public String getBoardNm(String nm_board) {
		return boardManager.getBoardNm(nm_board);
	}

	@Override
	public List<BoardInfoVO> SearchBoard(BoardForm boardForm) {
		return boardManager.SearchBoard(boardForm);
	}

	@Override
	public int SearchBoardCount(BoardForm boardForm) {
		return boardManager.SearchBoardCount(boardForm);
	}

	@Override
	public List<BoardInfoVO> getBoardFileList(BoardInfo boardInfo){
		// TODO Auto-generated method stub
		return boardManager.getBoardFileList(boardInfo);
	}

	@Override
	public BoardInfoVO getBoardFileInfo(BoardInfoVO boardInfoVO){
		// TODO Auto-generated method stub
		return boardManager.getBoardFileInfo(boardInfoVO);
	}

	@Override
	public Map<String, Object> getBoardImg(BoardInfoVO boardInfoVO){
		// TODO Auto-generated method stub
		return boardManager.getBoardImg(boardInfoVO);
	}
}
