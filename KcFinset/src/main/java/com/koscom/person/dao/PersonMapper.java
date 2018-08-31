package com.koscom.person.dao;

import java.util.List;

import com.koscom.domain.PersonLoginHistInfo;
import com.koscom.domain.PersonShareMessageInfo;
import com.koscom.person.model.PersonActiveHistVO;
import com.koscom.person.model.PersonShareInfoVO;
import com.koscom.person.model.PersonSmsListVO;
import com.koscom.person.model.PersonVO;

/**
 * Person Dao Interface
 *
 * @author bwko
 *
 */
public interface PersonMapper {

	/**
	 * 휴대폰번호로 고객정보 조회
	 *
	 * @param 	in_number
	 * @return	PersonVO
	 */
	PersonVO getPersonInfoHp(String hp);
	
	/**
	 * 고객정보 조회
	 *
	 * @param 	no_person
	 * @return	PersonVO
	 */
	PersonVO getPersonInfo(String no_person);
	
	/**
	 * 비밀번호/지문 틀린 횟수 업데이트
	 * @param	PersonVO personVO
	 * @return	int
	 */
	int modifyPwdFailCnt(PersonVO personVO);
	
	/**
	 * 회원 동의여부 조회
	 * @param	String no_person
	 * @return	String
	 */
	String getYnAgreeUsingInfo(String no_person);
	
	/**
	 * 비밀번호 체크
	 * @param personVO
	 * @return int
	 */
	String getPwdDB(String pwd);
	/**
	 * 비밀번호 체크
	 * @param personVO
	 * @return int
	 */
	int checkPersonPass(PersonVO personVO);
	
	/**
	 * 토큰 업데이트
	 * @param 	personVO
	 * @return	int
	 */
	int modifyFcmToken(PersonVO personVO);
	
	/**
	 * 비밀번호 수정
	 * @param 	personVO
	 * @return	int
	 */
	int modifyPassPerson(PersonVO personVO);
	
	/**
	 * 최근접속 업데이트
	 * @param no_person
	 */
	void modifyLastLogin(String no_person);
	
	/**
	 * 모바일 접속 이력
	 * @param PersonLoginHistInfo
	 */
	void insertPersonLoginHist(PersonLoginHistInfo personLoginHist);
	
	/**
	 * 페이지 이동 기록
	 * @param 	PersonLoginHistInfo
	 * @return	int
	 */
	int insertActiveHist(PersonActiveHistVO personActiveHistVO);
	
	/**
	 * 지문 업데이트
	 * @param 	personVO
	 * @return	int
	 */
	int modifyFingerPrint(PersonVO personVO);
	
	/**
	 * 이메일 업데이트
	 * @param 	personVO
	 * @return	int
	 */
	int modifyPersonEmail(PersonVO personVO);
	
	/**
	 * 로그아웃 업데이트
	 * @param 	personVO
	 * @return	int
	 */
	int modifyPersonLogout(PersonVO personVO);
	
	/**
	 * 푸쉬알림설정
	 * @param 	personVO
	 * @return	int
	 */
	int modifyPushNoti(PersonVO personVO);
	int insertCdPush(PersonVO personVO);
	int modifyCdPush(PersonVO personVO);
	int insertCdPushHist(PersonVO personVO);
	
	/**
	 * 로그아웃, 사용여부 업데이트
	 * @param 	personVO
	 * @return	int
	 */
	int modifyYnUseAndLogout(PersonVO personVO);
	
	/**
	 * 해당 고객의 마지막 SMS 수신함 정보 조회
	 * @param no_person
	 * @return String
	 */
	String getLastPersonSmsDt(String no_person);
	
	/**
	 * SMS 수신함 정보 저장
	 * @param personSmsListVO
	 * @return
	 */
	int createPersonSmsList(PersonSmsListVO personSmsListVO);
	
	/**
	 * 공유관리 메세지merge
	 * @param PersonShareMessageInfo
	 * @return int
	 */
	int mergePersonShareInfoMessage(PersonShareMessageInfo personShareMessageInfo);
	
	/**
	 * 공유관리 업데이트요청 List
	 * @param PersonShareInfoVO
	 * @return List<PersonShareInfoVO>
	 */
	List<PersonShareInfoVO> listPersonShareInfoReqUpdate(PersonShareInfoVO personShareInfoVO);
}
