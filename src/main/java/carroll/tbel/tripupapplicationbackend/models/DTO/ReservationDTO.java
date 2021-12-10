package carroll.tbel.tripupapplicationbackend.models.DTO;

import carroll.tbel.tripupapplicationbackend.models.entity.VacationType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ReservationDTO {

    private long id;
    private LocalDate departs;
    private LocalDate returns;
    private int nmbrTravelers;

    private ReservationDTO.ClientDTO client;
    private ReservationDTO.VacationDTO vacation;

    @Data
    @Builder
    public static class ClientDTO {
        private String username;
        private String firstName;
        private String lastName;
        private String tel;
        private List<String> mthdOfPay;
    }

    @Data
    @Builder
    public static class VacationDTO {
        private String packageName;
        private VacationType type;
        private double price;
    }
}
