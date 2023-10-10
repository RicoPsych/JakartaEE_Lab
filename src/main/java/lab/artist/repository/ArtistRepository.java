package lab.artist.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lab.artist.entities.Artist;
import lab.contracts.Repository;

public interface ArtistRepository extends Repository<Artist,UUID> {

}
