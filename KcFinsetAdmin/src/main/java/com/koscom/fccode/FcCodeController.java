package com.koscom.fccode;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

import com.koscom.fccode.model.FcCodeForm;
import com.koscom.fccode.model.FcCodeInfo;
import com.koscom.fccode.model.FcCodeVO;
import com.koscom.fccode.service.FcCodeManager;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/fccode")
public class FcCodeController {

	private static final Logger logger = LoggerFactory.getLogger(FcCodeController.class);
	
	@Autowired
	FcCodeManager fcCodeManager;
	
	/**
	 * 코드관리 메인
	 * @param fcCodeInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/listFcCodeMain.crz")
	public String listFcCodeMain(FcCodeInfo fcCodeInfo, Model model) {
		return "/fccode/listFcCodeMain";
	}
	
	/**
	 * 코드 목록 조회
	 * @param fcCodeForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listFcCode.crz")
	public String listFcCode(FcCodeForm fcCodeForm, Model model) {
		model.addAttribute("List", fcCodeManager.listFcCode(fcCodeForm));
		if("Y".equals(fcCodeForm.getYn_code_group())) {
			return "/fccode/listFcCodeGroup";
		}
		return "/fccode/listFcCodeDetail";
	}
	/**
	 * 코드 목록 조회
	 * @param fcCodeForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listFcCodeExcel.crz")
	public String listFcCodeExcel(FcCodeForm fcCodeForm, Model model) {
		model.addAttribute("List", fcCodeManager.listFcCode(fcCodeForm));
		return "/fccode/listFcCodeGroupExcel";
	}

	@RequestMapping("/getFcCode.crz")
	public String getFcCode(FcCodeForm fcCodeForm, Model model) {
		model.addAttribute("fcCodeInfo", fcCodeManager.getFcCode(fcCodeForm));
		if("Y".equals(fcCodeForm.getYn_code_group())) {
			return "/fccode/formFcCodeGroup";
		}
		return "/fccode/formFcCodeDetail";
	}
	@RequestMapping("/setFcCode.crz")
	public String setFcCode(FcCodeForm fcCodeForm, Model model) {
		FcCodeInfo fcCodeInfo= new FcCodeInfo();
		fcCodeInfo.setCd_fc(fcCodeForm.getCd_fc());
		fcCodeInfo.setNo_edoc(fcCodeForm.getNo_edoc());
		fcCodeInfo.setCode_group(fcCodeForm.getCode_group());
		fcCodeInfo.setItem_tag(fcCodeForm.getItem_tag());
		fcCodeInfo.setType_txrx(fcCodeForm.getType_txrx());
		model.addAttribute("fcCodeInfo", fcCodeInfo);
		return "/fccode/formFcCodeDetail";
	}
	
	/**
	 * 코드 등록/수정
	 * @param request
	 * @param fcCodeVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/procFcCodeInfo.json")
	public String procFcCodeInfo(HttpServletRequest request, FcCodeVO fcCodeVO,Model model) {
		SessionUtil session = new SessionUtil(request);
		fcCodeVO.setId_frt(session.getUserId());
		fcCodeVO.setId_lst(session.getUserId());
		
		ReturnClass returnClass = fcCodeManager.procFcCodeInfo(fcCodeVO);
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
	/**
	 * 코드 삭제
	 * @param fcCodeVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/delFcCodeInfo.json")
	public String delFcCodeInfo(FcCodeVO fcCodeVO,Model model) {
		
		ReturnClass returnClass = fcCodeManager.delFcCodeInfo(fcCodeVO);
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
	/**
	 * 코드 캐시 초기화
	 * @param model
	 * @return
	 */
	@RequestMapping("/clearCacheFcCode.json")
	public String clearCacheFcCode(Model model) {
		
		ReturnClass returnClass = fcCodeManager.clearCacheFcCode();
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	@RequestMapping("/listSrchFcCodeInfo.json")
	public String listSrchFcCodeInfoJson(FcCodeForm form, Model model) throws Exception {
		ReturnClass rc = fcCodeManager.listSrchFcCodeInfoJson(form);
		
		model.addAttribute("data", (JSONArray)rc.getReturnObj());
		logger.info("output=[" + (JSONArray)rc.getReturnObj() + "]" );
		return "jsonView";
	}
	@RequestMapping("/listNmFcEdoc.json")
	public String listNmEdoc(FcCodeForm fccodeForm, Model model) throws Exception {
		List<String> res = fcCodeManager.listNmFcEdoc(fccodeForm);
		model.addAttribute("List", res);
		return "jsonView";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/updateFcCodeSeq.json")
	public @ResponseBody FcCodeVO updateFcCodeSeq(HttpServletRequest request, @RequestBody String paramData, FcCodeVO fcCodeVO, Model model) throws UnsupportedEncodingException{
		
		System.out.println("updateFcCodeSeq :"+paramData);
		List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
		resultMap = JSONArray.fromObject(paramData);
		int index = 0;
		for (Map<String, Object> map : resultMap) {
			fcCodeVO = new FcCodeVO() ;
			fcCodeVO.setCd_fc(map.get("cd_fc").toString());
			fcCodeVO.setNo_edoc(map.get("no_edoc").toString());
			fcCodeVO.setCode_group(map.get("code_group").toString());
			fcCodeVO.setType_txrx(map.get("type_txrx").toString());
			fcCodeVO.setParent_code_group(map.get("parent_code_group").toString());
			fcCodeVO.setSeq_order(map.get("seq_order").toString());
			fcCodeManager.updateFcCodeSeq(fcCodeVO);
			index++;
		}
		return fcCodeVO;
	}
	
	@RequestMapping("/listSrchFcRepeat.json")
	public String listSrchFcRepeatJson(FcCodeForm form, Model model) throws Exception {
		logger.info("FcCodeForm.toString()"+form.toString());
		ReturnClass rc = fcCodeManager.listSrchFcRepeatJson(form);
		
		model.addAttribute("data", (JSONArray)rc.getReturnObj());
		logger.info("output=[" + (JSONArray)rc.getReturnObj() + "]" );
		return "jsonView";
	}
}
