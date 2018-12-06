package com.koscom.board.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

import com.koscom.domain.BoardInfo;

public class BoardInfoVO extends BoardInfo implements Serializable{
	
	private static final long serialVersionUID = 7119079691491572046L;
	
	private MultipartFile file1;	//파일첨부1
	private MultipartFile file2;	//파일첨부2
	
	/* srchou */
	private MultipartFile imgfiles1;	//파일첨부
	private MultipartFile imgfiles2;	//파일첨부
	private String fileName;
	private int fileSize;
	private byte[] fileArray;
	private byte[] img_files;
	private byte[] img_files1;
	private byte[] img_files2;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public MultipartFile getFile1() {
		return file1;
	}

	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}

	public MultipartFile getFile2() {
		return file2;
	}

	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}

	public MultipartFile getImgfiles1() {
		return imgfiles1;
	}

	public void setImgfiles1(MultipartFile imgfiles1) {
		this.imgfiles1 = imgfiles1;
	}

	public MultipartFile getImgfiles2() {
		return imgfiles2;
	}

	public void setImgfiles2(MultipartFile imgfiles2) {
		this.imgfiles2 = imgfiles2;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public byte[] getFileArray() {
		return fileArray;
	}

	public void setFileArray(byte[] fileArray) {
		this.fileArray = fileArray;
	}

	public byte[] getImg_files() {
		return img_files;
	}

	public void setImg_files(byte[] img_files) {
		this.img_files = img_files;
	}

	public byte[] getImg_files1() {
		return img_files1;
	}

	public void setImg_files1(byte[] img_files1) {
		this.img_files1 = img_files1;
	}

	public byte[] getImg_files2() {
		return img_files2;
	}

	public void setImg_files2(byte[] img_files2) {
		this.img_files2 = img_files2;
	}

}


