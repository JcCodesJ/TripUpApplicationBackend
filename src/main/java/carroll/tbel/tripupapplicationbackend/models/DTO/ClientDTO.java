package carroll.tbel.tripupapplicationbackend.models.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDTO {

    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String tel;
    private List<String> mthdOfPay;

    private List<ReservationDTO> reservations;

}
