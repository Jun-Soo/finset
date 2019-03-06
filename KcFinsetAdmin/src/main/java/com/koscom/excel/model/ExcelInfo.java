package com.koscom.excel.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class ExcelInfo implements Serializable{
	
	private static final long serialVersionUID = 8811623314916172496L;
	
	private String id_emp;
	private String SheetName;
	private String code_group;
	private List<HashMap<String, Object>> list;
	
	public ExcelInfo() {}

	public String getSheetName() {
		return SheetName;
	}

	public void setSheetName(String sheetName) {
		SheetName = sheetName;
	}
	
	public String getId_emp() {
		return id_emp;
	}

	public void setId_emp(String id_emp) {
		this.id_emp = id_emp;
	}

	public String getCode_group() {
		return code_group;
	}

	public void setCode_group(String code_group) {
		this.code_group = code_group;
	}

	public List<HashMap<String, Object>> getList() {
		return list;
	}

	public void setList(List<HashMap<String, Object>> list) {
		this.list = list;
	}

}
