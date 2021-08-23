package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // child1, 2 생성
            Child child1 = new Child();
            child1.setName("child1");

            Child child2 = new Child();
            child2.setName("child2");

            // parent1 생성
            Parent parent1 = new Parent();
            parent1.setName("parent1");
            parent1.getChildren().add(child1);
            parent1.getChildren().add(child2);

            child1.setParent(parent1);
            child2.setParent(parent1);
            em.persist(parent1);
//            em.remove(parent1);

            parent1.getChildren().remove(child1);

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

    public static void clearEm() {
        em.flush();
        em.clear();
    }
}
