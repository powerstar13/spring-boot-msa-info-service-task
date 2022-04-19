package study.webclient.msainfoservicetask.application.person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import study.webclient.msainfoservicetask.application.person.response.PersonJobResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonApplicationServiceTest {

    @Autowired
    private PersonApplicationService personApplicationService;

    @Test
    void findPersonJobByPersonName() {

        String name = "홍준성";

        Mono<PersonJobResponse> personJobResponseMono = personApplicationService.findPersonJobByPersonName(name)
            .log();

        StepVerifier.create(personJobResponseMono)
            .assertNext(personJobResponse -> assertEquals("BE", personJobResponse.getJob()))
            .as(name + "의 직업 정보 검증")
            .verifyComplete();
    }
}