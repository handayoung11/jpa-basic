package hellojpa;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Student {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    private int age;

    @Setter(AccessLevel.PROTECTED)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLUB_ID")
    Club club;

    @OneToMany(mappedBy = "student")
    List<SubjectOfStudent> mySubjects = new ArrayList<>();

    void changeClub(Club club) {
        this.setClub(club);
        club.getStudents().add(this);
    }
}
