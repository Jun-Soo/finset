package com.koscom.loanstock.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.koscom.common.fulltext.FulltextException;
import com.koscom.common.fulltext.FulltextField;
import com.koscom.common.fulltext.FulltextHeaderVO;
import com.koscom.common.fulltext.FulltextResultVO;
import com.koscom.common.fulltext.FulltextService;
import com.koscom.loanstock.model.LoanStock012InVO;
import com.koscom.loanstock.model.LoanStock012OutVO;
import com.koscom.loanstock.service.LoanStock012Service;

/**
 * 증권사BIZ - 스탁론상품 조회 비즈니스 구현 클래스
 * @author EndFoint 개발팀 김학진
 * @since 2018.08.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2018.08.01 김학진 최초 생성
 *  </pre>
*/
@Service("LoanStock012Service")
public class LoanStock012ServiceImpl extends FulltextService implements LoanStock012Service {
    @Resource(name = "LoanStock012DAO")
    private LoanStock012DAO loanStock012DAO;

	private static final Logger logger = LoggerFactory.getLogger(LoanStock012ServiceImpl.class);
	
	private static final String title = "스탁론상품 조회";
	private static final FulltextField [] inFields = {
		new FulltextField("증권사코드", "MbrCd", 4, FulltextField.FieldType.CHAR, true),
		new FulltextField("증권계좌번호", "AcntNo", 20, FulltextField.FieldType.CHAR, false)

	};
	
	private static final FulltextField [] inGridFields = {	
	};

	private static final FulltextField [] outFields = {
		new FulltextField("증권사코드", "MbrCd", 4, FulltextField.FieldType.CHAR, false),
		new FulltextField("증권사명", "MbrNm", 40, FulltextField.FieldType.CHAR, false),
		new FulltextField("그리드 카운트", "GridCnt", 3, FulltextField.FieldType.CHAR, false),
		new FulltextField("그리드 사이즈", "GridSz", 160, FulltextField.FieldType.CHAR, false)

	};
	
	private static final FulltextField [] outGridFields = {
		new FulltextField("여신사코드", "CrdtIttCd", 4, FulltextField.FieldType.CHAR, false),
		new FulltextField("여신사명", "CrdtIttNm", 40, FulltextField.FieldType.CHAR, false),
		new FulltextField("상품코드", "PrdtCd", 12, FulltextField.FieldType.CHAR, false),
		new FulltextField("상품명", "PrdtNm", 40, FulltextField.FieldType.CHAR, false),
		new FulltextField("투자유형", "InvstPlanTp", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("대출기간", "LoanTerm", 5, FulltextField.FieldType.CHAR, false),
		new FulltextField("상환방식", "RfundMthd", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("대출금리", "LoanIntrstRt", 12, FulltextField.FieldType.FLOAT, false),
		new FulltextField("연체이자율", "OvdIntrstRt", 12, FulltextField.FieldType.FLOAT, false),
		new FulltextField("대출최소한도", "LoanMinLmtAmt", 16, FulltextField.FieldType.NUMBER, false),
		new FulltextField("대출최대한도", "LoanMaxLmtAmt", 16, FulltextField.FieldType.NUMBER, false),
		new FulltextField("대출가능여부", "LoanAblYn", 1, FulltextField.FieldType.CHAR, false),

	};

	/**
	 * 스탁론상품 조회정보 VO를 이용해서 RMS 전송 전문을 생성하는 함수
	 * @param		스탁론상품 조회정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	public String CreateFulltext(final LoanStock012InVO vo) throws FulltextException {
		String fulltext = CreateFulltext(title, vo, inFields, inGridFields);
		
		return fulltext;
	}
	
	/**
	 * 스탁론상품 조회정보 VO를 이용해서 RMS 전송 전문을 생성하는 함수
	 * @param		스탁론상품 조회정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	public String CreateOutFulltext(final LoanStock012OutVO vo) throws FulltextException {
		String fulltext = CreateFulltext(title, vo, outFields, outGridFields);
		
		return fulltext;
	}
	
	/**
	 * RMS 전송 전문을 이용해서 스탁론상품 조회정보 VO를 생성하는 함수
	 * @param		스탁론상품 조회정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	public LoanStock012OutVO CreateOutVO(String fulltext) throws FulltextException {
		String outJson = CreateOutJson(title, fulltext, outFields, outGridFields);
		logger.debug("outJson : {}", outJson);
		
		Gson gson = new Gson();
		LoanStock012OutVO vo = gson.fromJson(outJson, LoanStock012OutVO.class);
		
		return vo;
	}
	
	/**
	 * 스탁론상품 조회정보를 RESTfulAPI 서버에 전송하는 함수
	 * @param		스탁론상품 조회정보 VO
	 * @return		전송결과 VO
	 * @exception 	FulltextException
	 */	
	public List<FulltextResultVO> Send(final LoanStock012InVO vo) throws FulltextException {
		//List<String> sndIttCdList = vo.getSndIttCdList();
		List<LoanStock012InVO> inVOList = vo.getInVOList();
		List<FulltextResultVO> resultVOList = new ArrayList<FulltextResultVO>();
		for(LoanStock012InVO inVO : inVOList){
			
			String fulltext = CreateFulltext(inVO);
			FulltextHeaderVO headerVO = new FulltextHeaderVO();
			
			/**
			 * TODO [김학진] 해더에 넣을 값을 모르겠음. 확인요망
			 */
			//headerVO.setSndIttCd(inVO.getMbrCd());
			headerVO.setRcvIttCd(inVO.getMbrCd());
			
			FulltextResultVO resultVO = loanStock012DAO.send(headerVO, fulltext, false);
			resultVOList.add(resultVO);
		}
		
		return resultVOList;
	}
	
	/**
	 * IN 필드의 정의 정보를 리턴하는 함수
	 * @return		IN 필드의 정의 정보
	 * @exception 	FulltextException
	 */	
	public String getInFieldInfo() throws FulltextException {
		Gson gson = new Gson();		
		
		return gson.toJson(inFields);
	}
}
