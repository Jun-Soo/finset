package com.koscom.memo;

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

@Controller
@RequestMapping("/m/memo")
public class MemoController {

	private static final Logger logger = LoggerFactory.getLogger(MemoController.class);
	
	@Autowired
	MemoManager memoManager;
	
	@RequestMapping("/createMemo.json")
	public String createMemo(HttpSession session, Model model, HttpServletRequest request) throws FinsetException {
		String no_person = (String) session.getAttribute("no_person");
		String no_manage_info = request.getParameter("no_manage_info");
		String memo_text = request.getParameter("memo_text");
		
		MemoVO memoVO = new MemoVO();
		memoVO.setNo_person(no_person);
		if(no_manage_info!=null) {
			if(!no_manage_info.equals("")) {
				memoVO.setNo_manage_info(no_manage_info);
			}
		}
		memoVO.setMemo_text(memo_text);
		memoManager.createMemo(memoVO);
		model.addAttribute("code","00");
		return "jsonView";
	}
	
	@RequestMapping("/listMemo.json")
	public String listMemo(HttpSession session, Model model, MemoVO memoVO) {
		String no_person = (String) session.getAttribute("no_person");
		memoVO.setRecordCount(5);
		memoVO.setNo_person(no_person);
		model.addAttribute("listMemo",memoManager.listMemo(memoVO));
		return "jsonView";
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
}
