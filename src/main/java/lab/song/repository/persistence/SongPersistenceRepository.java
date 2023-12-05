package lab.song.repository.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lab.album.entities.Album;
import lab.song.entities.Song;
import lab.song.entities.Song_;
import lab.song.repository.SongRepository;
import lab.user.entities.User;

@Dependent
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
        // return em.createQuery("select c from Song c where c.album = :album", Song.class)
        //         .setParameter("album", album)
        //         .getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Song> query = cb.createQuery(Song.class);
        Root<Song> root = query.from(Song.class);
        query.select(root)
                .where(cb.equal(root.get(Song_.album), album));
        return em.createQuery(query).getResultList();

        // return store.getSongs().stream().filter(entity -> album.equals(entity.getAlbum()))
        // .collect(Collectors.toList());
    }

    @Override  
    public List<Song> findByUser(User user) {
        // return em.createQuery("select c from Song c where c.owner = :owner", Song.class)
        //         .setParameter("owner", user)
        //         .getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Song> query = cb.createQuery(Song.class);
        Root<Song> root = query.from(Song.class);
        query.select(root)
                .where(cb.equal(root.get(Song_.owner), user));
        return em.createQuery(query).getResultList();

        // return store.getSongs().stream().filter(entity -> user.equals(entity.getOwner()))
        // .collect(Collectors.toList());
    }

  @Override  
    public List<Song> findByAlbumAndUser(Album album,User user) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Song> query = cb.createQuery(Song.class);
        Root<Song> root = query.from(Song.class);
        query.select(root)
                .where(cb.and(
                    cb.equal(root.get(Song_.album), album),
                    cb.equal(root.get(Song_.owner), user)
                    ));
        return em.createQuery(query).getResultList();

        // return em.createQuery("select c from Song c where c.owner = :owner and c.album = :album", Song.class)
        //         .setParameter("owner", user)
        //         .setParameter("album", album)
        //         .getResultList();
        // return store.getSongs().stream().filter(entity -> user.equals(entity.getOwner()))
        // .collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findByIdAndUser(UUID id, User user) {
        try {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Song> query = cb.createQuery(Song.class);
        Root<Song> root = query.from(Song.class);
        query.select(root)
                .where(cb.and(
                    cb.equal(root.get(Song_.id), id),
                    cb.equal(root.get(Song_.owner), user)
                    ));
        return Optional.of(em.createQuery(query).getSingleResult());

            // return Optional.of(em.createQuery("select c from Song c where c.id = :id and c.owner = :owner", Song.class)
            //         .setParameter("owner", user)
            //         .setParameter("id", id)
            //         .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Song> findAll() {
       // return em.createQuery("select c from Song c", Song.class).getResultList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Song> query = cb.createQuery(Song.class);
        Root<Song> root = query.from(Song.class);
        query.select(root);
        return em.createQuery(query).getResultList();
    }
    @Override
    public List<Song> findByFilter(UUID id, User user, String name, Float rating, Album album,
    Integer duration, Long version, LocalDateTime updateDateTime, LocalDateTime creationDateTime) {
       // return em.createQuery("select c from Song c", Song.class).getResultList();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Song> query = cb.createQuery(Song.class);
        Root<Song> root = query.from(Song.class);

        
        List<Predicate> predicates = new ArrayList<>();
        if (name != null) predicates.add(cb.equal(root.get(Song_.name), name));
        if (user != null) predicates.add(cb.equal(root.get(Song_.owner), user));
        if (rating != null)predicates.add(cb.equal(root.get(Song_.rating), rating));
        if (album != null)predicates.add(cb.equal(root.get(Song_.album), album));      
        if (duration != null)predicates.add(cb.equal(root.get(Song_.duration), duration));
        if (version != null)predicates.add(cb.equal(root.get(Song_.version), version));
        if (updateDateTime != null)predicates.add(cb.equal(root.get(Song_.updateDateTime), updateDateTime));
        if (creationDateTime != null)predicates.add(cb.equal(root.get(Song_.creationDateTime), creationDateTime));
        
        query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));        
        return em.createQuery(query).getResultList();
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
