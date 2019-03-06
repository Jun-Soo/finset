package com.koscom.prepare.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.koscom.finset.model.FinsetVO;
import com.koscom.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.apply.model.ApplyVO;
import com.koscom.apply.service.ApplyManager;
import com.koscom.env.model.ApprovalManageVO;
import com.koscom.env.model.CodeForm;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.model.CodeVO;
import com.koscom.env.service.CodeManager;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.prepare.dao.PrepareMapper;
import com.koscom.prepare.model.PrepareForm;
import com.koscom.prepare.model.PrepareVO;
import com.koscom.prepare.service.PrepareManager;
import com.koscom.worker.service.WorkerManager;

@Service("prepareManager")
public class PrepareManagerImpl implements PrepareManager {

	private static final Logger logger = LoggerFactory.getLogger(PrepareManagerImpl.class);

	@Autowired
	private PrepareMapper prepareMapper;

	@Autowired
	private CodeManager codeManager;

	@Autowired
	private WorkerManager workerManager;

	@Autowired
	private PersonManager personManager;

	@Autowired
	private ApplyManager applyManager;

	@Override
	public List<PrepareVO> listPrepareInfo(PrepareForm prepareForm) {
		return prepareMapper.listPrepareInfo(prepareForm);
	}

	@Override
	public int listPrepareCount(PrepareForm prepareForm) {
		return prepareMapper.listPrepareCount(prepareForm);
	}

	@Override
	public List<PrepareVO> listPrepareInfoAgency(PrepareForm prepareForm) {
		return prepareMapper.listPrepareInfoAgency(prepareForm);
	}

	@Override
	public int listPrepareCountAgency(PrepareForm prepareForm) {
		return prepareMapper.listPrepareCountAgency(prepareForm);
	}

	@Override
	public PrepareVO getPrepare(String no_prepare) {
		return prepareMapper.getPrepare(no_prepare);
	}

	@Override
	public ReturnClass createPrepareSummary(PrepareVO prepareVO) {
		if( StringUtil.isEmpty(prepareVO.getNo_person()) ){
			return new ReturnClass(Constant.FAILED, "고객번호가 입력되지 않았습니다.");
		}
		prepareVO.setCd_prepare_doc_box(PrepareVO.CD_PREPARE_DOC_BOX_10);
		prepareMapper.createPrepare(prepareVO);

		logger.info("신규 사전접수 신청서 생성 ["+prepareVO.getNo_prepare()+"]");
		ReturnClass returnClass = new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.", (Object) prepareVO.getNo_prepare());
		returnClass.setReturnObj(prepareVO.getNo_prepare());
		return returnClass;
	}
	@Override
	public ReturnClass deletePrepare(FinsetVO finsetVO) {
		logger.info("신규 사전접수 신청서 삭제 getNo_bunch=["+finsetVO.getNo_bunch()+"]");
		prepareMapper.deletePrepare(finsetVO);
		ReturnClass returnClass = new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.", finsetVO.getNo_bunch());
		returnClass.setReturnObj(finsetVO);
		return returnClass;
	}

	@Override
	public ReturnClass createPrepareAgency(PrepareVO prepareVO) {

		if( StringUtil.isEmpty(prepareVO.getNo_agency_person()) )
		{
			return new ReturnClass(Constant.FAILED, "고객번호가 입력되지 않았습니다.");
		}

		// 서류함 정보가 없으면 가접수
		if(StringUtil.isEmpty(prepareVO.getCd_prepare_doc_box()))
			prepareVO.setCd_prepare_doc_box(PrepareVO.CD_PREPARE_DOC_BOX_01);

		// 적법 최소수집경로 정보가 없으면 기본정보 세팅(상품분류가 존재하지않으면 기본 00코드 세팅)
		if(StringUtil.isEmpty(prepareVO.getCd_collect_path()))
		{
			String etc_legal = "";

			if(StringUtil.isEmpty(prepareVO.getCd_goods_type())) {
				etc_legal = codeManager.getNvlCodeName("cd_legal_default", "00", "");
			}else{
				etc_legal = codeManager.getNvlCodeName("cd_legal_default", prepareVO.getCd_goods_type(), "");
			}

			// 등록된 기본정보가 있을때만 세팅
			if( StringUtil.isNotEmpty(etc_legal) ) {

				String arrLegal[] = etc_legal.split("\\|\\|");

				prepareVO.setCd_collect_path(arrLegal[0].trim());
				prepareVO.setCd_contact_path(arrLegal[1].trim());
				prepareVO.setCd_collect_method(arrLegal[2].trim());
				prepareVO.setEtc_memo(arrLegal[3].trim());
				prepareVO.setNm_agency(arrLegal[4].trim());
				prepareVO.setNm_ceo_agency(arrLegal[5].trim());
				prepareVO.setUrl_homepage_agency(arrLegal[6].trim());
				prepareVO.setNm_writer(arrLegal[7].trim());
			}
		}

		// insert
		prepareMapper.createPrepareAgency(prepareVO);

		logger.info("신규 사전접수 신청서 생성(SA) ["+prepareVO.getNo_prepare()+"]");

		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.", (Object) prepareVO.getNo_prepare());

	}

	private void sendCntPrepareDoc(String id_prepare, String cd_prepare_doc_box) {

//		PrepareVO prepareVO = new PrepareVO();
//		prepareVO.setId_prepare(id_prepare);
//		prepareVO.setCd_prepare_doc_box(cd_prepare_doc_box);
//
//		HashMap<String, String> cnt = prepareMapper.getCntPrepare(prepareVO);
//		prepareVO.setCnt_prepare_doc(String.valueOf(cnt.get("CNT")));
//
//		sendToNodeThread(prepareVO);
	}

//	static int cntThread = 0; // 알람 리턴 쓰레드 진행
//	static int cntThreadTotal = 0; // 알람 리턴 쓰레드 총

//	private void sendToNodeThread(final PrepareVO prepareVO) {
//		Thread thread = new Thread() {
//			public void run() {
//				try {
//					++cntThreadTotal;
//					++cntThread;
//					sendToNode(prepareVO);
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					--cntThread;
//					logger.info("★ 알람 전송 쓰레드수 : 총/진행중 : " + cntThreadTotal + " / " + cntThread);
//				}
//			}
//
//		};
//
//		thread.start();
//	}

//	private void sendToNode(PrepareVO prepareVO) {
//		URLConnection url = new URLConnection();
//		String targetUrl = codeManager.getNvlCodeName("_CONF_ALARM", "URL_NODE","");
//
//		if( StringUtil.isEmpty(targetUrl) )
//		{
//			logger.warn("★ 알람 전송 URL 없음");
//			return;
//		}
//
//		targetUrl = targetUrl+"/sendToNode";
//		String param = "comp_id="+codeManager.getNvlCodeName("_CONF_SYSTEM", "ID_COMP","")+"&user_id="+StringUtil.nullToString(prepareVO.getId_prepare(),"")
//				+"&type=CNT_PREPARE&doc="+prepareVO.getCd_prepare_doc_box()+"&cnt="+prepareVO.getCnt_prepare_doc();
//
//		url.sendReqGET(targetUrl, param);
//	}

	/**
	 * 서류함 건수 node 전달
	 * @param prepareVO
	 * @param getPrepare
	 */
	public void setNodePrepareDoc(PrepareVO prepareVO, PrepareVO getPrepare){

		sendCntPrepareDoc(prepareVO.getId_prepare(), prepareVO.getCd_prepare_doc_box());
		sendCntPrepareDoc(prepareVO.getId_prepare(), prepareVO.getCd_prepare_doc_box_before());

		if(!StringUtil.nullToString(prepareVO.getId_prepare(), "").equals(getPrepare.getId_prepare()))
		{
			sendCntPrepareDoc(getPrepare.getId_prepare(), prepareVO.getCd_prepare_doc_box());
			sendCntPrepareDoc(getPrepare.getId_prepare(), prepareVO.getCd_prepare_doc_box_before());
		}

		//id_prepare가 둘다 비어있지않을 경우
		if(StringUtil.isNotEmpty(prepareVO.getId_prepare()) && StringUtil.isNotEmpty(getPrepare.getId_prepare())){
			sendCntPrepareDoc("", prepareVO.getCd_prepare_doc_box());
			sendCntPrepareDoc("", prepareVO.getCd_prepare_doc_box_before());
		}
	}

	/**
	 * 담당자 분배
	 * @param prepareVO
	 */
	public void getPrepareDiv(PrepareVO prepareVO){

//		//기존고객 여부 체크
//		PersonForm personForm = new PersonForm();
//		if(prepareVO.getNm_person() == null && prepareVO.getHp() == null ){
//			if(StringUtil.isNotEmpty(prepareVO.getNo_person()))
//				personForm.setNo_person(prepareVO.getNo_person());
//		}else{
//			personForm.setNm_person(prepareVO.getNm_person());
//			personForm.setHp(prepareVO.getHp());
//		}
//
//		int cntExistPerson = personManager.listExistPersonCount(personForm);
//		if(cntExistPerson >= 1) prepareVO.setYn_exist_person("Y");
//		else prepareVO.setYn_exist_person("N");
//
//		//새로운 고객이거나 기존고객 분배설정이 분배안함이 아닐경우
//		if("N".equals(prepareVO.getYn_exist_person())
//				|| !PrepareVO.CD_EXIST_DIV_03.equals(codeManager.getNvlCodeName("_CONF_PREPARE_DIV", "CD_EXIST_DIV", PrepareVO.CD_EXIST_DIV_01))){
//
//			// 담당자가 비어있는지 && 작성자가 담당자로 설정할지 && 작성자가 상담사 권한인지 확인
//			if( StringUtil.isEmpty(prepareVO.getId_prepare())
//					&& "Y".equals(codeManager.getNvlCodeName("_CONF_PREPARE_DIV", "YN_ID_PREPARE", "N"))
//					&& workerManager.getCachWorkerApprovalAuth(prepareVO.getId_frt(), "502001") ) {
//
//				prepareVO.setId_prepare(prepareVO.getId_frt());
//			}
//
//			// 담당자가 비어있는지 && 분배사용 확인
//			if( StringUtil.isEmpty(prepareVO.getId_prepare())
//					&& "Y".equals(codeManager.getNvlCodeName("_CONF_PREPARE_DIV", "YN_AUTO_PREPARE_DIV", "N"))  ){
//				prepareVO.setId_prepare(getAutoPrepareDiv());
//			}
//
//			//기존고객인지 && 기존고객분배가 기존상담사인지
//			if("Y".equals(prepareVO.getYn_exist_person())
//					&& "Y".equals(codeManager.getNvlCodeName("_CONF_PREPARE_DIV", "YN_AUTO_PREPARE_DIV", "N"))
//					&& PrepareVO.CD_EXIST_DIV_02.equals(codeManager.getNvlCodeName("_CONF_PREPARE_DIV", "CD_EXIST_DIV", PrepareVO.CD_EXIST_DIV_01))  ){
//				prepareVO.setId_prepare(personManager.getIdPrepare(personForm));
//			}
//
//			//담당자가 상담사권한이 없을경우 빈값 셋팅.
//			if(!workerManager.getCachWorkerApprovalAuth(prepareVO.getId_prepare(),"502001")){
//				prepareVO.setId_prepare("");
//			}
//		}//else prepareVO.setId_prepare(""); //기존고객 분배안함일 경우

	}

	/**
	 * Method Desc : 자동분배
	 */
	private synchronized String getAutoPrepareDiv() {
		String id_prepare = "";

		// 등록된 심사자목록
		List<ApprovalManageVO> listIdPrepare = workerManager.listCacheApprovalAuth("502001");
		// 심사분배 제외자
		String id_not_in[] = codeManager.getNvlCodeName("_CONF_PREPARE_DIV", "DIV_NOT_IN", "").split(",");

		// 순차분배
		logger.info("[순차분배]시작==>");
		String id_last = "";

		CodeInfo codeInfo = new CodeInfo();
		CodeForm codeform = new CodeForm();
		codeform.setCode_group("_CONF_PREPARE_DIV");
		codeform.setCode_value("ID_LAST");

		// 현재 마지막 심사자 조회
		codeInfo = codeManager.getCode(codeform);
		if(codeInfo != null && !StringUtil.isEmpty(codeInfo.getNm_code())) {
			id_last = codeInfo.getNm_code();
		}

		// 등록된 심사자 순차정렬
		List<String> list = new ArrayList<String>();
		for (ApprovalManageVO appr : listIdPrepare) {
			list.add(appr.getId_emp());
		}
		Collections.sort(list);

		int cnt = 1;
		String id_first = "";
		for (String id : list) {
			// 분배제외자에 등록된 경우 제외
			boolean flg_not_in = false;
			for (String id_not : id_not_in) {
				if(id_not.equals(id)) flg_not_in = true;
			}
			if(flg_not_in){
				// 마지막까지 분배가 안되면 첫번째 심사자 세팅
				if(cnt == list.size() && StringUtil.isEmpty(id_prepare)) id_prepare = id_first;

				cnt++;
				continue;
			}

			if(StringUtil.isEmpty(id_first)) id_first = id;

			// 마지막심사자가 없으면 대상자 설정후 루프종료
			if(StringUtil.isEmpty(id_last)) {
				id_prepare = id;
				break;
			}

			// 마지막심사자 다음 사번으로 설정후 루프종료
			if(id_last.compareTo(id) < 0){
				id_prepare = id;
				break;
			}

			// 마지막까지 분배가 안되면 첫번째 심사자 세팅
			if(cnt == list.size() && StringUtil.isEmpty(id_prepare)) id_prepare = id_first;

			cnt++;
		}

		CodeVO codeVO = new CodeVO();
		codeVO.setCode_group(codeInfo.getCode_group());
		codeVO.setCode_value(codeInfo.getCode_value());
		codeVO.setNm_code(id_prepare);
		codeVO.setEtc(codeInfo.getEtc());
		codeVO.setSeq_order(codeInfo.getSeq_order());
		codeVO.setYn_system_code(codeInfo.getYn_system_code());
		codeVO.setYn_use(codeInfo.getYn_use());
		codeVO.setId_lst("SYSTEM");
		codeManager.procCodeInfo(codeVO);



		logger.info("[분배완료]상담사==>"+id_prepare);
		return id_prepare;
	}

	/**
	 * 매체사 접수(SA) 접수확인
	 */
	@Override
	public ReturnClass updatePrepareChk(PrepareVO prepareVO)  throws ParseException, FinsetException, FinsetMessageException, IOException {

		//고객정보 조회하여 고객insert or update
		PersonVO personVO = personManager.getPersonInfoAgency(prepareVO.getNo_person());
		ReturnClass returnClass = personManager.procPersonAgency(personVO);
		prepareVO.setNo_person((String) returnClass.getReturnObj());

		//사전접수 고객번호 업데이트
		prepareMapper.updatePrepareChk(prepareVO);

		//담당자 분배
		getPrepareDiv(prepareVO);

		// 담당자저장 및 서류함 이동
		if(StringUtil.isNotEmpty(prepareVO.getId_prepare())) {
			modifyPrepareId(prepareVO);
		} else {
			// 서류함 건수 node 전송 (담당자가 있는경우 담당자 저장 후 전송함)
			prepareVO.setCd_prepare_doc_box("10");//담당자 없는경우 분배대기
			modifyPrepareDoc(prepareVO);
		}

		//접수 고객번호 업데이트
		List<ApplyVO> list = applyManager.listApplyByPrepareAgency(prepareVO.getNo_prepare());
		PrepareVO afterPrepare = prepareMapper.getPrepare(prepareVO.getNo_prepare());

		for(ApplyVO applyVO : list){
			applyVO.setNo_person((String) returnClass.getReturnObj());
			applyVO.setId_prepare(afterPrepare.getId_prepare());
			applyVO.setCd_apply_doc_box("01");
			applyVO.setId_lst(prepareVO.getId_lst());

			applyManager.updateApplyChk(applyVO);
		}

		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.", (Object) prepareVO.getNo_prepare());
	}

	@Override
	public ReturnClass updateOverlapChk(PrepareVO prepareVO) {

		if( StringUtil.isEmpty(prepareVO.getNo_person()) )
		{
			return new ReturnClass(Constant.FAILED, "고객번호가 입력되지 않았습니다.");
		}

		if(1 != prepareMapper.updateOverlapChk(prepareVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");
	}

	@Override
	public ReturnClass modifyPrepare(PrepareVO prepareVO) throws ParseException, IOException{

		if( StringUtil.isEmpty(prepareVO.getNo_prepare()) )
		{
			return new ReturnClass(Constant.FAILED, "신청서 번호가 입력되지 않았습니다.");
		}

		if(1 != prepareMapper.modifyPrepare(prepareVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		PrepareVO getPrepare = prepareMapper.getPrepare(prepareVO.getNo_prepare());
		prepareVO.setCd_prepare_doc_box_before(getPrepare.getCd_prepare_doc_box());

		//서류함 변경
		if( StringUtil.isNotEmpty(prepareVO.getCd_prepare_doc_box())){
			ReturnClass returnClass = modifyPrepareDoc(prepareVO);
			if(!Constant.SUCCESS.equals(returnClass.getCd_result()))
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		//담당자 변경
		if(workerManager.getCachWorkerApprovalAuth(prepareVO.getId_lst(),"102002")){
			/**
			 * 상담사가 변경되었을 시 사전접수는 상담-대기상태로 이동한다.
			 * 접수상태일 경우 제외 (-상담사변경되어도 서류함 이동하지않도록 수정)
			 */

			if(1 != prepareMapper.modifyPrepareId(prepareVO)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}

			// 접수상태일때 apply 서류함 건수 전달
			if(PrepareVO.CD_PREPARE_DOC_BOX_50.equals(prepareVO.getCd_prepare_doc_box())){

				List<String> applyList = applyManager.getNoApply(prepareVO.getNo_prepare());

				if( 0 < applyList.size()){
					ApplyVO applyVO = new ApplyVO();

					for(String no_apply: applyList){

						applyVO.setNo_apply(no_apply);
						ApplyVO applyBefore = applyManager.getApply(applyVO);

						applyManager.sendCntApplyDoc(prepareVO.getId_prepare(), applyBefore.getCd_apply_doc_box());

						if(!StringUtil.nullToString(prepareVO.getId_prepare(), "").equals(getPrepare.getId_prepare()))
						{
							applyManager.sendCntApplyDoc(getPrepare.getId_prepare(), applyBefore.getCd_apply_doc_box());
						}
					}
				}
			}
		}

		// 서류함 건수 node 전달
		setNodePrepareDoc(prepareVO, getPrepare);

		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");
	}

	@Override
	public ReturnClass modifyPrepareAgency(PrepareVO prepareVO) {

		if( StringUtil.isEmpty(prepareVO.getNo_prepare()) )
		{
			return new ReturnClass(Constant.FAILED, "신청서 번호가 입력되지 않았습니다.");
		}

		if(1 != prepareMapper.modifyPrepareAgency(prepareVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");
	}

	@Override
	public List<PrepareVO> listPrepareByPerson(PrepareForm prepareForm) {
		return prepareMapper.listPrepareByPerson(prepareForm);
	}

	@Override
	public ReturnClass modifyPrepareDoc(PrepareVO prepareVo)  throws ParseException, IOException {
		String[] arrNoPrepare = prepareVo.getNo_prepare().split(",");
		int nCnt = 0;

		for ( String no_prepare : arrNoPrepare ) {
			prepareVo.setNo_prepare(no_prepare);
			PrepareVO getPrepare = prepareMapper.getPrepare(no_prepare);
			prepareVo.setCd_prepare_doc_box_before(getPrepare.getCd_prepare_doc_box());

			if(!PrepareVO.CD_PREPARE_DOC_BOX_50.equals(prepareVo.getCd_prepare_doc_box())
					&& PrepareVO.CD_PREPARE_DOC_BOX_50.equals(getPrepare.getCd_prepare_doc_box())){
				//거절상태
				if(!"60".equals(getPrepare.getCd_prepare_class()))
					return new ReturnClass(Constant.FAILED, "접수상태에서는 서류함이동이 불가합니다.");

				if(!PrepareVO.CD_PREPARE_DOC_BOX_99.equals(prepareVo.getCd_prepare_doc_box()))
					return new ReturnClass(Constant.FAILED, "삭제로만 이동이 가능합니다.");
			}

			/*
			 * 변경할 서류함이 존재하고
			 * 접수 상태가 아닐 경우에 서류함 변경 가능
			 */
			if(StringUtil.isNotEmpty(prepareVo.getCd_prepare_doc_box()) )
			//		&& !PrepareVO.CD_PREPARE_DOC_BOX_50.equals(getPrepare.getCd_prepare_doc_box()) )
			{
				nCnt += prepareMapper.modifyPrepareDoc(prepareVo);

				// 매체사 기표 전송
				PrepareForm prepareForm = new PrepareForm();
				prepareForm.setNo_prepare(no_prepare);
				prepareForm.setCd_prepare_doc_box(prepareVo.getCd_prepare_doc_box());

				//상담, 접수일때 상세구분 함께 셋팅
				if(PrepareVO.CD_PREPARE_DOC_BOX_20.equals(prepareVo.getCd_prepare_doc_box())
						|| PrepareVO.CD_PREPARE_DOC_BOX_50.equals(prepareVo.getCd_prepare_doc_box()))
					prepareForm.setCd_prepare_class(prepareVo.getCd_prepare_class());
				//접수불가일때 불가사유 함께 셋팅
				else if(PrepareVO.CD_PREPARE_DOC_BOX_60.equals(prepareVo.getCd_prepare_doc_box()))
					prepareForm.setCd_reject_cause(prepareVo.getCd_reject_cause());

				// 일괄수정 아닐경우 서류함 건수 node 전달
				if (StringUtil.isEmpty(prepareVo.getYn_batch())) {
					setNodePrepareDoc(prepareVo, getPrepare);
				}

				applyManager.agencyReturn(prepareForm);

				if(PrepareVO.CD_PREPARE_DOC_BOX_99.equals(prepareVo.getCd_prepare_doc_box())
						&& !PrepareVO.CD_PREPARE_DOC_BOX_50.equals(getPrepare.getCd_prepare_doc_box())){
					//사전접수 삭제시 접수리스트 조회후 대기상태라면 삭제
					List<ApplyVO> applyVO = applyManager.listApplyByPrepare(no_prepare);

					for(ApplyVO list : applyVO){
						if(ApplyVO.CD_APPLY_DOC_BOX_01.equals(list.getCd_apply_doc_box()))
							applyManager.delApplyInfo(list);
					}
				}

			}
		}

		return new ReturnClass(Constant.SUCCESS, nCnt+"건 처리 되었습니다.");
	}

	@Override
	public ReturnClass modifyPrepareId(PrepareVO prepareVo) {
		String[] arrNoPrepare = new String[0];
		int nCnt = 0;
		if (prepareVo !=null) {
			arrNoPrepare = prepareVo.getNo_prepare().split(",");
			nCnt = 0;

			for ( String no_prepare : arrNoPrepare ) {
                prepareVo.setNo_prepare(no_prepare);

                /**
                 * 상담사가 변경되었을 시 사전접수는 상담-대기상태로 이동한다.
                 * 접수상태일 경우 제외 (-상담사변경되어도 서류함 이동하지않도록 수정)
                 */
                PrepareVO getPrepare = prepareMapper.getPrepare(no_prepare);
                prepareVo.setCd_prepare_doc_box_before(getPrepare.getCd_prepare_doc_box());

                //(-상담사변경되어도 서류함 이동하지않도록 수정)
                prepareVo.setCd_prepare_doc_box(getPrepare.getCd_prepare_doc_box());

                nCnt += prepareMapper.modifyPrepareId(prepareVo);

                // 일괄수정 아닐경우 서류함 건수 node 전달
                if (StringUtil.isEmpty(prepareVo.getYn_batch())) {
                    setNodePrepareDoc(prepareVo, getPrepare);
                }

                if(PrepareVO.CD_PREPARE_DOC_BOX_50.equals(prepareVo.getCd_prepare_doc_box())){
                    // 접수상태일때 apply 서류함건수 전달
                    List<String> applyList = applyManager.getNoApply(no_prepare);

                    if( 0 < applyList.size()){
                        ApplyVO applyVO = new ApplyVO();

                        for(String no_apply: applyList){

                            applyVO.setNo_apply(no_apply);
                            ApplyVO applyBefore = applyManager.getApply(applyVO);

							if (applyBefore != null) {
								applyManager.sendCntApplyDoc(prepareVo.getId_prepare(), applyBefore.getCd_apply_doc_box());

								if(!StringUtil.nullToString(prepareVo.getId_prepare(), "").equals(getPrepare.getId_prepare()))
                                {
                                    applyManager.sendCntApplyDoc(getPrepare.getId_prepare(), applyBefore.getCd_apply_doc_box());
                                }
							}
						}
                    }
                }
            }
		}

		if(arrNoPrepare.length != nCnt){
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");
	}

	@Override
	public ReturnClass modifyPrepareBatch(PrepareVO prepareVO) throws ParseException, IOException{
		prepareVO.setYn_batch("Y");

		String[] arrNoPrepare = prepareVO.getNo_prepare().split(",");
		int cntSussess = 0;
		int cntFail = 0;

		for ( String no_prepare : arrNoPrepare ) {
			PrepareVO getPrepare = prepareMapper.getPrepare(no_prepare);
			prepareVO.setNo_prepare(no_prepare);

			ReturnClass returnDoc = modifyPrepareDoc(prepareVO);
			if(!Constant.SUCCESS.equals(returnDoc.getCd_result()))
				continue;

			if (!StringUtil.nullToString(getPrepare.getId_prepare()).equals(prepareVO.getId_prepare())) {
				ReturnClass returnId = modifyPrepareId(prepareVO);
				if(!Constant.SUCCESS.equals(returnId.getCd_result()))
					continue;
			}

			cntSussess++;

			prepareVO.setCd_prepare_doc_box_before(getPrepare.getCd_prepare_doc_box());
			setNodePrepareDoc(prepareVO, getPrepare);
		}
		cntFail = arrNoPrepare.length - cntSussess;
		return new ReturnClass(Constant.SUCCESS, cntSussess+"건 처리/"+cntFail+"건 실패");
	}

	@Override
	public String getNoPerson(String no_prepare){
		return prepareMapper.getNoPerson(no_prepare);
	}

	@Override
	public List<HashMap<String, String>> getCntPrepareDoc(PrepareForm prepareForm){
		return prepareMapper.getCntPrepareDoc(prepareForm);
	}

	@Override
	public List<HashMap<String, String>> getCntPrepareClass(PrepareForm prepareForm) {
		return prepareMapper.getCntPrepareClass(prepareForm);
	}

	@Override
	public ReturnClass updatePrepareCnt(String no_prepare) {

		if(StringUtil.isEmpty(no_prepare)) {
			return new ReturnClass(Constant.FAILED, "사전접수번호가 입력되지 않았습니다.");
		}

		if(1 != prepareMapper.updatePrepareCnt(no_prepare)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");
	}

	@Override
	public int getCntPrepareTodayByHP(String hp) {
		int cnt_prepare_today = 0;

		cnt_prepare_today = prepareMapper.getCntPrepareTodayByHP(hp);

		return cnt_prepare_today;
	}

	@Override
	public ReturnClass updateCounselMemo(PrepareVO prepareVO) {

		if(1 != prepareMapper.updateCounselMemo(prepareVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");
	}

	@Override
	public List<HashMap<String, Object>> listPrepareInfo_excel(PrepareForm prepareForm) {
		return prepareMapper.listPrepareInfo_excel(prepareForm);
	}

	@Override
	public ReturnClass updatePrepareLst(PrepareVO prepareVO) {

		if(1 != prepareMapper.updatePrepareLst(prepareVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");

	}

	@Override
	public String getNoPrepare(PrepareForm prepareForm) {
		return prepareMapper.getNoPrepare(prepareForm);
	}

	@Override
	public PrepareVO getPrepareExist(PrepareVO prepareVO) {
		return prepareMapper.getPrepareExist(prepareVO);
	}

	@Override
	public ReturnClass procPreparePath(PrepareVO prepareVO) {

		if(1 != prepareMapper.procPreparePath(prepareVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");
	}

}
