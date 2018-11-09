package com.koscom.person.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.koscom.domain.PersonLoginHistInfo;
import com.koscom.domain.PersonShareInfo;
import com.koscom.domain.PersonShareMessageInfo;
import com.koscom.person.model.PersonActiveHistVO;
import com.koscom.person.model.PersonCertificateInfoVO;
import com.koscom.person.model.PersonShareInfoForm;
import com.koscom.person.model.PersonSmsListVO;
import com.koscom.person.model.PersonVO;
import com.koscom.util.FinsetException;
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
	 * GNB_최근접속시간
	 * @param PersonLoginHistInfo personLoginHist
	 */
	String getPersonConnectTime(String no_person);

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

	//공유관리
	/**
	 * 공유관리 List
	 * @param PersonShareInfoForm
	 * @return List<PersonShareInfo>
	 */
	List<PersonShareInfo> listPersonShareInfo(PersonShareInfoForm personShareInfoForm);

	/**
	 * 공유관리 이전내역보기 List
	 * @param PersonShareInfoForm
	 * @return List<PersonShareInfo>
	 */
	List<PersonShareInfo> listPersonShareInfoHist(PersonShareInfoForm personShareInfoForm);

	/**
	 * 공유관리 모두해지하기list
	 * @param PersonShareInfo
	 * @return List<PersonShareInfo>
	 */
	List<PersonShareInfo> listShareInfoAllCancel(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - hist정보
	 * @param PersonShareInfo
	 * @return PersonShareInfo
	 */
	PersonShareInfo getPersonShareInfoHist(PersonShareInfo personShareInfo);

	/**
	 * 공유관리상세 - 공유정보
	 * @param PersonShareInfo
	 * @return PersonShareInfo
	 */
	PersonShareInfo getPersonShareInfo(PersonShareInfo personShareInfo);

	/**
	 * 공유관리상세 - 소득정보
	 * @param PersonShareInfo
	 * @return PersonShareInfo
	 */
	PersonShareInfo getPersonShareEtmInfo(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(자산정보 계좌list)
	 * @param PersonShareInfo
	 * @return List<PersonShareInfo>
	 */
	List<PersonShareInfo> listPersonShareInfoAsset(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(소비정보 계좌list)
	 * @param PersonShareInfo
	 * @return List<PersonShareInfo>
	 */
	List<PersonShareInfo> listPersonShareInfoConsume(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(부채정보 계좌list)
	 * @param PersonShareInfo
	 * @return List<PersonShareInfo>
	 */
	List<PersonShareInfo> listPersonShareInfoDebt(PersonShareInfo personShareInfo);


	/**
	 * 공유관리 - 공유요청
	 * @param PersonShareInfo
	 * @return ReturnClass
	 */
	ReturnClass createPersonShareInfo(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(상태변경)
	 * @param PersonShareInfo
	 * @return ReturnClass
	 */
	ReturnClass updatePersonShareInfoSetStatus(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(항목변경)
	 * @param PersonShareInfo
	 * @return ReturnClass
	 */
	ReturnClass updatePersonShareInfoSetItems(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(공유재요청)
	 * @param PersonShareInfo
	 * @return ReturnClass
	 */
	ReturnClass updatePersonShareInfoSet01(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(허용/거절)
	 * @param PersonShareInfo
	 * @return ReturnClass
	 */
	ReturnClass updatePersonShareInfoSet02(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(변경/해지)
	 * @param PersonShareInfo
	 * @return ReturnClass
	 */
	ReturnClass updatePersonShareInfoSet03(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 메세지발송 기간체크
	 * @param PersonShareMessageInfo
	 * @return int
	 */
	int chkPersonShareInfoMessageTerm(PersonShareMessageInfo personShareMessageInfo);

	/**
	 * 공유관리 메세지merge
	 * @param PersonShareMessageInfo
	 * @return ReturnClass
	 */
	ReturnClass mergePersonShareInfoMessage(PersonShareMessageInfo personShareMessageInfo);

	/**
	 * 공유관리 업데이트요청 List
	 * @param PersonShareInfo
	 * @return List<PersonShareInfo>
	 */
	List<PersonShareInfo> listPersonShareInfoReqUpdate(PersonShareInfo personShareInfo);

	/**
	 * 고유 CI번호를 통해 번호가 바뀐 회언인지 확인
	 * @param kcb_ci - kcb 에서 사용하는 고유 판별 번호
	 * @return
	 */
	String getPersonInfoDupCi(PersonVO personVO);

	/**
	 * 핸드폰 번호만 업데이트
	 * @param personVO - hp, no_person 사용
	 * @return
	 */
	int modifyPersonHp(PersonVO personVO);

	/**
	 * 회원가입 시 중복체크
	 * @param personVO
	 * @return
	 */
	PersonVO getPersonInfoDup(PersonVO personVO);

	/**
	 * 회원가입 insert
	 * @param personVO
	 * @return
	 */
	ReturnClass insertPerson(PersonVO personVO) throws UnsupportedEncodingException, FinsetException,IOException;

	/**
	 * 사용자 공인인증서 관리
	 * @param PersonCertificateInfoVO
	 * @return
	 */
	int createPersonCertificateInfo(PersonCertificateInfoVO personCertificateInfoVO);

	/**
	 * 마이페이지 알람 설정정보 list조회
	 * @param String
	 * @return PersonVO
	 */
	List<PersonVO> getPushSettingInfo(String no_person);

	/**
	 * 회원 탈퇴 및 데이터 삭제
	 * @param no_person
	 * @return
	 */
	ReturnClass procPersonInfoDelQuit(String no_person) throws UnsupportedEncodingException, FinsetException, IOException;

}
