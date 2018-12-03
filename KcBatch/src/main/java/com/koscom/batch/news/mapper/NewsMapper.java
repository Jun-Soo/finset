package com.koscom.batch.news.mapper;

import com.koscom.batch.news.domain.NewsResultVO;
import com.koscom.batch.news.domain.NewsVO;

public interface NewsMapper {

	int getApiNewsSeq();

	/**
	 * API_NEWS 생성
	 * @param NewsVO
	 * @return int
	 */
	int createApiNews(NewsVO newsVO);

	/**
	 * API_NEWS_RESULT 중복체크(link값 이용)
	 * @param NewsResultVO
	 * @return int
	 */
	int duplApiNewsResultByLink(NewsResultVO newsResultVO);

	/**
	 * API_NEWS_RESULT 생성
	 * @param NewsResultVO
	 * @return int
	 */
	int createApiNewsResult(NewsResultVO newsResultVO);

	/**
	 * API_NEWS 에러 update
	 * @param NewsVO
	 * @return int
	 */
	int updateErrorApiNews(NewsVO newsVO);

}
