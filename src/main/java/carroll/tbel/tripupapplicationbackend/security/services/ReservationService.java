package carroll.tbel.tripupapplicationbackend.security.services;

import carroll.tbel.tripupapplicationbackend.models.DTO.ReservationDTO;
import carroll.tbel.tripupapplicationbackend.models.form.ReservationForm;

public interface ReservationService extends ServiceCRUD<ReservationDTO, ReservationForm, Long>{

    ReservationDTO askForReserve(String username, ReservationForm form);

}
