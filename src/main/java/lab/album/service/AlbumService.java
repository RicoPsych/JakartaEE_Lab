package lab.album.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lab.album.entities.Album;
import lab.album.repository.AlbumRepository;
import lombok.NoArgsConstructor;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class AlbumService {
    private final AlbumRepository albumRepository;

    @Inject
    public AlbumService(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    public Optional<Album> find(UUID id) {
        return albumRepository.find(id);
    }
    public List<Album> findAll() {
        return albumRepository.findAll();
    }
    @Transactional
    public void create(Album album) {
        albumRepository.create(album);
    }
    @Transactional
    public void update(Album album) {
        albumRepository.update(album);
    }
    @Transactional
    public void delete(UUID id) {
        albumRepository.delete(albumRepository.find(id).orElseThrow());
    }

}
