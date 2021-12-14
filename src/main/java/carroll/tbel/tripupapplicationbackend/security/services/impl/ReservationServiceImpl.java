package carroll.tbel.tripupapplicationbackend.security.services.impl;

import carroll.tbel.tripupapplicationbackend.exceptions.ElementNotFoundException;
import carroll.tbel.tripupapplicationbackend.models.DTO.ReservationDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.Client;
import carroll.tbel.tripupapplicationbackend.models.entity.Reservation;
import carroll.tbel.tripupapplicationbackend.models.entity.User;
import carroll.tbel.tripupapplicationbackend.models.entity.Vacation;
import carroll.tbel.tripupapplicationbackend.models.form.ReservationForm;
import carroll.tbel.tripupapplicationbackend.models.mapper.ReservationMapper;
import carroll.tbel.tripupapplicationbackend.repository.ClientRepository;
import carroll.tbel.tripupapplicationbackend.repository.ReservationRepository;
import carroll.tbel.tripupapplicationbackend.repository.UserRepository;
import carroll.tbel.tripupapplicationbackend.repository.VacationRepository;
import carroll.tbel.tripupapplicationbackend.security.services.ReservationService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final VacationRepository vacationRepository;
    private final ClientRepository clientRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, VacationRepository vacationRepository, ClientRepository clientRepository, ReservationMapper reservationMapper, UserRepository userRepository) {
        this.vacationRepository = vacationRepository;
        this.clientRepository = clientRepository;
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<ReservationDTO> getAll() {
        return null;
    }

    @Override
    public ReservationDTO getOne(Long aLong) {
        return null;
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public ReservationDTO insert(ReservationForm reservationForm) {
        throw new NotYetImplementedException();
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
    public ReservationDTO askForReserve(String username, ReservationForm form) {
        System.out.println(form);
        Reservation toInsert = reservationMapper.formToEntity( form );

        // get the client and set it in the reservation
        User user = userRepository.findByUsername( username )
                .orElseThrow(() -> new UsernameNotFoundException("username not found") );

        // get the vacation and set it in the reservation
        Vacation vacation = vacationRepository.findById( form.getPackageName() )
                .orElseThrow(ElementNotFoundException::new);

        toInsert.setVacation( vacation );
        toInsert.setBookedBy( user );

        toInsert = reservationRepository.save(toInsert);
        return reservationMapper.entityToDTO(toInsert);
    }
}
