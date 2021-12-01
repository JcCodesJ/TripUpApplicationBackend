package carroll.tbel.tripupapplicationbackend.models.mapper;


import carroll.tbel.tripupapplicationbackend.models.DTO.UserDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.User;
import carroll.tbel.tripupapplicationbackend.models.form.UserForm;

public class UserMapper implements Mapper <User, UserDTO, UserForm> {


    @Override
    public UserDTO entityToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
    }

    @Override
    public User formToEntity(UserForm userForm) {
        return User.builder()
                .id(userForm.getId())
                .password(userForm.getPassword())
                .username(userForm.getUsername())
                .email(userForm.getEmail())
                .build();
    }
}
