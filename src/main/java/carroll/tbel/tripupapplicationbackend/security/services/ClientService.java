package carroll.tbel.tripupapplicationbackend.security.services;

import carroll.tbel.tripupapplicationbackend.exceptions.ElementAlreadyExistsException;
import carroll.tbel.tripupapplicationbackend.models.entity.Client;
import carroll.tbel.tripupapplicationbackend.payload.request.SignupRequest;
import carroll.tbel.tripupapplicationbackend.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

// Create new client's account. Save to clientRepository. Make signup method.

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/signup")
    public void registerClient(@Valid @RequestBody SignupRequest signUpRequest) {
        if (clientRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new ElementAlreadyExistsException("Error: Username is already taken!");
        }

        if (clientRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ElementAlreadyExistsException("Error: Email is already in use!");
        }

        Client client = new Client();
        client.setUsername( signUpRequest.getUsername() );
        client.setPassword( passwordEncoder.encode(signUpRequest.getPassword()) );
        //client.setTel( signUpRequest.get);




        clientRepository.save(client);

    }
}
