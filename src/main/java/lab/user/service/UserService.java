package lab.user.service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lab.filesystemaccess.FileSystemAccess;
import lab.user.entities.User;
import lab.user.entities.UserRoles;
import lab.user.repository.UserRepository;
import lombok.NoArgsConstructor;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class UserService {
    private final UserRepository userRepository;
    private final Pbkdf2PasswordHash passwordHash;
    private final SecurityContext securityContext;
    // private final SongRepository songRepository;
    private final FileSystemAccess fileSystem;
    

    @Inject
    public UserService(UserRepository userRepository,
                    FileSystemAccess fileSystem,
                    @SuppressWarnings("CdiInjectionPointsInspection") Pbkdf2PasswordHash passwordHash,
                    @SuppressWarnings("CdiInjectionPointsInspection") SecurityContext securityContext
                    //, SongRepository songRepository
                        ){
        this.userRepository = userRepository;
        this.fileSystem = fileSystem;
        this.passwordHash = passwordHash;
        this.securityContext = securityContext;
        // this.songRepository = songRepository;
    }

    @RolesAllowed(UserRoles.ADMIN)
    public Optional<User> find(UUID id) {
        checkAdminRole();
        return userRepository.find(id);
    }
    @RolesAllowed(UserRoles.ADMIN)
    public Optional<User> find(String name) {
        checkAdminRole();
        return userRepository.findByName(name);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public List<User> findAll() {
        checkAdminRole();
        return userRepository.findAll();
    }

    @PermitAll
    public void create(User user) {
        user.setPassword(passwordHash.generate(user.getPassword().toCharArray()));
        userRepository.create(user);
    }

    @PermitAll
    public void update(User user) {
        user.setPassword(passwordHash.generate(user.getPassword().toCharArray()));
        userRepository.update(user);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void delete(UUID id) {
        fileSystem.deleteAvatar(id);
        userRepository.delete(userRepository.find(id).orElseThrow());
    }

    @PermitAll
    public Optional<byte[]> getAvatar(UUID id){
        return fileSystem.getAvatar(id);
    }

    @RolesAllowed(UserRoles.ADMIN)
    //@Transactional 
    public void updateAvatar(UUID id, InputStream is) {
        userRepository.find(id).ifPresent(user -> {
            //try {
                fileSystem.writeAvatar(id, is);
//                user.setAvatar(is.readAllBytes());
              //  userRepository.update(user);
            //} catch (IOException ex) {
             //   throw new IllegalStateException(ex);
           // }
        });
    }

    @RolesAllowed(UserRoles.ADMIN)
    //@Transactional
    public void updateAvatar(UUID id, byte[] is) {
        userRepository.find(id).ifPresent(user -> {fileSystem.writeAvatar(id, is); });
    }
    
    @RolesAllowed(UserRoles.ADMIN)
    public void deleteAvatar(UUID id) {
        userRepository.find(id).ifPresent(user -> {
        fileSystem.deleteAvatar(id);

        });
    }

    @PermitAll
    public boolean verify(String name, String password) {
        return find(name)
                .map(user -> passwordHash.verify(password.toCharArray(), user.getPassword()))
                .orElse(false);
    }

    private void checkAdminRole() throws SecurityException {
        if (!securityContext.isCallerInRole(UserRoles.ADMIN)) {
            throw new SecurityException("Caller not authorized.");
        }
    }

}
