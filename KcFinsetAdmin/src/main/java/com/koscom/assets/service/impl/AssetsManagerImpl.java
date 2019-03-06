package com.koscom.assets.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.assets.dao.AssetsMapper;
import com.koscom.assets.model.AssetsForm;
import com.koscom.assets.model.AssetsInfoVO;
import com.koscom.assets.service.AssetsManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Service("assetsManager")
public class AssetsManagerImpl implements AssetsManager {

	private static final Logger logger = LoggerFactory.getLogger(AssetsManagerImpl.class);


	@Autowired
	private AssetsMapper assetsMapper;

//	@Override
//	public ReturnClass createAssetsInfo(AssetsInfoVO assetsInfoVO) {
//
//		if(1 != assetsMapper.createAssetsInfo(assetsInfoVO)){
//			new ReturnClass(Constant.FAILED, "처리 실패하였습니다.");
//		}
//
//		return new ReturnClass(Constant.SUCCESS, "처리 성공하였습니다");
//	}

}
