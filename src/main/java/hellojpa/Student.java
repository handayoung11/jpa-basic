package hellojpa;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Student {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int age;

    @ManyToOne
    @JoinColumn(name = "CLUB_ID", insertable = false, updatable = false)
    private Club club;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    Locker locker;

    @OneToMany(mappedBy = "student")
    private List<StudentSubject> studentSubjects = new ArrayList<>();
//    @ManyToMany
//    @JoinTable(name = "StudentSubject")
//    private List<Subject> subjects = new ArrayList<>();
}
