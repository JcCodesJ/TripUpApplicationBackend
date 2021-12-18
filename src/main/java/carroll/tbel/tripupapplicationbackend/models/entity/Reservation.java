package carroll.tbel.tripupapplicationbackend.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate departs;

    @Column(nullable = false)
    private LocalDate returns;

    @Column(nullable = false)
    private int nmbrTravelers;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    private Vacation vacation;

    @ManyToOne
    private User bookedBy;


//    public void setVacation(String packageName){
//        this.vacation = new Vacation();
//        this.vacation.setDescription(packageName);
//    }

}
