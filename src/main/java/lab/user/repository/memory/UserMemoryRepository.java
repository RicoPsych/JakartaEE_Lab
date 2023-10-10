package lab.user.repository.memory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lab.datastorage.DataStorage;
import lab.user.entities.User;
import lab.user.repository.UserRepository;

public class UserMemoryRepository implements UserRepository {
    private final DataStorage store;

    public UserMemoryRepository(DataStorage store){
        this.store = store;
    }

    @Override
    public Optional<User> find(UUID id) {
        return store.getUsers().stream().filter(user -> user.getId().equals(id))
        .findFirst();
    }
    
    @Override
    public List<User> findAll() {
        return store.getUsers().stream().toList();
    }

    @Override
    public void create(User entity) {
        store.getUsers().add(entity);
    }

    @Override
    public void delete(User entity) {
        store.getUsers().remove(entity);
    }

    @Override
    public void update(User entity) {
        store.getUsers().removeIf(oldEntity -> oldEntity.getId().equals(entity.getId())); 
        store.getUsers().add(entity);
        
    }
    
}
