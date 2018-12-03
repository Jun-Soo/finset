package com.koscom.batch.push;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.koscom.batch.App;
import com.koscom.batch.push.domain.PushEachForm;
import com.koscom.batch.push.domain.PushEachVO;
import com.koscom.batch.push.mapper.PushMapper;
import com.koscom.util.FcmUtil;
import com.koscom.util.StringUtil;


@Configuration
@EnableBatchProcessing
@EnableScheduling
public class PushDebtJob {

	private static final Logger logger = LoggerFactory.getLogger(PushDebtJob.class);
	private static final String BATCH_NAME = "PushDebtJobStep";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private SimpleJobLauncher jobLauncher;
	@Autowired
	private PushMapper pushMapper;
	@Resource
    private Environment environment;

	List<PushEachVO> pushDebtList = new ArrayList<PushEachVO>();
	int count = 0;

	/**
	 * 스케쥴러
	 *
	 * 주기적으로 Job 실행 cron 설정에 따라 실행 jobLauncher 1개당 job 1+ 와 step 1+ 를 사용가능
	 *
	 * @throws Exception
	 */
	@Scheduled(cron = "${spring.scheduler.cron_4}")
	public void scheduler() throws Exception {

		String jobId = String.valueOf(System.currentTimeMillis());
		System.out.println("Started jobId : " + jobId);

		JobParameters param = new JobParametersBuilder().addString("JobID", jobId).toJobParameters();
		JobExecution execution = jobLauncher.run(pushDebtApiJob(), param);

		System.out.println("end : " + param.getString("JobID") + ":::" + execution.getStatus());
	}

	/**
	 * 배치 Job
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Job pushDebtApiJob() throws Exception {
		return jobBuilderFactory.get("[Job - " + BATCH_NAME + "]").listener(new JobExecutionListener() {

			@Override
			public void beforeJob(JobExecution jobExecution) {
				//푸시 대상자 조회
				try {
					pushDebtList = pushDebtApiCall();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void afterJob(JobExecution jobExecution) {
				count = 0;
			}

		})
		.start(pushDebtApiStep())
		.build();
	}

	public List<PushEachVO> pushDebtApiCall() throws Exception {

		PushEachForm eventEachForm = new PushEachForm();
		List<PushEachVO> pushDebtList = pushMapper.listPushDebtPerson(eventEachForm);

		for (PushEachVO pushEachVO : pushDebtList) {
			logger.info("no_person = "+pushEachVO.getNo_person());
			logger.info("yn_os = "+pushEachVO.getYn_os());
			logger.info("cd_push = "+pushEachVO.getCd_push());
			logger.info("fcm_token = "+pushEachVO.getFcm_token());
		}

		return pushDebtList;
	}

	/**
	 *
	 *
	 * <pre>
	 * reader() : push debt list조회
	 * processor() : 데이터 가공
	 * writer() : fcm발송
	 * </pre>
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Step pushDebtApiStep() throws Exception { // chunk 큰덩어리 프로세스단위
		return stepBuilderFactory.get("[Step - " + BATCH_NAME + "]")
		  .<PushEachVO, PushEachVO>chunk(1)
		  .reader(pushDebtApiReader())
		  .processor(pushDebtApiProcessor())
		  .writer(pushDebtApiWriter())
//		  .taskExecutor(App.taskExecutor())
		  .build();
	}

	@Bean
	public ItemReader<PushEachVO> pushDebtApiReader() throws Exception {

		ItemReader<PushEachVO> itemReader = new ItemReader<PushEachVO>(){

			@Override
			public PushEachVO read() {

				PushEachVO pushEachVO = new PushEachVO();

				if (count < pushDebtList.size()) {
		        	pushEachVO = pushDebtList.get(count);
		        	count++;
		        	logger.info("count-----------"+count);
                    return pushEachVO;
                }

		        return null;
			}

		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<PushEachVO, PushEachVO> pushDebtApiProcessor() {
		return new ItemProcessor<PushEachVO, PushEachVO>() {

			@Override
			public PushEachVO process(PushEachVO data) throws Exception {
				return data;
			}
		};
	}

	@Bean
	public ItemWriter<PushEachVO> pushDebtApiWriter() {
		ItemWriter<PushEachVO> writer = new ItemWriter<PushEachVO>() {

			public void write(List<? extends PushEachVO> items) throws Exception {

				for (PushEachVO vo : items) {
					sendFcm(vo);
					logger.info("wrtier-----------");
				}

			}
		};

		return writer;
	}

	public void sendFcm(PushEachVO pushEachVO) throws Exception {

		String no_person 	= pushEachVO.getNo_person();
		String sendTo     = pushEachVO.getFcm_token();
		String title      = "핀셋부채정보알리미";
		String body       = "내일 상환 예정일 입니다. 상환 계좌 잔고를 확인해주세요.";
		String linkAddr   = "";
		String yn_os      = StringUtil.nullToString(pushEachVO.getYn_os(), "1");
		String cd_push    = StringUtil.nullToString(pushEachVO.getCd_push(), "1");

//		pushEachVO.setPush_divcd("01");

		pushEachVO.setTitle(title);
		pushEachVO.setBody(body);
		pushEachVO.setLink_addr(linkAddr);

		pushEachVO.setId_frt(no_person);
        pushEachVO.setId_lst(no_person);

		FcmUtil.sendFcm(sendTo, title, body, linkAddr, yn_os, cd_push);

		pushMapper.createPushEachInfo(pushEachVO);
	}
}
