package com.koscom.attach.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.koscom.attach.model.AttachVO;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;

public interface AttachManager {

	/**
	 * 첨부파일 업로드
	 * @param attachVO
	 * @return
	 */
	ReturnClass createAttach(AttachVO attachVO) throws IOException, FinsetException ;
	
	/**
	 * 첨부파일 리스트 조회
	 * @param no_apply
	 * @param attachType
	 * @return
	 * @throws Exception
	 */
	public List<AttachVO> listAttach(String no_apply, String type);
	
	/**
	 * 첨부파일 삭제
	 * @param no_apply
	 * @param seq_attach
	 * @return
	 */
	public ReturnClass delAttachInfo(AttachVO attachVO);

	/**
	 * 첨부파일 데이터 생성
	 * @param attachVO
	 * @return
	 */
	ReturnClass insertAttach(AttachVO attachVO);
	
	/**
	 * 접수번호에 속한 첨부파일 전체삭제
	 * @param attachVO
	 */
	ReturnClass delAttachAll(AttachVO attachVO);
	
	/**
	 * 첨부파일 추가
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	File getOrgFile(String fileName);
	/**
	 * 첨부파일 추가
	 * @param attachVO
	 * @return
	 * @throws Exception
	 */
	ReturnClass downloadUrlFile(AttachVO attachVO) throws FinsetException;
}
