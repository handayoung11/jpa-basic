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
            Student student = new Student();
            student.setSchool(SchoolType.HIGH);
            student.setName("code-mania");
            student.setAge(21);
            em.persist(student);

            em.flush();
            em.clear();

            // JPQL 표준함수
//            String query = "select concat('이름: ', s.name) from Student s";
            // DB 종속함수
//            String query = "select avg(s.age) from Student s";
            // 사용자정의함수
            String query = "select average(s.age) from Student s";
            List<Double> students = em.createQuery(query, Double.class).getResultList();
            System.out.println("avg of ages = " + students.get(0));

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