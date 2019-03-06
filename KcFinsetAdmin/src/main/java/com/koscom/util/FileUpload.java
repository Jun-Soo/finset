package com.koscom.util;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;

public class FileUpload {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUpload.class);
	/**
	 * 파일 업로드
	 * @param file
	 * @param multipartFile
	 * @param fileName 
	 * @param isMultipartFile TRUE 일경우 MultipartFile 업로드 
	 * @return
	 * @throws Exception
	 */
	public boolean fileUpload(File file, MultipartFile multipartFile, String fileName, boolean isMultipartFile) throws IOException {
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		
		try {
			// 파일객체 생성
			File newfile = new File(fileName);
			logger.debug("newFileName["+fileName+"]");
			// 로컬파일을 읽어 서버에 저장
			InputStream in = null;
			
			if ( isMultipartFile ) {
				in = (InputStream) multipartFile.getInputStream();
			} 
			else {
				in = new FileInputStream(file);
			}
				
			bin = new BufferedInputStream(in);
			bout = new BufferedOutputStream(new FileOutputStream(newfile));
		
			int read = 0;
			byte[] buf = new byte[1024];
			while ((read = bin.read(buf, 0, buf.length)) != -1) {
				bout.write(buf, 0, read);
			}
		}
		finally {
			if (bin != null) {
				bin.close();
			}
			if (bout != null) {
				bout.close();
			}
		}
		return true;
	}
	
	/**
	 * 해당 파일명으로 저장된 데이터가 존재하면 새로운 파일명 리턴
	 * @param pFileName
	 * @return
	 * @throws Exception
	 */
	public String nameCheck(String pFileName) throws FinsetException  {
        String fileName = pFileName;
        fileName = StringUtil.nullToEmpty(fileName);

        if ( StringUtil.isEmpty(fileName) ) return null;

        // 파일확장자 체크
        fileNmCheck(fileName);

        // 저장경로 존재여부 검사를 위한 경로세팅
        String checkPath = fileName.substring(0, fileName.lastIndexOf('/') + 1);

        logger.info("checkPath : "+checkPath);
        logger.info("checkPath : "+checkPath);
        logger.info("checkPath : "+checkPath);
        // 저장경로 존재여부 검사, 폴더가 존재 하지 않으면 생성
        File dir = new File(checkPath);
        dir.mkdirs();

        // 해당경로 같은 파일명 존재여부 검사
        // 같은 파일명이 존재할경우 파일명 + "_i" 추가
        int i=0;
        String tmpName = fileName;
        while(true) {
            i++;
            dir = new File(tmpName);
            if(!dir.exists()){
                break;
            } else {
                // 파일명 생성
                tmpName = StringUtil.getPickupFirst(fileName) + "_" + i + "." + StringUtil.getPickupLast(fileName);
                continue;
            }
        }
        fileName = tmpName;

		return fileName;
	}
	
	// 파일 확장자 체크
	private void fileNmCheck(String fileName) throws FinsetException{
		String tmpLow = fileName.toLowerCase();
		if ( tmpLow.endsWith(".tiff") || tmpLow.endsWith(".tif") || tmpLow.endsWith(".jpeg") 
				|| tmpLow.endsWith(".jpg") ||tmpLow.endsWith(".gif") || tmpLow.endsWith(".bmp")
				|| tmpLow.endsWith(".wav") || tmpLow.endsWith(".mp3")) {
		}
		else if( tmpLow.endsWith(".jsp") || tmpLow.endsWith(".sh") ||tmpLow.endsWith(".htm") 
				|| tmpLow.endsWith(".html") ||tmpLow.endsWith(".php") || tmpLow.endsWith(".php3") 
				||tmpLow.endsWith(".php4") || tmpLow.endsWith(".phps")|| tmpLow.endsWith(".phtml") 
				|| tmpLow.endsWith(".class") || tmpLow.endsWith(".java") || tmpLow.endsWith(".js")
				||tmpLow.endsWith(".cgi") || tmpLow.endsWith(".py")) {
			throw new FinsetException("등록 할 수 없는 파일 형식입니다.");
		}
	}	
	
	/**
	 * 파일명을 리턴해준다.
	 * @return
	 */
	public String getFileName(String pFileName) throws FinsetException {
		String fileName = pFileName;
		if ( StringUtil.isEmpty(fileName) ) {
			throw new FinsetException("FileUpload [파일명 오류] 입니다.");
		}
		String root_dir = "";
		logger.info("OS : "+System.getProperty("os.name"));

		if(System.getProperty("os.name").indexOf("Windows") >= 0 ) {
			root_dir = "C:/pds/finset";
		} else {
			CodeManager codeManager = (CodeManager) SpringApplicationContext.getBean("codeManager");
			CodeInfo code = codeManager.getCodeInfo("_CONF_SYSTEM", "PATH_PDS");
			root_dir = code.getNm_code();
		}
//			CodeManager codeManager = (CodeManager) SpringApplicationContext.getBean("codeManager");
//			CodeInfo code = codeManager.getCodeInfo("_CONF_SYSTEM", "PATH_PDS");

		fileName = root_dir //경로 업체아이디
				 + "/"
				 + DateUtil.getCurrentDate() // 년월일 예:20100101
				 + "/"
				 + getRandomString() // 랜덤문자 10글자
//					 + StringUtil.getPickupFirst(fileName)
				 + "."
				 + StringUtil.getPickupLast(fileName); // 확장자
		return fileName;

	}
	
	private String getRandomString() {
		StringBuffer data = new StringBuffer();
		for (int i = 1; i <= 10; i++) {
			char ch = (char) ((Math.random() * 26) + 97);
			data.append(ch);
		}
		return data.toString();
	}
	
	/**
	 * url에서 파일명을 리턴해준다
	 * @param url_attach_file
	 * @param file_extension
	 * @return
	 * @throws Exception
	 */
	public String getDownFileName(String url_attach_file , String file_extension ) throws FinsetException {
		
		String file_name = "";
		String extension = "";
		
		if(url_attach_file.contains("&")){
			file_name = url_attach_file.substring(url_attach_file.lastIndexOf("."));
			if(file_name.indexOf("&")>0){
				extension = file_name.substring(0 ,file_name.indexOf("&"));
			}else{
				extension = file_name;
			}
			file_name = url_attach_file.substring(url_attach_file.lastIndexOf("=") +1);
			if(StringUtil.isEmpty(file_name)){
				file_name = url_attach_file.substring(url_attach_file.indexOf("=") +1);
			}
			if(file_name.contains(extension)){
				file_name = file_name.replaceAll(extension, "").replaceAll("&", "");
				fileNmCheck(extension);
				file_name = file_name + extension;
			}else{
				fileNmCheck(extension);
				file_name = file_name + extension;
			}
			
		}else{
			file_name = url_attach_file.substring(url_attach_file.lastIndexOf("."));
			if(file_name.indexOf("/")>0){
				extension = file_name.substring(0 ,file_name.indexOf("/"));
			}else{
				extension = file_name;
			}
			file_name = url_attach_file.substring(url_attach_file.lastIndexOf("/") + 1);
			if(StringUtil.isEmpty(file_name)){
				file_name = url_attach_file.substring(url_attach_file.indexOf("/") + 1);
			}
			if(file_name.contains(extension)){
				file_name = file_name.replaceAll(extension, "").replaceAll("/", "");
				fileNmCheck(extension);
				file_name = file_name + extension;
				
			}else{
				fileNmCheck(extension);
				file_name = file_name + extension;
			}
		}
		
		if(!StringUtil.isEmpty(file_extension) && StringUtil.isEmpty(extension) ) {
			file_name = file_name + "."+file_extension;
		}
		
		return file_name;
	}

}
