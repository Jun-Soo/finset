package com.koscom.common.fulltext;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

/**
 * 전문 처리용 - RESTful 통신관련 모듈
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
public class FulltextDAO implements EnvironmentAware{
    private static final Logger logger = LoggerFactory.getLogger(FulltextDAO.class);
    private static final int headerSize = 220;
    @Resource
	Environment environment;
    
    @Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		
	}
    private static final int headerfulltextSize = 6;
    protected static final String defaultCodeSetName = "euc-kr";

	public FulltextResultVO send(FulltextHeaderVO headerVO, final String fulltext) {
		boolean testMode = Boolean.parseBoolean(environment.getProperty("rest.server.testMode"));
		return send(headerVO, fulltext, testMode);
	}

	public FulltextResultVO send(FulltextHeaderVO headerVO, final String fulltext, final boolean isTest) {
		String restServerUrl = environment.getProperty("rest.server.url");
	    String sndIttCd = environment.getProperty("rest.header.sndIttCd");
	    String mediTp = environment.getProperty("rest.header.mediTp");
	    String bizTp = environment.getProperty("rest.header.bizTp");
	    boolean testMode = Boolean.parseBoolean(environment.getProperty("rest.server.testMode"));
	    String codeSetName = environment.getProperty("rest.charset.forName");
		logger.info("restServerUrl : {}", restServerUrl);
		logger.info("sndIttCd : {}", sndIttCd);
		logger.info("rcvIttCd : {}", headerVO.getRcvIttCd());
		logger.info("mediTp : {}", mediTp);
		logger.info("bizTp : {}", bizTp);
		logger.info("testMode : {}", testMode);
		logger.info("codeSetName : {}", codeSetName);
		logger.info("  >> send");
		
		Charset codeSet = Charset.forName(codeSetName == null ? defaultCodeSetName : codeSetName);
		String regId = "Finset";
		
		headerVO.setRegId(regId);					//조작자ID기관
		headerVO.setSndIttCd(sndIttCd);		//발신 기관코드(고정)
		headerVO.setRcvIttCd(headerVO.getRcvIttCd());		//수신 기관코드는 발신기관 코드와 동일 (UXUI 화면에서 값을 받지 랂름)
		headerVO.setBizTp(bizTp);				//업무구분(W:Web)
		headerVO.setMediTp(mediTp);			//발생매체구분 (TODO [김학진] 모르겠음.. 확인 요망)
		
		FulltextResultVO resultVO = new FulltextResultVO();
		resultVO.setErrorMessage("");
		resultVO.setFulltext("");

		if (!isTest) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", codeSet));

			Gson gson = new Gson();
			String headerJson = gson.toJson(headerVO);
			String url = restServerUrl + headerVO.getRestURL();

			logger.info("restFullUrl : {}", url);
			/*
			HttpEntity<String> rmsHeader = new HttpEntity<String>(headerJson, headers) ;
			MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
			
			parts.add("rmsHeader", rmsHeader);
			parts.add("rmsFulltext", fulltext);
			*/
			
			HttpEntity<String> rmsHeader = new HttpEntity<String>(headerJson, headers) ;
			HttpEntity<String> rmsFulltext = new HttpEntity<String>(fulltext, headers) ;
			
			MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
			
			parts.add("rmsHeader", rmsHeader);
			parts.add("rmsFulltext", rmsFulltext);

			logger.info("**** RESTful 발송 ****");
			logger.info("  해더(json) : {}", headerJson);
			logger.info("  전문(text) : ({}) [{}]", fulltext.getBytes(codeSet).length, fulltext);
			logger.info("**********************");

			
			logger.info("**** RESTful 수신 ****");
			
			String result = null;
			boolean isError  = false;
			try {
				/*
				ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, parts, String.class);
				//ResponseEntity<String> responseEntity = restTemplate.exchange(fullUrl, HttpMethod.POST, entity, String.class, params);
				result = responseEntity.getBody();
				*/
				ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(url, parts, byte[].class);
				byte[] byteArray = responseEntity.getBody();
				logger.error(">>>>byteArray length   : {}", byteArray.length);
				logger.error(">>>>result1 none   : {}", new String(byteArray));
				logger.error(">>>>result2 EUC-KR : {}", new String(byteArray, "EUC-KR"));
				logger.error(">>>>result3 UTF-8  : {}", new String(byteArray, "UTF-8"));
				logger.error(">>>>result4 Zero 1 : {}", checkZeroChar(byteArray) == true ? "없음" : "있음");
				logger.error(">>>>result5 Zero 2 : {}", removeZeroChar(byteArray));
				
				result = new String(byteArray, "EUC-KR");
				logger.error(">>>>result : {}", result);
			} catch (RestClientException ex) {
				isError = true;
				resultVO.setSuccess(false);
				resultVO.setErrorMessage("WAS ERROR : " + ex.getMessage());
				//e.printStackTrace();
				logger.error("  >> WAS ERROR : {}", ex.getMessage());
			} catch (Exception ex) {
				isError = true;
				resultVO.setSuccess(false);
				resultVO.setErrorMessage("WAS ERROR : " + ex.getMessage());
				//ex.printStackTrace();
				logger.error("  >> WAS ERROR : {}", ex.getMessage());
			}
			
			if (!isError) {
				if (result == null) {
					resultVO.setSuccess(false);
					resultVO.setErrorMessage("WAS ERROR : RESTful API 결과 NULL");
					logger.debug("  >> WAS ERROR : RESTful API 결과 NULL");
				} else {
					logger.debug("**********************");
					logger.debug("  전체(text) : ({}) [{}]", result.getBytes(codeSet).length, result);

					byte[] byteText = result.getBytes(codeSet);
					
					if (headerSize >  byteText.length ) {
						resultVO.setSuccess(false);
						resultVO.setErrorMessage("WAS ERROR : 전문의 길이가 일치하지 않습니다.");
						logger.debug("  >> WAS ERROR : 수신 전체 Size가 해더({})보다 짧습니다.", headerSize);
					} else {
						String resultHeader = new String(byteText, 0, headerSize, codeSet);
						String resultFulltext = new String(byteText, headerSize, byteText.length-headerSize, codeSet);
						
						String strHeagerJson = FulltextHeaderVO.CreateOutJson(resultHeader);
						FulltextHeaderVO resulHeaderVO = gson.fromJson(strHeagerJson, FulltextHeaderVO.class);

						logger.debug("  해더(text) : ({}) [{}]", resultHeader.getBytes(codeSet).length, resultHeader);
						logger.debug("  전문(text) : ({}) [{}]", resultFulltext.getBytes(codeSet).length, resultFulltext);
						logger.debug("  해더(json) : {}", strHeagerJson);
						logger.debug("  ErrCd()    : {}", resulHeaderVO.getErrCd());
						
						long fulltextSize = resultFulltext.getBytes(codeSet).length;
						long headerFulltextSize = Long.parseLong(resultHeader.substring(0,headerfulltextSize));
						if (fulltextSize == headerFulltextSize) {
							
							if ("0000".equals(resulHeaderVO.getErrCd()) || "0002".equals(resulHeaderVO.getErrCd())) {
								resultVO.setSuccess(true);
								resultVO.setFulltext(resultFulltext);
							} /*else if ("1000".compareTo(resulHeaderVO.getErrCd()) == 1 && "0000".compareTo(resulHeaderVO.getErrCd()) == -1) {
								resultVO.setSuccess(true);
								resultVO.setFulltext(resultFulltext);
								if (!"0000".equals(resulHeaderVO.getErrCd())) {
									resultVO.setErrorMessage(resulHeaderVO.getErrMsg());
								}
							} */else {
								resultVO.setSuccess(false);
								resultVO.setErrorMessage(resulHeaderVO.getErrMsg());
								logger.debug("  >> 결과가 오류입니다. (ErrCd:{}, ErrMsg:{})", headerVO.getErrCd(), headerVO.getErrMsg());
							} 
						} else {
							resultVO.setSuccess(false);
							resultVO.setErrorMessage("WAS ERROR : 전문의 길이가 일치하지 않습니다." + "\\n해더의전문길이 : "+headerFulltextSize + ", 받은전문길이 : " + fulltextSize);
							logger.debug("  >> WAS ERROR : 전문 길이가 일치하지 않습니다. (수신해더의전문길이값:{}, 수신전문길이:{})", headerFulltextSize, fulltextSize);
						}
					}
				}
			}
			
			logger.debug("**********************");
		} else {
			resultVO.setSuccess(true);
		}
		
		return resultVO;		
	}
	
	private boolean checkZeroChar(byte[] byteArray) {
		for (int n=0; n<byteArray.length; n++) {
			if (byteArray[n] == 0) {
				return false;
			}
		}
		return true;
	}

	private String removeZeroChar(byte[] byteArray) {
		byte[] result = new byte[byteArray.length];
		for (int n=0; n<byteArray.length; n++) {
			if (byteArray[n] == 0) {
				result[n] = (byte) 32;
			} else {
				result[n] = byteArray[n];
			}
		}
		return new String(result);
	}
}
