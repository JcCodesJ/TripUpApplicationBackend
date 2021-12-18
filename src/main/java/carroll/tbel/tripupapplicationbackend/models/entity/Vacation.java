package carroll.tbel.tripupapplicationbackend.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vacation")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Vacation {

    @Id
    @Column(name = "package_name")
    private String packageName;

    @Column(name = "description")
    private String description;

    @Column(nullable = false)
    @Enumerated( EnumType.STRING )
    private VacationType type;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double price;

    @OneToMany(mappedBy = "vacation", fetch = FetchType.EAGER)
    private Set<Reservation> reservations = new HashSet<>();

    @Column(name = "picture_address")
    private String picture_address;

}
