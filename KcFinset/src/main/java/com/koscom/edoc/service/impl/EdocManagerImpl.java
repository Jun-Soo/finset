package com.koscom.edoc.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.koscom.edoc.dao.EdocMapper;
import com.koscom.edoc.model.EdocForm;
import com.koscom.edoc.model.EdocInfo;
import com.koscom.edoc.model.EdocVO;
import com.koscom.edoc.service.EdocManager;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("edocManager")
public class EdocManagerImpl implements EdocManager {

	private static final Logger logger = LoggerFactory.getLogger(EdocManagerImpl.class);

	@Autowired
	private EdocMapper edocMapper;

	@Override
	@Cacheable(value="CacheEdoc" , key="#edocInfo.seq_edoc")
	public EdocInfo getEdocInfo(EdocInfo edocInfo) {
		return edocMapper.getEdocInfo(edocInfo);
	}

	/**
	 * Name   :getEdocDetail
	 * Desc   : 금융사전문정보 조회
	 * input  : EdocForm
	 * output : EdocVO
	 * Date   : 2017.09.15
	**/
	@Override
	public EdocVO getEdocDetail(EdocForm edocForm) {
		return edocMapper.getEdocDetail(edocForm);
	}

	@Override
	@Cacheable("CacheEdoc")
	public EdocInfo getEdocInfo(String seq_edoc) {
		EdocInfo edocInfo = new EdocInfo();
		edocInfo.setSeq_edoc(seq_edoc);
		return edocMapper.getEdocInfo(edocInfo);
	}
	@Override
	public EdocInfo getEdoc(EdocForm fcCodeForm) {
		logger.debug(fcCodeForm.toString());
		EdocInfo edocInfo = null;
		EdocInfo resultEdocInfo = null;
		if (fcCodeForm != null) {
			edocInfo = new EdocInfo();
			if(StringUtil.isEmpty(fcCodeForm.getSeq_edoc()))
                return edocInfo;
			edocInfo.setSeq_edoc(fcCodeForm.getSeq_edoc());
			resultEdocInfo = edocMapper.getEdocInfo(edocInfo);
		}
		return resultEdocInfo;
	}

	@Override
	@Cacheable(value="CacheListEdoc")
	public List<EdocInfo> listEdocInfo(EdocInfo edocInfo) {
		return edocMapper.listEdocInfo(edocInfo);
	}
	@Override
	public List<EdocVO> listEdoc(EdocForm fcCodeForm) {
		return edocMapper.listEdoc(fcCodeForm);
	}

	@Override
	public ReturnClass procEdocInfo(EdocVO fcCodeVO) {
		if(StringUtil.isEmpty(fcCodeVO.getCd_fc())) {
			return new ReturnClass(Constant.FAILED, "코드그룹이 입력되지 않았습니다.");
		}
		if(StringUtil.isEmpty(fcCodeVO.getNo_edoc())) {
			return new ReturnClass(Constant.FAILED, "전문번호가 입력되지 않았습니다.");
		}
		if (1 != edocMapper.procEdocInfo(fcCodeVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}
	@Override
	public ReturnClass delEdocInfo(EdocVO codeVO) {

		if(StringUtil.isEmpty(codeVO.getSeq_edoc())) {
			return new ReturnClass(Constant.FAILED, "삭제할 전문이 선택되지 않았습니다.");
		}
		if (1 != edocMapper.delEdocInfo(codeVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	@CacheEvict(value={"CacheEdoc","CacheListEdoc"}, allEntries=true)
	public ReturnClass clearCacheEdoc() {
		logger.info("Cache clear : [CacheEdoc,CacheListEdoc]");
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public EdocVO getMaxEdocInfo(EdocVO codeVO) {
		return edocMapper.getMaxEdocInfo(codeVO);
	}
	@Override
	public ReturnClass modifyListSeq(List<EdocVO> codeVO) {
		for (EdocVO cv : codeVO) {
			if (1 != edocMapper.modifyListSeq(cv)) {
				return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
			}
		}
		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public List<String> listNmEdoc(EdocForm edocForm) throws Exception {
		List<String> res = edocMapper.listNmEdoc(edocForm);
		return res;
	}
	@Override
	public String getNmEdoc(EdocForm edocForm) {
		return edocMapper.getNmEdoc(edocForm);
	}
	@Override
	public int getEdocCnt(EdocVO edocVO) {
		return edocMapper.getEdocCnt(edocVO);
	}
}
