package lab.user.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lab.filesystemaccess.FileSystemAccess;
import lab.song.entities.Song;
import lab.song.repository.SongRepository;
import lab.user.entities.User;
import lab.user.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;
    // private final SongRepository songRepository;
    private final FileSystemAccess fileSystem;
    


    public UserService(UserRepository userRepository,
                    FileSystemAccess fileSystem
                        //, SongRepository songRepository
                        ){
        this.userRepository = userRepository;
        this.fileSystem = fileSystem;
        // this.songRepository = songRepository;
    }


    public Optional<User> find(UUID id) {
        return userRepository.find(id);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public void create(User user) {
        userRepository.create(user);
    }
    public void update(User user) {
        userRepository.update(user);
    }
    public void delete(UUID id) {
        fileSystem.deleteAvatar(id);
        userRepository.delete(userRepository.find(id).orElseThrow());
    }

    public byte[] getAvatar(UUID id){
        return fileSystem.getAvatar(id);
    } 
    public void updateAvatar(UUID id, InputStream is) {
        userRepository.find(id).ifPresent(user -> {
            //try {
                fileSystem.writeAvatar(id, is);
//                user.setAvatar(is.readAllBytes());
              //  userRepository.update(user);
            //} catch (IOException ex) {
             //   throw new IllegalStateException(ex);
           // }
        });
    }

    public void updateAvatar(UUID id, byte[] is) {
        userRepository.find(id).ifPresent(user -> {
            //try {
                fileSystem.writeAvatar(id, is);
//                user.setAvatar(is.readAllBytes());
              //  userRepository.update(user);
            //} catch (IOException ex) {
             //   throw new IllegalStateException(ex);
           // }
        });
    }

  public void deleteAvatar(UUID id) {
        userRepository.find(id).ifPresent(user -> {
            // try {
                
            //     //userRepository.update(user);
            // } catch (IOException ex) {
            //     throw new IllegalStateException(ex);
            // }
        fileSystem.deleteAvatar(id);

        });
    }


}
