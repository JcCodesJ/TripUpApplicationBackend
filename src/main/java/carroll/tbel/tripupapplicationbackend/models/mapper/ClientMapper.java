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
    public ClientDTO entityToDTO(Client client) {

        if ( client == null )
            return null;

        return ClientDTO.builder()
                .id( client.getId() )
                .username(client.getUsername() )
                .firstName( client.getFirstName() )
                .lastName( client.getLastName() )
                .mthdOfPay( client.getMthdOfPay() )
                .tel( client.getTel() )
                .reservations(
                        client.getReservations()
                                .stream()
                                .map(reservationMapper::entityToDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public Client formToEntity(ClientForm form) {
        return form.toEntity();
    }

}
