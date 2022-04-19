package study.webclient.msainfoservicetask.presentation.webFlux;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import study.webclient.msainfoservicetask.application.person.PersonApplicationService;
import study.webclient.msainfoservicetask.application.person.response.PersonJobResponse;
import study.webclient.msainfoservicetask.presentation.shared.response.BadRequestResponse;

import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class PersonHandler {

    private final PersonApplicationService personApplicationService;

    /**
     * 이름 정보로 조회하여 직업 정보 찾기
     * @param request : 이름
     * @return Mono<ServerResponse> : 전달된 이름으로 조회된 직업 정보 전송
     */
    public Mono<ServerResponse> getPersonJob(ServerRequest request) {

        String name = request.queryParam("name").orElse(null); // Request에서 name 필드 추출

        if (StringUtils.isBlank(name)) { // 이름 유효성 검사
            return badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new BadRequestResponse("name", "전달된 이름이 없습니다.")), BadRequestResponse.class);
        }

        Mono<PersonJobResponse> response = personApplicationService.findPersonJobByPersonName(name);

        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response, PersonJobResponse.class);
    }
}
