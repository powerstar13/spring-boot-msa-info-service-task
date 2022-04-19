package study.webclient.msainfoservicetask.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void findAll() {

        Mono<Person> fallback = Mono.error(new ServerWebInputException("조회된 정보가 없습니다.")); // 조회된 정보가 없을 경우 fallback할 error 처리

        Flux<Person> personFlux = personRepository.findAll()
            .log()
            .switchIfEmpty(fallback);

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

        Mono<Person> fallback = Mono.error(new ServerWebInputException(name + "이름으로 조회되는 정보가 없습니다.")); // 조회된 정보가 없을 경우 fallback할 error 처리

        Mono<Person> foundedPerson = personRepository.findFirstByPersonName(name)
            .log()
            .switchIfEmpty(fallback);

        StepVerifier.create(foundedPerson)
            .assertNext(person -> {
                assertEquals("홍준성", person.getPersonName());
                assertEquals("BE", person.getPersonJob());
            })
            .as("홍준성 정보 조회 검증")
            .verifyComplete();
    }

    @Test
    void findFirstByPersonName_notFound() {

        String name = "노존재";

        Mono<Person> fallback = Mono.error(new ServerWebInputException(name + "이름으로 조회되는 정보가 없습니다.")); // 조회된 정보가 없을 경우 fallback할 error 처리

        Mono<Person> personMono = personRepository.findFirstByPersonName(name)
            .log()
            .switchIfEmpty(fallback);

        StepVerifier.create(personMono)
            .expectError(ServerWebInputException.class)
            .verify();
    }
}