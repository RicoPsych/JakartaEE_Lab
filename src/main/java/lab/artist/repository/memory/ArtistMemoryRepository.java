package lab.artist.repository.memory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lab.artist.entities.Artist;
import lab.artist.repository.ArtistRepository;
import lab.datastorage.DataStorage;

// public class ArtistMemoryRepository implements ArtistRepository {

//     /**
//      * Underlying data store. In future should be replaced with database connection.
//      */
//     private final DataStorage store;

//     public ArtistMemoryRepository(DataStorage store){
//         this.store = store;
//     }

//     @Override
//     public Optional<Artist> find(UUID id) {
//         return store.findAllAuthors().stream()
//                 .filter(user -> user.getId().equals(id))
//                 .findFirst();
//     }

//     @Override
//     public List<Artist> findAll() {
//         return store.findAllAuthors();
//     }

//     @Override
//     public void create(Artist entity) {
//         store.createAuthor(entity);
//     }

//     @Override
//     public void delete(Artist entity) {
//         store.deleteAuthor(entity.getId());
//     }

//     @Override
//     public void update(Artist entity) {
//         store.updateAuthor(entity);
//     }
    
// }
