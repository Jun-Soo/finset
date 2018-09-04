package com.koscom.attach.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.attach.service.AttachManager;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.util.StringUtil;

@Service("attachManager")
public class AttachManagerImpl implements AttachManager {

	@Autowired
	private FincorpManager fincorpManager;
	
	@Override
	public byte[] getBytesAttachFileC(String filename) throws Exception {
        FincorpVO vo = new FincorpVO();
        vo.setPath_file1(filename);
		byte readByte[] = null;
		if( !StringUtil.isEmpty(filename) ){
			readByte = fincorpManager.getImgBi(filename);
		}else{
			return null;
		}
		return readByte;
	}

}
