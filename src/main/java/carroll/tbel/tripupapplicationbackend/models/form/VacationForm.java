package carroll.tbel.tripupapplicationbackend.models.form;

import carroll.tbel.tripupapplicationbackend.models.entity.Vacation;
import carroll.tbel.tripupapplicationbackend.models.entity.VacationType;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Data
@Validated
public class VacationForm {

    private String packageName;

    private VacationType type;

    @Min(0)
    private double price;

    public Vacation toEntity() {
        Vacation vacation = new Vacation();
        vacation.setPackageName(packageName);
        vacation.setType(type);
        vacation.setPrice(price);
        return vacation;
    }

}
