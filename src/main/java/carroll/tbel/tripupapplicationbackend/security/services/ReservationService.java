package carroll.tbel.tripupapplicationbackend.security.services;

import carroll.tbel.tripupapplicationbackend.models.DTO.ReservationDTO;
import carroll.tbel.tripupapplicationbackend.models.form.ReservationForm;
import carroll.tbel.tripupapplicationbackend.models.form.ReservationRequestForm;

public interface ReservationService extends ServiceCRUD<ReservationDTO, ReservationForm, Long>{

    ReservationDTO askForReserve(String username, ReservationRequestForm form);

}
