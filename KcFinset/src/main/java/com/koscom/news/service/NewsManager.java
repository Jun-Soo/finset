package com.koscom.news.service;

import java.util.List;
import java.util.Map;

import com.koscom.news.model.NewsForm;
import com.koscom.news.model.NewsVO;

public interface NewsManager {

	/**
	 * 뉴스 메인 list
	 * @param NewsForm
	 * @return List<NewsVO>
	 */
	public List<NewsVO> listApiNews(NewsForm newsForm);

	/**
	 * 뉴스 메인 list count
	 * @param NewsForm
	 * @return int
	 */
	public int listApiNewsCount(NewsForm newsForm);

	/**
	 * 뉴스 이미지 불러오기
	 * @param NewsVO
	 * @return
	 */
	public Map<String, Object> getApiNewsImgInfo(NewsVO newsVO);

	/**
	 * 뉴스 상세
	 * @param NewsVO
	 * @return NewsVO
	 */
	public NewsVO getApiNewsInfo(NewsVO newsVO);

}
