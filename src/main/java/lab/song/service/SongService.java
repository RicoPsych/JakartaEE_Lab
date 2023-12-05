package lab.song.service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.swing.text.html.Option;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJBAccessException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.transaction.Transactional;
import lab.album.entities.Album;
import lab.album.repository.AlbumRepository;
import lab.song.entities.Song;
import lab.song.observer.event.SongEvent;
import lab.song.repository.SongRepository;
import lab.user.entities.User;
import lab.user.entities.UserRoles;
import lab.user.repository.UserRepository;
import lombok.NoArgsConstructor;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final SecurityContext securityContext;

    @Inject
    public SongService(SongRepository songRepository,  AlbumRepository albumRepository,
    UserRepository userRepository,
    @SuppressWarnings("CdiInjectionPointsInspection") SecurityContext securityContext){
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.securityContext = securityContext;
        this.userRepository = userRepository;
    }

    @RolesAllowed(UserRoles.ADMIN)
    public Optional<List<Song>> findByAlbum(UUID id){
        return albumRepository.find(id).map(songRepository::findByAlbum);
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<List<Song>> findByAlbum(User user, UUID id){

        return albumRepository.find(id).map(album -> songRepository.findByAlbumAndUser( album , user));
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<List<Song>> findByAlbumForCallerPrincipal(UUID id) {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return findByAlbum(id);
        }
        User user = userRepository.findByName(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);
        return findByAlbum(user, id);
    }
    
    @RolesAllowed(UserRoles.USER)
    public Optional<Song> find(UUID id) {
        return songRepository.find(id);
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Song> find(User user, UUID id) {
        return songRepository.findByIdAndUser(id, user);
    }


    @RolesAllowed(UserRoles.USER)
    public Optional<Song> findForCallerPrincipal(UUID id) {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return find(id);
        }
        User user = userRepository.findByName(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);
        return find(user, id);
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<List<Song>> findAllByUser(UUID id) {
        return userRepository.find(id).map(songRepository::findByUser);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public List<Song> findAll() {
        return songRepository.findAll();
    }
    @RolesAllowed(UserRoles.USER)
    public List<Song> findAll(User user) {
        return songRepository.findByUser(user);
    }
    @RolesAllowed(UserRoles.USER)
    public List<Song> findAllForCallerPrincipal() {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return findAll();
        }
        User user = userRepository.findByName(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);
        return findAll(user);
    }


    @RolesAllowed(UserRoles.ADMIN)
    public void create(Song song) {
        songRepository.create(song);
    }
    
    @RolesAllowed(UserRoles.USER)
    @SongEvent
    public void createForCallerPrincipal(Song song) {
        User user = userRepository.findByName(securityContext.getCallerPrincipal().getName())
                .orElseThrow(IllegalStateException::new);

        song.setOwner(user);
        create(song);
    }

    @RolesAllowed(UserRoles.USER)
    @SongEvent
    public void updateForCallerPrincipal(Song song) {
        checkAdminRoleOrOwner(songRepository.find(song.getId()));
        User user = userRepository.findByName(securityContext.getCallerPrincipal().getName())
        .orElseThrow(IllegalStateException::new);

        song.setOwner(user);
        songRepository.update(song);
    }

    @RolesAllowed(UserRoles.USER)
    @SongEvent
    public void delete(UUID id) {
        checkAdminRoleOrOwner(songRepository.find(id));
        songRepository.delete(songRepository.find(id).orElseThrow());
    }

    @RolesAllowed(UserRoles.ADMIN)
    public List<Song> findByFilter(UUID id, User user, String name, Float rating, Album album,
    Integer duration, Long version, LocalDateTime updateDateTime, LocalDateTime creationDateTime) {
        return songRepository.findByFilter(id, user, name, rating, album, duration, version, updateDateTime, creationDateTime);
    }

    private void checkAdminRoleOrOwner(Optional<Song> song) throws EJBAccessException {
        if (securityContext.isCallerInRole(UserRoles.ADMIN)) {
            return;
        }
        if (securityContext.isCallerInRole(UserRoles.USER)
                && song.isPresent()
                && song.get().getOwner().getName().equals(securityContext.getCallerPrincipal().getName())) {
            return;
        }
        throw new EJBAccessException("Caller not authorized.");
    }


}
