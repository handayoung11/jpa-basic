package hellojpa;

import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        LocalDateTime admissionDate = LocalDateTime.now();

        Period period1 = new Period();
        period1.setAdmissionDate(admissionDate);
        period1.setGraduationDate(null);

        Period period2 = new Period();
        period2.setAdmissionDate(admissionDate);
        period2.setGraduationDate(null);

        System.out.println("(period1 == period2) = " + (period1 == period2));
        System.out.println("(period1.equals(period2)) = " + (period1.equals(period2)));
    }
}
