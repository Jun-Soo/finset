package com.koscom.comm;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.loanworker.LoanWorkerController;
import com.koscom.util.Constant;

@Controller
@RequestMapping("/m/comm")
@PropertySource("classpath:prop/webservice.properties")
public class CommController implements Constant {
	private static final Logger logger = LoggerFactory.getLogger(LoanWorkerController.class);

    @Resource
    public Environment environment;


    @Autowired
	CodeManager codeManager;

    public static boolean IS_LOCAL = false;
    public CommController() {
        String site = (environment != null)?environment.getProperty("service.profile"):"";
        if(LOCAL.equals(site)) {
            IS_LOCAL = true;
        }
    }

    /**
	 * 코드명을 반환합니다.
	 * @param group
	 * @param id
	 * @return String
	 */
	@RequestMapping("/getCodeName.json")
	public String getCodeName(Model model, HttpServletRequest request, HttpSession session, String group, String code) {
		logger.debug("getCodeName.json || group : " + group + ", id : " + code);
		CodeInfo codeInfo = codeManager.getCodeInfo(group, code);

		if(codeInfo == null) return code;
		model.addAttribute("name", codeInfo.getNm_code());
		return "jsonView";
	}

	/**
	 * 코드목록을 반환합니다.
	 * @param code_group
	 * @return String
	 */
	@RequestMapping("/getCodeList.json")
	public String getCodeList(Model model, HttpServletRequest request, HttpSession session, String code_group) {
		logger.debug("getCodeList.json || code_group : " + code_group);

		List<CodeInfo> codeList = codeManager.listCodeInfo(code_group);
		model.addAttribute("codeList", codeList);
		return "jsonView";
	}


	
}
