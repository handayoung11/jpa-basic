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

//    @ManyToOne(fetch = FetchType.EAGER)
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "CLUB_ID")
//    private Club club;

    @Embedded
    private Period period;

    @Embedded
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "SCHOLLCITY")),
            @AttributeOverride(name = "street", column = @Column(name = "SCHOOLSTREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "SCHOOLZIPCODE"))
    })
    private Address schollAddress;

//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    Locker locker;
//
//    @OneToMany(mappedBy = "student")
//    private List<StudentSubject> studentSubjects = new ArrayList<>();
//
//    void addStudentSubject(StudentSubject studentSubject) {
//        studentSubject.setStudent(this);
//        studentSubjects.add(studentSubject);
//    }
}
