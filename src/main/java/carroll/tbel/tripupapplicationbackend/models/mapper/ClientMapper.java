package carroll.tbel.tripupapplicationbackend.models.mapper;

import carroll.tbel.tripupapplicationbackend.models.DTO.ClientDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.Client;
import carroll.tbel.tripupapplicationbackend.models.form.ClientForm;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ClientMapper implements Mapper<Client, ClientDTO, ClientForm > {

    private final ReservationMapper reservationMapper;

    public ClientMapper(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }
    @Override
    public ClientDTO toDto(Client entity) {

        if ( entity == null )
            return null;

        return ClientDTO.builder()
                .id( entity.getId() )
                .username(entity.getUsername() )
                .firstName( entity.getFirstName() )
                .lastName( entity.getLastName() )
                .mthdOfPay( entity.getMthdOfPay() )
                .tel( entity.getTel() )
                .reservations(
                        entity.getReservations()
                                .stream()
                                .map(reservationMapper::toDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public Client formToEntity(ClientForm form) {
        return form.toEntity();
    }

}
