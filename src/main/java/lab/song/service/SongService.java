package lab.song.service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lab.song.entities.Song;
import lab.song.repository.SongRepository;
import lab.user.entities.User;
import lombok.NoArgsConstructor;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class SongService {
    private final SongRepository songRepository;

    @Inject
    public SongService(SongRepository songRepository){
        this.songRepository = songRepository;
    }

    public Optional<Song> find(UUID id) {
        return songRepository.find(id);
    }
    public List<Song> findAll() {
        return songRepository.findAll();
    }
    public void create(Song song) {
        songRepository.create(song);
    }
    public void update(Song song) {
        songRepository.update(song);
    }
    public void delete(UUID id) {
        songRepository.delete(songRepository.find(id).orElseThrow());
    }

}
