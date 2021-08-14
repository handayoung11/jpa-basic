package hellojpa;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class StudentSubject {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @ManyToOne
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "SUBJECT_ID")
    private Subject subject;

    @CreationTimestamp
    private LocalDateTime registrationDate;

    public void takeSubject(Subject subject) {
        this.subject = subject;
        subject.getStudentSubjects().add(this);
    }
}
