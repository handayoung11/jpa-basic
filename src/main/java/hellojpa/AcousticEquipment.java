package hellojpa;

import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data @ToString(callSuper = true)
@DiscriminatorValue("A")
public class AcousticEquipment extends Appliance {
    // 무선여부
    private boolean wireless;
}
