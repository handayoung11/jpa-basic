package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            List<Student> resultList = em.createQuery("select s from Student s where s.name like '%김%'", Student.class)
                    .getResultList();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Student> query = cb.createQuery(Student.class);
            Root<Student> s = query.from(Student.class);

            CriteriaQuery<Student> cq = query.select(s);

            //동적쿼리
            String name = "kim";
            if(name != null) cq.where(cb.like(s.get("name"), name));

            List<Student> students = em.createQuery(cq).getResultList();

            List students2 = em.createNativeQuery("select * from student", Student.class).getResultList();

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