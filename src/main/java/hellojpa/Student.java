package hellojpa;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Student {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int age;

    @ManyToOne
    @JoinColumn(name = "CLUB_ID")
    private Club club;

    public void changeClub(Club club) {
        this.club = club;
        club.getStudents().add(this);
    }
}
