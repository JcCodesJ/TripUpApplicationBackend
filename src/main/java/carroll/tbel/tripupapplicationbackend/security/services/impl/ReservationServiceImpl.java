package carroll.tbel.tripupapplicationbackend.security.services.impl;

import carroll.tbel.tripupapplicationbackend.models.DTO.ReservationDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.Reservation;
import carroll.tbel.tripupapplicationbackend.models.form.ReservationForm;
import carroll.tbel.tripupapplicationbackend.models.form.ReservationRequestForm;
import carroll.tbel.tripupapplicationbackend.models.mapper.ReservationMapper;
import carroll.tbel.tripupapplicationbackend.repository.ClientRepository;
import carroll.tbel.tripupapplicationbackend.repository.ReservationRepository;
import carroll.tbel.tripupapplicationbackend.repository.VacationRepository;
import carroll.tbel.tripupapplicationbackend.security.services.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final VacationRepository vacationRepository;
    private final ClientRepository clientRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, VacationRepository vacationRepository, ClientRepository clientRepository, ReservationMapper reservationMapper) {
        this.vacationRepository = vacationRepository;
        this.clientRepository = clientRepository;
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
    }


    @Override
    public List<ReservationDTO> getAll() {
        return null;
    }

    @Override
    public ReservationDTO getOne(Long aLong) {
        return null;
    }

    public ReservationDTO insert(ReservationForm reservationForm) {

        System.out.println(reservationForm);
        Reservation toInsert = reservationMapper.formToEntity( reservationForm );
        toInsert = reservationRepository.save(toInsert);
        return reservationMapper.entityToDTO(toInsert);

    }

    @Override
    public ReservationDTO delete(Long aLong) {
        return null;
    }

    @Override
    public ReservationDTO update(ReservationForm form) {
        return null;
    }

    @Override
    public ReservationDTO askForReserve(String username, ReservationRequestForm form) {
        return null;
    }
}
