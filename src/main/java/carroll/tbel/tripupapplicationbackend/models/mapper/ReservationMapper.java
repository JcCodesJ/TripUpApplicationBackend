package carroll.tbel.tripupapplicationbackend.models.mapper;

import carroll.tbel.tripupapplicationbackend.models.DTO.ReservationDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.Client;
import carroll.tbel.tripupapplicationbackend.models.entity.Reservation;
import carroll.tbel.tripupapplicationbackend.models.entity.Vacation;
import carroll.tbel.tripupapplicationbackend.models.form.ReservationForm;
import org.springframework.stereotype.Service;

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
                .client( toInnerDTO(reservation.getBookedBy()) )
                .vacation( toInnerDTO(reservation.getVacation()) )
                .build();
    }

    @Override
    public Reservation formToEntity(ReservationForm form) {

        if ( form == null )
            return null;

        Reservation reservation = new Reservation();
        reservation.setDeparts(form.getDeparts());
        reservation.setReturns(form.getReturns());
        reservation.setNmbrTravelers(form.getNmbrTravelers());

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

    private ReservationDTO.ClientDTO toInnerDTO(Client entity) {
        if ( entity == null )
            return null;

        return ReservationDTO.ClientDTO.builder()
                .username(entity.getUsername() )
                .firstName(entity.getFirstName() )
                .lastName(entity.getLastName() )
                .tel(entity.getTel() )
                .mthdOfPay( entity.getMthdOfPay() )
                .build();
    }
}
