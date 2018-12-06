package com.koscom.news.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.news.dao.NewsMapper;
import com.koscom.news.model.NewsForm;
import com.koscom.news.model.NewsVO;
import com.koscom.news.service.NewsManager;

@Service("newsManager")
public class NewsManagerImpl implements NewsManager {

	@Autowired
	private NewsMapper newsMapper;

	@Override
	public List<NewsVO> listApiNews(NewsForm newsForm) {
		return newsMapper.listApiNews(newsForm);
	}

	@Override
	public int listApiNewsCount(NewsForm newsForm) {
		return newsMapper.listApiNewsCount(newsForm);
	}

	@Override
	public Map<String, Object> getApiNewsImgInfo(NewsVO newsVO){
		return newsMapper.getApiNewsImgInfo(newsVO);
	}

	@Override
	public NewsVO getApiNewsInfo(NewsVO newsVO){
		
		//조회수 증가
		newsMapper.updateApiNewsHits(newsVO);

		return newsMapper.getApiNewsInfo(newsVO);
	}

}
