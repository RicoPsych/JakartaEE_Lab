package lab.album.model;

import java.time.LocalDate;
import java.util.UUID;

import lab.album.entities.Album;
import lab.album.entities.Album.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AlbumCreateModel {
    private UUID id;
    private String name;
    private LocalDate releaseDate;
    private Genre genre;

    public static AlbumCreateModel mapper(Album album)
    {
        return AlbumCreateModel.builder()
            .name(album.getName())
            .releaseDate(album.getReleaseDate())
            .genre(album.getGenre())
            .build();
    }

    public Album saveEntity(){
        return Album.builder()
        .id(this.getId())
        .name(this.getName())
        .releaseDate(this.getReleaseDate())
        .genre(this.getGenre())
        //.songs(album.getSongs())
        .build();
    }
}
