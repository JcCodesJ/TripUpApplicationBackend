package carroll.tbel.tripupapplicationbackend.models.DTO;

import carroll.tbel.tripupapplicationbackend.models.entity.VacationType;
import lombok.AllArgsConstructor;
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

    private ReservationDTO.UserDTO bookedBy;
    private ReservationDTO.VacationDTO vacation;

    @Data
    @Builder
    public static class UserDTO {
        private Long id;


        private String username;


        private String email;


    }

    @Data
    @Builder
    public static class VacationDTO {
        private String packageName;
        private VacationType type;
        private double price;
    }
}
