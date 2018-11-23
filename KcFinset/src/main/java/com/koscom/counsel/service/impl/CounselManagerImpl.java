package com.koscom.counsel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.counsel.dao.CounselMapper;
import com.koscom.counsel.model.CounselForm;
import com.koscom.counsel.model.CounselVO;
import com.koscom.counsel.service.CounselManager;
import com.koscom.prepare.model.PrepareVO;
import com.koscom.prepare.service.PrepareManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;

@Service("counselManager")
public class CounselManagerImpl implements CounselManager {

	@Autowired
	private CounselMapper counselMapper;
	
	@Autowired
	private PrepareManager prepareManager;
	

	@Override
	public List<CounselVO> listCounselInfoPg(CounselForm counselForm) {
		return counselMapper.listCounselInfoPg(counselForm);
	}

	@Override
	public int listCounselCount(CounselForm counselForm) {
		return counselMapper.listCounselCount(counselForm);
	}

	@Override
	public CounselVO getCounselInfo(CounselVO counselVO) {
		return counselMapper.getCounselInfo(counselVO);
	}

	@Override
	public List<CounselVO> getCreditList(CounselVO counselVO) {
		return counselMapper.getCreditList(counselVO);
	}

	@Override
	public List<CounselVO> getDebtList(CounselVO counselVO) {
		return counselMapper.getDebtList(counselVO);
	}

	@Override
	public List<CounselVO> getCounselHist(CounselVO counselVO) {
		return counselMapper.getCounselHist(counselVO);
	}

	@Override
	public ReturnClass saveCounselStatus(CounselVO counselVO) {

		if (1 != counselMapper.saveCounselStatus(counselVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public ReturnClass saveCounselContents(CounselVO counselVO) {

		if (1 != counselMapper.saveCounselContents(counselVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}


	@Override
	public ReturnClass procCounselInfo(CounselVO counselVO) {
		if (StringUtil.isEmpty(counselVO.getNo_person())) {
			return new ReturnClass(Constant.FAILED, "등록되지 않은 고객입니다.");
		}

		if (1 != counselMapper.procCounselInfo(counselVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		
		// 기표변경 메모가 아니고 사전접수 번호가 있을때 상담메모 마지막 메모로 저장
		if( StringUtil.isNotEmpty(counselVO.getNo_prepare())
				&& !CounselVO.CD_COUNSEL_CLASS_20.equals(counselVO.getCd_counsel_class()) ) {
			
			String memo = counselVO.getEtc_counsel();
			
			// 메모길이가 너무 길면 apply_info DB에 들어가지 않음 (DB는 한글이 3바이트)
			if(memo.length() > 300) {
				memo = memo.substring(0, 300) + "...";
			}
			
			PrepareVO prepareVO = new PrepareVO();
			prepareVO.setNo_prepare(counselVO.getNo_prepare());
			prepareVO.setMemo_from_counsel(memo);
			prepareManager.updateCounselMemo(prepareVO);
		}
		
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	
	@Override
	public List<CounselVO> counselInfoList(CounselForm counselForm) {
		return counselMapper.counselInfoList(counselForm);
	}
}
