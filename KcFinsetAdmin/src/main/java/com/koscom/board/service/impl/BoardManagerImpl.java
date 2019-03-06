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
import com.koscom.domain.BoardComment;
import com.koscom.domain.BoardInfo;
import com.koscom.domain.BoardManage;
import com.koscom.util.Constant;
import com.koscom.util.FileUpload;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;

@Service("boardManager")
public class BoardManagerImpl implements BoardManager {

	private static final Logger logger = LoggerFactory.getLogger(BoardManagerImpl.class);

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardManage> listBoardManage(BoardForm boardForm) {
		return boardMapper.listBoardManage(boardForm);
	}

	@Override
	public ReturnClass procBoardManage(BoardManage boardManage) {

		if(1 != boardMapper.procBoardManage(boardManage)){
			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
	}

	@Override
	public BoardManage getBoardManage(BoardManage boardManage) {
		return boardMapper.getBoardManage(boardManage);
	}

	@Override
	public List<BoardInfoVO> listBoardInfo(BoardForm boardForm) {
		return boardMapper.listBoardInfo(boardForm);
	}

	@Override
	public int listBoardInfoCount(BoardForm boardForm) {
		return boardMapper.listBoardInfoCount(boardForm);
	}

	@Override
	public ReturnClass createBoardInfo(BoardInfoVO boardInfoVO) throws IOException, FinsetException {

        // 파일 업로드
        FileUpload fileUpload = new FileUpload();

        if(boardInfoVO.getFile1() != null){
            if(boardInfoVO.getFile1().getSize() > 0){
                // 실제 저장될 파일명세팅
                /**
                 * Todo:보여지는 파일명과 저장하는 파일명을 다르게 하는 로직 추가해야함.
                 */
                String fileName1 = fileUpload.nameCheck(fileUpload.getFileName(boardInfoVO.getFile1().getOriginalFilename()));
                if ( fileUpload.fileUpload(null, boardInfoVO.getFile1(), fileName1, true) ) {
                    // 실제 저장된 경로명
                    boardInfoVO.setUrl_files1(fileName1);
                    boardInfoVO.setOrg_files1(fileName1);
                    // 실제 저장된 파일명
                    //boardInfoVO.setNm_file1(fileName1.substring(fileName1.lastIndexOf('/')+1, fileName1.length()));
                }
            }
        }
        if(boardInfoVO.getFile2() != null){

            if(boardInfoVO.getFile2().getSize() > 0){
                /**
                 * Todo:보여지는 파일명과 저장하는 파일명을 다르게 하는 로직 추가해야함.
                 */
                String fileName2 = fileUpload.nameCheck(fileUpload.getFileName(boardInfoVO.getFile2().getOriginalFilename()));
                if ( fileUpload.fileUpload(null, boardInfoVO.getFile2(), fileName2, true) ) {
                    // 실제 저장된 경로명
                    boardInfoVO.setUrl_files2(fileName2);
                    boardInfoVO.setOrg_files2(fileName2);
                    // 실제 저장된 파일명
                    //boardInfoVO.setNm_file2(fileName2.substring(fileName2.lastIndexOf('/')+1, fileName2.length()));
                }
            }
        }
        if(boardInfoVO.getImg_files1() != null && boardInfoVO.getImg_files1().length > 0){
			boardInfoVO.setImg_files(boardInfoVO.getImg_files1());
			boardInfoVO.setNm_img_files(boardInfoVO.getNm_img_files1());
			boardInfoVO.setFile_type("01");
			String result = boardMapper.getBoardSeq();
			boardInfoVO.setSeq(result);
			int resultData = boardMapper.createBoardFileInfo(boardInfoVO);
			if(1 != resultData){
				return new ReturnClass(Constant.FAILED, "파일1 실패하였습니다.");
			}
        }
        if(boardInfoVO.getImg_files2() != null && boardInfoVO.getImg_files2().length >0){
        	boardInfoVO.setImg_files(boardInfoVO.getImg_files2());
			boardInfoVO.setNm_img_files(boardInfoVO.getNm_img_files2());
			boardInfoVO.setFile_type("02");
			String result = boardMapper.getBoardSeq();
			boardInfoVO.setSeq(result);
			int resultData = boardMapper.createBoardFileInfo(boardInfoVO);
			if(1 != resultData){
				return new ReturnClass(Constant.FAILED, "파일1 실패하였습니다.");
			}
        }
		int result = boardMapper.createBoardInfo(boardInfoVO);
		if(1 != result){
			throw new FinsetException("등록 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "등록 성공하였습니다.",(Object) boardInfoVO.getId_board());
	}

	@Override
	public ReturnClass modifyBoardInfo(BoardInfoVO boardInfoVO) throws IOException, FinsetException {
		// 파일 업로드
		FileUpload fileUpload = new FileUpload();

		if(boardInfoVO.getFile1() != null){
			if(boardInfoVO.getFile1().getSize() > 0){
				// 실제 저장될 파일명세팅
				String fileName1 = fileUpload.nameCheck(fileUpload.getFileName(boardInfoVO.getFile1().getOriginalFilename()));
				if ( fileUpload.fileUpload(null, boardInfoVO.getFile1(), fileName1, true) ) {
					// 실제 저장된 경로명
					boardInfoVO.setUrl_files1(fileName1);
					// 실제 저장된 파일명
					//boardInfoVO.setNm_file1(fileName1.substring(fileName1.lastIndexOf('/')+1, fileName1.length()));
				}
			}
		}
		if(boardInfoVO.getFile2() != null){
			if(boardInfoVO.getFile2().getSize() > 0){
				String fileName2 = fileUpload.nameCheck(fileUpload.getFileName(boardInfoVO.getFile2().getOriginalFilename()));
				if ( fileUpload.fileUpload(null, boardInfoVO.getFile2(), fileName2, true) ) {
					// 실제 저장된 경로명
					boardInfoVO.setUrl_files2(fileName2);
					// 실제 저장된 파일명
					//boardInfoVO.setNm_file2(fileName2.substring(fileName2.lastIndexOf('/')+1, fileName2.length()));
				}
			}
		}
		if(boardInfoVO.getImg_files1() != null && boardInfoVO.getImg_files1().length > 0){
			boardInfoVO.setImg_files(boardInfoVO.getImg_files1());
			boardInfoVO.setNm_img_files(boardInfoVO.getNm_img_files1());
			boardInfoVO.setFile_type("01");
			if(0 == boardMapper.dupliBoardFileInfo(boardInfoVO)){
				int resultData = boardMapper.createBoardFileInfo(boardInfoVO);
				if(1 != resultData){
					return new ReturnClass(Constant.FAILED, "파일1 실패하였습니다.");
				}
			}else{
				int resultData = boardMapper.modifyBoardFileInfo(boardInfoVO);
				if(1 != resultData){
					return new ReturnClass(Constant.FAILED, "파일1 실패하였습니다.");
				}
			}
        }
        if(boardInfoVO.getImg_files2() != null && boardInfoVO.getImg_files2().length >0){
        	boardInfoVO.setImg_files(boardInfoVO.getImg_files2());
			boardInfoVO.setNm_img_files(boardInfoVO.getNm_img_files2());
			boardInfoVO.setFile_type("02");
			if(0 == boardMapper.dupliBoardFileInfo(boardInfoVO)){
				int resultData = boardMapper.createBoardFileInfo(boardInfoVO);
				if(1 != resultData){
					return new ReturnClass(Constant.FAILED, "파일2 실패하였습니다.");
				}
			}else{
				int resultData = boardMapper.modifyBoardFileInfo(boardInfoVO);
				if(1 != resultData){
					return new ReturnClass(Constant.FAILED, "파일2 실패하였습니다.");
				}
			}
        }
		int result = boardMapper.modifyBoardInfo(boardInfoVO);
		if(1 != result) {
			return new ReturnClass(Constant.FAILED, "수정 실패하였습니다. 수정건수: "+result);
		}
		return new ReturnClass(Constant.SUCCESS, "수정 성공하였습니다.",(Object) boardInfoVO.getId_board());
	}

	@Override
	public BoardInfoVO getBoardInfo(BoardInfo boardInfo) {
		return boardMapper.getBoardInfo(boardInfo);
	}

	@Override
	public ReturnClass delBoardInfo(BoardInfo boardInfo) {

		if(1 != boardMapper.delBoardInfo(boardInfo)){
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.",(Object) boardInfo.getId_board());
	}

	@Override
	public List<BoardComment> listBoardComment(String seq_board) {
		return boardMapper.listBoardComment(seq_board);
	}

	@Override
	public ReturnClass createBoardComment(BoardComment boardComment) {

		if(1 != boardMapper.createBoardComment(boardComment)){
			return new ReturnClass(Constant.FAILED, "등록 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "등록 성공하였습니다.",(Object) boardComment.getSeq_board());
	}

	@Override
	public BoardComment getBoardComment(String seq) {
		return boardMapper.getBoardComment(seq);
	}

	@Override
	public ReturnClass modifyBoardComment(BoardComment boardComment) {
		if(1 != boardMapper.modifyBoardComment(boardComment)){
			return new ReturnClass(Constant.FAILED, "수정 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "등록 성공하였습니다.",(Object) boardComment.getSeq_board());
	}

	@Override
	public ReturnClass delBoardComment(String seq) {
		if(1 != boardMapper.delBoardComment(seq)){
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.");
	}

	@Override
	public ReturnClass updateBoardInfoHit(String seq) {

		if(1 != boardMapper.updateBoardInfoHit(seq)){
			return new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다.");
	}

	@Override
	public List<BoardInfo> listPopupBoard(BoardForm boardForm) {
		return boardMapper.listPopupBoard(boardForm);
	}

	@Override
	public List<BoardInfoVO> listBoardInfoSummary(BoardForm boardForm) {
		return boardMapper.listBoardInfoSummary(boardForm);
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
	public List<BoardInfoVO> getBoardFileList(BoardInfo boardInfo){
		// TODO Auto-generated method stub
		return boardMapper.getBoardFileList(boardInfo);
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
