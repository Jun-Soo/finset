package com.koscom.util;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MybatisUtil {

	private static final Logger logger = LoggerFactory.getLogger(MybatisUtil.class);

	public static boolean isEmpty(Object obj){
		logger.debug(obj.toString());
		
		if( obj instanceof String ) return obj==null || "".equals(obj.toString().trim());
		else if( obj instanceof List ) return obj==null || ((List)obj).isEmpty();
		else if( obj instanceof Map ) return obj==null || ((Map)obj).isEmpty();
		else if( obj instanceof Object[] ) return obj==null || Array.getLength(obj)==0;
		else return obj==null;
	}

	public static boolean isNotEmpty(String s){
		return !isEmpty(s);
	}
	
}
