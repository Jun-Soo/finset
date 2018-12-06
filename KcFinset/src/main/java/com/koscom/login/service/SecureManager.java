package com.koscom.login.service;

public interface SecureManager {

	/**
	 * 인코딩된 비밀번호를 입력받아 복호화한 결과값 리턴
	 * @param password: 복호화할 대상
	 * @param objects: 복호화에 필요한 기타 값들
	 * @return
	 */
	public String getDecodedPassword(String password, Object... objects);
}
