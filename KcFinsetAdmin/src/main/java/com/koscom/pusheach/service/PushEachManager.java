package com.koscom.pusheach.service;

import java.util.List;

import com.koscom.pusheach.model.PushEachForm;
import com.koscom.pusheach.model.PushEachVO;
import com.koscom.util.ReturnClass;

public interface PushEachManager {
	/**
	 * 푸시 AD01 YN_PUSH 업데이트
	 * @param pushEachVO
	 * @return
	 */
	public int modifyYnPushAD01(PushEachVO pushEachVO);
	
	/**
	 * 푸시 AD01 리스트
	 * @param eventEachForm
	 * @return
	 */
	public List<PushEachVO> listPushAD01Info(PushEachForm eventEachForm);
	
	/**
	 * 푸시 AD01 기록
	 * @param pushEachVO
	 * @return
	 */
	public ReturnClass createPushAD01Info(PushEachVO pushEachVO);
	/**
	 * 푸시 리스트
	 * @param eventEachForm
	 * @return
	 */
	public List<PushEachVO> listPushEachInfo(PushEachForm eventEachForm);
	
	/**
	 * 푸시 기록
	 * @param pushEachVO
	 * @return
	 */
	public ReturnClass createPushEachInfo(PushEachVO pushEachVO);
	
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
	public PushEachVO viewPushEachInfo(PushEachVO pushEachVO);
	
	/**
	 * 프론트 마이페이지 알림푸시 리스트
	 * @param eventForm
	 * @return
	 */
	public List<PushEachVO> listPushNotification(PushEachForm pushEachForm);

	/**
	 * 프론트 마이페이지 알림푸시 리스트 카운트
	 * @param eventForm
	 * @return
	 */
	int listPushNotificationCount(PushEachForm pushEachForm);
	
	/**
	 * 신용변동내역 전송
	 * @return
	 */
	void pushCreditInfo(PushEachVO pushEachVO);

}
