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
 * 증권사BIZ - 스탁론상품 조회 RESTful 통신관련 모듈
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
@Repository("LoanStock006DAO")
public class LoanStock006DAO extends FulltextDAO {
    private static final Logger logger = LoggerFactory.getLogger(LoanStock006DAO.class);
    @Resource
	Environment environment;
    
	@Override
	public FulltextResultVO send(FulltextHeaderVO headerVO, String fulltext) {
		boolean testMode = Boolean.parseBoolean(environment.getProperty("rest.server.testMode"));
		return send(headerVO, fulltext, testMode);
	}
	@Override
	public FulltextResultVO send(FulltextHeaderVO headerVO, String fulltext, boolean isTest) {
		headerVO.setRestURL("rms/finset/interaccount");
		
		logger.debug("input fulltext : {}", fulltext);
		FulltextResultVO vo = super.send(headerVO, fulltext, isTest);
		/*
		String str = "S063증권사명                                5  159  여1 여신사명1                               상품코드1   상품명1                                 0기1  00.26        0.36        100000000       1000000000      여2 여신사명2                               상품코드2   상품명2                                 0기2  00.26        0.36        100000000       1000000000      여3 여신사명3                               상품코드3   상품명3                                 0기3  00.26        0.36        100000000       1000000000      여4 여신사명4                               상품코드4   상품명4                                 0기4  00.26        0.36        100000000       1000000000      여5 여신사명5                               상품코드5   상품명5                                 0기5  00.26        0.36        100000000       1000000000      ";
		
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
