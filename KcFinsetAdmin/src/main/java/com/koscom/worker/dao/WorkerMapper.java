package com.koscom.worker.dao;

import java.util.List;

import com.koscom.domain.WorkerAuthHist;
import com.koscom.domain.WorkerInfo;
import com.koscom.domain.WorkerLoginHist;
import com.koscom.env.model.ApprovalManageForm;
import com.koscom.env.model.ApprovalManageVO;
import com.koscom.env.model.ProgramManageForm;
import com.koscom.env.model.ProgramManageVO;
import com.koscom.worker.model.WorkerForm;
import com.koscom.worker.model.WorkerVO;

/**
 * Worker Dao Interface
 *
 * @author bwko
 *
 */
public interface WorkerMapper {

	/**
	 * 사용자 id 를 입력받아 WorkerInfo 를 반환합니다.
	 * @param id_emp
	 * @return
	 */
	WorkerVO getWorkerInfo(String id_emp);

	/**
	 * 사용자 목록을 반환합니다.
	 * @param workerForm
	 * @return
	 */
	List<WorkerInfo> listWorkerInfo(WorkerForm workerForm);

	/**
	 * 직원상태가 정상인 사용자 목록을 반환 합니다.
	 * @param workerForm
	 * @return
	 */
	List<WorkerInfo> listWorkerInfoStatus();

	/**
	 * 사용자 id 를 입력받아 프로그램 목록을 반환합니다.
	 * @param id_emp
	 * @return
	 */
	List<ProgramManageVO> listWorkerProgramAuth(String id_emp);

	/**
	 * 사용자 id 를 입력받아 결재권한 목록을 반환힙니다.
	 * @param id_emp
	 * @return
	 */
	List<ApprovalManageVO> listWorkerApprovalAuth(String id_emp);

	/**
	 * cd_template_group(code_value)으로 프로그램 목록 조회
	 * @param code_value
	 * @return
	 */
	List<ProgramManageVO> listProgramTemplate(String code_value);

	/**
	 * cd_template_group(code_value)으로 권한 목록 조회
	 * @param code_value
	 * @return
	 */
	List<ApprovalManageVO> listApprovalTemplate(String code_value);

	/**
	 * 사용자 등록
	 * @param workerForm
	 * @return
	 */
	int createWorker(WorkerInfo WorkerInfo);

	/**
	 * 사용자 정보 변경
	 * @param workerForm
	 * @return
	 */
	int modifyWorker(WorkerInfo WorkerInfo);

	/**
	 * 금융사 정보 삭제
	 * @param friendVO
	 * @return
	 */
	int deleteWorker(WorkerInfo WorkerInfo);

	/**
	 * 사용자 프로그램 권한 추가
	 * @param pmForm
	 * @return
	 */
	int createWorkerProgramAuth(ProgramManageForm pmForm);

	/**
	 * 사용자 프로그램 권한 삭제
	 * @param pmForm
	 * @return
	 */
	int deleteWorkerProgramAuth(ProgramManageForm pmForm);

	/**
	 * 사용자 결재권한 추가
	 * @param amForm
	 * @return
	 */
	int createWorkerApprovalAuth(ApprovalManageForm amForm);

	/**
	 * 사용자 결재권한 삭제
	 * @param amForm
	 * @return
	 */
	int deleteWorkerApprovalAuth(ApprovalManageForm amForm);

	/**
	 * 템플릿 프로그램 권한추가
	 * @param pmForm
	 * @return
	 */
	int createProgramTemplate(ProgramManageForm pmForm);

	/**
	 * 템플릿 프로그램 권한삭제
	 * @param pmForm
	 * @return
	 */
	int deleteProgramTemplate(ProgramManageForm pmForm);

	/**
	 * 템플릿 결제 권한추가
	 * @param amForm
	 * @return
	 */
	int createApprovalTemplate(ApprovalManageForm amForm);

	/**
	 * 템플릿 결제 권한삭제
	 * @param amForm
	 * @return
	 */
	int deleteApprovalTemplate(ApprovalManageForm amForm);

	/**
	 * 로그인 이력 저장
	 * @param workerLoginHist
	 */
	void insertWorkerLoginHist(WorkerLoginHist workerLoginHist);

	/**
	 * 결재권한을 입력받아 사용자 id 목록을 반환합니다.(캐시)
	 * @param idAppr
	 * @return
	 */
	List<ApprovalManageVO> listCacheApprovalAuth(String id_appr);

	/**
	 * 사용자 ID에 결재권한에 대한 권한이 있는지 확인
	 * @param amForm
	 * @return
	 */
	String getCachWorkerApprovalAuth(ApprovalManageForm amForm);

	/**
	 * 사용자 직접 정보수정
	 * @param workerVO
	 * @return
	 */
	int modifyWorkerUser(WorkerInfo WorkerInfo);

	/**
	 * 비밀번호 초기화
	 * @param workerVO
	 * @return
	 */
	int initWorkerPass(WorkerVO workerVO);

	/**
	 * 로그인 이력 리스트 조회
	 * @param workerForm
	 * @return
	 */
	List<WorkerLoginHist> listWorkerLoginHist(WorkerForm workerForm);

	/**
	 * 로그인 이력 카운트 조회
	 * @param workerForm
	 * @return
	 */
	int listWorkerLoginHistCount(WorkerForm workerForm);

	/**
	 * 권한부여 히스토리 추가
	 * @param workerAuthHist
	 * @return
	 */
	int createWorkerAuthHist(WorkerAuthHist workerAuthHist);

	/**
	 * 권한부여 이력 리스트 조회
	 * @param workerForm
	 * @return
	 */
	List<WorkerAuthHist> listWorkerAuthHist(WorkerForm workerForm);

	/**
	 * 권한부여 이력 카운트 조회
	 * @param workerForm
	 * @return
	 */
	int listWorkerAuthHistCount(WorkerForm workerForm);

	/**
	 * 로그인 이력조회(개인)
	 * @param id_emp
	 * @return
	 */
	List<WorkerLoginHist> listWorkerLoginRecent(String id_emp);

	/**
	 * 직통번호로 사용자 조회
	 * @param in_number
	 * @return
	 */
	WorkerVO getWorkerInfoDirect(String direct_number);

	/**
	 * DB 암호화 비밀번호 조회
	 * @param pwd
	 * @return
	 */
	String getPwdDB(String pwd);


}