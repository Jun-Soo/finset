package com.koscom.attach.service;

import java.io.IOException;
import java.util.List;

import com.koscom.attach.model.AttachVO;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;

public interface AttachWebManager {

	/**
	 * 첨부파일 업로드
	 * @param attachVO
	 * @return
	 */
	ReturnClass createAttach(AttachVO attachVO) throws IOException, FinsetException ;
	
	/**
	 * 첨부파일 리스트 조회
	 * @param no_apply
	 * @param type
	 * @return
	 * @throws Exception
	 */
	List<AttachVO> listAttach(String no_apply, String type);
	
	/**
	 * 첨부파일 삭제
	 * @param attachVO
	 * @return
	 */
	ReturnClass delAttachInfo(AttachVO attachVO);

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
	 * @param attachVO
	 * @return
	 * @throws Exception
	 */
	ReturnClass downloadUrlFile(AttachVO attachVO) throws FinsetException;

	/**
	 * Method Desc : 파일이름으로 파일 byte 정보를 가져온다.
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	byte[] getBytesAttachFileC(String filename) throws Exception;
	/**
	 * 첨부파일 추가(대리점)
	 * @param attachVO
	 * @return
	 * @throws Exception
	 */
	ReturnClass appendAttach(AttachVO attachVO) throws FinsetException;
}
