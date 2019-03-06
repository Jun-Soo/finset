package com.koscom.contents.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.koscom.contents.dao.ContentsMapper;
import com.koscom.contents.model.ContentsForm;
import com.koscom.contents.model.ContentsVO;
import com.koscom.contents.service.ContentsManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;


@Service("contentsManager")
public class ContentsManagerImpl implements ContentsManager {

	private static final Logger logger = LoggerFactory.getLogger(ContentsManagerImpl.class);

	@Autowired
	private ContentsMapper contentsMapper;


	//뉴스관리
	@Override
	public List<ContentsVO> listNewsManagePg(ContentsForm contentsForm) {
		return contentsMapper.listNewsManagePg(contentsForm);
	}

	@Override
	public int listNewsManageCount(ContentsForm contentsForm) {
		return contentsMapper.listNewsManageCount(contentsForm);
	}

	@Override
	public ContentsVO getNewsManageInfo(ContentsVO contentsVO) {
		ContentsVO newsInfo = new ContentsVO();
		newsInfo = contentsMapper.getNewsManageInfo(contentsVO);

//		try {
//			Document doc = Jsoup.connect(newsInfo.getLink()).get();
//			Elements newsBody = doc.select("body");
//			logger.info("newsBody-----"+newsBody);
//		} catch (Exception e) {
//				logger.info("log", e.getMessage());
//				logger.info("link", newsInfo.getLink());
//		}

		return newsInfo;
	}

	@Override
	public Map<String, Object> getApiNewsImgInfo(ContentsVO contentsVO){
		// TODO Auto-generated method stub
		return contentsMapper.getApiNewsImgInfo(contentsVO);
	}

	@Override
	public ReturnClass modifyNewsManage(ContentsVO contentsVO) {

		//썸네일 파일 등록/수정
		if(contentsVO.getImg_files1() != null && contentsVO.getImg_files1().length > 0){
			contentsVO.setImg_files(contentsVO.getImg_files1());
			contentsVO.setNm_img_files(contentsVO.getNm_img_files1());
			contentsVO.setFile_type("01");
			String seq_thum_file = contentsVO.getSeq_thum_file();
			if(StringUtils.isEmpty(seq_thum_file)){
				int resultData = contentsMapper.createApiNewsFileInfo(contentsVO);
				if(1 != resultData){
					return new ReturnClass(Constant.FAILED, "썸네일이미지 첨부 실패하였습니다.");
				}
			}else{
				contentsVO.setSeq_file(Integer.parseInt(seq_thum_file));
				int resultData = contentsMapper.modifyApiNewsFileInfo(contentsVO);
				if(1 != resultData){
					return new ReturnClass(Constant.FAILED, "썸네일이미지 첨부 실패하였습니다.");
				}
			}
        }

		//본문 파일 등록/수정
		if(contentsVO.getImg_files2() != null && contentsVO.getImg_files2().length > 0){
			contentsVO.setImg_files(contentsVO.getImg_files2());
			contentsVO.setNm_img_files(contentsVO.getNm_img_files2());
			contentsVO.setFile_type("02");
			String seq_body_file = contentsVO.getSeq_body_file();
			if(StringUtils.isEmpty(seq_body_file)){
				int resultData = contentsMapper.createApiNewsFileInfo(contentsVO);
				if(1 != resultData){
					return new ReturnClass(Constant.FAILED, "본문이미지 첨부 실패하였습니다.");
				}
			}else{
				contentsVO.setSeq_file(Integer.parseInt(seq_body_file));
				int resultData = contentsMapper.modifyApiNewsFileInfo(contentsVO);
				if(1 != resultData){
					return new ReturnClass(Constant.FAILED, "본문이미지 첨부 실패하였습니다.");
				}
			}
		}

		//뉴스 등록
		if(1 != contentsMapper.modifyNewsManage(contentsVO)){
			return new ReturnClass(Constant.FAILED, "저장 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "저장 성공하였습니다.",(Object) contentsVO.getSeq_news());
	}

	@Override
	public ReturnClass delNewsManage(ContentsVO contentsVO) {

		//썸네일, 본문이미지 삭제
		contentsMapper.delApiNewsFileInfo(contentsVO);

		if(1 != contentsMapper.delNewsManage(contentsVO)){
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.",(Object) contentsVO.getSeq_news());
	}


	//소비분류관리
	//지출항목관리
	@Override
	public List<ContentsVO> listConsumeSpendMng(ContentsForm contentsForm) {
		return contentsMapper.listConsumeSpendMng(contentsForm);
	}
	public List<ContentsVO> listConsumeSchCdClass(ContentsForm contentsForm) {
		return contentsMapper.listConsumeSchCdClass(contentsForm);
	}
	@Override
	public ContentsVO getConsumeSpendMng(ContentsVO contentsVO) {

		ContentsVO emptyContentsVO = new ContentsVO();
		if(StringUtil.isEmpty(contentsVO.getCd_consume_class()))
			return emptyContentsVO;

		return contentsMapper.getConsumeSpendMng(contentsVO);
	}

	@Override
	public ReturnClass procConsumeSpendMng(ContentsVO contentsVO) {

		if(StringUtil.isEmpty(contentsVO.getCd_class())) {
			return new ReturnClass(Constant.FAILED, "분류코드가 입력되지 않았습니다.");
		}

		if(StringUtil.isEmpty(contentsVO.getCd_type())) {
			return new ReturnClass(Constant.FAILED, "항목코드가 입력되지 않았습니다.");
		}

		//소비분류코드
		contentsVO.setCd_consume_class(contentsVO.getCd_class()+contentsVO.getCd_type());

		// insert 구분
		if("C".equals(contentsVO.getCd_proc_type()) && contentsMapper.getConsumeSpendMng(contentsVO) != null) {
				return new ReturnClass(Constant.FAILED, "이미 존재하는 분류코드+항목코드입니다. 수정 후 입력하여주세요.");
		}

		if (1 != contentsMapper.procConsumeSpendMng(contentsVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public ReturnClass delConsumeSpendMng(ContentsVO contentsVO) {

		if(StringUtil.isEmpty(contentsVO.getCd_class())) {
			return new ReturnClass(Constant.FAILED, "분류코드가 입력되지 않았습니다.");
		}

		if(StringUtil.isEmpty(contentsVO.getCd_type())) {
			return new ReturnClass(Constant.FAILED, "항목코드가 입력되지 않았습니다.");
		}

		//소비분류코드
		contentsVO.setCd_consume_class(contentsVO.getCd_class()+contentsVO.getCd_type());

		if (1 != contentsMapper.delConsumeSpendMng(contentsVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		//BUSINESS_TYPE_INFO의 CD_CONSUME_CLASS
		contentsMapper.updateInitConsumeCardCd(contentsVO);

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	//카드업종관리
	@Override
	public List<ContentsVO> listConsumeCardFcInfo() {
		return contentsMapper.listConsumeCardFcInfo();
	}

	@Override
	public List<ContentsVO> listConsumeCardMng(ContentsForm contentsForm) {
		return contentsMapper.listConsumeCardMng(contentsForm);
	}

	@Override
	public ContentsVO getConsumeCardMng(ContentsVO contentsVO) {

		ContentsVO emptyContentsVO = new ContentsVO();
		if(StringUtil.isEmpty(contentsVO.getCd_business()))
			return emptyContentsVO;

		return contentsMapper.getConsumeCardMng(contentsVO);
	}

	@Override
	public ReturnClass procConsumeCardMng(ContentsVO contentsVO) {

		if(StringUtil.isEmpty(contentsVO.getCd_fc())) {
			return new ReturnClass(Constant.FAILED, "카드사가 선택되지 않았습니다.");
		}

		if(StringUtil.isEmpty(contentsVO.getCd_business())) {
			return new ReturnClass(Constant.FAILED, "업종코드가 입력되지 않았습니다.");
		}

		// insert 구분
		if("C".equals(contentsVO.getCd_proc_type()) && contentsMapper.getConsumeCardMng(contentsVO) != null) {
			return new ReturnClass(Constant.FAILED, "이미 존재하는 업종코드입니다. 수정 후 입력하여주세요.");
		}

		if (1 != contentsMapper.procConsumeCardMng(contentsVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public ReturnClass delConsumeCardMng(ContentsVO contentsVO) {

		if(StringUtil.isEmpty(contentsVO.getCd_fc())) {
			return new ReturnClass(Constant.FAILED, "카드사가 선택되지 않았습니다.");
		}

		if(StringUtil.isEmpty(contentsVO.getCd_business())) {
			return new ReturnClass(Constant.FAILED, "업종코드가 입력되지 않았습니다.");
		}

		if (1 != contentsMapper.delConsumeCardMng(contentsVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
}
