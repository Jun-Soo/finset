package com.koscom.edoc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.edoc.model.EdocForm;
import com.koscom.edoc.model.EdocInfo;
import com.koscom.edoc.model.EdocVO;
import com.koscom.edoc.service.EdocManager;
import com.koscom.goods.model.GoodsForm;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;
import com.koscom.util.StringUtil;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/edoc")
public class EdocController {
	private static final Logger logger = LoggerFactory.getLogger(EdocController.class);
	@Autowired
	EdocManager edocManager;
	@RequestMapping("/listEdocMain.crz")
	public String listEdocMain(EdocInfo edocInfo, Model model) {
		return "/edoc/listEdocMain";
	}
	@RequestMapping("/listEdoc.crz")
	public String listEdoc(EdocForm edocForm, Model model) {
		model.addAttribute("List", edocManager.listEdoc(edocForm));
		return "/edoc/listEdocGroup";
	}
	@RequestMapping("/getEdoc.crz")
	public String getEdoc(EdocForm edocForm, Model model) {
		model.addAttribute("edocInfo", edocManager.getEdoc(edocForm));
		return "/edoc/formEdocGroup";
	}
	@RequestMapping("/procEdocInfo.json")
	public String procEdocInfo(HttpServletRequest request, EdocVO edocVO,Model model) {
		SessionUtil session = new SessionUtil(request);
		edocVO.setId_frt(session.getUserId());
		edocVO.setId_lst(session.getUserId());
		logger.info(edocVO.toString());
		if(StringUtil.isNotEmpty(edocVO.getSeq_edoc())){
			ReturnClass returnClass = edocManager.procEdocInfo(edocVO);
			model.addAttribute("message", returnClass.getMessage());
			model.addAttribute("cd_result", returnClass.getCd_result());
		} else {
			int edocCnt = edocManager.getEdocCnt(edocVO);
			if( 0 == edocCnt) { //중복 아님
				ReturnClass returnClass = edocManager.procEdocInfo(edocVO);
				model.addAttribute("message", returnClass.getMessage());
				model.addAttribute("cd_result", returnClass.getCd_result());
				
			}else { //중복
				model.addAttribute("message", "금융사의 전문번호와 송수신구분이 중복됩니다.");
			}
		}
		
		return "jsonView";
	}
	
	@RequestMapping("/delEdocInfo.json")
	public String delEdocInfo(EdocVO edocVO,Model model) {
		
		ReturnClass returnClass = edocManager.delEdocInfo(edocVO);
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
	@RequestMapping("/listNmEdoc.json")
	public String listNmEdoc(EdocForm edocForm, Model model) throws Exception {
		List<String> res = edocManager.listNmEdoc(edocForm);
		model.addAttribute("List", res);
		return "jsonView";
	}
}