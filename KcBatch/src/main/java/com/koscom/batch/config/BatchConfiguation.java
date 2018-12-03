package com.koscom.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 배치 사용 환경 설정 :: 사용자는 컨텍스트에서 Bean으로 DataSource를 제공해야하며 그렇지 않으면 BatchConfigurer를 구성 클래스 자체에 구현해야합니다.
 *                        해당경우 MapJobRepositoryFactoryBean 추가함
 **/
@Configuration
@EnableBatchProcessing
public class BatchConfiguation {

	/**
     * jobLauncher를 사용하기 위한 bean
     * @param jobRepository
     * @return
     */
    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }
}
