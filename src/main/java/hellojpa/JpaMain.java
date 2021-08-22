package hellojpa;

import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;

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

            clearEm();

            // 타입 체크 코드
            Student refStudent = em.getReference(Student.class, student.getId());
            isStudent(refStudent);

            clearEm();

            // 동일성 보장 테스트 코드
            checkIdentity(student);

            clearEm();

            // 프록시 관리
            proxyUtill(em, student);

            clearEm();

            // 에러 체크 코드
            errorCheck(student);

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

    public static void checkIdentity(Student student) {
        Student refStudent = em.getReference(Student.class, student.getId());
        Student findStudent = em.find(Student.class, student.getId());

        System.out.println("refStudent = " + refStudent.getClass());
        System.out.println("findStudent = " + findStudent.getClass());
        System.out.println("(refStudent == findStudent) = " + (refStudent == findStudent));

        clearEm();

        Student findStudent2 = em.find(Student.class, student.getId());
        Student refStudent2 = em.getReference(Student.class, student.getId());

        System.out.println("refStudent2 = " + refStudent2.getClass());
        System.out.println("findStudent2 = " + findStudent2.getClass());
        System.out.println("(refStudent2 == findStudent2) = " + (refStudent2 == findStudent2));
    }

    public static boolean isStudent(Object student) {
        // ==으로 타입 체크
        System.out.println("student = " + (student.getClass() == Student.class));
        // instanceof로 타입체크
        System.out.println("student instanceof Student = " + (student instanceof Student));
        return student instanceof Student;
    }

    public static void errorCheck(Student student) {
        try {
            Student refStudent = em.getReference(Student.class, student.getId());
            // refStudent 준영속화
            em.detach(refStudent);
            // refStudent 초기화
            refStudent.getName();
        } catch (LazyInitializationException e) {
            System.out.println("0. error====================================");
            e.printStackTrace();
        }
    }

    public static void proxyUtill(EntityManager em, Student student) {
        System.out.println("1. proxy utill====================================");
        Student refStudent = em.getReference(Student.class, student.getId());
        // proxy class 이름 확인
        System.out.println("refStudent.getClass().getName() = " + refStudent.getClass().getName());
        // proxy class 초기화 여부 확인
        System.out.println("emf.getPersistenceUnitUtil().isLoaded(refStudent) = " + emf.getPersistenceUnitUtil().isLoaded(refStudent));
        // proxy class 초기화
        Hibernate.initialize(refStudent);
        System.out.println("emf.getPersistenceUnitUtil().isLoaded(refStudent) = " + emf.getPersistenceUnitUtil().isLoaded(refStudent));
    }
}
