package com.koscom.person.dao;

import java.util.List;

import com.koscom.domain.PersonLoginHistInfo;
import com.koscom.domain.PersonShareInfo;
import com.koscom.domain.PersonShareMessageInfo;
import com.koscom.kcb.model.KcbCreditInfoVO;
import com.koscom.person.model.PersonActiveHistVO;
import com.koscom.person.model.PersonAgreeHistVO;
import com.koscom.person.model.PersonAgreedtHistVO;
import com.koscom.person.model.PersonCertificateInfoVO;
import com.koscom.person.model.PersonShareInfoForm;
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
	 * 약관 동의 이력 저장
	 * @param PersonAgreeHistVO
	 * @return
	 */
	int createPersonAgreeHist(PersonAgreeHistVO personAgreeHistVO);

	/**
	 * 약관 동의 이력 조회
	 * @param String
	 * @return PersonAgreeHistVO
	 */
	List<PersonAgreeHistVO> getPersonAgreeHist(String no_person);

	/**
	 * 약관 동의 이력 상세 저장
	 * @param PersonAgreedtHistVO
	 * @return
	 */
	int createPersonAgreedtHist(PersonAgreedtHistVO personAgreedtHistVO);

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
	 * KCB ID 업데이트
	 * @param info
	 */
	void modifyKcbId(KcbCreditInfoVO info);
	
	/**
	 * 모바일 접속 이력
	 * @param PersonLoginHistInfo
	 */
	void insertPersonLoginHist(PersonLoginHistInfo personLoginHist);

	/**
	 * GNB_최근접속시간
	 * @param PersonLoginHistInfo personLoginHist
	 */
	String getPersonConnectTime(String no_person);

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
	 * 이메일 조회
	 * @param	String no_person
	 * @return	String
	 */
	String getPersonEmail(String no_person);

	/**
	 * 이메일 업데이트
	 * @param 	personVO
	 * @return	int
	 */
	int modifyPersonEmail(PersonVO personVO);

	/**
	 * 통신사 코드 업데이트
	 * @param 	personVO
	 * @return	int
	 */
	int modifyPersonCdTel(PersonVO personVO);

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

	//공유관리
	/**
	 * 공유관리List
	 * @param PersonShareInfoForm
	 * @return List<PersonShareInfo>
	 */
	List<PersonShareInfo> listPersonShareInfo(PersonShareInfoForm personShareInfoForm);

	/**
	 * 공유관리 이전내역보기List
	 * @param PersonShareInfoForm
	 * @return List<PersonShareInfo>
	 */
	List<PersonShareInfo> listPersonShareInfoHist(PersonShareInfoForm personShareInfoForm);

	/**
	 * 공유관리 모두해지하기 List
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
	 * 공유관리 등록
	 * @param PersonShareInfo
	 * @return int
	 */
	int duplPersonShareInfo(PersonShareInfo personShareInfo);
	String getPersonShareInfoSeq();
	int createPersonShareInfo(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(요청취소확인)
	 * @param PersonShareInfo
	 * @return int
	 */
	int chkPersonShareInfoReqCancel(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(상태변경)
	 * @param PersonShareInfo
	 * @return int
	 */
	int updatePersonShareInfoSetStatus(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(계좌추가)
	 * @param PersonShareInfo
	 * @return int
	 */
	int deletePsersonShareInfoDetail(PersonShareInfo personShareInfo);
	int createPersonShareInfoDetail(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(항목변경)
	 * @param PersonShareInfo
	 * @return int
	 */
	int updatePersonShareInfoSetItems(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(공유재요청)
	 * @param PersonShareInfo
	 * @return int
	 */
	int updatePersonShareInfoSet01(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(허용/거절)
	 * @param PersonShareInfo
	 * @return int
	 */
	int updatePersonShareInfoSet02(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(변경/해지)
	 * @param PersonShareInfo
	 * @return int
	 */
	int updatePersonShareInfoSet03(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 이력생성
	 * @param PersonShareInfo
	 * @return int
	 */
	int createPersonShareInfoHist(PersonShareInfo personShareInfo);


	/**
	 * 공유관리 메세지발송 기간체크
	 * @param PersonShareMessageInfo
	 * @return int
	 */
	int chkPersonShareInfoMessageTerm(PersonShareMessageInfo personShareMessageInfo);

	/**
	 * 공유관리 메세지merge
	 * @param PersonShareMessageInfo
	 * @return int
	 */
	int mergePersonShareInfoMessage(PersonShareMessageInfo personShareMessageInfo);

	/**
	 * 공유관리 업데이트요청 List
	 * @param PersonShareInfo
	 * @return List<PersonShareInfo>
	 */
	List<PersonShareInfo> listPersonShareInfoReqUpdate(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 공유여부체크
	 * @param PersonShareInfo
	 * @return int
	 */
	int checkSharePerson(PersonShareInfo personShareInfo);

	/**
	 * 고유 CI번호를 통해 번호가 바뀐 회언인지 확인
	 * @param kcb_ci - kcb 에서 사용하는 고유 판별 번호
	 * @return
	 */
	String getPersonInfoDupCi(PersonVO personVO);

	/**
	 * 핸드폰 번호 업데이트
	 * @param personVO - hp, no_person 사용
	 * @return
	 */
	int modifyPersonHp(PersonVO personVO);

	/**
	 * 고객정보 조회 중복 체크
	 * @param personVO
	 * @return
	 */
	PersonVO getPersonInfoDup(PersonVO personVO);

	/**
	 * 회원가입 insert
	 * @param personVO
	 * @return
	 */
	int insertPerson(PersonVO personVO);

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
	 * 회원탈퇴 및 데이터 삭제
	 * @param String
	 * @return
	 */
	int procPersonInfoDelQuit(String no_person);

	/**
	 * 회원탈퇴 내역 저장
	 * @param PersonVO
	 * @return
	 */
	void createPersonQuit(PersonVO personVO);

	/**
	 * 개인설정 존재여부 확인
	 * @param no_person
	 * @return
	 */
	Integer chkPersonSetExist(String no_person);

	/**
	 * 개인설정 기본 값 설정
	 * @param no_person
	 */
	void insertDefaultPersonSet(String no_person);

	
}
