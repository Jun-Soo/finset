package com.koscom.goodsbank;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.goodsbank.model.GoodsbankVO;
import com.koscom.goodsbank.service.GoodsbankManager;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;


@Controller
@RequestMapping("/m/goodsbank")
public class GoodsbankController {
	private static final Logger logger = LoggerFactory.getLogger(GoodsbankController.class);
	
	@Autowired
	private GoodsbankManager goodsbankManager;

	/**
	 * 링크 카운트 추가
	 * @param request
	 * @param goodsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/addLinkCount.json")
	public String addLinkCount(HttpServletRequest request, GoodsbankVO goodsbankVO, Model model){
		
		ReturnClass returnClass = new ReturnClass();
		SessionUtil session = new SessionUtil(request);
		
		goodsbankVO.setId_lst(session.getUserId());
		returnClass = goodsbankManager.addLinkCount(goodsbankVO);
				
		model.addAttribute("result", returnClass.getCd_result());
		return "/comm/ajaxResult";
	}
	
}
