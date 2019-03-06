package com.koscom.attach.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.koscom.board.dao.BoardMapper;
import com.koscom.domain.BoardInfo;
import com.koscom.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.attach.dao.AttachMapper;
import com.koscom.attach.model.AttachVO;
import com.koscom.attach.service.AttachManager;

@Service("attachManager")
public class AttachManagerImpl implements AttachManager {

	private static final Logger logger = LoggerFactory.getLogger(AttachManagerImpl.class);
	
	@Autowired
	private AttachMapper attachMapper;
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public ReturnClass createAttach(AttachVO attachVO) throws IOException, FinsetException {
		
		// 파일구분이 없으면 기타로 설정
		if (StringUtil.isEmpty(attachVO.getCd_attach()))
			attachVO.setCd_attach("99");
		
		// 파일 업로드
		FileUpload fileUpload = new FileUpload();

		// 실제 저장될 파일명세팅
		String fileName = fileUpload.nameCheck(fileUpload.getFileName(attachVO.getFile().getOriginalFilename()));

		if ( fileUpload.fileUpload(null, attachVO.getFile(), fileName, true) ) {
			// 실제 저장된 경로명
			attachVO.setPath_save_file(fileName);
			// 실제 저장된 파일명
			attachVO.setNm_save_file(fileName.substring(fileName.lastIndexOf('/')+1, fileName.length()));

			attachMapper.createAttach(attachVO);
		}

		return new ReturnClass(Constant.SUCCESS, "정상처리 하였습니다.");
	}

	@Override
	public List<AttachVO> listAttach(String no_apply, String type) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("no_apply", no_apply);
		map.put("type", type);
		return attachMapper.listAttach(map);
	}

	@Override
	public ReturnClass delAttachInfo(AttachVO attachVO) {

		if (StringUtil.isEmpty(attachVO.getNo_apply())) {
			return new ReturnClass(Constant.SUCCESS, "접수번호가 존재하지 않습니다.");
		}
		
		if (StringUtil.isEmpty(attachVO.getSeq_attach())) {
			return new ReturnClass(Constant.SUCCESS, "파일이 선택되지 않았습니다.");
		}
		
		if (1 != attachMapper.delAttachInfo(attachVO)) {
			return new ReturnClass(Constant.SUCCESS, "파일삭제를 실패 하였습니다.");
		}
		
		return new ReturnClass(Constant.SUCCESS, "정상처리 하였습니다.");
	}

	@Override
	public ReturnClass insertAttach(AttachVO attachVO) {
		attachMapper.createAttach(attachVO);
		return new ReturnClass(Constant.SUCCESS, "정상처리 하였습니다.");
	}


	@Override
	public ReturnClass delAttachAll(AttachVO attachVO) {
		
		if (1 != attachMapper.delAttachAll(attachVO)) {
			return new ReturnClass(Constant.SUCCESS, "파일삭제를 실패 하였습니다.");
		}
		
		return new ReturnClass(Constant.SUCCESS, "정상처리 하였습니다.");
	}

	@Override
	public ReturnClass downloadUrlFile(AttachVO attachVO) throws FinsetException {
		FileDownload fd = new FileDownload();
		String resultPath = "";
		
		resultPath = fd.getUrlFileDownLoad(attachVO.getUrl_attach());

		if(StringUtil.isEmpty(attachVO.getCd_attach())){
			if(attachVO.getUrl_attach().indexOf("wav") > -1){
				attachVO.setCd_attach("06"); // 06.wav
			}else{
				attachVO.setCd_attach("99"); // 99.기타
			}
		}
		attachVO.setNm_ori_file(fd.getDownFileName(attachVO.getUrl_attach(),""));

		if ( !StringUtil.isEmpty(resultPath) ) {

			attachVO.setPath_save_file(resultPath);
			attachVO.setNm_save_file(fd.getDownFileName(resultPath,""));
		}


		
		attachVO.setUrl_attach("");
		//logger.debug("[첨부파일 정보]  insert attach" + attachVO);
		attachMapper.createAttach(attachVO);
		
		return new ReturnClass(Constant.SUCCESS);

	}

    /**
     * 화면에 보여지는 파일명으로 실제 파일명을 가져와서
     * 파일을 리턴한다.
     * @param fileName
     * @return
     */
    @Override
    public File getOrgFile(String fileName) {
	    BoardInfo bi = new BoardInfo();
	    bi.setUrl_files1(fileName);
	    bi.setUrl_files2(fileName);
        String orgFileName = boardMapper.getOrgFileName(bi);
        File file = new File(orgFileName);
        return file;
    }
}
