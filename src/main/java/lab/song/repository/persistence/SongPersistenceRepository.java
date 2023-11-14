package lab.song.repository.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab.album.entities.Album;
import lab.song.entities.Song;
import lab.song.repository.SongRepository;
import lab.user.entities.User;

@RequestScoped
public class SongPersistenceRepository implements SongRepository {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    // @Inject
    // public SongPersistenceRepository(EntityManager em){
    //     this.em = em;
    // }

    @Override
    public Optional<Song> find(UUID id) {
        return Optional.ofNullable(em.find(Song.class, id));
    }

    @Override  
    public List<Song> findByAlbum(Album album) {
        return em.createQuery("select c from Song c where c.album = :album", Song.class)
                .setParameter("album", album)
                .getResultList();

        // return store.getSongs().stream().filter(entity -> album.equals(entity.getAlbum()))
        // .collect(Collectors.toList());
    }

    @Override  
    public List<Song> findByUser(User user) {

        return em.createQuery("select c from Song c where c.user = :user", Song.class)
                .setParameter("user", user)
                .getResultList();
        // return store.getSongs().stream().filter(entity -> user.equals(entity.getOwner()))
        // .collect(Collectors.toList());
    }


    @Override
    public List<Song> findAll() {
        return em.createQuery("select c from Song c", Song.class).getResultList();
       
    }

    @Override
    public void create(Song entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Song entity) {
        em.remove(em.find(Song.class, entity.getId()));
    }

    @Override
    public void update(Song entity) {
        // store.getSongs().removeIf(oldEntity -> oldEntity.getId().equals(entity.getId())); 
        // store.getSongs().add(entity);
        em.merge(entity);
    }
    
}
