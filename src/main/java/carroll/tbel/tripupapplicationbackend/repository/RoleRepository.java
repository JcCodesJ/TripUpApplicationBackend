package carroll.tbel.tripupapplicationbackend.repository;

import carroll.tbel.tripupapplicationbackend.models.ERole;
import carroll.tbel.tripupapplicationbackend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
