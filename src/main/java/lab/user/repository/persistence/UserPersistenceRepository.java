package lab.user.repository.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lab.user.entities.User;
import lab.user.entities.User_;
import lab.user.repository.UserRepository;

@Dependent
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
//        return em.createQuery("select c from User c", User.class).getResultList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        return em.createQuery(query).getResultList();
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

    @Override
    public Optional<User> findByName(String name) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root)
                    .where(cb.equal(root.get(User_.name), name)
                    );
            return Optional.of(em.createQuery(query).getSingleResult());


            // return Optional.of(em.createQuery("select u from User u where u.name = :name", User.class)
            //         .setParameter("name", name)
            //         .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
    
}
