package lab.user.repository.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab.datastorage.DataStorage;
import lab.user.entities.User;
import lab.user.repository.UserRepository;

@RequestScoped
public class UserPersistenceRepository implements UserRepository {
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<User> find(UUID id) {
        return Optional.ofNullable(em.find(User.class, id));
    }
    
    @Override
    public List<User> findAll() {
        return em.createQuery("select c from User c", User.class).getResultList();
    }

    @Override
    public void create(User entity) {
        em.persist(entity);
    }

    @Override
    public void delete(User entity) {
        em.remove(em.find(User.class, entity.getId()));
    }

    @Override
    public void update(User entity) {
        em.merge(entity);
    }
    
}
