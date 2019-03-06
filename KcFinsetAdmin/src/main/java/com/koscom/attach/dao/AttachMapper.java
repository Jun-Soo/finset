package com.koscom.attach.dao;

import java.util.HashMap;
import java.util.List;

import com.koscom.attach.model.AttachVO;
import com.koscom.domain.AttachInfo;

public interface AttachMapper {

	/**
	 * 첨부파일 저장
	 * @param attachInfo
	 */
	void createAttach(AttachInfo attachInfo);

	/**
	 * 첨부파일 목록 조회
	 * @param map
	 * @return
	 */
	List<AttachVO> listAttach(HashMap<String, String> map);
	
	/**
	 * 첨부파일 삭제
	 * @param no_apply
	 * @param seq_attach
	 * @return
	 */
	int delAttachInfo(AttachInfo attachVO);
	
	/**
	 * 접수번호에 속한 첨부파일 전체삭제
	 * @param attachVO
	 */
	int delAttachAll(AttachInfo attachInfo);
	
}
