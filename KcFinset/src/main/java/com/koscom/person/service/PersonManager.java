package com.koscom.person.service;

import java.util.List;

import com.koscom.domain.PersonLoginHistInfo;
import com.koscom.domain.PersonShareMessageInfo;
import com.koscom.person.model.PersonActiveHistVO;
import com.koscom.person.model.PersonShareInfoVO;
import com.koscom.person.model.PersonSmsListVO;
import com.koscom.person.model.PersonVO;
import com.koscom.util.ReturnClass;

public interface PersonManager {

	/**
	 * 휴대폰번호로 고객정보 조회
	 * @param 	hp
	 * @return 	PersonVO
	 */
	PersonVO getPersonInfoHp(String hp);

	/**
	 * 고객정보 조회
	 * @param 	no_person
	 * @return	PersonVO
	 */
	PersonVO getPersonInfo(String noPerson);

	/**
	 * 비밀번호/지문 틀린 횟수 업데이트
	 * @param	PersonVO personVO
	 * @return	ReturnClass
	 */
	ReturnClass modifyPwdFailCnt(PersonVO personVO);
	
	/**
	 * 회원 동의여부 조회
	 * @param	String no_person
	 * @return	String
	 */
	String getYnAgreeUsingInfo(String no_person);
	
	/**
	 * 비밀번호 체크
	 * @param String pwd
	 * @return String
	 */
	String getPwdDB(String pwd);
	/**
	 * 비밀번호 체크
	 * @param PersonVO
	 * @return int
	 */
	int checkPersonPass(PersonVO personVO);
	
	/**
	 * 토큰 업데이트
	 * @param 	PersonVO personVO
	 * @return	ReturnClass
	 */
	ReturnClass modifyFcmToken(PersonVO personVO);
	
	/**
	 * 비밀번호 수정
	 * @param 	personVO
	 * @return	ReturnClass
	 */
	ReturnClass modifyPassPerson(PersonVO personVO);
	
	/**
	 * 최근접속 업데이트
	 * @param no_person
	 */
	void modifyLastLogin(String no_person);
	
	/**
	 * 모바일 접속 이력
	 * @param PersonLoginHistInfo personLoginHist
	 */
	void insertPersonLoginHist(PersonLoginHistInfo personLoginHist);
	
	/**
	 * 페이지 이동 기록
	 * @param 	PersonActiveHistVO personActiveHistVO
	 * @return	ReturnClass
	 */
	ReturnClass insertActiveHist(PersonActiveHistVO personActiveHistVO);
	
	/**
	 * 지문 업데이트
	 * @param 	PersonVO personVO
	 * @return	ReturnClass
	 */
	ReturnClass modifyFingerPrint(PersonVO personVO);
	
	/**
	 * 이메일 업데이트
	 * @param 	PersonVO personVO
	 * @return	ReturnClass	
	 */
	ReturnClass modifyPersonEmail(PersonVO personVO);
	
	/**
	 * 로그아웃 업데이트
	 * @param 	PersonVO personVO
	 * @return	ReturnClass
	 */
	ReturnClass modifyPersonLogout(PersonVO personVO);
	
	/**
	 * 푸시알림설정
	 * @param PersonVO
	 * @return ReturnClass
	 */
	ReturnClass modifyPushNoti(PersonVO personVO);
	
	/**
	 * 로그아웃,사용여부 수정
	 * @param PersonVO
	 * @return ReturnClass
	 */
	ReturnClass modifyYnUseAndLogout(PersonVO personVO);
	
	/**
	 * 해당 고객의 마지막 SMS 수신함 조회일 반환
	 * @param no_person
	 * @return String
	 */
	String getLastPersonSmsDt(String no_person);
	
	/**
	 * SMS 수신함 조회 데이터 입력
	 * @param PersonSmsListVO
	 * @return
	 */
	ReturnClass createPersonSmsList(List<PersonSmsListVO> list);
	
	/**
	 * 공유관리 메세지merge
	 * @param PersonShareMessageInfo
	 * @return ReturnClass
	 */
	ReturnClass mergePersonShareInfoMessage(PersonShareMessageInfo personShareMessageInfo);
	
	/**
	 * 공유관리 업데이트요청 List
	 * @param PersonShareInfoVO
	 * @return List<PersonShareInfoVO>
	 */
	List<PersonShareInfoVO> listPersonShareInfoReqUpdate(PersonShareInfoVO personShareInfoVO);
}
