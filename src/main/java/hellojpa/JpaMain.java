package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.reflect.Member;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
//            비영속 상태
            Student student = em.find(Student.class, 200L);
            student.setName("AAAAA");

//            em.detach(student); 특정 엔티티만 준영속화
            em.clear(); // 영속성 컨텍스트 초기화

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
