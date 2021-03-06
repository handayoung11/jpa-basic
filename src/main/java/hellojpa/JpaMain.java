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
            student3.changeClub(c1);
            em.persist(student3);

            Room r1 = new Room();
            r1.setClub(c1);
            r1.setLocation("1F");
            r1.setName("도서관");
            em.persist(r1);

            Room r2 = new Room();
            r2.setClub(c1);
            r2.setLocation("2F");
            r2.setName("컴퓨터실");
            em.persist(r2);


            String query = "update Student s set s.age = 0";
            int updatedDataSize = em.createQuery(query).executeUpdate();
//            System.out.println("student = " + student.getAge());

            em.flush();
            em.clear();

            Student findStudent = em.find(Student.class, student.getId());
            System.out.println("findStudent = " + findStudent);
            System.out.println("student = " + student);
            System.out.println("updatedDataSize = " + updatedDataSize);

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



