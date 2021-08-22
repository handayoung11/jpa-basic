    package hellojpa;

    import javax.persistence.EntityManager;
    import javax.persistence.EntityManagerFactory;
    import javax.persistence.EntityTransaction;
    import javax.persistence.Persistence;

    public class JpaMain {
        public static void main(String[] args) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

            EntityManager em = emf.createEntityManager();
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

                em.flush();
                em.clear();

                System.out.println("0. ======================="); // 0번 checkpoint
                Student refMember = em.getReference(Student.class, student.getId());
                System.out.println("1. refMember.getClass() = " + refMember.getClass()); // 1번 print
                System.out.println("2. refMember.getId() = " + refMember.getId()); // 2번 print
                System.out.println("3. refMember.getName() = " + refMember.getName()); // 3번 print
                System.out.println("=======================");

                em.flush();
                em.clear();

                System.out.println("4. ======================="); // 4번 checkpoint
                Student findMember = em.find(Student.class, student.getId());
                System.out.println("5. findMember.getClass() = " + findMember.getClass()); // 5번 print
                System.out.println("6. findMember.getId() = " + findMember.getId()); // 6번 print
                System.out.println("7. findMember.getName() = " + findMember.getName()); // 7번 print
                System.out.println("=======================");

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
