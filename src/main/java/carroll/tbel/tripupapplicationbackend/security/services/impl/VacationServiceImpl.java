package carroll.tbel.tripupapplicationbackend.security.services.impl;

import carroll.tbel.tripupapplicationbackend.exceptions.ElementAlreadyExistsException;
import carroll.tbel.tripupapplicationbackend.exceptions.ElementNotFoundException;
import carroll.tbel.tripupapplicationbackend.models.DTO.VacationDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.*;
import carroll.tbel.tripupapplicationbackend.models.form.VacationForm;
import carroll.tbel.tripupapplicationbackend.models.mapper.VacationMapper;
import carroll.tbel.tripupapplicationbackend.repository.RoleRepository;
import carroll.tbel.tripupapplicationbackend.repository.UserRepository;
import carroll.tbel.tripupapplicationbackend.repository.VacationRepository;
import carroll.tbel.tripupapplicationbackend.security.services.ServiceCRUD;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacationServiceImpl implements ServiceCRUD<VacationDTO, VacationForm, String> {

    private final VacationMapper vacationMapper;
    private final VacationRepository vacationRepository;
    private final UserRepository userRespository;
    private final RoleRepository roleRepository;


    public VacationServiceImpl(VacationMapper vacationMapper, VacationRepository vacationRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.vacationMapper = vacationMapper;
        this.vacationRepository = vacationRepository;
        this.userRespository = userRepository;
        this.roleRepository = roleRepository;
    }


    public VacationDTO getOne(String packageName) {
        return vacationRepository.findById(packageName)
                .map(vacationMapper::entityToDTO)
                .orElseThrow(ElementNotFoundException::new);
    }


    public List<VacationDTO> getAll() {
        return vacationRepository.findAll()
                .stream()
                .map(vacationMapper::entityToDTO)
                .collect(Collectors.toList());
    }


    public VacationDTO insert(VacationForm vacationForm) {
        vacationForm.setHidden(false);
        vacationForm.setType(VacationType.PREMADE);
        if (vacationRepository.existsById(vacationForm.getPackageName()))
            throw new ElementAlreadyExistsException();

        Vacation vacation = vacationMapper.formToEntity( vacationForm );
        vacation = vacationRepository.save(vacation);

        return vacationMapper.entityToDTO(vacation);
    }


    @Override
    public VacationDTO delete(String packageName) {
        throw new NotYetImplementedException();
    }

    public VacationDTO update(VacationForm vacationForm) {

        Vacation toUpdate = vacationRepository.findById( vacationForm.getPackageName() )
                .orElseThrow(ElementNotFoundException::new);

        toUpdate.setPackageName(vacationForm.getPackageName());

        return vacationMapper.entityToDTO( vacationRepository.save(toUpdate) );
    }

    public VacationDTO hide(String name, String packageName) {
        // get the client and set it in the reservation
        User user = this.userRespository.findByUsername( name )
                .orElseThrow(() -> new UsernameNotFoundException("username not found") );
        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        if(user.getRoles().contains(adminRole)){
            Vacation toUpdate = vacationRepository.findById( packageName )
                    .orElseThrow(ElementNotFoundException::new);

            toUpdate.setHidden(true);
            return vacationMapper.entityToDTO( vacationRepository.save(toUpdate) );
        }else{
            throw new RuntimeException("Error: Role not matching");
        }


    }

    public List<VacationDTO> findAllByHiddenIsNot() {
        return vacationRepository.findAllByHidden(false)
                .stream()
                .map(vacationMapper::entityToDTO)
                .collect(Collectors.toList());
    }
}
