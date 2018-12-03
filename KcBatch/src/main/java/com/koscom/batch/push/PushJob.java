package com.koscom.batch.push;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
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
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.koscom.batch.person.domain.PersonInfo;
import com.koscom.batch.person.mapper.PersonMapper;
import com.koscom.batch.push.domain.Ad01SegmentInfo;
import com.koscom.batch.push.domain.PushEachForm;
import com.koscom.batch.push.domain.PushEachVO;
import com.koscom.batch.push.mapper.PushMapper;
import com.koscom.util.DateUtil;
import com.koscom.util.FcmUtil;
import com.koscom.util.StringUtil;


@Configuration
@EnableBatchProcessing
@EnableScheduling
public class PushJob {

	private static final Logger logger = LoggerFactory.getLogger(PushJob.class);
	private static final String BATCH_NAME = "PushJobStep";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private SimpleJobLauncher jobLauncher;
	@Autowired
	private PushMapper pushMapper;
	@Autowired
	private PersonMapper personMapper;
	@Resource
    private Environment environment;

	/**
     * 파일경로
     */
    @Value("${spring.push.filepath}")
	private String filePath;

	//step1 전역변수
	List<Ad01SegmentInfo> ad01SegmentList = new ArrayList<Ad01SegmentInfo>();
	int count1 = 0;
	String type = "1";
	public static String[] title={"신용거래정보","채무 불이행 정보","채무 불이행 정보","신용 조회 정보","대출 정보","기타 신용정보","기타 신용정보","기타 신용정보","기타 신용정보"};
	public static String[] url  ={
            "/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab1" /*신용관리->세부정보->신용조회 정보   */
           ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab2" /*신용관리->세부정보->대출/카드 정보	  */
           ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab2" /*신용관리->세부정보->대출/카드 정보	  */
           ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab2" /*신용관리->세부정보->대출/카드 정보	  */
           ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab3" /*신용관리->세부정보->연체정보		  */
           ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab3" /*신용관리->세부정보->연체정보		  */
           ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab3" /*신용관리->세부정보->연체정보		  */
           ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab3" /*신용관리->세부정보->연체정보		  */
           ,"/m/credit/frameCreditInfoDetail.crz?detailTabNm=tab3" /*신용관리->세부정보->연체정보		  */
	};

    //step2 전역변수
	List<PushEachVO> pushList = new ArrayList<PushEachVO>();
	int count2 = 0;

	/**
	 * 스케쥴러
	 *
	 * 주기적으로 Job 실행 cron 설정에 따라 실행 jobLauncher 1개당 job 1+ 와 step 1+ 를 사용가능
	 *
	 * @throws Exception
	 */
	@Scheduled(cron = "${spring.scheduler.cron_3}")
	public void scheduler() throws Exception {

		String jobId = String.valueOf(System.currentTimeMillis());
		System.out.println("Started jobId : " + jobId);

		JobParameters param = new JobParametersBuilder().addString("JobID", jobId).toJobParameters();
		JobExecution execution = jobLauncher.run(pushApiJob(), param);

		System.out.println("end : " + param.getString("JobID") + ":::" + execution.getStatus());
	}

	/**
	 * 배치 Job
	 *
	 * step1 pushApiStep1 호출한다
	 * step2 pushApiStep2 호출한다
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Job pushApiJob() throws Exception {
		return jobBuilderFactory.get("[Job - " + BATCH_NAME + "]")
				.start(pushApiStep1())
				.next(pushApiStep2())
				.build();
	}

	/**
	 * 배치 Step1
	 *
	 * <pre>
	 * reader() : file list조회
	 * processor() : 데이터 가공
	 * writer() : 메세지 등록
	 * </pre>
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Step pushApiStep1() throws Exception { // chunk 큰덩어리 프로세스단위
		return stepBuilderFactory.get("[Step1 - " + BATCH_NAME + "]")
		  .<Ad01SegmentInfo, Ad01SegmentInfo>chunk(1)
		  .reader(pushApiReaderStep1())
		  .processor(pushApiProcessorStep1())
		  .writer(pushApiWriterStep1())
//		  .taskExecutor(App.taskExecutor())
		  .build();
	}

	public List<Ad01SegmentInfo> pushApiCallStep1() throws Exception {
		logger.info("pushApiCallStep1----------in");
		List<Ad01SegmentInfo> ad01SegmentList = new ArrayList<Ad01SegmentInfo>();

//		String type = (pType == null)?"1":pType;
        type = ("1".equals(type) || "2".equals(type))? type:"1";

        /**
         * 파일 목록
         */
        String[] fileList = null;
        /**
         * 파싱할 데이터 파일명
         */
        String todayfileNm = null;
        boolean ynTodayFile = false;
        String strFile = null;
        String lang = "euc-kr";
        int bufferSize = 1024;
        byte[] bt = null;
        StringBuffer str = null;
        int len = 0;
        int pos = 0;
        int inx=0;
        int intTotalCnt = 0;

        /**
         * header
         */
        int[] arLenHeader = {1    //Record 구분
                ,10   //일련번호
                ,8    //회원사코드
                ,20   //FILE 이름
                ,8    //기준일자
                ,2    //포맷버전정보
                ,10   //총건수
                ,271  //FILLER
                ,1
        };
        String[] arrDataHeader  = new String[arLenHeader .length];
        String recordType=null; //Record 구분
        String seqNo     =null; //일련번호
        String cpCode    =null; //회원사코드
        String fileName  =null; //FILE 이름
        String stdDate   =null; //기준일자
        String version   =null; //포맷버전정보
        String totalCnt  =null; //총건수
        String filler    =null; //FILLER

        /**
         * body
         */
        int[] arLenSegment = {1   //1   Record 구분 recordType  AN  1   1
                ,10  //2   일련번호    seqNo   N   10  11
                ,8   //3   회원사코드  cpCode  AN  8   19
                ,13  //4   주민등록번호    ssn N   13  32
                ,50  //5   성명    nm_person   AN  50  82
                ,20  //6   관리번호    no_person   AN  20  102
                ,28  //7   FILLER  FILLER  AN  28  130
                ,3   //1   Segment Identification      AN  3   133
                ,3   //2   서비스코드      N   3   136
                ,8   //3   정보변동일자        AN  8   144
                ,12  //4   휴대폰      AN  12  156
                ,50  //5   이메일      AN  50  206
                ,1   //6   신용거래정보 변동여부       AN  1   207
                ,1   //7   은행연합회 채무불이행 변동여부      AN  1   208
                ,1   //8   신용정보사 채무불이행 변동여부      AN  1   209
                ,1   //9   조회처정보 변동여부     AN  1   210
                ,1   //10  대출정보 변동여부       AN  1   211
                ,1   //11  보증정보 변동여부       AN  1   212
                ,1   //12  금융질서문란정보 변동여부       AN  1   213
                ,1   //13  공공정보 변동여부       AN  1   214
                ,64  //14  DI      AN  64  278
                ,52  //15  fillerSeg fillerSeg      AN  52  330
                ,1
        };
        String[] arrDataSegment = new String[arLenSegment.length];
//      String recordType           = null;
    //  String seqNo                = null;
    //  String cpCode               = null;
        String ssn                  = null;
        String nm_person            = null;
        String no_person            = null;
//        String filler             = null;
        String segmentId            = null;
        String svcCode              = null;
        String dt_info_change       = null;
        String hp                   = null;
        String email                = null;
        String yn_credit_info       = null;
        String yn_kfb_default       = null;
        String yn_kci_default       = null;
        String yn_ref_info          = null;
        String yn_loan_info         = null;
        String yn_guarantee_info    = null;
        String yn_fnco_disorder_info= null;
        String yn_public_info       = null;
        String kcbdi                = null;
        String fillerSeg            = null;
        PersonInfo personInfo = null;
        String fcm_token            = null;
        Ad01SegmentInfo ad01SegmentInfo = null;

        if (filePath != null) {
            fileList = getFileList(filePath);
        }

        logger.info("fileList----------"+fileList.length);

        if(fileList != null && fileList.length > 0) {
//            todayfileNm = "AD01S004700020180301";
        	todayfileNm = "AD01S0047000" + DateUtil.getCurrentDate("yyyyMMdd");
//            LogUtil.infoLn(logger,"파일명 : "+todayfileNm);
        	logger.info("todayfileNm----------"+todayfileNm);
            for (String file : fileList) {
            	logger.info("file="+file);
                if(todayfileNm.equals(file)){
                	ynTodayFile = true;
                    break;
                }
           }

           if(ynTodayFile) {
        	   strFile = readFile(filePath + todayfileNm, lang, bufferSize);
        	   logger.info("strFile---"+strFile);

        	   logger.info("파일의 데이터 :"+strFile);
               bt = strFile.getBytes();
               logger.info("파일의 데이터 길이:"+bt.length);
               str = new StringBuffer();

               /**
                * header
                */
               for(int i=0;i<arLenHeader.length;i++) {
                   len = arLenHeader[i];
                   arrDataHeader[i]= StringUtil.nullToString(StringUtil.getByte2String(bt,pos,len).trim());
                   pos += len;
               }
               recordType = arrDataHeader[inx++];
               seqNo      = arrDataHeader[inx++];
               cpCode     = arrDataHeader[inx++];
               fileName   = arrDataHeader[inx++];
               stdDate    = arrDataHeader[inx++];
               version    = arrDataHeader[inx++];
               totalCnt   = arrDataHeader[inx++];
               filler     = arrDataHeader[inx++];
               intTotalCnt = Integer.parseInt(totalCnt);
               logger.info("intTotalCnt---"+intTotalCnt);

               for (int i = 0; i < intTotalCnt; i++) {
                   for(int j=0;j<arLenSegment.length;j++) {
                       len = arLenSegment[j];
                       arrDataSegment[j]= StringUtil.nullToString(StringUtil.getByte2String(bt,pos,len).trim());
                       pos += len;
                   }
                   inx=0;
                   recordType            = arrDataSegment[inx++];
                   seqNo                 = arrDataSegment[inx++];
                   cpCode                = arrDataSegment[inx++];
                   ssn                   = arrDataSegment[inx++];
                   nm_person             = arrDataSegment[inx++];
                   no_person             = arrDataSegment[inx++];
                   filler                = arrDataSegment[inx++];
                   segmentId             = arrDataSegment[inx++];
                   svcCode               = arrDataSegment[inx++];
                   dt_info_change        = arrDataSegment[inx++];
                   hp                    = arrDataSegment[inx++];
                   email                 = arrDataSegment[inx++];
                   yn_credit_info        = arrDataSegment[inx++];
                   yn_kfb_default        = arrDataSegment[inx++];
                   yn_kci_default        = arrDataSegment[inx++];
                   yn_ref_info           = arrDataSegment[inx++];
                   yn_loan_info          = arrDataSegment[inx++];
                   yn_guarantee_info     = arrDataSegment[inx++];
                   yn_fnco_disorder_info = arrDataSegment[inx++];
                   yn_public_info        = arrDataSegment[inx++];
                   kcbdi                 = arrDataSegment[inx++];
                   fillerSeg             = arrDataSegment[inx++];

                   if(no_person != null && !no_person.equals("")) {
                       personInfo = personMapper.getPersonInfo(no_person);
                       fcm_token = (personInfo != null)? personInfo.getFcm_token():null;
                       if(personInfo != null && fcm_token != null && !"".equals(fcm_token)) {
                           ad01SegmentInfo = new Ad01SegmentInfo();
                           ad01SegmentInfo.setRecordType           (recordType           );//1   Record 구분 recordType  AN  1   1
                           ad01SegmentInfo.setSeqNo                (seqNo                );//2   일련번호    seqNo   N   10  11
                           ad01SegmentInfo.setCpCode               (cpCode               );//3   회원사코드  cpCode  AN  8   19
                           ad01SegmentInfo.setSsn                  (ssn                  );//4   주민등록번호    ssn N   13  32
                           ad01SegmentInfo.setNm_person            (nm_person            );//5   성명    nm_person   AN  50  82
                           ad01SegmentInfo.setNo_person            (no_person            );//6   관리번호    no_person   AN  20  102
                           ad01SegmentInfo.setFiller               (filler               );//7   FILLER  filler  AN  28  130
                           ad01SegmentInfo.setSegmentId            (segmentId            );//1   Segment Identification      AN  3   133
                           ad01SegmentInfo.setSvcCode              (svcCode              );//2   서비스코드      N   3   136
                           ad01SegmentInfo.setDt_info_change       (dt_info_change       );//3   정보변동일자        AN  8   144
                           ad01SegmentInfo.setHp                   (hp                   );//4   휴대폰      AN  12  156
                           ad01SegmentInfo.setEmail                (email                );//5   이메일      AN  50  206
                           ad01SegmentInfo.setYn_credit_info       (yn_credit_info       );//6   신용거래정보 변동여부       AN  1   207
                           ad01SegmentInfo.setYn_kfb_default       (yn_kfb_default       );//7   은행연합회 채무불이행 변동여부      AN  1   208
                           ad01SegmentInfo.setYn_kci_default       (yn_kci_default       );//8   신용정보사 채무불이행 변동여부      AN  1   209
                           ad01SegmentInfo.setYn_ref_info          (yn_ref_info          );//9   조회처정보 변동여부     AN  1   210
                           ad01SegmentInfo.setYn_loan_info         (yn_loan_info         );//10  대출정보 변동여부       AN  1   211
                           ad01SegmentInfo.setYn_guarantee_info    (yn_guarantee_info    );//11  보증정보 변동여부       AN  1   212
                           ad01SegmentInfo.setYn_fnco_disorder_info(yn_fnco_disorder_info);//12  금융질서문란정보 변동여부       AN  1   213
                           ad01SegmentInfo.setYn_public_info       (yn_public_info       );//13  공공정보 변동여부       AN  1   214
                           ad01SegmentInfo.setKcbdi                (kcbdi                );//14  DI      AN  64  278
                           ad01SegmentInfo.setFillerSeg            (fillerSeg            );//15  FillerSeg      AN  52  330
                           ad01SegmentInfo.setId_frt               ("BATCH"                  );//등록자
                           ad01SegmentInfo.setFcm_token(fcm_token); //fcm_token
                           logger.info("fcm_token---"+fcm_token);

                           ad01SegmentList.add(ad01SegmentInfo);
                       } //fcm_token null체크
                   } //no_person null체크

               } //intTotalCnt만큼 for문
           } //ynTodayFile 여부 확인
        } //fileList null체크
        System.gc();

		return ad01SegmentList;
	}

	public static String[] getFileList(String path) {
        File dirFile = null;
        File []fileList = null;
        String[] result = null;
        if (path != null) {
            dirFile = new File(path);
            logger.info("dirFile-----"+dirFile.toString());
            logger.info("dirFileIsDir-----"+dirFile.isDirectory());
            fileList = dirFile.listFiles(); //이부분 에러..googleling
            logger.info("fileList-----"+fileList.length);
            int i = 0;
            if (fileList != null) {
                for(File tempFile : fileList) {
                    if(tempFile.isFile())
                        i++;
                }
                result = new String[i];
                int j = 0;
                for(File tempFile : fileList) {
                    if(tempFile.isFile()) {
                        String tempFileName = tempFile.getName();
                        logger.info("tempFileName-----"+tempFileName);
                        result[j] = tempFileName;
                        j++;
                    }
                }
            }
        }
        return result;
    }

	public static String readFile(String filename, String lang, int bufferSize){
        logger.info("PushJob.readFile="+filename);
        FileInputStream fileInputStream = null;
        StringBuilder sb = new StringBuilder();
        String result = null;
        byte[] data = new byte[bufferSize];
        File file = new File(filename);
        int cnt = 0;
        try {
            fileInputStream = new FileInputStream(file);
            while(true) {
//              System.out.println(cnt +":"+ new String(data, 0, cnt, lang));
//              System.out.println(cnt +":"+ new String(data, 0, cnt, lang));
                if((cnt = fileInputStream.read(data)) == -1){
                    break;
                }
                sb.append(new String(data, 0, cnt));
            }
//          System.out.println(stringBuffer.toString());
            fileInputStream.close();
            fileInputStream=null;
        } catch (IOException e) {
            if(fileInputStream != null) try{
                fileInputStream.close();
                fileInputStream = null;
            } catch(IOException ee){
                ee.printStackTrace();
            }
        }
        result = sb.toString();
        logger.info("PushJob.readFile : \n"+result);

        return result;
    }



	@Bean
	public ItemReader<Ad01SegmentInfo> pushApiReaderStep1() throws Exception {

		ItemReader<Ad01SegmentInfo> itemReader = new ItemReader<Ad01SegmentInfo>(){

			@BeforeStep
		    public void step1BeforeStep() throws Exception {
				//fileList 조회
				ad01SegmentList = pushApiCallStep1();
		    }

			@Override
			public Ad01SegmentInfo read() {
				Ad01SegmentInfo ad01SegmentInfo = new Ad01SegmentInfo();

				if (count1 < ad01SegmentList.size()) {
		        	ad01SegmentInfo = ad01SegmentList.get(count1);
		        	count1++;
		        	logger.info("count1-----------"+count1);
                    return ad01SegmentInfo;
                }

		        return null;
			}

			@AfterStep
		    public void step1AfterStep() throws Exception {
				count1 = 0;
		    }
		};



		return itemReader;
	}

	@Bean
	public ItemProcessor<Ad01SegmentInfo, Ad01SegmentInfo> pushApiProcessorStep1() {
		return new ItemProcessor<Ad01SegmentInfo, Ad01SegmentInfo>() {

			@Override
			public Ad01SegmentInfo process(Ad01SegmentInfo data) throws Exception {
				return data;
			}
		};

	}

	@Bean
	public ItemWriter<Ad01SegmentInfo> pushApiWriterStep1() {
		ItemWriter<Ad01SegmentInfo> writer = new ItemWriter<Ad01SegmentInfo>() {
			public void write(List<? extends Ad01SegmentInfo> items) throws Exception {

				for (Ad01SegmentInfo vo : items) {
					registMsg(vo);
					logger.info("wrtier1-----------");
				}
			}
		};

		return writer;
	}

	public void registMsg(Ad01SegmentInfo ad01SegmentInfo) throws Exception {
		PushEachVO pushEachVO = null;
		int ynInx = 0;
		String[] arrYn          = new String[8];
		int stYnInx = -1;
        int cntYn = 0;
        int cnt2Yn = 0;
        int cnt3Yn = 0;
        String conts = null;
        String yn_display = null;// 마이페이지에 보여지는 여부
        String push_divcd = null; //푸쉬구분
        String yn_send    = null;// 푸시를 보낼지 여부(사용자 알림셋팅이 Y가 아닌 경우와 'Y' 가 여러건인경우 한건을 제외한 나머지건은 N이 된다.)

		// Ad01SegmentInfo ad01SegmentInfo1 =
		// pushMapper.getPushAD01Info(ad01SegmentInfo);
		/**
		 * 기존에 등록된 내역이 있는지에 따른 처리
		 */
		// if(ad01SegmentInfo1 == null) {

        logger.info("insertPushHist---" + ad01SegmentInfo.getNo_person());
		pushMapper.insertPushHist(ad01SegmentInfo);
		// }
		// /**
		// * 전체다시
		// */
		// else if(type.equals("1")){
		// pushMapper.updatePushHist(ad01SegmentInfo);
		// logger.info("insertPushHist---");

        	pushEachVO = new PushEachVO();
        	pushEachVO.setNo_person(ad01SegmentInfo.getNo_person());
        	pushEachVO.setSendTo(ad01SegmentInfo.getFcm_token());
        	//TODO 실제로 사용할 메세지 등록
        	ynInx = 0;
        	arrYn[ynInx++] = ad01SegmentInfo.getYn_credit_info();
        	arrYn[ynInx++] = ad01SegmentInfo.getYn_kfb_default();
        	arrYn[ynInx++] = ad01SegmentInfo.getYn_kci_default();
        	arrYn[ynInx++] = ad01SegmentInfo.getYn_ref_info();
        	arrYn[ynInx++] = ad01SegmentInfo.getYn_loan_info();
        	arrYn[ynInx++] = ad01SegmentInfo.getYn_guarantee_info();
        	arrYn[ynInx++] = ad01SegmentInfo.getYn_fnco_disorder_info();
        	arrYn[ynInx  ] = ad01SegmentInfo.getYn_public_info();
        	stYnInx = -1;
        	cnt2Yn=0;
        	cnt3Yn=0;

        	for(int j=arrYn.length-1;j>-1;j--) {
        		if("Y".equals(arrYn[j])) {
        			stYnInx = j;
        			cntYn++;
        			switch(j) {
        				case 0:
        				case 1:
        				case 2:
        				case 3:
        				case 5:
                    	case 6:
                    	case 7:
                    		cnt2Yn++;
                    		break;
                    	case 4:
                    		cnt3Yn++;
        			}
        		}
        	}

        	logger.info("알림 내용 conts="+conts);
//     		yn_process = "N";
        	yn_display = "Y";

        	for(int j=0;j<arrYn.length;j++) {
        		if("Y".equals(arrYn[j])) {
        			switch(j) {
        				case 0:
        				case 1:
        				case 2:
        				case 3:
        				case 5:
        				case 6:
        				case 7:
        					push_divcd = "02";/* 신용 */
        					break;
        				case 4:
        					push_divcd = "03";/* 부채 */
        			}
        			/**
        			 * 여러건이면 보내지 않는다.
        			 */
        			if(cntYn > 1) {
        				yn_send = "N"; //보내지 않는다.
        			}
        			/**
        			 * 한건이면 보낸다.
        			 */
        			else {
        				yn_send = "Y"; //보낸다.
        			}
        			conts = ad01SegmentInfo.getNm_person() +"님의 "+title[j] + "가 변경되었습니다";
        			logger.info("conts---");
        			pushEachVO.setTitle("핀셋신용정보변경알리미");
        			pushEachVO.setBody(conts);
        			pushEachVO.setPush_divcd(push_divcd);
        			pushEachVO.setLink_addr(url[j]);
        			pushEachVO.setYn_send(yn_send);
        			pushEachVO.setYn_display(yn_display);
        			pushEachVO.setId_frt("BATCH"                  );//등록자
        			pushMapper.createPushAD01Info(pushEachVO);
        			logger.info("createPush---");
        		}
        	}

        	/**
        	 * 여러건인경우 발송 메세지가 한건더
        	 */
        	if(cntYn > 1) {
        		push_divcd = "";
        		/**
        		 * 신용 부채 모두 변경 되었을 경우
        		 * 푸쉬구분을 23 으로 한다.
        		 */
        		if(cnt2Yn>0 && cnt3Yn > 0){
        			push_divcd = "05";
        		}
        		/**
        		 * 신용만 변경내역이 있을 경우
        		 */
        		else if(cnt3Yn == 0 && cnt2Yn > 0) {
        			push_divcd = "02";
        		}
        		/**
        		 * 부채만 변경내역이 있을 경우
        		 */
        		else if(cnt2Yn == 0 && cnt3Yn > 0) {
        			push_divcd = "03";
        		}
        		/** yn_display : 알림내역에 보여질 여부
        		 *    Y 건수 > 1
        		 *      처음건 : 'N'
        		 *      나머지 : 'Y'
        		 *    Y 건수 = 1  : 'Y'
        		 */
        		conts = ad01SegmentInfo.getNm_person() +"님의 "+title[stYnInx] + "(외 "+(cntYn-1)+"건)가 변경되었습니다";
        		logger.info("conts---");
        		yn_send    = "Y";
        		yn_display = "N";// 알림내역에 보여주지 않음.
        		pushEachVO.setTitle("핀셋신용정보변경알리미");
        		pushEachVO.setBody(conts);
        		pushEachVO.setPush_divcd(push_divcd);
        		pushEachVO.setLink_addr("");
        		pushEachVO.setYn_send(yn_send);
        		pushEachVO.setYn_display(yn_display);
        		pushMapper.createPushAD01Info(pushEachVO);
        		logger.info("createPush---");
        	}
//        }
	}

	/**
	 * 배치 Step2
	 *
	 * <pre>
	 * reader() : push list조회
	 * processor() : 데이터 가공
	 * writer() : fcm발송, PUSH_AD01_INFO 상태update
	 * </pre>
	 *
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Step pushApiStep2() throws Exception { // chunk 큰덩어리 프로세스단위
		return stepBuilderFactory.get("[Step2 - " + BATCH_NAME + "]")
		  .<PushEachVO, PushEachVO>chunk(1)
		  .reader(pushApiReaderStep2())
		  .processor(pushApiProcessorStep2())
		  .writer(pushApiWriterStep2())
//		  .taskExecutor(App.taskExecutor())
		  .build();
	}

	public List<PushEachVO> pushApiCallStep2() throws Exception {
		PushEachForm eventEachForm = new PushEachForm();
		List<PushEachVO> pushList = pushMapper.listPushAD01Info(eventEachForm);
		PersonInfo personInfo = null;
		long   seq_push = 0L  ;// 푸시번호
		String no_person= null;// 고객번호
		String title    = null;// 제목
        String body     = null;// 내용
        String link_addr= null;// 링크주소
        String yn_push  = null;// 푸쉬보낸여부

        for (PushEachVO pushEachVO : pushList) {
			if(pushEachVO.getNo_person() != null) {
			    seq_push  = pushEachVO.getSeq_push ();
                no_person = pushEachVO.getNo_person();
                title     = pushEachVO.getTitle    ();
                body      = pushEachVO.getBody     ();
                link_addr = pushEachVO.getLink_addr();
                yn_push   = pushEachVO.getYn_push  ();
				logger.info("seq_push ="+seq_push );
				logger.info("no_person="+no_person);
				logger.info("title    ="+title    );
				logger.info("body     ="+body     );
				logger.info("link_addr="+link_addr);
				logger.info("yn_push  ="+yn_push  );
				personInfo = personMapper.getPersonInfo(no_person);
                pushEachVO.setYn_os(personInfo.getYn_os());
                pushEachVO.setCd_push(personInfo.getCd_push());
                pushEachVO.setFcm_token(personInfo.getFcm_token());
			}
        }

		return pushList;
	}

	@Bean
	public ItemReader<PushEachVO> pushApiReaderStep2() throws Exception {

		ItemReader<PushEachVO> itemReader = new ItemReader<PushEachVO>(){

			@BeforeStep
		    public void step2BeforeStep() throws Exception {
				//pushList 조회
				pushList = pushApiCallStep2();
		    }

			@Override
			public PushEachVO read() {

				PushEachVO pushEachVO = new PushEachVO();

				if (count2 < pushList.size()) {
		        	pushEachVO = pushList.get(count2);
		        	count2++;
		        	logger.info("count2-----------"+count2);
                    return pushEachVO;
                }

		        return null;
			}

			@AfterStep
		    public void step2AfterStep() throws Exception {
				count2 = 0;
		    }
		};

		return itemReader;
	}

	@Bean
	public ItemProcessor<PushEachVO, PushEachVO> pushApiProcessorStep2() {
		return new ItemProcessor<PushEachVO, PushEachVO>() {

			@Override
			public PushEachVO process(PushEachVO data) throws Exception {
				return data;
			}
		};
	}

	@Bean
	public ItemWriter<PushEachVO> pushApiWriterStep2() {
		ItemWriter<PushEachVO> writer = new ItemWriter<PushEachVO>() {

			public void write(List<? extends PushEachVO> items) throws Exception {

				for (PushEachVO vo : items) {
					sendFcm(vo);
					logger.info("wrtier2-----------");
				}

			}
		};

		return writer;
	}

	public void sendFcm(PushEachVO pushEachVO) throws Exception {

		boolean isPushSendResult = true;
		String no_person 	= pushEachVO.getNo_person();
		String curDate    	= null;

		String yn_send    = pushEachVO.getYn_send();
		String yn_display = pushEachVO.getYn_display();
		String yn_push2   = pushEachVO.getYn_push2();
		String yn_push3   = pushEachVO.getYn_push3();
		String sendTo     = pushEachVO.getFcm_token();
		String title      = pushEachVO.getTitle();
		String body       = pushEachVO.getBody();
		String linkAddr   = pushEachVO.getLink_addr();
		String push_divcd = pushEachVO.getPush_divcd();
		String yn_os      = StringUtil.nullToString(pushEachVO.getYn_os(), "1");
		String cd_push    = StringUtil.nullToString(pushEachVO.getCd_push(), "1");

		pushEachVO.setId_frt(no_person);
        pushEachVO.setId_lst(no_person);

		/**
		 * 보낼 대상인경우
		 */
		if("Y".equals(yn_send)) {
			isPushSendResult = true;
			/**
			 * 푸시를 발송해야하는 경우에만 발송을 한다.
			 */
			if("2".equals(push_divcd) && "Y".equals(yn_push2)
					|| "3".equals(push_divcd) && "Y".equals(yn_push3)
					|| "23".equals(push_divcd) && ("Y".equals(yn_push2)||"Y".equals(yn_push3))) {
				isPushSendResult = FcmUtil.sendFcm(sendTo, title, body, linkAddr, yn_os, cd_push);
			}
			if(isPushSendResult == true) {
				pushMapper.modifyYnPushAD01(pushEachVO);
			}
		} else {
			pushMapper.modifyYnPushAD01(pushEachVO);
		}
		pushMapper.createPushEachInfo(pushEachVO);
	}
}
