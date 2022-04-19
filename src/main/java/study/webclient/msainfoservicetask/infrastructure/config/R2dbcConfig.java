package study.webclient.msainfoservicetask.infrastructure.config;

import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import study.webclient.msainfoservicetask.domain.model.Person;
import study.webclient.msainfoservicetask.domain.model.PersonRepository;

import java.time.Duration;
import java.util.Arrays;

/**
 * Spring Data R2DBC를 사용하는 방법에는 Spring Data에서 지원하는 Repository를 이용하는 방법과 R2dbcEntityTemplate을 이용하는 방법이 있다.
 * 이번 예제에서는 Repository를 이용할 것이기 때문에 @EnableR2dbcRepositories 어노테이션을 추가한다.
 */
@Slf4j
@Configuration
@EnableR2dbcRepositories
public class R2dbcConfig extends AbstractR2dbcConfiguration {

    /**
     * ConnectionFactory는 application.yml에서 설정값으로 생성하기 때문에 생략한다.
     * @return ConnectionFactory
     */
    @Override
    public ConnectionFactory connectionFactory() { return null; }

    /**
     * 초기 데이터 세팅
     * @param personRepository : person 레포티조리
     * @return CommandLineRunner : 명령 실행
     */
    @Bean
    public CommandLineRunner setUp(PersonRepository personRepository) {

        return args -> {
            log.info("===== Person Data setUp START =====");
            // Person 정보 저장
            personRepository.saveAll(
                Arrays.asList(
                    Person.builder()
                        .personName("홍준성")
                        .personJob("BE")
                        .build(),
                    Person.builder()
                        .personName("홍길동")
                        .personJob("FE")
                        .build()
                )
            ).blockLast(Duration.ofSeconds(10)); // 10초가 만료될 때까지 차단한다.

            log.info("===== Person Data setUp Completed INFO =====");
            personRepository.findAll()
                .doOnNext(person -> log.info(person.toString()))
                .blockLast(Duration.ofSeconds(10));

            log.info("===== Person Data setUp END =====");
        };
    }
}
