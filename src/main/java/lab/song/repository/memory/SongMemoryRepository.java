// package lab.song.repository.memory;

// import java.util.List;
// import java.util.Optional;
// import java.util.UUID;
// import java.util.stream.Collector;
// import java.util.stream.Collectors;

// import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.enterprise.context.RequestScoped;
// import jakarta.inject.Inject;
// import lab.album.entities.Album;
// import lab.datastorage.DataStorage;
// import lab.song.entities.Song;
// import lab.song.repository.SongRepository;
// import lab.user.entities.User;

// @ApplicationScoped
// public class SongMemoryRepository implements SongRepository {

//     /**
//      * Underlying data store. In future should be replaced with database connection.
//      */
//     private final DataStorage store;

//     @Inject
//     public SongMemoryRepository(DataStorage store){
//         this.store = store;
//     }

//     @Override
//     public Optional<Song> find(UUID id) {
        
//         return store.getSongs().stream().filter(entity -> entity.getId().equals(id))
//         .findFirst();
//     }

//     @Override  
//     public List<Song> findByAlbum(Album album) {
//         return store.getSongs().stream().filter(entity -> album.equals(entity.getAlbum()))
//         .collect(Collectors.toList());
//     }

//     @Override  
//     public List<Song> findByUser(User user) {
//         return store.getSongs().stream().filter(entity -> user.equals(entity.getOwner()))
//         .collect(Collectors.toList());
//     }


//     @Override
//     public List<Song> findAll() {
//         return store.getSongs().stream().toList();
//     }

//     @Override
//     public void create(Song entity) {
//         store.getSongs().add(entity);
//     }

//     @Override
//     public void delete(Song entity) {
//         store.getSongs().remove(entity);
//     }

//     @Override
//     public void update(Song entity) {
//         store.getSongs().removeIf(oldEntity -> oldEntity.getId().equals(entity.getId())); 
//         store.getSongs().add(entity);
//     }
    
// }
