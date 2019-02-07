package com.koscom.common.fulltext;

import com.koscom.common.fulltext.FulltextField.FieldType;

/**
 * 전문 처리용 - 전문의 필드 정의용 클래스
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
public class FulltextField {
	public enum FieldType {
		NUMBER, CHAR, FLOAT
	};
	
	private String name;
	private String nameKr;
	private int fieldSize;
	private FieldType fieldType;
	private boolean required;
	
	public FulltextField(String nameKr, String name, int fieldSize, FieldType fieldType, boolean required) {
		// super();
		
		this.name = name;
		this.nameKr = nameKr;
		this.fieldSize = fieldSize;
		this.fieldType = fieldType;
		this.required = required;
	}
	
	public String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	public String getNameKr() {
		return nameKr;
	}
	protected void setNameKr(String nameKr) {
		this.nameKr = nameKr;
	}
	public int getFieldSize() {
		return fieldSize;
	}
	protected void setFieldSize(int fieldSize) {
		this.fieldSize = fieldSize;
	}
	public FieldType getFieldType() {
		return fieldType;
	}
	protected void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}
	public boolean isRequired() {
		return required;
	}
	protected void setRequired(boolean required) {
		this.required = required;
	}
}
