package lab.album.model;

import java.time.LocalDate;
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
public class AlbumEditModel {
    private String name;
    private LocalDate releaseDate;
    private Genre genre;

    public static AlbumEditModel mapper(Album album)
    {
        return AlbumEditModel.builder()
            .name(album.getName())
            .releaseDate(album.getReleaseDate())
            .genre(album.getGenre())
            .build();
    }

    public Album saveEntity(Album album){
        return Album.builder()
        .id(album.getId())
        .name(this.getName())
        .releaseDate(this.getReleaseDate())
        .genre(this.getGenre())
        //.songs(album.getSongs())
        .build();
    }
}
