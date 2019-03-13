package com.koscom.person.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koscom.domain.PersonLoginHist;
import com.koscom.domain.PersonNice12MonthCbScoreInfo;
import com.koscom.domain.PersonShareInfo;
import com.koscom.domain.PersonShareMessageInfo;
import com.koscom.domain.PersonalViewHist;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.person.dao.PersonMapper;
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
import com.koscom.util.Constant;
import com.koscom.util.DateUtil;
import com.koscom.util.FileUpload;
import com.koscom.util.FinsetException;
import com.koscom.util.LogUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;
import com.koscom.worker.model.WorkerVO;
import com.koscom.worker.service.WorkerManager;

@Service("personManager")
@Transactional
public class PersonManagerImpl implements PersonManager {

	private static final Logger logger = LoggerFactory.getLogger(PersonManagerImpl.class);

	@Autowired
	private WorkerManager workerManager;

	@Autowired
	private PersonMapper personMapper;

	@Autowired
	private CodeManager codeManager;
	
	@Override
	public List<PersonVO> listPersonInfoPg(PersonForm personForm) {
		return personMapper.listPersonInfoPg(personForm);
	}

	@Override
	public int listPersonCount(PersonForm personForm) {
		return personMapper.listPersonCount(personForm);
	}

	@Override
	public PersonVO getPersonInfo(String no_person) {
		return personMapper.getPersonInfo(no_person);
	}

	@Override
	public ReturnClass modifyYnUse(PersonVO personVO) {
		CodeInfo codeInfo = codeManager.getCodeInfo("cd_info" , "98");

		List<CodeInfo> person_field = new ArrayList<CodeInfo>();
		person_field.add(codeInfo);
		personVO.setFields(person_field);

		//기존정보 가져오기
		HashMap<String, String> personExist = personMapper.getPersonInfoMap(personVO);

		//사용여부 수정
		if(1 != personMapper.modifyYnUse(personVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}

		//개인정보수정이력 insert
		personVO.setId_frt(personVO.getId_lst());
		insertPersonInfoHist(person_field, personExist, personVO);

		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	@Override
	public PersonVO getPersonInfoAgency(String no_person) {
		return personMapper.getPersonInfoAgency(no_person);
	}

	@Override
	public ReturnClass procPerson(PersonVO personVO) {

		// 주민번호로 BGN 생성
		if(StringUtil.isNotEmpty(personVO.getSsn_person()))
		{
			personVO.setBgn(StringUtil.getBgn(personVO.getSsn_person()));
		}
		// BGN으로 주민번호 생성
		else if(StringUtil.isNotEmpty(personVO.getBgn()))
		{
			String bgn = personVO.getBgn();
			personVO.setSsn_person(bgn.substring(2));
		}

		// 등본주소 동일 체크시
		if ( "Y".equals(personVO.getYn_addr_equal()) ) {
			personVO.setCd_addr_reg(personVO.getCd_addr_home());
			personVO.setPost_reg(personVO.getPost_home());
			personVO.setCd_status_addr_reg(personVO.getCd_status_addr_home());
			personVO.setAddr1_reg(personVO.getAddr1_home());
			personVO.setAddr2_reg(personVO.getAddr2_home());
			personVO.setCd_house_type_reg(personVO.getCd_house_type_home());
			personVO.setYm_house_reg(personVO.getYm_house_home());
			personVO.setCd_live_type_reg(personVO.getCd_live_type_home());
			personVO.setAmt_rent_deposit_reg(personVO.getAmt_rent_deposit_home());
			personVO.setAmt_rent_monthly_reg(personVO.getAmt_rent_monthly_home());
			personVO.setNm_owner_reg(personVO.getNm_owner_home());
			personVO.setRel_owner_reg(personVO.getRel_owner_home());
		}


		// 휴대폰 형태에 맞게 다시 세팅
		if(!StringUtil.isCellphone(personVO.getHp()))
			personVO.setHp(StringUtil.formatTelNo(personVO.getHp()));

		if(StringUtil.isEmpty(personVO.getNo_person())) {
			if(StringUtil.isEmpty(personVO.getYn_agency()))
				personMapper.createPerson(personVO);
			else personMapper.createPersonAgency(personVO);

		} else {

			List<CodeInfo> person_field =  codeManager.listCodeInfo("cd_info");
			personVO.setFields(person_field);

			//기존정보 가져오기
			HashMap<String, String> personExist = personMapper.getPersonInfoMap(personVO);

			//새로운정보 업데이트
			if(1 != personMapper.modifyPerson(personVO)) {
				return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
			}

			if(StringUtil.isEmpty(personVO.getYn_agency())){
				//개인정보수정이력 insert
				insertPersonInfoHist(person_field, personExist, personVO);
			}
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.", (Object) personVO.getNo_person());
	}

	@Override

	public ReturnClass procPersonAgency(PersonVO personVO) {

		//기존고객 정보체크
		if(StringUtil.isEmpty(personVO.getId_frt()))
			personVO.setId_frt("SYSTEM");

		PersonVO existPersonVO = personMapper.getExistPersonInfo(personVO);

		if(null != existPersonVO){
			personVO.setNo_person(existPersonVO.getNo_person());
		}else personVO.setNo_person("");

		// 주민번호로 BGN 생성
		if(StringUtil.isNotEmpty(personVO.getSsn_person()))
		{
			personVO.setBgn(StringUtil.getBgn(personVO.getSsn_person()));
		}
		// BGN으로 주민번호 생성
		else if(StringUtil.isNotEmpty(personVO.getBgn()))
		{
			String bgn = personVO.getBgn();
			personVO.setSsn_person(bgn.substring(2));
		}

		// 등본주소 동일 체크시
		/*if ( "Y".equals(personVO.getYn_addr_equal()) ) {
			personVO.setCd_addr_reg(personVO.getCd_addr_home());
			personVO.setPost_reg(personVO.getPost_home());
			personVO.setCd_status_addr_reg(personVO.getCd_status_addr_home());
			personVO.setAddr1_reg(personVO.getAddr1_home());
			personVO.setAddr2_reg(personVO.getAddr2_home());
			personVO.setCd_house_type_reg(personVO.getCd_house_type_home());
			personVO.setYm_house_reg(personVO.getYm_house_home());
			personVO.setCd_live_type_reg(personVO.getCd_live_type_home());
			personVO.setAmt_rent_deposit_reg(personVO.getAmt_rent_deposit_home());
			personVO.setAmt_rent_monthly_reg(personVO.getAmt_rent_monthly_home());
			personVO.setNm_owner_reg(personVO.getNm_owner_home());
			personVO.setRel_owner_reg(personVO.getRel_owner_home());
		}*/


		// 휴대폰 형태에 맞게 다시 세팅
		if(!StringUtil.isCellphone(personVO.getHp()))
			personVO.setHp(StringUtil.formatTelNo(personVO.getHp()));

		if(StringUtil.isEmpty(personVO.getNo_person())) {
			personMapper.createPerson(personVO);
		} else {

			List<CodeInfo> person_field =  codeManager.listCodeInfo("cd_info");
			personVO.setFields(person_field);

			//기존정보 가져오기
			HashMap<String, String> personExist = personMapper.getPersonInfoMap(personVO);

			//새정보가 빈값이면 기존정보 셋팅
			BeanWrapper requestWrapper = new BeanWrapperImpl(personVO);

			for ( CodeInfo codeInfo :  person_field) {

				String[] splitList = codeInfo.getNm_code().split(" ");
				String key = splitList[1].trim();

				String newInfo = (String) requestWrapper.getPropertyValue(key);

				if(StringUtil.isEmpty(newInfo) || "".equals(newInfo)){
					String Existkey = key.toUpperCase();

					if(null != personExist.get(Existkey)){
						requestWrapper.setPropertyValue(key ,String.valueOf(personExist.get(Existkey)));
					}
				}
			}

			//새로운정보 업데이트
			if(1 != personMapper.modifyPerson(personVO)) {
				return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
			}

			//개인정보수정이력 insert
			insertPersonInfoHist(person_field, personExist, personVO);
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.", (Object) personVO.getNo_person());
	}

	//개인정보수정이력 insert
	private void insertPersonInfoHist(List<CodeInfo> person_field, HashMap<String, String> personExist, PersonVO personVO) {

		//새로운정보 가져오기
		HashMap<String, String> personNew = personMapper.getPersonInfoMap(personVO);

		PersonInfoHistVO personInfoHistVO =  new PersonInfoHistVO();

		for ( CodeInfo codeInfo :  person_field) {

			String[] splitList = codeInfo.getNm_code().split(" ");
			String key = splitList[1].trim().toUpperCase();
			String existInfo = "";
			String newInfo = "";

			if(personExist != null && personExist.get(key) != null ){
				existInfo = String.valueOf(personExist.get(key));
			}
			if(null != personNew.get(key)){
				newInfo = String.valueOf(personNew.get(key));
			}

			if(!existInfo.equals(newInfo)){

				PersonInfoHistVO setPersonHist = new PersonInfoHistVO();

				String no_person = personVO.getNo_person().toString();

				setPersonHist = personMapper.getPersonInfoHist(no_person);
				personInfoHistVO.setId_frt(personVO.getId_frt());
				personInfoHistVO.setId_lst(personVO.getId_frt());
				//personInfoHistVO.setNo_person(personVO.getNo_person());
				//personInfoHistVO.setId_frt(personVO.getId_frt());
				//personInfoHistVO.setCd_info(codeInfo.getCode_value());
				//personInfoHistVO.setBefore(existInfo);
				//personInfoHistVO.setAfter(newInfo);

				personMapper.insertPersonInfoHist(setPersonHist);
			}
		}

	}

	@Override
	public List<PersonVO> listPersonInfo(PersonForm personForm) {

		/*
		 * 선택된 고객번호가 있으며,
		 * 데프콘코드가 05 미만일때 핸드폰번호가 화면에서 넘어오지 않으므로 DB값으로 조회하여 세팅
		 */
		if( StringUtil.isNotEmpty(personForm.getNo_person())
				&& "05".compareTo(codeManager.getNvlCodeName("_CONF_SYSTEM", "CD_DEFCON", "05")) > 0 )
		{
			PersonVO personVO = getPersonInfo(personForm.getNo_person());
			personForm.setHp(personVO.getHp());
		}

		List<PersonVO> listAll = personMapper.listPersonInfo(personForm);
		// 검색핸드폰값과 일치하는 목록을 상단으로 나타내기 위해 sort 값 주임
		for (PersonVO personVO : listAll) {
			if( StringUtil.isNotEmpty(personVO.getHp())
				&& personVO.getHp().contains(personForm.getHp()) ) {
				personVO.setStr_sort("1");
			} else {
				personVO.setStr_sort("9");
			}
		}

		// sort 값으로 정렬
		Comparator<PersonVO> comparator = new Comparator<PersonVO>() {
			@Override
			public int compare(PersonVO o1, PersonVO o2) {
				return o1.getStr_sort().compareTo(o2.getStr_sort());
			}
		};
		Collections.sort(listAll, comparator);

		return listAll;
	}

	@Override
	public HashMap<String, List<PersonVO>> listPersonRel(PersonVO personVO) {
		HashMap<String, List<PersonVO>> map = new HashMap<String, List<PersonVO>>();

		// 이름조회
		if ( !StringUtil.isEmpty(personVO.getNm_person()) ) {
			personVO.setField_name("PI.nm_person");
			personVO.setField_value(personVO.getNm_person());

			List<PersonVO> nmList = personMapper.listPersonRel(personVO);
			for(Iterator<PersonVO> it = nmList.iterator() ; it.hasNext() ; ){
				PersonVO info = it.next();
				// 동일인물 제외
				if(info.getNo_person().equals(personVO.getNo_person())){
					it.remove();
				}
			}
			map.put("NM", nmList);
		}

		if(StringUtil.isNotEmpty(personVO.getSsn_person()))
		{
			personVO.setBgn(StringUtil.getBgn(personVO.getSsn_person()));
		}
		// 생년월일
		if ( !StringUtil.isEmpty(personVO.getBgn()) ) {
			personVO.setField_name("PI.bgn");
			personVO.setField_value(personVO.getBgn());

			List<PersonVO> bgnList = personMapper.listPersonRel(personVO);
			for(Iterator<PersonVO> it = bgnList.iterator() ; it.hasNext() ; ){
				PersonVO info = it.next();
				// 동일인물 제외
				if(info.getNo_person().equals(personVO.getNo_person())){
					it.remove();
				}
			}
			map.put("BGN", bgnList);
		}

		/*
		 * 선택된 고객번호가 있으며,
		 * 데프콘코드가 05 미만일때 핸드폰번호가 화면에서 넘어오지 않으므로 DB값으로 조회하여 세팅
		 */
		if( StringUtil.isNotEmpty(personVO.getNo_person())
				&& "05".compareTo(codeManager.getNvlCodeName("_CONF_SYSTEM", "CD_DEFCON", "05")) > 0 )
		{
			PersonVO personTmp = getPersonInfo(personVO.getNo_person());
			personVO.setHp(personTmp.getHp());
		}

		// 휴대폰
		if ( !StringUtil.isEmpty(personVO.getHp()) ) {
			personVO.setField_name("PI.hp");
			personVO.setField_value(personVO.getHp());

			List<PersonVO> hpList = personMapper.listPersonRel(personVO);
			for(Iterator<PersonVO> it = hpList.iterator() ; it.hasNext() ; ){
				PersonVO info = it.next();
				// 동일인물 제외
				if(info.getNo_person().equals(personVO.getNo_person())){
					it.remove();
				}
			}
			map.put("HP", hpList);
		}

		return map;
	}

	@Override
	public ReturnClass modifyHp(PersonVO personVO) {

		if(StringUtil.isEmpty(personVO.getNo_person())) {
			return new ReturnClass(Constant.FAILED,"고객번호가 존재하지 않습니다.");
		}

		CodeInfo codeInfo = codeManager.getCodeInfo("cd_info" , "03");

		List<CodeInfo> person_field = new ArrayList<CodeInfo>();
		person_field.add(codeInfo);
		personVO.setFields(person_field);

		//기존정보 가져오기
		HashMap<String, String> personExist = personMapper.getPersonInfoMap(personVO);

		//새로운정보 업데이트
		if(1 != personMapper.modifyHp(personVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}

		//개인정보수정이력 insert
		personVO.setId_frt(personVO.getId_lst());
		insertPersonInfoHist(person_field, personExist, personVO);

		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyBgnNmAndHp(PersonVO personVO) {

		if(StringUtil.isEmpty(personVO.getNo_person())) {
			return new ReturnClass(Constant.FAILED,"고객번호가 존재하지 않습니다.");
		}

		CodeInfo codeInfo = codeManager.getCodeInfo("cd_info" , "03");

		List<CodeInfo> person_field = new ArrayList<CodeInfo>();
		person_field.add(codeInfo);
		personVO.setFields(person_field);

		//기존정보 가져오기
		HashMap<String, String> personExist = personMapper.getPersonInfoMap(personVO);

		//새로운정보 업데이트
		if(1 != personMapper.modifyBgnNmAndHp(personVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}

		logger.debug(personVO.getNo_person());
		logger.debug(personVO.getNo_person());
		logger.debug(personVO.getNo_person());
		logger.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		logger.debug("person_field:"+ person_field);
		logger.debug("personExist:" + personExist);
		logger.debug("personVO:"+ personVO);
		logger.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		//개인정보수정이력 insert
		personVO.setId_frt(personVO.getNo_person());
		insertPersonInfoHist(person_field, personExist, personVO);

		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	@Override
	public List<HashMap<String, Object>> listPersonInfoPg_excel(PersonForm personForm) {
		return personMapper.listPersonInfoPg_excel(personForm);
	}

	@Override
	public ReturnClass viewPersonnal(PersonalViewHist personalViewHist) {

		String cd_defcon = codeManager.getNvlCodeName("_CONF_SYSTEM", "CD_DEFCON", "05");

		if ("03".equals(cd_defcon) )
		{
			// 사용자 ID 로 핸드폰 번호 조회
			WorkerVO workerVO = workerManager.getCacheWorkerInfo(personalViewHist.getId_frt());

			if(StringUtil.isEmpty(workerVO.getHp())) {
				return new ReturnClass(Constant.FAILED,"사용자 정보수정을 통해 휴대폰번호를 입력하여 주세요.");
			}
			if(StringUtil.isEmpty(workerVO.getExt_emp_direct())) {
				return new ReturnClass(Constant.FAILED,"사용자 정보수정을 통해 직통번호를 입력하여 주세요.");
			}

			// 고객번호로 정보 조회
			PersonVO personVO = getPersonInfo(personalViewHist.getNo_person());
			if(personVO == null) {
				return new ReturnClass(Constant.FAILED,"고객정보가 존재하지 않습니다.");
			}

			// 홍X동 님의 휴대전화번호는 0000 입니다.
			StringBuffer msg = new StringBuffer();
			msg.append( personVO.getNm_person().substring(0, 1) );
			msg.append("X");
			msg.append( personVO.getNm_person().substring(2) );
			msg.append(" 님의 번호는 ");
			msg.append( personVO.getHp_idx2() );
			msg.append(" 입니다");

		}
		else if ("04".equals(cd_defcon) )
		{
			// 팝업에 정보를 볼수 있기때문에 오픈한 이력을 저장.
			personMapper.insertPersonalViewHist(personalViewHist);
		}

		return new ReturnClass(Constant.SUCCESS,"정상처리 되었습니다.");
	}

	@Override
	public List<PersonInfoHistVO> listPersonInfoHist(PersonForm personForm) {
		return personMapper.listPersonInfoHist(personForm);

	}

	@Override
	public int listPersonInfoCount(PersonForm personForm) {
		return personMapper.listPersonInfoCount(personForm);
	}

	@Override
	public int listExistPersonCount(PersonForm personForm) {
		return personMapper.listExistPersonCount(personForm);
	}

	@Override
	public int getSamePersonCount(PersonVO personVO) {
		return personMapper.getSamePersonCount(personVO);
	}

	@Override
	public String getIdPrepare(PersonForm personForm) {
		return personMapper.getIdPrepare(personForm);
	}

	@Override
	public PersonVO getExistPersonInfo(PersonVO personVO) {
		return personMapper.getExistPersonInfo(personVO);
	}

	@Override
	public PersonVO getPersonInfoHp(String number) {
		return personMapper.getPersonInfoHp(number);
	}

	@Override
	@Cacheable(value="CachePerson", key="#id_emp")
	public PersonVO getCachePersonInfo(String no_person) {
		return personMapper.getPersonInfo(no_person);
	}

	@Override
	public String getPersonInfo(String no_person, String type) {
//		personManager = (PersonManager) SpringApplicationContext.getBean("personManager");
		PersonVO personVO = getCachePersonInfo(no_person);
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
	public String getExistNiceLast6MonthGrade(String no_person, int grade) {
		PersonNice12MonthCbScoreInfo personNice12MonthCbScoreInfo = new PersonNice12MonthCbScoreInfo();
		personNice12MonthCbScoreInfo.setNo_person(no_person);
		personNice12MonthCbScoreInfo.setGrade(grade);
		String result = personMapper.getExistNiceLast6MonthGrade(personNice12MonthCbScoreInfo);
		if(result == null)	//Z는 등록이 덜되거나 안된거, Y는 해당등급보다 낮은게 존재함, N은 OK 6개월간 낮은 신용등급없었음
			result = "Z";
		return result;
	}
	@Override
	public ReturnClass procPersonNice(PersonNiceVO personNiceVO) {
		if(1 != personMapper.procPersonNice(personNiceVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.", (Object) personNiceVO.getNo_person());
	}
	@Override
	public ReturnClass procPersonKcb(PersonKcbVO personKcbVO) {
		personKcbVO.setNo_person(personKcbVO.getNo_person());
		personKcbVO.setScore_cb(personKcbVO.getScore_cb());

		if(1 != personMapper.procPersonKcb(personKcbVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.", (Object) personKcbVO.getNo_person());
	}
	public PersonNiceVO getPersonNiceInfo(String no_person) {
		return personMapper.getPersonNiceInfo(no_person);
	}
	public PersonKcbVO getPersonKcbInfo(String no_person) {
		return personMapper.getPersonKcbInfo(no_person);
	}
	@Override
	public List<PersonNiceLoanVO> listPersonNiceLoanAmtSumByCdFin(String no_person) {
		List<PersonNiceLoanVO> listAll = personMapper.listPersonNiceLoanAmtSumByCdFin(no_person);
		return listAll;
	}
	@Override
	public PersonNiceLoanAnalysisVO getPersonNiceLoanAnalysis(String no_person) {
		return personMapper.getPersonNiceLoanAnalysis(no_person);
	}
	@Override
	public PersonNiceLoanVO getPersonNiceLoanAmtSum(String no_person) {
		return personMapper.getPersonNiceLoanAmtSum(no_person);
	}
	@Override
	public PersonNiceLoanVO getPersonNiceAmtPayMonthSum(String no_person) {
		return personMapper.getPersonNiceAmtPayMonthSum(no_person);
	}
	@Override
	public PersonNiceLoanVO getPersonNiceLoanInfo(PersonForm personForm) {
		return personMapper.getPersonNiceLoanInfo(personForm);
	}


	@Override
	public PersonNiceCashServiceVO getPersonNiceCashServiceInfo(PersonForm personForm) {
		return personMapper.getPersonNiceCashServiceInfo(personForm);
	}
	@Override
	public PersonNiceDebtGuaranteeVO getPersonNiceDebtGuaranteeInfo(PersonForm personForm) {
		return personMapper.getPersonNiceDebtGuaranteeInfo(personForm);
	}
	@Override
	public PersonNiceDebtAdjustmentVO getPersonNiceDebtAdjustmentInfo(PersonForm personForm) {
		return personMapper.getPersonNiceDebtAdjustmentInfo(personForm);
	}
	@Override
	public PersonNiceDefaultBankVO getPersonNiceDefaultBankInfo(PersonForm personForm) {
		return personMapper.getPersonNiceDefaultBankInfo(personForm);
	}
	@Override
	public PersonNiceDefaultNiceVO getPersonNiceDefaultNiceInfo(PersonForm personForm) {
		return personMapper.getPersonNiceDefaultNiceInfo(personForm);
	}
	@Override
	public PersonNiceDelayNiceVO getPersonNiceDelayNiceInfo(PersonForm personForm) {
		return personMapper.getPersonNiceDelayNiceInfo(personForm);
	}
	@Override
	public String[] formPersonNiceLastMonth(int cntLastMonth) {
		String[] strArryLast12Month = new String[cntLastMonth];
		int z = 0;
		for (int i = -1; i >= -cntLastMonth; i--) {
			strArryLast12Month[z] = DateUtil.addMonths(DateUtil.getCurrentDateTime("yyyyMMdd"), i).substring(0, 6);
			z++;
		}
		return strArryLast12Month;
	}

	@Override
	public ReturnClass procPersonNice12Month(PersonNice12MonthCbScoreVO personNice12MonthCbScoreVO) {
		for (int i = 0; i < 12; i++) {
			PersonNice12MonthCbScoreVO temp = new PersonNice12MonthCbScoreVO();
			temp.setNo_person(personNice12MonthCbScoreVO.getNo_person());
			temp.setId_frt(personNice12MonthCbScoreVO.getId_frt());
			temp.setId_lst(personNice12MonthCbScoreVO.getId_lst());
			if(i == 0){
				temp.setStd_month(personNice12MonthCbScoreVO.getStd_month_0());
				temp.setAvg_point(personNice12MonthCbScoreVO.getAvg_point_0());
				temp.setGrade(personNice12MonthCbScoreVO.getGrade_0());
				personMapper.procPersonNice12Month(temp);
			}else if(i == 1){
				temp.setStd_month(personNice12MonthCbScoreVO.getStd_month_1());
				temp.setAvg_point(personNice12MonthCbScoreVO.getAvg_point_1());
				temp.setGrade(personNice12MonthCbScoreVO.getGrade_1());
				personMapper.procPersonNice12Month(temp);
			}else if(i == 2){
				temp.setStd_month(personNice12MonthCbScoreVO.getStd_month_2());
				temp.setAvg_point(personNice12MonthCbScoreVO.getAvg_point_2());
				temp.setGrade(personNice12MonthCbScoreVO.getGrade_2());
				personMapper.procPersonNice12Month(temp);
			}else if(i == 3){
				temp.setStd_month(personNice12MonthCbScoreVO.getStd_month_3());
				temp.setAvg_point(personNice12MonthCbScoreVO.getAvg_point_3());
				temp.setGrade(personNice12MonthCbScoreVO.getGrade_3());
				personMapper.procPersonNice12Month(temp);
			}else if(i == 4){
				temp.setStd_month(personNice12MonthCbScoreVO.getStd_month_4());
				temp.setAvg_point(personNice12MonthCbScoreVO.getAvg_point_4());
				temp.setGrade(personNice12MonthCbScoreVO.getGrade_4());
				personMapper.procPersonNice12Month(temp);
			}else if(i == 5){
				temp.setStd_month(personNice12MonthCbScoreVO.getStd_month_5());
				temp.setAvg_point(personNice12MonthCbScoreVO.getAvg_point_5());
				temp.setGrade(personNice12MonthCbScoreVO.getGrade_5());
				personMapper.procPersonNice12Month(temp);
			}else if(i == 6){
				temp.setStd_month(personNice12MonthCbScoreVO.getStd_month_6());
				temp.setAvg_point(personNice12MonthCbScoreVO.getAvg_point_6());
				temp.setGrade(personNice12MonthCbScoreVO.getGrade_6());
				personMapper.procPersonNice12Month(temp);
			}else if(i == 7){
				temp.setStd_month(personNice12MonthCbScoreVO.getStd_month_7());
				temp.setAvg_point(personNice12MonthCbScoreVO.getAvg_point_7());
				temp.setGrade(personNice12MonthCbScoreVO.getGrade_7());
				personMapper.procPersonNice12Month(temp);
			}else if(i == 8){
				temp.setStd_month(personNice12MonthCbScoreVO.getStd_month_8());
				temp.setAvg_point(personNice12MonthCbScoreVO.getAvg_point_8());
				temp.setGrade(personNice12MonthCbScoreVO.getGrade_8());
				personMapper.procPersonNice12Month(temp);
			}else if(i == 9){
				temp.setStd_month(personNice12MonthCbScoreVO.getStd_month_9());
				temp.setAvg_point(personNice12MonthCbScoreVO.getAvg_point_9());
				temp.setGrade(personNice12MonthCbScoreVO.getGrade_9());
				personMapper.procPersonNice12Month(temp);
			}else if(i == 10){
				temp.setStd_month(personNice12MonthCbScoreVO.getStd_month_10());
				temp.setAvg_point(personNice12MonthCbScoreVO.getAvg_point_10());
				temp.setGrade(personNice12MonthCbScoreVO.getGrade_10());
				personMapper.procPersonNice12Month(temp);
			}else if(i == 11){
				temp.setStd_month(personNice12MonthCbScoreVO.getStd_month_11());
				temp.setAvg_point(personNice12MonthCbScoreVO.getAvg_point_11());
				temp.setGrade(personNice12MonthCbScoreVO.getGrade_11());
				personMapper.procPersonNice12Month(temp);
			}
		}

		logger.debug(personNice12MonthCbScoreVO.getNo_person());
		logger.debug("personNice12MonthCbScoreVO.getStd_month_8():" + personNice12MonthCbScoreVO.getStd_month_8());
		logger.debug("personNice12MonthCbScoreVO.getGrade_11():" + personNice12MonthCbScoreVO.getGrade_11());
		logger.debug("personNice12MonthCbScoreVO.getAvg_point_5():" + personNice12MonthCbScoreVO.getAvg_point_5());
//		if(1 != personMapper.procPersonNice12Month(personNice12MonthCbScoreVO)) {
//			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
//		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.", (Object) personNice12MonthCbScoreVO.getNo_person());
	}
	@Override
	public List<PersonNice12MonthCbScoreVO> getPersonNice12Month(String no_person) {
		List<PersonNice12MonthCbScoreVO> list = personMapper.getPersonNice12Month(no_person);
		return list;
	}
	@Override
	public String[] formPersonKcb12Month(String no_person) {
		String[] strArryLast12Month = new String[12];
		int z = 0;
		for (int i = -1; i >= -12; i--) {
			strArryLast12Month[z] = DateUtil.addMonths(DateUtil.getCurrentDateTime("yyyyMMdd"), i).substring(0, 6);
			z++;
		}
		return strArryLast12Month;
	}

	@Override
	public ReturnClass procPersonKcb12Month(PersonKcb12MonthCbScoreVO personKcb12MonthCbScoreVO) {
		for (int i = 0; i < 12; i++) {
			PersonKcb12MonthCbScoreVO temp = new PersonKcb12MonthCbScoreVO();
			temp.setNo_person(personKcb12MonthCbScoreVO.getNo_person());
			temp.setId_frt(personKcb12MonthCbScoreVO.getId_frt());
			temp.setId_lst(personKcb12MonthCbScoreVO.getId_lst());
			if(i == 0){
				temp.setStd_month(personKcb12MonthCbScoreVO.getStd_month_0());
				temp.setAvg_point(personKcb12MonthCbScoreVO.getAvg_point_0());
				temp.setGrade(personKcb12MonthCbScoreVO.getGrade_0());
				personMapper.procPersonKcb12Month(temp);
			}else if(i == 1){
				temp.setStd_month(personKcb12MonthCbScoreVO.getStd_month_1());
				temp.setAvg_point(personKcb12MonthCbScoreVO.getAvg_point_1());
				temp.setGrade(personKcb12MonthCbScoreVO.getGrade_1());
				personMapper.procPersonKcb12Month(temp);
			}else if(i == 2){
				temp.setStd_month(personKcb12MonthCbScoreVO.getStd_month_2());
				temp.setAvg_point(personKcb12MonthCbScoreVO.getAvg_point_2());
				temp.setGrade(personKcb12MonthCbScoreVO.getGrade_2());
				personMapper.procPersonKcb12Month(temp);
			}else if(i == 3){
				temp.setStd_month(personKcb12MonthCbScoreVO.getStd_month_3());
				temp.setAvg_point(personKcb12MonthCbScoreVO.getAvg_point_3());
				temp.setGrade(personKcb12MonthCbScoreVO.getGrade_3());
				personMapper.procPersonKcb12Month(temp);
			}else if(i == 4){
				temp.setStd_month(personKcb12MonthCbScoreVO.getStd_month_4());
				temp.setAvg_point(personKcb12MonthCbScoreVO.getAvg_point_4());
				temp.setGrade(personKcb12MonthCbScoreVO.getGrade_4());
				personMapper.procPersonKcb12Month(temp);
			}else if(i == 5){
				temp.setStd_month(personKcb12MonthCbScoreVO.getStd_month_5());
				temp.setAvg_point(personKcb12MonthCbScoreVO.getAvg_point_5());
				temp.setGrade(personKcb12MonthCbScoreVO.getGrade_5());
				personMapper.procPersonKcb12Month(temp);
			}else if(i == 6){
				temp.setStd_month(personKcb12MonthCbScoreVO.getStd_month_6());
				temp.setAvg_point(personKcb12MonthCbScoreVO.getAvg_point_6());
				temp.setGrade(personKcb12MonthCbScoreVO.getGrade_6());
				personMapper.procPersonKcb12Month(temp);
			}else if(i == 7){
				temp.setStd_month(personKcb12MonthCbScoreVO.getStd_month_7());
				temp.setAvg_point(personKcb12MonthCbScoreVO.getAvg_point_7());
				temp.setGrade(personKcb12MonthCbScoreVO.getGrade_7());
				personMapper.procPersonKcb12Month(temp);
			}else if(i == 8){
				temp.setStd_month(personKcb12MonthCbScoreVO.getStd_month_8());
				temp.setAvg_point(personKcb12MonthCbScoreVO.getAvg_point_8());
				temp.setGrade(personKcb12MonthCbScoreVO.getGrade_8());
				personMapper.procPersonKcb12Month(temp);
			}else if(i == 9){
				temp.setStd_month(personKcb12MonthCbScoreVO.getStd_month_9());
				temp.setAvg_point(personKcb12MonthCbScoreVO.getAvg_point_9());
				temp.setGrade(personKcb12MonthCbScoreVO.getGrade_9());
				personMapper.procPersonKcb12Month(temp);
			}else if(i == 10){
				temp.setStd_month(personKcb12MonthCbScoreVO.getStd_month_10());
				temp.setAvg_point(personKcb12MonthCbScoreVO.getAvg_point_10());
				temp.setGrade(personKcb12MonthCbScoreVO.getGrade_10());
				personMapper.procPersonKcb12Month(temp);
			}else if(i == 11){
				temp.setStd_month(personKcb12MonthCbScoreVO.getStd_month_11());
				temp.setAvg_point(personKcb12MonthCbScoreVO.getAvg_point_11());
				temp.setGrade(personKcb12MonthCbScoreVO.getGrade_11());
				personMapper.procPersonKcb12Month(temp);
			}
		}

		logger.debug(personKcb12MonthCbScoreVO.getNo_person());
		logger.debug("personKcb12MonthCbScoreVO.getStd_month_8():" + personKcb12MonthCbScoreVO.getStd_month_8());
		logger.debug("personKcb12MonthCbScoreVO.getGrade_11():" + personKcb12MonthCbScoreVO.getGrade_11());
		logger.debug("personKcb12MonthCbScoreVO.getAvg_point_5():" + personKcb12MonthCbScoreVO.getAvg_point_5());
//		if(1 != personMapper.procPersonKcb12Month(personKcb12MonthCbScoreVO)) {
//			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
//		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.", (Object) personKcb12MonthCbScoreVO.getNo_person());
	}
	public List<PersonKcb12MonthCbScoreVO> getPersonKcb12Month(String no_person) {
		List<PersonKcb12MonthCbScoreVO> list = personMapper.getPersonKcb12Month(no_person);
		return list;
	}
	@Override
	public List<PersonAgreeHistVO> listPersonAgreeHistInfoPg(PersonForm personForm) {
		return personMapper.listPersonAgreeHistInfoPg(personForm);
	}
	@Override
	public ReturnClass procPersonAgreeHist(PersonAgreeHistVO personAgreeHistVO) {
		personMapper.createPersonAgreeHist(personAgreeHistVO);
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}
	@Override
	public int listPersonAgreeHistCount(PersonForm personForm) {
		return personMapper.listPersonAgreeHistCount(personForm);
	}
	@Override
	public List<PersonQuitVO> listPersonQuitInfoPg(PersonForm personForm) {
		return personMapper.listPersonQuitInfoPg(personForm);
	}

	@Override
	public int listPersonQuitCount(PersonForm personForm) {
		return personMapper.listPersonQuitCount(personForm);
	}

	@Override
	public ReturnClass procPersonQuit(String no_person) {
		PersonVO personVO = new PersonVO();
		personVO = personMapper.getPersonInfo(no_person);
		logger.info(personVO.toString());
		personMapper.createPersonQuit(personVO);
		personVO.setId_lst(no_person);
		personMapper.procPersonQuit(no_person);
		return new ReturnClass(Constant.SUCCESS,"탈퇴가 완료되었습니다.");
	}
	@Override
	public ReturnClass procPersonReUse(String no_person) {
		personMapper.procPersonReUse(no_person);
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}
	@Override
	public ReturnClass modifyPersonSafeKey(PersonVO personVO) {
		personMapper.modifyPersonSafeKey(personVO);
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	@Override
	public List<PersonNiceLoanVO> listPersonNiceLoan(String no_person) {
		List<PersonNiceLoanVO> listAll = personMapper.listPersonNiceLoan(no_person);
		return listAll;
	}
	@Override
	public List<PersonNiceCashServiceVO> listPersonNiceCashService(String no_person){
		List<PersonNiceCashServiceVO> listAll = personMapper.listPersonNiceCashService(no_person);
		return listAll;
	}
	@Override
	public List<PersonNiceDebtGuaranteeVO> listPersonNiceDebtGuarantee(String no_person){
		List<PersonNiceDebtGuaranteeVO> listAll = personMapper.listPersonNiceDebtGuarantee (no_person);
		return listAll;
	}
	@Override
	public List<PersonNiceDebtAdjustmentVO> listPersonNiceDebtAdjustment(String no_person){
		List<PersonNiceDebtAdjustmentVO> listAll = personMapper.listPersonNiceDebtAdjustment(no_person);
		return listAll;
	}
	@Override
	public List<PersonNiceDefaultBankVO> listPersonNiceDefaultBank(String no_person){
		List<PersonNiceDefaultBankVO> listAll = personMapper.listPersonNiceDefaultBank(no_person);
		return listAll;
	}
	@Override
	public List<PersonNiceDefaultNiceVO> listPersonNiceDefaultNice(String no_person) {
		List<PersonNiceDefaultNiceVO> listAll = personMapper.listPersonNiceDefaultNice(no_person);
		return listAll;
	}
	@Override
	public List<PersonNiceDelayNiceVO> listPersonNiceDelayNice(String no_person){
		List<PersonNiceDelayNiceVO> listAll = personMapper.listPersonNiceDelayNice(no_person);
		return listAll;
	}


	@Override
	public ReturnClass procPersonNiceLoan(PersonNiceLoanVO personNiceLoanVO) {
		personNiceLoanVO.setNo_person(personNiceLoanVO.getNo_person());

		if(1 != personMapper.procPersonNiceLoan(personNiceLoanVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.", (Object) personNiceLoanVO.getNo_person());
	}

	public ReturnClass procPersonNiceCashService(PersonNiceCashServiceVO personNiceCashServiceVO) {
		personNiceCashServiceVO.setNo_person(personNiceCashServiceVO.getNo_person());
		if(1 != personMapper.procPersonNiceCashService(personNiceCashServiceVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.",(Object) personNiceCashServiceVO.getNo_person());

	}
	public ReturnClass procPersonNiceDebtGuarantee(PersonNiceDebtGuaranteeVO personNiceDebtGuaranteeVO) {
		personNiceDebtGuaranteeVO.setNo_person(personNiceDebtGuaranteeVO.getNo_person());
		if(1 != personMapper.procPersonNiceDebtGuarantee(personNiceDebtGuaranteeVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.",(Object) personNiceDebtGuaranteeVO.getNo_person());
	}
	public ReturnClass procPersonNiceDebtAdjustment(PersonNiceDebtAdjustmentVO personNiceDebtAdjustmentVO) {
		personNiceDebtAdjustmentVO.setNo_person(personNiceDebtAdjustmentVO.getNo_person());
		if(1 != personMapper.procPersonNiceDebtAdjustment(personNiceDebtAdjustmentVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.",(Object) personNiceDebtAdjustmentVO.getNo_person());
	}
	public ReturnClass procPersonNiceDefaultBank(PersonNiceDefaultBankVO personNiceDefaultBankVO) {
		personNiceDefaultBankVO.setNo_person(personNiceDefaultBankVO.getNo_person());
		if(1 != personMapper.procPersonNiceDefaultBank(personNiceDefaultBankVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.",(Object) personNiceDefaultBankVO.getNo_person());
	}
	public ReturnClass procPersonNiceDefaultNice(PersonNiceDefaultNiceVO personNiceDefaultNiceVO) {
		personNiceDefaultNiceVO.setNo_person(personNiceDefaultNiceVO.getNo_person());
		if(1 != personMapper.procPersonNiceDefaultNice(personNiceDefaultNiceVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.",(Object) personNiceDefaultNiceVO.getNo_person());
	}
	public ReturnClass procPersonNiceDelayNice(PersonNiceDelayNiceVO personNiceDelayNiceVO) {
		personNiceDelayNiceVO.setNo_person(personNiceDelayNiceVO.getNo_person());
		if(1 != personMapper.procPersonNiceDelayNice(personNiceDelayNiceVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.",(Object) personNiceDelayNiceVO.getNo_person());
	}

	@Override
	public ReturnClass delNiceLoan(String no_niceloan) {
		if(1 != personMapper.delNiceLoan(no_niceloan)){
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.");
	}

	public ReturnClass delNiceCashService(String no_nice_cash_service   ) {
		if(1 != personMapper.delNiceCashService(no_nice_cash_service   )){
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.");
	}
	public ReturnClass delNiceDebtGuarantee(String no_nice_debt_guarantee ) {
		if(1 != personMapper.delNiceDebtGuarantee(no_nice_debt_guarantee )){
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.");
	}
	public ReturnClass delNiceDebtAdjustment(String no_nice_debt_adjustment) {
		if(1 != personMapper.delNiceDebtAdjustment(no_nice_debt_adjustment)){
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.");
	}
	public ReturnClass delNiceDefaultBank(String no_nice_default_bank   ) {
		if(1 != personMapper.delNiceDefaultBank(no_nice_default_bank   )){
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.");
	}
	public ReturnClass delNiceDefaultNice(String no_nice_default_nice   ) {
		if(1 != personMapper.delNiceDefaultNice(no_nice_default_nice   )){
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.");
	}
	public ReturnClass delNiceDelayNice(String no_nice_delay_nice     ) {
		if(1 != personMapper.delNiceDelayNice(no_nice_delay_nice     )){
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.");
	}

	@Override
	public ReturnClass modifyAgreeUsing(PersonVO personVO) {
		personMapper.modifyAgreeUsing(personVO);
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	@Override
	public void insertPersonNice12MonthByQuartz() throws Exception {
		personMapper.insertPersonNice12MonthByQuartz();
	}

	@Override
	public String getYnAgreeUsingInfo(String no_person) {
		return personMapper.getYnAgreeUsingInfo(no_person);
	}

	@Override
	public ReturnClass listPersonInfoJson(PersonForm form) {
		JSONArray list = new JSONArray();
		JSONObject object = null;
		List<PersonVO> res = personMapper.listPersonInfoJson(form);
		for (int i=0; i < res.size() ; i++ ) {
			object = new JSONObject();
			object.put("data", res.get(i).getNo_person());
			list.add(object);
		}
		return new ReturnClass( Constant.SUCCESS, "정상적으로 처리되었습니다.", list );
	}

//	@Override
//	public ReturnClass insertPerson(PersonVO personVO) throws UnsupportedEncodingException, FinsetException,IOException {
//
//		if(1 != personMapper.insertPerson(personVO)) {
//			logger.info("회원 가입 처리에 실패하였습니다. 다시 시도해주세요    personVO :" + personVO.toString());
//			return new ReturnClass(Constant.FAILED, "회원가입 처리에 실패하였습니다. 다시 시도해주세요.", personVO);
//		} else {
//
//			//KCB 회원 등록 처리
//			logger.info("personVO === " + personVO);
//			KcbCreditInfoVO info = new KcbCreditInfoVO();
//			//info.setNmIf("600");
//			info.setCd_regist("01");					// 01 신규, 09 URL
//			info.setBgn(personVO.getBgn());				// 생년월일, 성별
//			info.setNoPerson(personVO.getNo_person());	// 회원번호
//			info.setNmCust(personVO.getNm_person());		// 회원명
//			info.setDi(personVO.getKcb_di());				// 회원 KCB DI
//			info.setCp(personVO.getKcb_cp());				// 회원 KCB CP
//			info.setHp(personVO.getHp());					// 회원 휴대폰번호
//			//logger.info("info === " + info.toString());
//			//kcbManager.procKcbCb(info);
//			//logger.info("600 전문 처리 완료");
//
//			info.setNmIf("600420");
//			info.setReq_menu_code("200");
//			info.setReq_view_code("s07143331300");
//			kcbManager.procKcbCb(info);
//
//			logger.info("600420 전문 처리 완료");
//
//			//등록자, 수정자 셋팅
//			personVO.setId_frt(personVO.getNo_person());
//			personVO.setId_lst(personVO.getNo_person());
//
//			//상품 검색조건 셋팅
//			logger.info("상품 검색조건 셋팅 시작");
//			ConditioncreditVO conditioncreditVO = new ConditioncreditVO();
//			ConditionbizVO conditionbizVO 		= new ConditionbizVO();
//			ConditionhouseVO conditionhouseVO 	= new ConditionhouseVO();
//
//			conditioncreditVO = conditioncreditMapper.getConditioncreditInfo(personVO.getNo_person());
//			conditionbizVO = conditionbizMapper.getConditionbizInfo(personVO.getNo_person());
//			conditionhouseVO = conditionhouseMapper.getConditionhouseInfo(personVO.getNo_person());
//
//			if(conditioncreditVO == null){
//				conditioncreditMapper.insertConditioncreditInfo(personVO.getNo_person()); //신용(개인)
//			}
//			if(conditionbizVO == null){
//				conditionbizMapper.insertConditionbizInfo(personVO.getNo_person()); //신용(사업자)
//			}
//			if(conditionhouseVO == null){
//				conditionhouseMapper.insertConditionhouseInfo(personVO.getNo_person()); //주택담보
//			}
//			logger.info("상품 검색조건 셋팅 완료");
//
//			//알림 setting(일반, 신용, 부채, 상품, 이벤트(선택))
//			logger.info("알림 셋팅 시작");
//			List<String> pushItems = new ArrayList<String>();
//
//			//고정 알림값(일반, 신용, 부채, 상품) 셋팅
//			String fixPushItems[] = {"01","02","03","04"};
//			for (int i = 0; i < fixPushItems.length; i++) {
//				pushItems.add(fixPushItems[i]);
//			}
//
//			//이벤트 푸시 수신여부 setting
//			if("Y".equals(personVO.getYn_eventPush())){
//				pushItems.add("05");
//			}
//
//			//person_info의 소리+진동 설정, 푸시 수신여부 default값 셋팅
//			personVO.setType_push("default");
//			personMapper.modifyPushNoti(personVO);
//
//			//push_setting_info insert
//			personVO.setStat_push("Y");
//			for (int i = 0; i < pushItems.size(); i++) {
//				personVO.setItem_push(pushItems.get(i));
//				personMapper.insertCdPush(personVO);
//				personMapper.insertCdPushHist(personVO);
//			}
//			logger.info("알림 셋팅 완료");
//		}
//
//		logger.info("회원 가입 정상 처리 하였습니다.    personVO :" + personVO.toString());
//		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.", personVO);
//	}

	@Override
	public PersonVO getPersonInfoDup(PersonVO personVO) {
		return personMapper.getPersonInfoDup(personVO);
	}

	@Override
	public ReturnClass modifyPassPerson(PersonVO personVO) {
		//비밀번호 수정
		if(1 != personMapper.modifyPassPerson(personVO)) {
			logger.info("비밀번호 수정 처리에 실패하였습니다. 다시 시도해주세요    personVO :" + personVO.toString());
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		logger.info("비밀번호 수정 정상 처리 하였습니다.    personVO :" + personVO.toString());
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyFcmToken(PersonVO personVO) {
		if(1 != personMapper.modifyFcmToken(personVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	@Override
	public void modifyLastLogin(String no_person) {
		personMapper.modifyLastLogin(no_person);
	}

	@Override
	public PersonQuitVO getPersonQuitInfo(String no_person) {
		return personMapper.getPersonQuitInfo(no_person);
	}

	/**
	 * 페이지 이동 기록
	 * @param personVO
	 * @return
	 */
	public ReturnClass insertActiveHist(PersonActiveHistVO personActiveHistVO){

		if(1 != personMapper.insertActiveHist(personActiveHistVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	@Override
	public List<PersonActiveHistVO> listPersonActiveHistInfoPg(PersonForm personForm) {
		return personMapper.listPersonActiveHistInfoPg(personForm);
	}

	@Override
	public int listPersonActiveHistCount(PersonForm personForm) {
		return personMapper.listPersonActiveHistCount(personForm);
	}

	@Override
	public void insertPersonLoginHist(PersonLoginHist personLoginHist) {
		 personMapper.insertPersonLoginHist(personLoginHist);
	}

	@Override
	public List<PersonLoginHistVO> listPersonLoginHist(PersonLoginHistForm personLoginHistForm) {
		return personMapper.listPersonLoginHist(personLoginHistForm);
	}

	@Override
	public int listPersonLoginHistCount(PersonLoginHistForm personLoginHistForm) {
		return personMapper.listPersonLoginHistCount(personLoginHistForm);
	}

	@Override
	public ReturnClass modifyPwdFailCnt(PersonVO personVO) {
		if(1 != personMapper.modifyPwdFailCnt(personVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyFingerPrint(PersonVO personVO) {
		if(1 != personMapper.modifyFingerPrint(personVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

//	/**
//	 * 회원 탈퇴 및 데이터 삭제
//	 * @param no_person
//	 * @return
//	 * @throws IOException
//	 * @throws FinsetException
//	 * @throws UnsupportedEncodingException
//	 */
//	@Override
//	public ReturnClass procPersonInfoDelQuit(String no_person) throws UnsupportedEncodingException, FinsetException, IOException {
//
//		try {
//			PersonVO personVO = new PersonVO();
//			personVO = personMapper.getPersonInfo(no_person);
//			logger.info(personVO.toString());
//
//			//KCB 회원 삭제 처리
//			logger.info("personVO === " + personVO);
//			KcbCreditInfoVO info = new KcbCreditInfoVO();
//			
//			//등록전문 정상여부 체크
//			HashMap<String, String> schMap = new HashMap<String, String>();
//			schMap.put("sch_no_person", personVO.getNo_person());
//			schMap.put("nm_if", "600420");
//			HashMap<String, String> clobMap = creditMapper.getKcbJoinInfo(schMap);
//			
//			if (clobMap != null) {
//				info.setBgn(personVO.getBgn());				// 생년월일, 성별
//				info.setNoPerson(personVO.getNo_person());	// 회원번호
//				info.setNmCust(personVO.getNm_person());	// 회원명
//				info.setDi(personVO.getKcb_di());			// 회원 KCB DI
//				info.setCp(personVO.getKcb_cp());			// 회원 KCB CP
//				info.setKcb_id(personVO.getKcb_id());		// 회원 KCB DI
//				info.setHp(personVO.getHp());				// 회원 휴대폰번호
//				info.setNmIf("600420");
//				info.setCd_regist("03");					// 01 신규, 03 해지, 09 URL
//				info.setReq_menu_code("200");
//				info.setReq_view_code("s07143331300");
//				logger.info("info === " + info.toString());
//				ReturnClass returnClass = kcbManager.procKcbCb(info);
//				logger.error(returnClass.getCd_result() + " ::: " + returnClass.getMessage() + " ::: " + returnClass.getReturnObj().toString());
//				logger.error("600420 전문 처리 완료");
//			}
//			
//			schMap.put("nm_if", "600");
//			clobMap = creditMapper.getKcbJoinInfo(schMap);
//			if (clobMap != null) {
//				info.setNmIf("600");
//				info.setCd_regist("05");					// 01 신규, 05 해지
//				logger.info("info === " + info.toString());
//				kcbManager.procKcbCb(info);
//				logger.error("600 전문 처리 완료");
//			}
//
//			personVO.setId_frt(personVO.getNo_person());
//			personMapper.createPersonQuit(personVO);
//			personMapper.procPersonInfoDelQuit(no_person);
//			
//		} catch (FinsetException e) {
//			
//			logger.error("600420 전문 처리 완료");
//			return new ReturnClass(Constant.FAILED, "탈퇴처리시 오류가 발생하였습니다.");
//		}
//		
//		return new ReturnClass(Constant.SUCCESS,"탈퇴가 완료되었습니다.");
//		
//	}

	/**
	 * 프로필 사진 등록/수정
	 * @param no_person
	 * @return
	 */
	@Override
	public ReturnClass profileImg(PersonVO personVO) {
		try {
			// 파일 업로드
			FileUpload fileUpload = new FileUpload();

			logger.debug("======LCA profileImg FILE INFO============");
			String fileName = personVO.getFileName();
			int fileSize = personVO.getFileSize();
			byte[] fileArray = personVO.getFileArray();

			logger.info("fileName->"+fileName);
			logger.info("fileSize->"+fileSize);

			if(fileArray != null && fileSize > 0){
				String fileName1 = fileUpload.nameCheck(fileUpload.getFileName(fileName));

				FileUtils.writeByteArrayToFile(new File(fileName1), fileArray);
				logger.debug("======profileImg====fileName1============="+fileName1);
				// 실제 저장된 경로명
				personVO.setPath_file1(fileName1);
				// 실제 저장된 파일명
				personVO.setNm_file1(fileName1.substring(fileName1.lastIndexOf('/')+1, fileName1.length()));
			}

		} catch (IOException e) {
			LogUtil.error(logger,e);
			return new ReturnClass(Constant.FAILED, "파일업로드에 실패하였습니다.");
		} catch (FinsetException e) {
			LogUtil.error(logger,e);
			return new ReturnClass(Constant.FAILED, "파일업로드에 실패하였습니다.");
		}


		if(1 != personMapper.profileImg(personVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	/**
	 * 이메일 업데이트
	 * @param personVO
	 * @return
	 */
	@Override
	public ReturnClass modifyPersonEmail(PersonVO personVO) {

		String email = personVO.getEmail();

		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);

        if( !m.matches() ) {
        	return new ReturnClass(Constant.FAILED,"올바른 E-mail 형식이 아닙니다.");
        }

		if(1 != personMapper.modifyPersonEmail(personVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	/**
	 * 로그아웃 업데이트
	 * @param no_person
	 * @return
	 */
	@Override
	public ReturnClass modifyPersonLogout(PersonVO personVO) {
		if(1 != personMapper.modifyPersonLogout(personVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
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
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	@Override
	public ReturnClass modifyYnUseAndLogout(PersonVO personVO) {
		//로그아웃, 사용여부 수정
		if(1 != personMapper.modifyYnUseAndLogout(personVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}
	/**
	 * 증권토큰 수정
	 * @param personVO
	 * @return
	 */
	@Override
	public ReturnClass modifyStockToken(PersonVO personVO) {
		if(1 != personMapper.modifyStockToken(personVO)) {
			return new ReturnClass(Constant.FAILED,"처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS,"정상 처리 하였습니다.");
	}

	@Override
	public PersonQuitVO getPersonQuitChk(PersonVO personVO) {
		return personMapper.getPersonQuitChk(personVO);
	}

	/**
	 * 마이페이지 알람 설정정보 list조회
	 *
	 */
	@Override
	public List<PersonVO> getPushSettingInfo(String no_person) {
		return personMapper.getPushSettingInfo(no_person);
	}

	/**
	 * 비밀번호 체크
	 * @param personVO
	 * @return int
	 */
	@Override
	public int checkPersonPass(PersonVO personVO) {
		return personMapper.checkPersonPass(personVO);
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

	/** srchou */
	@Override
	public List<PersonVO> getAllPersonInfo() {
		return personMapper.getAllPersonInfo();
	}

	@Override
	public String getPwdDB(String pwd) {
		return personMapper.getPwdDB(pwd);
	}

	/**
	 * 신용상담
	 *
	 */
	@Override
	public PersonCounselVO getPersonCounselInfo(String no_person) {
		return personMapper.getPersonCounselInfo(no_person);
	}

	@Override
	public ReturnClass createPersonCounsel(PersonCounselVO personCounselVO) {
		if(1 != personMapper.createPersonCounsel(personCounselVO)){
			return new ReturnClass(Constant.FAILED, "등록 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "등록 성공하였습니다.");
	}

	@Override
	public List<PersonCounselVO> getPersonCounselList(PersonCounselForm personCounselForm) {
		return personMapper.getPersonCounselList(personCounselForm);
	}

	@Override
	public int getPersonCounselListCount(PersonCounselForm personCounselForm) {
		return personMapper.getPersonCounselListCount(personCounselForm);
	}

	@Override
	public HashMap<String, String> getPersonCounselMapInfo(PersonCounselVO personCounselVO) {
		return personMapper.getPersonCounselMapInfo(personCounselVO);
	}


	@Override
	public int createPersonCertificateInfo(PersonCertificateInfoVO personCertificateInfoVO) {
		return personMapper.createPersonCertificateInfo(personCertificateInfoVO);
	}


	/**
	 * 20180717 VITCOM KHK
	 * 핸드폰 번호 변경에 따른 확인 및 업데이트
	 */
	@Override
	public String getPersonInfoDupCi(PersonVO personVO){
		return personMapper.getPersonInfoDupCi(personVO);
	}

	@Override
	public int modifyPersonHp(PersonVO personVO){
		return personMapper.modifyPersonHp(personVO);
	}

	//공유관리
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

}
