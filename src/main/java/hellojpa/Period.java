package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class Period {
    private LocalDateTime admissionDate;
    private LocalDateTime graduationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(admissionDate, period.admissionDate) && Objects.equals(graduationDate, period.graduationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(admissionDate, graduationDate);
    }
}
