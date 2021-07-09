package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Student {

    @Id
    private Long id;
    private int age;
    private String name;

    public Student() { }
    public Student(Long id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
}
