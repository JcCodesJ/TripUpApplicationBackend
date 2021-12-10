package carroll.tbel.tripupapplicationbackend.models.DTO;

import carroll.tbel.tripupapplicationbackend.models.entity.VacationType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VacationDTO {

    private String packageName;
    private VacationType vacationType;
    private double price;

    private List<ReservationDTO> reservations;

}
