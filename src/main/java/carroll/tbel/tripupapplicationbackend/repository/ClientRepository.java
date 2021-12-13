package carroll.tbel.tripupapplicationbackend.repository;

import carroll.tbel.tripupapplicationbackend.models.entity.Client;
import carroll.tbel.tripupapplicationbackend.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByUsername(String username);

}
