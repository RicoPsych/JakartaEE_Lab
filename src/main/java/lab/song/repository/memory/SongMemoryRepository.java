package lab.song.repository.memory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lab.datastorage.DataStorage;
import lab.song.entities.Song;
import lab.song.repository.SongRepository;

// public class SongMemoryRepository implements SongRepository {

//     /**
//      * Underlying data store. In future should be replaced with database connection.
//      */
//     private final DataStorage store;

//     public SongMemoryRepository(DataStorage store){
//         this.store = store;
//     }

//     @Override
//     public Optional<Song> find(UUID id) {
//         return store.findAllSongs().stream()
//                 .filter(user -> user.getId().equals(id))
//                 .findFirst();
//     }

//     @Override
//     public List<Song> findAll() {
//         return store.findAllSongs();
//     }

//     @Override
//     public void create(Song entity) {
//         store.createSong(entity);
//     }

//     @Override
//     public void delete(Song entity) {
//         store.deleteSong(entity.getId());
//     }

//     @Override
//     public void update(Song entity) {
//         store.updateSong(entity);
//     }
    
// }
