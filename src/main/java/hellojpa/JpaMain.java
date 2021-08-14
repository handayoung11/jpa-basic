package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // 수학 과목 등록
            Subject math = new Subject();
            math.setName("수학");
            em.persist(math);

            // 한국사 과목 등록
            Subject history = new Subject();
            history.setName("한국사");
            em.persist(history);

            // codeMania 학생 등록
            Student codeMania = new Student();
            codeMania.setName("code-mania");
            codeMania.setAge(21);
            em.persist(codeMania);

            // codeLover 학생 등록
            Student codeLover = new Student();
            codeLover.setName("code-lover");
            codeLover.setAge(21);
            em.persist(codeLover);

            // codeMania 학생 수학 과목 수강
            StudentSubject studentSubject = new StudentSubject();
            codeMania.addStudentSubject(studentSubject);
            studentSubject.takeSubject(math);
            em.persist(studentSubject);

            // codeMania 학생 한국사 과목 수강
            StudentSubject studentSubject2 = new StudentSubject();
            codeMania.addStudentSubject(new StudentSubject());
            studentSubject2.takeSubject(history);
            em.persist(studentSubject2);

            // codeLover 학생 한국사 과목 수강
            StudentSubject studentSubject3 = new StudentSubject();
            studentSubject3.setStudent(codeLover);
            studentSubject3.takeSubject(history);
            em.persist(studentSubject3);

            tx.commit();
            System.out.println("codeMania가 수강 중인 과목 수 = " + codeMania.getStudentSubjects().size());
            System.out.println("수학을 수강 중인 학생 수 = " + math.getStudentSubjects().size());
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        em.close();
        emf.close();
    }
}
