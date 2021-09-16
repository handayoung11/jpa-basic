package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Club club = new Club();
            club.setName("programming");
            em.persist(club);

            Student student = new Student();
            student.changeClub(club);
            student.setName("code-mania");
            student.setAge(21);
            em.persist(student);

            em.flush();
            em.clear();

//            String query = "select s from Student s inner join s.club c"; //inner join
//            String query = "select s from Student s left join s.club c"; //outer join
//            String query = "select s, c from Student s, Club c where s.name = c.name"; // cross join
            String query = "select s, c from Student s left join Club c on c.name='programming'"; // on으로 조건 걸기
            List<Object[]> students = em.createQuery(query).getResultList();
            System.out.println("members.size() = " + students.size());

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