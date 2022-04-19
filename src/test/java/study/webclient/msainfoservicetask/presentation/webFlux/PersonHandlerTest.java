package study.webclient.msainfoservicetask.presentation.webFlux;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import study.webclient.msainfoservicetask.application.person.response.PersonJobResponse;
import study.webclient.msainfoservicetask.infrastructure.config.WebFluxConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonHandlerTest {


    private WebTestClient webTestClient;

    @Autowired
    private WebFluxConfig webFluxConfig;
    @Autowired
    private PersonHandler personHandler;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient
            .bindToRouterFunction( // WebFluxConfig에서 작성한 router를 WebTestClient에 바인딩해준다.
                webFluxConfig.routerBuilder(personHandler)
            )
            .build();
    }

    /**
     * 직업 정보 조회 Test
     * isOk
     */
    @Test
    void getPersonJob() {
        String name = "홍준성";

        // 바인딩된 클라이언트를 이용하여 routerBuilder에 존재하는 라우팅 엔드포인트에 요청을 보내면, 핸들러 로직을 수행 후 응답을 반환한다.
        webTestClient
            .get()
            .uri("/info-service/job?name=" + name)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody(PersonJobResponse.class)
            .value(personMessageResponse ->
                assertAll(() -> {
                    assertEquals("BE", personMessageResponse.getJob()); // job 검증
                })
            );
    }
}