package com.koscom.commission;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.commission.model.CommissionForm;
import com.koscom.commission.model.CommissionVO;
import com.koscom.commission.service.CommissionManager;
import com.koscom.counsel.model.CounselVO;
import com.koscom.util.Pagination;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;
import com.koscom.worker.model.WorkerVO;
import com.koscom.worker.service.WorkerManager;


@Controller
@RequestMapping("/commission")
public class CommissionController {

	private static final Logger logger = LoggerFactory.getLogger(CommissionController.class);
	
	@Autowired
	private CommissionManager commissionManager;
	
	@Autowired
	private WorkerManager workerManager;
	
	/**
	 * 수수료관리 메인
	 * @param commissionForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listCommissionMain.crz")
	public String listCommissionMain(HttpServletRequest request, CommissionForm commissionForm, Model model){
		SessionUtil session = new SessionUtil(request);
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		model.addAttribute("workerVO", workerVO);
		return "/commission/listCommissionMain";
	}
	
	/**
	 * 수수료 리스트
	 * @param commissionForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listCommission.crz")
	public String listCommission(HttpServletRequest request, CommissionForm commissionForm, Model model){
		SessionUtil session = new SessionUtil(request);
		logger.info(session.getUserId());
		
		Pagination pagedList = (Pagination) commissionForm.setPagedList(commissionManager.listCommissionInfo(commissionForm), commissionManager.listCommissionCount(commissionForm));
		model.addAttribute("pagedList", pagedList);
		
		return "/commission/listCommission";
	}
	
	/**
	 * 수수료 수정 폼
	 * @param commissionForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/formCommissionInfo.crz")
	public String formCommissionInfo(HttpServletRequest request, CommissionForm commissionForm, Model model){
		SessionUtil session = new SessionUtil(request);
		logger.info(session.getUserId());
		
		logger.info("commissionForm.getNo_apply() : "+commissionForm.getNo_apply());
		CommissionVO commissionInfo = new CommissionVO();
		commissionInfo = commissionManager.getCommissionInfo(commissionForm.getNo_apply());
		if(commissionInfo == null ){
			model.addAttribute("no_apply", commissionForm.getNo_apply());
		} else {
			model.addAttribute("commissionInfo", commissionInfo);
			logger.info("============ formCommissionInfo commissionInfo 확인=============");
			logger.info(commissionInfo.toString());
		}
		
		return "/commission/formCommissionDetail";
	}
	
	/**
	 * 수수료 상세정보 변경
	 * @param request
	 * @param commissionVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/procCommissionDetails.json")
	public String procCommissionDetails(HttpServletRequest request, CommissionVO commissionVO, Model model){
		logger.info("============ modifyCommissionDetails commissionVO확인=============");
		logger.info(commissionVO.toString());
		
		ReturnClass returnClass = new ReturnClass();
		SessionUtil session = new SessionUtil(request);
		commissionVO.setId_lst(session.getUserId());
		
		returnClass = commissionManager.procCommissionDetails(commissionVO);
		
		model.addAttribute("result", returnClass.getCd_result());
		return "jsonView";
	}
	
	/**
	 * 수수료 정보 삭제
	 * @param counselVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/delCommissionDetails.json")
	public String delCounselInfo(CommissionVO commissionVO, Model model) {
		logger.info("============ delCommissionDetails commissionVO확인=============");
		logger.info(commissionVO.toString());
		
		ReturnClass returnClass = commissionManager.delCommissionDetails(commissionVO);
		model.addAttribute("returnData", returnClass);
		return "jsonView";
	}
}
