package lab.configuration.listeners;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lab.user.entities.User;
import lab.user.entities.UserRoles;
import lab.user.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@NoArgsConstructor(force = true)
public class AdminInitializator {

    private final UserRepository userRepository;
    private final Pbkdf2PasswordHash passwordHash;

    @Inject
    public AdminInitializator(
            UserRepository userRepository,
            @SuppressWarnings("CdiInjectionPointsInspection") Pbkdf2PasswordHash passwordHash
    ) {
        this.userRepository = userRepository;
        this.passwordHash = passwordHash;
    }

    @PostConstruct
    @SneakyThrows
    private void init() {
        if (userRepository.findByName("admin-service").isEmpty()) {

            User admin = User.builder()
                    .id(UUID.fromString("d5d5b622-51c6-4e5e-9698-c2dce4fd1b07"))
                    .name("admin-service")
                    .password(passwordHash.generate("adminadmin".toCharArray()))
                    .roles(List.of(UserRoles.ADMIN, UserRoles.USER))
                    .build();

            userRepository.create(admin);
        }
    }
}
