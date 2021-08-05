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

            Club club = new Club();
            club.setName("프로그래밍부");
//            club.setDescription("재밌게 같이 코딩해봐요~~~");
            em.persist(club);

            Student codeMania = new Student();
            codeMania.setName("code-mania");
            codeMania.setAge(21);
            codeMania.setClub(club);
            em.persist(codeMania);

            Student codeLover = new Student();
            codeLover.setName("code-lover");
            codeLover.setAge(21);
            codeLover.setClub(club);
            em.persist(codeLover);

            codeLover.setName("codeLover");
            em.flush();
            em.clear();

            System.out.println("After");
            Club findClub = em.find(Club.class, club.getId());
            List<Student> students = findClub.getStudents();
            for(Student s:students) System.out.println(club.getName() + ": " + s.getName());
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        em.close();
        emf.close();
    }
}
