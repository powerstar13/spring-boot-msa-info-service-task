package study.webclient.msainfoservicetask.domain.model.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonFindSpecification {

    private final PersonRepository personRepository;

    /**
     * 이름으로 정보 검증
     * @param personName : 이름
     * @return Mono<Person> : 조회된 정보
     */
    public Mono<Person> findFirstByPersonName(String personName) {

        Mono<Person> fallback = Mono.error(new ServerWebInputException(personName + "이름으로 조회되는 정보가 없습니다.")); // 조회된 정보가 없을 경우 fallback할 error 처리

        return personRepository.findFirstByPersonName(personName)
            .switchIfEmpty(fallback);
    }
}
