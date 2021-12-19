package carroll.tbel.tripupapplicationbackend.security.services.impl;

import carroll.tbel.tripupapplicationbackend.exceptions.ElementNotFoundException;
import carroll.tbel.tripupapplicationbackend.models.DTO.ReservationDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.*;
import carroll.tbel.tripupapplicationbackend.models.form.ReservationForm;
import carroll.tbel.tripupapplicationbackend.models.mapper.ReservationMapper;
import carroll.tbel.tripupapplicationbackend.repository.ReservationRepository;
import carroll.tbel.tripupapplicationbackend.repository.RoleRepository;
import carroll.tbel.tripupapplicationbackend.repository.UserRepository;
import carroll.tbel.tripupapplicationbackend.repository.VacationRepository;
import carroll.tbel.tripupapplicationbackend.security.services.ReservationService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final VacationRepository vacationRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, VacationRepository vacationRepository, ReservationMapper reservationMapper, UserRepository userRepository,RoleRepository roleRepository) {
        this.vacationRepository = vacationRepository;
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public List<ReservationDTO> getAll(String username) {
        // get the client and set it in the reservation
        User user = userRepository.findByUsername( username )
                .orElseThrow(() -> new UsernameNotFoundException("username not found") );
        Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        if(user.getRoles().contains(agentRole)){
            return reservationRepository.findAll()
                    .stream()
                    .map(reservationMapper::entityToDTO)
                    .collect(Collectors.toList());
        }else{
            throw new RuntimeException("Error: Role not matching");
        }

    }


    @Override
    public List<ReservationDTO> getAll() {
        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO getOne(Long aLong) {
        return null;
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
        Reservation toInsert = reservationMapper.formToEntity( form );

        // get the client and set it in the reservation
        User user = userRepository.findByUsername( username )
                .orElseThrow(() -> new UsernameNotFoundException("username not found") );

        // get the vacation and set it in the reservation
        Vacation vacation = vacationRepository.findById( form.getPackageName() )
                .orElseThrow(ElementNotFoundException::new);
        toInsert.setVacation( vacation );
        toInsert.setBookedBy( user );
        double days = Duration.between(toInsert.getDeparts().atStartOfDay(), toInsert.getReturns().atStartOfDay()).toDays();
        toInsert.setPrice( vacation.getPrice() * toInsert.getNmbrTravelers() * days);

        toInsert = reservationRepository.save(toInsert);
        return reservationMapper.entityToDTO(toInsert);
    }


    @Override
    public List<ReservationDTO> getReservationByBookedBy_Id(String username) {
        // get the user
        User user = userRepository.findByUsername( username )
                .orElseThrow(() -> new UsernameNotFoundException("username not found") );

        return reservationRepository.findAllByBookedBy(user)
                .stream()
                .map(reservationMapper::entityToDTO)
                .collect(Collectors.toList());
    }

}
