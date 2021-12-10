package carroll.tbel.tripupapplicationbackend.models.form;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Future;
import java.time.LocalDate;

@Data
@Validated
public class ReservationForm {

    @Future
    private LocalDate departs;

    @Future
    private LocalDate returns;

    private int nmbrTravelers;

}
