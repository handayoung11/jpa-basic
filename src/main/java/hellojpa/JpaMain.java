package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Subject math = new Subject();
            math.setName("수학");
            em.persist(math);

            Subject history = new Subject();
            history.setName("한국사");
            em.persist(history);

            Student codeMania = new Student();
            codeMania.setName("code-mania");
            codeMania.setAge(21);
//            codeMania.getSubjects().add(math);
//            codeMania.getSubjects().add(history);
            em.persist(codeMania);

            Student codeLover = new Student();
            codeLover.setName("code-lover");
            codeLover.setAge(21);
//            codeLover.getSubjects().add(history);
            em.persist(codeLover);

            StudentSubject studentSubject = new StudentSubject();
            studentSubject.setStudent(codeMania);
            studentSubject.setSubject(math);
            em.persist(studentSubject);

            StudentSubject studentSubject2 = new StudentSubject();
            studentSubject2.setStudent(codeMania);
            studentSubject2.setSubject(history);
            em.persist(studentSubject2);

            StudentSubject studentSubject3 = new StudentSubject();
            studentSubject3.setStudent(codeLover);
            studentSubject3.setSubject(history);
            em.persist(studentSubject3);

            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        em.close();
        emf.close();
    }
}
