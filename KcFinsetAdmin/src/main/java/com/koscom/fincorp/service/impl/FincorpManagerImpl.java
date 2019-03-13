package com.koscom.fincorp.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.koscom.util.*;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.FincorpInfo;
import com.koscom.fincorp.dao.FincorpMapper;
import com.koscom.fincorp.model.FincorpForm;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.fincorp.model.FincorpfcNminfoForm;
import com.koscom.fincorp.model.FincorpfcNminfoVO;
import com.koscom.worker.model.WorkerForm;
import com.koscom.worker.model.WorkerVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("fincorpManager")
public class FincorpManagerImpl implements FincorpManager {

	private static final Logger logger = LoggerFactory.getLogger(FincorpManagerImpl.class);

	@Autowired
	private FincorpMapper fincorpMapper;

	@Override
	public FincorpVO getFincorpInfo(String cd_fc) {
		FincorpInfo fincorpInfo = new FincorpInfo();
		fincorpInfo.setCd_fc(cd_fc);
		return fincorpMapper.getFincorpInfo(fincorpInfo);
	}

	@Override
	public List<String> getFcInfo(String cd_fin) {
		FincorpInfo fincorpInfo = new FincorpInfo();
		fincorpInfo.setCd_fin(cd_fin);
		return fincorpMapper.getFcInfo(fincorpInfo);
	}

	@Override
	public List<FincorpVO> listFincorpInfo(FincorpForm fincorpForm) {
		return fincorpMapper.listFincorpInfo(fincorpForm);
	}

	@Override
	public int listFincorpCount(FincorpForm fincorpForm) {
		return fincorpMapper.listFincorpCount(fincorpForm);
	}

	@Override
	public List<FincorpVO> listCodeFincorp(FincorpVO fincorpVO) {
		return fincorpMapper.listCodeFincorp(fincorpVO);
	}

	@Override
	public ReturnClass createFincorp(FincorpVO fincorpVO) {

        // 파일 업로드
        logger.debug("======LCA FILE INFO============");
        String fileName = fincorpVO.getFileName();
        int fileSize = fincorpVO.getFileSize();
        byte[] fileArray = fincorpVO.getFileArray();

        logger.info("fileName->"+fileName);
        logger.info("fileSize->"+fileSize);

        if(fileArray != null && fileSize > 0){
            fincorpVO.setImg_bi(fileArray);

            String fileName1 = nameCheck(fileName);

            logger.debug("======createFincorp====fileName1============="+fileName1);
            // 실제 저장된 경로명
            fincorpVO.setPath_file1(fileName1);
            // 실제 저장된 파일명
            fincorpVO.setNm_file1(fileName1.substring(fileName1.lastIndexOf('/')+1, fileName1.length()));
        }
        int result = fincorpMapper.createFincorp(fincorpVO);
		if (1 != result) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}


	@Override
	public ReturnClass procFincorpInfo(FincorpVO fincorpVO) throws IOException{
        logger.debug("======LCA FILE INFO============");
        String fileName = fincorpVO.getFileName();
        int fileSize = fincorpVO.getFileSize();
        byte[] fileArray = fincorpVO.getFileArray();

        logger.info("fileArray->"+fileArray);
        logger.info("fileName->"+fileName);
        logger.info("fileSize->"+fileSize);

        if(fileArray != null && fileSize > 0){
            fincorpVO.setImg_bi(fileArray);
            String fileName1 = nameCheck(fileName);

            FileUtils.writeByteArrayToFile(new File(fileName1), fileArray);
            logger.debug("======procFincorpInfo====fileName1============="+fileName1);
            // 실제 저장된 경로명
            fincorpVO.setPath_file1(fileName1);
            // 실제 저장된 파일명
            fincorpVO.setNm_file1(fileName1.substring(fileName1.lastIndexOf('/')+1, fileName1.length()));
        }

		// 휴대폰 형태에 맞게 다시 세팅 *계약담당자*
		if(!StringUtil.isCellphone(fincorpVO.getHp_staff_contract()))
		fincorpVO.setHp_staff_contract(StringUtil.formatTelNo(fincorpVO.getHp_staff_contract()));

		// 휴대폰 형태에 맞게 다시 세팅 *정산담당자*
		if(!StringUtil.isCellphone(fincorpVO.getHp_staff_adjust()))
		fincorpVO.setHp_staff_adjust(StringUtil.formatTelNo(fincorpVO.getHp_staff_adjust()));

		// 휴대폰 형태에 맞게 다시 세팅 *상품담당자*
		if(!StringUtil.isCellphone(fincorpVO.getHp_staff_goods()))
		fincorpVO.setHp_staff_goods(StringUtil.formatTelNo(fincorpVO.getHp_staff_goods()));

		if (1 != fincorpMapper.procFincorpInfo(fincorpVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public ReturnClass delFincorpInfo(FincorpVO fincorpVO) {
		if (1 != fincorpMapper.delFincorpInfo(fincorpVO)) {
			return new ReturnClass(Constant.FAILED, "삭제 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "삭제 성공하였습니다.");
	}

	@Override
	public String getNmFc(String cd_fc) {
		return fincorpMapper.getNmFc(cd_fc);
	}

	@Override
	public ReturnClass listWorkerInfoJson(WorkerForm form) {
		JSONArray list = new JSONArray();
		JSONObject object = null;
		List<WorkerVO> res = fincorpMapper.listWorkerInfoJson(form);
		for (int i=0; i < res.size() ; i++ ) {
			object = new JSONObject();
			object.put("data", res.get(i).getCd_fc());
			list.add(object);
		}
		return new ReturnClass( Constant.SUCCESS, "정상적으로 처리되었습니다.", list );
	}

	@Override
	public List<FincorpVO> listFincorpEdocInfo(FincorpForm fincorpForm) {
		return fincorpMapper.listFincorpEdocInfo(fincorpForm);
	}

	@Override
	public ReturnClass listNmfcInfoJson(WorkerForm form) {
		JSONArray list = new JSONArray();
		JSONObject object = null;
		List<FincorpVO> res = fincorpMapper.listNmfcInfoJson(form);
		for (int i=0; i < res.size() ; i++ ) {
			object = new JSONObject();
			object.put("data", res.get(i).getNm_fc());
			list.add(object);
		}
		return new ReturnClass( Constant.SUCCESS, "정상적으로 처리되었습니다.", list );
	}

	@Override
	public int getFcCnt(FincorpVO fincorpVO) {
		return fincorpMapper.getFcCnt(fincorpVO);
	}

	@Override
	public String getFcPathFile(String cd_fc) {
		return fincorpMapper.getFcPathFile(cd_fc);
	}

	@Override
	public byte[] getImgBi(String path_file1) throws Exception {
        FincorpVO vo = new FincorpVO();
        vo.setPath_file1(path_file1);
	    Map resMap = fincorpMapper.getImgBi(vo);
//        LogUtil.debugLn(logger,"resMap="+resMap);
        byte[] imgBi = (resMap != null)?(byte[])resMap.get("img_bi"):null;
//        LogUtil.debugLn(logger,"blImgBi="+imgBi);
//        byte[] imgBi = blImgBi.getBytes(1L, (int)blImgBi.length());
//        byte[] imgBi = blImgBi.getBytes();
//	    LogUtil.debugLn(logger,"1IMG_BI="+imgBi);
		return imgBi;
	}
    @Override
    public int regFcImg(String nm_fc, byte[] fileData,String fileName) {
	    FincorpVO vo = new FincorpVO();
	    vo.setPath_file1(fileName);
	    vo.setNm_fc(nm_fc);
	    vo.setImg_bi(fileData);
        return fincorpMapper.regFcImg(vo);
    }

    /**
     * 해당 파일명으로 저장된 데이터가 존재하면 새로운 파일명 리턴
     * @param pFileName
     * @return
     * @throws Exception
     */
    public String nameCheck(String pFileName) {
		String fileName = pFileName;
		fileName = StringUtil.nullToEmpty(fileName);

		if ( StringUtil.isEmpty(fileName) ) return null;

		// 저장경로 존재여부 검사를 위한 경로세팅
//            String checkPath = fileName.substring(0, fileName.lastIndexOf('/') + 1);
//
//            logger.info("checkPath : "+checkPath);
//            logger.info("checkPath : "+checkPath);
//            logger.info("checkPath : "+checkPath);
//            // 저장경로 존재여부 검사, 폴더가 존재 하지 않으면 생성
//            File dir = new File(checkPath);
//            dir.mkdirs();

		// 해당경로 같은 파일명 존재여부 검사
		// 같은 파일명이 존재할경우 파일명 + "_i" 추가
		int i=0;
		String tmpName = fileName;
		int cnt =  0;
		while(true) {
			i++;
			cnt = fincorpMapper.getFileCount(tmpName);
//                dir = new File(tmpName);
			/**
			 * 파일이 존재하면
			 */
			if(cnt == 0){
				break;
			} else {
				// 파일명 생성
				tmpName = StringUtil.getPickupFirst(fileName) + "_" + i + "." + StringUtil.getPickupLast(fileName);
				continue;
			}
		}
		fileName = tmpName;

        return fileName;
    }

    /* srchou 추가 */
	/**
	 * 금융사명 KCB 사전연계 list
	 * @param fincorpfcNminfoForm
	 * @return FincorpfcNminfoVO
	 */
	@Override
	public List<FincorpfcNminfoVO> listFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm) {
		// TODO Auto-generated method stub
		return fincorpMapper.listFincorpfcNminfo(fincorpfcNminfoForm);
	}
	/**
	 * 금융사명 KCB 사전연계 정보 삽입
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
	@Override
	public String createFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm) {
		// TODO Auto-generated method stub
		int count = 0;
		String result = null;
		count = fincorpMapper.createFincorpfcNminfo(fincorpfcNminfoForm);

		if(count > 0){
			result = "등록에 성공하였습니다.";
		}else{
			result = "등록에 실패하였습니다.";
		}

		return result;
	}
	/**
	 * 금융사명 KCB 사전연계 정보 수정
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
	@Override
	public String updFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm) {
		// TODO Auto-generated method stub
		int count = 0;
		String result = null;
		count = fincorpMapper.updFincorpfcNminfo(fincorpfcNminfoForm);

		if(count > 0){
			result = "수정에 성공하였습니다.";
		}else{
			result = "수정에 실패하였습니다.";
		}

		return result;
	}
	/**
	 * 금융사명 KCB 사전연계 정보 삭제
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
	@Override
	public String delFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm) {
		// TODO Auto-generated method stub
		int count = 0;
		String result = null;
		count = fincorpMapper.delFincorpfcNminfo(fincorpfcNminfoForm);

		if(count > 0){
			result = "삭제에 성공하였습니다.";
		}else{
			result = "삭제에 실패하였습니다.";
		}

		return result;
	}
	@Override
	public List<HashMap> tempQuery(FincorpVO vo) {
		return fincorpMapper.tempQuery(vo);
	}

	@Override
	public ReturnClass chkCdfc(FincorpVO fincorpVO) {
		int result = fincorpMapper.chkCdfc(fincorpVO);
		if( result > 0){
			return new ReturnClass(Constant.FAILED, "사용 불가능합니다", result);
		}else{
			return new ReturnClass(Constant.SUCCESS, "사용 가능합니다", result);
		}
	}

	/**
	 * 스크래핑 여부 변경(COOCON API관리 등록/삭제)
	 * @param FincorpVO
	 * @return String
	 * @throws IOException
	 */
	@Override
	public ReturnClass updateYnScrap(FincorpVO fincorpVO){
		// TODO Auto-generated method stub
		int count = 0;
		count = fincorpMapper.updateYnScrap(fincorpVO);

		if(count > 0){
			return new ReturnClass(Constant.FAILED, "수정에 성공하였습니다.", count);
		}else{
			return new ReturnClass(Constant.FAILED, "수정에 실패하였습니다.", count);
		}
	}

	
	/**
 	 * COOCON 금융사 코드(스크래핑 대상) 
	 *
	 * @param String
	 * @return List<String>
	 */

	@Override
	public List<String> getCooconFcCd(String type_fc){
		// TODO Auto-generated method stub

		return fincorpMapper.getCooconFcCd(type_fc);

	}
}
