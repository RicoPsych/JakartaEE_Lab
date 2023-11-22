package lab.album.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lab.album.entities.Album;
import lab.album.repository.AlbumRepository;
import lab.user.entities.UserRoles;
import lombok.NoArgsConstructor;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class AlbumService {
    private final AlbumRepository albumRepository;

    @Inject
    public AlbumService(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }
    @PermitAll
    public Optional<Album> find(UUID id) {
        return albumRepository.find(id);
    }

    @PermitAll
    public List<Album> findAll() {
        return albumRepository.findAll();
    }
    //@Transactional
    @RolesAllowed(UserRoles.ADMIN)
    public void create(Album album) {
        albumRepository.create(album);
    }
    //@Transactional
    @RolesAllowed(UserRoles.ADMIN)
    public void update(Album album) {
        albumRepository.update(album);
    }
    //@Transactional
    @RolesAllowed(UserRoles.ADMIN)
    public void delete(UUID id) {
        albumRepository.delete(albumRepository.find(id).orElseThrow());
    }

}
