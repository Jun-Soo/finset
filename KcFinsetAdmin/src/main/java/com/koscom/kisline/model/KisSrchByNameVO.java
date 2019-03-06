package com.koscom.kisline.model;

import java.io.Serializable;

public class KisSrchByNameVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1516171429322722141L;
	protected String no_bunch = "";		//순번
	protected String no_person = "";	//회원고유번호
	protected String kiscode = "";		//*	업체코드
	protected String business = "";		//*	사업자번호
	protected String crpno = "";				//	법인등록번호
	protected String stockcd = "";				//	주식코드
	protected String stockcode = "";				//	주식코드
	protected String korentrnm = "";	//*	업체명
	protected String korreprnm = "";	//*	대표자명
	protected String sanup = "";				//	산업명
	protected String sanupcode = "";			//	산업코드(표준산업분류 9차)
	protected String aa22_inmul_nm_cd = "";		//	경영진현황의 인물코드 목록(, 로 구분)
	protected String epr_cnu_yn = "";			//	기업존속여부(Y:정상, N:폐업)
	protected String eprdatastsdivcd = "";		//	기업자료상태구분코드(03:피흡수합병 경우만 처리)
	protected String eprmdydivcd = "";			//	기업주체구분코드(1:일반, 2:공기관, 3:비영리단체)
	protected String scaledivcd = "";			//	기업규모구분코드
	protected String etl_ipc_yn = "";			//	외부감사여부(Y:외감, N:비외감)
	protected String ltgmktdivcd = "";			//	상장시장구분코드(1:상장, 2:코스닥, 3:코넥스, 4:K-OTC, 9:기타)
	protected String amnisuyn = "";				//	관리종목여부(Y:관리종목해당(자본시장정보))
	protected String obz_date = "";				//	개업일자
	protected int empnum = 0;					//	종업원수
	protected String fadivcd = "";				//	"재무구분코드(00=제조, AA=은행, BB=증권, CC=생보, DD=손보, EE=신용금고,FF=종금, GG=투신, HH=리스, II=카드, JJ=창투, KK=할부금융, ZZ=기타)"
	protected String koraddr = "";		//*	도로명주소
	protected String nolt_koraddr = "";			//	지번주소
	protected String tel = "";					//	전화번호
	protected String nolt_engaddr = "";			//	영문 지번주소
	protected String engaddr = "";				//	영문 도로명주소
	protected String engentrnm = "";			//	영문 업체명
	protected String engreprnm = "";			//	영문 대표자명
	public String getNo_bunch() {
		return no_bunch;
	}
	public void setNo_bunch(String no_bunch) {
		this.no_bunch = no_bunch;
	}
	public String getNo_person() {
		return no_person;
	}
	public void setNo_person(String no_person) {
		this.no_person = no_person;
	}
	public String getKiscode() {
		return kiscode;
	}
	public void setKiscode(String kiscode) {
		this.kiscode = kiscode;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getCrpno() {
		return crpno;
	}
	public void setCrpno(String crpno) {
		this.crpno = crpno;
	}
	public String getStockcd() {
		return stockcd;
	}
	public void setStockcd(String stockcd) {
		this.stockcd = stockcd;
	}
	public String getStockcode() {
		return stockcode;
	}
	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}
	public String getKorentrnm() {
		return korentrnm;
	}
	public void setKorentrnm(String korentrnm) {
		this.korentrnm = korentrnm;
	}
	public String getKorreprnm() {
		return korreprnm;
	}
	public void setKorreprnm(String korreprnm) {
		this.korreprnm = korreprnm;
	}
	public String getSanup() {
		return sanup;
	}
	public void setSanup(String sanup) {
		this.sanup = sanup;
	}
	public String getSanupcode() {
		return sanupcode;
	}
	public void setSanupcode(String sanupcode) {
		this.sanupcode = sanupcode;
	}
	public String getAa22_inmul_nm_cd() {
		return aa22_inmul_nm_cd;
	}
	public void setAa22_inmul_nm_cd(String aa22_inmul_nm_cd) {
		this.aa22_inmul_nm_cd = aa22_inmul_nm_cd;
	}
	public String getEpr_cnu_yn() {
		return epr_cnu_yn;
	}
	public void setEpr_cnu_yn(String epr_cnu_yn) {
		this.epr_cnu_yn = epr_cnu_yn;
	}
	public String getEprdatastsdivcd() {
		return eprdatastsdivcd;
	}
	public void setEprdatastsdivcd(String eprdatastsdivcd) {
		this.eprdatastsdivcd = eprdatastsdivcd;
	}
	public String getEprmdydivcd() {
		return eprmdydivcd;
	}
	public void setEprmdydivcd(String eprmdydivcd) {
		this.eprmdydivcd = eprmdydivcd;
	}
	public String getScaledivcd() {
		return scaledivcd;
	}
	public void setScaledivcd(String scaledivcd) {
		this.scaledivcd = scaledivcd;
	}
	public String getEtl_ipc_yn() {
		return etl_ipc_yn;
	}
	public void setEtl_ipc_yn(String etl_ipc_yn) {
		this.etl_ipc_yn = etl_ipc_yn;
	}
	public String getLtgmktdivcd() {
		return ltgmktdivcd;
	}
	public void setLtgmktdivcd(String ltgmktdivcd) {
		this.ltgmktdivcd = ltgmktdivcd;
	}
	public String getAmnisuyn() {
		return amnisuyn;
	}
	public void setAmnisuyn(String amnisuyn) {
		this.amnisuyn = amnisuyn;
	}
	public String getObz_date() {
		return obz_date;
	}
	public void setObz_date(String obz_date) {
		this.obz_date = obz_date;
	}
	public int getEmpnum() {
		return empnum;
	}
	public void setEmpnum(int empnum) {
		this.empnum = empnum;
	}
	public String getFadivcd() {
		return fadivcd;
	}
	public void setFadivcd(String fadivcd) {
		this.fadivcd = fadivcd;
	}
	public String getKoraddr() {
		return koraddr;
	}
	public void setKoraddr(String koraddr) {
		this.koraddr = koraddr;
	}
	public String getNolt_koraddr() {
		return nolt_koraddr;
	}
	public void setNolt_koraddr(String nolt_koraddr) {
		this.nolt_koraddr = nolt_koraddr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getNolt_engaddr() {
		return nolt_engaddr;
	}
	public void setNolt_engaddr(String nolt_engaddr) {
		this.nolt_engaddr = nolt_engaddr;
	}
	public String getEngaddr() {
		return engaddr;
	}
	public void setEngaddr(String engaddr) {
		this.engaddr = engaddr;
	}
	public String getEngentrnm() {
		return engentrnm;
	}
	public void setEngentrnm(String engentrnm) {
		this.engentrnm = engentrnm;
	}
	public String getEngreprnm() {
		return engreprnm;
	}
	public void setEngreprnm(String engreprnm) {
		this.engreprnm = engreprnm;
	}
}