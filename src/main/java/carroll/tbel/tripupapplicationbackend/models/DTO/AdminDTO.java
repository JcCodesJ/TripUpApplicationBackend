package carroll.tbel.tripupapplicationbackend.models.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminDTO {

    private Long id;


    private String username;


    private String email;


    private String password;

}
