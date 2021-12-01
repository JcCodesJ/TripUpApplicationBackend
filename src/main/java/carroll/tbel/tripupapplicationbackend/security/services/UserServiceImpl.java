package carroll.tbel.tripupapplicationbackend.security.services;

import carroll.tbel.tripupapplicationbackend.exceptions.ElementAlreadyExistsException;
import carroll.tbel.tripupapplicationbackend.exceptions.ElementNotFoundException;
import carroll.tbel.tripupapplicationbackend.models.DTO.UserDTO;
import carroll.tbel.tripupapplicationbackend.models.entity.User;
import carroll.tbel.tripupapplicationbackend.models.form.UserForm;
import carroll.tbel.tripupapplicationbackend.models.mapper.UserMapper;
import carroll.tbel.tripupapplicationbackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::entityToDTO)
                .collect(Collectors.toList())
                ;
    }

    @Override
    public UserDTO getOne(Long aLong) throws UsernameNotFoundException {
        User user = userRepository.findById(aLong)
                .orElseThrow( () -> new UsernameNotFoundException("The id with the username given doesn't exist") );
        return userMapper.entityToDTO(user);
    }

    @Override
    public UserDTO insert(UserForm userForm) {
        if (userRepository.existsById(userForm.getId()))
            throw new ElementAlreadyExistsException();

        User toInsert = userMapper.formToEntity(userForm);
        toInsert = userRepository.save(toInsert);

        return userMapper.entityToDTO(toInsert);
    }

    @Override
    public UserDTO delete(Long aLong) {
        if (!userRepository.existsById(aLong))
            throw new ElementNotFoundException();

        User toDelete = userRepository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        userRepository.delete(toDelete);
        return userMapper.entityToDTO(toDelete);
    }

    @Override
    public UserDTO update(UserForm userForm) {
        if (!userRepository.existsById(userForm.getId()))
            throw new ElementNotFoundException();

        User toUpdate = userMapper.formToEntity(userForm);

        toUpdate = userRepository.save(toUpdate);

        return userMapper.entityToDTO(toUpdate);
    }
}
