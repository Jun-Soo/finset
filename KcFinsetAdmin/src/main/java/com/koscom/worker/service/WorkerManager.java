package com.koscom.worker.service;

import java.util.List;

import com.koscom.domain.WorkerAuthHist;
import com.koscom.domain.WorkerInfo;
import com.koscom.domain.WorkerLoginHist;
import com.koscom.env.model.ApprovalManageForm;
import com.koscom.env.model.ApprovalManageVO;
import com.koscom.env.model.ProgramManageForm;
import com.koscom.env.model.ProgramManageVO;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.util.ReturnClass;
import com.koscom.worker.model.WorkerForm;
import com.koscom.worker.model.WorkerVO;

public interface WorkerManager {


	/**
	 * 사용자 id 를 입력받아 WorkerInfo 를 반환합니다.
	 * @param id_emp
	 * @return
	 */
	public WorkerVO getWorkerInfo(String id_emp);

	/**
	 * 사용자 id 를 입력받아 WorkerInfo 를 반환합니다.(캐시)
	 * @param id_emp
	 * @return
	 */
	public WorkerVO getCacheWorkerInfo(String id_emp);

	/**
	 *  사용자 정보값을 반환합니다.(캐시)
	 * @param id_emp
	 * @param type
	 * @return
	 */
	public String getWorkerInfo(String id_emp, String type);

	/**
	 * 사용자 목록을 반환 합니다.
	 * @param workerForm
	 * @return
	 */
	public List<WorkerInfo> listWorkerInfo(WorkerForm workerForm);

	/**
	 * 직원상태가 정상인 사용자 목록을 반환 합니다.
	 * @param workerForm
	 * @return
	 */
	public List<WorkerInfo> listWorkerInfoStatus();

	/**
	 * 사용자 id 를 입력받아 프로그램 목록을 반환합니다.
	 * @param id_emp
	 * @return
	 */
	public List<ProgramManageVO> listWorkerProgramAuth(String id_emp);

	/**
	 * 사용자 id 를 입력받아 프로그램 목록을 반환합니다.(캐시)
	 * @param id_emp
	 * @return
	 */
	public List<ProgramManageVO> listCacheWorkerProgramAuth(String id_emp);

	/**
	 * 사용자 id 를 입력받아 결재권한 목록을 반환합니다.
	 * @param id_emp
	 * @return
	 */
	public List<ApprovalManageVO> listWorkerApprovalAuth(String id_emp);

	/**
	 * 사용자 id 를 입력받아 결재권한 목록을 반환합니다.(캐시)
	 * @param id_emp
	 * @return
	 */
	public List<ApprovalManageVO> listCacheWorkerApprovalAuth(String id_emp);

	/**
	 * cd_template_group(code_value)으로 프로그램 목록 조회
	 * @param code_value
	 * @return
	 */
	public List<ProgramManageVO> listProgramTemplate(String code_value);

	/**
	 * cd_template_group(code_value)으로 권한 목록 조회
	 * @param code_value
	 * @return
	 */
	public List<ApprovalManageVO> listApprovalTemplate(String code_value);


	/**
	 * Worker 캐시 초기화
	 */
	public void clearCacheWorker();

	/**
	 * Worker 관련 캐시 초기화
	 */
	public void clearCacheWorkerAuth();

	/**
	 * 사용자 등록
	 * @param workerForm
	 * @return
	 */
	public ReturnClass createWorker(WorkerVO workerVO);

	/**
	 * 사용자 정보 변경
	 * @param workerForm
	 * @return
	 */
	public ReturnClass modifyWorker(WorkerVO workerVO);

	/**
	 * 사용자 정보 삭제
	 * @param FincorpVO
	 * @return
	 */
	ReturnClass deleteWorker(WorkerVO workerVO);


	/**
	 * 사용자 프로그램 권한 추가
	 * @param pmForm
	 * @return
	 */
	public ReturnClass createWorkerProgramAuth(ProgramManageForm pmForm);

	/**
	 * 사용자 프로그램 권한 삭제
	 * @param pmForm
	 * @return
	 */
	public ReturnClass deleteWorkerProgramAuth(ProgramManageForm pmForm);

	/**
	 * 사용자 결재권한 추가
	 * @param amForm
	 * @return
	 */
	public ReturnClass createWorkerApprovalAuth(ApprovalManageForm amForm);

	/**
	 * 사용자 결재권한 삭제
	 * @param amForm
	 * @return
	 */
	public ReturnClass deleteWorkerApprovalAuth(ApprovalManageForm amForm);

	/**
	 * 템플릿 프로그램 권한 추가
	 * @param pmForm
	 * @return
	 */
	public ReturnClass createProgramTemplate(ProgramManageForm pmForm);

	/**
	 * 템플릿 프로그램 권한 삭제
	 * @param pmForm
	 * @return
	 */
	public ReturnClass deleteProgramTemplate(ProgramManageForm pmForm);

	/**
	 * 템플릿 결제 권한 추가
	 * @param amForm
	 * @return
	 */
	public ReturnClass createApprovalTemplate(ApprovalManageForm amForm);

	/**
	 * 템플릿 결제 권한 삭제
	 * @param amForm
	 * @return
	 */
	public ReturnClass deleteApprovalTemplate(ApprovalManageForm amForm);

	/**
	 * 사용자 비밀번호 md5 변환
	 * @param pass_emp
	 * @return
	 */
	public String getMD5Pass(String pass_emp);

	/**
	 * 로그인 이력 저장
	 * @param workerLoginHist
	 */
	public void insertWorkerLoginHist(WorkerLoginHist workerLoginHist);

	/**
	 * 결재권한을 입력받아 사용자 id 목록을 반환합니다.(캐시)
	 * @param idAppr
	 * @return
	 */
	public List<ApprovalManageVO> listCacheApprovalAuth(String idAppr);

	/**
	 * 사용자 ID에 결재권한에 대한 권한이 있는지 확인
	 * @param idEmp
	 * @param idAppr
	 * @return
	 */
	public boolean getCachWorkerApprovalAuth(String idEmp, String idAppr);

	/**
	 * 사용자 직접 정보수정
	 * @param workerVO
	 * @return
	 */
	public ReturnClass modifyWorkerUser(WorkerVO workerVO);

	/**
	 * 사용자 비밀번호 초기화
	 * @param workerVO
	 * @return
	 */
	public ReturnClass initWorkerPass(WorkerVO workerVO);

	/**
	 * 사용자 비밀번호 유효성검사
	 * @param id_user
	 * @param password
	 * @return
	 */
	public ReturnClass chkPassword(String id_user, String password);

	/**
	 * 로그인 이력 리스트 조회
	 * @param workerForm
	 * @return
	 */
	public List<WorkerLoginHist> listWorkerLoginHist(WorkerForm workerForm);

	/**
	 * 로그인 이력 카운트 조회
	 * @param workerForm
	 * @return
	 */
	public int listWorkerLoginHistCount(WorkerForm workerForm);

	/**
	 * 권한부여 이력 리스트 조회
	 * @param workerForm
	 * @return
	 */
	public List<WorkerAuthHist> listWorkerAuthHist(WorkerForm workerForm);

	/**
	 * 권한부여 이력 카운트 조회
	 * @param workerForm
	 * @return
	 */
	public int listWorkerAuthHistCount(WorkerForm workerForm);

	/**
	 * 로그인 이력조회(개인)
	 * @param id_emp
	 * @return
	 */
	public List<WorkerLoginHist> listWorkerLoginRecent(String id_emp);

	/**
	 * 직통번호로 사용자 조회
	 * @param in_number
	 * @return
	 */
	public WorkerVO getWorkerInfoDirect(String direct_number);

	/**
	 * DB 암호화 비밀번호 조회
	 * @param pwd
	 * @return
	 */
	public String getPwdDB(String pwd);
}
