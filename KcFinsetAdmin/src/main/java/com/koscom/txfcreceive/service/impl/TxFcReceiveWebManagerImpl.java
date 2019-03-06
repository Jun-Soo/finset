package com.koscom.txfcreceive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.txfcreceive.model.TxFcReceiveForm;
import com.koscom.txfcreceive.model.TxFcReceiveVO;
import com.koscom.txfcreceive.service.TxFcReceiveManager;
import com.koscom.txfcreceive.service.TxFcReceiveWebManager;

@Service("txfcreceiveWebManager")
public class TxFcReceiveWebManagerImpl implements TxFcReceiveWebManager{
	
	@Autowired
	private TxFcReceiveManager txfcreceiveManager;
	
	@Override
	public List<TxFcReceiveVO> listTxFcReceiveDetail(TxFcReceiveVO txfcreceiveVO) {
		return txfcreceiveManager.listTxFcReceiveDetail(txfcreceiveVO);
	}

	@Override
	public List<TxFcReceiveVO> listTxFcReceiveDetailPg(TxFcReceiveForm txfcreceiveForm) {
		return txfcreceiveManager.listTxFcReceiveDetailPg(txfcreceiveForm);
	}

	@Override
	public int listTxFcReceiveDetailCount(TxFcReceiveForm txfcreceiveForm) {
		return txfcreceiveManager.listTxFcReceiveDetailCount(txfcreceiveForm);
	}

	@Override
	public List<TxFcReceiveVO> listTxFcReceiveForCdGoods(String no_bunch) {
		// TODO Auto-generated method stub
		return txfcreceiveManager.listTxFcReceiveForCdGoods(no_bunch);
	}

}
