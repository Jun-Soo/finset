package com.koscom.worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.env.model.ApprovalManage;
import com.koscom.env.model.ApprovalManageForm;
import com.koscom.env.model.ApprovalManageVO;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.model.CodeVO;
import com.koscom.env.model.ProgramManage;
import com.koscom.env.model.ProgramManageForm;
import com.koscom.env.model.ProgramManageVO;
import com.koscom.env.service.CodeManager;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.util.Constant;
import com.koscom.util.Pagination;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;
import com.koscom.util.StringUtil;
import com.koscom.worker.model.WorkerForm;
import com.koscom.worker.model.WorkerVO;
import com.koscom.worker.service.WorkerManager;

@Controller
@RequestMapping("/worker")
public class WorkerController {

	private static final Logger logger = LoggerFactory.getLogger(WorkerController.class);

	@Autowired
	WorkerManager workerManager;
	
	@Autowired
	FincorpManager fincorpManager;

	@Autowired
	CodeManager codeManager;

	/**
	 * 사용자 관리 메인 페이지
	 * 
	 * @param workerForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/workerMain.crz")
	public String workerMain(WorkerForm workerForm, Model model, HttpServletRequest request) {

		try {
			logger.info("==== workerMain ====");
			model.addAttribute("List", workerManager.listWorkerInfo(workerForm));
			model.addAttribute("templateList", codeManager.listCodeInfo("cd_template_group"));
			
			//현로그인 사용자 ID 넘겨주기
			SessionUtil session = new SessionUtil(request);
			String userId = session.getUserId();
			model.addAttribute("userId", userId);
			logger.info("userId ==== " + userId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		

		return "/worker/workerMain";
	}

	/**
	 * 사용자 목록조회
	 * 
	 * @param workerForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listWorker.crz")
	public String listWorker(WorkerForm workerForm, Model model) {

		model.addAttribute("List", workerManager.listWorkerInfo(workerForm));

		return "/worker/listWorker";
	}

	/**
	 * 사용자 정보 수정 폼
	 * 
	 * @param workerForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/getWorkerForm.crz")
	public String getWorkerForm(WorkerVO workerVO, Model model) {

		if (!StringUtil.isEmpty(workerVO.getId_emp()))
			model.addAttribute("worker", workerManager.getWorkerInfo(workerVO.getId_emp()));

		return "/worker/formWorker";
	}
	
	/**
	 * 사용자 정보 수정 폼
	 * 
	 * @param workerForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/getListFc.json")
	public String getListFc(WorkerVO workerVO, Model model) {

		logger.info("!!!!!!!!!!!!! : " + workerVO.toString());
		List<FincorpVO> fincorpVO = null; 
		List<String> sCdFc = null;
		if (!StringUtil.isEmpty(workerVO.getCd_fin())){
			sCdFc = fincorpManager.getFcInfo(workerVO.getCd_fin());
			logger.info("!!!!!!!!!!!!!!!!! : " + sCdFc);
			
		}
		model.addAttribute("List", sCdFc);
		return "jsonView";
	}

	/**
	 * 사용자 권한 변경 폼
	 * 
	 * @param workerForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/getWorkerAuth.crz")
	public String getWorkerAuth(HttpServletRequest request, WorkerVO workerVO, Model model) {
		SessionUtil session = new SessionUtil(request);

		if (!StringUtil.isEmpty(workerVO.getId_emp())) {
			/*
			 * 프로그램 권한
			 */
			List<ProgramManage> program = new ArrayList<ProgramManage>();
			HashMap<String, ProgramManage> mapProgram = Constant.PROGRAM;
			Iterator<String> itrProgram = mapProgram.keySet().iterator();
			while (itrProgram.hasNext()) {
				String key = itrProgram.next();

				// admin 권한의 메뉴는 접속권한자에 따라 보이도록 설정
				if (!"ROLE_ADMIN".equals(session.getAuthority())) {
					CodeInfo code = codeManager.getCodeInfo("cd_admin_auth", key);
					if (code != null && "Y".equals(code.getYn_use()))
						continue;
				}

				program.add(mapProgram.get(key));
			}
			model.addAttribute("program", program);

			List<ProgramManageVO> workerProgramList = workerManager.listWorkerProgramAuth(workerVO.getId_emp());
			model.addAttribute("workerProgramList", workerProgramList);

			/*
			 * 결재 권한
			 */
			List<ApprovalManage> approval = new ArrayList<ApprovalManage>();
			HashMap<String, ApprovalManage> mapApproval = Constant.APPROVAL;
			Iterator<String> itrApproval = mapApproval.keySet().iterator();
			while (itrApproval.hasNext()) {
				String key = itrApproval.next();

				// admin 권한의 메뉴는 접속권한자에 따라 보이도록 설정
				if (!"ROLE_ADMIN".equals(session.getAuthority())) {
					CodeInfo code = codeManager.getCodeInfo("cd_admin_auth", key);
					if (code != null && "Y".equals(code.getYn_use()))
						continue;
				}

				approval.add(mapApproval.get(key));
			}
			model.addAttribute("approval", approval);

			List<ApprovalManageVO> workerApprovalList = workerManager.listWorkerApprovalAuth(workerVO.getId_emp());
			model.addAttribute("workerApprovalList", workerApprovalList);

		}
		model.addAttribute("worker", workerVO);

		return "/worker/workerAuth";
	}

	/**
	 * 사용자 템플릿 관리 폼
	 * 
	 * @param request
	 * @param codeVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/getWorkerTemplate.crz")
	public String getWorkerTemplate(HttpServletRequest request, CodeVO codeVO, Model model) {
		SessionUtil session = new SessionUtil(request);

		if (!StringUtil.isEmpty(codeVO.getCode_value())) {
			/*
			 * 프로그램 권한
			 */
			List<ProgramManage> program = new ArrayList<ProgramManage>();
			HashMap<String, ProgramManage> mapProgram = Constant.PROGRAM;
			Iterator<String> itrProgram = mapProgram.keySet().iterator();
			while (itrProgram.hasNext()) {
				String key = itrProgram.next();

				// admin 권한의 메뉴는 접속권한자에 따라 보이도록 설정
				if (!"ROLE_ADMIN".equals(session.getAuthority())) {
					CodeInfo code = codeManager.getCodeInfo("cd_admin_auth", key);
					if (code != null && "Y".equals(code.getYn_use()))
						continue;
				}

				program.add(mapProgram.get(key));
			}
			model.addAttribute("program", program);

			List<ProgramManageVO> programTemplateList = workerManager.listProgramTemplate(codeVO.getCode_value());
			model.addAttribute("programTemplateList", programTemplateList);

			/*
			 * 결재 권한
			 */
			List<ApprovalManage> approval = new ArrayList<ApprovalManage>();
			HashMap<String, ApprovalManage> mapApproval = Constant.APPROVAL;
			Iterator<String> itrApproval = mapApproval.keySet().iterator();
			while (itrApproval.hasNext()) {
				String key = itrApproval.next();

				// admin 권한의 메뉴는 접속권한자에 따라 보이도록 설정
				if (!"ROLE_ADMIN".equals(session.getAuthority())) {
					CodeInfo code = codeManager.getCodeInfo("cd_admin_auth", key);
					if (code != null && "Y".equals(code.getYn_use()))
						continue;
				}

				approval.add(mapApproval.get(key));
			}
			model.addAttribute("approval", approval);

			//List<ApprovalManageVO> approvalTemplateList = workerManager.listApprovalTemplate(codeVO.getCode_value());
			//model.addAttribute("approvalTemplateList", approvalTemplateList);

		}
		model.addAttribute("codeVO", codeVO);

		return "/worker/workerTemplate";
	}

	/**
	 * Worker 관련 캐시 적용(초기화)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/clearCacheWorker.json")
	public String clearCacheWorker(Model model) {
		workerManager.clearCacheWorkerAuth();

		return "jsonView";
	}

	/**
	 * 사용자 등록
	 * 
	 * @param workerForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/createWorker.json")
	public String createWorker(HttpServletRequest request, WorkerVO workerVO, Model model) {
		SessionUtil session = new SessionUtil(request);
		workerVO.setId_frt(session.getUserId());
		workerVO.setId_lst(session.getUserId());

		ReturnClass returnClass = workerManager.createWorker(workerVO);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 사용자 정보 수정
	 * 
	 * @param workerForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifyWorker.json")
	public String modifyWorker(HttpServletRequest request, WorkerVO workerVO, Model model) {
		SessionUtil session = new SessionUtil(request);
		workerVO.setId_frt(session.getUserId());
		workerVO.setId_lst(session.getUserId());

		ReturnClass returnClass = workerManager.modifyWorker(workerVO);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 사용자 정보 삭제
	 * 
	 * @param workerForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteWorker.json")
	public String deleteWorker(WorkerVO workerVO, Model model, HttpServletRequest request) {

		ReturnClass returnClass = null;

		// 세션
		SessionUtil session = new SessionUtil(request);
		String userId = session.getUserId();

		returnClass = workerManager.deleteWorker(workerVO);
		model.addAttribute("returnData", returnClass);
		logger.info("userId : "+userId);
		logger.info("id_emp : "+workerVO.getId_emp());

		if (userId.equals(workerVO.getId_emp())) {
			model.addAttribute("logout", "Y");
		} else{
			model.addAttribute("logout", "N");
		}
		return "jsonView";
	}

	/**
	 * 프로그램 권한 추가
	 * 
	 * @param pmForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/createWorkerProgramAuth.json")
	public String createWorkerProgramAuth(HttpServletRequest request, ProgramManageForm pmForm, Model model) {
		SessionUtil session = new SessionUtil(request);
		pmForm.setId_frt(session.getUserId());
		pmForm.setId_lst(session.getUserId());

		ReturnClass returnClass = workerManager.createWorkerProgramAuth(pmForm);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 프로그램 권한 삭제
	 * 
	 * @param pmForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteWorkerProgramAuth.json")
	public String deleteWorkerProgramAuth(HttpServletRequest request, ProgramManageForm pmForm, Model model) {
		SessionUtil session = new SessionUtil(request);
		pmForm.setId_frt(session.getUserId());
		pmForm.setId_lst(session.getUserId());

		ReturnClass returnClass = workerManager.deleteWorkerProgramAuth(pmForm);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 결재권한 추가
	 * 
	 * @param amForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/createWorkerApprovalAuth.json")
	public String createWorkerApprovalAuth(HttpServletRequest request, ApprovalManageForm amForm, Model model) {
		SessionUtil session = new SessionUtil(request);
		amForm.setId_frt(session.getUserId());
		amForm.setId_lst(session.getUserId());

		ReturnClass returnClass = workerManager.createWorkerApprovalAuth(amForm);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 결재권한 삭제
	 * 
	 * @param amForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteWorkerApprovalAuth.json")
	public String deleteWorkerApprovalAuth(HttpServletRequest request, ApprovalManageForm amForm, Model model) {
		SessionUtil session = new SessionUtil(request);
		amForm.setId_frt(session.getUserId());
		amForm.setId_lst(session.getUserId());

		ReturnClass returnClass = workerManager.deleteWorkerApprovalAuth(amForm);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 템플릿 프로그램 권한 추가
	 * 
	 * @param request
	 * @param pmForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/createProgramTemplate.json")
	public String createProgramTemplate(HttpServletRequest request, ProgramManageForm pmForm, Model model) {
		SessionUtil session = new SessionUtil(request);
		pmForm.setId_frt(session.getUserId());
		pmForm.setId_lst(session.getUserId());

		ReturnClass returnClass = workerManager.createProgramTemplate(pmForm);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 템플릿 프로그램 권한 삭제
	 * 
	 * @param request
	 * @param pmForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteProgramTemplate.json")
	public String deleteProgramTemplate(HttpServletRequest request, ProgramManageForm pmForm, Model model) {
		SessionUtil session = new SessionUtil(request);
		pmForm.setId_frt(session.getUserId());
		pmForm.setId_lst(session.getUserId());

		ReturnClass returnClass = workerManager.deleteProgramTemplate(pmForm);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 템플릿 결제 권한 추가
	 * 
	 * @param request
	 * @param amForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/createApprovalTemplate.json")
	public String createApprovalTemplate(HttpServletRequest request, ApprovalManageForm amForm, Model model) {
		SessionUtil session = new SessionUtil(request);
		amForm.setId_frt(session.getUserId());
		amForm.setId_lst(session.getUserId());

		ReturnClass returnClass = workerManager.createApprovalTemplate(amForm);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 템플릿 결제 권한 삭제
	 * 
	 * @param request
	 * @param amForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteApprovalTemplate.json")
	public String deleteApprovalTemplate(HttpServletRequest request, ApprovalManageForm amForm, Model model) {
		SessionUtil session = new SessionUtil(request);
		amForm.setId_frt(session.getUserId());
		amForm.setId_lst(session.getUserId());

		ReturnClass returnClass = workerManager.deleteApprovalTemplate(amForm);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 사용자 직접 정보수정 폼
	 * 
	 * @param request
	 * @param workerVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifyWorkerUser.crz")
	public String modifyWorkerUserMain(HttpServletRequest request, WorkerVO workerVO, Model model) {
		SessionUtil session = new SessionUtil(request);
		workerVO.setId_emp(session.getUserId());

		if (!StringUtil.isEmpty(workerVO.getId_emp())) {
			model.addAttribute("worker", workerManager.getWorkerInfo(workerVO.getId_emp()));
			model.addAttribute("loginList", workerManager.listWorkerLoginRecent(workerVO.getId_emp()));
		}

		return "/worker/formWorkerUser";
	}

	/**
	 * 사용자 직접 정보수정
	 * 
	 * @param request
	 * @param workerVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifyWorkerUser.json")
	public String modifyWorkerUser(HttpServletRequest request, WorkerVO workerVO, Model model) {
		SessionUtil session = new SessionUtil(request);
		workerVO.setId_lst(session.getUserId());
		workerVO.setId_emp(session.getUserId());

		ReturnClass returnClass = workerManager.modifyWorkerUser(workerVO);
		model.addAttribute("returnData", returnClass);

		// worker 캐시만 초기화함(내선,직통 변경시 즉시 적용)
		workerManager.clearCacheWorker();

		return "jsonView";
	}

	/**
	 * 비밀번호 초기화
	 * 
	 * @param request
	 * @param workerVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/initWorkerPass.json")
	public String initWorkerPass(HttpServletRequest request, WorkerVO workerVO, Model model) {
		SessionUtil session = new SessionUtil(request);
		workerVO.setId_lst(session.getUserId());

		ReturnClass returnClass = workerManager.initWorkerPass(workerVO);
		model.addAttribute("returnData", returnClass);

		return "jsonView";
	}

	/**
	 * 로그인 이력 리스트 메인
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/listWorkerLoginHistMain.crz")
	public String listWorkerLoginHistMain(Model model) {

		return "/worker/listWorkerLoginHistMain";
	}

	/**
	 * 로그인 이력 리스트
	 * 
	 * @param model
	 * @param workerForm
	 * @return
	 */
	@RequestMapping("/listWorkerLoginHist.crz")
	public String listWorkerLoginHist(Model model, WorkerForm workerForm) {

		Pagination pagedList = (Pagination) workerForm.setPagedList(workerManager.listWorkerLoginHist(workerForm),
				workerManager.listWorkerLoginHistCount(workerForm));
		model.addAttribute("pagedList", pagedList);

		return "/worker/listWorkerLoginHist";
	}

	/**
	 * 권한부여 이력 메인
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/listWorkerAuthHistMain.crz")
	public String listWorkerAuthHistMain(Model model) {

		return "/worker/listWorkerAuthHistMain";
	}

	/**
	 * 권한부여 이력 리스트
	 * 
	 * @param model
	 * @param workerForm
	 * @return
	 */
	@RequestMapping("/listWorkerAuthHist.crz")
	public String listWorkerAuthHist(Model model, WorkerForm workerForm) {

		Pagination pagedList = (Pagination) workerForm.setPagedList(workerManager.listWorkerAuthHist(workerForm),
				workerManager.listWorkerAuthHistCount(workerForm));
		model.addAttribute("pagedList", pagedList);

		return "/worker/listWorkerAuthHist";
	}
}
