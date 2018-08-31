package com.koscom.util;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsoupUtil {

	private static final Logger logger = LoggerFactory.getLogger(JsoupUtil.class);
	
	/**
    * 외부 크롤링
	* @throws IOException
    * @exception Exception
    */
	public static HashMap<String, Object> getCrawling(String url) throws IOException {

		Document 	doc  	= null;
		HashMap 	rtnMap 	= new HashMap<String, Object>();
		
		String 		jsessionId	= "";
		Response	res 		= null;

        logger.info("[KCB ] Crawling Connect");

        //크롤링 접속 3회 시도
        int tryCount = 0;
        while (tryCount < 3){
            try {
                res	= Jsoup.connect(url)
                        .timeout(5000)
                        .method(Method.GET)
                        .execute();

                logger.info("res statusCode ==== " + res.statusCode());
                if(res.statusCode() == 200) break;

            }catch(IllegalArgumentException e) {
                tryCount++;
            }
        }

        if (res != null) {
            logger.info("res header ==== " + res.headers().toString());
            logger.info("res body ==== " + res.body().toString());
            logger.info("res cookie ==== " + res.cookies().toString());
            doc 		= res.parse();
            jsessionId 	= res.cookie("JSESSIONID");
            rtnMap.put("doc", doc);
            rtnMap.put("jsessionId", jsessionId);
        } else {
            logger.info("res ==== " + res);
        }

        logger.info("rtnMap ==== " + rtnMap.toString());

		return rtnMap;
	}
	
	public static Document getCrawlingCookie(String url, String jsessionId) throws IOException {

		Document doc = null;
		Response res = null;

        
        //크롤링 접속 3회 시도
        int tryCount = 0;
        while (tryCount < 3){
            try {
                res = Jsoup.connect(url)
                        .cookie("JSESSIONID", jsessionId)
                        .timeout(5000)
                        .method(Method.GET)
                        .execute();

                logger.error("res statusCode ==== " + res.statusCode());
                if(res.statusCode() == 200) break;

            }catch(IllegalArgumentException e) {
                tryCount++;
            }
        }

        doc	= res.parse();
        
		/*
		int tryCount = 0;
        while (tryCount < 3){
            try {
            	 doc = Jsoup.connect(url)
                         .cookie("JSESSIONID", jsessionId)
                         .get();

                logger.info("res data ==== " + doc.data());
                if(doc.data().length() > 0) break;

            }catch(IllegalArgumentException e) {
                tryCount++;
            }
        }
        */
        /*
        doc = Jsoup.connect(url)
                .cookie("JSESSIONID", jsessionId)
                .get();
        */
		return doc;
	}
}
