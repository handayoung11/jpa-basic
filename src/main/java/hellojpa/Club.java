package hellojpa;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Club {

    @Id @GeneratedValue
    @Column(name = "CLUB_ID")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "CLUB_ID")
    private List<Student> students = new ArrayList<>();
}
