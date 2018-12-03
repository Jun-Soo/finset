package com.koscom.batch.push.mapper;

import java.util.List;

import com.koscom.batch.push.domain.Ad01SegmentInfo;
import com.koscom.batch.push.domain.PushEachForm;
import com.koscom.batch.push.domain.PushEachVO;

public interface PushMapper {
	Ad01SegmentInfo getPushAD01Info(Ad01SegmentInfo ad01SegmentInfo) ;
	void insertPushHist(Ad01SegmentInfo ad01SegmentInfo) ;
	void updatePushHist(Ad01SegmentInfo ad01SegmentInfo) ;
	/**
	 * 푸시 AD01 기록
	 * @param pushEachVO
	 * @return
	 */
	void createPushAD01Info(PushEachVO pushEachVO);

	/**
	 * 푸시 AD01 리스트
	 * @param pushEachForm
	 * @return
	 */
	List<PushEachVO> listPushAD01Info(PushEachForm pushEachForm);

	/**
	 * 푸시 AD01 YN_PUSH 업데이트
	 * @param pushEachVO
	 * @return
	 */
	int modifyYnPushAD01(PushEachVO pushEachVO);

	/**
	 * 푸시 PUSH_EACH_INFO INSERT
	 * @param pushEachVO
	 * @return
	 */
	void createPushEachInfo(PushEachVO pushEachVO);

	/**
	 * 부채 푸시 대상자 리스트
	 * @param pushEachForm
	 * @return
	 */
	List<PushEachVO> listPushDebtPerson(PushEachForm pushEachForm);


}
