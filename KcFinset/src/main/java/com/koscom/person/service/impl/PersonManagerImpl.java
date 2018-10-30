package com.koscom.person.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koscom.conditionbiz.dao.ConditionbizMapper;
import com.koscom.conditionbiz.model.ConditionbizVO;
import com.koscom.conditioncredit.dao.ConditioncreditMapper;
import com.koscom.conditioncredit.model.ConditioncreditVO;
import com.koscom.conditionhouse.dao.ConditionhouseMapper;
import com.koscom.conditionhouse.model.ConditionhouseVO;
import com.koscom.credit.dao.CreditMapper;
import com.koscom.domain.PersonLoginHistInfo;
import com.koscom.domain.PersonShareInfo;
import com.koscom.domain.PersonShareMessageInfo;
import com.koscom.kcb.model.KcbCreditInfoVO;
import com.koscom.kcb.service.KcbManager;
import com.koscom.person.dao.PersonMapper;
import com.koscom.person.model.PersonActiveHistVO;
import com.koscom.person.model.PersonCertificateInfoVO;
import com.koscom.person.model.PersonShareInfoForm;
import com.koscom.person.model.PersonShareInfoVO;
import com.koscom.person.model.PersonSmsListVO;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.util.Constant;
import com.koscom.util.FinsetException;
import com.koscom.util.ReturnClass;

@Service("personManager")
@Transactional
public class PersonManagerImpl implements PersonManager {

	private static final Logger logger = LoggerFactory.getLogger(PersonManagerImpl.class);

	@Autowired
	private PersonMapper personMapper;

	@Autowired
	private CreditMapper creditMapper;

	@Autowired
	private ConditioncreditMapper conditioncreditMapper;

	@Autowired
	private ConditionbizMapper conditionbizMapper;

	@Autowired
	private ConditionhouseMapper conditionhouseMapper;

	@Autowired
	KcbManager kcbManager;

	@Value("${service.profile}")
    private String profile;

	@Override
	public PersonVO getPersonInfoHp(String hp) {
		return personMapper.getPersonInfoHp(hp);
	}

	@Override
	public PersonVO getPersonInfo(String no_person) {
		return personMapper.getPersonInfo(no_person);
	}

	@Override
	public ReturnClass modifyPwdFailCnt(PersonVO personVO) {
		if(1 != personMapper.modifyPwdFailCnt(personVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public String getYnAgreeUsingInfo(String no_person) {
		return personMapper.getYnAgreeUsingInfo(no_person);
	}

	@Override
	public String getPwdDB(String pwd) {
		return personMapper.getPwdDB(pwd);
	}

	@Override
	public int checkPersonPass(PersonVO personVO) {
		return personMapper.checkPersonPass(personVO);
	}

	@Override
	public ReturnClass modifyFcmToken(PersonVO personVO) {
		if(1 != personMapper.modifyFcmToken(personVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyPassPerson(PersonVO personVO) {
		//비밀번호 수정
		if(1 != personMapper.modifyPassPerson(personVO)) {
			logger.info("비밀번호 수정 처리에 실패하였습니다. 다시 시도해주세요 personVO :" + personVO.toString());
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		logger.info("비밀번호 수정 정상 처리 하였습니다. personVO :" + personVO.toString());
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public void modifyLastLogin(String no_person) {
		personMapper.modifyLastLogin(no_person);
	}

	@Override
	public void insertPersonLoginHist(PersonLoginHistInfo personLoginHist) {
		personMapper.insertPersonLoginHist(personLoginHist);
	}

	@Override
	public String getPersonConnectTime(String no_person) {
		return personMapper.getPersonConnectTime(no_person);
	}

	@Override
	public ReturnClass insertActiveHist(PersonActiveHistVO personActiveHistVO) {
		if(1 != personMapper.insertActiveHist(personActiveHistVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyFingerPrint(PersonVO personVO) {
		if(1 != personMapper.modifyFingerPrint(personVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyPersonEmail(PersonVO personVO) {

		String email = personVO.getEmail();

		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);

        if( !m.matches() ) {
        	return new ReturnClass(Constant.FAILED, "올바른 E-mail 형식이 아닙니다.");
        }

		if(1 != personMapper.modifyPersonEmail(personVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyPersonLogout(PersonVO personVO) {
		if(1 != personMapper.modifyPersonLogout(personVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyPushNoti(PersonVO personVO) {

		int result = 0;

		//person정보 update
		result = personMapper.modifyPushNoti(personVO);

		//알림설정정보 insert,update
		if("each".equals(personVO.getType_push())){
			result = personMapper.modifyCdPush(personVO);
			result = personMapper.insertCdPushHist(personVO);
		}

		if(1 != result) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyYnUseAndLogout(PersonVO personVO) {
		//로그아웃, 사용여부 수정
		if(1 != personMapper.modifyYnUseAndLogout(personVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.");
	}

	@Override
	public String getLastPersonSmsDt(String no_person) {
		return personMapper.getLastPersonSmsDt(no_person);
	}

	@Override
	public ReturnClass createPersonSmsList(List<PersonSmsListVO> list) {

		logger.info("SMS 수신함 이력 저장 시작");
		int cnt_tot = 0;
		int cnt_fail = 0;
		cnt_tot = list.size();

		for (PersonSmsListVO personSmsListVO : list) {
			if ( 1 != personMapper.createPersonSmsList(personSmsListVO) ) {
				logger.info("SMS 수신이력 저장 실패");
				logger.debug( personSmsListVO.toString() );
				cnt_fail++;
			}
		}

		return new ReturnClass(Constant.SUCCESS, String.format("정상적으로 처리되었습니다. [ 전체: %1$d 건 / 성공: %2$d 건 / 실패: %3$d 건 ]", cnt_tot, cnt_tot-cnt_fail, cnt_fail));
	}

	@Override
	public List<PersonShareInfoVO> listPersonShareInfoSummary(PersonShareInfoForm personShareInfoForm) {
		return personMapper.listPersonShareInfoSummary(personShareInfoForm);
	}

	@Override
	public List<PersonShareInfoVO> listPersonShareInfoMain(PersonShareInfoForm personShareInfoForm) {
		return personMapper.listPersonShareInfoMain(personShareInfoForm);
	}
	@Override
	public int listPersonShareInfoMainCount(PersonShareInfoForm personShareInfoForm) {
		return personMapper.listPersonShareInfoMainCount(personShareInfoForm);
	}

	@Override
	public List<PersonShareInfoVO> listShareInfoAllCancel(PersonShareInfoVO personShareInfoVO) {
		return personMapper.listShareInfoAllCancel(personShareInfoVO);
	}

	@Override
	public PersonShareInfoVO getPersonShareInfo(PersonShareInfoVO personShareInfoVO) {
		return personMapper.getPersonShareInfo(personShareInfoVO);
	}

	@Override
	public PersonShareInfoVO getPersonShareEtmInfo(PersonShareInfoVO personShareInfoVO) {
		return personMapper.getPersonShareEtmInfo(personShareInfoVO);
	}

	@Override
	public ReturnClass createPersonShareInfo(PersonShareInfo personShareInfo) {
		String seq_share = personMapper.getPersonShareInfoSeq();

		personShareInfo.setSeq_share(seq_share);
		if(1 != personMapper.createPersonShareInfo(personShareInfo)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}

		personMapper.createPersonShareInfoHist(personShareInfo);
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.", (Object) seq_share);
	}

	@Override
	public ReturnClass updatePersonShareInfoSet01(PersonShareInfo personShareInfo) {
		if(1 != personMapper.duplChkPersonShareInfo(personShareInfo)){ //변경할 내용이 있는지 체크
			if(1 != personMapper.updatePersonShareInfoSet01(personShareInfo)) {
				return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
			}
		}

		return new ReturnClass(Constant.SUCCESS,"공유 재요청 되었습니다.", (Object) personShareInfo.getSeq_share());
	}

	@Override
	public ReturnClass updatePersonShareInfoSet02(PersonShareInfo personShareInfo) {
		String mode_nm = "";
		if("02".equals(personShareInfo.getShare_status())){
			mode_nm = "허용";
		}else if("03".equals(personShareInfo.getShare_status())){
			mode_nm = "거절";
		}

		if(1 != personMapper.updatePersonShareInfoSet02(personShareInfo)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}

		personMapper.createPersonShareInfoHist(personShareInfo);
		return new ReturnClass(Constant.SUCCESS, mode_nm+" 되었습니다.", (Object) personShareInfo.getSeq_share());
	}

	@Override
	public ReturnClass updatePersonShareInfoSet03(PersonShareInfo personShareInfo) {
		String mode_nm = "";
		if("02".equals(personShareInfo.getShare_status())){
			mode_nm = "변경";
		}else if("03".equals(personShareInfo.getShare_status())){
			mode_nm = "해지";
		}

		if("02".equals(personShareInfo.getShare_status()) && 1 == personMapper.duplChkPersonShareInfo(personShareInfo)){
			return new ReturnClass(Constant.FAILED,"변경할 내용이 없습니다.");
		}else{
			if(1 != personMapper.updatePersonShareInfoSet03(personShareInfo)) {
				return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
			}
		}

		personMapper.createPersonShareInfoHist(personShareInfo);
		return new ReturnClass(Constant.SUCCESS, mode_nm+" 되었습니다.", (Object) personShareInfo.getSeq_share());
	}

	@Override
	public int chkPersonShareInfoMessageTerm(PersonShareMessageInfo personShareMessageInfo) {
		return personMapper.chkPersonShareInfoMessageTerm(personShareMessageInfo);
	}

	@Override
	public ReturnClass mergePersonShareInfoMessage(PersonShareMessageInfo personShareMessageInfo) {
			if(1 != personMapper.mergePersonShareInfoMessage(personShareMessageInfo)) {
				return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
			}

		return new ReturnClass(Constant.SUCCESS,"정상처리하였습니다.", (Object) personShareMessageInfo.getSeq_share());
	}

	@Override
	public List<PersonShareInfoVO> listPersonShareInfoReqUpdate(PersonShareInfoVO personShareInfoVO) {
		return personMapper.listPersonShareInfoReqUpdate(personShareInfoVO);
	}

	@Override
	public String getPersonInfoDupCi(PersonVO personVO){
		return personMapper.getPersonInfoDupCi(personVO);
	}

	@Override
	public int modifyPersonHp(PersonVO personVO){
		return personMapper.modifyPersonHp(personVO);
	}

	@Override
	public PersonVO getPersonInfoDup(PersonVO personVO) {
		return personMapper.getPersonInfoDup(personVO);
	}

	@Override
	public ReturnClass insertPerson(PersonVO personVO) throws UnsupportedEncodingException, FinsetException,IOException {

		if(1 != personMapper.insertPerson(personVO)) {
			logger.info("회원 가입 처리에 실패하였습니다. 다시 시도해주세요    personVO :" + personVO.toString());
			return new ReturnClass(Constant.FAILED, "회원가입 처리에 실패하였습니다. 다시 시도해주세요.", personVO);
		} else {

			if(!profile.equals("LOCAL")){

				//KCB 회원 등록 처리
				logger.info("personVO === " + personVO);
				KcbCreditInfoVO info = new KcbCreditInfoVO();
				//info.setNmIf("600");
				info.setCd_regist("01");					// 01 신규, 09 URL
				info.setBgn(personVO.getBgn());				// 생년월일, 성별
				info.setNoPerson(personVO.getNo_person());	// 회원번호
				info.setNmCust(personVO.getNm_person());		// 회원명
				info.setDi(personVO.getKcb_di());				// 회원 KCB DI
				info.setCp(personVO.getKcb_cp());				// 회원 KCB CP
				info.setHp(personVO.getHp());					// 회원 휴대폰번호
				//logger.info("info === " + info.toString());
				//kcbManager.procKcbCb(info);
				//logger.info("600 전문 처리 완료");

				info.setNmIf("600420");
				info.setReq_menu_code("200");
				info.setReq_view_code("s07143331300");
				kcbManager.procKcbCb(info);

				logger.info("600420 전문 처리 완료");
			}

			//등록자, 수정자 셋팅
			personVO.setId_frt(personVO.getNo_person());
			personVO.setId_lst(personVO.getNo_person());

			//상품 검색조건 셋팅
			logger.info("상품 검색조건 셋팅 시작");
			ConditioncreditVO conditioncreditVO = new ConditioncreditVO();
			ConditionbizVO conditionbizVO 		= new ConditionbizVO();
			ConditionhouseVO conditionhouseVO 	= new ConditionhouseVO();

			conditioncreditVO = conditioncreditMapper.getConditioncreditInfo(personVO.getNo_person());
			conditionbizVO = conditionbizMapper.getConditionbizInfo(personVO.getNo_person());
			conditionhouseVO = conditionhouseMapper.getConditionhouseInfo(personVO.getNo_person());

			if(conditioncreditVO == null){
				conditioncreditMapper.insertConditioncreditInfo(personVO.getNo_person()); //신용(개인)
			}
			if(conditionbizVO == null){
				conditionbizMapper.insertConditionbizInfo(personVO.getNo_person()); //신용(사업자)
			}
			if(conditionhouseVO == null){
				conditionhouseMapper.insertConditionhouseInfo(personVO.getNo_person()); //주택담보
			}
			logger.info("상품 검색조건 셋팅 완료");

			//알림 setting(일반, 신용, 부채, 상품, 이벤트(선택))
			logger.info("알림 셋팅 시작");
			List<String> pushItems = new ArrayList<String>();

			//고정 알림값(일반, 신용, 부채, 상품) 셋팅
			String fixPushItems[] = {"01","02","03","04"};
			for (int i = 0; i < fixPushItems.length; i++) {
				pushItems.add(fixPushItems[i]);
			}

			//이벤트 푸시 수신여부 setting
			if("Y".equals(personVO.getYn_eventPush())){
				pushItems.add("05");
			}

			//person_info의 소리+진동 설정, 푸시 수신여부 default값 셋팅
			personVO.setType_push("default");
			personMapper.modifyPushNoti(personVO);

			//push_setting_info insert
			personVO.setStat_push("Y");
			for (int i = 0; i < pushItems.size(); i++) {
				personVO.setItem_push(pushItems.get(i));
				personMapper.insertCdPush(personVO);
				personMapper.insertCdPushHist(personVO);
			}
			logger.info("알림 셋팅 완료");

			logger.info("회원 설정 세팅 시작");
			personMapper.insertDefaultPersonSet(personVO.getNo_person());
			logger.info("회원 설정 세팅 완료");

		}
		logger.info("회원 가입 정상 처리 하였습니다.    personVO :" + personVO.toString());
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.", personVO);
	}

	@Override
	public int createPersonCertificateInfo(PersonCertificateInfoVO personCertificateInfoVO) {
		return personMapper.createPersonCertificateInfo(personCertificateInfoVO);
	}

	@Override
	public List<PersonVO> getPushSettingInfo(String no_person) {
		return personMapper.getPushSettingInfo(no_person);
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

		try {
			PersonVO personVO = new PersonVO();
			personVO = personMapper.getPersonInfo(no_person);
			logger.info(personVO.toString());

			//KCB 회원 삭제 처리
			logger.info("personVO === " + personVO);
			KcbCreditInfoVO info = new KcbCreditInfoVO();

			//등록전문 정상여부 체크
			HashMap<String, String> schMap = new HashMap<String, String>();
			schMap.put("sch_no_person", personVO.getNo_person());
			schMap.put("nm_if", "600420");
			HashMap<String, String> clobMap = creditMapper.getKcbJoinInfo(schMap);

			if (clobMap != null) {
				info.setBgn(personVO.getBgn());				// 생년월일, 성별
				info.setNoPerson(personVO.getNo_person());	// 회원번호
				info.setNmCust(personVO.getNm_person());	// 회원명
				info.setDi(personVO.getKcb_di());			// 회원 KCB DI
				info.setCp(personVO.getKcb_cp());			// 회원 KCB CP
				info.setHp(personVO.getHp());				// 회원 휴대폰번호
				info.setNmIf("600420");
				info.setCd_regist("03");					// 01 신규, 03 해지, 09 URL
				info.setReq_menu_code("200");
				info.setReq_view_code("s07143331300");
				logger.info("info === " + info.toString());
				ReturnClass returnClass = kcbManager.procKcbCb(info);
				logger.error(returnClass.getCd_result() + " ::: " + returnClass.getMessage() + " ::: " + returnClass.getReturnObj().toString());
				logger.error("600420 전문 처리 완료");
			}

			schMap.put("nm_if", "600");
			clobMap = creditMapper.getKcbJoinInfo(schMap);
			if (clobMap != null) {
				info.setNmIf("600");
				info.setCd_regist("05");					// 01 신규, 05 해지
				logger.info("info === " + info.toString());
				kcbManager.procKcbCb(info);
				logger.error("600 전문 처리 완료");
			}

			personVO.setId_frt(personVO.getNo_person());
			personMapper.createPersonQuit(personVO);
			personMapper.procPersonInfoDelQuit(no_person);

		} catch (FinsetException e) {

			logger.error("600420 전문 처리 완료");
			return new ReturnClass(Constant.FAILED, "탈퇴처리시 오류가 발생하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS,"탈퇴가 완료되었습니다.");
		}
}
