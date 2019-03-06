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
import com.koscom.apply.model.ApplyResultForm;
import com.koscom.apply.model.ApplyResultVO;
import com.koscom.apply.model.ApplyVO;
import com.koscom.apply.service.ApplyManager;
import com.koscom.attach.model.AttachVO;
import com.koscom.attach.service.AttachManager;
import com.koscom.car.service.CarManager;
import com.koscom.counsel.model.CounselVO;
import com.koscom.counsel.service.CounselManager;
import com.koscom.domain.GoodsInfo;
import com.koscom.env.model.CodeForm;
import com.koscom.env.model.CodeInfo;
import com.koscom.env.model.CodeVO;
import com.koscom.env.service.CodeManager;
import com.koscom.goods.model.GoodsVO;
import com.koscom.goods.service.GoodsManager;
import com.koscom.person.model.PersonVO;
import com.koscom.person.service.PersonManager;
import com.koscom.prepare.model.PrepareForm;
import com.koscom.prepare.model.PrepareReturn;
import com.koscom.prepare.model.PrepareVO;
import com.koscom.prepare.service.PrepareManager;
import com.koscom.worker.service.WorkerManager;
import org.springframework.web.multipart.MultipartFile;

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
	private AttachManager attachManager;
	
	@Autowired
	private CounselManager counselManager;

	@Autowired
	private WorkerManager workerManager;

	private static final String _CONF_SYSTEM           = "_CONF_SYSTEM"          ;
	private static final String _INFO_COMP             = "_INFO_COMP"            ;
	private static final String NM_COMP                = "NM_COMP"               ;
	private static final String YN_ADD_APPLYPATH       = "YN_ADD_APPLYPATH"      ;
	private static final String NO_LOAN_COMP           = "NO_LOAN_COMP"          ;
	private static final String NO_AGENCY_COMP         = "NO_AGENCY_COMP"        ;
	private static final String PH_COMP                = "PH_COMP"               ;
	private static final String Y                      = "Y"                     ;
	private static final String N                      = "N"                     ;
	private static final String URL_ATTACH_FILE        = "url_attach_file"       ;
	private static final String AGENCY_AUTO_BLACKLIST  = "agency_auto_blacklist" ;
	private static final String YN_USE                 = "YN_USE"                ;
	private static final String _MAP_GOODS_DOC         = "_MAP_GOODS_DOC"        ;
	private static final String _CONF_APPLY_REQUEST    = "_CONF_APPLY_REQUEST"   ;
	private static final String _CONF_APPLY_AGENCY     = "_CONF_APPLY_AGENCY"    ;
	private static final String _CONF_APPLY_ID         = "_CONF_APPLY_ID"        ;
	private static final String BLANK                  = ""                      ;

	@Override
	public List<ApplyVO> listApplyInfo(ApplyForm applyForm) {
		return applyMapper.listApplyInfo(applyForm);
	}

	@Override
	public List<ApplyVO> listPastInfo(ApplyForm applyForm) {
		return applyMapper.listPastInfo(applyForm);
	}
	
	@Override
	public List<ApplyVO> listPastInfoPg(ApplyForm applyForm) {
		return applyMapper.listPastInfoPg(applyForm);
	}
		
	@Override
	public List<ApplyVO> listStatusInfo(ApplyForm applyForm) {
		return applyMapper.listStatusInfo(applyForm);
	}

	@Override
	public List<ApplyVO> listStatusInfoPg(ApplyForm applyForm) {
		return applyMapper.listStatusInfoPg(applyForm);
	}
	
	@Override
	public HashMap<String, String> listApplyCount(ApplyForm applyForm) {
		return applyMapper.listApplyCount(applyForm);
	}

	@Override
	public HashMap<String, String> listPastCount(ApplyForm applyForm) {
		return applyMapper.listPastCount(applyForm);
	}
	
	@Override
	public HashMap<String, String> listPastPgCount(ApplyForm applyForm) {
		return applyMapper.listPastPgCount(applyForm);
	}
	
	@Override
	public HashMap<String, String> listStatusCount(ApplyForm applyForm) {
		return applyMapper.listStatusCount(applyForm);
	}
	
	@Override
	public List<ApplyVO> listApplyInfoAgency(ApplyForm applyForm) {
		return applyMapper.listApplyInfoAgency(applyForm);
	}
	
	@Override
	public int listApplyCountAgency(ApplyForm applyForm) {
		return applyMapper.listApplyCountAgency(applyForm);
	}
	
	@Override
	public List<ApplyVO> listApplyByPrepare(String no_prepare) {
		return applyMapper.listApplyByPrepare(no_prepare);
	}
	
	@Override
	public List<ApplyVO> listApplyByPrepareAgency(String no_prepare) {
		return applyMapper.listApplyByPrepareAgency(no_prepare);
	}

	@Override
	public ReturnClass createApply(ApplyVO applyVO) throws IOException, FinsetException, FinsetMessageException{
        ReturnClass rtClass     = null;
        ReturnClass returnClass = null;
        PrepareVO   prepareVO = null;

        String no_apply             = null;
		String no_person            = null;//개인번호
		String cd_advertisement     = null;//광고매체
		String amt_apply            = null;//신청금액
		String cd_collect_path      = null;//(적법)최초수집경로
		String cd_contact_path      = null;//(적법)연락처를 알게 된 경로
		String cd_collect_method    = null;//(적법)고객 연락방법
		String etc_memo             = null;//(적법)기타메모
		String nm_agency            = null;//(적법)제휴사명
		String nm_ceo_agency        = null;//(적법)대표자이름
		String url_homepage_agency  = null;//(적법)홈페이지
		String nm_writer            = null;//(적법)작성자
		String id_prepare           = null;//담당자
		String etc_prepare_path     = null;//사전접수경로
		String id_agency            = null;
        String cd_fc                = null;
        String id_frt               = null;
        String yn_commonFile        = null;
        String path_common_file     = null;
        String url_common_file      = null;
        String nm_common_file       = null;
        String url_attach_file      = null;
        String cd_goods             = null;

        String url_attach           = null; // 첨부파일 URL
        String yn_auto              = null;
        String cd_result            = null;
        String fileName             = null;

        String agency_auto_blacklist= null;

        String[] cd_goods_arr       = null;
        String[] flg_file_arr       = null;
        String[] flg_file2_arr      = null;
        MultipartFile   file_common = null;
        MultipartFile[] file        = null;
        MultipartFile[] file2       = null;

        GoodsVO         goodsVO       = null;
        FileUpload      fileUpload    = null;
        BeanWrapper     requestWrapper= null;
        AttachVO        attachVO      = null;

        int cnt_file  = 0;
        int cnt_file2 = 0;

        if (applyVO !=null) {
            returnClass = new ReturnClass();
            no_apply         = applyVO.getNo_prepare      ();
            id_frt           = applyVO.getId_frt          ();
            yn_commonFile    = applyVO.getYn_commonFile   ();
            path_common_file = applyVO.getPath_common_file();
            url_common_file  = applyVO.getUrl_common_file ();
            nm_common_file   = applyVO.getNm_common_file  ();

            file_common      = applyVO.getFile_common     ();
            flg_file_arr     = applyVO.getFlg_file_arr    ();
            flg_file2_arr    = applyVO.getFlg_file2_arr   ();

            file             = applyVO.getFile            ();
            file2            = applyVO.getFile2           ();

            cd_goods_arr     = applyVO.getCd_goods_arr    ();

            prepareVO = prepareManager.getPrepare(no_apply);

            no_person           = prepareVO.getNo_person          ();
			cd_advertisement    = prepareVO.getCd_advertisement   ();
			amt_apply           = prepareVO.getAmt_apply          ();
			cd_collect_path     = prepareVO.getCd_collect_path    ();
			cd_contact_path     = prepareVO.getCd_contact_path    ();
			cd_collect_method   = prepareVO.getCd_collect_method  ();
			etc_memo            = prepareVO.getEtc_memo           ();
			nm_agency           = prepareVO.getNm_agency          ();
			nm_ceo_agency       = prepareVO.getNm_ceo_agency      ();
			url_homepage_agency = prepareVO.getUrl_homepage_agency();
			nm_writer           = prepareVO.getNm_writer          ();
			id_prepare          = prepareVO.getId_prepare         ();
            etc_prepare_path    = prepareVO.getEtc_prepare_path   ();
            id_agency           = prepareVO.getId_agency          ();

            applyVO.setNo_person       (no_person                  );
            applyVO.setCd_apply_doc_box(ApplyVO.CD_APPLY_DOC_BOX_01);
            applyVO.setCd_advertisement(cd_advertisement           );
            applyVO.setAmt_apply       (amt_apply                  );

            /**
             * 적법수집 정보 세팅
             */
            if(StringUtil.isNotEmpty(cd_collect_path    )){applyVO.setCd_collect_path    (cd_collect_path    );}//(적법)최초수집경로
            if(StringUtil.isNotEmpty(cd_contact_path    )){applyVO.setCd_contact_path    (cd_contact_path    );}//(적법)연락처를 알게 된 경로
            if(StringUtil.isNotEmpty(cd_collect_method  )){applyVO.setCd_collect_method  (cd_collect_method  );}//(적법)고객 연락방법
            if(StringUtil.isNotEmpty(etc_memo           )){applyVO.setEtc_memo           (etc_memo           );}//(적법)기타메모
            if(StringUtil.isNotEmpty(nm_agency          )){applyVO.setNm_agency          (nm_agency          );}//(적법)제휴사명
            if(StringUtil.isNotEmpty(nm_ceo_agency      )){applyVO.setNm_ceo_agency      (nm_ceo_agency      );}//(적법)대표자이름
            if(StringUtil.isNotEmpty(url_homepage_agency)){applyVO.setUrl_homepage_agency(url_homepage_agency);}//(적법)홈페이지
            if(StringUtil.isNotEmpty(nm_writer          )){applyVO.setNm_writer          (nm_writer          );}//(적법)작성자
            else if(StringUtil.isNotEmpty(id_prepare)) {
				nm_writer = workerManager.getWorkerInfo(id_prepare,"NM");
            	applyVO.setNm_writer(nm_writer);
            }

            // 접수경로 추가
            etc_prepare_path = getEtc_prepare_path(etc_prepare_path);

            applyVO.setEtc_apply_path(etc_prepare_path);

            //제휴사 접수가 아닐경우
            if(StringUtil.isEmpty(applyVO.getYn_agency())){


                for (int i=0;i<cd_goods_arr.length;i++) {
                    cd_goods = cd_goods_arr[i];
                    applyVO.setCd_goods(cd_goods);
                    goodsVO = goodsManager.getGoodsInfo(cd_goods);

					if (goodsVO !=null) {
                        cd_fc = goodsVO.getCd_fc();
						applyVO.setCd_fc(cd_fc);
					}

                    // 신청서 정보 insert
                    applyMapper.insertApply(applyVO);
                    no_apply = applyVO.getNo_apply();

                    // 첨부파일 등록
                    attachVO = new AttachVO();
                    attachVO.setCd_attach(AttachVO.CD_ATTACH_01); //공통 첨부파일
                    attachVO.setNo_apply (no_apply             );
                    attachVO.setId_frt   (id_frt               );
                    if(Y.equals(yn_commonFile)){
                        // 등록된 공통파일이 동일 할 경우
                        attachVO.setPath_save_file(path_common_file);
                        attachVO.setUrl_attach    (url_common_file );
                        attachVO.setNm_save_file  (nm_common_file  );

                        returnClass = attachManager.insertAttach(attachVO);
                    }
                    else if(file_common != null) {
                        attachVO.setFile(file_common);
                        returnClass = attachManager.createAttach(attachVO);
                    }

                    // 개별 첨부파일 업로드1
                    if("false".equals(flg_file_arr[i])) {
                        attachVO.setCd_attach(AttachVO.CD_ATTACH_11);
                        attachVO.setFile     (file[cnt_file++]     );
                        returnClass = attachManager.createAttach(attachVO);
                    }

                    // 개별 첨부파일 업로드2
                    if("false".equals(flg_file2_arr[i])) {
                        attachVO.setCd_attach(AttachVO.CD_ATTACH_12);
                        attachVO.setFile     (file2[cnt_file2++]     );
                        returnClass = attachManager.createAttach(attachVO);
                    }

                    cd_result = returnClass.getCd_result();

                    if(!Constant.SUCCESS.equals(cd_result)) {
                        throw new FinsetException("접수등록 실패");
                    }

                    i++;
                }

            } else {	//제휴사 접수일경우
                goodsVO = goodsManager.getGoodsInfo(applyVO.getCd_goods());
                //applyVO.setCd_apply_comp(goodsVO.getCd_apply_comp());
				if (goodsVO != null) {
                    cd_goods = goodsVO.getCd_goods();
                    cd_fc    = goodsVO.getCd_fc();
                    applyVO.setCd_fc(cd_fc);
				}

				// 신청서 정보 insert
				applyMapper.insertApply(applyVO);
				no_apply = applyVO.getNo_apply();

				// 첨부파일 등록
                attachVO = new AttachVO();
                attachVO.setNo_apply(no_apply);
				attachVO.setId_frt  (id_frt  );


                fileUpload     = new FileUpload();
                requestWrapper = new BeanWrapperImpl(applyVO);

                for (int i = 1; i <= 3; i++) {
                    url_attach_file = (String) requestWrapper.getPropertyValue(URL_ATTACH_FILE+i);
					// 파일 url1
					if(StringUtil.isNotEmpty(url_attach_file)) {
                        switch(i) {
                            case 1:
                                attachVO.setCd_attach(AttachVO.CD_ATTACH_01);
                                break;
                            case 2:
                                attachVO.setCd_attach(AttachVO.CD_ATTACH_11);
                                break;
                            case 3:
                                attachVO.setCd_attach(AttachVO.CD_ATTACH_12);
                                break;
                        }

						attachVO.setUrl_attach(url_attach_file);

						//파일이름 가져오기
						fileName = fileUpload.getDownFileName(attachVO.getUrl_attach(), "");


						attachVO.setNm_save_file(fileName);
						returnClass = attachManager.insertAttach(attachVO);
					}
				}


				//자동접수 시작(상품 자동접수 여부, 코드관리 자동접수 제외 제휴사인지 체크)
                yn_auto = goodsVO.getYn_auto();
                yn_auto = StringUtil.isEmpty(yn_auto)?N:yn_auto;

                agency_auto_blacklist = codeManager.getNvlCodeName(AGENCY_AUTO_BLACKLIST, id_agency, N);

				if(Y.equals(yn_auto) && !Y.equals(agency_auto_blacklist)) {
					logger.info("※ 자동접수 ※");
					logger.info("매체사: " + id_agency + ", 상품코드: " + cd_goods);
					sendApply(applyVO);
				}
            }
            no_apply = applyVO.getNo_apply();
            // 사전접수정보 업데이트(applyBefore에 no_prepare 존재)
            prepareManager.updatePrepareCnt(no_person);

           logger.info("신청서 생성완료 = "+ no_apply);
            rtClass = new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.", no_apply);
        } else {
            rtClass = new ReturnClass(Constant.FAILED, "처리 실패 하였습니다.", "");
        }

        return rtClass;
	}

	@Override
	public ReturnClass createApplyAgency(ApplyVO applyVO) throws FinsetException{
        ReturnClass rtClass = null;
        String no_apply = null;

        PrepareVO prepareVO = null;
        String no_person            = null;//개인번호
        String cd_advertisement     = null;//광고매체
        String amt_apply            = null;//신청금액
        String cd_collect_path      = null;//(적법)최초수집경로
        String cd_contact_path      = null;//(적법)연락처를 알게 된 경로
        String cd_collect_method    = null;//(적법)고객 연락방법
        String etc_memo             = null;//(적법)기타메모
        String nm_agency            = null;//(적법)제휴사명
        String nm_ceo_agency        = null;//(적법)대표자이름
        String url_homepage_agency  = null;//(적법)홈페이지
        String nm_writer            = null;//(적법)작성자
        String etc_prepare_path     = null;//사전접수경로
        String cd_fc                = null;

        if (applyVO != null) {
            // 사전접수 신청정보 세팅
            prepareVO = prepareManager.getPrepare(applyVO.getNo_prepare());

            no_person           = prepareVO.getNo_person          ();
            cd_advertisement    = prepareVO.getCd_advertisement   ();
            amt_apply           = prepareVO.getAmt_apply          ();
            cd_collect_path     = prepareVO.getCd_collect_path    ();
            cd_contact_path     = prepareVO.getCd_contact_path    ();
            cd_collect_method   = prepareVO.getCd_collect_method  ();
            etc_memo            = prepareVO.getEtc_memo           ();
            nm_agency           = prepareVO.getNm_agency          ();
            nm_ceo_agency       = prepareVO.getNm_ceo_agency      ();
            url_homepage_agency = prepareVO.getUrl_homepage_agency();
            nm_writer           = prepareVO.getNm_writer          ();
            etc_prepare_path    = prepareVO.getEtc_prepare_path   ();

            applyVO.setNo_agency_person(no_person                  );
            applyVO.setCd_apply_doc_box(ApplyVO.CD_APPLY_DOC_BOX_00);
            applyVO.setCd_advertisement(cd_advertisement    );
            applyVO.setAmt_apply       (amt_apply           );

            //적법수집 정보 세팅
            if(StringUtil.isNotEmpty(cd_collect_path    )){applyVO.setCd_collect_path    (cd_collect_path    );}
            if(StringUtil.isNotEmpty(cd_contact_path    )){applyVO.setCd_contact_path    (cd_contact_path    );}
            if(StringUtil.isNotEmpty(cd_collect_method  )){applyVO.setCd_collect_method  (cd_collect_method  );}
            if(StringUtil.isNotEmpty(etc_memo           )){applyVO.setEtc_memo           (etc_memo           );}
            if(StringUtil.isNotEmpty(nm_agency          )){applyVO.setNm_agency          (nm_agency          );}
            if(StringUtil.isNotEmpty(nm_ceo_agency      )){applyVO.setNm_ceo_agency      (nm_ceo_agency      );}
            if(StringUtil.isNotEmpty(url_homepage_agency)){applyVO.setUrl_homepage_agency(url_homepage_agency);}
            if(StringUtil.isNotEmpty(nm_writer          )){applyVO.setNm_writer          (nm_writer          );}

            // 접수경로 추가
            etc_prepare_path = getEtc_prepare_path(etc_prepare_path);

            applyVO.setEtc_apply_path(etc_prepare_path);

            GoodsVO goodsVO = goodsManager.getGoodsInfo(applyVO.getCd_goods());
			if (goodsVO != null) {
                cd_fc = goodsVO.getCd_fc();
                applyVO.setCd_fc(cd_fc);
			}

			// 신청서 정보 insert
            applyMapper.insertApplyAgency(applyVO);
            no_apply = applyVO.getNo_apply();

            // 사전접수정보 업데이트(applyBefore에 no_prepare 존재)
            prepareManager.updatePrepareCnt(prepareVO.getNo_prepare());

            rtClass = new ReturnClass(Constant.SUCCESS, "정상 처리 하였습니다.", (Object) no_apply);
        } else {
            throw new FinsetException("처리 실패 하였습니다.");
        }

        return rtClass;
    }
	
	@Override
	public ReturnClass sendApply(ApplyVO applyVO) throws IOException, FinsetMessageException, FinsetException{
		
		//사전접수 정보 조회
		ApplyVO   applyBefore = null;
        PrepareVO prepareVO   = null;
        PersonVO  personVO    = null;

        String no_apply        = null;
        String no_prepare      = null;
        String cd_apply_doc_box= null;//서류함상태
        String no_person       = null;//개인번호
		String cd50_goods_job  = null;// 상품 직군
		String cd_job_class    = null;//직업구분
		String yn_use          = null;//직업구분
		String auto_goods_doc  = null;//직업구분
		String before_cd_goods = null;//직업구분
        String currentDate     = DateUtil.getCurrentDate();

        applyBefore = applyMapper.getApply(applyVO);

        no_apply         = applyVO    .getNo_apply        ();

        no_prepare       = applyBefore.getNo_prepare      ();
        cd_apply_doc_box = applyBefore.getCd_apply_doc_box();
        before_cd_goods  = applyBefore.getCd_goods        ();

        applyVO.setCd_apply_doc_box_before(cd_apply_doc_box);

        prepareVO = prepareManager.getPrepare(no_prepare);
        if(prepareVO != null) {
            no_person = prepareVO.getNo_person();
        }
		//고객정보 조회
        personVO = personManager.getPersonInfo(no_person);

        //상품정보 조회
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setCd_goods(applyBefore.getCd_goods());
		goodsInfo = goodsManager.getGoodsInfo(goodsInfo);

		if(goodsInfo == null) {
			logger.info(" !! 상품정보 없음 !! ");
            throw new FinsetMessageException("등록된 상품정보가 없습니다.");
		}
        cd50_goods_job = goodsInfo.getCd50_goods_job();
        cd_job_class   = personVO.getCd_job_class   ();
        logger.info(" ======= 접수 START ======= ");
        logger.info(" no_apply : " + no_apply);

		//특정 직군 상품인지 체크
		if(StringUtil.isNotEmpty(cd50_goods_job)) {
			if(StringUtil.isEmpty(cd_job_class) || !cd50_goods_job.contains(cd_job_class)){
				LogUtil.error(logger," !! 접수가능한 직군이 아님 !! ");
				throw new FinsetMessageException("접수가능한 직군이 아닙니다.");
			}
		}
		
		// DB 업데이트
		applyVO.setCd_apply_doc_box(ApplyVO.CD_APPLY_DOC_BOX_10);
		applyVO.setYmd_apply(currentDate);
		if(1 != applyMapper.modifyApplyDoc(applyVO)) {
			logger.info(" !! 서류함 변경 오류 !! ");
			throw new FinsetException(" !! 서류함 변경 오류 !! ");
		}
        yn_use           = codeManager.getNvlCodeName(_MAP_GOODS_DOC, YN_USE         , N    );
        cd_apply_doc_box = codeManager.getCodeName   (_MAP_GOODS_DOC, before_cd_goods       );
        auto_goods_doc   = codeManager.getNvlCodeName(_MAP_GOODS_DOC, before_cd_goods, BLANK);

		// 상품별 접수시 자동 서류함 변경
		if(Y.equals(yn_use) && StringUtil.isNotEmpty(auto_goods_doc)) {
			applyVO.setCd_apply_doc_box(cd_apply_doc_box);
			if(1 != applyMapper.modifyApplyDoc(applyVO)) {
				logger.info(" !! 상품별 자동 서류함 변경 오류 !! ");
			}
		}
		
		// 데이터 매핑 후 URL 전송
		ReturnClass returnClass = requestApply(applyVO, goodsInfo);
		if(Constant.FAILED.equals(returnClass.getCd_result())) {
		    throw new FinsetException(returnClass.getMessage());
        }
		logger.info(" ======= 접수 END ======= ");
		return returnClass;
	}

    /**
     * 신청정보 금융사로 전송
     * @param apply
     * @param goodsInfo
     * @return
     */
	private ReturnClass requestApply(ApplyVO apply, GoodsInfo goodsInfo) throws IOException, FinsetMessageException {
		logger.info(" ======= 데이터 맵핑 시작 ======= ");
		
		// 신청정보 조회
		ApplyVO applyVO = applyMapper.getApply(apply);
		
		String targetUrl = goodsInfo.getUrl_request();
		String id_request = goodsInfo.getId_request();
		String param = "";
		
		// 금융사 접수 아이디 맵핑 
		String yn_apply_use       = null;
        String cd_fc              = null;
        String type_apply_request = null;
        String code_group         = null;// 코드종류
        String id_agency          = null;//매체사ID
        String id_prepare         = null;//담당자
        String nm_code            = null;// 코드명

        PrepareVO ppVO            = null;
        CodeInfo  codeInfo        = null;
        CodeInfo  cdVO            = null;

        yn_apply_use = codeManager.getNvlCodeName(_CONF_APPLY_REQUEST, YN_USE, N);
        cd_fc        = (goodsInfo != null)? goodsInfo.getCd_fc(): null;
        if(Y.equals(yn_apply_use)){
			//String type_apply_request = codeManager.getNvlCodeName(_CONF_APPLY_REQUEST, goodsInfo.getCd_apply_comp(), "");
            type_apply_request = codeManager.getNvlCodeName(_CONF_APPLY_REQUEST, cd_fc, BLANK);

            ppVO = prepareManager.getPrepare(applyVO.getNo_prepare());
            id_agency  = ppVO.getId_agency ();
            id_prepare = ppVO.getId_prepare();
            codeInfo = new CodeInfo();

            if("AGENCY".equals(type_apply_request) && StringUtil.isNotEmpty(id_agency)) {
				// 접수처별 분리
                code_group = _CONF_APPLY_AGENCY;
				codeInfo.setCode_group(code_group);
				codeInfo.setCode_value(id_agency );
			} else if ("ID".equals(type_apply_request) && StringUtil.isNotEmpty(id_prepare)) {
				// 접수 아이디별 분리
                code_group = _CONF_APPLY_ID;
                codeInfo.setCode_group(_CONF_APPLY_ID    );
				codeInfo.setCode_value(id_prepare        );
			}
			if(StringUtil.isNotEmpty(code_group)){
                cdVO = codeManager.getCodeInfo(codeInfo);
                if(cdVO != null)
                    nm_code = cdVO.getNm_code();
					id_request = (StringUtil.isEmpty(nm_code))? id_request : nm_code;
			}
			
		}
		// 입력된 폼이 없으면 오류
		else {
			logger.info(" !! 신청모델정보 없음 !! ");
			throw new FinsetMessageException("신청모델정보가 없습니다. 관리자에게 문의하여주세요.");
//			return new ReturnClass(Constant.FAILED, "신청모델정보가 없습니다. 관리자에게 문의하여주세요.");
		}
		
		logger.info(" ======= 연동 START ======= ");
		// URL 요청
		URLConnection urlCon = new URLConnection();
		ReturnClass returnClass = urlCon.sendReqPOST(targetUrl, param);
		logger.info(" ======= 연동 END ======= ");
		if(Constant.FAILED.equals(returnClass.getCd_result())) 
			return returnClass;
		
		return returnClass;
	}
	
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
	public ApplyVO getApplyInfo(String no_apply) {
		ApplyVO applyVO = new ApplyVO();
		applyVO.setNo_apply(no_apply);
		return applyMapper.getApply(applyVO);
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
	public void sendCntApplyDoc(String id_prepare, String cd_apply_doc_box) {
		
//		ApplyVO applyVO = new ApplyVO();
//		applyVO.setId_prepare(id_prepare);
//		applyVO.setCd_apply_doc_box(cd_apply_doc_box);
//		
//		HashMap<String, String> cnt = applyMapper.getCntApply(applyVO);
//		applyVO.setCnt_apply_doc(String.valueOf(cnt.get("CNT")));
//		
//		sendToNodeThread(applyVO);
	}

//	static int cntThread = 0; // 알람 리턴 쓰레드 진행
//	static int cntThreadTotal = 0; // 알람 리턴 쓰레드 총
	
//	private void sendToNodeThread(final ApplyVO applyVO) {
//		Thread thread = new Thread() {
//			public void run() {
//				try {
//					++cntThreadTotal;
//					++cntThread;
//					sendToNode(applyVO);
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
//
//	private void sendToNode(ApplyVO applyVO) throws Exception {
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
//		String param = "comp_id="+codeManager.getNvlCodeName(_CONF_SYSTEM, "ID_COMP","")+"&user_id="+StringUtil.nullToString(applyVO.getId_prepare(),"")
//				+"&type=CNT_APPLY&doc="+applyVO.getCd_apply_doc_box()+"&cnt="+applyVO.getCnt_apply_doc();
//
//		url.sendReqGET(targetUrl, param);
//	}
	
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
	
	@Override
	public List<HashMap<String, String>> getCntApplyDoc(ApplyForm applyForm) {
		return applyMapper.getCntApplyDoc(applyForm);
	}

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
	public ReturnClass modifyApplyLegal(ApplyVO applyVO) {
		
		if (StringUtil.isEmpty(applyVO.getNo_apply())) {
			return new ReturnClass(Constant.FAILED, "접수번호가 입력되지 않았습니다.");
		}
		
		if (1 != applyMapper.modifyApplyLegal(applyVO)) {
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

	@Override
	public List<String> getNoApply(String no_prepare) {
		List<String> arrNoApply = applyMapper.getNoApply(no_prepare);
		return arrNoApply;
	}

	@Override
	public List<HashMap<String, Object>> listApplyInfo_excel(ApplyForm applyForm) {
		return applyMapper.listApplyInfo_excel(applyForm);
	}
	
	
	public ReturnClass procAutoApply(String no_apply, HashMap<String,HashMap<String,String>> listAutoApply) throws IOException, FinsetException, FinsetMessageException{
		ApplyVO applyVO = new ApplyVO();
		applyVO.setNo_apply(no_apply);
		ApplyVO applyInfo = applyMapper.getApply(applyVO);
		
		if(applyInfo == null) return new ReturnClass(Constant.FAILED, "신청서 복제에 실패했습니다.");
		
		// 기존 접수이력 가져옴 - 중복 상품 체크위해
		List<ApplyVO> listApply = applyMapper.listApplyByPrepare(applyInfo.getNo_prepare());
		HashMap<String, Boolean> mapApply = new HashMap<String, Boolean>();
		for(ApplyVO infoApply : listApply){
			mapApply.put(infoApply.getCd_goods(), true);
		}
		
		//첨부파일 가져옴
		List<AttachVO> listAttach = attachManager.listAttach(applyVO.getNo_apply(), "");
		
		for (String keyFinance : listAutoApply.keySet()){
			//logger.info(">>>>>>>>>>>>>>>>> key:"+keyFinance+",value:"+listAutoApply.get(keyFinance));
			HashMap<String,String> infoApply = listAutoApply.get(keyFinance);
			
			applyInfo.setCd_apply_doc_box(ApplyVO.CD_APPLY_DOC_BOX_01);
			String cd_goods = codeManager.getCodeName("_MAP_CONSOR_CZ_ID",keyFinance);
			
			if(mapApply.get(cd_goods) != null){
				logger.info("등록된 상품 => " + cd_goods);
				continue;
			}
			
			//코드에 맵핑된 상품이 없을 경우 건너뜀
			if(StringUtil.isEmpty(cd_goods) || cd_goods == keyFinance) continue;
			
			applyInfo.setCd_goods(cd_goods);
			GoodsVO goodsVO = goodsManager.getGoodsInfo(cd_goods);
			if (goodsVO != null) {
				applyInfo.setCd_fc(goodsVO.getCd_fc());
			}
			applyInfo.setMemo_to_apply(infoApply.get("memo_to_apply"));
			applyInfo.setId_frt("SYSTEM");
			
			//신청서 복제
			applyMapper.insertApply(applyInfo);
			
			//첨부파일 등록
			for(AttachVO listAtc : listAttach){
				listAtc.setNo_apply(applyInfo.getNo_apply());
				listAtc.setId_frt("SYSTEM");
				attachManager.insertAttach(listAtc);
			}
			
			//접수
			sendApply(applyInfo);
			
	    }
		
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public List<ApplyVO> listApplyDestory(int destroyTerm) {
		return applyMapper.listApplyDestory(destroyTerm);
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
	public ReturnClass updateApplyChk(ApplyVO applyVO) throws IOException, FinsetException, FinsetMessageException {
		ReturnClass returnClass = null;
		int applyChk = 0;
        PrepareVO prepareVO = null;
        String cd_goods = "";
        if (applyVO != null) {
            applyChk = applyMapper.updateApplyChk(applyVO);
            if(1 != applyChk) {
               throw new FinsetException("삭제처리에 실패하였습니다.");
            } else {

                prepareVO = prepareManager.getPrepare(applyVO.getNo_prepare());

                if (prepareVO != null) {
                    if(StringUtil.isNotEmpty(applyVO.getCd_goods())){
                        cd_goods = applyVO.getCd_goods();
                    }else{
                        ApplyVO applyInfo = applyMapper.getApply(applyVO);
                        cd_goods = applyInfo.getCd_goods();
                    }

                    //자동접수 시작(상품 자동접수 여부, 코드관리 자동접수 제외 제휴사인지 체크)
                    GoodsVO goodsVO = goodsManager.getGoodsInfo(cd_goods);
                    String yn_auto = null;
					if (goodsVO != null) {
						yn_auto = goodsVO.getYn_auto();
					}
					if(StringUtil.isEmpty(yn_auto))
                        yn_auto = "N";

                    if(Y.equals(yn_auto) && !Y.equals(codeManager.getNvlCodeName("agency_auto_blacklist", prepareVO.getId_agency(), "N"))){
                        logger.info("※ 자동접수 ※");
                        logger.info("매체사: " + prepareVO.getId_agency() + ", 상품코드: " + cd_goods);
                        sendApply(applyVO);
                    }
                    returnClass = new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
				}
			}
		}

        return returnClass;
	}
	
	@Override
	public List<HashMap<String, String>> getCntMonthApply(ApplyForm applyForm){
		List<HashMap<String, String>> cntMonthApply = applyMapper.getCntMonthApply(applyForm);
		return cntMonthApply;
	}
	
	@Override
	public List<HashMap<String, String>> getCntDayApply(ApplyForm applyForm){
		return applyMapper.getCntDayApply(applyForm);
	}
	
	@Override
	public HashMap<String, String> getCntSummaryApplyByAgency(ApplyForm applyForm){
		HashMap<String, String> cntSummaryApply = applyMapper.getCntSummaryApplyByAgency(applyForm);
		return cntSummaryApply;
	}

	@Override
	public ReturnClass procApplyPath(ApplyVO applyVO) {
		
		if(1 != applyMapper.procApplyPath(applyVO)){
			return new ReturnClass(Constant.FAILED, "삭제 처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상처리 되었습니다.");
	}
	
	@Override
	public int getCntApplyTodayByInfo(ApplyVO applyVO){
		return applyMapper.getCntApplyTodayByInfo(applyVO);
	}

	@Override
	public HashMap<String, String> getCntApplySummary(ApplyForm applyForm) {
		return applyMapper.getCntApplySummary(applyForm);
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
				applyVO.setNm_writer(workerManager.getWorkerInfo(prepareVO.getId_prepare(),"NM"));
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
	public List<ApplyVO> listLookupInfoPg(ApplyForm applyForm) {
		return applyMapper.listLookupInfoPg(applyForm);
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
		return applyMapper.getLoanProgSts(applyForm);
	}
	
	/**
	 * Name   : getLoanProgSts
	 * Desc   : 대출진행현황 조회
	 * input  : ApplyForm
	 * output : ApplyVO
	 * Date   : 2017.09.26
	 */
	@Override
	public List<ApplyVO> listLoanProgSts(ApplyForm applyForm) {
		return applyMapper.listLoanProgSts(applyForm);
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
		return applyMapper.listPastLoanHistory(applyForm);
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
		return applyMapper.listPastLoanHistoryCount(applyForm);
	}
	
	/**
	 * 상품조회 이력 조회
	 * @param applyResultForm
	 * @return List<ApplyResultVO>
	 */
	@Override
	public List<ApplyResultVO> ListApplyResult(ApplyResultForm applyResultForm) {
		
		List<ApplyResultVO> list =applyMapper.ListApplyResult(applyResultForm);
		
		for( int i =0 ; i <list.size(); i ++){
			logger.info(list.get(i).getCd_fc());
		}
		
		return list;
	}
	
	/**
	 * 상품조회 이력 건수
	 * @param applyResultForm
	 * @return int
	 */
	@Override
	public int ListApplyResultCount(ApplyResultForm applyResultForm) {
		return applyMapper.ListApplyResultCount(applyResultForm);
	}

	private String getEtc_prepare_path(String etc_prepare_path){
        String yn_add_applypath     = null;
        String nm_comp              = null;
        String code_nm_comp         = null;
        String code_no_loan_comp    = null;
        String code_no_agency_comp  = null;
        String code_ph_comp         = null;
        String currentDate          = null;
        int    i_etc_prepare_path   = 0   ;//사전접수경로 INDEX

        yn_add_applypath    = codeManager.getCodeName(_CONF_SYSTEM, YN_ADD_APPLYPATH);
        nm_comp             = codeManager.getCodeName(_INFO_COMP  , NM_COMP         );
        code_nm_comp        = codeManager.getCodeName(_INFO_COMP  , NM_COMP         );
        code_no_loan_comp   = codeManager.getCodeName(_INFO_COMP  , NO_LOAN_COMP    );
        code_no_agency_comp = codeManager.getCodeName(_INFO_COMP  , NO_AGENCY_COMP  );
        code_ph_comp        = codeManager.getCodeName(_INFO_COMP  , PH_COMP         );

        if(Y.equals(yn_add_applypath) && NM_COMP.equals(nm_comp)){
            if(StringUtil.isNotEmpty(etc_prepare_path)) {
                i_etc_prepare_path = etc_prepare_path.split("@@").length+1;
                etc_prepare_path += "@@"+i_etc_prepare_path+"||";
            }

            else {
                etc_prepare_path = "1||";
            }
            currentDate         = DateUtil.getCurrentDate();
            etc_prepare_path += currentDate         +"||"
                    + code_nm_comp        + "||"
                    + code_no_loan_comp   + "||"
                    + code_no_agency_comp + "||"
                    + code_ph_comp        ;
        }
        return etc_prepare_path;
    }
}
