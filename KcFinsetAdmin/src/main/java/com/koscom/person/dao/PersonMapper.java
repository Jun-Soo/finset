package com.koscom.person.dao;

import java.util.HashMap;
import java.util.List;

import com.koscom.domain.PersonInfo;
import com.koscom.domain.PersonLoginHist;
import com.koscom.domain.PersonNice12MonthCbScoreInfo;
import com.koscom.domain.PersonShareInfo;
import com.koscom.domain.PersonShareMessageInfo;
import com.koscom.domain.PersonalViewHist;
import com.koscom.person.model.PersonActiveHistVO;
import com.koscom.person.model.PersonAgreeHistVO;
import com.koscom.person.model.PersonCertificateInfoVO;
import com.koscom.person.model.PersonCounselForm;
import com.koscom.person.model.PersonCounselVO;
import com.koscom.person.model.PersonForm;
import com.koscom.person.model.PersonInfoHistVO;
import com.koscom.person.model.PersonKcb12MonthCbScoreVO;
import com.koscom.person.model.PersonKcbVO;
import com.koscom.person.model.PersonLoginHistForm;
import com.koscom.person.model.PersonLoginHistVO;
import com.koscom.person.model.PersonNice12MonthCbScoreVO;
import com.koscom.person.model.PersonNiceCashServiceVO;
import com.koscom.person.model.PersonNiceDebtAdjustmentVO;
import com.koscom.person.model.PersonNiceDebtGuaranteeVO;
import com.koscom.person.model.PersonNiceDefaultBankVO;
import com.koscom.person.model.PersonNiceDefaultNiceVO;
import com.koscom.person.model.PersonNiceDelayNiceVO;
import com.koscom.person.model.PersonNiceLoanAnalysisVO;
import com.koscom.person.model.PersonNiceLoanVO;
import com.koscom.person.model.PersonNiceVO;
import com.koscom.person.model.PersonQuitVO;
import com.koscom.person.model.PersonShareInfoForm;
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
	 * 고객 리스트 조회
	 *
	 * @param personForm
	 * @return
	 */
	List<PersonVO> listPersonInfoPg(PersonForm personForm);

	/**
	 * 고객 리스트 건수 조회
	 *
	 * @param personForm
	 * @return
	 */
	int listPersonCount(PersonForm personForm);

	/**
	 * 고객정보 조회
	 *
	 * @param no_person
	 * @return
	 */
	PersonVO getPersonInfo(String no_person);

	/**
	 * 사용여부 수정
	 *
	 * @param personVO
	 * @return
	 */
	int modifyYnUse(PersonVO personVO);
	/**
	 * 고객정보 조회(대리점)
	 *
	 * @param no_person
	 * @return
	 */
	PersonVO getPersonInfoAgency(String no_person);

	/**
	 * 고객정보 수정
	 *
	 * @param personInfo
	 * @return
	 */
	int modifyPerson(PersonVO personVO);

	/**
	 * 고객목록 조회
	 *
	 * @param personForm
	 * @return
	 */
	List<PersonVO> listPersonInfo(PersonForm personForm);

	/**
	 * 고객정보 등록
	 *
	 * @param personVO
	 */
	void createPerson(PersonVO personVO);

	/**
	 * 고객정보 등록(대리점)
	 *
	 * @param personVO
	 */
	void createPersonAgency(PersonVO personVO);

	/**
	 * 고객목록 연계검사
	 *
	 * @param personForm
	 * @return
	 */
	List<PersonVO> listPersonRel(PersonVO personVO);

	/**
	 * 휴대폰 번호 변경
	 *
	 * @param personVO
	 * @return
	 */
	int modifyHp(PersonInfo personInfo);
	int modifyBgnNmAndHp(PersonInfo personInfo);

	/**
	 * 개인정보 열람 이력 저장
	 *
	 * @param personVO
	 */
	void insertPersonalViewHist(PersonalViewHist personalViewHist);

	/**
	 * 고객목록 엑셀 다운로드
	 *
	 * @param personForm
	 * @return
	 */
	List<HashMap<String, Object>> listPersonInfoPg_excel(PersonForm personForm);

	/**
	 * 개인정보 조회(map)
	 *
	 * @param no_person
	 * @return
	 */
	HashMap<String, String> getPersonInfoMap(PersonVO personVO);

	/**
	 * 개인정보 수정이력 저장
	 *
	 * @param personInfoHistVO
	 */
	void insertPersonInfoHist(PersonInfoHistVO personInfoHistVO);

	/**
	 * 개인정보 수정이력 리스트
	 *
	 * @param personForm
	 * @return
	 */
	List<PersonInfoHistVO> listPersonInfoHist(PersonForm personForm);

	/**
	 * 개인정보 수정이력 건수 조회
	 *
	 * @param personForm
	 * @return
	 */
	int listPersonInfoCount(PersonForm personForm);

	/**
	 * 기존고객 카운트 조회
	 *
	 * @param personForm
	 * @return
	 */
	int listExistPersonCount(PersonForm personForm);

	/**
	 * 기존고객 카운트 조회(no_person 중복체크)
	 *
	 * @param personVO
	 * @return
	 */
	int getSamePersonCount(PersonVO personVO);

	/**
	 * 기존고객 상담사조회
	 *
	 * @param personForm
	 * @return
	 */
	String getIdPrepare(PersonForm personForm);

	/**
	 * 기존고객 정보조회
	 *
	 * @param personVO
	 * @return
	 */
	PersonVO getExistPersonInfo(PersonVO personVO);

	/**
	 * 휴대폰번호로 고객정보 조회
	 *
	 * @param in_number
	 * @return
	 */
	PersonVO getPersonInfoHp(String number);

	int procPersonNice(PersonNiceVO personNiceVO);

	PersonNiceVO getPersonNiceInfo(String no_person);

	int procPersonKcb(PersonKcbVO personKcbVO);

	PersonKcbVO getPersonKcbInfo(String no_person);

	int procPersonKcb12Month(PersonKcb12MonthCbScoreVO personKcb12MonthCbScoreVO);

	List<PersonKcb12MonthCbScoreVO> getPersonKcb12Month(String no_person);

	int procPersonNice12Month(PersonNice12MonthCbScoreVO personNice12MonthCbScoreVO);

	List<PersonNice12MonthCbScoreVO> getPersonNice12Month(String no_person);

	List<PersonNiceLoanVO> listPersonNiceLoanAmtSumByCdFin(String no_person);

	PersonNiceLoanVO getPersonNiceLoanAmtSum(String no_person);

	PersonNiceLoanVO getPersonNiceAmtPayMonthSum(String no_person);

	/**
	 * 탈퇴 고객 리스트 조회
	 *
	 * @param personQuitVO
	 * @return
	 */
	List<PersonQuitVO> listPersonQuitInfoPg(PersonForm personForm);

	/**
	 * 탈퇴 고객 리스트 건수 조회
	 *
	 * @param personQuitVO
	 * @return
	 */
	int listPersonQuitCount(PersonForm personForm);
	void createPersonQuit(PersonVO personVO);
	int procPersonQuit(String no_person);

	String getExistNiceLast6MonthGrade(PersonNice12MonthCbScoreInfo personNice12MonthCbScoreInfo);

	PersonNiceLoanAnalysisVO getPersonNiceLoanAnalysis(String no_person);
	int modifyPersonSafeKey(PersonVO personVO);

	List<PersonNiceLoanVO> listPersonNiceLoan(String no_person);

	List<PersonNiceCashServiceVO> listPersonNiceCashService(String no_person);
	List<PersonNiceDebtGuaranteeVO> listPersonNiceDebtGuarantee(String no_person);
	List<PersonNiceDebtAdjustmentVO> listPersonNiceDebtAdjustment(String no_person);
	List<PersonNiceDefaultBankVO> listPersonNiceDefaultBank(String no_person);
	List<PersonNiceDefaultNiceVO> listPersonNiceDefaultNice(String no_person);
	List<PersonNiceDelayNiceVO> listPersonNiceDelayNice(String no_person);

	PersonNiceLoanVO getPersonNiceLoanInfo(PersonForm personForm);

	PersonNiceCashServiceVO getPersonNiceCashServiceInfo(PersonForm personForm);
	PersonNiceDebtGuaranteeVO getPersonNiceDebtGuaranteeInfo(PersonForm personForm);
	PersonNiceDebtAdjustmentVO getPersonNiceDebtAdjustmentInfo(PersonForm personForm);
	PersonNiceDefaultBankVO getPersonNiceDefaultBankInfo(PersonForm personForm);
	PersonNiceDefaultNiceVO getPersonNiceDefaultNiceInfo(PersonForm personForm);
	PersonNiceDelayNiceVO getPersonNiceDelayNiceInfo(PersonForm personForm);

	int procPersonNiceLoan(PersonNiceLoanVO personNiceLoanVO);

	int procPersonNiceCashService(PersonNiceCashServiceVO personNiceCashServiceVO);
	int procPersonNiceDebtGuarantee(PersonNiceDebtGuaranteeVO personNiceDebtGuaranteeVO);
	int procPersonNiceDebtAdjustment(PersonNiceDebtAdjustmentVO personNiceDebtAdjustmentVO);
	int procPersonNiceDefaultBank(PersonNiceDefaultBankVO personNiceDefaultBankVO);
	int procPersonNiceDefaultNice(PersonNiceDefaultNiceVO personNiceDefaultNiceVO);
	int procPersonNiceDelayNice(PersonNiceDelayNiceVO personNiceDelayNiceVO);

	int delNiceLoan(String no_niceloan);

	int delNiceCashService(String no_nice_cash_service);
	int delNiceDebtGuarantee(String no_nice_debt_guarantee);
	int delNiceDebtAdjustment(String no_nice_debt_adjustment);
	int delNiceDefaultBank(String no_nice_default_bank);
	int delNiceDefaultNice(String no_nice_default_nice);
	int delNiceDelayNice(String no_nice_delay_nice);
	int modifyAgreeUsing(PersonVO personVO);
	int procPersonReUse(String no_person);
	List<PersonAgreeHistVO> listPersonAgreeHistInfoPg(PersonForm personForm);
	int listPersonAgreeHistCount(PersonForm personForm);
	int createPersonAgreeHist(PersonAgreeHistVO personAgreeHistVO);

	List<PersonActiveHistVO> listPersonActiveHistInfoPg(PersonForm personForm);
	int listPersonActiveHistCount(PersonForm personForm);
	int procActiveHist(PersonActiveHistVO personActiveHistVO);
	/**
	 * 매달 1일 이전달 신용등급, 평점으로 insert
	 *
	 * @return
	 */
	int insertPersonNice12MonthByQuartz();

	String getYnAgreeUsingInfo(String no_person);

	//자동완성
	public List<PersonVO> listPersonInfoJson(PersonForm form);

	/**
	 * 회원가입 insert
	 *
	 * @param personVO
	 * @return
	 */
	int insertPerson(PersonVO personVO);

	/**
	 * 고객정보 조회 중복 체크
	 *
	 * @param personVO
	 * @return
	 */
	PersonVO getPersonInfoDup(PersonVO personVO);

	int modifyPassPerson(PersonVO personVO);

	/**
	 * 토큰 업데이트
	 * @return
	 */
	int modifyFcmToken(PersonVO personVO);

	/**
	 * 최근접속 업데이트
	 *
	 * @param no_person
	 * @return
	 */
	void modifyLastLogin(String no_person);

	/**
	 * no_person으로 탈퇴이력 최근 1건만 조회
	 *
	 * @param no_person
	 * @return
	 */
	PersonQuitVO getPersonQuitInfo(String no_person);

	/**
	 * 페이지 이동 기록
	 * @param personVO
	 * @return
	 */
	int insertActiveHist(PersonActiveHistVO personActiveHistVO);

	/**
	 * 모바일 접속 이력
	 * @param personVO
	 * @return
	 */
	void insertPersonLoginHist(PersonLoginHist personLoginHist);

	/**
	 * 모바일 접속 이력
	 * @param personLoginHistForm
	 * @return
	 */
	List<PersonLoginHistVO> listPersonLoginHist(PersonLoginHistForm personLoginHistForm);

	/**
	 * 모바일 접속 이력 건수 조회
	 *
	 * @param personForm
	 * @return
	 */
	int listPersonLoginHistCount(PersonLoginHistForm personLoginHistForm);

	/**
	 * 비밀번호/지문 틀린 횟수 업데이트
	 * @return
	 */
	int modifyPwdFailCnt(PersonVO personVO);

	/**
	 * 지문 업데이트
	 * @return
	 */
	int modifyFingerPrint(PersonVO personVO);

	/**
	 * 회원탈퇴 및 데이터 삭제
	 * @return
	 */
	int procPersonInfoDelQuit(String no_person);

	/**
	 * 프로필 사진 등록/수정
	 * @return
	 */
	int profileImg(PersonVO personVO);

	/**
	 * 이메일 업데이트
	 * @return
	 */
	int modifyPersonEmail(PersonVO personVO);

	/**
	 * 로그아웃 업데이트
	 * @return
	 */
	int modifyPersonLogout(PersonVO personVO);

	/**
	 * 푸쉬알림설정
	 * @return
	 */
	int modifyPushNoti(PersonVO personVO);
	int insertCdPush(PersonVO personVO);
	int modifyCdPush(PersonVO personVO);
	int insertCdPushHist(PersonVO personVO);

	/**
	 * 로그아웃, 사용여부 업데이트
	 * @return
	 */
	int modifyYnUseAndLogout(PersonVO personVO);

	/**
	 * 증권토큰 업데이트
	 * @return
	 */
	int modifyStockToken(PersonVO personVO);

	/**
	 * personVO로 탈퇴이력 최근 1건만 조회
	 *
	 * @param personVO
	 * @return
	 */
	PersonQuitVO getPersonQuitChk(PersonVO personVO);

	/**
	 * 마이페이지 알람 설정정보 list조회
	 *
	 */
	List<PersonVO> getPushSettingInfo(String no_person);

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
	 * 비밀번호 체크
	 * @param personVO
	 * @return int
	 */
	int checkPersonPass(PersonVO personVO);
	String getPwdDB(String pwd);

	/* srchou */
	/**
	 * 전체 고객 조회(전체푸쉬위한정보)
	 * @param
	 * @return List<PersonVO>
	 */
	List<PersonVO> getAllPersonInfo();

	/**
	 * 개인정보 수정 이력을 위한 조회
	 * @param
	 * @return List<PersonVO>
	 */
	PersonInfoHistVO getPersonInfoHist(String no_person);

	/**
	 * 신용상담 신청정보 조회
	 * @param no_person
	 * @return
	 */
	PersonCounselVO getPersonCounselInfo(String no_person);

	/**
	 * 신용상담 신청
	 * @param PersonCounselVO
	 * @return
	 */
	int createPersonCounsel(PersonCounselVO personCounselVO);

	/**
	 * 신용상담(페이징)
	 * @param personCounselForm
	 * @return
	 */
	List<PersonCounselVO> getPersonCounselList(PersonCounselForm personCounselForm);

	/**
	 * 신용상담(페이징) 건수 조회
	 * @param personCounselForm
	 * @return
	 */
	int getPersonCounselListCount(PersonCounselForm personCounselForm);

	/**
	 * 신용상담 상세 조회
	 * @param personCounselVO
	 * @return
	 */
	HashMap<String, String> getPersonCounselMapInfo(PersonCounselVO personCounselVO);

	/**
	 * 사용자 공인인증서 관리
	 * @param PersonCertificateInfoVO
	 * @return
	 */
	int createPersonCertificateInfo(PersonCertificateInfoVO personCertificateInfoVO);

	/**
	 * 20180717 VITCOM KHK
	 * 고유 CI번호를 통해 번호가 바뀐 회언인지 확인
	 * @param kcb_ci - kcb 에서 사용하는 고유 판별 번호
	 * @return
	 */
	String getPersonInfoDupCi(PersonVO personVO);

	/**
	 * 20180717 VITCOM KHK
	 * 핸드폰 번호 업데이트
	 * @param personVO - hp, no_person 사용
	 * @return
	 */
	int modifyPersonHp(PersonVO personVO);

	//공유관리
	/**
	 * 공유관리 SummaryList
	 * @param PersonShareInfoForm
	 * @return List<PersonShareInfoVO>
	 */
	List<PersonShareInfoVO> listPersonShareInfoSummary(PersonShareInfoForm personShareInfoForm);

	/**
	 * 공유관리 마이페이지List
	 * @param PersonShareInfoForm
	 * @return List<PersonShareInfoVO>
	 */
	List<PersonShareInfoVO> listPersonShareInfoMain(PersonShareInfoForm personShareInfoForm);
	int listPersonShareInfoMainCount(PersonShareInfoForm personShareInfoForm);

	/**
	 * 공유관리 모두해지하기 List
	 * @param PersonShareInfoVO
	 * @return List<PersonShareInfoVO>
	 */
	List<PersonShareInfoVO> listShareInfoAllCancel(PersonShareInfoVO personShareInfoVO);

	/**
	 * 공유관리상세 - 공유정보
	 * @param PersonShareInfoVO
	 * @return PersonShareInfoVO
	 */
	PersonShareInfoVO getPersonShareInfo(PersonShareInfoVO personShareInfoVO);

	/**
	 * 공유관리상세 - 소득정보
	 * @param PersonShareInfoVO
	 * @return PersonShareInfoVO
	 */
	PersonShareInfoVO getPersonShareEtmInfo(PersonShareInfoVO personShareInfoVO);

	/**
	 * 공유관리 등록
	 * @param PersonShareInfo
	 * @return int
	 */
	String getPersonShareInfoSeq();
	int createPersonShareInfo(PersonShareInfo personShareInfo);

	/**
	 * 공유관리 - 공유설정(중복체크)
	 * @param PersonShareInfo
	 * @return int
	 */
	int duplChkPersonShareInfo(PersonShareInfo personShareInfo);

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
	 * @param PersonShareInfoVO
	 * @return List<PersonShareInfoVO>
	 */
	List<PersonShareInfoVO> listPersonShareInfoReqUpdate(PersonShareInfoVO personShareInfoVO);

	


}
