package com.koscom.contents.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.contents.model.ContentsForm;
import com.koscom.contents.model.ContentsVO;
import com.koscom.contents.service.ContentsManager;
import com.koscom.contents.service.ContentsWebManager;
import com.koscom.util.ReturnClass;

@Service("contentsWebManager")
public class ContentsWebManagerImpl implements ContentsWebManager{

	@Autowired
	private ContentsManager contentsManager;

	//뉴스관리
	@Override
	public List<ContentsVO> listNewsManagePg(ContentsForm contentsForm) {
		return contentsManager.listNewsManagePg(contentsForm);
	}

	@Override
	public int listNewsManageCount(ContentsForm contentsForm) {
		return contentsManager.listNewsManageCount(contentsForm);
	}

	@Override
	public ContentsVO getNewsManageInfo(ContentsVO contentsVO) {
		return contentsManager.getNewsManageInfo(contentsVO);
	}

	@Override
	public Map<String, Object> getApiNewsImgInfo(ContentsVO contentsVO){
		
		return contentsManager.getApiNewsImgInfo(contentsVO);
	}

	@Override
	public ReturnClass modifyNewsManage(ContentsVO contentsVO) {
		return contentsManager.modifyNewsManage(contentsVO);
	}

	@Override
	public ReturnClass delNewsManage(ContentsVO contentsVO) {
		return contentsManager.delNewsManage(contentsVO);
	}

	//소비분류관리
	//지출항목관리
	@Override
	public List<ContentsVO> listConsumeSpendMng(ContentsForm contentsForm) {
		
		return contentsManager.listConsumeSpendMng(contentsForm);
	}
	@Override
	public List<ContentsVO> listConsumeSchCdClass(ContentsForm contentsForm) {
		
		return contentsManager.listConsumeSchCdClass(contentsForm);
	}
	@Override
	public ContentsVO getConsumeSpendMng(ContentsVO contentsVO) {
		
		return contentsManager.getConsumeSpendMng(contentsVO);
	}

	@Override
	public ReturnClass procConsumeSpendMng(ContentsVO contentsVO) {
		
		return contentsManager.procConsumeSpendMng(contentsVO);
	}

	@Override
	public ReturnClass delConsumeSpendMng(ContentsVO contentsVO) {
		
		return contentsManager.delConsumeSpendMng(contentsVO);
	}

	//카드업종관리
	@Override
	public List<ContentsVO> listConsumeCardFcInfo() {
		
		return contentsManager.listConsumeCardFcInfo();
	}

	@Override
	public List<ContentsVO> listConsumeCardMng(ContentsForm contentsForm) {
		
		return contentsManager.listConsumeCardMng(contentsForm);
	}

	@Override
	public ContentsVO getConsumeCardMng(ContentsVO contentsVO) {
		
		return contentsManager.getConsumeCardMng(contentsVO);
	}

	@Override
	public ReturnClass procConsumeCardMng(ContentsVO contentsVO) {
		
		return contentsManager.procConsumeCardMng(contentsVO);
	}

	@Override
	public ReturnClass delConsumeCardMng(ContentsVO contentsVO) {
		
		return contentsManager.delConsumeCardMng(contentsVO);
	}
}
