package hellojpa;

import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data @ToString(callSuper = true)
@DiscriminatorValue("T")
public class Thermostat extends Appliance {

    // 냉방 여부
    private boolean cooling;

    // 난방 여부
    private boolean heating;
}
