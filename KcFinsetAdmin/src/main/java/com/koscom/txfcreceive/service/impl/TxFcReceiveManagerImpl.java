package com.koscom.txfcreceive.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.txfcreceive.dao.TxFcReceiveMapper;
import com.koscom.txfcreceive.model.TxFcReceiveForm;
import com.koscom.txfcreceive.model.TxFcReceiveVO;
import com.koscom.txfcreceive.service.TxFcReceiveManager;

@Service("txfcreceiveManager")
public class TxFcReceiveManagerImpl implements TxFcReceiveManager {

	private static final Logger logger = LoggerFactory.getLogger(TxFcReceiveManagerImpl.class);
	
	@Autowired
	private TxFcReceiveMapper txfcreceiveMapper;
	
	@Override
	public List<TxFcReceiveVO> listTxFcReceiveDetail(TxFcReceiveVO txfcreceiveVO) {
		return txfcreceiveMapper.listTxFcReceiveDetail(txfcreceiveVO);
	}
	@Override
	public List<TxFcReceiveVO> listTxFcReceiveForCdGoods(String no_bunch) {
		return txfcreceiveMapper.listTxFcReceiveForCdGoods(no_bunch);
	}
	@Override
	public List<TxFcReceiveVO> listTxFcReceiveDetailPg(TxFcReceiveForm txfcreceiveForm) {
		return txfcreceiveMapper.listTxFcReceiveDetailPg(txfcreceiveForm);
	}
	@Override
	public int listTxFcReceiveDetailCount(TxFcReceiveForm txfcreceiveForm) {
		return txfcreceiveMapper.listTxFcReceiveDetailCount(txfcreceiveForm);
	}
}
