package com.koscom.fincorp.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.koscom.util.LogUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.fincorp.model.FincorpForm;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.model.FincorpfcNminfoForm;
import com.koscom.fincorp.model.FincorpfcNminfoVO;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.fincorp.service.FincorpWebManager;
import com.koscom.util.ReturnClass;
import com.koscom.worker.model.WorkerForm;

import org.springframework.web.multipart.MultipartFile;

@Service("fincorpWebManager")
public class FincorpWebManagerImpl implements FincorpWebManager {

	private static final Logger logger = LoggerFactory.getLogger(FincorpWebManagerImpl.class);
	@Autowired
	FincorpManager fincorpManager;

	@Override
	public FincorpVO getFincorpInfo(String id_fincorp) {
		// TODO Auto-generated method stub
		return fincorpManager.getFincorpInfo(id_fincorp);
	}

	@Override
	public List<String> getFcInfo(String cd_fin) {
		// TODO Auto-generated method stub
		return fincorpManager.getFcInfo(cd_fin);
	}

	@Override
	public List<FincorpVO> listFincorpInfo(FincorpForm fincorpForm) {
		// TODO Auto-generated method stub
		return fincorpManager.listFincorpInfo(fincorpForm);
	}

	@Override
	public int listFincorpCount(FincorpForm fincorpForm) {
		// TODO Auto-generated method stub
		return fincorpManager.listFincorpCount(fincorpForm);
	}

	@Override
	public List<FincorpVO> listCodeFincorp(FincorpVO fincorpVO) {
		// TODO Auto-generated method stub
		return fincorpManager.listCodeFincorp(fincorpVO);
	}

	@Override
	public ReturnClass procFincorpInfo(FincorpVO fincorpVO) throws IOException {
		// TODO Auto-generated method stub
		return fincorpManager.procFincorpInfo(fincorpVO);
	}

	@Override
	public ReturnClass createFincorp(FincorpVO fincorpVO) {
		// TODO Auto-generated method stub
		return fincorpManager.createFincorp(fincorpVO);
	}

	@Override
	public ReturnClass delFincorpInfo(FincorpVO fincorpVO) {
		// TODO Auto-generated method stub
		return fincorpManager.delFincorpInfo(fincorpVO);
	}

	@Override
	public String getNmFc(String cd_fc) {
		// TODO Auto-generated method stub
		return fincorpManager.getNmFc(cd_fc);
	}

	@Override
	public ReturnClass listWorkerInfoJson(WorkerForm form) {
		// TODO Auto-generated method stub
		return fincorpManager.listWorkerInfoJson(form);
	}

	@Override
	public ReturnClass listNmfcInfoJson(WorkerForm form) {
		// TODO Auto-generated method stub
		return fincorpManager.listNmfcInfoJson(form);
	}

	@Override
	public int getFcCnt(FincorpVO fincorpVO) {
		// TODO Auto-generated method stub
		return fincorpManager.getFcCnt(fincorpVO);
	}

	@Override
	public String getFcPathFile(String cd_fc) {
		// TODO Auto-generated method stub
		return fincorpManager.getFcPathFile(cd_fc);
	}

	/**
	 * 이미지 파일을 저장하는 메소드
	 *
	 * @param nm_fc   금융사명
	 * @param fileData 이미지파일
	 */
	@Override
	public int regFcImg(String nm_fc, byte[] fileData,String fileName) {
		return fincorpManager.regFcImg(nm_fc, fileData, fileName);
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
		return fincorpManager.listFincorpfcNminfo(fincorpfcNminfoForm);
	}
	/**
	 * 금융사명 KCB 사전연계 정보 삽입
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
	@Override
	public String createFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm) {
		// TODO Auto-generated method stub
		return fincorpManager.createFincorpfcNminfo(fincorpfcNminfoForm);
	}
	/**
	 * 금융사명 KCB 사전연계 정보 수정
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
	@Override
	public String updFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm) {
		// TODO Auto-generated method stub
		return fincorpManager.updFincorpfcNminfo(fincorpfcNminfoForm);
	}
	/**
	 * 금융사명 KCB 사전연계 정보 삭제
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
	@Override
	public String delFincorpfcNminfo(FincorpfcNminfoForm fincorpfcNminfoForm) {
		// TODO Auto-generated method stub
		return fincorpManager.delFincorpfcNminfo(fincorpfcNminfoForm);
	}
    /**
     * 금융사명 KCB 사전연계 정보 삭제
     * @param vo
     * @return String
     */
    @Override
    public List<HashMap> tempQuery(FincorpVO vo) {
        // TODO Auto-generated method stub
        return fincorpManager.tempQuery(vo);
    }
	/**
	 * @param fincorpVO
	 * @return String
	 */
	@Override
	public void uploadFile(FincorpVO fincorpVO) {
        MultipartFile file1 = null;
		FileOutputStream fos = null;
		byte[] fileArray = null;
        try {
			if (fincorpVO != null) {
				file1 = fincorpVO.getFile1();
				String file_name = fincorpVO.getFile_name();
				File file = new File(file_name);
				LogUtil.debugLn(logger,"존재하는지 file="+file);
				if(file.exists() == true){
                    file.delete();
                }
				file = new File(file_name);
				LogUtil.debugLn(logger,"새로 만든 file="+file);
				if (file1 != null) {
					fileArray = file1.getBytes();
					LogUtil.debugLn(logger,"저장할 fileArray="+fileArray);
					if (fileArray != null) {
						LogUtil.debugLn(logger,"저장할 fileArray.length="+fileArray.length);
					}
				}
				if (file != null) {
					fos = new FileOutputStream(file);
				}
				fos.write(fileArray);
			}
//                org.apache.commons.io.FileUtils.writeByteArrayToFile(file,fileArray);
		} catch (IOException e) {
			LogUtil.error(logger,e);
		} finally {
			if (fos != null) {
				try {
					fos.flush();
				} catch (IOException e) {
					LogUtil.error(logger,e);
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					LogUtil.error(logger,e);
				}
			}
		}
	}

	@Override
	public ReturnClass chkCdfc(FincorpVO fincorpVO) {
		return fincorpManager.chkCdfc(fincorpVO);
	}
	
    /**
 	 * COOCON 금융사 코드(스크래핑 대상) 
	 * @param 
	 * @return List<String>
     */
    @Override
    public List<String> getCooconFcCd(String type_fc) {
        // TODO Auto-generated method stub
        return fincorpManager.getCooconFcCd(type_fc);
    }

}
