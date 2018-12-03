/**
 *
 * 작성자: 	김휘경
 * 작성일: 	20180515
 * 설명: 		금감원 api 연계 배치화 - 프로세스에 맞게 수정작업
 * 수정: 		20180516 was에 맞게 수정 - 김휘경
 * 			20180517 reader 통합 및 주택담보, 전세자금, 개인신용대출 구현 - 김휘경
 * 			20180518  각 배치의 분기를 job에서 하는게 아니라 scheduler가 처리하도록 이동,jobId 에 따른 처리, maxPageNo 로직 변경
 *			20180627 전체 소스 구조 재설계,
 *					 *RESULT writer는 vo가 들어왔을 때 DB에 데이터를 ISERT INTO
 *					 *PRODUCT, *OPTION writer는 vo가 들어왔을 때 DB에 내용을 MERGE INTO 로 변경 - 강민경
 *
 * 구조:		각 api(company, mortgageLoan, rentHouseLoan, creditLoan) 별 확인해야 할 그룹이 지정되어 있음 (topFinGrpNoArr)
 * 			set* 함수는 각 list(Product, Option, Result) 에 값을 가져와서 저장하는 함수로 job 이 시작되기 전에 호출된다(*JobListener에 구현됨)
 * 			reader는 일관되게 list에 담긴 객체들을 넘기고, processor는 아무 처리 없이 writer로 넘김.
 * 			*RESULT writer는 vo가 들어왔을 때 DB에 데이터를 ISERT INTO
 * 			*PRODUCT, *OPTION writer는 vo가 들어왔을 때 DB에 내용을 MERGE INTO
 */

package com.koscom.batch.fss;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.gson.Gson;
import com.koscom.batch.App;
import com.koscom.batch.fss.mapper.FssMapper;
import com.koscom.batch.fss.domain.FssCompanyInfo;
import com.koscom.batch.fss.domain.FssCompanyOptionVO;
import com.koscom.batch.fss.domain.FssCompanyProductVO;
import com.koscom.batch.fss.domain.FssCompanyResultVO;
import com.koscom.batch.fss.domain.FssCreditLoanInfo;
import com.koscom.batch.fss.domain.FssCreditLoanOptionVO;
import com.koscom.batch.fss.domain.FssCreditLoanProductVO;
import com.koscom.batch.fss.domain.FssCreditLoanResultVO;
import com.koscom.batch.fss.domain.FssMortgageLoanInfo;
import com.koscom.batch.fss.domain.FssMortgageLoanOptionVO;
import com.koscom.batch.fss.domain.FssMortgageLoanProductVO;
import com.koscom.batch.fss.domain.FssMortgageLoanResultVO;
import com.koscom.batch.fss.domain.FssRentHouseLoanInfo;
import com.koscom.batch.fss.domain.FssRentHouseLoanOptionVO;
import com.koscom.batch.fss.domain.FssRentHouseLoanProductVO;
import com.koscom.batch.fss.domain.FssRentHouseLoanResultVO;
import com.koscom.util.ReturnClass;
import com.koscom.util.StringUtil;
import com.koscom.util.URLConnection;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class FssJob {
	//그룹 명은 지정되어 있음
	String [] topFinGrpNoArr = {"020000","030200","030300","050000","060000"};
	//로그를 찍기위해 선언
	private final Logger logger = LoggerFactory.getLogger(FssJob.class);

	//배치 이름
	private final String BATCH_NAME = "FssJob";

	//companyList
	private List<FssCompanyResultVO> companyResultList = new ArrayList<>();
	private List<FssCompanyProductVO> companyProductList = new ArrayList<>();
	private List<FssCompanyOptionVO> companyOptionList = new ArrayList<>();
	int companyResultCount = 0;
	int companyProductCount = 0;
	int companyOptionCount = 0;

	//mortgageLoanList
	private List<FssMortgageLoanResultVO> mortgageLoanResultList = new ArrayList<>();
	private List<FssMortgageLoanProductVO> mortgageLoanProductList = new ArrayList<>();
	private List<FssMortgageLoanOptionVO> mortgageLoanOptionList = new ArrayList<>();
	int mortgageLoanResultCount = 0;
	int mortgageLoanProductCount = 0;
	int mortgageLoanOptionCount = 0;

	//rentHouseLoanList
	private List<FssRentHouseLoanResultVO> rentHouseLoanResultList = new ArrayList<>();
	private List<FssRentHouseLoanProductVO> rentHouseLoanProductList = new ArrayList<>();
	private List<FssRentHouseLoanOptionVO> rentHouseLoanOptionList = new ArrayList<>();
	int rentHouseLoanResultCount = 0;
	int rentHouseLoanProductCount = 0;
	int rentHouseLoanOptionCount = 0;

	//creditLoanList
	private List<FssCreditLoanResultVO> creditLoanResultList = new ArrayList<>();
	private List<FssCreditLoanProductVO> creditLoanProductList = new ArrayList<>();
	private List<FssCreditLoanOptionVO> creditLoanOptionList = new ArrayList<>();
	int creditLoanResultCount = 0;
	int creditLoanProductCount = 0;
	int creditLoanOptionCount = 0;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private SimpleJobLauncher jobLauncher;
	@Autowired
	FssMapper fssMapper;

	@Value("${spring.apiUrl.fss}")
	private String apiUrl;

	@Scheduled(cron = "${spring.scheduler.cron_2}")
	public void scheduler() throws Exception{
		//각 배치의 id를 저장
		String [] jobs = {"company","mortgageLoan","rentHouseLoan","creditLoan"};

		//id 에 따라 job 생성 후 실행
		for(String jobId:jobs){
			logger.debug("Started jobId : " + jobId + " in FssJob");
			JobParameters param = null;
			JobExecution execution = null;
			switch(jobId){
				case "company":
					param = new JobParametersBuilder().addString("JobID", jobId+"["+String.valueOf(System.currentTimeMillis())+"]").toJobParameters();
					execution = jobLauncher.run(createCompanyJob(), param);
					break;
				case "mortgageLoan":
					param = new JobParametersBuilder().addString("JobID", jobId+"["+String.valueOf(System.currentTimeMillis())+"]").toJobParameters();
					execution = jobLauncher.run(createMortgageLoanJob(), param);
					break;
				case "rentHouseLoan":
					param = new JobParametersBuilder().addString("JobID", jobId+"["+String.valueOf(System.currentTimeMillis())+"]").toJobParameters();
					execution = jobLauncher.run(createRentHouseLoanJob(), param);
					break;
				case "creditLoan":
					param = new JobParametersBuilder().addString("JobID", jobId+"["+String.valueOf(System.currentTimeMillis())+"]").toJobParameters();
					execution = jobLauncher.run(createCreditLoanJob(), param);
					break;
				default:
					break;
			}

			if(execution!=null){
				logger.debug("end : " + param.getString("JobID") + ":::" + execution.getStatus() + " in FssJob");
			} else{
				logger.debug("there is no execution");
			}
		}
	}

	//금감원 api를 호출해 자료를 내려받는 함수
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
			ReturnClass returnClass = url.sendReqPOST(apiUrl, param, "utf-8");

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

	//companyJob
	@Bean
	public Job createCompanyJob() throws Exception{
		return jobBuilderFactory.get("[Job-"+BATCH_NAME+".createFssCompany]")
			.listener(companyJobListener)
			.start(createCompanyResultStep())
			.next(createCompanyProductStep())
			.next(createCompanyOptionStep())
			.build();
	}

	//자료를 세팅하고, job이 끝나면 초기화 시킬 listener 구현
	private JobExecutionListener companyJobListener = new JobExecutionListener() {
		@Override
		public void beforeJob(JobExecution jobExecution) {
			try {
				setCompanyLists();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void afterJob(JobExecution jobExecution) {
			companyResultCount = 0;
			companyProductCount = 0;
			companyOptionCount = 0;
			companyResultList.clear();
			companyProductList.clear();
			companyOptionList.clear();
		}
	};

	//api를 호출해서 자료를 받아오는 메소드
	public void setCompanyLists() throws Exception {

		//json 형식을 사용하기 위한 Gson 선언
		Gson gson = new Gson();
		//group에 맞춰 데이터를 가져온 후 전부 list에 담아둔다
		for(String finGrp: topFinGrpNoArr){
			List<ReturnClass> companyList = getFssApi(apiUrl, finGrp);
			for(ReturnClass returnClass: companyList){
				FssCompanyInfo fssCompanyInfo =
						gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(returnClass.getDes_message())).get("result").toString(), FssCompanyInfo.class);

				FssCompanyResultVO resultVO = new FssCompanyResultVO();
				resultVO.setErr_cd(StringUtil.nullToString(fssCompanyInfo.getErr_cd()));		//응답코드
				resultVO.setErr_msg(StringUtil.nullToString(fssCompanyInfo.getErr_msg()));		//응답메시지
				resultVO.setTotal_count(fssCompanyInfo.getTotal_count());		//총 상품건수
				resultVO.setMax_page_no(fssCompanyInfo.getMax_page_no());		//총 페이지 건수(총 페이지 건수 = 총 상품건수/1회 조회 개수*)
				resultVO.setNow_page_no(fssCompanyInfo.getNow_page_no());
				resultVO.setId_frt("BATCH");
				resultVO.setId_lst("BATCH");
				companyResultList.add(resultVO);

				companyProductList.addAll(fssCompanyInfo.getBaseList());
				companyOptionList.addAll(fssCompanyInfo.getOptionList());
			}
		}

	}

	@Bean
	public Step createCompanyResultStep() throws Exception {
		return stepBuilderFactory.get("[Step-"+BATCH_NAME+".fssCompanyResult]")
			.<FssCompanyResultVO,FssCompanyResultVO>chunk(1)
			.reader(createFssCompanyResultReader())
			.processor(createFssCompanyResultProcessor())
			.writer(createFssCompanyResultWriter())
			.taskExecutor(App.taskExecutor())
			.build();
	}

	@Bean
	public ItemReader<FssCompanyResultVO> createFssCompanyResultReader() throws Exception{
		ItemReader<FssCompanyResultVO> itemReader = new ItemReader<FssCompanyResultVO>(){

			@Override
			public FssCompanyResultVO read() {

				FssCompanyResultVO fssCompanyResultVO = new FssCompanyResultVO();

				if (companyResultCount < companyResultList.size()) {
					fssCompanyResultVO = companyResultList.get(companyResultCount);
		        	companyResultCount++;
		        	logger.info("companyResultCount-----------"+companyResultCount);
                    return fssCompanyResultVO;
                }

		        return null;
			}

		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<FssCompanyResultVO, FssCompanyResultVO> createFssCompanyResultProcessor(){
		return new ItemProcessor<FssCompanyResultVO, FssCompanyResultVO>() {
			@Override
			public FssCompanyResultVO process(FssCompanyResultVO item) throws Exception {
				logger.info("-------------------createFssCompanyResultProcessor------------------");
				return item;
			}
		};
	}

	@Bean
	public ItemWriter<FssCompanyResultVO> createFssCompanyResultWriter(){
		ItemWriter<FssCompanyResultVO> writer = new ItemWriter<FssCompanyResultVO>() {
			@Override
			public void write(List<? extends FssCompanyResultVO> items) throws Exception {
				for(FssCompanyResultVO fssCompanyResultVO: items) {
					fssMapper.createFssCompanyResultInfo(fssCompanyResultVO);
				}
			}
		};
		return writer;
	}

	@Bean
	public Step createCompanyProductStep() throws Exception {
		return stepBuilderFactory.get("[Step-"+BATCH_NAME+".fssCompanyProduct]")
				.<FssCompanyProductVO,FssCompanyProductVO>chunk(1)
				.reader(createFssCompanyProductReader())
				.processor(createFssCompanyProductProcessor())
				.writer(createFssCompanyProductWriter())
				.taskExecutor(App.taskExecutor())
				.build();
	}

	@Bean
	public ItemReader<FssCompanyProductVO> createFssCompanyProductReader() throws Exception {
		ItemReader<FssCompanyProductVO> itemReader = new ItemReader<FssCompanyProductVO>(){

			@Override
			public FssCompanyProductVO read() {

				FssCompanyProductVO fssCompanyProductVO = new FssCompanyProductVO();

				if (companyProductCount < companyProductList.size()) {
					fssCompanyProductVO = companyProductList.get(companyProductCount);
		        	companyProductCount++;
		        	logger.info("companyProductCount-----------"+companyProductCount);
                    return fssCompanyProductVO;
                }

		        return null;
			}

		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<FssCompanyProductVO, FssCompanyProductVO> createFssCompanyProductProcessor(){
		return new ItemProcessor<FssCompanyProductVO, FssCompanyProductVO>() {
			@Override
			public FssCompanyProductVO process(FssCompanyProductVO item) throws Exception {
				logger.info("-------------------createFssCompanyProductProcessor------------------");
				return item;
			}
		};
	}

	@Bean
	public ItemWriter<FssCompanyProductVO> createFssCompanyProductWriter(){
		ItemWriter<FssCompanyProductVO> writer = new ItemWriter<FssCompanyProductVO>() {
			@Override
			public void write(List<? extends FssCompanyProductVO> items) throws Exception {
				for(FssCompanyProductVO fssCompanyProductVO: items) {
					fssCompanyProductVO.setId_frt("BATCH");
					fssCompanyProductVO.setId_lst("BATCH");
					fssMapper.mergeFssCompanyProductInfo(fssCompanyProductVO);
				}
			}
		};
		return writer;
	}

	@Bean
	public Step createCompanyOptionStep() throws Exception {
		return stepBuilderFactory.get("[Step-"+BATCH_NAME+".fssCompanyOption]")
			.<FssCompanyOptionVO,FssCompanyOptionVO>chunk(1)
			.reader(createFssCompanyOptionReader())
			.processor(createFssCompanyOptionProcessor())
			.writer(createFssCompanyOptionWriter())
			.taskExecutor(App.taskExecutor())
			.build();
	}

	@Bean
	public ItemReader<FssCompanyOptionVO> createFssCompanyOptionReader() throws Exception {
		ItemReader<FssCompanyOptionVO> itemReader = new ItemReader<FssCompanyOptionVO>(){

			@Override
			public FssCompanyOptionVO read() {

				FssCompanyOptionVO fssCompanyOptionVO = new FssCompanyOptionVO();

				if (companyOptionCount < companyOptionList.size()) {
					fssCompanyOptionVO = companyOptionList.get(companyOptionCount);
		        	companyOptionCount++;
		        	logger.info("companyOptionCount-----------"+companyOptionCount);
                    return fssCompanyOptionVO;
                }

		        return null;
			}

		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<FssCompanyOptionVO, FssCompanyOptionVO> createFssCompanyOptionProcessor(){
		return new ItemProcessor<FssCompanyOptionVO, FssCompanyOptionVO>() {
			@Override
			public FssCompanyOptionVO process(FssCompanyOptionVO item) throws Exception {
				logger.info("-------------------createFssCompanyOptionProcessor------------------");
				return item;
			}
		};
	}

	@Bean
	public ItemWriter<FssCompanyOptionVO> createFssCompanyOptionWriter(){
		ItemWriter<FssCompanyOptionVO> writer = new ItemWriter<FssCompanyOptionVO>() {
			@Override
			public void write(List<? extends FssCompanyOptionVO> items) throws Exception {
				for(FssCompanyOptionVO fssCompanyOptionVO: items) {
					fssCompanyOptionVO.setId_frt("BATCH");
					fssCompanyOptionVO.setId_lst("BATCH");
					fssMapper.mergeFssCompanyOptionInfo(fssCompanyOptionVO);
				}
			}
		};
		return writer;
	}

	//mortgageLoanJob
	@Bean
	public Job createMortgageLoanJob() throws Exception{
		return jobBuilderFactory.get("[Job-"+BATCH_NAME+".createFssMortgageLoan]")
			.listener(mortgageLoanJobListener)
			.start(createMortgageLoanResultStep())
			.next(createMortgageLoanProductStep())
			.next(createMortgageLoanOptionStep())
			.build();
	}

	private JobExecutionListener mortgageLoanJobListener = new JobExecutionListener() {
		@Override
		public void beforeJob(JobExecution jobExecution) {
			try {
				setMortgageLoanLists();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void afterJob(JobExecution jobExecution) {
			mortgageLoanResultCount = 0;
			mortgageLoanProductCount = 0;
			mortgageLoanOptionCount = 0;
			mortgageLoanResultList.clear();
			mortgageLoanProductList.clear();
			mortgageLoanOptionList.clear();
		}
	};

	//api를 호출해서 자료를 받아오는 메소드
	public void setMortgageLoanLists() throws Exception{

		String apiUrl = "http://finlife.fss.or.kr/finlifeapi/mortgageLoanProductsSearch.json";
		//json 형식을 사용하기 위한 Gson 선언
		Gson gson = new Gson();
		//group에 맞춰 데이터를 가져온 후 전부 list에 담아둔다
		for(String finGrp: topFinGrpNoArr){
			List<ReturnClass> mortgageLoanList = getFssApi(apiUrl, finGrp);
			for(ReturnClass returnClass: mortgageLoanList){
				FssMortgageLoanInfo fssMortgageLoanInfo =
						gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(returnClass.getDes_message())).get("result").toString(), FssMortgageLoanInfo.class);

				FssMortgageLoanResultVO resultVO = new FssMortgageLoanResultVO();
				resultVO.setErr_cd(StringUtil.nullToString(fssMortgageLoanInfo.getErr_cd()));		//응답코드
				resultVO.setErr_msg(StringUtil.nullToString(fssMortgageLoanInfo.getErr_msg()));		//응답메시지
				resultVO.setTotal_count(fssMortgageLoanInfo.getTotal_count());		//총 상품건수
				resultVO.setMax_page_no(fssMortgageLoanInfo.getMax_page_no());		//총 페이지 건수(총 페이지 건수 = 총 상품건수/1회 조회 개수*)
				resultVO.setNow_page_no(fssMortgageLoanInfo.getNow_page_no());		//현재 조회 페이지 번호
				resultVO.setId_frt("BATCH");
				resultVO.setId_lst("BATCH");
				mortgageLoanResultList.add(resultVO);

				mortgageLoanProductList.addAll(fssMortgageLoanInfo.getBaseList());
				mortgageLoanOptionList.addAll(fssMortgageLoanInfo.getOptionList());
			}
		}
	}

	@Bean
	public Step createMortgageLoanResultStep() throws Exception {
		return stepBuilderFactory.get("[Step-"+BATCH_NAME+".fssMortgageLoanResult]")
			.<FssMortgageLoanResultVO,FssMortgageLoanResultVO>chunk(1)
			.reader(createFssMortgageLoanResultReader())
			.processor(createFssMortgageLoanResultProcessor())
			.writer(createFssMortgageLoanResultWriter())
			.taskExecutor(App.taskExecutor())
			.build();
	}

	@Bean
	public ItemReader<FssMortgageLoanResultVO> createFssMortgageLoanResultReader() throws Exception {
		ItemReader<FssMortgageLoanResultVO> itemReader = new ItemReader<FssMortgageLoanResultVO>(){

			@Override
			public FssMortgageLoanResultVO read() {

				FssMortgageLoanResultVO fssMortgageLoanResultVO = new FssMortgageLoanResultVO();

				if (mortgageLoanResultCount < mortgageLoanResultList.size()) {
					fssMortgageLoanResultVO = mortgageLoanResultList.get(mortgageLoanResultCount);
		        	mortgageLoanResultCount++;
		        	logger.info("mortgageLoanResultCount-----------"+mortgageLoanResultCount);
                    return fssMortgageLoanResultVO;
                }

		        return null;
			}

		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<FssMortgageLoanResultVO, FssMortgageLoanResultVO> createFssMortgageLoanResultProcessor(){
		return new ItemProcessor<FssMortgageLoanResultVO, FssMortgageLoanResultVO>() {
			@Override
			public FssMortgageLoanResultVO process(FssMortgageLoanResultVO item) throws Exception {
				logger.info("-------------------createFssMortgageLoanResultProcessor------------------");
				return item;
			}
		};
	}

	@Bean
	public ItemWriter<FssMortgageLoanResultVO> createFssMortgageLoanResultWriter(){
		ItemWriter<FssMortgageLoanResultVO> writer = new ItemWriter<FssMortgageLoanResultVO>() {
			@Override
			public void write(List<? extends FssMortgageLoanResultVO> items) throws Exception {
				for(FssMortgageLoanResultVO fssMortgageLoanResultVO: items) {
					fssMapper.createFssMortgageLoanResultInfo(fssMortgageLoanResultVO);
				}
			}
		};
		return writer;
	}

	@Bean
	public Step createMortgageLoanProductStep() throws Exception {
		return stepBuilderFactory.get("[Step-"+BATCH_NAME+".fssMortgageLoanProduct]")
			.<FssMortgageLoanProductVO,FssMortgageLoanProductVO>chunk(1)
			.reader(createFssMortgageLoanProductReader())
			.processor(createFssMortgageLoanProductProcessor())
			.writer(createFssMortgageLoanProductWriter())
			.taskExecutor(App.taskExecutor())
			.build();
	}

	@Bean
	public ItemReader<FssMortgageLoanProductVO> createFssMortgageLoanProductReader() throws Exception {
		ItemReader<FssMortgageLoanProductVO> itemReader = new ItemReader<FssMortgageLoanProductVO>(){

			@Override
			public FssMortgageLoanProductVO read() {

				FssMortgageLoanProductVO fssMortgageLoanProductVO = new FssMortgageLoanProductVO();

				if (mortgageLoanProductCount < mortgageLoanProductList.size()) {
					fssMortgageLoanProductVO = mortgageLoanProductList.get(mortgageLoanProductCount);
		        	mortgageLoanProductCount++;
		        	logger.info("mortgageLoanProductCount-----------"+mortgageLoanProductCount);
                    return fssMortgageLoanProductVO;
                }

		        return null;
			}

		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<FssMortgageLoanProductVO, FssMortgageLoanProductVO> createFssMortgageLoanProductProcessor(){
		return new ItemProcessor<FssMortgageLoanProductVO, FssMortgageLoanProductVO>() {
			@Override
			public FssMortgageLoanProductVO process(FssMortgageLoanProductVO item) throws Exception {
				logger.info("-------------------createFssMortgageLoanProductProcessor------------------");
				return item;
			}
		};
	}

	@Bean
	public ItemWriter<FssMortgageLoanProductVO> createFssMortgageLoanProductWriter(){
		ItemWriter<FssMortgageLoanProductVO> writer = new ItemWriter<FssMortgageLoanProductVO>() {
			@Override
			public void write(List<? extends FssMortgageLoanProductVO> items) throws Exception {
				for(FssMortgageLoanProductVO fssMortgageLoanProductVO: items) {
					fssMortgageLoanProductVO.setId_frt("BATCH");
					fssMortgageLoanProductVO.setId_lst("BATCH");
					fssMapper.mergeFssMortgageLoanProductInfo(fssMortgageLoanProductVO);
				}
			}
		};
		return writer;
	}

	@Bean
	public Step createMortgageLoanOptionStep() throws Exception {
		return stepBuilderFactory.get("[Step-"+BATCH_NAME+".fssMortgageLoanOption]")
			.<FssMortgageLoanOptionVO,FssMortgageLoanOptionVO>chunk(1)
			.reader(createFssMortgageLoanOptionReader())
			.processor(createFssMortgageLoanOptionProcessor())
			.writer(createFssMortgageLoanOptionWriter())
			.taskExecutor(App.taskExecutor())
			.build();
	}

	@Bean
	public ItemReader<FssMortgageLoanOptionVO> createFssMortgageLoanOptionReader() throws Exception {
		ItemReader<FssMortgageLoanOptionVO> itemReader = new ItemReader<FssMortgageLoanOptionVO>(){

			@Override
			public FssMortgageLoanOptionVO read() {

				FssMortgageLoanOptionVO fssMortgageLoanOptionVO = new FssMortgageLoanOptionVO();

				if (mortgageLoanOptionCount < mortgageLoanOptionList.size()) {
					fssMortgageLoanOptionVO = mortgageLoanOptionList.get(mortgageLoanOptionCount);
		        	mortgageLoanOptionCount++;
		        	logger.info("mortgageLoanOptionCount-----------"+mortgageLoanOptionCount);
                    return fssMortgageLoanOptionVO;
                }

		        return null;
			}

		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<FssMortgageLoanOptionVO, FssMortgageLoanOptionVO> createFssMortgageLoanOptionProcessor(){
		return new ItemProcessor<FssMortgageLoanOptionVO, FssMortgageLoanOptionVO>() {
			@Override
			public FssMortgageLoanOptionVO process(FssMortgageLoanOptionVO item) throws Exception {
				logger.info("-------------------createFssMortgageLoanOptionProcessor------------------");
				return item;
			}
		};
	}

	@Bean
	public ItemWriter<FssMortgageLoanOptionVO> createFssMortgageLoanOptionWriter(){
		ItemWriter<FssMortgageLoanOptionVO> writer = new ItemWriter<FssMortgageLoanOptionVO>() {
			@Override
			public void write(List<? extends FssMortgageLoanOptionVO> items) throws Exception {
				for(FssMortgageLoanOptionVO fssMortgageLoanOptionVO: items) {
					fssMortgageLoanOptionVO.setId_frt("BATCH");
					fssMortgageLoanOptionVO.setId_lst("BATCH");
					fssMapper.mergeFssMortgageLoanOptionInfo(fssMortgageLoanOptionVO);
				}
			}
		};
		return writer;
	}

	//rentHouseLoanJob
	@Bean
	public Job createRentHouseLoanJob() throws Exception {
		return jobBuilderFactory.get("[Job-"+BATCH_NAME+".createFssRentHouseLoan]")
			.listener(rentHouseLoanJobListener)
			.start(createRentHouseLoanResultStep())
			.next(createRentHouseLoanProductStep())
			.next(createRentHouseLoanOptionStep())
			.build();
	}

	private JobExecutionListener rentHouseLoanJobListener = new JobExecutionListener() {
		@Override
		public void beforeJob(JobExecution jobExecution) {
			try {
				setRentHouseLoanLists();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void afterJob(JobExecution jobExecution) {
			rentHouseLoanResultCount = 0;
			rentHouseLoanProductCount = 0;
			rentHouseLoanOptionCount = 0;
			rentHouseLoanResultList.clear();
			rentHouseLoanProductList.clear();
			rentHouseLoanOptionList.clear();
		}
	};

	//api를 호출해서 자료를 받아오는 메소드
	public void setRentHouseLoanLists() throws Exception {

		String apiUrl = "http://finlife.fss.or.kr/finlifeapi/rentHouseLoanProductsSearch.json";
		//json 형식을 사용하기 위한 Gson 선언
		Gson gson = new Gson();
		//group에 맞춰 데이터를 가져온 후 전부 list에 담아둔다
		for(String finGrp: topFinGrpNoArr){
			List<ReturnClass> rentHouseLoanList = getFssApi(apiUrl, finGrp);
			for(ReturnClass returnClass: rentHouseLoanList){
				FssRentHouseLoanInfo fssRentHouseLoanInfo =
						gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(returnClass.getDes_message())).get("result").toString(), FssRentHouseLoanInfo.class);

				FssRentHouseLoanResultVO resultVO = new FssRentHouseLoanResultVO();
				resultVO.setErr_cd(StringUtil.nullToString(fssRentHouseLoanInfo.getErr_cd()));		//응답코드
				resultVO.setErr_msg(StringUtil.nullToString(fssRentHouseLoanInfo.getErr_msg()));		//응답메시지
				resultVO.setTotal_count(fssRentHouseLoanInfo.getTotal_count());		//총 상품건수
				resultVO.setMax_page_no(fssRentHouseLoanInfo.getMax_page_no());		//총 페이지 건수(총 페이지 건수 = 총 상품건수/1회 조회 개수*)
				resultVO.setNow_page_no(fssRentHouseLoanInfo.getNow_page_no());		//현재 조회 페이지 번호
				resultVO.setId_frt("BATCH");
				resultVO.setId_lst("BATCH");
				rentHouseLoanResultList.add(resultVO);

				rentHouseLoanProductList.addAll(fssRentHouseLoanInfo.getBaseList());
				rentHouseLoanOptionList.addAll(fssRentHouseLoanInfo.getOptionList());

			}
		}
	}

	@Bean
	public Step createRentHouseLoanResultStep() throws Exception {
		return stepBuilderFactory.get("[Step-"+BATCH_NAME+".fssRentHouseLoanResult]")
			.<FssRentHouseLoanResultVO,FssRentHouseLoanResultVO>chunk(1)
			.reader(createFssRentHouseLoanResultReader())
			.processor(createFssRentHouseLoanResultProcessor())
			.writer(createFssRentHouseLoanResultWriter())
			.taskExecutor(App.taskExecutor())
			.build();
	}

	@Bean
	public ItemReader<FssRentHouseLoanResultVO> createFssRentHouseLoanResultReader() throws Exception {
		ItemReader<FssRentHouseLoanResultVO> itemReader = new ItemReader<FssRentHouseLoanResultVO>(){

			@Override
			public FssRentHouseLoanResultVO read() {

				FssRentHouseLoanResultVO fssRentHouseLoanResultVO = new FssRentHouseLoanResultVO();

				if (rentHouseLoanResultCount < rentHouseLoanResultList.size()) {
					fssRentHouseLoanResultVO = rentHouseLoanResultList.get(rentHouseLoanResultCount);
		        	rentHouseLoanResultCount++;
		        	logger.info("rentHouseLoanResultCount-----------"+rentHouseLoanResultCount);
                    return fssRentHouseLoanResultVO;
                }

		        return null;
			}

		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<FssRentHouseLoanResultVO, FssRentHouseLoanResultVO> createFssRentHouseLoanResultProcessor(){
		return new ItemProcessor<FssRentHouseLoanResultVO, FssRentHouseLoanResultVO>() {
			@Override
			public FssRentHouseLoanResultVO process(FssRentHouseLoanResultVO item) throws Exception {
				logger.info("-------------------createFssRentHouseLoanResultProcessor------------------");
				return item;
			}
		};
	}

	@Bean
	public ItemWriter<FssRentHouseLoanResultVO> createFssRentHouseLoanResultWriter(){
		ItemWriter<FssRentHouseLoanResultVO> writer = new ItemWriter<FssRentHouseLoanResultVO>() {
			@Override
			public void write(List<? extends FssRentHouseLoanResultVO> items) throws Exception {
				for(FssRentHouseLoanResultVO fssRentHouseLoanResultVO: items) {
					fssMapper.createFssRentHouseLoanResultInfo(fssRentHouseLoanResultVO);
				}
			}
		};
		return writer;
	}

	@Bean
	public Step createRentHouseLoanProductStep() throws Exception {
		return stepBuilderFactory.get("[Step-"+BATCH_NAME+".fssRentHouseLoanProduct]")
			.<FssRentHouseLoanProductVO,FssRentHouseLoanProductVO>chunk(1)
			.reader(createFssRentHouseLoanProductReader())
			.processor(createFssRentHouseLoanProductProcessor())
			.writer(createFssRentHouseLoanProductWriter())
			.taskExecutor(App.taskExecutor())
			.build();
	}

	@Bean
	public ItemReader<FssRentHouseLoanProductVO> createFssRentHouseLoanProductReader() throws Exception {
		ItemReader<FssRentHouseLoanProductVO> itemReader = new ItemReader<FssRentHouseLoanProductVO>(){

			@Override
			public FssRentHouseLoanProductVO read() {

				FssRentHouseLoanProductVO fssRentHouseLoanProductVO = new FssRentHouseLoanProductVO();

				if (rentHouseLoanProductCount < rentHouseLoanProductList.size()) {
					fssRentHouseLoanProductVO = rentHouseLoanProductList.get(rentHouseLoanProductCount);
		        	rentHouseLoanProductCount++;
		        	logger.info("rentHouseLoanProductCount-----------"+rentHouseLoanProductCount);
                    return fssRentHouseLoanProductVO;
                }

		        return null;
			}

		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<FssRentHouseLoanProductVO, FssRentHouseLoanProductVO> createFssRentHouseLoanProductProcessor(){
		return new ItemProcessor<FssRentHouseLoanProductVO, FssRentHouseLoanProductVO>() {
			@Override
			public FssRentHouseLoanProductVO process(FssRentHouseLoanProductVO item) throws Exception {
				logger.info("-------------------createFssRentHouseLoanProductProcessor------------------");
				return item;
			}
		};
	}

	@Bean
	public ItemWriter<FssRentHouseLoanProductVO> createFssRentHouseLoanProductWriter(){
		ItemWriter<FssRentHouseLoanProductVO> writer = new ItemWriter<FssRentHouseLoanProductVO>() {
			@Override
			public void write(List<? extends FssRentHouseLoanProductVO> items) throws Exception {
				for(FssRentHouseLoanProductVO fssRentHouseLoanProductVO: items) {
					fssRentHouseLoanProductVO.setId_frt("BATCH");
					fssRentHouseLoanProductVO.setId_lst("BATCH");
					fssMapper.mergeFssRentHouseLoanProductInfo(fssRentHouseLoanProductVO);
				}
			}
		};
		return writer;
	}

	@Bean
	public Step createRentHouseLoanOptionStep() throws Exception {
		return stepBuilderFactory.get("[Step-"+BATCH_NAME+".fssRentHouseLoanOption]")
			.<FssRentHouseLoanOptionVO,FssRentHouseLoanOptionVO>chunk(1)
			.reader(createFssRentHouseLoanOptionReader())
			.processor(createFssRentHouseLoanOptionProcessor())
			.writer(createFssRentHouseLoanOptionWriter())
			.taskExecutor(App.taskExecutor())
			.build();
	}

	@Bean
	public ItemReader<FssRentHouseLoanOptionVO> createFssRentHouseLoanOptionReader() throws Exception {
		ItemReader<FssRentHouseLoanOptionVO> itemReader = new ItemReader<FssRentHouseLoanOptionVO>(){

			@Override
			public FssRentHouseLoanOptionVO read() {

				FssRentHouseLoanOptionVO fssRentHouseLoanOptionVO = new FssRentHouseLoanOptionVO();

				if (rentHouseLoanOptionCount < rentHouseLoanOptionList.size()) {
					fssRentHouseLoanOptionVO = rentHouseLoanOptionList.get(rentHouseLoanOptionCount);
		        	rentHouseLoanOptionCount++;
		        	logger.info("rentHouseLoanOptionCount-----------"+rentHouseLoanOptionCount);
                    return fssRentHouseLoanOptionVO;
                }

		        return null;
			}

		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<FssRentHouseLoanOptionVO, FssRentHouseLoanOptionVO> createFssRentHouseLoanOptionProcessor(){
		return new ItemProcessor<FssRentHouseLoanOptionVO, FssRentHouseLoanOptionVO>() {
			@Override
			public FssRentHouseLoanOptionVO process(FssRentHouseLoanOptionVO item) throws Exception {
				logger.info("-------------------createFssRentHouseLoanOptionProcessor------------------");
				return item;
			}
		};
	}

	@Bean
	public ItemWriter<FssRentHouseLoanOptionVO> createFssRentHouseLoanOptionWriter(){
		ItemWriter<FssRentHouseLoanOptionVO> writer = new ItemWriter<FssRentHouseLoanOptionVO>() {
			@Override
			public void write(List<? extends FssRentHouseLoanOptionVO> items) throws Exception {
				for(FssRentHouseLoanOptionVO fssRentHouseLoanOptionVO: items) {
					fssRentHouseLoanOptionVO.setId_frt("BATCH");
					fssRentHouseLoanOptionVO.setId_lst("BATCH");
					fssMapper.mergeFssRentHouseLoanOptionInfo(fssRentHouseLoanOptionVO);
				}
			}
		};
		return writer;
	}

	//creditLoanJob
	@Bean
	public Job createCreditLoanJob() throws Exception{
		return jobBuilderFactory.get("[Job-"+BATCH_NAME+".createFssCreditLoan]")
			.listener(creditLoanJobListener)
			.start(createCreditLoanResultStep())
			.next(createCreditLoanProductStep())
			.next(createCreditLoanOptionStep())
			.build();
	}

	private JobExecutionListener creditLoanJobListener = new JobExecutionListener() {
		@Override
		public void beforeJob(JobExecution jobExecution) {
			try {
				setCreditLoanLists();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void afterJob(JobExecution jobExecution) {
			creditLoanResultCount = 0;
			creditLoanProductCount = 0;
			creditLoanOptionCount = 0;
			creditLoanResultList.clear();
			creditLoanProductList.clear();
			creditLoanOptionList.clear();
		}
	};

	//api를 호출해서 자료를 받아오는 메소드
	public void setCreditLoanLists() throws Exception{

		String apiUrl = "http://finlife.fss.or.kr/finlifeapi/creditLoanProductsSearch.json";
		//json 형식을 사용하기 위한 Gson 선언
		Gson gson = new Gson();
		//group에 맞춰 데이터를 가져온 후 전부 list에 담아둔다
		for(String finGrp: topFinGrpNoArr){

			List<ReturnClass> creditLoanList = getFssApi(apiUrl, finGrp);
			for(ReturnClass returnClass: creditLoanList){
				FssCreditLoanInfo fssCreditLoanInfo =
						gson.fromJson(JSONObject.fromObject(JSONSerializer.toJSON(returnClass.getDes_message())).get("result").toString(), FssCreditLoanInfo.class);

				FssCreditLoanResultVO resultVO = new FssCreditLoanResultVO();
				resultVO.setErr_cd(StringUtil.nullToString(fssCreditLoanInfo.getErr_cd()));		//응답코드
				resultVO.setErr_msg(StringUtil.nullToString(fssCreditLoanInfo.getErr_msg()));		//응답메시지
				resultVO.setTotal_count(fssCreditLoanInfo.getTotal_count());		//총 상품건수
				resultVO.setMax_page_no(fssCreditLoanInfo.getMax_page_no());		//총 페이지 건수(총 페이지 건수 = 총 상품건수/1회 조회 개수*)
				resultVO.setNow_page_no(fssCreditLoanInfo.getNow_page_no());		//현재 조회 페이지 번호
				resultVO.setId_frt("BATCH");
				resultVO.setId_lst("BATCH");
				creditLoanResultList.add(resultVO);

				creditLoanProductList.addAll(fssCreditLoanInfo.getBaseList());
				creditLoanOptionList.addAll(fssCreditLoanInfo.getOptionList());

			}
		}
	}

	@Bean
	public Step createCreditLoanResultStep() throws Exception{
		return stepBuilderFactory.get("[Step-"+BATCH_NAME+".fssCreditLoanResult]")
			.<FssCreditLoanResultVO,FssCreditLoanResultVO>chunk(1)
			.reader(createFssCreditLoanResultReader())
			.processor(createFssCreditLoanResultProcessor())
			.writer(createFssCreditLoanResultWriter())
			.taskExecutor(App.taskExecutor())
			.build();
	}

	@Bean
	public ItemReader<FssCreditLoanResultVO> createFssCreditLoanResultReader() throws Exception {
		ItemReader<FssCreditLoanResultVO> itemReader = new ItemReader<FssCreditLoanResultVO>(){

			@Override
			public FssCreditLoanResultVO read() {

				FssCreditLoanResultVO fssCreditLoanResultVO = new FssCreditLoanResultVO();

				if (creditLoanResultCount < creditLoanResultList.size()) {
					fssCreditLoanResultVO = creditLoanResultList.get(creditLoanResultCount);
		        	creditLoanResultCount++;
		        	logger.info("creditLoanResultCount-----------"+creditLoanResultCount);
                    return fssCreditLoanResultVO;
                }

		        return null;
			}

		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<FssCreditLoanResultVO, FssCreditLoanResultVO> createFssCreditLoanResultProcessor(){
		return new ItemProcessor<FssCreditLoanResultVO, FssCreditLoanResultVO>() {
			@Override
			public FssCreditLoanResultVO process(FssCreditLoanResultVO item) throws Exception {
				logger.info("-------------------createFssCreditLoanResultProcessor------------------");
				return item;
			}
		};
	}

	@Bean
	public ItemWriter<FssCreditLoanResultVO> createFssCreditLoanResultWriter(){
		ItemWriter<FssCreditLoanResultVO> writer = new ItemWriter<FssCreditLoanResultVO>() {
			@Override
			public void write(List<? extends FssCreditLoanResultVO> items) throws Exception {
				for(FssCreditLoanResultVO fssCreditLoanResultVO: items) {
					fssMapper.createFssCreditLoanResultInfo(fssCreditLoanResultVO);
				}
			}
		};
		return writer;
	}

	@Bean
	public Step createCreditLoanProductStep() throws Exception {
		return stepBuilderFactory.get("[Step-"+BATCH_NAME+".fssCreditLoanProduct]")
			.<FssCreditLoanProductVO,FssCreditLoanProductVO>chunk(1)
			.reader(createFssCreditLoanProductReader())
			.processor(createFssCreditLoanProductProcessor())
			.writer(createFssCreditLoanProductWriter())
			.taskExecutor(App.taskExecutor())
			.build();
	}

	@Bean
	public ItemReader<FssCreditLoanProductVO> createFssCreditLoanProductReader() throws Exception {
		ItemReader<FssCreditLoanProductVO> itemReader = new ItemReader<FssCreditLoanProductVO>(){

			@Override
			public FssCreditLoanProductVO read() {

				FssCreditLoanProductVO fssCreditLoanProductVO = new FssCreditLoanProductVO();

				if (creditLoanProductCount < creditLoanProductList.size()) {
					fssCreditLoanProductVO = creditLoanProductList.get(creditLoanProductCount);
		        	creditLoanProductCount++;
		        	logger.info("creditLoanProductCount-----------"+creditLoanProductCount);
                    return fssCreditLoanProductVO;
                }

		        return null;
			}

		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<FssCreditLoanProductVO, FssCreditLoanProductVO> createFssCreditLoanProductProcessor(){
		return new ItemProcessor<FssCreditLoanProductVO, FssCreditLoanProductVO>() {
			@Override
			public FssCreditLoanProductVO process(FssCreditLoanProductVO item) throws Exception {
				logger.info("-------------------createFssCreditLoanProductProcessor------------------");
				return item;
			}
		};
	}

	@Bean
	public ItemWriter<FssCreditLoanProductVO> createFssCreditLoanProductWriter(){
		ItemWriter<FssCreditLoanProductVO> writer = new ItemWriter<FssCreditLoanProductVO>() {
			@Override
			public void write(List<? extends FssCreditLoanProductVO> items) throws Exception {
				for(FssCreditLoanProductVO fssCreditLoanProductVO: items) {
					fssCreditLoanProductVO.setId_frt("BATCH");
					fssCreditLoanProductVO.setId_lst("BATCH");
					fssMapper.mergeFssCreditLoanProductInfo(fssCreditLoanProductVO);
				}
			}
		};
		return writer;
	}

	@Bean
	public Step createCreditLoanOptionStep() throws Exception {
		return stepBuilderFactory.get("[Step-"+BATCH_NAME+".fssCreditLoanOption]")
			.<FssCreditLoanOptionVO,FssCreditLoanOptionVO>chunk(1)
			.reader(createFssCreditLoanOptionReader())
			.processor(createFssCreditLoanOptionProcessor())
			.writer(createFssCreditLoanOptionWriter())
			.taskExecutor(App.taskExecutor())
			.build();
	}

	@Bean
	public ItemReader<FssCreditLoanOptionVO> createFssCreditLoanOptionReader() throws Exception {
		ItemReader<FssCreditLoanOptionVO> itemReader = new ItemReader<FssCreditLoanOptionVO>(){

			@Override
			public FssCreditLoanOptionVO read() {

				FssCreditLoanOptionVO fssCreditLoanOptionVO = new FssCreditLoanOptionVO();

				if (creditLoanOptionCount < creditLoanOptionList.size()) {
					fssCreditLoanOptionVO = creditLoanOptionList.get(creditLoanOptionCount);
		        	creditLoanOptionCount++;
		        	logger.info("creditLoanOptionCount-----------"+creditLoanOptionCount);
                    return fssCreditLoanOptionVO;
                }

		        return null;
			}

		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<FssCreditLoanOptionVO, FssCreditLoanOptionVO> createFssCreditLoanOptionProcessor(){
		return new ItemProcessor<FssCreditLoanOptionVO, FssCreditLoanOptionVO>() {
			@Override
			public FssCreditLoanOptionVO process(FssCreditLoanOptionVO item) throws Exception {
				logger.info("-------------------createFssCreditLoanOptionProcessor------------------");
				return item;
			}
		};
	}

	@Bean
	public ItemWriter<FssCreditLoanOptionVO> createFssCreditLoanOptionWriter(){
		ItemWriter<FssCreditLoanOptionVO> writer = new ItemWriter<FssCreditLoanOptionVO>() {
			@Override
			public void write(List<? extends FssCreditLoanOptionVO> items) throws Exception {
				for(FssCreditLoanOptionVO fssCreditLoanOptionVO: items) {
					fssCreditLoanOptionVO.setId_frt("BATCH");
					fssCreditLoanOptionVO.setId_lst("BATCH");
					fssMapper.mergeFssCreditLoanOptionInfo(fssCreditLoanOptionVO);
				}
			}
		};
		return writer;
	}

}
