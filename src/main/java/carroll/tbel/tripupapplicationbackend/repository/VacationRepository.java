package carroll.tbel.tripupapplicationbackend.repository;

import carroll.tbel.tripupapplicationbackend.models.entity.Reservation;
import carroll.tbel.tripupapplicationbackend.models.entity.User;
import carroll.tbel.tripupapplicationbackend.models.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, String> {
    List<Vacation> findAllByHidden(boolean hidden);
}
