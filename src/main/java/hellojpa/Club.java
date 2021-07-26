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

    @OneToMany(mappedBy = "club")
    private List<Student> students = new ArrayList<>();
}
