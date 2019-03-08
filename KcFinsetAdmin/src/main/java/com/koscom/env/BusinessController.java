package com.koscom.env;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.env.model.BusinessDayForm;
import com.koscom.env.model.BusinessDayVO;
import com.koscom.env.service.BusinessManager;
//import com.koscom.fincorp.model.FincorpVO;
//import com.koscom.fincorp.service.FincorpManager;
import com.koscom.util.DateUtil;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;

@Controller
@RequestMapping("/business")
public class BusinessController {

	private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);
	
	@Autowired
	BusinessManager businessManager;
	
//	@Autowired
//	private FincorpManager fincorpManager;
	
	/**
	 * datepicker 영업일 목록 조회 
	 * @param model
	 * @return
	 */
	@RequestMapping("/listBusinessDay.json")
	public String listBusinessDay(Model model) {
		
		try {
			HashMap<String,String> hashMap = businessManager.listCacheBusinessDay();
			Iterator<String> iterator = hashMap.keySet().iterator();
			List<String> list = new ArrayList<String>();
			
			while(iterator.hasNext()){
				list.add(iterator.next());
			}
			
			model.addAttribute("List", list);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		
		return "jsonView";
	}
	
	@RequestMapping("/getCalendars.crz")
	public String getCalendars(Model model) {
		return "/env/businessDayMain";
	}
	
	/**
	 * 영업일 목록 설정화면
	 * @param businessDayForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listConfBusinessDay.crz")
	public String listConfBusinessDay(BusinessDayForm businessDayForm,Model model) {
		
		if(StringUtil.isEmpty(businessDayForm.getYear()))
			businessDayForm.setYear(DateUtil.getCurrentDate("yyyy"));
		
		model.addAttribute("businessDayForm", businessDayForm);
		
		return "/env/listBusinessDay";
	}
	
	/**
	 * 영업일 추가,해제
	 * @param businessDayForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/procBusinessDay.json")
	public String procBusinessDay(BusinessDayForm businessDayForm,Model model) {
		
		ReturnClass returnClass = businessManager.procBusinessDayYnHoliday(businessDayForm);
		model.addAttribute("returnData", returnClass);
		
		businessManager.clearCacheBusinessDay();
		
		return "jsonView";
	}
	
	/**
	 * datepicker 금융사 영업일 목록 조회 
	 * @param model
	 * @return
	 */
	@RequestMapping("/listFcBusinessDay.json")
	public String listFcBusinessDay(BusinessDayForm businessDayForm, Model model) {
		HashMap<String,String> hashMap = businessManager.listCacheFcBusinessDay(businessDayForm);
		Iterator<String> iterator = hashMap.keySet().iterator();
		List<String> list = new ArrayList<String>();
		
		while(iterator.hasNext()){
			list.add(iterator.next());
		}
		
		model.addAttribute("List", list);
		
		return "jsonView";
	}
	
	
	/**
	 * 금융사 영업일 목록 설정화면
	 * @param businessDayForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listConfFcBusinessDay.crz")
	public String listConfFcBusinessDay(BusinessDayForm businessDayForm,Model model) {
		
		if(StringUtil.isEmpty(businessDayForm.getYear()))
			businessDayForm.setYear(DateUtil.getCurrentDate("yyyy"));
		
		model.addAttribute("businessDayForm", businessDayForm);
		
		return "/fincorp/listBusinessDay";
	}
	
	/**
	 * 금융사 영업일 등록 ,수정
	 * @param businessDayForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/procFcBusinessDay.json")
	public String procFcBusinessDay(BusinessDayForm businessDayForm,Model model) {
		
		ReturnClass returnClass = businessManager.procFcBusinessDayYnHoliday(businessDayForm);
		model.addAttribute("returnData", returnClass);
		
		businessManager.clearCacheBusinessDay();
		
		return "jsonView";
	}
	
//	@RequestMapping("/listFcBusinessDay.crz")
//	public String listBusinessDay(BusinessDayForm businessDayForm,Model model) {
//		FincorpVO fincorpVO = fincorpManager.getFincorpInfo(businessDayForm.getCd_fc());
//		model.addAttribute("fincorpInfo", fincorpVO);
//		
//		return "/fincorp/listBusinessDay";
//	}
	@RequestMapping("/listBusinessFullDay.crz")
	public String listBusinessFullDay(BusinessDayForm businessDayForm,Model model) {
		
		return "/fincorp/listBusinessFullDay";
	}
	
	/**
	 * 금융사 영업일 상세정보
	 * @param finbusinessDayForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/getFcBusinessDayInfo.json")
	public String getFcBusinessDayInfo(BusinessDayForm businessDayForm,Model model) {
		
		BusinessDayVO businessDayVO = businessManager.getFcBusinessDayInfo(businessDayForm.getCd_fc(), businessDayForm.getYmd());
		if(businessDayVO != null){
			model.addAttribute("result", "success");
			model.addAttribute("BusinessDay", businessDayVO);
			logger.info(businessDayVO.toString());
		}
		else{
			model.addAttribute("result", "fail");
		}
		return "jsonView";
	}
	
	
	/**
	 * 금융사 영업일 삭제
	 * @param finbusinessDayForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteFcBusinessDay.json")
	public String deleteFcBusinessDay(BusinessDayForm businessDayForm,Model model) {
		
		ReturnClass returnClass = businessManager.deleteFcBusinessDay(businessDayForm);
		logger.info("ddddddddddddddddddd :" + returnClass);
		model.addAttribute("returnData", returnClass);
		
		businessManager.clearCacheBusinessDay();
		
		return "jsonView";
	}
	
}
