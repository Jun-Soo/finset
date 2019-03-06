package com.koscom.txfcreceive.service;

import java.util.List;

import com.koscom.txfcreceive.model.TxFcReceiveForm;
import com.koscom.txfcreceive.model.TxFcReceiveVO;

public interface TxFcReceiveWebManager {
	List<TxFcReceiveVO> listTxFcReceiveDetail(TxFcReceiveVO txfcreceiveVO);
	List<TxFcReceiveVO> listTxFcReceiveForCdGoods(String no_bunch);

	List<TxFcReceiveVO> listTxFcReceiveDetailPg(TxFcReceiveForm txfcreceiveForm);
	int listTxFcReceiveDetailCount(TxFcReceiveForm txfcreceiveForm);
}
