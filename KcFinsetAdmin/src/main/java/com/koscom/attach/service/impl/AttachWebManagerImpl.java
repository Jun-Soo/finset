package com.koscom.attach.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import java.io.*;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.util.FinsetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.attach.model.AttachVO;
import com.koscom.attach.service.AttachManager;
import com.koscom.attach.service.AttachWebManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;

@Service("attachWebManager")
public class AttachWebManagerImpl implements AttachWebManager {

//	private static final Logger logger = LoggerFactory.getLogger(AttachWebManagerImpl.class);
	
	@Autowired
	private AttachManager attachManager;

	@Autowired
	private FincorpManager fincorpManager;

	/**
	 * 이미지 파일 다운로드
	 */
	@Override
	public byte[] getBytesAttachFileC(String filename) throws Exception {
        FincorpVO vo = new FincorpVO();
        vo.setPath_file1(filename);
		File file = null;
		byte readByte[] = null;
		if( !StringUtil.isEmpty(filename) ){
			readByte = fincorpManager.getImgBi(filename);
		}else{
			return null;
		}

		return readByte;
	}

	@Override
	public List<AttachVO> listAttach(String no_apply, String type) {
		return attachManager.listAttach(no_apply, type);
	}

	@Override
	public ReturnClass appendAttach(AttachVO attachVO)  throws FinsetException {
		
		ReturnClass returnClass = new ReturnClass();
		
		if(StringUtil.isNotEmpty(attachVO.getNo_apply()) && StringUtil.isNotEmpty(attachVO.getUrl_attach())) {
			returnClass = attachManager.downloadUrlFile(attachVO);
		}
		
		if( Constant.SUCCESS.equals(returnClass.getCd_result()) ) {
			returnClass = new ReturnClass(Constant.SUCCESS);
		} else {
			returnClass = new ReturnClass(Constant.FAILED);
		}

		return returnClass;
	}

	@Override
	public ReturnClass delAttachInfo(AttachVO attachVO) {
		return attachManager.delAttachInfo(attachVO);
	}
	
	@Override
	public ReturnClass insertAttach(AttachVO attachVO) {
		return attachManager.insertAttach(attachVO);
	}

	@Override
	public ReturnClass createAttach(AttachVO attachVO) throws IOException, FinsetException  {
		// TODO Auto-generated method stub
		return attachManager.createAttach(attachVO);
	}

	@Override
	public ReturnClass delAttachAll(AttachVO attachVO) {
		// TODO Auto-generated method stub
		return attachManager.delAttachAll(attachVO);
	}

	@Override
	public ReturnClass downloadUrlFile(AttachVO attachVO) throws FinsetException{
		// TODO Auto-generated method stub
		return attachManager.downloadUrlFile(attachVO);
	}

}
