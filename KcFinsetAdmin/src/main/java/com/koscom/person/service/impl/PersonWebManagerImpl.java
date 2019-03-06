package com.koscom.person.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import com.koscom.util.FinsetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.PersonLoginHist;
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
import com.koscom.person.service.PersonManager;
import com.koscom.person.service.PersonWebManager;
import com.koscom.util.ReturnClass;
import com.koscom.util.SpringApplicationContext;

@Service("personWebManager")
public class PersonWebManagerImpl implements PersonWebManager{

	@Autowired
	private PersonManager personManager;


	@Override
	public ReturnClass procPerson(PersonVO personVO) {
		return personManager.procPerson(personVO);
	}
	@Override
	public String getYnAgreeUsingInfo(String no_person){
		return personManager.getYnAgreeUsingInfo(no_person);
	}
	@Override
	public PersonVO getPersonInfoAgency(String no_person) {
		return personManager.getPersonInfoAgency(no_person);
	}

	@Override
	public ReturnClass procPersonAgency(PersonVO personVO) {
		return personManager.procPersonAgency(personVO);
	}

	@Override
	public PersonVO getPersonInfo(String no_person) {
		return personManager.getPersonInfo(no_person);
	}
	@Override
	public String getPersonInfo(String no_person, String type) {
		personManager = (PersonManager) SpringApplicationContext.getBean("personManager");
		PersonVO personVO = personManager.getCachePersonInfo(no_person);
		if(personVO == null) return no_person;
		String result = "";
		// NO
		if ( "NO".equals(type) ) {
			result = personVO.getNo_person();
		// 이름
		}else if ( "NM".equals(type) ) {
			result = personVO.getNm_person();
		// 내선번호
		}else if ( "NO".equals(type) ) {
			result = personVO.getNo_person();
		// 휴대폰번호
		}else if ( "HP".equals(type)) {
			result = personVO.getHp();
		}
		return result;
	}

	@Override
	public ReturnClass procPersonAgreeHist(PersonAgreeHistVO personAgreeHistVO) {
		return personManager.procPersonAgreeHist(personAgreeHistVO);
	}
	@Override
	public ReturnClass procPersonQuit(String no_person) {
		return personManager.procPersonQuit(no_person);
	}
	@Override
	public ReturnClass procPersonReUse(String no_person) {
		return personManager.procPersonReUse(no_person);
	}

	@Override
	public int getSamePersonCount(PersonVO personVO) {
		return personManager.getSamePersonCount(personVO);
	}

	@Override
	public String[] formPersonNiceLastMonth(int cntLastMonth) {
		return personManager.formPersonNiceLastMonth(cntLastMonth);
	}

	@Override
	public List<PersonNice12MonthCbScoreVO> getPersonNice12Month(String no_person) {
		return personManager.getPersonNice12Month(no_person);
	}

	@Override
	public PersonNiceVO getPersonNiceInfo(String no_person) {
		return personManager.getPersonNiceInfo(no_person);
	}

	@Override
	public PersonKcbVO getPersonKcbInfo(String no_person) {
		return personManager.getPersonKcbInfo(no_person);
	}

	@Override
	public List<PersonNiceLoanVO> listPersonNiceLoan(String no_person) {
		return personManager.listPersonNiceLoan(no_person);
	}

	@Override
	public List<PersonNiceLoanVO> listPersonNiceLoanAmtSumByCdFin(String no_person) {
		return personManager.listPersonNiceLoanAmtSumByCdFin(no_person);
	}

	@Override
	public PersonNiceLoanVO getPersonNiceLoanAmtSum(String no_person) {
		return personManager.getPersonNiceLoanAmtSum(no_person);
	}

	@Override
	public PersonNiceLoanVO getPersonNiceAmtPayMonthSum(String no_person) {
		return personManager.getPersonNiceAmtPayMonthSum(no_person);
	}

	@Override
	public PersonNiceLoanAnalysisVO getPersonNiceLoanAnalysis(String no_person) {
		return personManager.getPersonNiceLoanAnalysis(no_person);
	}

	@Override
	public List<PersonVO> listPersonInfo(PersonForm personForm) {
		return personManager.listPersonInfo(personForm);
	}
	@Override
	public ReturnClass modifyBgnNmAndHp(PersonVO personVO) {
		return personManager.modifyBgnNmAndHp(personVO);
	}
	@Override
	public ReturnClass modifyPersonSafeKey(PersonVO personVO) {
		return personManager.modifyPersonSafeKey(personVO);
	}
	@Override
	public ReturnClass modifyAgreeUsing(PersonVO personVO) {
		return personManager.modifyAgreeUsing(personVO);
	}
	@Override
	public ReturnClass insertPerson(PersonVO personVO) throws UnsupportedEncodingException, FinsetException,IOException {
		return personManager.insertPerson(personVO);
	}
	@Override
	public PersonVO getPersonInfoDup(PersonVO personVO) {
		return personManager.getPersonInfoDup(personVO);
	}
	@Override
	public ReturnClass modifyPassPerson(PersonVO personVO) {
		return personManager.modifyPassPerson(personVO);
	}
	@Override
	public ReturnClass modifyFcmToken(PersonVO personVO) {
		return personManager.modifyFcmToken(personVO);
	}
	@Override
	public void modifyLastLogin(String no_person) {
		personManager.modifyLastLogin(no_person);
	}
	@Override
	public PersonQuitVO getPersonQuitInfo(String no_person) {
		return personManager.getPersonQuitInfo(no_person);
	}

	/**
	 * 페이지 이동 기록
	 * @param personVO
	 * @return
	 */
	public ReturnClass insertActiveHist(PersonActiveHistVO personActiveHistVO){
		return personManager.insertActiveHist(personActiveHistVO);
	}
	@Override
	public void insertPersonLoginHist(PersonLoginHist personLoginHist) {
		personManager.insertPersonLoginHist(personLoginHist);
	}
	@Override
	public List<PersonVO> listPersonInfoPg(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.listPersonInfoPg(personForm);
	}
	@Override
	public int listPersonCount(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.listPersonCount(personForm);
	}
	@Override
	public ReturnClass modifyYnUse(PersonVO personVO) {
		// TODO Auto-generated method stub
		return personManager.modifyYnUse(personVO);
	}
	@Override
	public HashMap<String, List<PersonVO>> listPersonRel(PersonVO personVO) {
		// TODO Auto-generated method stub
		return personManager.listPersonRel(personVO);
	}
	@Override
	public List<HashMap<String, Object>> listPersonInfoPg_excel(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.listPersonInfoPg_excel(personForm);
	}
	@Override
	public ReturnClass modifyHp(PersonVO personVO) {
		// TODO Auto-generated method stub
		return personManager.modifyHp(personVO);
	}
	@Override
	public ReturnClass viewPersonnal(PersonalViewHist personalViewHist) {
		// TODO Auto-generated method stub
		return personManager.viewPersonnal(personalViewHist);
	}
	@Override
	public List<PersonInfoHistVO> listPersonInfoHist(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.listPersonInfoHist(personForm);
	}
	@Override
	public int listPersonInfoCount(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.listPersonInfoCount(personForm);
	}
	@Override
	public int listExistPersonCount(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.listExistPersonCount(personForm);
	}
	@Override
	public String getIdPrepare(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.getIdPrepare(personForm);
	}
	@Override
	public PersonVO getExistPersonInfo(PersonVO personVO) {
		// TODO Auto-generated method stub
		return personManager.getExistPersonInfo(personVO);
	}
	@Override
	public PersonVO getPersonInfoHp(String number) {
		// TODO Auto-generated method stub
		return personManager.getPersonInfoHp(number);
	}
	@Override
	public PersonVO getCachePersonInfo(String no_person) {
		// TODO Auto-generated method stub
		return personManager.getCachePersonInfo(no_person);
	}
	@Override
	public ReturnClass procPersonNice(PersonNiceVO personNiceVO) {
		// TODO Auto-generated method stub
		return personManager.procPersonNice(personNiceVO);
	}
	@Override
	public ReturnClass procPersonKcb(PersonKcbVO personVO) {
		// TODO Auto-generated method stub
		return personManager.procPersonKcb(personVO);
	}
	@Override
	public ReturnClass procPersonNice12Month(PersonNice12MonthCbScoreVO personNice12MonthCbScoreVO) {
		// TODO Auto-generated method stub
		return personManager.procPersonNice12Month(personNice12MonthCbScoreVO);
	}
	@Override
	public ReturnClass procPersonKcb12Month(PersonKcb12MonthCbScoreVO personKcb12MonthCbScoreVO) {
		// TODO Auto-generated method stub
		return personManager.procPersonKcb12Month(personKcb12MonthCbScoreVO);
	}
	@Override
	public String[] formPersonKcb12Month(String no_person) {
		// TODO Auto-generated method stub
		return personManager.formPersonKcb12Month(no_person);
	}
	@Override
	public List<PersonKcb12MonthCbScoreVO> getPersonKcb12Month(String no_person) {
		// TODO Auto-generated method stub
		return personManager.getPersonKcb12Month(no_person);
	}
	@Override
	public List<PersonQuitVO> listPersonQuitInfoPg(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.listPersonQuitInfoPg(personForm);
	}
	@Override
	public int listPersonQuitCount(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.listPersonQuitCount(personForm);
	}
	@Override
	public String getExistNiceLast6MonthGrade(String no_person, int grade) {
		// TODO Auto-generated method stub
		return personManager.getExistNiceLast6MonthGrade(no_person, grade);
	}
	@Override
	public List<PersonNiceCashServiceVO> listPersonNiceCashService(String no_person) {
		// TODO Auto-generated method stub
		return personManager.listPersonNiceCashService(no_person);
	}
	@Override
	public List<PersonNiceDebtGuaranteeVO> listPersonNiceDebtGuarantee(String no_person) {
		// TODO Auto-generated method stub
		return personManager.listPersonNiceDebtGuarantee(no_person);
	}
	@Override
	public List<PersonNiceDebtAdjustmentVO> listPersonNiceDebtAdjustment(String no_person) {
		// TODO Auto-generated method stub
		return personManager.listPersonNiceDebtAdjustment(no_person);
	}
	@Override
	public List<PersonNiceDefaultBankVO> listPersonNiceDefaultBank(String no_person) {
		// TODO Auto-generated method stub
		return personManager.listPersonNiceDefaultBank(no_person);
	}
	@Override
	public List<PersonNiceDefaultNiceVO> listPersonNiceDefaultNice(String no_person) {
		// TODO Auto-generated method stub
		return personManager.listPersonNiceDefaultNice(no_person);
	}
	@Override
	public List<PersonNiceDelayNiceVO> listPersonNiceDelayNice(String no_person) {
		// TODO Auto-generated method stub
		return personManager.listPersonNiceDelayNice(no_person);
	}
	@Override
	public PersonNiceLoanVO getPersonNiceLoanInfo(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.getPersonNiceLoanInfo(personForm);
	}
	@Override
	public PersonNiceCashServiceVO getPersonNiceCashServiceInfo(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.getPersonNiceCashServiceInfo(personForm);
	}
	@Override
	public PersonNiceDebtGuaranteeVO getPersonNiceDebtGuaranteeInfo(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.getPersonNiceDebtGuaranteeInfo(personForm);
	}
	@Override
	public PersonNiceDebtAdjustmentVO getPersonNiceDebtAdjustmentInfo(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.getPersonNiceDebtAdjustmentInfo(personForm);
	}
	@Override
	public PersonNiceDefaultBankVO getPersonNiceDefaultBankInfo(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.getPersonNiceDefaultBankInfo(personForm);
	}
	@Override
	public PersonNiceDefaultNiceVO getPersonNiceDefaultNiceInfo(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.getPersonNiceDefaultNiceInfo(personForm);
	}
	@Override
	public PersonNiceDelayNiceVO getPersonNiceDelayNiceInfo(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.getPersonNiceDelayNiceInfo(personForm);
	}
	@Override
	public ReturnClass procPersonNiceLoan(PersonNiceLoanVO personNiceLoanVO) {
		// TODO Auto-generated method stub
		return personManager.procPersonNiceLoan(personNiceLoanVO);
	}
	@Override
	public ReturnClass procPersonNiceCashService(PersonNiceCashServiceVO personNiceCashServiceVO) {
		// TODO Auto-generated method stub
		return personManager.procPersonNiceCashService(personNiceCashServiceVO);
	}
	@Override
	public ReturnClass procPersonNiceDebtGuarantee(PersonNiceDebtGuaranteeVO personNiceDebtGuaranteeVO) {
		// TODO Auto-generated method stub
		return personManager.procPersonNiceDebtGuarantee(personNiceDebtGuaranteeVO);
	}
	@Override
	public ReturnClass procPersonNiceDebtAdjustment(PersonNiceDebtAdjustmentVO personNiceDebtAdjustmentVO) {
		// TODO Auto-generated method stub
		return personManager.procPersonNiceDebtAdjustment(personNiceDebtAdjustmentVO);
	}
	@Override
	public ReturnClass procPersonNiceDefaultBank(PersonNiceDefaultBankVO personNiceDefaultBankVO) {
		// TODO Auto-generated method stub
		return personManager.procPersonNiceDefaultBank(personNiceDefaultBankVO);
	}
	@Override
	public ReturnClass procPersonNiceDefaultNice(PersonNiceDefaultNiceVO personNiceDefaultNiceVO) {
		// TODO Auto-generated method stub
		return personManager.procPersonNiceDefaultNice(personNiceDefaultNiceVO);
	}
	@Override
	public ReturnClass procPersonNiceDelayNice(PersonNiceDelayNiceVO personNiceDelayNiceVO) {
		// TODO Auto-generated method stub
		return personManager.procPersonNiceDelayNice(personNiceDelayNiceVO);
	}
	@Override
	public ReturnClass delNiceLoan(String no_niceloan) {
		// TODO Auto-generated method stub
		return personManager.delNiceLoan(no_niceloan);
	}
	@Override
	public ReturnClass delNiceCashService(String no_nice_cash_service) {
		// TODO Auto-generated method stub
		return personManager.delNiceCashService(no_nice_cash_service);
	}
	@Override
	public ReturnClass delNiceDebtGuarantee(String no_nice_debt_guarantee) {
		// TODO Auto-generated method stub
		return personManager.delNiceDebtGuarantee(no_nice_debt_guarantee);
	}
	@Override
	public ReturnClass delNiceDebtAdjustment(String no_nice_debt_adjustment) {
		// TODO Auto-generated method stub
		return personManager.delNiceDebtAdjustment(no_nice_debt_adjustment);
	}
	@Override
	public ReturnClass delNiceDefaultBank(String no_nice_default_bank) {
		// TODO Auto-generated method stub
		return personManager.delNiceDefaultBank(no_nice_default_bank);
	}
	@Override
	public ReturnClass delNiceDefaultNice(String no_nice_default_nice) {
		// TODO Auto-generated method stub
		return personManager.delNiceDefaultNice(no_nice_default_nice);
	}
	@Override
	public ReturnClass delNiceDelayNice(String no_nice_delay_nice) {
		// TODO Auto-generated method stub
		return personManager.delNiceDelayNice(no_nice_delay_nice);
	}
	@Override
	public List<PersonAgreeHistVO> listPersonAgreeHistInfoPg(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.listPersonAgreeHistInfoPg(personForm);
	}
	@Override
	public int listPersonAgreeHistCount(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.listPersonAgreeHistCount(personForm);
	}
	@Override
	public void insertPersonNice12MonthByQuartz() throws Exception {
		// TODO Auto-generated method stub
		personManager.insertPersonNice12MonthByQuartz();
	}
	@Override
	public ReturnClass listPersonInfoJson(PersonForm form) {
		// TODO Auto-generated method stub
		return personManager.listPersonInfoJson(form);
	}
	@Override
	public List<PersonActiveHistVO> listPersonActiveHistInfoPg(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.listPersonActiveHistInfoPg(personForm);
	}
	@Override
	public int listPersonActiveHistCount(PersonForm personForm) {
		// TODO Auto-generated method stub
		return personManager.listPersonActiveHistCount(personForm);
	}
	@Override
	public List<PersonLoginHistVO> listPersonLoginHist(PersonLoginHistForm personLoginHistForm) {
		// TODO Auto-generated method stub
		return personManager.listPersonLoginHist(personLoginHistForm);
	}
	@Override
	public int listPersonLoginHistCount(PersonLoginHistForm personLoginHistForm) {
		// TODO Auto-generated method stub
		return personManager.listPersonLoginHistCount(personLoginHistForm);
	}
	@Override
	public ReturnClass modifyPwdFailCnt(PersonVO personVO) {
		return personManager.modifyPwdFailCnt(personVO);
	}
	@Override
	public ReturnClass modifyFingerPrint(PersonVO personVO) {
		return personManager.modifyFingerPrint(personVO);
	}

	/**
	 * 회원 탈퇴 및 데이터 삭제
	 * @param no_person
	 * @return
	 * @throws IOException
	 * @throws FinsetException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public ReturnClass procPersonInfoDelQuit(String no_person) throws UnsupportedEncodingException, FinsetException, IOException {
		return personManager.procPersonInfoDelQuit(no_person);
	}

	/**
	 * 프로필 사진 등록/수정
	 * @param no_person
	 * @return
	 */
	@Override
	public ReturnClass profileImg(PersonVO personVO) {
		return personManager.profileImg(personVO);
	}

	/**
	 * 이메일 업데이트
	 * @param no_person
	 * @return
	 */
	@Override
	public ReturnClass modifyPersonEmail(PersonVO personVO) {
		return personManager.modifyPersonEmail(personVO);
	}

	/**
	 * 로그아웃 업데이트
	 * @param no_person
	 * @return
	 */
	@Override
	public ReturnClass modifyPersonLogout(PersonVO personVO) {
		return personManager.modifyPersonLogout(personVO);
	}

	/**
	 * 푸시알림설정
	 * @param PersonVO
	 * @return ReturnClass
	 */
	@Override
	public ReturnClass modifyPushNoti(PersonVO personVO) {
		return personManager.modifyPushNoti(personVO);
	}

	@Override
	public ReturnClass modifyYnUseAndLogout(PersonVO personVO) {
		return personManager.modifyYnUseAndLogout(personVO);
	}
	/**
	 * 증권토큰 수정
	 * @param personVO
	 * @return
	 */
	@Override
	public ReturnClass modifyStockToken(PersonVO personVO) {
		return personManager.modifyStockToken(personVO);
	}
	@Override
	public PersonQuitVO getPersonQuitChk(PersonVO personVO) {
		return personManager.getPersonQuitChk(personVO);
	}

	/**
	 * 마이페이지 알람 설정정보 list조회
	 *
	 */
	@Override
	public List<PersonVO> getPushSettingInfo(String no_person) {
		return personManager.getPushSettingInfo(no_person);
	}

	/**
	 * 비밀번호 체크
	 * @param personVO
	 * @return int
	 */
	@Override
	public int checkPersonPass(PersonVO personVO) {
		return personManager.checkPersonPass(personVO);
	}

	@Override
	public String getLastPersonSmsDt(String no_person) {
		return personManager.getLastPersonSmsDt(no_person);
	}

	@Override
	public ReturnClass createPersonSmsList(List<PersonSmsListVO> list) {
		return personManager.createPersonSmsList(list);
	}

	@Override
	public List<PersonVO> getAllPersonInfo() {
		return personManager.getAllPersonInfo();
	}

	@Override
	public String getPwdDB(String pwd) {
		return personManager.getPwdDB(pwd);
	}

	/**
	 * 신용상담
	 *
	 */
	@Override
	public PersonCounselVO getPersonCounselInfo(String no_person) {
		return personManager.getPersonCounselInfo(no_person);
	}

	@Override
	public ReturnClass createPersonCounsel(PersonCounselVO personCounselVO) {
		return personManager.createPersonCounsel(personCounselVO);
	}

	@Override
	public List<PersonCounselVO> getPersonCounselList(PersonCounselForm personCounselForm) {
		return personManager.getPersonCounselList(personCounselForm);
	}

	@Override
	public int getPersonCounselListCount(PersonCounselForm personCounselForm) {
		return personManager.getPersonCounselListCount(personCounselForm);
	}

	@Override
	public HashMap<String, String> getPersonCounselMapInfo(PersonCounselVO personCounselVO) {
		return personManager.getPersonCounselMapInfo(personCounselVO);
	}

	@Override
	public int createPersonCertificateInfo(PersonCertificateInfoVO personCertificateInfoVO) {
		return personManager.createPersonCertificateInfo(personCertificateInfoVO);
	}


	/**
	 * 20180717 VITCOM KHK
	 * 핸드폰 번호 변경에 따른 확인
	 */
	@Override
	public String getPersonInfoDupCi(PersonVO personVO){
		return personManager.getPersonInfoDupCi(personVO);
	}

	@Override
	public int modifyPersonHp(PersonVO personVO){
		return personManager.modifyPersonHp(personVO);
	}

	//공유관리
	@Override
	public List<PersonShareInfoVO> listPersonShareInfoSummary(PersonShareInfoForm personShareInfoForm) {
		return personManager.listPersonShareInfoSummary(personShareInfoForm);
	}

	@Override
	public List<PersonShareInfoVO> listPersonShareInfoMain(PersonShareInfoForm personShareInfoForm) {
		return personManager.listPersonShareInfoMain(personShareInfoForm);
	}
	@Override
	public int listPersonShareInfoMainCount(PersonShareInfoForm personShareInfoForm) {
		return personManager.listPersonShareInfoMainCount(personShareInfoForm);
	}

	@Override
	public List<PersonShareInfoVO> listShareInfoAllCancel(PersonShareInfoVO personShareInfoVO) {
		return personManager.listShareInfoAllCancel(personShareInfoVO);
	}

	@Override
	public PersonShareInfoVO getPersonShareInfo(PersonShareInfoVO personShareInfoVO) {
		return personManager.getPersonShareInfo(personShareInfoVO);
	}

	@Override
	public PersonShareInfoVO getPersonShareEtmInfo(PersonShareInfoVO personShareInfoVO) {
		return personManager.getPersonShareEtmInfo(personShareInfoVO);
	}

	@Override
	public ReturnClass createPersonShareInfo(PersonShareInfo personShareInfo) {
		return personManager.createPersonShareInfo(personShareInfo);
	}

	@Override
	public ReturnClass updatePersonShareInfoSet01(PersonShareInfo personShareInfo) {
		return personManager.updatePersonShareInfoSet01(personShareInfo);
	}

	@Override
	public ReturnClass updatePersonShareInfoSet02(PersonShareInfo personShareInfo) {
		return personManager.updatePersonShareInfoSet02(personShareInfo);
	}

	@Override
	public ReturnClass updatePersonShareInfoSet03(PersonShareInfo personShareInfo) {
		return personManager.updatePersonShareInfoSet03(personShareInfo);
	}

	@Override
	public int chkPersonShareInfoMessageTerm(PersonShareMessageInfo personShareMessageInfo) {
		return personManager.chkPersonShareInfoMessageTerm(personShareMessageInfo);
	}

	@Override
	public ReturnClass mergePersonShareInfoMessage(PersonShareMessageInfo personShareMessageInfo) {
		return personManager.mergePersonShareInfoMessage(personShareMessageInfo);
	}

	@Override
	public List<PersonShareInfoVO> listPersonShareInfoReqUpdate(PersonShareInfoVO personShareInfoVO) {
		return personManager.listPersonShareInfoReqUpdate(personShareInfoVO);
	}

}
