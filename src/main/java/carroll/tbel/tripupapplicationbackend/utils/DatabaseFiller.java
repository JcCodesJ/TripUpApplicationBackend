package carroll.tbel.tripupapplicationbackend.utils;

import carroll.tbel.tripupapplicationbackend.models.entity.*;
import carroll.tbel.tripupapplicationbackend.repository.RoleRepository;
import carroll.tbel.tripupapplicationbackend.repository.UserRepository;
import carroll.tbel.tripupapplicationbackend.repository.VacationRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DatabaseFiller implements InitializingBean {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VacationRepository vacationRepository;
    private final PasswordEncoder encoder;

    public DatabaseFiller(UserRepository userRepository, RoleRepository roleRepository, VacationRepository vacationRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.vacationRepository = vacationRepository;
        this.encoder = encoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //saveUsers();
        saveVacations();
    }

    public void saveUsers() {

        Role role = new Role();
        role.setName(ERole.ROLE_ADMIN);
        roleRepository.save(role);

        User u = new User();
        u.setUsername("Admin");
        u.setPassword( encoder.encode("adminpass") );
        u.setRoles(Set.of(role));

        role = new Role();
        role.setName(ERole.ROLE_AGENT);
        roleRepository.save(role);

        u = new User();
        u.setUsername("Agent");
        u.setPassword( encoder.encode("agentpass") );
        u.setRoles(Set.of(role));

        userRepository.save(u);
    }

    public void saveVacations() {

        Vacation v = new Vacation();
        v.setType(VacationType.PREMADE);
        v.setPackageName("Tropicality");
        v.setPrice(4000);
        v.setDescription("A Tropicality and relaxing island paradise A beautiful and relaxing island paradise\n" +
                "Stay in an amazing ocean suite situated just above the water\n" +
                "Go on a snorkeling adventure and see the local sea-life");
        vacationRepository.save(v);

        v = new Vacation();
        v.setType(VacationType.PREMADE);
        v.setPackageName("BelgianVenture");
        v.setPrice(3000);
        v.setDescription("A BelgianVenture and relaxing island paradise ");
        vacationRepository.save(v);

        v = new Vacation();
        v.setType(VacationType.PREMADE);
        v.setPackageName("SnowTopia");
        v.setPrice(5000);
        v.setDescription("A SnowTopia and relaxing island paradise ");
        vacationRepository.save(v);
    }
}
