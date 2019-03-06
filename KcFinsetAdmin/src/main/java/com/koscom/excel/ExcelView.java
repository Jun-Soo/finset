package com.koscom.excel;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.AbstractView;

import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import com.koscom.excel.model.ExcelInfo;
import com.koscom.util.DateUtil;


public class ExcelView extends AbstractView {
	
	@Autowired
	private CodeManager codeManager;
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelView.class);
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> map , HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		buildExcelDocument(map, workbook, request, response);

		OutputStream os = response.getOutputStream();
		workbook.write(os);
		os.close();
		  
	}
	
	protected void buildExcelDocument(Map<String, Object> map, XSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelInfo excelInfo = (ExcelInfo) map.get("ExcelInfo");
		List<CodeInfo> codeList =  codeManager.listCodeInfo(excelInfo.getCode_group());
		
		// 시트 생성
		XSSFSheet sheet = workbook.createSheet(excelInfo.getSheetName());
		
		// 스타일 생성
		XSSFCellStyle headCellStyle = getCellStryle(workbook, "HEAD");
		XSSFCellStyle bobyCellStyle = getCellStryle(workbook, "BODY");
		
		int rowNum = 0; // Row 넘버변수
		int cellNum = 0; // Cell 넘버변수
		
		XSSFRow headerRow = sheet.createRow(0);
		
		for ( HashMap<String, Object> hashMap : excelInfo.getList() ) {
			
			XSSFRow bodyRow = sheet.createRow(rowNum+1);
			cellNum = 0;
			for(CodeInfo codeInfo : codeList){
				//key설정
				
				String[] splitList = codeInfo.getNm_code().split(" ");
				String key = splitList[splitList.length-1].trim().toUpperCase();
				
				// 첫번재 로우에 타이틀 생성
				if ( rowNum == 0 ) {
					String keyCell = "";
					if(key.contains("_")) {
						keyCell = key.split("_")[1];
					} else {
						keyCell = key;			
					}
					createRow(sheet, keyCell, headerRow, cellNum, headCellStyle);	
				}

				createRow(sheet, getMapValueIsNull(hashMap, key), bodyRow, cellNum, bobyCellStyle);
				cellNum++;
			}
			rowNum++;
		}
		
		String fileName = "";
	
		if(map.get("FileName") != null ){
			fileName = new String(((String)(map.get("FileName"))).getBytes("euc-kr"),"ISO8859-1");
			
		}else{
			fileName = "excelList";
		}

		fileName += ("_"+DateUtil.getCurrentDate()+".xlsx");
		
		response.setHeader("Content-Type", "application/vnd.ms-xls");
		response.setHeader("Content-Disposition", "attachement;  fileName=\""+fileName+ "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");

	}
	
	private String getMapValueIsNull(HashMap<String, Object> map, String key) {
		if ( map.get(key) != null ) {
			return "" + map.get(key);
		}
		return "";
	}
	
	// 로우생성
	private void createRow(Sheet sheet, String cellValue, XSSFRow row, int cellNum, XSSFCellStyle style) {
		//셀생성
		XSSFCell cell = row.createCell(cellNum);
		cell.setCellStyle(style);
		cell.setCellValue(cellValue);
	}
	
	private XSSFCellStyle getCellStryle(XSSFWorkbook workbook, String type) {
		
		XSSFCellStyle style = workbook.createCellStyle();
		
		XSSFFont font = workbook.createFont();
		font.setFontName("맑은 고딕");
		
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		
		if("HEAD".equals(type)){
			style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			style.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
			font.setBoldweight((short) 700);
		}else{
			style.setBorderBottom(XSSFCellStyle.BORDER_THIN);   
		}
		
		style.setFont(font);
		return style;
	}

}
