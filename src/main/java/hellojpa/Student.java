package hellojpa;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_SUBJECT"
            , joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "SUBJECT_NAME")
    private Set<String> favoriteSubjects = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "STUDENT_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();
}
