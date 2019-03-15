package com.koscom.coocon;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.koscom.coocon.model.CooconAPIinfoFormVO;
import com.koscom.coocon.model.CooconAPIinfoVO;
import com.koscom.coocon.model.CooconGoodsArray;
import com.koscom.coocon.model.CooconGoodsArrayList;
import com.koscom.coocon.model.CooconJsonArray;
import com.koscom.coocon.model.CooconVO;
import com.koscom.coocon.service.CooconManager;
import com.koscom.util.LogUtil;
import com.koscom.util.Pagination;
import com.koscom.util.SessionUtil;
import com.koscom.util.StringUtil;
import com.koscom.worker.model.WorkerVO;
import com.koscom.worker.service.WorkerManager;
@Controller
@RequestMapping("/coocon")
@PropertySource("classpath:prop/webservice.properties")
public class CooconController {

	private static final Logger logger = LoggerFactory.getLogger(CooconController.class);

	@Autowired
	private WorkerManager workerManager;
	
	@Autowired
	private CooconManager cooconManager;
	
	@Resource
	private Environment environment;
	
	/**
	 * 금융사관리 / COOCON API 관리
	 * @param 
	 * @return 
	 */
	@RequestMapping("/cooconMain.crz")
	public String cooconMain(HttpServletRequest request, CooconAPIinfoFormVO cooconAPIinfoFormVO, Model model){
		SessionUtil session = new SessionUtil(request);
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		model.addAttribute("workerVO", workerVO);

		return "coocon/cooconGoodsMain";
	}
	
	/**
	 * 금융사관리 / COOCON API 관리 / API BUTTON / COOCON_API_INFO TABLE DATA로 API PARAM 전달
	 * @param 
	 * @return 
	 */
	@RequestMapping("/cooconAPI.crz")
	public String cooconAPIinfo(HttpServletRequest request, Model model) throws Exception {
		
		/* cooconAPI관리 */
		List<CooconAPIinfoVO> list = null;
		List<CooconVO> APIlist = null;
		List<CooconVO> allAPIlist = new ArrayList<CooconVO>();
		String resultMessage = null;
		String cd_coocon_goods = null;
		String gubun = null;
		String org_type = null;
		String cd_org = null;
		String cd_fc = null;
		String nm_coocon_goods = null;
		
		List<CooconGoodsArrayList> cooconGoodsArrayList = null;
		List<CooconGoodsArrayList> allcooconGoodsArrayList = new ArrayList<CooconGoodsArrayList>();
		
		try {
			list = cooconManager.getCooconAPIinfo();
			if(list.size() != 0){
				for(int i=0; i<list.size(); i++){
					cooconGoodsArrayList = CooconGoodsArray(list.get(i).getCd_org(), list.get(i).getOrg_type(), list.get(i).getGubun(), request);
					
					cd_org = list.get(i).getCd_org();
					org_type = list.get(i).getOrg_type();
					gubun = list.get(i).getGubun();
					cd_fc = list.get(i).getCd_fc();
								
					if( cooconGoodsArrayList != null ){
						allcooconGoodsArrayList.addAll(cooconGoodsArrayList);
						for(int j=0; j < cooconGoodsArrayList.size(); j++){
							nm_coocon_goods = cooconGoodsArrayList.get(j).getPRODUCT_NAME();
							
							cd_coocon_goods = cooconManager.getCdcoocongoods(nm_coocon_goods, cd_org);
							
							if(StringUtil.isEmpty(cd_coocon_goods)) {
								cd_coocon_goods = cd_fc + "0" + String.format("%02d", Integer.parseInt(gubun)) + String.format("%05d", Integer.parseInt(cooconGoodsArrayList.get(j).getSEQ()));
							}
							APIlist = cooconAPIGoods(cooconGoodsArrayList.get(j).getSEQ(), cd_org, org_type, gubun, cd_coocon_goods, request);
							if( APIlist != null ){
								allAPIlist.addAll(APIlist);
							}
						}
					}
				}
				cooconManager.setCooconGoods(allAPIlist);
			}else{
				resultMessage = "입력에 실패하였습니다.";
			}
		} catch (Exception e) {
			LogUtil.error(logger, e);
		} finally {
			list = null;
			APIlist = null;
			allAPIlist = null;
			resultMessage = null;
			cd_coocon_goods = null;
			gubun = null;
			org_type = null;
			cd_org = null;
			cd_fc = null;
			nm_coocon_goods = null;
			cooconGoodsArrayList = null;
			allcooconGoodsArrayList = null;
		}
		
		model.addAttribute("message",resultMessage);
		
		return "jsonView";
	}
	
	/**
	 * COOCON API 금융사코드, 금융기관코드, 쿠콘 은행코드, 조회구분에 따른 리스트 조회
	 * @param cd_org, org_type, gubun;
	 * @return List<CooconGoodsArrayList>
	 */
	public List<CooconGoodsArrayList> CooconGoodsArray(String cd_org, String org_type, String gubun, HttpServletRequest request) throws Exception {
		SessionUtil session = new SessionUtil(request);
		
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
		    public void checkClientTrusted(X509Certificate[] certs, String authType) {  }
		    public void checkServerTrusted(X509Certificate[] certs, String authType) {  }
		 }
		};
		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
		  public boolean verify(String hostname, SSLSession session) { return true; }
		};    
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());	
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		//API 호출주소
		String url = environment.getProperty("coocon.apiUrl") + "batch_wapi.jsp";//현금영수증 api 호출예제.
		byte[] resMessage = null;

		JSONObject JSONDataVal = new JSONObject();
		JSONDataVal.put("API_KEY", "3s4Gis8kOAHwPSce4FHe");
		JSONDataVal.put("API_ID", "0127");
		JSONDataVal.put("ORG_TYPE", org_type);
		JSONDataVal.put("ORG_CD", cd_org);
		JSONDataVal.put("GUBUN", gubun);
		JSONDataVal.put("REQ_TYPE", "0");
		
		HttpURLConnection conn = null;
		OutputStreamWriter os = null;
		DataInputStream in = null;
		ByteArrayOutputStream bout = null;
		
		String postString = null;
		String temp = null;
		int bcount = 0;
		int n = 0;
		byte[] buf = null;
		Gson gson = new Gson();
		List<CooconGoodsArrayList> cooconGoodsArrayList = null;
		CooconGoodsArray cooconGoodsArray = new CooconGoodsArray();
		
		try {
			
		    conn = (HttpURLConnection) new URL(url).openConnection();	
		    conn.setDoInput(true); 
		    conn.setDoOutput(true);
		    conn.setRequestMethod("POST");
		    conn.setUseCaches(false);
		    os = new OutputStreamWriter(conn.getOutputStream());
		    
		    postString = "JSONData="+JSONDataVal;
		    os.write(postString);
		    os.flush();
		    os.close();
		    
		    in = new DataInputStream(conn.getInputStream());
		    bout = new ByteArrayOutputStream();
		    buf = new byte[2048];
		    while (true) {
		        n = in.read(buf);
		        if (n == -1) break;
		        bout.write(buf, 0, n);
		    }
	
		    bout.flush(); 
		    resMessage = bout.toByteArray();
		    temp = new String(resMessage);

			cooconGoodsArray = gson.fromJson( JSONSerializer.toJSON(temp).toString() , CooconGoodsArray.class ) ;
			logger.debug(cooconGoodsArray.getERRCODE() + ":" + cooconGoodsArray.getERRMSG()  + ":" +  cooconGoodsArray.getTOTAL_COUNT());
			
			if( cooconGoodsArray.getRESP_DATA() != null ){
				cooconGoodsArrayList = cooconGoodsArray.getRESP_DATA();
			}else{
				cooconGoodsArrayList = null;
			}
			
		}
		catch (MalformedURLException e) {
			logger.debug("CooconGoodsArray 의 MalformedURLException error");
		}
		catch (IOException e) {
			logger.debug("CooconGoodsArray 의 e error");
		    LogUtil.error(logger, e);
		}
		finally{
			if( os != null){try {os.close();} catch (Exception e2) {}}
			if( in != null){try {in.close();} catch (Exception e2) {}}
			if( bout != null){try {bout.close();} catch (Exception e2) {}}
			postString = null;
			temp = null;
			bcount = 0;
			n = 0;
			buf = null;
			gson = null;
			conn.disconnect();
			System.gc();
		}
		
		return cooconGoodsArrayList;
	}
	
	
	/**
	 * COOCON API 금융사코드, 금융기관코드, 쿠콘 은행코드, 조회구분에 따른 은행별 상세조회
	 * @param cd_org, org_type, gubun, cd_coocon_goods
	 * @return List<CooconVO>
	 */
	public List<CooconVO> cooconAPIGoods(String seq, String cd_org, String org_type, String gubun, String cd_coocon_goods, HttpServletRequest request) throws Exception {
		SessionUtil session = new SessionUtil(request);
		
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
		    public void checkClientTrusted(X509Certificate[] certs, String authType) {  }
		    public void checkServerTrusted(X509Certificate[] certs, String authType) {  }
		 }
		};
		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
		  public boolean verify(String hostname, SSLSession session) { return true; }
		};    
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());	
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		//API 호출주소
		String url = environment.getProperty("coocon.apiUrl") + "batch_wapi.jsp";//현금영수증 api 호출예제.
		byte[] resMessage = null;

		JSONObject JSONDataVal = new JSONObject();
		JSONDataVal.put("API_KEY", "3s4Gis8kOAHwPSce4FHe");
		JSONDataVal.put("API_ID", "0127");
		JSONDataVal.put("ORG_TYPE", org_type);
		JSONDataVal.put("ORG_CD", cd_org);
		JSONDataVal.put("GUBUN", gubun);
		JSONDataVal.put("SEQ", seq);
		JSONDataVal.put("REQ_TYPE", "1");
		
		HttpURLConnection conn = null;
		
		OutputStreamWriter os = null;
		DataInputStream in = null;
		ByteArrayOutputStream bout = null;
		
		String temp = null;
		String postString = null;
		int bcount = 0;
		int n = 0;
		byte[] buf = null;
		Gson gson = new Gson();
		List<CooconVO> APIlist = null;
		CooconJsonArray cooconJsonArray = new CooconJsonArray();

		try {
		    conn = (HttpURLConnection) new URL(url).openConnection();	
		    conn.setDoInput(true); 
		    conn.setDoOutput(true);
		    conn.setRequestMethod("POST");
		    conn.setUseCaches(false);
		    os = new OutputStreamWriter(conn.getOutputStream());
		    postString = "JSONData="+JSONDataVal;
		    os.write(postString);
		    os.flush();
		    os.close();

		    in = new DataInputStream(conn.getInputStream());
		    bout = new ByteArrayOutputStream();
		    buf = new byte[2048];

		    while (true) {
		        n = in.read(buf);
		        if (n == -1) break;
		        bout.write(buf, 0, n);
		    }
		    bout.flush(); 
		    resMessage = bout.toByteArray();
		    temp = new String(resMessage);
			
			cooconJsonArray = gson.fromJson( JSONSerializer.toJSON(temp).toString() , CooconJsonArray.class ) ;
			if(cooconJsonArray != null  && cooconJsonArray.getERRCODE().equals("00000000") && cooconJsonArray.getRESP_DATA() != null){
				logger.debug(cooconJsonArray.getERRCODE() + ":" + cooconJsonArray.getERRMSG()  + ":" +  cooconJsonArray.getTOTAL_COUNT());
			}
			
			APIlist = cooconJsonArray.getRESP_DATA();
			if(APIlist != null){
				if(Integer.parseInt(cooconJsonArray.getTOTAL_COUNT()) != 0){
					for(int i=0; i<cooconJsonArray.getRESP_DATA().size(); i++){
						APIlist.get(i).setCd_coocon_goods(cd_coocon_goods);
						APIlist.get(i).setCd_type_fc(org_type);
						APIlist.get(i).setCd_org(cd_org);
						APIlist.get(i).setCd_type_goods("");
						APIlist.get(i).setCd_type_req(gubun);
						APIlist.get(i).setId_frt(session.getUserId());
						APIlist.get(i).setId_lst(session.getUserId());
					}
				}else{
					APIlist = null;
				}
			}else{
				APIlist = null;
			}
		}
		catch (MalformedURLException e) {
			logger.debug("cooconAPIGoods 의 MalformedURLException error");
			logger.debug("MalformedURLException");
		}
		catch (IOException e) {
			logger.debug("cooconAPIGoods 의 e error");
		    LogUtil.error(logger, e);
		}
		finally{
			if( os != null){try {os.close();} catch (Exception e2) {}}
			if( in != null){try {in.close();} catch (Exception e2) {}}
			if( bout != null){try {bout.close();} catch (Exception e2) {}}
			temp = null;
			postString = null;
			bcount = 0;
			n = 0;
			buf = null;
			gson = null;
		    conn.disconnect();
			System.gc();
		}
		return APIlist;
		
	}
	
	/**
	 * 금융사 관리 / COOCON API 관리 / 상품 상세정보 조회
	 * @param cooconAPIinfoFormVO
	 * @return 
	 */
	@RequestMapping("/cooconAPIList.crz")
	public String cooconAPIList(HttpServletRequest request, CooconAPIinfoFormVO cooconAPIinfoFormVO, Model model){
		SessionUtil session = new SessionUtil(request);
		WorkerVO workerVO = new WorkerVO();
		workerVO = workerManager.getWorkerInfo(session.getUserId());
		model.addAttribute("workerVO", workerVO);

		Pagination pagedList = (Pagination) cooconAPIinfoFormVO.setPagedList(cooconManager.listcooconApiInfo(cooconAPIinfoFormVO), cooconManager.listcooconApiInfoCount(cooconAPIinfoFormVO));

		model.addAttribute("pagedList", pagedList);
		
		return "coocon/listcooconGoods";
	}
	
	/**
	 * 금융사관리 / COOCON API 관리 / 상품상세정보 추가
	 * @param CooconAPIinfoFormVO
	 * @return 
	 */
	@RequestMapping("/createcooconAPIinfo.crz")
	public String createcooconAPIinfo(HttpServletRequest request, CooconAPIinfoFormVO cooconAPIinfoFormVO, Model model){
		SessionUtil session = new SessionUtil(request);
		cooconAPIinfoFormVO.setId_frt(session.getUserId());
		cooconAPIinfoFormVO.setId_lst(session.getUserId());
		
		String returnData = null;
		returnData = cooconManager.createcooconAPIinfo(cooconAPIinfoFormVO);
		model.addAttribute("returnData", returnData);
		return "jsonView";
	}
	
	/**
	 * 금융사관리 / COOCON API 관리 / 상품상세정보 수정
	 * @param CooconAPIinfoFormVO
	 * @return 
	 */
	@RequestMapping("/updcooconAPIinfo.crz")
	public String updcooconAPIinfo(HttpServletRequest request, CooconAPIinfoFormVO cooconAPIinfoFormVO, Model model){
		SessionUtil session = new SessionUtil(request);
		cooconAPIinfoFormVO.setId_lst(session.getUserId());
		
		String returnData = null;
		returnData = cooconManager.updcooconAPIinfo(cooconAPIinfoFormVO);
		model.addAttribute("returnData", returnData);
		return "jsonView";
	}
	
	/**
	 * 금융사관리 / COOCON API 관리 / 상품상세정보 삭제
	 * @param CooconAPIinfoFormVO
	 * @return 
	 */
	@RequestMapping("/delcooconAPIinfo.crz")
	public String delcooconAPIinfo(HttpServletRequest request, CooconAPIinfoFormVO cooconAPIinfoFormVO, Model model){
		SessionUtil session = new SessionUtil(request);
		cooconAPIinfoFormVO.setId_lst(session.getUserId());
		String returnData = null;
		returnData = cooconManager.delcooconAPIinfo(cooconAPIinfoFormVO);
		model.addAttribute("returnData", returnData);
		return "jsonView";
	}

}
