package com.koscom.push.service;

import java.util.List;

import com.koscom.push.model.PushForm;
import com.koscom.push.model.PushVO;
import com.koscom.util.ReturnClass;

public interface PushManager {
	
	
	/**
	 * 푸시 리스트
	 * @param eventForm
	 * @return
	 */
	public List<PushVO> listPushInfo(PushForm eventForm);
	
	/**
	 * 푸시 기록
	 * @param pushVO
	 * @return
	 */
	public ReturnClass createPushInfo(PushVO pushVO);
	
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
	public PushVO viewPushInfo(PushVO pushVO);
	
	/**
	 * 발송고객 리스트 조회
	 * @param pushVO
	 * @return
	 */
	public List<PushVO> viewPushEachInfoPerson(PushVO pushVO);
	
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
	public ReturnClass createPushEachInfo(PushVO pushVO);

}
