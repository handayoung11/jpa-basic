package hellojpa;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Subject {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "subject")
    private List<StudentSubject> studentSubjects = new ArrayList<>();

//    @ManyToMany(mappedBy = "subjects")
//    private List<Student> students = new ArrayList<>();
}

