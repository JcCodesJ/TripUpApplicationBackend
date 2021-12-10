package carroll.tbel.tripupapplicationbackend.models.form;

import carroll.tbel.tripupapplicationbackend.models.entity.Vacation;
import carroll.tbel.tripupapplicationbackend.models.entity.VacationType;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Validated
public class ReservationRequestForm {

    @NotNull
    public VacationType vacation;

    @Future
    public LocalDate departs;

    @Future
    public LocalDate returns;

    @Min(1)
    public Integer nmbrTravelers;

}
