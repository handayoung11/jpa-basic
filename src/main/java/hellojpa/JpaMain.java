package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Student codeMania = new Student();
            codeMania.setName("code-mania");
            codeMania.setAge(21);
//            codeMania.setClub(club);
            em.persist(codeMania);

            Student codeLover = new Student();
            codeLover.setName("code-lover");
            codeLover.setAge(21);
//            codeLover.setClub(club);
            em.persist(codeLover);

            Club club = new Club();
            club.setName("프로그래밍부");
            club.getStudents().add(codeMania);
            club.getStudents().add(codeLover);
            em.persist(club);
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
