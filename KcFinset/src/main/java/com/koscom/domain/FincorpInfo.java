package com.koscom.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FincorpInfo implements Serializable{
	private static final long serialVersionUID = -5491449090925887481L;
	protected String cd_fc, cdFc; 			//금융기관코드
	protected String cd_fin;   				//금융기관업권 코드
	protected String no_biz_comp; 			//사업자번호
	protected String nm_fc; 				//금융기관명
	protected String nm_ceo; 				//대표자명
	protected String yn_use; 				//연계여부
	protected String yn_scrap;				//스크래핑 가능여부
	protected String yn_alliance; 			//제휴여부
	protected String cd_fc_coocon;			//쿠콘금융사코드
	protected String cd_addr_fc; 			//주소구분
	protected String post_fc; 				//우편번호1
	protected String addr1_fc; 				//주소1
	protected String addr2_fc; 				//주소2
	protected String tel;					//대표번호
	protected String home_page;				//홈페이지
	protected String path_file; 			//로그파일 경로
	protected String nm_file; 				//로그파일 명
	protected String nm_staff_contract; 	//계약 담당자 이름
	protected String hp_staff_contract; 	//계약 담당자 휴대폰번호
	protected String home_staff_contract; 	//계약 담당자 연락처
	protected String fax_staff_contract; 	//계약 담당자 팩스번호
	protected String email_staff_contract;  //계약 담당자 이메일
	protected String nm_staff_adjust; 		//정산 담당자 이름
	protected String hp_staff_adjust; 		//정산 담당자 휴대폰번호
	protected String home_staff_adjust; 	//정산 담당자 연락처
	protected String fax_staff_adjust; 		//정산 담당자 팩스번호
	protected String email_staff_adjust;  	//정산 담당자 이메일
	protected String id_emp_goods; 			//상품 담당자 이름
	protected String nm_staff_goods; 		//상품 담당자 이름
	protected String hp_staff_goods; 		//상품 담당자 휴대폰번호
	protected String home_staff_goods; 		//상품 담당자 연락처
	protected String fax_staff_goods; 		//상품 담당자 팩스번호
	protected String email_staff_goods;  	//상품 담당자 이메일
	protected String cl_service;			//서비스 이용 약관
	protected String cl_private;			//개인신용정보 약관
	private String id_frt;					// 최초등록자
	private String dt_frt;					// 최초등록일
	private String id_lst;					// 최종수정자
	private String dt_lst;					// 최종수정일
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getCd_fc() {
		return cd_fc;
	}
	public void setCd_fc(String cd_fc) {
		this.cd_fc = cd_fc;
	}
	public String getCdFc() {
		return cdFc;
	}
	public void setCdFc(String cdFc) {
		this.cdFc = cdFc;
	}
	public String getCd_fin() {
		return cd_fin;
	}
	public void setCd_fin(String cd_fin) {
		this.cd_fin = cd_fin;
	}
	public String getNo_biz_comp() {
		return no_biz_comp;
	}
	public void setNo_biz_comp(String no_biz_comp) {
		this.no_biz_comp = no_biz_comp;
	}
	public String getNm_fc() {
		return nm_fc;
	}
	public void setNm_fc(String nm_fc) {
		this.nm_fc = nm_fc;
	}
	public String getNm_ceo() {
		return nm_ceo;
	}
	public void setNm_ceo(String nm_ceo) {
		this.nm_ceo = nm_ceo;
	}
	public String getYn_use() {
		return yn_use;
	}
	public void setYn_use(String yn_use) {
		this.yn_use = yn_use;
	}
	public String getYn_scrap() {
		return yn_scrap;
	}
	public void setYn_scrap(String yn_scrap) {
		this.yn_scrap = yn_scrap;
	}
	public String getYn_alliance() {
		return yn_alliance;
	}
	public void setYn_alliance(String yn_alliance) {
		this.yn_alliance = yn_alliance;
	}
	public String getCd_fc_coocon() {
		return cd_fc_coocon;
	}
	public void setCd_fc_coocon(String cd_fc_coocon) {
		this.cd_fc_coocon = cd_fc_coocon;
	}
	public String getCd_addr_fc() {
		return cd_addr_fc;
	}
	public void setCd_addr_fc(String cd_addr_fc) {
		this.cd_addr_fc = cd_addr_fc;
	}
	public String getPost_fc() {
		return post_fc;
	}
	public void setPost_fc(String post_fc) {
		this.post_fc = post_fc;
	}
	public String getAddr1_fc() {
		return addr1_fc;
	}
	public void setAddr1_fc(String addr1_fc) {
		this.addr1_fc = addr1_fc;
	}
	public String getAddr2_fc() {
		return addr2_fc;
	}
	public void setAddr2_fc(String addr2_fc) {
		this.addr2_fc = addr2_fc;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHome_page() {
		return home_page;
	}
	public void setHome_page(String home_page) {
		this.home_page = home_page;
	}
	public String getPath_file() {
		return path_file;
	}
	public void setPath_file(String path_file) {
		this.path_file = path_file;
	}
	public String getNm_file() {
		return nm_file;
	}
	public void setNm_file(String nm_file) {
		this.nm_file = nm_file;
	}
	public String getNm_staff_contract() {
		return nm_staff_contract;
	}
	public void setNm_staff_contract(String nm_staff_contract) {
		this.nm_staff_contract = nm_staff_contract;
	}
	public String getHp_staff_contract() {
		return hp_staff_contract;
	}
	public void setHp_staff_contract(String hp_staff_contract) {
		this.hp_staff_contract = hp_staff_contract;
	}
	public String getHome_staff_contract() {
		return home_staff_contract;
	}
	public void setHome_staff_contract(String home_staff_contract) {
		this.home_staff_contract = home_staff_contract;
	}
	public String getFax_staff_contract() {
		return fax_staff_contract;
	}
	public void setFax_staff_contract(String fax_staff_contract) {
		this.fax_staff_contract = fax_staff_contract;
	}
	public String getEmail_staff_contract() {
		return email_staff_contract;
	}
	public void setEmail_staff_contract(String email_staff_contract) {
		this.email_staff_contract = email_staff_contract;
	}
	public String getNm_staff_adjust() {
		return nm_staff_adjust;
	}
	public void setNm_staff_adjust(String nm_staff_adjust) {
		this.nm_staff_adjust = nm_staff_adjust;
	}
	public String getHp_staff_adjust() {
		return hp_staff_adjust;
	}
	public void setHp_staff_adjust(String hp_staff_adjust) {
		this.hp_staff_adjust = hp_staff_adjust;
	}
	public String getHome_staff_adjust() {
		return home_staff_adjust;
	}
	public void setHome_staff_adjust(String home_staff_adjust) {
		this.home_staff_adjust = home_staff_adjust;
	}
	public String getFax_staff_adjust() {
		return fax_staff_adjust;
	}
	public void setFax_staff_adjust(String fax_staff_adjust) {
		this.fax_staff_adjust = fax_staff_adjust;
	}
	public String getEmail_staff_adjust() {
		return email_staff_adjust;
	}
	public void setEmail_staff_adjust(String email_staff_adjust) {
		this.email_staff_adjust = email_staff_adjust;
	}
	public String getId_emp_goods() {
		return id_emp_goods;
	}
	public void setId_emp_goods(String id_emp_goods) {
		this.id_emp_goods = id_emp_goods;
	}
	public String getNm_staff_goods() {
		return nm_staff_goods;
	}
	public void setNm_staff_goods(String nm_staff_goods) {
		this.nm_staff_goods = nm_staff_goods;
	}
	public String getHp_staff_goods() {
		return hp_staff_goods;
	}
	public void setHp_staff_goods(String hp_staff_goods) {
		this.hp_staff_goods = hp_staff_goods;
	}
	public String getHome_staff_goods() {
		return home_staff_goods;
	}
	public void setHome_staff_goods(String home_staff_goods) {
		this.home_staff_goods = home_staff_goods;
	}
	public String getFax_staff_goods() {
		return fax_staff_goods;
	}
	public void setFax_staff_goods(String fax_staff_goods) {
		this.fax_staff_goods = fax_staff_goods;
	}
	public String getEmail_staff_goods() {
		return email_staff_goods;
	}
	public void setEmail_staff_goods(String email_staff_goods) {
		this.email_staff_goods = email_staff_goods;
	}
	public String getCl_service() {
		return cl_service;
	}
	public void setCl_service(String cl_service) {
		this.cl_service = cl_service;
	}
	public String getCl_private() {
		return cl_private;
	}
	public void setCl_private(String cl_private) {
		this.cl_private = cl_private;
	}
	public String getId_frt() {
		return id_frt;
	}
	public void setId_frt(String id_frt) {
		this.id_frt = id_frt;
	}
	public String getDt_frt() {
		return dt_frt;
	}
	public void setDt_frt(String dt_frt) {
		this.dt_frt = dt_frt;
	}
	public String getId_lst() {
		return id_lst;
	}
	public void setId_lst(String id_lst) {
		this.id_lst = id_lst;
	}
	public String getDt_lst() {
		return dt_lst;
	}
	public void setDt_lst(String dt_lst) {
		this.dt_lst = dt_lst;
	}

}