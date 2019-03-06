package com.koscom.apply.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import com.koscom.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.apply.model.ApplyForm;
import com.koscom.apply.model.ApplyResultForm;
import com.koscom.apply.model.ApplyResultVO;
import com.koscom.apply.model.ApplyVO;
import com.koscom.apply.service.ApplyManager;
import com.koscom.apply.service.ApplyWebManager;
import com.koscom.goods.service.GoodsManager;
import com.koscom.prepare.model.PrepareForm;
import com.koscom.util.ReturnClass;
import com.koscom.util.URLConnection;

@Service("applyWebManager")
public class ApplyWebManagerImpl implements ApplyWebManager{

	private static final Logger logger = LoggerFactory.getLogger(ApplyWebManagerImpl.class);

	@Autowired
	private ApplyManager applyManager;
	
	@Autowired
	private GoodsManager goodsManager;
	
	@Override
	public ReturnClass modifyApplyDoc(ApplyVO applyVO) throws ParseException, FinsetException, IOException{
		return applyManager.modifyApplyDoc(applyVO);
	}
	
	@Override
	public ReturnClass procAutoApply(String no_apply, HashMap<String,HashMap<String,String>> listAutoApply) throws IOException, FinsetException, FinsetMessageException{
		return applyManager.procAutoApply(no_apply, listAutoApply);
	}
	
	@Override
	public ReturnClass createApply(ApplyVO applyVO)  throws IOException, FinsetException, FinsetMessageException {
		applyVO.setId_frt("SYSTEM");
		return applyManager.createApply(applyVO);
	}
	
	@Override
	public ReturnClass createApplyAgency(ApplyVO applyVO) throws FinsetException {
		return applyManager.createApplyAgency(applyVO);
	}
	
	@Override
	public List<ApplyVO> listApplyInfoAgency(ApplyForm applyForm) {
		return applyManager.listApplyInfoAgency(applyForm);
	}

	@Override
	public int listApplyCountAgency(ApplyForm applyForm) {
		return applyManager.listApplyCountAgency(applyForm);
	}
	
	@Override
	public List<ApplyVO> listApplyByPrepareAgency(String no_prepare) {
		return applyManager.listApplyByPrepareAgency(no_prepare);
	}
	
	@Override
	public ApplyVO getApplyInfo(String no_apply) {
		return applyManager.getApplyInfo(no_apply);
	}

	@Override
	public List<String> getNoApply(String no_prepare) {
		return applyManager.getNoApply(no_prepare);
	}

	@Override
	public ReturnClass modifyApplyMemo(ApplyVO applyVO) {
		return applyManager.modifyApplyMemo(applyVO);
	}

	@Override
	public ReturnClass delApplyInfo(ApplyVO applyVO) throws ParseException, IOException {
		return applyManager.delApplyInfo(applyVO);
	}

	@Override
	public ReturnClass modifyApplyLegal(ApplyVO applyVO) {
		return applyManager.modifyApplyLegal(applyVO);
	}

	@Override
	public ReturnClass updateApplyChk(ApplyVO applyVO) throws Exception {
		return applyManager.updateApplyChk(applyVO);
	}
	
	@Override
	public List<HashMap<String, String>> getCntMonthApply(ApplyForm applyForm){
		return applyManager.getCntMonthApply(applyForm);
	}
	
	@Override
	public List<HashMap<String, String>> getCntDayApply(ApplyForm applyForm){
		return applyManager.getCntDayApply(applyForm);
	}
	
	@Override
	public HashMap<String, String> getCntSummaryApplyByAgency(ApplyForm applyForm){
		return applyManager.getCntSummaryApplyByAgency(applyForm);
	}

	@Override
	public ReturnClass sendReqPOST(String targetUrl, String param) throws IOException{
		URLConnection urlCon = new URLConnection();
		return urlCon.sendReqPOST(targetUrl, param);
	}
	
	@Override
	public int getCntApplyTodayByInfo(ApplyVO applyVO){
		return applyManager.getCntApplyTodayByInfo(applyVO);
	}

	@Override
	public ApplyVO getApply(ApplyVO applyVO){
		return applyManager.getApply(applyVO);
	}

	@Override
	public List<HashMap<String, String>> getCntApplyDoc(ApplyForm applyForm) {
		return applyManager.getCntApplyDoc(applyForm);
	}

	@Override
	public HashMap<String, String> getCntApplySummary(ApplyForm applyForm) {
		return applyManager.getCntApplySummary(applyForm);
	}

	@Override
	public List<ApplyVO> listApplyInfo(ApplyForm applyForm) {
		return applyManager.listApplyInfo(applyForm);
	}

	@Override
	public HashMap<String, String> listApplyCount(ApplyForm applyForm) {
		return applyManager.listApplyCount(applyForm);
	}

	@Override
	public List<ApplyVO> listApplyByPrepare(String no_prepare) {
		return applyManager.listApplyByPrepare(no_prepare);
	}

	@Override
	public ReturnClass sendApply(ApplyVO applyVO) throws  IOException, FinsetException, FinsetMessageException{
		return applyManager.sendApply(applyVO);
	}

	@Override
	public ReturnClass createApplyForFinset(ApplyVO applyVO) {
		return applyManager.createApplyForFinset(applyVO);
	}

	@Override
	public List<ApplyVO> listStatusInfo(ApplyForm applyForm) {
		return applyManager.listStatusInfo(applyForm);
	}

	@Override
	public HashMap<String, String> listStatusCount(ApplyForm applyForm) {
		return applyManager.listStatusCount(applyForm);
	}

	@Override
	public List<ApplyVO> listPastInfo(ApplyForm applyForm) {
		return applyManager.listPastInfo(applyForm);
	}

	@Override
	public List<ApplyVO> listPastInfoPg(ApplyForm applyForm) {
		return applyManager.listPastInfoPg(applyForm);
	}
	
	@Override
	public HashMap<String, String> listPastCount(ApplyForm applyForm) {
		return applyManager.listPastCount(applyForm);
	}
	
	@Override
	public HashMap<String, String> listPastPgCount(ApplyForm applyForm) {
		return applyManager.listPastPgCount(applyForm);
	}

	@Override
	public List<ApplyVO> listStatusInfoPg(ApplyForm applyForm) {
		return applyManager.listStatusInfoPg(applyForm);
	}

	@Override
	public void agencyReturn(PrepareForm prepareForm) throws ParseException, IOException {
		applyManager.agencyReturn(prepareForm);
	}

	@Override
	public List<HashMap<String, Object>> listApplyInfo_excel(ApplyForm applyForm) {
		// TODO Auto-generated method stub
		return applyManager.listApplyInfo_excel(applyForm);
	}

	@Override
	public List<ApplyVO> listApplyDestory(int destroyTerm) {
		// TODO Auto-generated method stub
		return applyManager.listApplyDestory(destroyTerm);
	}

	@Override
	public void sendCntApplyDoc(String id_prepare, String cd_apply_doc_box) {
		// TODO Auto-generated method stub
		applyManager.sendCntApplyDoc(id_prepare, cd_apply_doc_box);
	}

	@Override
	public ReturnClass procApplyPath(ApplyVO applyVO) {
		// TODO Auto-generated method stub
		return applyManager.procApplyPath(applyVO);
	}

	@Override
	public List<ApplyVO> listLookupInfoPg(ApplyForm applyForm) {
		// TODO Auto-generated method stub
		return applyManager.listLookupInfoPg(applyForm);
	}
	
	/**
	 * Name   : getLoanProgSts
	 * Desc   : 최신 1건 대출 건 조회
	 * input  : ApplyForm
	 * output : ApplyVO
	 * Date   : 2017.09.26
	 */
	@Override
	public ApplyVO getLoanProgSts(ApplyForm applyForm) {
		return applyManager.getLoanProgSts(applyForm);
	}
	
	/**
	 * Name   : listLoanProgSts
	 * Desc   : 대출진행현황 조회
	 * input  : ApplyForm
	 * output : ApplyVO
	 * Date   : 2017.09.26
	 */
	@Override
	public List<ApplyVO> listLoanProgSts(ApplyForm applyForm) {
		return applyManager.listLoanProgSts(applyForm);
	}
	
	/**
	 * Name   : listPastLoanHistory
	 * Desc   : 과거 진행 내역 조회
	 * input  : ApplyForm
	 * output : ApplyVO
	 * Date   : 2017.09.26
	 */
	@Override
	public List<ApplyVO> listPastLoanHistory(ApplyForm applyForm) {
		return applyManager.listPastLoanHistory(applyForm);
	}
	
	/**
	 * Name   : listPastLoanHistoryCount
	 * Desc   : 과거 진행 내역 건수
	 * input  : ApplyForm
	 * output : int
	 * Date   : 2017.09.26
	 */
	@Override
	public int listPastLoanHistoryCount(ApplyForm applyForm) {
		return applyManager.listPastLoanHistoryCount(applyForm);
	}
	
	/**
	 * 상품조회 이력 조회
	 * @param ApplyResultForm
	 * @return List<ApplyResultVO>
	 */
	@Override
	public List<ApplyResultVO> ListApplyResult(ApplyResultForm applyResultForm) {
		return applyManager.ListApplyResult(applyResultForm);
	}
	
	/**
	 * 상품조회 이력 건수
	 * @param ApplyResultForm
	 * @return int
	 */
	@Override
	public int ListApplyResultCount(ApplyResultForm applyResultForm) {
		return applyManager.ListApplyResultCount(applyResultForm);
	}
}
