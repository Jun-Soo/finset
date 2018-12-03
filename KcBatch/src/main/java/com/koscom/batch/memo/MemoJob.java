/**
 *
 * 작성자: 	김휘경
 * 작성일: 	20180618~20180619
 * 설명: 		메모 push 작업
 *
 */

package com.koscom.batch.memo;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.koscom.batch.App;
import com.koscom.batch.memo.domain.MemoPushInfo;
import com.koscom.batch.memo.mapper.MemoMapper;
import com.koscom.util.FcmUtil;
import com.koscom.util.StringUtil;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class MemoJob {

	private static final Logger logger = LoggerFactory.getLogger(MemoJob.class);
	private static final String BATCH_NAME = "MemoJob";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private SimpleJobLauncher jobLauncher;
	@Autowired
    private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private MemoMapper memoMapper;
	@Value("${spring.apiUrl.fcm}")
	private String fcmUrl;
	
	private List<MemoPushInfo> memoPushInfoList = new ArrayList<MemoPushInfo>();
	
	@Scheduled(cron = "${spring.scheduler.cron_5}")
	public void scheduler() throws Exception {
		String jobId = String.valueOf(System.currentTimeMillis());
		System.out.println("Started jobId : " + jobId);
		
		JobParameters param = new JobParametersBuilder().addString("JobID", BATCH_NAME+"["+jobId+"]").toJobParameters();
		jobLauncher.setTaskExecutor(App.taskExecutor());
		JobExecution execution = jobLauncher.run(memoPushJob(), param);

		logger.debug("end : " + param.getString("JobID") + ":::" + execution.getStatus());
	}
	
	public Job memoPushJob() throws Exception {
		return jobBuilderFactory.get("[Job - " + BATCH_NAME + "]").start(memoPushStep()).build();
	}
	public Step memoPushStep() throws Exception { // chunk 큰덩어리 프로세스단위
		return stepBuilderFactory.get("[Step - " + BATCH_NAME + "]")
				.<MemoPushInfo, MemoPushInfo>chunk(20)
				.reader(memoPushReader())
				.processor(memoPushProcessor())
				.writer(memoPushWriter())
				.listener(memoStepListener)
				.build();
	}
	
	@Bean
	public ItemReader<MemoPushInfo> memoPushReader() throws Exception {
		ItemReader<MemoPushInfo> reader = new ItemReader<MemoPushInfo>() {
			@Override
			public MemoPushInfo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
				logger.debug("--------------------start of memoPushReader---------------------");
				if(!memoPushInfoList.isEmpty()){
					return memoPushInfoList.remove(0);
				}
				logger.debug("--------------------end of memoPushReader---------------------");
				return null;
			}
		};
		return reader;
	}
	
	@Bean
	public ItemProcessor<MemoPushInfo, MemoPushInfo> memoPushProcessor() throws Exception {
		ItemProcessor<MemoPushInfo, MemoPushInfo> processor = new ItemProcessor<MemoPushInfo, MemoPushInfo>() {
			@Override
			public MemoPushInfo process(MemoPushInfo data) throws Exception {
				logger.debug("--------------------start of memoPushProcessor---------------------");
				logger.debug(data+"");
				logger.debug("--------------------end of memoPushProcessor---------------------");
				return data;
			}
		};
		return processor;
	}
	
	@Bean
	public ItemWriter<MemoPushInfo> memoPushWriter() throws Exception {
		ItemWriter<MemoPushInfo> writer = new ItemWriter<MemoPushInfo>() {
			@Override
			public void write(List<? extends MemoPushInfo> items) throws Exception {
				logger.debug("--------------------start of memoPushWriter---------------------");
				for(MemoPushInfo info: items) {
					logger.debug(info+"");
					sendFcm(info);
				}
				logger.debug("--------------------end of memoPushWriter---------------------");
			}
		};
		return writer;
	}
	
	private StepExecutionListener memoStepListener = new StepExecutionListener() { //각 주기마다 list를 다시 가져오기 위해 listener 추가
		@Override
		public void beforeStep(StepExecution stepExecution) {
			memoPushInfoList = getMemoPushInfoList();
		}
		@Override
		public ExitStatus afterStep(StepExecution stepExecution) {
			memoPushInfoList.clear();
			return null;
		}
	};
	
	private List<MemoPushInfo> getMemoPushInfoList(){ //주기와 무관하게 보내야 되는 메모의 정보들을 가져오는 메소드
		return memoMapper.getMemoPushInfo();
	}
	
	public void sendFcm(MemoPushInfo memoPushInfo) throws Exception { //실제로 push를 보내고 히스토리성 데이터를 쌓는 메소드
		//push에 필요한 정보들
		String no_person 	= memoPushInfo.getNo_person();
		String sendTo     = memoPushInfo.getFcm_token();
		String title      = "핀셋메모알리미";
		String body       = memoPushInfo.getMemo_text();
		String linkAddr   = "";
		String yn_os      = StringUtil.nullToString(memoPushInfo.getYn_os(), "1");
		String cd_push    = StringUtil.nullToString(memoPushInfo.getCd_push(), "1");

		//createPushEachInfo() 에 맞게 변수 변환
		memoPushInfo.setTitle(title);
		memoPushInfo.setBody(body);
		memoPushInfo.setLink_addr(linkAddr);

		memoPushInfo.setId_frt(no_person);
		memoPushInfo.setId_lst(no_person);
		
		//sendFcm이 throw를 시키지 않아서 if문을 통해 예외처리가 필요, url 제어를 추가함
		if(FcmUtil.sendFcmWithUrl(fcmUrl ,sendTo, title, body, linkAddr, yn_os, cd_push)){
			//푸시를 보냈다고 저장
			memoMapper.createPushEachInfo(memoPushInfo);
		} else {
			throw new Exception("푸시 처리 중 에러가 발생했습니다. FcmUtil의 sendFcmWithUrl()를 확인해주세요");
		}
	}
}
