package carroll.tbel.tripupapplicationbackend.controllers;

import carroll.tbel.tripupapplicationbackend.models.DTO.ReservationDTO;
import carroll.tbel.tripupapplicationbackend.models.form.ReservationRequestForm;
import carroll.tbel.tripupapplicationbackend.security.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/reserve")
public class ReservationController{
    public ReservationController(ReservationService service) {
        super(service);
    }

    @PostMapping("/request")
    public ResponseEntity<ReservationDTO> askForReserve(Authentication auth, @Valid @RequestBody ReservationRequestForm form) {
        return ResponseEntity.ok( ((ReservationService)service).askForReserve( (String)auth.getPrincipal(), form));
    }
}
