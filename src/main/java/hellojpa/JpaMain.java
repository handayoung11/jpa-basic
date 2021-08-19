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
            // programming club 생성
            Club club = new Club();
            club.setName("programming");
            club.setDescription("즐거운 프로그래밍?");
            club.setCreator("code-mania");
            em.persist(club);

            // code-mania student 생성
            Student student = new Student();
            student.setName("code-mania");
            student.setClub(club);
            student.setCreator("code-mania");
            em.persist(student);

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
