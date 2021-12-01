package carroll.tbel.tripupapplicationbackend.security.services;

import carroll.tbel.tripupapplicationbackend.repository.UserRepository;
import carroll.tbel.tripupapplicationbackend.security.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public SessionService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }
}
