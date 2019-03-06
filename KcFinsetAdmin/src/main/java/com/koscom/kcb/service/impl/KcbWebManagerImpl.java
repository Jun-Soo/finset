package com.koscom.kcb.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import com.koscom.util.FinsetException;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.domain.CreditInfo;
import com.koscom.kcb.model.AbstractKcbInfo;
import com.koscom.kcb.model.KcbCreditInfoVO;
import com.koscom.kcb.model.KcbReqNonfiInfoVO;
import com.koscom.kcb.service.KcbManager;
import com.koscom.kcb.service.KcbWebManager;
import com.koscom.util.ReturnClass;

@Service("kcbWebManager")
public class KcbWebManagerImpl implements KcbWebManager {
	@Autowired
	private KcbManager kcbManager;
	@Override
	public List<CreditInfo> listKcbCredit(CreditInfo info) {
		return kcbManager.listKcbCredit(info);
	}
	@Override
	public AbstractKcbInfo viewKcbCreditInfo(KcbCreditInfoVO infoVO) throws UnsupportedEncodingException,FinsetException {
		return kcbManager.viewKcbCreditInfo(infoVO);
	}
	@Override
	public ReturnClass procKcbCb(KcbCreditInfoVO infoVO) throws UnsupportedEncodingException,FinsetException,IOException {
		return kcbManager.procKcbCb(infoVO);
	}
	@Override
	public ReturnClass getKcbCbInfo(KcbCreditInfoVO infoVO) throws UnsupportedEncodingException,FinsetException,IOException {
		return kcbManager.getKcbCbInfo(infoVO);
	}
	@Override
	public ReturnClass parseCrawling(KcbCreditInfoVO info) throws FinsetException {
		return kcbManager.parseCrawling(info);
	}
	@Override
	public ReturnClass deleteKcbCb(String noPerson) {
		return kcbManager.deleteKcbCb(noPerson);
	}
	@Override
	public ReturnClass createKcbReqNonfiInfo(KcbReqNonfiInfoVO kcbReqNonfiInfoVO) {
		return kcbManager.createKcbReqNonfiInfo(kcbReqNonfiInfoVO);
	}
	@Override
	public List<KcbReqNonfiInfoVO> getKcbReqNonfiInfo(KcbReqNonfiInfoVO kcbReqNonfiInfoVO) {
		return kcbManager.getKcbReqNonfiInfo(kcbReqNonfiInfoVO);
	}
	@Override
	public int updateKcbReqNonfiInfo(KcbReqNonfiInfoVO kcbReqNonfiInfoVO) {
		return kcbManager.updateKcbReqNonfiInfo(kcbReqNonfiInfoVO);
	}
}