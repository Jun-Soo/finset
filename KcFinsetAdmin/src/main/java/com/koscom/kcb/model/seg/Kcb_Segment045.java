package com.koscom.kcb.model.seg;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.util.LogUtil;
import com.koscom.util.StringUtil;

public class Kcb_Segment045 extends AbstractSegment{ // 카드이력정보(개인별)
	private static final long serialVersionUID = -6248315943492420384L;

	private static final Logger logger = LoggerFactory.getLogger(Kcb_Segment045.class);

	private final String segID = "045";

	private String no_person;
	private String callSegIdByMesg;

	private String[] cnt_card				= new String[12];	//신용카드수
	private String[] amt_limit_sum			= new String[12];	//총한도합계금액
	private String[] amt_ca_sum				= new String[12]; 	//단기카드대출(CA)한도합계금액
	private String[] cnt_use_card			= new String[12]; 	//이용카드수
	private String[] amt_all_sum			= new String[12]; 	//총이용금액
	private String[] cnt_ca_use_card		= new String[12];	//단기카드대출(CA)이용카드수
	private String[] amt_ca_use_card		= new String[12];	//단기카드대출(CA)이용금액
	private String[] cnt_install_use_card	= new String[12];	//할부이용카드수
	private String[] amt_install_use		= new String[12];	//할부이용금액





	private String cnt_card_before1;	//1개월전신용카드수KCB_045_INFO                     	//_1개월전신용카드수;
	private String cnt_card_before2;	//2개월전신용카드수KCB_045_INFO                     	//_2개월전신용카드수;
	private String cnt_card_before3;	//3개월전신용카드수KCB_045_INFO                     	//_3개월전신용카드수;
	private String cnt_card_before4;	//4개월전신용카드수KCB_045_INFO                     	//_4개월전신용카드수;
	private String cnt_card_before5;	//5개월전신용카드수KCB_045_INFO                     	//_5개월전신용카드수;
	private String cnt_card_before6;	//6개월전신용카드수KCB_045_INFO                     	//_6개월전신용카드수;
	private String cnt_card_before7;	//7개월전신용카드수KCB_045_INFO                     	//_7개월전신용카드수;
	private String cnt_card_before8;	//8개월전신용카드수KCB_045_INFO                     	//_8개월전신용카드수;
	private String cnt_card_before9;	//9개월전신용카드수KCB_045_INFO                     	//_9개월전신용카드수;
	private String cnt_card_before10;	//10개월전신용카드수KCB_045_INFO                    	//_10개월전신용카드수;
	private String cnt_card_before11;	//11개월전신용카드수KCB_045_INFO                    	//_11개월전신용카드수;
	private String cnt_card_before12;	//12개월전신용카드수KCB_045_INFO                    	//_12개월전신용카드수;

	private String amt_limit_sum_before1;	//1개월전총한도합계금액KCB_045_INFO               	//_1개월전총한도합계금액;
	private String amt_limit_sum_before2;	//2개월전총한도합계금액KCB_045_INFO               	//_2개월전총한도합계금액;
	private String amt_limit_sum_before3;	//3개월전총한도합계금액KCB_045_INFO               	//_3개월전총한도합계금액;
	private String amt_limit_sum_before4;	//4개월전총한도합계금액KCB_045_INFO               	//_4개월전총한도합계금액;
	private String amt_limit_sum_before5;	//5개월전총한도합계금액KCB_045_INFO               	//_5개월전총한도합계금액;
	private String amt_limit_sum_before6;	//6개월전총한도합계금액KCB_045_INFO               	//_6개월전총한도합계금액;
	private String amt_limit_sum_before7;	//7개월전총한도합계금액KCB_045_INFO               	//_7개월전총한도합계금액;
	private String amt_limit_sum_before8;	//8개월전총한도합계금액KCB_045_INFO               	//_8개월전총한도합계금액;
	private String amt_limit_sum_before9;	//9개월전총한도합계금액KCB_045_INFO               	//_9개월전총한도합계금액;
	private String amt_limit_sum_before10;	//10개월전총한도합계금액KCB_045_INFO            	//_10개월전총한도합계금액;
	private String amt_limit_sum_before11;	//11개월전총한도합계금액KCB_045_INFO            	//_11개월전총한도합계금액;
	private String amt_limit_sum_before12;	//12개월전총한도합계금액KCB_045_INFO            	//_12개월전총한도합계금액;

	private String amt_ca_sum_before1;	//1개월전단기카드대출(CA)한도합계금액KCB_045_INFO        	//_1개월전현금서비스한도합계금액;
	private String amt_ca_sum_before2;	//2개월전단기카드대출(CA)한도합계금액KCB_045_INFO        	//_2개월전현금서비스한도합계금액;
	private String amt_ca_sum_before3;	//3개월전단기카드대출(CA)한도합계금액KCB_045_INFO        	//_3개월전현금서비스한도합계금액;
	private String amt_ca_sum_before4;	//4개월전단기카드대출(CA)한도합계금액KCB_045_INFO        	//_4개월전현금서비스한도합계금액;
	private String amt_ca_sum_before5;	//5개월전단기카드대출(CA)한도합계금액KCB_045_INFO        	//_5개월전현금서비스한도합계금액;
	private String amt_ca_sum_before6;	//6개월전단기카드대출(CA)한도합계금액KCB_045_INFO        	//_6개월전현금서비스한도합계금액;
	private String amt_ca_sum_before7;	//7개월전단기카드대출(CA)한도합계금액KCB_045_INFO        	//_7개월전현금서비스한도합계금액;
	private String amt_ca_sum_before8;	//8개월전단기카드대출(CA)한도합계금액KCB_045_INFO        	//_8개월전현금서비스한도합계금액;
	private String amt_ca_sum_before9;	//9개월전단기카드대출(CA)한도합계금액KCB_045_INFO        	//_9개월전현금서비스한도합계금액;
	private String amt_ca_sum_before10;	//10개월전단기카드대출(CA)한도합계금액KCB_045_INFO       	//_10개월전현금서비스한도합계금액;
	private String amt_ca_sum_before11;	//11개월전단기카드대출(CA)한도합계금액KCB_045_INFO       	//_11개월전현금서비스한도합계금액;
	private String amt_ca_sum_before12;	//12개월전단기카드대출(CA)한도합계금액KCB_045_INFO       	//_12개월전현금서비스한도합계금액;

	private String cnt_use_card_before1;	//1개월전이용카드수KCB_045_INFO                 	//_1개월전이용카드수;
	private String cnt_use_card_before2;	//2개월전이용카드수KCB_045_INFO                 	//_2개월전이용카드수;
	private String cnt_use_card_before3;	//3개월전이용카드수KCB_045_INFO                 	//_3개월전이용카드수;
	private String cnt_use_card_before4;	//4개월전이용카드수KCB_045_INFO                 	//_4개월전이용카드수;
	private String cnt_use_card_before5;	//5개월전이용카드수KCB_045_INFO                 	//_5개월전이용카드수;
	private String cnt_use_card_before6;	//6개월전이용카드수KCB_045_INFO                 	//_6개월전이용카드수;
	private String cnt_use_card_before7;	//7개월전이용카드수KCB_045_INFO                 	//_7개월전이용카드수;
	private String cnt_use_card_before8;	//8개월전이용카드수KCB_045_INFO                 	//_8개월전이용카드수;
	private String cnt_use_card_before9;	//9개월전이용카드수KCB_045_INFO                 	//_9개월전이용카드수;
	private String cnt_use_card_before10;	//10개월전이용카드수KCB_045_INFO                	//_10개월전이용카드수;
	private String cnt_use_card_before11;	//11개월전이용카드수KCB_045_INFO                	//_11개월전이용카드수;
	private String cnt_use_card_before12;	//12개월전이용카드수KCB_045_INFO                	//_12개월전이용카드수;

	private String amt_all_sum_before1;	//1개월전총이용금액KCB_045_INFO                   	//_1개월전총이용금액;
	private String amt_all_sum_before2;	//2개월전총이용금액KCB_045_INFO                   	//_2개월전총이용금액;
	private String amt_all_sum_before3;	//3개월전총이용금액KCB_045_INFO                   	//_3개월전총이용금액;
	private String amt_all_sum_before4;	//4개월전총이용금액KCB_045_INFO                   	//_4개월전총이용금액;
	private String amt_all_sum_before5;	//5개월전총이용금액KCB_045_INFO                   	//_5개월전총이용금액;
	private String amt_all_sum_before6;	//6개월전총이용금액KCB_045_INFO                   	//_6개월전총이용금액;
	private String amt_all_sum_before7;	//7개월전총이용금액KCB_045_INFO                   	//_7개월전총이용금액;
	private String amt_all_sum_before8;	//8개월전총이용금액KCB_045_INFO                   	//_8개월전총이용금액;
	private String amt_all_sum_before9;	//9개월전총이용금액KCB_045_INFO                   	//_9개월전총이용금액;
	private String amt_all_sum_before10;	//10개월전총이용금액KCB_045_INFO                	//_10개월전총이용금액;
	private String amt_all_sum_before11;	//11개월전총이용금액KCB_045_INFO                	//_11개월전총이용금액;
	private String amt_all_sum_before12;	//12개월전총이용금액KCB_045_INFO                	//_12개월전총이용금액;

	private String cnt_ca_use_card_before1;	//1개월전단기카드대출(CA)이용카드수KCB_045_INFO     	//_1개월전현금서비스이용카드수;
	private String cnt_ca_use_card_before2;	//2개월전단기카드대출(CA)이용카드수KCB_045_INFO     	//_2개월전현금서비스이용카드수;
	private String cnt_ca_use_card_before3;	//3개월전단기카드대출(CA)이용카드수KCB_045_INFO     	//_3개월전현금서비스이용카드수;
	private String cnt_ca_use_card_before4;	//4개월전단기카드대출(CA)이용카드수KCB_045_INFO     	//_4개월전현금서비스이용카드수;
	private String cnt_ca_use_card_before5;	//5개월전단기카드대출(CA)이용카드수KCB_045_INFO     	//_5개월전현금서비스이용카드수;
	private String cnt_ca_use_card_before6;	//6개월전단기카드대출(CA)이용카드수KCB_045_INFO     	//_6개월전현금서비스이용카드수;
	private String cnt_ca_use_card_before7;	//7개월전단기카드대출(CA)이용카드수KCB_045_INFO     	//_7개월전현금서비스이용카드수;
	private String cnt_ca_use_card_before8;	//8개월전단기카드대출(CA)이용카드수KCB_045_INFO     	//_8개월전현금서비스이용카드수;
	private String cnt_ca_use_card_before9;	//9개월전단기카드대출(CA)이용카드수KCB_045_INFO     	//_9개월전현금서비스이용카드수;
	private String cnt_ca_use_card_before10;	//10개월전단기카드대출(CA)이용카드수KCB_045_INFO  	//_10개월전현금서비스이용카드수;
	private String cnt_ca_use_card_before11;	//11개월전단기카드대출(CA)이용카드수KCB_045_INFO  	//_11개월전현금서비스이용카드수;
	private String cnt_ca_use_card_before12;	//12개월전단기카드대출(CA)이용카드수KCB_045_INFO  	//_12개월전현금서비스이용카드수;

	private String amt_ca_use_card_before1;	//1개월전단기카드대출(CA)이용금액KCB_045_INFO      	//_1개월전현금서비스이용금액;
	private String amt_ca_use_card_before2;	//2개월전단기카드대출(CA)이용금액KCB_045_INFO      	//_2개월전현금서비스이용금액;
	private String amt_ca_use_card_before3;	//3개월전단기카드대출(CA)이용금액KCB_045_INFO      	//_3개월전현금서비스이용금액;
	private String amt_ca_use_card_before4;	//4개월전단기카드대출(CA)이용금액KCB_045_INFO      	//_4개월전현금서비스이용금액;
	private String amt_ca_use_card_before5;	//5개월전단기카드대출(CA)이용금액KCB_045_INFO      	//_5개월전현금서비스이용금액;
	private String amt_ca_use_card_before6;	//6개월전단기카드대출(CA)이용금액KCB_045_INFO      	//_6개월전현금서비스이용금액;
	private String amt_ca_use_card_before7;	//7개월전단기카드대출(CA)이용금액KCB_045_INFO      	//_7개월전현금서비스이용금액;
	private String amt_ca_use_card_before8;	//8개월전단기카드대출(CA)이용금액KCB_045_INFO      	//_8개월전현금서비스이용금액;
	private String amt_ca_use_card_before9;	//9개월전단기카드대출(CA)이용금액KCB_045_INFO      	//_9개월전현금서비스이용금액;
	private String amt_ca_use_card_before10;	//10개월전단기카드대출(CA)이용금액KCB_045_INFO   	//_10개월전현금서비스이용금액;
	private String amt_ca_use_card_before11;	//11개월전단기카드대출(CA)이용금액KCB_045_INFO   	//_11개월전현금서비스이용금액;
	private String amt_ca_use_card_before12;	//12개월전단기카드대출(CA)이용금액KCB_045_INFO   	//_12개월전현금서비스이용금액;

	private String cnt_install_use_card_before1;	//1개월전할부이용카드수KCB_045_INFO       	//_1개월전할부이용카드수;
	private String cnt_install_use_card_before2;	//2개월전할부이용카드수KCB_045_INFO       	//_2개월전할부이용카드수;
	private String cnt_install_use_card_before3;	//3개월전할부이용카드수KCB_045_INFO       	//_3개월전할부이용카드수;
	private String cnt_install_use_card_before4;	//4개월전할부이용카드수KCB_045_INFO       	//_4개월전할부이용카드수;
	private String cnt_install_use_card_before5;	//5개월전할부이용카드수KCB_045_INFO       	//_5개월전할부이용카드수;
	private String cnt_install_use_card_before6;	//6개월전할부이용카드수KCB_045_INFO       	//_6개월전할부이용카드수;
	private String cnt_install_use_card_before7;	//7개월전할부이용카드수KCB_045_INFO       	//_7개월전할부이용카드수;
	private String cnt_install_use_card_before8;	//8개월전할부이용카드수KCB_045_INFO       	//_8개월전할부이용카드수;
	private String cnt_install_use_card_before9;	//9개월전할부이용카드수KCB_045_INFO       	//_9개월전할부이용카드수;
	private String cnt_install_use_card_before10;	//10개월전할부이용카드수KCB_045_INFO      	//_10개월전할부이용카드수;
	private String cnt_install_use_card_before11;	//11개월전할부이용카드수KCB_045_INFO      	//_11개월전할부이용카드수;
	private String cnt_install_use_card_before12;	//12개월전할부이용카드수KCB_045_INFO      	//_12개월전할부이용카드수;

	private String amt_install_use_before1;	//1개월전할부이용금액KCB_045_INFO              	//_1개월전할부이용금액;
	private String amt_install_use_before2;	//2개월전할부이용금액KCB_045_INFO              	//_2개월전할부이용금액;
	private String amt_install_use_before3;	//3개월전할부이용금액KCB_045_INFO              	//_3개월전할부이용금액;
	private String amt_install_use_before4;	//4개월전할부이용금액KCB_045_INFO              	//_4개월전할부이용금액;
	private String amt_install_use_before5;	//5개월전할부이용금액KCB_045_INFO              	//_5개월전할부이용금액;
	private String amt_install_use_before6;	//6개월전할부이용금액KCB_045_INFO              	//_6개월전할부이용금액;
	private String amt_install_use_before7;	//7개월전할부이용금액KCB_045_INFO              	//_7개월전할부이용금액;
	private String amt_install_use_before8;	//8개월전할부이용금액KCB_045_INFO              	//_8개월전할부이용금액;
	private String amt_install_use_before9;	//9개월전할부이용금액KCB_045_INFO              	//_9개월전할부이용금액;
	private String amt_install_use_before10;	//10개월전할부이용금액KCB_045_INFO           	//_10개월전할부이용금액;
	private String amt_install_use_before11;	//11개월전할부이용금액KCB_045_INFO           	//_11개월전할부이용금액;
	private String amt_install_use_before12;	//12개월전할부이용금액KCB_045_INFO           	//_12개월전할부이용금액;		// total = 660

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
        Kcb_Segment045 seq045  = new Kcb_Segment045();

        pos+=len; len=3; // segID
        pos+=len; len=2; seq045.setCnt_card(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;	     seq045.setCnt_card_before1(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_card(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;       seq045.setCnt_card_before2(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_card(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;       seq045.setCnt_card_before3(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_card(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;       seq045.setCnt_card_before4(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_card(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;       seq045.setCnt_card_before5(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_card(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;       seq045.setCnt_card_before6(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_card(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;       seq045.setCnt_card_before7(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_card(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;       seq045.setCnt_card_before8(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_card(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;       seq045.setCnt_card_before9(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_card(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;       seq045.setCnt_card_before10(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_card(StringUtil.getByte2String(bt,pos,len).trim(), 10);       seq045.setCnt_card_before11(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_card(StringUtil.getByte2String(bt,pos,len).trim(), 11);       seq045.setCnt_card_before12(StringUtil.getByte2String(bt,pos,len).trim());

        pos+=len; len=9; seq045.setAmt_limit_sum(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;	     seq045.setAmt_limit_sum_before1(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_limit_sum(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;      seq045.setAmt_limit_sum_before2(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_limit_sum(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;      seq045.setAmt_limit_sum_before3(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_limit_sum(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;      seq045.setAmt_limit_sum_before4(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_limit_sum(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;      seq045.setAmt_limit_sum_before5(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_limit_sum(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;      seq045.setAmt_limit_sum_before6(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_limit_sum(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;      seq045.setAmt_limit_sum_before7(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_limit_sum(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;      seq045.setAmt_limit_sum_before8(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_limit_sum(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;      seq045.setAmt_limit_sum_before9(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_limit_sum(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;      seq045.setAmt_limit_sum_before10(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_limit_sum(StringUtil.getByte2String(bt,pos,len).trim(), 10);      seq045.setAmt_limit_sum_before11(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_limit_sum(StringUtil.getByte2String(bt,pos,len).trim(), 11);      seq045.setAmt_limit_sum_before12(StringUtil.getByte2String(bt,pos,len).trim());

        pos+=len; len=9; seq045.setAmt_ca_sum(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;       seq045.setAmt_ca_sum_before1(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_sum(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;       seq045.setAmt_ca_sum_before2(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_sum(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;       seq045.setAmt_ca_sum_before3(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_sum(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;       seq045.setAmt_ca_sum_before4(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_sum(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;       seq045.setAmt_ca_sum_before5(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_sum(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;       seq045.setAmt_ca_sum_before6(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_sum(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;       seq045.setAmt_ca_sum_before7(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_sum(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;       seq045.setAmt_ca_sum_before8(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_sum(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;       seq045.setAmt_ca_sum_before9(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_sum(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;       seq045.setAmt_ca_sum_before10(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_sum(StringUtil.getByte2String(bt,pos,len).trim(), 10);       seq045.setAmt_ca_sum_before11(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_sum(StringUtil.getByte2String(bt,pos,len).trim(), 11);       seq045.setAmt_ca_sum_before12(StringUtil.getByte2String(bt,pos,len).trim());

        pos+=len; len=2; seq045.setCnt_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;     seq045.setCnt_use_card_before1(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;     seq045.setCnt_use_card_before2(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;     seq045.setCnt_use_card_before3(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;     seq045.setCnt_use_card_before4(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;     seq045.setCnt_use_card_before5(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;     seq045.setCnt_use_card_before6(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;     seq045.setCnt_use_card_before7(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;     seq045.setCnt_use_card_before8(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;     seq045.setCnt_use_card_before9(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;     seq045.setCnt_use_card_before10(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 10);     seq045.setCnt_use_card_before11(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 11);     seq045.setCnt_use_card_before12(StringUtil.getByte2String(bt,pos,len).trim());

        pos+=len; len=9; seq045.setAmt_all_sum(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;      seq045.setAmt_all_sum_before1(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_all_sum(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;      seq045.setAmt_all_sum_before2(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_all_sum(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;      seq045.setAmt_all_sum_before3(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_all_sum(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;      seq045.setAmt_all_sum_before4(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_all_sum(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;      seq045.setAmt_all_sum_before5(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_all_sum(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;      seq045.setAmt_all_sum_before6(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_all_sum(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;      seq045.setAmt_all_sum_before7(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_all_sum(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;      seq045.setAmt_all_sum_before8(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_all_sum(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;      seq045.setAmt_all_sum_before9(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_all_sum(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;      seq045.setAmt_all_sum_before10(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_all_sum(StringUtil.getByte2String(bt,pos,len).trim(), 10);      seq045.setAmt_all_sum_before11(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_all_sum(StringUtil.getByte2String(bt,pos,len).trim(), 11);      seq045.setAmt_all_sum_before12(StringUtil.getByte2String(bt,pos,len).trim());

        pos+=len; len=2; seq045.setCnt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;        seq045.setCnt_ca_use_card_before1(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;        seq045.setCnt_ca_use_card_before2(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;        seq045.setCnt_ca_use_card_before3(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;        seq045.setCnt_ca_use_card_before4(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;        seq045.setCnt_ca_use_card_before5(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;        seq045.setCnt_ca_use_card_before6(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;        seq045.setCnt_ca_use_card_before7(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;        seq045.setCnt_ca_use_card_before8(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;        seq045.setCnt_ca_use_card_before9(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;        seq045.setCnt_ca_use_card_before10(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 10);        seq045.setCnt_ca_use_card_before11(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 11);        seq045.setCnt_ca_use_card_before12(StringUtil.getByte2String(bt,pos,len).trim());

        pos+=len; len=9; seq045.setAmt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;      seq045.setAmt_ca_use_card_before1(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;      seq045.setAmt_ca_use_card_before2(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;      seq045.setAmt_ca_use_card_before3(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;      seq045.setAmt_ca_use_card_before4(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;      seq045.setAmt_ca_use_card_before5(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;      seq045.setAmt_ca_use_card_before6(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;      seq045.setAmt_ca_use_card_before7(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;      seq045.setAmt_ca_use_card_before8(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;      seq045.setAmt_ca_use_card_before9(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;      seq045.setAmt_ca_use_card_before10(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 10);      seq045.setAmt_ca_use_card_before11(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_ca_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 11);      seq045.setAmt_ca_use_card_before12(StringUtil.getByte2String(bt,pos,len).trim());

        pos+=len; len=2; seq045.setCnt_install_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;     seq045.setCnt_install_use_card_before1(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_install_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;     seq045.setCnt_install_use_card_before2(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_install_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;     seq045.setCnt_install_use_card_before3(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_install_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;     seq045.setCnt_install_use_card_before4(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_install_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;     seq045.setCnt_install_use_card_before5(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_install_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;     seq045.setCnt_install_use_card_before6(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_install_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;     seq045.setCnt_install_use_card_before7(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_install_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;     seq045.setCnt_install_use_card_before8(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_install_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;     seq045.setCnt_install_use_card_before9(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_install_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;     seq045.setCnt_install_use_card_before10(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_install_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 10);     seq045.setCnt_install_use_card_before11(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=2; seq045.setCnt_install_use_card(StringUtil.getByte2String(bt,pos,len).trim(), 11);     seq045.setCnt_install_use_card_before12(StringUtil.getByte2String(bt,pos,len).trim());

        pos+=len; len=9; seq045.setAmt_install_use(StringUtil.getByte2String(bt,pos,len).trim(), 0) ;     seq045.setAmt_install_use_before1(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_install_use(StringUtil.getByte2String(bt,pos,len).trim(), 1) ;     seq045.setAmt_install_use_before2(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_install_use(StringUtil.getByte2String(bt,pos,len).trim(), 2) ;     seq045.setAmt_install_use_before3(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_install_use(StringUtil.getByte2String(bt,pos,len).trim(), 3) ;     seq045.setAmt_install_use_before4(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_install_use(StringUtil.getByte2String(bt,pos,len).trim(), 4) ;     seq045.setAmt_install_use_before5(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_install_use(StringUtil.getByte2String(bt,pos,len).trim(), 5) ;     seq045.setAmt_install_use_before6(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_install_use(StringUtil.getByte2String(bt,pos,len).trim(), 6) ;     seq045.setAmt_install_use_before7(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_install_use(StringUtil.getByte2String(bt,pos,len).trim(), 7) ;     seq045.setAmt_install_use_before8(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_install_use(StringUtil.getByte2String(bt,pos,len).trim(), 8) ;     seq045.setAmt_install_use_before9(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_install_use(StringUtil.getByte2String(bt,pos,len).trim(), 9) ;     seq045.setAmt_install_use_before10(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_install_use(StringUtil.getByte2String(bt,pos,len).trim(), 10);     seq045.setAmt_install_use_before11(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=9; seq045.setAmt_install_use(StringUtil.getByte2String(bt,pos,len).trim(), 11);     seq045.setAmt_install_use_before12(StringUtil.getByte2String(bt,pos,len).trim());
        pos+=len; len=21; // filler
        pos+=len; len=bt.length-pos; str.append(StringUtil.getByte2String(bt,pos,len < 0 ? 0 : len)) ;

        map.put("retObj", seq045);
        map.put("retStr", str.toString());

		return map;
	}


	public String getCallSegIdByMesg() {
		return callSegIdByMesg;
	}

	public void setCallSegIdByMesg(String callSegIdByMesg) {
		this.callSegIdByMesg = callSegIdByMesg;
	}

	public String getSegID() {
		return segID;
	}

	public String getNo_person() {
		return no_person;
	}

	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}

	public String[] getCnt_card() {
		return cnt_card;
	}

	public String getCnt_card(int i) {
		return cnt_card[i];
	}

	public void setCnt_card(String[] cnt_card) {
		this.cnt_card = cnt_card;
	}
	public void setCnt_card(String cnt_card, int i) {
		this.cnt_card[i] = cnt_card;
	}

	public String[] getAmt_limit_sum() {
		return amt_limit_sum;
	}
	public String getAmt_limit_sum(int i) {
		return amt_limit_sum[i];
	}

	public void setAmt_limit_sum(String[] amt_limit_sum) {
		this.amt_limit_sum = amt_limit_sum;
	}
	public void setAmt_limit_sum(String amt_limit_sum, int i) {
		this.amt_limit_sum[i] = amt_limit_sum;
	}

	public String[] getAmt_ca_sum() {
		return amt_ca_sum;
	}
	public String getAmt_ca_sum(int i) {
		return amt_ca_sum[i];
	}

	public void setAmt_ca_sum(String[] amt_ca_sum) {
		this.amt_ca_sum = amt_ca_sum;
	}
	public void setAmt_ca_sum(String amt_ca_sum, int i) {
		this.amt_ca_sum[i] = amt_ca_sum;
	}

	public String[] getCnt_use_card() {
		return cnt_use_card;
	}
	public String getCnt_use_card(int i) {
		return cnt_use_card[i];
	}

	public void setCnt_use_card(String[] cnt_use_card) {
		this.cnt_use_card = cnt_use_card;
	}
	public void setCnt_use_card(String cnt_use_card, int i) {
		this.cnt_use_card[i] = cnt_use_card;
	}

	public String[] getAmt_all_sum() {
		return amt_all_sum;
	}
	public String getAmt_all_sum(int i) {
		return amt_all_sum[i];
	}

	public void setAmt_all_sum(String[] amt_all_sum) {
		this.amt_all_sum = amt_all_sum;
	}
	public void setAmt_all_sum(String amt_all_sum, int i) {
		this.amt_all_sum[i] = amt_all_sum;
	}

	public String[] getCnt_ca_use_card() {
		return cnt_ca_use_card;
	}
	public String getCnt_ca_use_card(int i) {
		return cnt_ca_use_card[i];
	}

	public void setCnt_ca_use_card(String[] cnt_ca_use_card) {
		this.cnt_ca_use_card = cnt_ca_use_card;
	}
	public void setCnt_ca_use_card(String cnt_ca_use_card, int i) {
		this.cnt_ca_use_card[i] = cnt_ca_use_card;
	}

	public String[] getAmt_ca_use_card() {
		return amt_ca_use_card;
	}
	public String getAmt_ca_use_card(int i) {
		return amt_ca_use_card[i];
	}

	public void setAmt_ca_use_card(String[] amt_ca_use_card) {
		this.amt_ca_use_card = amt_ca_use_card;
	}
	public void setAmt_ca_use_card(String amt_ca_use_card, int i) {
		this.amt_ca_use_card[i] = amt_ca_use_card;
	}

	public String[] getCnt_install_use_card() {
		return cnt_install_use_card;
	}
	public String getCnt_install_use_card(int i) {
		return cnt_install_use_card[i];
	}

	public void setCnt_install_use_card(String[] cnt_install_use_card) {
		this.cnt_install_use_card = cnt_install_use_card;
	}
	public void setCnt_install_use_card(String cnt_install_use_card, int i) {
		this.cnt_install_use_card[i] = cnt_install_use_card;
	}

	public String[] getAmt_install_use() {
		return amt_install_use;
	}
	public String getAmt_install_use(int i) {
		return amt_install_use[i];
	}

	public void setAmt_install_use(String[] amt_install_use) {
		this.amt_install_use = amt_install_use;
	}
	public void setAmt_install_use(String amt_install_use, int i) {
		this.amt_install_use[i] = amt_install_use;
	}










	public String getCnt_card_before1() {
		return cnt_card_before1;
	}

	public void setCnt_card_before1(String cnt_card_before1) {
		this.cnt_card_before1 = cnt_card_before1;
	}

	public String getCnt_card_before2() {
		return cnt_card_before2;
	}

	public void setCnt_card_before2(String cnt_card_before2) {
		this.cnt_card_before2 = cnt_card_before2;
	}

	public String getCnt_card_before3() {
		return cnt_card_before3;
	}

	public void setCnt_card_before3(String cnt_card_before3) {
		this.cnt_card_before3 = cnt_card_before3;
	}

	public String getCnt_card_before4() {
		return cnt_card_before4;
	}

	public void setCnt_card_before4(String cnt_card_before4) {
		this.cnt_card_before4 = cnt_card_before4;
	}

	public String getCnt_card_before5() {
		return cnt_card_before5;
	}

	public void setCnt_card_before5(String cnt_card_before5) {
		this.cnt_card_before5 = cnt_card_before5;
	}

	public String getCnt_card_before6() {
		return cnt_card_before6;
	}

	public void setCnt_card_before6(String cnt_card_before6) {
		this.cnt_card_before6 = cnt_card_before6;
	}

	public String getCnt_card_before7() {
		return cnt_card_before7;
	}

	public void setCnt_card_before7(String cnt_card_before7) {
		this.cnt_card_before7 = cnt_card_before7;
	}

	public String getCnt_card_before8() {
		return cnt_card_before8;
	}

	public void setCnt_card_before8(String cnt_card_before8) {
		this.cnt_card_before8 = cnt_card_before8;
	}

	public String getCnt_card_before9() {
		return cnt_card_before9;
	}

	public void setCnt_card_before9(String cnt_card_before9) {
		this.cnt_card_before9 = cnt_card_before9;
	}

	public String getCnt_card_before10() {
		return cnt_card_before10;
	}

	public void setCnt_card_before10(String cnt_card_before10) {
		this.cnt_card_before10 = cnt_card_before10;
	}

	public String getCnt_card_before11() {
		return cnt_card_before11;
	}

	public void setCnt_card_before11(String cnt_card_before11) {
		this.cnt_card_before11 = cnt_card_before11;
	}

	public String getCnt_card_before12() {
		return cnt_card_before12;
	}

	public void setCnt_card_before12(String cnt_card_before12) {
		this.cnt_card_before12 = cnt_card_before12;
	}

	public String getAmt_limit_sum_before1() {
		return amt_limit_sum_before1;
	}

	public void setAmt_limit_sum_before1(String amt_limit_sum_before1) {
		this.amt_limit_sum_before1 = amt_limit_sum_before1;
	}

	public String getAmt_limit_sum_before2() {
		return amt_limit_sum_before2;
	}

	public void setAmt_limit_sum_before2(String amt_limit_sum_before2) {
		this.amt_limit_sum_before2 = amt_limit_sum_before2;
	}

	public String getAmt_limit_sum_before3() {
		return amt_limit_sum_before3;
	}

	public void setAmt_limit_sum_before3(String amt_limit_sum_before3) {
		this.amt_limit_sum_before3 = amt_limit_sum_before3;
	}

	public String getAmt_limit_sum_before4() {
		return amt_limit_sum_before4;
	}

	public void setAmt_limit_sum_before4(String amt_limit_sum_before4) {
		this.amt_limit_sum_before4 = amt_limit_sum_before4;
	}

	public String getAmt_limit_sum_before5() {
		return amt_limit_sum_before5;
	}

	public void setAmt_limit_sum_before5(String amt_limit_sum_before5) {
		this.amt_limit_sum_before5 = amt_limit_sum_before5;
	}

	public String getAmt_limit_sum_before6() {
		return amt_limit_sum_before6;
	}

	public void setAmt_limit_sum_before6(String amt_limit_sum_before6) {
		this.amt_limit_sum_before6 = amt_limit_sum_before6;
	}

	public String getAmt_limit_sum_before7() {
		return amt_limit_sum_before7;
	}

	public void setAmt_limit_sum_before7(String amt_limit_sum_before7) {
		this.amt_limit_sum_before7 = amt_limit_sum_before7;
	}

	public String getAmt_limit_sum_before8() {
		return amt_limit_sum_before8;
	}

	public void setAmt_limit_sum_before8(String amt_limit_sum_before8) {
		this.amt_limit_sum_before8 = amt_limit_sum_before8;
	}

	public String getAmt_limit_sum_before9() {
		return amt_limit_sum_before9;
	}

	public void setAmt_limit_sum_before9(String amt_limit_sum_before9) {
		this.amt_limit_sum_before9 = amt_limit_sum_before9;
	}

	public String getAmt_limit_sum_before10() {
		return amt_limit_sum_before10;
	}

	public void setAmt_limit_sum_before10(String amt_limit_sum_before10) {
		this.amt_limit_sum_before10 = amt_limit_sum_before10;
	}

	public String getAmt_limit_sum_before11() {
		return amt_limit_sum_before11;
	}

	public void setAmt_limit_sum_before11(String amt_limit_sum_before11) {
		this.amt_limit_sum_before11 = amt_limit_sum_before11;
	}

	public String getAmt_limit_sum_before12() {
		return amt_limit_sum_before12;
	}

	public void setAmt_limit_sum_before12(String amt_limit_sum_before12) {
		this.amt_limit_sum_before12 = amt_limit_sum_before12;
	}

	public String getAmt_ca_sum_before1() {
		return amt_ca_sum_before1;
	}

	public void setAmt_ca_sum_before1(String amt_ca_sum_before1) {
		this.amt_ca_sum_before1 = amt_ca_sum_before1;
	}

	public String getAmt_ca_sum_before2() {
		return amt_ca_sum_before2;
	}

	public void setAmt_ca_sum_before2(String amt_ca_sum_before2) {
		this.amt_ca_sum_before2 = amt_ca_sum_before2;
	}

	public String getAmt_ca_sum_before3() {
		return amt_ca_sum_before3;
	}

	public void setAmt_ca_sum_before3(String amt_ca_sum_before3) {
		this.amt_ca_sum_before3 = amt_ca_sum_before3;
	}

	public String getAmt_ca_sum_before4() {
		return amt_ca_sum_before4;
	}

	public void setAmt_ca_sum_before4(String amt_ca_sum_before4) {
		this.amt_ca_sum_before4 = amt_ca_sum_before4;
	}

	public String getAmt_ca_sum_before5() {
		return amt_ca_sum_before5;
	}

	public void setAmt_ca_sum_before5(String amt_ca_sum_before5) {
		this.amt_ca_sum_before5 = amt_ca_sum_before5;
	}

	public String getAmt_ca_sum_before6() {
		return amt_ca_sum_before6;
	}

	public void setAmt_ca_sum_before6(String amt_ca_sum_before6) {
		this.amt_ca_sum_before6 = amt_ca_sum_before6;
	}

	public String getAmt_ca_sum_before7() {
		return amt_ca_sum_before7;
	}

	public void setAmt_ca_sum_before7(String amt_ca_sum_before7) {
		this.amt_ca_sum_before7 = amt_ca_sum_before7;
	}

	public String getAmt_ca_sum_before8() {
		return amt_ca_sum_before8;
	}

	public void setAmt_ca_sum_before8(String amt_ca_sum_before8) {
		this.amt_ca_sum_before8 = amt_ca_sum_before8;
	}

	public String getAmt_ca_sum_before9() {
		return amt_ca_sum_before9;
	}

	public void setAmt_ca_sum_before9(String amt_ca_sum_before9) {
		this.amt_ca_sum_before9 = amt_ca_sum_before9;
	}

	public String getAmt_ca_sum_before10() {
		return amt_ca_sum_before10;
	}

	public void setAmt_ca_sum_before10(String amt_ca_sum_before10) {
		this.amt_ca_sum_before10 = amt_ca_sum_before10;
	}

	public String getAmt_ca_sum_before11() {
		return amt_ca_sum_before11;
	}

	public void setAmt_ca_sum_before11(String amt_ca_sum_before11) {
		this.amt_ca_sum_before11 = amt_ca_sum_before11;
	}

	public String getAmt_ca_sum_before12() {
		return amt_ca_sum_before12;
	}

	public void setAmt_ca_sum_before12(String amt_ca_sum_before12) {
		this.amt_ca_sum_before12 = amt_ca_sum_before12;
	}

	public String getCnt_use_card_before1() {
		return cnt_use_card_before1;
	}

	public void setCnt_use_card_before1(String cnt_use_card_before1) {
		this.cnt_use_card_before1 = cnt_use_card_before1;
	}

	public String getCnt_use_card_before2() {
		return cnt_use_card_before2;
	}

	public void setCnt_use_card_before2(String cnt_use_card_before2) {
		this.cnt_use_card_before2 = cnt_use_card_before2;
	}

	public String getCnt_use_card_before3() {
		return cnt_use_card_before3;
	}

	public void setCnt_use_card_before3(String cnt_use_card_before3) {
		this.cnt_use_card_before3 = cnt_use_card_before3;
	}

	public String getCnt_use_card_before4() {
		return cnt_use_card_before4;
	}

	public void setCnt_use_card_before4(String cnt_use_card_before4) {
		this.cnt_use_card_before4 = cnt_use_card_before4;
	}

	public String getCnt_use_card_before5() {
		return cnt_use_card_before5;
	}

	public void setCnt_use_card_before5(String cnt_use_card_before5) {
		this.cnt_use_card_before5 = cnt_use_card_before5;
	}

	public String getCnt_use_card_before6() {
		return cnt_use_card_before6;
	}

	public void setCnt_use_card_before6(String cnt_use_card_before6) {
		this.cnt_use_card_before6 = cnt_use_card_before6;
	}

	public String getCnt_use_card_before7() {
		return cnt_use_card_before7;
	}

	public void setCnt_use_card_before7(String cnt_use_card_before7) {
		this.cnt_use_card_before7 = cnt_use_card_before7;
	}

	public String getCnt_use_card_before8() {
		return cnt_use_card_before8;
	}

	public void setCnt_use_card_before8(String cnt_use_card_before8) {
		this.cnt_use_card_before8 = cnt_use_card_before8;
	}

	public String getCnt_use_card_before9() {
		return cnt_use_card_before9;
	}

	public void setCnt_use_card_before9(String cnt_use_card_before9) {
		this.cnt_use_card_before9 = cnt_use_card_before9;
	}

	public String getCnt_use_card_before10() {
		return cnt_use_card_before10;
	}

	public void setCnt_use_card_before10(String cnt_use_card_before10) {
		this.cnt_use_card_before10 = cnt_use_card_before10;
	}

	public String getCnt_use_card_before11() {
		return cnt_use_card_before11;
	}

	public void setCnt_use_card_before11(String cnt_use_card_before11) {
		this.cnt_use_card_before11 = cnt_use_card_before11;
	}

	public String getCnt_use_card_before12() {
		return cnt_use_card_before12;
	}

	public void setCnt_use_card_before12(String cnt_use_card_before12) {
		this.cnt_use_card_before12 = cnt_use_card_before12;
	}

	public String getAmt_all_sum_before1() {
		return amt_all_sum_before1;
	}

	public void setAmt_all_sum_before1(String amt_all_sum_before1) {
		this.amt_all_sum_before1 = amt_all_sum_before1;
	}

	public String getAmt_all_sum_before2() {
		return amt_all_sum_before2;
	}

	public void setAmt_all_sum_before2(String amt_all_sum_before2) {
		this.amt_all_sum_before2 = amt_all_sum_before2;
	}

	public String getAmt_all_sum_before3() {
		return amt_all_sum_before3;
	}

	public void setAmt_all_sum_before3(String amt_all_sum_before3) {
		this.amt_all_sum_before3 = amt_all_sum_before3;
	}

	public String getAmt_all_sum_before4() {
		return amt_all_sum_before4;
	}

	public void setAmt_all_sum_before4(String amt_all_sum_before4) {
		this.amt_all_sum_before4 = amt_all_sum_before4;
	}

	public String getAmt_all_sum_before5() {
		return amt_all_sum_before5;
	}

	public void setAmt_all_sum_before5(String amt_all_sum_before5) {
		this.amt_all_sum_before5 = amt_all_sum_before5;
	}

	public String getAmt_all_sum_before6() {
		return amt_all_sum_before6;
	}

	public void setAmt_all_sum_before6(String amt_all_sum_before6) {
		this.amt_all_sum_before6 = amt_all_sum_before6;
	}

	public String getAmt_all_sum_before7() {
		return amt_all_sum_before7;
	}

	public void setAmt_all_sum_before7(String amt_all_sum_before7) {
		this.amt_all_sum_before7 = amt_all_sum_before7;
	}

	public String getAmt_all_sum_before8() {
		return amt_all_sum_before8;
	}

	public void setAmt_all_sum_before8(String amt_all_sum_before8) {
		this.amt_all_sum_before8 = amt_all_sum_before8;
	}

	public String getAmt_all_sum_before9() {
		return amt_all_sum_before9;
	}

	public void setAmt_all_sum_before9(String amt_all_sum_before9) {
		this.amt_all_sum_before9 = amt_all_sum_before9;
	}

	public String getAmt_all_sum_before10() {
		return amt_all_sum_before10;
	}

	public void setAmt_all_sum_before10(String amt_all_sum_before10) {
		this.amt_all_sum_before10 = amt_all_sum_before10;
	}

	public String getAmt_all_sum_before11() {
		return amt_all_sum_before11;
	}

	public void setAmt_all_sum_before11(String amt_all_sum_before11) {
		this.amt_all_sum_before11 = amt_all_sum_before11;
	}

	public String getAmt_all_sum_before12() {
		return amt_all_sum_before12;
	}

	public void setAmt_all_sum_before12(String amt_all_sum_before12) {
		this.amt_all_sum_before12 = amt_all_sum_before12;
	}

	public String getCnt_ca_use_card_before1() {
		return cnt_ca_use_card_before1;
	}

	public void setCnt_ca_use_card_before1(String cnt_ca_use_card_before1) {
		this.cnt_ca_use_card_before1 = cnt_ca_use_card_before1;
	}

	public String getCnt_ca_use_card_before2() {
		return cnt_ca_use_card_before2;
	}

	public void setCnt_ca_use_card_before2(String cnt_ca_use_card_before2) {
		this.cnt_ca_use_card_before2 = cnt_ca_use_card_before2;
	}

	public String getCnt_ca_use_card_before3() {
		return cnt_ca_use_card_before3;
	}

	public void setCnt_ca_use_card_before3(String cnt_ca_use_card_before3) {
		this.cnt_ca_use_card_before3 = cnt_ca_use_card_before3;
	}

	public String getCnt_ca_use_card_before4() {
		return cnt_ca_use_card_before4;
	}

	public void setCnt_ca_use_card_before4(String cnt_ca_use_card_before4) {
		this.cnt_ca_use_card_before4 = cnt_ca_use_card_before4;
	}

	public String getCnt_ca_use_card_before5() {
		return cnt_ca_use_card_before5;
	}

	public void setCnt_ca_use_card_before5(String cnt_ca_use_card_before5) {
		this.cnt_ca_use_card_before5 = cnt_ca_use_card_before5;
	}

	public String getCnt_ca_use_card_before6() {
		return cnt_ca_use_card_before6;
	}

	public void setCnt_ca_use_card_before6(String cnt_ca_use_card_before6) {
		this.cnt_ca_use_card_before6 = cnt_ca_use_card_before6;
	}

	public String getCnt_ca_use_card_before7() {
		return cnt_ca_use_card_before7;
	}

	public void setCnt_ca_use_card_before7(String cnt_ca_use_card_before7) {
		this.cnt_ca_use_card_before7 = cnt_ca_use_card_before7;
	}

	public String getCnt_ca_use_card_before8() {
		return cnt_ca_use_card_before8;
	}

	public void setCnt_ca_use_card_before8(String cnt_ca_use_card_before8) {
		this.cnt_ca_use_card_before8 = cnt_ca_use_card_before8;
	}

	public String getCnt_ca_use_card_before9() {
		return cnt_ca_use_card_before9;
	}

	public void setCnt_ca_use_card_before9(String cnt_ca_use_card_before9) {
		this.cnt_ca_use_card_before9 = cnt_ca_use_card_before9;
	}

	public String getCnt_ca_use_card_before10() {
		return cnt_ca_use_card_before10;
	}

	public void setCnt_ca_use_card_before10(String cnt_ca_use_card_before10) {
		this.cnt_ca_use_card_before10 = cnt_ca_use_card_before10;
	}

	public String getCnt_ca_use_card_before11() {
		return cnt_ca_use_card_before11;
	}

	public void setCnt_ca_use_card_before11(String cnt_ca_use_card_before11) {
		this.cnt_ca_use_card_before11 = cnt_ca_use_card_before11;
	}

	public String getCnt_ca_use_card_before12() {
		return cnt_ca_use_card_before12;
	}

	public void setCnt_ca_use_card_before12(String cnt_ca_use_card_before12) {
		this.cnt_ca_use_card_before12 = cnt_ca_use_card_before12;
	}

	public String getAmt_ca_use_card_before1() {
		return amt_ca_use_card_before1;
	}

	public void setAmt_ca_use_card_before1(String amt_ca_use_card_before1) {
		this.amt_ca_use_card_before1 = amt_ca_use_card_before1;
	}

	public String getAmt_ca_use_card_before2() {
		return amt_ca_use_card_before2;
	}

	public void setAmt_ca_use_card_before2(String amt_ca_use_card_before2) {
		this.amt_ca_use_card_before2 = amt_ca_use_card_before2;
	}

	public String getAmt_ca_use_card_before3() {
		return amt_ca_use_card_before3;
	}

	public void setAmt_ca_use_card_before3(String amt_ca_use_card_before3) {
		this.amt_ca_use_card_before3 = amt_ca_use_card_before3;
	}

	public String getAmt_ca_use_card_before4() {
		return amt_ca_use_card_before4;
	}

	public void setAmt_ca_use_card_before4(String amt_ca_use_card_before4) {
		this.amt_ca_use_card_before4 = amt_ca_use_card_before4;
	}

	public String getAmt_ca_use_card_before5() {
		return amt_ca_use_card_before5;
	}

	public void setAmt_ca_use_card_before5(String amt_ca_use_card_before5) {
		this.amt_ca_use_card_before5 = amt_ca_use_card_before5;
	}

	public String getAmt_ca_use_card_before6() {
		return amt_ca_use_card_before6;
	}

	public void setAmt_ca_use_card_before6(String amt_ca_use_card_before6) {
		this.amt_ca_use_card_before6 = amt_ca_use_card_before6;
	}

	public String getAmt_ca_use_card_before7() {
		return amt_ca_use_card_before7;
	}

	public void setAmt_ca_use_card_before7(String amt_ca_use_card_before7) {
		this.amt_ca_use_card_before7 = amt_ca_use_card_before7;
	}

	public String getAmt_ca_use_card_before8() {
		return amt_ca_use_card_before8;
	}

	public void setAmt_ca_use_card_before8(String amt_ca_use_card_before8) {
		this.amt_ca_use_card_before8 = amt_ca_use_card_before8;
	}

	public String getAmt_ca_use_card_before9() {
		return amt_ca_use_card_before9;
	}

	public void setAmt_ca_use_card_before9(String amt_ca_use_card_before9) {
		this.amt_ca_use_card_before9 = amt_ca_use_card_before9;
	}

	public String getAmt_ca_use_card_before10() {
		return amt_ca_use_card_before10;
	}

	public void setAmt_ca_use_card_before10(String amt_ca_use_card_before10) {
		this.amt_ca_use_card_before10 = amt_ca_use_card_before10;
	}

	public String getAmt_ca_use_card_before11() {
		return amt_ca_use_card_before11;
	}

	public void setAmt_ca_use_card_before11(String amt_ca_use_card_before11) {
		this.amt_ca_use_card_before11 = amt_ca_use_card_before11;
	}

	public String getAmt_ca_use_card_before12() {
		return amt_ca_use_card_before12;
	}

	public void setAmt_ca_use_card_before12(String amt_ca_use_card_before12) {
		this.amt_ca_use_card_before12 = amt_ca_use_card_before12;
	}

	public String getCnt_install_use_card_before1() {
		return cnt_install_use_card_before1;
	}

	public void setCnt_install_use_card_before1(String cnt_install_use_card_before1) {
		this.cnt_install_use_card_before1 = cnt_install_use_card_before1;
	}

	public String getCnt_install_use_card_before2() {
		return cnt_install_use_card_before2;
	}

	public void setCnt_install_use_card_before2(String cnt_install_use_card_before2) {
		this.cnt_install_use_card_before2 = cnt_install_use_card_before2;
	}

	public String getCnt_install_use_card_before3() {
		return cnt_install_use_card_before3;
	}

	public void setCnt_install_use_card_before3(String cnt_install_use_card_before3) {
		this.cnt_install_use_card_before3 = cnt_install_use_card_before3;
	}

	public String getCnt_install_use_card_before4() {
		return cnt_install_use_card_before4;
	}

	public void setCnt_install_use_card_before4(String cnt_install_use_card_before4) {
		this.cnt_install_use_card_before4 = cnt_install_use_card_before4;
	}

	public String getCnt_install_use_card_before5() {
		return cnt_install_use_card_before5;
	}

	public void setCnt_install_use_card_before5(String cnt_install_use_card_before5) {
		this.cnt_install_use_card_before5 = cnt_install_use_card_before5;
	}

	public String getCnt_install_use_card_before6() {
		return cnt_install_use_card_before6;
	}

	public void setCnt_install_use_card_before6(String cnt_install_use_card_before6) {
		this.cnt_install_use_card_before6 = cnt_install_use_card_before6;
	}

	public String getCnt_install_use_card_before7() {
		return cnt_install_use_card_before7;
	}

	public void setCnt_install_use_card_before7(String cnt_install_use_card_before7) {
		this.cnt_install_use_card_before7 = cnt_install_use_card_before7;
	}

	public String getCnt_install_use_card_before8() {
		return cnt_install_use_card_before8;
	}

	public void setCnt_install_use_card_before8(String cnt_install_use_card_before8) {
		this.cnt_install_use_card_before8 = cnt_install_use_card_before8;
	}

	public String getCnt_install_use_card_before9() {
		return cnt_install_use_card_before9;
	}

	public void setCnt_install_use_card_before9(String cnt_install_use_card_before9) {
		this.cnt_install_use_card_before9 = cnt_install_use_card_before9;
	}

	public String getCnt_install_use_card_before10() {
		return cnt_install_use_card_before10;
	}

	public void setCnt_install_use_card_before10(String cnt_install_use_card_before10) {
		this.cnt_install_use_card_before10 = cnt_install_use_card_before10;
	}

	public String getCnt_install_use_card_before11() {
		return cnt_install_use_card_before11;
	}

	public void setCnt_install_use_card_before11(String cnt_install_use_card_before11) {
		this.cnt_install_use_card_before11 = cnt_install_use_card_before11;
	}

	public String getCnt_install_use_card_before12() {
		return cnt_install_use_card_before12;
	}

	public void setCnt_install_use_card_before12(String cnt_install_use_card_before12) {
		this.cnt_install_use_card_before12 = cnt_install_use_card_before12;
	}

	public String getAmt_install_use_before1() {
		return amt_install_use_before1;
	}

	public void setAmt_install_use_before1(String amt_install_use_before1) {
		this.amt_install_use_before1 = amt_install_use_before1;
	}

	public String getAmt_install_use_before2() {
		return amt_install_use_before2;
	}

	public void setAmt_install_use_before2(String amt_install_use_before2) {
		this.amt_install_use_before2 = amt_install_use_before2;
	}

	public String getAmt_install_use_before3() {
		return amt_install_use_before3;
	}

	public void setAmt_install_use_before3(String amt_install_use_before3) {
		this.amt_install_use_before3 = amt_install_use_before3;
	}

	public String getAmt_install_use_before4() {
		return amt_install_use_before4;
	}

	public void setAmt_install_use_before4(String amt_install_use_before4) {
		this.amt_install_use_before4 = amt_install_use_before4;
	}

	public String getAmt_install_use_before5() {
		return amt_install_use_before5;
	}

	public void setAmt_install_use_before5(String amt_install_use_before5) {
		this.amt_install_use_before5 = amt_install_use_before5;
	}

	public String getAmt_install_use_before6() {
		return amt_install_use_before6;
	}

	public void setAmt_install_use_before6(String amt_install_use_before6) {
		this.amt_install_use_before6 = amt_install_use_before6;
	}

	public String getAmt_install_use_before7() {
		return amt_install_use_before7;
	}

	public void setAmt_install_use_before7(String amt_install_use_before7) {
		this.amt_install_use_before7 = amt_install_use_before7;
	}

	public String getAmt_install_use_before8() {
		return amt_install_use_before8;
	}

	public void setAmt_install_use_before8(String amt_install_use_before8) {
		this.amt_install_use_before8 = amt_install_use_before8;
	}

	public String getAmt_install_use_before9() {
		return amt_install_use_before9;
	}

	public void setAmt_install_use_before9(String amt_install_use_before9) {
		this.amt_install_use_before9 = amt_install_use_before9;
	}

	public String getAmt_install_use_before10() {
		return amt_install_use_before10;
	}

	public void setAmt_install_use_before10(String amt_install_use_before10) {
		this.amt_install_use_before10 = amt_install_use_before10;
	}

	public String getAmt_install_use_before11() {
		return amt_install_use_before11;
	}

	public void setAmt_install_use_before11(String amt_install_use_before11) {
		this.amt_install_use_before11 = amt_install_use_before11;
	}

	public String getAmt_install_use_before12() {
		return amt_install_use_before12;
	}

	public void setAmt_install_use_before12(String amt_install_use_before12) {
		this.amt_install_use_before12 = amt_install_use_before12;
	}


}
