package com.koscom.attach.service;

public interface AttachManager {
	/**
	 * Method Desc : 파일이름으로 파일 byte 정보를 가져온다.
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	byte[] getBytesAttachFileC(String filename) throws Exception;
}
