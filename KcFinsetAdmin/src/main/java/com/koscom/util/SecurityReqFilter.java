package com.koscom.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import com.koscom.env.model.ApprovalManage;
import com.koscom.env.model.ApprovalManageVO;
import com.koscom.env.model.ProgramManage;
import com.koscom.env.model.ProgramManageVO;
import com.koscom.env.service.IpManager;
import com.koscom.worker.service.WorkerManager;

public class SecurityReqFilter extends OncePerRequestFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityReqFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		// 사용자의 정보를 가져옴
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null)
		{
			/*
			 * URI action 필터
			 *  기본적으로 모든 페이지를 접근 가능합니다.
			 *  단, 프로그램 권한 및 결재권한으로 정의된 action 일 경우, 해당권한을 보유하고 있지 않으면 페이지를 접근할 수 없습니다.
			 *  보안을 강화하고자 하는 URI 에 대해서는 action 값을 권한으로 등록 후 이용하여야 합니다.
			 */
			boolean isAllowedAction = true;
			
			/*
			 * 요청 URI .crz .json 앞 부분 추출
			 */
			UrlPathHelper urlPathHelper=new UrlPathHelper();
			String req_action = StringUtil.nullToString(urlPathHelper.getRequestUri(request), "Request URI is empty.").split("\\.")[0];
			
			HashMap<String, ProgramManage> map = Constant.PROGRAM;
			Iterator<String> iterator = map.keySet().iterator();
			while(iterator.hasNext())
			{
				String id_program = iterator.next();
				ProgramManage pm = map.get(id_program);
				/*
				 * 프로그램목록 URI .crz .json 앞 부분 추출
				 */
				String pm_action = urlPathHelper.getContextPath(request)+StringUtil.nullToString(pm.getNm_action(), "Program URI is empty.").split("\\.")[0];
				// 프로그램에 등록된 액션일때만 검증
				if(req_action.equals(pm_action))
				{
					// 사용자가 갖고있는 권한이 아니면 권한없음
					if(!getWorkerProgramAuth(authentication.getName(), id_program))
					{
						isAllowedAction = false;
					}
				}
			}
			
			HashMap<String, ApprovalManage> mapAppr = Constant.APPROVAL;
			Iterator<String> iteratorAppr = mapAppr.keySet().iterator();
			while(iteratorAppr.hasNext())
			{
				String id_appr = iteratorAppr.next();
				ApprovalManage am = mapAppr.get(id_appr);
				/*
				 * 권한목록 URI .crz .json 앞 부분 추출
				 */
				String am_action = urlPathHelper.getContextPath(request)+StringUtil.nullToString(am.getNm_action(), "Approval URI is empty.").split("\\.")[0];
				// 결제권한에 등록된 액션일때만 검증
				if(req_action.equals(am_action))
				{
					// 사용자가 갖고있는 권한이 아니면 권한없음
					if(!getWorkerApprovalAuth(authentication.getName(), id_appr))
					{
						isAllowedAction = false;
					}
				}
			}
			
			if (!isAllowedAction) {
				logger.warn("[[ ! 허용되지 않은 권한으로 URI 접근 ! : "+authentication.getName()+" : "+urlPathHelper.getRequestUri(request)+" ]]");
				throw new AccessDeniedException(urlPathHelper.getRequestUri(request) + "은 접근이 허용되지 않은 URI 입니다");
			}
			
			logger.debug("[[ ! URI 허용 ! : "+authentication.getName()+" : "+urlPathHelper.getRequestUri(request)+" ]]");
			
			boolean isAllowedIpAddress = false;
			String ipAddr = request.getRemoteAddr();
			String authorities = "";
			
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				authorities = authority.toString();
			}
			
			if(Constant.SECURITY_ROLE.equals(authorities)){
				// IP 주소 체크.(ADMIN)
				isAllowedIpAddress = Constant.getIsAllowedIpAddress(ipAddr, isAllowedIpAddress);
			}else{
				IpManager ipManager = (IpManager) SpringApplicationContext.getBean("ipManager");
				
				// IP 주소 체크.(USER)
				isAllowedIpAddress = ipManager.isVaildIp(Constant.AG, ipAddr);
			}
			
			// IP 주소 체크에서 통과하지 못했을 경우,
			if (!isAllowedIpAddress){
				logger.warn("[[ ! 허용되지 않은 IP로 접근 ! : "+authentication.getName()+" : "+request.getRemoteAddr()+" ]]");
				throw new AccessDeniedException(request.getRemoteAddr() + "은 접근이 허용되지 않은 IP 주소입니다");
			}
			
			logger.debug("[[ ! IP 허용 ! : "+authentication.getName()+" : "+request.getRemoteAddr()+" ]]");
			
			/*
			 *  비밀번호와 ID가 같아 차단된 ID 인지 체크
			 *  변경화면 과 변경 action URI 외에 모두 차단 함.
			 */
			if( hmBlock.get(authentication.getName()) != null
					&& !req_action.contains("changePasswd")
					&& !req_action.contains("modifyWorkerUser") )
			{
				response.sendRedirect(request.getContextPath()+"/changePasswd.crz");
				return;
			}
			
		} else {
			logger.warn("[[ ! 인증되지 않은 사용자 접근 ! : "+request.getRemoteAddr() +" "+ request.getRequestURI() +" ]]");
			throw new AccessDeniedException("인증되지 않은 사용자입니다");
		}

		filterChain.doFilter(request, response);
		
	}

	private boolean getWorkerProgramAuth(String id_emp, String id_program) {
		boolean auth = false;
		if ( StringUtil.isEmpty(id_emp) || StringUtil.isEmpty(id_program) ) 
			return auth;
		
		WorkerManager workerManager = (WorkerManager) SpringApplicationContext.getBean("workerManager");
		List<ProgramManageVO> listProgram = workerManager.listCacheWorkerProgramAuth(id_emp);
		for (ProgramManageVO programManageVO : listProgram) {
			if(id_program.equals(programManageVO.getId_program())) {
				auth = true;
				break;
			}
		}
		
		return auth;
	}
	
	private boolean getWorkerApprovalAuth(String id_emp, String id_appr) {
		boolean auth = false;
		if ( StringUtil.isEmpty(id_emp) || StringUtil.isEmpty(id_appr) ) 
			return auth;
		
		WorkerManager workerManager = (WorkerManager) SpringApplicationContext.getBean("workerManager");
		List<ApprovalManageVO> listAppr = workerManager.listCacheWorkerApprovalAuth(id_emp);
		for (ApprovalManageVO approvalManageVO : listAppr) {
			if(id_appr.equals(approvalManageVO.getId_appr())) {
				auth = true;
				break;
			}
		}
		
		return auth;
	}
	
	private HashMap<String, String> hmBlock = new HashMap<String, String>();
	
	public void setBlockUser(String id_emp) {
		hmBlock.put(id_emp, id_emp);
	}
	
	public void removeBlockUser(String id_emp) {
		hmBlock.remove(id_emp);
	}
}
