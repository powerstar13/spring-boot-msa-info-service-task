package study.webclient.msainfoservicetask.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void findAll() {

        Flux<Person> personFlux = personRepository.findAll()
            .log();

        StepVerifier.create(personFlux)
            .assertNext(person -> {
                assertEquals("홍준성", person.getPersonName());
                assertEquals("BE", person.getPersonJob());
            })
            .as("홍준성 정보 조회 검증")
            .assertNext(person -> {
                assertEquals("홍길동", person.getPersonName());
                assertEquals("FE", person.getPersonJob());
            })
            .as("홍길동 정보 조회 검증")
            .verifyComplete();
    }

    @Test
    void findFirstByPersonName_exist() {

        String name = "홍준성";

        Mono<Person> personMono = personRepository.findFirstByPersonName(name)
            .log();

        StepVerifier.create(personMono)
            .assertNext(person -> {
                assertEquals("홍준성", person.getPersonName());
                assertEquals("BE", person.getPersonJob());
            })
            .as("홍준성 정보 조회 검증")
            .verifyComplete();
    }
}