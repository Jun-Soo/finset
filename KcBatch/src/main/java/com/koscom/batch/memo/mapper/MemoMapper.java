/*
 * xml을 호출 할 Mapper 인터페이스
 * 작성자: 김휘경
 * 작성일: 20180618~20180619
 */

package com.koscom.batch.memo.mapper;

import java.util.List;

import com.koscom.batch.memo.domain.MemoPushInfo;

public interface MemoMapper {
	List<MemoPushInfo> getMemoPushInfo(); //메모 푸시를 보내야 되는 정보를 list로 가져옴
	void createPushEachInfo(MemoPushInfo memoPushInfo); //히스토리성 푸시 정보를 저장하는 함수
}
