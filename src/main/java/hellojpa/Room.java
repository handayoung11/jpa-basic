package hellojpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Room {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CLUB_ID")
    private Club club;

    private String name;

    private String location;
}
