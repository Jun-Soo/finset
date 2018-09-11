package com.koscom.fincorp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koscom.attach.service.AttachManager;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.util.FinsetException;
import com.koscom.util.LogUtil;
import com.koscom.util.StringUtil;

@Controller
@RequestMapping("/m/fincorp")
public class FincorpController {
	
    private static final Logger logger = LoggerFactory.getLogger(FincorpController.class);
	
	@Autowired
	private FincorpManager fincorpManager;
	
	@Autowired
	private AttachManager attachManager;
	
    @RequestMapping("/getFinCorpIcon.crz")
    public void getFilec(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GZIPOutputStream gzipoutputstream = null;
        ServletOutputStream servletOutputStream = null;
        byte[] readByte = null;
        String path = null;
        try{
            path = StringUtil.nullToEmpty(request.getSession().getServletContext().getRealPath("/"));
            String file_name = "";
           
            if(request.getParameter("file_name") != null && !"".equals(request.getParameter("file_name"))) {
            	file_name = request.getParameter("file_name");
            }else {
            	String cd_fc = request.getParameter("cd_fc");
            	file_name = fincorpManager.getFcPathFile(cd_fc);
            }
            if(file_name!=null && !"".equals(file_name)) {
	            fileNmCheck(file_name);
	            readByte = attachManager.getBytesAttachFileC(file_name);
                if (readByte == null) {
                    LogUtil.debugLn(logger,"IMAGE FILE ="+readByte);
                }
            }
            /**
             * 파일이 등록되어 있지 않는 경우에는
             * 기본이미지를 리턴한다.
             */
            if(readByte == null ||  readByte.length == 0) {
                File defaultImgFile = new File(path+"/images/bank_img/agency_default.png");
                if (defaultImgFile != null) {
                    readByte = org.apache.commons.io.FileUtils.readFileToByteArray(defaultImgFile);
                }
            }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename="+file_name);
            servletOutputStream = response.getOutputStream();
            servletOutputStream.write(readByte);

        } catch(IOException e) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('File Not Found');</script>");
        }finally{
            if (gzipoutputstream != null) {
                try {
                    gzipoutputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (servletOutputStream != null) {
                try {
                    servletOutputStream.flush();
                    servletOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void fileNmCheck(String fileName) throws Exception{
        if(fileName.indexOf("..") > -1){
            throw new FinsetException("지원하지 않는 파일");
        }
        if(StringUtil.isEmpty(fileName)){
            throw new FinsetException("파일명 이 NULL");
        }
        if( fileName.endsWith(".jsp")  || fileName.endsWith(".sh")  || fileName.endsWith(".htm")
                || fileName.endsWith(".html") || fileName.endsWith(".php") || fileName.endsWith(".php3")
                || fileName.endsWith(".php4") || fileName.endsWith(".phps")|| fileName.endsWith(".phtml")
                || fileName.endsWith(".class")|| fileName.endsWith(".java")|| fileName.endsWith(".js")
                || fileName.endsWith(".cgi")  || fileName.endsWith(".py")) {
            throw new FinsetException("지원하지 않는 파일");
        }
    }
}
