package carroll.tbel.tripupapplicationbackend.utils;

import carroll.tbel.tripupapplicationbackend.models.entity.User;
import carroll.tbel.tripupapplicationbackend.models.entity.Vacation;
import carroll.tbel.tripupapplicationbackend.models.entity.VacationType;
import carroll.tbel.tripupapplicationbackend.repository.UserRepository;
import carroll.tbel.tripupapplicationbackend.repository.VacationRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class DatabaseFiller implements InitializingBean {

    private final UserRepository repository;
    private final VacationRepository vacationRepository;
    private final PasswordEncoder encoder;

    public DatabaseFiller(UserRepository repository, VacationRepository vacationRepository, PasswordEncoder encoder) {
        this.repository = repository;
        this.vacationRepository = vacationRepository;
        this.encoder = encoder;
    }

    public void afterPropertiesSet() throws Exception {
        saveUsers();
        saveVacations();
    }

    public void saveUsers() {
        User u = new User();
        u.setUsername("admin");
        u.setPassword( encoder.encode("adminpass") );
        u.setRoles("ADMIN");

        u = new User();
        u.setUsername("agent");
        u.setPassword( encoder.encode("agentpass") );
        u.setRoles(List.of( "AGENT" ));

        repository.save(u);
    }

    public void saveVacations() {

        Vacation v = new Vacation();
        v.setType(VacationType.PREMADE);
        v.setPackageName("Tropicality");
        v.setPrice(4000);
        vacationRepository.save(v);

        v = new Vacation();
        v.setType(VacationType.PREMADE);
        v.setPackageName("BelgianVenture");
        v.setPrice(3000);
        vacationRepository.save(v);

        v = new Vacation();
        v.setType(VacationType.PREMADE);
        v.setPackageName("SnowTopia");
        v.setPrice(5000);
        vacationRepository.save(v);
    }
}
