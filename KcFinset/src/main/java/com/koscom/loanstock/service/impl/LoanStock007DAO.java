package com.koscom.loanstock.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.koscom.common.fulltext.FulltextDAO;
import com.koscom.common.fulltext.FulltextHeaderVO;
import com.koscom.common.fulltext.FulltextResultVO;

/**
 * FINSET - 상품 조회/조회결과 상세 RESTful 통신관련 모듈
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
@Configuration
@PropertySource("classpath:prop/apiconfig.properties")
@Repository("LoanStock007DAO")
public class LoanStock007DAO extends FulltextDAO {
	private static final Logger logger = LoggerFactory.getLogger(LoanStock007DAO.class);
    @Resource
	Environment environment;
	
    @Override
	public FulltextResultVO send(FulltextHeaderVO headerVO, String fulltext) {
		boolean testMode = Boolean.parseBoolean(environment.getProperty("rest.server.testMode"));
		return send(headerVO, fulltext, testMode);
	}
    
    @Override
	public FulltextResultVO send(FulltextHeaderVO headerVO, String fulltext , boolean isTest) {
    	headerVO.setRestURL("rms/finset/interitemdetail");
    	
    	logger.debug("input fulltext : {}", fulltext);
    	FulltextResultVO vo = super.send(headerVO, fulltext, isTest);
		/*
		String str = "상품명                                  여신사명                                증권사명                                00등록일  상품개  상품만  3    00.31        1.31        2.31        3.31        4.31        5.31        10000           한 01000000         10000000        00.6         10000           10000000        10  10  10  ";
		
		logger.debug("vo.isSuccess() : {}", vo.isSuccess());
		logger.debug("vo.getFulltext() : {}", vo.getFulltext());
		
		if (vo.isSuccess() && "".equals(vo.getFulltext())) {
			vo.setFulltext(str);
			logger.debug("  ***>> 가짜 데이터 : {}", str);
		} else if (!vo.isSuccess()) {
			vo.setFulltext(str);
			logger.debug("  ***>> 오류에 의한 가짜 데이터 : {}", str);
		} else {
			logger.debug("  ***>> 실 데이터 : {}", vo.getFulltext());
		}
		*/
		return vo;
	}

}
