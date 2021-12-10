package carroll.tbel.tripupapplicationbackend.models.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AgentDTO {

    private Long id;


    private String username;


    private String email;
}
