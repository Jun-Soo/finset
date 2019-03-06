package com.koscom.fss.dao;


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

public interface FssMapper {
	int createFssCompanyResultInfo(FssCompanyResultVO fssCompanyResultVO);
	int createFssCompanyProductInfo(FssCompanyProductVO fssCompanyProductVO);
	int createFssCompanyOptionInfo(FssCompanyOptionVO fssCompanyOptionVO);
	int delFssCompanyResultInfo();
	int delFssCompanyProductInfo();
	int delFssCompanyOptionInfo();
	
	int createFssCreditLoanResultInfo(FssCreditLoanResultVO fssCreditLoanResultVO);
	int createFssCreditLoanProductInfo(FssCreditLoanProductVO fssCreditLoanProductVO);
	int createFssCreditLoanOptionInfo(FssCreditLoanOptionVO fssCreditLoanOptionVO);
	int delFssCreditLoanResultInfo();
	int delFssCreditLoanProductInfo();
	int delFssCreditLoanOptionInfo();

	int createFssRentHouseLoanResultInfo(FssRentHouseLoanResultVO fssRentHouseLoanResultVO);
	int createFssRentHouseLoanProductInfo(FssRentHouseLoanProductVO fssRentHouseLoanProductVO);
	int createFssRentHouseLoanOptionInfo(FssRentHouseLoanOptionVO fssRentHouseLoanOptionVO);
	int delFssRentHouseLoanResultInfo();
	int delFssRentHouseLoanProductInfo();
	int delFssRentHouseLoanOptionInfo();

	int createFssMortgageLoanResultInfo(FssMortgageLoanResultVO fssMortgageLoanResultVO);
	int createFssMortgageLoanProductInfo(FssMortgageLoanProductVO fssMortgageLoanProductVO);
	int createFssMortgageLoanOptionInfo(FssMortgageLoanOptionVO fssMortgageLoanOptionVO);
	int delFssMortgageLoanResultInfo();
	int delFssMortgageLoanProductInfo();
	int delFssMortgageLoanOptionInfo();
}