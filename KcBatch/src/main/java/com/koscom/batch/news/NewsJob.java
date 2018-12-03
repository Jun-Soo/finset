package com.koscom.batch.news;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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

import com.google.gson.Gson;
import com.koscom.batch.App;
import com.koscom.batch.code.domain.CodeInfo;
import com.koscom.batch.code.mapper.CodeMapper;
import com.koscom.batch.news.domain.NewsVO;
import com.koscom.batch.news.domain.NewsResultVO;
import com.koscom.batch.news.mapper.NewsMapper;
import com.koscom.util.StringUtil;


@Configuration
@EnableBatchProcessing
@EnableScheduling
public class NewsJob {

	private static final Logger logger = LoggerFactory.getLogger(NewsJob.class);
	private static final String BATCH_NAME = "NewsJobStep";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private SimpleJobLauncher jobLauncher;
	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private CodeMapper codeMapper;

	@Value("${spring.apiUrl.naver}")
	private String apiUrl;

	List<CodeInfo> keywordList = new ArrayList<CodeInfo>();
	int count = 0;

	int start_point = 1; //검색 시작 위치 : 1(기본값), 1000(최대)
	int req_display_cnt = 50; //검색 결과 출력 건수 : 10(기본값), 100(최대)
	String sort_option = "sim"; //정렬 옵션 : sim(기본값, 유사도순), date(날짜순)

	/**
	 * 스케쥴러
	 *
	 * 주기적으로 Job 실행 cron 설정에 따라 실행 jobLauncher 1개당 job 1+ 와 step 1+ 를 사용가능
	 *
	 * @throws Exception
	 */
	@Scheduled(cron = "${spring.scheduler.cron_6}")
	public void scheduler() throws Exception {

		String jobId = String.valueOf(System.currentTimeMillis());
		System.out.println("Started jobId : " + jobId);

		JobParameters param = new JobParametersBuilder().addString("JobID", jobId).toJobParameters();
		JobExecution execution = jobLauncher.run(newsApiJob(), param);

		System.out.println("end : " + param.getString("JobID") + ":::" + execution.getStatus());
	}

	/**
	 * 배치 Job
	 *
	 * newsApiStep 호출한다
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Job newsApiJob() throws Exception {
		return jobBuilderFactory.get("[Job - " + BATCH_NAME + "]").listener(new JobExecutionListener() {

			@Override
			public void beforeJob(JobExecution jobExecution) {
				//newsAPI 호출 및 데이터 조회
				try {
					keywordList = getKeywordList();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void afterJob(JobExecution jobExecution) {
				count = 0;
			}

		}).start(newsApiStep()).build();
	}

	public List<CodeInfo> getKeywordList() throws Exception {

		List<CodeInfo> codeInfoList = new ArrayList<CodeInfo>();
		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setCode_group("news_search_query");

		codeInfoList = codeMapper.listCodeInfo(codeInfo);

		return codeInfoList;
	}

	/**
	 * 배치 Step
	 *
	 * <pre>
	 * reader() : newsAPI조회
	 * processor() : 데이터 가공
	 * writer() : newsAPI 결과 저장
	 * </pre>
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Step newsApiStep() throws Exception { // chunk 큰덩어리 프로세스단위
		return stepBuilderFactory.get("[Step - " + BATCH_NAME + "]")
				.<String, NewsVO>chunk(1)
				.reader(newsApiReader())
				.processor(newsApiProcessor())
				.writer(newsApiWriter())
//				.taskExecutor(App.taskExecutor())
				.build();
	}

	@Bean
	public ItemReader<String> newsApiReader() throws Exception {

		ItemReader<String> itemReader = new ItemReader<String>() {

			@Override
			public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

				String search_keyword = "";

				if (count < keywordList.size()) {
					search_keyword = keywordList.get(count).getNm_code();
					count++;
					return search_keyword;
				}

				return null;
			}
		};

		return itemReader;

	}

	@Bean
	public ItemProcessor<String, NewsVO> newsApiProcessor() {
		return new ItemProcessor<String, NewsVO>() {

			@Override
			public NewsVO process(String search_keyword) throws Exception {
				NewsVO newsVO = new NewsVO();
				newsVO = newsApiCall(search_keyword);
				return newsVO;
			}
		};
	}

	public NewsVO newsApiCall(String keyword) throws Exception {

		Gson 	gson = new Gson();
		NewsVO newsVO = new NewsVO();

		try {
            String search_query = URLEncoder.encode(keyword, "UTF-8"); //검색어
            String apiParamUrl = apiUrl+"news.json?query="+ search_query + "&display="+req_display_cnt+"&start="+start_point+"&sort="+sort_option; // 뉴스의 json 결과
            logger.info("apiParamUrl---"+apiParamUrl);
            URL url = new URL(apiParamUrl);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", "TdZVs8EQUZu3Fx1Yg_GZ");
            con.setRequestProperty("X-Naver-Client-Secret", "ou2ZR_7o8F");

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            logger.info("responseToString---"+response.toString());
            newsVO = gson.fromJson( response.toString() , NewsVO.class ) ;

            //db정보 셋팅
            newsVO.setSearch_query(keyword);
            newsVO.setReq_display_cnt(req_display_cnt);
            newsVO.setSort_option(sort_option);

            if(!StringUtil.isEmpty(newsVO.getErrorCode())){ //에러일경우
            	logger.debug("errorCode--"+newsVO.getErrorCode());
            	logger.debug("errorMessage--"+newsVO.getErrorMessage());
            	newsVO.setErr_cd(newsVO.getErrorCode());
            	newsVO.setErr_msg(newsVO.getErrorMessage());
            }else{
            	newsVO.setResult_cnt(newsVO.getTotal());
                newsVO.setResult_date(newsVO.getLastBuildDate());
                newsVO.setErr_cd("0000"); //정상
                newsVO.setErr_msg("정상적으로 입력되었습니다");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

		return newsVO;
	}

	@Bean
	public ItemWriter<NewsVO> newsApiWriter() {
		ItemWriter<NewsVO> writer = new ItemWriter<NewsVO>() {

			public void write(List<? extends NewsVO> items) throws Exception {

				for (NewsVO vo : items) {
					logger.info("searchQuery-----"+vo.getSearch_query());
					logger.info("newsVOToString-----"+vo.toString());
					setNewsAPI(vo);
				}

			}
		};

		return writer;
	}

	public void setNewsAPI(NewsVO newsVO) throws Exception {

  		List<NewsResultVO> newsItemList = null;
  		int apiNewsSeq = 0;
  		apiNewsSeq = newsMapper.getApiNewsSeq();
  		NewsResultVO newsResultVO = new NewsResultVO();

  		//API_NEWS INSERT
  		newsVO.setSeq_search(apiNewsSeq);
  		newsMapper.createApiNews(newsVO);

		//API_NEWS_RESULT INSERT
        if(newsVO.getTotal() != 0){
           newsItemList = newsVO.getItems();

           for(int i=0; i<newsItemList.size(); i++){
        	   newsResultVO = newsItemList.get(i);
        	   newsResultVO.setSeq_search(apiNewsSeq);
        	   newsResultVO.setPub_date(newsResultVO.getPubDate());
        	   newsResultVO.setNews_status("01");
        	   newsResultVO.setYn_use("Y");

        	   try {
        		 //뉴스결과 중복체크, insert
            	   int duplNewsVal = newsMapper.duplApiNewsResultByLink(newsResultVO);
            	   if(duplNewsVal < 1){
            		   //originallink이용해서 comm_cd에서 뉴스원정보 가져오기
            		   CodeInfo codeInfo = new CodeInfo();
            		   codeInfo.setCode_group("cd_news_cp");
            		   codeInfo.setCode_value(newsResultVO.getOriginallink().split("/")[2]);
            		   String newsCompany = codeMapper.getCommCdNmCode(codeInfo);
            		   newsResultVO.setNews_company(newsCompany);

            		   newsMapper.createApiNewsResult(newsResultVO);
            	   }
        	   } catch (Exception e) {
        		   logger.info("API_NEWS_RESULT테이블 INSERT ERROR : " + e.getMessage());

        		   newsVO.setErr_cd("9999"); //에러코드
        		   newsVO.setErr_msg("API_NEWS_RESULT테이블 INSERT ERROR");
        		   newsMapper.updateErrorApiNews(newsVO);

        		   break;
        	   }

           }
        }
	}

}
