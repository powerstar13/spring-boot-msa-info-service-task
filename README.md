# REST API로 내부 통신하는 MSA 구축

## Github Repository Link

`현재 접근하신 레포지토리는 InfoService Server 입니다.`

### [SayHello Server](https://github.com/powerstar13/spring-boot-webflux-functional-endpoints-task)
- Server port = 8080
### [InfoService Server](https://github.com/powerstar13/spring-boot-msa-info-service-task)
- Server port = 8081


# Requirement

1. SayHello Server에서 InfoService Server로 내부 호출
    1. InfoService Server의 Port
        1. application.yml 에서 port 변경하여 설정
2. InfoService는 호출 파라미터로 찾은 데이터의 직업 응답

# Spec

1. Spring 5+ Or SpringBoot 2+
2. Java 8+
3. WebFlux
4. Functional Endpoint
5. WebClient

## SayHello Server Request

`GET localhost:8080/hello?name=$name`

## SayHello Server Response

`application/json`
- 직업 데이터가 추가 되었다.
```json
{
    "to": "$name", 
    "job": "BE",
    "message": "hello $name"
}
```

## InfoService Server Request

`GET localhost:8081/info-service/job?name=$name`

## InfoService Server Response

`application/json`
```json
{
  "job": "BE"
}
```

# 내부 통신

![img.png](img.png)

# 생성된 파일 및 패키지 위치

1. resources
   1. application.yml
   2. schema.sql

```java
// R2dbcConfig
// WebFluxConfig
package study.webclient.msainfoservicetask.infrastructure.config;

// Person
// PersonFindSpecification
// PersonRepository
// PersonRepositoryTest >>> TEST
package study.webclient.msainfoservicetask.domain.model;

// PersonApplicationService
// PersonApplicationServiceTest >>> TEST
package study.webclient.msainfoservicetask.application.person;

// PersonJobResponse
package study.webclient.msainfoservicetask.application.person.response;

// PersonHandler
// PersonHandlerTest >>> TEST
package study.webclient.msainfoservicetask.presentation.webFlux;

// BadRequestResponse
package study.webclient.msainfoservicetask.presentation.shared.response;
```