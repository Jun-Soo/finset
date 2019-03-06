package com.koscom.fincorp.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import com.koscom.domain.FincorpInfo;
import com.koscom.util.StringUtil;

public class FincorpVO extends FincorpInfo implements Serializable {

	private static final long serialVersionUID = -5283835109034749303L;

	@JsonIgnore
	private MultipartFile file1;

	//for file transformation
	private byte[] fileArray;
	private byte[] img_bi;
	private String fileName;
	private int fileSize;

	private String query;
	private String type;
	private String file_name;

	public MultipartFile getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}

	public void setPost_fc_tmp(String[] post_fc_tmp) {
		super.post_fc = StringUtil.addChar(post_fc_tmp, ",");
	}
	public String getPost6_fc() {
		return StringUtil.splitStr(super.post_fc, ",", 1);
	}

	public String getPost5_fc() {
		return StringUtil.splitStr(super.post_fc, ",", 2);
	}

	public String getHp_con_idx1() {
		return StringUtil.splitStr(super.hp_staff_contract, "-", 1);
	}

	public String getHp_con_idx2() {
		return StringUtil.splitStr(super.hp_staff_contract, "-", 2);
	}

	public String getHp_con_idx3() {
		return StringUtil.splitStr(super.hp_staff_contract, "-", 3);
	}

	public String getHome_con_idx1() {
		return StringUtil.splitStr(super.home_staff_contract, "-", 1);
	}

	public String getHome_con_idx2() {
		return StringUtil.splitStr(super.home_staff_contract, "-", 2);
	}

	public String getHome_con_idx3() {
		return StringUtil.splitStr(super.home_staff_contract, "-", 3);
	}

	public String getFax_con_idx1() {
		return StringUtil.splitStr(super.fax_staff_contract, "-", 1);
	}

	public String getFax_con_idx2() {
		return StringUtil.splitStr(super.fax_staff_contract, "-", 2);
	}

	public String getFax_con_idx3() {
		return StringUtil.splitStr(super.fax_staff_contract, "-", 3);
	}

	public String getEmail_con_idx1() {
		return StringUtil.splitStr(super.email_staff_contract, "@", 1);
	}

	public String getEmail_con_idx2() {
		return StringUtil.splitStr(super.email_staff_contract, "@", 2);
	}

	public String getHp_adj_idx1() {
		return StringUtil.splitStr(super.hp_staff_adjust, "-", 1);
	}

	public String getHp_adj_idx2() {
		return StringUtil.splitStr(super.hp_staff_adjust, "-", 2);
	}

	public String getHp_adj_idx3() {
		return StringUtil.splitStr(super.hp_staff_adjust, "-", 3);
	}

	public String getHome_adj_idx1() {
		return StringUtil.splitStr(super.home_staff_adjust, "-", 1);
	}

	public String getHome_adj_idx2() {
		return StringUtil.splitStr(super.home_staff_adjust, "-", 2);
	}

	public String getHome_adj_idx3() {
		return StringUtil.splitStr(super.home_staff_adjust, "-", 3);
	}

	public String getFax_adj_idx1() {
		return StringUtil.splitStr(super.fax_staff_adjust, "-", 1);
	}

	public String getFax_adj_idx2() {
		return StringUtil.splitStr(super.fax_staff_adjust, "-", 2);
	}

	public String getFax_adj_idx3() {
		return StringUtil.splitStr(super.fax_staff_adjust, "-", 3);
	}

	public String getEmail_adj_idx1() {
		return StringUtil.splitStr(super.email_staff_adjust, "@", 1);
	}

	public String getEmail_adj_idx2() {
		return StringUtil.splitStr(super.email_staff_adjust, "@", 2);
	}

	public String getHp_god_idx1() {
		return StringUtil.splitStr(super.hp_staff_goods, "-", 1);
	}

	public String getHp_god_idx2() {
		return StringUtil.splitStr(super.hp_staff_goods, "-", 2);
	}

	public String getHp_god_idx3() {
		return StringUtil.splitStr(super.hp_staff_goods, "-", 3);
	}

	public String getHome_god_idx1() {
		return StringUtil.splitStr(super.home_staff_goods, "-", 1);
	}

	public String getHome_god_idx2() {
		return StringUtil.splitStr(super.home_staff_goods, "-", 2);
	}

	public String getHome_god_idx3() {
		return StringUtil.splitStr(super.home_staff_goods, "-", 3);
	}

	public String getFax_god_idx1() {
		return StringUtil.splitStr(super.fax_staff_goods, "-", 1);
	}

	public String getFax_god_idx2() {
		return StringUtil.splitStr(super.fax_staff_goods, "-", 2);
	}

	public String getFax_god_idx3() {
		return StringUtil.splitStr(super.fax_staff_goods, "-", 3);
	}

	public String getEmail_god_idx1() {
		return StringUtil.splitStr(super.email_staff_goods, "@", 1);
	}

	public String getEmail_god_idx2() {
		return StringUtil.splitStr(super.email_staff_goods, "@", 2);
	}

	public void setHp_con_tmp(String[] hp_con_tmp) {
		super.hp_staff_contract = StringUtil.addChar(hp_con_tmp, "-");
	}
	public void setHome_con_tmp(String[] home_con_tmp) {
		super.home_staff_contract = StringUtil.addChar(home_con_tmp, "-");
	}

	public void setFax_con_tmp(String[] fax_con_tmp) {
		super.fax_staff_contract = StringUtil.addChar(fax_con_tmp, "-");
	}
	public void setEmail_con_tmp(String[] email_con_tmp) {
		super.email_staff_contract = StringUtil.addChar(email_con_tmp, "@");
	}

	public void setHp_adj_tmp(String[] hp_adj_tmp) {
		super.hp_staff_adjust = StringUtil.addChar(hp_adj_tmp, "-");
	}
	public void setHome_adj_tmp(String[] home_adj_tmp) {
		super.home_staff_adjust = StringUtil.addChar(home_adj_tmp, "-");
	}

	public void setFax_adj_tmp(String[] fax_adj_tmp) {
		super.fax_staff_adjust = StringUtil.addChar(fax_adj_tmp, "-");
	}
	public void setEmail_adj_tmp(String[] email_adj_tmp) {
		super.email_staff_adjust = StringUtil.addChar(email_adj_tmp, "@");
	}

	public void setHp__god_tmp(String[] hp__god_tmp) {
		super.hp_staff_goods = StringUtil.addChar(hp__god_tmp, "-");
	}
	public void setHome__god_tmp(String[] home__god_tmp) {
		super.home_staff_goods = StringUtil.addChar(home__god_tmp, "-");
	}

	public void setFax__god_tmp(String[] fax__god_tmp) {
		super.fax_staff_goods = StringUtil.addChar(fax__god_tmp, "-");
	}
	public void setEmail__god_tmp(String[] email__god_tmp) {
		super.email_staff_goods = StringUtil.addChar(email__god_tmp, "@");
	}
	public byte[] getFileArray() {
		return fileArray;
	}
	public void setFileArray(byte[] fileArray) {
		this.fileArray = fileArray;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public byte[] getImg_bi() {
		return img_bi;
	}

	public void setImg_bi(byte[] img_bi) {
		this.img_bi = img_bi;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
}