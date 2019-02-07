package com.koscom.common.fulltext;

/**
 * 전문 처리용 - 전문의 해더 정의용 클래스
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
public class FulltextHeaderVO extends FulltextVO <Object> {
	private static FulltextService server = new FulltextService();
	public static String CreateOutJson(String fulltext) {
		return server.CreateOutJson("해더전문", fulltext, headerFields, null);
	}

	private static final FulltextField [] headerFields = {
			new FulltextField("전문길이", "FulltextLen", 6, FulltextField.FieldType.CHAR, true),
			new FulltextField("UUID", "UuId", 40, FulltextField.FieldType.CHAR, true),
			new FulltextField("서비스코드", "ServiceCd", 3, FulltextField.FieldType.CHAR, true),
			new FulltextField("업무구분", "BizTp", 1, FulltextField.FieldType.CHAR, true),
			new FulltextField("기관코드분류", "FnIttTp", 1, FulltextField.FieldType.CHAR, true),
			new FulltextField("수신 기관코드", "RcvIttCd", 6, FulltextField.FieldType.CHAR, true),
			new FulltextField("발신 기관코드", "SndIttCd", 6, FulltextField.FieldType.CHAR, true),
			new FulltextField("조작자ID", "RegId", 8, FulltextField.FieldType.CHAR, true),
			new FulltextField("로그인여부", "LoginYn", 1, FulltextField.FieldType.CHAR, true),
			new FulltextField("프로그램ID", "PgmId", 13, FulltextField.FieldType.CHAR, true),
			new FulltextField("발생매체구분", "MediTp", 1, FulltextField.FieldType.CHAR, true),
			new FulltextField("응답(에러)코드", "ErrCd", 4, FulltextField.FieldType.CHAR, true),
			new FulltextField("에러메시지", "ErrMsg", 80, FulltextField.FieldType.CHAR, true),
			new FulltextField("연속여부", "LoopYn", 1, FulltextField.FieldType.CHAR, true),
			new FulltextField("연속키", "LoopKey", 18, FulltextField.FieldType.CHAR, true),
			new FulltextField("Filler", "Filler", 31, FulltextField.FieldType.CHAR, true)
	};
	
	private String restURL = "";	// RESTful API Server URL
	
	private String fulltextLen = ""; //전문길이
	private String uuId = ""; //UUID
	private String serviceCd = ""; //서비스코드
	private String bizTp = ""; //업무구분
	private String fnIttTp = ""; //기관코드분류
	private String rcvIttCd = ""; //수신 기관코드
	private String sndIttCd = ""; //발신 기관코드
	private String regId = ""; //조작자ID
	private String loginYn = ""; //로그인여부
	private String pgmId = ""; //프로그램ID
	private String mediTp = ""; //발생매체구분
	private String errCd = ""; //응답(에러)코드
	private String errMsg = ""; //에러메시지
	private String loopYn = ""; //연속여부
	private String loopKey = ""; //연속키
	private String filler = ""; //Filler

	public String getRestURL() {
		return restURL;
	}
	public void setRestURL(String restURL) {
		this.restURL = restURL;
	}
	public String getFulltextLen() {
		return fulltextLen;
	}
	public void setFulltextLen(String fulltextLen) {
		this.fulltextLen = fulltextLen;
	}
	public String getUuId() {
		return uuId;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	public String getServiceCd() {
		return serviceCd;
	}
	public void setServiceCd(String serviceCd) {
		this.serviceCd = serviceCd;
	}
	public String getBizTp() {
		return bizTp;
	}
	public void setBizTp(String bizTp) {
		this.bizTp = bizTp;
	}
	public String getFnIttTp() {
		return fnIttTp;
	}
	public void setFnIttTp(String fnIttTp) {
		this.fnIttTp = fnIttTp;
	}
	public String getRcvIttCd() {
		return rcvIttCd;
	}
	public void setRcvIttCd(String rcvIttCd) {
		this.rcvIttCd = rcvIttCd;
	}
	public String getSndIttCd() {
		return sndIttCd;
	}
	public void setSndIttCd(String sndIttCd) {
		this.sndIttCd = sndIttCd;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getLoginYn() {
		return loginYn;
	}
	public void setLoginYn(String loginYn) {
		this.loginYn = loginYn;
	}
	public String getPgmId() {
		return pgmId;
	}
	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}
	public String getMediTp() {
		return mediTp;
	}
	public void setMediTp(String mediTp) {
		this.mediTp = mediTp;
	}
	public String getErrCd() {
		return errCd;
	}
	public void setErrCd(String errCd) {
		this.errCd = errCd;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getLoopYn() {
		return loopYn;
	}
	public void setLoopYn(String loopYn) {
		this.loopYn = loopYn;
	}
	public String getLoopKey() {
		return loopKey;
	}
	public void setLoopKey(String loopKey) {
		this.loopKey = loopKey;
	}
	public String getFiller() {
		return filler;
	}
	public void setFiller(String filler) {
		this.filler = filler;
	}
	public static FulltextField[] getHeaderfields() {
		return headerFields;
	}
}
