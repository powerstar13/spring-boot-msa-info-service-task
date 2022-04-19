package study.webclient.msainfoservicetask.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import study.webclient.msainfoservicetask.presentation.webFlux.PersonHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux // WebFlux 설정 활성화
public class WebFluxConfig implements WebFluxConfigurer {

    @Bean
    public RouterFunction<ServerResponse> routerBuilder(PersonHandler personHandler) {

        return route()
            .path("/info-service", helloBuilder ->
                helloBuilder.nest(accept(APPLICATION_JSON), builder ->
                    builder
                        .GET("/job", personHandler::getPersonJob)
                )
            )
            .build();
    }
}
