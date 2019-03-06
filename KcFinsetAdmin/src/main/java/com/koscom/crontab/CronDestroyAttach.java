package com.koscom.crontab;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.koscom.apply.model.ApplyVO;
import com.koscom.apply.service.ApplyManager;
import com.koscom.attach.model.AttachVO;
import com.koscom.attach.service.AttachManager;
import com.koscom.env.service.CodeManager;

public class CronDestroyAttach {
	
	private AttachManager attachManager;
	private ApplyManager applyManager;
	private CodeManager codeManager;
	
	public CronDestroyAttach() {
		ApplicationContext context = new ClassPathXmlApplicationContext("conf/application-context.xml");
		attachManager = (AttachManager) context.getBean("attachManager");
		applyManager = (ApplyManager) context.getBean("applyManager");
		codeManager = (CodeManager) context.getBean("codeManager");
	}

	public boolean service() {
		boolean isSuccess = true;
		if("Y".equals(codeManager.getCodeName("_CONF_DESTROY", "YN_USE_ATTACH"))){
			
			int destroyTerm = Integer.parseInt(codeManager.getCodeName("_CONF_DESTROY", "DESTROY_TERM"));
			List<ApplyVO> destroyApply = applyManager.listApplyDestory(destroyTerm);
			AttachVO attachVO = new AttachVO();
			
			//기한지난 첨부파일 삭제
			if(destroyApply != null && destroyApply.isEmpty()){
				for(ApplyVO list : destroyApply){
					attachVO.setNo_apply(list.getNo_apply());
					
					attachManager.delAttachAll(attachVO);
				}
			}
		}
		return isSuccess;
	}
	
}
