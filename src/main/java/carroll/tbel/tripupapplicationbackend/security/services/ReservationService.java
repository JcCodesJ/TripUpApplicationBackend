package carroll.tbel.tripupapplicationbackend.security.services;

import carroll.tbel.tripupapplicationbackend.models.DTO.ReservationDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.User;
import carroll.tbel.tripupapplicationbackend.models.form.ReservationForm;

import java.util.List;

public interface ReservationService extends ServiceCRUD<ReservationDTO, ReservationForm, Long>{

    ReservationDTO askForReserve(String username, ReservationForm form);

    List<ReservationDTO> getReservationByBookedBy_Id(String username);

}
