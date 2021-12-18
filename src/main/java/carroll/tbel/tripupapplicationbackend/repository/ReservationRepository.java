package carroll.tbel.tripupapplicationbackend.repository;

import carroll.tbel.tripupapplicationbackend.models.DTO.ReservationDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.Reservation;
import carroll.tbel.tripupapplicationbackend.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByBookedBy(User bookedBy);
}
