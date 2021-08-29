package hellojpa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Club extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "CLUB_ID")
    private Long id;

    private String name;

    private String description;

//    @OneToMany(mappedBy = "club")
//    private List<Student> students = new ArrayList<>();

}
