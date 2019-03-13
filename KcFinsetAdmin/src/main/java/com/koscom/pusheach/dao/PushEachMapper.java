package com.koscom.pusheach.dao;

import java.util.List;

import com.koscom.pusheach.model.PushEachForm;
import com.koscom.pusheach.model.PushEachVO;

public interface PushEachMapper {
	/**
	 * 푸시 AD01 YN_PUSH 업데이트
	 * @param pushEachVO
	 * @return
	 */
	int modifyYnPushAD01(PushEachVO pushEachVO);
	
	/**
	 * 푸시 AD01 리스트
	 * @param eventForm
	 * @return
	 */
	List<PushEachVO> listPushAD01Info(PushEachForm pushEachForm);
	/**
	 * 푸시 리스트
	 * @param eventForm
	 * @return
	 */
	List<PushEachVO> listPushEachInfo(PushEachForm pushEachForm);
	
	/**
	 * 푸시 AD01 기록
	 * @param pushEachVO
	 * @return
	 */
	void createPushAD01Info(PushEachVO pushEachVO);
	
	/**
	 * 푸시 기록
	 * @param pushEachVO
	 * @return
	 */
	void createPushEachInfo(PushEachVO pushEachVO);
	
	/**
	 * 푸시 목록 총 카운트
	 * @param pushEachVO
	 * @return
	 */
	int listPushEachInfoCount(PushEachForm pushEachForm);
	
	/**
	 * 푸시 상세
	 * @param pushEachVO
	 * @return
	 */
	PushEachVO viewPushEachInfo(PushEachVO pushEachVO);
	
	/**
	 * 프론트 마이페이지 알림푸시 리스트
	 * @param eventForm
	 * @return
	 */
	List<PushEachVO> listPushNotification(PushEachForm pushEachForm);

	/**
	 * 프론트 마이페이지 알림푸시 리스트 카운트
	 * @param eventForm
	 * @return
	 */
	int listPushNotificationCount(PushEachForm pushEachForm);
}
