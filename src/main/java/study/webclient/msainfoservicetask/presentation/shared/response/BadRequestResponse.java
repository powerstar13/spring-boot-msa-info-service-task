package study.webclient.msainfoservicetask.presentation.shared.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BadRequestResponse {

    private String field;
    private String message;
}
