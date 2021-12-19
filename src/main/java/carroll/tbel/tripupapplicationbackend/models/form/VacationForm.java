package carroll.tbel.tripupapplicationbackend.models.form;

import carroll.tbel.tripupapplicationbackend.models.entity.Vacation;
import carroll.tbel.tripupapplicationbackend.models.entity.VacationType;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Validated
public class VacationForm {

    @NotBlank
    private String packageName;

    @NotBlank
    private VacationType type;

    @Min(0)
    private double price;

    @NotBlank
    private String description;

    private String picture_address;

    private boolean hidden;

    public Vacation formToEntity() {
        Vacation vacation = new Vacation();
        vacation.setPackageName(packageName);
        vacation.setType(type);
        vacation.setPrice(price);
        vacation.setDescription(description);
        vacation.setPicture_address(picture_address);
        vacation.setHidden(hidden);
        return vacation;
    }

}
