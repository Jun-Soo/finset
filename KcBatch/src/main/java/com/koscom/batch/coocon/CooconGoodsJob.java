package com.koscom.batch.coocon;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.el.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.koscom.batch.App;
import com.koscom.batch.coocon.domain.CooconAPIinfoVO;
import com.koscom.batch.coocon.domain.CooconGoodsArray;
import com.koscom.batch.coocon.domain.CooconGoodsArrayList;
import com.koscom.batch.coocon.domain.CooconJsonArray;
import com.koscom.batch.coocon.domain.CooconVO;
import com.koscom.batch.coocon.mapper.CooconMapper;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class CooconGoodsJob {

	private static final Logger logger = LoggerFactory.getLogger(CooconGoodsJob.class);
	private static final String BATCH_NAME = "CooconGoodsJobStep";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private SimpleJobLauncher jobLauncher;
	@Autowired
	private CooconMapper cooconMapper;

	@Value("${spring.apiUrl.coocon}")
	private String apiUrl;

	List<CooconVO> allAPIlist = new ArrayList<CooconVO>();
	int count = 0;

	/**
	 * 스케쥴러
	 *
	 * 주기적으로 Job 실행 cron 설정에 따라 실행 jobLauncher 1개당 job 1+ 와 step 1+ 를 사용가능
	 *
	 * @throws Exception
	 */
	@Scheduled(cron = "${spring.scheduler.cron_2}")
	public void scheduler() throws Exception {

		String jobId = String.valueOf(System.currentTimeMillis());
		System.out.println("Started jobId : " + jobId);

		JobParameters param = new JobParametersBuilder().addString("JobID", jobId).toJobParameters();
		JobExecution execution = jobLauncher.run(cooconApiJob(), param);

		System.out.println("end : " + param.getString("JobID") + ":::" + execution.getStatus());
	}

	/**
	 * 배치 Job
	 *
	 * cooconApiStep 호출한다
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Job cooconApiJob() throws Exception {
		return jobBuilderFactory.get("[Job - " + BATCH_NAME + "]").listener(new JobExecutionListener() {

			@Override
			public void beforeJob(JobExecution jobExecution) {
				//CooconData 1차 조회 및 2차조회
				try {
					allAPIlist = cooconApiCall();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void afterJob(JobExecution jobExecution) {
				count = 0;
			}

		}).start(cooconApiStep()).build();
	}

	public List<CooconVO> cooconApiCall() throws Exception {

		List<CooconVO> allAPIlist = new ArrayList<CooconVO>();
		List<CooconGoodsArrayList> 	allcooconGoodsArrayList = new ArrayList<CooconGoodsArrayList>();
		List<CooconAPIinfoVO> list = cooconMapper.getCooconAPIinfo();

		for(CooconAPIinfoVO vo : list) {

			String cd_org 	= vo.getCd_org();
			String org_type = vo.getOrg_type();
			String gubun 	= vo.getGubun();
			String cd_fc 	= vo.getCd_fc();

			List<CooconGoodsArrayList> cooconGoodsArrayList = CooconGoodsArrayJson(cd_org, org_type, gubun);

			if(cooconGoodsArrayList != null) {
				allcooconGoodsArrayList.addAll(cooconGoodsArrayList);
				for(int j=0; j < cooconGoodsArrayList.size(); j++){

					String nm_coocon_goods = cooconGoodsArrayList.get(j).getPRODUCT_NAME();
					CooconAPIinfoVO cooconApiInfoVO = new CooconAPIinfoVO();
					cooconApiInfoVO.setNm_coocon_goods(nm_coocon_goods);
					cooconApiInfoVO.setCd_org(cd_org);
					String cd_coocon_goods = cooconMapper.getCdcoocongoods(cooconApiInfoVO);

					if(StringUtils.isEmpty(cd_coocon_goods)) {
						cd_coocon_goods = cd_fc + "0" + String.format("%02d", Integer.parseInt(gubun)) + String.format("%05d", Integer.parseInt(cooconGoodsArrayList.get(j).getSEQ()));
					}

					List<CooconVO> APIlist = cooconAPIGoods(cooconGoodsArrayList.get(j).getSEQ(), cd_org, org_type, gubun, cd_coocon_goods);
					if( APIlist != null ){
						allAPIlist.addAll(APIlist);
					}
				}
			}
		}

		return allAPIlist;
	}

	public List<CooconGoodsArrayList> CooconGoodsArrayJson(String cd_org, String org_type, String gubun) throws Exception {

		TrustManager[] trustAllCerts = new TrustManager[] {
			new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}
				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}
			}
		};

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		// API 호출주소
		String url = apiUrl + "batch_wapi.jsp";
		byte[] resMessage = null;

		// API PARAM 정보 SET
		JSONObject JSONDataVal = new JSONObject();
		JSONDataVal.put("API_KEY", 	"3s4Gis8kOAHwPSce4FHe");
		JSONDataVal.put("API_ID", 	"0127");
		JSONDataVal.put("ORG_TYPE", org_type);
		JSONDataVal.put("ORG_CD", 	cd_org);
		JSONDataVal.put("GUBUN", 	gubun);
		JSONDataVal.put("REQ_TYPE", "0");

		HttpURLConnection 		conn 	= null;
		OutputStreamWriter 		os 		= null;
		DataInputStream 		in 		= null;
		ByteArrayOutputStream 	bout 	= null;

		String 	postString 	= null;
		String 	temp 		= null;
		int 	bcount 		= 0;
		int 	n 			= 0;
		byte[] 	buf 		= null;

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

			postString = "JSONData=" + JSONDataVal;
			os.write(postString);
			os.flush();
			os.close();

			in = new DataInputStream(conn.getInputStream());
			bout = new ByteArrayOutputStream();
			buf = new byte[2048];
			while (true) {
				n = in.read(buf);
				if (n == -1)
					break;
				bout.write(buf, 0, n);
			}

			bout.flush();
			resMessage = bout.toByteArray();
			temp = new String(resMessage);

			cooconGoodsArray = gson.fromJson(JSONSerializer.toJSON(temp).toString(), CooconGoodsArray.class);
			logger.debug(cooconGoodsArray.getERRCODE() + ":" + cooconGoodsArray.getERRMSG() + ":"
					+ cooconGoodsArray.getTOTAL_COUNT());

			if (cooconGoodsArray.getRESP_DATA() != null) {
				cooconGoodsArrayList = cooconGoodsArray.getRESP_DATA();
			} else {
				cooconGoodsArrayList = null;
			}

		} catch (MalformedURLException e) {
			logger.debug("CooconGoodsArray 의 MalformedURLException error");
		} catch (IOException e) {
			logger.debug("CooconGoodsArray 의 e error");
			logger.error(e.getMessage());
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e2) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception e2) {
				}
			}
			if (bout != null) {
				try {
					bout.close();
				} catch (Exception e2) {
				}
			}
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
	public List<CooconVO> cooconAPIGoods(String seq, String cd_org, String org_type, String gubun, String cd_coocon_goods) throws Exception {

		TrustManager[] trustAllCerts = new TrustManager[] {
			new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}
				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}
			}
		};

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		//API 호출주소
		String url = apiUrl + "batch_wapi.jsp";
		byte[] resMessage = null;

		JSONObject JSONDataVal = new JSONObject();
		JSONDataVal.put("API_KEY", 	"3s4Gis8kOAHwPSce4FHe");
		JSONDataVal.put("API_ID", 	"0127");
		JSONDataVal.put("ORG_TYPE", org_type);
		JSONDataVal.put("ORG_CD", 	cd_org);
		JSONDataVal.put("GUBUN", 	gubun);
		JSONDataVal.put("SEQ", 		seq);
		JSONDataVal.put("REQ_TYPE", "1");

		HttpURLConnection conn = null;

		OutputStreamWriter 	os = null;
		DataInputStream 	in = null;
		ByteArrayOutputStream bout = null;

		String 	temp 		= null;
		String 	postString 	= null;
		int 	bcount 		= 0;
		int 	n 			= 0;
		byte[] 	buf 		= null;

		Gson 	gson = new Gson();
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
						APIlist.get(i).setId_frt("BATCH");
						APIlist.get(i).setId_lst("BATCH");
					}
				} else {
					APIlist = null;
				}
			} else {
				APIlist = null;
			}
		}
		catch (MalformedURLException e) {
			logger.debug("cooconAPIGoods 의 MalformedURLException error");
			logger.debug("MalformedURLException");
		}
		catch (IOException e) {
			logger.debug("cooconAPIGoods 의 e error");
			logger.error(e.getMessage());
		}
		finally{
			if (os != null) {
				try {
					os.close();
				} catch (Exception e2) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception e2) {
				}
			}
			if (bout != null) {
				try {
					bout.close();
				} catch (Exception e2) {
				}
			}
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
	 * 배치 Step
	 *
	 * <pre>
	 * reader() : 쿠콘 비제휴상품 1차 및 2차 조회
	 * processor() : 데이터 가공
	 * writer() : 비제휴 상품 저장
	 * </pre>
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Step cooconApiStep() throws Exception { // chunk 큰덩어리 프로세스단위
		return stepBuilderFactory.get("[Step - " + BATCH_NAME + "]")
				.<CooconVO, CooconVO>chunk(1)
				.reader(cooconApiReader())
				.processor(cooconApiProcessor())
				.writer(cooconApiWriter())
				.taskExecutor(App.taskExecutor())
				.build();
	}

	@Bean
	public ItemReader<CooconVO> cooconApiReader() throws Exception {

		ItemReader<CooconVO> itemReader = new ItemReader<CooconVO>() {

			@Override
			public CooconVO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

				CooconVO vo = new CooconVO();

				if (count < allAPIlist.size()) {
					vo = allAPIlist.get(count);
					count++;
					return vo;
				}

				return null;
			}
		};

		return itemReader;

	}

	@Bean
	public ItemProcessor<CooconVO, CooconVO> cooconApiProcessor() {
		return new ItemProcessor<CooconVO, CooconVO>() {

			@Override
			public CooconVO process(CooconVO data) throws Exception {
				return data;
			}
		};
	}

	@Bean
	public ItemWriter<CooconVO> cooconApiWriter() {
		ItemWriter<CooconVO> writer = new ItemWriter<CooconVO>() {

			public void write(List<? extends CooconVO> items) throws Exception {

				for (CooconVO vo : items) {
					setCooconGoods(vo);
				}

			}
		};

		return writer;
	}

	public void setCooconGoods(CooconVO cooconVO) throws Exception {

		String cdNonGoods = "";
		int chk_goods = 0;
		int chk_change = 0;
		int result = 0;
		int count = 0;
		int goodsbankCnt = 0;

		//0. PARAM URLDecoding
		cooconVO.decodeData();

		if(!StringUtils.isEmpty(cooconVO.getCd_coocon_goods())
				&& !StringUtils.isEmpty(cooconVO.getNm_coocon_goods())){

			//COOCON_GOODS_INFO의 기존데이터의 cd_non_goods값 조회
			cdNonGoods = cooconMapper.getCooconGoodsInfoCdNongoods(cooconVO);

			//1. API상품정보 insert
			cooconVO.setCd_non_goods(cdNonGoods);
			result = cooconMapper.setCooconGoods(cooconVO);

			if(1 != result)  {
				logger.info("상품 정보 입력에 실패하였습니다.");
			}

			//2. 현 상품별 카운트 체크
			chk_goods = cooconMapper.chkCooconGoods(cooconVO);
			//2. coocon_goods_change_info data 유무 확인
			chk_change = cooconMapper.chkCooconGoodsChangeInfo(cooconVO);

			if( (chk_goods == 1) && (chk_change == 0) ) { //변경정보 INSERT
				cooconMapper.insCooconChangeinfo(cooconVO);
			} else {

				//쿠콘API MIN(SEQ) DELETE - 2건만 남도록
				if( chk_goods > 2) {
					cooconMapper.delCooconGoods(cooconVO);
				}

				//COOCON_GOODS_CHANGE_INFO 정보 셋팅
				//변경정보 UPDATE
				cooconMapper.updCooconChangeinfo(cooconVO);
				/* 변경건수 COUNT */
				count = cooconMapper.chkChangeValue(cooconVO);

				//cd_non_goods값으로 GOODSBANK_INFO 데이터 조회
				if(!StringUtils.isEmpty(cdNonGoods)){
					goodsbankCnt = cooconMapper.getCdnongoodsGoodsbankCnt(cdNonGoods);
				}

				if( count > 0 ){ //변경건수 > 0
					cooconVO.setStatus("2");
					if(goodsbankCnt > 0){
						cooconVO.setYn_reg("N");
					}
					cooconMapper.updStatusCooconChangeinfo(cooconVO);
				}else if (count == 0){ //변경건수 zero
					cooconVO.setStatus("4");
					if(goodsbankCnt > 0){
						cooconVO.setYn_reg("Y");
					}
					cooconMapper.updStatusCooconChangeinfo(cooconVO);
				}
			}
		}

		//쿠콘 데이터 삭제로 인한 coocon_goods_change_info stats를 '3' 으로 변경
		//cooconMapper.delCoocongoodsinfo();
		cooconMapper.delCoocongoodchangeinfo(cooconVO);
		//쿠콘 데이터 삭제로 인한 표시여부 update
		cooconMapper.updYnUseGoodsbankInfo(cooconVO);
	}
}
