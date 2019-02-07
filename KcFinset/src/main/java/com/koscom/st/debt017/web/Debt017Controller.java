package com.koscom.st.debt017.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * FINSET - 부채관리/부채개요 컨트롤러 클래스
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
public class Debt017Controller {

    private static final Logger logger = LoggerFactory.getLogger(Debt017Controller.class);
	
	/**
	 * 부채관리/부채개요정보를 조회하는 함수
	 * @param	
	 * @return	
	 * @exception 
	 */
	@RequestMapping("/debt017List.crz")
	public String Debt017List() {
		logger.info("*** Debt017List");
			
		
		return "/_st/debt017/debt017List";
	}
}
