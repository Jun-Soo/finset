package com.koscom.batch.kcb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

import com.koscom.batch.kcb.domain.KcbInfoVO;
import com.koscom.batch.kcb.domain.KcbVO;
import com.koscom.batch.kcb.mapper.KcbMapper;
import com.koscom.util.StringUtil;


@Configuration
@EnableBatchProcessing
@EnableScheduling
public class KcbSendJob {

	private static final Logger logger = LoggerFactory.getLogger(KcbSendJob.class);
	private static final String BATCH_NAME = "KcbSendJob";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private SimpleJobLauncher jobLauncher;
	
	@Autowired
	private KcbMapper kcbMapper;

	@Value("${spring.kcb.filepath}")
	private String filePath;
	
	int count = 0;
	private List<KcbVO> kcbSendPersonList = new ArrayList<KcbVO>();
	private List<KcbVO> kcbSendInfoList = new ArrayList<KcbVO>();

	//File 
	private BufferedWriter writerCY00 = null;
	private BufferedWriter writerCY01 = null;
	private BufferedWriter writerCY02 = null;
	private BufferedWriter writerCY03 = null;
//	private BufferedWriter writerCY04 = null;
	
	//회원사코드
	static final String compNo = "S0047000";
	
	private int cntCY01 = 0, totCY01 = 0;
	private int cntCY02 = 0, totCY02 = 0;
	private int cntCY03 = 0, totCY03 = 0;
	
	private String yesterday;
	
	/**
	 * 스케쥴러
	 *
	 * 주기적으로 Job 실행 cron 설정에 따라 실행 jobLauncher 1개당 job 1+ 와 step 1+ 를 사용가능
	 *
	 * @throws Exception
	 */
	@Scheduled(cron = "${spring.scheduler.cron_7}")
	public void scheduler() throws Exception {

		String jobId = String.valueOf(System.currentTimeMillis());
		System.out.println("Started jobId : " + jobId);

		JobParameters param = new JobParametersBuilder().addString("JobID", jobId).toJobParameters();
		JobExecution execution = jobLauncher.run(kcbSendSftpJob(), param);

		System.out.println("end : " + param.getString("JobID") + ":::" + execution.getStatus());
	}

	/**
	 * 배치 Job
	 *
	 * kcbRecvStep 호출한다
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Job kcbSendSftpJob() throws Exception {
		return jobBuilderFactory.get("[Job - " + BATCH_NAME + "]").listener(new JobExecutionListener() {

			@Override
			public void beforeJob(JobExecution jobExecution) {
				
				logger.debug("--------------------start of beforeJob---------------------:::");
				try {
					
					GregorianCalendar today = new GregorianCalendar();
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
					today.add(Calendar.DAY_OF_MONTH, -1);

					yesterday = format.format(today.getTime());
					
					writerCY00 = new BufferedWriter(new FileWriter(filePath + getFileName("CY00")));
					writerCY01 = new BufferedWriter(new FileWriter(filePath + getFileName("CY01")));
					writerCY02 = new BufferedWriter(new FileWriter(filePath + getFileName("CY02")));
					writerCY03 = new BufferedWriter(new FileWriter(filePath + getFileName("CY03")));
//					writerCY04 = new BufferedWriter(new FileWriter(filePath + getFileName("CY04")));
					
					
					//KCB 비금융정보 전송 대기목록 조회
					kcbSendPersonList = kcbMapper.listKcbSendPerson();
					kcbSendInfoList = kcbMapper.listKcbSendInfo();
					
					logger.debug("kcbSendPersonList ---------- :::" + kcbSendPersonList.toString());
					
					//CY00 HEADER 
					setFileHeaderkcb(writerCY00, "CY00", String.valueOf(kcbSendPersonList.size()), 941);
					//CY00 BODY HEADER
					int record = 0;
					for(KcbVO vo : kcbSendPersonList) {
						
						StringBuffer sb = new StringBuffer();
						sb.append( StringUtil.STR( "D", 					1) );	//Record구분		AN	1	1
						sb.append( StringUtil.NUM( String.valueOf(++record),10));	//일련번호			N	10	11
						sb.append( StringUtil.STR( "3",						1) );   //식별번호구분코드	AN	1	12
						sb.append( StringUtil.STR( vo.getKcb_id(),	 		80));   //식별번호			AN	80	92
						sb.append( StringUtil.STR( "1", 					1) );   //조회동의여부		AN	1	93
						sb.append( StringUtil.STR( vo.getNm_person(),		50));   //성명				AN	50	143
						sb.append( StringUtil.NUM( "", 						12));   //휴대폰번호		AN	12	155
						sb.append( StringUtil.STR( "", 						50));	//이메일주소		AN	50	205
						sb.append( StringUtil.STR( "", 						795));	//FILLER			AN	795	1000
						writerCY00.write(sb.toString());
						writerCY00.newLine();
					}
					//CY00 TRAILER
					setFileTrailerkcb(writerCY00, "CY00", String.valueOf(kcbSendPersonList.size()),  943);
					
					//CY01, CY02, CY03 HEADER 
					for(KcbVO vo : kcbSendInfoList) {
						if("01".equals(vo.getCd_req())) {	// 01 소득금액증명정보
							totCY01++;
						} else if("02".equals(vo.getCd_req())) {	// 02 건강보험납부정보
							totCY02++;
						} else if("03".equals(vo.getCd_req())) {	// 03 국민연금납부정보
							totCY03++;
						}
					}
					setFileHeaderkcb(writerCY01, "CY01", String.valueOf(totCY01), 2441);
					setFileHeaderkcb(writerCY02, "CY02", String.valueOf(totCY02), 4941);
					setFileHeaderkcb(writerCY03, "CY03", String.valueOf(totCY03), 4941);
					
					//CY04 
//					setFileHeaderkcb(writerCY04, "CY04", String.valueOf(0), 1941);
//					setFileTrailerkcb(writerCY04, "CY04", String.valueOf(0),  1943);
					
				} catch (Exception e) {
					e.printStackTrace();
					logger.debug(e.getMessage());
				}
			}
			@Override
			public void afterJob(JobExecution jobExecution) {
				
				count = 0;
				
				try {
					
					setFileTrailerkcb(writerCY01, "CY01", String.valueOf(totCY01), 2443);
					setFileTrailerkcb(writerCY02, "CY02", String.valueOf(totCY02), 4943);
					setFileTrailerkcb(writerCY03, "CY03", String.valueOf(totCY03), 4943);
					
					//파일 이동(임시폴더에서 SFTP전송 폴더로 이동)
					
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}).start(kcbRecvStep()).build();
	}


	/**
	 * 배치 Step
	 *
	 * <pre>
	 * reader() : kcb조회
	 * processor() : 데이터 가공
	 * writer() : kcb 결과 저장
	 * </pre>
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Step kcbRecvStep() throws Exception { // chunk 큰덩어리 프로세스단위
		return stepBuilderFactory.get("[Step - " + BATCH_NAME + "]")
				.<KcbVO, KcbVO>chunk(1)
				.reader(kcbRecvReader())
				.processor(kcbRecvProcessor())
				.writer(kcbRecvWriter())
//				.taskExecutor(App.taskExecutor())
				.build();
	}

	@Bean
	public ItemReader<KcbVO> kcbRecvReader() throws Exception {

		ItemReader<KcbVO> itemReader = new ItemReader<KcbVO>() {

			@Override
			public KcbVO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
				logger.debug("--------------------start of kcbRecvReader---------------------:::" + kcbSendInfoList.size());
				KcbVO kcbSend = new KcbVO();

				if (count < kcbSendInfoList.size()) {
					kcbSend = kcbSendInfoList.get(count);
					count++;
					return kcbSend;
				}

				return null;
			}
		};

		return itemReader;

	}

	@Bean
	public ItemProcessor<KcbVO, KcbVO> kcbRecvProcessor() {
		return new ItemProcessor<KcbVO, KcbVO>() {

			@Override
			public KcbVO process(KcbVO kcbVO) throws Exception { 
				return kcbDetailList(kcbVO);
			}
		};
	}

	public KcbVO kcbDetailList(KcbVO kcbVO) throws Exception {

		try {
			
			if("01".equals(kcbVO.getCd_req())) {	// 01 소득금액증명정보
				
				kcbVO.setKcbInfoList(kcbMapper.listScrIncome(kcbVO));
				
			} else if("02".equals(kcbVO.getCd_req())) {	// 02 건강보험납부정보
				
				kcbVO.setKcbInfoList(kcbMapper.listScrHealthPayment(kcbVO));
				
			} else if("03".equals(kcbVO.getCd_req())) {	// 03 국민연금납부정보
				
				kcbVO.setKcbInfoList(kcbMapper.listScrPensionPayment(kcbVO));
			}
			
			
        } catch (Exception e) {
            System.out.println(e);
        }

		return kcbVO;
	}

	@Bean
	public ItemWriter<KcbVO> kcbRecvWriter() throws Exception {
		
		ItemWriter<KcbVO> writer = new ItemWriter<KcbVO>() {

			public void write(List<? extends KcbVO> items) throws Exception {

				for (KcbVO vo : items) {
					logger.info("KcbVOToString-----"+vo.toString());
					setFileBodykcb(vo);
				}

			}
		};

		return writer;
	}

	public void setFileHeaderkcb(BufferedWriter writer, String type, String totCnt, int fillerSize) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append( StringUtil.STR( "H", 				1) );	//Record구분	AN	1		1
		sb.append( StringUtil.NUM( "0000000000",		10));	//일련번호		N	10		11
		sb.append( StringUtil.STR( compNo, 				8) );   //회원사코드	AN	8		19
		sb.append( StringUtil.STR( getFileName(type),	20));   //FILE이름		AN	20		39
		sb.append( StringUtil.NUM( yesterday, 			8) );   //기준일자		N	8		47
		sb.append( StringUtil.STR( "11", 				2) );   //포맷버전정보	AN	2		49
		sb.append( StringUtil.NUM( totCnt, 				10) );  //총건수		N	10		59
		sb.append( StringUtil.STR( "", 			fillerSize));	//FILLER		AN	
		
		writer.write(sb.toString());
		writer.newLine();
	}
	
	public void setFileTrailerkcb(BufferedWriter writer, String type, String totCnt, int fillerSize) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append( StringUtil.STR( "T", 				1) );	//Record구분	AN	1		1
		sb.append( StringUtil.NUM( "0000000000",		10));	//일련번호		N	10		11
		sb.append( StringUtil.STR( compNo, 				8) );   //회원사코드	AN	8		19
		sb.append( StringUtil.STR( getFileName(type), 	20));   //FILE이름		AN	20		39
		sb.append( StringUtil.NUM( yesterday, 			8) );   //기준일자		N	8		47
		sb.append( StringUtil.NUM( totCnt, 				10) );  //총건수		N	10		59
		sb.append( StringUtil.STR( "", 			fillerSize));	//FILLER		AN	
		
		writer.write(sb.toString());
		writer.close();
	}
	
	public void setFileBodykcb(KcbVO kcbVO) throws Exception {

		StringBuffer sb = new StringBuffer();
		sb.append( StringUtil.STR( "D", 					1 ));	//Record구분				AN	1	1
		sb.append( StringUtil.NUM(String.valueOf(("01".equals(kcbVO.getCd_req()) ? ++cntCY01 : ("02".equals(kcbVO.getCd_req()) ? ++cntCY02 : ++cntCY03))),10));	//일련번호					N	10	11
		sb.append( StringUtil.STR( "3",						1 ));   //식별번호구분코드			AN	1	12
		sb.append( StringUtil.STR( kcbVO.getKcb_id(),		80));   //식별번호					AN	80	92
		sb.append( StringUtil.STR( kcbVO.getDt_pbls(),		8 ));   //발급일자					AN	8	100
		sb.append( StringUtil.STR( kcbVO.getNo_pbls(),		50));   //발급번호					AN	50	150
		
		if("01".equals(kcbVO.getCd_req())) {	// 01 소득금액증명정보

			sb.append( StringUtil.STR( kcbVO.getCert_division(),2 ));   //소득금액증명서구분코드	AN	2	152
			sb.append( StringUtil.STR( "", 						150));	//주소						AN	150	302
			sb.append( StringUtil.STR( "", 						198));	//FILLER					AN	198	500
			
			for(KcbInfoVO vo : kcbVO.getKcbInfoList()) {
				
				sb.append( StringUtil.STR( vo.getIncome_division(),			2 ));	//소득구분코드	AN	2	2402
				sb.append( StringUtil.STR( vo.getReversion_year(),			4 ));	//귀속년도		AN	4	2406
				sb.append( StringUtil.NUM( vo.getAmt_income(),				12));  	//기준소득금액	N	12	2418
				sb.append( StringUtil.NUM( vo.getAmt_total_decision_tax(),	12));   //총결정세액	N	12	2430
				sb.append( StringUtil.STR( vo.getCorp_nm(), 				50));   //법인명		AN	50	2480
				sb.append( StringUtil.STR( vo.getBiz_licence(),				10));   //사업자등록번호AN	10	2490
				sb.append( StringUtil.STR( "", 								10));	//FILLER		AN	10  2500
			}
			for(int i=0; i<20-kcbVO.getKcbInfoList().size(); i++) {
				
				sb.append( StringUtil.STR( "",	2 ));	//소득구분코드	AN	2	2402
				sb.append( StringUtil.STR( "",	4 ));	//귀속년도		AN	4	2406
				sb.append( StringUtil.STR( "",	12));   //기준소득금액	N	12	2418
				sb.append( StringUtil.STR( "", 	12));   //총결정세액	N	12	2430
				sb.append( StringUtil.STR( "", 	50));   //법인명		AN	50	2480
				sb.append( StringUtil.STR( "",	10));   //사업자등록번호AN	10	2490
				sb.append( StringUtil.STR( "", 	10));	//FILLER		AN	10  2500
			}
			
			writerCY01.write(sb.toString());
			writerCY01.newLine();
			
		} else if("02".equals(kcbVO.getCd_req())) {	// 02 건강보험납부정보
			
			sb.append( StringUtil.STR( kcbVO.getNo_payer(),			50));   //납부자번호	AN	50	200
			sb.append( StringUtil.NUM( kcbVO.getMember_division(), 	1 ));	//가입자구분	N	1	201
			sb.append( StringUtil.STR( kcbVO.getNm_comp(),			50));	//상호명		AN	50	251
			sb.append( StringUtil.STR( "", 							69));	//FILLER		AN	69	320
			
			for(KcbInfoVO vo : kcbVO.getKcbInfoList()) {
				
				sb.append( StringUtil.STR( vo.getPay_yyyy(),					4 ));	//납부년도				AN	4	4874
				sb.append( StringUtil.STR( vo.getPay_yyyymm(),					6 ));	//납부년월				AN	6	4880
				sb.append( StringUtil.NUM( vo.getAmt_nt_health_insu(),			12));   //건강보험고지보험료	N	12	4892
				sb.append( StringUtil.NUM( vo.getAmt_pay_health_insu(), 		12));   //건강보험납부보험료	N	12	4904
				sb.append( StringUtil.NUM( vo.getAmt_nt_lngtrm_cr_ins(),		12));   //장기요양고지보험료	N	12	4916
				sb.append( StringUtil.NUM( vo.getAmt_pay_longterm_care_insu(),	12));   //장기요양납부보험료	N	12	4928
				sb.append( StringUtil.NUM( vo.getAmt_icnt_health_insu(),		12));   //소득건강보험고지보험료N	12	4940
				sb.append( StringUtil.NUM( vo.getAmt_icpay_health_insu(),		12));   //소득건강보험납부보험료N	12	4952
				sb.append( StringUtil.NUM( vo.getAmt_icnt_lngtrm_cr_ins(),		12));   //소득장기요양고지보험료N	12	4964
				sb.append( StringUtil.NUM( vo.getAmt_icpay_longterm_care_insu(),12));   //소득장기요양납부보험료N	12	4976
				sb.append( StringUtil.STR( "",									24));   //FILLER				AN	24	5000
			}
			for(int i=0; i<36-kcbVO.getKcbInfoList().size(); i++) {
				
				sb.append( StringUtil.STR( "",	4 ));	//납부년도				AN	4	4874
				sb.append( StringUtil.STR( "",	6 ));	//납부년월				AN	6	4880
				sb.append( StringUtil.STR( "",	12));   //건강보험고지보험료	N	12	4892
				sb.append( StringUtil.STR( "", 	12));   //건강보험납부보험료	N	12	4904
				sb.append( StringUtil.STR( "",	12));   //장기요양고지보험료	N	12	4916
				sb.append( StringUtil.STR( "",	12));   //장기요양납부보험료	N	12	4928
				sb.append( StringUtil.STR( "",	12));   //소득건강보험고지보험료N	12	4940
				sb.append( StringUtil.STR( "",	12));   //소득건강보험납부보험료N	12	4952
				sb.append( StringUtil.STR( "",	12));   //소득장기요양고지보험료N	12	4964
				sb.append( StringUtil.STR( "",	12));   //소득장기요양납부보험료N	12	4976
				sb.append( StringUtil.STR( "",	24));   //FILLER				AN	24	5000
			}

			writerCY02.write(sb.toString());
			writerCY02.newLine();
			
		} else if("03".equals(kcbVO.getCd_req())) {	// 03 국민연금납부정보
			
			sb.append( StringUtil.STR( kcbVO.getNo_verify(),			4 ));   //검증번호			AN	4	154
			sb.append( StringUtil.NUM( kcbVO.getCnt_month_pay(), 		3 ));	//총보험료납부월수	N	3	157
			sb.append( StringUtil.NUM( kcbVO.getAmt_pay(),				12));	//총보험료납부금액	N	12	169
			sb.append( StringUtil.NUM( kcbVO.getCnt_month_not_pay(),	3 ));	//총보험료미납월수	N	3	172
			sb.append( StringUtil.NUM( kcbVO.getAmt_not_pay(),			12));	//총보험료미납금액	N	12	184
			sb.append( StringUtil.NUM( kcbVO.getCnt_month_cannot_pay(),	3 ));	//총납부불가월수	N	3	187
			sb.append( StringUtil.NUM( kcbVO.getAmt_cannot_pay(),		12));	//총납부불가금액	N	12	199
			sb.append( StringUtil.NUM( kcbVO.getAmt_pay_return(),		12));	//반납금액			N	12	211
			sb.append( StringUtil.NUM( kcbVO.getAmt_pay_primium(),		12));	//추가납부금액		N	12	223
			sb.append( StringUtil.STR( "", 								97));	//FILLER			AN	97	320
			
			for(KcbInfoVO vo : kcbVO.getKcbInfoList()) {
				
				sb.append( StringUtil.STR( vo.getStart_yyyymm(),				6 ));	//납부기간시작년월	AN	6	4876
				sb.append( StringUtil.STR( vo.getEnd_yyyymm(),					6 ));	//납부기간종료년월	AN	6	4882
				sb.append( StringUtil.NUM( vo.getAmt_base_income_month(),		12));   //기준소득금액		N	12	4894
				sb.append( StringUtil.NUM( vo.getCnt_month_pay(),		 		3 ));   //보험료납부월수	N	3	4897
				sb.append( StringUtil.NUM( vo.getAmt_pay(),						12));   //보험료납부금액	N	12	4909
				sb.append( StringUtil.NUM( vo.getCnt_month_not_pay(),			3 ));   //보험료미납부월수	N	3	4912
				sb.append( StringUtil.NUM( vo.getAmt_not_pay(),					12));   //보험료미납부금액	N	12	4924
				sb.append( StringUtil.STR( vo.getAmt_icpay_health_insu(),		1 ));   //종별구분코드		AN	1	4925
				sb.append( StringUtil.STR( vo.getAmt_icnt_lngtrm_cr_ins(),		50));   //비고				AN	50	4975
				sb.append( StringUtil.STR( "",									25));   //FILLER			AN	25	5000
			}
			for(int i=0; i<36-kcbVO.getKcbInfoList().size(); i++) {
				
				sb.append( StringUtil.STR( "",	6 ));	//납부기간시작년월	AN	6	4876
				sb.append( StringUtil.STR( "",	6 ));	//납부기간종료년월	AN	6	4882
				sb.append( StringUtil.STR( "",	12));   //기준소득금액		N	12	4894
				sb.append( StringUtil.STR( "",	3 ));   //보험료납부월수	N	3	4897
				sb.append( StringUtil.STR( "",	12));   //보험료납부금액	N	12	4909
				sb.append( StringUtil.STR( "",	3 ));   //보험료미납부월수	N	3	4912
				sb.append( StringUtil.STR( "",	12));   //보험료미납부금액	N	12	4924
				sb.append( StringUtil.STR( "",	1 ));   //종별구분코드		AN	1	4925
				sb.append( StringUtil.STR( "",	50));   //비고				AN	50	4975
				sb.append( StringUtil.STR( "",	25));   //FILLER			AN	25	5000
			}
			
			writerCY03.write(sb.toString());
			writerCY03.newLine();
		}
	}

	
	public String getFileName(String type) {
		return type + compNo + yesterday; 
	}
}
