package carroll.tbel.tripupapplicationbackend.models.mapper;

import carroll.tbel.tripupapplicationbackend.models.DTO.ReservationDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.Client;
import carroll.tbel.tripupapplicationbackend.models.entity.Reservation;
import carroll.tbel.tripupapplicationbackend.models.entity.User;
import carroll.tbel.tripupapplicationbackend.models.entity.Vacation;
import carroll.tbel.tripupapplicationbackend.models.form.ReservationForm;
import org.springframework.stereotype.Service;
import carroll.tbel.tripupapplicationbackend.security.services.impl.VacationServiceImpl;

import java.time.LocalDate;

@Service
public class ReservationMapper implements Mapper<Reservation, ReservationDTO, ReservationForm> {

    @Override
    public ReservationDTO entityToDTO(Reservation reservation) {
        if ( reservation == null )
            return null;

        return ReservationDTO.builder()
                .id( reservation.getId() )
                .departs( reservation.getDeparts() )
                .returns( reservation.getReturns() )
                .nmbrTravelers(reservation.getNmbrTravelers() )
                .user( toInnerDTO(reservation.getBookedBy()) )
                .vacation( toInnerDTO(reservation.getVacation()) )
                .build();
    }

    @Override
    public Reservation formToEntity(ReservationForm form) {

        if ( form == null )
            return null;

        Reservation reservation = new Reservation();
        reservation.setDeparts(LocalDate.parse(form.getDeparts()));
        reservation.setReturns(LocalDate.parse(form.getReturns()));
        reservation.setNmbrTravelers(form.getNmbrTravelers());
//        reservation.setId(form.getPackageName());
//        reservation.setClient("test client");
        return reservation;
    }

    private ReservationDTO.VacationDTO toInnerDTO(Vacation entity) {
        if ( entity == null )
            return null;

        return ReservationDTO.VacationDTO.builder()
                .packageName( entity.getPackageName() )
                .type( entity.getType() )
                .price( entity.getPrice() )
                .build();
    }

    private ReservationDTO.UserDTO toInnerDTO(User entity) {
        if ( entity == null )
            return null;

        return ReservationDTO.UserDTO.builder()
                .username(entity.getUsername() )
                .email(entity.getEmail() )
                .id(entity.getId() )
                .build();
    }
}
