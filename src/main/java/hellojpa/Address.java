package hellojpa;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {
    private String city;

    private String stret;

    private String zipcode;
}
