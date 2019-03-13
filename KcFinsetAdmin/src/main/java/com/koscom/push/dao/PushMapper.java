package com.koscom.push.dao;

import java.util.List;

import com.koscom.push.model.PushForm;
import com.koscom.push.model.PushVO;

public interface PushMapper {
	
	
	/**
	 * 푸시 리스트
	 * @param eventForm
	 * @return
	 */
	List<PushVO> listPushInfo(PushForm pushForm);
	
	/**
	 * 푸시 전체 발송
	 * @param pushVO
	 * @return
	 */
	void createPushInfo(PushVO pushVO);
	
	/**
	 * 푸시 목록 총 카운트
	 * @param pushVO
	 * @return
	 */
	int listPushInfoCount(PushForm pushForm);
	
	/**
	 * 푸시 상세
	 * @param pushVO
	 * @return
	 */
	PushVO viewPushInfo(PushVO pushVO);
	
	/**
	 * 발송고객 리스트 조회
	 * @param pushVO
	 * @return
	 */
	List<PushVO> viewPushEachInfoPerson(PushVO pushVO);
	
	/**
	 * 개별발송 seq 채번
	 * @param pushVO
	 * @return
	 */
	int getNewSeqPush(PushForm pushForm);
	
	/**
	 * 푸시 개별 발송
	 * @param pushVO
	 * @return
	 */
	void createPushEachInfo(PushVO pushVO);

}
