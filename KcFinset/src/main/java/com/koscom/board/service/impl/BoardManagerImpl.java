package com.koscom.board.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.board.dao.BoardMapper;
import com.koscom.board.model.BoardForm;
import com.koscom.board.model.BoardInfoVO;
import com.koscom.board.service.BoardManager;
import com.koscom.domain.BoardInfo;
//import com.koscom.domain.BoardComment;
//import com.koscom.domain.BoardInfo;
//import com.koscom.domain.BoardManage;
import com.koscom.util.Constant;
//import com.koscom.util.FileUpload;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;

@Service("boardManager")
public class BoardManagerImpl implements BoardManager {

	private static final Logger logger = LoggerFactory.getLogger(BoardManagerImpl.class);

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardInfoVO> listBoardInfo(BoardForm boardForm) {
		return boardMapper.listBoardInfo(boardForm);
	}

	@Override
	public int listBoardInfoCount(BoardForm boardForm) {
		return boardMapper.listBoardInfoCount(boardForm);
	}

	@Override
	public BoardInfoVO getBoardInfo(BoardInfo boardInfo) {
		return boardMapper.getBoardInfo(boardInfo);
	}

	@Override
	public String getBoardNm(String nm_board){
		return boardMapper.getBoardNm(nm_board);
	}

	@Override
	public List<BoardInfoVO> SearchBoard(BoardForm boardForm) {
		return boardMapper.SearchBoard(boardForm);
	}

	@Override
	public int SearchBoardCount(BoardForm boardForm) {
		return boardMapper.SearchBoardCount(boardForm);
	}

	@Override
	public BoardInfoVO getBoardFileInfo(BoardInfoVO boardInfoVO){
		// TODO Auto-generated method stub
		return boardMapper.getBoardFileInfo(boardInfoVO);
	}

	@Override
	public Map<String, Object> getBoardImg(BoardInfoVO boardInfoVO){
		// TODO Auto-generated method stub
		return boardMapper.getBoardImg(boardInfoVO);
	}
}
