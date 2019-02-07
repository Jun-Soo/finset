package com.koscom.st.debt018.service.impl;

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
import com.koscom.st.debt018.model.Debt018InVO;
import com.koscom.st.debt018.model.Debt018OutVO;
import com.koscom.st.debt018.service.Debt018Service;

/**
 * FINSET - 부채관리/부채상세 비즈니스 구현 클래스
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
@Service("Debt018Service")
public class Debt018ServiceImpl extends FulltextService implements Debt018Service {
    @Resource(name = "Debt018DAO")
    private Debt018DAO debt018DAO;

	private static final Logger logger = LoggerFactory.getLogger(Debt018ServiceImpl.class);
	
	private static final String title = "부채관리/부채상세";
	private static final FulltextField [] inFields = {
		new FulltextField("계좌번호", "AcntNo", 20, FulltextField.FieldType.CHAR, true),
		new FulltextField("연계계좌번호", "LnkAcntNo", 20, FulltextField.FieldType.CHAR, true),
		new FulltextField("nextkey", "NextKey", 64, FulltextField.FieldType.CHAR, false)
	};
	
	private static final FulltextField [] inGridFields = {	
	};

	private static final FulltextField [] outFields = {
		new FulltextField("대출일련번호", "LoanSeq", 18, FulltextField.FieldType.CHAR, false),
		new FulltextField("최종갱신일자", "lastUpdtDt", 8, FulltextField.FieldType.CHAR, false),
		new FulltextField("여신사코드", "CrdtIttCd", 4, FulltextField.FieldType.CHAR, false),
		new FulltextField("여신사명", "CrdtIttNm", 40, FulltextField.FieldType.CHAR, false),
		new FulltextField("증권사코드", "MbrCd", 4, FulltextField.FieldType.CHAR, false),
		new FulltextField("증권사명", "MbrNm", 40, FulltextField.FieldType.CHAR, false),
		new FulltextField("상품코드", "PrdtCd", 12, FulltextField.FieldType.CHAR, false),
		new FulltextField("상품명", "PrdtNm", 40, FulltextField.FieldType.CHAR, false),
		new FulltextField("핸드폰번호", "MobilNo", 16, FulltextField.FieldType.CHAR, false),
		new FulltextField("SMS통지여부", "SmsNotiYn", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("계좌활동구분", "AcntActvTp", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("등록일자", "RegDt", 8, FulltextField.FieldType.CHAR, false),
		new FulltextField("폐쇄일자", "CloseDt", 8, FulltextField.FieldType.CHAR, false),
		new FulltextField("계좌상태코드", "AcntStatCd", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("로스컷상태", "LscutStat", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("연체발생일", "OvdOcrDt", 8, FulltextField.FieldType.CHAR, false),
		new FulltextField("거래제한유형코드", "TrdRestrcPtnCode", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("대출구분", "LoanTp", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("대출시작일자", "LoanSrtDt", 8, FulltextField.FieldType.CHAR, false),
		new FulltextField("대출종료일자", "LoadEndDt", 8, FulltextField.FieldType.CHAR, false),
		new FulltextField("만기연장가능여부", "ExpExtndAblTp", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("로스컷설정비율", "LscutSetRt", 4, FulltextField.FieldType.NUMBER, false),
		new FulltextField("출금가능비율", "MnyOutAblRt", 4, FulltextField.FieldType.NUMBER, false),
		new FulltextField("만기연장가능비율", "ExpExtndAblRt", 4, FulltextField.FieldType.NUMBER, false),
		new FulltextField("이자출금가능비율", "IntrstOutAblRt", 4, FulltextField.FieldType.NUMBER, false),
		new FulltextField("계좌재개비율", "AcntResumRt", 4, FulltextField.FieldType.NUMBER, false),
		new FulltextField("포트폴리오비율", "PtflRt", 4, FulltextField.FieldType.NUMBER, false),
		new FulltextField("대출한도", "LoanLmtAmt", 16, FulltextField.FieldType.NUMBER, false),
		new FulltextField("대출금액", "LoanAmt", 16, FulltextField.FieldType.NUMBER, false),
		new FulltextField("누적대출금액", "AcmLoanAmt", 16, FulltextField.FieldType.NUMBER, false),
		new FulltextField("추가대출가능금액", "AddLoanAbleAmt", 16, FulltextField.FieldType.NUMBER, false),
		new FulltextField("재매매가능입금액", "OrdAblMnyInAmt", 16, FulltextField.FieldType.NUMBER, false),
		new FulltextField("로스컷체크일시", "LscutChkTm", 20, FulltextField.FieldType.CHAR, false),
		new FulltextField("로스컷발생일", "LscutOcrDttm", 8, FulltextField.FieldType.CHAR, false),
		
		new FulltextField("로스컷주문일", "LscutOrdDttm", 8, FulltextField.FieldType.CHAR, false),
		new FulltextField("테스트계좌구분", "TstAcntTp", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("강제로스컷구분", "FrcLscutTp", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("강제로스컷사유", "FrcLscutRsn", 80, FulltextField.FieldType.CHAR, false),
		new FulltextField("인출가능금액", "MnyoutAbleAmt", 16, FulltextField.FieldType.CHAR, false),
		new FulltextField("금리", "LoanIntrstRt", 12, FulltextField.FieldType.CHAR, false),
		new FulltextField("상환방식", "RfundMthd", 1, FulltextField.FieldType.CHAR, false),
		new FulltextField("잔여기간", "TermDays", 5, FulltextField.FieldType.CHAR, false),
		new FulltextField("동일종목투자", "IsuInvstLmt", 16, FulltextField.FieldType.CHAR, false),
		new FulltextField("총평가금", "TotEvalAmt", 16, FulltextField.FieldType.CHAR, false),
		new FulltextField("주식평가금", "AssetEvalAmt", 16, FulltextField.FieldType.CHAR, false),
		new FulltextField("예수금", "Dps", 16, FulltextField.FieldType.CHAR, false),
		new FulltextField("수익률", "Ernrat", 12, FulltextField.FieldType.FLOAT, false),
		new FulltextField("수익금액", "ErnAmt", 16, FulltextField.FieldType.CHAR, false),
		new FulltextField("nextkey", "NextKey", 64, FulltextField.FieldType.CHAR, false),
		new FulltextField("그리드 카운트", "GridCnt", 3, FulltextField.FieldType.CHAR, false),
		new FulltextField("그리드 사이즈", "GridSz", 134, FulltextField.FieldType.CHAR, false)
	};
	
	private static final FulltextField [] outGridFields = {
		new FulltextField("종목코드", "IsuNo", 12, FulltextField.FieldType.CHAR, false),
		new FulltextField("종목명", "ShtnIsuNm", 40, FulltextField.FieldType.CHAR, false),
		new FulltextField("평가금액", "EvalAmt", 16, FulltextField.FieldType.CHAR, false),
		new FulltextField("현재가", "CurPrc", 10, FulltextField.FieldType.CHAR, false),
		new FulltextField("등락률", "UpdnRat", 10, FulltextField.FieldType.CHAR, false),
		new FulltextField("평단가", "BuyPrc", 10, FulltextField.FieldType.CHAR, false),
		new FulltextField("보유수량", "HldQty", 10, FulltextField.FieldType.CHAR, false),
		new FulltextField("평가손익", "EvalPnl", 16, FulltextField.FieldType.CHAR, false),
		new FulltextField("평가손익률", "EvalRat", 10, FulltextField.FieldType.CHAR, false)
	};

	/**
	 * 부채관리/부채상세정보 VO를 이용해서 RMS 전송 전문을 생성하는 함수
	 * @param		부채관리/부채상세정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	public String CreateFulltext(final Debt018InVO vo) throws FulltextException {
		String fulltext = CreateFulltext(title, vo, inFields, inGridFields);
		
		return fulltext;
	}
	
	/**
	 * 부채관리/부채상세정보 VO를 이용해서 RMS 전송 전문을 생성하는 함수
	 * @param		부채관리/부채상세정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	public String CreateOutFulltext(final Debt018OutVO vo) throws FulltextException {
		String fulltext = CreateFulltext(title, vo, outFields, outGridFields);
		
		return fulltext;
	}
	
	/**
	 * RMS 전송 전문을 이용해서 부채관리/부채상세정보 VO를 생성하는 함수
	 * @param		부채관리/부채상세정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	public Debt018OutVO CreateOutVO(String fulltext) throws FulltextException {
		String outJson = CreateOutJson(title, fulltext, outFields, outGridFields);
		logger.info("outJson : {}", outJson);
		
		Gson gson = new Gson();
		Debt018OutVO vo = gson.fromJson(outJson, Debt018OutVO.class);
		logger.info("vo111111 : {}", gson.toJson(vo));
		return vo;
	}
	
	/**
	 * 부채관리/부채상세정보를 RESTfulAPI 서버에 전송하는 함수
	 * @param		부채관리/부채상세정보 VO
	 * @return		전송결과 VO
	 * @exception 	FulltextException
	 */	
	public FulltextResultVO Send(final Debt018InVO vo) throws FulltextException {
		String fulltext = CreateFulltext(vo);
		FulltextHeaderVO headerVO = new FulltextHeaderVO();
		
		/**
		 * TODO [김학진] 해더에 넣을 값을 모르겠음. 확인요망
		 */
		//headerVO.setSndIttCd(vo.getMbrCd());
		headerVO.setRcvIttCd(vo.getMbrCd());
		
		FulltextResultVO resultVO = debt018DAO.send(headerVO, fulltext);
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
