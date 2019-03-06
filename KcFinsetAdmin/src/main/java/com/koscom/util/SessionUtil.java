package com.koscom.util;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.koscom.env.model.ApprovalManageVO;
import com.koscom.env.model.ProgramManageVO;
import com.koscom.worker.service.WorkerManager;

public class SessionUtil {
	
	private String Authority;
	private String ID;
	private String[] ARR_PROGRAM;
	private String[] ARR_APPROVAL;
	
	public SessionUtil() {};
	
	public SessionUtil(HttpServletRequest request) {
		
		WorkerManager workerManager = (WorkerManager) SpringApplicationContext.getBean("workerManager");
		
		HttpSession session = request.getSession();
		
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(authentication == null)
			return;
		
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			this.Authority = authority.toString();
		}
		
		this.ID = authentication.getName();
		
		if(session.getAttribute("id_emp") == null) {
			
			session.setAttribute("id_emp", this.ID);
			
			List<ProgramManageVO> listProgram = workerManager.listCacheWorkerProgramAuth(this.ID);
			
			ARR_PROGRAM = new String[listProgram.size()];
			
			for(int i=0;i<listProgram.size();i++) {
				ARR_PROGRAM[i] = listProgram.get(i).getId_program();
			}
			session.setAttribute("ARR_PROGRAM", ARR_PROGRAM);
			session.setAttribute("STR_PROGRAM", Arrays.toString(ARR_PROGRAM));
			
			
			
			List<ApprovalManageVO> listApproval = workerManager.listCacheWorkerApprovalAuth(this.ID);
			
			ARR_APPROVAL = new String[listApproval.size()];
			
			for(int i=0;i<listApproval.size();i++) {
				ARR_APPROVAL[i] = listApproval.get(i).getId_appr();
			}
			session.setAttribute("ARR_APPROVAL", ARR_APPROVAL);
			session.setAttribute("STR_APPROVAL", Arrays.toString(ARR_APPROVAL));
		}
		
	}
	
	public String getUserId() {
		return this.ID;
	}
	
	public String getAuthority() {
		return this.Authority;
	}

}
