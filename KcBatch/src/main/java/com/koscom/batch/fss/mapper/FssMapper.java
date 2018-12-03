package com.koscom.batch.fss.mapper;


import com.koscom.batch.fss.domain.FssCompanyOptionVO;
import com.koscom.batch.fss.domain.FssCompanyProductVO;
import com.koscom.batch.fss.domain.FssCompanyResultVO;
import com.koscom.batch.fss.domain.FssCreditLoanOptionVO;
import com.koscom.batch.fss.domain.FssCreditLoanProductVO;
import com.koscom.batch.fss.domain.FssCreditLoanResultVO;
import com.koscom.batch.fss.domain.FssMortgageLoanOptionVO;
import com.koscom.batch.fss.domain.FssMortgageLoanProductVO;
import com.koscom.batch.fss.domain.FssMortgageLoanResultVO;
import com.koscom.batch.fss.domain.FssRentHouseLoanOptionVO;
import com.koscom.batch.fss.domain.FssRentHouseLoanProductVO;
import com.koscom.batch.fss.domain.FssRentHouseLoanResultVO;

public interface FssMapper {
	int createFssCompanyResultInfo(FssCompanyResultVO fssCompanyResultVO);
	int mergeFssCompanyProductInfo(FssCompanyProductVO fssCompanyProductVO);
	int mergeFssCompanyOptionInfo(FssCompanyOptionVO fssCompanyOptionVO);

	int createFssMortgageLoanResultInfo(FssMortgageLoanResultVO fssMortgageLoanResultVO);
	int mergeFssMortgageLoanProductInfo(FssMortgageLoanProductVO fssMortgageLoanProductVO);
	int mergeFssMortgageLoanOptionInfo(FssMortgageLoanOptionVO fssMortgageLoanOptionVO);

	int createFssRentHouseLoanResultInfo(FssRentHouseLoanResultVO fssRentHouseLoanResultVO);
	int mergeFssRentHouseLoanProductInfo(FssRentHouseLoanProductVO fssRentHouseLoanProductVO);
	int mergeFssRentHouseLoanOptionInfo(FssRentHouseLoanOptionVO fssRentHouseLoanOptionVO);

	int createFssCreditLoanResultInfo(FssCreditLoanResultVO fssCreditLoanResultVO);
	int mergeFssCreditLoanProductInfo(FssCreditLoanProductVO fssCreditLoanProductVO);
	int mergeFssCreditLoanOptionInfo(FssCreditLoanOptionVO fssCreditLoanOptionVO);

}