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

            // code-mania 학생 등록
            Student codeMania = new Student();
            codeMania.setName("code-mania");
            codeMania.setAge(21);
            codeMania.getSubjects().add(math); // 수학 과목 수강
            codeMania.getSubjects().add(history); // 역사 과목 수강
            em.persist(codeMania);

            // code-lover 학생 등록
            Student codeLover = new Student();
            codeLover.setName("code-lover");
            codeLover.setAge(21);
            codeLover.getSubjects().add(history); // 역사 과목 수강
            em.persist(codeLover);
            tx.commit();
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
