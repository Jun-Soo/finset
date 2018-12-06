package com.koscom.kcb.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public abstract class AbstractSegment implements Serializable {
	private static final long serialVersionUID = -3478692101740806156L;

	public abstract HashMap<String, Object> getParseDataByResData(String retStr) throws UnsupportedEncodingException;

}
