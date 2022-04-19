package study.webclient.msainfoservicetask.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "person")
public class Person {

    @Id
    @Column(value = "personId")
    private Long personId; // 고유번호

    @Column(value = "personName")
    private String personName; // 이름

    @Column(value = "personJob")
    private String personJob; // 직업
}
