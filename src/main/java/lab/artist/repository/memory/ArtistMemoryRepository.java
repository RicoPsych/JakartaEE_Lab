package lab.artist.repository.memory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lab.artist.entities.Artist;
import lab.artist.repository.ArtistRepository;
import lab.datastorage.DataStorage;

@RequestScoped
public class ArtistMemoryRepository implements ArtistRepository {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private final DataStorage store;

    @Inject
    public ArtistMemoryRepository(DataStorage store){
        this.store = store;
    }

    @Override
    public Optional<Artist> find(UUID id) {
        
        return store.getArtists().stream().filter(user -> user.getId().equals(id))
        .findFirst();
    }

    @Override
    public List<Artist> findAll() {
        return store.getArtists().stream().toList();
    }

    @Override
    public void create(Artist entity) {
        store.getArtists().add(entity);
    }

    @Override
    public void delete(Artist entity) {
        store.getArtists().remove(entity);
    }

    @Override
    public void update(Artist entity) {
        store.getArtists().removeIf(oldEntity -> oldEntity.getId().equals(entity.getId())); 
        store.getArtists().add(entity);
    }
    
    
}
