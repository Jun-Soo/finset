package com.koscom.apply.service.impl;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import com.koscom.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.apply.dao.ApplyMapper;
import com.koscom.apply.model.ApplyCzReturn;
import com.koscom.apply.model.ApplyForm;
import com.koscom.apply.model.ApplyVO;
import com.koscom.apply.service.ApplyManager;
import com.koscom.counsel.model.CounselVO;
import com.koscom.counsel.service.CounselManager;
import com.koscom.env.model.CodeForm;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.model.CodeVO;
import com.koscom.env.service.CodeManager;
import com.koscom.goods.service.GoodsManager;
import com.koscom.person.service.PersonManager;
import com.koscom.prepare.model.PrepareForm;
import com.koscom.prepare.model.PrepareReturn;
import com.koscom.prepare.model.PrepareVO;
import com.koscom.prepare.service.PrepareManager;

@Service("applyManager")
public class ApplyManagerImpl implements ApplyManager {

	private static final Logger logger = LoggerFactory.getLogger(ApplyManagerImpl.class);
	
	@Autowired
	private ApplyMapper applyMapper;
	
	@Autowired
	private PrepareManager prepareManager;
	
	@Autowired
	private PersonManager personManager;
	
	@Autowired
	private CodeManager codeManager;
	
	@Autowired
	private GoodsManager goodsManager;
	
	@Autowired
	private CounselManager counselManager;



	private static final String _CONF_SYSTEM           = "_CONF_SYSTEM"          ;
	private static final String _INFO_COMP             = "_INFO_COMP"            ;
	private static final String NM_COMP                = "NM_COMP"               ;
	private static final String YN_ADD_APPLYPATH       = "YN_ADD_APPLYPATH"      ;
	private static final String NO_LOAN_COMP           = "NO_LOAN_COMP"          ;
	private static final String NO_AGENCY_COMP         = "NO_AGENCY_COMP"        ;
	private static final String PH_COMP                = "PH_COMP"               ;
	private static final String Y                      = "Y"                     ;
	
	private String getParamStr(Object object) throws UnsupportedEncodingException  {
		return getParamStr(object, "01");
	}
	
	/**
	 * 전송 폼 클래스를 입력받아 URI 파라메터를 생성
	 * @param object
	 * @param cd_array 배열 처리방식
	 * 		  01 : 같은 필드명으로 여러번 호출
	 * 		  02 : 필드명 뒤에 [] 붙임
	 * @return
	 */
	private String getParamStr(Object object, String cd_array) throws UnsupportedEncodingException {
		
		StringBuffer params = new StringBuffer();
		StringBuffer plantxt = new StringBuffer();
		
		BeanWrapper requestWrapper = new BeanWrapperImpl(object);
		PropertyDescriptor[] descriptors=BeanUtils.getPropertyDescriptors(object.getClass());
		int i = 1;
		String flag = "";
		String str_array = "";
		
		if("02".equals(cd_array)) str_array = "[]=";
		else str_array = "=";
		
		for (PropertyDescriptor propertyDescriptor : descriptors) {
			
			String name = propertyDescriptor.getName();
			// 클래스 이름은 보내지 않도록 함.
			if("class".equals(name)) continue;
			
			// 첫번째 이후에 구분자 세팅
			if(i != 1) flag = "&";
			
//			try {
				
			// 전송 변수가 배열인지 판단
			if(requestWrapper.getPropertyType(propertyDescriptor.getName()).isArray()) {
				// getter 를 이용하여 배열값 추출
				String[] array = (String[]) requestWrapper.getPropertyValue(propertyDescriptor.getName());
				// 배열이 비어있는 경우 빈값 세팅
				if(array == null)
				{
					params.append( flag + name +"=");
					plantxt.append( flag + name +"=");
				}
				// 이름은 같고 배열값 만큼 세팅
				else {
					for (String value : array) {
						params.append( flag + name + str_array + URLEncoder.encode( StringUtil.nullToString(value,""), "EUC-KR" ) );
						plantxt.append( flag + name + str_array + StringUtil.nullToString(value,"") );
					}
				}
			}
			// 전송 변수가 문자열일때
			else {
				String value = (String) requestWrapper.getPropertyValue(propertyDescriptor.getName());

				params.append( flag + name +"="+ URLEncoder.encode( StringUtil.nullToString(value,""), "EUC-KR" ) );
				plantxt.append( flag + name +"="+ StringUtil.nullToString(value,"") );
			}
				
//			} catch (Exception e) {
//				logger.debug(name + " : getPropertyValue Failed");
//			}
			i++;
		}
		
		logger.debug("ParamsString=> "+plantxt.toString());
		return params.toString();
	}
	
	@Override
	public ApplyVO getApply(ApplyVO applyVO){
		return applyMapper.getApply(applyVO);
	}
	
	@Override
	public ReturnClass modifyApplyDoc(ApplyVO applyVO) throws ParseException, FinsetException, IOException {

		ApplyVO applyBefore = applyMapper.getApply(applyVO);
		applyVO.setNo_person(applyBefore.getNo_person());
		applyVO.setNo_prepare(applyBefore.getNo_prepare());
		applyVO.setCd_apply_doc_box_before(applyBefore.getCd_apply_doc_box());
		
		if( StringUtil.isEmpty(applyVO.getNo_apply()) ) {
		    throw new FinsetException("신청번호가 입력되지 않았습니다.");
        }
//			return new ReturnClass(Constant.FAILED, "신청번호가 입력되지 않았습니다.");
		
		if(1 != applyMapper.modifyApplyDoc(applyVO))  {
            throw new FinsetException("처리에 실패하였습니다.");
        }

		StringBuffer sb = new StringBuffer();
		sb.append("[금융사] : " +  codeManager.getCodeName("cd_fc", applyBefore.getCd_fc()) + "<br>");
		
		if (StringUtil.isNotEmpty(applyVO.getCd_goods())) 
			sb.append("[상품명] : " +  goodsManager.getGoodsDetail(applyVO.getCd_goods(), "NM"));
		else
			sb.append("[상품명] : " +  goodsManager.getGoodsDetail(applyBefore.getCd_goods(), "NM"));
		
		if(StringUtil.isNotEmpty(applyVO.getCd_apply_doc_box())) 
			sb.append(" [상태] : " + codeManager.getCodeName("cd_apply_doc_box", applyVO.getCd_apply_doc_box()));
		
		if(StringUtil.isNotEmpty(applyVO.getYmd_approval())) 
			sb.append( " [처리일] : " + DateUtil.formatDate(applyVO.getYmd_approval()));
		
		if(StringUtil.isNotEmpty(applyVO.getAmt_approval())) 
			 sb.append(" [승인금액] : " + NumberUtil.formatNumber(applyVO.getAmt_approval()));
		
		if(StringUtil.isNotEmpty(applyVO.getMemo_apply())) 
			sb.append("<br> [메모] : " + StringUtil.replaceContentExt(applyVO.getMemo_apply(),"<br>"));
		
		
		CounselVO counselVO = new CounselVO();
		counselVO.setNo_person(applyVO.getNo_person());
		counselVO.setNo_prepare(applyVO.getNo_prepare());
		counselVO.setNo_apply(applyVO.getNo_apply());
		counselVO.setCd_counsel_class(CounselVO.CD_COUNSEL_CLASS_20); 				// 상담구분 20 : 금융사메모
		counselVO.setEtc_counsel(sb.toString());
		counselVO.setId_frt(applyVO.getId_lst());
		
		// 상담메모에 금융사메모 저장
		ReturnClass returnClass = counselManager.procCounselInfo(counselVO);
		if(Constant.FAILED.equals(returnClass.getCd_result())){
            throw new FinsetException("상담메모 저장에 실패하였습니다");
        }

            String memo = "";
		
		// 기표변경 메모정보를 apply_info 에 저장
		if(StringUtil.isNotEmpty(applyVO.getMemo_apply())) {
			memo = StringUtil.replaceContentExt(applyVO.getMemo_apply(),"<br>");	
		}
			
		// 메모길이가 너무 길면 apply_info DB에 들어가지 않음 (DB는 한글이 3바이트)
		if(memo.length() > 3990) {
			memo = memo.substring(0, 3990) + "...";
		}
		
		applyBefore.setMemo_from_apply(memo);
		ReturnClass returnMemo = modifyApplyMemo(applyBefore);
		if(Constant.FAILED.equals(returnMemo.getCd_result())) {
            throw new FinsetException("접수정보에 메모저장을 실패하였습니다.");
        }

		PrepareVO prepareVO = prepareManager.getPrepare(applyBefore.getNo_prepare());
		
		// cd_apply_doc_box 가 01 이 아니면 사전접수는 접수상태여야 한다.
		if (!ApplyVO.CD_APPLY_DOC_BOX_01.equals(applyVO.getCd_apply_doc_box()))
		{
			// 사전접수가 접수상태여부 확인 
			if(!PrepareVO.CD_PREPARE_DOC_BOX_50.equals(prepareVO.getCd_prepare_doc_box()))
			{
				prepareVO.setCd_prepare_doc_box("50");
			}
		}
		
		if("50".equals(prepareVO.getCd_prepare_doc_box())){
			//접수리스트 가져오기
			List<ApplyVO> listApply = applyMapper.listApplyByPrepare(applyBefore.getNo_prepare());
			HashMap<String, Integer> cntMap = new HashMap<String, Integer>();
			
			int cnt = 0;
			String cd_apply_class = "";
			
			for(ApplyVO infoApply : listApply){
				if(cntMap.get(infoApply.getCd_apply_doc_box()) == null)
					cnt = 1;
				else
					cnt = cntMap.get(infoApply.getCd_apply_doc_box()) + 1;
				
				cd_apply_class = infoApply.getCd_apply_doc_box();
				cntMap.put(infoApply.getCd_apply_doc_box(), cnt);
			}
			
			/**
			 * apply 통합기표
			 * 접수 상세 변경
			 * 상태별 우선순위 : 보류 > 심사 > 승인 > 가승인 > 결재 > 접수 > 거절
			 * 상태별 우선순위 (변경) : 승인 > 가승인 > 결재 > 심사 > 보류 > 접수 > 거절
			 */
			if(cntMap.size()>1){
				if(cntMap.get(ApplyVO.CD_APPLY_DOC_BOX_50) != null)
					prepareVO.setCd_prepare_class(ApplyVO.CD_APPLY_DOC_BOX_50);
				else if(cntMap.get(ApplyVO.CD_APPLY_DOC_BOX_40) != null)
					prepareVO.setCd_prepare_class(ApplyVO.CD_APPLY_DOC_BOX_40);
				else if(cntMap.get(ApplyVO.CD_APPLY_DOC_BOX_30) != null)
					prepareVO.setCd_prepare_class(ApplyVO.CD_APPLY_DOC_BOX_30);
				else if(cntMap.get(ApplyVO.CD_APPLY_DOC_BOX_20) != null)
					prepareVO.setCd_prepare_class(ApplyVO.CD_APPLY_DOC_BOX_20);
				else if(cntMap.get(ApplyVO.CD_APPLY_DOC_BOX_70) != null)
					prepareVO.setCd_prepare_class(ApplyVO.CD_APPLY_DOC_BOX_70);
				else if(cntMap.get(ApplyVO.CD_APPLY_DOC_BOX_10) != null)
					prepareVO.setCd_prepare_class(ApplyVO.CD_APPLY_DOC_BOX_10);
				else
					prepareVO.setCd_prepare_class(ApplyVO.CD_APPLY_DOC_BOX_60);
			}else{
				if(ApplyVO.CD_APPLY_DOC_BOX_01.equals(cd_apply_class))
					prepareVO.setCd_prepare_class(ApplyVO.CD_APPLY_DOC_BOX_10);
				else
					prepareVO.setCd_prepare_class(cd_apply_class);
			}
			
			prepareManager.modifyPrepareDoc(prepareVO);
		}else{
			
			//심사처리 기표
			PrepareForm prepareForm = new PrepareForm();
			prepareForm.setNo_prepare(applyVO.getNo_prepare());
			agencyReturn(prepareForm);
		}
		
		//컨소상품의 경우 해당 선심사 금융사로 리턴함
		applyVO.setCd_goods(applyBefore.getCd_goods());
		consorCompReturn(applyVO);
		
		// 사전접수정보 업데이트(applyBefore에 no_prepare 존재)
		return prepareManager.updatePrepareCnt(applyBefore.getNo_prepare());
	}
	
	@Override
	public void agencyReturn(PrepareForm prepareForm)  throws UnsupportedEncodingException, ParseException,IOException {
		logger.info("== 매체사 리턴 START ==");
		logger.info("1. 사전접수 정보 조회 : " + prepareForm.getNo_prepare());
		
		//사전접수조회
		PrepareVO prepareVO = prepareManager.getPrepare(prepareForm.getNo_prepare());
		String targetUrl = "";
		
		//리턴URL, 매체사 키값이 없을 경우 진행하지 않음
		if(StringUtil.isEmpty(targetUrl) || StringUtil.isEmpty(prepareVO.getNo_agency())){
			logger.error("2-2. 리턴URL, 매체사 키값이 없음");
			return;
		}
		
		logger.info("3. 접수리스트 조회");
		//접수리스트 가져오기
		List<ApplyVO> listApply = applyMapper.listApplyByPrepare(prepareForm.getNo_prepare());
		
		String status = ""; //결과상태
		String etc_counsel = ""; //제휴사통보
		int approval = 0, semi_approval = 0, reject = 0, etc = 0;	//승인,가승인,거절,심사(대기,접수,심사,보류) 카운트
		int approval_cnt = 0;		//승인금액 합
		String last_ymd_approval ="";//마지막 승인날짜
		
		StringBuffer memo = new StringBuffer();
		
		//상담메모 기표일때
		if(StringUtil.isNotEmpty(prepareForm.getEtc_counsel())){
			etc_counsel = prepareForm.getEtc_counsel();
		}
		
		/**
		 * 사전접수 상태가 접수가 아닐 경우 => 서류함 기표
		 * 접수일 경우 => 금융사 통합기표 
		 */
		logger.info("4. 기표 확인");
		if(!PrepareVO.CD_PREPARE_DOC_BOX_50.equals(prepareVO.getCd_prepare_doc_box())){
			logger.info("4-1. 금융사 접수 전");
			//분배대기
			if(PrepareVO.CD_PREPARE_DOC_BOX_10.equals(prepareVO.getCd_prepare_doc_box())){
				status = ApplyVO.CD_APPLY_DOC_BOX_10; //정상접수
			}
			//상담
			if(PrepareVO.CD_PREPARE_DOC_BOX_20.equals(prepareVO.getCd_prepare_doc_box())){ 
				
				if(StringUtil.isNotEmpty(prepareVO.getCd_prepare_class())){
					//사접접수상세 리턴에 등록되어있는 상태일 경우 해당 코드로 리턴
					HashMap<String, CodeVO> codeMap = codeManager.getCodeMapInfo("cd_prepare_class_return");
					
					if(codeMap.containsKey(prepareVO.getCd_prepare_class())){
						CodeVO codeInfo = codeMap.get(prepareVO.getCd_prepare_class());
						status = codeInfo.getNm_code();
					}
					
					memo.append(codeManager.getCodeName("cd_prepare_class", prepareVO.getCd_prepare_class()));
				}
				if(StringUtil.isEmpty(status)) status = ApplyVO.CD_APPLY_DOC_BOX_10; //접수
				
			}
			//접수불가
			if(PrepareVO.CD_PREPARE_DOC_BOX_60.equals(prepareVO.getCd_prepare_doc_box())){ 
				status = ApplyVO.CD_APPLY_DOC_BOX_60; //부결
				if(StringUtil.isNotEmpty(prepareVO.getCd_reject_cause()))
					memo.append(codeManager.getCodeName("cd_reject_cause", prepareVO.getCd_reject_cause()));
			}
			//휴지통, 삭제
			if(PrepareVO.CD_PREPARE_DOC_BOX_90.equals(prepareVO.getCd_prepare_doc_box()) ||
					PrepareVO.CD_PREPARE_DOC_BOX_99.equals(prepareVO.getCd_prepare_doc_box())){
				status = ApplyVO.CD_APPLY_DOC_BOX_99; //휴지통
			}
			
			memo.append(" " + etc_counsel);
			
		}else{
			logger.info("4-2. 금융사 접수 후");
			memo.append(etc_counsel + "\n");
			
			// 사전접수 상태가 접수일 경우 상세 상태로 기표 내림
			if(StringUtil.isNotEmpty(prepareVO.getCd_prepare_class()))
				status = prepareVO.getCd_prepare_class();
			
			for(ApplyVO list : listApply){
				
				//접수가 하나일때
				if(listApply.size() == 1){
					//status = list.getCd_apply_doc_box();
					if(ApplyVO.CD_APPLY_DOC_BOX_50.equals(status) && StringUtil.isNotEmpty(list.getYmd_approval()))
						last_ymd_approval = list.getYmd_approval();
					
				}else{
					if(ApplyVO.CD_APPLY_DOC_BOX_01.equals(list.getCd_apply_doc_box()) || ApplyVO.CD_APPLY_DOC_BOX_10.equals(list.getCd_apply_doc_box()) ||
							ApplyVO.CD_APPLY_DOC_BOX_20.equals(list.getCd_apply_doc_box()) || ApplyVO.CD_APPLY_DOC_BOX_70.equals(list.getCd_apply_doc_box())) //대기,접수,심사,보류
						etc++;
					else if(ApplyVO.CD_APPLY_DOC_BOX_40.equals(list.getCd_apply_doc_box())) //가승인 
						semi_approval++;
					else if(ApplyVO.CD_APPLY_DOC_BOX_50.equals(list.getCd_apply_doc_box())){//승인
						approval++;
						//승인날짜 비교
						if(StringUtil.isNotEmpty(list.getYmd_approval()))
							if(0 >= last_ymd_approval.compareTo(list.getYmd_approval())) last_ymd_approval = list.getYmd_approval();
					}
					else if(ApplyVO.CD_APPLY_DOC_BOX_60.equals(list.getCd_apply_doc_box())) reject++; //거절
					
					/**
					 * 통합기표 사용 여부
					 * N => 선심사 금융사의 기표 그대로 내림
					 */
					String cd_first_comp = codeManager.getCodeName("_CONF_AGENCY_RETURN", "CD_FIRST_COMP");
					if("N".equals(codeManager.getCodeName("_CONF_AGENCY_RETURN", "YN_TOTAL_RETURN")) && StringUtil.isNotEmpty(cd_first_comp)){
						if(cd_first_comp.equals(list.getCd_fc())){
							status = list.getCd_apply_doc_box();
							if(StringUtil.isNotEmpty(list.getYmd_approval()))
								last_ymd_approval = list.getYmd_approval();
						}
					}
				}
				
				memo.append("[금융사]:" +  codeManager.getCodeName("cd_fc", list.getCd_fc()));
				if(StringUtil.isNotEmpty(list.getCd_apply_doc_box()))
					memo.append(" [상태]:" + codeManager.getCodeName("cd_apply_doc_box", list.getCd_apply_doc_box()));
				if(StringUtil.isNotEmpty(list.getAmt_approval())) 
					memo.append(" [승인금액]:" + NumberUtil.formatNumber(list.getAmt_approval()));
				approval_cnt += NumberUtil.stringToInt(list.getAmt_approval());
				if(StringUtil.isNotEmpty(list.getMemo_from_apply())) 
					memo.append(" [메모]:" + StringUtil.replaceContentExt(list.getMemo_from_apply(),"<br>"));
				memo.append("\n");
			}
			
		}
		
		if(StringUtil.isEmpty(status)){
			logger.error("4-2-1. 상태값 없음");
			return;
		}
		
		PrepareReturn prepareReturn = new PrepareReturn();
		prepareReturn.setSeq(prepareVO.getNo_agency()); //매체사 신청서 고유번호
		prepareReturn.setYmd(DateUtil.getCurrentDate()); //처리일자
		prepareReturn.setHis(DateUtil.getCurrentDate(DateUtil.TIME_HMS_PATTERN)); //처리시간
		prepareReturn.setMemo(memo.toString()); //메모
		prepareReturn.setStatus(status); //등록처리 결과상태
		if(ApplyVO.CD_APPLY_DOC_BOX_50.equals(status)){
			prepareReturn.setYmd_approval(last_ymd_approval); //승인일자
			prepareReturn.setAmt(String.valueOf(approval_cnt)); //승인금액
		}
		
		String param = getParamStr(prepareReturn);
		
		// URL 요청
		logger.info(" ======= 리턴 START ======= ");
		URLConnection urlCon = new URLConnection();
		urlCon.sendReqPOST(targetUrl, param);
		logger.info(" ======= 리턴 END ======= ");
	}
	
//	@Override
//	public List<HashMap<String, String>> getCntApplyDoc(ApplyForm applyForm) {
//		return applyMapper.getCntApplyDoc(applyForm);
//	}

	@Override
	public ReturnClass modifyApplyMemo(ApplyVO applyVO) {
		
		if (StringUtil.isEmpty(applyVO.getNo_apply())) {
			return new ReturnClass(Constant.FAILED, "접수번호가 입력되지 않았습니다.");
		}
		
		if (1 != applyMapper.modifyApplyMemo(applyVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public ReturnClass delApplyInfo(ApplyVO applyVO)  throws ParseException, IOException{
		
		if (StringUtil.isEmpty(applyVO.getNo_apply())) {
			return new ReturnClass(Constant.FAILED, "접수번호가 입력되지 않았습니다.");
		}
		
		//접수 정보조회
		ApplyVO applyBefore = applyMapper.getApply(applyVO);
		PrepareVO prepareVO =prepareManager.getPrepare(applyBefore.getNo_prepare());
		
		if (1 != applyMapper.delApplyInfo(applyVO)) {
			return new ReturnClass(Constant.FAILED, "삭제 처리에 실패하였습니다.");
		}
		
		//카운트 업데이트
		prepareManager.updatePrepareCnt(applyVO.getNo_prepare());
		
		//매체사 리턴
		PrepareForm prepareForm = new PrepareForm();
		prepareForm.setNo_prepare(applyVO.getNo_prepare());
		agencyReturn(prepareForm);
		
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	
	private void consorCompReturn(ApplyVO applyVO) throws UnsupportedEncodingException {
		URLConnection url = new URLConnection();
		String param = "";

		String id_pre_goods = codeManager.getNvlCodeName("_MAP_CONSOR_GOODS", applyVO.getCd_goods(), ""); // 선심사 상품
		if(StringUtil.isEmpty(id_pre_goods))
			return;
		
		CodeInfo codeInfo = codeManager.getCodeInfo("_MAP_CONSOR_INFO", id_pre_goods);
		if( codeInfo == null )
		{
			logger.warn("컨소 리턴 정보 없음");
			return;
		}
		
		if("applyCzReturn".equals(codeInfo.getNm_code())){
			CodeForm codeForm = new CodeForm();
			codeForm.setCode_group("_MAP_CONSOR_CZ_ID");
			codeForm.setNm_code(applyVO.getCd_goods());
			List<CodeVO> listCode = codeManager.listCode(codeForm);
			
			CodeVO infoCode = listCode.get(0); 
			
			if(infoCode == null) return;
			
			ApplyCzReturn applyCzReturn = new ApplyCzReturn();
			applyCzReturn.setMode("modifyApprovalDetail");
			applyCzReturn.setId_coop(infoCode.getCode_value());
			applyCzReturn.setNo_coop(applyVO.getNo_prepare());
			applyCzReturn.setStatus(applyVO.getCd_apply_doc_box());
			if(ApplyVO.CD_APPLY_DOC_BOX_50.equals(applyVO.getCd_apply_doc_box())){
				if(StringUtil.isNotEmpty(applyVO.getYmd_approval()))
					applyCzReturn.setYmd_approval(applyVO.getYmd_approval());
				if(StringUtil.isNotEmpty(applyVO.getAmt_approval()))
					applyCzReturn.setAmt_approval(applyVO.getAmt_approval());
			}
			param = getParamStr(applyCzReturn);
		
		}else if("applyCzMemoReturn".equals(codeInfo.getNm_code())){
 			CodeForm codeForm = new CodeForm();
 			codeForm.setCode_group("_MAP_CONSOR_CZ_ID");
 			codeForm.setNm_code(applyVO.getCd_goods());
 			List<CodeVO> listCode = codeManager.listCode(codeForm);
 			CodeVO infoCode = listCode.get(0); 
 			
 			if(infoCode == null) return;
 			
 			ApplyCzReturn applyCzReturn = new ApplyCzReturn();
 			applyCzReturn.setMode("modifyApprovalDetail");
 			applyCzReturn.setId_coop(infoCode.getCode_value());
 			applyCzReturn.setNo_coop(applyVO.getNo_prepare());
 			applyCzReturn.setStatus(applyVO.getCd_apply_doc_box());
 			if(StringUtil.isNotEmpty(applyVO.getMemo_apply()))
 				applyCzReturn.setMemo(applyVO.getMemo_apply());
 			if(ApplyVO.CD_APPLY_DOC_BOX_50.equals(applyVO.getCd_apply_doc_box())){
 				if(StringUtil.isNotEmpty(applyVO.getYmd_approval()))
 					applyCzReturn.setYmd_approval(applyVO.getYmd_approval());
 				if(StringUtil.isNotEmpty(applyVO.getAmt_approval()))
 					applyCzReturn.setAmt_approval(applyVO.getAmt_approval());
 			}
 			param = getParamStr(applyCzReturn);
 			
		}else{
			logger.warn("지정된 리턴폼이 없음");
			return;
		}
		
		url.sendReqGET(codeInfo.getEtc(), param);
	}

	@Override
	public ReturnClass createApplyForFinset(ApplyVO applyVO) {
		
		String no_apply = "";
		
		// 사전접수 신청정보 세팅
		PrepareVO prepareVO = prepareManager.getPrepare(applyVO.getNo_prepare());
		applyVO.setCd_fc(applyVO.getCd_fc());
		//applyVO.setCd_apply_comp(applyVO.getCd_apply_comp());
		applyVO.setNo_person(prepareVO.getNo_person());
		applyVO.setCd_apply_doc_box(ApplyVO.CD_APPLY_DOC_BOX_01);
		applyVO.setCd_advertisement(prepareVO.getCd_advertisement());
		applyVO.setAmt_apply(applyVO.getAmt_apply());
		//적법수집 정보 세팅
		if(StringUtil.isNotEmpty(prepareVO.getCd_collect_path())){
			applyVO.setCd_collect_path(prepareVO.getCd_collect_path());
		}
		if(StringUtil.isNotEmpty(prepareVO.getCd_contact_path())){
			applyVO.setCd_contact_path(prepareVO.getCd_contact_path());
		}
		if(StringUtil.isNotEmpty(prepareVO.getCd_collect_method())){
			applyVO.setCd_collect_method(prepareVO.getCd_collect_method());
		}
		if(StringUtil.isNotEmpty(prepareVO.getEtc_memo())){
			applyVO.setEtc_memo(prepareVO.getEtc_memo());
		}
		if(StringUtil.isNotEmpty(prepareVO.getNm_agency())){
			applyVO.setNm_agency(prepareVO.getNm_agency());
		}
		if(StringUtil.isNotEmpty(prepareVO.getNm_ceo_agency())){
			applyVO.setNm_ceo_agency(prepareVO.getNm_ceo_agency());
		}
		if(StringUtil.isNotEmpty(prepareVO.getUrl_homepage_agency())){
			applyVO.setUrl_homepage_agency(prepareVO.getUrl_homepage_agency());
		}
		if(StringUtil.isNotEmpty(prepareVO.getNm_writer())){
			applyVO.setNm_writer(prepareVO.getNm_writer());
		}else{
			if(StringUtil.isNotEmpty(prepareVO.getId_prepare()))
				//applyVO.setNm_writer(workerManager.getWorkerInfo(prepareVO.getId_prepare(),"NM"));
				applyVO.setNm_writer("SYS");
		} 
		
		// 접수경로 추가
		String etc_path = prepareVO.getEtc_prepare_path();
		
		if(Y.equals(codeManager.getCodeName(_CONF_SYSTEM, YN_ADD_APPLYPATH)) && codeManager.getCodeName(_INFO_COMP, NM_COMP) != NM_COMP){
			if(StringUtil.isNotEmpty(etc_path))
			{
				etc_path += "@@"+(etc_path.split("@@").length+1)+"||";
			} else {
				etc_path = "1||"; 
			}
			
			etc_path += DateUtil.getCurrentDate()+"||";
			etc_path += codeManager.getCodeName(_INFO_COMP, NM_COMP)+"||";
			etc_path += codeManager.getCodeName(_INFO_COMP, NO_LOAN_COMP)+"||";
			etc_path += codeManager.getCodeName(_INFO_COMP, NO_AGENCY_COMP)+"||";
			etc_path += codeManager.getCodeName(_INFO_COMP, PH_COMP);
		}
		
		applyVO.setEtc_apply_path(etc_path);
		
//			GoodsVO goodsVO = goodsManager.getGoodsInfo(applyVO.getCd_goods());
//			applyVO.setCd_apply_comp(goodsVO.getCd_apply_comp());
			
			// 신청서 정보 insert
		applyMapper.insertApply(applyVO);
		no_apply = applyVO.getNo_apply();
			
			// 첨부파일 등록  ===> 제외(코스콤은 필요없음)
			
			// 서류함 건수 node 전달
//			sendCntApplyDoc(applyVO.getId_prepare(), applyVO.getCd_apply_doc_box());
			
//			if(!StringUtil.nullToString(applyVO.getId_prepare(), "").equals(prepareVO.getId_prepare()))
//			{
//				sendCntApplyDoc(prepareVO.getId_prepare(), applyVO.getCd_apply_doc_box());
//			}
			
//			//자동접수 시작(상품 자동접수 여부, 코드관리 자동접수 제외 제휴사인지 체크)
//			String yn_auto = goodsVO.getYn_auto();
//			if(StringUtil.isEmpty(yn_auto))
//				yn_auto = "N";
//			
//			if(Y.equals(yn_auto) && !Y.equals(codeManager.getNvlCodeName("agency_auto_blacklist", prepareVO.getId_agency(), "N"))){
//				logger.info("※ 자동접수 ※");
//				logger.info("매체사: " + prepareVO.getId_agency() + ", 상품코드: " + goodsVO.getCd_goods());
//				sendApply(applyVO);
//			}
//			
			
		// 사전접수정보 업데이트(applyBefore에 no_prepare 존재)
		prepareManager.updatePrepareCnt(prepareVO.getNo_prepare());
		
		logger.info("신청서 생성완료 = "+ no_apply);

		return new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.", (Object) no_apply);
	}
	
	@Override
	public List<ApplyVO> listLoanProgSts(ApplyForm applyForm) {
		return applyMapper.listLoanProgSts(applyForm);
	}
	
	@Override
	public List<ApplyVO> listApplyByPrepare(String no_prepare) {
		return applyMapper.listApplyByPrepare(no_prepare);
	}
	
	@Override
	public List<ApplyVO> listPastLoanHistory(ApplyForm applyForm) {
		return applyMapper.listPastLoanHistory(applyForm);
	}
	
	@Override
	public int listPastLoanHistoryCount(ApplyForm applyForm) {
		return applyMapper.listPastLoanHistoryCount(applyForm);
	}
}
