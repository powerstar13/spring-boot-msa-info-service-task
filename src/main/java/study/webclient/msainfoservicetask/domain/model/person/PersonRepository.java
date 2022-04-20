package study.webclient.msainfoservicetask.domain.model.person;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {

    Mono<Person> findFirstByPersonName(String personName); // 이름으로 정보 조회
}
