package lab.album.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lab.album.entities.Album;
import lab.album.entities.Album.Genre;
import lab.song.entities.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAlbumResponse {

    private String name;
    private UUID id;

    //private List<_Song> songs;
    private LocalDate releaseDate;
    private Genre genre;


    public static GetAlbumResponse mapper(Album album) {
        return GetAlbumResponse.builder()
        .name(album.getName())
        .id(album.getId())
        .build();
    }
    
}
