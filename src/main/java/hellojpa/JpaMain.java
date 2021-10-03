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
            Club c1 = new Club();
            c1.setName("c1");
            em.persist(c1);

            Club c2 = new Club();
            c2.setName("c2");
            em.persist(c2);

            Student student = new Student();
            student.setName("code-mania");
            student.setAge(21);
            student.changeClub(c1);
            em.persist(student);

            Student student2 = new Student();
            student2.setName("code-mania2");
            student2.setAge(21);
            student2.changeClub(c1);
            em.persist(student2);

            Student student3 = new Student();
            student3.setName("code-mania3");
            student3.setAge(21);
            student3.changeClub(c2);
            em.persist(student3);

            em.flush();
            em.clear();

            //페치조인
//            String query = "select s from Student s join fetch s.club c";
//            List<Student> students = em.createQuery(query, Student.class).getResultList();
//
//            for (Student s : students) {
//                System.out.println("name = " + s.getName());
//                System.out.println("club = " + s.getClub().getName());
//                System.out.println("================================");
//            }

            //컬렉션페치조인
            String query = "select distinct c from Club c join fetch c.students s";
            List<Club> clubs = em.createQuery(query, Club.class).getResultList();

            for (Club c : clubs) {
                System.out.println("name = " + c.getName());
                System.out.println("student size = " + c.getStudents().size());
                System.out.println("================================");
            }

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



