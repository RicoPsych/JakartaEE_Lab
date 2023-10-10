package lab.album.repository.memory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lab.album.repository.AlbumRepository;
import lab.datastorage.DataStorage;

// public class AlbumMemoryRepository implements AlbumRepository {

//     /**
//      * Underlying data store. In future should be replaced with database connection.
//      */
//     private final DataStorage store;

//     public UserMemoryRepository(DataStorage store){
//         this.store = store;
//     }

//     @Override
//     public Optional<User> find(UUID id) {
//         return store.findAllUsers().stream()
//                 .filter(user -> user.getId().equals(id))
//                 .findFirst();
//     }

//     @Override
//     public List<User> findAll() {
//         return store.findAllUsers();
//     }

//     @Override
//     public void create(User entity) {
//         store.createUser(entity);
//     }

//     @Override
//     public void delete(User entity) {
//         store.deleteUser(entity.getId());
//     }

//     @Override
//     public void update(User entity) {
//         store.updateUser(entity);
//     }
    
// }
