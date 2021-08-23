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
            // programming club 생성
            Club club = new Club();
            club.setName("programming");
            club.setDescription("즐거운 프로그래밍?");
            club.setCreator("code-mania");
            em.persist(club);

            // code-mania student 생성
            Student student = new Student();
            student.setName("code-mania");
            student.setClub(club);
            student.setCreator("code-mania");
            em.persist(student);

            // programming club 생성
            Club club2 = new Club();
            club2.setName("programming");
            club2.setDescription("즐거운 프로그래밍?");
            club2.setCreator("code-mania");
            em.persist(club2);

            // code-mania student 생성
            Student student2 = new Student();
            student2.setName("code-mania");
            student2.setClub(club2);
            student2.setCreator("code-mania");
            em.persist(student2);

            clearEm();

            Student findStudent = em.find(Student.class, student.getId());
            // 실행결과: findStudent = class Club의 proxy class
            System.out.println("findStudent = " + findStudent.getClub().getClass());
            System.out.println("club.name = " + findStudent.getClub().getName());

            clearEm();

            // JPQL과 N+1 알아보기
            List<Student> students = em.createQuery("select s from Student s").getResultList();

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
