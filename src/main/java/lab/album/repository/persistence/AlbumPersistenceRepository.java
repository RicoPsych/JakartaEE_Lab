package lab.album.repository.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab.album.entities.Album;
import lab.album.repository.AlbumRepository;


@RequestScoped
public class AlbumPersistenceRepository implements AlbumRepository {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }
    // @Inject
    // public AlbumPersistenceRepository(EntityManager em){
    //     this.em = em;
    // }

    @Override
    public Optional<Album> find(UUID id) {
        return Optional.ofNullable(em.find(Album.class, id));
    }

    @Override
    public List<Album> findAll() {
        return em.createQuery("select c from Album c", Album.class).getResultList();
    }

    @Override
    public void create(Album entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Album entity) {
        em.remove(em.find(Album.class, entity.getId()));
    }

    @Override
    public void update(Album entity) {
        em.merge(entity);
    }
    
}
