package hellojpa;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Column(nullable = false)
    private String creator;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private String modifier;

    private LocalDateTime lastModifiedBy;
}
