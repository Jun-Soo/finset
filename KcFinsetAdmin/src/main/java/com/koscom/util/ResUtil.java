package com.koscom.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResUtil {
	
    private static final Logger logger = LoggerFactory.getLogger(ResUtil.class);
    
    /**
     * response.redirect()
     * 운영 path http -> https
     * @param response
     * @return url
     */
    public static String getPath(HttpServletRequest req) {
    	
    	String path 	= "";
    	
    	if("manage.finset.io".equals(req.getServerName())) {
    		path = "https://" + req.getServerName();
    	} else {
    		path = "http://" + req.getServerName() + ":" + req.getServerPort();
    	}
        return path;
    }
}
