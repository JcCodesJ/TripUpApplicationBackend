package carroll.tbel.tripupapplicationbackend.controllers;

import carroll.tbel.tripupapplicationbackend.models.DTO.VacationDTO;
import carroll.tbel.tripupapplicationbackend.models.form.VacationForm;
import carroll.tbel.tripupapplicationbackend.security.services.impl.VacationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vacation")
public class VacationController {

    private final VacationServiceImpl vacationService;


    public VacationController(VacationServiceImpl vacationService) {
        this.vacationService = vacationService;
    }

    @PostMapping(path = {"", "/", "/add"})
    public ResponseEntity<VacationDTO> insert(@Valid @RequestBody VacationForm vacationForm) {
        return ResponseEntity.ok( vacationService.insert(vacationForm) );
    }


    @GetMapping("/getavaca")
    public ResponseEntity<VacationDTO> getOne(@RequestParam("package") String packageName) {
        return ResponseEntity.ok( vacationService.getOne(packageName) );
    }

    @GetMapping("getvacas")
    public ResponseEntity<List<VacationDTO>> getAll() {
        //ResponseEntity<List<VacationDTO>> a = ResponseEntity.ok( vacationService.getAll() );
        return ResponseEntity.ok( vacationService.getAll() );
    }

    @PutMapping("/upvac")
    public ResponseEntity<VacationDTO> update(@Valid @RequestParam("package") @RequestBody VacationForm vacationForm) {
        return ResponseEntity.ok( vacationService.update(vacationForm) );
    }

}
