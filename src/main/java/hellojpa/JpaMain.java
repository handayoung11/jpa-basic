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
            Address address = new Address("city", "street", "100");

            Student codeMania = new Student();
            codeMania.setName("code-mania");
            codeMania.setAddress(address);
            em.persist(codeMania);

//            Address address2 = new Address(address.getCity(), address.getStreet(), address.getZipcode());
            Student codeLover = new Student();
            codeLover.setName("code-lover");
            codeLover.setAddress(address);
            em.persist(codeLover);

//            codeMania.getAddress().setCity("newCity");
            codeMania.setAddress(new Address("newCity", address.getStreet(), address.getZipcode()));

            System.out.println("codeLover.getAddress() = " + codeLover.getAddress());
            System.out.println("codeLover.getAddress() = " + (codeLover.getAddress() == codeMania.getAddress()));
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
