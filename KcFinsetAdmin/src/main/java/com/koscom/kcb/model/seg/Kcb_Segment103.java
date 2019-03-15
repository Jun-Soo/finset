package com.koscom.kcb.model.seg;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.util.LogUtil;
import com.koscom.util.StringUtil;

public class Kcb_Segment103 extends AbstractSegment{ // CPS(고정)
	private static final long serialVersionUID = -6844587036961811308L;

	private static final Logger logger = LoggerFactory.getLogger(Kcb_Segment103.class);

	private final String segID = "103";
	private String	cd_profile;			//프로파일 코드
	private String	result_profile;		//프로파일 결과값

	// 모델 출력 스타일 설정
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public HashMap<String, Object> getParseDataByResData(String retStr)  throws UnsupportedEncodingException {
		StringBuffer str = new StringBuffer();
		HashMap<String, Object> map = new HashMap<String, Object>();
		int pos = 0;
		int len = 0;
		byte[] bt = null;


		bt = retStr.getBytes();
		Kcb_Segment103 seq103  = new Kcb_Segment103();

		pos+=len; len=	3;  // segID
		pos+=len; len=	9;	seq103.setCd_profile(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=	9;	seq103.setResult_profile(StringUtil.getByte2String(bt,pos,len).trim()) ;
		pos+=len; len=bt.length-pos; str.append(StringUtil.getByte2String(bt,pos,len < 0 ? 0 : len)) ;

		map.put("retObj", seq103);
		map.put("retStr", str.toString());


		return map;
	}


	public String getSegID() {
		return segID;
	}

	public String getCd_profile() {
		return cd_profile;
	}

	public void setCd_profile(String cd_profile) {
		this.cd_profile = cd_profile;
	}

	public String getResult_profile() {
		return result_profile;
	}

	public void setResult_profile(String result_profile) {
		this.result_profile = result_profile;
	}

}
