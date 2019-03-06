package com.koscom.assets.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.assets.model.AssetsForm;
import com.koscom.assets.model.AssetsInfoVO;
import com.koscom.assets.service.AssetsManager;
import com.koscom.assets.service.AssetsWebManager;
import com.koscom.util.ReturnClass;


@Service("assetsWebManager")
public class AssetsWebManagerImpl implements AssetsWebManager{

	private static final Logger logger = LoggerFactory.getLogger(AssetsWebManagerImpl.class);

	@Autowired
	private AssetsManager assetsManager;

}
