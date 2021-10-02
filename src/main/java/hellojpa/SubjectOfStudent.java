package hellojpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SubjectOfStudent {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    Student student;
}
