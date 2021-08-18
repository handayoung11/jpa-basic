package hellojpa;

import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data @ToString(callSuper = true)
@DiscriminatorValue("K")
public class Kitchenware extends Appliance { //주방용품

    // 제조사
    private String manufacturer;

    // 용도
    private String purpose;
}
