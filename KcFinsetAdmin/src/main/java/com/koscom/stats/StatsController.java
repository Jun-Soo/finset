package com.koscom.stats;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.stats.model.StatsChartForm;
import com.koscom.stats.model.StatsForm;
import com.koscom.stats.model.StatsVO;
import com.koscom.stats.service.StatsManager;
import com.koscom.util.DateUtil;
import com.koscom.util.Pagination;
import com.koscom.util.SessionUtil;
import com.koscom.worker.service.WorkerManager;

@Controller
@RequestMapping("/stats")
public class StatsController {

	private static final Logger logger = LoggerFactory.getLogger(StatsController.class);
	
	@Autowired
	private StatsManager statsManager;
	
	@Autowired
	private WorkerManager workerManager;
	
	/**
	 * 금융사별 통계
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewStatApplyComp.crz")
	public String viewStatApplyComp(HttpServletRequest request, StatsForm statsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		
		return "/stats/viewStatApplyComp";
	}
	
	/**
	 * 금융사별 통계 리스트
	 * @param request
	 * @param statsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatApplyComp.crz")
	public String listStatApplyComp(HttpServletRequest request, StatsForm statsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		
		List<StatsVO> listStatApplyComp = statsManager.listStatApplyComp(statsForm); 
		model.addAttribute("listStatApplyComp", listStatApplyComp);
		
		return "/stats/listStatApplyComp";
	}
	
	/**
	 * 금융사별 통계 리스트 엑셀
	 * @param request
	 * @param response
	 * @param statsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatApplyCompExcel.crz")
	public String listStatApplyCompExcel(HttpServletRequest request, HttpServletResponse response, StatsForm statsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		
		//통계 엑셀 권한있어야만 가능
		if(!workerManager.getCachWorkerApprovalAuth(session.getUserId(),"402005")){
			model.addAttribute("message", "alert('엑셀출력 권한이 없습니다.')");
			return "/comm/message";
		}
		
		List<StatsVO> listStatApplyComp = statsManager.listStatApplyComp(statsForm); 
		model.addAttribute("listStatApplyComp", listStatApplyComp);
		
		String fileName = "금융사별통계"+DateUtil.getCurrentDate()+".xls";
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8") + ";");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setContentType("application/vnd.ms-excel");
		
		return "/stats/listStatApplyCompExcel";
	}
	
	/**
	 * 담당자별 통계
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewStatWorker.crz")
	public String viewStatWorker(HttpServletRequest request, StatsForm statsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		
		return "/stats/viewStatWorker";
	}
	
	/**
	 * 사용자별 통계 리스트
	 * @param request
	 * @param statsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatWorker.crz")
	public String listStatWorker(HttpServletRequest request, StatsForm statsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		
		List<StatsVO> listStatWorker = statsManager.listStatWorker(statsForm); 
		model.addAttribute("listStatWorker", listStatWorker);
		
		return "/stats/listStatWorker";
	}
	
	/**
	 * 사용자별 통계 리스트 엑셀
	 * @param request
	 * @param response
	 * @param statsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatWorkerExcel.crz")
	public String listStatWorkerExcel(HttpServletRequest request, HttpServletResponse response, StatsForm statsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		
		//통계 엑셀 권한있어야만 가능
		if(!workerManager.getCachWorkerApprovalAuth(session.getUserId(),"402005")){
			model.addAttribute("message", "alert('엑셀출력 권한이 없습니다.')");
			return "/comm/message";
		}
		
		List<StatsVO> listStatWorker = statsManager.listStatWorker(statsForm); 
		model.addAttribute("listStatWorker", listStatWorker);
		
		String fileName = "담당자별통계"+DateUtil.getCurrentDate()+".xls";
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8") + ";");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setContentType("application/vnd.ms-excel");
		return "/stats/listStatWorkerExcel";
	}
	
	/**
	 * 키워드별 통계
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewStatKeyword.crz")
	public String viewStatKeyword(HttpServletRequest request, StatsForm statsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		
		return "/stats/viewStatKeyword";
	}
	
	/**
	 * 키워드별 통계 리스트
	 * @param request
	 * @param statsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatKeyword.crz")
	public String listStatKeyword(HttpServletRequest request, StatsForm statsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		
		List<StatsVO> listStatKeyword = statsManager.listStatKeyword(statsForm);
		model.addAttribute("listStatKeyword", listStatKeyword);
		
		return "/stats/listStatKeyword";
	}
	
	/**
	 * 키워드별 통계 리스트 엑셀
	 * @param request
	 * @param statsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatKeywordExcel.crz")
	public String listStatKeywordExcel(HttpServletRequest request, HttpServletResponse response, StatsForm statsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		
		//통계 엑셀 권한있어야만 가능
		if(!workerManager.getCachWorkerApprovalAuth(session.getUserId(),"402005")){
			model.addAttribute("message", "alert('엑셀출력 권한이 없습니다.')");
			return "/comm/message";
		}
		
		List<StatsVO> listStatKeyword = statsManager.listStatKeyword(statsForm);
		model.addAttribute("listStatKeyword", listStatKeyword);
		
		String fileName = "키워드별통계"+DateUtil.getCurrentDate()+".xls";
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8") + ";");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setContentType("application/vnd.ms-excel"); 
		return "/stats/listStatKeywordExcel";
	}
	
	/**
	 * 광고매체별 통계(신청인) 
	 * @param request
	 * @param statsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewStatAdvertisement.crz")
	public String viewStatAdvertisement(HttpServletRequest request, StatsForm statsForm, Model model) {

		return "/stats/viewStatAdvertisement";
	}
	
	/**
	 * 광고매체별 통계 리스트(신청인) 
	 * @param request
	 * @param statsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatAdvertisement.crz")
	public String listStatAdvertisement(HttpServletRequest request, StatsForm statsForm, Model model) {
		
		List<StatsVO> listStatAdvertisement = statsManager.listStatAdvertisement(statsForm);
		model.addAttribute("listStatAdvertisement", listStatAdvertisement);
		
		return "/stats/listStatAdvertisement";
	}
	
	/**
	 * 광고매체별 통계 리스트(신청인) 엑셀
	 * @param request
	 * @param response
	 * @param statsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatAdvertisementExcel.crz")
	public String listStatAdvertisementExcel(HttpServletRequest request, HttpServletResponse response, StatsForm statsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		
		//통계 엑셀 권한있어야만 가능
		if(!workerManager.getCachWorkerApprovalAuth(session.getUserId(),"402005")){
			model.addAttribute("message", "alert('엑셀출력 권한이 없습니다.')");
			return "/comm/message";
		}
		
		List<StatsVO> listStatAdvertisement = statsManager.listStatAdvertisement(statsForm);
		model.addAttribute("listStatAdvertisement", listStatAdvertisement);
		
		String fileName = "매체사별통계_신청인"+DateUtil.getCurrentDate()+".xls";
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8") + ";");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setContentType("application/vnd.ms-excel");
		return "/stats/listStatAdvertisementExcel";
	}
	
	/**
	 * 광고매체별 통계(신청서) 
	 * @param request
	 * @param statsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewStatAdvertisementApply.crz")
	public String viewStatAdvertisementApply(StatsForm statsForm, Model model) {

		return "/stats/viewStatAdvertisementApply";
	}
	
	/**
	 * 광고매체별 통계 리스트(신청서)
	 * @param request
	 * @param statsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatAdvertisementApply.crz")
	public String listStatAdvertisementApply(StatsForm statsForm, Model model) {
		
		List<StatsVO> listStatAdvertisementApply = statsManager.listStatAdvertisementApply(statsForm);
		model.addAttribute("listStatAdvertisementApply", listStatAdvertisementApply);

		return "/stats/listStatAdvertisementApply";
	}
	
	/**
	 * 광고매체별 통계 리스트(신청서) 엑셀
	 * @param request
	 * @param response
	 * @param statsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatAdvertisementApplyExcel.crz")
	public String listStatAdvertisementApplyExcel(HttpServletRequest request, HttpServletResponse response, StatsForm statsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		
		//통계 엑셀 권한있어야만 가능
		if(!workerManager.getCachWorkerApprovalAuth(session.getUserId(),"402005")){
			model.addAttribute("message", "alert('엑셀출력 권한이 없습니다.')");
			return "/comm/message";
		}
		
		List<StatsVO> listStatAdvertisementApply = statsManager.listStatAdvertisementApply(statsForm);
		model.addAttribute("listStatAdvertisementApply", listStatAdvertisementApply);
		
		String fileName = "매체사별통계_신청서"+DateUtil.getCurrentDate()+".xls";
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8") + ";");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setContentType("application/vnd.ms-excel");
		return "/stats/listStatAdvertisementApplyExcel";
	}
	
	/**
	 * SMS 발송통계
	 * @param statsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewStatSms.crz")
	public String viewStatSms(StatsForm statsForm, Model model) {

		return "/stats/viewStatSms";
	}
	
	/**
	 * SMS 발송통계 리스트
	 * @param statsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatSms.crz")
	public String listStatSms(StatsForm statsForm, Model model) {
		
		List<StatsVO> listStatSms = statsManager.listStatSms(statsForm);
		model.addAttribute("listStatSms", listStatSms);

		return "/stats/listStatSms";
	}
	
	/**
	 * SMS 발송통계 엑셀
	 * @param request
	 * @param response
	 * @param statsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatSmsExcel.crz")
	public String listStatSmsExcel(HttpServletRequest request, HttpServletResponse response, StatsForm statsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		
		//통계 엑셀 권한있어야만 가능
		if(!workerManager.getCachWorkerApprovalAuth(session.getUserId(),"402005")){
			model.addAttribute("message", "alert('엑셀출력 권한이 없습니다.')");
			return "/comm/message";
		}
		
		List<StatsVO> listStatSms = statsManager.listStatSms(statsForm);
		model.addAttribute("listStatSms", listStatSms);
		
		String fileName = "SMS발송 통계_"+DateUtil.getCurrentDate()+".xls";
		try {
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8") + ";");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setContentType("application/vnd.ms-excel");
		
		return "/stats/listStatSmsExcel";
	}
	
	
	/**
	 * 일별 사용 현황
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewStatDailyUse.crz")
	public String viewStatDailyUse(HttpServletRequest request, StatsForm statsForm, Model model){
		
		model.addAttribute("defaultDateFrom",DateUtil.getThisMonth()+"-01");
		model.addAttribute("defaultDateTo",DateUtil.getCurrentDate(DateUtil.DATE_PATTERN_DASH));
		
		return "/stats/viewStatDailyUse";
	}
	
	/**
	 * 일별 사용 현황 리스트
	 * @param request
	 * @param statsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatDailyUse.crz")
	public String listStatDailyUse(HttpServletRequest request, StatsForm statsForm, Model model){

		Pagination pagedList = (Pagination) statsForm.setPagedList(statsManager.listStatDailyUse(statsForm), statsManager.listStatDailyUseCount(statsForm));
		model.addAttribute("pagedList", pagedList);
		
		return "/stats/listStatDailyUse";
	}
	
	/**
	 * 일별 사용 현황 리스트_상세
	 * @param request
	 * @param statsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatDailyUseDetail.crz")
	public String listStatDailyUseDetail(HttpServletRequest request, StatsForm statsForm, Model model){

		List<StatsVO> listStatDailyUseDetail = statsManager.listStatDailyUseDetail(statsForm); 
		model.addAttribute("listStatDailyUseDetail", listStatDailyUseDetail);
		
		return "/stats/listStatDailyUseDetail";
	}
	
	/**
	 * 일별 사용 현황 차트
	 * @param request
	 * @param statsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/listStatDailyUseChart.json")
	public String listStatDailyUseChart(HttpServletRequest request, StatsChartForm statsChartForm, Model model){

		List<StatsVO> listStatDailyUseChart = statsManager.listStatDailyUseChart(statsChartForm); 
		model.addAttribute("listStatDailyUseChart", listStatDailyUseChart);
		
		return "jsonView";
	}
	
	/**
	 * 사용자별 통계 리스트 엑셀
	 * @param request
	 * @param response
	 * @param statsForm
	 * @param model
	 * @return
	 */
//	@RequestMapping("/listStatDailyUseExcel.crz")
//	public String listStatDailyUseExcel(HttpServletRequest request, HttpServletResponse response, StatsForm statsForm, Model model){
//		SessionUtil session = new SessionUtil(request);
//		
//		//통계 엑셀 권한있어야만 가능
//		if(!workerManager.getCachWorkerApprovalAuth(session.getUserId(),"402005")){
//			model.addAttribute("message", "alert('엑셀출력 권한이 없습니다.')");
//			return "/comm/message";
//		}
//		
//		List<StatsVO> listStatDailyUse = statsManager.listStatDailyUse(statsForm); 
//		model.addAttribute("listStatDailyUse", listStatDailyUse);
//		
//		String fileName = "일별 사용 현황"+DateUtil.getCurrentDate()+".xls";
//		try {
//			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8") + ";");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		response.setContentType("application/vnd.ms-excel");
//		return "/stats/listStatDailyUseExcel";
//	}
	
}
