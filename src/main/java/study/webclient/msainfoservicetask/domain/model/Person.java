package study.webclient.msainfoservicetask.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "person")
public class Person {

    @Id // ID로 엔티티를 식별한다.
    @Column(value = "personId")
    private Long personId; // 고유번호

    @Column(value = "personName")
    private String personName; // 이름

    @Column(value = "personJob")
    private String personJob; // 직업
}
