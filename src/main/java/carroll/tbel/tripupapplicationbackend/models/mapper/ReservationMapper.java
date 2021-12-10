package carroll.tbel.tripupapplicationbackend.models.mapper;

import carroll.tbel.tripupapplicationbackend.models.DTO.ReservationDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.Client;
import carroll.tbel.tripupapplicationbackend.models.entity.Reservation;
import carroll.tbel.tripupapplicationbackend.models.entity.Vacation;
import carroll.tbel.tripupapplicationbackend.models.form.ReservationForm;
import org.springframework.stereotype.Service;

@Service
public class ReservationMapper implements Mapper<ReservationDTO, ReservationForm, Reservation> {

    @Override
    public ReservationDTO toDto(Reservation entity) {
        if ( entity == null )
            return null;

        return ReservationDTO.builder()
                .id( entity.getId() )
                .departs( entity.getDeparts() )
                .returns( entity.getReturns() )
                .nmbrTravelers(entity.getNmbrTravelers() )
                .client( toInnerDTO(entity.getBookedBy()) )
                .vacation( toInnerDTO(entity.getVacation()) )
                .build();
    }

    public Reservation formToEntity(ReservationForm form) {

        if ( form == null )
            return null;

        Reservation reservation = new Reservation();
        reservation.setDeparts(form.getDeparts());
        reservation.setReturns(form.getReturns());
        reservation.setNmbrTravelers(form.getNmbrTravelers);

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
