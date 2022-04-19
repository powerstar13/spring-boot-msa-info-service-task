package study.webclient.msainfoservicetask.infrastructure.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * Spring Data R2DBC를 사용하는 방법에는 Spring Data에서 지원하는 Repository를 이용하는 방법과 R2dbcEntityTemplate을 이용하는 방법이 있다.
 * 이번 예제에서는 Repository를 이용할 것이기 때문에 @EnableR2dbcRepositories 어노테이션을 추가한다.
 */
@Configuration
@EnableR2dbcRepositories
public class R2dbcConfig extends AbstractR2dbcConfiguration {

    /**
     * ConnectionFactory는 application.yml에서 설정값으로 생성하기 때문에 생략한다.
     * @return ConnectionFactory
     */
    @Override
    public ConnectionFactory connectionFactory() { return null; }
}
