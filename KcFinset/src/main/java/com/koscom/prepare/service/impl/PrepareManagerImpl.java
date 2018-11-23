package com.koscom.prepare.service.impl;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.koscom.finset.model.FinsetVO;
import com.koscom.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.apply.model.ApplyVO;
import com.koscom.apply.service.ApplyManager;
import com.koscom.env.service.CodeManager;
import com.koscom.person.service.PersonManager;
import com.koscom.prepare.dao.PrepareMapper;
import com.koscom.prepare.model.PrepareForm;
import com.koscom.prepare.model.PrepareVO;
import com.koscom.prepare.service.PrepareManager;


@Service("prepareManager")
public class PrepareManagerImpl implements PrepareManager {

	private static final Logger logger = LoggerFactory.getLogger(PrepareManagerImpl.class);

	@Autowired
	private PrepareMapper prepareMapper;

	@Autowired
	private CodeManager codeManager;

	@Autowired
	private PersonManager personManager;

	@Autowired
	private ApplyManager applyManager;


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
	public ReturnClass updateCounselMemo(PrepareVO prepareVO) {

		if(1 != prepareMapper.updateCounselMemo(prepareVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");
	}
	
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
}
