package com.koscom.attach.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.koscom.domain.AttachInfo;

public class AttachVO extends AttachInfo implements Serializable{
	
	private static final long serialVersionUID = 772119540306256461L;

	private MultipartFile file;
	
	/**
	 * 첨부파일 구분
	 * 01 공통 
	 * 11 개별1 
	 * 12 개별2 
       99 기타
	 */
	public static String CD_ATTACH_01 = "01";
	public static String CD_ATTACH_11 = "11";
	public static String CD_ATTACH_12 = "12";
	public static String CD_ATTACH_99 = "99";
	

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
