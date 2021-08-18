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
            Kitchenware kitchenware = new Kitchenware();
            kitchenware.setName("냄비");
            kitchenware.setPrice(20000);
            kitchenware.setManufacturer("code-mania");
            kitchenware.setPurpose("무수분, 저수분 웰빙요리에 적합한 스테인리스 냄비");
            em.persist(kitchenware);

            em.flush();
            em.clear();

            Appliance appliance = em.find(Appliance.class, kitchenware.getId());
            System.out.println("appliance = " + appliance);

            if(appliance instanceof Kitchenware) {
                Kitchenware findKitchenware = (Kitchenware) appliance;
                System.out.println("kitchenware.getPurpose() = " + findKitchenware.getPurpose());
            } else if(appliance instanceof AcousticEquipment) {
                AcousticEquipment acousticEquipment = (AcousticEquipment) appliance;
                System.out.println("acousticEquipment.isWireless() = " + acousticEquipment.isWireless());
            } else if(appliance instanceof Thermostat) {
                Thermostat thermostat = (Thermostat) appliance;
                System.out.println("thermostat.isCooling() = " + thermostat.isCooling());
            }

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
