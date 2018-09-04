package com.koscom.pusheach.dao;

import java.util.List;

import com.koscom.pusheach.model.PushEachForm;
import com.koscom.pusheach.model.PushEachVO;

public interface PushEachMapper {

	/**
	 * 마이페이지 알림푸시 리스트
	 * @param pushEachForm
	 * @return List<PushEachVO>
	 */
	List<PushEachVO> listPushNotification(PushEachForm pushEachForm);
	int listPushNotificationCount(PushEachForm pushEachForm);
}
