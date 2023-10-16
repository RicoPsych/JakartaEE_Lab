package lab.artist.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lab.artist.entities.Artist;
import lab.artist.repository.ArtistRepository;
import lombok.NoArgsConstructor;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Inject
    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public Optional<Artist> find(UUID id) {
        return artistRepository.find(id);
    }
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }
    public void create(Artist artist) {
        artistRepository.create(artist);
    }
    public void update(Artist artist) {
        artistRepository.update(artist);
    }
    public void delete(UUID id) {
        artistRepository.delete(artistRepository.find(id).orElseThrow());
    }
}
