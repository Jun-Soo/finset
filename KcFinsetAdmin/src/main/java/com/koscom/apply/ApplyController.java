package com.koscom.apply;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koscom.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.apply.model.ApplyForm;
import com.koscom.apply.model.ApplyResultForm;
import com.koscom.apply.model.ApplyVO;
import com.koscom.apply.service.ApplyManager;
import com.koscom.attach.model.AttachVO;
import com.koscom.attach.service.AttachManager;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.excel.model.ExcelInfo;
import com.koscom.worker.model.WorkerVO;
import com.koscom.worker.service.WorkerManager;

@Controller
@RequestMapping("/apply")
public class ApplyController {

	private static final Logger logger = LoggerFactory.getLogger(ApplyController.class);

	@Autowired
	private ApplyManager applyManager;

	@Autowired
	private WorkerManager workerManager;

	@Autowired
	private AttachManager attachManager;

	@Autowired
	private CodeManager codeManager;

	/**
	 * 접수리스트 메인
	 * @param applyForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listApplyMain.crz")
	public String listApplyMain(HttpServletRequest request, ApplyForm applyForm, Model model) {
		SessionUtil session = new SessionUtil(request);
		// 상담사 권한이 있을때 자신것만 카운터되기위함
		
		WorkerVO workerVO = new WorkerVO();
		HttpSession sessionID = request.getSession();
		workerVO = workerManager.getWorkerInfo(sessionID.getAttribute("id_emp").toString());
		
		model.addAttribute("workerVO", workerVO);
		
		if(workerManager.getCachWorkerApprovalAuth(session.getUserId(),"502001")){
			applyForm.setId_prepare(session.getUserId());
		}

		applyForm.setDoc_fields(codeManager.listCodeInfo("cd_apply_doc_box"));

		List<HashMap<String, String>> listCnt = applyManager.getCntApplyDoc(applyForm);
		HashMap cntApplyDoc = new HashMap();
		for ( HashMap<String,String> tmp : listCnt ) {
			cntApplyDoc.put(tmp.get("CD_APPLY_DOC_BOX"), tmp.get("CNT"));
		}

		model.addAttribute("cntApplyDoc", cntApplyDoc);

		//접수요약정보
		HashMap<String, String> summary = applyManager.getCntApplySummary(applyForm);
		model.addAttribute("summary", summary);
		System.out.println(summary);

		return "/apply/listApplyMain";
	}

	/**
	 * 접수리스트 조회
	 * @param applyForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listApply.crz")
	public String listApply(HttpServletRequest request, ApplyForm applyForm, Model model) {
		SessionUtil session = new SessionUtil(request);

		// 상담사 권한이 있을때 자신것만 보이도록
		if(workerManager.getCachWorkerApprovalAuth(session.getUserId(),"502001")){
			applyForm.setId_prepare(session.getUserId());
		}
		
		// 현재 서류함 2자리로 바뀌고 leftpad 기능 안됨
		// 선택된 서류함이 있으면 2자리로 조회
		/*if(StringUtil.isNotEmpty(applyForm.getCd_apply_doc())) {
			applyForm.setCd_apply_doc(StringUtil.leftPad(applyForm.getCd_apply_doc().toString(), 2, "0"));
		}*/

		Pagination pagedList = (Pagination) applyForm.setPagedList(applyManager.listApplyInfo(applyForm), applyManager.listApplyCount(applyForm));
		model.addAttribute("pagedList", pagedList);

		String nmView = codeManager.getNvlCodeName("_CONF_LIST", "apply", "");
		if("".equals(nmView)) {
			return "/apply/listApply";
		}
		else {
			return "/apply/listApply_prepare" ;
		}
	}

	/**
	 * 접수리스트 엑셀출력
	 * @param request
	 * @param applyForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listApply_excel.crz")
	public String listApply_excel(HttpServletRequest request, ApplyForm applyForm, Model model) {
		SessionUtil session = new SessionUtil(request);
		
		// 상담사 권한이 있을때 자신것만 다운되도록
		if(workerManager.getCachWorkerApprovalAuth(session.getUserId(),"502001")){
			applyForm.setId_prepare(session.getUserId());
		}

		String code_group = "cd_apply_excel";

		List<CodeInfo> excel_field =  codeManager.listCodeInfo(code_group);
		applyForm.setFields(excel_field);

		ExcelInfo excelInfo = new ExcelInfo();
		excelInfo.setList(applyManager.listApplyInfo_excel(applyForm));
		excelInfo.setCode_group(code_group);
		excelInfo.setSheetName("접수리스트");
		model.addAttribute("FileName", "접수리스트");
		model.addAttribute("ExcelInfo", excelInfo);

		return "excelView";
	}

	/**
	 * 사전접수번호로 접수목록 조회
	 * @param applyForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listApplyByPrepare.crz")
	public String listApplyByPrepare(ApplyForm applyForm, Model model) {

		model.addAttribute("listApply", applyManager.listApplyByPrepare(applyForm.getNo_prepare()));
		model.addAttribute("no_apply", applyForm.getNo_apply());

		return "/apply/listApplySummary";
	}

	/**
	 * 접수신청서 폼
	 * @param applyForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/formApplyMain.crz")
	public String formApplyMain(ApplyForm applyForm, Model model) {
		List<String> arrNoApply = applyManager.getNoApply(applyForm.getNo_prepare());

		for(String no_apply : arrNoApply){
			List<AttachVO> arrAttachVO = attachManager.listAttach(no_apply, AttachVO.CD_ATTACH_01);

			if(arrAttachVO.size() >= 1){
				int cnt = arrAttachVO.size() - 1;
				model.addAttribute("attach", arrAttachVO.get(cnt));
			}
		}

		model.addAttribute("applyForm", applyForm);

		return "/apply/formApplyMain";
	}

	/**
	 * 접수신청서 생성
	 * @param request
	 * @param applyVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/createApply.json")
	public String createApply(HttpServletRequest request, ApplyVO applyVO, Model model) throws IOException, FinsetException,FinsetMessageException {
		SessionUtil session = new SessionUtil(request);
		applyVO.setId_frt(session.getUserId());

		ReturnClass returnClass = null;
		returnClass = applyManager.createApply(applyVO);
		model.addAttribute("returnData", returnClass);
		model.addAttribute("yn_close", "Y");

		return "/apply/formApplyMain";
	}

	/**
	 * 접수신청서 전송
	 * @param request
	 * @param applyVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/sendApply.json")
	public String sendApply(HttpServletRequest request, ApplyVO applyVO, Model model)throws IOException, FinsetException,FinsetMessageException {
		SessionUtil session = new SessionUtil(request);
		applyVO.setId_lst(session.getUserId());
		applyVO.setId_apply(session.getUserId());

		ReturnClass returnClass = applyManager.sendApply(applyVO);

		/* Test Code
		String returnMsg = "<!--include virtual=\"admin/agent/status/navi.asp\"--><script>alert('진행중이지 않은 상품입니다.');window.close();</script>";
		String returnMsg = "<meta http-equiv=\"Refresh\" content=\"0;URL=http://localhost:8301/apply/receiveNeo.crz?app_id=201507230000199&status=00&app_prod=3&emsg=&cons_ag_name=%C0%FC%C1%F6%C7%F6&cons_ag_id_num=2-325-0150\">";
		ReturnClass returnClass = new ReturnClass(Constant.SUCCESS,returnMsg);
		*/

		// 사전접수 창이 닫히면 안되므로 close 함수가 있는경우 replace 로 제거
		returnClass.setDes_message(StringUtil.replaceAll(returnClass.getDes_message(), "window.close\\(\\)", ""));

		if(Constant.FAILED.equals(returnClass.getCd_result())){
			model.addAttribute("message", "alert('"+returnClass.getDes_message()+"')");
			return "/comm/message";
		}else{
			model.addAttribute("message", returnClass.getDes_message());
			return "/comm/plainText";
		}
	}

	/**
	 * 접수신청 팝업
	 * @param request
	 * @param applyVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/requestApply.crz")
	public String requestApply(HttpServletRequest request, ApplyVO applyVO, Model model) {
		model.addAttribute("no_apply", applyVO.getNo_apply());
		return "/apply/popRequestApply";
	}

	/**
	 * 심사처리 폼
	 * @param applyVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/formModifyApply.crz")
	public String formModifyApply(ApplyVO applyVO, Model model) {

		model.addAttribute("List", applyManager.getApply(applyVO));
		return "/apply/formModifyApply";
	}

	/**
	 * 심사처리 실행
	 * @param request
	 * @param applyVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifyApplyJudge.json")
	public String modifyApplyJudge(HttpServletRequest request, ApplyVO applyVO, Model model) throws IOException, ParseException, FinsetException {
		ReturnClass returnClass = new ReturnClass();
		SessionUtil session = new SessionUtil(request);
		if(workerManager.getCachWorkerApprovalAuth(session.getUserId(),"102001")){
			applyVO.setId_lst(session.getUserId());

			returnClass = applyManager.modifyApplyDoc(applyVO);
		}else{
			returnClass = new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		model.addAttribute("returnData", returnClass);
		model.addAttribute("applyVO", applyVO);

		return "jsonView";
	}

	/**
	 * 상세 화면 / 접수메모, 파일, 경로, 적법 순서로 보임
	 * @param request
	 * @param applyVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/popApplyDetail.crz")
	public String popApplyDetail(HttpServletRequest request, ApplyVO applyVO, Model model) {

		// 첨부파일 리스트
		model.addAttribute("listAttach", attachManager.listAttach(applyVO.getNo_apply(), ""));
		// 경로
		applyVO = applyManager.getApplyInfo(applyVO.getNo_apply());
		applyVO.setMemo_to_apply(StringUtil.replaceContentLineFeed(applyVO.getMemo_to_apply(), "\n"));
		model.addAttribute("applyVO", applyVO);

		return "/apply/popApplyDetail";
	}

	/**
	 * 접수 시 메모 수정
	 * @param applyVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifyApplyMemo.json")
	public String modifyApplyMemo(ApplyVO applyVO, Model model) {

		applyVO.setMemo_to_apply(StringUtil.replaceContentExt(applyVO.getMemo_to_apply(), "<br>"));

		ReturnClass returnData = applyManager.modifyApplyMemo(applyVO);
		model.addAttribute("returnData", returnData);

		return "jsonView";
	}

	/**
	 * 적법수집 수정
	 * @param applyVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/modifyApplyLegal.json")
	public String modifyApplyLegal(ApplyVO applyVO, Model model) {

		ReturnClass returnData = applyManager.modifyApplyLegal(applyVO);
		model.addAttribute("returnData", returnData);

		return "jsonView";
	}

	/**
	 * 접수 삭제
	 * @param applyVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/delApplyInfo.json")
	public String delApplyInfo(ApplyVO applyVO, Model model) throws IOException, ParseException{

		ReturnClass returnData = applyManager.delApplyInfo(applyVO);
		model.addAttribute("returnData", returnData);

		return "jsonView";
	}

	/**
	 * 접수경로 조회(팝업)
	 * @param applyVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/popApplyPath.crz")
	public String popApplyPath(ApplyVO applyVO, Model model) {

		applyVO = applyManager.getApply(applyVO);
		model.addAttribute("applyVO", applyVO);

		return "/apply/formApplyPath";
	}
	
	
	/**
	 * 조회결과
	 * @param applyResultForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listApplyResultMain.crz")
	public String listApplyResultMain(HttpServletRequest request, ApplyResultForm applyResultForm, Model model) {
		model.addAttribute("applyResultForm", applyResultForm);
		return "/apply/listApplyResultMain";
	}
	
	/**
	 * 조회결과
	 * @param applyResultForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listApplyResultInfo.crz")
	public String listApplyResult(HttpServletRequest request, ApplyResultForm applyResultForm, Model model) {
		Pagination pagedList = (Pagination) applyResultForm.setPagedList(applyManager.ListApplyResult(applyResultForm), applyManager.ListApplyResultCount(applyResultForm));
		model.addAttribute("pagedList", pagedList);
		
		return "/apply/listApplyResultInfo";
	}

}
