package com.koscom.st.debt018.service;

import com.koscom.common.fulltext.FulltextException;
import com.koscom.common.fulltext.FulltextResultVO;
import com.koscom.st.debt018.model.Debt018InVO;
import com.koscom.st.debt018.model.Debt018OutVO;

/**
 * FINSET - 상품 조회/조회결과 상세 비즈니스 정의 인터페이스
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
public interface Debt018Service {

	/**
	 * 상품 조회/조회결과 상세정보 VO를 이용해서 RMS 전송 전문을 생성하는 함수
	 * @param		상품 조회/조회결과 상세정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	
	public String CreateFulltext(final Debt018InVO vo) throws FulltextException;
	/**
	 * TODO 테스트용 전문 생성
	 * 운영기에서는 필요없는 기능
	 */
	public String CreateOutFulltext(final Debt018OutVO vo) throws FulltextException;

	/**
	 * RMS 전송 전문을 이용해서 상품 조회/조회결과 상세정보 VO를 생성하는 함수
	 * @param		상품 조회/조회결과 상세정보 VO
	 * @return		RMS 전송 전문
	 * @exception 	FulltextException
	 */	
	public Debt018OutVO CreateOutVO(String fulltext) throws FulltextException;
	
	/**
	 * 상품 조회/조회결과 상세정보를 RESTfulAPI 서버에 전송하는 함수
	 * @param		상품 조회/조회결과 상세정보 VO
	 * @return		전송결과 VO
	 * @exception 	FulltextException
	 */	
	public FulltextResultVO Send(final Debt018InVO vo) throws FulltextException;
	
	/**
	 * IN 필드의 정의 정보를 리턴하는 함수
	 * @return		IN 필드의 정의 정보
	 * @exception 	FulltextException
	 */	
	public String getInFieldInfo() throws FulltextException;
}
