package com.koscom.fss.service;

import java.util.List;

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
import com.koscom.util.ReturnClass;

public interface FssManager {
	/*
	public void createFssCompanyApi();
	*/
	public ReturnClass delFssCompanyProductInfo();
	public ReturnClass delFssCompanyResultInfo();
	public ReturnClass delFssCompanyOptionInfo();
	public ReturnClass createFssCompanyProductInfo(List<FssCompanyProductVO> list);
	public ReturnClass createFssCompanyResultInfo(FssCompanyResultVO list);
	public ReturnClass createFssCompanyOptionInfo(List<FssCompanyOptionVO> list);
	
	/*
	public void createFssCreditLoanApi();
	*/
	public ReturnClass delFssCreditLoanProductInfo();
	public ReturnClass delFssCreditLoanResultInfo();
	public ReturnClass delFssCreditLoanOptionInfo();
	public ReturnClass createFssCreditLoanProductInfo(List<FssCreditLoanProductVO> list);
	public ReturnClass createFssCreditLoanResultInfo(FssCreditLoanResultVO list);
	public ReturnClass createFssCreditLoanOptionInfo(List<FssCreditLoanOptionVO> list);

	/*
	public void createFssRentHouseLoanApi();
	*/
	public ReturnClass delFssRentHouseLoanProductInfo();
	public ReturnClass delFssRentHouseLoanResultInfo();
	public ReturnClass delFssRentHouseLoanOptionInfo();
	public ReturnClass createFssRentHouseLoanProductInfo(List<FssRentHouseLoanProductVO> list);
	public ReturnClass createFssRentHouseLoanResultInfo(FssRentHouseLoanResultVO list);
	public ReturnClass createFssRentHouseLoanOptionInfo(List<FssRentHouseLoanOptionVO> list);

	/*
	public void createFssMortgageLoanApi();
	*/
	public ReturnClass delFssMortgageLoanProductInfo();
	public ReturnClass delFssMortgageLoanResultInfo();
	public ReturnClass delFssMortgageLoanOptionInfo();
	public ReturnClass createFssMortgageLoanProductInfo(List<FssMortgageLoanProductVO> list);
	public ReturnClass createFssMortgageLoanResultInfo(FssMortgageLoanResultVO list);
	public ReturnClass createFssMortgageLoanOptionInfo(List<FssMortgageLoanOptionVO> list);
	
}