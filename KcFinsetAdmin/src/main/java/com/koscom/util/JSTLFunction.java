package com.koscom.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.board.model.BoardForm;
import com.koscom.board.service.BoardManager;
import com.koscom.domain.BoardManage;
import com.koscom.domain.WorkerInfo;
import com.koscom.edoc.model.EdocForm;
import com.koscom.edoc.model.EdocInfo;
import com.koscom.edoc.model.EdocVO;
import com.koscom.edoc.service.EdocManager;
import com.koscom.env.model.ApprovalManage;
import com.koscom.env.model.ApprovalManageVO;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.model.ProgramManage;
import com.koscom.env.service.CodeManager;
import com.koscom.fccode.model.FcCodeForm;
import com.koscom.fccode.model.FcCodeInfo;
import com.koscom.fccode.model.FcCodeVO;
import com.koscom.fccode.service.FcCodeManager;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.service.GoodsManager;
import com.koscom.stdcode.model.StdCodeForm;
import com.koscom.stdcode.model.StdCodeInfo;
import com.koscom.stdcode.model.StdCodeVO;
import com.koscom.stdcode.service.StdCodeManager;
import com.koscom.worker.service.WorkerManager;


public class JSTLFunction {

	private static final Logger logger = LoggerFactory.getLogger(JSTLFunction.class);

	/**
	 *  ApplicationContext 빈 가져오기
	 * @param bean
	 * @return
	 */
	private static Object getBean(String bean){
		return SpringApplicationContext.getBean(bean);
	}

	/**
	 * 코드명을 반환합니다.
	 * @param group
	 * @param id
	 * @return
	 */
	public static String getCodeName(String group, String id){
		CodeManager codeManager = (CodeManager)getBean("codeManager");
		CodeInfo code = codeManager.getCodeInfo(group, id);
		if(code == null) return id;
		return code.getNm_code();
	}
	/**
	 * 표준코드명 반환
	 * @param group
	 * @param id
	 * @return
	 */
	public static String getStdCodeName(String group, String id){
		StdCodeManager stdCodeManager = (StdCodeManager)getBean("stdCodeManager");
		StdCodeInfo stdCodeInfo = stdCodeManager.getStdCodeInfo(group, id);
		if(stdCodeInfo  == null) return id;
		return stdCodeInfo.getNm_code();
	}
	/**
	 * 설명을 반환합니다
	 * @param group
	 * @param id
	 * @return
	 */
	public static String getCodeEtc(String group, String id){
		CodeManager codeManager = (CodeManager)getBean("codeManager");
		CodeInfo code = codeManager.getCodeInfo(group, id);

		if(code == null) return id;

		return code.getEtc();
	}
	public static String getNvlCodeName(String group, String id, String defaultStr){
		if (StringUtil.isEmpty(group) || StringUtil.isEmpty(id)) {
			return defaultStr;
		}
		CodeManager codeManager = (CodeManager)getBean("codeManager");
		String returnValue = codeManager.getCodeName(group, id);

		// codeValue 와 returnValue 가 같다는 의미는
		// code name이 null 이란 의미이다
		if(StringUtil.isEmpty(returnValue) || id.equals(returnValue))
			return defaultStr;
		return returnValue;
	}

	/**
	 * 코드목록을 반환합니다.
	 * @param code_group
	 * @return
	 */
	public static List<CodeInfo> getCodeList(String code_group){
		CodeManager codeManager = (CodeManager)getBean("codeManager");
		return codeManager.listCodeInfo(code_group);
	}
	public static List<FcCodeInfo> getCodeFcList(String code_group){
		FcCodeManager fcCodeManager = (FcCodeManager)getBean("fcCodeManager");
		return fcCodeManager.listFcCodeInfo(code_group);
	}
	/**
	 * 담당자 셀렉트 박스 생성
	 * @param idAppr
	 * @param defaultText
	 * @param selectValue
	 * @return
	 */
	public static String makeIdOptions(String idAppr, String defaultText, String selectValue) {
		return makeIdOptionsByBranch(idAppr, defaultText, selectValue, "000");
	}

	/**
	 * 지점별 담당자 셀렉트 박스 생성
	 * @param idAppr
	 * @param c3_branch
	 * @param defaultText
	 * @param selectValue
	 * @return
	 */
	public static String makeIdOptionsByBranch(String idAppr, String defaultText, String selectValue, String c3_branch) {

		WorkerManager workerManager = (WorkerManager)getBean("workerManager");
		List<ApprovalManageVO> list = workerManager.listCacheApprovalAuth(idAppr);

		StringBuffer data = new StringBuffer();
		data.append("<option value=\"\">" + defaultText + "</option>");
		data.append("<option data-divider='true'></option>");

		if(list == null)
			return data.toString();

		for (ApprovalManageVO WAA : list) {

			String val = WAA.getId_emp();
			if( !StringUtil.isEmpty(WAA.getId_emp()) && WAA.getId_emp().equals(selectValue))
				val += "\" selected=\"selected";
			if( !"000".equals(c3_branch) && c3_branch.equals(WAA.getC3_branch())){
				data.append("\n<option value=\"" + val + "\">" + WAA.getNm_emp() + "</option>");
			}else if("000".equals(c3_branch)){
				data.append("\n<option value=\"" + val + "\">" + WAA.getNm_emp() + "</option>");
			}
		}
		return data.toString();
	}

	/**
	 * 모든 직원 셀렉트 박스 생성
	 * @param defaultText
	 * @param selectValue
	 * @return
	 */
	public static String makeAllIdOptions(String defaultText, String selectValue) {
		return makeAllIdOptionsByBranch(defaultText, selectValue, "000");
	}

	/**
	 * 지점별 모든 직원 셀렉트 박스 생성
	 * @param c3_branch
	 * @param defaultText
	 * @param selectValue
	 * @return
	 */
	public static String makeAllIdOptionsByBranch(String defaultText, String selectValue, String c3_branch) {

		WorkerManager workerManager = (WorkerManager)getBean("workerManager");
		List<WorkerInfo> list = workerManager.listWorkerInfoStatus();

		StringBuffer data = new StringBuffer();
		data.append("<option value=\"\">" + defaultText + "</option>");
		data.append("<option data-divider='true'></option>");

		if(list == null)
			return data.toString();

		for (WorkerInfo WI : list) {

			String val = WI.getId_emp();
			if( !StringUtil.isEmpty(WI.getId_emp()) && WI.getId_emp().equals(selectValue))
				val += "\" selected=\"selected";
			if( !"000".equals(c3_branch) && c3_branch.equals(WI.getC3_branch())){
				data.append("\n<option value=\"" + val + "\">" + WI.getNm_emp() + "</option>");
			}else if("000".equals(c3_branch)){
				data.append("\n<option value=\"" + val + "\">" + WI.getNm_emp() + "</option>");
			}
		}
		return data.toString();
	}

	/**
	 * 코드그룹명을 받아 해당 코드리스트를 select box 안의 option 으로 변환합니다.
	 * @param code_group 코드그룹명
	 * @param defaultText 기본텍스트
	 * @param selectValue 선택 되어진 텍스트 ( 만약 선택 값이 존재한다면)
	 * @param pType = 1:NM(Default) , 2:ID.NM ,3:ETC
	 * @return
	 */
	public static String makeOptions(String code_group, String defaultText,String selectValue, String pType) {
        String type = pType;
		CodeManager codeManager = (CodeManager)getBean("codeManager");
		WorkerManager workerManager = (WorkerManager)getBean("workerManager");

		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setCode_group(code_group);

		List<CodeInfo> list = codeManager.listCodeInfo(codeInfo);

		StringBuffer data = new StringBuffer();

		data.append("<option value=''>" + defaultText + "</option>");
		data.append("<option data-divider='true'></option>");

		if(list == null)
			return data.toString();

		boolean isALL = false;
		if(type != null && type.indexOf("ALL_") ==0)
		{
			isALL = true;
			type = type.substring(4);
		}

		if("cd_ph_sender".equals(code_group)){
			if(StringUtil.isNotEmpty(workerManager.getWorkerInfo(type, "DIRECT"))){
				data.append("<option value='"+ workerManager.getWorkerInfo(type, "DIRECT_HYPHEN") +"' selected='selected'>" + workerManager.getWorkerInfo(type, "DIRECT") + "</option>");
			}
		}

		for (CodeInfo ci : list) {

			if(!isALL && "N".equals(ci.getYn_use()))
				continue;

			String val = ci.getCode_value();
			if( !StringUtil.isEmpty(ci.getCode_value()) && ci.getCode_value().equals(selectValue)) {
				val += "' selected='selected";
			}
			else if ("DEFAULT".equals(selectValue)) {
				if(!StringUtil.isEmpty(ci.getEtc()) && ci.getEtc().indexOf("DEFAULT") != -1 )
				val += "' selected='selected";
			}

			if(type.equals("ID.NM"))
				data.append("<option value='" + val + "'>" + ci.getCode_value() +"." + ci.getNm_code() + "</option>");
			else
				data.append("<option value='" + val + "'>" + ci.getNm_code() + "</option>");
		}

		return data.toString();

	}

	// Funciton Overloading
	public static String makeOptions(String code_group, String defaultText,String selectValue) {
		return makeOptions(code_group,defaultText,selectValue,"NM");
	}

	public static String makeGoodsOptions(String defaultText,String selectValue, String pType) {
        String type = pType;
		GoodsManager goodsManager = (GoodsManager)getBean("goodsManager");
		StringBuffer data = new StringBuffer();

		List<GoodsVO> list = goodsManager.listCodeGoods();

		data.append("<option value=''>" + defaultText + "</option>");
		data.append("<option data-divider='true'></option>");

		boolean isALL = false;
		if(type != null && type.indexOf("ALL_") ==0)
		{
			isALL = true;
			type = type.substring(4);
		}

		for (GoodsVO gvo : list) {
			String val = gvo.getCd_fc();
			String nm = gvo.getNm_goods();
			String str_select = "";

			if(!isALL && "N".equals(gvo.getYn_use()))
				continue;

			if(StringUtil.isEmpty(val)){
				continue;
			}

			if( !StringUtil.isEmpty(val) && val.equals(selectValue)) {
				str_select = "selected='selected'";
			}

			if(type.equals("ID.NM"))
				data.append("<option value='" + val + "' "+str_select+">" + val +"." + nm + "</option>");
			else
				data.append("<option value='" + val + "' "+str_select+">" + nm + "</option>");

		}

		return data.toString();

	}

	/**
	 * 코드그룹과 코드이름으로 옵션을 만듭니다.
	 * @param code_group
	 * @param defaultText
	 * @param selectName
	 * @param pType
	 * @return
	 */
	public static String makeOptionsName(String code_group, String defaultText, String selectName, String pType) {
        String type = pType;
		CodeManager codeManager = (CodeManager)getBean("codeManager");

		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setCode_group(code_group);

		List<CodeInfo> list = codeManager.listCodeInfo(codeInfo);

		StringBuffer data = new StringBuffer();

		data.append("<option value=''>" + defaultText + "</option>");
		data.append("<option data-divider='true'></option>");

		if(list == null)
			return data.toString();

		boolean isALL = false;
		if(type != null && type.indexOf("ALL_") ==0)
		{
			isALL = true;
			type = type.substring(4);
		}

		for (CodeInfo ci : list) {

			if(!isALL && "N".equals(ci.getYn_use()))
				continue;

			String val = ci.getNm_code();
			if( !StringUtil.isEmpty(ci.getNm_code()) && ci.getNm_code().equals(selectName)) {
				val += "' selected='selected";
			}
			else if ("DEFAULT".equals(selectName)) {
				if(!StringUtil.isEmpty(ci.getEtc()) && ci.getEtc().indexOf("DEFAULT") != -1 )
				val += "' selected='selected";
			}

			if(type.equals("ID.NM"))
				data.append("<option value='" + val + "'>" + ci.getCode_value() +"." + ci.getNm_code() + "</option>");
			else
				data.append("<option value='" + val + "'>" + ci.getNm_code() + "</option>");
		}

		return data.toString();

	}

	/**
	 * 코드그룹과 기타정보로 옵션을 만듭니다.(option value - etc)
	 * @param code_group
	 * @param defaultText
	 * @param selectName
	 * @param pType
	 * @return
	 */
	public static String makeOptionsEtc(String code_group, String defaultText, String selectName, String pType) {
        String type = pType;
		CodeManager codeManager = (CodeManager)getBean("codeManager");

		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setCode_group(code_group);

		List<CodeInfo> list = codeManager.listCodeInfo(codeInfo);

		StringBuffer data = new StringBuffer();

		data.append("<option value=''>" + defaultText + "</option>");
		data.append("<option data-divider='true'></option>");

		if(list == null)
			return data.toString();

		boolean isALL = false;
		if(type != null && type.indexOf("ALL_") ==0)
		{
			isALL = true;
			type = type.substring(4);
		}

		for (CodeInfo ci : list) {

			if(!isALL && "N".equals(ci.getYn_use()))
				continue;

			String val = ci.getEtc();
			if( !StringUtil.isEmpty(ci.getEtc()) && ci.getEtc().equals(selectName)) {
				val += "' selected='selected";
			}

			if(type.equals("ID.NM"))
				data.append("<option value='" + val + "'>" + ci.getCode_value() +"." + ci.getNm_code() + "</option>");
			else
				data.append("<option value='" + val + "'>" + ci.getNm_code() + "</option>");
		}

		return data.toString();

	}

	public static String makeBoardOptions(String defaultText, String selectValue) {
		BoardManager boardManager = (BoardManager)getBean("boardManager");
		BoardForm boardForm = new BoardForm();
		List<BoardManage> list = boardManager.listBoardManage(boardForm);

		StringBuffer data = new StringBuffer();
		data.append("<option value=\"\">" + defaultText + "</option>");
		data.append("<option data-divider='true'></option>");

		if(list == null)
			return data.toString();

		for (BoardManage BM : list) {

			if("N".equals(BM.getYn_use())) continue;

			String val = BM.getId_board();
			if( !StringUtil.isEmpty(val) && val.equals(selectValue)) {
				val += "\" selected=\"selected";
			}
			data.append("\n<option value=\"" + val + "\">" + BM.getNm_board() + "</option>");
		}
		return data.toString();
	}

//	public static String makeAgencyOptions(String defaultText, String selectValue) {
//		AgencyManager agencyManager = (AgencyManager)getBean("agencyManager");
//		AgencyForm agencyForm = new AgencyForm();
//		List<AgencyVO> list = agencyManager.listAgency(agencyForm);
//
//		StringBuffer data = new StringBuffer();
//		data.append("<option value=\"\">" + defaultText + "</option>");
//		data.append("<option data-divider='true'></option>");
//
//		if(list == null)
//			return data.toString();
//
//		for (AgencyVO agencyVO : list) {
//
//			if("N".equals(agencyVO.getYn_use())) continue;
//
//			String val = agencyVO.getId_agency();
//			if( !StringUtil.isEmpty(val) && val.equals(selectValue)) {
//				val += "\" selected=\"selected";
//			}
//			data.append("\n<option value=\"" + val + "\">" + agencyVO.getNm_agency() + "</option>");
//		}
//		return data.toString();
//	}
//
	public static String makeRadioAndCheckBoxs(String code_group, String name, String type, String checkValues
            , int number, String pEvent) {
        String event = pEvent;

		CodeManager codeManager = (CodeManager)getBean("codeManager");

		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setCode_group(code_group);

		List<CodeInfo> list = codeManager.listCodeInfo(codeInfo);
		StringBuffer data = new StringBuffer();
		String[] values = checkValues.split(",");
		int cnt = 1;

		if(list == null)
			return data.toString();

		if(!StringUtil.isEmpty(event)) {
			event = "onclick='"+event+"'";
		}

		for (CodeInfo ci : list) {

			if("N".equals(ci.getYn_use()))
				continue;

			String val = ci.getCode_value();

			for ( String checkValue : values ) {
				if( !StringUtil.isEmpty(ci.getCode_value()) && ci.getCode_value().equals(checkValue))
				{
					val += "' checked='checked";
				}
			}

			data.append("<input type='" +type+ "' name='" +name+ "' id='" +name +cnt+ "' value='" +val+ "' " +event+ ">");
			data.append("<label for='" +name +cnt+ "' class='checkbox-inline'>");
			data.append(ci.getNm_code() +"&nbsp;");
			data.append("</label>");

			if ( number > 0 && cnt % number == 0 ) {
				data.append("<br/>");
			}

			cnt ++;
		}

		return data.toString();
	}

	// Funciton Overloading
	public static String makeRadioAndCheckBoxs(String code_group, String name, String type, String checkValues, int number) {
		return makeRadioAndCheckBoxs(code_group, name, type, checkValues, number, "");
	}

	public static String makeGoodsTypeRadioAndCheckBoxs(String cd_goods_tab, String goods_type_comp, String name
            , String type, String checkValues, int number, String pEvent) {
        String event = pEvent;
		GoodsManager goodsManager = (GoodsManager)getBean("goodsManager");
		CodeManager codeManager = (CodeManager)getBean("codeManager");

		List<GoodsVO> list = new ArrayList<GoodsVO>();

		//01 : 상품구분 기준 / 02: 금융사 기준 상품 탭 설정
		if("01".equals(cd_goods_tab)){
			list = goodsManager.listGoodsInfoBYType(goods_type_comp);
		}else list = goodsManager.listGoodsInfoBYComp(goods_type_comp);


		int cntList = list.size();
		int addList = number - (cntList % number);

		StringBuffer data = new StringBuffer();

		String[] values = checkValues.split(",");
		int cnt = 1;

		if(list == null || list.size() == 0)
			return data.toString();


		if(!StringUtil.isEmpty(event)) {
			event = "onclick='"+event+"'";
		}

		data.append("<colgroup>");
		for(int i = 0, n = number; i < n; i++){
			int width = Math.round(100 / number);
			data.append("<col width=\""+width+"%\"/>");
		}
		data.append("</colgroup>");

		data.append("<tbody><tr>");

		for (GoodsVO gvo : list) {

			if("N".equals(gvo.getYn_use()))
				continue;

			String val = gvo.getCd_goods();

			String nm_comp = codeManager.getCodeName("cd_fc",gvo.getCd_fc());
			String summary = "";
			String check = "";

			if("Y".equals(gvo.getYn_first()))
				check = "★";

			if(gvo.getSummary() != null){
				summary = "<a href=\"#\" class=\"a-font-normal\" onclick=\"goGoodsModal('"+val+"'); return false;\"><span id=\"popover"+val+"\">[" + nm_comp + "]" + gvo.getNm_goods()+"</span></a><script type=\"text/javascript\">$(\"#popover"+val+"\").popover({trigger:\"hover\",html : true,placement : \"bottom\",title:\"간단설명\",content: function() {return $(\"#pop_content"+val+"\").html();}});</script><div id=\"pop_content"+val+"\" class=\"popover\" role=\"tooltip\"><div>"+gvo.getSummary()+"</div></div>";
			}else{
				summary = "<a href=\"#\" class=\"a-font-normal\" onclick=\"goGoodsModal('"+val+"'); return false;\"><span id=\"popover"+val+"\">[" + nm_comp + "]" + gvo.getNm_goods()+"</span></a><div id=\"pop_content"+val+"\" class=\"popover\" role=\"tooltip\"><div>"+gvo.getSummary()+"</div></div>";
			}

			for ( String checkValue : values ) {
				if( !StringUtil.isEmpty(gvo.getCd_goods()) && gvo.getCd_goods().equals(checkValue))
				{
					val += "' checked='checked";
				}
			}

			data.append("<td><span class=\"pull-left\">");
			data.append("<input type='" +type+ "' name='" +name+ "' id='" +name +cnt+ "' value='" +val+ "' " +event+ ">");
			data.append("<label for='" +name +cnt+ "' class='checkbox-inline'>");
			data.append("</label>");
			data.append(check);
			data.append(summary);
			data.append("</span>");

			if(StringUtil.isNotEmpty(gvo.getPath_file1()) || StringUtil.isNotEmpty(gvo.getPath_file2())){
				data.append("<script type=\"text/javascript\">$(function(){$('[data-toggle=\"tooltip\"]').tooltip()});</script>");
				data.append("<span class=\"pull-right\">");
			}

			if(StringUtil.isNotEmpty(gvo.getPath_file1())){
				data.append("<a href=\"/attach/getFile.crz?file_name="+gvo.getPath_file1()+"\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"동의서\"><img src=\"../img/ico_download.png\" alt=\"파일다운로드\"> </a>");
			}
			if(StringUtil.isNotEmpty(gvo.getPath_file2())){
				data.append("<a href=\"/attach/getFile.crz?file_name="+gvo.getPath_file2()+"\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"기타\"><img src=\"../img/ico_download2.png\" alt=\"파일다운로드\"></a>");
			}

			if(StringUtil.isNotEmpty(gvo.getPath_file1()) || StringUtil.isNotEmpty(gvo.getPath_file2())){
			data.append("</span>");
			}

			data.append("</td>");

			if ( number > 0 && cnt % number == 0 ) {
				data.append("</tr><tr>");
			}

			cnt ++;
		}

		if(cntList < number){
			addList = number - cntList;
		}
		for(int i = 0, n = addList; i < n; i++){
			data.append("<td></td>");
		}

		data.append("</tr></tbody>");
		return data.toString();
	}

//	public static String makeAgencyRadioAndCheckBoxs(String name, String type, String checkValues, int number, String event) {
//
//		AgencyManager agencyManager = (AgencyManager)getBean("agencyManager");
//
//		AgencyForm agencyForm = new AgencyForm();
//		List<AgencyVO> list = agencyManager.listAgency(agencyForm);
//
//		StringBuffer data = new StringBuffer();
//		String[] values = checkValues.split(",");
//		int cnt = 1;
//
//		if(list == null)
//			return data.toString();
//
//		if(!StringUtil.isEmpty(event)) {
//			event = "onclick='"+event+"'";
//		}
//
//		for (AgencyVO ai : list) {
//
//			if("N".equals(ai.getYn_use()))
//				continue;
//
//			String val = ai.getId_agency();
//
//			for ( String checkValue : values ) {
//				if( !StringUtil.isEmpty(ai.getId_agency()) && ai.getId_agency().equals(checkValue))
//				{
//					val += "' checked='checked";
//				}
//			}
//
//			data.append("<input type='" +type+ "' name='" +name+ "' id='" +name +cnt+ "' value='" +val+ "' " +event+ ">");
//			data.append("<label for='" +name +cnt+ "' class='checkbox-inline'>");
//			data.append(ai.getNm_agency() +"&nbsp;");
//			data.append("</label>");
//
//			if ( number > 0 && cnt % number == 0 ) {
//				data.append("<br/>");
//			}
//
//			cnt ++;
//		}
//
//		return data.toString();
//	}

	public static String getProgram(String id_program, String pType) {
        String type = pType;

		ProgramManage pm = Constant.PROGRAM.get(id_program);

		if(pm == null) return "";

		if(StringUtil.isEmpty(type))
			type="NM";

		if(type.equals("ACTION"))
			return pm.getNm_action();
		else if(type.equals("SYSTEM"))
			return pm.getCd_system();
		else if(type.equals("WORK"))
			return pm.getCd_work();

		return pm.getNm_program();
	}

	public static boolean isProgram(String id_program_target, String cd_system,String cd_work,String cd_program_group) {

		if(StringUtil.isEmpty(id_program_target))
			return false;

		ProgramManage pm = Constant.PROGRAM.get(id_program_target);

		if(pm == null) return false;

		if(!StringUtil.isEmpty(cd_system) && !cd_system.equals(pm.getCd_system()))
			return false;

		if(!StringUtil.isEmpty(cd_work) && !cd_work.equals(pm.getCd_work()))
			return false;

		if(!StringUtil.isEmpty(cd_program_group) && !cd_program_group.equals(pm.getCd_program_group()))
			return false;

		return true;
	}

	public static String getApproval(String id_approval, String pType) {
        String type = pType;

		ApprovalManage am = Constant.APPROVAL.get(id_approval);

		if(am == null) return "";

		if(StringUtil.isEmpty(type))
			type="NM";

		if(type.equals("ACTION"))
			return am.getNm_action();
		else if(type.equals("GROUP"))
			return am.getCd_approval_group();
		else if(type.equals("WORK"))
			return am.getCd_work();

		return am.getNm_appr();
	}

	public static boolean isApproval(String id_approval_target,String cd_work,String cd_approval_group) {

		if(StringUtil.isEmpty(id_approval_target))
			return false;

		ApprovalManage am = Constant.APPROVAL.get(id_approval_target);

		if(am == null) return false;

		if(!StringUtil.isEmpty(cd_work) && !cd_work.equals(am.getCd_work()))
			return false;

		if(!StringUtil.isEmpty(cd_approval_group) && !cd_approval_group.equals(am.getCd_approval_group()))
			return false;

		return true;
	}

    public static String getWorkerInfo(String id_emp, String type) {
    	WorkerManager workerManager = (WorkerManager)getBean("workerManager");
		return workerManager.getWorkerInfo(id_emp, type);
	}

    public static String getCurrentDate() {
		return DateUtil.getCurrentDate(DateUtil.DATE_PATTERN_DASH);
	}

    public static String getDayOfWeek() {
		return DateUtil.getDayOfWeek(DateUtil.getCurrentDate());
	}

    public static String formatTelNo(String number) {
    	return StringUtil.formatTelNo(number);
    }

    /**
     * 전화번호의 특정 위치의 값을 리턴한다.
     * 사용 예 : ${ufn:getTelNo("0212345678", 3)} -> 리턴 값 : "5678"
     * 인덱스 의미 : 1 - 국번, 2 - 전화번호 중간자리, 3 - 전화번호 마지막자리
     * @param telNo
     * @param idx
     * @return
     */
    public static String getTelNo(String telNo, int idx) {
    	return StringUtil.getTelNo(telNo, idx);
    }

    public static String formatNumber(String number) throws ParseException  {
    	return NumberUtil.formatNumber(number);
	}

    public static String formatDate(String date) {
    	return DateUtil.formatDate(date,DateUtil.DATE_PATTERN_DASH);
    }

    public static String formatDateDot(String date) {
    	if(date== null || date.equals(""))return date;
    	return date.substring(0,4)+"."+date.substring(4,6)+"."+date.substring(6,8);
    }


    public static String formatShortDate(String date) {
    	return DateUtil.formatDate(date,"yy-MM-dd");
    }

	public static String getFormattedTime(String frForm, String toForm, String thisDate) throws Exception {
		return DateUtil.getFormattedTime(frForm, toForm, thisDate);
	}

    public static String formatTime(String time) throws ParseException {
    	return DateUtil.formatTime(time);
    }

    public static boolean isApprAuth(String id_appr_target, String str_apprs) {

    	if(StringUtil.isEmpty(id_appr_target)) return false;

    	String array_appr[] = StringUtil.deleteAny(str_apprs, "[]").split(",");
    	for (String id_appr : array_appr) {
			if(id_appr_target.equals(id_appr.trim()))
			{
				return true;
			}
		}

    	return false;
    }

    /**
	 * 코드명을 반환합니다.
	 * @param cd_goods
	 * @param type
	 * @return
	 */
	public static String getGoodsDetail(String cd_goods, String type)
	{
		GoodsManager goodsManager = (GoodsManager)getBean("goodsManager");
		return goodsManager.getGoodsDetail(cd_goods, type);
	}

	public static List<String> getApplyPath(String path) {
		return StringUtil.getApplyPath(path);
	}


	/**
	 * 코드그룹명을 받아 해당 코드리스트를 select box 안의 option 으로 변환합니다.
	 * @param code_group 코드그룹명
	 * @param defaultText 기본텍스트
	 * @param selectValue 선택 되어진 텍스트 ( 만약 선택 값이 존재한다면)
	 * @param id 사용자 ID
	 * @return
	 */
	public static String makeOptionsEmp(String code_group, String defaultText,String selectValue, String id) {

		CodeManager codeManager = (CodeManager)getBean("codeManager");

		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setCode_group(code_group);

		List<CodeInfo> list = codeManager.listCodeInfo(codeInfo);

		StringBuffer data = new StringBuffer();

		data.append("<option value=''>" + defaultText + "</option>");
		data.append("<option data-divider='true'></option>");

		if(list == null)
			return data.toString();

		for (CodeInfo ci : list) {

			if("N".equals(ci.getYn_use()))
				continue;

			String val = ci.getCode_value();
			if( !StringUtil.isEmpty(ci.getCode_value()) && ci.getCode_value().equals(selectValue)) {
				val += "' selected='selected";
			}
			else if ("DEFAULT".equals(selectValue)) {
				if(!StringUtil.isEmpty(ci.getEtc()) && ci.getEtc().indexOf("DEFAULT") != -1 )
				val += "' selected='selected";
			}

			if(id.equals(ci.getEtc()))
				data.append("<option value='" + val + "'>" + ci.getNm_code() + "</option>");
		}

		return data.toString();

	}

	/**
 	 * 날짜선택 박스 생성
 	 * @param date : jsp에 뿌려줄 날짜선택 메뉴
 	 * @param from : jsp에 세팅해줄 from input의 네임값
 	 * @param to : : jsp에 세팅해줄 to input의 네임값
 	 * @return
 	 */
 	public static String quickYmdFromTo(String date, String from, String to){

 		String[] dataArray = date.split(",");
 		StringBuffer data = new StringBuffer();

 		data.append("<div class=\"dropdown\">\n");
 		data.append("<button id=\"selectDate\" type=\"button\" class=\"btn btn-default\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">날짜<span class=\"caret\"></span></button>\n");
 		data.append("<ul id=\"dateMenu\" class=\"dropdown-menu\" aria-labelledby=\"selectDate\" style=\"min-width:0px;\">\n");

 		//배열의 길이만큼 for문
 		for (int i = 0; i < dataArray.length; i++) {

 			if("T".equals(dataArray[i]))
 				data.append("<li onclick=\"getDate('T', '"+from+"', '"+to+"');\"><a href='#'>오늘</a></li>\n");
 			else if("Y".equals(dataArray[i]))
 				data.append("<li onclick=\"getDate('Y', '"+from+"', '"+to+"');\"><a href='#'>어제</a></li>\n");
 			else if("TM".equals(dataArray[i]))
 				data.append("<li onclick=\"getDate('TM', '"+from+"', '"+to+"');\"><a href='#'>내일</a></li>\n");
 			else if("W".equals(dataArray[i]))
 				data.append("<li onclick=\"getDate('W', '"+from+"', '"+to+"');\"><a href='#'>금주</a></li>\n");
 			else if("LW".equals(dataArray[i]))
 				data.append("<li onclick=\"getDate('LW', '"+from+"', '"+to+"');\"><a href='#'>전주</a></li>\n");
 			else if("M".equals(dataArray[i]))
 				data.append("<li onclick=\"getDate('M', '"+from+"', '"+to+"');\"><a href='#'>당월</a></li>\n");
 			else if("LM".equals(dataArray[i]))
 				data.append("<li onclick=\"getDate('LM', '"+from+"', '"+to+"');\"><a href='#'>전월</a></li>\n");
 		}

 		data.append("</ul>\n");
 		data.append("</div>\n");

 		return data.toString();
 	}

 	public static String quickYmd(String date, String ymd){
 		return quickYmdFromTo(date,ymd,"");
 	}

 	public static String formatMaskHp(String hp) {
    	return StringUtil.formatMaskHp(hp);
	}

 	public static String formatMaskHpType(String type, String hp) {
 		return StringUtil.formatMaskHp(type, hp);
 	}

// 	public static String getAgencyInfo(String id_agency, String type) {
// 		AgencyManager agencyManager = (AgencyManager)getBean("agencyManager");
//		return agencyManager.getAgencyInfo(id_agency, type);
//	}
 	/**
 	 * text 개행문자 치환 후 자르기
 	 * @param content
 	 * @param size
 	 * @return
 	 */
 	public static String getSummaryContent(String content, int size){
 		if(StringUtil.isEmpty(content)) return "";

 		return StringUtil.left(StringUtil.replaceContentLineFeed(content, " "), size);
 	}

 	/**
 	 * 게시판 리스트
 	 * @return
 	 */
	public static List<BoardManage> getBoardList()
	{
		BoardManager boardManager = (BoardManager)getBean("boardManager");
		BoardForm boardForm = new BoardForm();
		List<BoardManage> list = boardManager.listBoardManage(boardForm);
		List<BoardManage> boardList = new ArrayList<BoardManage>();

		if(list == null || list.size() == 0) return boardList;

		for (BoardManage BM : list) {

			if("N".equals(BM.getYn_use())) continue;

			boardList.add(BM);
		}
		return boardList;
	}

	/**
	 * 게시판 정보
	 * @param id_board
	 * @param type
	 * @return
	 */
	public static String getBoardInfo(String id_board, String type) {
		BoardManager boardManager = (BoardManager)getBean("boardManager");

		BoardManage boardManage = new BoardManage();
		boardManage.setId_board(id_board);
		boardManage = boardManager.getBoardManage(boardManage);

		if(boardManage == null) return "";

		if("NM".equals(type)) return boardManage.getNm_board();
		else if("OPTION".equals(type)) return boardManage.getCd50_board_type();
		else return id_board;
	}

	/**
	 * 금융사명코드
	 * @param
	 * @return
	 */
	public static String makeFincorpOptions(String defaultText,String selectValue, String pType, String ynAlliance) {
        String type = pType;
        FincorpManager fincorpManager = (FincorpManager)getBean("fincorpManager");
		StringBuffer data = new StringBuffer();

		FincorpVO fincorpVO = new FincorpVO();
		fincorpVO.setYn_alliance(ynAlliance);
		List<FincorpVO> list = fincorpManager.listCodeFincorp(fincorpVO);

		data.append("<option value=''>" + defaultText + "</option>");
		data.append("<option data-divider='true'></option>");

		boolean isALL = false;
		if(type != null && type.indexOf("ALL_") ==0)
		{
			isALL = true;
			type = type.substring(4);
		}

		for (FincorpVO fvo : list) {
			String val = fvo.getCd_fc();
			String nm = fvo.getNm_fc();
			String str_select = "";

			if(!isALL && "N".equals(fvo.getYn_use()))
				continue;

			if(StringUtil.isEmpty(val)){
				continue;
			}

			if( !StringUtil.isEmpty(val) && val.equals(selectValue)) {
				str_select = "selected='selected'";
			}

			if(type.equals("ID.NM"))
				data.append("<option value='" + val + "' "+str_select+">" + val +"." + nm + "</option>");
			else
				data.append("<option value='" + val + "' "+str_select+">" + nm + "</option>");

		}

		return data.toString();

	}

	public static String makeEdocOptions(String defaultText,String selectValue, String pType) {
        String type = pType;
		EdocManager edocManager = (EdocManager)getBean("edocManager");
		StringBuffer data = new StringBuffer();
		EdocForm edocForm = new EdocForm();
		edocForm.setCd_fc(selectValue);
		List<EdocVO> list = edocManager.listEdoc(edocForm);

		data.append("<option value=''>" + defaultText + "</option>");
		data.append("<option data-divider='true'></option>");

		boolean isALL = false;
		if(type != null && type.indexOf("ALL_") ==0)
		{
			isALL = true;
			type = type.substring(4);
		}

		for (EdocVO fvo : list) {
			String val = fvo.getNo_edoc();
			String nm = fvo.getNo_edoc();
			String str_select = "";

			if(!isALL && "N".equals(fvo.getYn_use()))
				continue;

			if(StringUtil.isEmpty(val)){
				continue;
			}

			if( !StringUtil.isEmpty(val) && val.equals(selectValue)) {
				str_select = "selected='selected'";
			}
			if(type.equals("ID.NM"))
				data.append("<option value='" + val + "' "+str_select+">" + val +"." + nm + "</option>");
			else
				data.append("<option value='" + val + "' "+str_select+">" + nm + "</option>");
		}
		return data.toString();
	}
 	public static String getNmFc(String cd_fc){
 		FincorpManager fincorpManager = (FincorpManager)getBean("fincorpManager");
		return StringUtil.nullToString(fincorpManager.getNmFc(cd_fc));
 	}
 	public static String getNmEdoc(String cd_fc, String no_edoc){
 		EdocManager edocManager = (EdocManager)getBean("edocManager");
 		EdocForm edocForm = new EdocForm();
 		edocForm.setCd_fc(cd_fc);
 		edocForm.setNo_edoc(no_edoc);
 		return StringUtil.nullToString(edocManager.getNmEdoc(edocForm));
 	}
	public static String makeFcCodeOptions(String defaultText,String selectValue, String pType) {
		String type = pType;
		FcCodeManager fcCodeManager = (FcCodeManager)getBean("fcCodeManager");
		StringBuffer data = new StringBuffer();
		FcCodeForm fcCodeForm = new FcCodeForm();
		fcCodeForm.setCd_fc(selectValue);
		List<FcCodeVO> list = fcCodeManager.listFcCode(fcCodeForm);

		data.append("<option value=''>" + defaultText + "</option>");
		data.append("<option data-divider='true'></option>");

		boolean isALL = false;
		if(type != null && type.indexOf("ALL_") ==0)
		{
			isALL = true;
			type = type.substring(4);
		}

		for (FcCodeVO fvo : list) {
			String val = fvo.getCode_group();
			String nm = fvo.getNm_code();
			String str_select = "";

			if(!isALL && "N".equals(fvo.getYn_use()))
				continue;

			if(StringUtil.isEmpty(val)){
				continue;
			}

			if( !StringUtil.isEmpty(val) && val.equals(selectValue)) {
				str_select = "selected='selected'";
			}
			if(type.equals("ID.NM"))
				data.append("<option value='" + val + "' "+str_select+">" + val +"." + nm + "</option>");
			else
				data.append("<option value='" + val + "' "+str_select+">" + nm + "</option>");
		}
		return data.toString();
	}
	public static String makeStdCodeOptions(String defaultText,String code_group, String selectValue, String pType) {
		String type = pType;
		StdCodeManager stdCodeManager = (StdCodeManager)getBean("stdCodeManager");
		StringBuffer data = new StringBuffer();
		StdCodeForm stdCodeForm = new StdCodeForm();
		stdCodeForm.setYn_code_group("N");
		stdCodeForm.setCode_group(code_group);
		List<StdCodeVO> list = stdCodeManager.listStdCode(stdCodeForm);

		data.append("<option value=''>" + defaultText + "</option>");
		data.append("<option data-divider='true'></option>");

		boolean isALL = false;
		if(type != null && type.indexOf("ALL_") ==0)
		{
			isALL = true;
			type = type.substring(4);
		}

		for (StdCodeVO fvo : list) {
			String val = fvo.getCode_value();
			String nm = fvo.getNm_code();
			String str_select = "";

			if(!isALL && "N".equals(fvo.getYn_use()))
				continue;

			if(StringUtil.isEmpty(val)){
				continue;
			}

			if( !StringUtil.isEmpty(val) && val.equals(selectValue)) {
				str_select = "selected='selected'";
			}
			if(type.equals("ID.NM"))
				data.append("<option value='" + val + "' "+str_select+">" + val +"." + nm + "</option>");
			else
				data.append("<option value='" + val + "' "+str_select+">" + nm + "</option>");
		}
		return data.toString();

	}
	/**
	 * 노출 상품 리스트
	 * @param cd_goods
	 * @return
	 */
//	public static String showOpenGoodsAgency(String type, String field){
//		GoodsManager goodsManager = (GoodsManager)getBean("goodsManager");
//		AgencyManager agencyManager = (AgencyManager)getBean("agencyManager");
//
//		GoodsForm goodsForm = new GoodsForm();
//		AgencyForm agencyForm = new AgencyForm();
//
//		if(StringUtil.isEmpty(field))
//			return "<tr><td colspan=\"4\" height=\"100\" align=\"center\">검색결과가 없습니다.</td></tr>";
//
//		List<GoodsVO> goodsVO = goodsManager.listGoods(goodsForm);
//		List<AgencyVO> agencyVO = agencyManager.listAgency(agencyForm);
//
//		if("AGENCY".equals(type)) goodsForm.setId_agency(field);
//		else goodsForm.setCd_goods(field);
//		List<GoodsVO> openGoods = goodsManager.listAgencyGoods(goodsForm);
//
//		HashMap<String,String> hashAgency = new HashMap<String,String>();
//		HashMap<String,String> hashGoods = new HashMap<String,String>();
//		for (GoodsVO og : openGoods) {
//			hashAgency.put(og.getId_agency(), og.getYn_open());
//			hashGoods.put(og.getCd_goods(), og.getYn_open());
//		}
//
//		StringBuffer data = new StringBuffer();
//		int num = 1;
//
//		if("AGENCY".equals(type)){
//			AgencyVO agVO = new AgencyVO();
//			agVO.setId_agency(field);
//			AgencyVO agInfo = agencyManager.getAgencyInfo(agVO);
//
//			for (GoodsVO go : goodsVO) {
//				String strOpen = "";
//				strOpen = hashGoods.get(go.getCd_goods());
//				strOpen = ("".equals(StringUtil.nullToEmpty(strOpen)) || "N".equals(strOpen))? "N" : "Y";
//
//				data.append("<tr><td><input type=\"checkbox\" name=\"cd_goods_list\" id=\"cd_goods_list"+num+"\" value=\""+go.getCd_goods()+"\"/><label for=\"cd_goods_list"+num+"\" class=\"checkbox-inline\"></label></td>");
//
//				data.append("<td>"+agInfo.getNm_agency()+"</td>");
//				data.append("<td>"+go.getCd_goods()+"."+go.getNm_goods()+"</td>");
//				data.append("<td>"+strOpen+"</td>");
//
//				data.append("</td></tr>");
//
//				num++;
//			}
//
//		}else{
//			GoodsInfo gdVO = new GoodsInfo();
//			gdVO.setCd_goods(field);
//			GoodsVO gdInfo = goodsManager.getGoodsInfo(gdVO);
//
//			for (AgencyVO ag : agencyVO) {
//				String strOpen = "";
//				strOpen = hashAgency.get(ag.getId_agency());
//				strOpen = ("".equals(StringUtil.nullToEmpty(strOpen)) || "N".equals(strOpen))? "N" : "Y";
//
//				data.append("<tr><td><input type=\"checkbox\" name=\"id_agency_list\" id=\"id_agency_list"+num+"\" value=\""+ag.getId_agency()+"\"/><label for=\"id_agency_list"+num+"\" class=\"checkbox-inline\"></label></td>");
//
//				data.append("<td>"+gdInfo.getNm_goods()+"</td>");
//				data.append("<td>"+ag.getNm_agency()+"</td>");
//				data.append("<td>"+strOpen+"</td>");
//
//				data.append("</td></tr>");
//
//				num++;
//			}
//		}
//
//		return data.toString();
//	}

}
