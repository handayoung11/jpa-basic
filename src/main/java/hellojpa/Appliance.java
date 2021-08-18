package hellojpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "KIND")
public abstract class Appliance {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;
}
