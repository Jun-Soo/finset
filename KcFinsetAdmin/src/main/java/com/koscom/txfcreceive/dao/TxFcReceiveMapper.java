package com.koscom.txfcreceive.dao;

import java.util.List;

import com.koscom.txfcreceive.model.TxFcReceiveForm;
import com.koscom.txfcreceive.model.TxFcReceiveVO;

public interface TxFcReceiveMapper {
	List<TxFcReceiveVO> listTxFcReceiveDetail(TxFcReceiveVO txfcreceiveVO);
	List<TxFcReceiveVO> listTxFcReceiveForCdGoods(String no_bunch);
	int listTxFcReceiveDetailCount(TxFcReceiveForm txfcreceiveForm);
	List<TxFcReceiveVO> listTxFcReceiveDetailPg(TxFcReceiveForm txfcreceiveForm);
}