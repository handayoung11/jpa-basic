package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // 저장 코드
            Student student = new Student();
            student.setName("code-mania");

            student.getFavoriteSubjects().add("수학");
            student.getFavoriteSubjects().add("영어");
            student.getFavoriteSubjects().add("역사");

            student.getAddressHistory().add(new AddressEntity("서울특별시", "세종대로", "10000"));
            student.getAddressHistory().add(new AddressEntity("인천광역시", "부평대로", "60000"));

            em.persist(student);

            em.flush();
            em.clear();

            // 조회 코드
            System.out.println("============================");
            Student findStudent = em.find(Student.class, student.getId());
            List<AddressEntity> addressHistory = findStudent.getAddressHistory();
            for (AddressEntity address : addressHistory) {
                System.out.println("address.getCity() = " + address.getAddress().getCity());
            }

            Set<String> favoriteSubjects = findStudent.getFavoriteSubjects();
            for (String favoriteSubject : favoriteSubjects) {
                System.out.println("favoriteSubject = " + favoriteSubject);
            }
            System.out.println("============================");

            // 수정 코드
            favoriteSubjects.remove("역사");
            favoriteSubjects.add("과학");

            Address address = new Address("서울특별시", "세종대로", "10000");
            findStudent.getAddressHistory().get(0).setAddress(new Address(address.getCity(), address.getStreet(), "65202"));

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