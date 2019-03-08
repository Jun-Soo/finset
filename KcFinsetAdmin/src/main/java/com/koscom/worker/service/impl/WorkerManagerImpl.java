package com.koscom.worker.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.koscom.domain.WorkerAuthHist;
import com.koscom.domain.WorkerInfo;
import com.koscom.domain.WorkerLoginHist;
import com.koscom.env.model.ApprovalManage;
import com.koscom.env.model.ApprovalManageForm;
import com.koscom.env.model.ApprovalManageVO;
import com.koscom.env.model.ProgramManage;
import com.koscom.env.model.ProgramManageForm;
import com.koscom.env.model.ProgramManageVO;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.SecurityReqFilter;
import com.koscom.util.SpringApplicationContext;
import com.koscom.util.StringUtil;
import com.koscom.worker.dao.WorkerMapper;
import com.koscom.worker.model.WorkerAuthHistVO;
import com.koscom.worker.model.WorkerForm;
import com.koscom.worker.model.WorkerVO;
import com.koscom.worker.service.WorkerManager;

@Service("workerManager")
public class WorkerManagerImpl implements WorkerManager {

	private static final Logger logger = LoggerFactory.getLogger(WorkerManagerImpl.class);

	private WorkerManager workerManager;

	@Autowired
	private WorkerMapper workerMapper;

	@Autowired
	private SecurityReqFilter securityReqFilter;

	@Override
	public WorkerVO getWorkerInfo(String id_emp) {
		return workerMapper.getWorkerInfo(id_emp);
	}

	@Override
	@Cacheable(value="CacheWorker", key="#id_emp")
	public WorkerVO getCacheWorkerInfo(String id_emp) {
		return workerMapper.getWorkerInfo(id_emp);
	}

	@Override
	public String getWorkerInfo(String id_emp, String type) {

		workerManager = (WorkerManager) SpringApplicationContext.getBean("workerManager");
		WorkerVO workerVO = workerManager.getCacheWorkerInfo(id_emp);
		if(workerVO == null) return id_emp;

		String result = "";

		// 이름
		if ( "NM".equals(type) ) {
			result = workerVO.getNm_emp();
		}
		// 내선번호
		if ( "EXT".equals(type) ) {
			result = workerVO.getExt_emp();
		}
		// 부서
		else if ( "DEPT".equals(type) ) {
			result = workerVO.getCd_dept_emp();
		}
		// 템플릿
		else if ( "TEMPLATE".equals(type) ) {
			result = workerVO.getCd_template_group();
		}
		// 지점
		else if ( "C3_BRANCH".equals(type) ) {
			result = workerVO.getC3_branch();
		}
		// 상태
		else if ( "STATUS".equals(type)) {
			result = workerVO.getCd_status_emp();
		}
		// 직통번호
		else if ( "DIRECT".equals(type)) {
			result = workerVO.getExt_emp_direct();
		}
		// 직통번호(하이픈포함)
		else if ( "DIRECT_HYPHEN".equals(type)) {
			if(StringUtil.isNotEmpty(workerVO.getExt_direct_idx1()) && StringUtil.isNotEmpty(workerVO.getExt_direct_idx2()) && StringUtil.isNotEmpty(workerVO.getExt_direct_idx3())){
				result = workerVO.getExt_direct_idx1() + "-" + workerVO.getExt_direct_idx2() + "-" + workerVO.getExt_direct_idx3();
			}
		}
		// 휴대폰번호
		else if ( "HP".equals(type)) {
			result = workerVO.getHp();
		}

		return result;
	}

	@Override
	public List<WorkerInfo> listWorkerInfo(WorkerForm workerForm) {
		return workerMapper.listWorkerInfo(workerForm);
	}

	@Override
	public List<WorkerInfo> listWorkerInfoStatus() {
		return workerMapper.listWorkerInfoStatus();
	}

	@Override
	public List<ProgramManageVO> listWorkerProgramAuth(String id_emp) {
		return workerMapper.listWorkerProgramAuth(id_emp);
	}

	@Override
	@Cacheable("CacheWorkerProgramAuth")
	public List<ProgramManageVO> listCacheWorkerProgramAuth(String id_emp) {
		return workerMapper.listWorkerProgramAuth(id_emp);
	}

	@Override
	public List<ApprovalManageVO> listWorkerApprovalAuth(String id_emp) {
		return workerMapper.listWorkerApprovalAuth(id_emp);
	}

	@Override
	@Cacheable("CacheWorkerApprovalAuth")
	public List<ApprovalManageVO> listCacheWorkerApprovalAuth(String id_emp) {
		return workerMapper.listWorkerApprovalAuth(id_emp);
	}

	@Override
	public List<ProgramManageVO> listProgramTemplate(String code_value) {
		return workerMapper.listProgramTemplate(code_value);
	}

	@Override
	public List<ApprovalManageVO> listApprovalTemplate(String code_value) {
		return workerMapper.listApprovalTemplate(code_value);
	}

	@Override
	@CacheEvict(value={"CacheWorker"}, allEntries=true)
	public void clearCacheWorker() {
		logger.info("Cache clear : [CacheWorker]");
	}

	@Override
	@CacheEvict(value={"CacheWorker","CacheWorkerProgramAuth","CacheWorkerApprovalAuth","CacheApprovalAuth"}, allEntries=true)
	public void clearCacheWorkerAuth() {
		logger.info("Cache clear : [CacheWorker,WorkerProgramAuth,WorkerApprovalAuth,CacheApprovalAuth]");
	}

	@Override
	public ReturnClass createWorker(WorkerVO workerVO) {
		WorkerVO wi = workerMapper.getWorkerInfo(workerVO.getId_emp());

		if(wi != null) return new ReturnClass(Constant.FAILED, "사용자ID가 존재합니다. 사용자ID를 변경하여 주세요.");

		// 비밀번호 md5 변환. (초기 비밀번호는 id)
		//workerVO.setPass_emp(getMD5Pass(workerVO.getId_emp()));
		workerVO.setPass_emp(workerVO.getId_emp());
		int cnt_insert = workerMapper.createWorker(workerVO);

		if(cnt_insert != 1) return new ReturnClass(Constant.FAILED, "사용자등록에 실패하였습니다.");

		//템플릿 적용
		if(StringUtil.isNotEmpty(workerVO.getCd_template_group())){

			//프로그램 템플릿
			List<ProgramManageVO> programTemplateList = workerMapper.listProgramTemplate(workerVO.getCd_template_group());
			String id_program_str = "";

			if(programTemplateList.size() > 0){
				for(ProgramManageVO list : programTemplateList){
					id_program_str += list.getId_program()+",";
				}
				ProgramManageForm pmForm = new ProgramManageForm();
				pmForm.setId_program_list(id_program_str);
				pmForm.setId_emp(workerVO.getId_emp());
				pmForm.setId_frt(workerVO.getId_frt());
				createWorkerProgramAuth(pmForm);
			}

			//결제권한 템플릿
			/*	현재 테이블이 없음
			List<ApprovalManageVO> approvalTemplateList = workerMapper.listApprovalTemplate(workerVO.getCd_template_group());
			String id_appr_str = "";

			if(approvalTemplateList.size() > 0){
				for(ApprovalManageVO list : approvalTemplateList){
					id_appr_str += list.getId_appr()+",";
				}
				ApprovalManageForm amForm = new ApprovalManageForm();
				amForm.setId_emp(workerVO.getId_emp());
				amForm.setId_appr_list(id_appr_str);
				amForm.setId_frt(workerVO.getId_frt());
				createWorkerApprovalAuth(amForm);
			}
			*/
		}

		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");
	}

	public String getMD5Pass(String pass_emp) {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		return encoder.encodePassword(pass_emp, null);
	}

	@Override
	public ReturnClass modifyWorker(WorkerVO workerVO) {
		WorkerVO wi = workerMapper.getWorkerInfo(workerVO.getId_emp());

		if(wi == null) return new ReturnClass(Constant.FAILED, "존재하지 않는 사용자 입니다.");

		int cnt_update = workerMapper.modifyWorker(workerVO);
		if(cnt_update != 1) return new ReturnClass(Constant.FAILED, "사용자정보 변경에 실패하였습니다.");

		//템플릿 적용
		if(StringUtil.isNotEmpty(workerVO.getCd_template_group()) &&
				!StringUtil.nullToString(wi.getCd_template_group(),"").equals(StringUtil.nullToString(workerVO.getCd_template_group(),""))){
			modifyWorkerTemplate(workerVO);
		}

		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");
	}

	@Override
	public ReturnClass deleteWorker( WorkerVO workerVO) {
		if (1 != workerMapper.deleteWorker(workerVO)) {
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.");
	}

	//템플릿 설정 변경
	private void modifyWorkerTemplate(WorkerVO workerVO) {

		//프로그램 템플릿 조회
		List<ProgramManageVO> programTemplateList = workerMapper.listProgramTemplate(workerVO.getCd_template_group());
		//기존 프로그램 조회
		List<ProgramManageVO> workerProgramList = workerMapper.listWorkerProgramAuth(workerVO.getId_emp());

		String insertProg = "";

		//삭제리스트에 기존프로그램 셋
		List<String> deleteProgList = new ArrayList<String>();
		for(ProgramManageVO list : workerProgramList){
			deleteProgList.add(list.getId_program());
		}

		for (ProgramManageVO list : programTemplateList) {
			boolean flg_tmp = false;

			//템플릿과 기존에 같은 프로그램이있다면 삭제리스트에서 제거
			for (ProgramManageVO sub : workerProgramList) {

				if(list.getId_program().equals(sub.getId_program())){
					deleteProgList.remove(sub.getId_program());
					flg_tmp = true;
					continue;
				}
			}
			//탬플릿에만있는 프로그램 삽입리스트에 추가
			if(!flg_tmp) insertProg += list.getId_program()+",";
		}

		ProgramManageForm pmForm = new ProgramManageForm();
		pmForm.setId_emp(workerVO.getId_emp());
		pmForm.setId_frt(workerVO.getId_frt());

		//프로그램 추가
		if(StringUtil.isNotEmpty(insertProg)){
			pmForm.setId_program_list(insertProg);
			createWorkerProgramAuth(pmForm);
		}

		//프로그램삭제
		if(0 < deleteProgList.size()){
			String deleteProg="";

			for (String list : deleteProgList) {
				deleteProg += list+",";
			}
			pmForm.setId_program_list(deleteProg);
			deleteWorkerProgramAuth(pmForm);
		}

		//결재권한 템플릿 조회
		/* 현재 테이블 없음
		List<ApprovalManageVO> apprTemplateList = workerMapper.listApprovalTemplate(workerVO.getCd_template_group());
		*/
		//기존 결재권한 조회
		List<ApprovalManageVO> workerApprList = workerMapper.listWorkerApprovalAuth(workerVO.getId_emp());

		String insertAppr = "";
		//삭제리스트에 기존 결재권한 셋
		List<String> deleteApprList = new ArrayList<String>();
		for(ApprovalManageVO list : workerApprList){
			deleteApprList.add(list.getId_appr());
		}

		/*
		for (ApprovalManageVO list : apprTemplateList) {
			boolean flg_tmp = false;

			//템플릿과 기존에 같은 결제권한이있다면 삭제리스트에서 제거
			for (ApprovalManageVO sub : workerApprList) {

				if(list.getId_appr().equals(sub.getId_appr())){
					deleteApprList.remove(sub.getId_appr());
					flg_tmp = true;
					continue;
				}
			}
			//탬플릿에만있는 결제권한 삽입리스트에 추가
			if(!flg_tmp) insertAppr += list.getId_appr()+",";
		}
		*/
		ApprovalManageForm amForm = new ApprovalManageForm();
		amForm.setId_emp(workerVO.getId_emp());
		amForm.setId_frt(workerVO.getId_frt());

		//권한추가
		if(StringUtil.isNotEmpty(insertAppr)){
			amForm.setId_appr_list(insertAppr);
			createWorkerApprovalAuth(amForm);
		}

		//권한 삭제
		if(0 < deleteApprList.size()){
			String deleteAppr="";

			for (String list : deleteApprList) {
				deleteAppr += list+",";
			}
			amForm.setId_appr_list(deleteAppr);
			deleteWorkerApprovalAuth(amForm);
		}

	}

	@Override
	public ReturnClass createWorkerProgramAuth(ProgramManageForm pmForm) {
		String[] idPrograms = pmForm.getId_program_list().split(",");

		HashMap<String, ProgramManage> mapProgram = Constant.PROGRAM;
		HashMap<String,String> mapProgramIn = new HashMap<String,String>();

		WorkerAuthHist workerAuthHist = new WorkerAuthHist();

		String program = "";
		// 선택된 상위 프로그램권한을 같이 insert 하도록.
		for ( String id_program : idPrograms ) {
			mapProgramIn.put(id_program, id_program);
			if( mapProgram.get(id_program) != null ){
				ProgramManage pm = mapProgram.get(id_program);
	
				program = pm.getCd_system()+"000001";
				if(mapProgram.get(program) != null){
					mapProgramIn.put(program, program);
				}
				program = pm.getCd_system()+pm.getCd_work()+"1001";
				if(mapProgram.get(program) != null){
					mapProgramIn.put(program, program);
				}
	
				// 선택된 프로그램권한 중 하위메뉴만 insert
				if ("20".equals(pm.getCd_program_group())) {
					workerAuthHist.setId_emp(pmForm.getId_emp());
					workerAuthHist.setId_auth(pmForm.getId_frt());
					workerAuthHist.setCd_auth(WorkerAuthHistVO.CD_AUTH_02);
					workerAuthHist.setCd_proc(WorkerAuthHistVO.CD_AUTH_PROC_00);
					workerAuthHist.setId_frt(pmForm.getId_frt());
					workerAuthHist.setCd_detail(id_program);
	
					if (1 != workerMapper.createWorkerAuthHist(workerAuthHist)) {
						return new ReturnClass(Constant.FAILED, "프로그램 권한이력 처리에 실패 하였습니다.");
					}
				}
			}
		}

		Iterator<String> itrProgram = mapProgramIn.keySet().iterator();
		while(itrProgram.hasNext()) {
			pmForm.setId_program(itrProgram.next());

            workerMapper.createWorkerProgramAuth(pmForm);

		}

		return new ReturnClass(Constant.SUCCESS, "정상적으로 처리 되었습니다.");
	}

	@Override
	public ReturnClass deleteWorkerProgramAuth(ProgramManageForm pmForm) {
		String[] idPrograms = pmForm.getId_program_list().split(",");
		int nCnt = 0;

		WorkerAuthHist workerAuthHist = new WorkerAuthHist();

		for ( String id_program : idPrograms ) {
			pmForm.setId_program(id_program);

			// 하위메뉴를 구분함
			if ("2".equals(pmForm.getId_program().substring(4, 5))) {
				workerAuthHist.setId_emp(pmForm.getId_emp());
				workerAuthHist.setId_auth(pmForm.getId_frt());
				workerAuthHist.setCd_auth(WorkerAuthHistVO.CD_AUTH_02);
				workerAuthHist.setCd_proc(WorkerAuthHistVO.CD_AUTH_PROC_99);
				workerAuthHist.setId_frt(pmForm.getId_frt());
				workerAuthHist.setCd_detail(id_program);

				if (1 != workerMapper.createWorkerAuthHist(workerAuthHist)) {
					return new ReturnClass(Constant.FAILED, "프로그램 권한이력 처리에 실패 하였습니다.");
				}
			}

			nCnt += workerMapper.deleteWorkerProgramAuth(pmForm);
		}

		if ( nCnt != idPrograms.length ) {
			return new ReturnClass(Constant.FAILED, "처리에 실패 하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상적으로 처리 되었습니다.");
	}

	@Override
	public ReturnClass createWorkerApprovalAuth(ApprovalManageForm amForm) {
		String[] idApprs = amForm.getId_appr_list().split(",");

		HashMap<String, ApprovalManage> mapApproval = Constant.APPROVAL;
		HashMap<String,String> mapApprovalIn = new HashMap<String,String>();

		WorkerAuthHist workerAuthHist = new WorkerAuthHist();

		String approval="";
		// 선택된 상위 프로그램권한을 같이 insert 하도록.
		for ( String id_appr : idApprs ) {
			mapApprovalIn.put(id_appr, id_appr);
			ApprovalManage am = mapApproval.get(id_appr);

			approval = am.getCd_work()+"1001";
			if(mapApproval.get(approval) != null){
				mapApprovalIn.put(approval, approval);
			}

			// 선택된 프로그램권한 중 하위메뉴만 insert
			if ("20".equals(am.getCd_approval_group())) {
				workerAuthHist.setId_emp(amForm.getId_emp());
				workerAuthHist.setId_auth(amForm.getId_frt());
				workerAuthHist.setCd_auth(WorkerAuthHistVO.CD_AUTH_01);
				workerAuthHist.setCd_proc(WorkerAuthHistVO.CD_AUTH_PROC_00);
				workerAuthHist.setId_frt(amForm.getId_frt());
				workerAuthHist.setCd_detail(id_appr);

				if (1 != workerMapper.createWorkerAuthHist(workerAuthHist)) {
					return new ReturnClass(Constant.FAILED, "결재 권한이력 처리에 실패 하였습니다.");
				}
			}
		}

		Iterator<String> itrApproval = mapApprovalIn.keySet().iterator();
		while(itrApproval.hasNext())
		{
			amForm.setId_appr(itrApproval.next());

            workerMapper.createWorkerApprovalAuth(amForm);
		}

		return new ReturnClass(Constant.SUCCESS, "정상적으로 처리 되었습니다.");
	}

	@Override
	public ReturnClass deleteWorkerApprovalAuth(ApprovalManageForm amForm) {
		String[] idApprs = amForm.getId_appr_list().split(",");
		int nCnt = 0;

		WorkerAuthHist workerAuthHist = new WorkerAuthHist();

		for ( String id_appr : idApprs ) {
			amForm.setId_appr(id_appr);

			// 하위메뉴를 구분함
			if ("2".equals(amForm.getId_appr().substring(2, 3))) {
				workerAuthHist.setId_emp(amForm.getId_emp());
				workerAuthHist.setId_auth(amForm.getId_frt());
				workerAuthHist.setCd_auth(WorkerAuthHistVO.CD_AUTH_01);
				workerAuthHist.setCd_proc(WorkerAuthHistVO.CD_AUTH_PROC_99);
				workerAuthHist.setId_frt(amForm.getId_frt());
				workerAuthHist.setCd_detail(id_appr);

				if (1 != workerMapper.createWorkerAuthHist(workerAuthHist)) {
					return new ReturnClass(Constant.FAILED, "프로그램 권한이력 처리에 실패 하였습니다.");
				}
			}

			nCnt += workerMapper.deleteWorkerApprovalAuth(amForm);
		}

		if ( nCnt != idApprs.length ) {
			return new ReturnClass(Constant.FAILED, "처리에 실패 하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상적으로 처리 되었습니다.");
	}

	@Override
	public ReturnClass createProgramTemplate(ProgramManageForm pmForm) {
		String[] idPrograms = pmForm.getId_program_list().split(",");

		HashMap<String, ProgramManage> mapProgram = Constant.PROGRAM;
		HashMap<String,String> mapProgramIn = new HashMap<String,String>();
		String program = "";

		// 선택된 상위 프로그램권한을 같이 insert 하도록.
		for ( String id_program : idPrograms ) {
			mapProgramIn.put(id_program, id_program);
			ProgramManage pm = mapProgram.get(id_program);

			program = pm.getCd_system()+"000001";
			if(mapProgram.get(program) != null){
				mapProgramIn.put(program, program);
			}
			program = pm.getCd_system()+pm.getCd_work()+"1001";
			if(mapProgram.get(program) != null){
				mapProgramIn.put(program, program);
			}
		}

		Iterator<String> itrProgram = mapProgramIn.keySet().iterator();
		while(itrProgram.hasNext())
		{
			pmForm.setId_program(itrProgram.next());

            workerMapper.createProgramTemplate(pmForm);

            // 템플릿으로 적용된 권한을 해당템플릿에 속한 사용자들 조회하여 추가함
            WorkerForm workerForm = new WorkerForm();
            workerForm.setCd_template_group(pmForm.getCd_template_group());
            // 템플릿으로 사용자ID리스트 조회
            List<WorkerInfo> listWorker = workerMapper.listWorkerInfo(workerForm);

            for (WorkerInfo workerInfo : listWorker) {
                pmForm.setId_emp(workerInfo.getId_emp());
                workerMapper.createWorkerProgramAuth(pmForm);
            }

		}

		return new ReturnClass(Constant.SUCCESS, "정상적으로 처리 되었습니다.");
	}

	@Override
	public ReturnClass deleteProgramTemplate(ProgramManageForm pmForm) {
		String[] idPrograms = pmForm.getId_program_list().split(",");
		int nCnt = 0;

		for ( String id_program : idPrograms ) {
			pmForm.setId_program(id_program);
			nCnt += workerMapper.deleteProgramTemplate(pmForm);

			// 템플릿으로 적용된 권한을 해당템플릿에 속한 사용자들 조회하여 추가함
			WorkerForm workerForm = new WorkerForm();
			workerForm.setCd_template_group(pmForm.getCd_template_group());
			// 템플릿으로 사용자ID리스트 조회
			List<WorkerInfo> listWorker = workerMapper.listWorkerInfo(workerForm);

			for (WorkerInfo workerInfo : listWorker) {
				pmForm.setId_emp(workerInfo.getId_emp());
				workerMapper.deleteWorkerProgramAuth(pmForm);
			}
		}

		if ( nCnt != idPrograms.length ) {
			return new ReturnClass(Constant.FAILED, "처리에 실패 하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상적으로 처리 되었습니다.");
	}

	@Override
	public ReturnClass createApprovalTemplate(ApprovalManageForm amForm) {
		String[] idApprs = amForm.getId_appr_list().split(",");

		HashMap<String, ApprovalManage> mapApproval = Constant.APPROVAL;
		HashMap<String,String> mapApprovalIn = new HashMap<String,String>();

		String approval= "";
		// 선택된 상위 프로그램권한을 같이 insert 하도록.
		for ( String id_appr : idApprs ) {
			mapApprovalIn.put(id_appr, id_appr);
			ApprovalManage am = mapApproval.get(id_appr);

			approval = am.getCd_work()+"1001";
			if(mapApproval.get(approval) != null){
				mapApprovalIn.put(approval, approval);
			}
		}

		Iterator<String> itrApproval = mapApprovalIn.keySet().iterator();
		while(itrApproval.hasNext())
		{
			amForm.setId_appr(itrApproval.next());

            workerMapper.createApprovalTemplate(amForm);

            // 템플릿으로 적용된 권한을 해당템플릿에 속한 사용자들 조회하여 추가함
            WorkerForm workerForm = new WorkerForm();
            workerForm.setCd_template_group(amForm.getCd_template_group());
            // 템플릿으로 사용자ID리스트 조회
            List<WorkerInfo> listWorker = workerMapper.listWorkerInfo(workerForm);

            for (WorkerInfo workerInfo : listWorker) {
                amForm.setId_emp(workerInfo.getId_emp());
                workerMapper.createWorkerApprovalAuth(amForm);
            }
		}

		return new ReturnClass(Constant.SUCCESS, "정상적으로 처리 되었습니다.");
	}

	@Override
	public ReturnClass deleteApprovalTemplate(ApprovalManageForm amForm) {
		String[] idApprs = amForm.getId_appr_list().split(",");
		int nCnt = 0;

		for ( String id_appr : idApprs ) {
			amForm.setId_appr(id_appr);
			nCnt += workerMapper.deleteApprovalTemplate(amForm);

			// 템플릿으로 적용된 권한을 해당템플릿에 속한 사용자들 조회하여 추가함
			WorkerForm workerForm = new WorkerForm();
			workerForm.setCd_template_group(amForm.getCd_template_group());
			// 템플릿으로 사용자ID리스트 조회
			List<WorkerInfo> listWorker = workerMapper.listWorkerInfo(workerForm);

			for (WorkerInfo workerInfo : listWorker) {
				amForm.setId_emp(workerInfo.getId_emp());
				workerMapper.deleteWorkerApprovalAuth(amForm);
			}
		}

		if ( nCnt != idApprs.length ) {
			return new ReturnClass(Constant.FAILED, "처리에 실패 하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상적으로 처리 되었습니다.");
	}

	@Override
	public void insertWorkerLoginHist(WorkerLoginHist workerLoginHist) {
		workerMapper.insertWorkerLoginHist(workerLoginHist);
	}

	@Override
	@Cacheable("CacheApprovalAuth")
	public List<ApprovalManageVO> listCacheApprovalAuth(String idAppr) {
		return workerMapper.listCacheApprovalAuth(idAppr);
	}

	@Override
	@Cacheable("CacheApprovalAuth")
	public boolean getCachWorkerApprovalAuth(String idEmp, String idAppr){
		if ( StringUtil.isEmpty(idEmp) || StringUtil.isEmpty(idAppr) )
			return false;

		ApprovalManageForm amForm = new ApprovalManageForm();
		amForm.setId_emp(idEmp);
		amForm.setId_appr(idAppr);
		String approvalAuth = workerMapper.getCachWorkerApprovalAuth(amForm);

		if(approvalAuth == null) return false;
		else return true;
	}

	@Override
	public ReturnClass modifyWorkerUser(WorkerVO workerVO) {

		WorkerVO wi = workerMapper.getWorkerInfo(workerVO.getId_emp());

		if(wi == null) return new ReturnClass(Constant.FAILED, "등록된 사용자ID 를 확인하여 주세요.");

		//현재비밀번호 확인
		//if(StringUtil.isEmpty(workerVO.getPass_old()) || !getMD5Pass(workerVO.getPass_old()).equals(wi.getPass_emp())) {
		if(StringUtil.isEmpty(workerManager.getPwdDB(workerVO.getPass_old())) || !workerManager.getPwdDB(workerVO.getPass_old()).equals(wi.getPass_emp())) {
			return new ReturnClass(Constant.FAILED, "현재 비밀번호가 일치하지 않습니다. 다시 확인하여주세요.");
		}

		// 새로운 비밀번호 확인
		
		if(!workerVO.getPass_new().equals(workerVO.getPass_re_new())) {
			return new ReturnClass(Constant.FAILED, "입력된 새 비밀번호가 일치하지 않습니다.");
		} else if(StringUtil.isNotEmpty(workerVO.getPass_new())) {
			// 변경할 비밀번호가 있을때 유효성 검사
			ReturnClass returnClass = chkPassword(workerVO.getId_emp(), workerVO.getPass_new());
			if(!Constant.SUCCESS.equals(returnClass.getCd_result())) {
				return returnClass;
			}

			// 비밀번호 md5 변환.
			//workerVO.setPass_emp(getMD5Pass(workerVO.getPass_new()));
			workerVO.setPass_emp(workerVO.getPass_new());
		}
		int result = workerMapper.modifyWorkerUser(workerVO);
		if(1 != result)  {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		// 비밀번호 변경 후 차단목록에 있을 수 있는 ID 제거
		securityReqFilter.removeBlockUser(workerVO.getId_emp());

		return new ReturnClass(Constant.SUCCESS, "정상처리 하였습니다.");
	}

	@Override
	public ReturnClass initWorkerPass(WorkerVO workerVO) {

		WorkerVO wi = workerMapper.getWorkerInfo(workerVO.getId_emp());

		if(wi == null) return new ReturnClass(Constant.FAILED, "등록된 사용자ID 를 확인하여 주세요.");

		// 비밀번호 md5 변환. (초기 비밀번호는 id)
		//workerVO.setPass_emp(getMD5Pass(wi.getId_emp()));
		logger.debug("id check "  + wi.getId_emp());
		workerVO.setPass_emp(wi.getId_emp());

		if(1 != workerMapper.initWorkerPass(workerVO))
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");

		return new ReturnClass(Constant.SUCCESS, "정상처리 하였습니다.");
	}

	/**
	 * 비밀번호 유효성 검사
	 * @param id_user
	 * @param password
	 * @return
	 */
	@Override
	public ReturnClass chkPassword(String id_user, String password) {

		if( StringUtil.isEmpty(id_user) || StringUtil.isEmpty(password) ){
			return new ReturnClass(Constant.FAILED,"입력 데이터 오류");
		}

	    if(password.indexOf(id_user) > -1){
	    	return new ReturnClass(Constant.FAILED,"비밀번호에 아이디를 사용할 수 없습니다.");
	    }
	    /*
	     * 생년월일을 보유하지 않음
	    if(!StringUtil.isEmpty(ssn) && ssn.length() > 6){
	    	String str_birth = ssn.substring(2,6);
	    	int int_birth = NumberUtil.parseInt(ssn.substring(2,6));

	    	if(password.indexOf(str_birth) > -1 || password.indexOf(int_birth) > -1){
	    		return new ReturnClass(Constant.FAILED,"비밀번호에 생년월일을 사용할 수 없습니다.");
	    	}
	    }
	    */
	    int SamePass_0 = 0; //동일문자 카운트
	    int SamePass_1_str = 0; //연속성(-) 카운드(문자)
		int SamePass_2_str = 0; //연속성(+) 카운드(문자)
		int SamePass_1_num = 0; //연속성(-) 카운드(숫자)
		int SamePass_2_num = 0; //연속성(+) 카운드(숫자)

		int chr_pass_0;
		int chr_pass_1;

	    for(int i=0; i < password.length(); i++){
	        chr_pass_0 = password.charAt(i);

	        if((i+1) >= password.length()) continue;

	        chr_pass_1 = password.charAt(i+1);

	        //동일문자 카운트
	        if(chr_pass_0 == chr_pass_1){
	        	SamePass_0++;
	        }else{
	        	SamePass_0 = 0;
	        }

	        if(chr_pass_0 >= 48 && chr_pass_0 <= 57) {
	        	//숫자
				//연속성(-) 카운드
				if(chr_pass_0 - chr_pass_1 == 1){
					SamePass_1_num++;
				}else{
					SamePass_1_num = 0;
				}

				//연속성(+) 카운드
				if(chr_pass_0 - chr_pass_1 == -1){
					SamePass_2_num++;
				}else{
					SamePass_2_num = 0;
				}
	        } else {
	        	//문자
				//연속성(-) 카운드
				if(chr_pass_0 - chr_pass_1 == 1){
					SamePass_1_str++;
				}else{
					SamePass_1_str = 0;
				}

				//연속성(+) 카운드
				if(chr_pass_0 - chr_pass_1 == -1){
					SamePass_2_str++;
				}else{
					SamePass_2_str = 0;
				}
	        }
	        if(SamePass_0 > 1){
		    	return new ReturnClass(Constant.FAILED,"동일문자를 3번 이상 사용할 수 없습니다.");
		    }

		    if(SamePass_1_str > 2 || SamePass_2_str > 2 || SamePass_1_num > 2 || SamePass_2_num > 2){
		    	return new ReturnClass(Constant.FAILED,"연속된 문자열(1234 또는 4321, abcd, dcba 등)을 4자 이상 사용 할 수 없습니다.");
			}
	    }

		if(Pattern.matches("[A-Z]*", password) || Pattern.matches("[a-z]*", password) || Pattern.matches("[0-9]*", password) || Pattern.matches("[^가-힣a-zA-Z0-9]*", password)){
			return new ReturnClass(Constant.FAILED,"비밀번호는 2가지이상 문자조합으로 입력해주세요.\\n[영문 대문자/소문자/숫자/특수문자]");
	    }

		int chk_passwd = 6;
		if(Pattern.matches(".*[A-Za-z].*", password)) chk_passwd--;
		if(Pattern.matches(".*[A-Z0-9].*", password)) chk_passwd--;
		if(Pattern.matches(".*[^가-힣a-z0-9].*", password)) chk_passwd--;
		if(Pattern.matches(".*[a-z0-9].*", password)) chk_passwd--;
		if(Pattern.matches(".*[^가-힣A-Z0-9].*", password)) chk_passwd--;
		if(Pattern.matches(".*[^가-힣a-zA-Z].*", password)) chk_passwd--;

		if(chk_passwd > 0){
			if(password.length() < 10){
	    		return new ReturnClass(Constant.FAILED,"2가지 문자조합의 비밀번호는 10자리이상으로 입력해주세요");
	    	}
		}
		else {
			if(password.length() < 8){
	    		return new ReturnClass(Constant.FAILED,"3가지이상 문자조합의 비밀번호는 8자리이상으로 입력해주세요");
	    	}
		}

		return new ReturnClass(Constant.SUCCESS,"");
	}

	@Override
	public List<WorkerLoginHist> listWorkerLoginHist(WorkerForm workerForm) {
		return workerMapper.listWorkerLoginHist(workerForm);
	}

	@Override
	public int listWorkerLoginHistCount(WorkerForm workerForm) {
		return workerMapper.listWorkerLoginHistCount(workerForm);
	}

	@Override
	public List<WorkerAuthHist> listWorkerAuthHist(WorkerForm workerForm) {
		return workerMapper.listWorkerAuthHist(workerForm);
	}

	@Override
	public int listWorkerAuthHistCount(WorkerForm workerForm) {
		return workerMapper.listWorkerAuthHistCount(workerForm);
	}

	@Override
	public List<WorkerLoginHist> listWorkerLoginRecent(String id_emp) {
		return workerMapper.listWorkerLoginRecent(id_emp);
	}

	@Override
	public WorkerVO getWorkerInfoDirect(String direct_number) {
		return workerMapper.getWorkerInfoDirect(direct_number);
	}

	@Override
	public String getPwdDB(String pwd) {
		return workerMapper.getPwdDB(pwd);
	}



}