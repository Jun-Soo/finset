package com.koscom.fss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.fss.model.FssCompanyOptionVO;
import com.koscom.fss.model.FssCompanyProductVO;
import com.koscom.fss.model.FssCompanyResultVO;
import com.koscom.fss.model.FssCreditLoanOptionVO;
import com.koscom.fss.model.FssCreditLoanProductVO;
import com.koscom.fss.model.FssCreditLoanResultVO;
import com.koscom.fss.model.FssMortgageLoanOptionVO;
import com.koscom.fss.model.FssMortgageLoanProductVO;
import com.koscom.fss.model.FssMortgageLoanResultVO;
import com.koscom.fss.model.FssRentHouseLoanOptionVO;
import com.koscom.fss.model.FssRentHouseLoanProductVO;
import com.koscom.fss.model.FssRentHouseLoanResultVO;
import com.koscom.fss.service.FssManager;
import com.koscom.fss.service.FssWebManager;
import com.koscom.util.ReturnClass;

@Service("fssWebManager")
public class FssWebManagerImpl implements FssWebManager {
	@Autowired
	private FssManager fssManager;
	/*
	@Override
	public void createFssCompanyApi() {
		fssManager.createFssCompanyApi();
	}
	*/
	@Override
	public ReturnClass delFssCompanyProductInfo() {
		return fssManager.delFssCompanyProductInfo();
	}
	@Override
	public ReturnClass delFssCompanyResultInfo() {
		return fssManager.delFssCompanyResultInfo();
	}
	@Override
	public ReturnClass delFssCompanyOptionInfo() {
		return fssManager.delFssCompanyOptionInfo();
	}
	@Override
	public ReturnClass createFssCompanyProductInfo(List<FssCompanyProductVO> list) {
		return fssManager.createFssCompanyProductInfo(list);
	}
	@Override
	public ReturnClass createFssCompanyResultInfo(FssCompanyResultVO list) {
		return fssManager.createFssCompanyResultInfo(list);
	}
	@Override
	public ReturnClass createFssCompanyOptionInfo(List<FssCompanyOptionVO> list) {
		return fssManager.createFssCompanyOptionInfo(list);
	}
	/*
	@Override
	public void createFssCreditLoanApi() {
		fssManager.createFssCreditLoanApi();
	}
	*/
	@Override
	public ReturnClass delFssCreditLoanProductInfo() {
		return fssManager.delFssCreditLoanProductInfo();
	}
	@Override
	public ReturnClass delFssCreditLoanResultInfo() {
		return fssManager.delFssCreditLoanResultInfo();
	}
	@Override
	public ReturnClass delFssCreditLoanOptionInfo() {
		return fssManager.delFssCreditLoanOptionInfo();
	}
	@Override
	public ReturnClass createFssCreditLoanProductInfo(List<FssCreditLoanProductVO> list) {
		return fssManager.createFssCreditLoanProductInfo(list);
	}
	@Override
	public ReturnClass createFssCreditLoanResultInfo(FssCreditLoanResultVO list) {
		return fssManager.createFssCreditLoanResultInfo(list);
	}
	@Override
	public ReturnClass createFssCreditLoanOptionInfo(List<FssCreditLoanOptionVO> list) {
		return fssManager.createFssCreditLoanOptionInfo(list);
	}
	/*
	@Override
	public void createFssRentHouseLoanApi() {
		fssManager.createFssRentHouseLoanApi();
	}
	*/
	@Override
	public ReturnClass delFssRentHouseLoanProductInfo() {
		return fssManager.delFssRentHouseLoanProductInfo();
	}
	@Override
	public ReturnClass delFssRentHouseLoanResultInfo() {
		return fssManager.delFssRentHouseLoanResultInfo();
	}
	@Override
	public ReturnClass delFssRentHouseLoanOptionInfo() {
		return fssManager.delFssRentHouseLoanOptionInfo();
	}
	@Override
	public ReturnClass createFssRentHouseLoanProductInfo(List<FssRentHouseLoanProductVO> list) {
		return fssManager.createFssRentHouseLoanProductInfo(list);
	}
	@Override
	public ReturnClass createFssRentHouseLoanResultInfo(FssRentHouseLoanResultVO list) {
		return fssManager.createFssRentHouseLoanResultInfo(list);
	}
	@Override
	public ReturnClass createFssRentHouseLoanOptionInfo(List<FssRentHouseLoanOptionVO> list) {
		return fssManager.createFssRentHouseLoanOptionInfo(list);
	}
	/*
	@Override
	public void createFssMortgageLoanApi() {
		fssManager.createFssMortgageLoanApi();
	}
	*/
	@Override
	public ReturnClass delFssMortgageLoanProductInfo() {
		return fssManager.delFssMortgageLoanProductInfo();
	}
	@Override
	public ReturnClass delFssMortgageLoanResultInfo() {
		return fssManager.delFssMortgageLoanResultInfo();
	}
	@Override
	public ReturnClass delFssMortgageLoanOptionInfo() {
		return fssManager.delFssMortgageLoanOptionInfo();
	}
	@Override
	public ReturnClass createFssMortgageLoanProductInfo(List<FssMortgageLoanProductVO> list) {
		return fssManager.createFssMortgageLoanProductInfo(list);
	}
	@Override
	public ReturnClass createFssMortgageLoanResultInfo(FssMortgageLoanResultVO list) {
		return fssManager.createFssMortgageLoanResultInfo(list);
	}
	@Override
	public ReturnClass createFssMortgageLoanOptionInfo(List<FssMortgageLoanOptionVO> list) {
		return fssManager.createFssMortgageLoanOptionInfo(list);
	}
}