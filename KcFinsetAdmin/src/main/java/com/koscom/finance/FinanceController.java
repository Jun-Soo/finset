package com.koscom.finance;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.finance.model.TxFcReceiveVO;
import com.koscom.finance.service.FinanceManager;
import com.koscom.util.SkipLoginCheck;

@Controller
@RequestMapping("/finance")
public class FinanceController {
	private static final Logger logger = LoggerFactory.getLogger(FinanceController.class);
	
	
	@Autowired
	private FinanceManager financeManager;
	
	/**
	 * 3차전문(실시간) 업데이트 처리 
	 * @param request
	 * @param model
	 * @return
	 */
	@SkipLoginCheck
	@RequestMapping("/statusNoti.crz")
	public String financeStatusNoti(HttpServletRequest request, Model model) throws Exception {
		
		Map<String, String> paramMap = getParameterNames(request);
		if(paramMap != null){
			
			String cdFc           = paramMap.get("cdFc"          );//금융기관고유번호
			String loanRqsNo      = paramMap.get("loanRqsNo"     );//금융기관신청번호
			String loanApprStatus = paramMap.get("loanApprStatus");//대출진행상태
			String loanApprAmt    = paramMap.get("loanApprAmt"   );//대출지급금액
			String loanApprInrt   = paramMap.get("loanApprInrt"  );//대출지급금리
			String loanApprDt     = paramMap.get("loanApprDt"    );//대출지급일자
			String rsltMsg        = paramMap.get("rsltMsg"       );//거절사유

			if(rsltMsg!=null) {
				try {
					rsltMsg = new String(rsltMsg.getBytes("iso-8859-1"), "euc-kr");
					logger.info("rsltMsg->"+rsltMsg);					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			logger.debug("금융기관고유번호:cdFc          ->"+cdFc          );
			logger.debug("금융기관신청번호:loanRqsNo     ->"+loanRqsNo     );
			logger.debug("대출진행상태    :loanApprStatus->"+loanApprStatus);
			logger.debug("대출지급금액    :loanApprAmt   ->"+loanApprAmt   );
			logger.debug("대출지급금리    :loanApprInrt  ->"+loanApprInrt  );
			logger.debug("대출지급일자    :loanApprDt    ->"+loanApprDt    );
			logger.debug("거절사유        :rsltMsg       ->"+rsltMsg       );
			
			if(loanRqsNo != null && !loanRqsNo.equals("") &&
					loanApprStatus != null && !loanApprStatus.equals("") && 
					cdFc != null && (cdFc.equals("0010471"))		//페퍼저축은행
			){
				TxFcReceiveVO txFcReceiveVO = new TxFcReceiveVO();
				txFcReceiveVO.setCd_fc(cdFc);
				txFcReceiveVO.setNo_fc_req(loanRqsNo);						//금융기관신청번호

				//값 Fix되면 DB처리로 변경 할 것  
				if(loanApprStatus!=null && !loanApprStatus.equals("")){		
					if(loanApprStatus.equals("1") || loanApprStatus.equals("01"))loanApprStatus = "10";	//상담					
					else if(loanApprStatus.equals("2") || loanApprStatus.equals("02"))loanApprStatus = "20";	//심사					
					else if(loanApprStatus.equals("3") || loanApprStatus.equals("03"))loanApprStatus = "50";	//승인					
					else if(loanApprStatus.equals("4") || loanApprStatus.equals("04"))loanApprStatus = "60";	//지급
					else if(loanApprStatus.equals("5") || loanApprStatus.equals("05"))loanApprStatus = "80";	//보류					
					else if(loanApprStatus.equals("6") || loanApprStatus.equals("06"))loanApprStatus = "99";	//취소					
					else if(loanApprStatus.equals("9") || loanApprStatus.equals("09"))loanApprStatus = "70";	//거절					
					txFcReceiveVO.setSts_loan(loanApprStatus);	
				}
				if(loanApprAmt!=null && !loanApprAmt.equals("")){			//대출지급금액
					txFcReceiveVO.setAmt_pay(Long.parseLong(loanApprAmt));	
				}
				if(loanApprInrt!=null && !loanApprInrt.equals("")){			//대출지급금리
					txFcReceiveVO.setRto_pay(Double.parseDouble(loanApprInrt));
				}
				if(loanApprDt!=null && !loanApprDt.equals("") && !loanApprDt.equals("0")) {			//대출지급일자
					txFcReceiveVO.setDt_pay(Long.parseLong(loanApprDt));	
				}
				if(rsltMsg!=null ){							//결과 메시지
					txFcReceiveVO.setReason(rsltMsg);
				}
				financeManager.reqStatusNotiProcess(txFcReceiveVO);

			}else{
				model.addAttribute("result", "fail");
				return "jsonView";
			}
		}
		model.addAttribute("result", "success");
		return "jsonView";

	}
	
	private static HashMap<String, String> getParameterNames(HttpServletRequest request){
		
		HashMap<String, String> reqMap = new HashMap<String, String>();
		
		@SuppressWarnings("unchecked")
		Enumeration<String> e = request.getParameterNames();
	    while( e.hasMoreElements())
	    {
	        String   name   = (String)e.nextElement();
	        String[] values = request.getParameterValues(name);
	        if(values.length >0){
	        	reqMap.put(name, values[0]);
	        }
	     }
	    return reqMap;
	}
}