package com.koscom.batch.test;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
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
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.koscom.batch.App;
import com.koscom.batch.test.domain.TestVO;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class TestJobCase1 {

	private static final Logger logger = LoggerFactory.getLogger(TestJobCase1.class);
	private static final String BATCH_NAME = "TestJobCase1";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private SimpleJobLauncher jobLauncher;
	@Autowired
    private SqlSessionFactory sqlSessionFactory;

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
		JobExecution execution = jobLauncher.run(baseJob1(), param);

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
	public Job baseJob1() throws Exception {
		return jobBuilderFactory.get("[Job - " + BATCH_NAME + "]").start(baseStep1()).build();
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
	public Step baseStep1() throws Exception { // chunk 큰덩어리 프로세스단위
		return stepBuilderFactory.get("[Step - " + BATCH_NAME + "]")
				.<TestVO, TestVO>chunk(2)
				.reader(testApiReader1())
				.processor(testApiProcessor1())
				.writer(testApiWriter1())
				.taskExecutor(App.taskExecutor())
				.build();
	}

	@Bean
	public ItemReader<TestVO> testApiReader1() throws Exception {

		MyBatisCursorItemReader<TestVO> reader = new MyBatisCursorItemReader<TestVO>();
		logger.debug("ItemReader start");
	    reader.setSqlSessionFactory(sqlSessionFactory);
        reader.setQueryId("com.koscom.batch.test.mapper.TestMapper.getCooconAPIinfo");
        return reader;

	}

	@Bean
	public ItemProcessor<TestVO, TestVO> testApiProcessor1() {
		return new ItemProcessor<TestVO, TestVO>() {

			@Override
			public TestVO process(TestVO data) throws Exception {
				logger.debug("ItemProcessor =" + data.getCd_fc() + "===" + data.getCd_org());
				return data;
			}
		};
	}

	@Bean
	public ItemWriter<TestVO> testApiWriter1() {

		ItemWriter<TestVO> writer = new ItemWriter<TestVO>() {

			public void write(List<? extends TestVO> items) throws Exception {

				for (TestVO test : items) {
					logger.debug("ItemWriter =" + test.toString());

					logger.debug("ItemWriter start");
					//MyBatisBatchItemWriter<TestVO> writer = new MyBatisBatchItemWriter<TestVO>();
					//writer.setSqlSessionFactory(sqlSessionFactory);
					//writer.setStatementId("com.koscom.batch.test.mapper.TestMapper.getCooconAPIinfo");

				}
			}
		};

		return writer;
	}
}
