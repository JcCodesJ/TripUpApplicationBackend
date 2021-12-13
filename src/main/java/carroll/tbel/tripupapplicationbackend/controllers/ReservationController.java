package carroll.tbel.tripupapplicationbackend.controllers;

import carroll.tbel.tripupapplicationbackend.models.DTO.ReservationDTO;
import carroll.tbel.tripupapplicationbackend.models.DTO.VacationDTO;
import carroll.tbel.tripupapplicationbackend.models.form.ReservationForm;
import carroll.tbel.tripupapplicationbackend.models.form.VacationForm;
import carroll.tbel.tripupapplicationbackend.security.services.impl.ReservationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
public class ReservationController{

    private final ReservationServiceImpl reservationService;

    @PostMapping(path = {"", "/", "/add"})
    public ResponseEntity<ReservationDTO> insert(Authentication auth,  @Valid @RequestBody ReservationForm reservationForm) {
        String username = ((UserDetails)auth.getPrincipal()).getUsername();
        return ResponseEntity.ok(reservationService.askForReserve(username, reservationForm));
    }


    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/getares")
    public ResponseEntity<ReservationDTO> getOne(@RequestParam("reservation") long id) {
        return ResponseEntity.ok( reservationService.getOne(id) );

    }

}
