package com.koscom.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;

public class FileDownload {
	
	private static final Logger logger = LoggerFactory.getLogger(FileDownload.class);
	
	private String returnMsg = null; // 메세지 저장

	/**
	 * Method Desc : URL 파일 다운로드
	 * @author KKW <farmerkw@crizen.com>
	 * @since 2010. 4. 15.
	 * @param pDownloadUrl : 다운로드 URL
	 * @param saveFilePath : 저장할 경로
	 * @return 파일 다운로드 및 저장에 성공하면 true 실패하면 false
	 * @throws IOException 
	 */
	public boolean getUrlFileDownLoad(String pDownloadUrl, String saveFilePath) {
        String downloadUrl = pDownloadUrl;
		// 메세지
		returnMsg = null;
		
		// 다운로드 성공 여부 체크 기본적으로 실패로 초기화
		boolean isProcess = false;
		logger.debug("다운받을 URL : [" + downloadUrl + "]");
		
		// 공백 인코딩
		downloadUrl = downloadUrl.replaceAll(" ", "%20");
		logger.debug("Decode URL : [" + downloadUrl + "]");
		
		// 로컬에 파일이 존재하는지 체크하기 위한 경로
		String downloadUrlFilePath = saveFilePath.substring(0, saveFilePath.lastIndexOf('/') + 1);
		
		// 경로 검사
		File dir = new File(downloadUrlFilePath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		File file = new File(saveFilePath);

		HttpURLConnection connection = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;

		try {
			URL url = new URL(downloadUrl);

			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(20000);
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream in = connection.getInputStream();
				bin = new BufferedInputStream(in);
				bout = new BufferedOutputStream(new FileOutputStream(file));
				int read = 0;
				byte[] buf = new byte[1024];
				while ((read = bin.read(buf, 0, buf.length)) != -1) {
					bout.write(buf, 0, read);
				}
				isProcess = true;
			} else {
				returnMsg = "[파일 접근 실패(" + connection.getResponseCode() + ")]";
				logger.error(returnMsg);
			}
		} catch (FileNotFoundException e) {
			returnMsg = "[파일 저장 실패)]" + e.getMessage();
			logger.error(returnMsg);
		} catch (IOException e) {
			returnMsg = "[파일 접근 실패(URL)]" + e.getMessage();
			logger.error(returnMsg);
		} finally {
			if( bin != null ) {
				try {
					bin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if( bout != null ){
				try {
					bout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				connection.disconnect();
			}
		}

		return isProcess;
	}

	public String getReturnMsg() {
		return returnMsg;
	}
	
	/**
	 * @Method Name  : getUrlFileDownLoad
	 * @Date         : 2010. 10. 23.
	 * @author       : khlim@crizen.com
	 * @Method Comment : 매체사에서 접수된 url 경로를 파일 이름(체크후)으로 서버에 저장을 요청
	 * 						1.	정상이면 서버 로컬에 저장된 파일 이름을 리턴, 
	 * 						2.	실패면 공백을 리턴 
	 * @param downloadUrl
	 * @return
	 */
	public String getUrlFileDownLoad(String downloadUrl) throws FinsetException{
		
		//1 . 파일명이 서버에 있는지 검사
		//2.  있으면 새로운 파일명 생성
		//3.  없으면 그대로 요청
		
		String resutlMsg = "";
		String refFileName = "";
		
		refFileName = getDownFileName(downloadUrl , "");
		refFileName = nameCheck(refFileName);
		refFileName = getFileName( refFileName );
				
		if ( getUrlFileDownLoad(downloadUrl, refFileName) ){
			resutlMsg = refFileName; 	//저장된 파일명 
		}
		
		return resutlMsg;

	}
	
	/**
	 * @Method Name  : getFileDonload
	 * @Date         : 2010. 10. 30.
	 * @author       : khlim@crizen.com
	 * @Method Comment : MultipartFile 의 file 정보와 서버 destination 저장 경로를 인자로 파일을 저장한다.
	 * @param attachInfo
	 * @param destination
	 * @return
	 */
	/*public boolean getFileDonload( AttachInfo attachInfo, String destination) throws Exception{
		return getFileCopy(attachInfo.getFile(), destination);
	}*/
	
	/**
	 * @Method Name  : downFile
	 * @Date         : 2010. 10. 13.
	 * @author       : khlim@crizen.com
	 * @Method Comment : 파일 다운로드 (파일 저장경로가 포함된 상태) -  
	 * 					 파일 저장경로는 파일 특성마다 틀릴수가 있으므로 요청 메소드에서 결정한다.
	 * @param response
	 * @param saveFileNm	: 파일 full 경로
	 * @param orignFileNm TODO
	 * @return
	 * @throws Exception
	 */
	public ReturnClass downFile(HttpServletResponse response, String saveFileNm, String orignFileNm) throws Exception {
		
		ReturnClass rc = new ReturnClass();
		
		String downFileName = saveFileNm;
		String orgFileName = orignFileNm;
		
		File file = new File(downFileName);
		BufferedInputStream in = null;		
		
		OutputStream outputStream = response.getOutputStream();
		
		try{
			if (!file.exists()) {
				rc.setCd_result(Constant.FAILED);
				rc.setDes_message("[파일 디렉토리]가 존재하지 않습니다");
			    throw new FileNotFoundException(downFileName);
			}
	
			if (!file.isFile()) {
				rc.setCd_result(Constant.FAILED);
				rc.setDes_message("[파일]이존재하지 않습니다");
			    throw new FileNotFoundException(downFileName);
			}		
	
			int BUFF_SIZE = 2048;
			
			int fSize = (int)file.length();
			
			//다운로드를 위한 response SETTING
			if (fSize > 0) {
				
			    in = new BufferedInputStream(new FileInputStream(file));
			    String mimetype = "text/html"; //"application/x-msdownload"
			    response.setBufferSize(fSize);
			    response.setContentType(mimetype);
			    response.setHeader("Content-Disposition:", "attachment;filename=" + orgFileName);
			    response.setContentLength(fSize);
			    
			    FileCopyUtils.copy(in, outputStream);    
			}
			
		} finally {
			if( in != null ){
				in.close();
			}
			outputStream.flush();
			outputStream.close();
		}
		rc.setCd_result(Constant.SUCCESS);
		rc.setDes_message("파일 다운로드 완료");		

		return rc;
	}
	
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
	
	public boolean fileExtensionChk(String extension) throws Exception {
		boolean flag = false;
		String tmpLow = extension.toLowerCase();
		if ((tmpLow.equals("tiff") || tmpLow.equals("tif") || tmpLow.equals("jpeg") 
				|| tmpLow.equals("jpg") ||tmpLow.equals("gif") || tmpLow.equals("bmp") || tmpLow.equals("pdf")
				|| tmpLow.equals("wav") || tmpLow.equals("mp3") || tmpLow.equals("3gp")) ) {
			flag = true;
		}
		
		return  flag;
	}

	public String getUrlFileDownLoadExtension(String httpUrl, String etc_attach) throws Exception {
		String resutlMsg = "";
		
		String refFileName = getDownFileName(httpUrl , etc_attach);		
		refFileName = nameCheck(refFileName);
		refFileName = getFileName( refFileName );
				
		if ( getUrlFileDownLoad(httpUrl, refFileName) ){
			resutlMsg = refFileName; 	//저장된 파일명 
		}
		
		return resutlMsg;
	}
	
	
	/**
	 * 해당 파일명으로 저장된 데이터가 존재하면 새로운 파일명 리턴
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public String nameCheck(String pFileName) throws FinsetException {
        String fileName = pFileName;
		fileName = StringUtil.nullToEmpty(fileName);

		if ( StringUtil.isEmpty(fileName) ) return null;

		// 파일확장자 체크
		fileNmCheck(fileName);

		// 저장경로 존재여부 검사를 위한 경로세팅
		String checkPath = fileName.substring(0, fileName.lastIndexOf('/') + 1);

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
		if ( StringUtil.isEmpty(fileName) )
			throw new FinsetException("FileUpload [파일명 오류] 입니다.");

		CodeManager codeManager = (CodeManager) SpringApplicationContext.getBean("codeManager");
		CodeInfo code = codeManager.getCodeInfo("_CONF_SYSTEM", "PATH_PDS");

		fileName = code.getNm_code() //경로 업체아이디
				 + "/"
				 + DateUtil.getCurrentDate() // 년월일 예:20100101
				 + "/"
				 + getRandomString() // 랜덤문자 10글자
//						 + StringUtil.getPickupFirst(fileName)
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
		
}
