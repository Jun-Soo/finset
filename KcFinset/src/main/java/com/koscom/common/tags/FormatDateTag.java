package com.koscom.common.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatDateTag extends TagSupport {
	private static final Logger logger = LoggerFactory.getLogger(FormatDateTag.class);

	private static final long serialVersionUID = 1L;
	private String value = "";
    private String type = "date";
    private String mark = "-";
    
	@Override
    public int doEndTag() throws JspException {
		
		try {
			JspWriter out = this.pageContext.getOut();
			logger.debug("value : {}", value);
			logger.debug("type : {}", type);
			logger.debug("mark : {}", mark);
			
			String data = "";
		    if (type!=null && !"".equals(type)) {
		    	if ("date".equals(type)) {
		    		if (value.length() == 8) {
		    			data = value.substring(0,4) + mark + value.substring(4,6) + mark + value.substring(6,8);
		    		}
		    	} else if ("time".equals(type)) {
		    		if (value.length() == 6) {
		    			data = value.substring(0,2) + ":" + value.substring(2,4) + ":" + value.substring(4,6);
		    		}
		    	} else if ("both".equals(type)) {
		    		if (value.length() == 6) {
			    		data = value.substring(0,4) + mark + value.substring(4,6) + mark + value.substring(6,8)
			    				+ " "
			    				+ value.substring(0,2) + ":" + value.substring(2,4) + ":" + value.substring(4,6);
		    		}
		    	} 
		    }

			out.print(data);

			return 0;
		} catch (IOException e) {
			throw new JspException();
		}
	}

    public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type.trim();
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

}
