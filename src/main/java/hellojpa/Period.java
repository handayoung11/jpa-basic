package hellojpa;

import lombok.Data;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class Period {
    private LocalDateTime admissionDate;
    private LocalDateTime graduationDate;
}
