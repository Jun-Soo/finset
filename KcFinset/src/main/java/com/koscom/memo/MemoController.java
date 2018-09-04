package com.koscom.memo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.memo.model.MemoVO;
import com.koscom.memo.service.MemoManager;
import com.koscom.util.FinsetException;
import com.koscom.util.ResUtil;

@Controller
@RequestMapping("/m/memo")
public class MemoController {

	private static final Logger logger = LoggerFactory.getLogger(MemoController.class);
	
	@Autowired
	MemoManager memoManager;
	
	@RequestMapping("/frameCreateMemo.crz")
	public String frameCreateMemo(HttpSession session, String no_manage_info, Model model) throws FinsetException{
		String no_person = (String) session.getAttribute("no_person");
		logger.debug("In createMemo, NO_PERSON is" + no_person);
		MemoVO memoVO = new MemoVO();
		memoVO.setNo_person(no_person);
		if(no_manage_info!=null){
			if(!no_manage_info.equals("")){
				memoVO.setNo_manage_info(no_manage_info);
			}
		}
		model.addAttribute("memoVO",memoVO);
		return "/memo/frameCreateMemo";
	}
	
	@RequestMapping("/createMemo.crz")
	public String createMemo(HttpServletRequest request, MemoVO memoVO, Model model) throws FinsetException {
		
		//path는 환경에 따라 달라지므로 확인해야 한다.
		String path = ResUtil.getPath(request);
		
		logger.debug("In createMemo, memoVO is "+ memoVO);
		//에러 처리를 하지 않은 이유는 web.xml에서 500에러에 대한 설정이 되어 있기 때문
		memoManager.createMemo(memoVO);
		if(memoVO.getNo_manage_info()!=null&&!memoVO.getNo_manage_info().equals("")){
			return "redirect:"+path+"/m/memo/frameListMemo.crz?no_manage_info="+memoVO.getNo_manage_info();
		}
		else{
			return "redirect:"+path+"/m/memo/frameListMemo.crz";
		}
	}
	
	@RequestMapping("/frameListMemo.crz")
	public String frameListMemo(HttpSession session ,HttpServletRequest request, Model model) throws FinsetException {
		MemoVO memoVO = new MemoVO();
		String no_person = (String) session.getAttribute("no_person");
		logger.debug("In listMemo, NO_PERSON is "+ no_person);
		memoVO.setNo_person(no_person);
		String no_manage_info = (String) request.getParameter("no_manage_info");
		if(no_manage_info != null){
			if(!no_manage_info.equals("")){
				memoVO.setNo_manage_info(no_manage_info);
				model.addAttribute("no_manage_info",no_manage_info);
			}
		}
		List<MemoVO> listMemo = memoManager.listMemo(memoVO);
		model.addAttribute("listMemo", listMemo);
		return "/memo/frameListMemo";
	}
	
	@RequestMapping("/frameDetailMemo.crz")
	public String frameDetailMemo(MemoVO memoVO, Model model) throws FinsetException {
		logger.debug("In read, the memoVO is " + memoVO);
		model.addAttribute("memoVO", memoManager.getMemoDetail(memoVO));
		return "/memo/frameDetailMemo";
	}
	
	@RequestMapping("/updateMemoText.json")
	public String updateMemoText(HttpSession session, MemoVO memoVO, Model model) throws FinsetException {
		logger.debug("In update text, the memoVO is " + memoVO);
		String no_person = (String) session.getAttribute("no_person");
		if(memoVO == null) {
			model.addAttribute("code","99");
		} else{
			memoVO.setNo_person(no_person);
			memoManager.updateMemoText(memoVO);
			model.addAttribute("code","00");
		}
		return "jsonView";
	}
	
	@RequestMapping("/updateMemoAlarm.json")
	public String updateMemoAlarm(MemoVO memoVO, Model model) throws FinsetException {
		logger.debug("In update alarm, the memoVO is " + memoVO);
		if(memoVO == null) {
			model.addAttribute("code","99");
		} else{
			memoManager.updateMemoAlarm(memoVO);
			model.addAttribute("code","00");
		}
		return "jsonView";
	}
	
	@RequestMapping("/frameAlarmMemo")
	public String frameAlarmMemo(MemoVO memoVO, Model model) throws FinsetException {
		logger.debug("In alarmMemo, the memoVO is " + memoVO);
		model.addAttribute("memoVO", memoVO);
		return "/memo/frameAlarmMemo";
	}
	
	@RequestMapping("/delMemo.crz")
	public String delMemo(HttpServletRequest request, MemoVO memoVO, Model model) throws FinsetException {
		String path = ResUtil.getPath(request);
		logger.debug("In delMemo, the memoVO is" + memoVO);
		memoManager.delMemo(memoVO);
		if(memoVO.getNo_manage_info()!=null&&!memoVO.getNo_manage_info().equals("")){
			return "redirect:"+path+"/m/memo/frameListMemo.crz?no_manage_info="+memoVO.getNo_manage_info();
		}
		else{
			return "redirect:"+path+"/m/memo/frameListMemo.crz";
		}
	}
}
