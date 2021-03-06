package carroll.tbel.tripupapplicationbackend.models.form;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Constraint;
import javax.validation.constraints.Future;

@Data
@Validated
public class ReservationForm {

    private String packageName;

    @Future
    private String departs;

    @Future
    private String returns;

    private int nmbrTravelers;

    private int price;

}
