package lab.album.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lab.album.entities.Album;
import lab.contracts.Repository;

public interface AlbumRepository extends Repository<Album,UUID> {

}
