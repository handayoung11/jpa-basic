package hellojpa;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Club {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "club")
    @BatchSize(size = 100)
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    @ToString.Exclude
    private List<Room> rooms = new ArrayList<>();
}
