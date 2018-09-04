package com.koscom.fincorp.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.fincorp.dao.FincorpMapper;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.service.FincorpManager;

@Service("fincorpManager")
public class FincorpManagerImpl implements FincorpManager{
	
	@Autowired
	private FincorpMapper fincorpMapper;
	
	@Override
	public String getNmFc(String cd_fc) {
		return fincorpMapper.getNmFc(cd_fc);
	}
	
	@Override
	public String getFcPathFile(String cd_fc) {
		return fincorpMapper.getFcPathFile(cd_fc);
	}
	
	@Override
	public byte[] getImgBi(String path_file1) throws Exception {
        FincorpVO vo = new FincorpVO();
        vo.setPath_file1(path_file1);
	    @SuppressWarnings("rawtypes")
		Map resMap = fincorpMapper.getImgBi(vo);
        byte[] imgBi = (resMap != null)?(byte[])resMap.get("img_bi"):null;
		return imgBi;
	}
}
