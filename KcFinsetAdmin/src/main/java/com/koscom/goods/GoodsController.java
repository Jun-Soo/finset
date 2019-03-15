package com.koscom.goods;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koscom.domain.FinsetInfo;
import com.koscom.goods.model.GoodsForm;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.model.MueVO;
import com.koscom.goods.model.RtoCommissionVO;
import com.koscom.goods.service.GoodsManager;
import com.koscom.util.NumberUtil;
import com.koscom.util.Pagination;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;
import com.koscom.worker.model.WorkerVO;
import com.koscom.worker.service.WorkerManager;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/goods")
public class GoodsController {

	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	@Autowired
	private GoodsManager goodsManager;
	
	@Autowired
	private WorkerManager workerManager;
	
	/**
	 * 상품정보 캐시 초기화
	 * @param model
	 * @return
	 */
	@RequestMapping("/clearCacheGoods.json")
	public String clearCacheGoods(Model model) {
		
		ReturnClass returnClass = goodsManager.clearCacheGoods();
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
	/**
	 * 상품관리 메인
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listGoodsMain.crz")
	public String listGoodsMain(HttpServletRequest request, GoodsForm goodsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		model.addAttribute("workerVO", workerVO);
		return "/goods/listGoodsMain";
	}
	
	/**
	 * 상품리스트
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listGoods.crz")
	public String listGoods(HttpServletRequest request, GoodsForm goodsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		logger.info(session.getUserId());
		
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		if(workerVO.getCd_template_group().equals("12") ){
			goodsForm.setCd_fc(workerVO.getCd_fc());
		}
		model.addAttribute("workerVO", workerVO);
		
		Pagination pagedList = (Pagination) goodsForm.setPagedList(goodsManager.listGoodsInfo(goodsForm), goodsManager.listGoodsCount(goodsForm));
		model.addAttribute("pagedList", pagedList);
		
		return "/goods/listGoods";
	}
	
	/**
	 * 상품 수정 폼
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/formGoodsInfo.crz")
	public String formGoodsInfo(HttpServletRequest request, GoodsForm goodsForm, Model model){
		SessionUtil session = new SessionUtil(request);
		logger.info(session.getUserId());
		
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		model.addAttribute("workerVO", workerVO);
		
		
		
		GoodsVO goodsInfo = new GoodsVO();
		GoodsVO goodsRtoInfo = new GoodsVO();
		List<RtoCommissionVO> rtoCommissionList = null;
		if(goodsForm.getCd_fc() != null && goodsForm.getCd_goods() != null){
			goodsInfo = goodsManager.getGoodsInfo(goodsForm.getCd_fc(), goodsForm.getCd_goods());
			rtoCommissionList = goodsManager.listGoodsRtoCommission(goodsInfo);
//			for (RtoCommissionVO rtoCommissionVO: rtoCommissionList) {
//				logger.debug(rtoCommissionVO.getCd_fc());
//			}
			model.addAttribute("goodsInfo", goodsInfo);
			logger.info("============ formGoodsInfo goodsInfo 확인=============");
			logger.info(goodsInfo.toString());
			model.addAttribute("goodsRtoInfo", goodsRtoInfo);
			model.addAttribute("rtoCommissionList", rtoCommissionList);
		}
		
		return "/goods/formGoodsDetail";
	}
	
	/**
	 * 상품 상세정보 변경
	 * @param request
	 * @param goodsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifyGoodsDetails.json")
	public String modifyGoodsDetails(HttpServletRequest request, GoodsVO goodsVO, Model model){
		logger.info("============ modifyGoodsDetails goodsVO확인=============");
		logger.info(goodsVO.toString());
		
		ReturnClass returnClass = new ReturnClass();
		SessionUtil session = new SessionUtil(request);
		goodsVO.setId_lst(session.getUserId());
		
		returnClass = goodsManager.modifyGoodsDetails(goodsVO);
		
		model.addAttribute("result", returnClass.getCd_result());
		return "/comm/ajaxResult";
	}
	
	/**
	 * 상품 연동정보 변경
	 * @param request
	 * @param goodsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifyGoodsTransmit.json")
	public String modifyGoodsTransmit(HttpServletRequest request, GoodsVO goodsVO, Model model){
		ReturnClass returnClass = new ReturnClass();
		SessionUtil session = new SessionUtil(request);
		goodsVO.setId_lst(session.getUserId());
		
		if(workerManager.getCachWorkerApprovalAuth(goodsVO.getId_lst(),"202003")){
			returnClass = goodsManager.modifyGoodsTransmit(goodsVO);
		}
		
		model.addAttribute("result", returnClass.getCd_result());
		return "/comm/ajaxResult";
	}
	
	/**
	 * 상품별 상세정보 확인
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewGoodsDetails.crz")
	public String viewGoodsDetails(GoodsForm goodsForm, Model model){
		GoodsVO goodsInfo = new GoodsVO();
		GoodsVO goodsRtoInfo = new GoodsVO();
		if(goodsForm.getCd_goods() != null){
			goodsInfo = goodsManager.getGoodsInfo(goodsForm.getCd_goods());
			goodsRtoInfo = goodsManager.getGoodsRtoInfo(goodsForm.getCd_fc(), goodsForm.getCd_goods());
		}
		
		model.addAttribute("goodsInfo", goodsInfo);
		model.addAttribute("goodsRtoInfo", goodsRtoInfo);
		return "/goods/viewGoodsDetails";
	}
	
	@RequestMapping("/viewGoodsDetailsPop.crz")
	public String viewGoodsDetailsPop(GoodsForm goodsForm, Model model){
		GoodsVO goodsInfo = new GoodsVO();
		GoodsVO goodsRtoInfo = new GoodsVO();
		List<MueVO> mueList = null;
		if(goodsForm.getCd_goods() != null){
			goodsInfo = goodsManager.getGoodsInfo(goodsForm.getCd_fc(), goodsForm.getCd_goods());
			goodsRtoInfo = goodsManager.getGoodsRtoInfo(goodsForm.getCd_fc(), goodsForm.getCd_goods());
			mueList = goodsManager.listGoodsMue(goodsInfo);
			
			model.addAttribute("goodsInfo", goodsInfo);
			model.addAttribute("goodsRtoInfo", goodsRtoInfo);
			model.addAttribute("mueList", mueList);
		}
		
		model.addAttribute("goodsInfo", goodsInfo);
		model.addAttribute("goodsRtoInfo", goodsRtoInfo);
		return "/goods/viewGoodsDetailsPop";
	}
	/**
	 * 상품 추가
	 * @param request
	 * @param goodsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/createGoods.json")
	public String createGoods(HttpServletRequest request, GoodsVO goodsVO, Model model){
		ReturnClass returnClass = new ReturnClass();
		SessionUtil session = new SessionUtil(request);
		goodsVO.setId_frt(session.getUserId());
		
		logger.info("============goodsVO확인=============");
		logger.info(goodsVO.toString());
		
		returnClass = goodsManager.createGoods(goodsVO);
				
		model.addAttribute("result", returnClass.getCd_result());
		return "/comm/ajaxResult";
	}
	
	/**
	 * 상품코드 중복체크
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/duplicationChk.json")
	public String duplicationChk(GoodsForm goodsForm, Model model){
		
		if(goodsForm.getCd_goods() != null){
			int goodsCnt = goodsManager.getGoodsCnt(goodsForm.getCd_goods());
			if( 0 == goodsCnt) model.addAttribute("result", "Y");	//중복아님
		}
		return "jsonView";
	}
	
	
	/**
	 * 상품관리 메인
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listAgencyGoodsMain.crz")
	public String listAgencyGoodsMain(GoodsForm goodsForm, Model model){
		
		return "/goods/listAgencyGoodsMain";
	}
	
	/**
	 * 상품리스트 제휴사별
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listOpenAgency.crz")
	public String listOpenAgency(GoodsForm goodsForm, Model model){
		
		return "/goods/listOpenAgency";
	}
	
	/**
	 * 상품리스트 상품별
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listOpenGoods.crz")
	public String listOpenGoods(GoodsForm goodsForm, Model model){
		
		return "/goods/listOpenGoods";
	}
	
	/**
	 * 노출상품 여부 일괄 변경
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/procGoodsOpen.json")
	public String procGoodsOpen(HttpServletRequest request, GoodsVO goodsVO, Model model){
		SessionUtil session = new SessionUtil(request);
		
		goodsVO.setId_frt(session.getUserId());
		goodsVO.setId_lst(session.getUserId());
		
		ReturnClass returnClass = goodsManager.procGoodsOpen(goodsVO);
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
	/**
	 * 노출상품 여부 일괄 변경
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/procAgencyOpen.json")
	public String procAgencyOpen(HttpServletRequest request, GoodsVO goodsVO, Model model){
		SessionUtil session = new SessionUtil(request);
		
		goodsVO.setId_frt(session.getUserId());
		goodsVO.setId_lst(session.getUserId());
		
		ReturnClass returnClass = goodsManager.procAgencyOpen(goodsVO);
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/procMue.json")
	public @ResponseBody MueVO createMue(HttpServletRequest request, @RequestBody String paramData, MueVO mueVO, Model model) throws UnsupportedEncodingException{
		
		logger.debug("procMue"+paramData);
		List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
		resultMap = JSONArray.fromObject(paramData);
		int index = 0;
		for (Map<String, Object> map : resultMap) {
			mueVO = new MueVO() ;
			mueVO.setCd_fc(map.get("cd_fc").toString());
			mueVO.setCd_goods(map.get("cd_goods").toString());
			if(index == 0){
				goodsManager.delMue(mueVO);
			}
			mueVO.setMin_year_income(NumberUtil.stringToInt(map.get("min_year_income").toString()));
			mueVO.setMax_year_income(NumberUtil.stringToInt(map.get("max_year_income").toString()));
			mueVO.setGrade_year_income(NumberUtil.stringToInt(map.get("grade_year_income").toString()));
			mueVO.setGrade_mue(NumberUtil.stringToInt(map.get("grade_mue").toString()));
			mueVO.setMue(Double.parseDouble(map.get("mue").toString()));
			goodsManager.createMue(mueVO);
			index++;
		}
		return mueVO;
	}
	
	/**
	 * 수수료 입력
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/procRtoCommissionVO.json")
	public @ResponseBody RtoCommissionVO createRtoCommissionVO(HttpServletRequest request, @RequestBody String paramData, RtoCommissionVO rtoCommissionVO, Model model) throws UnsupportedEncodingException{
		
		logger.debug("procMue"+paramData);
		List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
		resultMap = JSONArray.fromObject(paramData);
		int index = 0;
		for (Map<String, Object> map : resultMap) {
			rtoCommissionVO = new RtoCommissionVO() ;
			rtoCommissionVO.setCd_fc(map.get("cd_fc").toString());
			rtoCommissionVO.setCd_goods(map.get("cd_goods").toString());
			if(index == 0){
				goodsManager.deleteRtoCommission(rtoCommissionVO);
			}
			
			
			rtoCommissionVO.setAmt_limit_min(NumberUtil.stringToInt(map.get("amt_limit_min").toString()));
			rtoCommissionVO.setAmt_limit_max(NumberUtil.stringToInt(map.get("amt_limit_max").toString()));
			
			rtoCommissionVO.setRto_interest_min(Double.parseDouble(map.get("rto_interest_min").toString()));
			rtoCommissionVO.setRto_interest_max(Double.parseDouble(map.get("rto_interest_max").toString()));
			
			rtoCommissionVO.setRto_commission(Double.parseDouble(map.get("rto_commission").toString()));
			
			rtoCommissionVO.setGrade(NumberUtil.stringToInt(map.get("grade").toString()));

			goodsManager.createRtoCommission(rtoCommissionVO);
			index++;
		}
		return rtoCommissionVO;
	}
	
	
	/**
	 * 대 중 소 분류
	 * @param goodsForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/ChangeSelectBoxLMS.json")
	public String ChangeSelectBoxLMS(GoodsForm goodsForm, Model model) throws Exception {
		List<GoodsForm> res = goodsManager.ChangeSelectBoxLMS(goodsForm);
		logger.info("중분류 소분류 size  : " + res.size());
		model.addAttribute("returnData", res);
		return "jsonView";
	}
	
	/**
	 * 상품 삭제
	 * @param GoodsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteGoods.json")
	public String deleteGoods(GoodsVO goodsVO,Model model) {
		
		ReturnClass returnClass = goodsManager.deleteGoods(goodsVO);
		//TODO 수수료 삭제 추가
		if(returnClass.getCd_result().equals("00")){
			RtoCommissionVO rtoCommissionVO = new RtoCommissionVO();
			rtoCommissionVO.setCd_fc(goodsVO.getCd_fc());
			rtoCommissionVO.setCd_goods(goodsVO.getCd_goods());
			goodsManager.deleteRtoCommission(rtoCommissionVO);
		}
		model.addAttribute("returnData", returnClass);
		
		
		return "jsonView";
	}
	
	/**
	 * test컨트롤러
	 * @param GoodsVO
	 * @param model
	 * @return
	 */
//	@RequestMapping("/listGoodsInfoForFinset.crz")
//	public String listGoodsInfoForFinset(GoodsForm goodsForm,Model model) {
//		
//		LinkedHashMap<GoodsVO, List<FinsetInfo>> linkedMap = goodsManager.listGoodsInfoForFinset(goodsForm);
//		
//		logger.debug("linkedMap : " + linkedMap.size());
//		return null;
//	}
	
	/**
 	 * CFS주택담보 페이지
 	 * @param goodsForm
 	 * @param model
 	 * @return
 	 */
 	@RequestMapping("/formGoodsCFSHousing.crz")
 	public String formGoodsCFSHousing(GoodsForm goodsForm, Model model){
 		
 		GoodsVO goodsInfo = new GoodsVO();
		if(goodsForm.getCd_fc() != null && goodsForm.getCd_goods() != null){
			goodsInfo = goodsManager.getGoodsInfo(goodsForm.getCd_fc(), goodsForm.getCd_goods());
			model.addAttribute("goodsInfo", goodsInfo);
			logger.info("============ formGoodsInfo goodsInfo 확인=============");
			logger.info(goodsInfo.toString());
		}
 		
 		return "/goods/formGoodsCFSHousing";
 	}
 	
 	/**
 	 * CFS신용 페이지
 	 * @param goodsForm
 	 * @param model
 	 * @return
 	 */
 	@RequestMapping("/formGoodsCFSCredit.crz")
 	public String formGoodsCFSCredit(GoodsForm goodsForm, Model model){
 		
		GoodsVO goodsInfo = new GoodsVO();
		if(goodsForm.getCd_fc() != null && goodsForm.getCd_goods() != null){
			goodsInfo = goodsManager.getGoodsInfo(goodsForm.getCd_fc(), goodsForm.getCd_goods());
			model.addAttribute("goodsInfo", goodsInfo);
			logger.info("============ formGoodsInfo goodsInfo 확인=============");
			logger.info(goodsInfo.toString());
		}
 		
 		return "/goods/formGoodsCFSCredit";
 	}
 	
 	/**
 	 * CFS자동차 페이지
 	 * @param goodsForm
 	 * @param model
 	 * @return
 	 */
 	@RequestMapping("/formGoodsCFSCar.crz")
 	public String formGoodsCFSCar(GoodsForm goodsForm, Model model){
 		
		GoodsVO goodsInfo = new GoodsVO();
		if(goodsForm.getCd_fc() != null && goodsForm.getCd_goods() != null){
			goodsInfo = goodsManager.getGoodsInfo(goodsForm.getCd_fc(), goodsForm.getCd_goods());
			model.addAttribute("goodsInfo", goodsInfo);
			logger.info("============ formGoodsInfo goodsInfo 확인=============");
			logger.info(goodsInfo.toString());
		}
 		
 		return "/goods/formGoodsCFSCar";
 	}
 	
 	/**
 	 * 조건검색 신용
 	 * @param goodsForm
 	 * @param model
 	 * @return
 	 */
 	@RequestMapping("/formGoodsConditionCredit.crz")
 	public String formGoodsConditionCredit(GoodsForm goodsForm, Model model){
 		
 		GoodsVO goodsInfo = new GoodsVO();
		if(goodsForm.getCd_fc() != null && goodsForm.getCd_goods() != null){
			goodsInfo = goodsManager.getGoodsInfo(goodsForm.getCd_fc(), goodsForm.getCd_goods());
			model.addAttribute("goodsInfo", goodsInfo);
			logger.info("============ formGoodsInfo goodsInfo 확인=============");
			logger.info(goodsInfo.toString());
		}
 		
 		return "/goods/formGoodsConditionCredit";
 	}
 	
 	/**
 	 * 조건검색 담보
 	 * @param goodsForm
 	 * @param model
 	 * @return
 	 */
 	@RequestMapping("/formGoodsConditionGuarantee.crz")
 	public String formGoodsConditionGuarantee(GoodsForm goodsForm, Model model){
 		
		GoodsVO goodsInfo = new GoodsVO();
		if(goodsForm.getCd_fc() != null && goodsForm.getCd_goods() != null){
			goodsInfo = goodsManager.getGoodsInfo(goodsForm.getCd_fc(), goodsForm.getCd_goods());
			model.addAttribute("goodsInfo", goodsInfo);
			logger.info("============ formGoodsInfo goodsInfo 확인=============");
			logger.info(goodsInfo.toString());
		}
 		
 		return "/goods/formGoodsConditionGuarantee";
 	}
}
