package com.koscom.st.customercenter019.service.impl;

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
import com.koscom.st.customercenter019.model.Customercenter019InVO;
import com.koscom.st.customercenter019.model.Customercenter019OutVO;
import com.koscom.st.customercenter019.service.Customercenter019Service;

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
@Service("Customercenter019Service")
public class Customercenter019ServiceImpl extends FulltextService implements Customercenter019Service {
    @Resource(name = "Customercenter019DAO")
    private Customercenter019DAO customercenter019DAO;

	private static final Logger logger = LoggerFactory.getLogger(Customercenter019ServiceImpl.class);
	
	private static final String title = "마이페이지/반대매매";
	private static final FulltextField [] inFields = {
		new FulltextField("계좌번호", "AcntNo", 20, FulltextField.FieldType.CHAR, true),
		new FulltextField("nextkey", "NextKey", 64, FulltextField.FieldType.CHAR, false)
	};
	
	private static final FulltextField [] inGridFields = {	
	};

	private static final FulltextField [] outFields = {
		new FulltextField("nextkey", "NextKey", 64, FulltextField.FieldType.CHAR, false),
		new FulltextField("그리드 카운트", "GridCnt", 3, FulltextField.FieldType.CHAR, false),
		new FulltextField("그리드 사이즈", "GridSz", 148, FulltextField.FieldType.CHAR, false)
	};
	
	private static final FulltextField [] outGridFields = {
		new FulltextField("여신사코드", "CrdtIttCd", 4, FulltextField.FieldType.CHAR, false),
		new FulltextField("여신사명", "CrdtIttNm", 40, FulltextField.FieldType.CHAR, false),
		new FulltextField("증권사코드", "MbrCd", 4, FulltextField.FieldType.CHAR, false),
		new FulltextField("증권사명", "MbrNm", 40, FulltextField.FieldType.CHAR, false),
		new FulltextField("상품코드", "PrdtCd", 12, FulltextField.FieldType.CHAR, false),
		new FulltextField("상품명", "PrdtNm", 40, FulltextField.FieldType.CHAR, false),
		new FulltextField("발생일", "CvrgOcrDttm", 8, FulltextField.FieldType.CHAR, false)
	};

	/**
	 * 상품 조회/조회결과 상세정보 VO를 이용해서 RMS 전송 전문을 생성하는 함수
	 * @param		상품 조회/조회결과 상세정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	public String CreateFulltext(final Customercenter019InVO vo) throws FulltextException {
		String fulltext = CreateFulltext(title, vo, inFields, inGridFields);
		
		return fulltext;
	}
	
	/**
	 * 상품 조회/조회결과 상세정보 VO를 이용해서 RMS 전송 전문을 생성하는 함수
	 * @param		상품 조회/조회결과 상세정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	public String CreateOutFulltext(final Customercenter019OutVO vo) throws FulltextException {
		String fulltext = CreateFulltext(title, vo, outFields, outGridFields);
		
		return fulltext;
	}
	
	/**
	 * RMS 전송 전문을 이용해서 상품 조회/조회결과 상세정보 VO를 생성하는 함수
	 * @param		상품 조회/조회결과 상세정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	public Customercenter019OutVO CreateOutVO(String fulltext) throws FulltextException {
		String outJson = CreateOutJson(title, fulltext, outFields, outGridFields);
		logger.debug("outJson : {}", outJson);
		
		Gson gson = new Gson();
		Customercenter019OutVO vo = gson.fromJson(outJson, Customercenter019OutVO.class);
		
		return vo;
	}
	
	/**
	 * 상품 조회/조회결과 상세정보를 RESTfulAPI 서버에 전송하는 함수
	 * @param		상품 조회/조회결과 상세정보 VO
	 * @return		전송결과 VO
	 * @exception 	FulltextException
	 */	
	public FulltextResultVO Send(final Customercenter019InVO vo) throws FulltextException {
		String fulltext = CreateFulltext(vo);
		FulltextHeaderVO headerVO = new FulltextHeaderVO();
		
		/**
		 * TODO [김학진] 해더에 넣을 값을 모르겠음. 확인요망
		 */
		//headerVO.setSndIttCd(vo.getMbrCd());
		headerVO.setRcvIttCd(vo.getMbrCd());
		
		FulltextResultVO resultVO = customercenter019DAO.send(headerVO, fulltext);
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
