package com.koscom.util;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.fincorp.service.FincorpManager;

public class JSTLFunction {

	private static final Logger logger = LoggerFactory.getLogger(JSTLFunction.class);

	/**
	 *  ApplicationContext 빈 가져오기
	 * @param bean
	 * @return
	 */
	private static Object getBean(String bean)
	{
		return SpringApplicationContext.getBean(bean);
	}
//
	/**
	 * 코드명을 반환합니다.
	 * @param group
	 * @param id
	 * @return
	 */
	public static String getCodeName(String group, String id)
	{
		CodeManager codeManager = (CodeManager)getBean("codeManager");
		CodeInfo code = codeManager.getCodeInfo(group, id);

		if(code == null) return id;
		return code.getNm_code();
	}
//
//	/**
//	 * 코드목록을 반환합니다.
//	 * @param code_group
//	 * @return
//	 */
//	public static List<CodeInfo> getCodeList(String code_group)
//	{
//		CodeManager codeManager = (CodeManager)getBean("codeManager");
//		return codeManager.listCodeInfo(code_group);
//	}
//
//	public static String getNvlCodeName(String group, String id, String defaultStr)
//	{
//
//		if (StringUtil.isEmpty(group) || StringUtil.isEmpty(id)) {
//			return defaultStr;
//		}
//		CodeManager codeManager = (CodeManager)getBean("codeManager");
//		String returnValue = codeManager.getCodeName(group, id);
//
//		// codeValue 와 returnValue 가 같다는 의미는
//		// code name이 null 이란 의미이다
//		if(StringUtil.isEmpty(returnValue) || id.equals(returnValue))
//			return defaultStr;
//
//		return returnValue;
//	}
//
//	/**
//	 * 담당자 셀렉트 박스 생성
//	 * @param idAppr
//	 * @param defaultText
//	 * @param selectValue
//	 * @return
//	 */
//	public static String makeIdOptions(String idAppr, String defaultText, String selectValue) {
//		return makeIdOptionsByBranch(idAppr, defaultText, selectValue, "000");
//	}
//
//	/**
//	 * 지점별 담당자 셀렉트 박스 생성
//	 * @param idAppr
//	 * @param c3_branch
//	 * @param defaultText
//	 * @param selectValue
//	 * @return
//	 */
//	public static String makeIdOptionsByBranch(String idAppr, String defaultText, String selectValue, String c3_branch) {
//
//		WorkerManager workerManager = (WorkerManager)getBean("workerManager");
////		List<ApprovalManageVO> list = workerManager.listCacheApprovalAuth(idAppr);
//
//		StringBuffer data = new StringBuffer();
//		data.append("<option value=\"\">" + defaultText + "</option>");
//
////		if(list == null)
////			return data.toString();
////
////		for (ApprovalManageVO WAA : list) {
////
////			String val = WAA.getId_emp();
////			if( !StringUtil.isEmpty(WAA.getId_emp()) && WAA.getId_emp().equals(selectValue))
////				val += "\" selected=\"selected";
////			if( !"000".equals(c3_branch) && c3_branch.equals(WAA.getC3_branch())){
////				data.append("\n<option value=\"" + val + "\">" + WAA.getNm_emp() + "</option>");
////			}else if("000".equals(c3_branch)){
////				data.append("\n<option value=\"" + val + "\">" + WAA.getNm_emp() + "</option>");
////			}
////		}
//		return data.toString();
//	}
//
//
//	/**
//	 * 코드그룹명을 받아 해당 코드리스트를 select box 안의 option 으로 변환합니다.
//	 * @param code_group 코드그룹명
//	 * @param defaultText 기본텍스트
//	 * @param selectValue 선택 되어진 텍스트 ( 만약 선택 값이 존재한다면)
//	 * @param pType = 1:NM(Default) , 2:ID.NM ,3:ETC
//	 * @return
//	 */
//	public static String makeOptions(String code_group, String defaultText,String selectValue, String pType) {
//
//		CodeManager codeManager = (CodeManager)getBean("codeManager");
//		WorkerManager workerManager = (WorkerManager)getBean("workerManager");
//
//		CodeInfo codeInfo = new CodeInfo();
//		codeInfo.setCode_group(code_group);
//        String type = pType;
//		List<CodeInfo> list = codeManager.listCodeInfo(codeInfo);
//
//		StringBuffer data = new StringBuffer();
//
//		if(!"EMPTY".equals(defaultText)){
//			data.append("<option value=''>" + defaultText + "</option>");
//			data.append("<option data-divider='true' disabled></option>");
//		}
//
//		if(list != null) {
//
//            boolean isALL = false;
//            if(type != null && type.indexOf("ALL_") ==0)
//            {
//                isALL = true;
//                type = type.substring(4);
//            }
//
//            if("cd_ph_sender".equals(code_group)){
//                if(StringUtil.isNotEmpty(workerManager.getWorkerInfo(type, "DIRECT"))){
//                    data.append("<option value='"+ workerManager.getWorkerInfo(type, "DIRECT_HYPHEN") +"' selected='selected'>" + workerManager.getWorkerInfo(type, "DIRECT") + "</option>");
//                }
//            }
//
//            for (CodeInfo ci : list) {
//
//                if(ci == null || (!isALL && "N".equals(ci.getYn_use()))) {
//                    continue;
//                }
//                String val = ci.getCode_value();
//                if( !StringUtil.isEmpty(ci.getCode_value()) && ci.getCode_value().equals(selectValue)) {
//                    val += "' selected='selected";
//                }
//                else if ("DEFAULT".equals(selectValue)) {
//                    if(!StringUtil.isEmpty(ci.getEtc()) && ci.getEtc().indexOf("DEFAULT") != -1 )
//                    val += "' selected='selected";
//                }
//
//                if(type != null && type.equals("ID.NM")) {
//                    data.append("<option value='" + val + "'>" + ci.getCode_value() +"." + ci.getNm_code() + "</option>");
//                }
//                else {
//                    data.append("<option value='" + val + "'>" + ci.getNm_code() + "</option>");
//                }
//            }
//        }
//
//		return data.toString();
//
//	}
//	public static String makeOptionsOnClick(String code_group, String defaultText,String selectValue, String functionNm) {
//
//		CodeManager codeManager = (CodeManager)getBean("codeManager");
////		WorkerManager workerManager = (WorkerManager)getBean("workerManager");
//
//		CodeInfo codeInfo = new CodeInfo();
//		codeInfo.setCode_group(code_group);
//
//		List<CodeInfo> list = codeManager.listCodeInfo(codeInfo);
//
//		StringBuffer data = new StringBuffer();
//
//		data.append("<option value=''>" + defaultText + "</option>");
//		data.append("<option data-divider='true'></option>");
//
//		if(list == null)
//			return data.toString();
//
////		boolean isALL = false;
////		if(type != null && type.indexOf("ALL_") ==0)
////		{
////			isALL = true;
////			type = type.substring(4);
////		}
////
////		if("cd_ph_sender".equals(code_group)){
////			if(StringUtil.isNotEmpty(workerManager.getWorkerInfo(type, "DIRECT"))){
////				data.append("<option value='"+ workerManager.getWorkerInfo(type, "DIRECT_HYPHEN") +"' selected='selected'>" + workerManager.getWorkerInfo(type, "DIRECT") + "</option>");
////			}
////		}
//
//		for (CodeInfo ci : list) {
//
////			if(!isALL && "N".equals(ci.getYn_use()))
////				continue;
//
//			String val = ci.getCode_value();
//			if( !StringUtil.isEmpty(ci.getCode_value()) && ci.getCode_value().equals(selectValue)) {
//				val += "' selected='selected";
//			}
//			else if ("DEFAULT".equals(selectValue)) {
//				if(!StringUtil.isEmpty(ci.getEtc()) && ci.getEtc().indexOf("DEFAULT") != -1 )
//					val += "' selected='selected";
//			}
//
////			if(type.equals("ID.NM"))
////				data.append("<option value='" + val + "'>" + ci.getCode_value() +"." + ci.getNm_code() + "</option>");
////			else
//				data.append("<option value='" + val + "' onclick='"+functionNm+"'>" + ci.getNm_code() + "</option>");
//		}
//
//		return data.toString();
//
//	}
//
//	// Funciton Overloading
//	public static String makeOptions(String code_group, String defaultText,String selectValue) {
//		return makeOptions(code_group,defaultText,selectValue,"NM");
//	}
	// 키워드 보여주는 부분
	public static String makeKeyWordList(String keyWord) {
		String[] arrKeyword = StringUtil.tokenizeToStringArray(keyWord,",");
        StringBuilder data = new StringBuilder();
        String word = null;
		if (arrKeyword != null) {
			for(int i=0;i<arrKeyword.length;i++) {
                word = arrKeyword[i];
                data.append("<span class=\"label\">").append(word).append("</span>");
            }
		}
		String result = data.toString();
        return result;
	}
//
//	public static String makeGoodsOptions(String defaultText,String selectValue, String pType) {
//		GoodsManager goodsManager = (GoodsManager)getBean("goodsManager");
//		StringBuffer data = new StringBuffer();
//
//		List<GoodsVO> list = goodsManager.listCodeGoods();
//        String type = pType;
//		data.append("<option value=''>" + defaultText + "</option>");
//		data.append("<option data-divider='true'></option>");
//
//		boolean isALL = false;
//		if(type != null && type.indexOf("ALL_") ==0)
//		{
//			isALL = true;
//			type = type.substring(4);
//		}
//
//		for (GoodsVO gvo : list) {
//			String val = gvo.getCd_goods();
//			String nm = gvo.getNm_goods();
//			String str_select = "";
//
//			if(!isALL && "N".equals(gvo.getYn_use()))
//				continue;
//
//			if(StringUtil.isEmpty(val)){
//				continue;
//			}
//
//			if( !StringUtil.isEmpty(val) && val.equals(selectValue)) {
//				str_select = "selected='selected'";
//			}
//
//			if(type.equals("ID.NM"))
//				data.append("<option value='" + val + "' "+str_select+">" + val +"." + nm + "</option>");
//			else
//				data.append("<option value='" + val + "' "+str_select+">" + nm + "</option>");
//
//		}
//
//		return data.toString();
//
//	}
//
//	/**
//	 * 코드그룹과 코드이름으로 옵션을 만듭니다.
//	 * @param code_group
//	 * @param defaultText
//	 * @param selectName
//	 * @param pType
//	 * @return
//	 */
//	public static String makeOptionsName(String code_group, String defaultText, String selectName, String pType) {
//		CodeManager codeManager = (CodeManager)getBean("codeManager");
//
//		CodeInfo codeInfo = new CodeInfo();
//		codeInfo.setCode_group(code_group);
//        String type = pType;
//		List<CodeInfo> list = codeManager.listCodeInfo(codeInfo);
//
//		StringBuffer data = new StringBuffer();
//
//		data.append("<option value=''>" + defaultText + "</option>");
//		data.append("<option data-divider='true'></option>");
//
//		if(list != null) {
//            boolean isALL = false;
//            if(type != null && type.indexOf("ALL_") ==0)
//            {
//                isALL = true;
//                type = type.substring(4);
//            }
//
//            for (CodeInfo ci : list) {
//
//                if(ci == null || (!isALL && "N".equals(ci.getYn_use())))
//                    continue;
//
//                String val = ci.getNm_code();
//                if( !StringUtil.isEmpty(ci.getNm_code()) && ci.getNm_code().equals(selectName)) {
//                    val += "' selected='selected";
//                }
//                else if ("DEFAULT".equals(selectName)) {
//                    if(!StringUtil.isEmpty(ci.getEtc()) && ci.getEtc().indexOf("DEFAULT") != -1 )
//                    val += "' selected='selected";
//                }
//
//                if(type != null && type.equals("ID.NM"))
//                    data.append("<option value='" + val + "'>" + ci.getCode_value() +"." + ci.getNm_code() + "</option>");
//                else
//                    data.append("<option value='" + val + "'>" + ci.getNm_code() + "</option>");
//            }
//        }
//		return data.toString();
//
//	}
//
//
//	public static String makeRadioAndCheckBoxs(String code_group, String name, String type, String checkValues, int number, String pEvent) {
//
//		CodeManager codeManager = (CodeManager)getBean("codeManager");
//
//		CodeInfo codeInfo = new CodeInfo();
//		codeInfo.setCode_group(code_group);
//        String event = pEvent;
//		List<CodeInfo> list = codeManager.listCodeInfo(codeInfo);
//		StringBuffer data = new StringBuffer();
//		String[] values = checkValues.split(",");
//		int cnt = 1;
//
//		if(list != null) {
//
//            if(!StringUtil.isEmpty(event)) {
//                event = "onclick='"+event+"'";
//            }
//
//            for (CodeInfo ci : list) {
//
//                if(ci == null || "N".equals(ci.getYn_use()))
//                    continue;
//
//                String val = ci.getCode_value();
//
//                for ( String checkValue : values ) {
//                    if( !StringUtil.isEmpty(ci.getCode_value()) && ci.getCode_value().equals(checkValue))
//                    {
//                        val += "' checked='checked";
//                    }
//                }
//
//                data.append("<input type='" +type+ "' name='" +name+ "' id='" +name +cnt+ "' value='" +val+ "' " +event+ ">");
//                data.append("<label for='" +name +cnt+ "' class='checkbox-inline'>");
//                data.append(ci.getNm_code() +"&nbsp;");
//                data.append("</label>");
//
//                if ( number > 0 && cnt % number == 0 ) {
//                    data.append("<br/>");
//                }
//
//                cnt ++;
//            }
//        }
//
//		return data.toString();
//	}
//
//	// Funciton Overloading
//	public static String makeRadioAndCheckBoxs(String code_group, String name, String type, String checkValues, int number) {
//		return makeRadioAndCheckBoxs(code_group, name, type, checkValues, number, "");
//	}
//
//
//	public static String getProgram(String id_program, String pType) {
//		String type = pType;
//		ProgramManage pm = Constant.PROGRAM.get(id_program);
//        String result = "";
//		if(pm != null) {
//            if(StringUtil.isEmpty(type)) {
//                type="NM";
//            }
//
//            if(type.equals("ACTION")) {
//                result = pm.getNm_action();
//            }
//            else if(type.equals("SYSTEM")) {
//                result = pm.getCd_system();
//            }
//            else if(type.equals("WORK")) {
//                result = pm.getCd_work();
//            } else {
//                result = pm.getNm_program();
//            }
//        }
//        return result;
//	}
//
//	public static boolean isProgram(String id_program_target, String cd_system,String cd_work,String cd_program_group) {
//
//		if(StringUtil.isEmpty(id_program_target))
//			return false;
//
//		ProgramManage pm = Constant.PROGRAM.get(id_program_target);
//
//		if(pm == null) return false;
//
//		if(!StringUtil.isEmpty(cd_system) && !cd_system.equals(pm.getCd_system()))
//			return false;
//
//		if(!StringUtil.isEmpty(cd_work) && !cd_work.equals(pm.getCd_work()))
//			return false;
//
//		if(!StringUtil.isEmpty(cd_program_group) && !cd_program_group.equals(pm.getCd_program_group()))
//			return false;
//
//		return true;
//	}
//
//	public static String getApproval(String id_approval, String pType) {
//
//		ApprovalManage am = Constant.APPROVAL.get(id_approval);
//        String result = "";
//        String type= pType;
//		if(am != null) {
//
//            if(StringUtil.isEmpty(type)) {
//                type="NM";
//            }
//            if(type.equals("ACTION")) {
//                result = am.getNm_action();
//            }
//            else if(type.equals("GROUP")) {
//                result = am.getCd_approval_group();
//            }
//            else if(type.equals("WORK")) {
//                result = am.getCd_work();
//            } else {
//                result = am.getNm_appr();
//            }
//        }
//		return result;
//	}
//
//	public static boolean isApproval(String id_approval_target,String cd_work,String cd_approval_group) {
//
//		if(StringUtil.isEmpty(id_approval_target))
//			return false;
//
//		ApprovalManage am = Constant.APPROVAL.get(id_approval_target);
//
//		if(am == null) return false;
//
//		if(!StringUtil.isEmpty(cd_work) && !cd_work.equals(am.getCd_work()))
//			return false;
//
//		if(!StringUtil.isEmpty(cd_approval_group) && !cd_approval_group.equals(am.getCd_approval_group()))
//			return false;
//
//		return true;
//	}
//
//    public static String getWorkerInfo(String id_emp, String type) {
//    	WorkerManager workerManager = (WorkerManager)getBean("workerManager");
//		return workerManager.getWorkerInfo(id_emp, type);
//	}
//
//
//
////    public static String getPersonInfo(String no_person, String type) {
////    	PersonManager personManager = (PersonManager)getBean("personManager");
////    	return personManager.getPersonInfo(no_person, type);
////    }
//
//
//    public static String getCurrentDate() {
//		return DateUtil.getCurrentDate(DateUtil.DATE_PATTERN_DASH);
//	}
//
//    public static String getDayOfWeek() {
//		return DateUtil.getDayOfWeek(DateUtil.getCurrentDate());
//	}
//
//    public static String formatNumber(String number) {
//    	String rtn_num = null;
//    	try {
//    		rtn_num = NumberUtil.formatNumber(""+NumberUtil.stringToInt(number),NumberUtil.CURRENCY_NO_DECIMALPOINT);
//		} catch (ParseException e) {
//    		logger.debug("JSTLFunction.formatNumber:error-->"+e.toString());
//			rtn_num = "0";
//		}
//    	return rtn_num;
//	}
//
//    public static String formatNumberMan(String number) {
//    	String rtn_num = null;
//    	int intNumber = NumberUtil.stringToInt(number);
//    	double dblNumber = 0.0;
//    	String test;
//    	try {
//    	    dblNumber = Math.round(intNumber/1000)/10;
//    		test = Double.toString(dblNumber);
//    		rtn_num = NumberUtil.formatNumber(test,NumberUtil.CURRENCY_NO_DECIMALPOINT);
//		} catch (ParseException e) {
//			rtn_num = "0";
//		}
//
//    	return rtn_num;
//	}
//
//	public static String formatNumberThUint(String pNumber) {
//    	String rtn_num = null;
//        String number = pNumber;
//    	logger.info("number : " + number);
//    	try {
//    		number = number.substring(0, number.length() -4);
//    		rtn_num = NumberUtil.formatNumber(number, NumberUtil.CURRENCY_NO_DECIMALPOINT);
//		} catch (ParseException e) {
//			rtn_num = "0";
//		}
//    	logger.info("rtn_num : " + rtn_num);
//    	return rtn_num;
//	}
//
//    public static String formatDate(String date) {
//    	return DateUtil.formatDate(date,DateUtil.DATE_PATTERN_DASH);
//    }
//
    public static String formatDateDot(String pDate) {
		String result = null;
		String date = pDate;
	    if(date !=null) {
	        date = date.trim();
        }
    	if(date== null || date.equals("") || date.equals("-")) {
    	    result = "";
        } else {
        	if(date.length() == 8)	{
        		result = date.substring(0,4)+"."+date.substring(4,6)+"."+date.substring(6,8);
        	}
        	else if(date.length() == 6)	{
        		result = date.substring(0,4)+"."+date.substring(4,6);
        	}
        }
    	return result;
    }
//
//    public static String formatShortDate(String date) {
//    	return DateUtil.formatDate(date,"yy-MM-dd");
//    }
//
//   	public static String getFormattedTime(String frForm, String toForm, String thisDate) throws Exception {
//   		return DateUtil.getFormattedTime(frForm, toForm, thisDate);
//   	}
//
//    public static boolean isApprAuth(String id_appr_target, String str_apprs) {
//
//    	if(StringUtil.isEmpty(id_appr_target)) return false;
//
//    	String array_appr[] = StringUtil.deleteAny(str_apprs, "[]").split(",");
//    	for (String id_appr : array_appr) {
//			if(id_appr_target.equals(id_appr.trim()))
//			{
//				return true;
//			}
//		}
//
//    	return false;
//    }
//
//
//    public static List<String> getApplyPath(String path) {
//		return StringUtil.getApplyPath(path);
//	}
//
//    /**
// 	 * 날짜선택 박스 생성
// 	 * @param date : jsp에 뿌려줄 날짜선택 메뉴
// 	 * @param from : jsp에 세팅해줄 from input의 네임값
// 	 * @param to : : jsp에 세팅해줄 to input의 네임값
// 	 * @return
// 	 */
// 	public static String quickYmdFromTo(String date, String from, String to){
//
// 		String[] dataArray = date.split(",");
// 		StringBuffer data = new StringBuffer();
//
// 		data.append("<div class=\"dropdown\">\n");
// 		data.append("<button id=\"selectDate\" type=\"button\" class=\"btn btn-default\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">날짜<span class=\"caret\"></span></button>\n");
// 		data.append("<ul id=\"dateMenu\" class=\"dropdown-menu\" aria-labelledby=\"selectDate\" style=\"min-width:0px;\">\n");
//
// 		//배열의 길이만큼 for문
// 		for (int i = 0; i < dataArray.length; i++) {
//
// 			if("T".equals(dataArray[i]))
// 				data.append("<li onclick=\"getDate('T', '"+from+"', '"+to+"');\"><a href='#'>오늘</a></li>\n");
// 			else if("Y".equals(dataArray[i]))
// 				data.append("<li onclick=\"getDate('Y', '"+from+"', '"+to+"');\"><a href='#'>어제</a></li>\n");
// 			else if("TM".equals(dataArray[i]))
// 				data.append("<li onclick=\"getDate('TM', '"+from+"', '"+to+"');\"><a href='#'>내일</a></li>\n");
// 			else if("W".equals(dataArray[i]))
// 				data.append("<li onclick=\"getDate('W', '"+from+"', '"+to+"');\"><a href='#'>금주</a></li>\n");
// 			else if("LW".equals(dataArray[i]))
// 				data.append("<li onclick=\"getDate('LW', '"+from+"', '"+to+"');\"><a href='#'>전주</a></li>\n");
// 			else if("M".equals(dataArray[i]))
// 				data.append("<li onclick=\"getDate('M', '"+from+"', '"+to+"');\"><a href='#'>당월</a></li>\n");
// 			else if("LM".equals(dataArray[i]))
// 				data.append("<li onclick=\"getDate('LM', '"+from+"', '"+to+"');\"><a href='#'>전월</a></li>\n");
// 		}
//
// 		data.append("</ul>\n");
// 		data.append("</div>\n");
//
// 		return data.toString();
// 	}
//
// 	public static String quickYmd(String date, String ymd){
// 		return quickYmdFromTo(date,ymd,"");
// 	}
//
//    public static String formatMaskHp(String hp) {
//    	return StringUtil.formatMaskHp(hp);
//	}
//
//	public static String getSummaryContent(String content, int size){
// 		if(StringUtil.isEmpty(content)) return "";
//
// 		return StringUtil.left(StringUtil.replaceContentLineFeed(content, " "), size);
// 	}
//
// 	public static String makeGoodsOptions(String defaultText, String id_agency, String selectValue, String pType) {
//        GoodsManager goodsManager = (GoodsManager)getBean("goodsManager");
//        StringBuffer data = new StringBuffer();
//
//        List<GoodsVO> list = goodsManager.listGoodsAgency(id_agency);
//
//        String val = null;
//        String nm = null;
//
//        data.append("<option value=''>" + defaultText + "</option>");
//        data.append("<option data-divider='true'></option>");
//
//        boolean isALL = false;
//        String type = pType;
//        if(type != null && type.indexOf("ALL_") ==0) {
//            isALL = true;
//            type = type.substring(4);
//        }
//
//         if (list != null) {
//             for (GoodsVO gvo : list) {
//                 if (gvo != null) {
//                     val = gvo.getCd_goods();
//                     nm = gvo.getNm_goods();
//                     if(!isALL && "N".equals(gvo.getYn_use()))
//                         continue;
//
//                     if(StringUtil.isEmpty(val)){
//                         continue;
//                     }
//
//                     if( !StringUtil.isEmpty(val) && val.equals(selectValue)) {
//                         val += "' selected='selected";
//                     }
//
//                     if(type.equals("ID.NM")) {
//                         data.append("<option value='" + val + "'>" + val +"." + nm + "</option>");
//                     }
//                     else {
//                         data.append("<option value='" + val + "'>" + nm + "</option>");
//                     }
//                 }
//             }
//         }
//
//         return data.toString();
//
//    }
//
//	 	/**
//		 * 게시판 정보
//		 * @param id_board
//		 * @param type
//		 * @return
//		 */
//		public static String getBoardInfo(String id_board, String type) {
//			BoardManager boardManager = (BoardManager)getBean("boardManager");
//
//			BoardManage boardManage = new BoardManage();
//			boardManage.setId_board(id_board);
//			boardManage = boardManager.getBoardManage(boardManage);
//
//			if(boardManage == null) return "";
//
//			if("NM".equals(type)) return boardManage.getNm_board();
//			else if("OPTION".equals(type)) return boardManage.getCd50_board_type();
//			else return id_board;
//		}
//
//        /**
//         * 코드명을 반환합니다.
//         * @param cd_goods
//         * @param type
//         * @return
//         */
//		public static String getGoodsDetail(String cd_goods, String type)
//		{
//			GoodsManager goodsManager = (GoodsManager)getBean("goodsManager");
//			return goodsManager.getGoodsDetail(cd_goods, type);
//		}
//
//		/**
//		 * 설명을 반환합니다
//		 * @param group
//		 * @param id
//		 * @return
//		 */
//		public static String getCodeEtc(String group, String id)
//		{
//			CodeManager codeManager = (CodeManager)getBean("codeManager");
//			CodeInfo code = codeManager.getCodeInfo(group, id);
//
//			if(code == null) return id;
//
//			return code.getEtc();
//		}
//
//		public static String makeGoodsTypeRadioAndCheckBoxs(String cd_goods_tab, String goods_type_comp, String name, String type, String checkValues, int number, String event) {
//			GoodsManager goodsManager = (GoodsManager)getBean("goodsManager");
//			CodeManager codeManager = (CodeManager)getBean("codeManager");
//
//			List<GoodsVO> list = new ArrayList<GoodsVO>();
//			String strEvent = "";
//			//01 : 상품구분 기준 / 02: 금융사 기준 상품 탭 설정
//			if("01".equals(cd_goods_tab)){
//				list = goodsManager.listGoodsInfoBYType(goods_type_comp);
//			}else list = goodsManager.listGoodsInfoBYComp(goods_type_comp);
//
//
//			int cntList = list.size();
//			int addList = number - (cntList % number);
//
//			StringBuffer data = new StringBuffer();
//
//			String[] values = checkValues.split(",");
//			int cnt = 1;
//
//			if(list == null || list.size() == 0)
//				return data.toString();
//
//
//			if(!StringUtil.isEmpty(event)) {
//				strEvent = "onclick='"+event+"'";
//			}
//
//			data.append("<colgroup>");
//			for(int i = 0, n = number; i < n; i++){
//				int width = Math.round(100 / number);
//				data.append("<col width=\""+width+"%\"/>");
//			}
//			data.append("</colgroup>");
//
//			data.append("<tbody><tr>");
//
//			for (GoodsVO gvo : list) {
//
//				if("N".equals(gvo.getYn_use()))
//					continue;
//
//				String val = gvo.getCd_goods();
//
//				String nm_comp = codeManager.getCodeName("cd_fc",gvo.getCd_fc());
//				String summary = "";
//				String check = "";
//
//				if("Y".equals(gvo.getYn_first()))
//					check = "★";
//
//				if(gvo.getSummary() != null){
//					summary = "<a href=\"#\" class=\"a-font-normal\" onclick=\"goGoodsModal('"+val+"'); return false;\"><span id=\"popover"+val+"\">[" + nm_comp + "]" + gvo.getNm_goods()+"</span></a><script type=\"text/javascript\">$(\"#popover"+val+"\").popover({trigger:\"hover\",html : true,placement : \"bottom\",title:\"간단설명\",content: function() {return $(\"#pop_content"+val+"\").html();}});</script><div id=\"pop_content"+val+"\" class=\"popover\" role=\"tooltip\"><div>"+gvo.getSummary()+"</div></div>";
//				}else{
//					summary = "<a href=\"#\" class=\"a-font-normal\" onclick=\"goGoodsModal('"+val+"'); return false;\"><span id=\"popover"+val+"\">[" + nm_comp + "]" + gvo.getNm_goods()+"</span></a><div id=\"pop_content"+val+"\" class=\"popover\" role=\"tooltip\"><div>"+gvo.getSummary()+"</div></div>";
//				}
//
//				for ( String checkValue : values ) {
//					if( !StringUtil.isEmpty(gvo.getCd_goods()) && gvo.getCd_goods().equals(checkValue))
//					{
//						val += "' checked='checked";
//					}
//				}
//
//				data.append("<td><span class=\"pull-left\">");
//				data.append("<input type='" +type+ "' name='" +name+ "' id='" +name +cnt+ "' value='" +val+ "' " +strEvent + ">");
//				data.append("<label for='" +name +cnt+ "' class='checkbox-inline'>");
//				data.append("</label>");
//				data.append(check);
//				data.append(summary);
//				data.append("</span>");
//
//				if(StringUtil.isNotEmpty(gvo.getPath_file1()) || StringUtil.isNotEmpty(gvo.getPath_file2())){
//					data.append("<script type=\"text/javascript\">$(function(){$('[data-toggle=\"tooltip\"]').tooltip()});</script>");
//					data.append("<span class=\"pull-right\">");
//				}
//
//				if(StringUtil.isNotEmpty(gvo.getPath_file1())){
////					data.append("<a href=\"/attach/getFile.crz?file_name="+gvo.getPath_file1()+"\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"동의서\"><img src=\"../img/ico_download.png\" alt=\"파일다운로드\"> </a>");
//				}
//				if(StringUtil.isNotEmpty(gvo.getPath_file2())){
////					data.append("<a href=\"/attach/getFile.crz?file_name="+gvo.getPath_file2()+"\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"기타\"><img src=\"../img/ico_download2.png\" alt=\"파일다운로드\"></a>");
//				}
//
//				if(StringUtil.isNotEmpty(gvo.getPath_file1()) || StringUtil.isNotEmpty(gvo.getPath_file2())){
//				data.append("</span>");
//				}
//
//				data.append("</td>");
//
//				if ( number > 0 && cnt % number == 0 ) {
//					data.append("</tr><tr>");
//				}
//
//				cnt ++;
//			}
//
//			if(cntList < number){
//				addList = number - cntList;
//			}
//			for(int i = 0, n = addList; i < n; i++){
//				data.append("<td></td>");
//			}
//
//			data.append("</tr></tbody>");
//			return data.toString();
//		}
//
		public static String getNmFc(String cd_fc){
	 		FincorpManager fincorpManager = (FincorpManager)getBean("fincorpManager");
			return StringUtil.nullToString(fincorpManager.getNmFc(cd_fc));
	 	}
//
//		public static String getGoodsEvent(String code_group, String checkValues)
//		{
//			logger.info("checkValues : "+checkValues);
//			CodeManager codeManager = (CodeManager)getBean("codeManager");
//			CodeInfo codeInfo = new CodeInfo();
//			codeInfo.setCode_group(code_group);
//			List<CodeInfo> list = codeManager.listCodeInfo(codeInfo);
//			StringBuffer data = new StringBuffer();
//			String[] values = checkValues.split(",");
//			int valuesLength = values.length;
//			int index = 0;
//			for (CodeInfo ci : list) {
//				String val = ci.getCode_value();
//				for(int i = 0; i < values.length; i++){
//					if( !StringUtil.isEmpty(ci.getCode_value()) && ci.getCode_value().equals(values[i])){
//						if(values.length == 1){
//							data.append("<li>"+ci.getNm_code()+"</li>");
//						} else {
//							if(i == values.length-1){
//								data.append("<li>"+ci.getNm_code()+"</li>");
//							} else{
//								data.append("<li>"+ci.getNm_code()+"</li><span class='bar'>|</span>");
//							}
//						}
//					}
//				}
//			}
//			return data.toString();
//		}
//
//		public static String chgYearToMonth(String year){
//		    String result = null;
//			if("1".equals(year)){
//				result = "12";
//			}else if("2".equals(year)){
//				result = "24";
//			}else if("3".equals(year)){
//				result = "36";
//			}else if("4".equals(year)){
//				result = "48";
//			}else if("5".equals(year)){
//				result = "60";
//			} else {
//                result = year;
//            }
//			return result;
//		}
//
//		public static String getFcPathFile(String cd_fc)
//		{
//			FincorpManager fincorpManager = (FincorpManager)getBean("fincorpManager");
//			String fcPathFile = fincorpManager.getFcPathFile(cd_fc);
//			return fcPathFile;
//		}
//		
		public static String formatNumberPattern(String number, String pattern) {
	    	
			String rtn_num = null;
			if("NaN".equals(number)) {
				rtn_num = "0";
            } else if(!"-".equals(number)) {
                try {
                    DecimalFormat df = new DecimalFormat(pattern);
                    rtn_num = df.format(NumberUtil.stringToDouble(number));
                } catch (NumberFormatException e) {
                    logger.debug("ERROR==>"+e.toString());
                    rtn_num = "0";
                }
            } else {
			    rtn_num = number;
            }
			
	    	return rtn_num;
		}
}
