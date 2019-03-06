package com.koscom.worker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.WorkerAuthHist;
import com.koscom.domain.WorkerInfo;
import com.koscom.domain.WorkerLoginHist;
import com.koscom.env.model.ApprovalManageForm;
import com.koscom.env.model.ApprovalManageVO;
import com.koscom.env.model.ProgramManageForm;
import com.koscom.env.model.ProgramManageVO;
import com.koscom.util.ReturnClass;
import com.koscom.worker.model.WorkerForm;
import com.koscom.worker.model.WorkerVO;
import com.koscom.worker.service.WorkerManager;
import com.koscom.worker.service.WorkerWebManager;

@Service("workerWebManager")
public class WorkerWebManagerImpl implements WorkerWebManager{
	
	@Autowired
	private WorkerManager workerManager;
	
	@Override
	public String getWorkerInfo(String id_emp, String type) {
		return workerManager.getWorkerInfo(id_emp, type);
	}

	@Override
	public void insertWorkerLoginHist(WorkerLoginHist workerLoginHist) {
		workerManager.insertWorkerLoginHist(workerLoginHist);
	}

	@Override
	public List<WorkerLoginHist> listWorkerLoginRecent(String id_emp) {
		return workerManager.listWorkerLoginRecent(id_emp);
	}

	@Override
	public boolean getCachWorkerApprovalAuth(String idEmp, String idAppr) {
		return workerManager.getCachWorkerApprovalAuth(idEmp, idAppr);
	}

	@Override
	public WorkerVO getWorkerInfo(String id_emp) {
		// TODO Auto-generated method stub
		return workerManager.getWorkerInfo(id_emp);
	}

	@Override
	public WorkerVO getCacheWorkerInfo(String id_emp) {
		// TODO Auto-generated method stub
		return workerManager.getCacheWorkerInfo(id_emp);
	}

	@Override
	public List<WorkerInfo> listWorkerInfo(WorkerForm workerForm) {
		// TODO Auto-generated method stub
		return workerManager.listWorkerInfo(workerForm);
	}

	@Override
	public List<WorkerInfo> listWorkerInfoStatus() {
		// TODO Auto-generated method stub
		return workerManager.listWorkerInfoStatus();
	}

	@Override
	public List<ProgramManageVO> listWorkerProgramAuth(String id_emp) {
		// TODO Auto-generated method stub
		return workerManager.listWorkerProgramAuth(id_emp);
	}

	@Override
	public List<ProgramManageVO> listCacheWorkerProgramAuth(String id_emp) {
		// TODO Auto-generated method stub
		return workerManager.listCacheWorkerProgramAuth(id_emp);
	}

	@Override
	public List<ApprovalManageVO> listWorkerApprovalAuth(String id_emp) {
		// TODO Auto-generated method stub
		return workerManager.listWorkerApprovalAuth(id_emp);
	}

	@Override
	public List<ApprovalManageVO> listCacheWorkerApprovalAuth(String id_emp) {
		// TODO Auto-generated method stub
		return workerManager.listCacheWorkerApprovalAuth(id_emp);
	}

	@Override
	public List<ProgramManageVO> listProgramTemplate(String code_value) {
		// TODO Auto-generated method stub
		return workerManager.listProgramTemplate(code_value);
	}

	@Override
	public List<ApprovalManageVO> listApprovalTemplate(String code_value) {
		// TODO Auto-generated method stub
		return workerManager.listApprovalTemplate(code_value);
	}

	@Override
	public void clearCacheWorker() {
		// TODO Auto-generated method stub
		workerManager.clearCacheWorker();
	}

	@Override
	public void clearCacheWorkerAuth() {
		// TODO Auto-generated method stub
		workerManager.clearCacheWorkerAuth();
	}

	@Override
	public ReturnClass createWorker(WorkerVO workerVO) {
		// TODO Auto-generated method stub
		return workerManager.createWorker(workerVO);
	}

	@Override
	public ReturnClass modifyWorker(WorkerVO workerVO) {
		// TODO Auto-generated method stub
		return workerManager.modifyWorker(workerVO);
	}

	@Override
	public ReturnClass deleteWorker(WorkerVO workerVO) {
		// TODO Auto-generated method stub
		return workerManager.deleteWorker(workerVO);
	}

	@Override
	public ReturnClass createWorkerProgramAuth(ProgramManageForm pmForm) {
		// TODO Auto-generated method stub
		return workerManager.createWorkerProgramAuth(pmForm);
	}

	@Override
	public ReturnClass deleteWorkerProgramAuth(ProgramManageForm pmForm) {
		// TODO Auto-generated method stub
		return workerManager.deleteWorkerProgramAuth(pmForm);
	}

	@Override
	public ReturnClass createWorkerApprovalAuth(ApprovalManageForm amForm) {
		// TODO Auto-generated method stub
		return workerManager.createWorkerApprovalAuth(amForm);
	}

	@Override
	public ReturnClass deleteWorkerApprovalAuth(ApprovalManageForm amForm) {
		// TODO Auto-generated method stub
		return workerManager.deleteWorkerApprovalAuth(amForm);
	}

	@Override
	public ReturnClass createProgramTemplate(ProgramManageForm pmForm) {
		// TODO Auto-generated method stub
		return workerManager.createProgramTemplate(pmForm);
	}

	@Override
	public ReturnClass deleteProgramTemplate(ProgramManageForm pmForm) {
		// TODO Auto-generated method stub
		return workerManager.deleteProgramTemplate(pmForm);
	}

	@Override
	public ReturnClass createApprovalTemplate(ApprovalManageForm amForm) {
		// TODO Auto-generated method stub
		return workerManager.createApprovalTemplate(amForm);
	}

	@Override
	public ReturnClass deleteApprovalTemplate(ApprovalManageForm amForm) {
		// TODO Auto-generated method stub
		return workerManager.deleteApprovalTemplate(amForm);
	}

	@Override
	public String getMD5Pass(String pass_emp) {
		// TODO Auto-generated method stub
		return workerManager.getMD5Pass(pass_emp);
	}

	@Override
	public List<ApprovalManageVO> listCacheApprovalAuth(String idAppr) {
		// TODO Auto-generated method stub
		return workerManager.listCacheApprovalAuth(idAppr);
	}

	@Override
	public ReturnClass modifyWorkerUser(WorkerVO workerVO) {
		// TODO Auto-generated method stub
		return workerManager.modifyWorkerUser(workerVO);
	}

	@Override
	public ReturnClass initWorkerPass(WorkerVO workerVO) {
		// TODO Auto-generated method stub
		return workerManager.initWorkerPass(workerVO);
	}

	@Override
	public ReturnClass chkPassword(String id_user, String password) {
		// TODO Auto-generated method stub
		return workerManager.chkPassword(id_user, password);
	}

	@Override
	public List<WorkerLoginHist> listWorkerLoginHist(WorkerForm workerForm) {
		// TODO Auto-generated method stub
		return workerManager.listWorkerLoginHist(workerForm);
	}

	@Override
	public int listWorkerLoginHistCount(WorkerForm workerForm) {
		// TODO Auto-generated method stub
		return  workerManager.listWorkerLoginHistCount(workerForm);
	}

	@Override
	public List<WorkerAuthHist> listWorkerAuthHist(WorkerForm workerForm) {
		// TODO Auto-generated method stub
		return workerManager.listWorkerAuthHist(workerForm);
	}

	@Override
	public int listWorkerAuthHistCount(WorkerForm workerForm) {
		// TODO Auto-generated method stub
		return  workerManager.listWorkerAuthHistCount(workerForm);
	}

	@Override
	public WorkerVO getWorkerInfoDirect(String direct_number) {
		// TODO Auto-generated method stub
		return workerManager.getWorkerInfoDirect(direct_number);
	}
	
	@Override
	public String getPwdDB(String pwd) {
		// TODO Auto-generated method stub
		return workerManager.getPwdDB(pwd);
	}

}
