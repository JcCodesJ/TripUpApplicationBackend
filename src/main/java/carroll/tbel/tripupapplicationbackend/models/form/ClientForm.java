package carroll.tbel.tripupapplicationbackend.models.form;

import carroll.tbel.tripupapplicationbackend.models.entity.Client;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
public class ClientForm {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String tel;
    private List<String> mthdOfPay;

    public Client toEntity() {
        Client client = new Client();

        client.setUsername(username);
        client.setPassword(password);

        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setTel(tel);
        client.setMthdOfPay(mthdOfPay);

        return client;
    }

}
