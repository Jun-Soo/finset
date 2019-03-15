package com.koscom.fincorp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.koscom.attach.service.AttachManager;
import com.koscom.fincorp.model.FincorpForm;
import com.koscom.fincorp.model.FincorpVO;
import com.koscom.fincorp.model.FincorpfcNminfoForm;
import com.koscom.fincorp.model.FincorpfcNminfoVO;
import com.koscom.fincorp.service.FincorpManager;
import com.koscom.util.LogUtil;
import com.koscom.util.Pagination;
import com.koscom.util.ReturnClass;
import com.koscom.util.SessionUtil;
import com.koscom.util.StringUtil;
import com.koscom.worker.model.WorkerForm;
import com.koscom.worker.model.WorkerVO;
import com.koscom.worker.service.WorkerManager;


@Controller
@RequestMapping("/fincorp")
public class FincorpController {

	private static final Logger logger = LoggerFactory.getLogger(FincorpController.class);
	
	@Autowired
	private FincorpManager fincorpManager;
	
	@Autowired
	WorkerManager workerManager;

    @Autowired
    private AttachManager attachManager;

	/**
	 * 금융사 조회 메인
	 * @param fincorpForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listFincorpMain.crz")
	public String listFincorpMain(HttpServletRequest request, FincorpForm fincorpForm, Model model){
		SessionUtil session = new SessionUtil(request);
		
		logger.info("session.getUserId() :"+session.getUserId());
		
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		
		model.addAttribute("cd_template_group", workerVO.getCd_template_group());
		
		
		return "/fincorp/listFincorpMain";
	}
    @RequestMapping("/getFinCorpIcon.crz")
    public void getFilec(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try{
            String file_name = request.getParameter("file_name");

            String path = StringUtil.nullToEmpty(request.getSession().getServletContext().getRealPath("/"));

            logger.info("=============[ request ip : "+request.getRemoteAddr()+"]=============");
            logger.info("=============[ path name : "+path+"]=============");
            logger.info("=============[ file name : "+file_name+"]=============");

            fileNmCheck(file_name);

            String agentType = request.getHeader("Accept-Encoding");

            boolean flag = false;

            if( agentType != null && agentType.indexOf("gzip") >= 0 ){
                flag = true;
            }else{
                flag = false;
            }

            if( flag ){
                response.setHeader("Content-Encoding", "gzip");
                response.setHeader("Content-disposition", "attachment;filename="+file_name);
                ServletOutputStream servletOutputStream = response.getOutputStream();
                GZIPOutputStream gzipoutputstream = null;

                try{
                    gzipoutputstream = new GZIPOutputStream(servletOutputStream);
                    dumpFile(file_name, gzipoutputstream);
                }catch(Exception e){
                    PrintWriter out =response.getWriter();
                    out.println("<script> alert('File Not Found');</script>");
                }finally{
                    if( gzipoutputstream != null ){
                        gzipoutputstream.close();
                    }
                    servletOutputStream.close();
                }
            } else {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment;filename="+file_name);
                ServletOutputStream servletoutputstream1 = null;

                try{
                    servletoutputstream1 = response.getOutputStream();
                    dumpFile(file_name, servletoutputstream1);
                }catch(Exception e){
                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('File Not Found');</script>");
                }finally{
                    if( servletoutputstream1 != null ){
                        servletoutputstream1.flush();
                        servletoutputstream1.close();
                    }
                }
            }
        } catch(Exception e) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('File Not Found');</script>");
        }
    }

	/**
	 * 금융사 리스트
	 * @param fincorpForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/listFincorp.crz")
	public String listFincorp(HttpServletRequest request, FincorpForm fincorpForm, Model model){
		
		List<FincorpVO> list = null;	
		list = fincorpManager.listFincorpInfo(fincorpForm);
		
		SessionUtil session = new SessionUtil(request);
		logger.info("session.getUserId() :"+session.getUserId());
		
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		if(workerVO.getCd_template_group().equals("12") ){
			fincorpForm.setCd_fc(workerVO.getCd_fc());
		}
		model.addAttribute("workerVO", workerVO);
		
		Pagination pagedList = (Pagination) fincorpForm.setPagedList(fincorpManager.listFincorpInfo(fincorpForm), fincorpManager.listFincorpCount(fincorpForm));
		model.addAttribute("pagedList", pagedList);
		
		return "/fincorp/listFincorp";
	}
	
	/**
	 * 금융사 상세정보 메인
	 * @param fincorpForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/formFincorpMain.crz")
	public String formFincorpMain(FincorpForm fincorpForm, Model model) {
		FincorpfcNminfoForm fincorpfcNminfoForm = new FincorpfcNminfoForm();
		fincorpfcNminfoForm.setCd_fc(fincorpForm.getCd_fc());
		List<FincorpfcNminfoVO> list = fincorpManager.listFincorpfcNminfo(fincorpfcNminfoForm);
		model.addAttribute("list", list);
		
		logger.info("========================================formFincorpMain call");
		if(fincorpForm != null && fincorpForm.getCd_fc() != null && fincorpForm.getCd_fc() != "") {
			FincorpVO fincorpVO = fincorpManager.getFincorpInfo(fincorpForm.getCd_fc());
			
			model.addAttribute("fincorpInfo", fincorpVO);
			model.addAttribute("fincorpForm", fincorpForm);
			model.addAttribute("id_fincorp", fincorpForm.getCd_fc());
			
		}else {
			model.addAttribute("fincorpForm", fincorpForm);
			model.addAttribute("id_fincorp", "");
		}
		return "/fincorp/formFincorpMain";
	}
	
	@RequestMapping("/formPopFincorpMain.crz")
	public String formPopFincorpMain(FincorpForm fincorpForm, Model model) {
		
		return "/fincorp/formFincorpMain";
	}
	
	/**
	 * 금융사 추가
	 * @param fincorpVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/createFincorp.json")
	public String createFincorp(HttpServletRequest request, FincorpVO fincorpVO, Model model) {
		
		SessionUtil session = new SessionUtil(request);
		fincorpVO.setId_frt(session.getUserId());
		logger.info("============fincorpVO확인=============");
		logger.info(fincorpVO.toString());
		
		MultipartFile file1 = fincorpVO.getFile1();
        File upFile = null;
		try {
			if(file1 != null){
				byte[] fileArray = file1.getBytes();
				int fileSize = fileArray.length;
				String fileName = file1.getOriginalFilename();
				
				if(fileArray != null && fileSize > 0){
					fincorpVO.setFileArray(fileArray);
					fincorpVO.setFileName(fileName);
					fincorpVO.setFileSize(fileSize);
					logger.info("fileName->"+fileName);
					logger.info("fileSize->"+fileSize);
					fincorpVO.setImg_bi(fileArray);
				}
			}
			fincorpVO.setFile1(null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int fcCnt = fincorpManager.getFcCnt(fincorpVO);
		if( 0 == fcCnt) { //중복 아님
			ReturnClass returnClass = fincorpManager.createFincorp(fincorpVO);
			
			model.addAttribute("message", returnClass.getMessage());
			model.addAttribute("cd_result", returnClass.getCd_result());
			
		}else { //중복
			model.addAttribute("message", "이미 등록된 금융사 입니다.");
		}
		
		return "jsonView";
	}
    private static final String defaultImgFile = "/img/agency_default.png";
	/**
	 * 금융사 등록/수정
	 * @param fincorpVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/procFincorpInfo.json")
	public String procFincorpInfo(HttpServletRequest request, FincorpVO fincorpVO, Model model) throws IOException{
		
		SessionUtil session = new SessionUtil(request);
		fincorpVO.setId_frt(session.getUserId());
		fincorpVO.setId_lst(session.getUserId());
		logger.info("============fincorpVO확인=============");
		logger.info(fincorpVO.toString());
		
		MultipartFile file1 = fincorpVO.getFile1();
		try {
			if(file1 != null) {
				byte[] fileArray = file1.getBytes();
				int fileSize = fileArray.length; 
				String fileName = file1.getOriginalFilename();

				if(fileArray != null && fileSize > 0){
					fincorpVO.setFileArray(fileArray);
					fincorpVO.setFileName(fileName);
					fincorpVO.setFileSize(fileSize);
					logger.info("fileName->"+fileName);
					logger.info("fileSize->"+fileSize);
					fincorpVO.setImg_bi(fileArray);
				}
				fincorpVO.setFile1(null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
            LogUtil.error(logger,e);
		}
		
		ReturnClass returnClass = fincorpManager.procFincorpInfo(fincorpVO);
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
	/**
	 * 금융사 삭제
	 * @param fincorpVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/delFincorpInfo.json")
	public String delFincorpInfo(FincorpVO fincorpVO,Model model) {
		
		ReturnClass returnClass = fincorpManager.delFincorpInfo(fincorpVO);
		model.addAttribute("returnData", returnClass);
		
		return "jsonView";
	}
	
	/**
	 * 자동완성
	 * @param form
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listWorkerInfoAuto.json")
	public String listWorkerInfoAuto(WorkerForm form, Model model) throws Exception {
		ReturnClass rc = fincorpManager.listWorkerInfoJson(form);
		model.addAttribute("data", (JSONArray)rc.getReturnObj());
		return "jsonView";
	}
	
	/**
	 * 금융기관명 자동완성
	 * @param form
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listNmfcInfoAuto.json")
	public String listNmfcInfoAuto(WorkerForm form, Model model) throws Exception {
		ReturnClass rc = fincorpManager.listNmfcInfoJson(form);
		model.addAttribute("data", (JSONArray)rc.getReturnObj());
		return "jsonView";
	}
    private void fileNmCheck(String fileName) throws Exception{
        if(fileName.indexOf("..") > -1){
            throw new Exception();
        }
        if(StringUtil.isEmpty(fileName)){
            throw new Exception();
        }
        if( fileName.endsWith(".jsp")  || fileName.endsWith(".sh")  || fileName.endsWith(".htm")
                || fileName.endsWith(".html") || fileName.endsWith(".php") || fileName.endsWith(".php3")
                || fileName.endsWith(".php4") || fileName.endsWith(".phps")|| fileName.endsWith(".phtml")
                || fileName.endsWith(".class")|| fileName.endsWith(".java")|| fileName.endsWith(".js")
                || fileName.endsWith(".cgi")  || fileName.endsWith(".py")) {
            throw new Exception();
        }
    }
    private void dumpFile(String file_name, OutputStream outputstream) throws Exception {
        try {
            byte[] readByte = attachManager.getBytesAttachFileC(file_name);
			if(readByte == null) {
                readByte = new byte[0];
			}
            outputstream.write(readByte);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /* srchou 추가 */
    /**
	 * 금융사명 KCB 사전연계 list
	 * @param fincorpfcNminfoForm
	 * @return FincorpfcNminfoVO
	 */
    @RequestMapping("/listFincorpfcNminfo.crz")
	public String listFincorpfcNminfo(HttpServletRequest request, FincorpfcNminfoForm fincorpfcNminfoForm, Model model) throws Exception {
    	SessionUtil session = new SessionUtil(request);
    	WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		model.addAttribute("workerVO", workerVO);
		List<FincorpfcNminfoVO> list = fincorpManager.listFincorpfcNminfo(fincorpfcNminfoForm);
		model.addAttribute("list", list);
		
		return "/fincorp/listFincorpfcNminfo";
    }
    
    /**
	 * 금융사명 KCB 사전연계 정보 삽입
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
    @RequestMapping("/createFincorpfcNminfo.json")
	public String createFincorpfcNminfo(HttpServletRequest request, FincorpfcNminfoForm fincorpfcNminfoForm, Model model) throws Exception {
    	SessionUtil session = new SessionUtil(request);
    	WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		model.addAttribute("workerVO", workerVO);
		fincorpfcNminfoForm.setYn_use(fincorpfcNminfoForm.getNm_yn_use());
		String result = null;
		result = fincorpManager.createFincorpfcNminfo(fincorpfcNminfoForm);
		model.addAttribute("result", result);
		
		return "jsonView";
    }
    
    /**
	 * 금융사명 KCB 사전연계 정보 수정
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
    @RequestMapping("/updFincorpfcNminfo.json")
	public String updFincorpfcNminfo(HttpServletRequest request, FincorpfcNminfoForm fincorpfcNminfoForm, Model model) throws Exception {
    	SessionUtil session = new SessionUtil(request);
    	WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		model.addAttribute("workerVO", workerVO);
		fincorpfcNminfoForm.setYn_use(fincorpfcNminfoForm.getNm_yn_use());
		String result = null;
		result = fincorpManager.updFincorpfcNminfo(fincorpfcNminfoForm);
		model.addAttribute("result", result);
		
		return "jsonView";
    }
    
    /**
	 * 금융사명 KCB 사전연계 정보 삭제
	 * @param fincorpfcNminfoForm
	 * @return String
	 */
    @RequestMapping("/delFincorpfcNminfo.json")
	public String delFincorpfcNminfo(HttpServletRequest request, FincorpfcNminfoForm fincorpfcNminfoForm, Model model) throws Exception {
    	SessionUtil session = new SessionUtil(request);
    	WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		model.addAttribute("workerVO", workerVO);
		
		String result = null;
		result = fincorpManager.delFincorpfcNminfo(fincorpfcNminfoForm);
		model.addAttribute("result", result);
		
		return "jsonView";
    }

    /**
     * 이미지 등록 기능(테스트)
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/setFincorpImg.crz")
    public void setFincorpImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = null;
        File imgFileDir = null;
        File[] subFiles = null;
        String fileName = null;
        String cdNm = null;
        HashMap<String,String> mapFcCode = new HashMap<String,String>();
        String cdFc = null;
        byte[] imgData = null;

        FincorpVO vo = new FincorpVO();
        List<FincorpVO> listFcCode = fincorpManager.listCodeFincorp(vo);
        
        for(FincorpVO fcCode : listFcCode) {
        	if("Y".equals(StringUtil.NVL(fcCode.getYn_use(), "N"))) {
        		mapFcCode.put(fcCode.getNm_fc(), fcCode.getCd_fc());
        	}
        }
        
        out = response.getWriter();
        String path = StringUtil.nullToEmpty(request.getSession().getServletContext().getRealPath("/"));
        imgFileDir = new File(path+"/images/bank_img");
        if (imgFileDir != null) {
            subFiles = imgFileDir.listFiles();
        }
        logger.debug("=================================");
        if (subFiles != null) {
            int i=0;
            for (File subFile : subFiles) {
                i++;
                fileName = subFile.getName();
                logger.debug(i+" 파일명:" + fileName);
                cdNm = StringUtil.getPickupFirst(fileName);
                cdFc = mapFcCode.get(cdNm);
                if(cdFc != null) {
	                imgData = org.apache.commons.io.FileUtils.readFileToByteArray(subFile);
	                logger.debug(i+" imgData.length" + imgData.length);
	                logger.debug(i+" 금융사명:" + cdNm);
	                fincorpManager.regFcImg(cdNm, imgData, cdFc);
	                logger.debug(i+" 저장성공");
                }
            }
        }
        logger.debug("=================================");
//            String cd_nm =
//            fincorpWebManager.
        if (out != null) {
            out.println("<script>alert('ICON REG SUCCESS');</script>");
        }
    }

    /**
     * 자동완성
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/tempQuery.crz")
    public String tempQuery(HttpServletRequest request,  FincorpVO form,Model model) throws Exception {
        String query = form.getQuery();
        LogUtil.debugLn(logger,"query=\n"+query);
        List<HashMap> rc = fincorpManager.tempQuery(form);
        ArrayList<String> listKey = new ArrayList<String>();
        int size = rc.size();
        String key = null;
        HashMap temp= null;
        Iterator it = null;
        for(int i=0;i<size;i++) {
            if(i==0) {
                temp = rc.get(i);
                it = temp.keySet().iterator();
                while(it.hasNext()) {
                    key = (String)it.next();
                    listKey.add(key);
                    logger.debug("KEY="+key);
                }

                break;
            }
        }
        model.addAttribute("keys", listKey);
        model.addAttribute("data", rc);
        return "/fincorp/deqlaction";
    }
    /**
     * 자동완성
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/tempQueryInput.crz")
    public String tempQueryInput(HttpServletRequest request, Model model) throws Exception {
        return "/fincorp/deql";
    }
    /**
     * 자동완성
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/filesSelect.crz")
    public String filesSelect(HttpServletRequest request, Model model) throws Exception {
        return "/fincorp/filesSelect";
    }
    /**
     * 자동완성
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/lcafilesSelect.crz")
    public String lcafilesSelect(HttpServletRequest request, Model model) throws Exception {
        return "/fincorp/lcaFilesSelect";
    }
    /**
     * 자동완성
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/filesSelectUpload.crz")
    public String filesSelectUpload(HttpServletRequest request, FincorpVO fincorpVO, Model model) throws Exception {
        MultipartFile file1 = fincorpVO.getFile1();
        String path = StringUtil.nullToEmpty(request.getSession().getServletContext().getRealPath("/"));
        String file_name = fincorpVO.getFile_name();
        LogUtil.debugLn(logger,"file_name="+file_name);
        LogUtil.debugLn(logger,"file1="+file1);
        if(file1 != null) {
            File file = new File(file_name);
            LogUtil.debugLn(logger,"존재하는지 file="+file);
            if(file.exists() == true){
                file.delete();
            }
            file = new File(file_name);
            LogUtil.debugLn(logger,"새로 만든 file="+file);
            byte[] fileArray = file1.getBytes();
            LogUtil.debugLn(logger,"저장할 fileArray="+fileArray);
            LogUtil.debugLn(logger,"저장할 fileArray.length="+fileArray.length);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(fileArray);
                fos.flush();
                fos.close();
//                org.apache.commons.io.FileUtils.writeByteArrayToFile(file,fileArray);
            } catch (IOException e) {
                LogUtil.error(logger,e);
            }
        }
        return "/fincorp/filesSelect";
    }
    /**
     * 자동완성
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/LCAfilesSelectUpload.crz")
    public String LCAfilesSelectUpload(HttpServletRequest request, FincorpVO fincorpVO, Model model) throws Exception {
        MultipartFile file1 = fincorpVO.getFile1();
        String path = StringUtil.nullToEmpty(request.getSession().getServletContext().getRealPath("/"));
        String file_name = fincorpVO.getFile_name();
        LogUtil.debugLn(logger,"file_name="+file_name);
        LogUtil.debugLn(logger,"file1="+file1);
        if(file1 != null) {
            fincorpManager.uploadFile(fincorpVO);
        }
        return "/fincorp/filesSelect";
    }
    
    /**
     * 신규 은행명, 금융기관코드 check
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/chkCdfc.json")
    public String chkCdfc(HttpServletRequest request, FincorpVO fincorpVO, Model model) throws Exception {
        ReturnClass result = fincorpManager.chkCdfc(fincorpVO);
        model.addAttribute("result", result);
        return "jsonView";
    }
}
