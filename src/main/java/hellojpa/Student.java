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
    @JoinColumn(name = "CLUB_ID", insertable = false, updatable = false)
    private Club club;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    Locker locker;
}
