package carroll.tbel.tripupapplicationbackend.models.mapper;

import carroll.tbel.tripupapplicationbackend.models.DTO.VacationDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.Vacation;
import carroll.tbel.tripupapplicationbackend.models.form.VacationForm;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class VacationMapper implements Mapper< Vacation, VacationDTO, VacationForm > {

    private final ReservationMapper reservationMapper;

    public VacationMapper(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }


    @Override
    public VacationDTO entityToDTO(Vacation vacation) {
        if( vacation == null )
            return null;

        return VacationDTO.builder()
                .packageName( vacation.getPackageName() )
                .vacationType( vacation.getType() )
                .price( vacation.getPrice() )
                .description( vacation.getDescription() )
                .picture_address( vacation.getPicture_address() )
                .hidden(vacation.isHidden())
                .reservations(
                        vacation.getReservations()
                                .stream()
                                .map( reservationMapper::entityToDTO )
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public Vacation formToEntity(VacationForm form) {
        if ( form == null)
            return null;

        Vacation vacation = new Vacation();
        vacation.setPackageName( form.getPackageName() );
        vacation.setType( form.getType() );
        vacation.setPrice( form.getPrice() );
        vacation.setPicture_address( form.getPicture_address() );
        vacation.setHidden(form.isHidden());
        vacation.setDescription(form.getDescription());
        return vacation;
    }

}
