package carroll.tbel.tripupapplicationbackend.security.services.impl;

import carroll.tbel.tripupapplicationbackend.exceptions.ElementAlreadyExistsException;
import carroll.tbel.tripupapplicationbackend.exceptions.ElementNotFoundException;
import carroll.tbel.tripupapplicationbackend.models.DTO.VacationDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.Vacation;
import carroll.tbel.tripupapplicationbackend.models.form.VacationForm;
import carroll.tbel.tripupapplicationbackend.models.mapper.VacationMapper;
import carroll.tbel.tripupapplicationbackend.repository.VacationRepository;
import carroll.tbel.tripupapplicationbackend.security.services.ServiceCRUD;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacationServiceImpl implements ServiceCRUD<VacationDTO, VacationForm, String> {

    private final VacationMapper vacationMapper;
    private final VacationRepository vacationRepository;


    public VacationServiceImpl(VacationMapper vacationMapper, VacationRepository vacationRepository) {
        this.vacationMapper = vacationMapper;
        this.vacationRepository = vacationRepository;
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
}
