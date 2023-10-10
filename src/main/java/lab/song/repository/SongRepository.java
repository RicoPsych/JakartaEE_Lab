package lab.song.repository;

import java.util.UUID;

import lab.contracts.Repository;
import lab.song.entities.Song;

public interface SongRepository extends Repository<Song,UUID> {

}
