package carroll.tbel.tripupapplicationbackend.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "clientId")
public class Client extends User{

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String tel;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> mthdOfPay;

    @OneToMany(mappedBy = "bookedBy", fetch = FetchType.EAGER)
    private Set<Reservation> reservations = new HashSet<>();

}
