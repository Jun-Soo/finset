package com.koscom.batch.test;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.gson.Gson;
import com.koscom.batch.App;
import com.koscom.batch.fss.domain.FssCompanyInfo;
import com.koscom.util.ReturnClass;
import com.koscom.util.URLConnection;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class TestJobCase2 {

	private static final Logger logger = LoggerFactory.getLogger(TestJobCase2.class);
	private static final String BATCH_NAME = "TestJobCase2";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private SimpleJobLauncher jobLauncher;

	@Value("${spring.apiUrl.fss}")
	private String apiUrl;

	int count = 0;

	/**
	 * 스케쥴러
	 *
	 * 주기적으로 Job 실행 cron 설정에 따라 실행 jobLauncher 1개당 job 1+ 와 step 1+ 를 사용가능
	 *
	 * @throws Exception
	 */
	@Scheduled(cron = "${spring.scheduler.cron_1}")
	public void scheduler() throws Exception {

		String jobId = String.valueOf(System.currentTimeMillis());
		System.out.println("Started jobId : " + jobId);

		JobParameters param = new JobParametersBuilder().addString("JobID", jobId).toJobParameters();
		JobExecution execution = jobLauncher.run(baseJob2(), param);

		System.out.println("end : " + param.getString("JobID") + ":::" + execution.getStatus());
	}

	/**
	 * 배치 Job
	 *
	 * baseStep 호출한다
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Job baseJob2() throws Exception {
		return jobBuilderFactory.get("[Job - " + BATCH_NAME + "]").listener(new JobExecutionListener() {

			@Override
			public void beforeJob(JobExecution jobExecution) {}
			@Override
			public void afterJob(JobExecution jobExecution) {
				count = 0;
			}

		}).start(baseStep2()).build();
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
	public Step baseStep2() throws Exception { // chunk 큰덩어리 프로세스단위
		return stepBuilderFactory.get("[Step - " + BATCH_NAME + "]")
				.<ReturnClass, FssCompanyInfo>chunk(1)
				.reader(testApiReader2())
				.processor(testApiProcessor2())
				.writer(testApiWriter2())
				.taskExecutor(App.taskExecutor())
				.build();
	}

	@Bean
	public ItemReader<ReturnClass> testApiReader2() {

		List<ReturnClass> returnClassList = getFssApi(apiUrl, "020000");

		ItemReader<ReturnClass> itemReader = new ListItemReader<ReturnClass>(returnClassList) {

			@Override
			public ReturnClass read() {

		        if (count < returnClassList.size()) {
                    return returnClassList.get(count++);
                } else {
                	 return null;
                }
			}
		};

		return itemReader;
	}



	@Bean
	public ItemProcessor<ReturnClass, FssCompanyInfo> testApiProcessor2() {
		return new ItemProcessor<ReturnClass, FssCompanyInfo>() {

			@Override
			public FssCompanyInfo process(ReturnClass data) throws Exception {
				FssCompanyInfo fssCompanyInfo = new FssCompanyInfo();
				Gson gson = new Gson();
				fssCompanyInfo = gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(data.getDes_message())).get("result").toString(), FssCompanyInfo.class);
				return fssCompanyInfo;
			}
		};
	}

	@Bean
	public ItemWriter<FssCompanyInfo> testApiWriter2() {

		ItemWriter<FssCompanyInfo> writer = new ItemWriter<FssCompanyInfo>() {

			public void write(List<? extends FssCompanyInfo> items) throws Exception {

				for (FssCompanyInfo test : items) {
					logger.debug("ItemWriter =" + test.toString());
				}
			}
		};

		return writer;
	}


	private List<ReturnClass> getFssApi(String apiUrl, String topFinGrpNo) {
		//pageNo
		int pageNo = 1;
		//몇 번이나 반복을 시키기 위해 임시로 지정
		int maxPageNo = 1;
		//return 시킬 class를 저장할 리스트 선언
		List<ReturnClass> list = new ArrayList<>();
		for(;pageNo<=maxPageNo;pageNo++){
			//기존에 짜여져 있던 부분
			String param = "auth=53e1793b7afaf71884f6659bb596a877&topFinGrpNo="+topFinGrpNo+"&pageNo="+pageNo;
			URLConnection url = new URLConnection();
			ReturnClass returnClass = url.sendReqPOST(apiUrl, param);

			//max page를 가져오기 위해 로직 설정
			if(pageNo==1){
				Gson gson = new Gson();
				int maxPageNoFromUrl = gson.fromJson(
						JSONObject
								.fromObject(
										JSONSerializer.toJSON(returnClass.getDes_message())).get("result")
								.toString(), FssCompanyInfo.class).getMax_page_no();
				//금감원에서 넘겨주는 maxPageNo 가 0 이라면 리스트에 아무것도 담지 않고 넘겨줘야 한다.
				if(maxPageNoFromUrl<1){
					return list;
				}else{
					list.add(returnClass);
				}
				//maxPageNo를 수정
				maxPageNo = maxPageNoFromUrl;
			}
		}
		return list;
	}
}
