package hellojpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Club {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "club")
    private List<Student> students = new ArrayList<>();
}
