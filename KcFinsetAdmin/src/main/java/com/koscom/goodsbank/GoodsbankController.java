package com.koscom.goodsbank;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.coocon.model.CooconAPIinfoFormVO;
import com.koscom.coocon.model.CooconAPIinfoVO;
import com.koscom.coocon.service.CooconManager;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goodsbank.model.CooconChangeInfoFormVO;
import com.koscom.goodsbank.model.CooconChangeInfoVO;
import com.koscom.goodsbank.model.CooconGoodsBankInfoForm;
import com.koscom.goodsbank.model.CooconGoodsBankInfoVO;
import com.koscom.goodsbank.model.GoodsStatsFormVO;
import com.koscom.goodsbank.model.GoodsbankForm;
import com.koscom.goodsbank.model.GoodsbankVO;
import com.koscom.goodsbank.service.GoodsbankManager;
import com.koscom.util.Pagination;
import com.koscom.util.SessionUtil;
import com.koscom.util.StringUtil;
import com.koscom.worker.model.WorkerVO;
import com.koscom.worker.service.WorkerManager;


@Controller
@RequestMapping("/goodsbank")
public class GoodsbankController {
	private static final Logger logger = LoggerFactory.getLogger(GoodsbankController.class);
	@Autowired
	private GoodsbankManager goodsbankManager;
	@Autowired
	private WorkerManager workerManager;
	@Autowired
	private CooconManager cooconManager;

	@RequestMapping("/listGoodsbankMain.crz")
	public String listGoodsbankMain(HttpServletRequest request, CooconAPIinfoFormVO cooconAPIinfoFormVO, Model model){
		SessionUtil session = new SessionUtil(request);
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		model.addAttribute("workerVO", workerVO);
		return "/goodsbank/listGoodsbankMain";
	}
	
	
	@RequestMapping("/formGoodsbankInfo.crz")
	public String formGoodsInfo(HttpServletRequest request, CooconGoodsBankInfoForm CooconGoodsBankInfoForm, Model model){
		SessionUtil session = new SessionUtil(request);
		logger.info(session.getUserId());
		
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		model.addAttribute("workerVO", workerVO);
		
		CooconGoodsBankInfoVO cooconGoodsBankInfoVO = new CooconGoodsBankInfoVO();
		
		model.addAttribute("goodsbankInfo", cooconGoodsBankInfoVO);
		return "/goodsbank/formGoodsbankDetail";
		
	}
	
	
	/**
	 * 대 중 소 분류
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/ChangeSelectBoxLMS.json")
	public String ChangeSelectBoxLMS(GoodsbankForm goodsbankForm, Model model) throws Exception {
		List<GoodsbankForm> res = goodsbankManager.ChangeSelectBoxLMS(goodsbankForm);
		logger.info("중분류 소분류 size  : " + res.size());
		model.addAttribute("returnData", res);
		return "jsonView";
	}
	
	/**
 	 * 담보 페이지
 	 * @param goodsForm
 	 * @param model
 	 * @return
 	 */
 	@RequestMapping("/formGoodsbankHousing.crz")
 	public String formGoodsbankHousing(GoodsbankForm goodsbankForm, Model model){
 		
 		GoodsbankVO goodsbankInfo = new GoodsbankVO();
		if(goodsbankForm.getCd_fc() != null && goodsbankForm.getCd_goods() != null){
			goodsbankInfo = goodsbankManager.getGoodsbankInfo(goodsbankForm.getCd_fc(), goodsbankForm.getCd_goods());
			model.addAttribute("goodsbankInfo", goodsbankInfo);
			logger.info("============ formGoodsbankInfo goodsbankInfo 확인=============");
			logger.info(goodsbankInfo.toString());
		}
 		
 		return "/goodsbank/formGoodsbankHousing";
 	}
 	
 	/**
 	 * 신용 페이지
 	 * @param goodsForm
 	 * @param model
 	 * @return
 	 */
 	@RequestMapping("/formGoodsbankCredit.crz")
 	public String formGoodsbankCredit(GoodsbankForm goodsbankForm, Model model){
 		
		GoodsbankVO goodsbankInfo = new GoodsbankVO();
		if(goodsbankForm.getCd_fc() != null && goodsbankForm.getCd_goods() != null){
			goodsbankInfo = goodsbankManager.getGoodsbankInfo(goodsbankForm.getCd_fc(), goodsbankForm.getCd_goods());
			model.addAttribute("goodsbankInfo", goodsbankInfo);
			logger.info("============ formGoodsInfo goodsInfo 확인=============");
			logger.info(goodsbankInfo.toString());
		}
 		
 		return "/goodsbank/formGoodsbankCredit";
 	}
 	
	
	
	/** srchou */
	
	/**
	 * 상품관리 / 상품상세정보 입력
	 * @param CooconGoodsBankInfoForm
	 * @return String
	 */
	@RequestMapping("/createGoodsbank.json")
	public String createGoods(HttpServletRequest request, CooconGoodsBankInfoForm cooconGoodsBankInfoForm, Model model){
		SessionUtil session = new SessionUtil(request);
		cooconGoodsBankInfoForm.setId_lst(session.getUserId());
		cooconGoodsBankInfoForm.setId_frt(session.getUserId());

		if(cooconGoodsBankInfoForm.getYn_send_docu() != null){
			if("01".equals(cooconGoodsBankInfoForm.getYn_send_docu())){
				cooconGoodsBankInfoForm.setYn_send_docu("Y");
			}else{
				cooconGoodsBankInfoForm.setYn_send_docu("N");
			}
		}
		
		if(cooconGoodsBankInfoForm.getYn_post_ranking() != null){
			if("01".equals(cooconGoodsBankInfoForm.getYn_post_ranking())){
				cooconGoodsBankInfoForm.setYn_post_ranking("Y");
			}else{
				cooconGoodsBankInfoForm.setYn_post_ranking("N");
			}
		}
		
		if(cooconGoodsBankInfoForm.getYn_visit() != null){
			if("01".equals(cooconGoodsBankInfoForm.getYn_visit())){
				cooconGoodsBankInfoForm.setYn_visit("Y");
			}else{
				cooconGoodsBankInfoForm.setYn_visit("N");
			}
		}
		
		if(cooconGoodsBankInfoForm.getYn_erly_rpay_fee() != null){
			if("01".equals(cooconGoodsBankInfoForm.getYn_erly_rpay_fee())){
				cooconGoodsBankInfoForm.setYn_erly_rpay_fee("Y");
			}else{
				cooconGoodsBankInfoForm.setYn_erly_rpay_fee("N");
			}
		}
		
		if(cooconGoodsBankInfoForm.getYn_srch_ratio_limit() != null){
			if("01".equals(cooconGoodsBankInfoForm.getYn_srch_ratio_limit())){
				cooconGoodsBankInfoForm.setYn_srch_ratio_limit("Y");
				logger.info(cooconGoodsBankInfoForm.getYn_srch_ratio_limit());
			}else{
				cooconGoodsBankInfoForm.setYn_srch_ratio_limit("N");
				logger.info(cooconGoodsBankInfoForm.getYn_srch_ratio_limit());
			}
		}
		
		String result = null;
		result = goodsbankManager.createGoodsbank(cooconGoodsBankInfoForm);
		model.addAttribute("result", result);
		return "comm/ajaxResult";
	}
	
	/**
	 * 상품관리 / 상품상세정보 수정
	 * @param CooconGoodsBankInfoForm
	 * @return String
	 */
	@RequestMapping("/modifyGoodsbankDetails.json")
	public String modifyGoodsbankDetails(HttpServletRequest request, CooconGoodsBankInfoForm cooconGoodsBankInfoForm, Model model){
		SessionUtil session = new SessionUtil(request);
		cooconGoodsBankInfoForm.setId_lst(session.getUserId());
		cooconGoodsBankInfoForm.setId_frt(session.getUserId());
		
		logger.info("============ modifyGoodsDetails goodsVO확인=============");
		logger.info(cooconGoodsBankInfoForm.toString());
		
		cooconGoodsBankInfoForm.setDesc_feature(cooconGoodsBankInfoForm.getDesc_feature().replaceAll("\r\n|\n|\r","<br>"));
		if(cooconGoodsBankInfoForm.getYn_send_docu() != null){
			if("01".equals(cooconGoodsBankInfoForm.getYn_send_docu())){
				cooconGoodsBankInfoForm.setYn_send_docu("Y");
			}else{
				cooconGoodsBankInfoForm.setYn_send_docu("N");
			}
		}
		
		if(cooconGoodsBankInfoForm.getYn_post_ranking() != null){
			if("01".equals(cooconGoodsBankInfoForm.getYn_post_ranking())){
				cooconGoodsBankInfoForm.setYn_post_ranking("Y");
			}else{
				cooconGoodsBankInfoForm.setYn_post_ranking("N");
			}
		}
		
		if(cooconGoodsBankInfoForm.getYn_visit() != null){
			if("01".equals(cooconGoodsBankInfoForm.getYn_visit())){
				cooconGoodsBankInfoForm.setYn_visit("Y");
			}else{
				cooconGoodsBankInfoForm.setYn_visit("N");
			}
		}
		
		if(cooconGoodsBankInfoForm.getYn_erly_rpay_fee() != null){
			if("01".equals(cooconGoodsBankInfoForm.getYn_erly_rpay_fee())){
				cooconGoodsBankInfoForm.setYn_erly_rpay_fee("Y");
			}else{
				cooconGoodsBankInfoForm.setYn_erly_rpay_fee("N");
			}
		}
		
		if(cooconGoodsBankInfoForm.getYn_srch_ratio_limit() != null){
			if("01".equals(cooconGoodsBankInfoForm.getYn_srch_ratio_limit())){
				cooconGoodsBankInfoForm.setYn_srch_ratio_limit("Y");
			}else{
				cooconGoodsBankInfoForm.setYn_srch_ratio_limit("N");
			}
		}
		
		String result = null;
		result = goodsbankManager.modifyGoodsbankDetails(cooconGoodsBankInfoForm);
		
		model.addAttribute("result", result);
		return "comm/ajaxResult";
	}
	
	/**
	 * 상품관리 / 상품상세정보 삭제
	 * @param CooconGoodsBankInfoForm
	 * @return String
	 */
	@RequestMapping("/delGoodsbankDetails.json")
	public String delGoodsbankDetails(HttpServletRequest request, CooconGoodsBankInfoForm cooconGoodsBankInfoForm, Model model){
		SessionUtil session = new SessionUtil(request);
		cooconGoodsBankInfoForm.setId_lst(session.getUserId());
		cooconGoodsBankInfoForm.setId_frt(session.getUserId());
		
		logger.info("============ delGoodsbankDetails goodsVO확인=============");
		logger.info(cooconGoodsBankInfoForm.toString());

		String result = null;
		result = goodsbankManager.delGoodsbankDetails(cooconGoodsBankInfoForm);
		
		model.addAttribute("result", result);
		return "comm/ajaxResult";
	}
	
	/**
	 * 상품관리 / 전체 현황
	 * @param GoodsStatsFormVO
	 * @return List<GoodsStatsVO>
	 */
	@RequestMapping("/listGoodsbank.crz")
	public String listGoodsbank(HttpServletRequest request, GoodsbankForm goodsbankForm, Model model, GoodsStatsFormVO goodsStatsFormVO){
		SessionUtil session = new SessionUtil(request);
		logger.info("session.getUserId() :"+session.getUserId());
		
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		if("12".equals(workerVO.getCd_template_group()) ){
			goodsbankForm.setCd_fc(workerVO.getCd_fc());
		}
		model.addAttribute("workerVO", workerVO);
		
		Pagination pagedList = (Pagination) goodsStatsFormVO.setPagedList(goodsbankManager.listGoodsStats(goodsStatsFormVO), goodsbankManager.listGoodsStatsCount(goodsStatsFormVO));
		
		model.addAttribute("pagedList", pagedList);
		
		return "/goodsbank/listGoodsbank";
	}
	
	/**
	 * 상품관리 / 상품별 현황
	 * @param CooconChangeInfoFormVO
	 * @return List<CooconChangeInfoVO>
	 */
	@RequestMapping("/listGoodsbankChange.crz")
	public String listGoodsbank_change(HttpServletRequest request, GoodsbankForm goodsbankForm, Model model, CooconChangeInfoFormVO cooconChangeInfoFormVO){
		SessionUtil session = new SessionUtil(request);
		logger.info("session.getUserId() :"+session.getUserId());
		
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		if("12".equals(workerVO.getCd_template_group()) ){
			goodsbankForm.setCd_fc(workerVO.getCd_fc());
		}
		model.addAttribute("workerVO", workerVO);
		
		List<CooconChangeInfoVO> listcooconChangeInfo = goodsbankManager.listcooconChangeInfo(cooconChangeInfoFormVO);
		int listcooconChangeInfoCount = goodsbankManager.listcooconChangeInfoCount(cooconChangeInfoFormVO);
		model.addAttribute("listcooconChangeInfo", listcooconChangeInfo);
		model.addAttribute("listcooconChangeInfoCount",listcooconChangeInfoCount);
		
		return "/goodsbank/listGoodsbankChange";
	}
	

	/**
	 * 상품관리 / 변경된 정보 
	 * @param CooconChangeInfoFormVO
	 * @return CooconChangeInfoVO
	 */
 	@RequestMapping("/ChangeData.crz")
	public String pastChangeData(HttpServletRequest request, Model model, CooconChangeInfoFormVO cooconChangeInfoFormVO){
		SessionUtil session = new SessionUtil(request);
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		
		CooconChangeInfoVO cooconChangeInfoVO_past = new CooconChangeInfoVO();
		CooconChangeInfoVO cooconChangeInfoVO_current = new CooconChangeInfoVO();
		
		cooconChangeInfoVO_past = goodsbankManager.pastChangeData(cooconChangeInfoFormVO);
		
		cooconChangeInfoVO_current = goodsbankManager.currentChangeData(cooconChangeInfoFormVO);
		
		model.addAttribute("workerVO", workerVO);
		model.addAttribute("pastResult", cooconChangeInfoVO_past);
		model.addAttribute("currentResult", cooconChangeInfoVO_current);
		return "/goodsbank/GoodsbankChangeData";
	}
 	
	/**
	 * 상품관리 / 상품상세정보 가져오기
	 * @param CooconGoodsBankInfoForm
	 * @return list CooconGoodsBankInfoVO
	 */
 	@RequestMapping("/SetData.crz")
	public String SetData(HttpServletRequest request, Model model, CooconGoodsBankInfoForm cooconGoodsBankInfoForm){
		SessionUtil session = new SessionUtil(request);
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		
		CooconGoodsBankInfoVO cooconGoodsBankInfoVO = new CooconGoodsBankInfoVO();
		cooconGoodsBankInfoVO = goodsbankManager.setGoodsbankinfo(cooconGoodsBankInfoForm);
		
		if(cooconGoodsBankInfoVO.getYn_send_docu() != null){
			if("Y".equals(cooconGoodsBankInfoVO.getYn_send_docu())){
				cooconGoodsBankInfoVO.setYn_send_docu("01");
			}else{
				cooconGoodsBankInfoVO.setYn_send_docu("02");
			}
		}
		
		if(cooconGoodsBankInfoVO.getYn_post_ranking() != null){
			if("Y".equals(cooconGoodsBankInfoVO.getYn_post_ranking())){
				cooconGoodsBankInfoVO.setYn_post_ranking("01");
			}else{
				cooconGoodsBankInfoVO.setYn_post_ranking("02");
			}
		}
		if( cooconGoodsBankInfoVO.getDesc_feature() != null){
			cooconGoodsBankInfoVO.setDesc_feature(cooconGoodsBankInfoVO.getDesc_feature().replaceAll("<br>","\n"));
		}
		
		if(cooconGoodsBankInfoVO.getYn_visit() != null){
			if("Y".equals(cooconGoodsBankInfoVO.getYn_visit())){
				cooconGoodsBankInfoVO.setYn_visit("01");
			}else{
				cooconGoodsBankInfoVO.setYn_visit("02");
			}
		}
		
		if(cooconGoodsBankInfoVO.getYn_erly_rpay_fee() != null){
			if("Y".equals(cooconGoodsBankInfoVO.getYn_erly_rpay_fee())){
				cooconGoodsBankInfoVO.setYn_erly_rpay_fee("01");
			}else{
				cooconGoodsBankInfoVO.setYn_erly_rpay_fee("02");
			}
		}
		
		if(cooconGoodsBankInfoVO.getYn_srch_ratio_limit() != null){
			if("Y".equals(cooconGoodsBankInfoVO.getYn_srch_ratio_limit())){
				cooconGoodsBankInfoVO.setYn_srch_ratio_limit("01");
			}else{
				cooconGoodsBankInfoVO.setYn_srch_ratio_limit("02");
			}
		}
		
		model.addAttribute("workerVO", workerVO);
		model.addAttribute("goodsbankInfo", cooconGoodsBankInfoVO);
		return "/goodsbank/formGoodsbankDetail";
	}
 	
 	/**
	 * 금융사리스트 / 상품관리
	 * @param 
	 * @param model
	 * @return
	 */
	@RequestMapping("/listGoodsbankInfoMain.crz")
	public String listGoodsMain(HttpServletRequest request, Model model){
		SessionUtil session = new SessionUtil(request);
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		model.addAttribute("workerVO", workerVO);
		return "/goodsbank/listGoodsbankInfoMain";
	}
	
	/**
	 * 금융사리스트 / 상품관리 / 상품현황
	 * @param 
	 * @param model
	 * @return
	 */
	@RequestMapping("/listGoodsbankInfo.crz")
	public String listGoodsbankInfoUse(HttpServletRequest request, Model model, CooconGoodsBankInfoForm cooconGoodsBankInfoForm){
		SessionUtil session = new SessionUtil(request);
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		Pagination pagedList = (Pagination) cooconGoodsBankInfoForm.setPagedList(goodsbankManager.listGoodsbankInfoUse(cooconGoodsBankInfoForm), goodsbankManager.listGoodsbankInfoUseCount(cooconGoodsBankInfoForm));
		model.addAttribute("workerVO", workerVO);
		model.addAttribute("pagedList",pagedList);
		return "/goodsbank/listGoodsbankInfo";
	}
 
	/**
	 * 금융사리스트 / 상품관리 / 상품현황
	 * @param 
	 * @param model
	 * @return
	 */
	@RequestMapping("/setGoodsbankInfoUse.crz")
	public String setGoodsbankInfoUse(HttpServletRequest request, Model model, CooconGoodsBankInfoForm cooconGoodsBankInfoForm){
		SessionUtil session = new SessionUtil(request);
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		if(!StringUtil.isEmpty(cooconGoodsBankInfoForm.getCd_non_goods())) {
			CooconGoodsBankInfoVO cooconGoodsBankInfoVO = goodsbankManager.setGoodsbankInfoUse(cooconGoodsBankInfoForm);
			
			if( cooconGoodsBankInfoVO.getDesc_feature() != null){
				cooconGoodsBankInfoVO.setDesc_feature(cooconGoodsBankInfoVO.getDesc_feature().replaceAll("<br>","\n"));
			}
			model.addAttribute("cooconGoodsBankInfo",cooconGoodsBankInfoVO);
		}
		
		model.addAttribute("workerVO", workerVO);
		
		return "/goodsbank/formGoodsbankInfoDetail";
	}
	
}
