package hellojpa;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Subject {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String professor;

}
