package lab.user.repository.memory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lab.datastorage.DataStorage;
import lab.user.entities.User;
import lab.user.repository.UserRepository;

@ApplicationScoped
public class UserMemoryRepository implements UserRepository {
    private final DataStorage store;

    @Inject
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
