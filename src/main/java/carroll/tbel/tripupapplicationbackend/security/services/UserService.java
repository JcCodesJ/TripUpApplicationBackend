package carroll.tbel.tripupapplicationbackend.security.services;

import carroll.tbel.tripupapplicationbackend.models.DTO.UserDTO;
import carroll.tbel.tripupapplicationbackend.models.form.UserForm;

import java.util.List;

public interface UserService extends ServiceCRUD <UserDTO, UserForm, Long>{

    @Override
    List<UserDTO> getAll();

    @Override
    UserDTO getOne(Long aLong);

    @Override
    UserDTO insert(UserForm userForm);

    @Override
    UserDTO delete(Long aLong);

    @Override
    UserDTO update(UserForm userForm);
}
