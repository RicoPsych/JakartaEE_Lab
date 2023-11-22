package lab.song.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lab.album.entities.Album;
import lab.contracts.Repository;
import lab.song.entities.Song;
import lab.user.entities.User;

public interface SongRepository extends Repository<Song,UUID> {
    public List<Song> findByAlbum(Album album);
    public List<Song> findByUser(User user);
    public Optional<Song> findByIdAndUser(UUID id, User user);
    public List<Song> findByAlbumAndUser(Album album,User user);
}
