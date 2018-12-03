package com.koscom.batch.kcb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.koscom.batch.kcb.domain.KcbVO;
import com.koscom.batch.kcb.mapper.KcbMapper;
import com.koscom.util.StringUtil;


@Configuration
@EnableBatchProcessing
@EnableScheduling
public class KcbRecvJob {

	private static final Logger logger = LoggerFactory.getLogger(KcbRecvJob.class);
	private static final String BATCH_NAME = "KcbRecvJob";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private SimpleJobLauncher jobLauncher;
	
	@Autowired
	private KcbMapper kcbMapper;

	@Value("${spring.push.filepath}")
	private String filePath;

	//회원사코드
	static final String compNo = "S0047000";
	
	private String yesterday;
	int count = 0, fileCnt = 3;
	List<KcbVO> kcbRecvList = new ArrayList<KcbVO>();
	
	/**
	 * 스케쥴러
	 *
	 * 주기적으로 Job 실행 cron 설정에 따라 실행 jobLauncher 1개당 job 1+ 와 step 1+ 를 사용가능
	 *
	 * @throws Exception
	 */
	@Scheduled(cron = "${spring.scheduler.cron_8}")
	public void scheduler() throws Exception {

		String jobId = String.valueOf(System.currentTimeMillis());
		System.out.println("Started jobId : " + jobId);

		JobParameters param = new JobParametersBuilder().addString("JobID", jobId).toJobParameters();
		JobExecution execution = jobLauncher.run(kcbRecvSftpJob(), param);

		System.out.println("end : " + param.getString("JobID") + ":::" + execution.getStatus());
	}

	/**
	 * 배치 Job
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Job kcbRecvSftpJob() throws Exception {
		return jobBuilderFactory.get("[Job - " + BATCH_NAME + "]").listener(new JobExecutionListener() {

			@Override
			public void beforeJob(JobExecution jobExecution) {
				
				try {
					
					GregorianCalendar today = new GregorianCalendar();
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
					today.add(Calendar.DAY_OF_MONTH, -1);

					yesterday = format.format(today.getTime());
					
					//파일에서 응답 전문 확인
					fileReadList();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			@Override
			public void afterJob(JobExecution jobExecution) {
				
				count = 0;
			}
		})
		.start(kcbRecvStep())
		.build();
	}

	public void fileReadList() throws Exception {
		
		logger.info("fileReadList---------- IN ");

		/**
         * 파일 데이터 파싱
         */
        for(int i=1; i<fileCnt; i++) {
        	
        	File file = new File(filePath + getFileName("CY0"+i)); 
        	boolean isExists = file.exists(); 
        	if(isExists) {
        		readFileList(file, "0"+i);
        	}
        }
	}
        
    public void readFileList(File file, String type) throws IOException {
        
    	String 			lang = "euc-kr";
        int 			bufferSize = 1024;
        byte[] 			bt = null;
        int len=0, pos=0, cnt=0;
        
        FileInputStream fileInputStream = null;
        StringBuilder 	sb 				= new StringBuilder();
        byte[] data = new byte[bufferSize];

        /**
         * body
         */
        int body = 228, bodyDetail = 4680; // 02 건강보험납부정보, 03 국민연금납부정보
        if("01".equals(type)) {	// 01 소득금액증명정보
        	body = 408;
        	bodyDetail = 2000;
		}
        
        int[] arLenSegment = {
        		 1  		//1   		Record 구분 AN  1   1
                ,11 		//body1
                ,80 		//80 		식별번호	AN	80	92
                ,body		//body2
                ,bodyDetail	//2000		BodyDetail
                ,5	 		//5			오류응답코드
                ,495 		//495		오류체크비트
        };
        String[] arrDataSegment = new String[arLenSegment.length];
        
        try {
        	
            fileInputStream = new FileInputStream(file);
            while(true) {
                if((cnt = fileInputStream.read(data)) == -1){
                    break;
                }
                sb.append(new String(data, 0, cnt));
                bt = sb.toString().getBytes(lang);
                
                String recordType = StringUtil.nullToString(StringUtil.getByte2String(bt,pos,1).trim());
                
                if("D".equals(recordType)) {
                	
                	for(int i=0;i<arLenSegment.length;i++) {
                        len = arLenSegment[i];
                        arrDataSegment[i] = StringUtil.nullToString(StringUtil.getByte2String(bt,pos,len).trim());
                        pos += len;
                    }
                	
                	KcbVO vo = new KcbVO();
                	vo.setCd_req(type);
                	vo.setNo_person(arrDataSegment[2]);
                	vo.setCd_err(arrDataSegment[5]);
                	vo.setBit_err(arrDataSegment[6]);
                	
                	if(StringUtil.isEmpty(vo.getCd_err())) {
                		vo.setStatus("03");
                	} else {
                		vo.setStatus("04");
                	}
                	kcbRecvList.add(vo);
                }
            }

            fileInputStream.close();
            fileInputStream=null;
            
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if(fileInputStream != null) { 
                fileInputStream.close();
                fileInputStream = null;
        	}
		}
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
	public Step kcbRecvStep() throws Exception { // chunk 큰덩어리 프로세스단위
		return stepBuilderFactory.get("[Step - " + BATCH_NAME + "]")
		  .<KcbVO, KcbVO>chunk(1)
		  .reader(kcbRecvReader())
		  .processor(kcbRecvProcessor())
		  .writer(kcbRecvWriter())
//		  .taskExecutor(App.taskExecutor())
		  .build();
	}

	@Bean
	public ItemReader<KcbVO> kcbRecvReader() throws Exception {

		ItemReader<KcbVO> itemReader = new ItemReader<KcbVO>(){

			@Override
			public KcbVO read() {

				KcbVO KcbVO = new KcbVO();

				if (count < kcbRecvList.size()) {
		        	KcbVO = kcbRecvList.get(count);
		        	count++;
		        	logger.info("count-----------"+count);
                    return KcbVO;
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
			public KcbVO process(KcbVO data) throws Exception {
				return data;
			}
		};
	}

	@Bean
	public ItemWriter<KcbVO> kcbRecvWriter() {
		ItemWriter<KcbVO> writer = new ItemWriter<KcbVO>() {

			public void write(List<? extends KcbVO> items) throws Exception {

				for (KcbVO vo : items) {
					logger.info("wrtier-----------");
					
					//요청내역 UPDATE
					kcbMapper.updateReqKcbNonFi(vo);
				}
			}
		};
		return writer;
	}
	
	public String getFileName(String type) {
		return type + compNo + yesterday; 
	}
}
