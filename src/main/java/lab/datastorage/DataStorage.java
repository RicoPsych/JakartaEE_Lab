package lab.datastorage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import lab.album.entities.Album;
import lab.artist.entities.Artist;
import lab.serialization.component.CloningUtility;
import lab.song.entities.Song;
import lab.user.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Getter
@Log
@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataStorage {
    private final Set<User> users = new HashSet<>();
    private final Set<Artist> artists = new HashSet<>();
    private final Set<Song> songs = new HashSet<>();
    private final Set<Album> albums = new HashSet<>();
        
    //private final CloningUtility cloningUtility;

    // public DataStorage(CloningUtility cloningUtility) {
    //     this.cloningUtility = cloningUtility;
    // }


    // public synchronized List<User> findAllUsers(){
    //     return users.stream()
    //             .map(cloningUtility::clone)
    //             .collect(Collectors.toList());
    // }

   
    // public synchronized void createUser(User value) throws IllegalArgumentException {
    //     if (users.stream().anyMatch(user -> user.getId().equals(value.getId()))) {
    //         throw new IllegalArgumentException("The user id \"%s\" is not unique".formatted(value.getId()));
    //     }
    //     User entity = cloneWithRelationships(value);
    //     users.add(entity);
    // }

 
    // public synchronized void updateUser(User value) throws IllegalArgumentException {
    //     User entity = cloneWithRelationships(value);
    //     if (users.removeIf(user -> user.getId().equals(value.getId()))) {
    //         users.add(entity);
    //     } else {
    //         throw new IllegalArgumentException("The User with id \"%s\" does not exist".formatted(value.getId()));
    //     }
    // }

    // public synchronized void deleteUser(UUID id) throws IllegalArgumentException {
    //     if (!users.removeIf(user -> user.getId().equals(id))) {
    //         throw new IllegalArgumentException("The User with id \"%s\" does not exist".formatted(id));
    //     }
    // }




    // public synchronized List<Song> findAllSongs() {
    //     return songs.stream()
    //             .map(cloningUtility::clone)
    //             .collect(Collectors.toList());
    // }

    // public synchronized void createSong(Song value) throws IllegalArgumentException {
    //     if (songs.stream().anyMatch(song -> song.getId().equals(value.getId()))) {
    //         throw new IllegalArgumentException("The song id \"%s\" is not unique".formatted(value.getId()));
    //     }
    //     Song entity = cloneWithRelationships(value);
    //     songs.add(entity);
    // }

 
    // public synchronized void updateSong(Song value) throws IllegalArgumentException {
    //     Song entity = cloneWithRelationships(value);
    //     if (songs.removeIf(song -> song.getId().equals(value.getId()))) {
    //         songs.add(entity);
    //     } else {
    //         throw new IllegalArgumentException("The Song with id \"%s\" does not exist".formatted(value.getId()));
    //     }
    // }

    // public synchronized void deleteSong(UUID id) throws IllegalArgumentException {
    //     if (!songs.removeIf(song -> song.getId().equals(id))) {
    //         throw new IllegalArgumentException("The Song with id \"%s\" does not exist".formatted(id));
    //     }
    // }



    // private User cloneWithRelationships(User value) {
    //     User entity = cloningUtility.clone(value);

    //     if (entity.getFavourites() != null) {

    //     }
    //     return entity;
    // }

}
