package com.koscom.st.customercenter019.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.common.fulltext.FulltextResultVO;
import com.koscom.st.customercenter019.model.Customercenter019InVO;
import com.koscom.st.customercenter019.model.Customercenter019OutVO;
import com.koscom.st.customercenter019.service.Customercenter019Service;

/**
 * FINSET - 마이페이지/반대매매 컨트롤러 클래스
 * @author EndFoint 개발팀 김학진
 * @since 2018.08.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2018.08.01 김학진 최초 생성
 *  </pre>
*/
@Controller
@RequestMapping("/m/st")
public class Customercenter019Controller {
    @Resource(name = "Customercenter019Service")
    private Customercenter019Service customercenter019Service;

    private static final Logger logger = LoggerFactory.getLogger(Customercenter019Controller.class);
	
	/**
	 * 상품 조회/조회결과 상세정보를 조회하는 함수
	 * @param	
	 * @return	
	 * @exception 
	 */
	@RequestMapping("/customercenter019List.crz")
	public String Customercenter019View(Model model, Customercenter019InVO vo) {
		logger.info("*** Customercenter019List");
		Customercenter019OutVO outVO = null;
		String errorMessage = "";
		
		if(vo.getAcntNo()!=null && !"".equals(vo.getAcntNo())) {
			FulltextResultVO resultVO = customercenter019Service.Send(vo);
			/*
			logger.info("*****");
			logger.info(resultVO.getErrorMessage());
			logger.info("resultVO.getFulltext():"+resultVO.getFulltext());
			logger.info("*****");
			
			if (resultVO.getErrorMessage().length()>=9 && "WAS ERROR".equals(resultVO.getErrorMessage().substring(0, 9))) {
				resultVO.setSuccess(true);
				resultVO.setErrorMessage(resultVO.getErrorMessage() + "\\n테스트를 위해 임시로 가짜 테이터를 표시합니다.");
				errorMessage = resultVO.getErrorMessage();
				logger.info("**  resultVO.setErrorMessage 설정 ***");
			}
			*/
			if (resultVO.isSuccess()) {
				outVO = customercenter019Service.CreateOutVO(resultVO.getFulltext());
				
			} else {
				errorMessage = resultVO.getErrorMessage();
			}
		}
		model.addAttribute("inVO", vo);
		model.addAttribute("outVO", outVO);
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("inFieldJson", customercenter019Service.getInFieldInfo());
		
		return "/_st/customercenter019/customercenter019List";
	}
}
