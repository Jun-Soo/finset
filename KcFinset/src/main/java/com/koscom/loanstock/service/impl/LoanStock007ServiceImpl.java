package com.koscom.loanstock.service.impl;

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
import com.koscom.loanstock.model.LoanStock007InVO;
import com.koscom.loanstock.model.LoanStock007OutVO;
import com.koscom.loanstock.service.LoanStock007Service;

/**
 * FINSET - 상품 조회/조회결과 상세 비즈니스 구현 클래스
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
@Service("LoanStock007Service")
public class LoanStock007ServiceImpl extends FulltextService implements LoanStock007Service {
    @Resource(name = "LoanStock007DAO")
    private LoanStock007DAO loanStock007DAO;

	private static final Logger logger = LoggerFactory.getLogger(LoanStock007ServiceImpl.class);
	
	private static final String title = "상품 조회/조회결과 상세";
	private static final FulltextField [] inFields = {
		new FulltextField("증권사코드", "MbrCd", 4, FulltextField.FieldType.CHAR, false),
		new FulltextField("여신사코드", "CrdtIttCd", 4, FulltextField.FieldType.CHAR, false),
		new FulltextField("상품코드", "PrdtCd", 12, FulltextField.FieldType.CHAR, false)
	};
	
	private static final FulltextField [] inGridFields = {	
	};

	private static final FulltextField [] outFields = {
		new FulltextField("상품명", "PrdtNm", 40, FulltextField.FieldType.CHAR, false),
		new FulltextField("여신사명", "CrdtIttNm", 40, FulltextField.FieldType.CHAR, false),
		new FulltextField("증권사명", "MbrNm", 40, FulltextField.FieldType.CHAR, false),
		new FulltextField("투자유형", "InvstPlanTp", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("마이너스구분", "MinusTp", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("등록일자", "RegDt", 8, FulltextField.FieldType.CHAR, false),
		new FulltextField("상품개시일자", "PrdtStrDt", 8, FulltextField.FieldType.CHAR, false),
		new FulltextField("상품만기일자", "PrdtEndDt", 8, FulltextField.FieldType.CHAR, false),
		new FulltextField("대출기간", "LoanTerm", 5, FulltextField.FieldType.CHAR, false),
		new FulltextField("상환방식", "RfundMthd", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("평균금리", "AvgIntrstRt", 12, FulltextField.FieldType.FLOAT, false),
		new FulltextField("취급금리", "HndIntrstRtt", 12, FulltextField.FieldType.FLOAT, false),
		new FulltextField("대출금리", "LoanIntrstRt", 12, FulltextField.FieldType.FLOAT, false),
		new FulltextField("연체이자율", "OvdIntrstRt", 12, FulltextField.FieldType.FLOAT, false),
		new FulltextField("연장금리", "ExtndIntrstRt", 12, FulltextField.FieldType.FLOAT, false),
		new FulltextField("중도상환율", "RfundRat", 12, FulltextField.FieldType.FLOAT, false),
		new FulltextField("인지세", "Stmptax", 16, FulltextField.FieldType.NUMBER, false),
		new FulltextField("한종목집중율", "IsuCmpstRat", 3, FulltextField.FieldType.CHAR, false),
		new FulltextField("금리구분", "IntrstRatTp", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("대출최소한도", "LoanMinLmtAmt", 16, FulltextField.FieldType.NUMBER, false),
		new FulltextField("대출최대한도", "LoanMaxLmtAmt", 16, FulltextField.FieldType.NUMBER, false),
		new FulltextField("중개료구분", "IntrmdFeeTp", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("중개요율", "IntrmdFeeRt", 12, FulltextField.FieldType.FLOAT, false),
		new FulltextField("중개요금", "IntrmdFee", 16, FulltextField.FieldType.NUMBER, false),
		new FulltextField("한족목투자한도", "IsuInvstLmt", 16, FulltextField.FieldType.NUMBER, false),
		new FulltextField("로스컷비율", "LscutSetRt", 4, FulltextField.FieldType.NUMBER, false),
		new FulltextField("현금인출비율", "MnyOutAblRt", 4, FulltextField.FieldType.NUMBER, false),
		new FulltextField("만기연장비율", "ExpExtndAblRt", 4, FulltextField.FieldType.NUMBER, false)
	};
	
	private static final FulltextField [] outGridFields = {
	};

	/**
	 * 상품 조회/조회결과 상세정보 VO를 이용해서 RMS 전송 전문을 생성하는 함수
	 * @param		상품 조회/조회결과 상세정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	public String CreateFulltext(final LoanStock007InVO vo) throws FulltextException {
		String fulltext = CreateFulltext(title, vo, inFields, inGridFields);
		
		return fulltext;
	}
	
	/**
	 * 상품 조회/조회결과 상세정보 VO를 이용해서 RMS 전송 전문을 생성하는 함수
	 * @param		상품 조회/조회결과 상세정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	public String CreateOutFulltext(final LoanStock007OutVO vo) throws FulltextException {
		String fulltext = CreateFulltext(title, vo, outFields, outGridFields);
		
		return fulltext;
	}
	
	/**
	 * RMS 전송 전문을 이용해서 상품 조회/조회결과 상세정보 VO를 생성하는 함수
	 * @param		상품 조회/조회결과 상세정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	public LoanStock007OutVO CreateOutVO(String fulltext) throws FulltextException {
		String outJson = CreateOutJson(title, fulltext, outFields, outGridFields);
		logger.debug("outJson : {}", outJson);
		
		Gson gson = new Gson();
		LoanStock007OutVO vo = gson.fromJson(outJson, LoanStock007OutVO.class);
		
		return vo;
	}
	
	/**
	 * 상품 조회/조회결과 상세정보를 RESTfulAPI 서버에 전송하는 함수
	 * @param		상품 조회/조회결과 상세정보 VO
	 * @return		전송결과 VO
	 * @exception 	FulltextException
	 */	
	public FulltextResultVO Send(final LoanStock007InVO vo) throws FulltextException {
		String fulltext = CreateFulltext(vo);
		FulltextHeaderVO headerVO = new FulltextHeaderVO();
		
		/**
		 * TODO [김학진] 해더에 넣을 값을 모르겠음. 확인요망
		 */
		//headerVO.setSndIttCd(vo.getMbrCd());
		headerVO.setRcvIttCd(vo.getMbrCd());
		
		FulltextResultVO resultVO = loanStock007DAO.send(headerVO, fulltext);
		return resultVO;
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
