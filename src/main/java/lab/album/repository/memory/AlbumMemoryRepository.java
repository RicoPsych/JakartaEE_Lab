package lab.album.repository.memory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lab.album.entities.Album;
import lab.album.repository.AlbumRepository;
import lab.datastorage.DataStorage;


@ApplicationScoped
public class AlbumMemoryRepository implements AlbumRepository {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private final DataStorage store;
    
    @Inject
    public AlbumMemoryRepository(DataStorage store){
        this.store = store;
    }

    @Override
    public Optional<Album> find(UUID id) {
        
        return store.getAlbums().stream().filter(user -> user.getId().equals(id))
        .findFirst();
    }

    @Override
    public List<Album> findAll() {
        return store.getAlbums().stream().toList();
    }

    @Override
    public void create(Album entity) {
        store.getAlbums().add(entity);
    }

    @Override
    public void delete(Album entity) {
        store.getAlbums().remove(entity);
    }

    @Override
    public void update(Album entity) {
        store.getAlbums().removeIf(oldEntity -> oldEntity.getId().equals(entity.getId())); 
        store.getAlbums().add(entity);
    }
    
}
