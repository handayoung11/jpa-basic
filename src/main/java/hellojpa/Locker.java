package hellojpa;

import javax.persistence.*;

@Entity
public class Locker {

    @Id @GeneratedValue
    private Long id;

    private String name;

//    @OneToOne(mappedBy = "locker")
//    Student student;
}
